package com.android.keyguard;

import android.view.View;
import android.view.ViewGroup;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardInputViewController;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSecurityViewFlipperController extends ViewController {
    public final AsyncLayoutInflater mAsyncLayoutInflater;
    public final List mChildren;
    public final FeatureFlags mFeatureFlags;
    public final KeyguardInputViewController.Factory mKeyguardSecurityViewControllerFactory;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnViewInflatedCallback {
        void onViewInflated(KeyguardInputViewController keyguardInputViewController);
    }

    static {
        boolean z = KeyguardConstants.DEBUG;
    }

    public KeyguardSecurityViewFlipperController(KeyguardSecurityViewFlipper keyguardSecurityViewFlipper, AsyncLayoutInflater asyncLayoutInflater, KeyguardInputViewController.Factory factory, FeatureFlags featureFlags) {
        super(keyguardSecurityViewFlipper);
        this.mChildren = new ArrayList();
        this.mKeyguardSecurityViewControllerFactory = factory;
        this.mAsyncLayoutInflater = asyncLayoutInflater;
        this.mFeatureFlags = featureFlags;
    }

    public final void asynchronouslyInflateView(final KeyguardSecurityModel.SecurityMode securityMode, final KeyguardSecurityCallback keyguardSecurityCallback, final OnViewInflatedCallback onViewInflatedCallback) {
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        int ordinal = securityMode.ordinal();
        int i = ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? ordinal != 5 ? ordinal != 6 ? 0 : R.layout.keyguard_sim_puk_view : R.layout.keyguard_sim_pin_view : R.layout.keyguard_pin_view : R.layout.keyguard_password_view : R.layout.keyguard_pattern_view;
        if (i != 0) {
            ViewGroup viewGroup = (ViewGroup) this.mView;
            AsyncLayoutInflater.OnInflateFinishedListener onInflateFinishedListener = new AsyncLayoutInflater.OnInflateFinishedListener() { // from class: com.android.keyguard.KeyguardSecurityViewFlipperController$$ExternalSyntheticLambda0
                @Override // androidx.asynclayoutinflater.view.AsyncLayoutInflater.OnInflateFinishedListener
                public final void onInflateFinished(View view, ViewGroup viewGroup2) {
                    KeyguardInputViewController keyguardSimPukViewController;
                    KeyguardSecurityViewFlipperController keyguardSecurityViewFlipperController = KeyguardSecurityViewFlipperController.this;
                    ((KeyguardSecurityViewFlipper) keyguardSecurityViewFlipperController.mView).addView(view);
                    KeyguardInputView keyguardInputView = (KeyguardInputView) view;
                    KeyguardInputViewController.Factory factory = keyguardSecurityViewFlipperController.mKeyguardSecurityViewControllerFactory;
                    factory.getClass();
                    EmergencyButton emergencyButton = (EmergencyButton) keyguardInputView.findViewById(R.id.emergency_call_button);
                    EmergencyButtonController.Factory factory2 = factory.mEmergencyButtonControllerFactory;
                    EmergencyButtonController emergencyButtonController = new EmergencyButtonController(emergencyButton, factory2.mConfigurationController, factory2.mKeyguardUpdateMonitor, factory2.mPowerManager, factory2.mActivityTaskManager, factory2.mShadeController, factory2.mTelecomManager, factory2.mMetricsLogger, factory2.mLockPatternUtils, factory2.mMainExecutor, factory2.mBackgroundExecutor, factory2.mSelectedUserInteractor, factory2.mMSDLPlayer);
                    boolean z = keyguardInputView instanceof KeyguardPatternView;
                    BouncerHapticPlayer bouncerHapticPlayer = factory.mBouncerHapticPlayer;
                    KeyguardMessageAreaController.Factory factory3 = factory.mMessageAreaControllerFactory;
                    KeyguardSecurityModel.SecurityMode securityMode2 = securityMode;
                    KeyguardSecurityCallback keyguardSecurityCallback2 = keyguardSecurityCallback;
                    if (z) {
                        keyguardSimPukViewController = new KeyguardPatternViewController((KeyguardPatternView) keyguardInputView, factory.mKeyguardUpdateMonitor, securityMode2, factory.mLockPatternUtils, keyguardSecurityCallback2, factory.mLatencyTracker, factory.mFalsingCollector, emergencyButtonController, factory3, factory.mDevicePostureController, factory.mFeatureFlags, factory.mSelectedUserInteractor, bouncerHapticPlayer);
                    } else {
                        boolean z2 = keyguardInputView instanceof KeyguardPasswordView;
                        UserActivityNotifier userActivityNotifier = factory.mUserActivityNotifier;
                        if (z2) {
                            keyguardSimPukViewController = new KeyguardPasswordViewController((KeyguardPasswordView) keyguardInputView, factory.mKeyguardUpdateMonitor, securityMode2, factory.mLockPatternUtils, keyguardSecurityCallback2, factory3, factory.mLatencyTracker, factory.mInputMethodManager, emergencyButtonController, factory.mResources, factory.mFalsingCollector, factory.mKeyguardViewController, factory.mDevicePostureController, factory.mFeatureFlags, factory.mSelectedUserInteractor, factory.mKeyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
                        } else {
                            boolean z3 = keyguardInputView instanceof KeyguardPINView;
                            LiftToActivateListener liftToActivateListener = factory.mLiftToActivateListener;
                            if (z3) {
                                keyguardSimPukViewController = new KeyguardPinViewController((KeyguardPINView) keyguardInputView, factory.mKeyguardUpdateMonitor, securityMode2, factory.mLockPatternUtils, keyguardSecurityCallback2, factory3, factory.mLatencyTracker, liftToActivateListener, emergencyButtonController, factory.mFalsingCollector, factory.mDevicePostureController, factory.mFeatureFlags, factory.mSelectedUserInteractor, factory.mUiEventLogger, factory.mKeyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
                            } else if (keyguardInputView instanceof KeyguardSimPinView) {
                                keyguardSimPukViewController = new KeyguardSimPinViewController((KeyguardSimPinView) keyguardInputView, factory.mKeyguardUpdateMonitor, securityMode2, factory.mLockPatternUtils, keyguardSecurityCallback2, factory3, factory.mLatencyTracker, liftToActivateListener, factory.mTelephonyManager, factory.mFalsingCollector, emergencyButtonController, factory.mSelectedUserInteractor, factory.mKeyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
                            } else {
                                if (!(keyguardInputView instanceof KeyguardSimPukView)) {
                                    throw new RuntimeException("Unable to find controller for " + keyguardInputView);
                                }
                                keyguardSimPukViewController = new KeyguardSimPukViewController((KeyguardSimPukView) keyguardInputView, factory.mKeyguardUpdateMonitor, securityMode2, factory.mLockPatternUtils, keyguardSecurityCallback2, factory3, factory.mLatencyTracker, liftToActivateListener, factory.mTelephonyManager, factory.mFalsingCollector, emergencyButtonController, factory.mSelectedUserInteractor, factory.mKeyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
                            }
                        }
                    }
                    keyguardSimPukViewController.init$9();
                    keyguardSecurityViewFlipperController.mChildren.add(keyguardSimPukViewController);
                    KeyguardSecurityViewFlipperController.OnViewInflatedCallback onViewInflatedCallback2 = onViewInflatedCallback;
                    if (onViewInflatedCallback2 != null) {
                        onViewInflatedCallback2.onViewInflated(keyguardSimPukViewController);
                        UnreleasedFlag unreleasedFlag2 = Flags.NULL_FLAG;
                        keyguardSecurityViewFlipperController.mFeatureFlags.getClass();
                    }
                }
            };
            AsyncLayoutInflater asyncLayoutInflater = this.mAsyncLayoutInflater;
            asyncLayoutInflater.inflateInternal(i, viewGroup, onInflateFinishedListener, asyncLayoutInflater.mInflater, null);
        }
    }

    public void getSecurityView(KeyguardSecurityModel.SecurityMode securityMode, KeyguardSecurityCallback keyguardSecurityCallback, OnViewInflatedCallback onViewInflatedCallback) {
        for (KeyguardInputViewController keyguardInputViewController : this.mChildren) {
            if (keyguardInputViewController.mSecurityMode == securityMode) {
                onViewInflatedCallback.onViewInflated(keyguardInputViewController);
                return;
            }
        }
        asynchronouslyInflateView(securityMode, keyguardSecurityCallback, onViewInflatedCallback);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
    }
}
