package com.android.systemui.biometrics;

import com.android.systemui.statusbar.phone.BiometricUnlockController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleController$biometricModeListener$1 implements BiometricUnlockController.BiometricUnlockEventsListener {
    @Override // com.android.systemui.statusbar.phone.BiometricUnlockController.BiometricUnlockEventsListener
    public final void onBiometricUnlockedWithKeyguardDismissal() {
        throw new IllegalStateException("Legacy code path not supported when com.android.systemui.device_entry_udfps_refactor is enabled.");
    }
}
