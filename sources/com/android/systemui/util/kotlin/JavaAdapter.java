package com.android.systemui.util.kotlin;

import java.util.function.Consumer;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class JavaAdapter {
    public final CoroutineScope scope;

    public JavaAdapter(CoroutineScope coroutineScope) {
        this.scope = coroutineScope;
    }

    public final StandaloneCoroutine alwaysCollectFlow(Flow flow, Consumer consumer) {
        return BuildersKt.launch$default(this.scope, null, null, new JavaAdapter$alwaysCollectFlow$1(flow, consumer, null), 3);
    }
}
