package androidx.compose.runtime;

import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComputedProvidableCompositionLocal extends ProvidableCompositionLocal {
    public final ComputedValueHolder defaultValueHolder;

    public ComputedProvidableCompositionLocal(Function1 function1) {
        super(new Function0() { // from class: androidx.compose.runtime.ComputedProvidableCompositionLocal.1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ComposerKt.composeRuntimeError("Unexpected call to default provider");
                throw new KotlinNothingValueException();
            }
        });
        this.defaultValueHolder = new ComputedValueHolder(function1);
    }

    @Override // androidx.compose.runtime.ProvidableCompositionLocal
    public final ProvidedValue defaultProvidedValue$runtime_release(Object obj) {
        return new ProvidedValue(this, obj, obj == null, null, true);
    }

    @Override // androidx.compose.runtime.CompositionLocal
    public final ValueHolder getDefaultValueHolder$runtime_release() {
        return this.defaultValueHolder;
    }
}
