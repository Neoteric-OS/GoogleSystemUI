package com.android.systemui.globalactions.data.repository;

import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsRepository {
    public final StateFlowImpl _isVisible;
    public final ReadonlyStateFlow isVisible;

    public GlobalActionsRepository() {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isVisible = MutableStateFlow;
        this.isVisible = new ReadonlyStateFlow(MutableStateFlow);
    }
}
