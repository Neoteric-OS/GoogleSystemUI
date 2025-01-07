package androidx.datastore.core.okio;

import androidx.datastore.core.SingleProcessCoordinator;
import androidx.datastore.core.Storage;
import androidx.datastore.core.StorageConnection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import okio.JvmSystemFileSystem;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OkioStorage implements Storage {
    public static final Set activeFiles = new LinkedHashSet();
    public static final Synchronizer activeFilesLock = new Synchronizer();
    public final Lazy canonicalPath$delegate;
    public final Function2 coordinatorProducer;
    public final JvmSystemFileSystem fileSystem;
    public final Function0 producePath;

    public OkioStorage(JvmSystemFileSystem jvmSystemFileSystem, Function0 function0) {
        AnonymousClass1 anonymousClass1 = new Function2() { // from class: androidx.datastore.core.okio.OkioStorage.1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Path.Companion.get(((Path) obj).bytes.utf8(), true).bytes.utf8();
                return new SingleProcessCoordinator();
            }
        };
        this.fileSystem = jvmSystemFileSystem;
        this.coordinatorProducer = anonymousClass1;
        this.producePath = function0;
        this.canonicalPath$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.datastore.core.okio.OkioStorage$canonicalPath$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Path path = (Path) OkioStorage.this.producePath.invoke();
                path.getClass();
                boolean z = okio.internal.Path.access$rootLength(path) != -1;
                OkioStorage okioStorage = OkioStorage.this;
                if (z) {
                    return Path.Companion.get(path.bytes.utf8(), true);
                }
                throw new IllegalStateException(("OkioStorage requires absolute paths, but did not get an absolute path from producePath = " + okioStorage.producePath + ", instead got " + path).toString());
            }
        });
    }

    @Override // androidx.datastore.core.Storage
    public final StorageConnection createConnection() {
        String utf8 = ((Path) this.canonicalPath$delegate.getValue()).bytes.utf8();
        synchronized (activeFilesLock) {
            Set set = activeFiles;
            if (set.contains(utf8)) {
                throw new IllegalStateException(("There are multiple DataStores active for the same file: " + utf8 + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
            }
            set.add(utf8);
        }
        return new OkioStorageConnection(this.fileSystem, (Path) this.canonicalPath$delegate.getValue(), (SingleProcessCoordinator) this.coordinatorProducer.invoke((Path) this.canonicalPath$delegate.getValue(), this.fileSystem), new OkioStorage$createConnection$2(this));
    }
}
