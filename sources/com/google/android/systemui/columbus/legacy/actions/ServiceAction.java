package com.google.android.systemui.columbus.legacy.actions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import com.google.android.systemui.columbus.ColumbusServiceProxy$binder$1;
import com.google.android.systemui.columbus.IColumbusService;
import com.google.android.systemui.columbus.IColumbusService$Stub$Proxy;
import com.google.android.systemui.columbus.IColumbusServiceGestureListener$Stub$Proxy;
import com.google.android.systemui.columbus.IColumbusServiceListener;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptySet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ServiceAction extends Action implements IBinder.DeathRecipient {
    public IColumbusService columbusService;
    public IColumbusServiceGestureListener$Stub$Proxy columbusServiceGestureListener;
    public final ColumbusServiceListener columbusServiceListener;
    public final IBinder token;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ColumbusServiceConnection implements ServiceConnection {
        public final /* synthetic */ SettingsAction this$0;

        public ColumbusServiceConnection(SettingsAction settingsAction) {
            this.this$0 = settingsAction;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IColumbusService iColumbusService;
            SettingsAction settingsAction = this.this$0;
            int i = ColumbusServiceProxy$binder$1.$r8$clinit;
            if (iBinder == null) {
                iColumbusService = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.systemui.columbus.IColumbusService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusService)) {
                    IColumbusService$Stub$Proxy iColumbusService$Stub$Proxy = new IColumbusService$Stub$Proxy();
                    iColumbusService$Stub$Proxy.mRemote = iBinder;
                    iColumbusService = iColumbusService$Stub$Proxy;
                } else {
                    iColumbusService = (IColumbusService) queryLocalInterface;
                }
            }
            settingsAction.columbusService = iColumbusService;
            try {
                SettingsAction settingsAction2 = this.this$0;
                IColumbusService iColumbusService2 = settingsAction2.columbusService;
                if (iColumbusService2 != null) {
                    iColumbusService2.registerServiceListener(settingsAction2.token, settingsAction2.columbusServiceListener);
                }
            } catch (RemoteException e) {
                Log.e("Columbus/ServiceAction", "Error registering listener", e);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            this.this$0.columbusService = null;
        }
    }

    public ServiceAction(Context context) {
        super(context, null);
        EmptySet emptySet = EmptySet.INSTANCE;
        this.token = new Binder();
        SettingsAction settingsAction = (SettingsAction) this;
        ColumbusServiceConnection columbusServiceConnection = new ColumbusServiceConnection(settingsAction);
        this.columbusServiceListener = new ColumbusServiceListener(settingsAction);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, (Class<?>) ColumbusServiceProxy.class));
            context.bindService(intent, columbusServiceConnection, 1);
        } catch (SecurityException e) {
            Log.e("Columbus/ServiceAction", "Unable to bind to ColumbusServiceProxy", e);
        }
        updateAvailable$1();
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.w("Columbus/ServiceAction", "Binder died");
        this.columbusServiceGestureListener = null;
        updateAvailable$1();
    }

    public final void updateAvailable$1() {
        setAvailable(this.columbusServiceGestureListener != null);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ColumbusServiceListener extends Binder implements IColumbusServiceListener {
        public final /* synthetic */ SettingsAction this$0;

        public ColumbusServiceListener(SettingsAction settingsAction) {
            this.this$0 = settingsAction;
            attachInterface(this, "com.google.android.systemui.columbus.IColumbusServiceListener");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.google.android.systemui.columbus.IColumbusServiceListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.google.android.systemui.columbus.IColumbusServiceListener");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            IBinder readStrongBinder = parcel.readStrongBinder();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            parcel.enforceNoDataAvail();
            setListener(readStrongBinder, readStrongBinder2);
            return true;
        }

        @Override // com.google.android.systemui.columbus.IColumbusServiceListener
        public final void setListener(IBinder iBinder, IBinder iBinder2) {
            IColumbusServiceGestureListener$Stub$Proxy iColumbusServiceGestureListener$Stub$Proxy;
            Object obj;
            SettingsAction settingsAction = this.this$0;
            String[] packagesForUid = settingsAction.context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
            if (packagesForUid == null) {
                return;
            }
            Iterator it = settingsAction.supportedCallerPackages.iterator();
            while (true) {
                iColumbusServiceGestureListener$Stub$Proxy = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (ArraysKt.contains(packagesForUid, (String) obj)) {
                        break;
                    }
                }
            }
            if (obj != null) {
                if (iBinder2 == null && this.this$0.columbusServiceGestureListener == null) {
                    return;
                }
                SettingsAction settingsAction2 = this.this$0;
                if (iBinder2 != null) {
                    IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.systemui.columbus.IColumbusServiceGestureListener");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IColumbusServiceGestureListener$Stub$Proxy)) {
                        iColumbusServiceGestureListener$Stub$Proxy = new IColumbusServiceGestureListener$Stub$Proxy();
                        iColumbusServiceGestureListener$Stub$Proxy.mRemote = iBinder2;
                    } else {
                        iColumbusServiceGestureListener$Stub$Proxy = (IColumbusServiceGestureListener$Stub$Proxy) queryLocalInterface;
                    }
                }
                settingsAction2.columbusServiceGestureListener = iColumbusServiceGestureListener$Stub$Proxy;
                this.this$0.updateAvailable$1();
                if (iBinder != null) {
                    try {
                        SettingsAction settingsAction3 = this.this$0;
                        if (iBinder2 == null) {
                            iBinder.unlinkToDeath(settingsAction3, 0);
                        } else {
                            iBinder.linkToDeath(settingsAction3, 0);
                        }
                    } catch (RemoteException e) {
                        Log.e("Columbus/ServiceAction", "RemoteException during linkToDeath", e);
                    } catch (NoSuchElementException e2) {
                        Log.e("Columbus/ServiceAction", "NoSuchElementException during linkToDeath", e2);
                    }
                }
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
