package com.android.systemui.statusbar.notification.row;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationCompactHeadsUpTemplateViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationCustomViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.policy.InflatedSmartReplyState;
import com.android.systemui.statusbar.policy.InflatedSmartReplyViewHolder;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.SmartReplyStateInflaterKt;
import com.android.systemui.statusbar.policy.SmartReplyView;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import kotlin.collections.EmptyList;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationContentView extends FrameLayout implements NotificationFadeAware {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mAnimate;
    public int mAnimationStartVisibleType;
    public boolean mBeforeN;
    public boolean mBubblesEnabledForUser;
    public RemoteInputView mCachedExpandedRemoteInput;
    public RemoteInputViewControllerImpl mCachedExpandedRemoteInputViewController;
    public RemoteInputView mCachedHeadsUpRemoteInput;
    public RemoteInputViewControllerImpl mCachedHeadsUpRemoteInputViewController;
    public int mClipBottomAmount;
    public final Rect mClipBounds;
    public boolean mClipToActualHeight;
    public int mClipTopAmount;
    public ExpandableNotificationRow mContainingNotification;
    public boolean mContentAnimating;
    public int mContentHeight;
    public int mContentHeightAtAnimationStart;
    public DisposableHandle mContractedBinderHandle;
    public View mContractedChild;
    public NotificationViewWrapper mContractedWrapper;
    public InflatedSmartReplyState mCurrentSmartReplyState;
    public final AnonymousClass1 mEnableAnimationPredrawListener;
    public ExpandableNotificationRow.AnonymousClass1 mExpandClickListener;
    public boolean mExpandable;
    public DisposableHandle mExpandedBinderHandle;
    public View mExpandedChild;
    public InflatedSmartReplyViewHolder mExpandedInflatedSmartReplies;
    public RemoteInputView mExpandedRemoteInput;
    public RemoteInputViewControllerImpl mExpandedRemoteInputController;
    public SmartReplyView mExpandedSmartReplyView;
    public Runnable mExpandedVisibleListener;
    public NotificationViewWrapper mExpandedWrapper;
    public boolean mFocusOnVisibilityChange;
    public boolean mForceSelectNextLayout;
    public boolean mHeadsUpAnimatingAway;
    public DisposableHandle mHeadsUpBinderHandle;
    public View mHeadsUpChild;
    public int mHeadsUpHeight;
    public InflatedSmartReplyViewHolder mHeadsUpInflatedSmartReplies;
    public RemoteInputView mHeadsUpRemoteInput;
    public RemoteInputViewControllerImpl mHeadsUpRemoteInputController;
    public SmartReplyView mHeadsUpSmartReplyView;
    public NotificationViewWrapper mHeadsUpWrapper;
    public boolean mIsChildInGroup;
    public boolean mIsContentExpandable;
    public boolean mIsHeadsUp;
    public boolean mLegacy;
    public int mMinContractedHeight;
    public int mMinSingleLineHeight;
    public NotificationEntry mNotificationEntry;
    public int mNotificationMaxHeight;
    public final ArrayMap mOnContentViewInactiveListeners;
    public PeopleNotificationIdentifierImpl mPeopleIdentifier;
    public PendingIntent mPreviousExpandedRemoteInputIntent;
    public PendingIntent mPreviousHeadsUpRemoteInputIntent;
    public RemoteInputController mRemoteInputController;
    public DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl mRemoteInputSubcomponentFactory;
    public boolean mRemoteInputVisible;
    public NotificationViewWrapper mShownWrapper;
    protected HybridNotificationView mSingleLineView;
    public int mSingleLineWidthIndention;
    public int mSmallHeight;
    public SmartReplyConstants mSmartReplyConstants;
    public SmartReplyController mSmartReplyController;
    public IStatusBarService mStatusBarService;
    public int mTransformationStartVisibleType;
    public UiEventLogger mUiEventLogger;
    public int mUnrestrictedContentHeight;
    public boolean mUserExpanding;
    public int mVisibleType;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoteInputViewData {
        public RemoteInputViewControllerImpl mController;
        public RemoteInputView mView;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.statusbar.notification.row.NotificationContentView$1] */
    public NotificationContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClipBounds = new Rect();
        this.mShownWrapper = null;
        this.mVisibleType = -1;
        this.mOnContentViewInactiveListeners = new ArrayMap();
        this.mEnableAnimationPredrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                NotificationContentView.this.post(new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentView.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        NotificationContentView.this.mAnimate = true;
                    }
                });
                NotificationContentView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        };
        this.mClipToActualHeight = true;
        this.mAnimationStartVisibleType = -1;
        this.mForceSelectNextLayout = true;
        this.mContentHeightAtAnimationStart = -1;
        new HybridGroupManager(getContext());
        this.mMinContractedHeight = getResources().getDimensionPixelSize(R.dimen.min_notification_layout_height);
        this.mMinSingleLineHeight = getResources().getDimensionPixelSize(R.dimen.conversation_single_line_face_pile_size);
    }

    public static void applyExternalSmartReplyState(View view, InflatedSmartReplyState inflatedSmartReplyState) {
        List emptyList;
        boolean z = inflatedSmartReplyState != null && inflatedSmartReplyState.hasPhishingAction;
        View findViewById = view.findViewById(android.R.id.portrait);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 0 : 8);
        }
        if (inflatedSmartReplyState != null) {
            InflatedSmartReplyState.SuppressedActions suppressedActions = inflatedSmartReplyState.suppressedActions;
            emptyList = suppressedActions != null ? suppressedActions.suppressedActionIndices : EmptyList.INSTANCE;
        } else {
            emptyList = Collections.emptyList();
        }
        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.alarm);
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                Object tag = childAt.getTag(android.R.id.notification_top_line);
                childAt.setVisibility(((tag instanceof Integer) && emptyList.contains(tag)) ? 8 : 0);
            }
        }
    }

    public static SmartReplyView applySmartReplyView(View view, InflatedSmartReplyState inflatedSmartReplyState, NotificationEntry notificationEntry, InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder) {
        SmartReplyView smartReplyView;
        View findViewById = view.findViewById(android.R.id.spinner);
        SmartReplyView smartReplyView2 = null;
        if (!(findViewById instanceof LinearLayout)) {
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) findViewById;
        if (!SmartReplyStateInflaterKt.shouldShowSmartReplyView(notificationEntry, inflatedSmartReplyState)) {
            linearLayout.setVisibility(8);
            return null;
        }
        int childCount = linearLayout.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt.getId() == R.id.smart_reply_view && (childAt instanceof SmartReplyView)) {
                break;
            }
            i++;
        }
        if (i < childCount) {
            linearLayout.removeViewAt(i);
        }
        if (inflatedSmartReplyViewHolder != null && (smartReplyView = inflatedSmartReplyViewHolder.smartReplyView) != null) {
            linearLayout.addView(smartReplyView, i);
            smartReplyView2 = smartReplyView;
        }
        if (smartReplyView2 != null) {
            smartReplyView2.mSmartReplyContainer = linearLayout;
            smartReplyView2.removeAllViews();
            smartReplyView2.setBackgroundTintColor(smartReplyView2.mDefaultBackgroundColor, false);
            for (Button button : inflatedSmartReplyViewHolder.smartSuggestionButtons) {
                smartReplyView2.addView(button);
                smartReplyView2.setButtonColors(button);
            }
            smartReplyView2.mCandidateButtonQueueForSqueezing = new PriorityQueue(Math.max(smartReplyView2.getChildCount(), 1), SmartReplyView.DECREASING_MEASURED_WIDTH_WITHOUT_PADDING_COMPARATOR);
            smartReplyView2.setBackgroundTintColor(notificationEntry.row.mCurrentBackgroundTint, notificationEntry.mSbn.getNotification().isColorized());
            linearLayout.setVisibility(0);
        }
        return smartReplyView2;
    }

    public static void dumpChildViewDimensions(IndentingPrintWriter indentingPrintWriter, View view, String str) {
        indentingPrintWriter.print(str.concat(" "));
        DumpUtilsKt.withIncreasedIndent(indentingPrintWriter, new NotificationContentView$$ExternalSyntheticLambda1(indentingPrintWriter, view));
    }

    public static void updateAllSingleLineViews() {
        try {
            Trace.beginSection("NotifContentView#updateSingleLineView");
        } finally {
            Trace.endSection();
        }
    }

    public final void applyBubbleAction(View view, NotificationEntry notificationEntry) {
        if (view == null || this.mContainingNotification == null || this.mPeopleIdentifier == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(android.R.id.by_org);
        View findViewById = view.findViewById(android.R.id.alertTitle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.notouch);
        if (imageView == null || findViewById == null) {
            return;
        }
        if (!shouldShowBubbleButton(notificationEntry)) {
            imageView.setVisibility(8);
            return;
        }
        Drawable drawable = ((FrameLayout) this).mContext.getDrawable(notificationEntry.isBubble() ? R.drawable.bubble_ic_stop_bubble : R.drawable.bubble_ic_create_bubble);
        imageView.setContentDescription(((FrameLayout) this).mContext.getResources().getString(notificationEntry.isBubble() ? R.string.notification_conversation_unbubble : R.string.notification_conversation_bubble));
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(this.mContainingNotification.mBubbleClickListener);
        imageView.setVisibility(0);
        findViewById.setVisibility(0);
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                if (marginLayoutParams.bottomMargin > 0) {
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, 0);
                }
            }
        }
    }

    public final RemoteInputViewData applyRemoteInput(View view, NotificationEntry notificationEntry, boolean z, PendingIntent pendingIntent, RemoteInputView remoteInputView, RemoteInputViewControllerImpl remoteInputViewControllerImpl, NotificationViewWrapper notificationViewWrapper) {
        RemoteInput[] remoteInputs;
        RemoteInput remoteInput;
        RemoteInputViewData remoteInputViewData = new RemoteInputViewData();
        View findViewById = view.findViewById(android.R.id.alertTitle);
        if (findViewById instanceof FrameLayout) {
            Object obj = RemoteInputView.VIEW_TAG;
            RemoteInputView remoteInputView2 = (RemoteInputView) view.findViewWithTag(obj);
            remoteInputViewData.mView = remoteInputView2;
            if (remoteInputView2 != null) {
                remoteInputView2.onNotificationUpdateOrReset();
                remoteInputViewData.mController = remoteInputViewData.mView.mViewController;
            }
            if (remoteInputViewData.mView == null && z) {
                FrameLayout frameLayout = (FrameLayout) findViewById;
                if (remoteInputView == null) {
                    Context context = ((FrameLayout) this).mContext;
                    RemoteInputController remoteInputController = this.mRemoteInputController;
                    RemoteInputView remoteInputView3 = (RemoteInputView) LayoutInflater.from(context).inflate(R.layout.remote_input, (ViewGroup) frameLayout, false);
                    remoteInputView3.mController = remoteInputController;
                    remoteInputView3.mEntry = notificationEntry;
                    UserHandle user = notificationEntry.mSbn.getUser();
                    if (UserHandle.ALL.equals(user)) {
                        user = UserHandle.of(ActivityManager.getCurrentUser());
                    }
                    RemoteInputView.RemoteEditText remoteEditText = remoteInputView3.mEditText;
                    remoteEditText.mUser = user;
                    remoteEditText.setTextOperationUser(user);
                    remoteInputView3.setTag(obj);
                    remoteInputView3.setVisibility(8);
                    frameLayout.addView(remoteInputView3, new FrameLayout.LayoutParams(-1, -1));
                    remoteInputViewData.mView = remoteInputView3;
                    DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl = this.mRemoteInputSubcomponentFactory;
                    RemoteInputController remoteInputController2 = this.mRemoteInputController;
                    daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl.getClass();
                    remoteInputController2.getClass();
                    NotificationEntry notificationEntry2 = ((DaggerSysUIGoogleGlobalRootComponent$ExpandableNotificationRowComponentImpl) daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl.view).notificationEntry;
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl.sysUIGoogleSysUIComponentImpl;
                    RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler = (RemoteInputQuickSettingsDisabler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.remoteInputQuickSettingsDisablerProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                    ShortcutManager shortcutManager = (ShortcutManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideShortcutManagerProvider.get();
                    UiEventLogger uiEventLogger = (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get();
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = new RemoteInputViewControllerImpl(remoteInputView3, notificationEntry2, remoteInputQuickSettingsDisabler, remoteInputController2, shortcutManager, uiEventLogger);
                    remoteInputViewData.mController = remoteInputViewControllerImpl2;
                    remoteInputViewData.mView.mViewController = remoteInputViewControllerImpl2;
                } else {
                    frameLayout.addView(remoteInputView);
                    remoteInputView.dispatchFinishTemporaryDetach();
                    remoteInputView.requestFocus();
                    remoteInputViewData.mView = remoteInputView;
                    remoteInputViewData.mController = remoteInputViewControllerImpl;
                }
            }
            if (z) {
                RemoteInputView remoteInputView4 = remoteInputViewData.mView;
                remoteInputView4.mWrapper = notificationViewWrapper;
                remoteInputView4.mOnVisibilityChangedListener = new NotificationContentView$$ExternalSyntheticLambda0(this);
                if (pendingIntent != null || remoteInputView4.isActive()) {
                    Notification.Action[] actionArr = notificationEntry.mSbn.getNotification().actions;
                    if (pendingIntent != null) {
                        remoteInputViewData.mController.pendingIntent = pendingIntent;
                    }
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl3 = remoteInputViewData.mController;
                    if (actionArr == null) {
                        remoteInputViewControllerImpl3.getClass();
                    } else {
                        PendingIntent pendingIntent2 = remoteInputViewControllerImpl3.pendingIntent;
                        Intent intent = pendingIntent2 != null ? pendingIntent2.getIntent() : null;
                        if (intent != null) {
                            int i = 0;
                            while (i < actionArr.length) {
                                int i2 = i + 1;
                                try {
                                    Notification.Action action = actionArr[i];
                                    PendingIntent pendingIntent3 = action.actionIntent;
                                    if (pendingIntent3 != null && (remoteInputs = action.getRemoteInputs()) != null && intent.filterEquals(pendingIntent3.getIntent())) {
                                        int length = remoteInputs.length;
                                        int i3 = 0;
                                        while (true) {
                                            if (i3 >= length) {
                                                remoteInput = null;
                                                break;
                                            }
                                            remoteInput = remoteInputs[i3];
                                            if (remoteInput.getAllowFreeFormInput()) {
                                                break;
                                            }
                                            i3++;
                                        }
                                        if (remoteInput != null) {
                                            remoteInputViewControllerImpl3.pendingIntent = pendingIntent3;
                                            remoteInputViewControllerImpl3.setRemoteInput(remoteInput);
                                            remoteInputViewControllerImpl3.remoteInputs = remoteInputs;
                                            remoteInputViewControllerImpl3.entry.editedSuggestionInfo = null;
                                            if (!remoteInputViewData.mController.view.isActive()) {
                                                remoteInputViewData.mController.view.focus();
                                            }
                                        }
                                    }
                                    i = i2;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    throw new NoSuchElementException(e.getMessage());
                                }
                            }
                        }
                    }
                    if (remoteInputViewData.mController.view.isActive()) {
                        RemoteInputView.RemoteEditText remoteEditText2 = remoteInputViewData.mController.view.mEditText;
                        int i4 = RemoteInputView.RemoteEditText.$r8$clinit;
                        remoteEditText2.defocusIfNeeded(false);
                    }
                }
            }
            if (remoteInputViewData.mView != null) {
                remoteInputViewData.mView.setBackgroundTintColor(notificationEntry.row.mCurrentBackgroundTint, notificationEntry.mSbn.getNotification().isColorized());
            }
        }
        return remoteInputViewData;
    }

    public final void applySystemActions(View view, NotificationEntry notificationEntry) {
        if (view != null && this.mContainingNotification != null) {
            ImageView imageView = (ImageView) view.findViewById(android.R.id.src_in);
            View findViewById = view.findViewById(android.R.id.alertTitle);
            if (imageView != null && findViewById != null) {
                boolean isEnabled = imageView.isEnabled();
                if (this.mContainingNotification.mShowSnooze && isEnabled) {
                    imageView.setImageDrawable(((FrameLayout) this).mContext.getDrawable(R.drawable.ic_snooze));
                    NotificationSnooze notificationSnooze = (NotificationSnooze) LayoutInflater.from(((FrameLayout) this).mContext).inflate(R.layout.notification_snooze, (ViewGroup) null, false);
                    NotificationMenuRow.NotificationMenuItem notificationMenuItem = new NotificationMenuRow.NotificationMenuItem(((FrameLayout) this).mContext, ((FrameLayout) this).mContext.getString(R.string.notification_menu_snooze_description), notificationSnooze, R.drawable.ic_snooze);
                    imageView.setContentDescription(((FrameLayout) this).mContext.getResources().getString(R.string.notification_menu_snooze_description));
                    ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
                    expandableNotificationRow.getClass();
                    imageView.setOnClickListener(new ExpandableNotificationRow$$ExternalSyntheticLambda3(expandableNotificationRow, notificationMenuItem, 1));
                    imageView.setVisibility(0);
                    findViewById.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
            }
        }
        applyBubbleAction(view, notificationEntry);
    }

    public final int calculateVisibleType() {
        if (!this.mUserExpanding) {
            int intrinsicHeight = this.mContainingNotification.getIntrinsicHeight();
            int i = this.mContentHeight;
            if (intrinsicHeight != 0) {
                i = Math.min(i, intrinsicHeight);
            }
            return getVisualTypeForHeight(i);
        }
        int maxContentHeight = (!this.mIsChildInGroup || this.mContainingNotification.isGroupExpanded() || this.mContainingNotification.isExpanded(true)) ? this.mContainingNotification.getMaxContentHeight() : this.mContainingNotification.getShowingLayout().getMinHeight(false);
        if (maxContentHeight == 0) {
            maxContentHeight = this.mContentHeight;
        }
        int visualTypeForHeight = getVisualTypeForHeight(maxContentHeight);
        int visualTypeForHeight2 = (!this.mIsChildInGroup || this.mContainingNotification.isGroupExpanded()) ? getVisualTypeForHeight(this.mContainingNotification.getCollapsedHeight()) : 3;
        return this.mTransformationStartVisibleType == visualTypeForHeight2 ? visualTypeForHeight : visualTypeForHeight2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            Log.e("NotificationContentView", "Drawing view failed: " + e);
            try {
                setVisibility(8);
                StatusBarNotification statusBarNotification = this.mNotificationEntry.mSbn;
                IStatusBarService iStatusBarService = this.mStatusBarService;
                if (iStatusBarService != null) {
                    iStatusBarService.onNotificationError(statusBarNotification.getPackageName(), statusBarNotification.getTag(), statusBarNotification.getId(), statusBarNotification.getUid(), statusBarNotification.getInitialPid(), e.getMessage(), statusBarNotification.getUser().getIdentifier());
                }
            } catch (RemoteException e2) {
                Log.e("NotificationContentView", "cancelNotification failed: " + e2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        View viewForVisibleType = getViewForVisibleType(this.mVisibleType);
        RemoteInputView remoteInputView = viewForVisibleType == this.mExpandedChild ? this.mExpandedRemoteInput : viewForVisibleType == this.mHeadsUpChild ? this.mHeadsUpRemoteInput : null;
        if (remoteInputView != null && remoteInputView.getVisibility() == 0) {
            int height = this.mUnrestrictedContentHeight - remoteInputView.getHeight();
            if (y <= this.mUnrestrictedContentHeight && y >= height) {
                motionEvent.offsetLocation(0.0f, -height);
                return remoteInputView.dispatchTouchEvent(motionEvent);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void fireExpandedVisibleListenerIfVisible() {
        if (this.mExpandedVisibleListener == null || this.mExpandedChild == null || !isShown() || this.mExpandedChild.getVisibility() != 0) {
            return;
        }
        Runnable runnable = this.mExpandedVisibleListener;
        this.mExpandedVisibleListener = null;
        runnable.run();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i) {
        ViewParent viewParent = ((FrameLayout) this).mParent;
        if (viewParent != null) {
            return viewParent.focusSearch(view, i);
        }
        Log.wtf("NotificationContentView", "NotificationContentView doesn't have parent");
        return null;
    }

    public final void forceUpdateVisibility(int i, View view, TransformableView transformableView) {
        if (view == null) {
            return;
        }
        if (this.mVisibleType == i || this.mTransformationStartVisibleType == i) {
            transformableView.setVisible(true);
        } else {
            view.setVisibility(4);
        }
    }

    public final View[] getAllViews() {
        return new View[]{this.mContractedChild, this.mHeadsUpChild, this.mExpandedChild, this.mSingleLineView};
    }

    public NotificationViewWrapper getContractedWrapper() {
        return this.mContractedWrapper;
    }

    public NotificationViewWrapper getExpandedWrapper() {
        return this.mExpandedWrapper;
    }

    public final int getExtraRemoteInputHeight(RemoteInputView remoteInputView) {
        if (remoteInputView == null) {
            return 0;
        }
        if (!remoteInputView.isActive()) {
            if (remoteInputView.getVisibility() != 0) {
                return 0;
            }
            RemoteInputController remoteInputController = remoteInputView.mController;
            String str = remoteInputView.mEntry.mKey;
            if (remoteInputController.mSpinning.get(str) != remoteInputView.mToken) {
                return 0;
            }
        }
        return getResources().getDimensionPixelSize(android.R.dimen.notification_expand_button_pill_height);
    }

    public final int getHeadsUpHeight(boolean z) {
        int i;
        if (this.mHeadsUpChild != null) {
            i = 2;
        } else {
            if (this.mContractedChild == null) {
                return getMinHeight(false);
            }
            i = 0;
        }
        return getExtraRemoteInputHeight(this.mExpandedRemoteInput) + getExtraRemoteInputHeight(this.mHeadsUpRemoteInput) + getViewHeight(i, z);
    }

    public NotificationViewWrapper getHeadsUpWrapper() {
        return this.mHeadsUpWrapper;
    }

    public final int getMaxHeight() {
        int viewHeight;
        int extraRemoteInputHeight;
        if (this.mExpandedChild != null) {
            viewHeight = getViewHeight(1, false);
            extraRemoteInputHeight = getExtraRemoteInputHeight(this.mExpandedRemoteInput);
        } else {
            if (!this.mIsHeadsUp || this.mHeadsUpChild == null || !this.mContainingNotification.canShowHeadsUp()) {
                return this.mContractedChild != null ? getViewHeight(0, false) : this.mNotificationMaxHeight;
            }
            viewHeight = getViewHeight(2, false);
            extraRemoteInputHeight = getExtraRemoteInputHeight(this.mHeadsUpRemoteInput);
        }
        return extraRemoteInputHeight + viewHeight;
    }

    public final int getMinContentHeightHint() {
        int viewHeight;
        int i;
        if (this.mIsChildInGroup && isVisibleOrTransitioning(3)) {
            return ((FrameLayout) this).mContext.getResources().getDimensionPixelSize(android.R.dimen.notification_big_picture_max_height_low_ram);
        }
        if (this.mHeadsUpChild != null && this.mExpandedChild != null) {
            int i2 = this.mTransformationStartVisibleType;
            boolean z = ((i2 == 2 || this.mAnimationStartVisibleType == 2) && this.mVisibleType == 1) || ((i2 == 1 || this.mAnimationStartVisibleType == 1) && this.mVisibleType == 2);
            boolean z2 = !isVisibleOrTransitioning(0) && (this.mIsHeadsUp || this.mHeadsUpAnimatingAway) && this.mContainingNotification.canShowHeadsUp();
            if (z || z2) {
                return Math.min(getViewHeight(2, false), getViewHeight(1, false));
            }
        }
        if (this.mVisibleType == 1 && (i = this.mContentHeightAtAnimationStart) != -1 && this.mExpandedChild != null) {
            return Math.min(i, getViewHeight(1, false));
        }
        if (this.mHeadsUpChild == null || !isVisibleOrTransitioning(2)) {
            viewHeight = this.mExpandedChild != null ? getViewHeight(1, false) : this.mContractedChild != null ? getViewHeight(0, false) + ((FrameLayout) this).mContext.getResources().getDimensionPixelSize(android.R.dimen.notification_big_picture_max_height_low_ram) : getMinHeight(false);
        } else {
            viewHeight = getViewHeight(2, false);
            RemoteInputView remoteInputView = this.mHeadsUpRemoteInput;
            if (remoteInputView != null && remoteInputView.mIsAnimatingAppearance) {
                viewHeight = 0;
            }
        }
        return (this.mExpandedChild == null || !isVisibleOrTransitioning(1)) ? viewHeight : Math.min(viewHeight, getViewHeight(1, false));
    }

    public final int getMinHeight(boolean z) {
        return (z || !this.mIsChildInGroup || this.mContainingNotification.isGroupExpanded()) ? this.mContractedChild != null ? getViewHeight(0, false) : this.mMinContractedHeight : this.mSingleLineView != null ? getViewHeight(3, false) : this.mMinSingleLineHeight;
    }

    public final TransformableView getTransformableViewForVisibleType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? this.mContractedWrapper : this.mSingleLineView : this.mHeadsUpWrapper : this.mExpandedWrapper;
    }

    public final View getViewForVisibleType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? this.mContractedChild : this.mSingleLineView : this.mHeadsUpChild : this.mExpandedChild;
    }

    public final int getViewHeight(int i, boolean z) {
        View viewForVisibleType = getViewForVisibleType(i);
        int height = viewForVisibleType.getHeight();
        NotificationViewWrapper notificationViewWrapper = viewForVisibleType == this.mContractedChild ? this.mContractedWrapper : viewForVisibleType == this.mExpandedChild ? this.mExpandedWrapper : viewForVisibleType == this.mHeadsUpChild ? this.mHeadsUpWrapper : null;
        return notificationViewWrapper != null ? height + notificationViewWrapper.getHeaderTranslation(z) : height;
    }

    public final NotificationViewWrapper getVisibleWrapper(int i) {
        if (i == 0) {
            return this.mContractedWrapper;
        }
        if (i == 1) {
            return this.mExpandedWrapper;
        }
        if (i != 2) {
            return null;
        }
        return this.mHeadsUpWrapper;
    }

    public final int getVisualTypeForHeight(float f) {
        boolean z = this.mExpandedChild == null;
        if (!z && f == getViewHeight(1, false)) {
            return 1;
        }
        if (!this.mUserExpanding && this.mIsChildInGroup && !this.mContainingNotification.isGroupExpanded()) {
            return 3;
        }
        if ((this.mIsHeadsUp || this.mHeadsUpAnimatingAway) && this.mHeadsUpChild != null && this.mContainingNotification.canShowHeadsUp()) {
            return (f <= ((float) getViewHeight(2, false)) || z) ? 2 : 1;
        }
        if (z || !(this.mContractedChild == null || f > getViewHeight(0, false) || (this.mIsChildInGroup && !this.mContainingNotification.isGroupExpanded() && this.mContainingNotification.isExpanded(true)))) {
            return 0;
        }
        return !z ? 1 : -1;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isAnimatingVisibleType() {
        return this.mAnimationStartVisibleType != -1;
    }

    public final boolean isVisibleOrTransitioning(int i) {
        return this.mVisibleType == i || this.mTransformationStartVisibleType == i || this.mAnimationStartVisibleType == i;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void notifySubtreeAccessibilityStateChanged(View view, View view2, int i) {
        if (isAnimatingVisibleType()) {
            return;
        }
        super.notifySubtreeAccessibilityStateChanged(view, view2, i);
    }

    public final void notifySubtreeForAccessibilityContentChange() {
        ViewParent viewParent = ((FrameLayout) this).mParent;
        if (viewParent != null) {
            viewParent.notifySubtreeAccessibilityStateChanged(this, this, 1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateVisibility$3();
    }

    public final void onChildVisibilityChanged(View view, int i, int i2) {
        Runnable runnable;
        super.onChildVisibilityChanged(view, i, i2);
        if ((view != null && isShown() && (view.getVisibility() == 0 || getViewForVisibleType(this.mVisibleType) == view)) || (runnable = (Runnable) this.mOnContentViewInactiveListeners.remove(view)) == null) {
            return;
        }
        runnable.run();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnPreDrawListener(this.mEnableAnimationPredrawListener);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.mExpandedChild;
        int height = view != null ? view.getHeight() : 0;
        super.onLayout(z, i, i2, i3, i4);
        if (height != 0 && this.mExpandedChild.getHeight() != height) {
            this.mContentHeightAtAnimationStart = height;
        }
        updateClipping();
        invalidateOutline();
        selectLayout(false, this.mForceSelectNextLayout);
        this.mForceSelectNextLayout = false;
        updateExpandButtonsDuringLayout(this.mExpandable, true);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        boolean z;
        boolean z2;
        int mode = View.MeasureSpec.getMode(i2);
        boolean z3 = true;
        boolean z4 = mode == 1073741824;
        boolean z5 = mode == Integer.MIN_VALUE;
        int size = View.MeasureSpec.getSize(i);
        int size2 = (z4 || z5) ? View.MeasureSpec.getSize(i2) : 1073741823;
        if (this.mExpandedChild != null) {
            int i4 = this.mNotificationMaxHeight;
            SmartReplyView smartReplyView = this.mExpandedSmartReplyView;
            if (smartReplyView != null) {
                i4 += smartReplyView.mHeightUpperLimit;
            }
            int extraMeasureHeight = this.mExpandedWrapper.getExtraMeasureHeight() + i4;
            int i5 = this.mExpandedChild.getLayoutParams().height;
            if (i5 >= 0) {
                extraMeasureHeight = Math.min(extraMeasureHeight, i5);
                z2 = true;
            } else {
                z2 = false;
            }
            measureChildWithMargins(this.mExpandedChild, i, 0, View.MeasureSpec.makeMeasureSpec(extraMeasureHeight, z2 ? 1073741824 : Integer.MIN_VALUE), 0);
            i3 = Math.max(0, this.mExpandedChild.getMeasuredHeight());
        } else {
            i3 = 0;
        }
        View view = this.mContractedChild;
        if (view != null) {
            int i6 = this.mSmallHeight;
            int i7 = view.getLayoutParams().height;
            if (i7 >= 0) {
                i6 = Math.min(i6, i7);
                z = true;
            } else {
                z = false;
            }
            measureChildWithMargins(this.mContractedChild, i, 0, ((this.mBeforeN && (this.mContractedWrapper instanceof NotificationCustomViewWrapper)) || z) ? View.MeasureSpec.makeMeasureSpec(i6, 1073741824) : View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), 0);
            int measuredHeight = this.mContractedChild.getMeasuredHeight();
            int i8 = this.mMinContractedHeight;
            if (measuredHeight < i8) {
                measureChildWithMargins(this.mContractedChild, i, 0, View.MeasureSpec.makeMeasureSpec(i8, 1073741824), 0);
            }
            i3 = Math.max(i3, measuredHeight);
            if (this.mExpandedChild != null && this.mContractedChild.getMeasuredHeight() > this.mExpandedChild.getMeasuredHeight()) {
                measureChildWithMargins(this.mExpandedChild, i, 0, View.MeasureSpec.makeMeasureSpec(this.mContractedChild.getMeasuredHeight(), 1073741824), 0);
            }
        }
        if (this.mHeadsUpChild != null) {
            int i9 = this.mHeadsUpHeight;
            SmartReplyView smartReplyView2 = this.mHeadsUpSmartReplyView;
            if (smartReplyView2 != null) {
                i9 += smartReplyView2.mHeightUpperLimit;
            }
            int extraMeasureHeight2 = this.mHeadsUpWrapper.getExtraMeasureHeight() + i9;
            int i10 = this.mHeadsUpChild.getLayoutParams().height;
            if (i10 >= 0) {
                extraMeasureHeight2 = Math.min(extraMeasureHeight2, i10);
            } else {
                z3 = false;
            }
            measureChildWithMargins(this.mHeadsUpChild, i, 0, View.MeasureSpec.makeMeasureSpec(extraMeasureHeight2, z3 ? 1073741824 : Integer.MIN_VALUE), 0);
            i3 = Math.max(i3, this.mHeadsUpChild.getMeasuredHeight());
        }
        if (this.mSingleLineView != null) {
            this.mSingleLineView.measure((this.mSingleLineWidthIndention == 0 || View.MeasureSpec.getMode(i) == 0) ? i : View.MeasureSpec.makeMeasureSpec(this.mSingleLineView.getPaddingEnd() + (size - this.mSingleLineWidthIndention), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mNotificationMaxHeight, Integer.MIN_VALUE));
            i3 = Math.max(i3, this.mSingleLineView.getMeasuredHeight());
        }
        setMeasuredDimension(size, Math.min(i3, size2));
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        view.setTag(R.id.row_tag_for_content_view, this.mContainingNotification);
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        updateShownWrapper(this.mVisibleType);
        if (z) {
            fireExpandedVisibleListenerIfVisible();
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        updateVisibility$3();
        if (i == 0 || this.mOnContentViewInactiveListeners.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.mOnContentViewInactiveListeners.values()).iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.mOnContentViewInactiveListeners.clear();
    }

    public final void performWhenContentInactive(int i, Runnable runnable) {
        View viewForVisibleType;
        View viewForVisibleType2 = getViewForVisibleType(i);
        if (viewForVisibleType2 == null || (viewForVisibleType = getViewForVisibleType(i)) == null || !isShown() || !(viewForVisibleType.getVisibility() == 0 || getViewForVisibleType(this.mVisibleType) == viewForVisibleType)) {
            runnable.run();
        } else {
            this.mOnContentViewInactiveListeners.put(viewForVisibleType2, runnable);
        }
    }

    public final boolean pointInView(float f, float f2, float f3) {
        return f >= (-f3) && f2 >= ((float) this.mClipTopAmount) - f3 && f < ((float) (((FrameLayout) this).mRight - ((FrameLayout) this).mLeft)) + f3 && f2 < ((float) this.mUnrestrictedContentHeight) + f3;
    }

    public final void removeContentInactiveRunnable(int i) {
        View viewForVisibleType = getViewForVisibleType(i);
        if (viewForVisibleType == null) {
            return;
        }
        this.mOnContentViewInactiveListeners.remove(viewForVisibleType);
    }

    public final void selectLayout(boolean z, boolean z2) {
        View expandButton;
        RemoteInputViewControllerImpl remoteInputViewControllerImpl;
        RemoteInputViewControllerImpl remoteInputViewControllerImpl2;
        if (this.mContractedChild == null) {
            return;
        }
        if (this.mUserExpanding) {
            int calculateVisibleType = calculateVisibleType();
            if (getTransformableViewForVisibleType(this.mVisibleType) == null) {
                this.mVisibleType = calculateVisibleType;
                updateViewVisibilities(calculateVisibleType);
                updateBackgroundColor(false);
                return;
            }
            int i = this.mVisibleType;
            if (calculateVisibleType != i) {
                this.mTransformationStartVisibleType = i;
                TransformableView transformableViewForVisibleType = getTransformableViewForVisibleType(calculateVisibleType);
                TransformableView transformableViewForVisibleType2 = getTransformableViewForVisibleType(this.mTransformationStartVisibleType);
                transformableViewForVisibleType.transformFrom(0.0f, transformableViewForVisibleType2);
                getViewForVisibleType(calculateVisibleType).setVisibility(0);
                transformableViewForVisibleType2.transformTo(0.0f, transformableViewForVisibleType);
                this.mVisibleType = calculateVisibleType;
                updateBackgroundColor(true);
            }
            if (this.mForceSelectNextLayout) {
                forceUpdateVisibility(0, this.mContractedChild, this.mContractedWrapper);
                forceUpdateVisibility(1, this.mExpandedChild, this.mExpandedWrapper);
                forceUpdateVisibility(2, this.mHeadsUpChild, this.mHeadsUpWrapper);
                HybridNotificationView hybridNotificationView = this.mSingleLineView;
                forceUpdateVisibility(3, hybridNotificationView, hybridNotificationView);
                updateShownWrapper(this.mVisibleType);
                fireExpandedVisibleListenerIfVisible();
                this.mAnimationStartVisibleType = -1;
                notifySubtreeForAccessibilityContentChange();
            }
            int i2 = this.mTransformationStartVisibleType;
            if (i2 == -1 || this.mVisibleType == i2 || getViewForVisibleType(i2) == null) {
                updateViewVisibilities(calculateVisibleType);
                updateBackgroundColor(false);
                return;
            }
            TransformableView transformableViewForVisibleType3 = getTransformableViewForVisibleType(this.mVisibleType);
            TransformableView transformableViewForVisibleType4 = getTransformableViewForVisibleType(this.mTransformationStartVisibleType);
            int viewHeight = getViewHeight(this.mTransformationStartVisibleType, false);
            int viewHeight2 = getViewHeight(this.mVisibleType, false);
            int abs = Math.abs(this.mContentHeight - viewHeight);
            int abs2 = Math.abs(viewHeight2 - viewHeight);
            float f = 1.0f;
            if (abs2 == 0) {
                StringBuilder sb = new StringBuilder("the total transformation distance is 0\n StartType: ");
                ViewPager$$ExternalSyntheticOutline0.m(sb, this.mTransformationStartVisibleType, " height: ", viewHeight, "\n VisibleType: ");
                ViewPager$$ExternalSyntheticOutline0.m(sb, this.mVisibleType, " height: ", viewHeight2, "\n mContentHeight: ");
                sb.append(this.mContentHeight);
                Log.wtf("NotificationContentView", sb.toString());
            } else {
                f = Math.min(1.0f, abs / abs2);
            }
            transformableViewForVisibleType3.transformFrom(f, transformableViewForVisibleType4);
            transformableViewForVisibleType4.transformTo(f, transformableViewForVisibleType3);
            NotificationViewWrapper visibleWrapper = getVisibleWrapper(this.mVisibleType);
            int customBackgroundColor = visibleWrapper != null ? visibleWrapper.getCustomBackgroundColor() : 0;
            NotificationViewWrapper visibleWrapper2 = getVisibleWrapper(this.mTransformationStartVisibleType);
            int customBackgroundColor2 = visibleWrapper2 != null ? visibleWrapper2.getCustomBackgroundColor() : 0;
            if (customBackgroundColor != customBackgroundColor2) {
                if (customBackgroundColor2 == 0) {
                    customBackgroundColor2 = this.mContainingNotification.mNormalColor;
                }
                if (customBackgroundColor == 0) {
                    customBackgroundColor = this.mContainingNotification.mNormalColor;
                }
                customBackgroundColor = NotificationUtils.interpolateColors(customBackgroundColor2, f, customBackgroundColor);
            }
            ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
            if (expandableNotificationRow.getShowingLayout() != this || customBackgroundColor == expandableNotificationRow.mBgTint) {
                return;
            }
            expandableNotificationRow.mBgTint = customBackgroundColor;
            expandableNotificationRow.updateBackgroundTint(false);
            return;
        }
        int calculateVisibleType2 = calculateVisibleType();
        boolean z3 = calculateVisibleType2 != this.mVisibleType;
        if (z3 || z2) {
            View viewForVisibleType = getViewForVisibleType(calculateVisibleType2);
            if (viewForVisibleType != null) {
                viewForVisibleType.setVisibility(0);
                if (calculateVisibleType2 == 2 && this.mHeadsUpRemoteInputController != null && (remoteInputViewControllerImpl2 = this.mExpandedRemoteInputController) != null && remoteInputViewControllerImpl2.view.isActive()) {
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl3 = this.mHeadsUpRemoteInputController;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl4 = this.mExpandedRemoteInputController;
                    remoteInputViewControllerImpl3.getClass();
                    RemoteInputView.RemoteEditText remoteEditText = remoteInputViewControllerImpl4.view.mEditText;
                    int i3 = RemoteInputView.RemoteEditText.$r8$clinit;
                    remoteEditText.defocusIfNeeded(false);
                    remoteInputViewControllerImpl3.setRemoteInput(remoteInputViewControllerImpl4.remoteInput);
                    remoteInputViewControllerImpl3.remoteInputs = remoteInputViewControllerImpl4.remoteInputs;
                    remoteInputViewControllerImpl3.pendingIntent = remoteInputViewControllerImpl4.pendingIntent;
                    remoteInputViewControllerImpl3.view.focus();
                }
                if (calculateVisibleType2 == 1 && this.mExpandedRemoteInputController != null && (remoteInputViewControllerImpl = this.mHeadsUpRemoteInputController) != null && remoteInputViewControllerImpl.view.isActive()) {
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl5 = this.mExpandedRemoteInputController;
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl6 = this.mHeadsUpRemoteInputController;
                    remoteInputViewControllerImpl5.getClass();
                    RemoteInputView.RemoteEditText remoteEditText2 = remoteInputViewControllerImpl6.view.mEditText;
                    int i4 = RemoteInputView.RemoteEditText.$r8$clinit;
                    remoteEditText2.defocusIfNeeded(false);
                    remoteInputViewControllerImpl5.setRemoteInput(remoteInputViewControllerImpl6.remoteInput);
                    remoteInputViewControllerImpl5.remoteInputs = remoteInputViewControllerImpl6.remoteInputs;
                    remoteInputViewControllerImpl5.pendingIntent = remoteInputViewControllerImpl6.pendingIntent;
                    remoteInputViewControllerImpl5.view.focus();
                }
            }
            if (!z || ((calculateVisibleType2 != 1 || this.mExpandedChild == null) && ((calculateVisibleType2 != 2 || this.mHeadsUpChild == null) && ((calculateVisibleType2 != 3 || this.mSingleLineView == null) && calculateVisibleType2 != 0)))) {
                updateViewVisibilities(calculateVisibleType2);
            } else {
                TransformableView transformableViewForVisibleType5 = getTransformableViewForVisibleType(calculateVisibleType2);
                final TransformableView transformableViewForVisibleType6 = getTransformableViewForVisibleType(this.mVisibleType);
                if (transformableViewForVisibleType5 == transformableViewForVisibleType6 || transformableViewForVisibleType6 == null) {
                    transformableViewForVisibleType5.setVisible(true);
                } else {
                    this.mAnimationStartVisibleType = this.mVisibleType;
                    transformableViewForVisibleType5.transformFrom(transformableViewForVisibleType6);
                    getViewForVisibleType(calculateVisibleType2).setVisibility(0);
                    updateShownWrapper(calculateVisibleType2);
                    transformableViewForVisibleType6.transformTo(transformableViewForVisibleType5, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentView.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            TransformableView transformableView = transformableViewForVisibleType6;
                            NotificationContentView notificationContentView = NotificationContentView.this;
                            if (transformableView != notificationContentView.getTransformableViewForVisibleType(notificationContentView.mVisibleType)) {
                                transformableViewForVisibleType6.setVisible(false);
                            }
                            NotificationContentView notificationContentView2 = NotificationContentView.this;
                            notificationContentView2.mAnimationStartVisibleType = -1;
                            notificationContentView2.notifySubtreeForAccessibilityContentChange();
                        }
                    });
                    fireExpandedVisibleListenerIfVisible();
                }
            }
            this.mVisibleType = calculateVisibleType2;
            if (z3 && this.mFocusOnVisibilityChange) {
                NotificationViewWrapper visibleWrapper3 = getVisibleWrapper(calculateVisibleType2);
                if (visibleWrapper3 != null && (expandButton = visibleWrapper3.getExpandButton()) != null) {
                    expandButton.requestAccessibilityFocus();
                }
                this.mFocusOnVisibilityChange = false;
            }
            NotificationViewWrapper visibleWrapper4 = getVisibleWrapper(calculateVisibleType2);
            if (visibleWrapper4 != null) {
                visibleWrapper4.setContentHeight(this.mUnrestrictedContentHeight, getMinContentHeightHint());
            }
            updateBackgroundColor(z);
        }
    }

    public void setAnimationStartVisibleType(int i) {
        this.mAnimationStartVisibleType = i;
    }

    @Override // android.view.ViewGroup
    public final void setClipChildren(boolean z) {
        super.setClipChildren(z && !this.mRemoteInputVisible);
    }

    public final void setContractedChild(View view) {
        View view2 = this.mContractedChild;
        if (view2 != null) {
            this.mOnContentViewInactiveListeners.remove(view2);
            this.mContractedChild.animate().cancel();
            removeView(this.mContractedChild);
        }
        if (view != null) {
            addView(view);
            this.mContractedChild = view;
            this.mContractedWrapper = NotificationViewWrapper.wrap(getContext(), view, this.mContainingNotification);
            updateShownWrapper(this.mVisibleType);
            return;
        }
        this.mContractedChild = null;
        this.mContractedWrapper = null;
        if (this.mTransformationStartVisibleType == 0) {
            this.mTransformationStartVisibleType = -1;
        }
    }

    public void setContractedWrapper(NotificationViewWrapper notificationViewWrapper) {
        this.mContractedWrapper = notificationViewWrapper;
    }

    public final void setExpandedChild(View view) {
        if (this.mExpandedChild != null) {
            this.mPreviousExpandedRemoteInputIntent = null;
            RemoteInputView remoteInputView = this.mExpandedRemoteInput;
            if (remoteInputView != null) {
                remoteInputView.onNotificationUpdateOrReset();
                if (this.mExpandedRemoteInput.isActive()) {
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl = this.mExpandedRemoteInputController;
                    if (remoteInputViewControllerImpl != null) {
                        this.mPreviousExpandedRemoteInputIntent = remoteInputViewControllerImpl.pendingIntent;
                    }
                    RemoteInputView remoteInputView2 = this.mExpandedRemoteInput;
                    this.mCachedExpandedRemoteInput = remoteInputView2;
                    this.mCachedExpandedRemoteInputViewController = remoteInputViewControllerImpl;
                    remoteInputView2.dispatchStartTemporaryDetach();
                    ((ViewGroup) this.mExpandedRemoteInput.getParent()).removeView(this.mExpandedRemoteInput);
                }
            }
            this.mOnContentViewInactiveListeners.remove(this.mExpandedChild);
            this.mExpandedChild.animate().cancel();
            removeView(this.mExpandedChild);
            this.mExpandedRemoteInput = null;
            RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = this.mExpandedRemoteInputController;
            if (remoteInputViewControllerImpl2 != null) {
                remoteInputViewControllerImpl2.unbind();
            }
            this.mExpandedRemoteInputController = null;
        }
        if (view == null) {
            this.mExpandedChild = null;
            this.mExpandedWrapper = null;
            if (this.mTransformationStartVisibleType == 1) {
                this.mTransformationStartVisibleType = -1;
            }
            if (this.mVisibleType == 1) {
                selectLayout(false, true);
                return;
            }
            return;
        }
        addView(view);
        this.mExpandedChild = view;
        this.mExpandedWrapper = NotificationViewWrapper.wrap(getContext(), view, this.mContainingNotification);
        ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
        if (expandableNotificationRow != null) {
            applySystemActions(this.mExpandedChild, expandableNotificationRow.mEntry);
        }
        updateShownWrapper(this.mVisibleType);
    }

    public void setExpandedWrapper(NotificationViewWrapper notificationViewWrapper) {
        this.mExpandedWrapper = notificationViewWrapper;
    }

    public final void setHeadsUpChild(View view) {
        UiEventLogger uiEventLogger;
        if (this.mHeadsUpChild != null) {
            this.mPreviousHeadsUpRemoteInputIntent = null;
            RemoteInputView remoteInputView = this.mHeadsUpRemoteInput;
            if (remoteInputView != null) {
                remoteInputView.onNotificationUpdateOrReset();
                if (this.mHeadsUpRemoteInput.isActive()) {
                    RemoteInputViewControllerImpl remoteInputViewControllerImpl = this.mHeadsUpRemoteInputController;
                    if (remoteInputViewControllerImpl != null) {
                        this.mPreviousHeadsUpRemoteInputIntent = remoteInputViewControllerImpl.pendingIntent;
                    }
                    RemoteInputView remoteInputView2 = this.mHeadsUpRemoteInput;
                    this.mCachedHeadsUpRemoteInput = remoteInputView2;
                    this.mCachedHeadsUpRemoteInputViewController = remoteInputViewControllerImpl;
                    remoteInputView2.dispatchStartTemporaryDetach();
                    ((ViewGroup) this.mHeadsUpRemoteInput.getParent()).removeView(this.mHeadsUpRemoteInput);
                }
            }
            this.mOnContentViewInactiveListeners.remove(this.mHeadsUpChild);
            this.mHeadsUpChild.animate().cancel();
            removeView(this.mHeadsUpChild);
            this.mHeadsUpRemoteInput = null;
            RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = this.mHeadsUpRemoteInputController;
            if (remoteInputViewControllerImpl2 != null) {
                remoteInputViewControllerImpl2.unbind();
            }
            this.mHeadsUpRemoteInputController = null;
        }
        if (view == null) {
            this.mHeadsUpChild = null;
            this.mHeadsUpWrapper = null;
            if (this.mTransformationStartVisibleType == 2) {
                this.mTransformationStartVisibleType = -1;
            }
            if (this.mVisibleType == 2) {
                selectLayout(false, true);
                return;
            }
            return;
        }
        addView(view);
        this.mHeadsUpChild = view;
        NotificationViewWrapper wrap = NotificationViewWrapper.wrap(getContext(), view, this.mContainingNotification);
        this.mHeadsUpWrapper = wrap;
        if ((wrap instanceof NotificationCompactHeadsUpTemplateViewWrapper) && (uiEventLogger = this.mUiEventLogger) != null) {
            uiEventLogger.log(NotificationCompactHeadsUpEvent.NOTIFICATION_COMPACT_HUN_SHOWN);
        }
        ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
        if (expandableNotificationRow != null) {
            applySystemActions(this.mHeadsUpChild, expandableNotificationRow.mEntry);
        }
        updateShownWrapper(this.mVisibleType);
    }

    public final void setHeadsUpInflatedSmartReplies(InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder) {
        this.mHeadsUpInflatedSmartReplies = inflatedSmartReplyViewHolder;
        if (inflatedSmartReplyViewHolder == null) {
            this.mHeadsUpSmartReplyView = null;
        }
    }

    public void setHeadsUpWrapper(NotificationViewWrapper notificationViewWrapper) {
        this.mHeadsUpWrapper = notificationViewWrapper;
    }

    public final void setNotificationFaded(boolean z) {
        NotificationViewWrapper notificationViewWrapper = this.mContractedWrapper;
        if (notificationViewWrapper != null) {
            notificationViewWrapper.setNotificationFaded(z);
        }
        NotificationViewWrapper notificationViewWrapper2 = this.mHeadsUpWrapper;
        if (notificationViewWrapper2 != null) {
            notificationViewWrapper2.setNotificationFaded(z);
        }
        NotificationViewWrapper notificationViewWrapper3 = this.mExpandedWrapper;
        if (notificationViewWrapper3 != null) {
            notificationViewWrapper3.setNotificationFaded(z);
        }
        HybridNotificationView hybridNotificationView = this.mSingleLineView;
        if (hybridNotificationView != null) {
            hybridNotificationView.setNotificationFaded(z);
        }
    }

    public final void setSingleLineView(HybridNotificationView hybridNotificationView) {
        HybridNotificationView hybridNotificationView2 = this.mSingleLineView;
        if (hybridNotificationView2 != null) {
            this.mOnContentViewInactiveListeners.remove(hybridNotificationView2);
            this.mSingleLineView.animate().cancel();
            removeView(this.mSingleLineView);
        }
        if (hybridNotificationView != null) {
            addView(hybridNotificationView);
            this.mSingleLineView = hybridNotificationView;
        } else {
            this.mSingleLineView = null;
            if (this.mTransformationStartVisibleType == 3) {
                this.mTransformationStartVisibleType = -1;
            }
        }
    }

    @Override // android.view.View
    public final void setTranslationY(float f) {
        super.setTranslationY(f);
        updateClipping();
    }

    public boolean shouldShowBubbleButton(NotificationEntry notificationEntry) {
        return this.mBubblesEnabledForUser && (this.mPeopleIdentifier.getPeopleNotificationType(notificationEntry) >= 2) && notificationEntry.mBubbleMetadata != null;
    }

    public final void updateBackgroundColor(boolean z) {
        NotificationViewWrapper visibleWrapper = getVisibleWrapper(this.mVisibleType);
        int customBackgroundColor = visibleWrapper != null ? visibleWrapper.getCustomBackgroundColor() : 0;
        ExpandableNotificationRow expandableNotificationRow = this.mContainingNotification;
        if (expandableNotificationRow.getShowingLayout() != this || customBackgroundColor == expandableNotificationRow.mBgTint) {
            return;
        }
        expandableNotificationRow.mBgTint = customBackgroundColor;
        expandableNotificationRow.updateBackgroundTint(z);
    }

    public final void updateClipping() {
        if (!this.mClipToActualHeight) {
            setClipBounds(null);
            return;
        }
        int translationY = (int) (this.mClipTopAmount - getTranslationY());
        this.mClipBounds.set(0, translationY, getWidth(), Math.max(translationY, (int) ((this.mUnrestrictedContentHeight - this.mClipBottomAmount) - getTranslationY())));
        setClipBounds(this.mClipBounds);
    }

    public final void updateExpandButtonsDuringLayout(boolean z, boolean z2) {
        this.mExpandable = z;
        View view = this.mExpandedChild;
        boolean z3 = false;
        if (view != null && view.getHeight() != 0 && ((this.mIsHeadsUp || this.mHeadsUpAnimatingAway) && this.mHeadsUpChild != null && this.mContainingNotification.canShowHeadsUp() ? this.mExpandedChild.getHeight() <= this.mHeadsUpChild.getHeight() : !(this.mContractedChild != null && this.mExpandedChild.getHeight() > this.mContractedChild.getHeight()))) {
            z = false;
        }
        if (z2 && this.mIsContentExpandable != z) {
            z3 = true;
        }
        if (this.mExpandedChild != null) {
            this.mExpandedWrapper.updateExpandability(z, this.mExpandClickListener, z3);
        }
        if (this.mContractedChild != null) {
            this.mContractedWrapper.updateExpandability(z, this.mExpandClickListener, z3);
        }
        if (this.mHeadsUpChild != null) {
            this.mHeadsUpWrapper.updateExpandability(z, this.mExpandClickListener, z3);
        }
        this.mIsContentExpandable = z;
    }

    public final void updateLegacy() {
        if (this.mContractedChild != null) {
            this.mContractedWrapper.setLegacy(this.mLegacy);
        }
        if (this.mExpandedChild != null) {
            this.mExpandedWrapper.setLegacy(this.mLegacy);
        }
        if (this.mHeadsUpChild != null) {
            this.mHeadsUpWrapper.setLegacy(this.mLegacy);
        }
    }

    public final void updateShownWrapper(int i) {
        NotificationViewWrapper visibleWrapper = isShown() ? getVisibleWrapper(i) : null;
        NotificationViewWrapper notificationViewWrapper = this.mShownWrapper;
        if (notificationViewWrapper != visibleWrapper) {
            this.mShownWrapper = visibleWrapper;
            if (notificationViewWrapper != null) {
                notificationViewWrapper.onContentShown(false);
            }
            if (visibleWrapper != null) {
                visibleWrapper.onContentShown(true);
            }
        }
    }

    public final void updateViewVisibilities(int i) {
        View view = this.mContractedChild;
        NotificationViewWrapper notificationViewWrapper = this.mContractedWrapper;
        if (view != null) {
            notificationViewWrapper.setVisible(i == 0);
        }
        View view2 = this.mExpandedChild;
        NotificationViewWrapper notificationViewWrapper2 = this.mExpandedWrapper;
        if (view2 != null) {
            notificationViewWrapper2.setVisible(i == 1);
        }
        View view3 = this.mHeadsUpChild;
        NotificationViewWrapper notificationViewWrapper3 = this.mHeadsUpWrapper;
        if (view3 != null) {
            notificationViewWrapper3.setVisible(i == 2);
        }
        HybridNotificationView hybridNotificationView = this.mSingleLineView;
        if (hybridNotificationView != null) {
            hybridNotificationView.setVisible(i == 3);
        }
        updateShownWrapper(i);
        fireExpandedVisibleListenerIfVisible();
        this.mAnimationStartVisibleType = -1;
        notifySubtreeForAccessibilityContentChange();
    }

    public final void updateVisibility$3() {
        if (isShown()) {
            getViewTreeObserver().removeOnPreDrawListener(this.mEnableAnimationPredrawListener);
            getViewTreeObserver().addOnPreDrawListener(this.mEnableAnimationPredrawListener);
        } else {
            getViewTreeObserver().removeOnPreDrawListener(this.mEnableAnimationPredrawListener);
            this.mAnimate = false;
        }
    }
}
