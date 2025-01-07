package com.android.systemui.utils.coroutines.flow;

import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FlowConflatedKt {
    public static final Flow conflatedCallbackFlow(Function2 function2) {
        return FlowKt.buffer$default(FlowKt.callbackFlow(function2), -1);
    }
}
