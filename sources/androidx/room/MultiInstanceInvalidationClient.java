package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultiInstanceInvalidationClient {
    public final Context appContext;
    public int clientId;
    public final ContextScope coroutineScope;
    public final MultiInstanceInvalidationClient$invalidationCallback$1 invalidationCallback;
    public IMultiInstanceInvalidationService invalidationService;
    public final InvalidationTracker invalidationTracker;
    public final String name;
    public final MultiInstanceInvalidationClient$observer$1 observer;
    public final MultiInstanceInvalidationClient$serviceConnection$1 serviceConnection;
    public final AtomicBoolean stopped;

    /* JADX WARN: Type inference failed for: r1v6, types: [androidx.room.MultiInstanceInvalidationClient$observer$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [androidx.room.MultiInstanceInvalidationClient$serviceConnection$1] */
    public MultiInstanceInvalidationClient(Context context, String str, InvalidationTracker invalidationTracker) {
        this.name = str;
        this.invalidationTracker = invalidationTracker;
        this.appContext = context.getApplicationContext();
        ContextScope contextScope = invalidationTracker.database.coroutineScope;
        this.coroutineScope = contextScope == null ? null : contextScope;
        this.stopped = new AtomicBoolean(true);
        final String[] strArr = invalidationTracker.tableNames;
        this.observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.MultiInstanceInvalidationClient$observer$1
            @Override // androidx.room.InvalidationTracker.Observer
            public final void onInvalidated(Set set) {
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                if (multiInstanceInvalidationClient.stopped.get()) {
                    return;
                }
                try {
                    IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.invalidationService;
                    if (iMultiInstanceInvalidationService != null) {
                        iMultiInstanceInvalidationService.broadcastInvalidation(multiInstanceInvalidationClient.clientId, (String[]) set.toArray(new String[0]));
                    }
                } catch (RemoteException e) {
                    Log.w("ROOM", "Cannot broadcast invalidation", e);
                }
            }
        };
        this.invalidationCallback = new MultiInstanceInvalidationClient$invalidationCallback$1(this);
        this.serviceConnection = new ServiceConnection() { // from class: androidx.room.MultiInstanceInvalidationClient$serviceConnection$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v6, types: [androidx.room.IMultiInstanceInvalidationService] */
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IMultiInstanceInvalidationService$Stub$Proxy iMultiInstanceInvalidationService$Stub$Proxy;
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                int i = MultiInstanceInvalidationService$binder$1.$r8$clinit;
                IInterface queryLocalInterface = iBinder.queryLocalInterface(IMultiInstanceInvalidationService.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationService)) {
                    IMultiInstanceInvalidationService$Stub$Proxy iMultiInstanceInvalidationService$Stub$Proxy2 = new IMultiInstanceInvalidationService$Stub$Proxy();
                    iMultiInstanceInvalidationService$Stub$Proxy2.mRemote = iBinder;
                    iMultiInstanceInvalidationService$Stub$Proxy = iMultiInstanceInvalidationService$Stub$Proxy2;
                } else {
                    iMultiInstanceInvalidationService$Stub$Proxy = (IMultiInstanceInvalidationService) queryLocalInterface;
                }
                multiInstanceInvalidationClient.invalidationService = iMultiInstanceInvalidationService$Stub$Proxy;
                MultiInstanceInvalidationClient multiInstanceInvalidationClient2 = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient2.getClass();
                try {
                    IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient2.invalidationService;
                    if (iMultiInstanceInvalidationService != null) {
                        multiInstanceInvalidationClient2.clientId = iMultiInstanceInvalidationService.registerCallback(multiInstanceInvalidationClient2.invalidationCallback, multiInstanceInvalidationClient2.name);
                    }
                } catch (RemoteException e) {
                    Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
                }
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
                MultiInstanceInvalidationClient.this.invalidationService = null;
            }
        };
    }
}
