package androidx.datastore.core.okio;

import androidx.datastore.core.ReadScope;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import okio.JvmSystemFileSystem;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class OkioReadScope implements ReadScope {
    public final AtomicBoolean closed = new AtomicBoolean();
    public final JvmSystemFileSystem fileSystem;
    public final Path path;

    public OkioReadScope(JvmSystemFileSystem jvmSystemFileSystem, Path path) {
        this.fileSystem = jvmSystemFileSystem;
        this.path = path;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x009b A[Catch: FileNotFoundException -> 0x009f, TRY_ENTER, TryCatch #1 {FileNotFoundException -> 0x009f, blocks: (B:15:0x009b, B:18:0x00a3, B:47:0x0047), top: B:46:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a3 A[Catch: FileNotFoundException -> 0x009f, TRY_LEAVE, TryCatch #1 {FileNotFoundException -> 0x009f, blocks: (B:15:0x009b, B:18:0x00a3, B:47:0x0047), top: B:46:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object readData$suspendImpl(androidx.datastore.core.okio.OkioReadScope r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            Method dump skipped, instructions count: 189
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.okio.OkioReadScope.readData$suspendImpl(androidx.datastore.core.okio.OkioReadScope, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.datastore.core.Closeable
    public final void close() {
        this.closed.delegate.set(true);
    }

    @Override // androidx.datastore.core.ReadScope
    public final Object readData(Continuation continuation) {
        return readData$suspendImpl(this, (ContinuationImpl) continuation);
    }
}
