package com.android.systemui.devicepolicy;

import android.app.admin.DevicePolicyManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DevicePolicyManagerExtKt {
    public static boolean areKeyguardShortcutsDisabled$default(DevicePolicyManager devicePolicyManager, int i) {
        int keyguardDisabledFeatures = devicePolicyManager.getKeyguardDisabledFeatures(null, i);
        return (keyguardDisabledFeatures & 512) == 512 || (keyguardDisabledFeatures & Integer.MAX_VALUE) == Integer.MAX_VALUE;
    }
}
