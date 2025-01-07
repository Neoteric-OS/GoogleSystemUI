package com.android.systemui.statusbar;

import android.R;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RemoteViews;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.LinearInterpolator;
import androidx.core.animation.ObjectAnimator;
import androidx.core.animation.ValueAnimator;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.RemoteInputControllerLogger;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.RemoteInputUriController;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.systemui.statusbar.policy.RemoteInputView$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.ListenerSet;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRemoteInputManager implements CoreStartable {
    public static final boolean ENABLE_REMOTE_INPUT = SystemProperties.getBoolean("debug.enable_remote_input", true);
    public static final boolean FORCE_REMOTE_INPUT_HISTORY = SystemProperties.getBoolean("debug.force_remoteinput_history", true);
    public StatusBarRemoteInputCallback mCallback;
    public final NotificationClickNotifier mClickNotifier;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardManager mKeyguardManager;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final ActionClickLogger mLogger;
    public final PowerInteractor mPowerInteractor;
    public RemoteInputController mRemoteInputController;
    public final RemoteInputControllerLogger mRemoteInputControllerLogger;
    public RemoteInputCoordinator mRemoteInputListener;
    public final RemoteInputUriController mRemoteInputUriController;
    public final ShadeInteractor mShadeInteractor;
    public final SmartReplyController mSmartReplyController;
    public final StatusBarStateController mStatusBarStateController;
    public final UserManager mUserManager;
    public final NotificationVisibilityProvider mVisibilityProvider;
    public final List mControllerCallbacks = new ArrayList();
    public final ListenerSet mActionPressListeners = new ListenerSet();
    public final AnonymousClass1 mInteractionHandler = new AnonymousClass1();
    public final IStatusBarService mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.NotificationRemoteInputManager$1, reason: invalid class name */
    public final class AnonymousClass1 implements RemoteViews.InteractionHandler {
        public AnonymousClass1() {
        }

        public static Notification.Action getActionFromView(View view, NotificationEntry notificationEntry, PendingIntent pendingIntent) {
            Integer num = (Integer) view.getTag(R.id.notification_top_line);
            if (num == null) {
                return null;
            }
            if (notificationEntry == null) {
                Log.w("NotifRemoteInputManager", "Couldn't determine notification for click.");
                return null;
            }
            StatusBarNotification statusBarNotification = notificationEntry.mSbn;
            Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
            if (actionArr == null || num.intValue() >= actionArr.length) {
                Log.w("NotifRemoteInputManager", "statusBarNotification.getNotification().actions is null or invalid");
                return null;
            }
            Notification.Action action = statusBarNotification.getNotification().actions[num.intValue()];
            if (Objects.equals(action.actionIntent, pendingIntent)) {
                return action;
            }
            Log.w("NotifRemoteInputManager", "actionIntent does not match");
            return null;
        }

        public final boolean onInteraction(View view, final PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
            NotificationEntry notificationEntry;
            boolean activateRemoteInput;
            NotificationListenerService.Ranking ranking;
            NotificationChannel channel;
            NotificationRemoteInputManager.this.mPowerInteractor.wakeUpIfDozing(4, "NOTIFICATION_CLICK");
            final Integer num = (Integer) view.getTag(R.id.notification_top_line);
            ViewParent parent = view.getParent();
            while (true) {
                if (parent == null) {
                    notificationEntry = null;
                    break;
                }
                if (parent instanceof ExpandableNotificationRow) {
                    notificationEntry = ((ExpandableNotificationRow) parent).mEntry;
                    break;
                }
                parent = parent.getParent();
            }
            ActionClickLogger actionClickLogger = NotificationRemoteInputManager.this.mLogger;
            actionClickLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            ActionClickLogger$logInitialClick$2 actionClickLogger$logInitialClick$2 = new Function1() { // from class: com.android.systemui.statusbar.ActionClickLogger$logInitialClick$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String str1 = logMessage.getStr1();
                    String str2 = logMessage.getStr2();
                    String str3 = logMessage.getStr3();
                    int int1 = logMessage.getInt1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("ACTION CLICK ", str1, " (channel=", str2, ") for pending intent ");
                    m.append(str3);
                    m.append(" at index ");
                    m.append(int1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = actionClickLogger.buffer;
            LogMessage obtain = logBuffer.obtain("ActionClickLogger", logLevel, actionClickLogger$logInitialClick$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = notificationEntry != null ? notificationEntry.mKey : null;
            logMessageImpl.str2 = (notificationEntry == null || (ranking = notificationEntry.mRanking) == null || (channel = ranking.getChannel()) == null) ? null : channel.getId();
            logMessageImpl.str3 = pendingIntent.toString();
            logMessageImpl.int1 = num != null ? num.intValue() : Integer.MIN_VALUE;
            logBuffer.commit(obtain);
            if ((NotificationRemoteInputManager.this.mCallback.mDisabled2 & 4) != 0) {
                activateRemoteInput = true;
            } else {
                Object tag = view.getTag(R.id.resolver_empty_state_subtitle);
                RemoteInput[] remoteInputArr = tag instanceof RemoteInput[] ? (RemoteInput[]) tag : null;
                if (remoteInputArr != null) {
                    RemoteInput remoteInput = null;
                    for (RemoteInput remoteInput2 : remoteInputArr) {
                        if (remoteInput2.getAllowFreeFormInput()) {
                            remoteInput = remoteInput2;
                        }
                    }
                    if (remoteInput != null) {
                        activateRemoteInput = NotificationRemoteInputManager.this.activateRemoteInput(view, remoteInputArr, remoteInput, pendingIntent, null);
                    }
                }
                activateRemoteInput = false;
            }
            if (activateRemoteInput) {
                ActionClickLogger actionClickLogger2 = NotificationRemoteInputManager.this.mLogger;
                actionClickLogger2.getClass();
                LogLevel logLevel2 = LogLevel.DEBUG;
                ActionClickLogger$logRemoteInputWasHandled$2 actionClickLogger$logRemoteInputWasHandled$2 = new Function1() { // from class: com.android.systemui.statusbar.ActionClickLogger$logRemoteInputWasHandled$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("  [Action click] Triggered remote input (for ", logMessage.getStr1(), ") at index ", logMessage.getInt1());
                    }
                };
                LogBuffer logBuffer2 = actionClickLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("ActionClickLogger", logLevel2, actionClickLogger$logRemoteInputWasHandled$2, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                logMessageImpl2.str1 = notificationEntry != null ? notificationEntry.mKey : null;
                logMessageImpl2.int1 = num != null ? num.intValue() : Integer.MIN_VALUE;
                logBuffer2.commit(obtain2);
                return true;
            }
            Notification.Action actionFromView = getActionFromView(view, notificationEntry, pendingIntent);
            if (actionFromView != null) {
                ViewParent parent2 = view.getParent();
                String key = notificationEntry.mSbn.getKey();
                int indexOfChild = (view.getId() == 16908741 && parent2 != null && (parent2 instanceof ViewGroup)) ? ((ViewGroup) parent2).indexOfChild(view) : -1;
                NotificationVisibility obtain3 = ((NotificationVisibilityProviderImpl) NotificationRemoteInputManager.this.mVisibilityProvider).obtain(notificationEntry);
                NotificationClickNotifier notificationClickNotifier = NotificationRemoteInputManager.this.mClickNotifier;
                notificationClickNotifier.backgroundExecutor.execute(new NotificationClickNotifier$onNotificationActionClick$1(notificationClickNotifier, key, indexOfChild, actionFromView, obtain3, false));
                notificationClickNotifier.mainExecutor.execute(new NotificationClickNotifier$onNotificationClick$1(notificationClickNotifier, key, 1));
            }
            try {
                ActivityManager.getService().resumeAppSwitches();
            } catch (RemoteException unused) {
            }
            Notification.Action actionFromView2 = getActionFromView(view, notificationEntry, pendingIntent);
            final StatusBarRemoteInputCallback statusBarRemoteInputCallback = NotificationRemoteInputManager.this.mCallback;
            boolean isAuthenticationRequired = actionFromView2 == null ? false : actionFromView2.isAuthenticationRequired();
            final NotificationRemoteInputManager$1$$ExternalSyntheticLambda0 notificationRemoteInputManager$1$$ExternalSyntheticLambda0 = new NotificationRemoteInputManager$1$$ExternalSyntheticLambda0(this, remoteResponse, view, notificationEntry, pendingIntent, num);
            statusBarRemoteInputCallback.getClass();
            if (!pendingIntent.isActivity() && !isAuthenticationRequired) {
                return notificationRemoteInputManager$1$$ExternalSyntheticLambda0.handleClick();
            }
            ActionClickLogger actionClickLogger3 = statusBarRemoteInputCallback.mActionClickLogger;
            actionClickLogger3.getClass();
            LogLevel logLevel3 = LogLevel.DEBUG;
            ActionClickLogger$logWaitingToCloseKeyguard$2 actionClickLogger$logWaitingToCloseKeyguard$2 = new Function1() { // from class: com.android.systemui.statusbar.ActionClickLogger$logWaitingToCloseKeyguard$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "  [Action click] Intent " + logMessage.getStr1() + " at index " + logMessage.getInt1() + " launches an activity, dismissing keyguard first...";
                }
            };
            LogBuffer logBuffer3 = actionClickLogger3.buffer;
            LogMessage obtain4 = logBuffer3.obtain("ActionClickLogger", logLevel3, actionClickLogger$logWaitingToCloseKeyguard$2, null);
            LogMessageImpl logMessageImpl3 = (LogMessageImpl) obtain4;
            logMessageImpl3.str1 = pendingIntent.toString();
            logMessageImpl3.int1 = num != null ? num.intValue() : Integer.MIN_VALUE;
            logBuffer3.commit(obtain4);
            statusBarRemoteInputCallback.mActivityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda1
                @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                public final boolean onDismiss() {
                    PendingIntent pendingIntent2 = pendingIntent;
                    StatusBarRemoteInputCallback statusBarRemoteInputCallback2 = StatusBarRemoteInputCallback.this;
                    statusBarRemoteInputCallback2.mActionClickLogger.logKeyguardGone(pendingIntent2, num);
                    try {
                        ActivityManager.getService().resumeAppSwitches();
                    } catch (RemoteException unused2) {
                    }
                    if (!notificationRemoteInputManager$1$$ExternalSyntheticLambda0.handleClick()) {
                        return false;
                    }
                    statusBarRemoteInputCallback2.mShadeController.closeShadeIfOpen();
                    return false;
                }
            }, null, statusBarRemoteInputCallback.mActivityIntentHelper.getPendingTargetActivityInfo(pendingIntent, ((NotificationLockscreenUserManagerImpl) statusBarRemoteInputCallback.mLockscreenUserManager).mCurrentUserId) == null);
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface BouncerChecker {
        boolean showBouncerIfNecessary();
    }

    public NotificationRemoteInputManager(Context context, NotificationLockscreenUserManager notificationLockscreenUserManager, SmartReplyController smartReplyController, NotificationVisibilityProvider notificationVisibilityProvider, PowerInteractor powerInteractor, StatusBarStateController statusBarStateController, RemoteInputUriController remoteInputUriController, RemoteInputControllerLogger remoteInputControllerLogger, NotificationClickNotifier notificationClickNotifier, ActionClickLogger actionClickLogger, JavaAdapter javaAdapter, ShadeInteractor shadeInteractor) {
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mSmartReplyController = smartReplyController;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mPowerInteractor = powerInteractor;
        this.mLogger = actionClickLogger;
        this.mJavaAdapter = javaAdapter;
        this.mShadeInteractor = shadeInteractor;
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mKeyguardManager = (KeyguardManager) context.getSystemService(KeyguardManager.class);
        this.mStatusBarStateController = statusBarStateController;
        this.mRemoteInputUriController = remoteInputUriController;
        this.mRemoteInputControllerLogger = remoteInputControllerLogger;
        this.mClickNotifier = notificationClickNotifier;
    }

    public final boolean activateRemoteInput(final View view, final RemoteInput[] remoteInputArr, final RemoteInput remoteInput, final PendingIntent pendingIntent, final NotificationEntry.EditedSuggestionInfo editedSuggestionInfo) {
        RemoteInputView remoteInputView;
        ExpandableNotificationRow expandableNotificationRow;
        UserInfo profileParent;
        int i = 0;
        ViewParent parent = view.getParent();
        while (true) {
            if (parent == null) {
                remoteInputView = null;
                expandableNotificationRow = null;
                break;
            }
            if (parent instanceof View) {
                View view2 = (View) parent;
                if (view2.getId() == 16909611) {
                    remoteInputView = (RemoteInputView) view2.findViewWithTag(RemoteInputView.VIEW_TAG);
                    expandableNotificationRow = (ExpandableNotificationRow) view2.getTag(com.android.wm.shell.R.id.row_tag_for_content_view);
                    break;
                }
            }
            parent = parent.getParent();
        }
        if (expandableNotificationRow == null) {
            return false;
        }
        expandableNotificationRow.setUserExpanded(true, false);
        int identifier = pendingIntent.getCreatorUserHandle().getIdentifier();
        boolean z = this.mUserManager.getUserInfo(identifier).isManagedProfile() && this.mKeyguardManager.isDeviceLocked(identifier);
        boolean z2 = z && (profileParent = this.mUserManager.getProfileParent(identifier)) != null && this.mKeyguardManager.isDeviceLocked(profileParent.id);
        if (((NotificationLockscreenUserManagerImpl) this.mLockscreenUserManager).isLockscreenPublicMode(identifier) || this.mStatusBarStateController.getState() == 1) {
            if (!z || z2) {
                StatusBarRemoteInputCallback statusBarRemoteInputCallback = this.mCallback;
                statusBarRemoteInputCallback.getClass();
                if (!expandableNotificationRow.mIsPinned) {
                    ((StatusBarStateControllerImpl) statusBarRemoteInputCallback.mStatusBarStateController).mLeaveOpenOnKeyguardHide = true;
                }
                StatusBarKeyguardViewManager statusBarKeyguardViewManager = statusBarRemoteInputCallback.mStatusBarKeyguardViewManager;
                AlternateBouncerInteractor alternateBouncerInteractor = statusBarKeyguardViewManager.mAlternateBouncerInteractor;
                if (((Boolean) alternateBouncerInteractor.canShowAlternateBouncer.getValue()).booleanValue()) {
                    Log.d("StatusBarKeyguardViewManager", "showBouncer:alternateBouncer.forceShow()");
                    alternateBouncerInteractor.bouncerRepository.setAlternateVisible(true);
                    statusBarKeyguardViewManager.updateAlternateBouncerShowing(alternateBouncerInteractor.isVisibleState());
                } else {
                    statusBarKeyguardViewManager.showPrimaryBouncer(true);
                }
                statusBarRemoteInputCallback.mPendingRemoteInputView = view;
            } else {
                StatusBarRemoteInputCallback statusBarRemoteInputCallback2 = this.mCallback;
                statusBarRemoteInputCallback2.mCommandQueue.animateCollapsePanels();
                statusBarRemoteInputCallback2.startWorkChallengeIfNecessary(identifier, null, null);
                statusBarRemoteInputCallback2.mPendingWorkRemoteInputView = view;
            }
        } else {
            if (!z) {
                if (remoteInputView != null && !remoteInputView.isAttachedToWindow()) {
                    remoteInputView = null;
                }
                if (remoteInputView == null) {
                    View view3 = expandableNotificationRow.mPrivateLayout.mExpandedChild;
                    remoteInputView = view3 == null ? null : (RemoteInputView) view3.findViewWithTag(RemoteInputView.VIEW_TAG);
                    if (remoteInputView == null) {
                        return false;
                    }
                }
                NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                if (remoteInputView == notificationContentView.mExpandedRemoteInput && !notificationContentView.mExpandedChild.isShown()) {
                    StatusBarRemoteInputCallback statusBarRemoteInputCallback3 = this.mCallback;
                    Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.NotificationRemoteInputManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            NotificationRemoteInputManager.this.activateRemoteInput(view, remoteInputArr, remoteInput, pendingIntent, editedSuggestionInfo);
                        }
                    };
                    if (((KeyguardStateControllerImpl) statusBarRemoteInputCallback3.mKeyguardStateController).mShowing) {
                        if (!expandableNotificationRow.mIsPinned) {
                            ((StatusBarStateControllerImpl) statusBarRemoteInputCallback3.mStatusBarStateController).mLeaveOpenOnKeyguardHide = true;
                        }
                        StatusBarKeyguardViewManager statusBarKeyguardViewManager2 = statusBarRemoteInputCallback3.mStatusBarKeyguardViewManager;
                        AlternateBouncerInteractor alternateBouncerInteractor2 = statusBarKeyguardViewManager2.mAlternateBouncerInteractor;
                        if (((Boolean) alternateBouncerInteractor2.canShowAlternateBouncer.getValue()).booleanValue()) {
                            Log.d("StatusBarKeyguardViewManager", "showBouncer:alternateBouncer.forceShow()");
                            alternateBouncerInteractor2.bouncerRepository.setAlternateVisible(true);
                            statusBarKeyguardViewManager2.updateAlternateBouncerShowing(alternateBouncerInteractor2.isVisibleState());
                        } else {
                            statusBarKeyguardViewManager2.showPrimaryBouncer(true);
                        }
                        statusBarRemoteInputCallback3.mPendingRemoteInputView = view;
                    } else {
                        if (expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.mChildrenExpanded) {
                            NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
                            GroupExpansionManagerImpl groupExpansionManagerImpl = statusBarRemoteInputCallback3.mGroupExpansionManager;
                            groupExpansionManagerImpl.setGroupExpanded(notificationEntry, !groupExpansionManagerImpl.isGroupExpanded(notificationEntry));
                            groupExpansionManagerImpl.isGroupExpanded(notificationEntry);
                        }
                        expandableNotificationRow.setUserExpanded(true, false);
                        NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
                        notificationContentView2.mExpandedVisibleListener = runnable;
                        notificationContentView2.fireExpandedVisibleListenerIfVisible();
                    }
                    return true;
                }
                if (!remoteInputView.isAttachedToWindow()) {
                    return false;
                }
                RemoteInputViewControllerImpl remoteInputViewControllerImpl = remoteInputView.mViewController;
                remoteInputViewControllerImpl.pendingIntent = pendingIntent;
                remoteInputViewControllerImpl.setRemoteInput(remoteInput);
                RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = remoteInputView.mViewController;
                remoteInputViewControllerImpl2.remoteInputs = remoteInputArr;
                NotificationEntry notificationEntry2 = remoteInputViewControllerImpl2.entry;
                notificationEntry2.editedSuggestionInfo = editedSuggestionInfo;
                if (editedSuggestionInfo != null) {
                    notificationEntry2.remoteInputText = editedSuggestionInfo.originalText;
                    notificationEntry2.remoteInputAttachment = null;
                }
                if (remoteInputView.getVisibility() != 0) {
                    remoteInputView.mIsAnimatingAppearance = true;
                    remoteInputView.setAlpha(0.0f);
                    ViewGroup viewGroup = (ViewGroup) remoteInputView.getParent();
                    View findViewById = viewGroup != null ? viewGroup.findViewById(R.id.alerted_icon) : null;
                    AnimatorSet animatorSet = new AnimatorSet();
                    Property property = View.ALPHA;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(remoteInputView, property, 0.0f, 1.0f);
                    ofFloat.setStartDelay(33L);
                    ofFloat.setDuration(83L);
                    LinearInterpolator linearInterpolator = InterpolatorsAndroidX.LINEAR;
                    ofFloat.setInterpolator(linearInterpolator);
                    ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.5f, 1.0f);
                    ofFloat2.addUpdateListener(new RemoteInputView$$ExternalSyntheticLambda1(remoteInputView, ofFloat2, i));
                    ofFloat2.setDuration(360L);
                    ofFloat2.setInterpolator(InterpolatorsAndroidX.FAST_OUT_SLOW_IN);
                    if (findViewById == null) {
                        animatorSet.playTogether(ofFloat, ofFloat2);
                    } else {
                        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(findViewById, property, 1.0f, 0.0f);
                        ofFloat3.setDuration(50L);
                        ofFloat3.setInterpolator(linearInterpolator);
                        animatorSet.addListener(new RemoteInputView.AnonymousClass3(findViewById));
                        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
                    }
                    animatorSet.addListener(new RemoteInputView.AnonymousClass3(remoteInputView, i));
                    animatorSet.start();
                }
                remoteInputView.focus();
                return true;
            }
            StatusBarRemoteInputCallback statusBarRemoteInputCallback4 = this.mCallback;
            statusBarRemoteInputCallback4.mCommandQueue.animateCollapsePanels();
            statusBarRemoteInputCallback4.startWorkChallengeIfNecessary(identifier, null, null);
            statusBarRemoteInputCallback4.mPendingWorkRemoteInputView = view;
        }
        return true;
    }

    public final void closeRemoteInputs() {
        ExpandableNotificationRow expandableNotificationRow;
        RemoteInputController remoteInputController = this.mRemoteInputController;
        if (remoteInputController == null || remoteInputController.mOpen.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(remoteInputController.mOpen.size());
        for (int size = remoteInputController.mOpen.size() - 1; size >= 0; size--) {
            NotificationEntry notificationEntry = (NotificationEntry) ((WeakReference) ((Pair) remoteInputController.mOpen.get(size)).first).get();
            if (notificationEntry != null && notificationEntry.rowExists()) {
                arrayList.add(notificationEntry);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            NotificationEntry notificationEntry2 = (NotificationEntry) arrayList.get(size2);
            if (notificationEntry2.rowExists() && (expandableNotificationRow = notificationEntry2.row) != null) {
                for (NotificationContentView notificationContentView : expandableNotificationRow.mLayouts) {
                    RemoteInputView remoteInputView = notificationContentView.mHeadsUpRemoteInput;
                    if (remoteInputView != null) {
                        RemoteInputView.RemoteEditText remoteEditText = remoteInputView.mEditText;
                        int i = RemoteInputView.RemoteEditText.$r8$clinit;
                        remoteEditText.defocusIfNeeded(false);
                    }
                    RemoteInputView remoteInputView2 = notificationContentView.mExpandedRemoteInput;
                    if (remoteInputView2 != null) {
                        RemoteInputView.RemoteEditText remoteEditText2 = remoteInputView2.mEditText;
                        int i2 = RemoteInputView.RemoteEditText.$r8$clinit;
                        remoteEditText2.defocusIfNeeded(false);
                    }
                }
            }
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        final PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        if (this.mRemoteInputController != null) {
            asIndenting.println("mRemoteInputController: " + this.mRemoteInputController);
            asIndenting.increaseIndent();
            final RemoteInputController remoteInputController = this.mRemoteInputController;
            remoteInputController.getClass();
            asIndenting.print("mLastAppliedRemoteInputActive: ");
            asIndenting.println(remoteInputController.mLastAppliedRemoteInputActive);
            asIndenting.print("isRemoteInputActive: ");
            asIndenting.println(remoteInputController.isRemoteInputActive$1());
            asIndenting.println("mOpen: " + remoteInputController.mOpen.size());
            final int i = 0;
            DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.RemoteInputController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            RemoteInputController remoteInputController2 = remoteInputController;
                            IndentingPrintWriter indentingPrintWriter = asIndenting;
                            Iterator it = remoteInputController2.mOpen.iterator();
                            while (it.hasNext()) {
                                NotificationEntry notificationEntry = (NotificationEntry) ((WeakReference) ((Pair) it.next()).first).get();
                                indentingPrintWriter.println(notificationEntry == null ? "???" : notificationEntry.mKey);
                            }
                            break;
                        default:
                            RemoteInputController remoteInputController3 = remoteInputController;
                            IndentingPrintWriter indentingPrintWriter2 = asIndenting;
                            Iterator it2 = remoteInputController3.mSpinning.keySet().iterator();
                            while (it2.hasNext()) {
                                indentingPrintWriter2.println((String) it2.next());
                            }
                            break;
                    }
                }
            });
            asIndenting.println("mSpinning: " + remoteInputController.mSpinning.size());
            final int i2 = 1;
            DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.RemoteInputController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            RemoteInputController remoteInputController2 = remoteInputController;
                            IndentingPrintWriter indentingPrintWriter = asIndenting;
                            Iterator it = remoteInputController2.mOpen.iterator();
                            while (it.hasNext()) {
                                NotificationEntry notificationEntry = (NotificationEntry) ((WeakReference) ((Pair) it.next()).first).get();
                                indentingPrintWriter.println(notificationEntry == null ? "???" : notificationEntry.mKey);
                            }
                            break;
                        default:
                            RemoteInputController remoteInputController3 = remoteInputController;
                            IndentingPrintWriter indentingPrintWriter2 = asIndenting;
                            Iterator it2 = remoteInputController3.mSpinning.keySet().iterator();
                            while (it2.hasNext()) {
                                indentingPrintWriter2.println((String) it2.next());
                            }
                            break;
                    }
                }
            });
            asIndenting.println(remoteInputController.mSpinning);
            asIndenting.print("mDelegate: ");
            asIndenting.println(remoteInputController.mDelegate);
            asIndenting.decreaseIndent();
        }
        RemoteInputCoordinator remoteInputCoordinator = this.mRemoteInputListener;
        if (remoteInputCoordinator != null) {
            asIndenting.println("mRemoteInputListener: ".concat(remoteInputCoordinator.getClass().getSimpleName()));
            asIndenting.increaseIndent();
            this.mRemoteInputListener.dump(asIndenting, strArr);
            asIndenting.decreaseIndent();
        }
    }

    public final boolean isNotificationKeptForRemoteInputHistory(String str) {
        RemoteInputCoordinator remoteInputCoordinator = this.mRemoteInputListener;
        return remoteInputCoordinator != null && (remoteInputCoordinator.mRemoteInputHistoryExtender.mEntriesExtended.containsKey(str) || remoteInputCoordinator.mSmartReplyHistoryExtender.mEntriesExtended.containsKey(str));
    }

    public final boolean isRemoteInputActive() {
        RemoteInputController remoteInputController = this.mRemoteInputController;
        return remoteInputController != null && remoteInputController.isRemoteInputActive$1();
    }

    public final void onPanelCollapsed() {
        RemoteInputCoordinator remoteInputCoordinator = this.mRemoteInputListener;
        if (remoteInputCoordinator != null) {
            RemoteInputCoordinator.RemoteInputActiveExtender remoteInputActiveExtender = remoteInputCoordinator.mRemoteInputActiveExtender;
            List<NotificationEntry> list = CollectionsKt.toList(remoteInputActiveExtender.mEntriesExtended.values());
            if (remoteInputActiveExtender.debug) {
                Log.d("RemoteInputCoordinator", remoteInputActiveExtender.name + ".endAllLifetimeExtensions() entries=" + list);
            }
            remoteInputActiveExtender.mEntriesExtended.clear();
            remoteInputActiveExtender.warnIfEnding();
            remoteInputActiveExtender.mEnding = true;
            for (NotificationEntry notificationEntry : list) {
                NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12 = remoteInputActiveExtender.mCallback;
                if (notifCollection$$ExternalSyntheticLambda12 == null) {
                    notifCollection$$ExternalSyntheticLambda12 = null;
                }
                notifCollection$$ExternalSyntheticLambda12.onEndLifetimeExtension(notificationEntry, remoteInputActiveExtender);
            }
            remoteInputActiveExtender.mEnding = false;
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mJavaAdapter.alwaysCollectFlow(((ShadeInteractorImpl) this.mShadeInteractor).baseShadeInteractor.isAnyExpanded(), new Consumer() { // from class: com.android.systemui.statusbar.NotificationRemoteInputManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationRemoteInputManager notificationRemoteInputManager = NotificationRemoteInputManager.this;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (booleanValue && notificationRemoteInputManager.mStatusBarStateController.getState() != 1) {
                    try {
                        notificationRemoteInputManager.mBarService.clearNotificationEffects();
                    } catch (RemoteException unused) {
                    }
                }
                if (booleanValue) {
                    notificationRemoteInputManager.getClass();
                } else {
                    notificationRemoteInputManager.onPanelCollapsed();
                }
            }
        });
    }
}
