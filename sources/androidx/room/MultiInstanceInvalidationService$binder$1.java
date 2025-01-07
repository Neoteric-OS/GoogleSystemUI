package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultiInstanceInvalidationService$binder$1 extends Binder implements IMultiInstanceInvalidationService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ MultiInstanceInvalidationService this$0;

    public MultiInstanceInvalidationService$binder$1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.this$0 = multiInstanceInvalidationService;
        attachInterface(this, IMultiInstanceInvalidationService.DESCRIPTOR);
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public final void broadcastInvalidation(int i, String[] strArr) {
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (multiInstanceInvalidationService.callbackList) {
            String str = (String) multiInstanceInvalidationService.clientNames.get(Integer.valueOf(i));
            if (str == null) {
                Log.w("ROOM", "Remote invalidation client ID not registered");
                return;
            }
            int beginBroadcast = multiInstanceInvalidationService.callbackList.beginBroadcast();
            for (int i2 = 0; i2 < beginBroadcast; i2++) {
                try {
                    Integer num = (Integer) multiInstanceInvalidationService.callbackList.getBroadcastCookie(i2);
                    int intValue = num.intValue();
                    String str2 = (String) multiInstanceInvalidationService.clientNames.get(num);
                    if (i != intValue && str.equals(str2)) {
                        try {
                            ((IMultiInstanceInvalidationCallback) multiInstanceInvalidationService.callbackList.getBroadcastItem(i2)).onInvalidation(strArr);
                        } catch (RemoteException e) {
                            Log.w("ROOM", "Error invoking a remote callback", e);
                        }
                    }
                } finally {
                    multiInstanceInvalidationService.callbackList.finishBroadcast();
                }
            }
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        String str = IMultiInstanceInvalidationService.DESCRIPTOR;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface(str);
        }
        if (i == 1598968902) {
            parcel2.writeString(str);
            return true;
        }
        IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback = null;
        IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback2 = null;
        if (i == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(IMultiInstanceInvalidationCallback.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                    IMultiInstanceInvalidationCallback$Stub$Proxy iMultiInstanceInvalidationCallback$Stub$Proxy = new IMultiInstanceInvalidationCallback$Stub$Proxy();
                    iMultiInstanceInvalidationCallback$Stub$Proxy.mRemote = readStrongBinder;
                    iMultiInstanceInvalidationCallback = iMultiInstanceInvalidationCallback$Stub$Proxy;
                } else {
                    iMultiInstanceInvalidationCallback = (IMultiInstanceInvalidationCallback) queryLocalInterface;
                }
            }
            int registerCallback = registerCallback(iMultiInstanceInvalidationCallback, parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(registerCallback);
        } else if (i == 2) {
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface(IMultiInstanceInvalidationCallback.DESCRIPTOR);
                if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IMultiInstanceInvalidationCallback)) {
                    IMultiInstanceInvalidationCallback$Stub$Proxy iMultiInstanceInvalidationCallback$Stub$Proxy2 = new IMultiInstanceInvalidationCallback$Stub$Proxy();
                    iMultiInstanceInvalidationCallback$Stub$Proxy2.mRemote = readStrongBinder2;
                    iMultiInstanceInvalidationCallback2 = iMultiInstanceInvalidationCallback$Stub$Proxy2;
                } else {
                    iMultiInstanceInvalidationCallback2 = (IMultiInstanceInvalidationCallback) queryLocalInterface2;
                }
            }
            unregisterCallback(iMultiInstanceInvalidationCallback2, parcel.readInt());
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            broadcastInvalidation(parcel.readInt(), parcel.createStringArray());
        }
        return true;
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public final int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
        int i = 0;
        if (str == null) {
            return 0;
        }
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (multiInstanceInvalidationService.callbackList) {
            try {
                int i2 = multiInstanceInvalidationService.maxClientId + 1;
                multiInstanceInvalidationService.maxClientId = i2;
                if (multiInstanceInvalidationService.callbackList.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i2))) {
                    multiInstanceInvalidationService.clientNames.put(Integer.valueOf(i2), str);
                    i = i2;
                } else {
                    multiInstanceInvalidationService.maxClientId--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Override // androidx.room.IMultiInstanceInvalidationService
    public final void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) {
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (multiInstanceInvalidationService.callbackList) {
            multiInstanceInvalidationService.callbackList.unregister(iMultiInstanceInvalidationCallback);
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
