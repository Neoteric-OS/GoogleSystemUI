package com.android.systemui.keyguard.data.repository;

import android.app.admin.DevicePolicyManager;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricSettingsRepositoryKt {
    public static final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 access$and(Flow flow, Flow flow2) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, flow2, new BiometricSettingsRepositoryKt$and$1(3, null));
    }

    public static final boolean isNotActive(DevicePolicyManager devicePolicyManager, int i, int i2) {
        return (devicePolicyManager.getKeyguardDisabledFeatures(null, i) & i2) == 0;
    }
}
