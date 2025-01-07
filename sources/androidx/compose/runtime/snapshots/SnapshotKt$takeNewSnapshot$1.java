package androidx.compose.runtime.snapshots;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SnapshotKt$takeNewSnapshot$1 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotKt$takeNewSnapshot$1(Function1 function1) {
        super(1);
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Snapshot snapshot = (Snapshot) this.$block.invoke((SnapshotIdSet) obj);
        synchronized (SnapshotKt.lock) {
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(snapshot.getId());
        }
        return snapshot;
    }
}
