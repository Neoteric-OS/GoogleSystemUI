package androidx.datastore.core;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FileWriteScope extends FileReadScope implements WriteScope {
    @Override // androidx.datastore.core.WriteScope
    public final Object writeData(Object obj, Continuation continuation) {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.");
        }
        Object access$runFileDiagnosticsIfNotCorruption = FileStorageKt.access$runFileDiagnosticsIfNotCorruption(this.file, new FileWriteScope$writeData$2(this, obj, null), (ContinuationImpl) continuation);
        return access$runFileDiagnosticsIfNotCorruption == CoroutineSingletons.COROUTINE_SUSPENDED ? access$runFileDiagnosticsIfNotCorruption : Unit.INSTANCE;
    }
}
