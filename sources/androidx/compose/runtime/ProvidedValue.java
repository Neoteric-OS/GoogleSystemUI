package androidx.compose.runtime;

import kotlin.KotlinNothingValueException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProvidedValue {
    public boolean canOverride = true;
    public final ProvidableCompositionLocal compositionLocal;
    public final boolean explicitNull;
    public final boolean isDynamic;
    public final SnapshotMutationPolicy mutationPolicy;
    public final Object providedValue;

    public ProvidedValue(ProvidableCompositionLocal providableCompositionLocal, Object obj, boolean z, SnapshotMutationPolicy snapshotMutationPolicy, boolean z2) {
        this.compositionLocal = providableCompositionLocal;
        this.explicitNull = z;
        this.mutationPolicy = snapshotMutationPolicy;
        this.isDynamic = z2;
        this.providedValue = obj;
    }

    public final Object getEffectiveValue$runtime_release() {
        if (this.explicitNull) {
            return null;
        }
        Object obj = this.providedValue;
        if (obj != null) {
            return obj;
        }
        ComposerKt.composeRuntimeError("Unexpected form of a provided value");
        throw new KotlinNothingValueException();
    }
}
