package com.android.systemui.shade;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.UdfpsKeyguardViewControllerLegacy$statusBarKeyguardViewManagerCallback$1;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.controller.MediaHierarchyManager;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.EmptyShadeView;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.QsFrameTranslateImpl;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.StackScrollAlgorithm;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager;
import com.android.systemui.statusbar.policy.CastControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.wm.shell.R;
import dagger.Lazy;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickSettingsControllerImpl implements QuickSettingsController, Dumpable {
    public final AccessibilityManager mAccessibilityManager;
    public final ActiveNotificationsInteractor mActiveNotificationsInteractor;
    public final AmbientState mAmbientState;
    public boolean mAnimateNextNotificationBounds;
    public boolean mAnimating;
    public boolean mAnimatingHiddenFromCollapsed;
    public boolean mAnimatorExpand;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mApplyClippingImmediatelyListener;
    public int mBarState;
    public Insets mCachedGestureInsets;
    public int mCachedWindowWidth;
    public final CastControllerImpl mCastController;
    public boolean mCollapsedOnDown;
    public final Lazy mCommunalTransitionViewModelLazy;
    public boolean mConflictingExpansionGesture;
    public final NotificationShadeDepthController mDepthController;
    public final DeviceEntryFaceAuthInteractor mDeviceEntryFaceAuthInteractor;
    public int mDistanceForFullShadeTransition;
    public boolean mDozing;
    public boolean mEnableClipping;
    public boolean mExpandedWhenExpandingStarted;
    public ValueAnimator mExpansionAnimator;
    public boolean mExpansionFromOverscroll;
    public float mExpansionHeight;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mExpansionHeightListener;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mExpansionHeightSetToMaxListener;
    public final FalsingManager mFalsingManager;
    public int mFalsingThreshold;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mFlingQsWithoutClickListener;
    public boolean mFullyExpanded;
    public float mInitialHeightOnTouch;
    public float mInitialTouchX;
    public float mInitialTouchY;
    public final Lazy mInteractionJankMonitorLazy;
    public boolean mIsFullWidth;
    public boolean mIsPulseExpansionResettingAnimator;
    public boolean mIsTranslationResettingAnimator;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardBypassController mKeyguardBypassController;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardStatusBarView mKeyguardStatusBar;
    public final Lazy mLargeScreenHeaderHelperLazy;
    public int mLargeScreenShadeHeaderHeight;
    public float mLastOverscroll;
    public boolean mLastShadeFlingWasExpanding;
    public final LightBarController mLightBarController;
    public final LockscreenGestureLogger mLockscreenGestureLogger;
    public int mLockscreenNotificationPadding;
    public final LockscreenShadeTransitionController mLockscreenShadeTransitionController;
    public int mMaxExpansionHeight;
    public final MediaDataManager mMediaDataManager;
    public final MediaHierarchyManager mMediaHierarchyManager;
    public final MetricsLogger mMetricsLogger;
    public int mMinExpansionHeight;
    public long mNotificationBoundsAnimationDelay;
    public long mNotificationBoundsAnimationDuration;
    public final NotificationStackScrollLayoutController mNotificationStackScrollLayoutController;
    public final NotificationPanelView mPanelView;
    public final Lazy mPanelViewControllerLazy;
    public final PulseExpansionHandler mPulseExpansionHandler;
    public QS mQs;
    public final FrameLayout mQsFrame;
    public final QsFrameTranslateImpl mQsFrameTranslateController;
    public NotificationPanelViewController$$ExternalSyntheticLambda11 mQsStateUpdateListener;
    public VelocityTracker mQsVelocityTracker;
    public float mQuickQsHeaderHeight;
    public final RecordingController mRecordingController;
    public final NotificationRemoteInputManager mRemoteInputManager;
    public final Resources mResources;
    public int mScreenCornerRadius;
    public final ScrimController mScrimController;
    public int mScrimCornerRadius;
    public float mShadeExpandedFraction;
    public final ShadeHeaderController mShadeHeaderController;
    public final ShadeInteractor mShadeInteractor;
    public final ShadeLogger mShadeLog;
    public final ShadeRepository mShadeRepository;
    public ValueAnimator mSizeChangeAnimator;
    public float mSlopMultiplier;
    public boolean mSplitShadeEnabled;
    public int mSplitShadeNotificationsScrimMarginBottom;
    public final SplitShadeStateControllerImpl mSplitShadeStateController;
    public boolean mStackScrollerOverscrolling;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public int mStatusBarMinHeight;
    public final StatusBarTouchableRegionManager mStatusBarTouchableRegionManager;
    public boolean mTouchAboveFalsingThreshold;
    public int mTouchSlop;
    public int mTrackingPointer;
    public int mTransitionToFullShadePosition;
    public float mTransitioningToFullShadeProgress;
    public float mTranslationForFullShadeTransition;
    public boolean mTwoFingerExpandPossible;
    public boolean mUseLargeScreenShadeHeader;
    public boolean mVisible;
    public boolean mScrimEnabled = true;
    public int mDisplayRightInset = 0;
    public int mDisplayLeftInset = 0;
    public float mShadeExpandedHeight = 0.0f;
    public boolean mExpansionEnabledPolicy = true;
    public boolean mExpansionEnabledAmbient = true;
    public final Region mInterceptRegion = new Region();
    public final Rect mClippingAnimationEndBounds = new Rect();
    public final Rect mLastClipBounds = new Rect();
    public ValueAnimator mClippingAnimator = null;
    public final QuickSettingsControllerImpl$$ExternalSyntheticLambda2 mQsHeightListener = new QS.HeightListener() { // from class: com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda2
        @Override // com.android.systemui.plugins.qs.QS.HeightListener
        public final void onQsHeightChanged() {
            QuickSettingsControllerImpl.this.onHeightChanged();
        }
    };
    public final QuickSettingsControllerImpl$$ExternalSyntheticLambda3 mQsCollapseExpandAction = new QuickSettingsControllerImpl$$ExternalSyntheticLambda3(0, this);
    public final QuickSettingsControllerImpl$$ExternalSyntheticLambda4 mQsScrollListener = new QS.ScrollListener() { // from class: com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda4
        @Override // com.android.systemui.plugins.qs.QS.ScrollListener
        public final void onQsPanelScrollChanged(int i) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            ShadeHeaderController shadeHeaderController = quickSettingsControllerImpl.mShadeHeaderController;
            if (shadeHeaderController.qsScrollY != i) {
                shadeHeaderController.qsScrollY = i;
                if (!shadeHeaderController.largeScreenActive) {
                    shadeHeaderController.header.setScrollY(i);
                }
            }
            if (i <= 0 || quickSettingsControllerImpl.mFullyExpanded) {
                return;
            }
            ((NotificationPanelViewController) quickSettingsControllerImpl.mPanelViewControllerLazy.get()).expandToQs();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.QuickSettingsControllerImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ QuickSettingsControllerImpl this$0;

        public /* synthetic */ AnonymousClass1(QuickSettingsControllerImpl quickSettingsControllerImpl, int i) {
            this.$r8$classId = i;
            this.this$0 = quickSettingsControllerImpl;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mSizeChangeAnimator = null;
                    break;
                default:
                    QuickSettingsControllerImpl quickSettingsControllerImpl = this.this$0;
                    quickSettingsControllerImpl.mClippingAnimator = null;
                    quickSettingsControllerImpl.mIsTranslationResettingAnimator = false;
                    quickSettingsControllerImpl.mIsPulseExpansionResettingAnimator = false;
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LockscreenShadeTransitionCallback {
        public LockscreenShadeTransitionCallback() {
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00bc  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void setTransitionToFullShadeAmount(float r6, long r7, boolean r9) {
            /*
                r5 = this;
                r0 = 0
                r1 = 1
                r2 = 0
                com.android.systemui.shade.QuickSettingsControllerImpl r5 = com.android.systemui.shade.QuickSettingsControllerImpl.this
                if (r9 == 0) goto L1e
                boolean r9 = r5.mIsFullWidth
                if (r9 == 0) goto L1e
                r5.mAnimateNextNotificationBounds = r1
                r3 = 448(0x1c0, double:2.213E-321)
                r5.mNotificationBoundsAnimationDuration = r3
                r5.mNotificationBoundsAnimationDelay = r7
                float r7 = r5.mTranslationForFullShadeTransition
                int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r7 <= 0) goto L1b
                r7 = r1
                goto L1c
            L1b:
                r7 = r0
            L1c:
                r5.mIsTranslationResettingAnimator = r7
            L1e:
                int r7 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r7 <= 0) goto L9e
                boolean r7 = r5.mSplitShadeEnabled
                if (r7 == 0) goto L37
                int r7 = r5.mMinExpansionHeight
                int r8 = r5.mMaxExpansionHeight
                com.android.systemui.statusbar.LockscreenShadeTransitionController r9 = r5.mLockscreenShadeTransitionController
                com.android.systemui.statusbar.LockscreenShadeQsTransitionController r9 = r9.qsTransitionController
                float r9 = r9.qsTransitionFraction
                float r7 = android.util.MathUtils.lerp(r7, r8, r9)
                r5.setExpansionHeight(r7)
            L37:
                com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor r7 = r5.mActiveNotificationsInteractor
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository r7 = r7.repository
                kotlinx.coroutines.flow.StateFlowImpl r7 = r7.activeNotifications
                java.lang.Object r7 = r7.getValue()
                com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore r7 = (com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore) r7
                java.util.List r7 = r7.renderList
                boolean r7 = r7.isEmpty()
                com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController r8 = r5.mNotificationStackScrollLayoutController
                if (r7 == 0) goto L6e
                com.android.systemui.media.controls.domain.pipeline.MediaDataManager r7 = r5.mMediaDataManager
                boolean r7 = r7.hasActiveMediaOrRecommendation()
                if (r7 != 0) goto L6e
                boolean r7 = r5.isQsFragmentCreated()
                if (r7 == 0) goto L9e
                float r7 = r5.getEdgePosition()
                com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r8 = r8.mView
                com.android.systemui.statusbar.notification.stack.AmbientState r8 = r8.mAmbientState
                int r8 = r8.mTopPadding
                float r8 = (float) r8
                float r7 = r7 - r8
                int r8 = r5.getHeaderHeight()
                float r8 = (float) r8
                float r7 = r7 + r8
                goto L9f
            L6e:
                float r7 = r5.getEdgePosition()
                com.android.systemui.media.controls.ui.controller.KeyguardMediaController r9 = r8.mKeyguardMediaController
                com.android.systemui.statusbar.notification.stack.MediaContainerView r9 = r9.singlePaneContainer
                if (r9 == 0) goto L93
                int r3 = r9.getHeight()
                if (r3 == 0) goto L93
                com.android.systemui.statusbar.SysuiStatusBarStateController r3 = r8.mStatusBarStateController
                com.android.systemui.statusbar.StatusBarStateControllerImpl r3 = (com.android.systemui.statusbar.StatusBarStateControllerImpl) r3
                int r3 = r3.mState
                if (r3 == r1) goto L87
                goto L93
            L87:
                int r9 = r9.getHeight()
                com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r8 = r8.mView
                int r0 = r8.mGapHeight
                int r8 = r8.mPaddingBetweenElements
                int r0 = r0 + r8
                int r0 = r0 + r9
            L93:
                float r8 = (float) r0
                float r7 = r7 + r8
                int r8 = r5.mBarState
                if (r8 != r1) goto L9f
                int r8 = r5.mLockscreenNotificationPadding
                float r8 = (float) r8
                float r7 = r7 - r8
                goto L9f
            L9e:
                r7 = r2
            L9f:
                android.view.animation.Interpolator r8 = com.android.app.animation.Interpolators.FAST_OUT_SLOW_IN
                int r9 = r5.mDistanceForFullShadeTransition
                float r9 = (float) r9
                float r6 = r6 / r9
                float r6 = android.util.MathUtils.saturate(r6)
                android.view.animation.PathInterpolator r8 = (android.view.animation.PathInterpolator) r8
                float r6 = r8.getInterpolation(r6)
                r5.mTransitioningToFullShadeProgress = r6
                float r6 = android.util.MathUtils.lerp(r2, r7, r6)
                int r6 = (int) r6
                float r7 = r5.mTransitioningToFullShadeProgress
                int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r7 <= 0) goto Lc0
                int r6 = java.lang.Math.max(r1, r6)
            Lc0:
                r5.mTransitionToFullShadePosition = r6
                r5.updateExpansion()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.QuickSettingsControllerImpl.LockscreenShadeTransitionCallback.setTransitionToFullShadeAmount(float, long, boolean):void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NsslOverscrollTopChangedListener {
        public NsslOverscrollTopChangedListener() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class QsFragmentListener implements FragmentHostManager.FragmentListener {
        public QsFragmentListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
        public final void onFragmentViewCreated(Fragment fragment) {
            QS qs = (QS) fragment;
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            quickSettingsControllerImpl.mQs = qs;
            qs.setPanelView(quickSettingsControllerImpl.mQsHeightListener);
            quickSettingsControllerImpl.mQs.setCollapseExpandAction(quickSettingsControllerImpl.mQsCollapseExpandAction);
            quickSettingsControllerImpl.mQs.setHeaderClickable(quickSettingsControllerImpl.isExpansionEnabled());
            quickSettingsControllerImpl.mQs.setOverscrolling(quickSettingsControllerImpl.mStackScrollerOverscrolling);
            quickSettingsControllerImpl.mQs.setInSplitShade(quickSettingsControllerImpl.mSplitShadeEnabled);
            quickSettingsControllerImpl.mQs.setIsNotificationPanelFullWidth(quickSettingsControllerImpl.mIsFullWidth);
            quickSettingsControllerImpl.mQs.getView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.shade.QuickSettingsControllerImpl$QsFragmentListener$$ExternalSyntheticLambda0
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    QuickSettingsControllerImpl.QsFragmentListener qsFragmentListener = QuickSettingsControllerImpl.QsFragmentListener.this;
                    if (i4 - i2 != i8 - i6) {
                        QuickSettingsControllerImpl.this.onHeightChanged();
                    } else {
                        qsFragmentListener.getClass();
                    }
                }
            });
            quickSettingsControllerImpl.mQs.setCollapsedMediaVisibilityChangedListener(new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(5, this));
            QS qs2 = quickSettingsControllerImpl.mQs;
            quickSettingsControllerImpl.mLockscreenShadeTransitionController.qS = qs2;
            quickSettingsControllerImpl.mNotificationStackScrollLayoutController.mView.mQsHeader = (ViewGroup) qs2.getHeader();
            quickSettingsControllerImpl.mQs.setScrollListener(quickSettingsControllerImpl.mQsScrollListener);
            quickSettingsControllerImpl.updateExpansion();
        }

        @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
        public final void onFragmentViewDestroyed(Fragment fragment) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            if (fragment == quickSettingsControllerImpl.mQs) {
                quickSettingsControllerImpl.mNotificationStackScrollLayoutController.mView.mQsHeader = null;
                quickSettingsControllerImpl.mQs = null;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda4] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda2] */
    public QuickSettingsControllerImpl(Lazy lazy, NotificationPanelView notificationPanelView, QsFrameTranslateImpl qsFrameTranslateImpl, PulseExpansionHandler pulseExpansionHandler, NotificationRemoteInputManager notificationRemoteInputManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, LightBarController lightBarController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, LockscreenShadeTransitionController lockscreenShadeTransitionController, NotificationShadeDepthController notificationShadeDepthController, ShadeHeaderController shadeHeaderController, StatusBarTouchableRegionManager statusBarTouchableRegionManager, KeyguardStateController keyguardStateController, KeyguardBypassController keyguardBypassController, ScrimController scrimController, MediaDataManager mediaDataManager, MediaHierarchyManager mediaHierarchyManager, AmbientState ambientState, RecordingController recordingController, FalsingManager falsingManager, AccessibilityManager accessibilityManager, LockscreenGestureLogger lockscreenGestureLogger, MetricsLogger metricsLogger, Lazy lazy2, ShadeLogger shadeLogger, DumpManager dumpManager, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, ShadeRepository shadeRepository, ShadeInteractor shadeInteractor, ActiveNotificationsInteractor activeNotificationsInteractor, JavaAdapter javaAdapter, CastControllerImpl castControllerImpl, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Lazy lazy3, Lazy lazy4) {
        this.mPanelViewControllerLazy = lazy;
        this.mPanelView = notificationPanelView;
        this.mLargeScreenHeaderHelperLazy = lazy4;
        this.mQsFrame = (FrameLayout) notificationPanelView.findViewById(R.id.qs_frame);
        this.mKeyguardStatusBar = (KeyguardStatusBarView) notificationPanelView.findViewById(R.id.keyguard_header);
        Resources resources = notificationPanelView.getResources();
        this.mResources = resources;
        this.mSplitShadeStateController = splitShadeStateControllerImpl;
        this.mSplitShadeEnabled = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(resources);
        this.mQsFrameTranslateController = qsFrameTranslateImpl;
        this.mPulseExpansionHandler = pulseExpansionHandler;
        pulseExpansionHandler.pulseExpandAbortListener = new QuickSettingsControllerImpl$$ExternalSyntheticLambda3(1, this);
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mLightBarController = lightBarController;
        this.mNotificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.mLockscreenShadeTransitionController = lockscreenShadeTransitionController;
        this.mDepthController = notificationShadeDepthController;
        this.mShadeHeaderController = shadeHeaderController;
        this.mStatusBarTouchableRegionManager = statusBarTouchableRegionManager;
        this.mKeyguardStateController = keyguardStateController;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mScrimController = scrimController;
        this.mMediaDataManager = mediaDataManager;
        this.mMediaHierarchyManager = mediaHierarchyManager;
        this.mAmbientState = ambientState;
        this.mRecordingController = recordingController;
        this.mFalsingManager = falsingManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mLockscreenGestureLogger = lockscreenGestureLogger;
        this.mMetricsLogger = metricsLogger;
        this.mShadeLog = shadeLogger;
        this.mDeviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.mCastController = castControllerImpl;
        this.mInteractionJankMonitorLazy = lazy2;
        this.mShadeRepository = shadeRepository;
        this.mShadeInteractor = shadeInteractor;
        this.mActiveNotificationsInteractor = activeNotificationsInteractor;
        this.mCommunalTransitionViewModelLazy = lazy3;
        this.mJavaAdapter = javaAdapter;
        LockscreenShadeTransitionCallback lockscreenShadeTransitionCallback = new LockscreenShadeTransitionCallback();
        if (!lockscreenShadeTransitionController.callbacks.contains(lockscreenShadeTransitionCallback)) {
            lockscreenShadeTransitionController.callbacks.add(lockscreenShadeTransitionCallback);
        }
        dumpManager.registerDumpable(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyClippingImmediately(boolean r25, int r26, int r27, int r28, int r29) {
        /*
            Method dump skipped, instructions count: 771
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.QuickSettingsControllerImpl.applyClippingImmediately(boolean, int, int, int, int):void");
    }

    public final void beginJankMonitoring(boolean z) {
        InteractionJankMonitor interactionJankMonitor = (InteractionJankMonitor) this.mInteractionJankMonitorLazy.get();
        if (interactionJankMonitor == null) {
            return;
        }
        interactionJankMonitor.begin(InteractionJankMonitor.Configuration.Builder.withView(0, this.mPanelView).setTag(z ? "Expand" : "Collapse"));
    }

    public int calculateBottomCornerRadius(float f) {
        return (int) MathUtils.lerp(f, this.mScrimCornerRadius, Math.min(calculateBottomRadiusProgress(), 1.0f));
    }

    public final int calculateBottomPosition(float f) {
        if (this.mTransitioningToFullShadeProgress > 0.0f) {
            return this.mTransitionToFullShadePosition;
        }
        return (int) MathUtils.lerp(this.mQs.getQsMinExpansionHeight() + ((int) getHeaderTranslation()), this.mQs.getDesiredHeight() + (this.mUseLargeScreenShadeHeader ? this.mLargeScreenShadeHeaderHeight : 0), f);
    }

    public float calculateBottomRadiusProgress() {
        return (1.0f - this.mScrimController.mNotificationsScrim.getScaleY()) * 100.0f;
    }

    public final int calculatePanelHeightExpanded(int i) {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        float height = (notificationStackScrollLayoutController.mView.getHeight() - notificationStackScrollLayoutController.mView.getEmptyBottomMarginInternal()) - notificationStackScrollLayoutController.mView.mAmbientState.mTopPadding;
        if (notificationStackScrollLayoutController.getNotGoneChildCount() == 0) {
            EmptyShadeView emptyShadeView = notificationStackScrollLayoutController.mView.mEmptyShadeView;
            if (emptyShadeView.mIsVisible) {
                height = emptyShadeView.getHeight();
            }
        }
        int i2 = this.mMaxExpansionHeight;
        ValueAnimator valueAnimator = this.mSizeChangeAnimator;
        if (valueAnimator != null) {
            i2 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        }
        if (this.mBarState != 1) {
            i = 0;
        }
        float max = Math.max(i2, i) + height + notificationStackScrollLayoutController.mView.mTopPaddingOverflow;
        if (max > r5.getHeight()) {
            max = Math.max(notificationStackScrollLayoutController.mView.getLayoutMinHeightInternal() + i2, notificationStackScrollLayoutController.mView.getHeight());
        }
        return (int) max;
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final void closeQs() {
        if (this.mSplitShadeEnabled) {
            this.mShadeLog.d("Closing QS while in split shade");
        }
        ValueAnimator valueAnimator = this.mExpansionAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setExpansionHeight(this.mMinExpansionHeight);
        setExpandImmediate(false);
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final void closeQsCustomizer() {
        QS qs = this.mQs;
        if (qs != null) {
            qs.closeCustomizer();
        }
    }

    public final float computeExpansionFraction() {
        if (this.mAnimatingHiddenFromCollapsed) {
            return 0.0f;
        }
        return Math.min(1.0f, (this.mExpansionHeight - this.mMinExpansionHeight) / (this.mMaxExpansionHeight - r1));
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("QuickSettingsController:");
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        asIndenting.print("mIsFullWidth=");
        asIndenting.println(this.mIsFullWidth);
        asIndenting.print("mTouchSlop=");
        asIndenting.println(this.mTouchSlop);
        asIndenting.print("mSlopMultiplier=");
        asIndenting.println(this.mSlopMultiplier);
        asIndenting.print("mBarState=");
        asIndenting.println(this.mBarState);
        asIndenting.print("mStatusBarMinHeight=");
        asIndenting.println(this.mStatusBarMinHeight);
        asIndenting.print("mScrimEnabled=");
        asIndenting.println(this.mScrimEnabled);
        asIndenting.print("mScrimCornerRadius=");
        asIndenting.println(this.mScrimCornerRadius);
        asIndenting.print("mScreenCornerRadius=");
        asIndenting.println(this.mScreenCornerRadius);
        asIndenting.print("mUseLargeScreenShadeHeader=");
        asIndenting.println(this.mUseLargeScreenShadeHeader);
        asIndenting.print("mLargeScreenShadeHeaderHeight=");
        asIndenting.println(this.mLargeScreenShadeHeaderHeight);
        asIndenting.print("mDisplayRightInset=");
        asIndenting.println(this.mDisplayRightInset);
        asIndenting.print("mDisplayLeftInset=");
        asIndenting.println(this.mDisplayLeftInset);
        asIndenting.print("mSplitShadeEnabled=");
        asIndenting.println(this.mSplitShadeEnabled);
        asIndenting.print("mLockscreenNotificationPadding=");
        asIndenting.println(this.mLockscreenNotificationPadding);
        asIndenting.print("mSplitShadeNotificationsScrimMarginBottom=");
        asIndenting.println(this.mSplitShadeNotificationsScrimMarginBottom);
        asIndenting.print("mDozing=");
        asIndenting.println(this.mDozing);
        asIndenting.print("mEnableClipping=");
        asIndenting.println(this.mEnableClipping);
        asIndenting.print("mFalsingThreshold=");
        asIndenting.println(this.mFalsingThreshold);
        asIndenting.print("mTransitionToFullShadePosition=");
        asIndenting.println(this.mTransitionToFullShadePosition);
        asIndenting.print("mCollapsedOnDown=");
        asIndenting.println(this.mCollapsedOnDown);
        asIndenting.print("mShadeExpandedHeight=");
        asIndenting.println(this.mShadeExpandedHeight);
        asIndenting.print("mLastShadeFlingWasExpanding=");
        asIndenting.println(this.mLastShadeFlingWasExpanding);
        asIndenting.print("mInitialHeightOnTouch=");
        asIndenting.println(this.mInitialHeightOnTouch);
        asIndenting.print("mInitialTouchX=");
        asIndenting.println(this.mInitialTouchX);
        asIndenting.print("mInitialTouchY=");
        asIndenting.println(this.mInitialTouchY);
        asIndenting.print("mTouchAboveFalsingThreshold=");
        asIndenting.println(this.mTouchAboveFalsingThreshold);
        asIndenting.print("mTracking=");
        asIndenting.println(isTracking());
        asIndenting.print("mTrackingPointer=");
        asIndenting.println(this.mTrackingPointer);
        asIndenting.print("mExpanded=");
        asIndenting.println(getExpanded());
        asIndenting.print("mFullyExpanded=");
        asIndenting.println(this.mFullyExpanded);
        asIndenting.print("isExpandImmediate()=");
        asIndenting.println(isExpandImmediate());
        asIndenting.print("mExpandedWhenExpandingStarted=");
        asIndenting.println(this.mExpandedWhenExpandingStarted);
        asIndenting.print("mAnimatingHiddenFromCollapsed=");
        asIndenting.println(this.mAnimatingHiddenFromCollapsed);
        asIndenting.print("mVisible=");
        asIndenting.println(this.mVisible);
        asIndenting.print("mExpansionHeight=");
        asIndenting.println(this.mExpansionHeight);
        asIndenting.print("mMinExpansionHeight=");
        asIndenting.println(this.mMinExpansionHeight);
        asIndenting.print("mMaxExpansionHeight=");
        asIndenting.println(this.mMaxExpansionHeight);
        asIndenting.print("mShadeExpandedFraction=");
        asIndenting.println(this.mShadeExpandedFraction);
        asIndenting.print("mLastOverscroll=");
        asIndenting.println(this.mLastOverscroll);
        asIndenting.print("mExpansionFromOverscroll=");
        asIndenting.println(this.mExpansionFromOverscroll);
        asIndenting.print("mExpansionEnabledPolicy=");
        asIndenting.println(this.mExpansionEnabledPolicy);
        asIndenting.print("mExpansionEnabledAmbient=");
        asIndenting.println(this.mExpansionEnabledAmbient);
        asIndenting.print("mQuickQsHeaderHeight=");
        asIndenting.println(this.mQuickQsHeaderHeight);
        asIndenting.print("mTwoFingerExpandPossible=");
        asIndenting.println(this.mTwoFingerExpandPossible);
        asIndenting.print("mConflictingExpansionGesture=");
        asIndenting.println(this.mConflictingExpansionGesture);
        asIndenting.print("mAnimatorExpand=");
        asIndenting.println(this.mAnimatorExpand);
        asIndenting.print("mCachedGestureInsets=");
        asIndenting.println(this.mCachedGestureInsets);
        asIndenting.print("mCachedWindowWidth=");
        asIndenting.println(this.mCachedWindowWidth);
        asIndenting.print("mTransitioningToFullShadeProgress=");
        asIndenting.println(this.mTransitioningToFullShadeProgress);
        asIndenting.print("mDistanceForFullShadeTransition=");
        asIndenting.println(this.mDistanceForFullShadeTransition);
        asIndenting.print("mStackScrollerOverscrolling=");
        asIndenting.println(this.mStackScrollerOverscrolling);
        asIndenting.print("mAnimating=");
        asIndenting.println(this.mAnimating);
        asIndenting.print("mIsTranslationResettingAnimator=");
        asIndenting.println(this.mIsTranslationResettingAnimator);
        asIndenting.print("mIsPulseExpansionResettingAnimator=");
        asIndenting.println(this.mIsPulseExpansionResettingAnimator);
        asIndenting.print("mTranslationForFullShadeTransition=");
        asIndenting.println(this.mTranslationForFullShadeTransition);
        asIndenting.print("mAnimateNextNotificationBounds=");
        asIndenting.println(this.mAnimateNextNotificationBounds);
        asIndenting.print("mNotificationBoundsAnimationDelay=");
        asIndenting.println(this.mNotificationBoundsAnimationDelay);
        asIndenting.print("mNotificationBoundsAnimationDuration=");
        asIndenting.println(this.mNotificationBoundsAnimationDuration);
        asIndenting.print("mInterceptRegion=");
        asIndenting.println(this.mInterceptRegion);
        asIndenting.print("mClippingAnimationEndBounds=");
        asIndenting.println(this.mClippingAnimationEndBounds);
        asIndenting.print("mLastClipBounds=");
        asIndenting.println(this.mLastClipBounds);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void flingQs(float r12, int r13, final com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda3 r14, final boolean r15) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.QuickSettingsControllerImpl.flingQs(float, int, com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda3, boolean):void");
    }

    public final float getEdgePosition() {
        float f = this.mQuickQsHeaderHeight;
        AmbientState ambientState = this.mAmbientState;
        float f2 = ambientState.mExpansionFraction;
        return Math.max(f * f2, ((ambientState.mStackTopMargin * f2) + ambientState.mStackY) - ambientState.mScrollY);
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean getExpanded() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyIsQsExpanded.$$delegate_0).getValue()).booleanValue();
    }

    public final int getHeaderHeight() {
        if (isQsFragmentCreated()) {
            return this.mQs.getHeader().getHeight();
        }
        return 0;
    }

    public final float getHeaderTranslation() {
        if (this.mSplitShadeEnabled) {
            return 0.0f;
        }
        int i = this.mBarState;
        KeyguardBypassController keyguardBypassController = this.mKeyguardBypassController;
        if (i == 1 && !keyguardBypassController.getBypassEnabled()) {
            return -this.mQs.getQsMinExpansionHeight();
        }
        float f = this.mShadeExpandedHeight;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        float calculateAppearFraction = notificationStackScrollLayoutController.mView.calculateAppearFraction(f);
        float f2 = -this.mExpansionHeight;
        if (this.mBarState == 0) {
            f2 *= 0.175f;
        }
        if (keyguardBypassController.getBypassEnabled() && this.mBarState == 1) {
            float f3 = notificationStackScrollLayoutController.mView.mAmbientState.mPulseHeight;
            if (f3 == 100000.0f) {
                f3 = 0.0f;
            }
            calculateAppearFraction = MathUtils.smoothStep(0.0f, r0.mIntrinsicPadding, f3);
            f2 = -this.mQs.getQsMinExpansionHeight();
        }
        return Math.min(0.0f, MathUtils.lerp(f2, 0.0f, Math.min(1.0f, calculateAppearFraction)));
    }

    public final float getLockscreenShadeDragProgress() {
        return this.mTransitioningToFullShadeProgress > 0.0f ? this.mLockscreenShadeTransitionController.qsTransitionController.qsTransitionFraction : computeExpansionFraction();
    }

    public int getScrimCornerRadius() {
        return this.mScrimCornerRadius;
    }

    public float getShadeExpandedHeight() {
        return this.mShadeExpandedHeight;
    }

    public boolean isConflictingExpansionGesture() {
        return this.mConflictingExpansionGesture;
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean isCustomizing() {
        return isQsFragmentCreated() && this.mQs.isCustomizing();
    }

    public boolean isExpandImmediate() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyExpandImmediate.$$delegate_0).getValue()).booleanValue();
    }

    public final boolean isExpansionEnabled() {
        return this.mExpansionEnabledPolicy && this.mExpansionEnabledAmbient && !(this.mRemoteInputManager.isRemoteInputActive() && this.mPanelView.getRootWindowInsets().isVisible(WindowInsets.Type.ime()));
    }

    public boolean isOpenQsEvent(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int actionMasked = motionEvent.getActionMasked();
        return (actionMasked == 5 && pointerCount == 2) || (actionMasked == 0 && (motionEvent.isButtonPressed(32) || motionEvent.isButtonPressed(64))) || (actionMasked == 0 && (motionEvent.isButtonPressed(2) || motionEvent.isButtonPressed(4)));
    }

    public final boolean isQsFragmentCreated() {
        return this.mQs != null;
    }

    public final boolean isSplitShadeAndTouchXOutsideQs(float f) {
        return (this.mSplitShadeEnabled && f < this.mQsFrame.getX()) || f > this.mQsFrame.getX() + ((float) this.mQsFrame.getWidth());
    }

    public boolean isTracking() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyQsTracking.$$delegate_0).getValue()).booleanValue();
    }

    public boolean isTwoFingerExpandPossible() {
        return this.mTwoFingerExpandPossible;
    }

    public final void onExpansionStarted() {
        ValueAnimator valueAnimator = this.mExpansionAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ((NotificationPanelViewController) this.mPanelViewControllerLazy.get()).cancelHeightAnimator();
        DejankUtils.notifyRendererOfExpensiveFrame(this.mPanelView, "onExpansionStarted");
        float f = this.mExpansionHeight;
        setExpansionHeight(f);
        this.mNotificationStackScrollLayoutController.checkSnoozeLeavebehind();
        if (f != 0.0f || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mCanDismissLockScreen) {
            return;
        }
        this.mDeviceEntryFaceAuthInteractor.onShadeExpansionStarted();
    }

    public void onHeightChanged() {
        this.mMaxExpansionHeight = isQsFragmentCreated() ? this.mQs.getDesiredHeight() : 0;
        if (getExpanded() && this.mFullyExpanded) {
            this.mExpansionHeight = this.mMaxExpansionHeight;
            NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = this.mExpansionHeightSetToMaxListener;
            if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
                notificationPanelViewController$$ExternalSyntheticLambda11.onExpansionHeightSetToMax(true);
            }
        }
        if (this.mAccessibilityManager.isEnabled()) {
            this.mPanelView.setAccessibilityPaneTitle(((NotificationPanelViewController) this.mPanelViewControllerLazy.get()).determineAccessibilityPaneTitle());
        }
        this.mNotificationStackScrollLayoutController.mView.mMaxTopPadding = this.mMaxExpansionHeight;
    }

    public final void setClippingBounds() {
        int i;
        int right;
        int i2;
        float computeExpansionFraction = computeExpansionFraction();
        int calculateBottomPosition = calculateBottomPosition(computeExpansionFraction);
        boolean z = this.mSplitShadeEnabled;
        int i3 = 1;
        boolean z2 = (!z && (computeExpansionFraction > 0.0f ? 1 : (computeExpansionFraction == 0.0f ? 0 : -1)) == 0 && calculateBottomPosition > 0) || ((computeExpansionFraction > 0.0f ? 1 : (computeExpansionFraction == 0.0f ? 0 : -1)) > 0);
        Lazy lazy = this.mPanelViewControllerLazy;
        if (z) {
            boolean z3 = this.mBarState == 1 && ((NotificationPanelViewController) lazy.get()).mKeyguardOnlyContentAlpha == 1.0f;
            if (computeExpansionFraction == 1.0f && z3) {
                Log.wtf("QuickSettingsController", "Incorrect state, scrim is visible at the same time when clock is visible");
            }
        }
        if (this.mSplitShadeEnabled) {
            i = Math.min(calculateBottomPosition, this.mLargeScreenShadeHeaderHeight);
        } else {
            if (this.mTransitioningToFullShadeProgress > 0.0f) {
                calculateBottomPosition = this.mTransitionToFullShadePosition;
            } else {
                float edgePosition = getEdgePosition();
                if (this.mBarState == 1) {
                    if (!this.mKeyguardBypassController.getBypassEnabled()) {
                        edgePosition = Math.min(calculateBottomPosition, edgePosition);
                    }
                }
                calculateBottomPosition = (int) edgePosition;
            }
            i = (int) (calculateBottomPosition + ((NotificationPanelViewController) lazy.get()).mOverStretchAmount);
            float f = ((NotificationPanelViewController) lazy.get()).mMinFraction;
            if (f > 0.0f && f < 1.0f) {
                i = (int) (MathUtils.saturate(((this.mShadeExpandedFraction - f) / (1.0f - f)) / f) * i);
            }
        }
        boolean z4 = this.mSplitShadeEnabled;
        NotificationPanelView notificationPanelView = this.mPanelView;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        int height = z4 ? notificationStackScrollLayoutController.mView.getHeight() + i + this.mSplitShadeNotificationsScrimMarginBottom : notificationPanelView.getBottom();
        int left = this.mIsFullWidth ? 0 : notificationStackScrollLayoutController.mView.getLeft() + this.mDisplayLeftInset;
        if (this.mIsFullWidth) {
            right = notificationPanelView.getRight();
            i2 = this.mDisplayRightInset;
        } else {
            right = notificationStackScrollLayoutController.mView.getRight();
            i2 = this.mDisplayLeftInset;
        }
        int i4 = right + i2;
        int min = Math.min(i, height);
        if (this.mAnimateNextNotificationBounds && !this.mLastClipBounds.isEmpty()) {
            this.mClippingAnimationEndBounds.set(left, min, i4, height);
            Rect rect = this.mLastClipBounds;
            final int i5 = rect.left;
            final int i6 = rect.top;
            final int i7 = rect.right;
            final int i8 = rect.bottom;
            ValueAnimator valueAnimator = this.mClippingAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mClippingAnimator = ofFloat;
            ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            this.mClippingAnimator.setDuration(this.mNotificationBoundsAnimationDuration);
            this.mClippingAnimator.setStartDelay(this.mNotificationBoundsAnimationDelay);
            final boolean z5 = z2;
            this.mClippingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.shade.QuickSettingsControllerImpl$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
                    int i9 = i5;
                    int i10 = i6;
                    int i11 = i7;
                    int i12 = i8;
                    boolean z6 = z5;
                    quickSettingsControllerImpl.getClass();
                    float animatedFraction = valueAnimator2.getAnimatedFraction();
                    quickSettingsControllerImpl.applyClippingImmediately(z6, (int) MathUtils.lerp(i9, quickSettingsControllerImpl.mClippingAnimationEndBounds.left, animatedFraction), (int) MathUtils.lerp(i10, quickSettingsControllerImpl.mClippingAnimationEndBounds.top, animatedFraction), (int) MathUtils.lerp(i11, quickSettingsControllerImpl.mClippingAnimationEndBounds.right, animatedFraction), (int) MathUtils.lerp(i12, quickSettingsControllerImpl.mClippingAnimationEndBounds.bottom, animatedFraction));
                }
            });
            this.mClippingAnimator.addListener(new AnonymousClass1(this, i3));
            this.mClippingAnimator.start();
        } else if (this.mClippingAnimator != null) {
            this.mClippingAnimationEndBounds.set(left, min, i4, height);
        } else {
            applyClippingImmediately(z2, left, min, i4, height);
        }
        this.mAnimateNextNotificationBounds = false;
        this.mNotificationBoundsAnimationDelay = 0L;
    }

    public final void setExpandImmediate(boolean z) {
        if (z != isExpandImmediate()) {
            ShadeLogger shadeLogger = this.mShadeLog;
            shadeLogger.getClass();
            LogLevel logLevel = LogLevel.VERBOSE;
            ShadeLogger$logQsExpandImmediateChanged$2 shadeLogger$logQsExpandImmediateChanged$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logQsExpandImmediateChanged$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("qsExpandImmediate=", ((LogMessage) obj).getBool1());
                }
            };
            LogBuffer logBuffer = shadeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logQsExpandImmediateChanged$2, null);
            ((LogMessageImpl) obtain).bool1 = z;
            logBuffer.commit(obtain);
            AuthContainerView$$ExternalSyntheticOutline0.m(z, ((ShadeRepositoryImpl) this.mShadeRepository)._legacyExpandImmediate, null);
        }
    }

    public void setExpanded(boolean z) {
        if (getExpanded() != z) {
            StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) this.mShadeRepository)._legacyIsQsExpanded;
            Boolean valueOf = Boolean.valueOf(z);
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
            updateQsState$1();
            NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) this.mPanelViewControllerLazy.get();
            notificationPanelViewController.updateExpandedHeightToMaxHeight();
            ((KeyguardStatusView) notificationPanelViewController.mKeyguardStatusViewController.mView).setImportantForAccessibility(z ? 4 : 0);
            notificationPanelViewController.updateSystemUiStateFlags();
            NavigationBarView navigationBarView = notificationPanelViewController.mNavigationBarController.getNavigationBarView(notificationPanelViewController.mDisplayId);
            if (navigationBarView != null) {
                navigationBarView.updateSlippery();
            }
            int i = this.mMinExpansionHeight;
            int i2 = this.mMaxExpansionHeight;
            boolean z2 = this.mStackScrollerOverscrolling;
            boolean z3 = this.mAnimatorExpand;
            boolean z4 = this.mAnimating;
            ShadeLogger shadeLogger = this.mShadeLog;
            shadeLogger.getClass();
            LogLevel logLevel = LogLevel.VERBOSE;
            ShadeLogger$logQsExpansionChanged$2 shadeLogger$logQsExpansionChanged$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logQsExpansionChanged$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return logMessage.getStr1() + " qsExpanded=" + logMessage.getBool1() + ",qsMinExpansionHeight=" + logMessage.getInt1() + ",qsMaxExpansionHeight=" + logMessage.getInt2() + ",stackScrollerOverscrolling=" + logMessage.getBool2() + ",qsAnimatorExpand=" + logMessage.getBool3() + ",animatingQs=" + logMessage.getLong1();
                }
            };
            LogBuffer logBuffer = shadeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logQsExpansionChanged$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = "QS Expansion Changed.";
            logMessageImpl.bool1 = z;
            logMessageImpl.int1 = i;
            logMessageImpl.int2 = i2;
            logMessageImpl.bool2 = z2;
            logMessageImpl.bool3 = z3;
            logMessageImpl.long1 = Boolean.compare(z4, false);
            logBuffer.commit(obtain);
        }
    }

    public final void setExpansionHeight(float f) {
        if (this.mExpansionHeight == f) {
            return;
        }
        int i = this.mMaxExpansionHeight;
        float f2 = i;
        float min = Math.min(Math.max(f, this.mMinExpansionHeight), f2);
        this.mFullyExpanded = min == f2 && i != 0;
        boolean z = !this.mAnimatorExpand && this.mAnimating;
        if (min > this.mMinExpansionHeight && !getExpanded() && !this.mStackScrollerOverscrolling && !this.mDozing && !z) {
            setExpanded(true);
        } else if (min <= this.mMinExpansionHeight && getExpanded()) {
            setExpanded(false);
        }
        this.mExpansionHeight = min;
        updateExpansion();
        NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = this.mExpansionHeightListener;
        if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
            notificationPanelViewController$$ExternalSyntheticLambda11.f$0.onQsSetExpansionHeightCalled(this.mFullyExpanded);
        }
    }

    public void setQs(QS qs) {
        this.mQs = qs;
    }

    public void setStatusBarMinHeight(int i) {
        this.mStatusBarMinHeight = i;
    }

    public final void setTracking(boolean z) {
        AuthContainerView$$ExternalSyntheticOutline0.m(z, ((ShadeRepositoryImpl) this.mShadeRepository)._legacyQsTracking, null);
    }

    @Override // com.android.systemui.shade.QuickSettingsController
    public final boolean shouldQuickSettingsIntercept(float f, float f2, float f3) {
        int bottom;
        int i;
        QS qs;
        boolean z = this.mBarState == 1;
        if (!isExpansionEnabled() || this.mCollapsedOnDown || ((z && this.mKeyguardBypassController.getBypassEnabled()) || this.mSplitShadeEnabled)) {
            return false;
        }
        if (z || (qs = this.mQs) == null) {
            KeyguardStatusBarView keyguardStatusBarView = this.mKeyguardStatusBar;
            int top = keyguardStatusBarView.getTop();
            bottom = keyguardStatusBarView.getBottom();
            i = top;
        } else {
            i = qs.getHeader().getTop();
            bottom = this.mQs.getHeader().getBottom();
        }
        int top2 = (z || this.mQs == null) ? 0 : this.mQsFrame.getTop();
        this.mInterceptRegion.set((int) this.mQsFrame.getX(), i + top2, this.mQsFrame.getWidth() + ((int) this.mQsFrame.getX()), bottom + top2);
        this.mStatusBarTouchableRegionManager.updateRegionForNotch(this.mInterceptRegion);
        boolean contains = this.mInterceptRegion.contains((int) f, (int) f2);
        if (!getExpanded()) {
            return contains;
        }
        if (!contains) {
            float f4 = 0.0f;
            if (f3 >= 0.0f || isSplitShadeAndTouchXOutsideQs(f)) {
                return false;
            }
            if (((NotificationPanelViewController) this.mPanelViewControllerLazy.get()).mIsGestureNavigation && f2 > r11.mView.getHeight() - r11.mNavigationBarBottomHeight) {
                return false;
            }
            NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
            int childCount = notificationStackScrollLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                ExpandableView expandableView = (ExpandableView) notificationStackScrollLayout.getChildAt(i2);
                if (expandableView.getVisibility() != 8) {
                    float translationY = (expandableView.getTranslationY() + expandableView.mActualHeight) - expandableView.mClipBottomAmount;
                    if (translationY > f4) {
                        f4 = translationY;
                    }
                }
            }
            if (f2 > f4 + notificationStackScrollLayout.mAmbientState.mStackTranslation && f2 > this.mQs.getView().getY() + this.mQs.getView().getHeight()) {
                return false;
            }
        }
        return true;
    }

    public final void traceQsJank(boolean z, boolean z2) {
        InteractionJankMonitor interactionJankMonitor = (InteractionJankMonitor) this.mInteractionJankMonitorLazy.get();
        if (interactionJankMonitor == null) {
            return;
        }
        if (z) {
            interactionJankMonitor.begin(this.mPanelView, 5);
        } else if (z2) {
            interactionJankMonitor.cancel(5);
        } else {
            interactionJankMonitor.end(5);
        }
    }

    public final void trackMovement(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.mQsVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
    }

    public final void updateExpansion() {
        float f;
        if (this.mQs == null) {
            return;
        }
        boolean isExpandImmediate = isExpandImmediate();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        if ((isExpandImmediate || getExpanded()) && !this.mSplitShadeEnabled) {
            f = 1.0f;
        } else if (this.mTransitioningToFullShadeProgress > 0.0f) {
            f = this.mLockscreenShadeTransitionController.qsTransitionController.qsSquishTransitionFraction;
        } else {
            NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
            StackScrollAlgorithm stackScrollAlgorithm = notificationStackScrollLayout.mStackScrollAlgorithm;
            f = stackScrollAlgorithm.getExpansionFractionWithoutShelf(stackScrollAlgorithm.mTempAlgorithmState, notificationStackScrollLayout.mAmbientState);
        }
        float computeExpansionFraction = computeExpansionFraction();
        this.mQs.setQsExpansion(this.mSplitShadeEnabled ? 1.0f : computeExpansionFraction(), this.mShadeExpandedFraction, getHeaderTranslation(), f);
        MediaHierarchyManager mediaHierarchyManager = this.mMediaHierarchyManager;
        boolean z = false;
        if (mediaHierarchyManager.qsExpansion != computeExpansionFraction) {
            mediaHierarchyManager.qsExpansion = computeExpansionFraction;
            MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, false, 3);
            if (mediaHierarchyManager.getQSTransformationProgress() >= 0.0f) {
                mediaHierarchyManager.updateTargetState();
                mediaHierarchyManager.applyTargetStateIfNotAnimating();
            }
        }
        int calculateBottomPosition = calculateBottomPosition(computeExpansionFraction);
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        if (!Float.isNaN(computeExpansionFraction)) {
            float notificationScrimAlpha = ShadeInterpolation.getNotificationScrimAlpha(computeExpansionFraction);
            boolean z2 = calculateBottomPosition > 0;
            if (scrimController.mQsExpansion != notificationScrimAlpha || scrimController.mQsBottomVisible != z2) {
                scrimController.mQsExpansion = notificationScrimAlpha;
                scrimController.mQsBottomVisible = z2;
                ScrimState scrimState = scrimController.mState;
                if ((scrimState == ScrimState.SHADE_LOCKED || scrimState == ScrimState.KEYGUARD || scrimState == ScrimState.PULSING) && scrimController.mExpansionAffectsAlpha) {
                    scrimController.applyAndDispatchState();
                }
            }
        }
        setClippingBounds();
        if (this.mSplitShadeEnabled) {
            NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController.mView;
            float f2 = notificationStackScrollLayout2.mQsExpansionFraction;
            notificationStackScrollLayout2.mQsExpansionFraction = 0.0f;
            notificationStackScrollLayout2.updateUseRoundedRectClipping();
            int i = notificationStackScrollLayout2.mOwnScrollY;
            if (i > 0) {
                notificationStackScrollLayout2.setOwnScrollY((int) MathUtils.lerp(i, 0, notificationStackScrollLayout2.mQsExpansionFraction));
            }
        } else {
            NotificationStackScrollLayout notificationStackScrollLayout3 = notificationStackScrollLayoutController.mView;
            float f3 = notificationStackScrollLayout3.mQsExpansionFraction;
            notificationStackScrollLayout3.mQsExpansionFraction = computeExpansionFraction;
            notificationStackScrollLayout3.updateUseRoundedRectClipping();
            int i2 = notificationStackScrollLayout3.mOwnScrollY;
            if (i2 > 0) {
                notificationStackScrollLayout3.setOwnScrollY((int) MathUtils.lerp(i2, 0, notificationStackScrollLayout3.mQsExpansionFraction));
            }
        }
        NotificationShadeDepthController notificationShadeDepthController = this.mDepthController;
        notificationShadeDepthController.getClass();
        if (Float.isNaN(computeExpansionFraction)) {
            Log.w("DepthController", "Invalid qs expansion");
        } else if (notificationShadeDepthController.qsPanelExpansion != computeExpansionFraction) {
            notificationShadeDepthController.qsPanelExpansion = computeExpansionFraction;
            notificationShadeDepthController.scheduleUpdate();
        }
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        statusBarKeyguardViewManager.getClass();
        Iterator it = ((HashSet) statusBarKeyguardViewManager.mCallbacks).iterator();
        if (it.hasNext()) {
            ((UdfpsKeyguardViewControllerLegacy$statusBarKeyguardViewManagerCallback$1) it.next()).getClass();
            throw null;
        }
        ShadeRepository shadeRepository = this.mShadeRepository;
        StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) shadeRepository)._qsExpansion;
        Float valueOf = Float.valueOf(computeExpansionFraction);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        float lockscreenShadeDragProgress = this.mBarState == 1 ? getLockscreenShadeDragProgress() : this.mShadeExpandedFraction;
        ShadeHeaderController shadeHeaderController = this.mShadeHeaderController;
        if (shadeHeaderController.qsVisible && shadeHeaderController.shadeExpandedFraction != lockscreenShadeDragProgress) {
            shadeHeaderController.header.setAlpha(ShadeInterpolation.getContentAlpha(lockscreenShadeDragProgress));
            shadeHeaderController.shadeExpandedFraction = lockscreenShadeDragProgress;
            shadeHeaderController.updateIgnoredSlots();
        }
        if (shadeHeaderController.visible && shadeHeaderController.qsExpandedFraction != computeExpansionFraction) {
            shadeHeaderController.qsExpandedFraction = computeExpansionFraction;
            shadeHeaderController.iconContainer.mQsExpansionTransitioning = computeExpansionFraction > 0.0f && computeExpansionFraction < 1.0f;
            shadeHeaderController.updatePosition$2();
            shadeHeaderController.updateIgnoredSlots();
        }
        boolean z3 = this.mVisible;
        if (shadeHeaderController.qsVisible != z3) {
            shadeHeaderController.qsVisible = z3;
            HeaderPrivacyIconsController headerPrivacyIconsController = shadeHeaderController.privacyIconsController;
            if (z3) {
                headerPrivacyIconsController.listening = true;
                PrivacyItemController privacyItemController = headerPrivacyIconsController.privacyItemController;
                PrivacyConfig privacyConfig = privacyItemController.privacyConfig;
                headerPrivacyIconsController.micCameraIndicatorsEnabled = privacyConfig.micCameraAvailable;
                headerPrivacyIconsController.locationIndicatorsEnabled = privacyConfig.locationAvailable;
                privacyItemController.addCallback(headerPrivacyIconsController.picCallback);
            } else {
                headerPrivacyIconsController.listening = false;
                headerPrivacyIconsController.privacyItemController.removeCallback(headerPrivacyIconsController.picCallback);
                headerPrivacyIconsController.privacyChipLogged = false;
            }
            shadeHeaderController.updateVisibility$2();
            shadeHeaderController.updatePosition$2();
        }
        boolean z4 = this.mFullyExpanded;
        LightBarController lightBarController = this.mLightBarController;
        if (lightBarController.mQsExpanded != z4) {
            lightBarController.mQsExpanded = z4;
            lightBarController.reevaluate();
        }
        boolean z5 = this.mFullyExpanded && !this.mSplitShadeEnabled;
        AuthContainerView$$ExternalSyntheticOutline0.m(z5, ((ShadeRepositoryImpl) shadeRepository)._legacyQsFullscreen, null);
        NotificationStackScrollLayout notificationStackScrollLayout4 = notificationStackScrollLayoutController.mView;
        if (z5 != notificationStackScrollLayout4.mQsFullScreen) {
            notificationStackScrollLayout4.mQsFullScreen = z5;
            notificationStackScrollLayout4.updateAlgorithmLayoutMinHeight();
            boolean z6 = !notificationStackScrollLayout4.mQsFullScreen && notificationStackScrollLayout4.getScrollRange() > 0;
            if (z6 != notificationStackScrollLayout4.mScrollable) {
                notificationStackScrollLayout4.mScrollable = z6;
                notificationStackScrollLayout4.setFocusable(z6);
                notificationStackScrollLayout4.updateForwardAndBackwardScrollability();
            }
        }
        if (this.mBarState != 1 && (!z5 || this.mExpansionFromOverscroll)) {
            z = true;
        }
        notificationStackScrollLayoutController.mView.mScrollingEnabled = z;
    }

    public final void updateExpansionEnabledAmbient() {
        AmbientState ambientState = this.mAmbientState;
        this.mExpansionEnabledAmbient = this.mSplitShadeEnabled || ((float) ambientState.mScrollY) <= ((float) ambientState.mTopPadding) - this.mQuickQsHeaderHeight;
        QS qs = this.mQs;
        if (qs != null) {
            qs.setHeaderClickable(isExpansionEnabled());
        }
    }

    public final void updateMinHeight() {
        float f = this.mMinExpansionHeight;
        if (this.mBarState == 1 || this.mSplitShadeEnabled) {
            this.mMinExpansionHeight = 0;
        } else {
            this.mMinExpansionHeight = this.mQs.getQsMinExpansionHeight();
        }
        if (this.mExpansionHeight == f) {
            this.mExpansionHeight = this.mMinExpansionHeight;
        }
    }

    public final void updateQsState$1() {
        NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = this.mQsStateUpdateListener;
        if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
            boolean expanded = getExpanded();
            boolean z = this.mStackScrollerOverscrolling;
            KeyguardUserSwitcherController keyguardUserSwitcherController = notificationPanelViewController$$ExternalSyntheticLambda11.f$0.mKeyguardUserSwitcherController;
            if (keyguardUserSwitcherController != null && expanded && !z) {
                keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(true);
            }
        }
        QS qs = this.mQs;
        if (qs == null) {
            return;
        }
        qs.setExpanded(getExpanded());
    }
}
