package androidx.datastore.core;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class FileReadScope implements ReadScope {
    public final AtomicBoolean closed = new AtomicBoolean(false);
    public final File file;
    public final Serializer serializer;

    public FileReadScope(File file, Serializer serializer) {
        this.file = file;
        this.serializer = serializer;
    }

    @Override // androidx.datastore.core.Closeable
    public final void close() {
        this.closed.set(true);
    }

    @Override // androidx.datastore.core.ReadScope
    public final Object readData(Continuation continuation) {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.");
        }
        return FileStorageKt.access$runFileDiagnosticsIfNotCorruption(this.file, new FileReadScope$readData$2(this, null), (ContinuationImpl) continuation);
    }
}
