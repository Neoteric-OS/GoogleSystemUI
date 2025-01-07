package com.android.systemui.volume.data.repository;

import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeDialogRepository {
    public final StateFlowImpl _isDialogVisible;
    public final ReadonlyStateFlow isDialogVisible;

    public VolumeDialogRepository() {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isDialogVisible = MutableStateFlow;
        this.isDialogVisible = new ReadonlyStateFlow(MutableStateFlow);
    }
}
