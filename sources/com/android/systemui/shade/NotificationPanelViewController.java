package com.android.systemui.shade;

import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import android.util.MathUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.compose.animation.scene.SceneKey;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.policy.SystemBarUtils;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.CarrierText;
import com.android.keyguard.CarrierTextController;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.keyguard.KeyguardUnfoldTransition;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.LockIconViewController;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.common.buffer.RingBuffer;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.common.ui.view.LongPressHandlingView;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysTableLogger;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewConfigurator;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.NaturalScrollingSettingObserver;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.binder.KeyguardLongPressViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToDreamingLockscreenHostedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBottomAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardTouchHandlingViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController;
import com.android.systemui.media.controls.ui.controller.MediaHierarchyManager;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.Utilities;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NPVCDownEventState;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.QuickSettingsControllerImpl.NsslOverscrollTopChangedListener;
import com.android.systemui.shade.QuickSettingsControllerImpl.QsFragmentListener;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.ConversationNotificationManager;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator$setStackScroller$1;
import com.android.systemui.statusbar.notification.PropertyAnimator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator;
import com.android.systemui.statusbar.notification.stack.StackStateAnimator;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda1;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda25;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewController;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager;
import com.android.systemui.statusbar.phone.TapAgainView;
import com.android.systemui.statusbar.phone.TapAgainViewController;
import com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BrightnessMirrorController$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherController;
import com.android.systemui.statusbar.policy.KeyguardUserSwitcherView;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.ui.viewmodel.KeyguardStatusBarViewModel;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.Utils;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import dagger.internal.Provider;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableSet;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationPanelViewController implements ShadeSurface, Dumpable {
    public final AccessibilityManager mAccessibilityManager;
    public final ActiveNotificationsInteractor mActiveNotificationsInteractor;
    public final ActivityStarter mActivityStarter;
    public boolean mAllowExpandForSmallExpansion;
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public int mAmbientIndicationBottomPadding;
    public final AmbientState mAmbientState;
    public boolean mAnimateAfterExpanding;
    public final NotificationPanelViewController$$ExternalSyntheticLambda0 mAnimateKeyguardBottomAreaInvisibleEndRunnable;
    public boolean mAnimateNextPositionUpdate;
    public boolean mAnimatingOnDown;
    public final AuthController mAuthController;
    public int mBarState;
    public boolean mBlockingExpansionForCurrentTouch;
    public float mBottomAreaShadeAlpha;
    public final ValueAnimator mBottomAreaShadeAlphaAnimator;
    public boolean mBouncerShowing;
    public CentralSurfacesImpl mCentralSurfaces;
    KeyguardClockPositionAlgorithm mClockPositionAlgorithm;
    public boolean mClosingWithAlphaFadeOut;
    public boolean mCollapsedAndHeadsUpOnDown;
    public boolean mCollapsedOnDown;
    public final CommandQueue mCommandQueue;
    public final ConfigurationController mConfigurationController;
    public final ContentResolver mContentResolver;
    public final ConversationNotificationManager mConversationNotificationManager;
    public int mCurrentPanelState;
    public final NotificationShadeDepthController mDepthController;
    public final DeviceEntryFaceAuthInteractor mDeviceEntryFaceAuthInteractor;
    public final int mDisplayId;
    public long mDownTime;
    public float mDownX;
    public float mDownY;
    public final DozeLog mDozeLog;
    public final DozeParameters mDozeParameters;
    public boolean mDozing;
    public boolean mDozingOnDown;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mDreamingLockscreenHostedToLockscreenTransition;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mDreamingToLockscreenTransition;
    public int mDreamingToLockscreenTransitionTranslationY;
    public final DreamingToLockscreenTransitionViewModel mDreamingToLockscreenTransitionViewModel;
    public boolean mExpandLatencyTracking;
    public float mExpandedFraction;
    public boolean mExpanding;
    public boolean mExpandingFromHeadsUp;
    public float mExpansionDragDownAmountPx;
    public boolean mExpectingSynthesizedDown;
    public final FalsingCollector mFalsingCollector;
    public final FalsingManager mFalsingManager;
    public final FeatureFlags mFeatureFlags;
    public int mFixedDuration;
    public FlingAnimationUtils mFlingAnimationUtils;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mFlingAnimationUtilsBuilder;
    public final FlingAnimationUtils mFlingAnimationUtilsClosing;
    public final FlingAnimationUtils mFlingAnimationUtilsDismissing;
    public final NotificationPanelViewController$$ExternalSyntheticLambda0 mFlingCollapseRunnable;
    public boolean mForceFlingAnimationForTest;
    public final FragmentService mFragmentService;
    public boolean mGestureWaitForTouchSlop;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mGoneToDreamingLockscreenHostedTransition;
    public final GoneToDreamingLockscreenHostedTransitionViewModel mGoneToDreamingLockscreenHostedTransitionViewModel;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mGoneToDreamingTransition;
    public int mGoneToDreamingTransitionTranslationY;
    public final GoneToDreamingTransitionViewModel mGoneToDreamingTransitionViewModel;
    public final NotificationGutsManager mGutsManager;
    public boolean mHandlingPointerUp;
    public boolean mHasLayoutedSinceDown;
    public boolean mHasVibratedOnOpen;
    public boolean mHeadsUpAnimatingAway;
    public HeadsUpAppearanceController mHeadsUpAppearanceController;
    public final NotificationPanelViewController$$ExternalSyntheticLambda0 mHeadsUpExistenceChangedRunnable;
    public int mHeadsUpInset;
    public HeadsUpManager mHeadsUpManager;
    public boolean mHeadsUpPinnedMode;
    public int mHeadsUpStartHeight;
    public HeadsUpTouchHelper mHeadsUpTouchHelper;
    public ValueAnimator mHeightAnimator;
    public CentralSurfacesImpl$$ExternalSyntheticLambda25 mHideExpandedRunnable;
    public float mHintDistance;
    public boolean mIgnoreXTouchSlop;
    public int mIndicationBottomPadding;
    public float mInitialExpandX;
    public float mInitialExpandY;
    public float mInitialOffsetOnTouch;
    public boolean mInitialTouchFromKeyguard;
    public boolean mInstantExpanding;
    public float mInterpolatedDarkAmount;
    public boolean mIsExpandingOrCollapsing;
    public boolean mIsFlinging;
    public boolean mIsFullWidth;
    public boolean mIsGestureNavigation;
    public boolean mIsOcclusionTransitionRunning;
    public boolean mIsPanelCollapseOnQQS;
    public boolean mIsSpringBackAnimation;
    public boolean mIsTrackpadReverseScroll;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mKeyguardBottomAreaViewControllerProvider;
    public final KeyguardBypassController mKeyguardBypassController;
    public final KeyguardClockInteractor mKeyguardClockInteractor;
    public final KeyguardIndicationController mKeyguardIndicationController;
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardMediaController mKeyguardMediaController;
    public float mKeyguardNotificationBottomPadding;
    public float mKeyguardNotificationTopPadding;
    public float mKeyguardOnlyContentAlpha;
    public int mKeyguardOnlyTransitionTranslationY;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mKeyguardQsUserSwitchComponentFactory;
    public KeyguardQsUserSwitchController mKeyguardQsUserSwitchController;
    public boolean mKeyguardQsUserSwitchEnabled;
    public final KeyguardStateControllerImpl mKeyguardStateController;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mKeyguardStatusBarViewComponentFactory;
    public KeyguardStatusBarViewController mKeyguardStatusBarViewController;
    public KeyguardStatusViewController mKeyguardStatusViewController;
    public final KeyguardTransitionInteractor mKeyguardTransitionInteractor;
    public final Optional mKeyguardUnfoldTransition;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mKeyguardUserSwitcherComponentFactory;
    public KeyguardUserSwitcherController mKeyguardUserSwitcherController;
    public boolean mKeyguardUserSwitcherEnabled;
    public final KeyguardViewConfigurator mKeyguardViewConfigurator;
    public final NPVCDownEventState.Buffer mLastDownEvents;
    public boolean mLastEventSynthesizedDown;
    public float mLastGesturedOverExpansion;
    public final LatencyTracker mLatencyTracker;
    public final LayoutInflater mLayoutInflater;
    public float mLinearDarkAmount;
    public boolean mListenForHeadsUp;
    public final LockIconViewController mLockIconViewController;
    public final LockscreenGestureLogger mLockscreenGestureLogger;
    public final LockscreenShadeTransitionController mLockscreenShadeTransitionController;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mLockscreenToDreamingLockscreenHostedTransition;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mLockscreenToDreamingTransition;
    public int mLockscreenToDreamingTransitionTranslationY;
    public final LockscreenToDreamingTransitionViewModel mLockscreenToDreamingTransitionViewModel;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mLockscreenToOccludedTransition;
    public final CoroutineDispatcher mMainDispatcher;
    public int mMaxAllowedKeyguardNotifications;
    public int mMaxOverscrollAmountForPulse;
    public final NotificationPanelViewController$$ExternalSyntheticLambda0 mMaybeHideExpandedRunnable;
    public final MediaDataManager mMediaDataManager;
    public final MediaHierarchyManager mMediaHierarchyManager;
    public final MetricsLogger mMetricsLogger;
    public float mMinFraction;
    public boolean mMotionAborted;
    public final NaturalScrollingSettingObserver mNaturalScrollingSettingObserver;
    public int mNavigationBarBottomHeight;
    public final NavigationBarControllerImpl mNavigationBarController;
    public float mNextCollapseSpeedUpFactor;
    public NotificationsQuickSettingsContainer mNotificationContainerParent;
    public final NotificationStackScrollLayoutController.NotificationListContainerImpl mNotificationListContainer;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final NotificationStackScrollLayoutController mNotificationStackScrollLayoutController;
    public final NotificationStackSizeCalculator mNotificationStackSizeCalculator;
    public final boolean mNotificationsDragEnabled;
    public final NotificationsQSContainerController mNotificationsQSContainerController;
    public final NotificationPanelViewController$$ExternalSyntheticLambda2 mOccludedToLockscreenTransition;
    public int mOldLayoutDirection;
    public ShadeControllerImpl.AnonymousClass2 mOpenCloseListener;
    public float mOverExpansion;
    public float mOverStretchAmount;
    public int mPanelAlpha;
    public final AnimatableProperty.AnonymousClass6 mPanelAlphaAnimator;
    public BrightnessMirrorController$$ExternalSyntheticLambda0 mPanelAlphaEndAction;
    public final AnimationProperties mPanelAlphaInPropertiesAnimator;
    public final AnimationProperties mPanelAlphaOutPropertiesAnimator;
    public boolean mPanelClosedOnDown;
    public float mPanelFlingOvershootAmount;
    public boolean mPanelUpdateWhenAnimatorEnds;
    public final PowerInteractor mPowerInteractor;
    public final PulseExpansionHandler mPulseExpansionHandler;
    public boolean mPulsing;
    public final QuickSettingsControllerImpl mQsController;
    public final Resources mResources;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public final ScrimController mScrimController;
    public final SettingsChangeObserver mSettingsChangeObserver;
    public final ShadeAnimationInteractor mShadeAnimationInteractor;
    public final ShadeExpansionStateManager mShadeExpansionStateManager;
    public final ShadeHeaderController mShadeHeaderController;
    public final ShadeLogger mShadeLog;
    public final ShadeRepository mShadeRepository;
    public final AnonymousClass10 mShadeViewStateProvider;
    public final SharedNotificationContainerInteractor mSharedNotificationContainerInteractor;
    public boolean mShowIconsWhenExpanded;
    public float mSlopMultiplier;
    public boolean mSplitShadeEnabled;
    public int mSplitShadeFullTransitionDistance;
    public int mSplitShadeScrimTransitionDistance;
    public final SplitShadeStateControllerImpl mSplitShadeStateController;
    public int mStackScrollerMeasuringPass;
    public int mStatusBarHeaderHeightKeyguard;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public int mStatusBarMinHeight;
    public final IStatusBarService mStatusBarService;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final StatusBarTouchableRegionManager mStatusBarTouchableRegionManager;
    public final SysUiState mSysUiState;
    public final SystemClock mSystemClock;
    public final TapAgainViewController mTapAgainViewController;
    Set mTestSetOfAnimatorsUsed;
    public boolean mTouchAboveFalsingThreshold;
    public boolean mTouchDisabled;
    public int mTouchSlop;
    public boolean mTouchSlopExceeded;
    public boolean mTouchSlopExceededBeforeDown;
    public boolean mTouchStartedInEmptyArea;
    public ExpandableNotificationRow mTrackedHeadsUpNotification;
    public int mTrackingPointer;
    public ShadeControllerImpl$$ExternalSyntheticLambda1 mTrackingStartedListener;
    public float mUdfpsMaxYBurnInOffset;
    public final UnlockedScreenOffAnimationController mUnlockedScreenOffAnimationController;
    public boolean mUpdateFlingOnLayout;
    public float mUpdateFlingVelocity;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public boolean mUpwardsWhenThresholdReached;
    public boolean mUseExternalTouch;
    public final UserManager mUserManager;
    public final boolean mVibrateOnOpening;
    public final VibratorHelper mVibratorHelper;
    public final NotificationPanelView mView;
    public String mViewName;
    public final NotificationWakeUpCoordinator mWakeUpCoordinator;
    public static final Rect M_DUMMY_DIRTY_RECT = new Rect(0, 0, 1, 1);
    public static final Rect EMPTY_RECT = new Rect();
    public final NotificationPanelViewController$$ExternalSyntheticLambda11 mOnEmptySpaceClickListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
    public final ShadeHeadsUpChangedListener mOnHeadsUpChangedListener = new ShadeHeadsUpChangedListener();
    public final ConfigurationListener mConfigurationListener = new ConfigurationListener();
    public final StatusBarStateListener mStatusBarStateListener = new StatusBarStateListener();
    public final VelocityTracker mVelocityTracker = VelocityTracker.obtain();
    public final NotificationPanelViewController$$ExternalSyntheticLambda12 mFalsingTapListener = new FalsingManager.FalsingTapListener() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda12
        @Override // com.android.systemui.plugins.FalsingManager.FalsingTapListener
        public final void onAdditionalTapRequired() {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) notificationPanelViewController.mStatusBarStateController;
            if (statusBarStateControllerImpl.mState == 2) {
                final TapAgainViewController tapAgainViewController = notificationPanelViewController.mTapAgainViewController;
                ExecutorImpl.ExecutionToken executionToken = tapAgainViewController.mHideCanceler;
                if (executionToken != null) {
                    executionToken.run();
                }
                ((TapAgainView) tapAgainViewController.mView).animateIn();
                tapAgainViewController.mHideCanceler = tapAgainViewController.mDelayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.TapAgainViewController$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TapAgainViewController tapAgainViewController2 = TapAgainViewController.this;
                        tapAgainViewController2.mHideCanceler = null;
                        ((TapAgainView) tapAgainViewController2.mView).animateOut();
                    }
                }, tapAgainViewController.mDoubleTapTimeMs);
            } else {
                notificationPanelViewController.mKeyguardIndicationController.showTransientIndication(R.string.notification_tap_again);
            }
            if (statusBarStateControllerImpl.mIsDozing) {
                return;
            }
            notificationPanelViewController.mVibratorHelper.getClass();
            notificationPanelViewController.mView.performHapticFeedback(17);
        }
    };
    public final ShadeAccessibilityDelegate mAccessibilityDelegate = new ShadeAccessibilityDelegate();
    public final TouchHandler mTouchHandler = new TouchHandler();
    public float mExpandedHeight = 0.0f;
    public int mDisplayTopInset = 0;
    public int mDisplayRightInset = 0;
    public int mDisplayLeftInset = 0;
    public final KeyguardClockPositionAlgorithm.Result mClockPositionResult = new KeyguardClockPositionAlgorithm.Result();
    public final AnonymousClass10 mShadeHeadsUpTracker = new AnonymousClass10();
    public final ShadeFoldAnimatorImpl mShadeFoldAnimator = new ShadeFoldAnimatorImpl();
    public final ArrayList mTrackingHeadsUpListeners = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.NotificationPanelViewController$10, reason: invalid class name */
    public final class AnonymousClass10 implements ShadeHeadsUpTracker {
        public /* synthetic */ AnonymousClass10() {
        }

        @Override // com.android.systemui.shade.ShadeHeadsUpTracker
        public void addTrackingHeadsUpListener(Consumer consumer) {
            NotificationPanelViewController.this.mTrackingHeadsUpListeners.add(consumer);
        }

        public float getPanelViewExpandedHeight() {
            return NotificationPanelViewController.this.mExpandedHeight;
        }

        @Override // com.android.systemui.shade.ShadeHeadsUpTracker
        public ExpandableNotificationRow getTrackedHeadsUpNotification() {
            return NotificationPanelViewController.this.mTrackedHeadsUpNotification;
        }

        @Override // com.android.systemui.shade.ShadeHeadsUpTracker
        public void removeTrackingHeadsUpListener(HeadsUpAppearanceController$$ExternalSyntheticLambda0 headsUpAppearanceController$$ExternalSyntheticLambda0) {
            NotificationPanelViewController.this.mTrackingHeadsUpListeners.remove(headsUpAppearanceController$$ExternalSyntheticLambda0);
        }

        @Override // com.android.systemui.shade.ShadeHeadsUpTracker
        public void setHeadsUpAppearanceController(HeadsUpAppearanceController headsUpAppearanceController) {
            NotificationPanelViewController.this.mHeadsUpAppearanceController = headsUpAppearanceController;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConfigurationListener implements ConfigurationController.ConfigurationListener {
        public ConfigurationListener() {
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onDensityOrFontScaleChanged() {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.getClass();
            notificationPanelViewController.reInflateViews();
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onSmallestScreenWidthChanged() {
            Trace.beginSection("onSmallestScreenWidthChanged");
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.getClass();
            boolean z = notificationPanelViewController.mKeyguardUserSwitcherEnabled;
            boolean z2 = notificationPanelViewController.mKeyguardQsUserSwitchEnabled;
            notificationPanelViewController.updateUserSwitcherFlags();
            if (z != notificationPanelViewController.mKeyguardUserSwitcherEnabled || z2 != notificationPanelViewController.mKeyguardQsUserSwitchEnabled) {
                notificationPanelViewController.reInflateViews();
            }
            Trace.endSection();
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onThemeChanged() {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.getClass();
            notificationPanelViewController.reInflateViews();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsChangeObserver extends ContentObserver {
        public SettingsChangeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            NotificationPanelViewController.this.getClass();
            NotificationPanelViewController.this.reInflateViews();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShadeAccessibilityDelegate extends View.AccessibilityDelegate {
        public ShadeAccessibilityDelegate() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i != AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId() && i != AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId()) {
                return super.performAccessibilityAction(view, i, bundle);
            }
            NotificationPanelViewController.this.mStatusBarKeyguardViewManager.showPrimaryBouncer(true);
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShadeFoldAnimatorImpl implements ShadeFoldAnimator {
        public ShadeFoldAnimatorImpl() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShadeHeadsUpChangedListener implements OnHeadsUpChangedListener {
        public ShadeHeadsUpChangedListener() {
        }

        @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
        public final void onHeadsUpPinned(NotificationEntry notificationEntry) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            if (notificationPanelViewController.isKeyguardShowing$1()) {
                return;
            }
            NotificationStackScrollLayout notificationStackScrollLayout = notificationPanelViewController.mNotificationStackScrollLayoutController.mView;
            notificationStackScrollLayout.getClass();
            notificationStackScrollLayout.generateHeadsUpAnimation(notificationEntry.row, true);
        }

        @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
        public final void onHeadsUpPinnedModeChanged(boolean z) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            if (z) {
                notificationPanelViewController.mHeadsUpExistenceChangedRunnable.run();
            } else {
                notificationPanelViewController.mHeadsUpAnimatingAway = true;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = notificationPanelViewController.mNotificationStackScrollLayoutController;
                notificationStackScrollLayoutController.mView.setHeadsUpAnimatingAway(true);
                notificationPanelViewController.updateVisibility$6();
                notificationStackScrollLayoutController.mView.mAnimationFinishedRunnables.add(notificationPanelViewController.mHeadsUpExistenceChangedRunnable);
            }
            notificationPanelViewController.updateGestureExclusionRect();
            notificationPanelViewController.mHeadsUpPinnedMode = z;
            notificationPanelViewController.updateVisibility$6();
            notificationPanelViewController.mKeyguardStatusBarViewController.updateForHeadsUp(true);
        }

        @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
        public final void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
            ExpandableNotificationRow expandableNotificationRow;
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            if (!notificationPanelViewController.isFullyCollapsed() || (expandableNotificationRow = notificationEntry.row) == null || !expandableNotificationRow.mIsHeadsUp || notificationPanelViewController.isKeyguardShowing$1()) {
                return;
            }
            NotificationStackScrollLayout notificationStackScrollLayout = notificationPanelViewController.mNotificationStackScrollLayoutController.mView;
            notificationStackScrollLayout.getClass();
            notificationStackScrollLayout.generateHeadsUpAnimation(notificationEntry.row, false);
            ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
            if (expandableNotificationRow2 != null) {
                expandableNotificationRow2.mMustStayOnScreen = false;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShadeLayoutChangeListener implements View.OnLayoutChangeListener {
        public ShadeLayoutChangeListener() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            DejankUtils.startDetectingBlockingIpcs("NVP#onLayout");
            NotificationPanelViewController.this.updateExpandedHeightToMaxHeight();
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.mHasLayoutedSinceDown = true;
            if (notificationPanelViewController.mUpdateFlingOnLayout) {
                notificationPanelViewController.cancelHeightAnimator();
                notificationPanelViewController.mView.removeCallbacks(notificationPanelViewController.mFlingCollapseRunnable);
                NotificationPanelViewController notificationPanelViewController2 = NotificationPanelViewController.this;
                notificationPanelViewController2.fling(notificationPanelViewController2.mUpdateFlingVelocity, 1.0f, true, false);
                NotificationPanelViewController.this.mUpdateFlingOnLayout = false;
            }
            NotificationPanelViewController.this.mUnlockedScreenOffAnimationController.getClass();
            NotificationPanelViewController notificationPanelViewController3 = NotificationPanelViewController.this;
            boolean z = ((float) notificationPanelViewController3.mNotificationStackScrollLayoutController.mView.getWidth()) == ((float) NotificationPanelViewController.this.mView.getWidth());
            notificationPanelViewController3.mIsFullWidth = z;
            notificationPanelViewController3.mScrimController.setClipsQsScrim(z);
            notificationPanelViewController3.mNotificationStackScrollLayoutController.mView.mAmbientState.mIsSmallScreen = z;
            QuickSettingsControllerImpl quickSettingsControllerImpl = notificationPanelViewController3.mQsController;
            quickSettingsControllerImpl.mIsFullWidth = z;
            QS qs = quickSettingsControllerImpl.mQs;
            if (qs != null) {
                qs.setIsNotificationPanelFullWidth(z);
            }
            QuickSettingsControllerImpl quickSettingsControllerImpl2 = NotificationPanelViewController.this.mQsController;
            int i9 = quickSettingsControllerImpl2.mMaxExpansionHeight;
            if (quickSettingsControllerImpl2.isQsFragmentCreated()) {
                quickSettingsControllerImpl2.updateMinHeight();
                int desiredHeight = quickSettingsControllerImpl2.mQs.getDesiredHeight();
                quickSettingsControllerImpl2.mMaxExpansionHeight = desiredHeight;
                quickSettingsControllerImpl2.mNotificationStackScrollLayoutController.mView.mMaxTopPadding = desiredHeight;
            }
            NotificationPanelViewController.this.positionClockAndNotifications(false);
            QuickSettingsControllerImpl quickSettingsControllerImpl3 = NotificationPanelViewController.this.mQsController;
            if (quickSettingsControllerImpl3.getExpanded() && quickSettingsControllerImpl3.mFullyExpanded) {
                quickSettingsControllerImpl3.mExpansionHeight = quickSettingsControllerImpl3.mMaxExpansionHeight;
                NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = quickSettingsControllerImpl3.mExpansionHeightSetToMaxListener;
                if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
                    notificationPanelViewController$$ExternalSyntheticLambda11.onExpansionHeightSetToMax(true);
                }
                int i10 = quickSettingsControllerImpl3.mMaxExpansionHeight;
                if (i10 != i9) {
                    ValueAnimator valueAnimator = quickSettingsControllerImpl3.mSizeChangeAnimator;
                    if (valueAnimator != null) {
                        i9 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        quickSettingsControllerImpl3.mSizeChangeAnimator.cancel();
                    }
                    ValueAnimator ofInt = ValueAnimator.ofInt(i9, i10);
                    quickSettingsControllerImpl3.mSizeChangeAnimator = ofInt;
                    ofInt.setDuration(300L);
                    quickSettingsControllerImpl3.mSizeChangeAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
                    quickSettingsControllerImpl3.mSizeChangeAnimator.addUpdateListener(new QuickSettingsControllerImpl$$ExternalSyntheticLambda1(quickSettingsControllerImpl3, 1));
                    quickSettingsControllerImpl3.mSizeChangeAnimator.addListener(new QuickSettingsControllerImpl.AnonymousClass1(quickSettingsControllerImpl3, 0));
                    quickSettingsControllerImpl3.mSizeChangeAnimator.start();
                }
            } else if (quickSettingsControllerImpl3.getExpanded() || quickSettingsControllerImpl3.mExpansionAnimator != null) {
                quickSettingsControllerImpl3.mShadeLog.v("onLayoutChange: qs expansion not set");
            } else {
                quickSettingsControllerImpl3.setExpansionHeight(quickSettingsControllerImpl3.mMinExpansionHeight + quickSettingsControllerImpl3.mLastOverscroll);
            }
            NotificationPanelViewController notificationPanelViewController4 = NotificationPanelViewController.this;
            notificationPanelViewController4.updateExpandedHeight(notificationPanelViewController4.mExpandedHeight);
            NotificationPanelViewController notificationPanelViewController5 = NotificationPanelViewController.this;
            if (notificationPanelViewController5.mBarState == 1) {
                notificationPanelViewController5.mKeyguardStatusBarViewController.updateViewState();
            }
            notificationPanelViewController5.mQsController.updateExpansion();
            QuickSettingsControllerImpl quickSettingsControllerImpl4 = NotificationPanelViewController.this.mQsController;
            if (quickSettingsControllerImpl4.mSizeChangeAnimator != null && quickSettingsControllerImpl4.isQsFragmentCreated()) {
                QS qs2 = quickSettingsControllerImpl4.mQs;
                qs2.setHeightOverride(qs2.getDesiredHeight());
            }
            NotificationPanelViewController.this.updateMaxHeadsUpTranslation();
            NotificationPanelViewController.this.updateGestureExclusionRect();
            NotificationPanelViewController.this.getClass();
            DejankUtils.stopDetectingBlockingIpcs("NVP#onLayout");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatusBarStateListener implements StatusBarStateController.StateListener {
        public StatusBarStateListener() {
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozeAmountChanged(float f, float f2) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.mInterpolatedDarkAmount = f2;
            notificationPanelViewController.mLinearDarkAmount = f;
            notificationPanelViewController.positionClockAndNotifications(false);
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            onStateChanged(i, false);
        }

        public final void onStateChanged(int i, boolean z) {
            QS qs;
            long j;
            long j2;
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            boolean goingToFullShade = ((StatusBarStateControllerImpl) notificationPanelViewController.mStatusBarStateController).goingToFullShade();
            KeyguardStateControllerImpl keyguardStateControllerImpl = notificationPanelViewController.mKeyguardStateController;
            boolean z2 = keyguardStateControllerImpl.mKeyguardFadingAway;
            int i2 = notificationPanelViewController.mBarState;
            boolean z3 = i == 1;
            notificationPanelViewController.mDozeParameters.mScreenOffAnimationController.shouldDelayKeyguardShow();
            notificationPanelViewController.mBarState = i;
            QuickSettingsControllerImpl quickSettingsControllerImpl = notificationPanelViewController.mQsController;
            quickSettingsControllerImpl.mBarState = i;
            boolean z4 = i == 1 && (i2 == 0 || i2 == 2);
            if (notificationPanelViewController.mSplitShadeEnabled && z4) {
                quickSettingsControllerImpl.closeQs();
            }
            if (i2 == 1 && (goingToFullShade || i == 2)) {
                if (keyguardStateControllerImpl.mKeyguardFadingAway) {
                    j = keyguardStateControllerImpl.mKeyguardFadingAwayDelay;
                    keyguardStateControllerImpl.getClass();
                    j2 = keyguardStateControllerImpl.mKeyguardFadingAwayDuration / 2;
                } else {
                    j = 0;
                    j2 = 360;
                }
                final KeyguardStatusBarViewController keyguardStatusBarViewController = notificationPanelViewController.mKeyguardStatusBarViewController;
                keyguardStatusBarViewController.getClass();
                keyguardStatusBarViewController.mLogger.buffer.log("KeyguardStatusBarViewController", LogLevel.DEBUG, "animating status bar out", null);
                androidx.core.animation.ValueAnimator ofFloat = androidx.core.animation.ValueAnimator.ofFloat(((KeyguardStatusBarView) keyguardStatusBarViewController.mView).getAlpha(), 0.0f);
                ofFloat.addUpdateListener(keyguardStatusBarViewController.mAnimatorUpdateListener);
                ofFloat.setStartDelay(j);
                ofFloat.setDuration(j2);
                ofFloat.setInterpolator(InterpolatorsAndroidX.LINEAR_OUT_SLOW_IN);
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.7
                    public AnonymousClass7() {
                    }

                    @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
                    public final void onAnimationEnd$1(Animator animator) {
                        KeyguardStatusBarViewController keyguardStatusBarViewController2 = KeyguardStatusBarViewController.this;
                        ((KeyguardStatusBarView) keyguardStatusBarViewController2.mView).setVisibility(4);
                        ((KeyguardStatusBarView) keyguardStatusBarViewController2.mView).setAlpha(1.0f);
                        keyguardStatusBarViewController2.mKeyguardStatusBarAnimateAlpha = 1.0f;
                    }
                });
                ofFloat.start(false);
                quickSettingsControllerImpl.updateMinHeight();
            } else if (i2 == 2 && i == 1) {
                notificationPanelViewController.mKeyguardStatusBarViewController.animateKeyguardStatusBarIn();
                NotificationStackScrollLayout notificationStackScrollLayout = notificationPanelViewController.mNotificationStackScrollLayoutController.mView;
                notificationStackScrollLayout.mScroller.abortAnimation();
                notificationStackScrollLayout.setOwnScrollY(0);
            } else {
                if (i2 != 0 || i != 1 || !notificationPanelViewController.mScreenOffAnimationController.isKeyguardShowDelayed() || z) {
                    boolean isOnAod = notificationPanelViewController.isOnAod();
                    ShadeLogger shadeLogger = notificationPanelViewController.mShadeLog;
                    shadeLogger.getClass();
                    LogLevel logLevel = LogLevel.VERBOSE;
                    ShadeLogger$logKeyguardStatudBarVisibiliy$2 shadeLogger$logKeyguardStatudBarVisibiliy$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logKeyguardStatudBarVisibiliy$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            boolean bool1 = logMessage.getBool1();
                            boolean bool2 = logMessage.getBool2();
                            int int1 = logMessage.getInt1();
                            int int2 = logMessage.getInt2();
                            boolean bool3 = logMessage.getBool3();
                            StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("Setting keyguard status bar visibility to: ", ", isOnAod: ", "oldShadeState: ", bool1, bool2);
                            ViewPager$$ExternalSyntheticOutline0.m(m, int1, ", newShadeState: ", int2, ",animatingUnlockedShadeToKeyguardBypass: ");
                            m.append(bool3);
                            return m.toString();
                        }
                    };
                    LogBuffer logBuffer = shadeLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logKeyguardStatudBarVisibiliy$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.bool1 = z3;
                    logMessageImpl.bool2 = isOnAod;
                    logMessageImpl.bool3 = z;
                    logMessageImpl.int1 = i2;
                    logMessageImpl.int2 = i;
                    logBuffer.commit(obtain);
                    KeyguardStatusBarViewController keyguardStatusBarViewController2 = notificationPanelViewController.mKeyguardStatusBarViewController;
                    int i3 = keyguardStatusBarViewController2.mDisableStateTracker.isDisabled ? 4 : z3 ? 0 : 4;
                    ((KeyguardStatusBarView) keyguardStatusBarViewController2.mView).setAlpha(1.0f);
                    ((KeyguardStatusBarView) keyguardStatusBarViewController2.mView).setVisibility(i3);
                }
                if (z3 && i2 != notificationPanelViewController.mBarState && (qs = quickSettingsControllerImpl.mQs) != null) {
                    qs.hideImmediately();
                }
            }
            notificationPanelViewController.mKeyguardStatusBarViewController.updateForHeadsUp(true);
            if (z3) {
                KeyguardRepositoryImpl keyguardRepositoryImpl = notificationPanelViewController.mKeyguardInteractor.repository;
                Boolean bool = Boolean.FALSE;
                StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._animateBottomAreaDozingTransitions;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
            }
            notificationPanelViewController.maybeAnimateBottomAreaAlpha();
            quickSettingsControllerImpl.updateQsState$1();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchHandler implements View.OnTouchListener, Gefingerpoken {
        public long mLastTouchDownTime = -1;

        public TouchHandler() {
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int pointerId;
            int pointerId2;
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            if (!notificationPanelViewController.mUseExternalTouch) {
                return false;
            }
            notificationPanelViewController.mShadeLog.logMotionEvent(motionEvent, "NPVC onInterceptTouchEvent");
            QS qs = NotificationPanelViewController.this.mQsController.mQs;
            if (qs != null ? qs.disallowPanelTouches() : false) {
                NotificationPanelViewController.this.mShadeLog.logMotionEvent(motionEvent, "NPVC not intercepting touch, panel touches disallowed");
                return false;
            }
            NotificationPanelViewController.m859$$Nest$minitDownStates(NotificationPanelViewController.this, motionEvent);
            NotificationPanelViewController notificationPanelViewController2 = NotificationPanelViewController.this;
            if (notificationPanelViewController2.mCentralSurfaces.mBouncerShowing) {
                notificationPanelViewController2.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: bouncer is showing");
                return true;
            }
            if (notificationPanelViewController2.mCommandQueue.panelsEnabled()) {
                NotificationPanelViewController notificationPanelViewController3 = NotificationPanelViewController.this;
                if (!(notificationPanelViewController3.mNotificationStackScrollLayoutController.mLongPressedView != null) && notificationPanelViewController3.mHeadsUpTouchHelper.onInterceptTouchEvent(motionEvent)) {
                    NotificationPanelViewController.this.mMetricsLogger.count("panel_open", 1);
                    NotificationPanelViewController.this.mMetricsLogger.count("panel_open_peek", 1);
                    NotificationPanelViewController.this.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: HeadsUpTouchHelper");
                    return true;
                }
            }
            NotificationPanelViewController notificationPanelViewController4 = NotificationPanelViewController.this;
            if (!notificationPanelViewController4.mQsController.shouldQuickSettingsIntercept(notificationPanelViewController4.mDownX, notificationPanelViewController4.mDownY, 0.0f) && NotificationPanelViewController.this.mPulseExpansionHandler.onInterceptTouchEvent(motionEvent)) {
                NotificationPanelViewController.this.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: PulseExpansionHandler");
                return true;
            }
            if (!NotificationPanelViewController.this.isFullyCollapsed()) {
                QuickSettingsControllerImpl quickSettingsControllerImpl = NotificationPanelViewController.this.mQsController;
                int findPointerIndex = motionEvent.findPointerIndex(quickSettingsControllerImpl.mTrackingPointer);
                if (findPointerIndex < 0) {
                    quickSettingsControllerImpl.mTrackingPointer = motionEvent.getPointerId(0);
                    findPointerIndex = 0;
                }
                float x = motionEvent.getX(findPointerIndex);
                float y = motionEvent.getY(findPointerIndex);
                int actionMasked = motionEvent.getActionMasked();
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = quickSettingsControllerImpl.mNotificationStackScrollLayoutController;
                Lazy lazy = quickSettingsControllerImpl.mPanelViewControllerLazy;
                ShadeLogger shadeLogger = quickSettingsControllerImpl.mShadeLog;
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked == 2) {
                            float f = y - quickSettingsControllerImpl.mInitialTouchY;
                            quickSettingsControllerImpl.trackMovement(motionEvent);
                            if (quickSettingsControllerImpl.isTracking()) {
                                quickSettingsControllerImpl.setExpansionHeight(f + quickSettingsControllerImpl.mInitialHeightOnTouch);
                                quickSettingsControllerImpl.trackMovement(motionEvent);
                            } else {
                                float f2 = motionEvent.getClassification() == 1 ? quickSettingsControllerImpl.mTouchSlop * quickSettingsControllerImpl.mSlopMultiplier : quickSettingsControllerImpl.mTouchSlop;
                                if ((f > f2 || (f < (-f2) && quickSettingsControllerImpl.getExpanded())) && Math.abs(f) > Math.abs(x - quickSettingsControllerImpl.mInitialTouchX) && quickSettingsControllerImpl.shouldQuickSettingsIntercept(quickSettingsControllerImpl.mInitialTouchX, quickSettingsControllerImpl.mInitialTouchY, f)) {
                                    shadeLogger.getClass();
                                    LogLevel logLevel = LogLevel.VERBOSE;
                                    ShadeLogger$onQsInterceptMoveQsTrackingEnabled$2 shadeLogger$onQsInterceptMoveQsTrackingEnabled$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$onQsInterceptMoveQsTrackingEnabled$2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return "onQsIntercept: move action, QS tracking enabled. h = " + ((LogMessage) obj).getDouble1();
                                        }
                                    };
                                    LogBuffer logBuffer = shadeLogger.buffer;
                                    LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$onQsInterceptMoveQsTrackingEnabled$2, null);
                                    ((LogMessageImpl) obtain).double1 = f;
                                    logBuffer.commit(obtain);
                                    quickSettingsControllerImpl.setTracking(true);
                                    quickSettingsControllerImpl.traceQsJank(true, false);
                                    quickSettingsControllerImpl.onExpansionStarted();
                                    ((NotificationPanelViewController) lazy.get()).notifyExpandingFinished();
                                    quickSettingsControllerImpl.mInitialHeightOnTouch = quickSettingsControllerImpl.mExpansionHeight;
                                    quickSettingsControllerImpl.mInitialTouchY = y;
                                    quickSettingsControllerImpl.mInitialTouchX = x;
                                    notificationStackScrollLayoutController.mView.cancelLongPress();
                                } else {
                                    float f3 = quickSettingsControllerImpl.mInitialTouchY;
                                    boolean expanded = quickSettingsControllerImpl.getExpanded();
                                    boolean isKeyguardShowing$1 = ((NotificationPanelViewController) lazy.get()).isKeyguardShowing$1();
                                    boolean isExpansionEnabled = quickSettingsControllerImpl.isExpansionEnabled();
                                    long downTime = motionEvent.getDownTime();
                                    shadeLogger.getClass();
                                    LogLevel logLevel2 = LogLevel.VERBOSE;
                                    ShadeLogger$logQsTrackingNotStarted$2 shadeLogger$logQsTrackingNotStarted$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logQsTrackingNotStarted$2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            LogMessage logMessage = (LogMessage) obj;
                                            String str1 = logMessage.getStr1();
                                            int int1 = logMessage.getInt1();
                                            int int2 = logMessage.getInt2();
                                            long long1 = logMessage.getLong1();
                                            double double1 = logMessage.getDouble1();
                                            boolean bool1 = logMessage.getBool1();
                                            boolean bool2 = logMessage.getBool2();
                                            boolean bool3 = logMessage.getBool3();
                                            StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("QsTrackingNotStarted: downTime=", str1, ",initTouchY=", int1, ",y=");
                                            m.append(int2);
                                            m.append(",h=");
                                            m.append(long1);
                                            m.append(",slop=");
                                            m.append(double1);
                                            m.append(",qsExpanded=");
                                            BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool1, ",keyguardShowing=", bool2, ",qsExpansion=");
                                            m.append(bool3);
                                            return m.toString();
                                        }
                                    };
                                    LogBuffer logBuffer2 = shadeLogger.buffer;
                                    LogMessage obtain2 = logBuffer2.obtain("systemui.shade", logLevel2, shadeLogger$logQsTrackingNotStarted$2, null);
                                    int i = (int) f3;
                                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
                                    logMessageImpl.int1 = i;
                                    logMessageImpl.int2 = (int) y;
                                    logMessageImpl.long1 = (long) f;
                                    logMessageImpl.double1 = f2;
                                    logMessageImpl.bool1 = expanded;
                                    logMessageImpl.bool2 = isKeyguardShowing$1;
                                    logMessageImpl.bool3 = isExpansionEnabled;
                                    logMessageImpl.str1 = String.valueOf(downTime);
                                    logBuffer2.commit(obtain2);
                                }
                            }
                            NotificationPanelViewController.this.getClass();
                            NotificationPanelViewController.this.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: QsIntercept");
                            return true;
                        }
                        if (actionMasked != 3) {
                            if (actionMasked == 6 && quickSettingsControllerImpl.mTrackingPointer == (pointerId2 = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                                int i2 = motionEvent.getPointerId(0) != pointerId2 ? 0 : 1;
                                quickSettingsControllerImpl.mTrackingPointer = motionEvent.getPointerId(i2);
                                quickSettingsControllerImpl.mInitialTouchX = motionEvent.getX(i2);
                                quickSettingsControllerImpl.mInitialTouchY = motionEvent.getY(i2);
                            }
                        }
                    }
                    quickSettingsControllerImpl.trackMovement(motionEvent);
                    shadeLogger.logMotionEvent(motionEvent, "onQsIntercept: up action, QS tracking disabled");
                    quickSettingsControllerImpl.setTracking(false);
                } else {
                    quickSettingsControllerImpl.mInitialTouchY = y;
                    quickSettingsControllerImpl.mInitialTouchX = x;
                    VelocityTracker velocityTracker = quickSettingsControllerImpl.mQsVelocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.recycle();
                    }
                    quickSettingsControllerImpl.mQsVelocityTracker = VelocityTracker.obtain();
                    quickSettingsControllerImpl.trackMovement(motionEvent);
                    float computeExpansionFraction = quickSettingsControllerImpl.computeExpansionFraction();
                    if (!quickSettingsControllerImpl.mSplitShadeEnabled) {
                        double d = computeExpansionFraction;
                        if (d > 0.0d && d < 1.0d) {
                            shadeLogger.logMotionEvent(motionEvent, "onQsIntercept: down action, QS partially expanded/collapsed");
                            NotificationPanelViewController.this.getClass();
                            NotificationPanelViewController.this.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: QsIntercept");
                            return true;
                        }
                    }
                    if (((NotificationPanelViewController) lazy.get()).isKeyguardShowing$1()) {
                        quickSettingsControllerImpl.shouldQuickSettingsIntercept(quickSettingsControllerImpl.mInitialTouchX, quickSettingsControllerImpl.mInitialTouchY, 0.0f);
                    }
                    if (quickSettingsControllerImpl.mExpansionAnimator != null) {
                        quickSettingsControllerImpl.mInitialHeightOnTouch = quickSettingsControllerImpl.mExpansionHeight;
                        shadeLogger.logMotionEvent(motionEvent, "onQsIntercept: down action, QS tracking enabled");
                        quickSettingsControllerImpl.setTracking(true);
                        quickSettingsControllerImpl.traceQsJank(true, false);
                        notificationStackScrollLayoutController.mView.cancelLongPress();
                    }
                }
            }
            NotificationPanelViewController notificationPanelViewController5 = NotificationPanelViewController.this;
            boolean z = notificationPanelViewController5.mInstantExpanding;
            boolean z2 = notificationPanelViewController5.mNotificationsDragEnabled;
            if (z || !z2 || notificationPanelViewController5.mTouchDisabled) {
                boolean z3 = true ^ z2;
                boolean z4 = notificationPanelViewController5.mTouchDisabled;
                ShadeLogger shadeLogger2 = notificationPanelViewController5.mShadeLog;
                shadeLogger2.getClass();
                LogLevel logLevel3 = LogLevel.VERBOSE;
                ShadeLogger$logNotInterceptingTouchInstantExpanding$2 shadeLogger$logNotInterceptingTouchInstantExpanding$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logNotInterceptingTouchInstantExpanding$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        boolean bool1 = logMessage.getBool1();
                        boolean bool2 = logMessage.getBool2();
                        boolean bool3 = logMessage.getBool3();
                        StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("NPVC not intercepting touch, instantExpanding: ", ", !notificationsDragEnabled: ", ", touchDisabled: ", bool1, bool2);
                        m.append(bool3);
                        return m.toString();
                    }
                };
                LogBuffer logBuffer3 = shadeLogger2.buffer;
                LogMessage obtain3 = logBuffer3.obtain("systemui.shade", logLevel3, shadeLogger$logNotInterceptingTouchInstantExpanding$2, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
                logMessageImpl2.bool1 = z;
                logMessageImpl2.bool2 = z3;
                logMessageImpl2.bool3 = z4;
                logBuffer3.commit(obtain3);
                return false;
            }
            if (notificationPanelViewController5.mMotionAborted && motionEvent.getActionMasked() != 0) {
                NotificationPanelViewController notificationPanelViewController6 = NotificationPanelViewController.this;
                notificationPanelViewController6.mShadeLog.logMotionEventStatusBarState(motionEvent, ((StatusBarStateControllerImpl) notificationPanelViewController6.mStatusBarStateController).mState, "NPVC MotionEvent not intercepted: non-down action, motion was aborted");
                return false;
            }
            int findPointerIndex2 = motionEvent.findPointerIndex(NotificationPanelViewController.this.mTrackingPointer);
            if (findPointerIndex2 < 0) {
                NotificationPanelViewController.this.mTrackingPointer = motionEvent.getPointerId(0);
                findPointerIndex2 = 0;
            }
            float x2 = motionEvent.getX(findPointerIndex2);
            float y2 = motionEvent.getY(findPointerIndex2);
            boolean canCollapsePanelOnTouch = NotificationPanelViewController.this.canCollapsePanelOnTouch();
            boolean z5 = Utilities.isTrackpadScroll(motionEvent) || Utilities.isTrackpadThreeFingerSwipe(motionEvent);
            int actionMasked2 = motionEvent.getActionMasked();
            if (actionMasked2 != 0) {
                if (actionMasked2 != 1) {
                    if (actionMasked2 == 2) {
                        NotificationPanelViewController notificationPanelViewController7 = NotificationPanelViewController.this;
                        float f4 = (y2 - notificationPanelViewController7.mInitialExpandY) * (notificationPanelViewController7.mIsTrackpadReverseScroll ? -1 : 1);
                        NotificationPanelViewController.m857$$Nest$maddMovement(notificationPanelViewController7, motionEvent);
                        NotificationPanelViewController notificationPanelViewController8 = NotificationPanelViewController.this;
                        boolean z6 = notificationPanelViewController8.mPanelClosedOnDown && !notificationPanelViewController8.mCollapsedAndHeadsUpOnDown;
                        if (canCollapsePanelOnTouch || notificationPanelViewController8.mTouchStartedInEmptyArea || notificationPanelViewController8.mAnimatingOnDown || z6) {
                            float abs = Math.abs(f4);
                            NotificationPanelViewController notificationPanelViewController9 = NotificationPanelViewController.this;
                            notificationPanelViewController9.getClass();
                            float f5 = motionEvent.getClassification() == 1 ? notificationPanelViewController9.mTouchSlop * notificationPanelViewController9.mSlopMultiplier : notificationPanelViewController9.mTouchSlop;
                            if ((f4 < (-f5) || ((z6 || NotificationPanelViewController.this.mAnimatingOnDown) && abs > f5)) && abs > Math.abs(x2 - NotificationPanelViewController.this.mInitialExpandX)) {
                                NotificationPanelViewController.this.cancelHeightAnimator();
                                NotificationPanelViewController notificationPanelViewController10 = NotificationPanelViewController.this;
                                NotificationPanelViewController.m860$$Nest$mstartExpandMotion(notificationPanelViewController10, x2, y2, true, notificationPanelViewController10.mExpandedHeight);
                                NotificationPanelViewController.this.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: startExpandMotion");
                                return true;
                            }
                        }
                    } else if (actionMasked2 != 3) {
                        if (actionMasked2 == 5) {
                            NotificationPanelViewController notificationPanelViewController11 = NotificationPanelViewController.this;
                            notificationPanelViewController11.mShadeLog.logMotionEventStatusBarState(motionEvent, ((StatusBarStateControllerImpl) notificationPanelViewController11.mStatusBarStateController).mState, "onInterceptTouchEvent: pointer down action");
                            if (!z5) {
                                NotificationPanelViewController notificationPanelViewController12 = NotificationPanelViewController.this;
                                if (((StatusBarStateControllerImpl) notificationPanelViewController12.mStatusBarStateController).mState == 1) {
                                    notificationPanelViewController12.mMotionAborted = true;
                                    notificationPanelViewController12.mVelocityTracker.clear();
                                }
                            }
                        } else if (actionMasked2 == 6 && !z5 && NotificationPanelViewController.this.mTrackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                            int i3 = motionEvent.getPointerId(0) != pointerId ? 0 : 1;
                            NotificationPanelViewController.this.mTrackingPointer = motionEvent.getPointerId(i3);
                            NotificationPanelViewController.this.mInitialExpandX = motionEvent.getX(i3);
                            NotificationPanelViewController.this.mInitialExpandY = motionEvent.getY(i3);
                        }
                    }
                    return false;
                }
                NotificationPanelViewController.this.mVelocityTracker.clear();
                return false;
            }
            NotificationPanelViewController notificationPanelViewController13 = NotificationPanelViewController.this;
            notificationPanelViewController13.mAnimatingOnDown = (notificationPanelViewController13.mHeightAnimator == null || notificationPanelViewController13.mIsSpringBackAnimation) ? false : true;
            ((SystemClockImpl) notificationPanelViewController13.mSystemClock).getClass();
            notificationPanelViewController13.mDownTime = android.os.SystemClock.uptimeMillis();
            NotificationPanelViewController notificationPanelViewController14 = NotificationPanelViewController.this;
            if (notificationPanelViewController14.mAnimatingOnDown && notificationPanelViewController14.isClosing()) {
                NotificationPanelViewController.this.cancelHeightAnimator();
                NotificationPanelViewController notificationPanelViewController15 = NotificationPanelViewController.this;
                notificationPanelViewController15.mTouchSlopExceeded = true;
                notificationPanelViewController15.mShadeLog.v("NotificationPanelViewController MotionEvent intercepted: mAnimatingOnDown: true, isClosing(): true");
                return true;
            }
            NotificationPanelViewController notificationPanelViewController16 = NotificationPanelViewController.this;
            NaturalScrollingSettingObserver naturalScrollingSettingObserver = notificationPanelViewController16.mNaturalScrollingSettingObserver;
            if (!naturalScrollingSettingObserver.isInitialized) {
                naturalScrollingSettingObserver.isInitialized = true;
                naturalScrollingSettingObserver.update();
            }
            notificationPanelViewController16.mIsTrackpadReverseScroll = !naturalScrollingSettingObserver.isNaturalScrollingEnabled && Utilities.isTrackpadScroll(motionEvent);
            if (!NotificationPanelViewController.this.isTracking() || NotificationPanelViewController.this.isFullyCollapsed()) {
                NotificationPanelViewController notificationPanelViewController17 = NotificationPanelViewController.this;
                notificationPanelViewController17.mInitialExpandY = y2;
                notificationPanelViewController17.mInitialExpandX = x2;
            } else {
                NotificationPanelViewController.this.mShadeLog.d("not setting mInitialExpandY in onInterceptTouch");
            }
            NotificationPanelViewController notificationPanelViewController18 = NotificationPanelViewController.this;
            NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = notificationPanelViewController18.mNotificationStackScrollLayoutController;
            float x3 = notificationStackScrollLayoutController2.mView.getX();
            notificationPanelViewController18.mTouchStartedInEmptyArea = !(!notificationStackScrollLayoutController2.mView.isBelowLastNotification(x2 - x3, y2) && x3 < x2 && x2 < ((float) notificationStackScrollLayoutController2.mView.getWidth()) + x3);
            NotificationPanelViewController notificationPanelViewController19 = NotificationPanelViewController.this;
            notificationPanelViewController19.mTouchSlopExceeded = notificationPanelViewController19.mTouchSlopExceededBeforeDown;
            notificationPanelViewController19.mMotionAborted = false;
            notificationPanelViewController19.mPanelClosedOnDown = notificationPanelViewController19.isFullyCollapsed();
            NotificationPanelViewController notificationPanelViewController20 = NotificationPanelViewController.this;
            ShadeLogger shadeLogger3 = notificationPanelViewController20.mShadeLog;
            boolean z7 = notificationPanelViewController20.mPanelClosedOnDown;
            float f6 = notificationPanelViewController20.mExpandedFraction;
            shadeLogger3.getClass();
            LogLevel logLevel4 = LogLevel.VERBOSE;
            ShadeLogger$logPanelClosedOnDown$2 shadeLogger$logPanelClosedOnDown$2 = ShadeLogger$logPanelClosedOnDown$2.INSTANCE;
            LogBuffer logBuffer4 = shadeLogger3.buffer;
            LogMessage obtain4 = logBuffer4.obtain("systemui.shade", logLevel4, shadeLogger$logPanelClosedOnDown$2, null);
            LogMessageImpl logMessageImpl3 = (LogMessageImpl) obtain4;
            logMessageImpl3.str1 = "intercept down touch";
            logMessageImpl3.bool1 = z7;
            logMessageImpl3.double1 = f6;
            logBuffer4.commit(obtain4);
            NotificationPanelViewController notificationPanelViewController21 = NotificationPanelViewController.this;
            notificationPanelViewController21.mCollapsedAndHeadsUpOnDown = false;
            notificationPanelViewController21.mHasLayoutedSinceDown = false;
            notificationPanelViewController21.mUpdateFlingOnLayout = false;
            notificationPanelViewController21.mTouchAboveFalsingThreshold = false;
            NotificationPanelViewController.m857$$Nest$maddMovement(notificationPanelViewController21, motionEvent);
            return false;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return onTouchEvent(motionEvent);
        }

        /* JADX WARN: Code restructure failed: missing block: B:100:0x01c0, code lost:
        
            if (r6.mSplitShadeEnabled != false) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x01c4, code lost:
        
            if (r6.mLastShadeFlingWasExpanding != false) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:104:0x01d2, code lost:
        
            if (r6.computeExpansionFraction() > 0.01d) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:106:0x01db, code lost:
        
            if (r6.mShadeExpandedFraction >= 1.0d) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x01dd, code lost:
        
            r6.setTracking(false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:109:0x01e4, code lost:
        
            if (r6.isExpandImmediate() != false) goto L220;
         */
        /* JADX WARN: Code restructure failed: missing block: B:111:0x01ea, code lost:
        
            if (r6.isTracking() == false) goto L220;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x01ec, code lost:
        
            r9 = r21.findPointerIndex(r6.mTrackingPointer);
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x01f2, code lost:
        
            if (r9 >= 0) goto L122;
         */
        /* JADX WARN: Code restructure failed: missing block: B:114:0x01f4, code lost:
        
            r6.mTrackingPointer = r21.getPointerId(0);
            r9 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:115:0x01fb, code lost:
        
            r10 = r21.getY(r9);
            r9 = r21.getX(r9);
            r12 = r10 - r6.mInitialTouchY;
            r4 = r21.getActionMasked();
         */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x020b, code lost:
        
            if (r4 == 0) goto L211;
         */
        /* JADX WARN: Code restructure failed: missing block: B:117:0x020d, code lost:
        
            if (r4 == 1) goto L148;
         */
        /* JADX WARN: Code restructure failed: missing block: B:118:0x020f, code lost:
        
            if (r4 == 2) goto L136;
         */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0211, code lost:
        
            if (r4 == 3) goto L148;
         */
        /* JADX WARN: Code restructure failed: missing block: B:120:0x0213, code lost:
        
            if (r4 == 6) goto L129;
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x0217, code lost:
        
            r4 = r21.getPointerId(r21.getActionIndex());
         */
        /* JADX WARN: Code restructure failed: missing block: B:122:0x0221, code lost:
        
            if (r6.mTrackingPointer != r4) goto L215;
         */
        /* JADX WARN: Code restructure failed: missing block: B:124:0x0228, code lost:
        
            if (r21.getPointerId(0) == r4) goto L134;
         */
        /* JADX WARN: Code restructure failed: missing block: B:125:0x022a, code lost:
        
            r4 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:126:0x022d, code lost:
        
            r7 = r21.getY(r4);
            r9 = r21.getX(r4);
            r6.mTrackingPointer = r21.getPointerId(r4);
            r6.mInitialHeightOnTouch = r6.mExpansionHeight;
            r6.mInitialTouchY = r7;
            r6.mInitialTouchX = r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:127:0x022c, code lost:
        
            r4 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:129:0x039d, code lost:
        
            if (r6.mConflictingExpansionGesture != false) goto L220;
         */
        /* JADX WARN: Code restructure failed: missing block: B:131:0x03a1, code lost:
        
            if (r6.mSplitShadeEnabled != false) goto L220;
         */
        /* JADX WARN: Code restructure failed: missing block: B:132:0x03a3, code lost:
        
            r4 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:133:0x0245, code lost:
        
            r6.setExpansionHeight(r6.mInitialHeightOnTouch + r12);
            r7 = (com.android.systemui.power.shared.model.WakefulnessModel) ((kotlinx.coroutines.flow.StateFlowImpl) ((com.android.systemui.shade.NotificationPanelViewController) r7.get()).mPowerInteractor.detailedWakefulness.$$delegate_0).getValue();
         */
        /* JADX WARN: Code restructure failed: missing block: B:134:0x0263, code lost:
        
            if (r7.isAwake() == false) goto L143;
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:0x0265, code lost:
        
            r9 = com.android.systemui.power.shared.model.WakeSleepReason.TAP;
            r7 = r7.lastWakeReason;
         */
        /* JADX WARN: Code restructure failed: missing block: B:136:0x0269, code lost:
        
            if (r7 == r9) goto L142;
         */
        /* JADX WARN: Code restructure failed: missing block: B:138:0x026d, code lost:
        
            if (r7 != com.android.systemui.power.shared.model.WakeSleepReason.GESTURE) goto L143;
         */
        /* JADX WARN: Code restructure failed: missing block: B:139:0x026f, code lost:
        
            r7 = 1.5f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x027e, code lost:
        
            if (r12 < ((int) (r4.mQsController.mFalsingThreshold * r7))) goto L147;
         */
        /* JADX WARN: Code restructure failed: missing block: B:142:0x0280, code lost:
        
            r6.mTouchAboveFalsingThreshold = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x0282, code lost:
        
            r6.trackMovement(r21);
         */
        /* JADX WARN: Code restructure failed: missing block: B:144:0x0272, code lost:
        
            r7 = 1.0f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:145:0x0287, code lost:
        
            r11.logMotionEvent(r21, "onQsTouch: up/cancel action, QS tracking disabled");
            r6.setTracking(false);
            r6.mTrackingPointer = -1;
            r6.trackMovement(r21);
         */
        /* JADX WARN: Code restructure failed: missing block: B:146:0x029d, code lost:
        
            if (r6.computeExpansionFraction() != 0.0f) goto L159;
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x02a3, code lost:
        
            if (r10 < r6.mInitialTouchY) goto L153;
         */
        /* JADX WARN: Code restructure failed: missing block: B:150:0x02aa, code lost:
        
            if (r21.getActionMasked() != 3) goto L157;
         */
        /* JADX WARN: Code restructure failed: missing block: B:151:0x02ac, code lost:
        
            r7 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:153:0x02b1, code lost:
        
            r6.traceQsJank(false, r7);
            r9 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:154:0x036a, code lost:
        
            r4 = r6.mQsVelocityTracker;
         */
        /* JADX WARN: Code restructure failed: missing block: B:155:0x036c, code lost:
        
            if (r4 == null) goto L215;
         */
        /* JADX WARN: Code restructure failed: missing block: B:156:0x036e, code lost:
        
            r4.recycle();
            r6.mQsVelocityTracker = r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:157:0x02af, code lost:
        
            r7 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:159:0x02bb, code lost:
        
            if (r21.getActionMasked() != 3) goto L162;
         */
        /* JADX WARN: Code restructure failed: missing block: B:160:0x02bd, code lost:
        
            r9 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:161:0x02c0, code lost:
        
            r11 = r6.mQsVelocityTracker;
         */
        /* JADX WARN: Code restructure failed: missing block: B:162:0x02c4, code lost:
        
            if (r11 != null) goto L166;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x02c6, code lost:
        
            r11 = 0.0f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:164:0x02d1, code lost:
        
            r4 = (com.android.systemui.shade.NotificationPanelViewController) r7.get();
            r4.getClass();
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x02e6, code lost:
        
            if (java.lang.Math.abs(r11) >= r4.mFlingAnimationUtils.mMinVelocityPxPerSecond) goto L173;
         */
        /* JADX WARN: Code restructure failed: missing block: B:167:0x02f2, code lost:
        
            if (r4.mQsController.computeExpansionFraction() <= 0.5f) goto L172;
         */
        /* JADX WARN: Code restructure failed: missing block: B:168:0x02f4, code lost:
        
            r4 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:169:0x02fe, code lost:
        
            r13 = r6.mFalsingManager;
         */
        /* JADX WARN: Code restructure failed: missing block: B:170:0x0300, code lost:
        
            if (r4 == false) goto L196;
         */
        /* JADX WARN: Code restructure failed: missing block: B:172:0x0306, code lost:
        
            if (r13.isUnlockingDisabled() != false) goto L195;
         */
        /* JADX WARN: Code restructure failed: missing block: B:174:0x030c, code lost:
        
            if (r13.isClassifierEnabled() == false) goto L183;
         */
        /* JADX WARN: Code restructure failed: missing block: B:175:0x030e, code lost:
        
            r13 = r13.isFalseTouch(0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:176:0x0317, code lost:
        
            if (r13 == false) goto L186;
         */
        /* JADX WARN: Code restructure failed: missing block: B:177:0x031a, code lost:
        
            r13 = r6.mQsVelocityTracker;
         */
        /* JADX WARN: Code restructure failed: missing block: B:178:0x031c, code lost:
        
            if (r13 != null) goto L189;
         */
        /* JADX WARN: Code restructure failed: missing block: B:179:0x031e, code lost:
        
            r12 = 0.0f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:181:0x032b, code lost:
        
            if (r6.mBarState != 1) goto L193;
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x032d, code lost:
        
            r13 = 193;
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x0332, code lost:
        
            r7 = ((com.android.systemui.shade.NotificationPanelViewController) r7.get()).mCentralSurfaces.mDisplayMetrics.density;
            r6.mLockscreenGestureLogger.write(r13, (int) ((r10 - r6.mInitialTouchY) / r7), (int) (r12 / r7));
         */
        /* JADX WARN: Code restructure failed: missing block: B:184:0x0357, code lost:
        
            if (r4 == false) goto L203;
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x0359, code lost:
        
            if (r9 != false) goto L203;
         */
        /* JADX WARN: Code restructure failed: missing block: B:186:0x035b, code lost:
        
            r4 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:187:0x035c, code lost:
        
            r9 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:188:0x0367, code lost:
        
            r6.flingQs(r11, r4, null, false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:190:0x0361, code lost:
        
            if (r6.mSplitShadeEnabled == false) goto L206;
         */
        /* JADX WARN: Code restructure failed: missing block: B:191:0x0363, code lost:
        
            r4 = 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:192:0x0365, code lost:
        
            r4 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:193:0x0330, code lost:
        
            r13 = 194;
         */
        /* JADX WARN: Code restructure failed: missing block: B:194:0x0320, code lost:
        
            r13.computeCurrentVelocity(1000);
            r12 = r6.mQsVelocityTracker.getYVelocity();
         */
        /* JADX WARN: Code restructure failed: missing block: B:195:0x0314, code lost:
        
            r13 = !r6.mTouchAboveFalsingThreshold;
         */
        /* JADX WARN: Code restructure failed: missing block: B:196:0x034b, code lost:
        
            r4 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:198:0x0350, code lost:
        
            if (r11 >= 0.0f) goto L199;
         */
        /* JADX WARN: Code restructure failed: missing block: B:199:0x0352, code lost:
        
            r13.isFalseTouch(12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:200:0x02f6, code lost:
        
            r4 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:202:0x02fb, code lost:
        
            if (r11 <= 0.0f) goto L172;
         */
        /* JADX WARN: Code restructure failed: missing block: B:203:0x02c8, code lost:
        
            r11.computeCurrentVelocity(1000);
            r11 = r6.mQsVelocityTracker.getYVelocity();
         */
        /* JADX WARN: Code restructure failed: missing block: B:204:0x02bf, code lost:
        
            r9 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:205:0x0374, code lost:
        
            r11.logMotionEvent(r21, "onQsTouch: down action, QS tracking enabled");
            r6.setTracking(true);
            r6.traceQsJank(true, false);
            r6.mInitialTouchY = r10;
            r6.mInitialTouchX = r9;
            r6.onExpansionStarted();
            r6.mInitialHeightOnTouch = r6.mExpansionHeight;
            r4 = r6.mQsVelocityTracker;
         */
        /* JADX WARN: Code restructure failed: missing block: B:206:0x038d, code lost:
        
            if (r4 == null) goto L214;
         */
        /* JADX WARN: Code restructure failed: missing block: B:207:0x038f, code lost:
        
            r4.recycle();
         */
        /* JADX WARN: Code restructure failed: missing block: B:208:0x0392, code lost:
        
            r6.mQsVelocityTracker = android.view.VelocityTracker.obtain();
            r6.trackMovement(r21);
         */
        /* JADX WARN: Code restructure failed: missing block: B:209:0x03a5, code lost:
        
            if (r15 == 3) goto L222;
         */
        /* JADX WARN: Code restructure failed: missing block: B:210:0x03a7, code lost:
        
            if (r15 != 1) goto L223;
         */
        /* JADX WARN: Code restructure failed: missing block: B:211:0x03ac, code lost:
        
            if (r15 != 0) goto L228;
         */
        /* JADX WARN: Code restructure failed: missing block: B:212:0x03ae, code lost:
        
            if (r8 == false) goto L228;
         */
        /* JADX WARN: Code restructure failed: missing block: B:214:0x03b4, code lost:
        
            if (r6.isExpansionEnabled() == false) goto L228;
         */
        /* JADX WARN: Code restructure failed: missing block: B:215:0x03b6, code lost:
        
            r6.mTwoFingerExpandPossible = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:217:0x03ba, code lost:
        
            if (r6.mTwoFingerExpandPossible == false) goto L240;
         */
        /* JADX WARN: Code restructure failed: missing block: B:219:0x03c0, code lost:
        
            if (r6.isOpenQsEvent(r21) == false) goto L240;
         */
        /* JADX WARN: Code restructure failed: missing block: B:221:0x03cf, code lost:
        
            if (r21.getY(r21.getActionIndex()) >= r6.mStatusBarMinHeight) goto L240;
         */
        /* JADX WARN: Code restructure failed: missing block: B:222:0x03d1, code lost:
        
            r6.mMetricsLogger.count("panel_open_qs", 1);
            r6.setExpandImmediate(true);
            r4 = !r6.mSplitShadeEnabled;
            r7 = r6.mNotificationStackScrollLayoutController.mView;
            r7.mShouldShowShelfOnly = r4;
            r7.updateAlgorithmLayoutMinHeight();
            r4 = r6.mExpansionHeightSetToMaxListener;
         */
        /* JADX WARN: Code restructure failed: missing block: B:223:0x03e9, code lost:
        
            if (r4 == null) goto L237;
         */
        /* JADX WARN: Code restructure failed: missing block: B:224:0x03eb, code lost:
        
            r4.onExpansionHeightSetToMax(false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:225:0x03ef, code lost:
        
            r4 = r6.mQs;
         */
        /* JADX WARN: Code restructure failed: missing block: B:226:0x03f1, code lost:
        
            if (r4 == null) goto L240;
         */
        /* JADX WARN: Code restructure failed: missing block: B:227:0x03f3, code lost:
        
            r4.setListening(true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:228:0x03f6, code lost:
        
            r4 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:229:0x03a9, code lost:
        
            r6.mConflictingExpansionGesture = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:230:0x0157, code lost:
        
            r7 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:231:0x0142, code lost:
        
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:232:0x03f7, code lost:
        
            if (r4 == false) goto L246;
         */
        /* JADX WARN: Code restructure failed: missing block: B:234:0x03fe, code lost:
        
            if (r21.getActionMasked() == 2) goto L245;
         */
        /* JADX WARN: Code restructure failed: missing block: B:235:0x0400, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "onTouch: handleQsTouch handled event");
         */
        /* JADX WARN: Code restructure failed: missing block: B:236:0x0409, code lost:
        
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:237:0x0120, code lost:
        
            r9 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:239:0x040e, code lost:
        
            if (r21.getActionMasked() != 0) goto L251;
         */
        /* JADX WARN: Code restructure failed: missing block: B:241:0x0416, code lost:
        
            if (r20.this$0.isFullyCollapsed() == false) goto L251;
         */
        /* JADX WARN: Code restructure failed: missing block: B:242:0x0418, code lost:
        
            r20.this$0.mMetricsLogger.count("panel_open", 1);
            r2 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:244:0x0426, code lost:
        
            if (r21.getActionMasked() != 0) goto L258;
         */
        /* JADX WARN: Code restructure failed: missing block: B:246:0x042e, code lost:
        
            if (r20.this$0.isFullyExpanded() == false) goto L258;
         */
        /* JADX WARN: Code restructure failed: missing block: B:247:0x0430, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:248:0x0436, code lost:
        
            if (r4.mKeyguardStateController.mShowing == false) goto L258;
         */
        /* JADX WARN: Code restructure failed: missing block: B:249:0x0438, code lost:
        
            r6 = r21.getX();
            r4 = r4.mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.repository._keyguardPosition;
            r6 = java.lang.Float.valueOf(r6);
            r4.getClass();
            r4.updateState(null, r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:250:0x044f, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:251:0x0453, code lost:
        
            if (r4.mInstantExpanding == false) goto L262;
         */
        /* JADX WARN: Code restructure failed: missing block: B:252:0x0455, code lost:
        
            r4.mShadeLog.logMotionEvent(r21, "handleTouch: touch ignored due to instant expanding");
         */
        /* JADX WARN: Code restructure failed: missing block: B:253:0x045c, code lost:
        
            r7 = false;
            r18 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:254:0x0820, code lost:
        
            r1 = r2 | r18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:255:0x0826, code lost:
        
            if (r20.this$0.mDozing == false) goto L446;
         */
        /* JADX WARN: Code restructure failed: missing block: B:256:0x0828, code lost:
        
            if (r1 == false) goto L445;
         */
        /* JADX WARN: Code restructure failed: missing block: B:258:?, code lost:
        
            return r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:260:0x082e, code lost:
        
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:262:0x0463, code lost:
        
            if (r4.mTouchDisabled == false) goto L267;
         */
        /* JADX WARN: Code restructure failed: missing block: B:264:0x0469, code lost:
        
            if (r21.getActionMasked() == 3) goto L267;
         */
        /* JADX WARN: Code restructure failed: missing block: B:265:0x046b, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "handleTouch: non-cancel action, touch disabled");
         */
        /* JADX WARN: Code restructure failed: missing block: B:267:0x0479, code lost:
        
            if (r20.this$0.mMotionAborted == false) goto L272;
         */
        /* JADX WARN: Code restructure failed: missing block: B:269:0x047f, code lost:
        
            if (r21.getActionMasked() == 0) goto L272;
         */
        /* JADX WARN: Code restructure failed: missing block: B:270:0x0481, code lost:
        
            r4 = r20.this$0;
            r4.mShadeLog.logMotionEventStatusBarState(r21, ((com.android.systemui.statusbar.StatusBarStateControllerImpl) r4.mStatusBarStateController).mState, "handleTouch: non-down action, motion was aborted");
         */
        /* JADX WARN: Code restructure failed: missing block: B:271:0x0491, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:272:0x0495, code lost:
        
            if (r4.mNotificationsDragEnabled != false) goto L278;
         */
        /* JADX WARN: Code restructure failed: missing block: B:274:0x049b, code lost:
        
            if (r4.isTracking() == false) goto L277;
         */
        /* JADX WARN: Code restructure failed: missing block: B:275:0x049d, code lost:
        
            r20.this$0.onTrackingStopped(true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:276:0x04a2, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "handleTouch: drag not enabled");
         */
        /* JADX WARN: Code restructure failed: missing block: B:277:0x04ac, code lost:
        
            r4 = r21.findPointerIndex(r4.mTrackingPointer);
         */
        /* JADX WARN: Code restructure failed: missing block: B:278:0x04b2, code lost:
        
            if (r4 >= 0) goto L281;
         */
        /* JADX WARN: Code restructure failed: missing block: B:279:0x04b4, code lost:
        
            r20.this$0.mTrackingPointer = r21.getPointerId(0);
            r4 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:280:0x04be, code lost:
        
            r6 = r21.getX(r4);
            r4 = r21.getY(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:281:0x04ca, code lost:
        
            if (r21.getActionMasked() == 0) goto L285;
         */
        /* JADX WARN: Code restructure failed: missing block: B:283:0x04d1, code lost:
        
            if (r21.getActionMasked() != 2) goto L295;
         */
        /* JADX WARN: Code restructure failed: missing block: B:285:0x04f3, code lost:
        
            if (com.android.systemui.navigationbar.gestural.Utilities.isTrackpadScroll(r21) != false) goto L301;
         */
        /* JADX WARN: Code restructure failed: missing block: B:287:0x04f9, code lost:
        
            if (com.android.systemui.navigationbar.gestural.Utilities.isTrackpadThreeFingerSwipe(r21) == false) goto L300;
         */
        /* JADX WARN: Code restructure failed: missing block: B:288:0x04fc, code lost:
        
            r7 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:289:0x04ff, code lost:
        
            r8 = r21.getActionMasked();
         */
        /* JADX WARN: Code restructure failed: missing block: B:290:0x0505, code lost:
        
            if (r8 == 0) goto L404;
         */
        /* JADX WARN: Code restructure failed: missing block: B:291:0x0507, code lost:
        
            if (r8 == 1) goto L390;
         */
        /* JADX WARN: Code restructure failed: missing block: B:293:0x050a, code lost:
        
            if (r8 == 2) goto L327;
         */
        /* JADX WARN: Code restructure failed: missing block: B:294:0x050c, code lost:
        
            if (r8 == 3) goto L390;
         */
        /* JADX WARN: Code restructure failed: missing block: B:296:0x050f, code lost:
        
            if (r8 == 5) goto L322;
         */
        /* JADX WARN: Code restructure failed: missing block: B:298:0x0512, code lost:
        
            if (r8 == 6) goto L313;
         */
        /* JADX WARN: Code restructure failed: missing block: B:299:0x0514, code lost:
        
            r7 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:300:0x080e, code lost:
        
            r1 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:301:0x0812, code lost:
        
            if (r1.mGestureWaitForTouchSlop == false) goto L439;
         */
        /* JADX WARN: Code restructure failed: missing block: B:303:0x0818, code lost:
        
            if (r1.isTracking() == false) goto L438;
         */
        /* JADX WARN: Code restructure failed: missing block: B:304:0x081b, code lost:
        
            r1 = r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:305:0x081e, code lost:
        
            r18 = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:306:0x081d, code lost:
        
            r1 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:307:0x0517, code lost:
        
            if (r7 == false) goto L315;
         */
        /* JADX WARN: Code restructure failed: missing block: B:308:0x051a, code lost:
        
            r4 = r21.getPointerId(r21.getActionIndex());
         */
        /* JADX WARN: Code restructure failed: missing block: B:309:0x0526, code lost:
        
            if (r20.this$0.mTrackingPointer != r4) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:311:0x052d, code lost:
        
            if (r21.getPointerId(0) == r4) goto L320;
         */
        /* JADX WARN: Code restructure failed: missing block: B:312:0x052f, code lost:
        
            r4 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:313:0x0532, code lost:
        
            r5 = r21.getY(r4);
            r6 = r21.getX(r4);
            r20.this$0.mTrackingPointer = r21.getPointerId(r4);
            r1 = r20.this$0;
            r1.mHandlingPointerUp = true;
            com.android.systemui.shade.NotificationPanelViewController.m860$$Nest$mstartExpandMotion(r1, r6, r5, true, r1.mExpandedHeight);
            r20.this$0.mHandlingPointerUp = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:314:0x0531, code lost:
        
            r4 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:315:0x0551, code lost:
        
            r5 = r20.this$0;
            r5.mShadeLog.logMotionEventStatusBarState(r21, ((com.android.systemui.statusbar.StatusBarStateControllerImpl) r5.mStatusBarStateController).mState, "handleTouch: pointer down action");
         */
        /* JADX WARN: Code restructure failed: missing block: B:316:0x0560, code lost:
        
            if (r7 != false) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:317:0x0562, code lost:
        
            r5 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:318:0x056a, code lost:
        
            if (((com.android.systemui.statusbar.StatusBarStateControllerImpl) r5.mStatusBarStateController).mState != 1) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:319:0x056c, code lost:
        
            r5.mMotionAborted = true;
            com.android.systemui.shade.NotificationPanelViewController.m858$$Nest$mendMotionEvent(r5, r21, r6, r4, true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:321:0x0579, code lost:
        
            if (r20.this$0.isFullyCollapsed() == false) goto L330;
         */
        /* JADX WARN: Code restructure failed: missing block: B:322:0x057b, code lost:
        
            r5 = r20.this$0;
            r5.mHasVibratedOnOpen = false;
            r7 = r5.mExpandedFraction;
            r5 = r5.mShadeLog;
            r5.getClass();
            r8 = com.android.systemui.log.core.LogLevel.VERBOSE;
            r10 = com.android.systemui.shade.ShadeLogger$logHasVibrated$2.INSTANCE;
            r5 = r5.buffer;
            r8 = r5.obtain("systemui.shade", r8, r10, null);
            r9 = (com.android.systemui.log.LogMessageImpl) r8;
            r9.bool1 = false;
            r9.double1 = r7;
            r5.commit(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:323:0x059e, code lost:
        
            com.android.systemui.shade.NotificationPanelViewController.m857$$Nest$maddMovement(r20.this$0, r21);
         */
        /* JADX WARN: Code restructure failed: missing block: B:324:0x05a9, code lost:
        
            if (r20.this$0.isFullyCollapsed() != false) goto L333;
         */
        /* JADX WARN: Code restructure failed: missing block: B:325:0x05ab, code lost:
        
            r20.this$0.maybeVibrateOnOpening(true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:326:0x05b0, code lost:
        
            r5 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:327:0x05b4, code lost:
        
            if (r5.mIsTrackpadReverseScroll == false) goto L336;
         */
        /* JADX WARN: Code restructure failed: missing block: B:328:0x05b6, code lost:
        
            r12 = -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:329:0x05b9, code lost:
        
            r5 = (r4 - r5.mInitialExpandY) * r12;
            r7 = java.lang.Math.abs(r5);
            r8 = r20.this$0;
            r8.getClass();
         */
        /* JADX WARN: Code restructure failed: missing block: B:330:0x05cc, code lost:
        
            if (r21.getClassification() != 1) goto L340;
         */
        /* JADX WARN: Code restructure failed: missing block: B:331:0x05ce, code lost:
        
            r1 = r8.mTouchSlop * r8.mSlopMultiplier;
         */
        /* JADX WARN: Code restructure failed: missing block: B:333:0x05da, code lost:
        
            if (r7 <= r1) goto L357;
         */
        /* JADX WARN: Code restructure failed: missing block: B:335:0x05ec, code lost:
        
            if (java.lang.Math.abs(r5) > java.lang.Math.abs(r6 - r20.this$0.mInitialExpandX)) goto L347;
         */
        /* JADX WARN: Code restructure failed: missing block: B:337:0x05f2, code lost:
        
            if (r20.this$0.mIgnoreXTouchSlop == false) goto L357;
         */
        /* JADX WARN: Code restructure failed: missing block: B:338:0x05f4, code lost:
        
            r1 = r20.this$0;
            r1.mTouchSlopExceeded = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:339:0x05fa, code lost:
        
            if (r1.mGestureWaitForTouchSlop == false) goto L357;
         */
        /* JADX WARN: Code restructure failed: missing block: B:341:0x0600, code lost:
        
            if (r1.isTracking() != false) goto L357;
         */
        /* JADX WARN: Code restructure failed: missing block: B:342:0x0602, code lost:
        
            r1 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:343:0x0606, code lost:
        
            if (r1.mCollapsedAndHeadsUpOnDown != false) goto L357;
         */
        /* JADX WARN: Code restructure failed: missing block: B:345:0x060d, code lost:
        
            if (r1.mInitialOffsetOnTouch == 0.0f) goto L356;
         */
        /* JADX WARN: Code restructure failed: missing block: B:346:0x060f, code lost:
        
            com.android.systemui.shade.NotificationPanelViewController.m860$$Nest$mstartExpandMotion(r1, r6, r4, false, r1.mExpandedHeight);
            r5 = 0.0f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:347:0x0616, code lost:
        
            r20.this$0.cancelHeightAnimator();
            r20.this$0.onTrackingStarted$1();
         */
        /* JADX WARN: Code restructure failed: missing block: B:348:0x0620, code lost:
        
            r1 = java.lang.Math.max(0.0f, r20.this$0.mInitialOffsetOnTouch + r5);
            r20.this$0.getClass();
            r1 = java.lang.Math.max(r1, 0.0f);
            r7 = -r5;
            r9 = (com.android.systemui.power.shared.model.WakefulnessModel) ((kotlinx.coroutines.flow.StateFlowImpl) r20.this$0.mPowerInteractor.detailedWakefulness.$$delegate_0).getValue();
         */
        /* JADX WARN: Code restructure failed: missing block: B:349:0x0648, code lost:
        
            if (r9.isAwake() == false) goto L364;
         */
        /* JADX WARN: Code restructure failed: missing block: B:350:0x064a, code lost:
        
            r10 = com.android.systemui.power.shared.model.WakeSleepReason.TAP;
            r9 = r9.lastWakeReason;
         */
        /* JADX WARN: Code restructure failed: missing block: B:351:0x064e, code lost:
        
            if (r9 == r10) goto L363;
         */
        /* JADX WARN: Code restructure failed: missing block: B:353:0x0652, code lost:
        
            if (r9 != com.android.systemui.power.shared.model.WakeSleepReason.GESTURE) goto L364;
         */
        /* JADX WARN: Code restructure failed: missing block: B:354:0x0654, code lost:
        
            r9 = 1.5f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:356:0x0663, code lost:
        
            if (r7 < ((int) (r8.mQsController.mFalsingThreshold * r9))) goto L368;
         */
        /* JADX WARN: Code restructure failed: missing block: B:357:0x0665, code lost:
        
            r7 = r20.this$0;
            r7.mTouchAboveFalsingThreshold = true;
            r7.mUpwardsWhenThresholdReached = r7.isDirectionUpwards(r6, r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:358:0x066f, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:359:0x0673, code lost:
        
            if (r4.mGestureWaitForTouchSlop == false) goto L372;
         */
        /* JADX WARN: Code restructure failed: missing block: B:361:0x0679, code lost:
        
            if (r4.isTracking() == false) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:362:0x067b, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:363:0x067f, code lost:
        
            if (r4.mBlockingExpansionForCurrentTouch != false) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:364:0x0681, code lost:
        
            r4 = r4.mQsController;
         */
        /* JADX WARN: Code restructure failed: missing block: B:365:0x0685, code lost:
        
            if (r4.mConflictingExpansionGesture == false) goto L379;
         */
        /* JADX WARN: Code restructure failed: missing block: B:367:0x068b, code lost:
        
            if (r4.getExpanded() == false) goto L379;
         */
        /* JADX WARN: Code restructure failed: missing block: B:368:0x068d, code lost:
        
            r4 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:369:0x0690, code lost:
        
            if (r4 != false) goto L312;
         */
        /* JADX WARN: Code restructure failed: missing block: B:370:0x0692, code lost:
        
            r4 = r20.this$0;
            r6 = r4.mAmbientState;
         */
        /* JADX WARN: Code restructure failed: missing block: B:371:0x0699, code lost:
        
            if (r5 > 0.0f) goto L384;
         */
        /* JADX WARN: Code restructure failed: missing block: B:372:0x069b, code lost:
        
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:373:0x069e, code lost:
        
            if (r5 != false) goto L389;
         */
        /* JADX WARN: Code restructure failed: missing block: B:375:0x06a2, code lost:
        
            if (r6.mIsSwipingUp == false) goto L389;
         */
        /* JADX WARN: Code restructure failed: missing block: B:376:0x06a4, code lost:
        
            r6.mIsFlingRequiredAfterLockScreenSwipeUp = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:377:0x06a6, code lost:
        
            r6.mIsSwipingUp = r5;
            r4.setExpandedHeightInternal(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:378:0x069d, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:379:0x068f, code lost:
        
            r4 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:380:0x0657, code lost:
        
            r9 = 1.0f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:381:0x05d5, code lost:
        
            r1 = r8.mTouchSlop;
         */
        /* JADX WARN: Code restructure failed: missing block: B:382:0x05b8, code lost:
        
            r12 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:383:0x06ad, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "onTouch: up/cancel action");
            com.android.systemui.shade.NotificationPanelViewController.m857$$Nest$maddMovement(r20.this$0, r21);
            com.android.systemui.shade.NotificationPanelViewController.m858$$Nest$mendMotionEvent(r20.this$0, r21, r6, r4, false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:384:0x06c5, code lost:
        
            if (r20.this$0.mHeightAnimator != null) goto L398;
         */
        /* JADX WARN: Code restructure failed: missing block: B:386:0x06cb, code lost:
        
            if (r21.getActionMasked() != 1) goto L399;
         */
        /* JADX WARN: Code restructure failed: missing block: B:387:0x06cd, code lost:
        
            r1 = (com.android.internal.jank.InteractionJankMonitor) r20.this$0.mQsController.mInteractionJankMonitorLazy.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:388:0x06d9, code lost:
        
            if (r1 != null) goto L397;
         */
        /* JADX WARN: Code restructure failed: missing block: B:389:0x06dc, code lost:
        
            r1.end(0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:390:0x06e2, code lost:
        
            r1 = (com.android.internal.jank.InteractionJankMonitor) r20.this$0.mQsController.mInteractionJankMonitorLazy.get();
         */
        /* JADX WARN: Code restructure failed: missing block: B:391:0x06ee, code lost:
        
            if (r1 != null) goto L402;
         */
        /* JADX WARN: Code restructure failed: missing block: B:392:0x06f1, code lost:
        
            r4 = false;
            r1.cancel(0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:393:0x06f5, code lost:
        
            r20.this$0.mIsTrackpadReverseScroll = r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:394:0x06e0, code lost:
        
            r4 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:395:0x06fb, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "onTouch: down action");
            r5 = r20.this$0;
            com.android.systemui.shade.NotificationPanelViewController.m860$$Nest$mstartExpandMotion(r5, r6, r4, false, r5.mExpandedHeight);
            r4 = r20.this$0;
            r4.getClass();
            r4.mPanelClosedOnDown = r4.isFullyCollapsed();
            r4 = r20.this$0;
            r5 = r4.mShadeLog;
            r6 = r4.mPanelClosedOnDown;
            r4 = r4.mExpandedFraction;
            r5.getClass();
            r7 = com.android.systemui.log.core.LogLevel.VERBOSE;
            r8 = com.android.systemui.shade.ShadeLogger$logPanelClosedOnDown$2.INSTANCE;
            r5 = r5.buffer;
            r7 = r5.obtain("systemui.shade", r7, r8, null);
            r8 = (com.android.systemui.log.LogMessageImpl) r7;
            r8.str1 = "handle down touch";
            r8.bool1 = r6;
            r8.double1 = r4;
            r5.commit(r7);
            r4 = r20.this$0;
            r4.mHasLayoutedSinceDown = false;
            r4.mUpdateFlingOnLayout = false;
            r4.mMotionAborted = false;
            ((com.android.systemui.util.time.SystemClockImpl) r4.mSystemClock).getClass();
            r4.mDownTime = android.os.SystemClock.uptimeMillis();
            r4 = r20.this$0;
            r7 = false;
            r4.mTouchAboveFalsingThreshold = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:396:0x075b, code lost:
        
            if (r4.isFullyCollapsed() == false) goto L409;
         */
        /* JADX WARN: Code restructure failed: missing block: B:398:0x0765, code lost:
        
            if (((com.android.systemui.statusbar.policy.BaseHeadsUpManager) r20.this$0.mHeadsUpManager).mHasPinnedNotification == false) goto L409;
         */
        /* JADX WARN: Code restructure failed: missing block: B:399:0x0767, code lost:
        
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:400:0x076a, code lost:
        
            r4.mCollapsedAndHeadsUpOnDown = r5;
            com.android.systemui.shade.NotificationPanelViewController.m857$$Nest$maddMovement(r20.this$0, r21);
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:401:0x0775, code lost:
        
            if (r4.mHeightAnimator == null) goto L415;
         */
        /* JADX WARN: Code restructure failed: missing block: B:403:0x0779, code lost:
        
            if (r4.mIsSpringBackAnimation != false) goto L415;
         */
        /* JADX WARN: Code restructure failed: missing block: B:404:0x077b, code lost:
        
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:406:0x0780, code lost:
        
            if (r4.mGestureWaitForTouchSlop == false) goto L419;
         */
        /* JADX WARN: Code restructure failed: missing block: B:407:0x0782, code lost:
        
            if (r5 == false) goto L426;
         */
        /* JADX WARN: Code restructure failed: missing block: B:409:0x079e, code lost:
        
            if (r20.this$0.isFullyCollapsed() == false) goto L433;
         */
        /* JADX WARN: Code restructure failed: missing block: B:410:0x07a0, code lost:
        
            r4 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:411:0x07a8, code lost:
        
            if (((com.android.systemui.statusbar.policy.BaseHeadsUpManager) r4.mHeadsUpManager).mHasPinnedNotification != false) goto L433;
         */
        /* JADX WARN: Code restructure failed: missing block: B:413:0x07ae, code lost:
        
            if (r4.mCentralSurfaces.mBouncerShowing != false) goto L433;
         */
        /* JADX WARN: Code restructure failed: missing block: B:414:0x07b0, code lost:
        
            r4.updateExpansionAndVisibility();
            r5 = r4.mCentralSurfaces;
            r6 = r5.mDisplayMetrics;
            r4.mLockscreenGestureLogger.mMetricsLogger.write(new android.metrics.LogMaker(1328).setType(4).addTaggedData(1326, java.lang.Integer.valueOf((int) ((r21.getX() / r6.widthPixels) * 100.0f))).addTaggedData(1327, java.lang.Integer.valueOf((int) ((r21.getY() / r6.heightPixels) * 100.0f))).addTaggedData(1329, java.lang.Integer.valueOf(r5.mDisplay.getRotation())));
            new com.android.internal.logging.UiEventLoggerImpl().log(com.android.systemui.statusbar.phone.LockscreenGestureLogger.LockscreenUiEvent.LOCKSCREEN_UNLOCKED_NOTIFICATION_PANEL_EXPAND);
         */
        /* JADX WARN: Code restructure failed: missing block: B:415:0x0784, code lost:
        
            if (r5 != false) goto L424;
         */
        /* JADX WARN: Code restructure failed: missing block: B:417:0x0788, code lost:
        
            if (r4.mTouchSlopExceededBeforeDown == false) goto L423;
         */
        /* JADX WARN: Code restructure failed: missing block: B:418:0x078b, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:419:0x078e, code lost:
        
            r4.mTouchSlopExceeded = r5;
            r4.cancelHeightAnimator();
            r20.this$0.onTrackingStarted$1();
         */
        /* JADX WARN: Code restructure failed: missing block: B:420:0x078d, code lost:
        
            r5 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:421:0x077d, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:422:0x0769, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:423:0x04fe, code lost:
        
            r7 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:424:0x04d3, code lost:
        
            r7 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:425:0x04d7, code lost:
        
            if (r7.mExpectingSynthesizedDown == false) goto L289;
         */
        /* JADX WARN: Code restructure failed: missing block: B:426:0x04d9, code lost:
        
            r7.mExpectingSynthesizedDown = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:427:0x04dc, code lost:
        
            r8 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:428:0x04e9, code lost:
        
            r7.mGestureWaitForTouchSlop = r8;
            r20.this$0.mIgnoreXTouchSlop = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:430:0x04e2, code lost:
        
            if (r7.isFullyCollapsed() != false) goto L293;
         */
        /* JADX WARN: Code restructure failed: missing block: B:432:0x04e6, code lost:
        
            if (r7.mBarState == 0) goto L288;
         */
        /* JADX WARN: Code restructure failed: missing block: B:433:0x04e8, code lost:
        
            r8 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:435:0x00b4, code lost:
        
            if (r20.this$0.mPulseExpansionHandler.isExpanding != false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00ac, code lost:
        
            if (r2.mQsController.shouldQuickSettingsIntercept(r2.mDownX, r2.mDownY, 0.0f) != false) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00be, code lost:
        
            if (r20.this$0.mPulseExpansionHandler.onTouchEvent(r21) == false) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00c0, code lost:
        
            r20.this$0.mShadeLog.logMotionEvent(r21, "onTouch: PulseExpansionHandler handled event");
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00c9, code lost:
        
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ca, code lost:
        
            r2 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00ce, code lost:
        
            if (r2.mPulsing == false) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00d0, code lost:
        
            r2.mShadeLog.logMotionEvent(r21, "onTouch: eat touch, device pulsing");
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00d7, code lost:
        
            return true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00da, code lost:
        
            if (r2.mListenForHeadsUp == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00dc, code lost:
        
            r6 = r2.mHeadsUpTouchHelper;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00e0, code lost:
        
            if (r6.mTrackingHeadsUp != false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00e6, code lost:
        
            if (r2.mNotificationStackScrollLayoutController.mLongPressedView == null) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00e8, code lost:
        
            r2 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00eb, code lost:
        
            if (r2 != false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00f1, code lost:
        
            if (r6.onInterceptTouchEvent(r21) == false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00f3, code lost:
        
            r20.this$0.mMetricsLogger.count("panel_open_peek", 1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00ea, code lost:
        
            r2 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00fc, code lost:
        
            r2 = r20.this$0.mHeadsUpTouchHelper.onTouchEvent(r21);
            r6 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x010e, code lost:
        
            if (r6.mHeadsUpTouchHelper.mTrackingHeadsUp != false) goto L246;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0110, code lost:
        
            r8 = r6.isFullyCollapsed();
            r15 = r20.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0118, code lost:
        
            if (r15.mHeightAnimator == null) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x011c, code lost:
        
            if (r15.mIsSpringBackAnimation != false) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x011e, code lost:
        
            r9 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0121, code lost:
        
            r6 = r6.mQsController;
            r6.getClass();
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x012e, code lost:
        
            if (r6.isSplitShadeAndTouchXOutsideQs(r21.getX()) == false) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0132, code lost:
        
            r15 = r21.getActionMasked();
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x013a, code lost:
        
            if (r6.getExpanded() != false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x013e, code lost:
        
            if (r6.mSplitShadeEnabled != false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0140, code lost:
        
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0147, code lost:
        
            if (r6.mShadeExpandedFraction != 1.0f) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x014b, code lost:
        
            if (r6.mBarState == 1) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x014d, code lost:
        
            if (r11 == false) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0153, code lost:
        
            if (r6.isExpansionEnabled() == false) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0155, code lost:
        
            r7 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0158, code lost:
        
            r11 = r6.mShadeLog;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x015a, code lost:
        
            if (r15 != 0) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x015c, code lost:
        
            if (r7 == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x015e, code lost:
        
            r11.logMotionEvent(r21, "handleQsTouch: down action, QS tracking enabled");
            r6.setTracking(true);
            r6.traceQsJank(true, false);
            r6.mConflictingExpansionGesture = true;
            r6.onExpansionStarted();
            r6.mInitialHeightOnTouch = r6.mExpansionHeight;
            r6.mInitialTouchY = r21.getY();
            r6.mInitialTouchX = r21.getX();
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x017e, code lost:
        
            r7 = r6.mPanelViewControllerLazy;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0180, code lost:
        
            if (r8 != false) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0182, code lost:
        
            if (r9 != false) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0188, code lost:
        
            if (r21.getActionMasked() != 0) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0198, code lost:
        
            if (r6.shouldQuickSettingsIntercept(r21.getX(), r21.getY(), -1.0f) == false) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x019a, code lost:
        
            r11.logMotionEvent(r21, "handleQsDown: down action, QS tracking enabled");
            r6.setTracking(true);
            r6.onExpansionStarted();
            r6.mInitialHeightOnTouch = r6.mExpansionHeight;
            r6.mInitialTouchY = r21.getY();
            r6.mInitialTouchX = r21.getX();
            ((com.android.systemui.shade.NotificationPanelViewController) r7.get()).notifyExpandingFinished();
         */
        @Override // com.android.systemui.Gefingerpoken
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean onTouchEvent(android.view.MotionEvent r21) {
            /*
                Method dump skipped, instructions count: 2095
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationPanelViewController.TouchHandler.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    /* renamed from: -$$Nest$maddMovement, reason: not valid java name */
    public static void m857$$Nest$maddMovement(NotificationPanelViewController notificationPanelViewController, MotionEvent motionEvent) {
        notificationPanelViewController.getClass();
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        notificationPanelViewController.mVelocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x013d, code lost:
    
        if ((android.os.SystemClock.uptimeMillis() - r19.mDownTime) <= 300) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0144, code lost:
    
        if (r11 > 0.0f) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c6  */
    /* renamed from: -$$Nest$mendMotionEvent, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m858$$Nest$mendMotionEvent(com.android.systemui.shade.NotificationPanelViewController r19, android.view.MotionEvent r20, float r21, float r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationPanelViewController.m858$$Nest$mendMotionEvent(com.android.systemui.shade.NotificationPanelViewController, android.view.MotionEvent, float, float, boolean):void");
    }

    /* renamed from: -$$Nest$minitDownStates, reason: not valid java name */
    public static void m859$$Nest$minitDownStates(NotificationPanelViewController notificationPanelViewController, MotionEvent motionEvent) {
        boolean z;
        notificationPanelViewController.getClass();
        boolean z2 = false;
        if (motionEvent.getActionMasked() != 0) {
            notificationPanelViewController.mLastEventSynthesizedDown = false;
            return;
        }
        notificationPanelViewController.mDozingOnDown = notificationPanelViewController.mDozing;
        notificationPanelViewController.mDownX = motionEvent.getX();
        notificationPanelViewController.mDownY = motionEvent.getY();
        boolean isFullyCollapsed = notificationPanelViewController.isFullyCollapsed();
        notificationPanelViewController.mCollapsedOnDown = isFullyCollapsed;
        QuickSettingsControllerImpl quickSettingsControllerImpl = notificationPanelViewController.mQsController;
        quickSettingsControllerImpl.mCollapsedOnDown = isFullyCollapsed;
        float f = notificationPanelViewController.mDownX;
        float f2 = notificationPanelViewController.mDownY;
        if (!isFullyCollapsed && quickSettingsControllerImpl.mBarState != 1 && !quickSettingsControllerImpl.getExpanded()) {
            QS qs = quickSettingsControllerImpl.mQs;
            int bottom = qs == null ? quickSettingsControllerImpl.mKeyguardStatusBar.getBottom() : qs.getHeader().getBottom();
            if (f >= quickSettingsControllerImpl.mQsFrame.getX() && f <= quickSettingsControllerImpl.mQsFrame.getX() + quickSettingsControllerImpl.mQsFrame.getWidth() && f2 <= bottom) {
                z = true;
                notificationPanelViewController.mIsPanelCollapseOnQQS = z;
                if (notificationPanelViewController.mCollapsedOnDown && ((BaseHeadsUpManager) notificationPanelViewController.mHeadsUpManager).mHasPinnedNotification) {
                    z2 = true;
                }
                notificationPanelViewController.mListenForHeadsUp = z2;
                boolean z3 = notificationPanelViewController.mExpectingSynthesizedDown;
                notificationPanelViewController.mAllowExpandForSmallExpansion = z3;
                notificationPanelViewController.mTouchSlopExceededBeforeDown = z3;
                notificationPanelViewController.mLastEventSynthesizedDown = z3;
                long eventTime = motionEvent.getEventTime();
                float f3 = notificationPanelViewController.mDownX;
                float f4 = notificationPanelViewController.mDownY;
                boolean z4 = quickSettingsControllerImpl.mFullyExpanded;
                quickSettingsControllerImpl.mTouchAboveFalsingThreshold = z4;
                boolean z5 = notificationPanelViewController.mDozingOnDown;
                boolean z6 = notificationPanelViewController.mCollapsedOnDown;
                boolean z7 = notificationPanelViewController.mIsPanelCollapseOnQQS;
                boolean z8 = notificationPanelViewController.mListenForHeadsUp;
                boolean z9 = notificationPanelViewController.mAllowExpandForSmallExpansion;
                boolean z10 = notificationPanelViewController.mTouchSlopExceededBeforeDown;
                boolean z11 = notificationPanelViewController.mLastEventSynthesizedDown;
                NPVCDownEventState nPVCDownEventState = (NPVCDownEventState) notificationPanelViewController.mLastDownEvents.buffer.advance();
                nPVCDownEventState.timeStamp = eventTime;
                nPVCDownEventState.x = f3;
                nPVCDownEventState.y = f4;
                nPVCDownEventState.qsTouchAboveFalsingThreshold = z4;
                nPVCDownEventState.dozing = z5;
                nPVCDownEventState.collapsed = z6;
                nPVCDownEventState.canCollapseOnQQS = z7;
                nPVCDownEventState.listenForHeadsUp = z8;
                nPVCDownEventState.allowExpandForSmallExpansion = z9;
                nPVCDownEventState.touchSlopExceededBeforeDown = z10;
                nPVCDownEventState.lastEventSynthesized = z11;
            }
        }
        z = false;
        notificationPanelViewController.mIsPanelCollapseOnQQS = z;
        if (notificationPanelViewController.mCollapsedOnDown) {
            z2 = true;
        }
        notificationPanelViewController.mListenForHeadsUp = z2;
        boolean z32 = notificationPanelViewController.mExpectingSynthesizedDown;
        notificationPanelViewController.mAllowExpandForSmallExpansion = z32;
        notificationPanelViewController.mTouchSlopExceededBeforeDown = z32;
        notificationPanelViewController.mLastEventSynthesizedDown = z32;
        long eventTime2 = motionEvent.getEventTime();
        float f32 = notificationPanelViewController.mDownX;
        float f42 = notificationPanelViewController.mDownY;
        boolean z42 = quickSettingsControllerImpl.mFullyExpanded;
        quickSettingsControllerImpl.mTouchAboveFalsingThreshold = z42;
        boolean z52 = notificationPanelViewController.mDozingOnDown;
        boolean z62 = notificationPanelViewController.mCollapsedOnDown;
        boolean z72 = notificationPanelViewController.mIsPanelCollapseOnQQS;
        boolean z82 = notificationPanelViewController.mListenForHeadsUp;
        boolean z92 = notificationPanelViewController.mAllowExpandForSmallExpansion;
        boolean z102 = notificationPanelViewController.mTouchSlopExceededBeforeDown;
        boolean z112 = notificationPanelViewController.mLastEventSynthesizedDown;
        NPVCDownEventState nPVCDownEventState2 = (NPVCDownEventState) notificationPanelViewController.mLastDownEvents.buffer.advance();
        nPVCDownEventState2.timeStamp = eventTime2;
        nPVCDownEventState2.x = f32;
        nPVCDownEventState2.y = f42;
        nPVCDownEventState2.qsTouchAboveFalsingThreshold = z42;
        nPVCDownEventState2.dozing = z52;
        nPVCDownEventState2.collapsed = z62;
        nPVCDownEventState2.canCollapseOnQQS = z72;
        nPVCDownEventState2.listenForHeadsUp = z82;
        nPVCDownEventState2.allowExpandForSmallExpansion = z92;
        nPVCDownEventState2.touchSlopExceededBeforeDown = z102;
        nPVCDownEventState2.lastEventSynthesized = z112;
    }

    /* renamed from: -$$Nest$mstartExpandMotion, reason: not valid java name */
    public static void m860$$Nest$mstartExpandMotion(NotificationPanelViewController notificationPanelViewController, float f, float f2, boolean z, float f3) {
        if (!notificationPanelViewController.mHandlingPointerUp && !((StatusBarStateControllerImpl) notificationPanelViewController.mStatusBarStateController).mIsDozing) {
            notificationPanelViewController.mQsController.beginJankMonitoring(notificationPanelViewController.isFullyCollapsed());
        }
        notificationPanelViewController.mInitialOffsetOnTouch = f3;
        if (!notificationPanelViewController.isTracking() || notificationPanelViewController.isFullyCollapsed()) {
            notificationPanelViewController.mInitialExpandY = f2;
            notificationPanelViewController.mInitialExpandX = f;
        } else {
            notificationPanelViewController.mShadeLog.d("not setting mInitialExpandY in startExpandMotion");
        }
        notificationPanelViewController.mInitialTouchFromKeyguard = notificationPanelViewController.mKeyguardStateController.mShowing;
        if (z) {
            notificationPanelViewController.mTouchSlopExceeded = true;
            notificationPanelViewController.setExpandedHeight(notificationPanelViewController.mInitialOffsetOnTouch);
            notificationPanelViewController.onTrackingStarted$1();
        }
    }

    /* JADX WARN: Type inference failed for: r9v5, types: [com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda12] */
    public NotificationPanelViewController(NotificationPanelView notificationPanelView, Handler handler, LayoutInflater layoutInflater, FeatureFlags featureFlags, NotificationWakeUpCoordinator notificationWakeUpCoordinator, PulseExpansionHandler pulseExpansionHandler, DynamicPrivacyController dynamicPrivacyController, KeyguardBypassController keyguardBypassController, FalsingManager falsingManager, FalsingCollector falsingCollector, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, NotificationShadeWindowController notificationShadeWindowController, DozeLog dozeLog, DozeParameters dozeParameters, CommandQueue commandQueue, VibratorHelper vibratorHelper, LatencyTracker latencyTracker, AccessibilityManager accessibilityManager, int i, KeyguardUpdateMonitor keyguardUpdateMonitor, MetricsLogger metricsLogger, ShadeLogger shadeLogger, ConfigurationController configurationController, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, StatusBarTouchableRegionManager statusBarTouchableRegionManager, ConversationNotificationManager conversationNotificationManager, MediaHierarchyManager mediaHierarchyManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager, NotificationGutsManager notificationGutsManager, NotificationsQSContainerController notificationsQSContainerController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4, LockscreenShadeTransitionController lockscreenShadeTransitionController, AuthController authController, ScrimController scrimController, UserManager userManager, MediaDataManager mediaDataManager, NotificationShadeDepthController notificationShadeDepthController, AmbientState ambientState, LockIconViewController lockIconViewController, KeyguardMediaController keyguardMediaController, TapAgainViewController tapAgainViewController, NavigationModeController navigationModeController, NavigationBarControllerImpl navigationBarControllerImpl, QuickSettingsControllerImpl quickSettingsControllerImpl, FragmentService fragmentService, IStatusBarService iStatusBarService, ContentResolver contentResolver, ShadeHeaderController shadeHeaderController, ScreenOffAnimationController screenOffAnimationController, LockscreenGestureLogger lockscreenGestureLogger, ShadeExpansionStateManager shadeExpansionStateManager, ShadeRepository shadeRepository, Optional optional, SysUiState sysUiState, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2, KeyguardUnlockAnimationController keyguardUnlockAnimationController, KeyguardIndicationController keyguardIndicationController, NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl, NotificationStackSizeCalculator notificationStackSizeCalculator, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, SystemClock systemClock, KeyguardBottomAreaViewModel keyguardBottomAreaViewModel, KeyguardBottomAreaInteractor keyguardBottomAreaInteractor, KeyguardClockInteractor keyguardClockInteractor, AlternateBouncerInteractor alternateBouncerInteractor, DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel, OccludedToLockscreenTransitionViewModel occludedToLockscreenTransitionViewModel, LockscreenToDreamingTransitionViewModel lockscreenToDreamingTransitionViewModel, GoneToDreamingTransitionViewModel goneToDreamingTransitionViewModel, GoneToDreamingLockscreenHostedTransitionViewModel goneToDreamingLockscreenHostedTransitionViewModel, LockscreenToOccludedTransitionViewModel lockscreenToOccludedTransitionViewModel, PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, CoroutineDispatcher coroutineDispatcher, KeyguardTransitionInteractor keyguardTransitionInteractor, DumpManager dumpManager, KeyguardTouchHandlingViewModel keyguardTouchHandlingViewModel, KeyguardInteractor keyguardInteractor, ActivityStarter activityStarter, SharedNotificationContainerInteractor sharedNotificationContainerInteractor, ActiveNotificationsInteractor activeNotificationsInteractor, ShadeAnimationInteractor shadeAnimationInteractor, KeyguardViewConfigurator keyguardViewConfigurator, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, SplitShadeStateControllerImpl splitShadeStateControllerImpl, PowerInteractor powerInteractor, KeyguardClockPositionAlgorithm keyguardClockPositionAlgorithm, NaturalScrollingSettingObserver naturalScrollingSettingObserver) {
        int i2 = 4;
        int i3 = 2;
        final int i4 = 1;
        final int i5 = 0;
        BiConsumer biConsumer = new BiConsumer() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda23
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                notificationPanelViewController.getClass();
                float floatValue = ((Float) obj2).floatValue();
                KeyguardRepositoryImpl keyguardRepositoryImpl = notificationPanelViewController.mKeyguardInteractor.repository;
                Float valueOf = Float.valueOf(floatValue / 255.0f);
                StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.panelAlpha;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
                int i6 = (int) floatValue;
                NotificationPanelView notificationPanelView2 = notificationPanelViewController.mView;
                notificationPanelView2.mCurrentPanelAlpha = i6;
                notificationPanelView2.mAlphaPaint.setARGB(i6, 255, 255, 255);
                notificationPanelView2.invalidate();
            }
        };
        NotificationPanelViewController$$ExternalSyntheticLambda21 notificationPanelViewController$$ExternalSyntheticLambda21 = new NotificationPanelViewController$$ExternalSyntheticLambda21(i4);
        AnimatableProperty.AnonymousClass7 anonymousClass7 = AnimatableProperty.Y;
        AnimatableProperty.AnonymousClass5 anonymousClass5 = new AnimatableProperty.AnonymousClass5("panelAlpha", notificationPanelViewController$$ExternalSyntheticLambda21, biConsumer);
        this.mPanelAlphaAnimator = new AnimatableProperty.AnonymousClass6(R.id.panel_alpha_animator_start_tag, R.id.panel_alpha_animator_end_tag, R.id.panel_alpha_animator_tag, anonymousClass5);
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 150L;
        Interpolator interpolator = Interpolators.ALPHA_OUT;
        animationProperties.setCustomInterpolator(anonymousClass5, interpolator);
        this.mPanelAlphaOutPropertiesAnimator = animationProperties;
        AnimationProperties animationProperties2 = new AnimationProperties();
        animationProperties2.duration = 200L;
        animationProperties2.mAnimationEndAction = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, i4);
        animationProperties2.setCustomInterpolator(anonymousClass5, Interpolators.ALPHA_IN);
        this.mPanelAlphaInPropertiesAnimator = animationProperties2;
        this.mCurrentPanelState = 0;
        this.mKeyguardOnlyContentAlpha = 1.0f;
        this.mKeyguardOnlyTransitionTranslationY = 0;
        this.mHasVibratedOnOpen = false;
        this.mFixedDuration = -1;
        this.mLastGesturedOverExpansion = -1.0f;
        this.mExpandedFraction = 0.0f;
        this.mExpansionDragDownAmountPx = 0.0f;
        this.mNextCollapseSpeedUpFactor = 1.0f;
        this.mUseExternalTouch = false;
        this.mIsOcclusionTransitionRunning = false;
        this.mForceFlingAnimationForTest = false;
        this.mFlingCollapseRunnable = new NotificationPanelViewController$$ExternalSyntheticLambda0(this, i4);
        new NotificationPanelViewController$$ExternalSyntheticLambda0(this, i3);
        this.mHeadsUpExistenceChangedRunnable = new NotificationPanelViewController$$ExternalSyntheticLambda0(this, 3);
        this.mMaybeHideExpandedRunnable = new NotificationPanelViewController$$ExternalSyntheticLambda0(this, i2);
        this.mDreamingToLockscreenTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, i3);
        this.mOccludedToLockscreenTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, i5);
        this.mLockscreenToDreamingTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, i2);
        this.mGoneToDreamingTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, 6);
        this.mGoneToDreamingLockscreenHostedTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, 7);
        this.mLockscreenToDreamingLockscreenHostedTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, 8);
        this.mDreamingLockscreenHostedToLockscreenTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, 9);
        this.mLockscreenToOccludedTransition = new NotificationPanelViewController$$ExternalSyntheticLambda2(this, 10);
        this.mShadeViewStateProvider = new AnonymousClass10();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.shade.NotificationPanelViewController.1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardFadingAwayChanged() {
                NotificationPanelViewController.this.updateExpandedHeightToMaxHeight();
            }
        });
        this.mAmbientState = ambientState;
        this.mView = notificationPanelView;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mLockscreenGestureLogger = lockscreenGestureLogger;
        this.mShadeExpansionStateManager = shadeExpansionStateManager;
        this.mShadeRepository = shadeRepository;
        this.mShadeAnimationInteractor = shadeAnimationInteractor;
        this.mShadeLog = shadeLogger;
        this.mGutsManager = notificationGutsManager;
        this.mDreamingToLockscreenTransitionViewModel = dreamingToLockscreenTransitionViewModel;
        this.mLockscreenToDreamingTransitionViewModel = lockscreenToDreamingTransitionViewModel;
        this.mGoneToDreamingTransitionViewModel = goneToDreamingTransitionViewModel;
        this.mGoneToDreamingLockscreenHostedTransitionViewModel = goneToDreamingLockscreenHostedTransitionViewModel;
        this.mKeyguardTransitionInteractor = keyguardTransitionInteractor;
        this.mSharedNotificationContainerInteractor = sharedNotificationContainerInteractor;
        this.mActiveNotificationsInteractor = activeNotificationsInteractor;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mPowerInteractor = powerInteractor;
        this.mKeyguardViewConfigurator = keyguardViewConfigurator;
        this.mClockPositionAlgorithm = keyguardClockPositionAlgorithm;
        this.mNaturalScrollingSettingObserver = naturalScrollingSettingObserver;
        notificationPanelView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(this) { // from class: com.android.systemui.shade.NotificationPanelViewController.2
            public final /* synthetic */ NotificationPanelViewController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                switch (i5) {
                    case 0:
                        NotificationPanelViewController notificationPanelViewController = this.this$0;
                        notificationPanelViewController.mViewName = notificationPanelViewController.mResources.getResourceName(notificationPanelViewController.mView.getId());
                        break;
                    default:
                        NotificationPanelViewController notificationPanelViewController2 = this.this$0;
                        FragmentHostManager fragmentHostManager = notificationPanelViewController2.mFragmentService.getFragmentHostManager(notificationPanelViewController2.mView);
                        QuickSettingsControllerImpl quickSettingsControllerImpl2 = this.this$0.mQsController;
                        quickSettingsControllerImpl2.getClass();
                        fragmentHostManager.addTagListener(QS.TAG, quickSettingsControllerImpl2.new QsFragmentListener());
                        NotificationPanelViewController notificationPanelViewController3 = this.this$0;
                        ((StatusBarStateControllerImpl) notificationPanelViewController3.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) notificationPanelViewController3.mStatusBarStateListener);
                        NotificationPanelViewController notificationPanelViewController4 = this.this$0;
                        notificationPanelViewController4.mStatusBarStateListener.onStateChanged(((StatusBarStateControllerImpl) notificationPanelViewController4.mStatusBarStateController).mState, true);
                        NotificationPanelViewController notificationPanelViewController5 = this.this$0;
                        ((ConfigurationControllerImpl) notificationPanelViewController5.mConfigurationController).addCallback(notificationPanelViewController5.mConfigurationListener);
                        this.this$0.mConfigurationListener.onThemeChanged();
                        NotificationPanelViewController notificationPanelViewController6 = this.this$0;
                        notificationPanelViewController6.mFalsingManager.addTapListener(notificationPanelViewController6.mFalsingTapListener);
                        this.this$0.mKeyguardIndicationController.init();
                        NotificationPanelViewController notificationPanelViewController7 = this.this$0;
                        notificationPanelViewController7.mContentResolver.registerContentObserver(Settings.Global.getUriFor("user_switcher_enabled"), false, notificationPanelViewController7.mSettingsChangeObserver);
                        break;
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                switch (i5) {
                    case 0:
                        break;
                    default:
                        NotificationPanelViewController notificationPanelViewController = this.this$0;
                        notificationPanelViewController.mContentResolver.unregisterContentObserver(notificationPanelViewController.mSettingsChangeObserver);
                        NotificationPanelViewController notificationPanelViewController2 = this.this$0;
                        FragmentHostManager fragmentHostManager = notificationPanelViewController2.mFragmentService.getFragmentHostManager(notificationPanelViewController2.mView);
                        QuickSettingsControllerImpl quickSettingsControllerImpl2 = this.this$0.mQsController;
                        quickSettingsControllerImpl2.getClass();
                        QuickSettingsControllerImpl.QsFragmentListener qsFragmentListener = quickSettingsControllerImpl2.new QsFragmentListener();
                        ArrayList arrayList = (ArrayList) fragmentHostManager.mListeners.get(QS.TAG);
                        if (arrayList != null && arrayList.remove(qsFragmentListener) && arrayList.size() == 0) {
                            fragmentHostManager.mListeners.remove(QS.TAG);
                        }
                        NotificationPanelViewController notificationPanelViewController3 = this.this$0;
                        ((StatusBarStateControllerImpl) notificationPanelViewController3.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) notificationPanelViewController3.mStatusBarStateListener);
                        NotificationPanelViewController notificationPanelViewController4 = this.this$0;
                        ((ConfigurationControllerImpl) notificationPanelViewController4.mConfigurationController).removeCallback(notificationPanelViewController4.mConfigurationListener);
                        NotificationPanelViewController notificationPanelViewController5 = this.this$0;
                        notificationPanelViewController5.mFalsingManager.removeTapListener(notificationPanelViewController5.mFalsingTapListener);
                        break;
                }
            }

            private final void onViewDetachedFromWindow$com$android$systemui$shade$NotificationPanelViewController$2(View view) {
            }
        });
        notificationPanelView.addOnLayoutChangeListener(new ShadeLayoutChangeListener());
        TouchHandler touchHandler = getTouchHandler();
        notificationPanelView.setOnTouchListener(touchHandler);
        notificationPanelView.mTouchHandler = touchHandler;
        notificationPanelView.mOnConfigurationChangedListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        Resources resources = notificationPanelView.getResources();
        this.mResources = resources;
        this.mKeyguardStateController = keyguardStateControllerImpl;
        this.mQsController = quickSettingsControllerImpl;
        this.mKeyguardIndicationController = keyguardIndicationController;
        this.mStatusBarStateController = (SysuiStatusBarStateController) statusBarStateController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        FlingAnimationUtils.Builder builder = (FlingAnimationUtils.Builder) switchingProvider.get();
        builder.reset();
        builder.mMaxLengthSeconds = 0.6f;
        builder.mSpeedUpFactor = 0.6f;
        this.mFlingAnimationUtils = builder.build();
        builder.reset();
        builder.mMaxLengthSeconds = 0.6f;
        builder.mSpeedUpFactor = 0.6f;
        this.mFlingAnimationUtilsClosing = builder.build();
        builder.reset();
        builder.mMaxLengthSeconds = 0.5f;
        builder.mSpeedUpFactor = 0.6f;
        builder.mX2 = 0.6f;
        builder.mY2 = 0.84f;
        this.mFlingAnimationUtilsDismissing = builder.build();
        this.mLatencyTracker = latencyTracker;
        this.mFalsingManager = falsingManager;
        this.mDozeLog = dozeLog;
        this.mNotificationsDragEnabled = resources.getBoolean(R.bool.config_enableNotificationShadeDrag);
        this.mVibratorHelper = vibratorHelper;
        this.mVibrateOnOpening = resources.getBoolean(R.bool.config_vibrateOnIconAnimation);
        this.mStatusBarTouchableRegionManager = statusBarTouchableRegionManager;
        this.mSystemClock = systemClock;
        this.mKeyguardMediaController = keyguardMediaController;
        this.mMetricsLogger = metricsLogger;
        this.mConfigurationController = configurationController;
        this.mFlingAnimationUtilsBuilder = switchingProvider;
        this.mMediaHierarchyManager = mediaHierarchyManager;
        this.mNotificationsQSContainerController = notificationsQSContainerController;
        this.mNotificationListContainer = notificationListContainerImpl;
        this.mNotificationStackSizeCalculator = notificationStackSizeCalculator;
        this.mNavigationBarController = navigationBarControllerImpl;
        notificationsQSContainerController.init$9();
        this.mNotificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.mKeyguardStatusBarViewComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory4;
        this.mDepthController = notificationShadeDepthController;
        this.mContentResolver = contentResolver;
        this.mKeyguardQsUserSwitchComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2;
        this.mKeyguardUserSwitcherComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory3;
        this.mFragmentService = fragmentService;
        this.mStatusBarService = iStatusBarService;
        this.mSettingsChangeObserver = new SettingsChangeObserver(handler);
        this.mSplitShadeStateController = splitShadeStateControllerImpl;
        this.mSplitShadeEnabled = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(resources);
        notificationPanelView.setWillNotDraw(true);
        this.mShadeHeaderController = shadeHeaderController;
        this.mLayoutInflater = layoutInflater;
        this.mFeatureFlags = featureFlags;
        this.mFalsingCollector = falsingCollector;
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mMainDispatcher = coroutineDispatcher;
        this.mAccessibilityManager = accessibilityManager;
        notificationPanelView.setAccessibilityPaneTitle(determineAccessibilityPaneTitle());
        setAlpha(255, false);
        this.mCommandQueue = commandQueue;
        this.mDisplayId = i;
        this.mPulseExpansionHandler = pulseExpansionHandler;
        this.mDozeParameters = dozeParameters;
        this.mScrimController = scrimController;
        this.mUserManager = userManager;
        this.mMediaDataManager = mediaDataManager;
        this.mTapAgainViewController = tapAgainViewController;
        this.mSysUiState = sysUiState;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mLockscreenShadeTransitionController = lockscreenShadeTransitionController;
        dynamicPrivacyController.mListeners.add(new DynamicPrivacyController.Listener() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda10
            @Override // com.android.systemui.statusbar.notification.DynamicPrivacyController.Listener
            public final void onDynamicPrivacyChanged() {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                if (notificationPanelViewController.mLinearDarkAmount != 0.0f) {
                    return;
                }
                notificationPanelViewController.mAnimateNextPositionUpdate = true;
            }
        });
        quickSettingsControllerImpl.mExpansionHeightListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        quickSettingsControllerImpl.mQsStateUpdateListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        quickSettingsControllerImpl.mApplyClippingImmediatelyListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        quickSettingsControllerImpl.mFlingQsWithoutClickListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        quickSettingsControllerImpl.mExpansionHeightSetToMaxListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        shadeExpansionStateManager.stateListeners.add(new ShadeStateListener() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda17
            @Override // com.android.systemui.shade.ShadeStateListener
            public final void onPanelStateChanged(int i6) {
                NotificationPanelViewController.this.onPanelStateChanged$1(i6);
            }
        });
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mBottomAreaShadeAlphaAnimator = ofFloat;
        ofFloat.addUpdateListener(new NotificationPanelViewController$$ExternalSyntheticLambda18(this, 0));
        ofFloat.setDuration(160L);
        ofFloat.setInterpolator(interpolator);
        this.mConversationNotificationManager = conversationNotificationManager;
        this.mAuthController = authController;
        this.mLockIconViewController = lockIconViewController;
        this.mScreenOffAnimationController = screenOffAnimationController;
        this.mUnlockedScreenOffAnimationController = unlockedScreenOffAnimationController;
        this.mLastDownEvents = new NPVCDownEventState.Buffer();
        this.mDeviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.mIsGestureNavigation = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda19
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i6) {
                NotificationPanelViewController.this.mIsGestureNavigation = QuickStepContract.isGesturalMode(i6);
            }
        }));
        notificationPanelView.setBackgroundColor(0);
        View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener(this) { // from class: com.android.systemui.shade.NotificationPanelViewController.2
            public final /* synthetic */ NotificationPanelViewController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                switch (i4) {
                    case 0:
                        NotificationPanelViewController notificationPanelViewController = this.this$0;
                        notificationPanelViewController.mViewName = notificationPanelViewController.mResources.getResourceName(notificationPanelViewController.mView.getId());
                        break;
                    default:
                        NotificationPanelViewController notificationPanelViewController2 = this.this$0;
                        FragmentHostManager fragmentHostManager = notificationPanelViewController2.mFragmentService.getFragmentHostManager(notificationPanelViewController2.mView);
                        QuickSettingsControllerImpl quickSettingsControllerImpl2 = this.this$0.mQsController;
                        quickSettingsControllerImpl2.getClass();
                        fragmentHostManager.addTagListener(QS.TAG, quickSettingsControllerImpl2.new QsFragmentListener());
                        NotificationPanelViewController notificationPanelViewController3 = this.this$0;
                        ((StatusBarStateControllerImpl) notificationPanelViewController3.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) notificationPanelViewController3.mStatusBarStateListener);
                        NotificationPanelViewController notificationPanelViewController4 = this.this$0;
                        notificationPanelViewController4.mStatusBarStateListener.onStateChanged(((StatusBarStateControllerImpl) notificationPanelViewController4.mStatusBarStateController).mState, true);
                        NotificationPanelViewController notificationPanelViewController5 = this.this$0;
                        ((ConfigurationControllerImpl) notificationPanelViewController5.mConfigurationController).addCallback(notificationPanelViewController5.mConfigurationListener);
                        this.this$0.mConfigurationListener.onThemeChanged();
                        NotificationPanelViewController notificationPanelViewController6 = this.this$0;
                        notificationPanelViewController6.mFalsingManager.addTapListener(notificationPanelViewController6.mFalsingTapListener);
                        this.this$0.mKeyguardIndicationController.init();
                        NotificationPanelViewController notificationPanelViewController7 = this.this$0;
                        notificationPanelViewController7.mContentResolver.registerContentObserver(Settings.Global.getUriFor("user_switcher_enabled"), false, notificationPanelViewController7.mSettingsChangeObserver);
                        break;
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                switch (i4) {
                    case 0:
                        break;
                    default:
                        NotificationPanelViewController notificationPanelViewController = this.this$0;
                        notificationPanelViewController.mContentResolver.unregisterContentObserver(notificationPanelViewController.mSettingsChangeObserver);
                        NotificationPanelViewController notificationPanelViewController2 = this.this$0;
                        FragmentHostManager fragmentHostManager = notificationPanelViewController2.mFragmentService.getFragmentHostManager(notificationPanelViewController2.mView);
                        QuickSettingsControllerImpl quickSettingsControllerImpl2 = this.this$0.mQsController;
                        quickSettingsControllerImpl2.getClass();
                        QuickSettingsControllerImpl.QsFragmentListener qsFragmentListener = quickSettingsControllerImpl2.new QsFragmentListener();
                        ArrayList arrayList = (ArrayList) fragmentHostManager.mListeners.get(QS.TAG);
                        if (arrayList != null && arrayList.remove(qsFragmentListener) && arrayList.size() == 0) {
                            fragmentHostManager.mListeners.remove(QS.TAG);
                        }
                        NotificationPanelViewController notificationPanelViewController3 = this.this$0;
                        ((StatusBarStateControllerImpl) notificationPanelViewController3.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) notificationPanelViewController3.mStatusBarStateListener);
                        NotificationPanelViewController notificationPanelViewController4 = this.this$0;
                        ((ConfigurationControllerImpl) notificationPanelViewController4.mConfigurationController).removeCallback(notificationPanelViewController4.mConfigurationListener);
                        NotificationPanelViewController notificationPanelViewController5 = this.this$0;
                        notificationPanelViewController5.mFalsingManager.removeTapListener(notificationPanelViewController5.mFalsingTapListener);
                        break;
                }
            }

            private final void onViewDetachedFromWindow$com$android$systemui$shade$NotificationPanelViewController$2(View view) {
            }
        };
        notificationPanelView.addOnAttachStateChangeListener(onAttachStateChangeListener);
        if (notificationPanelView.isAttachedToWindow()) {
            onAttachStateChangeListener.onViewAttachedToWindow(notificationPanelView);
        }
        notificationPanelView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda20
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                notificationPanelViewController.getClass();
                Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
                notificationPanelViewController.mDisplayTopInset = insetsIgnoringVisibility.top;
                int i6 = insetsIgnoringVisibility.right;
                notificationPanelViewController.mDisplayRightInset = i6;
                int i7 = insetsIgnoringVisibility.left;
                notificationPanelViewController.mDisplayLeftInset = i7;
                QuickSettingsControllerImpl quickSettingsControllerImpl2 = notificationPanelViewController.mQsController;
                quickSettingsControllerImpl2.mDisplayLeftInset = i7;
                quickSettingsControllerImpl2.mDisplayRightInset = i6;
                notificationPanelViewController.mNavigationBarBottomHeight = windowInsets.getStableInsetBottom();
                notificationPanelViewController.updateMaxHeadsUpTranslation();
                return windowInsets;
            }
        });
        this.mKeyguardUnfoldTransition = optional.map(new NotificationPanelViewController$$ExternalSyntheticLambda21(i5));
        updateUserSwitcherFlags();
        this.mKeyguardClockInteractor = keyguardClockInteractor;
        KeyguardLongPressViewBinder.bind((LongPressHandlingView) notificationPanelView.requireViewById(R.id.keyguard_long_press), keyguardTouchHandlingViewModel, new NotificationPanelViewController$$ExternalSyntheticLambda22(this), falsingManager);
        this.mActivityStarter = activityStarter;
        onFinishInflate();
        keyguardUnlockAnimationController.listeners.add(new KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener() { // from class: com.android.systemui.shade.NotificationPanelViewController.3
            @Override // com.android.systemui.keyguard.KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener
            public final void onUnlockAnimationFinished() {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                notificationPanelViewController.positionClockAndNotifications(true);
                ScrimController scrimController2 = notificationPanelViewController.mScrimController;
                scrimController2.mAnimatingPanelExpansionOnUnlock = false;
                scrimController2.applyAndDispatchState();
            }

            @Override // com.android.systemui.keyguard.KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener
            public final void onUnlockAnimationStarted(boolean z, boolean z2) {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                NotificationShadeDepthController notificationShadeDepthController2 = notificationPanelViewController.mDepthController;
                boolean isTracking = notificationPanelViewController.isTracking();
                if (notificationShadeDepthController2.blursDisabledForUnlock != isTracking) {
                    notificationShadeDepthController2.blursDisabledForUnlock = isTracking;
                    notificationShadeDepthController2.scheduleUpdate();
                }
                if (!z || z2) {
                    return;
                }
                if (notificationPanelViewController.isTracking() || notificationPanelViewController.mIsFlinging) {
                    notificationPanelViewController.onTrackingStopped(false);
                    notificationPanelViewController.instantCollapse();
                } else {
                    NotificationPanelView notificationPanelView2 = notificationPanelViewController.mView;
                    notificationPanelView2.animate().cancel();
                    notificationPanelView2.postDelayed(new NotificationPanelViewController$$ExternalSyntheticLambda0(notificationPanelViewController, 0), 25L);
                }
            }
        });
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final void animateCollapseQs(boolean z) {
        if (this.mSplitShadeEnabled) {
            collapse(1.0f, true, false);
            return;
        }
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        ValueAnimator valueAnimator = quickSettingsControllerImpl.mExpansionAnimator;
        if (valueAnimator != null) {
            if (!quickSettingsControllerImpl.mAnimatorExpand) {
                return;
            }
            float f = quickSettingsControllerImpl.mExpansionHeight;
            valueAnimator.cancel();
            quickSettingsControllerImpl.setExpansionHeight(f);
        }
        quickSettingsControllerImpl.flingQs(0.0f, z ? 2 : 1, null, false);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void blockExpansionForCurrentTouch() {
        this.mBlockingExpansionForCurrentTouch = isTracking();
    }

    public final int calculatePanelHeightShade() {
        int i = this.mBarState;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        if (i == 1 && this.mKeyguardBypassController.getBypassEnabled()) {
            return notificationStackScrollLayoutController.mView.getHeight();
        }
        int height = notificationStackScrollLayoutController.mView.getHeight() - notificationStackScrollLayoutController.mView.getEmptyBottomMarginInternal();
        return this.mBarState == 1 ? Math.max(height, this.mClockPositionAlgorithm.mKeyguardStatusHeight + ((int) notificationStackScrollLayoutController.mView.mIntrinsicContentHeight)) : height;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final boolean canBeCollapsed() {
        return (isFullyCollapsed() || isTracking() || isClosing()) ? false : true;
    }

    public boolean canCollapsePanelOnTouch() {
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (!quickSettingsControllerImpl.getExpanded() && this.mBarState == 1) {
            return true;
        }
        NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayout.this;
        if (notificationStackScrollLayout.mOwnScrollY >= notificationStackScrollLayout.getScrollRange()) {
            return true;
        }
        return !this.mSplitShadeEnabled && (quickSettingsControllerImpl.getExpanded() || this.mIsPanelCollapseOnQQS);
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void cancelAnimation() {
        this.mView.animate().cancel();
    }

    public void cancelHeightAnimator() {
        ValueAnimator valueAnimator = this.mHeightAnimator;
        if (valueAnimator != null) {
            if (valueAnimator.isRunning()) {
                this.mPanelUpdateWhenAnimatorEnds = false;
            }
            this.mHeightAnimator.cancel();
        }
        endClosing();
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void cancelInputFocusTransfer() {
        if (this.mCommandQueue.panelsEnabled() && this.mExpectingSynthesizedDown) {
            this.mExpectingSynthesizedDown = false;
            collapse(1.0f, false);
            onTrackingStopped(false);
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void cancelPendingCollapse() {
        this.mView.removeCallbacks(this.mMaybeHideExpandedRunnable);
    }

    public final void closeQsIfPossible() {
        boolean z = isShadeFullyExpanded() || isExpandingOrCollapsing();
        if (this.mSplitShadeEnabled && z) {
            return;
        }
        this.mQsController.closeQs();
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final boolean closeUserSwitcherIfOpen() {
        KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
        if (keyguardUserSwitcherController != null) {
            return keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(true);
        }
        return false;
    }

    public final void collapse(float f, boolean z, boolean z2) {
        if (z && !isFullyCollapsed()) {
            collapse(f, z2);
            return;
        }
        resetViews(false);
        setExpandedFraction(0.0f);
        ShadeExpansionStateManager shadeExpansionStateManager = this.mShadeExpansionStateManager;
        ShadeExpansionStateManagerKt.panelStateToString(shadeExpansionStateManager.state);
        ShadeExpansionStateManagerKt.panelStateToString(0);
        if (shadeExpansionStateManager.state != 0) {
            shadeExpansionStateManager.updateStateInternal(0);
        }
    }

    public int computeMaxKeyguardNotifications() {
        if (this.mAmbientState.mFractionToShade > 0.0f) {
            return this.mMaxAllowedKeyguardNotifications;
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        return this.mNotificationStackSizeCalculator.computeMaxKeyguardNotifications(notificationStackScrollLayoutController.mView, getVerticalSpaceForLockscreenNotifications(), getVerticalSpaceForLockscreenShelf(), notificationStackScrollLayoutController.mView.mShelf == null ? 0 : r0.getHeight());
    }

    public final String determineAccessibilityPaneTitle() {
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        return (quickSettingsControllerImpl == null || !quickSettingsControllerImpl.isCustomizing()) ? (quickSettingsControllerImpl == null || quickSettingsControllerImpl.mExpansionHeight == 0.0f || !quickSettingsControllerImpl.mFullyExpanded) ? this.mBarState == 1 ? this.mResources.getString(R.string.accessibility_desc_lock_screen) : this.mResources.getString(R.string.accessibility_desc_notification_shade) : this.mSplitShadeEnabled ? this.mResources.getString(R.string.accessibility_desc_qs_notification_shade) : this.mResources.getString(R.string.accessibility_desc_quick_settings) : this.mResources.getString(R.string.accessibility_desc_quick_settings_edit);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void dozeTimeTick() {
        this.mLockIconViewController.dozeTimeTick();
        if (this.mInterpolatedDarkAmount > 0.0f) {
            positionClockAndNotifications(false);
        }
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("NotificationPanelView:");
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        asIndenting.print("mDownTime=");
        asIndenting.println(this.mDownTime);
        asIndenting.print("mTouchSlopExceededBeforeDown=");
        asIndenting.println(this.mTouchSlopExceededBeforeDown);
        asIndenting.print("mIsLaunchAnimationRunning=");
        asIndenting.println(isLaunchingActivity$1());
        asIndenting.print("mOverExpansion=");
        asIndenting.println(this.mOverExpansion);
        asIndenting.print("mExpandedHeight=");
        asIndenting.println(this.mExpandedHeight);
        asIndenting.print("isTracking()=");
        asIndenting.println(isTracking());
        asIndenting.print("mExpanding=");
        asIndenting.println(this.mExpanding);
        asIndenting.print("mSplitShadeEnabled=");
        asIndenting.println(this.mSplitShadeEnabled);
        asIndenting.print("mKeyguardNotificationBottomPadding=");
        asIndenting.println(this.mKeyguardNotificationBottomPadding);
        asIndenting.print("mKeyguardNotificationTopPadding=");
        asIndenting.println(this.mKeyguardNotificationTopPadding);
        asIndenting.print("mMaxAllowedKeyguardNotifications=");
        asIndenting.println(this.mMaxAllowedKeyguardNotifications);
        asIndenting.print("mAnimateNextPositionUpdate=");
        asIndenting.println(this.mAnimateNextPositionUpdate);
        asIndenting.print("isPanelExpanded()=");
        asIndenting.println(isPanelExpanded());
        asIndenting.print("mKeyguardQsUserSwitchEnabled=");
        asIndenting.println(this.mKeyguardQsUserSwitchEnabled);
        asIndenting.print("mKeyguardUserSwitcherEnabled=");
        asIndenting.println(this.mKeyguardUserSwitcherEnabled);
        asIndenting.print("mDozing=");
        asIndenting.println(this.mDozing);
        asIndenting.print("mDozingOnDown=");
        asIndenting.println(this.mDozingOnDown);
        asIndenting.print("mBouncerShowing=");
        asIndenting.println(this.mBouncerShowing);
        asIndenting.print("mBarState=");
        asIndenting.println(this.mBarState);
        asIndenting.print("mStatusBarMinHeight=");
        asIndenting.println(this.mStatusBarMinHeight);
        asIndenting.print("mStatusBarHeaderHeightKeyguard=");
        asIndenting.println(this.mStatusBarHeaderHeightKeyguard);
        asIndenting.print("mOverStretchAmount=");
        asIndenting.println(this.mOverStretchAmount);
        asIndenting.print("mDownX=");
        asIndenting.println(this.mDownX);
        asIndenting.print("mDownY=");
        asIndenting.println(this.mDownY);
        asIndenting.print("mDisplayTopInset=");
        asIndenting.println(this.mDisplayTopInset);
        asIndenting.print("mDisplayRightInset=");
        asIndenting.println(this.mDisplayRightInset);
        asIndenting.print("mDisplayLeftInset=");
        asIndenting.println(this.mDisplayLeftInset);
        asIndenting.print("mIsExpandingOrCollapsing=");
        asIndenting.println(this.mIsExpandingOrCollapsing);
        asIndenting.print("mHeadsUpStartHeight=");
        asIndenting.println(this.mHeadsUpStartHeight);
        asIndenting.print("mListenForHeadsUp=");
        asIndenting.println(this.mListenForHeadsUp);
        asIndenting.print("mNavigationBarBottomHeight=");
        asIndenting.println(this.mNavigationBarBottomHeight);
        asIndenting.print("mExpandingFromHeadsUp=");
        asIndenting.println(this.mExpandingFromHeadsUp);
        asIndenting.print("mCollapsedOnDown=");
        asIndenting.println(this.mCollapsedOnDown);
        asIndenting.print("mClosingWithAlphaFadeOut=");
        asIndenting.println(this.mClosingWithAlphaFadeOut);
        asIndenting.print("mHeadsUpAnimatingAway=");
        asIndenting.println(this.mHeadsUpAnimatingAway);
        asIndenting.print("mShowIconsWhenExpanded=");
        asIndenting.println(this.mShowIconsWhenExpanded);
        asIndenting.print("mIndicationBottomPadding=");
        asIndenting.println(this.mIndicationBottomPadding);
        asIndenting.print("mAmbientIndicationBottomPadding=");
        asIndenting.println(this.mAmbientIndicationBottomPadding);
        asIndenting.print("mIsFullWidth=");
        asIndenting.println(this.mIsFullWidth);
        asIndenting.print("mBlockingExpansionForCurrentTouch=");
        asIndenting.println(this.mBlockingExpansionForCurrentTouch);
        asIndenting.print("mExpectingSynthesizedDown=");
        asIndenting.println(this.mExpectingSynthesizedDown);
        asIndenting.print("mLastEventSynthesizedDown=");
        asIndenting.println(this.mLastEventSynthesizedDown);
        asIndenting.print("mInterpolatedDarkAmount=");
        asIndenting.println(this.mInterpolatedDarkAmount);
        asIndenting.print("mLinearDarkAmount=");
        asIndenting.println(this.mLinearDarkAmount);
        asIndenting.print("mPulsing=");
        asIndenting.println(this.mPulsing);
        asIndenting.print("mStackScrollerMeasuringPass=");
        asIndenting.println(this.mStackScrollerMeasuringPass);
        asIndenting.print("mPanelAlpha=");
        asIndenting.println(this.mPanelAlpha);
        asIndenting.print("mBottomAreaShadeAlpha=");
        asIndenting.println(this.mBottomAreaShadeAlpha);
        asIndenting.print("mHeadsUpInset=");
        asIndenting.println(this.mHeadsUpInset);
        asIndenting.print("mHeadsUpPinnedMode=");
        asIndenting.println(this.mHeadsUpPinnedMode);
        asIndenting.print("mAllowExpandForSmallExpansion=");
        asIndenting.println(this.mAllowExpandForSmallExpansion);
        asIndenting.print("mMaxOverscrollAmountForPulse=");
        asIndenting.println(this.mMaxOverscrollAmountForPulse);
        asIndenting.print("mIsPanelCollapseOnQQS=");
        asIndenting.println(this.mIsPanelCollapseOnQQS);
        asIndenting.print("mKeyguardOnlyContentAlpha=");
        asIndenting.println(this.mKeyguardOnlyContentAlpha);
        asIndenting.print("mKeyguardOnlyTransitionTranslationY=");
        asIndenting.println(this.mKeyguardOnlyTransitionTranslationY);
        asIndenting.print("mUdfpsMaxYBurnInOffset=");
        asIndenting.println(this.mUdfpsMaxYBurnInOffset);
        asIndenting.print("mIsGestureNavigation=");
        asIndenting.println(this.mIsGestureNavigation);
        asIndenting.print("mOldLayoutDirection=");
        asIndenting.println(this.mOldLayoutDirection);
        asIndenting.print("mMinFraction=");
        asIndenting.println(this.mMinFraction);
        asIndenting.print("mSplitShadeFullTransitionDistance=");
        asIndenting.println(this.mSplitShadeFullTransitionDistance);
        asIndenting.print("mSplitShadeScrimTransitionDistance=");
        asIndenting.println(this.mSplitShadeScrimTransitionDistance);
        asIndenting.print("mMinExpandHeight=");
        asIndenting.println(0.0f);
        asIndenting.print("mPanelUpdateWhenAnimatorEnds=");
        asIndenting.println(this.mPanelUpdateWhenAnimatorEnds);
        asIndenting.print("mHasVibratedOnOpen=");
        asIndenting.println(this.mHasVibratedOnOpen);
        asIndenting.print("mFixedDuration=");
        asIndenting.println(this.mFixedDuration);
        asIndenting.print("mPanelFlingOvershootAmount=");
        asIndenting.println(this.mPanelFlingOvershootAmount);
        asIndenting.print("mLastGesturedOverExpansion=");
        asIndenting.println(this.mLastGesturedOverExpansion);
        asIndenting.print("mIsSpringBackAnimation=");
        asIndenting.println(this.mIsSpringBackAnimation);
        asIndenting.print("mHintDistance=");
        asIndenting.println(this.mHintDistance);
        asIndenting.print("mInitialOffsetOnTouch=");
        asIndenting.println(this.mInitialOffsetOnTouch);
        asIndenting.print("mCollapsedAndHeadsUpOnDown=");
        asIndenting.println(this.mCollapsedAndHeadsUpOnDown);
        asIndenting.print("mExpandedFraction=");
        asIndenting.println(this.mExpandedFraction);
        asIndenting.print("mExpansionDragDownAmountPx=");
        asIndenting.println(this.mExpansionDragDownAmountPx);
        asIndenting.print("mPanelClosedOnDown=");
        asIndenting.println(this.mPanelClosedOnDown);
        asIndenting.print("mHasLayoutedSinceDown=");
        asIndenting.println(this.mHasLayoutedSinceDown);
        asIndenting.print("mUpdateFlingVelocity=");
        asIndenting.println(this.mUpdateFlingVelocity);
        asIndenting.print("mUpdateFlingOnLayout=");
        asIndenting.println(this.mUpdateFlingOnLayout);
        asIndenting.print("isClosing()=");
        asIndenting.println(isClosing());
        asIndenting.print("mTouchSlopExceeded=");
        asIndenting.println(this.mTouchSlopExceeded);
        asIndenting.print("mTrackingPointer=");
        asIndenting.println(this.mTrackingPointer);
        asIndenting.print("mTouchSlop=");
        asIndenting.println(this.mTouchSlop);
        asIndenting.print("mSlopMultiplier=");
        asIndenting.println(this.mSlopMultiplier);
        asIndenting.print("mTouchAboveFalsingThreshold=");
        asIndenting.println(this.mTouchAboveFalsingThreshold);
        asIndenting.print("mTouchStartedInEmptyArea=");
        asIndenting.println(this.mTouchStartedInEmptyArea);
        asIndenting.print("mMotionAborted=");
        asIndenting.println(this.mMotionAborted);
        asIndenting.print("mUpwardsWhenThresholdReached=");
        asIndenting.println(this.mUpwardsWhenThresholdReached);
        asIndenting.print("mAnimatingOnDown=");
        asIndenting.println(this.mAnimatingOnDown);
        asIndenting.print("mHandlingPointerUp=");
        asIndenting.println(this.mHandlingPointerUp);
        asIndenting.print("mInstantExpanding=");
        asIndenting.println(this.mInstantExpanding);
        asIndenting.print("mAnimateAfterExpanding=");
        asIndenting.println(this.mAnimateAfterExpanding);
        asIndenting.print("mIsFlinging=");
        asIndenting.println(this.mIsFlinging);
        asIndenting.print("mViewName=");
        asIndenting.println(this.mViewName);
        asIndenting.print("mInitialExpandY=");
        asIndenting.println(this.mInitialExpandY);
        asIndenting.print("mInitialExpandX=");
        asIndenting.println(this.mInitialExpandX);
        asIndenting.print("mTouchDisabled=");
        asIndenting.println(this.mTouchDisabled);
        asIndenting.print("mInitialTouchFromKeyguard=");
        asIndenting.println(this.mInitialTouchFromKeyguard);
        asIndenting.print("mNextCollapseSpeedUpFactor=");
        asIndenting.println(this.mNextCollapseSpeedUpFactor);
        asIndenting.print("mGestureWaitForTouchSlop=");
        asIndenting.println(this.mGestureWaitForTouchSlop);
        asIndenting.print("mIgnoreXTouchSlop=");
        asIndenting.println(this.mIgnoreXTouchSlop);
        asIndenting.print("mExpandLatencyTracking=");
        asIndenting.println(this.mExpandLatencyTracking);
        StringBuilder sb = new StringBuilder("gestureExclusionRect:");
        Region calculateTouchableRegion = this.mStatusBarTouchableRegionManager.calculateTouchableRegion();
        Rect bounds = (!isFullyCollapsed() || calculateTouchableRegion == null) ? null : calculateTouchableRegion.getBounds();
        if (bounds == null) {
            bounds = EMPTY_RECT;
        }
        sb.append(bounds);
        asIndenting.println(sb.toString());
        Trace.beginSection("Table<DownEvents>");
        List list = NPVCDownEventState.TABLE_HEADERS;
        NPVCDownEventState.Buffer buffer = this.mLastDownEvents;
        buffer.getClass();
        RingBuffer ringBuffer = buffer.buffer;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(ringBuffer, 10));
        int i = 0;
        while (true) {
            if (!(i < ringBuffer.getSize())) {
                new DumpsysTableLogger("NotificationPanelView", list, arrayList).printTableData(asIndenting);
                Trace.endSection();
                return;
            } else {
                if (i >= ringBuffer.getSize()) {
                    throw new NoSuchElementException();
                }
                Object obj = ringBuffer.get(i);
                i++;
                arrayList.add((List) ((NPVCDownEventState) obj).asStringList$delegate.getValue());
            }
        }
    }

    public final void endClosing() {
        if (isClosing()) {
            setClosing(false);
            ShadeControllerImpl.AnonymousClass2 anonymousClass2 = this.mOpenCloseListener;
            if (anonymousClass2 != null) {
                ShadeControllerImpl.this.onClosingFinished();
            }
            this.mClosingWithAlphaFadeOut = false;
            this.mNotificationStackScrollLayoutController.mView.mForceNoOverlappingRendering = false;
            this.mMediaHierarchyManager.getClass();
            MediaCarouselController.closeGuts(true);
        }
    }

    public final void expand(boolean z) {
        if (isFullyCollapsed() || isCollapsing()) {
            this.mInstantExpanding = true;
            this.mAnimateAfterExpanding = z;
            this.mUpdateFlingOnLayout = false;
            cancelHeightAnimator();
            NotificationPanelViewController$$ExternalSyntheticLambda0 notificationPanelViewController$$ExternalSyntheticLambda0 = this.mFlingCollapseRunnable;
            NotificationPanelView notificationPanelView = this.mView;
            notificationPanelView.removeCallbacks(notificationPanelViewController$$ExternalSyntheticLambda0);
            if (isTracking()) {
                onTrackingStopped(true);
            }
            if (this.mExpanding) {
                notifyExpandingFinished();
            }
            updateExpansionAndVisibility();
            notificationPanelView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.shade.NotificationPanelViewController.7
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                    if (!notificationPanelViewController.mInstantExpanding) {
                        notificationPanelViewController.mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        return;
                    }
                    if (((NotificationShadeWindowControllerImpl) notificationPanelViewController.mNotificationShadeWindowController).mWindowRootView.isVisibleToUser()) {
                        NotificationPanelViewController.this.mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        NotificationPanelViewController notificationPanelViewController2 = NotificationPanelViewController.this;
                        if (notificationPanelViewController2.mAnimateAfterExpanding) {
                            notificationPanelViewController2.notifyExpandingStarted();
                            NotificationPanelViewController notificationPanelViewController3 = NotificationPanelViewController.this;
                            notificationPanelViewController3.mQsController.beginJankMonitoring(notificationPanelViewController3.isFullyCollapsed());
                            NotificationPanelViewController.this.fling(0.0f, 1.0f, true, false);
                        } else {
                            notificationPanelViewController2.setExpandedFraction(1.0f);
                        }
                        NotificationPanelViewController.this.mInstantExpanding = false;
                    }
                }
            });
            notificationPanelView.requestLayout();
        }
        setListening$1(true);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void expandToNotifications() {
        if (this.mSplitShadeEnabled && (isShadeFullyExpanded() || isExpandingOrCollapsing())) {
            return;
        }
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (quickSettingsControllerImpl.getExpanded()) {
            quickSettingsControllerImpl.flingQs(0.0f, 1, null, false);
        } else {
            expand(true);
        }
    }

    public final void expandToQs() {
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (quickSettingsControllerImpl.isExpansionEnabled()) {
            quickSettingsControllerImpl.setExpandImmediate(true);
            setShowShelfOnly(true);
        }
        if (this.mSplitShadeEnabled && isKeyguardShowing$1()) {
            this.mLockscreenShadeTransitionController.goToLockedShade(null, true);
        } else if (isFullyCollapsed()) {
            expand(true);
        } else {
            quickSettingsControllerImpl.traceQsJank(true, false);
            quickSettingsControllerImpl.flingQs(0.0f, 0, null, false);
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void fadeOut(CentralSurfacesImpl$$ExternalSyntheticLambda1 centralSurfacesImpl$$ExternalSyntheticLambda1) {
        NotificationPanelView notificationPanelView = this.mView;
        notificationPanelView.animate().cancel();
        notificationPanelView.animate().alpha(0.0f).setStartDelay(100L).setDuration(300L).setInterpolator(Interpolators.ALPHA_OUT).withLayer().withEndAction(centralSurfacesImpl$$ExternalSyntheticLambda1);
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void finishInputFocusTransfer(float f) {
        if (this.mCommandQueue.panelsEnabled() && this.mExpectingSynthesizedDown) {
            maybeVibrateOnOpening(false);
            fling(f > 1.0f ? f * 1000.0f : 0.0f, 1.0f, true, false);
            onTrackingStopped(false);
        }
    }

    public final void fling(float f, float f2, boolean z, boolean z2) {
        float maxPanelTransitionDistance = z ? getMaxPanelTransitionDistance() : 0.0f;
        if (!z) {
            setClosing(true);
        }
        flingToHeight(f, z, maxPanelTransitionDistance, f2, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void flingToHeight(float r22, boolean r23, final float r24, float r25, boolean r26) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationPanelViewController.flingToHeight(float, boolean, float, float, boolean):void");
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final int getBarState() {
        return this.mBarState;
    }

    public final int getMaxPanelHeight() {
        int i = this.mStatusBarMinHeight;
        int i2 = this.mBarState;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (i2 != 1 && this.mNotificationStackScrollLayoutController.getNotGoneChildCount() == 0) {
            i = Math.max(i, quickSettingsControllerImpl.mMinExpansionHeight);
        }
        boolean isExpandImmediate = quickSettingsControllerImpl.isExpandImmediate();
        KeyguardClockPositionAlgorithm.Result result = this.mClockPositionResult;
        int max = Math.max(i, (isExpandImmediate || quickSettingsControllerImpl.getExpanded() || (this.mIsExpandingOrCollapsing && quickSettingsControllerImpl.mExpandedWhenExpandingStarted) || this.mPulsing || this.mSplitShadeEnabled) ? quickSettingsControllerImpl.calculatePanelHeightExpanded(result.stackScrollerPadding) : calculatePanelHeightShade());
        if (max == 0) {
            Log.wtf("NotificationPanelView", "maxPanelHeight is invalid. mOverExpansion: " + this.mOverExpansion + ", calculatePanelHeightQsExpanded: " + quickSettingsControllerImpl.calculatePanelHeightExpanded(result.stackScrollerPadding) + ", calculatePanelHeightShade: " + calculatePanelHeightShade() + ", mStatusBarMinHeight = " + this.mStatusBarMinHeight + ", mQsMinExpansionHeight = " + quickSettingsControllerImpl.mMinExpansionHeight);
        }
        return max;
    }

    public int getMaxPanelTransitionDistance() {
        if (!this.mSplitShadeEnabled || this.mBarState != 0) {
            return getMaxPanelHeight();
        }
        HeadsUpManager headsUpManager = this.mHeadsUpManager;
        if ((headsUpManager == null || !((HeadsUpManagerPhone) headsUpManager).mTrackingHeadsUp) && !this.mExpandingFromHeadsUp) {
            return this.mSplitShadeFullTransitionDistance;
        }
        return (int) Math.min(getMaxPanelHeight(), Math.max(this.mSplitShadeFullTransitionDistance, this.mHeadsUpStartHeight * 2.5d));
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final ShadeFoldAnimator getShadeFoldAnimator$1() {
        return this.mShadeFoldAnimator;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final ShadeHeadsUpTracker getShadeHeadsUpTracker$1() {
        return this.mShadeHeadsUpTracker;
    }

    public StatusBarStateController getStatusBarStateController() {
        return this.mStatusBarStateController;
    }

    public StatusBarStateController.StateListener getStatusBarStateListener() {
        return this.mStatusBarStateListener;
    }

    public TouchHandler getTouchHandler() {
        return this.mTouchHandler;
    }

    public float getVerticalSpaceForLockscreenNotifications() {
        float f;
        float f2;
        View findViewById = this.mKeyguardViewConfigurator.keyguardRootView.findViewById(R.id.device_entry_icon_view);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        float max = Math.max(findViewById != null ? notificationStackScrollLayoutController.mView.getBottom() - findViewById.getTop() : 0.0f, Math.max(this.mIndicationBottomPadding, this.mAmbientIndicationBottomPadding));
        this.mKeyguardNotificationBottomPadding = max;
        KeyguardClockPositionAlgorithm keyguardClockPositionAlgorithm = this.mClockPositionAlgorithm;
        float top = notificationStackScrollLayoutController.mView.getTop();
        if (keyguardClockPositionAlgorithm.mBypassEnabled) {
            f2 = keyguardClockPositionAlgorithm.mUnlockedStackScrollerPadding;
        } else {
            if (!keyguardClockPositionAlgorithm.mIsSplitShade) {
                f = keyguardClockPositionAlgorithm.mMinTopMargin + keyguardClockPositionAlgorithm.mKeyguardStatusHeight;
                this.mKeyguardNotificationTopPadding = f;
                return (notificationStackScrollLayoutController.mView.getHeight() - f) - max;
            }
            f2 = keyguardClockPositionAlgorithm.mSplitShadeTargetTopMargin + keyguardClockPositionAlgorithm.mUserSwitchHeight;
        }
        f = f2 - top;
        this.mKeyguardNotificationTopPadding = f;
        return (notificationStackScrollLayoutController.mView.getHeight() - f) - max;
    }

    public float getVerticalSpaceForLockscreenShelf() {
        if (this.mSplitShadeEnabled) {
            return 0.0f;
        }
        View findViewById = this.mKeyguardViewConfigurator.keyguardRootView.findViewById(R.id.device_entry_icon_view);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        float bottom = (findViewById != null ? notificationStackScrollLayoutController.mView.getBottom() - findViewById.getTop() : 0.0f) - Math.max(this.mIndicationBottomPadding, this.mAmbientIndicationBottomPadding);
        if (bottom <= 0.0f) {
            return 0.0f;
        }
        return Math.min(notificationStackScrollLayoutController.mView.mShelf == null ? 0 : r4.getHeight(), bottom);
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean handleExternalInterceptTouch(MotionEvent motionEvent) {
        try {
            this.mUseExternalTouch = true;
            return this.mTouchHandler.onInterceptTouchEvent(motionEvent);
        } finally {
            this.mUseExternalTouch = false;
        }
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean handleExternalTouch(MotionEvent motionEvent) {
        try {
            this.mUseExternalTouch = true;
            return this.mTouchHandler.onTouchEvent(motionEvent);
        } finally {
            this.mUseExternalTouch = false;
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void initDependencies(CentralSurfacesImpl centralSurfacesImpl, CentralSurfacesImpl$$ExternalSyntheticLambda25 centralSurfacesImpl$$ExternalSyntheticLambda25, HeadsUpManager headsUpManager) {
        this.mHeadsUpManager = headsUpManager;
        ((BaseHeadsUpManager) headsUpManager).addListener(this.mOnHeadsUpChangedListener);
        this.mHeadsUpTouchHelper = new HeadsUpTouchHelper(headsUpManager, this.mStatusBarService, this.mNotificationStackScrollLayoutController.mView.mHeadsUpCallback, new AnonymousClass10());
        this.mCentralSurfaces = centralSurfacesImpl;
        this.mHideExpandedRunnable = centralSurfacesImpl$$ExternalSyntheticLambda25;
    }

    public final void instantCollapse() {
        cancelHeightAnimator();
        this.mView.removeCallbacks(this.mFlingCollapseRunnable);
        setExpandedFraction(0.0f);
        if (this.mExpanding) {
            notifyExpandingFinished();
        }
        if (this.mInstantExpanding) {
            this.mInstantExpanding = false;
            updateExpansionAndVisibility();
        }
    }

    public boolean isClosing() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyIsClosing.$$delegate_0).getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isCollapsing() {
        return isClosing() || isLaunchingActivity$1();
    }

    public final boolean isDirectionUpwards(float f, float f2) {
        float f3 = f - this.mInitialExpandX;
        float f4 = (f2 - this.mInitialExpandY) * (this.mIsTrackpadReverseScroll ? -1 : 1);
        return f4 < 0.0f && Math.abs(f4) >= Math.abs(f3);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final boolean isExpanded() {
        return this.mExpandedFraction > 0.0f || this.mInstantExpanding || ((((BaseHeadsUpManager) this.mHeadsUpManager).mHasPinnedNotification || this.mHeadsUpAnimatingAway) && this.mBarState == 0) || isTracking() || this.mHeightAnimator != null || (this.mUnlockedScreenOffAnimationController.lightRevealAnimationPlaying && !this.mIsSpringBackAnimation);
    }

    public final boolean isExpandingOrCollapsing() {
        float lockscreenShadeDragProgress = this.mQsController.getLockscreenShadeDragProgress();
        return this.mIsExpandingOrCollapsing || (0.0f < lockscreenShadeDragProgress && lockscreenShadeDragProgress < 1.0f);
    }

    public final boolean isFalseTouch(int i, float f, float f2) {
        FalsingManager falsingManager = this.mFalsingManager;
        if (falsingManager.isClassifierEnabled()) {
            return falsingManager.isFalseTouch(i);
        }
        if (!this.mTouchAboveFalsingThreshold) {
            return true;
        }
        if (this.mUpwardsWhenThresholdReached) {
            return false;
        }
        return !isDirectionUpwards(f, f2);
    }

    public boolean isFlinging() {
        return this.mIsFlinging;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isFullyCollapsed() {
        return this.mExpandedFraction <= 0.0f;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isFullyExpanded() {
        return this.mExpandedHeight >= ((float) getMaxPanelTransitionDistance());
    }

    public final boolean isKeyguardShowing$1() {
        return this.mBarState == 1;
    }

    public final boolean isLaunchingActivity$1() {
        return ((Boolean) ((StateFlowImpl) this.mShadeAnimationInteractor.isLaunchingActivity.$$delegate_0).getValue()).booleanValue();
    }

    public final boolean isOnAod() {
        return this.mDozing && this.mDozeParameters.getAlwaysOn();
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isPanelExpanded() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyExpandedOrAwaitingInputTransfer.$$delegate_0).getValue()).booleanValue();
    }

    public final boolean isShadeFullyExpanded() {
        int i = this.mBarState;
        return i == 0 ? isFullyExpanded() : i == 2 || this.mQsController.computeExpansionFraction() == 1.0f;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean isTracking() {
        return ((Boolean) ((StateFlowImpl) ((ShadeRepositoryImpl) this.mShadeRepository).legacyShadeTracking.$$delegate_0).getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final boolean isViewEnabled() {
        return this.mView.isEnabled();
    }

    public void loadDimens() {
        NotificationPanelView notificationPanelView = this.mView;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(notificationPanelView.getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mSlopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
        this.mHintDistance = this.mResources.getDimension(R.dimen.hint_move_distance);
        this.mPanelFlingOvershootAmount = this.mResources.getDimension(R.dimen.panel_overshoot_amount);
        FlingAnimationUtils.Builder builder = (FlingAnimationUtils.Builder) this.mFlingAnimationUtilsBuilder.get();
        builder.mMaxLengthSeconds = 0.4f;
        this.mFlingAnimationUtils = builder.build();
        this.mStatusBarMinHeight = SystemBarUtils.getStatusBarHeight(notificationPanelView.getContext());
        this.mStatusBarHeaderHeightKeyguard = Utils.getStatusBarHeaderHeightKeyguard(notificationPanelView.getContext());
        KeyguardClockPositionAlgorithm keyguardClockPositionAlgorithm = this.mClockPositionAlgorithm;
        Context context = notificationPanelView.getContext();
        Resources resources = this.mResources;
        keyguardClockPositionAlgorithm.getClass();
        keyguardClockPositionAlgorithm.mStatusViewBottomMargin = resources.getDimensionPixelSize(R.dimen.keyguard_status_view_bottom_margin);
        keyguardClockPositionAlgorithm.mSplitShadeTopNotificationsMargin = Math.max(context.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_height), SystemBarUtils.getStatusBarHeight(context));
        keyguardClockPositionAlgorithm.mSplitShadeTargetTopMargin = resources.getDimensionPixelSize(R.dimen.keyguard_split_shade_top_margin);
        keyguardClockPositionAlgorithm.mContainerTopPadding = resources.getDimensionPixelSize(R.dimen.keyguard_clock_top_margin);
        keyguardClockPositionAlgorithm.mMaxBurnInPreventionOffsetX = resources.getDimensionPixelSize(R.dimen.burn_in_prevention_offset_x);
        keyguardClockPositionAlgorithm.mMaxBurnInPreventionOffsetYClock = resources.getDimensionPixelSize(R.dimen.burn_in_prevention_offset_y_clock);
        this.mIndicationBottomPadding = this.mResources.getDimensionPixelSize(R.dimen.keyguard_indication_bottom_padding);
        this.mHeadsUpInset = this.mResources.getDimensionPixelSize(R.dimen.heads_up_status_bar_padding) + SystemBarUtils.getStatusBarHeight(notificationPanelView.getContext());
        this.mMaxOverscrollAmountForPulse = this.mResources.getDimensionPixelSize(R.dimen.pulse_expansion_max_top_overshoot);
        this.mUdfpsMaxYBurnInOffset = this.mResources.getDimensionPixelSize(R.dimen.udfps_burn_in_offset_y);
        this.mSplitShadeScrimTransitionDistance = this.mResources.getDimensionPixelSize(R.dimen.split_shade_scrim_transition_distance);
        this.mDreamingToLockscreenTransitionTranslationY = this.mResources.getDimensionPixelSize(R.dimen.dreaming_to_lockscreen_transition_lockscreen_translation_y);
        this.mLockscreenToDreamingTransitionTranslationY = this.mResources.getDimensionPixelSize(R.dimen.lockscreen_to_dreaming_transition_lockscreen_translation_y);
        this.mGoneToDreamingTransitionTranslationY = this.mResources.getDimensionPixelSize(R.dimen.gone_to_dreaming_transition_lockscreen_translation_y);
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        NotificationPanelView notificationPanelView2 = quickSettingsControllerImpl.mPanelView;
        ViewConfiguration viewConfiguration2 = ViewConfiguration.get(notificationPanelView2.getContext());
        quickSettingsControllerImpl.mTouchSlop = viewConfiguration2.getScaledTouchSlop();
        quickSettingsControllerImpl.mSlopMultiplier = viewConfiguration2.getScaledAmbiguousGestureMultiplier();
        quickSettingsControllerImpl.mStatusBarMinHeight = SystemBarUtils.getStatusBarHeight(notificationPanelView2.getContext());
        quickSettingsControllerImpl.mScrimCornerRadius = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.notification_scrim_corner_radius);
        quickSettingsControllerImpl.mScreenCornerRadius = (int) ScreenDecorationsUtils.getWindowCornerRadius(notificationPanelView2.getContext());
        quickSettingsControllerImpl.mFalsingThreshold = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.qs_falsing_threshold);
        quickSettingsControllerImpl.mLockscreenNotificationPadding = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.notification_side_paddings);
        quickSettingsControllerImpl.mDistanceForFullShadeTransition = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.lockscreen_shade_qs_transition_distance);
    }

    public void maybeAnimateBottomAreaAlpha() {
        this.mBottomAreaShadeAlphaAnimator.cancel();
        if (this.mBarState != 2) {
            this.mBottomAreaShadeAlpha = 1.0f;
        } else {
            this.mBottomAreaShadeAlphaAnimator.setFloatValues(this.mBottomAreaShadeAlpha, 0.0f);
            this.mBottomAreaShadeAlphaAnimator.start();
        }
    }

    public final void maybeVibrateOnOpening(boolean z) {
        int i;
        if (!this.mVibrateOnOpening || (i = this.mBarState) == 1 || i == 2) {
            return;
        }
        if (z && this.mHasVibratedOnOpen) {
            return;
        }
        this.mVibratorHelper.getClass();
        this.mView.performHapticFeedback(12);
        this.mHasVibratedOnOpen = true;
        this.mShadeLog.v("Vibrating on opening, mHasVibratedOnOpen=true");
    }

    public final void notifyExpandingFinished() {
        endClosing();
        if (this.mExpanding) {
            this.mExpanding = false;
            NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
            notificationStackScrollLayout.mCheckForLeavebehind = false;
            notificationStackScrollLayout.mIsExpansionChanging = false;
            notificationStackScrollLayout.mAmbientState.mExpansionChanging = false;
            if (!notificationStackScrollLayout.mIsExpanded) {
                notificationStackScrollLayout.mScroller.abortAnimation();
                notificationStackScrollLayout.setOwnScrollY(0);
                notificationStackScrollLayout.mResetUserExpandedStatesRunnable.run();
                notificationStackScrollLayout.clearTemporaryViewsInGroup(notificationStackScrollLayout, "clearTemporaryViews");
                for (int i = 0; i < notificationStackScrollLayout.getChildCount(); i++) {
                    ExpandableView expandableView = (ExpandableView) notificationStackScrollLayout.getChildAt(i);
                    if (expandableView instanceof ExpandableNotificationRow) {
                        notificationStackScrollLayout.clearTemporaryViewsInGroup(((ExpandableNotificationRow) expandableView).mChildrenContainer, "clearTemporaryViewsInGroup(row.getChildrenContainer())");
                    }
                }
                for (int i2 = 0; i2 < notificationStackScrollLayout.getChildCount(); i2++) {
                    ExpandableView expandableView2 = (ExpandableView) notificationStackScrollLayout.getChildAt(i2);
                    if (expandableView2 instanceof ExpandableNotificationRow) {
                        ((ExpandableNotificationRow) expandableView2).setUserLocked(false);
                    }
                }
                notificationStackScrollLayout.resetAllSwipeState();
            }
            HeadsUpManagerPhone headsUpManagerPhone = (HeadsUpManagerPhone) this.mHeadsUpManager;
            if (headsUpManagerPhone.mReleaseOnExpandFinish) {
                headsUpManagerPhone.releaseAllImmediately();
                headsUpManagerPhone.mReleaseOnExpandFinish = false;
            } else {
                Iterator it = headsUpManagerPhone.getAllEntries().toList().iterator();
                while (it.hasNext()) {
                    ((NotificationEntry) it.next()).getClass();
                }
                Iterator it2 = headsUpManagerPhone.mEntriesToRemoveAfterExpand.iterator();
                while (it2.hasNext()) {
                    NotificationEntry notificationEntry = (NotificationEntry) it2.next();
                    if (headsUpManagerPhone.isHeadsUpEntry(notificationEntry.mKey)) {
                        headsUpManagerPhone.removeEntry(notificationEntry.mKey, "onExpandingFinished");
                    }
                }
            }
            headsUpManagerPhone.mEntriesToRemoveAfterExpand.clear();
            this.mConversationNotificationManager.onNotificationPanelExpandStateChanged(isFullyCollapsed());
            this.mIsExpandingOrCollapsing = false;
            MediaHierarchyManager mediaHierarchyManager = this.mMediaHierarchyManager;
            if (mediaHierarchyManager.collapsingShadeFromQS) {
                mediaHierarchyManager.collapsingShadeFromQS = false;
                MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, true, 2);
            }
            QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
            mediaHierarchyManager.setQsExpanded(quickSettingsControllerImpl.getExpanded());
            if (isFullyCollapsed()) {
                DejankUtils.postAfterTraversal(new NotificationPanelViewController$$ExternalSyntheticLambda0(this, 6));
                this.mView.postOnAnimation(new NotificationPanelViewController$$ExternalSyntheticLambda0(this, 7));
            } else {
                setListening$1(true);
            }
            if (this.mBarState != 0) {
                ShadeLogger shadeLogger = this.mShadeLog;
                shadeLogger.d("onExpandingFinished called");
                if (this.mSplitShadeEnabled && !quickSettingsControllerImpl.getExpanded()) {
                    shadeLogger.d("onExpandingFinished called before QS got expanded");
                }
                quickSettingsControllerImpl.setExpandImmediate(false);
            }
            setShowShelfOnly(false);
            quickSettingsControllerImpl.mTwoFingerExpandPossible = false;
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.mTrackedHeadsUpNotification = null;
            for (int i3 = 0; i3 < notificationPanelViewController.mTrackingHeadsUpListeners.size(); i3++) {
                ((Consumer) notificationPanelViewController.mTrackingHeadsUpListeners.get(i3)).accept(null);
            }
            this.mExpandingFromHeadsUp = false;
            setPanelScrimMinFraction$1(0.0f);
            setKeyguardStatusBarAlpha(-1.0f);
        }
    }

    public void notifyExpandingStarted() {
        if (this.mExpanding) {
            return;
        }
        DejankUtils.notifyRendererOfExpensiveFrame(this.mView, "notifyExpandingStarted");
        this.mExpanding = true;
        this.mIsExpandingOrCollapsing = true;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        boolean z = quickSettingsControllerImpl.mFullyExpanded;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = quickSettingsControllerImpl.mNotificationStackScrollLayoutController;
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mIsExpansionChanging = true;
        notificationStackScrollLayout.mAmbientState.mExpansionChanging = true;
        notificationStackScrollLayoutController.checkSnoozeLeavebehind();
        quickSettingsControllerImpl.mExpandedWhenExpandingStarted = z;
        boolean z2 = z && !quickSettingsControllerImpl.mAnimating;
        MediaHierarchyManager mediaHierarchyManager = quickSettingsControllerImpl.mMediaHierarchyManager;
        if (mediaHierarchyManager.collapsingShadeFromQS != z2) {
            mediaHierarchyManager.collapsingShadeFromQS = z2;
            MediaHierarchyManager.updateDesiredLocation$default(mediaHierarchyManager, true, 2);
        }
        if (quickSettingsControllerImpl.getExpanded()) {
            quickSettingsControllerImpl.onExpansionStarted();
        }
        QS qs = quickSettingsControllerImpl.mQs;
        if (qs == null) {
            return;
        }
        qs.setHeaderListening(true);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor
    public final void onBackPressed() {
        closeQsIfPossible();
    }

    public final void onEmptySpaceClick() {
        int i = this.mBarState;
        if (i != 1) {
            if (i == 2 && !this.mQsController.getExpanded()) {
                SysuiStatusBarStateController sysuiStatusBarStateController = this.mStatusBarStateController;
                sysuiStatusBarStateController.getClass();
                ((StatusBarStateControllerImpl) sysuiStatusBarStateController).setState(1, false);
                return;
            }
            return;
        }
        if (this.mDozingOnDown) {
            return;
        }
        this.mShadeLog.v("onMiddleClicked on Keyguard, mDozingOnDown: false");
        DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor = this.mDeviceEntryFaceAuthInteractor;
        deviceEntryFaceAuthInteractor.onNotificationPanelClicked();
        if (deviceEntryFaceAuthInteractor.canFaceAuthRun()) {
            this.mUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.UNLOCK_INTENT_LEGACY, "lockScreenEmptySpaceTap");
        } else {
            this.mLockscreenGestureLogger.write(188, 0, 0);
            new UiEventLoggerImpl().log(LockscreenGestureLogger.LockscreenUiEvent.LOCKSCREEN_LOCK_SHOW_HINT);
            this.mKeyguardIndicationController.showActionToUnlock();
        }
    }

    public void onFinishInflate() {
        KeyguardUserSwitcherView keyguardUserSwitcherView;
        int i = 3;
        int i2 = 0;
        int i3 = 1;
        loadDimens();
        boolean z = this.mKeyguardUserSwitcherEnabled;
        NotificationPanelView notificationPanelView = this.mView;
        FrameLayout frameLayout = null;
        if (!z || !this.mUserManager.isUserSwitcherEnabled(this.mResources.getBoolean(R.bool.qs_show_user_switcher_for_single_user))) {
            keyguardUserSwitcherView = null;
        } else if (this.mKeyguardQsUserSwitchEnabled) {
            frameLayout = (FrameLayout) ((ViewStub) notificationPanelView.findViewById(R.id.keyguard_qs_user_switch_stub)).inflate();
            keyguardUserSwitcherView = null;
        } else {
            keyguardUserSwitcherView = (KeyguardUserSwitcherView) ((ViewStub) notificationPanelView.findViewById(R.id.keyguard_user_switcher_stub)).inflate();
        }
        KeyguardStatusBarView keyguardStatusBarView = (KeyguardStatusBarView) notificationPanelView.findViewById(R.id.keyguard_header);
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.mKeyguardStatusBarViewComponentFactory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
        keyguardStatusBarView.getClass();
        AnonymousClass10 anonymousClass10 = this.mShadeViewStateProvider;
        anonymousClass10.getClass();
        DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, keyguardStatusBarView, anonymousClass10);
        CarrierText carrierText = (CarrierText) daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.getCarrierTextProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.sysUIGoogleSysUIComponentImpl;
        CarrierTextController carrierTextController = new CarrierTextController(carrierText, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierTextManagerBuilder(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
        ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get();
        SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = (SystemStatusAnimationSchedulerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemStatusAnimationSchedulerProvider.get();
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        UserInfoControllerImpl userInfoControllerImpl = (UserInfoControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userInfoControllerImplProvider.get();
        StatusBarIconController statusBarIconController = (StatusBarIconController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconControllerImplProvider.get();
        TintedIconManager.Factory factory = (TintedIconManager.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider27.get();
        BatteryMeterView batteryMeterView = (BatteryMeterView) daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.getBatteryMeterViewProvider.get();
        StatusBarLocation statusBarLocation = (StatusBarLocation) daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.getStatusBarLocationProvider.get();
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        ConfigurationController configurationController2 = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get();
        TunerService tunerService = (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        KeyguardStatusBarViewController keyguardStatusBarViewController = new KeyguardStatusBarViewController(daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.view, carrierTextController, configurationController, systemStatusAnimationSchedulerImpl, batteryController, userInfoControllerImpl, statusBarIconController, factory, new BatteryMeterViewController(batteryMeterView, statusBarLocation, userTracker, configurationController2, tunerService, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get()), daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.shadeViewStateProvider, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardBypassController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBypassControllerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (KeyguardStatusBarViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStatusBarViewModelProvider.get(), (BiometricUnlockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider.get(), (SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get(), new StatusBarUserChipViewModel((UserSwitcherInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherInteractorProvider.get()), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1581$$Nest$mkeyguardLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1695$$Nest$mstatusOverlayHoverListenerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (CommunalSceneInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalSceneInteractorProvider.get());
        this.mKeyguardStatusBarViewController = keyguardStatusBarViewController;
        keyguardStatusBarViewController.init$9();
        this.mNotificationContainerParent = (NotificationsQuickSettingsContainer) notificationPanelView.findViewById(R.id.notification_container_parent);
        updateViewControllers(frameLayout, keyguardUserSwitcherView);
        AnonymousClass10 anonymousClass102 = new AnonymousClass10();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mOnHeightChangedListener = anonymousClass102;
        notificationStackScrollLayout.mOnEmptySpaceClickListener = this.mOnEmptySpaceClickListener;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        quickSettingsControllerImpl.getClass();
        QuickSettingsControllerImpl.NsslOverscrollTopChangedListener nsslOverscrollTopChangedListener = quickSettingsControllerImpl.new NsslOverscrollTopChangedListener();
        NotificationStackScrollLayout notificationStackScrollLayout2 = quickSettingsControllerImpl.mNotificationStackScrollLayoutController.mView;
        notificationStackScrollLayout2.mOverscrollTopChangedListener = nsslOverscrollTopChangedListener;
        notificationStackScrollLayout2.mOnStackYChanged = new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(4, quickSettingsControllerImpl);
        notificationStackScrollLayout2.mScrollListener = new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(i3, quickSettingsControllerImpl);
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) quickSettingsControllerImpl.mShadeInteractor;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = shadeInteractorImpl.isExpandToQsEnabled;
        QuickSettingsControllerImpl$$ExternalSyntheticLambda6 quickSettingsControllerImpl$$ExternalSyntheticLambda6 = new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(i2, quickSettingsControllerImpl);
        JavaAdapter javaAdapter = quickSettingsControllerImpl.mJavaAdapter;
        javaAdapter.alwaysCollectFlow(flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1, quickSettingsControllerImpl$$ExternalSyntheticLambda6);
        javaAdapter.alwaysCollectFlow(((CommunalTransitionViewModel) quickSettingsControllerImpl.mCommunalTransitionViewModelLazy.get()).isUmoOnCommunal, new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(2, quickSettingsControllerImpl));
        javaAdapter.alwaysCollectFlow(shadeInteractorImpl.baseShadeInteractor.isAnyExpanded(), new QuickSettingsControllerImpl$$ExternalSyntheticLambda6(i, quickSettingsControllerImpl));
        this.mShadeHeadsUpTracker.addTrackingHeadsUpListener(new NotificationPanelViewController$$ExternalSyntheticLambda2(notificationStackScrollLayoutController, 11));
        NotificationWakeUpCoordinator notificationWakeUpCoordinator = this.mWakeUpCoordinator;
        notificationWakeUpCoordinator.stackScrollerController = notificationStackScrollLayoutController;
        notificationWakeUpCoordinator.pulseExpanding = notificationStackScrollLayoutController.mView.mAmbientState.isPulseExpanding();
        notificationStackScrollLayoutController.mView.mAmbientState.mOnPulseHeightChangedListener = new NotificationWakeUpCoordinator$setStackScroller$1(notificationWakeUpCoordinator);
        notificationWakeUpCoordinator.wakeUpListeners.add(new NotificationWakeUpCoordinator.WakeUpListener() { // from class: com.android.systemui.shade.NotificationPanelViewController.4
            @Override // com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator.WakeUpListener
            public final void onFullyHiddenChanged(boolean z2) {
                NotificationPanelViewController.this.mKeyguardStatusBarViewController.updateForHeadsUp(true);
            }
        });
        notificationPanelView.mRtlChangeListener = new NotificationPanelViewController$$ExternalSyntheticLambda11(this);
        notificationPanelView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        if (this.mSplitShadeEnabled) {
            updateResources();
        }
        this.mTapAgainViewController.init$9();
        ShadeHeaderController shadeHeaderController = this.mShadeHeaderController;
        shadeHeaderController.init$9();
        shadeHeaderController.shadeCollapseAction = new NotificationPanelViewController$$ExternalSyntheticLambda0(this, 5);
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.DREAMING;
        KeyguardState keyguardState2 = KeyguardState.LOCKSCREEN;
        Edge.StateToState stateToState = new Edge.StateToState(keyguardState, keyguardState2);
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.mKeyguardTransitionInteractor;
        Flow transition = keyguardTransitionInteractor.transition(stateToState);
        NotificationPanelViewController$$ExternalSyntheticLambda2 notificationPanelViewController$$ExternalSyntheticLambda2 = this.mDreamingToLockscreenTransition;
        CoroutineDispatcher coroutineDispatcher = this.mMainDispatcher;
        JavaAdapterKt.collectFlow(notificationPanelView, transition, notificationPanelViewController$$ExternalSyntheticLambda2, coroutineDispatcher);
        DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel = this.mDreamingToLockscreenTransitionViewModel;
        JavaAdapterKt.collectFlow(notificationPanelView, dreamingToLockscreenTransitionViewModel.lockscreenAlpha, new NotificationPanelViewController$$ExternalSyntheticLambda41(this, notificationStackScrollLayoutController, i2), coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, dreamingToLockscreenTransitionViewModel.lockscreenTranslationY(this.mDreamingToLockscreenTransitionTranslationY), new NotificationPanelViewController$$ExternalSyntheticLambda2(this, notificationStackScrollLayoutController), coroutineDispatcher);
        SceneKey sceneKey = Scenes.Bouncer;
        KeyguardState keyguardState3 = KeyguardState.DREAMING_LOCKSCREEN_HOSTED;
        KeyguardState keyguardState4 = KeyguardState.GONE;
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState4, keyguardState3)), this.mGoneToDreamingLockscreenHostedTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, this.mGoneToDreamingLockscreenHostedTransitionViewModel.lockscreenAlpha, new NotificationPanelViewController$$ExternalSyntheticLambda41(this, notificationStackScrollLayoutController, i3), coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState2, keyguardState3)), this.mLockscreenToDreamingLockscreenHostedTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState3, keyguardState2)), this.mDreamingLockscreenHostedToLockscreenTransition, coroutineDispatcher);
        KeyguardState keyguardState5 = KeyguardState.OCCLUDED;
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState5, keyguardState2)), this.mOccludedToLockscreenTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState2, keyguardState)), this.mLockscreenToDreamingTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, this.mLockscreenToDreamingTransitionViewModel.lockscreenTranslationY(this.mLockscreenToDreamingTransitionTranslationY), new NotificationPanelViewController$$ExternalSyntheticLambda2(this, notificationStackScrollLayoutController), coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState4, keyguardState)), this.mGoneToDreamingTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, this.mGoneToDreamingTransitionViewModel.lockscreenTranslationY(this.mGoneToDreamingTransitionTranslationY), new NotificationPanelViewController$$ExternalSyntheticLambda2(this, notificationStackScrollLayoutController), coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, keyguardTransitionInteractor.transition(new Edge.StateToState(keyguardState2, keyguardState5)), this.mLockscreenToOccludedTransition, coroutineDispatcher);
        JavaAdapterKt.collectFlow(notificationPanelView, this.mShadeAnimationInteractor.isLaunchingActivity, new NotificationPanelViewController$$ExternalSyntheticLambda2(this, i), coroutineDispatcher);
    }

    public void onFlingEnd(boolean z) {
        this.mIsFlinging = false;
        this.mExpectingSynthesizedDown = false;
        setOverExpansionInternal(0.0f);
        setAnimator(null);
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguardStateController;
        keyguardStateControllerImpl.mFlingingToDismissKeyguard = false;
        keyguardStateControllerImpl.mFlingingToDismissKeyguardDuringSwipeGesture = false;
        keyguardStateControllerImpl.mSnappingKeyguardBackAfterSwipe = false;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (z) {
            InteractionJankMonitor interactionJankMonitor = (InteractionJankMonitor) quickSettingsControllerImpl.mInteractionJankMonitorLazy.get();
            if (interactionJankMonitor != null) {
                interactionJankMonitor.cancel(0);
            }
        } else {
            InteractionJankMonitor interactionJankMonitor2 = (InteractionJankMonitor) quickSettingsControllerImpl.mInteractionJankMonitorLazy.get();
            if (interactionJankMonitor2 != null) {
                interactionJankMonitor2.end(0);
            }
            notifyExpandingFinished();
        }
        updateExpansionAndVisibility();
        this.mNotificationStackScrollLayoutController.setPanelFlinging(false);
        this.mShadeLog.d("onFlingEnd called");
        quickSettingsControllerImpl.setExpandImmediate(false);
        ((ShadeRepositoryImpl) this.mShadeRepository)._currentFling.setValue(null);
    }

    public final void onPanelStateChanged$1(int i) {
        ShadeLogger shadeLogger = this.mShadeLog;
        shadeLogger.getClass();
        LogLevel logLevel = LogLevel.VERBOSE;
        ShadeLogger$logPanelStateChanged$2 shadeLogger$logPanelStateChanged$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logPanelStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("New panel State: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = shadeLogger.buffer;
        LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logPanelStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = ShadeExpansionStateManagerKt.panelStateToString(i);
        logBuffer.commit(obtain);
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        quickSettingsControllerImpl.updateExpansionEnabledAmbient();
        NotificationPanelView notificationPanelView = this.mView;
        if (i == 2 && this.mCurrentPanelState != i) {
            quickSettingsControllerImpl.setExpandImmediate(false);
            notificationPanelView.sendAccessibilityEvent(32);
        }
        if (i == 1) {
            if (this.mSplitShadeEnabled && !isKeyguardShowing$1()) {
                quickSettingsControllerImpl.setExpandImmediate(true);
            }
            ShadeControllerImpl.AnonymousClass2 anonymousClass2 = this.mOpenCloseListener;
            if (anonymousClass2 != null) {
                ShadeControllerImpl.this.makeExpandedVisible(false);
            }
        }
        if (i == 0) {
            quickSettingsControllerImpl.setExpandImmediate(false);
            notificationPanelView.post(this.mMaybeHideExpandedRunnable);
        }
        this.mCurrentPanelState = i;
    }

    public void onQsSetExpansionHeightCalled(boolean z) {
        requestScrollerTopPaddingUpdate();
        this.mKeyguardStatusBarViewController.updateViewState();
        int i = this.mBarState;
        if (i == 2 || i == 1) {
            positionClockAndNotifications(false);
        }
        if (this.mAccessibilityManager.isEnabled()) {
            this.mView.setAccessibilityPaneTitle(determineAccessibilityPaneTitle());
        }
        if (this.mFalsingManager.isUnlockingDisabled() || !z) {
            return;
        }
        this.mFalsingCollector.getClass();
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void onThemeChanged() {
        this.mConfigurationListener.onThemeChanged();
    }

    public final void onTrackingStarted$1() {
        endClosing();
        ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) this.mShadeRepository;
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = shadeRepositoryImpl._legacyShadeTracking;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        ShadeControllerImpl$$ExternalSyntheticLambda1 shadeControllerImpl$$ExternalSyntheticLambda1 = this.mTrackingStartedListener;
        if (shadeControllerImpl$$ExternalSyntheticLambda1 != null) {
            shadeControllerImpl$$ExternalSyntheticLambda1.f$0.runPostCollapseActions();
        }
        notifyExpandingStarted();
        updateExpansionAndVisibility();
        ScrimController scrimController = this.mScrimController;
        scrimController.mDarkenWhileDragging = !((KeyguardStateControllerImpl) scrimController.mKeyguardStateController).mCanDismissLockScreen;
        if (!scrimController.mKeyguardUnlockAnimationController.playingCannedUnlockAnimation) {
            scrimController.mAnimatingPanelExpansionOnUnlock = false;
        }
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (quickSettingsControllerImpl.mFullyExpanded) {
            quickSettingsControllerImpl.setExpandImmediate(true);
            setShowShelfOnly(true);
        }
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mPanelTracking = true;
        notificationStackScrollLayout.mAmbientState.mPanelTracking = true;
        notificationStackScrollLayout.mSwipeHelper.resetExposedMenuView(true, true);
        cancelPendingCollapse();
    }

    public final void onTrackingStopped(boolean z) {
        ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) this.mShadeRepository;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = shadeRepositoryImpl._legacyShadeTracking;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        updateExpansionAndVisibility();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        if (z) {
            notificationStackScrollLayoutController.mView.setOverScrollAmount(0.0f, true, true, true);
        }
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mPanelTracking = false;
        notificationStackScrollLayout.mAmbientState.mPanelTracking = false;
        NotificationShadeDepthController notificationShadeDepthController = this.mDepthController;
        if (notificationShadeDepthController.blursDisabledForUnlock) {
            notificationShadeDepthController.blursDisabledForUnlock = false;
            notificationShadeDepthController.scheduleUpdate();
        }
    }

    public final void positionClockAndNotifications(boolean z) {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        if (notificationStackScrollLayout != null && notificationStackScrollLayout.mNeedsAnimation && notificationStackScrollLayout.mChildrenToAddAnimated.isEmpty()) {
            notificationStackScrollLayout.mChildrenToRemoveAnimated.isEmpty();
        }
        boolean isKeyguardShowing$1 = isKeyguardShowing$1();
        if (isKeyguardShowing$1 || z) {
            updateClockAppearance();
        }
        notificationStackScrollLayoutController.mView.mIntrinsicPadding = !isKeyguardShowing$1 ? this.mSplitShadeEnabled ? 0 : this.mQsController.getHeaderHeight() : this.mClockPositionResult.stackScrollerPaddingExpanded;
        this.mStackScrollerMeasuringPass++;
        requestScrollerTopPaddingUpdate();
        this.mStackScrollerMeasuringPass = 0;
        this.mAnimateNextPositionUpdate = false;
    }

    public final View reInflateStub(int i, int i2, int i3, boolean z) {
        View view;
        NotificationPanelView notificationPanelView = this.mView;
        View findViewById = notificationPanelView.findViewById(i);
        if (findViewById == null) {
            return z ? ((ViewStub) notificationPanelView.findViewById(i2)).inflate() : findViewById;
        }
        int indexOfChild = notificationPanelView.indexOfChild(findViewById);
        notificationPanelView.removeView(findViewById);
        if (z) {
            view = this.mLayoutInflater.inflate(i3, (ViewGroup) notificationPanelView, false);
            notificationPanelView.addView(view, indexOfChild);
        } else {
            ViewStub viewStub = new ViewStub(notificationPanelView.getContext(), i3);
            viewStub.setId(i2);
            notificationPanelView.addView(viewStub, indexOfChild);
            view = null;
        }
        return view;
    }

    public void reInflateViews() {
        updateResources();
        updateUserSwitcherFlags();
        boolean isUserSwitcherEnabled = this.mUserManager.isUserSwitcherEnabled(this.mResources.getBoolean(R.bool.qs_show_user_switcher_for_single_user));
        boolean z = this.mKeyguardQsUserSwitchEnabled;
        updateViewControllers((FrameLayout) reInflateStub(R.id.keyguard_qs_user_switch_view, R.id.keyguard_qs_user_switch_stub, R.layout.keyguard_qs_user_switch, z && isUserSwitcherEnabled), (KeyguardUserSwitcherView) reInflateStub(R.id.keyguard_user_switcher_view, R.id.keyguard_user_switcher_stub, R.layout.keyguard_user_switcher, !z && this.mKeyguardUserSwitcherEnabled && isUserSwitcherEnabled));
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        float f = statusBarStateControllerImpl.mDozeAmount;
        this.mStatusBarStateListener.onDozeAmountChanged(f, statusBarStateControllerImpl.mDozeInterpolator.getInterpolation(f));
        KeyguardQsUserSwitchController keyguardQsUserSwitchController = this.mKeyguardQsUserSwitchController;
        if (keyguardQsUserSwitchController != null) {
            keyguardQsUserSwitchController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
        }
        KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
        if (keyguardUserSwitcherController != null) {
            keyguardUserSwitcherController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
        }
    }

    public final void requestScrollerTopPaddingUpdate() {
        int i;
        float lerp;
        int max;
        boolean z = this.mIsExpandingOrCollapsing;
        boolean isKeyguardShowing$1 = isKeyguardShowing$1();
        float f = 0.0f;
        KeyguardBypassController keyguardBypassController = this.mKeyguardBypassController;
        if (isKeyguardShowing$1) {
            boolean bypassEnabled = keyguardBypassController.getBypassEnabled();
            KeyguardClockPositionAlgorithm.Result result = this.mClockPositionResult;
            if (bypassEnabled) {
                i = this.mHeadsUpInset;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
                if (notificationStackScrollLayoutController.mView.mAmbientState.isPulseExpanding()) {
                    int i2 = result.stackScrollerPadding;
                    float f2 = notificationStackScrollLayoutController.mView.mAmbientState.mPulseHeight;
                    if (f2 == 100000.0f) {
                        f2 = 0.0f;
                    }
                    lerp = MathUtils.lerp(i, i2, MathUtils.smoothStep(0.0f, r6.mIntrinsicPadding, f2));
                    i = (int) lerp;
                }
            } else if (this.mSplitShadeEnabled) {
                i = result.stackScrollerPadding;
            } else {
                lerp = ((NotificationContainerBounds) ((StateFlow) this.mKeyguardInteractor.notificationContainerBounds$delegate.getValue()).getValue()).top;
                i = (int) lerp;
            }
        } else {
            i = 0;
        }
        float f3 = this.mExpandedFraction;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        boolean z2 = quickSettingsControllerImpl.mBarState == 1;
        if (!quickSettingsControllerImpl.mSplitShadeEnabled) {
            if (z2 && (quickSettingsControllerImpl.isExpandImmediate() || (z && quickSettingsControllerImpl.mExpandedWhenExpandingStarted))) {
                int i3 = quickSettingsControllerImpl.mMaxExpansionHeight;
                if (z2) {
                    i3 = Math.max(i, i3);
                }
                max = (int) MathUtils.lerp(quickSettingsControllerImpl.mMinExpansionHeight, i3, f3);
            } else {
                ValueAnimator valueAnimator = quickSettingsControllerImpl.mSizeChangeAnimator;
                if (valueAnimator != null) {
                    max = Math.max(((Integer) valueAnimator.getAnimatedValue()).intValue(), i);
                } else if (z2) {
                    f = MathUtils.lerp(i, quickSettingsControllerImpl.mMaxExpansionHeight, quickSettingsControllerImpl.computeExpansionFraction());
                } else {
                    float f4 = quickSettingsControllerImpl.mExpansionHeight;
                    quickSettingsControllerImpl.mQsFrameTranslateController.getClass();
                    f = Math.max(f4, quickSettingsControllerImpl.mQuickQsHeaderHeight);
                }
            }
            f = max;
        } else if (z2) {
            f = i;
        }
        StateFlowImpl stateFlowImpl = this.mSharedNotificationContainerInteractor._topPosition;
        Float valueOf = Float.valueOf(f);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        if (isKeyguardShowing$1() && keyguardBypassController.getBypassEnabled()) {
            quickSettingsControllerImpl.updateExpansion();
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void resetAlpha() {
        this.mView.setAlpha(1.0f);
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void resetTranslation() {
        this.mView.setTranslationX(0.0f);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViewGroupFade() {
        NotificationPanelView notificationPanelView = this.mView;
        Object tag = notificationPanelView.getTag(R.id.view_group_fade_helper_modified_views);
        if ((tag instanceof KMappedMarker) && !(tag instanceof KMutableSet)) {
            TypeIntrinsics.throwCce(tag, "kotlin.collections.MutableSet");
            throw null;
        }
        try {
            Set<View> set = (Set) tag;
            android.animation.Animator animator = (android.animation.Animator) notificationPanelView.getTag(R.id.view_group_fade_helper_animator);
            if (set == null || animator == null) {
                return;
            }
            animator.cancel();
            Float f = (Float) notificationPanelView.getTag(R.id.view_group_fade_helper_previous_value_tag);
            for (View view : set) {
                Float f2 = (Float) view.getTag(R.id.view_group_fade_helper_restore_tag);
                if (f2 != null) {
                    if (Intrinsics.areEqual(f, view.getAlpha())) {
                        view.setAlpha(f2.floatValue());
                    }
                    if (Intrinsics.areEqual((Boolean) view.getTag(R.id.view_group_fade_helper_hardware_layer), Boolean.TRUE)) {
                        view.setLayerType(0, null);
                        view.setTag(R.id.view_group_fade_helper_hardware_layer, null);
                    }
                    view.setTag(R.id.view_group_fade_helper_restore_tag, null);
                }
            }
            notificationPanelView.setTag(R.id.view_group_fade_helper_modified_views, null);
            notificationPanelView.setTag(R.id.view_group_fade_helper_previous_value_tag, null);
            notificationPanelView.setTag(R.id.view_group_fade_helper_animator, null);
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void resetViews(boolean z) {
        this.mGutsManager.closeAndSaveGuts(true, true, true, true);
        if (!z || isFullyCollapsed()) {
            closeQsIfPossible();
        } else {
            animateCollapseQs(true);
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        notificationStackScrollLayoutController.mView.setOverScrollAmount(0.0f, true, z, !z);
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mScroller.abortAnimation();
        notificationStackScrollLayout.setOwnScrollY(0);
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAlpha(int i, boolean z) {
        if (this.mPanelAlpha != i) {
            this.mPanelAlpha = i;
            PropertyAnimator.setProperty(this.mView, this.mPanelAlphaAnimator, i, i == 255 ? this.mPanelAlphaInPropertiesAnimator : this.mPanelAlphaOutPropertiesAnimator, z);
        }
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAlphaChangeAnimationEndAction(BrightnessMirrorController$$ExternalSyntheticLambda0 brightnessMirrorController$$ExternalSyntheticLambda0) {
        this.mPanelAlphaEndAction = brightnessMirrorController$$ExternalSyntheticLambda0;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setAmbientIndicationTop(int i, boolean z) {
        int bottom = z ? this.mNotificationStackScrollLayoutController.mView.getBottom() - i : 0;
        if (this.mAmbientIndicationBottomPadding != bottom) {
            this.mAmbientIndicationBottomPadding = bottom;
        }
    }

    public final void setAnimator(ValueAnimator valueAnimator) {
        Set set = this.mTestSetOfAnimatorsUsed;
        if (set != null && valueAnimator != null) {
            set.add(valueAnimator);
        }
        this.mHeightAnimator = valueAnimator;
        if (valueAnimator == null && this.mPanelUpdateWhenAnimatorEnds) {
            this.mPanelUpdateWhenAnimatorEnds = false;
            updateExpandedHeightToMaxHeight();
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setBouncerShowing(boolean z) {
        this.mBouncerShowing = z;
        updateVisibility$6();
    }

    public void setClosing(boolean z) {
        AuthContainerView$$ExternalSyntheticOutline0.m(z, ((ShadeRepositoryImpl) this.mShadeRepository)._legacyIsClosing, null);
        this.mAmbientState.mIsClosing = z;
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setDozing(boolean z, boolean z2) {
        if (z == this.mDozing) {
            return;
        }
        NotificationPanelView notificationPanelView = this.mView;
        notificationPanelView.mDozing = z;
        this.mDozing = z;
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
        if (ambientState.mDozing != z) {
            ambientState.mDozing = z;
            notificationStackScrollLayout.requestChildrenUpdate();
            notificationStackScrollLayout.notifyHeightChangeListener(notificationStackScrollLayout.mShelf, false);
        }
        KeyguardInteractor keyguardInteractor = this.mKeyguardInteractor;
        KeyguardRepositoryImpl keyguardRepositoryImpl = keyguardInteractor.repository;
        Boolean valueOf = Boolean.valueOf(z2);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._animateBottomAreaDozingTransitions;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        KeyguardStatusBarViewController keyguardStatusBarViewController = this.mKeyguardStatusBarViewController;
        boolean z3 = this.mDozing;
        keyguardStatusBarViewController.mDozing = z3;
        this.mQsController.mDozing = z3;
        if (z) {
            this.mBottomAreaShadeAlphaAnimator.cancel();
        }
        int i = this.mBarState;
        if (i == 1 || i == 2) {
            KeyguardRepositoryImpl keyguardRepositoryImpl2 = keyguardInteractor.repository;
            Boolean valueOf2 = Boolean.valueOf(z2);
            StateFlowImpl stateFlowImpl2 = keyguardRepositoryImpl2._animateBottomAreaDozingTransitions;
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, valueOf2);
            if (!this.mDozing && z2) {
                this.mKeyguardStatusBarViewController.animateKeyguardStatusBarIn();
            }
        }
        float f = z ? 1.0f : 0.0f;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        ValueAnimator valueAnimator = statusBarStateControllerImpl.mDarkAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            if (!z2 || statusBarStateControllerImpl.mDozeAmountTarget != f) {
                statusBarStateControllerImpl.mDarkAnimator.cancel();
            }
            updateKeyguardStatusViewAlignment();
        }
        View view = statusBarStateControllerImpl.mView;
        if ((view == null || !view.isAttachedToWindow()) && notificationPanelView.isAttachedToWindow()) {
            statusBarStateControllerImpl.mView = notificationPanelView;
        }
        statusBarStateControllerImpl.mDozeAmountTarget = f;
        if (z2) {
            float f2 = statusBarStateControllerImpl.mDozeAmount;
            if (f2 == 0.0f || f2 == 1.0f) {
                statusBarStateControllerImpl.mDozeInterpolator = statusBarStateControllerImpl.mIsDozing ? Interpolators.FAST_OUT_SLOW_IN : Interpolators.TOUCH_RESPONSE_REVERSE;
            }
            if (f2 == 1.0f && !statusBarStateControllerImpl.mIsDozing) {
                statusBarStateControllerImpl.setDozeAmountInternal(0.99f);
            }
            statusBarStateControllerImpl.mDarkAnimator = statusBarStateControllerImpl.createDarkAnimator();
        } else {
            statusBarStateControllerImpl.setDozeAmountInternal(f);
        }
        updateKeyguardStatusViewAlignment();
    }

    public void setExpandedFraction(float f) {
        setExpandedHeight(getMaxPanelTransitionDistance() * f);
    }

    public void setExpandedHeight(float f) {
        setExpandedHeightInternal(f);
    }

    public final void setExpandedHeightInternal(final float f) {
        if (Float.isNaN(f)) {
            Log.wtf("NotificationPanelView", "ExpandedHeight set to NaN");
        }
        ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).batchApplyWindowLayoutParams(new Runnable() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                float f2 = f;
                if (notificationPanelViewController.mExpandLatencyTracking && f2 != 0.0f) {
                    DejankUtils.postAfterTraversal(new NotificationPanelViewController$$ExternalSyntheticLambda0(notificationPanelViewController, 8));
                    notificationPanelViewController.mExpandLatencyTracking = false;
                }
                float maxPanelTransitionDistance = notificationPanelViewController.getMaxPanelTransitionDistance();
                float min = Math.min(f2, maxPanelTransitionDistance);
                notificationPanelViewController.mExpandedHeight = min;
                float f3 = 1.0f;
                if (min < 1.0f && min != 0.0f && notificationPanelViewController.isClosing()) {
                    notificationPanelViewController.mExpandedHeight = 0.0f;
                    ValueAnimator valueAnimator = notificationPanelViewController.mHeightAnimator;
                    if (valueAnimator != null) {
                        valueAnimator.end();
                    }
                }
                float min2 = Math.min(1.0f, maxPanelTransitionDistance == 0.0f ? 0.0f : notificationPanelViewController.mExpandedHeight / maxPanelTransitionDistance);
                notificationPanelViewController.mExpandedFraction = min2;
                if (min2 > 0.0f && notificationPanelViewController.mExpectingSynthesizedDown) {
                    notificationPanelViewController.mExpectingSynthesizedDown = false;
                }
                StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) notificationPanelViewController.mShadeRepository)._legacyShadeExpansion;
                Float valueOf = Float.valueOf(min2);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
                float f4 = notificationPanelViewController.mExpandedHeight;
                float f5 = notificationPanelViewController.mExpandedFraction;
                QuickSettingsControllerImpl quickSettingsControllerImpl = notificationPanelViewController.mQsController;
                quickSettingsControllerImpl.mShadeExpandedHeight = f4;
                quickSettingsControllerImpl.mShadeExpandedFraction = f5;
                notificationPanelViewController.mExpansionDragDownAmountPx = f2;
                notificationPanelViewController.mAmbientState.mExpansionFraction = f5;
                if (f4 <= 0.0f) {
                    notificationPanelViewController.mShadeLog.logExpansionChanged("onHeightUpdated: fully collapsed.", f5, notificationPanelViewController.isExpanded(), notificationPanelViewController.isTracking(), notificationPanelViewController.mExpansionDragDownAmountPx);
                } else if (notificationPanelViewController.isFullyExpanded()) {
                    notificationPanelViewController.mShadeLog.logExpansionChanged("onHeightUpdated: fully expanded.", notificationPanelViewController.mExpandedFraction, notificationPanelViewController.isExpanded(), notificationPanelViewController.isTracking(), notificationPanelViewController.mExpansionDragDownAmountPx);
                }
                if ((!quickSettingsControllerImpl.getExpanded() || quickSettingsControllerImpl.isExpandImmediate() || (notificationPanelViewController.mIsExpandingOrCollapsing && quickSettingsControllerImpl.mExpandedWhenExpandingStarted)) && notificationPanelViewController.mStackScrollerMeasuringPass <= 2) {
                    notificationPanelViewController.positionClockAndNotifications(false);
                }
                boolean z = quickSettingsControllerImpl.isExpandImmediate() || (quickSettingsControllerImpl.getExpanded() && !quickSettingsControllerImpl.isTracking() && quickSettingsControllerImpl.mExpansionAnimator == null && !quickSettingsControllerImpl.mExpansionFromOverscroll);
                boolean z2 = notificationPanelViewController.mSplitShadeEnabled;
                boolean z3 = (z2 && ((HeadsUpManagerPhone) notificationPanelViewController.mHeadsUpManager).mTrackingHeadsUp && f4 <= ((float) notificationPanelViewController.mHeadsUpStartHeight)) ? false : true;
                if (z && z3) {
                    if (!z2) {
                        if (notificationPanelViewController.isKeyguardShowing$1()) {
                            f3 = f4 / notificationPanelViewController.getMaxPanelHeight();
                        } else {
                            NotificationStackScrollLayout notificationStackScrollLayout = notificationPanelViewController.mNotificationStackScrollLayoutController.mView;
                            float layoutMinHeightInternal = notificationStackScrollLayout.getLayoutMinHeightInternal() + notificationStackScrollLayout.mIntrinsicPadding;
                            f3 = (f4 - layoutMinHeightInternal) / (quickSettingsControllerImpl.calculatePanelHeightExpanded(notificationPanelViewController.mClockPositionResult.stackScrollerPadding) - layoutMinHeightInternal);
                        }
                    }
                    quickSettingsControllerImpl.setExpansionHeight((f3 * (quickSettingsControllerImpl.mMaxExpansionHeight - r12)) + quickSettingsControllerImpl.mMinExpansionHeight);
                }
                notificationPanelViewController.updateExpandedHeight(f4);
                if (notificationPanelViewController.mBarState == 1) {
                    notificationPanelViewController.mKeyguardStatusBarViewController.updateViewState();
                }
                quickSettingsControllerImpl.updateExpansion();
                notificationPanelViewController.updatePanelExpanded();
                notificationPanelViewController.updateGestureExclusionRect();
                notificationPanelViewController.updateExpansionAndVisibility();
            }
        });
    }

    public void setForceFlingAnimationForTest(boolean z) {
        this.mForceFlingAnimationForTest = z;
    }

    public void setHeadsUpDraggingStartingHeight(int i) {
        float maxPanelHeight;
        float f;
        int i2;
        this.mHeadsUpStartHeight = i;
        if (!this.mSplitShadeEnabled) {
            maxPanelHeight = getMaxPanelHeight();
            f = 0.0f;
            if (maxPanelHeight > 0.0f) {
                i2 = this.mHeadsUpStartHeight;
            }
            setPanelScrimMinFraction$1(f);
        }
        double d = i * 2.5d;
        int i3 = this.mSplitShadeFullTransitionDistance;
        if (d > i3) {
            i3 = getMaxPanelTransitionDistance();
        }
        maxPanelHeight = i3;
        i2 = this.mHeadsUpStartHeight;
        f = i2 / maxPanelHeight;
        setPanelScrimMinFraction$1(f);
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setImportantForAccessibility(int i) {
        this.mView.setImportantForAccessibility(i);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setKeyguardStatusBarAlpha(float f) {
        KeyguardStatusBarViewController keyguardStatusBarViewController = this.mKeyguardStatusBarViewController;
        keyguardStatusBarViewController.mExplicitAlpha = f;
        keyguardStatusBarViewController.updateViewState();
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setKeyguardTransitionProgress(int i, float f) {
        float interpolation = ((PathInterpolator) Interpolators.ALPHA_IN).getInterpolation(f);
        this.mKeyguardOnlyContentAlpha = interpolation;
        this.mKeyguardOnlyTransitionTranslationY = i;
        if (this.mBarState == 1) {
            this.mBottomAreaShadeAlpha = interpolation;
        }
        updateClock$2();
    }

    public final void setListening$1(boolean z) {
        KeyguardStatusBarViewController keyguardStatusBarViewController = this.mKeyguardStatusBarViewController;
        if (z != keyguardStatusBarViewController.mBatteryListening) {
            keyguardStatusBarViewController.mBatteryListening = z;
            KeyguardStatusBarViewController.AnonymousClass3 anonymousClass3 = keyguardStatusBarViewController.mBatteryStateChangeCallback;
            BatteryController batteryController = keyguardStatusBarViewController.mBatteryController;
            if (z) {
                batteryController.addCallback(anonymousClass3);
            } else {
                ((BatteryControllerImpl) batteryController).removeCallback(anonymousClass3);
            }
        }
        QS qs = this.mQsController.mQs;
        if (qs != null) {
            qs.setListening(z);
        }
    }

    public void setMaxDisplayedNotifications(int i) {
        this.mMaxAllowedKeyguardNotifications = i;
    }

    public void setOverExpansion(float f) {
        if (f == this.mOverExpansion) {
            return;
        }
        this.mOverExpansion = f;
        boolean z = this.mSplitShadeEnabled;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        if (z) {
            int i = (int) f;
            QS qs = quickSettingsControllerImpl.mQs;
            if (qs != null) {
                qs.setOverScrollAmount(i);
            }
            ScrimView scrimView = this.mScrimController.mNotificationsScrim;
            if (scrimView != null) {
                scrimView.setTranslationY(i);
            }
        } else {
            int i2 = ((NotificationPanelViewController) quickSettingsControllerImpl.mPanelViewControllerLazy.get()).mNavigationBarBottomHeight;
            int i3 = quickSettingsControllerImpl.mAmbientState.mStackTopMargin;
            quickSettingsControllerImpl.mQsFrameTranslateController.getClass();
        }
        this.mNotificationStackScrollLayoutController.setOverExpansion(f);
    }

    public final void setOverExpansionInternal(float f) {
        this.mLastGesturedOverExpansion = -1.0f;
        setOverExpansion(f);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setOverStretchAmount(float f) {
        float height = f / this.mView.getHeight();
        Interpolator interpolator = Interpolators.EMPHASIZED;
        float exp = (float) (1.0d - Math.exp(height * (-4.0f)));
        if (0.0f > exp) {
            exp = 0.0f;
        }
        this.mOverStretchAmount = exp * this.mMaxOverscrollAmountForPulse;
        positionClockAndNotifications(true);
    }

    public final void setPanelScrimMinFraction$1(float f) {
        this.mMinFraction = f;
        this.mDepthController.panelPullDownMinFraction = f;
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("minFraction should not be NaN");
        }
        scrimController.mPanelScrimMinFraction = f;
        scrimController.calculateAndUpdatePanelExpansion();
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void setPulsing(boolean z) {
        this.mPulsing = z;
        DozeParameters dozeParameters = this.mDozeParameters;
        boolean z2 = !dozeParameters.getDisplayNeedsBlanking() && dozeParameters.getAlwaysOn();
        if (z2) {
            this.mAnimateNextPositionUpdate = true;
        }
        if (!this.mPulsing && !this.mDozing) {
            this.mAnimateNextPositionUpdate = false;
        }
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        if (notificationStackScrollLayout.mPulsing || z) {
            notificationStackScrollLayout.mPulsing = z;
            notificationStackScrollLayout.mAmbientState.mPulsing = z;
            notificationStackScrollLayout.mSwipeHelper.mPulsing = z;
            notificationStackScrollLayout.updateNotificationAnimationStates();
            notificationStackScrollLayout.updateAlgorithmHeightAndPadding();
            notificationStackScrollLayout.updateContentHeight();
            notificationStackScrollLayout.requestChildrenUpdate();
            notificationStackScrollLayout.notifyHeightChangeListener(null, z2);
        }
        updateKeyguardStatusViewAlignment();
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void setQsScrimEnabled(boolean z) {
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        boolean z2 = quickSettingsControllerImpl.mScrimEnabled != z;
        quickSettingsControllerImpl.mScrimEnabled = z;
        if (z2) {
            quickSettingsControllerImpl.updateQsState$1();
        }
    }

    public final void setShowShelfOnly(boolean z) {
        boolean z2 = z && !this.mSplitShadeEnabled;
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mShouldShowShelfOnly = z2;
        notificationStackScrollLayout.updateAlgorithmLayoutMinHeight();
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setTouchAndAnimationDisabled(boolean z) {
        this.mTouchDisabled = z;
        if (z) {
            cancelHeightAnimator();
            if (isTracking()) {
                onTrackingStopped(true);
            }
            notifyExpandingFinished();
        }
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mAnimationsEnabled = !z;
        notificationStackScrollLayout.updateNotificationAnimationStates();
        if (z) {
            notificationStackScrollLayout.mSwipedOutViews.clear();
            notificationStackScrollLayout.mChildrenToRemoveAnimated.clear();
            notificationStackScrollLayout.clearTemporaryViewsInGroup(notificationStackScrollLayout, "setAnimationsEnabled");
        }
    }

    public void setTouchSlopExceeded(boolean z) {
        this.mTouchSlopExceeded = z;
    }

    @Override // com.android.systemui.shade.domain.interactor.PanelExpansionInteractor
    public final boolean shouldHideStatusBarIconsWhenExpanded() {
        if (isLaunchingActivity$1()) {
            return false;
        }
        HeadsUpAppearanceController headsUpAppearanceController = this.mHeadsUpAppearanceController;
        if (headsUpAppearanceController == null || !headsUpAppearanceController.shouldBeVisible$1()) {
            return !this.mShowIconsWhenExpanded;
        }
        return false;
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void showAodUi() {
        setDozing(true, false);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.mStatusBarStateController;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) sysuiStatusBarStateController;
        statusBarStateControllerImpl.recordHistoricalState(1, statusBarStateControllerImpl.mState, true);
        statusBarStateControllerImpl.updateUpcomingState(1);
        sysuiStatusBarStateController.getClass();
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).setState(1, false);
        this.mStatusBarStateListener.onDozeAmountChanged(1.0f, 1.0f);
        setExpandedFraction(1.0f);
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void startBouncerPreHideAnimation() {
        KeyguardQsUserSwitchController keyguardQsUserSwitchController = this.mKeyguardQsUserSwitchController;
        if (keyguardQsUserSwitchController != null) {
            keyguardQsUserSwitchController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
        }
        KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
        if (keyguardUserSwitcherController != null) {
            keyguardUserSwitcherController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
        }
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void startExpandLatencyTracking() {
        if (this.mLatencyTracker.isEnabled()) {
            this.mLatencyTracker.onActionStart(0);
            this.mExpandLatencyTracking = true;
        }
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void startInputFocusTransfer() {
        if (this.mCommandQueue.panelsEnabled() && isFullyCollapsed()) {
            this.mExpectingSynthesizedDown = true;
            onTrackingStarted$1();
            updatePanelExpanded();
        }
    }

    @Override // com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor
    public final void transitionToExpandedShade(long j) {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mGoToFullShadeNeedsAnimation = true;
        notificationStackScrollLayout.mGoToFullShadeDelay = j;
        notificationStackScrollLayout.mNeedsAnimation = true;
        notificationStackScrollLayout.requestChildrenUpdate();
        this.mView.requestLayout();
        this.mAnimateNextPositionUpdate = true;
    }

    public final void updateClock$2() {
        if (this.mIsOcclusionTransitionRunning) {
            return;
        }
        float f = this.mClockPositionResult.clockAlpha * this.mKeyguardOnlyContentAlpha;
        KeyguardStatusViewController keyguardStatusViewController = this.mKeyguardStatusViewController;
        keyguardStatusViewController.mKeyguardVisibilityHelper.getClass();
        ((KeyguardStatusView) keyguardStatusViewController.mView).setAlpha(f);
        KeyguardQsUserSwitchController keyguardQsUserSwitchController = this.mKeyguardQsUserSwitchController;
        if (keyguardQsUserSwitchController != null) {
            keyguardQsUserSwitchController.mKeyguardVisibilityHelper.getClass();
            ((FrameLayout) keyguardQsUserSwitchController.mView).setAlpha(f);
        }
        KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
        if (keyguardUserSwitcherController != null) {
            keyguardUserSwitcherController.mKeyguardVisibilityHelper.getClass();
            ((KeyguardUserSwitcherView) keyguardUserSwitcherController.mView).setAlpha(f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateClockAppearance() {
        /*
            Method dump skipped, instructions count: 751
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.NotificationPanelViewController.updateClockAppearance():void");
    }

    public final void updateExpandedHeight(float f) {
        boolean isTracking = isTracking();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mNotificationStackScrollLayoutController;
        if (isTracking) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            notificationStackScrollLayoutController.mView.mAmbientState.mExpandingVelocity = this.mVelocityTracker.getYVelocity() * (this.mIsTrackpadReverseScroll ? -1 : 1);
        }
        if (this.mKeyguardBypassController.getBypassEnabled() && isKeyguardShowing$1()) {
            f = getMaxPanelHeight();
        }
        notificationStackScrollLayoutController.mView.setExpandedHeight(f);
        float f2 = this.mExpandedHeight;
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        boolean z = f2 < (notificationStackScrollLayout.mEmptyShadeView.getVisibility() == 8 ? (float) notificationStackScrollLayout.getMinExpansionHeight() : notificationStackScrollLayout.getAppearEndPosition());
        if (z && isKeyguardShowing$1()) {
            z = false;
        }
        if (z != this.mShowIconsWhenExpanded) {
            this.mShowIconsWhenExpanded = z;
            this.mCommandQueue.recomputeDisableFlags(this.mDisplayId, false);
        }
    }

    public final void updateExpandedHeightToMaxHeight() {
        float maxPanelHeight = getMaxPanelHeight();
        if (isFullyCollapsed() || maxPanelHeight == this.mExpandedHeight) {
            return;
        }
        if (isTracking() && !this.mBlockingExpansionForCurrentTouch) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
            if (!quickSettingsControllerImpl.mConflictingExpansionGesture || !quickSettingsControllerImpl.getExpanded()) {
                return;
            }
        }
        if (this.mHeightAnimator == null || this.mIsSpringBackAnimation) {
            setExpandedHeight(maxPanelHeight);
        } else {
            this.mPanelUpdateWhenAnimatorEnds = true;
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void updateExpansionAndVisibility() {
        boolean z;
        boolean z2;
        float f = this.mExpandedFraction;
        boolean isExpanded = isExpanded();
        boolean isTracking = isTracking();
        ShadeExpansionStateManager shadeExpansionStateManager = this.mShadeExpansionStateManager;
        shadeExpansionStateManager.getClass();
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("fraction cannot be NaN");
        }
        int i = shadeExpansionStateManager.state;
        shadeExpansionStateManager.fraction = f;
        shadeExpansionStateManager.expanded = isExpanded;
        shadeExpansionStateManager.tracking = isTracking;
        if (isExpanded) {
            if (i == 0) {
                shadeExpansionStateManager.updateStateInternal(1);
            }
            z2 = f >= 1.0f;
            z = false;
        } else {
            z = true;
            z2 = false;
        }
        if (z2 && !isTracking) {
            shadeExpansionStateManager.updateStateInternal(2);
        } else if (z && !isTracking && shadeExpansionStateManager.state != 0) {
            shadeExpansionStateManager.updateStateInternal(0);
        }
        ShadeExpansionStateManagerKt.panelStateToString(i);
        ShadeExpansionStateManagerKt.panelStateToString(shadeExpansionStateManager.state);
        if (Trace.isTagEnabled(4096L)) {
            Trace.traceCounter(4096L, "panel_expansion", (int) (100 * f));
            if (shadeExpansionStateManager.state != i) {
                Trace.asyncTraceForTrackEnd(4096L, "ShadeExpansionState", 0);
                Trace.asyncTraceForTrackBegin(4096L, "ShadeExpansionState", ShadeExpansionStateManagerKt.panelStateToString(shadeExpansionStateManager.state), 0);
            }
        }
        ShadeExpansionChangeEvent shadeExpansionChangeEvent = new ShadeExpansionChangeEvent(f, isExpanded, isTracking);
        Iterator it = shadeExpansionStateManager.expansionListeners.iterator();
        while (it.hasNext()) {
            ((ShadeExpansionListener) it.next()).onPanelExpansionChanged(shadeExpansionChangeEvent);
        }
        updateVisibility$6();
    }

    public final void updateGestureExclusionRect() {
        Region calculateTouchableRegion = this.mStatusBarTouchableRegionManager.calculateTouchableRegion();
        Rect bounds = (!isFullyCollapsed() || calculateTouchableRegion == null) ? null : calculateTouchableRegion.getBounds();
        if (bounds == null) {
            bounds = EMPTY_RECT;
        }
        this.mView.setSystemGestureExclusionRects(bounds.isEmpty() ? Collections.emptyList() : Collections.singletonList(bounds));
    }

    public final void updateKeyguardStatusViewAlignment() {
        boolean z = this.mSplitShadeEnabled;
        KeyguardInteractor keyguardInteractor = this.mKeyguardInteractor;
        final boolean z2 = true;
        if (z && ((!((ActiveNotificationsStore) this.mActiveNotificationsInteractor.repository.activeNotifications.getValue()).renderList.isEmpty() || this.mMediaDataManager.hasActiveMediaOrRecommendation()) && !((Boolean) keyguardInteractor.isActiveDreamLockscreenHosted.getValue()).booleanValue())) {
            z2 = NotificationStackScrollLayoutController.this.mView.mPulsing ? false : isOnAod();
        }
        this.mKeyguardUnfoldTransition.ifPresent(new Consumer() { // from class: com.android.systemui.shade.NotificationPanelViewController$$ExternalSyntheticLambda31
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((KeyguardUnfoldTransition) obj).statusViewCentered = z2;
            }
        });
        KeyguardRepositoryImpl keyguardRepositoryImpl = keyguardInteractor.repository;
        Boolean valueOf = Boolean.valueOf(z2);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._clockShouldBeCentered;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
    }

    public final void updateMaxHeadsUpTranslation() {
        int height = this.mView.getHeight();
        int i = this.mNavigationBarBottomHeight;
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationStackScrollLayoutController.mView;
        AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
        ambientState.mMaxHeadsUpTranslation = height - i;
        notificationStackScrollLayout.mStackScrollAlgorithm.mHeadsUpAppearHeightBottom = height;
        StackStateAnimator stackStateAnimator = notificationStackScrollLayout.mStateAnimator;
        stackStateAnimator.mHeadsUpAppearHeightBottom = height;
        stackStateAnimator.mStackTopMargin = ambientState.mStackTopMargin;
        notificationStackScrollLayout.requestChildrenUpdate();
    }

    public final void updatePanelExpanded() {
        boolean z = !isFullyCollapsed() || this.mExpectingSynthesizedDown;
        if (isPanelExpanded() != z) {
            StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) this.mShadeRepository)._legacyExpandedOrAwaitingInputTransfer;
            Boolean valueOf = Boolean.valueOf(z);
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
            updateSystemUiStateFlags();
            if (z) {
                return;
            }
            this.mQsController.closeQsCustomizer();
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void updateResources() {
        Trace.beginSection("NSSLC#updateResources");
        boolean shouldUseSplitNotificationShade = this.mSplitShadeStateController.shouldUseSplitNotificationShade(this.mResources);
        boolean z = this.mSplitShadeEnabled != shouldUseSplitNotificationShade;
        this.mSplitShadeEnabled = shouldUseSplitNotificationShade;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        boolean shouldUseSplitNotificationShade2 = quickSettingsControllerImpl.mSplitShadeStateController.shouldUseSplitNotificationShade(quickSettingsControllerImpl.mResources);
        quickSettingsControllerImpl.mSplitShadeEnabled = shouldUseSplitNotificationShade2;
        QS qs = quickSettingsControllerImpl.mQs;
        if (qs != null) {
            qs.setInSplitShade(shouldUseSplitNotificationShade2);
        }
        quickSettingsControllerImpl.mSplitShadeNotificationsScrimMarginBottom = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.split_shade_notifications_scrim_margin_bottom);
        NotificationPanelView notificationPanelView = quickSettingsControllerImpl.mPanelView;
        quickSettingsControllerImpl.mUseLargeScreenShadeHeader = notificationPanelView.getResources().getBoolean(R.bool.config_use_large_screen_shade_header);
        Context context = ((LargeScreenHeaderHelper) quickSettingsControllerImpl.mLargeScreenHeaderHelperLazy.get()).context;
        int max = Math.max(context.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_height), SystemBarUtils.getStatusBarHeight(context));
        quickSettingsControllerImpl.mLargeScreenShadeHeaderHeight = max;
        if (!quickSettingsControllerImpl.mUseLargeScreenShadeHeader) {
            max = quickSettingsControllerImpl.mResources.getDimensionPixelSize(R.dimen.notification_panel_margin_top);
        }
        boolean z2 = quickSettingsControllerImpl.mUseLargeScreenShadeHeader;
        ShadeHeaderController shadeHeaderController = quickSettingsControllerImpl.mShadeHeaderController;
        if (shadeHeaderController.largeScreenActive != z2) {
            shadeHeaderController.largeScreenActive = z2;
            shadeHeaderController.updateTransition();
        }
        quickSettingsControllerImpl.mAmbientState.mStackTopMargin = max;
        quickSettingsControllerImpl.mQuickQsHeaderHeight = quickSettingsControllerImpl.mLargeScreenShadeHeaderHeight;
        quickSettingsControllerImpl.mEnableClipping = quickSettingsControllerImpl.mResources.getBoolean(R.bool.qs_enable_clipping);
        WindowMetrics currentWindowMetrics = ((WindowManager) notificationPanelView.getContext().getSystemService(WindowManager.class)).getCurrentWindowMetrics();
        quickSettingsControllerImpl.mCachedGestureInsets = currentWindowMetrics.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());
        quickSettingsControllerImpl.mCachedWindowWidth = currentWindowMetrics.getBounds().width();
        this.mNotificationsQSContainerController.updateResources();
        updateKeyguardStatusViewAlignment();
        this.mKeyguardMediaController.refreshMediaPosition("NotificationPanelViewController.updateResources");
        if (z) {
            if ((((BaseHeadsUpManager) this.mHeadsUpManager).mHasPinnedNotification || this.mHeadsUpAnimatingAway) && this.mBarState == 0) {
                onPanelStateChanged$1(0);
            }
            boolean z3 = this.mSplitShadeEnabled;
            ShadeLogger shadeLogger = this.mShadeLog;
            shadeLogger.getClass();
            LogLevel logLevel = LogLevel.VERBOSE;
            ShadeLogger$logSplitShadeChanged$2 shadeLogger$logSplitShadeChanged$2 = new Function1() { // from class: com.android.systemui.shade.ShadeLogger$logSplitShadeChanged$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return "Split shade state changed: split shade ".concat(((LogMessage) obj).getBool1() ? "enabled" : "disabled");
                }
            };
            LogBuffer logBuffer = shadeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("systemui.shade", logLevel, shadeLogger$logSplitShadeChanged$2, null);
            ((LogMessageImpl) obtain).bool1 = z3;
            logBuffer.commit(obtain);
            QS qs2 = quickSettingsControllerImpl.mQs;
            if (qs2 != null) {
                qs2.setOverScrollAmount(0);
            }
            ScrimView scrimView = this.mScrimController.mNotificationsScrim;
            if (scrimView != null) {
                scrimView.setTranslationY(0);
            }
            if (!isKeyguardShowing$1() && isPanelExpanded()) {
                quickSettingsControllerImpl.setExpanded(this.mSplitShadeEnabled);
            }
            if (isKeyguardShowing$1() && quickSettingsControllerImpl.getExpanded() && this.mSplitShadeEnabled) {
                ((StatusBarStateControllerImpl) this.mStatusBarStateController).setState(2, false);
            }
            updateClockAppearance();
            quickSettingsControllerImpl.updateQsState$1();
        }
        this.mSplitShadeFullTransitionDistance = this.mResources.getDimensionPixelSize(R.dimen.split_shade_full_transition_distance);
        Trace.endSection();
    }

    public final void updateStatusViewController() {
        KeyguardViewConfigurator keyguardViewConfigurator = this.mKeyguardViewConfigurator;
        if (keyguardViewConfigurator.keyguardStatusViewController == null) {
            KeyguardStatusView keyguardStatusView = (KeyguardStatusView) LayoutInflater.from(keyguardViewConfigurator.context).inflate(R.layout.keyguard_status_view, (ViewGroup) null);
            Display display = keyguardViewConfigurator.context.getDisplay();
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = keyguardViewConfigurator.keyguardStatusViewComponentFactory;
            keyguardStatusView.getClass();
            display.getClass();
            KeyguardStatusViewController keyguardStatusViewController = new DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, keyguardStatusView, display).getKeyguardStatusViewController();
            keyguardStatusViewController.init$9();
            keyguardViewConfigurator.keyguardStatusViewController = keyguardStatusViewController;
        }
        this.mKeyguardStatusViewController = keyguardViewConfigurator.keyguardStatusViewController;
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void updateSystemUiStateFlags() {
        boolean z = false;
        boolean z2 = isPanelExpanded() && !isCollapsing();
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.setFlag(1073741824L, z2);
        boolean isFullyExpanded = isFullyExpanded();
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
        sysUiState.setFlag(4L, isFullyExpanded && !quickSettingsControllerImpl.getExpanded());
        if (isFullyExpanded() && quickSettingsControllerImpl.getExpanded()) {
            z = true;
        }
        sysUiState.setFlag(2048L, z);
        sysUiState.commitUpdate(this.mDisplayId);
    }

    @Override // com.android.systemui.shade.ShadeViewController
    public final void updateTouchableRegion() {
        NotificationPanelView notificationPanelView = this.mView;
        notificationPanelView.requestLayout();
        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController;
        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
        notificationShadeWindowState.forceWindowCollapsed = true;
        notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        notificationPanelView.post(new NotificationPanelViewController$$ExternalSyntheticLambda0(this, 9));
    }

    public final void updateUserSwitcherFlags() {
        boolean z;
        boolean z2 = this.mResources.getBoolean(android.R.bool.config_letterboxIsHorizontalReachabilityEnabled);
        this.mKeyguardUserSwitcherEnabled = z2;
        if (z2) {
            if (((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.QS_USER_DETAIL_SHORTCUT)) {
                z = true;
                this.mKeyguardQsUserSwitchEnabled = z;
            }
        }
        z = false;
        this.mKeyguardQsUserSwitchEnabled = z;
    }

    public final void updateViewControllers(FrameLayout frameLayout, KeyguardUserSwitcherView keyguardUserSwitcherView) {
        updateStatusViewController();
        KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
        if (keyguardUserSwitcherController != null) {
            keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(false);
        }
        this.mKeyguardQsUserSwitchController = null;
        this.mKeyguardUserSwitcherController = null;
        if (frameLayout != null) {
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.mKeyguardQsUserSwitchComponentFactory;
            daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
            KeyguardQsUserSwitchController keyguardQsUserSwitchController = (KeyguardQsUserSwitchController) ((Provider) new DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, frameLayout).setShellMainThread).get();
            this.mKeyguardQsUserSwitchController = keyguardQsUserSwitchController;
            keyguardQsUserSwitchController.init$9();
            ((KeyguardStatusBarView) this.mKeyguardStatusBarViewController.mView).mKeyguardUserSwitcherEnabled = true;
            return;
        }
        if (keyguardUserSwitcherView == null) {
            ((KeyguardStatusBarView) this.mKeyguardStatusBarViewController.mView).mKeyguardUserSwitcherEnabled = false;
            return;
        }
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2 = this.mKeyguardUserSwitcherComponentFactory;
        daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.getClass();
        KeyguardUserSwitcherController keyguardUserSwitcherController2 = (KeyguardUserSwitcherController) ((Provider) new DaggerSysUIGoogleGlobalRootComponent$WMComponentBuilder(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory2.sysUIGoogleSysUIComponentImpl, keyguardUserSwitcherView).setShellMainThread).get();
        this.mKeyguardUserSwitcherController = keyguardUserSwitcherController2;
        keyguardUserSwitcherController2.init$9();
        ((KeyguardStatusBarView) this.mKeyguardStatusBarViewController.mView).mKeyguardUserSwitcherEnabled = true;
    }

    public final void updateVisibility$6() {
        this.mView.setVisibility((this.mHeadsUpAnimatingAway || this.mHeadsUpPinnedMode || isExpanded() || this.mBouncerShowing) ? 0 : 4);
    }

    public final void collapse(float f, boolean z) {
        if (canBeCollapsed()) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = this.mQsController;
            if (quickSettingsControllerImpl.getExpanded()) {
                quickSettingsControllerImpl.setExpandImmediate(true);
                setShowShelfOnly(true);
            }
            if (canBeCollapsed()) {
                cancelHeightAnimator();
                notifyExpandingStarted();
                setClosing(true);
                this.mUpdateFlingOnLayout = false;
                if (z) {
                    this.mNextCollapseSpeedUpFactor = f;
                    this.mView.postDelayed(this.mFlingCollapseRunnable, 120L);
                } else {
                    fling(0.0f, f, false, false);
                }
            }
        }
    }

    @Override // com.android.systemui.shade.ShadeSurface
    public final void setWillPlayDelayedDozeAmountAnimation() {
    }
}
