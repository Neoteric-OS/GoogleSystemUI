package com.android.app.tracing.coroutines.flow;

import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowExt$currentFileName$1 implements Function {
    public static final FlowExt$currentFileName$1 INSTANCE = new FlowExt$currentFileName$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Stream) obj).limit(1L).findFirst();
    }
}
