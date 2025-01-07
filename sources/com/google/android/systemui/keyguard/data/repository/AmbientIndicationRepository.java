package com.google.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AmbientIndicationRepository {
    public final StateFlowImpl ambientMusic = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl reverseChargingMessage = StateFlowKt.MutableStateFlow("");
    public final StateFlowImpl wirelessChargingMessage = StateFlowKt.MutableStateFlow("");
}
