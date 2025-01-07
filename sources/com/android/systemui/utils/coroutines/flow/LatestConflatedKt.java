package com.android.systemui.utils.coroutines.flow;

import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LatestConflatedKt {
    public static final Flow flatMapLatestConflated(Function2 function2, Flow flow) {
        return FlowKt.buffer$default(FlowKt.transformLatest(flow, new LatestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1(null, function2)), -1);
    }
}
