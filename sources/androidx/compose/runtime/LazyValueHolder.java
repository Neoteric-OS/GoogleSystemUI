package androidx.compose.runtime;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyValueHolder implements ValueHolder {
    public final Lazy current$delegate;

    public LazyValueHolder(Function0 function0) {
        this.current$delegate = LazyKt__LazyJVMKt.lazy(function0);
    }

    @Override // androidx.compose.runtime.ValueHolder
    public final Object readValue(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        return this.current$delegate.getValue();
    }
}
