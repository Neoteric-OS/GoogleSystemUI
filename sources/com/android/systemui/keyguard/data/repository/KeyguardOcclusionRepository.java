package com.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardOcclusionRepository {
    public final StateFlowImpl showWhenLockedActivityInfo = StateFlowKt.MutableStateFlow(new ShowWhenLockedActivityInfo(null, false));
}
