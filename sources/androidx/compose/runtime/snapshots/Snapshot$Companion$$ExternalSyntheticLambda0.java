package androidx.compose.runtime.snapshots;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class Snapshot$Companion$$ExternalSyntheticLambda0 implements ObserverHandle {
    public final /* synthetic */ Lambda f$0;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ Snapshot$Companion$$ExternalSyntheticLambda0(Function2 function2) {
        this.f$0 = (Lambda) function2;
    }

    @Override // androidx.compose.runtime.snapshots.ObserverHandle
    public final void dispose() {
        Lambda lambda = this.f$0;
        synchronized (SnapshotKt.lock) {
            SnapshotKt.applyObservers = CollectionsKt.minus(SnapshotKt.applyObservers, lambda);
        }
    }
}
