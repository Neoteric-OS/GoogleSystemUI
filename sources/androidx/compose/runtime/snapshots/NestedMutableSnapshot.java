package androidx.compose.runtime.snapshots;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedMutableSnapshot extends MutableSnapshot {
    public boolean deactivated;
    public final MutableSnapshot parent;

    public NestedMutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12, MutableSnapshot mutableSnapshot) {
        super(i, snapshotIdSet, function1, function12);
        this.parent = mutableSnapshot;
        mutableSnapshot.nestedActivated$runtime_release();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0066 A[Catch: all -> 0x0058, TryCatch #1 {all -> 0x0058, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x009d, B:31:0x00a4, B:34:0x00ad, B:35:0x00ae, B:44:0x00b9, B:47:0x00c3, B:48:0x00bf, B:51:0x00d8, B:52:0x00d9, B:53:0x009a, B:54:0x0050, B:55:0x005b, B:33:0x00a5), top: B:10:0x0020, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[Catch: all -> 0x0058, TryCatch #1 {all -> 0x0058, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x009d, B:31:0x00a4, B:34:0x00ad, B:35:0x00ae, B:44:0x00b9, B:47:0x00c3, B:48:0x00bf, B:51:0x00d8, B:52:0x00d9, B:53:0x009a, B:54:0x0050, B:55:0x005b, B:33:0x00a5), top: B:10:0x0020, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009a A[Catch: all -> 0x0058, TryCatch #1 {all -> 0x0058, blocks: (B:11:0x0020, B:13:0x0025, B:16:0x002a, B:21:0x0044, B:23:0x004c, B:24:0x005e, B:26:0x0066, B:27:0x006b, B:29:0x008c, B:30:0x009d, B:31:0x00a4, B:34:0x00ad, B:35:0x00ae, B:44:0x00b9, B:47:0x00c3, B:48:0x00bf, B:51:0x00d8, B:52:0x00d9, B:53:0x009a, B:54:0x0050, B:55:0x005b, B:33:0x00a5), top: B:10:0x0020, inners: #0 }] */
    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.NestedMutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        if (this.disposed) {
            return;
        }
        super.dispose();
        if (this.deactivated) {
            return;
        }
        this.deactivated = true;
        this.parent.nestedDeactivated$runtime_release();
    }
}
