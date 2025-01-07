package com.android.keyguard;

import android.content.res.Configuration;
import android.hardware.biometrics.BiometricSourceType;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardMessageAreaController extends ViewController {
    public final AnnounceRunnable mAnnounceRunnable;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass3 mConfigurationListener;
    public final KeyguardUpdateMonitorCallback mInfoCallback;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public Pair mMessageBiometricSource;
    public final AnonymousClass1 mTextWatcher;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class AnnounceRunnable implements Runnable {
        public final WeakReference mHost;
        public CharSequence mTextToAnnounce;

        public AnnounceRunnable(KeyguardMessageArea keyguardMessageArea) {
            this.mHost = new WeakReference(keyguardMessageArea);
        }

        @Override // java.lang.Runnable
        public final void run() {
            View view = (View) this.mHost.get();
            if (view == null || !view.isVisibleToUser()) {
                return;
            }
            view.announceForAccessibility(this.mTextToAnnounce);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ConfigurationController mConfigurationController;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;

        public Factory(KeyguardUpdateMonitor keyguardUpdateMonitor, ConfigurationController configurationController) {
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mConfigurationController = configurationController;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.keyguard.KeyguardMessageAreaController$3] */
    public KeyguardMessageAreaController(KeyguardMessageArea keyguardMessageArea, KeyguardUpdateMonitor keyguardUpdateMonitor, ConfigurationController configurationController) {
        super(keyguardMessageArea);
        this.mMessageBiometricSource = null;
        this.mTextWatcher = new AnonymousClass1();
        this.mInfoCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardMessageAreaController.2
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onFinishedGoingToSleep(int i) {
                ((KeyguardMessageArea) KeyguardMessageAreaController.this.mView).setSelected(false);
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onStartedWakingUp() {
                ((KeyguardMessageArea) KeyguardMessageAreaController.this.mView).setSelected(true);
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.KeyguardMessageAreaController.3
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                int statusBarHeight;
                KeyguardMessageArea keyguardMessageArea2 = (KeyguardMessageArea) KeyguardMessageAreaController.this.mView;
                if (keyguardMessageArea2.mContainer == null || keyguardMessageArea2.mTopMargin == (statusBarHeight = SystemBarUtils.getStatusBarHeight(keyguardMessageArea2.getContext()))) {
                    return;
                }
                keyguardMessageArea2.mTopMargin = statusBarHeight;
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) keyguardMessageArea2.mContainer.getLayoutParams();
                marginLayoutParams.topMargin = keyguardMessageArea2.mTopMargin;
                keyguardMessageArea2.mContainer.setLayoutParams(marginLayoutParams);
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                ((KeyguardMessageArea) KeyguardMessageAreaController.this.mView).onDensityOrFontScaleChanged();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                ((KeyguardMessageArea) KeyguardMessageAreaController.this.mView).onThemeChanged();
            }
        };
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mConfigurationController = configurationController;
        this.mAnnounceRunnable = new AnnounceRunnable(keyguardMessageArea);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = this.mInfoCallback;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        keyguardUpdateMonitor.registerCallback(keyguardUpdateMonitorCallback);
        ((KeyguardMessageArea) this.mView).setSelected(keyguardUpdateMonitor.mDeviceInteractive);
        ((KeyguardMessageArea) this.mView).onThemeChanged();
        ((KeyguardMessageArea) this.mView).addTextChangedListener(this.mTextWatcher);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        this.mKeyguardUpdateMonitor.removeCallback(this.mInfoCallback);
        ((KeyguardMessageArea) this.mView).removeTextChangedListener(this.mTextWatcher);
    }

    public final void setIsVisible(boolean z) {
        KeyguardMessageArea keyguardMessageArea = (KeyguardMessageArea) this.mView;
        if (keyguardMessageArea.mIsVisible != z) {
            keyguardMessageArea.mIsVisible = z;
            keyguardMessageArea.update();
        }
    }

    public final void setMessage(CharSequence charSequence, boolean z) {
        long uptimeMillis = SystemClock.uptimeMillis();
        Pair pair = this.mMessageBiometricSource;
        if (pair != null && BiometricSourceType.FACE == null && pair.first == BiometricSourceType.FINGERPRINT && uptimeMillis - ((Long) pair.second).longValue() < 3500) {
            Log.d("KeyguardMessageAreaController", "Skip showing face message \"" + ((Object) charSequence) + "\"");
            return;
        }
        this.mMessageBiometricSource = new Pair(null, Long.valueOf(uptimeMillis));
        KeyguardMessageArea keyguardMessageArea = (KeyguardMessageArea) this.mView;
        if (keyguardMessageArea.mIsDisabled) {
            return;
        }
        keyguardMessageArea.setMessage(charSequence, z);
    }

    public final void setMessage(int i) {
        setMessage(i != 0 ? ((KeyguardMessageArea) this.mView).getResources().getString(i) : null, true);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardMessageAreaController$1, reason: invalid class name */
    public final class AnonymousClass1 implements TextWatcher {
        public AnonymousClass1() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(final Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                return;
            }
            KeyguardMessageAreaController keyguardMessageAreaController = KeyguardMessageAreaController.this;
            ((KeyguardMessageArea) keyguardMessageAreaController.mView).removeCallbacks(keyguardMessageAreaController.mAnnounceRunnable);
            KeyguardMessageAreaController keyguardMessageAreaController2 = KeyguardMessageAreaController.this;
            keyguardMessageAreaController2.mAnnounceRunnable.mTextToAnnounce = editable;
            ((KeyguardMessageArea) keyguardMessageAreaController2.mView).postDelayed(new Runnable() { // from class: com.android.keyguard.KeyguardMessageAreaController$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    KeyguardMessageAreaController.AnonymousClass1 anonymousClass1 = KeyguardMessageAreaController.AnonymousClass1.this;
                    if (editable == ((KeyguardMessageArea) KeyguardMessageAreaController.this.mView).getText()) {
                        KeyguardMessageAreaController.this.mAnnounceRunnable.run();
                    }
                }
            }, 250L);
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
