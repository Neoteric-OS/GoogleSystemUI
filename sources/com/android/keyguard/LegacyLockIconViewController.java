package com.android.keyguard;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.biometrics.BiometricSourceType;
import android.os.VibrationAttributes;
import android.util.DisplayMetrics;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.doze.util.BurnInHelperKt;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LegacyLockIconViewController implements Dumpable, LockIconViewController {
    public final AnonymousClass1 mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public final AuthController mAuthController;
    public boolean mCanDismissLockScreen;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public int mDefaultPaddingPx;
    public boolean mDownDetected;
    final Consumer mDozeTransitionCallback;
    public final DelayableExecutor mExecutor;
    public final FalsingManager mFalsingManager;
    public final FeatureFlags mFeatureFlags;
    public float mInterpolatedDarkAmount;
    public boolean mIsActiveDreamLockscreenHosted;
    final Consumer mIsActiveDreamLockscreenHostedCallback;
    public boolean mIsBouncerShowing;
    public boolean mIsDozing;
    final Consumer mIsDozingCallback;
    public boolean mIsKeyguardShowing;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final StatusBarKeyguardViewManager mKeyguardViewController;
    public CharSequence mLockedLabel;
    public ExecutorImpl.ExecutionToken mLongPressCancelRunnable;
    public final long mLongPressTimeout;
    public final int mMaxBurnInOffsetX;
    public final int mMaxBurnInOffsetY;
    public final PrimaryBouncerInteractor mPrimaryBouncerInteractor;
    public final Resources mResources;
    public boolean mRunningFPS;
    public boolean mShowAodLockIcon;
    public boolean mShowAodUnlockedIcon;
    public boolean mShowLockIcon;
    public boolean mShowUnlockIcon;
    public int mStatusBarState;
    public final StatusBarStateController mStatusBarStateController;
    public boolean mUdfpsEnrolled;
    public boolean mUdfpsSupported;
    public CharSequence mUnlockedLabel;
    public VelocityTracker mVelocityTracker;
    public final VibratorHelper mVibrator;
    public LockIconView mView;
    public int mActivePointerId = -1;
    public final Rect mSensorTouchLocation = new Rect();
    public final AnonymousClass3 mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.keyguard.LegacyLockIconViewController.3
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozeAmountChanged(float f, float f2) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            FeatureFlags featureFlags = legacyLockIconViewController.mFeatureFlags;
            UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
            featureFlags.getClass();
            legacyLockIconViewController.mInterpolatedDarkAmount = f2;
            LockIconView lockIconView = legacyLockIconViewController.mView;
            lockIconView.mDozeAmount = f2;
            lockIconView.updateColorAndBackgroundVisibility();
            legacyLockIconViewController.updateBurnInOffsets$1();
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(boolean z) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            FeatureFlags featureFlags = legacyLockIconViewController.mFeatureFlags;
            UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
            featureFlags.getClass();
            legacyLockIconViewController.mIsDozing = z;
            legacyLockIconViewController.updateBurnInOffsets$1();
            legacyLockIconViewController.updateVisibility$5();
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.mStatusBarState = i;
            legacyLockIconViewController.updateVisibility$5();
        }
    };
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.LegacyLockIconViewController.4
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricRunningStateChanged(BiometricSourceType biometricSourceType, boolean z) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            boolean z2 = legacyLockIconViewController.mRunningFPS;
            if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                legacyLockIconViewController.mRunningFPS = z;
            }
            if (z2 != legacyLockIconViewController.mRunningFPS) {
                legacyLockIconViewController.updateVisibility$5();
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardBouncerStateChanged(boolean z) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.mIsBouncerShowing = z;
            legacyLockIconViewController.updateVisibility$5();
        }
    };
    public final AnonymousClass5 mKeyguardStateCallback = new KeyguardStateController.Callback() { // from class: com.android.keyguard.LegacyLockIconViewController.5
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardFadingAwayChanged() {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.updateKeyguardShowing();
            legacyLockIconViewController.updateVisibility$5();
        }

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.mCanDismissLockScreen = ((KeyguardStateControllerImpl) legacyLockIconViewController.mKeyguardStateController).mCanDismissLockScreen;
            legacyLockIconViewController.mIsBouncerShowing = legacyLockIconViewController.mKeyguardViewController.isBouncerShowing();
            legacyLockIconViewController.updateKeyguardShowing();
            legacyLockIconViewController.updateVisibility$5();
        }

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onUnlockedChanged() {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.mCanDismissLockScreen = ((KeyguardStateControllerImpl) legacyLockIconViewController.mKeyguardStateController).mCanDismissLockScreen;
            legacyLockIconViewController.updateKeyguardShowing();
            legacyLockIconViewController.updateVisibility$5();
        }
    };
    public final AnonymousClass6 mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.LegacyLockIconViewController.6
        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onConfigChanged(Configuration configuration) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.updateConfiguration$2();
            legacyLockIconViewController.mView.updateColorAndBackgroundVisibility();
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onThemeChanged() {
            LegacyLockIconViewController.this.mView.updateColorAndBackgroundVisibility();
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onUiModeChanged() {
            LegacyLockIconViewController.this.mView.updateColorAndBackgroundVisibility();
        }
    };
    public final AnonymousClass7 mAuthControllerCallback = new AuthController.Callback() { // from class: com.android.keyguard.LegacyLockIconViewController.7
        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onAllAuthenticatorsRegistered(int i) {
            if (i == 2) {
                LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
                legacyLockIconViewController.getClass();
                ((ExecutorImpl) legacyLockIconViewController.mExecutor).execute(new LegacyLockIconViewController$$ExternalSyntheticLambda8(legacyLockIconViewController, 1));
            }
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onEnrollmentsChanged(int i) {
            if (i == 2) {
                LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
                legacyLockIconViewController.getClass();
                ((ExecutorImpl) legacyLockIconViewController.mExecutor).execute(new LegacyLockIconViewController$$ExternalSyntheticLambda8(legacyLockIconViewController, 1));
            }
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onUdfpsLocationChanged(UdfpsOverlayParams udfpsOverlayParams) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            legacyLockIconViewController.getClass();
            ((ExecutorImpl) legacyLockIconViewController.mExecutor).execute(new LegacyLockIconViewController$$ExternalSyntheticLambda8(legacyLockIconViewController, 1));
        }
    };
    public final LegacyLockIconViewController$$ExternalSyntheticLambda6 mA11yClickListener = new View.OnClickListener() { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            LegacyLockIconViewController.this.onLongPress();
        }
    };
    public final LegacyLockIconViewController$$ExternalSyntheticLambda7 mAccessibilityStateChangeListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda7
        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public final void onAccessibilityStateChanged(boolean z) {
            LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
            if (legacyLockIconViewController.mAccessibilityManager.isEnabled()) {
                legacyLockIconViewController.mView.setOnClickListener(legacyLockIconViewController.mA11yClickListener);
            } else {
                legacyLockIconViewController.mView.setOnClickListener(null);
            }
        }
    };

    static {
        int i = DisplayMetrics.DENSITY_DEVICE_STABLE;
        VibrationAttributes.createForUsage(18);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.keyguard.LegacyLockIconViewController$1] */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda6] */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda7] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.keyguard.LegacyLockIconViewController$3] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.keyguard.LegacyLockIconViewController$5] */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.android.keyguard.LegacyLockIconViewController$6] */
    /* JADX WARN: Type inference failed for: r2v9, types: [com.android.keyguard.LegacyLockIconViewController$7] */
    public LegacyLockIconViewController(StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardStateController keyguardStateController, FalsingManager falsingManager, AuthController authController, DumpManager dumpManager, AccessibilityManager accessibilityManager, ConfigurationController configurationController, DelayableExecutor delayableExecutor, VibratorHelper vibratorHelper, AuthRippleController authRippleController, Resources resources, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardInteractor keyguardInteractor, FeatureFlags featureFlags, PrimaryBouncerInteractor primaryBouncerInteractor, Context context, Lazy lazy) {
        final int i = 0;
        this.mDozeTransitionCallback = new Consumer(this) { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda3
            public final /* synthetic */ LegacyLockIconViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                LegacyLockIconViewController legacyLockIconViewController = this.f$0;
                switch (i2) {
                    case 0:
                        Float f = (Float) obj;
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mInterpolatedDarkAmount = f.floatValue();
                        LockIconView lockIconView = legacyLockIconViewController.mView;
                        lockIconView.mDozeAmount = f.floatValue();
                        lockIconView.updateColorAndBackgroundVisibility();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        break;
                    case 1:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsDozing = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                    default:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsActiveDreamLockscreenHosted = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mIsDozingCallback = new Consumer(this) { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda3
            public final /* synthetic */ LegacyLockIconViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                LegacyLockIconViewController legacyLockIconViewController = this.f$0;
                switch (i22) {
                    case 0:
                        Float f = (Float) obj;
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mInterpolatedDarkAmount = f.floatValue();
                        LockIconView lockIconView = legacyLockIconViewController.mView;
                        lockIconView.mDozeAmount = f.floatValue();
                        lockIconView.updateColorAndBackgroundVisibility();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        break;
                    case 1:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsDozing = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                    default:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsActiveDreamLockscreenHosted = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mIsActiveDreamLockscreenHostedCallback = new Consumer(this) { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda3
            public final /* synthetic */ LegacyLockIconViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i3;
                LegacyLockIconViewController legacyLockIconViewController = this.f$0;
                switch (i22) {
                    case 0:
                        Float f = (Float) obj;
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mInterpolatedDarkAmount = f.floatValue();
                        LockIconView lockIconView = legacyLockIconViewController.mView;
                        lockIconView.mDozeAmount = f.floatValue();
                        lockIconView.updateColorAndBackgroundVisibility();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        break;
                    case 1:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsDozing = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateBurnInOffsets$1();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                    default:
                        legacyLockIconViewController.getClass();
                        legacyLockIconViewController.mIsActiveDreamLockscreenHosted = ((Boolean) obj).booleanValue();
                        legacyLockIconViewController.updateVisibility$5();
                        break;
                }
            }
        };
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mAuthController = authController;
        this.mKeyguardViewController = statusBarKeyguardViewManager;
        this.mKeyguardStateController = keyguardStateController;
        this.mFalsingManager = falsingManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mConfigurationController = configurationController;
        this.mExecutor = delayableExecutor;
        this.mVibrator = vibratorHelper;
        this.mFeatureFlags = featureFlags;
        this.mPrimaryBouncerInteractor = primaryBouncerInteractor;
        this.mMaxBurnInOffsetX = resources.getDimensionPixelSize(R.dimen.udfps_burn_in_offset_x);
        this.mMaxBurnInOffsetY = resources.getDimensionPixelSize(R.dimen.udfps_burn_in_offset_y);
        this.mUnlockedLabel = resources.getString(R.string.accessibility_unlock_button);
        this.mLockedLabel = resources.getString(R.string.accessibility_lock_icon);
        this.mLongPressTimeout = resources.getInteger(R.integer.config_lockIconLongPress);
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "LockIconViewController", this);
        this.mResources = resources;
        this.mContext = context;
        this.mAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: com.android.keyguard.LegacyLockIconViewController.1
            public final AccessibilityNodeInfo.AccessibilityAction mAccessibilityAuthenticateHint;
            public final AccessibilityNodeInfo.AccessibilityAction mAccessibilityEnterHint;

            {
                this.mAccessibilityAuthenticateHint = new AccessibilityNodeInfo.AccessibilityAction(16, LegacyLockIconViewController.this.mResources.getString(R.string.accessibility_authenticate_hint));
                this.mAccessibilityEnterHint = new AccessibilityNodeInfo.AccessibilityAction(16, LegacyLockIconViewController.this.mResources.getString(R.string.accessibility_enter_hint));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
                if (legacyLockIconViewController.mIsBouncerShowing) {
                    return;
                }
                if (legacyLockIconViewController.mUdfpsSupported || legacyLockIconViewController.mShowUnlockIcon) {
                    if (legacyLockIconViewController.mShowLockIcon) {
                        accessibilityNodeInfo.addAction(this.mAccessibilityAuthenticateHint);
                    } else if (legacyLockIconViewController.mShowUnlockIcon) {
                        accessibilityNodeInfo.addAction(this.mAccessibilityEnterHint);
                    }
                }
            }
        };
    }

    public final void cancelTouches() {
        this.mDownDetected = false;
        ExecutorImpl.ExecutionToken executionToken = this.mLongPressCancelRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void dozeTimeTick() {
        updateBurnInOffsets$1();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mUdfpsSupported: "), this.mUdfpsSupported, printWriter, "mUdfpsEnrolled: "), this.mUdfpsEnrolled, printWriter, "mIsKeyguardShowing: ");
        m.append(this.mIsKeyguardShowing);
        printWriter.println(m.toString());
        printWriter.println();
        StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder(" mShowUnlockIcon: "), this.mShowUnlockIcon, printWriter, " mShowLockIcon: "), this.mShowLockIcon, printWriter, " mShowAodUnlockedIcon: ");
        m2.append(this.mShowAodUnlockedIcon);
        printWriter.println(m2.toString());
        printWriter.println();
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder(" mIsDozing: "), this.mIsDozing, printWriter);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        printWriter.println(" isFlagEnabled(DOZING_MIGRATION_1): false");
        StringBuilder m3 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder(" mIsBouncerShowing: "), this.mIsBouncerShowing, printWriter, " mRunningFPS: "), this.mRunningFPS, printWriter, " mCanDismissLockScreen: "), this.mCanDismissLockScreen, printWriter, " mStatusBarState: ");
        m3.append(StatusBarState.toString(this.mStatusBarState));
        printWriter.println(m3.toString());
        StringBuilder m4 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder(" mInterpolatedDarkAmount: "), this.mInterpolatedDarkAmount, printWriter, " mSensorTouchLocation: ");
        m4.append(this.mSensorTouchLocation);
        printWriter.println(m4.toString());
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder(" mDefaultPaddingPx: "), this.mDefaultPaddingPx, printWriter, " mIsActiveDreamLockscreenHosted: "), this.mIsActiveDreamLockscreenHosted, printWriter);
        LockIconView lockIconView = this.mView;
        if (lockIconView != null) {
            lockIconView.dump(printWriter, strArr);
        }
    }

    public void onLongPress() {
        cancelTouches();
        if (this.mFalsingManager.isFalseLongTap(1)) {
            return;
        }
        this.mIsBouncerShowing = true;
        updateVisibility$5();
        vibrateOnLongPress();
        this.mKeyguardViewController.showPrimaryBouncer(true);
    }

    public final void registerCallbacks$1() {
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        this.mAuthController.addCallback(this.mAuthControllerCallback);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        this.mStatusBarStateController.addCallback(this.mStatusBarStateListener);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateCallback);
        this.mAccessibilityManager.addAccessibilityStateChangeListener(this.mAccessibilityStateChangeListener);
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void setAlpha(float f) {
        this.mView.setAlpha(f);
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void setLockIconView(LockIconView lockIconView) {
        this.mView = lockIconView;
        lockIconView.setAccessibilityDelegate(this.mAccessibilityDelegate);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        updateIsUdfpsEnrolled();
        updateConfiguration$2();
        updateKeyguardShowing();
        this.mIsBouncerShowing = this.mKeyguardViewController.isBouncerShowing();
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        this.mIsDozing = statusBarStateController.isDozing();
        this.mInterpolatedDarkAmount = statusBarStateController.getDozeAmount();
        this.mRunningFPS = this.mKeyguardUpdateMonitor.isFingerprintDetectionRunning();
        this.mCanDismissLockScreen = ((KeyguardStateControllerImpl) this.mKeyguardStateController).mCanDismissLockScreen;
        this.mStatusBarState = statusBarStateController.getState();
        this.mView.updateColorAndBackgroundVisibility();
        this.mDownDetected = false;
        updateBurnInOffsets$1();
        updateVisibility$5();
        if (this.mAccessibilityManager.isEnabled()) {
            this.mView.setOnClickListener(this.mA11yClickListener);
        } else {
            this.mView.setOnClickListener(null);
        }
        lockIconView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.keyguard.LegacyLockIconViewController.2
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                LegacyLockIconViewController.this.registerCallbacks$1();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
                legacyLockIconViewController.mAuthController.removeCallback(legacyLockIconViewController.mAuthControllerCallback);
                ((ConfigurationControllerImpl) legacyLockIconViewController.mConfigurationController).removeCallback(legacyLockIconViewController.mConfigurationListener);
                legacyLockIconViewController.mKeyguardUpdateMonitor.removeCallback(legacyLockIconViewController.mKeyguardUpdateMonitorCallback);
                legacyLockIconViewController.mStatusBarStateController.removeCallback(legacyLockIconViewController.mStatusBarStateListener);
                ((KeyguardStateControllerImpl) legacyLockIconViewController.mKeyguardStateController).removeCallback(legacyLockIconViewController.mKeyguardStateCallback);
                legacyLockIconViewController.mAccessibilityManager.removeAccessibilityStateChangeListener(legacyLockIconViewController.mAccessibilityStateChangeListener);
            }
        });
        if (lockIconView.isAttachedToWindow()) {
            registerCallbacks$1();
        }
        lockIconView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                LegacyLockIconViewController legacyLockIconViewController = LegacyLockIconViewController.this;
                if (!((!legacyLockIconViewController.mIsBouncerShowing && (legacyLockIconViewController.mUdfpsSupported || legacyLockIconViewController.mShowUnlockIcon)) ? motionEvent.getActionMasked() == 0 ? true : legacyLockIconViewController.mDownDetected : false)) {
                    legacyLockIconViewController.cancelTouches();
                    return false;
                }
                int actionMasked = motionEvent.getActionMasked();
                long j = legacyLockIconViewController.mLongPressTimeout;
                DelayableExecutor delayableExecutor = legacyLockIconViewController.mExecutor;
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked != 3) {
                                if (actionMasked != 7) {
                                    if (actionMasked != 9) {
                                        if (actionMasked != 10) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        legacyLockIconViewController.mVelocityTracker.addMovement(motionEvent);
                        legacyLockIconViewController.mVelocityTracker.computeCurrentVelocity(1000);
                        VelocityTracker velocityTracker = legacyLockIconViewController.mVelocityTracker;
                        int i = legacyLockIconViewController.mActivePointerId;
                        float sqrt = (float) Math.sqrt(Math.pow(velocityTracker.getYVelocity(i), 2.0d) + Math.pow(velocityTracker.getXVelocity(i), 2.0d));
                        if (motionEvent.getClassification() == 2 || sqrt <= 750.0f) {
                            return true;
                        }
                        legacyLockIconViewController.mLongPressCancelRunnable.run();
                        legacyLockIconViewController.mLongPressCancelRunnable = delayableExecutor.executeDelayed(new LegacyLockIconViewController$$ExternalSyntheticLambda8(legacyLockIconViewController, 0), j);
                        return true;
                    }
                    legacyLockIconViewController.cancelTouches();
                    return true;
                }
                if (!legacyLockIconViewController.mDownDetected && legacyLockIconViewController.mAccessibilityManager.isTouchExplorationEnabled()) {
                    legacyLockIconViewController.vibrateOnTouchExploration();
                }
                legacyLockIconViewController.mActivePointerId = motionEvent.getPointerId(0);
                VelocityTracker velocityTracker2 = legacyLockIconViewController.mVelocityTracker;
                if (velocityTracker2 == null) {
                    legacyLockIconViewController.mVelocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker2.clear();
                }
                legacyLockIconViewController.mVelocityTracker.addMovement(motionEvent);
                legacyLockIconViewController.mDownDetected = true;
                legacyLockIconViewController.mLongPressCancelRunnable = delayableExecutor.executeDelayed(new LegacyLockIconViewController$$ExternalSyntheticLambda8(legacyLockIconViewController, 0), j);
                return true;
            }
        });
    }

    public final void updateBurnInOffsets$1() {
        int i = this.mMaxBurnInOffsetX;
        float lerp = MathUtils.lerp(0.0f, BurnInHelperKt.getBurnInOffset(i * 2, true) - i, this.mInterpolatedDarkAmount);
        int i2 = this.mMaxBurnInOffsetY;
        float lerp2 = MathUtils.lerp(0.0f, BurnInHelperKt.getBurnInOffset(i2 * 2, false) - i2, this.mInterpolatedDarkAmount);
        this.mView.setTranslationX(lerp);
        this.mView.setTranslationY(lerp2);
    }

    public final void updateConfiguration$2() {
        int i = ((WindowManager) this.mContext.getSystemService(WindowManager.class)).getCurrentWindowMetrics().getBounds().right;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        this.mResources.getDimensionPixelSize(R.dimen.lock_icon_margin_bottom);
        this.mDefaultPaddingPx = this.mResources.getDimensionPixelSize(R.dimen.lock_icon_padding);
        this.mUnlockedLabel = this.mResources.getString(R.string.accessibility_unlock_button);
        this.mLockedLabel = this.mResources.getString(R.string.accessibility_lock_icon);
        int i2 = (int) (this.mDefaultPaddingPx * this.mAuthController.mScaleFactor);
        this.mView.mLockIcon.setPadding(i2, i2, i2, i2);
    }

    public final void updateIsUdfpsEnrolled() {
        boolean z = this.mUdfpsSupported;
        boolean z2 = this.mUdfpsEnrolled;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        boolean isUdfpsSupported = keyguardUpdateMonitor.mAuthController.isUdfpsSupported();
        this.mUdfpsSupported = isUdfpsSupported;
        LockIconView lockIconView = this.mView;
        lockIconView.mUseBackground = isUdfpsSupported;
        lockIconView.updateColorAndBackgroundVisibility();
        boolean isUdfpsEnrolled = keyguardUpdateMonitor.isUdfpsEnrolled();
        this.mUdfpsEnrolled = isUdfpsEnrolled;
        if (z == this.mUdfpsSupported && z2 == isUdfpsEnrolled) {
            return;
        }
        updateVisibility$5();
    }

    public final void updateKeyguardShowing() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        this.mIsKeyguardShowing = keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mKeyguardGoingAway;
    }

    public final void updateVisibility$5() {
        boolean z = this.mIsKeyguardShowing;
        if (!z && !this.mIsDozing) {
            this.mView.setVisibility(4);
            return;
        }
        if (z && this.mIsActiveDreamLockscreenHosted) {
            this.mView.setVisibility(4);
            return;
        }
        boolean z2 = this.mUdfpsEnrolled;
        boolean z3 = false;
        boolean z4 = (!z2 || this.mShowUnlockIcon || this.mShowLockIcon || this.mShowAodUnlockedIcon || this.mShowAodLockIcon) ? false : true;
        boolean z5 = this.mCanDismissLockScreen;
        this.mShowLockIcon = (z5 || this.mIsDozing || this.mIsBouncerShowing || this.mStatusBarState != 1 || (z2 && this.mRunningFPS)) ? false : true;
        this.mShowUnlockIcon = z5 && !this.mIsDozing && !this.mIsBouncerShowing && this.mStatusBarState == 1;
        boolean z6 = this.mIsDozing;
        this.mShowAodUnlockedIcon = z6 && z2 && !this.mRunningFPS && z5;
        this.mShowAodLockIcon = z6 && z2 && !this.mRunningFPS && !z5;
        CharSequence contentDescription = this.mView.getContentDescription();
        if (this.mShowLockIcon) {
            if (z4) {
                this.mView.updateIcon(1, false);
            }
            this.mView.updateIcon(0, false);
            this.mView.setContentDescription(this.mLockedLabel);
            this.mView.setVisibility(0);
        } else if (this.mShowUnlockIcon) {
            if (z4) {
                this.mView.updateIcon(1, false);
            }
            this.mView.updateIcon(2, false);
            this.mView.setContentDescription(this.mUnlockedLabel);
            this.mView.setVisibility(0);
        } else if (this.mShowAodUnlockedIcon) {
            this.mView.updateIcon(2, true);
            this.mView.setContentDescription(this.mUnlockedLabel);
            this.mView.setVisibility(0);
        } else if (this.mShowAodLockIcon) {
            this.mView.updateIcon(0, true);
            this.mView.setContentDescription(this.mLockedLabel);
            this.mView.setVisibility(0);
        } else {
            this.mView.updateIcon(-1, false);
            this.mView.setVisibility(4);
            this.mView.setContentDescription(null);
        }
        if (!this.mPrimaryBouncerInteractor.isAnimatingAway() && this.mView.isVisibleToUser()) {
            z3 = true;
        }
        this.mView.setImportantForAccessibility(z3 ? 1 : 2);
        if (Objects.equals(contentDescription, this.mView.getContentDescription()) || this.mView.getContentDescription() == null || !z3) {
            return;
        }
        LockIconView lockIconView = this.mView;
        lockIconView.announceForAccessibility(lockIconView.getContentDescription());
    }

    public void vibrateOnLongPress() {
        LockIconView lockIconView = this.mView;
        this.mVibrator.getClass();
        lockIconView.performHapticFeedback(0);
    }

    public void vibrateOnTouchExploration() {
        LockIconView lockIconView = this.mView;
        this.mVibrator.getClass();
        lockIconView.performHapticFeedback(6);
    }

    @Override // com.android.keyguard.LockIconViewController
    public final boolean willHandleTouchWhileDozing(MotionEvent motionEvent) {
        this.mView.getHitRect(this.mSensorTouchLocation);
        if (this.mSensorTouchLocation.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && this.mView.getVisibility() == 0) {
            return (!this.mIsBouncerShowing && (this.mUdfpsSupported || this.mShowUnlockIcon)) ? motionEvent.getActionMasked() == 0 ? true : this.mDownDetected : false;
        }
        return false;
    }
}
