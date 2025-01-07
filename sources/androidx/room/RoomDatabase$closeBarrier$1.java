package androidx.room;

import android.os.RemoteException;
import android.util.Log;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class RoomDatabase$closeBarrier$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        RoomDatabase roomDatabase = (RoomDatabase) this.receiver;
        ContextScope contextScope = roomDatabase.coroutineScope;
        if (contextScope == null) {
            contextScope = null;
        }
        CoroutineScopeKt.cancel(contextScope, null);
        InvalidationTracker invalidationTracker = roomDatabase.getInvalidationTracker();
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = invalidationTracker.multiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null && multiInstanceInvalidationClient.stopped.compareAndSet(false, true)) {
            InvalidationTracker invalidationTracker2 = multiInstanceInvalidationClient.invalidationTracker;
            invalidationTracker2.getClass();
            BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new InvalidationTracker$removeObserver$1(invalidationTracker2, multiInstanceInvalidationClient.observer, null));
            try {
                IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.invalidationService;
                if (iMultiInstanceInvalidationService != null) {
                    iMultiInstanceInvalidationService.unregisterCallback(multiInstanceInvalidationClient.invalidationCallback, multiInstanceInvalidationClient.clientId);
                }
            } catch (RemoteException e) {
                Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
            }
            multiInstanceInvalidationClient.appContext.unbindService(multiInstanceInvalidationClient.serviceConnection);
        }
        invalidationTracker.multiInstanceInvalidationClient = null;
        RoomConnectionManager roomConnectionManager = roomDatabase.connectionManager;
        (roomConnectionManager != null ? roomConnectionManager : null).connectionPool.close();
        return Unit.INSTANCE;
    }
}
