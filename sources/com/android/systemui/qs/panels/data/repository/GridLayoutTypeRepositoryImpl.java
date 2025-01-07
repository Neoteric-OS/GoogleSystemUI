package com.android.systemui.qs.panels.data.repository;

import com.android.systemui.qs.panels.shared.model.PaginatedGridLayoutType;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GridLayoutTypeRepositoryImpl {
    public final StateFlowImpl _layout;
    public final ReadonlyStateFlow layout = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(PaginatedGridLayoutType.INSTANCE));
}
