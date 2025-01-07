package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StructuralEqualityPolicy implements SnapshotMutationPolicy {
    public static final StructuralEqualityPolicy INSTANCE = new StructuralEqualityPolicy();

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public final boolean equivalent(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    public final String toString() {
        return "StructuralEqualityPolicy";
    }
}
