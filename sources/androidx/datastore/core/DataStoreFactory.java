package androidx.datastore.core;

import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DataStoreFactory {
    public static final DataStoreFactory INSTANCE = null;

    public static DataStoreImpl create(Storage storage, List list, CoroutineScope coroutineScope) {
        return new DataStoreImpl(storage, Collections.singletonList(new DataMigrationInitializer$Companion$getInitializer$1(list, null)), new NoOpCorruptionHandler(), coroutineScope);
    }

    public static DataStoreImpl create$default(Serializer serializer, CoroutineScope coroutineScope, Function0 function0, int i) {
        EmptyList emptyList = EmptyList.INSTANCE;
        if ((i & 8) != 0) {
            DefaultIoScheduler defaultIoScheduler = Dispatchers.IO;
            SupervisorJobImpl SupervisorJob$default = SupervisorKt.SupervisorJob$default();
            defaultIoScheduler.getClass();
            coroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(defaultIoScheduler, SupervisorJob$default));
        }
        return create(new FileStorage(serializer, function0), emptyList, coroutineScope);
    }
}
