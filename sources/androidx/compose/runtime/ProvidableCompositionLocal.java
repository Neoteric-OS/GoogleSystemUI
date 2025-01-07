package androidx.compose.runtime;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ProvidableCompositionLocal extends CompositionLocal {
    public abstract ProvidedValue defaultProvidedValue$runtime_release(Object obj);

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0034, code lost:
    
        if (r2 != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0036, code lost:
    
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0043, code lost:
    
        if (r2 == null) goto L17;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.ValueHolder updatedStateOf$runtime_release(androidx.compose.runtime.ProvidedValue r3, androidx.compose.runtime.ValueHolder r4) {
        /*
            r2 = this;
            boolean r2 = r4 instanceof androidx.compose.runtime.DynamicValueHolder
            r0 = 0
            if (r2 == 0) goto L18
            boolean r2 = r3.isDynamic
            if (r2 == 0) goto L46
            r0 = r4
            androidx.compose.runtime.DynamicValueHolder r0 = (androidx.compose.runtime.DynamicValueHolder) r0
            androidx.compose.runtime.MutableState r2 = r0.state
            java.lang.Object r4 = r3.getEffectiveValue$runtime_release()
            androidx.compose.runtime.SnapshotMutableStateImpl r2 = (androidx.compose.runtime.SnapshotMutableStateImpl) r2
            r2.setValue(r4)
            goto L46
        L18:
            boolean r2 = r4 instanceof androidx.compose.runtime.StaticValueHolder
            if (r2 == 0) goto L38
            boolean r2 = r3.explicitNull
            if (r2 != 0) goto L24
            java.lang.Object r2 = r3.providedValue
            if (r2 == 0) goto L46
        L24:
            boolean r2 = r3.isDynamic
            if (r2 != 0) goto L46
            java.lang.Object r2 = r3.getEffectiveValue$runtime_release()
            androidx.compose.runtime.StaticValueHolder r4 = (androidx.compose.runtime.StaticValueHolder) r4
            java.lang.Object r1 = r4.value
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r1)
            if (r2 == 0) goto L46
        L36:
            r0 = r4
            goto L46
        L38:
            boolean r2 = r4 instanceof androidx.compose.runtime.ComputedValueHolder
            if (r2 == 0) goto L46
            r3.getClass()
            androidx.compose.runtime.ComputedValueHolder r4 = (androidx.compose.runtime.ComputedValueHolder) r4
            kotlin.jvm.functions.Function1 r2 = r4.compute
            if (r2 != 0) goto L46
            goto L36
        L46:
            if (r0 != 0) goto L6a
            boolean r2 = r3.isDynamic
            if (r2 == 0) goto L60
            androidx.compose.runtime.DynamicValueHolder r2 = new androidx.compose.runtime.DynamicValueHolder
            androidx.compose.runtime.SnapshotMutationPolicy r4 = r3.mutationPolicy
            if (r4 != 0) goto L54
            androidx.compose.runtime.StructuralEqualityPolicy r4 = androidx.compose.runtime.StructuralEqualityPolicy.INSTANCE
        L54:
            androidx.compose.runtime.ParcelableSnapshotMutableState r0 = new androidx.compose.runtime.ParcelableSnapshotMutableState
            java.lang.Object r3 = r3.providedValue
            r0.<init>(r3, r4)
            r2.<init>(r0)
        L5e:
            r0 = r2
            goto L6a
        L60:
            androidx.compose.runtime.StaticValueHolder r2 = new androidx.compose.runtime.StaticValueHolder
            java.lang.Object r3 = r3.getEffectiveValue$runtime_release()
            r2.<init>(r3)
            goto L5e
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ProvidableCompositionLocal.updatedStateOf$runtime_release(androidx.compose.runtime.ProvidedValue, androidx.compose.runtime.ValueHolder):androidx.compose.runtime.ValueHolder");
    }
}
