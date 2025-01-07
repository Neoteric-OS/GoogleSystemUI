package com.android.systemui.statusbar.phone;

import android.app.ActivityOptions;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.service.dreams.IDreamManager;
import android.util.EventLog;
import android.view.RemoteAnimationAdapter;
import android.view.View;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.FrameworkStatsLog;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationClickNotifier;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.NotificationLaunchAnimatorControllerProvider;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl;
import com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarNotificationActivityStarter {
    public final ActivityIntentHelper mActivityIntentHelper;
    public final ActivityStarter mActivityStarter;
    public final ActivityTransitionAnimator mActivityTransitionAnimator;
    public final Lazy mAssistManagerLazy;
    public final Optional mBubblesManagerOptional;
    public final NotificationClickNotifier mClickNotifier;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final int mDisplayId;
    public final IDreamManager mDreamManager;
    public final HeadsUpManager mHeadsUpManager;
    public boolean mIsCollapsingToShowActivityOverLockscreen;
    public final KeyguardManager mKeyguardManager;
    public final KeyguardStateController mKeyguardStateController;
    public final LockPatternUtils mLockPatternUtils;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final StatusBarNotificationActivityStarterLogger mLogger;
    public final Handler mMainThreadHandler;
    public final MetricsLogger mMetricsLogger;
    public final NotificationLaunchAnimatorControllerProvider mNotificationAnimationProvider;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final OnUserInteractionCallbackImpl mOnUserInteractionCallback;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final PowerInteractor mPowerInteractor;
    public final StatusBarNotificationPresenter mPresenter;
    public final NotificationRemoteInputManager mRemoteInputManager;
    public final ShadeAnimationInteractor mShadeAnimationInteractor;
    public final ShadeController mShadeController;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarRemoteInputCallback mStatusBarRemoteInputCallback;
    public final Executor mUiBgExecutor;
    public final UserTracker mUserTracker;
    public final NotificationVisibilityProvider mVisibilityProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$2, reason: invalid class name */
    public final class AnonymousClass2 implements ActivityStarter.OnDismissAction {
        public final /* synthetic */ boolean val$animate;
        public final /* synthetic */ int val$appUid;
        public final /* synthetic */ Intent val$intent;
        public final /* synthetic */ ExpandableNotificationRow val$row;

        public AnonymousClass2(ExpandableNotificationRow expandableNotificationRow, boolean z, Intent intent, int i) {
            this.val$row = expandableNotificationRow;
            this.val$animate = z;
            this.val$intent = intent;
            this.val$appUid = i;
        }

        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
        public final boolean onDismiss() {
            final Intent intent = this.val$intent;
            final boolean z = this.val$animate;
            final int i = this.val$appUid;
            final ExpandableNotificationRow expandableNotificationRow = this.val$row;
            AsyncTask.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    final StatusBarNotificationActivityStarter.AnonymousClass2 anonymousClass2 = StatusBarNotificationActivityStarter.AnonymousClass2.this;
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                    boolean z2 = z;
                    final Intent intent2 = intent;
                    final int i2 = i;
                    StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = StatusBarNotificationActivityStarter.this;
                    statusBarNotificationActivityStarter.mActivityTransitionAnimator.startIntentWithAnimation(new StatusBarTransitionAnimatorController(statusBarNotificationActivityStarter.mNotificationAnimationProvider.getAnimatorController(expandableNotificationRow2), statusBarNotificationActivityStarter.mShadeAnimationInteractor, statusBarNotificationActivityStarter.mShadeController, statusBarNotificationActivityStarter.mNotificationShadeWindowController, statusBarNotificationActivityStarter.mCommandQueue, statusBarNotificationActivityStarter.mDisplayId, true), z2, intent2.getPackage(), false, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Intent intent3 = intent2;
                            StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = StatusBarNotificationActivityStarter.this;
                            return Integer.valueOf(TaskStackBuilder.create(statusBarNotificationActivityStarter2.mContext).addNextIntentWithParentStack(intent3).startActivities(CentralSurfaces.getActivityOptions(statusBarNotificationActivityStarter2.mDisplayId, (RemoteAnimationAdapter) obj), new UserHandle(UserHandle.getUserId(i2))));
                        }
                    });
                }
            });
            return true;
        }

        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
        public final boolean willRunAnimationOnKeyguard() {
            return this.val$animate;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$3, reason: invalid class name */
    public final class AnonymousClass3 implements ActivityStarter.OnDismissAction {
        public final /* synthetic */ boolean val$animate;
        public final /* synthetic */ boolean val$showHistory;
        public final /* synthetic */ View val$view;

        public AnonymousClass3(boolean z, View view, boolean z2) {
            this.val$showHistory = z;
            this.val$view = view;
            this.val$animate = z2;
        }

        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
        public final boolean onDismiss() {
            final View view = this.val$view;
            final boolean z = this.val$showHistory;
            final boolean z2 = this.val$animate;
            AsyncTask.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    final StatusBarNotificationActivityStarter.AnonymousClass3 anonymousClass3 = StatusBarNotificationActivityStarter.AnonymousClass3.this;
                    boolean z3 = z;
                    View view2 = view;
                    boolean z4 = z2;
                    Intent intent = z3 ? new Intent("android.settings.NOTIFICATION_HISTORY") : new Intent("android.settings.NOTIFICATION_SETTINGS");
                    StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = StatusBarNotificationActivityStarter.this;
                    final TaskStackBuilder addNextIntent = TaskStackBuilder.create(statusBarNotificationActivityStarter.mContext).addNextIntent(new Intent("android.settings.NOTIFICATION_SETTINGS"));
                    if (z3) {
                        addNextIntent.addNextIntent(intent);
                    }
                    GhostedViewTransitionAnimatorController fromView$default = ActivityTransitionAnimator.Controller.Companion.fromView$default(view2, 30, 28);
                    statusBarNotificationActivityStarter.mActivityTransitionAnimator.startIntentWithAnimation(fromView$default == null ? null : new StatusBarTransitionAnimatorController(fromView$default, statusBarNotificationActivityStarter.mShadeAnimationInteractor, statusBarNotificationActivityStarter.mShadeController, statusBarNotificationActivityStarter.mNotificationShadeWindowController, statusBarNotificationActivityStarter.mCommandQueue, statusBarNotificationActivityStarter.mDisplayId, true), z4, intent.getPackage(), false, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$3$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            TaskStackBuilder taskStackBuilder = addNextIntent;
                            StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = StatusBarNotificationActivityStarter.this;
                            return Integer.valueOf(taskStackBuilder.startActivities(CentralSurfaces.getActivityOptions(statusBarNotificationActivityStarter2.mDisplayId, (RemoteAnimationAdapter) obj), ((UserTrackerImpl) statusBarNotificationActivityStarter2.mUserTracker).getUserHandle()));
                        }
                    });
                }
            });
            return true;
        }

        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
        public final boolean willRunAnimationOnKeyguard() {
            return this.val$animate;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnKeyguardDismissedAction {
        boolean onDismiss(PendingIntent pendingIntent, boolean z, boolean z2, boolean z3);
    }

    public StatusBarNotificationActivityStarter(Context context, int i, Handler handler, Executor executor, NotificationVisibilityProvider notificationVisibilityProvider, HeadsUpManager headsUpManager, ActivityStarter activityStarter, CommandQueue commandQueue, NotificationClickNotifier notificationClickNotifier, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardManager keyguardManager, IDreamManager iDreamManager, Optional optional, Lazy lazy, NotificationRemoteInputManager notificationRemoteInputManager, NotificationLockscreenUserManager notificationLockscreenUserManager, ShadeController shadeController, KeyguardStateController keyguardStateController, LockPatternUtils lockPatternUtils, StatusBarRemoteInputCallback statusBarRemoteInputCallback, ActivityIntentHelper activityIntentHelper, MetricsLogger metricsLogger, StatusBarNotificationActivityStarterLogger statusBarNotificationActivityStarterLogger, OnUserInteractionCallbackImpl onUserInteractionCallbackImpl, StatusBarNotificationPresenter statusBarNotificationPresenter, PanelExpansionInteractor panelExpansionInteractor, NotificationShadeWindowController notificationShadeWindowController, ActivityTransitionAnimator activityTransitionAnimator, ShadeAnimationInteractor shadeAnimationInteractor, NotificationLaunchAnimatorControllerProvider notificationLaunchAnimatorControllerProvider, LaunchFullScreenIntentProvider launchFullScreenIntentProvider, PowerInteractor powerInteractor, UserTracker userTracker) {
        this.mContext = context;
        this.mDisplayId = i;
        this.mMainThreadHandler = handler;
        this.mUiBgExecutor = executor;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mHeadsUpManager = headsUpManager;
        this.mActivityStarter = activityStarter;
        this.mCommandQueue = commandQueue;
        this.mClickNotifier = notificationClickNotifier;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mKeyguardManager = keyguardManager;
        this.mDreamManager = iDreamManager;
        this.mBubblesManagerOptional = optional;
        this.mAssistManagerLazy = lazy;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mShadeController = shadeController;
        this.mKeyguardStateController = keyguardStateController;
        this.mLockPatternUtils = lockPatternUtils;
        this.mStatusBarRemoteInputCallback = statusBarRemoteInputCallback;
        this.mActivityIntentHelper = activityIntentHelper;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mShadeAnimationInteractor = shadeAnimationInteractor;
        this.mMetricsLogger = metricsLogger;
        this.mLogger = statusBarNotificationActivityStarterLogger;
        this.mOnUserInteractionCallback = onUserInteractionCallbackImpl;
        this.mPresenter = statusBarNotificationPresenter;
        this.mActivityTransitionAnimator = activityTransitionAnimator;
        this.mNotificationAnimationProvider = notificationLaunchAnimatorControllerProvider;
        this.mPowerInteractor = powerInteractor;
        this.mUserTracker = userTracker;
        launchFullScreenIntentProvider.listeners.addIfAbsent(new StatusBarNotificationActivityStarter$$ExternalSyntheticLambda9(this));
    }

    public void launchFullScreenIntent(NotificationEntry notificationEntry) {
        boolean z = this.mPresenter.mVrMode;
        LogBuffer logBuffer = this.mLogger.buffer;
        if (z) {
            LogMessage obtain = logBuffer.obtain("NotifActivityStarter", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logFullScreenIntentSuppressedByVR$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("No Fullscreen intent: suppressed by VR mode: ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
            return;
        }
        this.mUiBgExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = StatusBarNotificationActivityStarter.this;
                statusBarNotificationActivityStarter.getClass();
                try {
                    statusBarNotificationActivityStarter.mDreamManager.awaken();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        PendingIntent pendingIntent = notificationEntry.mSbn.getNotification().fullScreenIntent;
        LogMessage obtain2 = logBuffer.obtain("NotifActivityStarter", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logSendingFullScreenIntent$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Notification ", logMessage.getStr1(), " has fullScreenIntent; sending fullScreenIntent ", logMessage.getStr2());
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        Intent intent = pendingIntent.getIntent();
        logMessageImpl.str2 = intent != null ? intent.toString() : null;
        logBuffer.commit(obtain2);
        try {
            EventLog.writeEvent(36002, notificationEntry.mKey);
            this.mPowerInteractor.wakeUpForFullScreenIntent();
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setPendingIntentBackgroundActivityStartMode(1);
            pendingIntent.sendAndReturnResult(null, 0, null, null, null, null, makeBasic.toBundle());
            notificationEntry.interruption = true;
            notificationEntry.lastFullScreenIntentLaunchTime = SystemClock.elapsedRealtime();
            this.mMetricsLogger.count("note_fullscreen", 1);
            List queryIntentComponents = pendingIntent.queryIntentComponents(0);
            FrameworkStatsLog.write(631, pendingIntent.getCreatorUid(), (queryIntentComponents.size() <= 0 || queryIntentComponents.get(0) == null || ((ResolveInfo) queryIntentComponents.get(0)).activityInfo == null || ((ResolveInfo) queryIntentComponents.get(0)).activityInfo.name == null) ? "" : ((ResolveInfo) queryIntentComponents.get(0)).activityInfo.name);
        } catch (PendingIntent.CanceledException unused) {
        }
    }

    public final void onNotificationClicked(final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow) {
        boolean isHeadsUpState = expandableNotificationRow.isHeadsUpState();
        KeyguardStateController keyguardStateController = this.mKeyguardStateController;
        keyguardStateController.getClass();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        boolean z = keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded;
        boolean z2 = ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mCurrentState.shadeOrQsExpanded;
        StatusBarNotificationActivityStarterLogger statusBarNotificationActivityStarterLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        StatusBarNotificationActivityStarterLogger$logStartingActivityFromClick$2 statusBarNotificationActivityStarterLogger$logStartingActivityFromClick$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logStartingActivityFromClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("(1/5) onNotificationClicked: ", str1, " isHeadsUpState: ", bool1, " isKeyguardVisible: ");
                m.append(bool2);
                m.append(" isPanelExpanded: ");
                m.append(bool3);
                return m.toString();
            }
        };
        LogBuffer logBuffer = statusBarNotificationActivityStarterLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotifActivityStarter", logLevel, statusBarNotificationActivityStarterLogger$logStartingActivityFromClick$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.bool1 = isHeadsUpState;
        logMessageImpl.bool2 = z;
        logMessageImpl.bool3 = z2;
        logBuffer.commit(obtain);
        performActionAfterKeyguardDismissed(notificationEntry, new OnKeyguardDismissedAction() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda10
            @Override // com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter.OnKeyguardDismissedAction
            public final boolean onDismiss(final PendingIntent pendingIntent, final boolean z3, final boolean z4, boolean z5) {
                final StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = StatusBarNotificationActivityStarter.this;
                StatusBarNotificationActivityStarterLogger statusBarNotificationActivityStarterLogger2 = statusBarNotificationActivityStarter.mLogger;
                LogLevel logLevel2 = LogLevel.DEBUG;
                StatusBarNotificationActivityStarterLogger$logHandleClickAfterKeyguardDismissed$2 statusBarNotificationActivityStarterLogger$logHandleClickAfterKeyguardDismissed$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logHandleClickAfterKeyguardDismissed$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("(2/5) handleNotificationClickAfterKeyguardDismissed: ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer2 = statusBarNotificationActivityStarterLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("NotifActivityStarter", logLevel2, statusBarNotificationActivityStarterLogger$logHandleClickAfterKeyguardDismissed$2, null);
                final NotificationEntry notificationEntry2 = notificationEntry;
                ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logBuffer2.commit(obtain2);
                final ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda0
                    /* JADX WARN: Code restructure failed: missing block: B:43:0x01f2, code lost:
                    
                        if (r22.isNotificationKeptForRemoteInputHistory(r1) != false) goto L75;
                     */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x01c8 A[ADDED_TO_REGION] */
                    /* JADX WARN: Removed duplicated region for block: B:40:0x01df  */
                    /* JADX WARN: Removed duplicated region for block: B:48:0x020a  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void run() {
                        /*
                            Method dump skipped, instructions count: 533
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda0.run():void");
                    }
                };
                ShadeController shadeController = statusBarNotificationActivityStarter.mShadeController;
                if (z5) {
                    ((BaseShadeControllerImpl) shadeController).postCollapseActions.add(runnable);
                    shadeController.collapseShade(true);
                } else {
                    KeyguardStateControllerImpl keyguardStateControllerImpl2 = (KeyguardStateControllerImpl) statusBarNotificationActivityStarter.mKeyguardStateController;
                    if (keyguardStateControllerImpl2.mShowing && keyguardStateControllerImpl2.mOccluded) {
                        statusBarNotificationActivityStarter.mStatusBarKeyguardViewManager.mAfterKeyguardGoneRunnables.add(runnable);
                        shadeController.collapseShade();
                    } else {
                        runnable.run();
                    }
                }
                return z4 || !statusBarNotificationActivityStarter.mPanelExpansionInteractor.isFullyCollapsed();
            }
        });
    }

    public final void performActionAfterKeyguardDismissed(NotificationEntry notificationEntry, final OnKeyguardDismissedAction onKeyguardDismissedAction) {
        NotificationRemoteInputManager notificationRemoteInputManager = this.mRemoteInputManager;
        RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
        boolean z = false;
        boolean z2 = remoteInputController != null && remoteInputController.pruneWeakThenRemoveAndContains(notificationEntry, null, null);
        LogBuffer logBuffer = this.mLogger.buffer;
        if (z2) {
            notificationRemoteInputManager.closeRemoteInputs();
            LogMessage obtain = logBuffer.obtain("NotifActivityStarter", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logCloseRemoteInput$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Closing remote input for ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
            return;
        }
        Notification notification = notificationEntry.mSbn.getNotification();
        PendingIntent pendingIntent = notification.contentIntent;
        if (pendingIntent == null) {
            pendingIntent = notification.fullScreenIntent;
        }
        final PendingIntent pendingIntent2 = pendingIntent;
        boolean isBubble = notificationEntry.isBubble();
        if (pendingIntent2 == null && !isBubble) {
            LogMessage obtain2 = logBuffer.obtain("NotifActivityStarter", LogLevel.ERROR, new Function1() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterLogger$logNonClickableNotification$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onNotificationClicked called for non-clickable notification! ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain2);
            return;
        }
        final boolean z3 = (pendingIntent2 == null || !pendingIntent2.isActivity() || isBubble) ? false : true;
        NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
        ActivityIntentHelper activityIntentHelper = this.mActivityIntentHelper;
        boolean z4 = z3 && activityIntentHelper.getPendingTargetActivityInfo(pendingIntent2, ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mCurrentUserId) == null;
        ActivityStarter activityStarter = this.mActivityStarter;
        final boolean z5 = !z4 && activityStarter.shouldAnimateLaunch(z3);
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && pendingIntent2 != null && activityIntentHelper.wouldPendingShowOverLockscreen(pendingIntent2, ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mCurrentUserId)) {
            z = true;
        }
        final boolean z6 = z;
        ActivityStarter.OnDismissAction onDismissAction = new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter.1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                return OnKeyguardDismissedAction.this.onDismiss(pendingIntent2, z3, z5, z6);
            }

            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean willRunAnimationOnKeyguard() {
                return z5;
            }
        };
        if (!z) {
            activityStarter.dismissKeyguardThenExecute(onDismissAction, null, z4);
        } else {
            this.mIsCollapsingToShowActivityOverLockscreen = true;
            onDismissAction.onDismiss();
        }
    }

    public final void removeHunAfterClick(ExpandableNotificationRow expandableNotificationRow) {
        String key = expandableNotificationRow.mEntry.mSbn.getKey();
        HeadsUpManager headsUpManager = this.mHeadsUpManager;
        if (headsUpManager != null) {
            BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) headsUpManager;
            if (baseHeadsUpManager.isHeadsUpEntry(key)) {
                if (this.mPresenter.mPanelExpansionInteractor.isFullyCollapsed()) {
                    expandableNotificationRow.setTag(R.id.is_clicked_heads_up_tag, Boolean.TRUE);
                }
                baseHeadsUpManager.removeNotification$1(key, "removeHunAfterClick", true);
            }
        }
    }
}
