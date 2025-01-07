package androidx.datastore.core;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FileStorage implements Storage {
    public static final Set activeFiles = new LinkedHashSet();
    public static final Object activeFilesLock = new Object();
    public final Function1 coordinatorProducer;
    public final Lambda produceFile;
    public final Serializer serializer;

    /* JADX WARN: Multi-variable type inference failed */
    public FileStorage(Serializer serializer, Function0 function0) {
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: androidx.datastore.core.FileStorage.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((File) obj).getCanonicalFile().getAbsolutePath();
                return new SingleProcessCoordinator();
            }
        };
        this.serializer = serializer;
        this.coordinatorProducer = anonymousClass1;
        this.produceFile = (Lambda) function0;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.datastore.core.Storage
    public final StorageConnection createConnection() {
        final File canonicalFile = ((File) this.produceFile.invoke()).getCanonicalFile();
        synchronized (activeFilesLock) {
            String absolutePath = canonicalFile.getAbsolutePath();
            Set set = activeFiles;
            if (set.contains(absolutePath)) {
                throw new IllegalStateException(("There are multiple DataStores active for the same file: " + absolutePath + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
            }
            set.add(absolutePath);
        }
        return new FileStorageConnection(canonicalFile, this.serializer, (SingleProcessCoordinator) this.coordinatorProducer.invoke(canonicalFile), new Function0() { // from class: androidx.datastore.core.FileStorage$createConnection$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Object obj = FileStorage.activeFilesLock;
                File file = canonicalFile;
                synchronized (obj) {
                    FileStorage.activeFiles.remove(file.getAbsolutePath());
                }
                return Unit.INSTANCE;
            }
        });
    }
}
