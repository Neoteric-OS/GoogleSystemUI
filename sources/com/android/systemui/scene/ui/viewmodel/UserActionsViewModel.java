package com.android.systemui.scene.ui.viewmodel;

import com.android.systemui.lifecycle.ExclusiveActivatable;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UserActionsViewModel extends ExclusiveActivatable {
    public final StateFlowImpl _actions;
    public final ReadonlyStateFlow actions = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(MapsKt.emptyMap()));
}
