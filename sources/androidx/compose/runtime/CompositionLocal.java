package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CompositionLocal {
    public final LazyValueHolder defaultValueHolder;

    public CompositionLocal(Function0 function0) {
        this.defaultValueHolder = new LazyValueHolder(function0);
    }

    public ValueHolder getDefaultValueHolder$runtime_release() {
        return this.defaultValueHolder;
    }
}
