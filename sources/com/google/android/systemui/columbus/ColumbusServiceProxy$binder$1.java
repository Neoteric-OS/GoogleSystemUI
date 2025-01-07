package com.google.android.systemui.columbus;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusServiceProxy$binder$1 extends Binder implements IColumbusService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ ColumbusServiceProxy this$0;

    public ColumbusServiceProxy$binder$1(ColumbusServiceProxy columbusServiceProxy) {
        this.this$0 = columbusServiceProxy;
        attachInterface(this, "com.google.android.systemui.columbus.IColumbusService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.columbus.IColumbusService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.columbus.IColumbusService");
            return true;
        }
        if (i == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            ColumbusServiceProxy columbusServiceProxy = this.this$0;
            int i3 = ColumbusServiceProxy.$r8$clinit;
            columbusServiceProxy.enforceCallingOrSelfPermission("com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE", "Must have com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE permission");
            for (int size = ((ArrayList) this.this$0.columbusServiceListeners).size() - 1; -1 < size; size--) {
                IColumbusServiceListener iColumbusServiceListener = ((ColumbusServiceProxy.ColumbusServiceListener) ((ArrayList) this.this$0.columbusServiceListeners).get(size)).listener;
                if (iColumbusServiceListener == null) {
                    this.this$0.columbusServiceListeners.remove(size);
                } else {
                    try {
                        iColumbusServiceListener.setListener(readStrongBinder, readStrongBinder2);
                    } catch (RemoteException e) {
                        Log.e("Columbus/ColumbusProxy", "Cannot set listener", e);
                        this.this$0.columbusServiceListeners.remove(size);
                    }
                }
            }
        } else {
            if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            IBinder readStrongBinder3 = parcel.readStrongBinder();
            IBinder readStrongBinder4 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            registerServiceListener(readStrongBinder3, readStrongBinder4);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.google.android.systemui.columbus.IColumbusServiceListener] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.os.IBinder] */
    @Override // com.google.android.systemui.columbus.IColumbusService
    public final void registerServiceListener(final IBinder iBinder, IBinder iBinder2) {
        IColumbusServiceListener$Stub$Proxy iColumbusServiceListener$Stub$Proxy;
        ColumbusServiceProxy columbusServiceProxy = this.this$0;
        int i = ColumbusServiceProxy.$r8$clinit;
        columbusServiceProxy.enforceCallingOrSelfPermission("com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE", "Must have com.google.android.columbus.permission.CONFIGURE_COLUMBUS_GESTURE permission");
        if (iBinder == 0) {
            Log.e("Columbus/ColumbusProxy", "Binder token must not be null");
            return;
        }
        if (iBinder2 == null) {
            CollectionsKt__MutableCollectionsKt.removeAll(this.this$0.columbusServiceListeners, new Function1() { // from class: com.google.android.systemui.columbus.ColumbusServiceProxy$binder$1$registerServiceListener$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ColumbusServiceProxy.ColumbusServiceListener columbusServiceListener = (ColumbusServiceProxy.ColumbusServiceListener) obj;
                    boolean z = false;
                    if (Intrinsics.areEqual(iBinder, columbusServiceListener.token)) {
                        IBinder iBinder3 = columbusServiceListener.token;
                        if (iBinder3 != null) {
                            iBinder3.unlinkToDeath(columbusServiceListener, 0);
                        }
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            });
            return;
        }
        List list = this.this$0.columbusServiceListeners;
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.columbus.IColumbusServiceListener");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusServiceListener)) {
            IColumbusServiceListener$Stub$Proxy iColumbusServiceListener$Stub$Proxy2 = new IColumbusServiceListener$Stub$Proxy();
            iColumbusServiceListener$Stub$Proxy2.mRemote = iBinder2;
            iColumbusServiceListener$Stub$Proxy = iColumbusServiceListener$Stub$Proxy2;
        } else {
            iColumbusServiceListener$Stub$Proxy = (IColumbusServiceListener) queryLocalInterface;
        }
        ColumbusServiceProxy.ColumbusServiceListener columbusServiceListener = new ColumbusServiceProxy.ColumbusServiceListener();
        columbusServiceListener.token = iBinder;
        columbusServiceListener.listener = iColumbusServiceListener$Stub$Proxy;
        try {
            iBinder.linkToDeath(columbusServiceListener, 0);
        } catch (RemoteException e) {
            Log.e("Columbus/ColumbusProxy", "Unable to linkToDeath", e);
        }
        list.add(columbusServiceListener);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
