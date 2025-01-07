package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DynamicProvidableCompositionLocal extends ProvidableCompositionLocal {
    public final SnapshotMutationPolicy policy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicProvidableCompositionLocal(Function0 function0) {
        super(function0);
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.policy = structuralEqualityPolicy;
    }

    @Override // androidx.compose.runtime.ProvidableCompositionLocal
    public final ProvidedValue defaultProvidedValue$runtime_release(Object obj) {
        return new ProvidedValue(this, obj, obj == null, this.policy, true);
    }
}
