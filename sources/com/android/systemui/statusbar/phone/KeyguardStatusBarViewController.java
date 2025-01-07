package com.android.systemui.statusbar.phone;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricSourceType;
import android.os.UserManager;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.keyguard.CarrierTextController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.disableflags.DisableStateTracker;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.phone.fragment.StatusBarSystemEventDefaultAnimator;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoController$OnUserInfoChangedListener;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.ui.viewmodel.KeyguardStatusBarViewModel;
import com.android.systemui.user.ui.binder.StatusBarUserChipViewBinder;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlin.collections.ArraysKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardStatusBarViewController extends ViewController {
    public static final AnimationProperties KEYGUARD_HUN_PROPERTIES;
    public final AnonymousClass2 mAnimationCallback;
    public final SystemStatusAnimationSchedulerImpl mAnimationScheduler;
    public final KeyguardStatusBarViewController$$ExternalSyntheticLambda5 mAnimatorUpdateListener;
    public final Executor mBackgroundExecutor;
    public final BatteryController mBatteryController;
    public boolean mBatteryListening;
    public final BatteryMeterViewController mBatteryMeterViewController;
    public final AnonymousClass3 mBatteryStateChangeCallback;
    public final BiometricUnlockController mBiometricUnlockController;
    public final List mBlockedIcons;
    public final CarrierTextController mCarrierTextController;
    public final CommandQueue mCommandQueue;
    public final KeyguardStatusBarViewController$$ExternalSyntheticLambda6 mCommunalConsumer;
    public final CommunalSceneInteractor mCommunalSceneInteractor;
    public boolean mCommunalShowing;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass1 mConfigurationListener;
    public boolean mDelayShowingKeyguardStatusBar;
    public final DisableStateTracker mDisableStateTracker;
    public boolean mDozing;
    public float mExplicitAlpha;
    public boolean mFirstBypassAttempt;
    public final AnimatableProperty.AnonymousClass6 mHeadsUpShowingAmountAnimation;
    public final StatusBarContentInsetsProvider mInsetsProvider;
    public final KeyguardBypassController mKeyguardBypassController;
    public float mKeyguardHeadsUpShowingAmount;
    public final KeyguardStateController mKeyguardStateController;
    public float mKeyguardStatusBarAnimateAlpha;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final Object mLock;
    public final KeyguardLogger mLogger;
    public final Executor mMainExecutor;
    public final int mNotificationsHeaderCollideDistance;
    public final KeyguardStatusBarViewController$$ExternalSyntheticLambda4 mOnUserInfoChangedListener;
    public final SecureSettings mSecureSettings;
    public final NotificationPanelViewController.AnonymousClass10 mShadeViewStateProvider;
    public boolean mShowingKeyguardHeadsUp;
    public final StatusBarIconController mStatusBarIconController;
    public int mStatusBarState;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final AnonymousClass5 mStatusBarStateListener;
    public final StatusBarUserChipViewModel mStatusBarUserChipViewModel;
    public final StatusOverlayHoverListenerFactory mStatusOverlayHoverListenerFactory;
    public StatusBarSystemEventDefaultAnimator mSystemEventAnimator;
    public float mSystemEventAnimatorAlpha;
    public View mSystemIconsContainer;
    public TintedIconManager mTintedIconManager;
    public final TintedIconManager.Factory mTintedIconManagerFactory;
    public final UserInfoControllerImpl mUserInfoController;
    public final UserManager mUserManager;
    public final AnonymousClass8 mVolumeSettingObserver;

    static {
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 360L;
        KEYGUARD_HUN_PROPERTIES = animationProperties;
    }

    /* JADX WARN: Type inference failed for: r5v10, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$5] */
    /* JADX WARN: Type inference failed for: r5v11, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda6] */
    /* JADX WARN: Type inference failed for: r5v15, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$8] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$1] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$2] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$3] */
    /* JADX WARN: Type inference failed for: r5v7, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda4] */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda5] */
    public KeyguardStatusBarViewController(KeyguardStatusBarView keyguardStatusBarView, CarrierTextController carrierTextController, ConfigurationController configurationController, SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, BatteryController batteryController, UserInfoControllerImpl userInfoControllerImpl, StatusBarIconController statusBarIconController, TintedIconManager.Factory factory, BatteryMeterViewController batteryMeterViewController, NotificationPanelViewController.AnonymousClass10 anonymousClass10, KeyguardStateController keyguardStateController, KeyguardBypassController keyguardBypassController, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStatusBarViewModel keyguardStatusBarViewModel, BiometricUnlockController biometricUnlockController, SysuiStatusBarStateController sysuiStatusBarStateController, StatusBarContentInsetsProvider statusBarContentInsetsProvider, UserManager userManager, StatusBarUserChipViewModel statusBarUserChipViewModel, SecureSettings secureSettings, CommandQueue commandQueue, Executor executor, Executor executor2, KeyguardLogger keyguardLogger, StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory, CommunalSceneInteractor communalSceneInteractor) {
        super(keyguardStatusBarView);
        this.mKeyguardHeadsUpShowingAmount = 0.0f;
        BiConsumer biConsumer = new BiConsumer() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                keyguardStatusBarViewController.getClass();
                keyguardStatusBarViewController.mKeyguardHeadsUpShowingAmount = ((Float) obj2).floatValue();
                keyguardStatusBarViewController.updateViewState();
            }
        };
        Function function = new Function() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Float.valueOf(KeyguardStatusBarViewController.this.mKeyguardHeadsUpShowingAmount);
            }
        };
        AnimatableProperty.AnonymousClass7 anonymousClass7 = AnimatableProperty.Y;
        this.mHeadsUpShowingAmountAnimation = new AnimatableProperty.AnonymousClass6(R.id.keyguard_hun_animator_end_tag, R.id.keyguard_hun_animator_start_tag, R.id.keyguard_hun_animator_tag, new AnimatableProperty.AnonymousClass5("KEYGUARD_HEADS_UP_SHOWING_AMOUNT", function, biConsumer));
        this.mLock = new Object();
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                keyguardStatusBarViewController.mBackgroundExecutor.execute(new KeyguardStatusBarViewController$$ExternalSyntheticLambda8(keyguardStatusBarViewController, 1));
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                ((KeyguardStatusBarView) keyguardStatusBarViewController.mView).loadDimens();
                keyguardStatusBarViewController.mSystemEventAnimator = new StatusBarSystemEventDefaultAnimator(keyguardStatusBarViewController.mView.getResources(), new KeyguardStatusBarViewController$$ExternalSyntheticLambda10(keyguardStatusBarViewController, 0), new KeyguardStatusBarViewController$$ExternalSyntheticLambda10(keyguardStatusBarViewController, 1), keyguardStatusBarViewController.mSystemEventAnimator.isAnimationRunning);
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                KeyguardStatusBarView keyguardStatusBarView2 = (KeyguardStatusBarView) keyguardStatusBarViewController.mView;
                keyguardStatusBarView2.mCarrierLabel.setTextAppearance(R.style.TextAppearance_StatusBar_Clock);
                BatteryMeterView batteryMeterView = keyguardStatusBarView2.mBatteryView;
                TextView textView = batteryMeterView.mBatteryPercentView;
                if (textView != null) {
                    batteryMeterView.removeView(textView);
                    batteryMeterView.mBatteryPercentView = null;
                }
                batteryMeterView.updateShowPercent();
                TextView textView2 = (TextView) keyguardStatusBarView2.mUserSwitcherContainer.findViewById(R.id.current_user_name);
                if (textView2 != null) {
                    textView2.setTextAppearance(R.style.TextAppearance_StatusBar_UserChip);
                }
                ((KeyguardStatusBarView) keyguardStatusBarViewController.mView).onThemeChanged(keyguardStatusBarViewController.mTintedIconManager);
            }
        };
        this.mAnimationCallback = new SystemStatusAnimationCallback() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.2
            @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
            public final Animator onSystemEventAnimationBegin() {
                return KeyguardStatusBarViewController.this.mSystemEventAnimator.onSystemEventAnimationBegin();
            }

            @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
            public final Animator onSystemEventAnimationFinish(boolean z) {
                return KeyguardStatusBarViewController.this.mSystemEventAnimator.onSystemEventAnimationFinish(z);
            }
        };
        this.mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.3
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
                KeyguardStatusBarView keyguardStatusBarView2 = (KeyguardStatusBarView) KeyguardStatusBarViewController.this.mView;
                if (keyguardStatusBarView2.mBatteryCharging != z2) {
                    keyguardStatusBarView2.mBatteryCharging = z2;
                    keyguardStatusBarView2.updateVisibilities();
                }
            }
        };
        this.mOnUserInfoChangedListener = new UserInfoController$OnUserInfoChangedListener() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda4
            @Override // com.android.systemui.statusbar.policy.UserInfoController$OnUserInfoChangedListener
            public final void onUserInfoChanged(Drawable drawable) {
                ((KeyguardStatusBarView) KeyguardStatusBarViewController.this.mView).mMultiUserAvatar.setImageDrawable(drawable);
            }
        };
        this.mAnimatorUpdateListener = new Animator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda5
            @Override // androidx.core.animation.Animator.AnimatorUpdateListener
            public final void onAnimationUpdate(Animator animator) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                keyguardStatusBarViewController.getClass();
                keyguardStatusBarViewController.mKeyguardStatusBarAnimateAlpha = ((Float) ((ValueAnimator) animator).getAnimatedValue()).floatValue();
                keyguardStatusBarViewController.updateViewState();
            }
        };
        this.mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.4
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                if (keyguardStatusBarViewController.mFirstBypassAttempt && keyguardStatusBarViewController.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed(z)) {
                    keyguardStatusBarViewController.mDelayShowingKeyguardStatusBar = true;
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onBiometricRunningStateChanged(BiometricSourceType biometricSourceType, boolean z) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                int i = keyguardStatusBarViewController.mStatusBarState;
                boolean z2 = true;
                if (i != 1 && i != 2) {
                    z2 = false;
                }
                if (z || !keyguardStatusBarViewController.mFirstBypassAttempt || !z2 || keyguardStatusBarViewController.mDozing || keyguardStatusBarViewController.mDelayShowingKeyguardStatusBar) {
                    return;
                }
                BiometricUnlockController biometricUnlockController2 = keyguardStatusBarViewController.mBiometricUnlockController;
                if (biometricUnlockController2.isWakeAndUnlock() || biometricUnlockController2.mMode == 5) {
                    return;
                }
                keyguardStatusBarViewController.mFirstBypassAttempt = false;
                keyguardStatusBarViewController.animateKeyguardStatusBarIn();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onFinishedGoingToSleep(int i) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                keyguardStatusBarViewController.mFirstBypassAttempt = keyguardStatusBarViewController.mKeyguardBypassController.getBypassEnabled();
                keyguardStatusBarViewController.mDelayShowingKeyguardStatusBar = false;
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardVisibilityChanged(boolean z) {
                if (z) {
                    KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                    keyguardStatusBarViewController.mBackgroundExecutor.execute(new KeyguardStatusBarViewController$$ExternalSyntheticLambda8(keyguardStatusBarViewController, 1));
                }
            }
        };
        this.mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.5
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                KeyguardStatusBarViewController.this.mStatusBarState = i;
            }
        };
        this.mCommunalConsumer = new Consumer() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                keyguardStatusBarViewController.getClass();
                keyguardStatusBarViewController.mCommunalShowing = ((Boolean) obj).booleanValue();
                keyguardStatusBarViewController.updateViewState();
            }
        };
        this.mBlockedIcons = new ArrayList();
        this.mKeyguardStatusBarAnimateAlpha = 1.0f;
        this.mSystemEventAnimatorAlpha = 1.0f;
        this.mExplicitAlpha = -1.0f;
        this.mVolumeSettingObserver = new ContentObserver() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.8
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                KeyguardStatusBarViewController.this.updateBlockedIcons();
            }
        };
        this.mCarrierTextController = carrierTextController;
        this.mConfigurationController = configurationController;
        this.mAnimationScheduler = systemStatusAnimationSchedulerImpl;
        this.mBatteryController = batteryController;
        this.mUserInfoController = userInfoControllerImpl;
        this.mStatusBarIconController = statusBarIconController;
        this.mTintedIconManagerFactory = factory;
        this.mBatteryMeterViewController = batteryMeterViewController;
        this.mShadeViewStateProvider = anonymousClass10;
        this.mKeyguardStateController = keyguardStateController;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mBiometricUnlockController = biometricUnlockController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mInsetsProvider = statusBarContentInsetsProvider;
        this.mUserManager = userManager;
        this.mStatusBarUserChipViewModel = statusBarUserChipViewModel;
        this.mSecureSettings = secureSettings;
        this.mCommandQueue = commandQueue;
        this.mMainExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mLogger = keyguardLogger;
        this.mCommunalSceneInteractor = communalSceneInteractor;
        this.mFirstBypassAttempt = keyguardBypassController.getBypassEnabled();
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.6
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardFadingAwayChanged() {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                if (((KeyguardStateControllerImpl) keyguardStatusBarViewController.mKeyguardStateController).mKeyguardFadingAway) {
                    return;
                }
                keyguardStatusBarViewController.mFirstBypassAttempt = false;
                keyguardStatusBarViewController.mDelayShowingKeyguardStatusBar = false;
            }
        });
        Resources resources = keyguardStatusBarView.getResources();
        updateBlockedIcons();
        this.mNotificationsHeaderCollideDistance = resources.getDimensionPixelSize(R.dimen.header_notifications_collide_distance);
        keyguardStatusBarView.mKeyguardUserAvatarEnabled = !statusBarUserChipViewModel.chipEnabled;
        keyguardStatusBarView.updateVisibilities();
        this.mSystemEventAnimator = new StatusBarSystemEventDefaultAnimator(keyguardStatusBarView.getResources(), new KeyguardStatusBarViewController$$ExternalSyntheticLambda10(this, 0), new KeyguardStatusBarViewController$$ExternalSyntheticLambda10(this, 1), false);
        this.mDisableStateTracker = new DisableStateTracker(new KeyguardStatusBarViewController$$ExternalSyntheticLambda7(this));
        this.mStatusOverlayHoverListenerFactory = statusOverlayHoverListenerFactory;
    }

    public final void animateKeyguardStatusBarIn() {
        this.mLogger.buffer.log("KeyguardStatusBarViewController", LogLevel.DEBUG, "animating status bar in", null);
        if (this.mDisableStateTracker.isDisabled) {
            return;
        }
        ((KeyguardStatusBarView) this.mView).setVisibility(0);
        ((KeyguardStatusBarView) this.mView).setAlpha(0.0f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(this.mAnimatorUpdateListener);
        ofFloat.setDuration(360L);
        ofFloat.setInterpolator(InterpolatorsAndroidX.LINEAR_OUT_SLOW_IN);
        ofFloat.start(false);
    }

    public List getBlockedIcons() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mBlockedIcons);
        }
        return arrayList;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mCarrierTextController.init$9();
        this.mBatteryMeterViewController.init$9();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        StatusBarUserChipViewBinder.bind(((KeyguardStatusBarView) this.mView).mUserSwitcherContainer, this.mStatusBarUserChipViewModel);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        this.mAnimationScheduler.addCallback(this.mAnimationCallback);
        this.mUserInfoController.addCallback(this.mOnUserInfoChangedListener);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        Integer valueOf = Integer.valueOf(((KeyguardStatusBarView) this.mView).getDisplay().getDisplayId());
        DisableStateTracker disableStateTracker = this.mDisableStateTracker;
        disableStateTracker.displayId = valueOf;
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) disableStateTracker);
        if (this.mTintedIconManager == null) {
            TintedIconManager create = this.mTintedIconManagerFactory.create((ViewGroup) ((KeyguardStatusBarView) this.mView).findViewById(R.id.statusIcons), StatusBarLocation.KEYGUARD);
            this.mTintedIconManager = create;
            create.setBlockList(getBlockedIcons());
            ((StatusBarIconControllerImpl) this.mStatusBarIconController).addIconGroup(this.mTintedIconManager);
        }
        View findViewById = ((KeyguardStatusBarView) this.mView).findViewById(R.id.system_icons);
        this.mSystemIconsContainer = findViewById;
        ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(((KeyguardStatusBarView) this.mView).mDarkChange);
        StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory = this.mStatusOverlayHoverListenerFactory;
        statusOverlayHoverListenerFactory.getClass();
        this.mSystemIconsContainer.setOnHoverListener(new StatusOverlayHoverListener(findViewById, statusOverlayHoverListenerFactory.configurationController, statusOverlayHoverListenerFactory.resources, new StatusOverlayHoverListenerFactory$createDarkAwareListener$$inlined$map$1(readonlyStateFlow, statusOverlayHoverListenerFactory, findViewById)));
        ((KeyguardStatusBarView) this.mView).setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                KeyguardStatusBarViewController keyguardStatusBarViewController = KeyguardStatusBarViewController.this;
                return ((KeyguardStatusBarView) keyguardStatusBarViewController.mView).updateWindowInsets(windowInsets, keyguardStatusBarViewController.mInsetsProvider);
            }
        });
        this.mSecureSettings.registerContentObserverForUserSync("status_bar_show_vibrate_icon", false, (ContentObserver) this.mVolumeSettingObserver, -1);
        this.mBackgroundExecutor.execute(new KeyguardStatusBarViewController$$ExternalSyntheticLambda8(this, 1));
        ((KeyguardStatusBarView) this.mView).onThemeChanged(this.mTintedIconManager);
        JavaAdapterKt.collectFlow(this.mView, this.mCommunalSceneInteractor.isCommunalVisible, this.mCommunalConsumer);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mSystemIconsContainer.setOnHoverListener(null);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        this.mAnimationScheduler.removeCallback(this.mAnimationCallback);
        this.mUserInfoController.removeCallback(this.mOnUserInfoChangedListener);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardUpdateMonitorCallback);
        DisableStateTracker disableStateTracker = this.mDisableStateTracker;
        disableStateTracker.displayId = null;
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) disableStateTracker);
        this.mSecureSettings.unregisterContentObserverSync(this.mVolumeSettingObserver);
        TintedIconManager tintedIconManager = this.mTintedIconManager;
        if (tintedIconManager != null) {
            StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) this.mStatusBarIconController;
            statusBarIconControllerImpl.getClass();
            tintedIconManager.destroy();
            statusBarIconControllerImpl.mIconGroups.remove(tintedIconManager);
        }
    }

    public void updateBlockedIcons() {
        Resources resources = this.mView.getResources();
        SecureSettings secureSettings = this.mSecureSettings;
        List list = ArraysKt.toList(resources.getStringArray(R.array.config_collapsed_statusbar_icon_blocklist));
        String string = resources.getString(android.R.string.time_of_day);
        boolean z = secureSettings.getIntForUser("status_bar_show_vibrate_icon", 0, -2) == 0;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!((String) obj).equals(string) || z) {
                arrayList.add(obj);
            }
        }
        synchronized (this.mLock) {
            this.mBlockedIcons.clear();
            this.mBlockedIcons.addAll(arrayList);
        }
        this.mMainExecutor.execute(new KeyguardStatusBarViewController$$ExternalSyntheticLambda8(this, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateForHeadsUp(boolean r5) {
        /*
            r4 = this;
            int r0 = r4.mStatusBarState
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L1b
            com.android.systemui.shade.NotificationPanelViewController$10 r0 = r4.mShadeViewStateProvider
            com.android.systemui.shade.NotificationPanelViewController r0 = com.android.systemui.shade.NotificationPanelViewController.this
            com.android.systemui.statusbar.phone.HeadsUpAppearanceController r0 = r0.mHeadsUpAppearanceController
            if (r0 == 0) goto L16
            boolean r0 = r0.shouldBeVisible$1()
            if (r0 == 0) goto L16
            r0 = 1
            goto L17
        L16:
            r0 = 0
        L17:
            if (r0 == 0) goto L1b
            r0 = r2
            goto L1c
        L1b:
            r0 = r1
        L1c:
            boolean r3 = r4.mShowingKeyguardHeadsUp
            if (r3 == r0) goto L56
            r4.mShowingKeyguardHeadsUp = r0
            int r3 = r4.mStatusBarState
            if (r3 != r2) goto L27
            r1 = r2
        L27:
            r2 = 0
            com.android.systemui.statusbar.notification.AnimatableProperty$6 r3 = r4.mHeadsUpShowingAmountAnimation
            if (r1 == 0) goto L3a
            android.view.View r4 = r4.mView
            com.android.systemui.statusbar.phone.KeyguardStatusBarView r4 = (com.android.systemui.statusbar.phone.KeyguardStatusBarView) r4
            if (r0 == 0) goto L34
            r2 = 1065353216(0x3f800000, float:1.0)
        L34:
            com.android.systemui.statusbar.notification.stack.AnimationProperties r0 = com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.KEYGUARD_HUN_PROPERTIES
            com.android.systemui.statusbar.notification.PropertyAnimator.setProperty(r4, r3, r2, r0, r5)
            goto L56
        L3a:
            android.view.View r4 = r4.mView
            com.android.systemui.statusbar.phone.KeyguardStatusBarView r4 = (com.android.systemui.statusbar.phone.KeyguardStatusBarView) r4
            int r5 = r3.getAnimatorTag()
            java.lang.Object r5 = r4.getTag(r5)
            android.animation.ValueAnimator r5 = (android.animation.ValueAnimator) r5
            if (r5 == 0) goto L4d
            r5.cancel()
        L4d:
            com.android.systemui.statusbar.notification.AnimatableProperty$5 r5 = r3.val$property
            java.lang.Float r0 = java.lang.Float.valueOf(r2)
            r5.set(r4, r0)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.KeyguardStatusBarViewController.updateForHeadsUp(boolean):void");
    }

    public final void updateViewState() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor;
        float panelViewExpandedHeight;
        int height;
        boolean z = true;
        if (this.mStatusBarState == 1) {
            NotificationPanelViewController.AnonymousClass10 anonymousClass10 = this.mShadeViewStateProvider;
            float min = 1.0f - Math.min(1.0f, NotificationPanelViewController.this.mQsController.getLockscreenShadeDragProgress() * 2.0f);
            float f = this.mExplicitAlpha;
            if (f == -1.0f) {
                if (this.mStatusBarState == 1) {
                    panelViewExpandedHeight = anonymousClass10.getPanelViewExpandedHeight();
                    height = ((KeyguardStatusBarView) this.mView).getHeight() + this.mNotificationsHeaderCollideDistance;
                } else {
                    panelViewExpandedHeight = anonymousClass10.getPanelViewExpandedHeight();
                    height = ((KeyguardStatusBarView) this.mView).getHeight();
                }
                f = (1.0f - this.mKeyguardHeadsUpShowingAmount) * Math.min((float) Math.pow(MathUtils.saturate(panelViewExpandedHeight / height), 0.75d), min) * this.mKeyguardStatusBarAnimateAlpha;
            }
            if (this.mSystemEventAnimator.isAnimationRunning) {
                f = Math.min(f, this.mSystemEventAnimatorAlpha);
            } else {
                ((KeyguardStatusBarView) this.mView).setTranslationX(0.0f);
            }
            if ((!this.mFirstBypassAttempt || (systemUIDeviceEntryFaceAuthInteractor = this.mKeyguardUpdateMonitor.mFaceAuthInteractor) == null || !systemUIDeviceEntryFaceAuthInteractor.canFaceAuthRun()) && !this.mDelayShowingKeyguardStatusBar) {
                z = false;
            }
            DisableStateTracker disableStateTracker = this.mDisableStateTracker;
            int i = disableStateTracker.isDisabled ? 4 : (f == 0.0f || this.mDozing || z || disableStateTracker.isDisabled || this.mCommunalShowing) ? 4 : 0;
            ((KeyguardStatusBarView) this.mView).setAlpha(f);
            ((KeyguardStatusBarView) this.mView).setVisibility(i);
        }
    }
}
