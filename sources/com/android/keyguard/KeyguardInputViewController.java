package com.android.keyguard;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardInputViewController extends ViewController implements KeyguardSecurityView {
    public final BouncerHapticPlayer mBouncerHapticPlayer;
    public final EmergencyButtonController mEmergencyButtonController;
    public final KeyguardSecurityCallback mKeyguardSecurityCallback;
    public final KeyguardMessageAreaController mMessageAreaController;
    public final AnonymousClass1 mNullCallback;
    public boolean mPaused;
    public final KeyguardSecurityModel.SecurityMode mSecurityMode;
    public final SelectedUserInteractor mSelectedUserInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardInputViewController$1, reason: invalid class name */
    public final class AnonymousClass1 implements KeyguardSecurityCallback {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BouncerHapticPlayer mBouncerHapticPlayer;
        public final DevicePostureController mDevicePostureController;
        public final EmergencyButtonController.Factory mEmergencyButtonControllerFactory;
        public final FalsingCollector mFalsingCollector;
        public final FeatureFlags mFeatureFlags;
        public final InputMethodManager mInputMethodManager;
        public final KeyguardKeyboardInteractor mKeyguardKeyboardInteractor;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
        public final StatusBarKeyguardViewManager mKeyguardViewController;
        public final LatencyTracker mLatencyTracker;
        public final LiftToActivateListener mLiftToActivateListener;
        public final LockPatternUtils mLockPatternUtils;
        public final DelayableExecutor mMainExecutor;
        public final KeyguardMessageAreaController.Factory mMessageAreaControllerFactory;
        public final Resources mResources;
        public final SelectedUserInteractor mSelectedUserInteractor;
        public final TelephonyManager mTelephonyManager;
        public final UiEventLogger mUiEventLogger;
        public final UserActivityNotifier mUserActivityNotifier;

        public Factory(KeyguardUpdateMonitor keyguardUpdateMonitor, LockPatternUtils lockPatternUtils, LatencyTracker latencyTracker, KeyguardMessageAreaController.Factory factory, InputMethodManager inputMethodManager, DelayableExecutor delayableExecutor, Resources resources, LiftToActivateListener liftToActivateListener, TelephonyManager telephonyManager, FalsingCollector falsingCollector, EmergencyButtonController.Factory factory2, DevicePostureController devicePostureController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, FeatureFlags featureFlags, SelectedUserInteractor selectedUserInteractor, UiEventLogger uiEventLogger, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mLockPatternUtils = lockPatternUtils;
            this.mLatencyTracker = latencyTracker;
            this.mMessageAreaControllerFactory = factory;
            this.mInputMethodManager = inputMethodManager;
            this.mResources = resources;
            this.mLiftToActivateListener = liftToActivateListener;
            this.mTelephonyManager = telephonyManager;
            this.mEmergencyButtonControllerFactory = factory2;
            this.mFalsingCollector = falsingCollector;
            this.mDevicePostureController = devicePostureController;
            this.mKeyguardViewController = statusBarKeyguardViewManager;
            this.mFeatureFlags = featureFlags;
            this.mSelectedUserInteractor = selectedUserInteractor;
            this.mUiEventLogger = uiEventLogger;
            this.mKeyguardKeyboardInteractor = keyguardKeyboardInteractor;
            this.mBouncerHapticPlayer = bouncerHapticPlayer;
            this.mUserActivityNotifier = userActivityNotifier;
        }
    }

    public KeyguardInputViewController(KeyguardInputView keyguardInputView, KeyguardSecurityModel.SecurityMode securityMode, KeyguardSecurityCallback keyguardSecurityCallback, EmergencyButtonController emergencyButtonController, KeyguardMessageAreaController.Factory factory, SelectedUserInteractor selectedUserInteractor, BouncerHapticPlayer bouncerHapticPlayer) {
        super(keyguardInputView);
        this.mNullCallback = new AnonymousClass1();
        this.mSecurityMode = securityMode;
        this.mKeyguardSecurityCallback = keyguardSecurityCallback;
        this.mEmergencyButtonController = emergencyButtonController;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mBouncerHapticPlayer = bouncerHapticPlayer;
        try {
            KeyguardMessageAreaController keyguardMessageAreaController = new KeyguardMessageAreaController((BouncerKeyguardMessageArea) keyguardInputView.requireViewById(R.id.bouncer_message_area), factory.mKeyguardUpdateMonitor, factory.mConfigurationController);
            this.mMessageAreaController = keyguardMessageAreaController;
            keyguardMessageAreaController.init$9();
            keyguardMessageAreaController.setIsVisible(true);
        } catch (IllegalArgumentException unused) {
            Log.e("KeyguardInputViewController", "Ensure that a BouncerKeyguardMessageArea is included in the layout");
        }
    }

    public abstract int getInitialMessageResId();

    public final KeyguardSecurityCallback getKeyguardSecurityCallback() {
        return this.mPaused ? this.mNullCallback : this.mKeyguardSecurityCallback;
    }

    public abstract void onPause();

    public abstract void onResume(int i);

    @Override // com.android.systemui.util.ViewController
    public void onViewAttached() {
        updateMessageAreaVisibility();
        KeyguardMessageAreaController keyguardMessageAreaController = this.mMessageAreaController;
        if (!TextUtils.isEmpty(((KeyguardMessageArea) keyguardMessageAreaController.mView).getText()) || getInitialMessageResId() == 0) {
            return;
        }
        keyguardMessageAreaController.setMessage(((KeyguardInputView) this.mView).getResources().getString(getInitialMessageResId()), false);
    }

    public abstract void reset$1();

    public abstract void showMessage(CharSequence charSequence, ColorStateList colorStateList, boolean z);

    public abstract void showPromptReason(int i);

    public boolean startDisappearAnimation(Runnable runnable) {
        return ((KeyguardInputView) this.mView).startDisappearAnimation(runnable);
    }

    public void updateMessageAreaVisibility() {
        KeyguardMessageAreaController keyguardMessageAreaController = this.mMessageAreaController;
        if (keyguardMessageAreaController == null) {
            return;
        }
        KeyguardMessageArea keyguardMessageArea = (KeyguardMessageArea) keyguardMessageAreaController.mView;
        keyguardMessageArea.mIsDisabled = true;
        keyguardMessageArea.update();
    }
}
