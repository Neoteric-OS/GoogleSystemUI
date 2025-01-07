package com.android.systemui.keyguard.util;

import android.hardware.biometrics.BiometricSourceType;
import com.android.keyguard.KeyguardUpdateMonitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IndicationHelper {
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BiometricSourceType.values().length];
            try {
                iArr[BiometricSourceType.FINGERPRINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BiometricSourceType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public IndicationHelper(KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    public final boolean shouldSuppressErrorMsg(BiometricSourceType biometricSourceType, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[biometricSourceType.ordinal()];
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.keyguardUpdateMonitor;
        if (i2 != 1) {
            if (i2 == 2 && ((!keyguardUpdateMonitor.isUnlockingWithBiometricAllowed(true) && i != 7 && i != 9) || i == 5 || i == 2)) {
                return true;
            }
        } else if ((!keyguardUpdateMonitor.isUnlockingWithBiometricAllowed(true) && i != 7 && i != 9) || i == 5 || i == 10 || i == 19) {
            return true;
        }
        return false;
    }
}
