package androidx.datastore.core.okio;

import androidx.datastore.core.SingleProcessCoordinator;
import androidx.datastore.core.StorageConnection;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;
import okio.JvmSystemFileSystem;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OkioStorageConnection implements StorageConnection {
    public final SingleProcessCoordinator coordinator;
    public final JvmSystemFileSystem fileSystem;
    public final Function0 onClose;
    public final Path path;
    public final AtomicBoolean closed = new AtomicBoolean();
    public final MutexImpl transactionMutex = MutexKt.Mutex$default();

    public OkioStorageConnection(JvmSystemFileSystem jvmSystemFileSystem, Path path, SingleProcessCoordinator singleProcessCoordinator, Function0 function0) {
        this.fileSystem = jvmSystemFileSystem;
        this.path = path;
        this.coordinator = singleProcessCoordinator;
        this.onClose = function0;
    }

    @Override // androidx.datastore.core.Closeable
    public final void close() {
        this.closed.delegate.set(true);
        ((OkioStorage$createConnection$2) this.onClose).invoke();
    }

    @Override // androidx.datastore.core.StorageConnection
    public final SingleProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|1|(2:3|(8:5|6|7|(1:(3:10|11|12)(2:41|42))(2:43|(5:45|46|47|48|(1:50)(1:51))(2:55|56))|14|15|16|(2:(1:19)|20)(2:22|23)))|58|6|7|(0)(0)|14|15|16|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0078, code lost:
    
        r9 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /* JADX WARN: Type inference failed for: r10v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [kotlin.jvm.functions.Function3] */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readScope(kotlin.jvm.functions.Function3 r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.datastore.core.okio.OkioStorageConnection$readScope$1
            if (r0 == 0) goto L13
            r0 = r10
            androidx.datastore.core.okio.OkioStorageConnection$readScope$1 r0 = (androidx.datastore.core.okio.OkioStorageConnection$readScope$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.datastore.core.okio.OkioStorageConnection$readScope$1 r0 = new androidx.datastore.core.okio.OkioStorageConnection$readScope$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 != r4) goto L38
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$1
            androidx.datastore.core.Closeable r9 = (androidx.datastore.core.Closeable) r9
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.okio.OkioStorageConnection r0 = (androidx.datastore.core.okio.OkioStorageConnection) r0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L32
            goto L73
        L32:
            r10 = move-exception
            r7 = r10
            r10 = r8
            r8 = r0
            r0 = r7
            goto L8b
        L38:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L40:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.datastore.core.okio.AtomicBoolean r10 = r8.closed
            java.util.concurrent.atomic.AtomicBoolean r10 = r10.delegate
            boolean r10 = r10.get()
            if (r10 != 0) goto L9d
            kotlinx.coroutines.sync.MutexImpl r10 = r8.transactionMutex
            boolean r10 = r10.tryLock()
            androidx.datastore.core.okio.OkioReadScope r2 = new androidx.datastore.core.okio.OkioReadScope     // Catch: java.lang.Throwable -> L94
            okio.JvmSystemFileSystem r5 = r8.fileSystem     // Catch: java.lang.Throwable -> L94
            okio.Path r6 = r8.path     // Catch: java.lang.Throwable -> L94
            r2.<init>(r5, r6)     // Catch: java.lang.Throwable -> L94
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> L88
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L88
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L88
            r0.Z$0 = r10     // Catch: java.lang.Throwable -> L88
            r0.label = r4     // Catch: java.lang.Throwable -> L88
            java.lang.Object r9 = r9.invoke(r2, r5, r0)     // Catch: java.lang.Throwable -> L88
            if (r9 != r1) goto L6f
            return r1
        L6f:
            r0 = r8
            r8 = r10
            r10 = r9
            r9 = r2
        L73:
            r9.close()     // Catch: java.lang.Throwable -> L78
            r9 = r3
            goto L79
        L78:
            r9 = move-exception
        L79:
            if (r9 != 0) goto L83
            if (r8 == 0) goto L82
            kotlinx.coroutines.sync.MutexImpl r8 = r0.transactionMutex
            r8.unlock(r3)
        L82:
            return r10
        L83:
            throw r9     // Catch: java.lang.Throwable -> L84
        L84:
            r9 = move-exception
            r10 = r8
            r8 = r0
            goto L95
        L88:
            r9 = move-exception
            r0 = r9
            r9 = r2
        L8b:
            r9.close()     // Catch: java.lang.Throwable -> L8f
            goto L93
        L8f:
            r9 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r0, r9)     // Catch: java.lang.Throwable -> L94
        L93:
            throw r0     // Catch: java.lang.Throwable -> L94
        L94:
            r9 = move-exception
        L95:
            if (r10 == 0) goto L9c
            kotlinx.coroutines.sync.MutexImpl r8 = r8.transactionMutex
            r8.unlock(r3)
        L9c:
            throw r9
        L9d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "StorageConnection has already been disposed."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.okio.OkioStorageConnection.readScope(kotlin.jvm.functions.Function3, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011f A[Catch: all -> 0x012f, IOException -> 0x0132, TRY_ENTER, TryCatch #9 {IOException -> 0x0132, all -> 0x012f, blocks: (B:18:0x011f, B:20:0x0127, B:24:0x013b, B:31:0x0149, B:34:0x0146), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x013b A[Catch: all -> 0x012f, IOException -> 0x0132, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x0132, all -> 0x012f, blocks: (B:18:0x011f, B:20:0x0127, B:24:0x013b, B:31:0x0149, B:34:0x0146), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0113 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.datastore.core.okio.OkioStorageConnection$writeScope$1, java.lang.Object, kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r0v4, types: [okio.JvmSystemFileSystem] */
    /* JADX WARN: Type inference failed for: r11v1, types: [okio.Path] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r1v1, types: [okio.FileSystem, okio.JvmSystemFileSystem] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, kotlinx.coroutines.sync.MutexImpl] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [kotlin.jvm.functions.Function2] */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeScope(kotlin.jvm.functions.Function2 r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instructions count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.okio.OkioStorageConnection.writeScope(kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
