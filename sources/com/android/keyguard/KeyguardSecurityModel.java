package com.android.keyguard;

import android.R;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.telephony.SubscriptionManager;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.DejankUtils;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSecurityModel {
    public final boolean mIsPukScreenAvailable;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LockPatternUtils mLockPatternUtils;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SecurityMode {
        public static final /* synthetic */ SecurityMode[] $VALUES;
        public static final SecurityMode Invalid;
        public static final SecurityMode None;
        public static final SecurityMode PIN;
        public static final SecurityMode Password;
        public static final SecurityMode Pattern;
        public static final SecurityMode SimPin;
        public static final SecurityMode SimPuk;

        static {
            SecurityMode securityMode = new SecurityMode("Invalid", 0);
            Invalid = securityMode;
            SecurityMode securityMode2 = new SecurityMode("None", 1);
            None = securityMode2;
            SecurityMode securityMode3 = new SecurityMode("Pattern", 2);
            Pattern = securityMode3;
            SecurityMode securityMode4 = new SecurityMode("Password", 3);
            Password = securityMode4;
            SecurityMode securityMode5 = new SecurityMode("PIN", 4);
            PIN = securityMode5;
            SecurityMode securityMode6 = new SecurityMode("SimPin", 5);
            SimPin = securityMode6;
            SecurityMode securityMode7 = new SecurityMode("SimPuk", 6);
            SimPuk = securityMode7;
            $VALUES = new SecurityMode[]{securityMode, securityMode2, securityMode3, securityMode4, securityMode5, securityMode6, securityMode7};
        }

        public static SecurityMode valueOf(String str) {
            return (SecurityMode) Enum.valueOf(SecurityMode.class, str);
        }

        public static SecurityMode[] values() {
            return (SecurityMode[]) $VALUES.clone();
        }
    }

    public KeyguardSecurityModel(Resources resources, LockPatternUtils lockPatternUtils, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.mIsPukScreenAvailable = resources.getBoolean(R.bool.config_fillSecondaryBuiltInDisplayCutout);
        this.mLockPatternUtils = lockPatternUtils;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
    }

    public final SecurityMode getSecurityMode(final int i) {
        boolean z = this.mIsPukScreenAvailable;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (z && SubscriptionManager.isValidSubscriptionId(keyguardUpdateMonitor.getNextSubIdForState(3))) {
            return SecurityMode.SimPuk;
        }
        if (SubscriptionManager.isValidSubscriptionId(keyguardUpdateMonitor.getNextSubIdForState(2))) {
            return SecurityMode.SimPin;
        }
        int intValue = ((Integer) DejankUtils.whitelistIpcs(new Supplier() { // from class: com.android.keyguard.KeyguardSecurityModel$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                KeyguardSecurityModel keyguardSecurityModel = KeyguardSecurityModel.this;
                return Integer.valueOf(keyguardSecurityModel.mLockPatternUtils.getActivePasswordQuality(i));
            }
        })).intValue();
        if (intValue == 0) {
            return SecurityMode.None;
        }
        if (intValue == 65536) {
            return SecurityMode.Pattern;
        }
        if (intValue == 131072 || intValue == 196608) {
            return SecurityMode.PIN;
        }
        if (intValue == 262144 || intValue == 327680 || intValue == 393216 || intValue == 524288) {
            return SecurityMode.Password;
        }
        throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(intValue, "Unknown security quality:"));
    }
}
