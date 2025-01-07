package com.google.android.systemui.reversecharging;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import vendor.google.wireless_charger.IWirelessCharger;
import vendor.google.wireless_charger.IWirelessChargerRtxStatusCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReverseWirelessCharger extends IWirelessChargerRtxStatusCallback.Stub implements IBinder.DeathRecipient {
    public static final boolean DEBUG = Log.isLoggable("ReverseWirelessCharger", 3);
    public final Context mContext;
    public final Object mLock;
    public final ArrayList mRtxStatusCallbacks;
    public IWirelessCharger mWirelessCharger;

    public ReverseWirelessCharger(Context context) {
        markVintfStability();
        attachInterface(this, IWirelessChargerRtxStatusCallback.DESCRIPTOR);
        this.mLock = new Object();
        new ArrayList();
        this.mRtxStatusCallbacks = new ArrayList();
        this.mContext = context;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.i("ReverseWirelessCharger", "serviceDied");
        this.mWirelessCharger = null;
    }

    @Override // android.os.Binder
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.printf("rtx callback in [%d]%s\n", Integer.valueOf(Process.myPid()), this.mContext.getPackageName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v7, types: [vendor.google.wireless_charger.IWirelessCharger] */
    public final boolean initHALInterface() {
        IWirelessCharger.Stub.Proxy proxy;
        if (this.mWirelessCharger != null) {
            return true;
        }
        IBinder service = ServiceManager.getService("vendor.google.wireless_charger.IWirelessCharger/default");
        if (service != null) {
            IInterface queryLocalInterface = service.queryLocalInterface(IWirelessCharger.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWirelessCharger)) {
                IWirelessCharger.Stub.Proxy proxy2 = new IWirelessCharger.Stub.Proxy();
                proxy2.mRemote = service;
                proxy = proxy2;
            } else {
                proxy = (IWirelessCharger) queryLocalInterface;
            }
            this.mWirelessCharger = proxy;
            try {
                service.linkToDeath(this, 0);
                Log.i("ReverseWirelessCharger", "mWirelessCharger service connected!!!!");
            } catch (RemoteException unused) {
                Log.w("ReverseWirelessCharger", "Can't link death recipient to HAL");
                this.mWirelessCharger = null;
            }
        }
        IWirelessCharger iWirelessCharger = this.mWirelessCharger;
        if (iWirelessCharger == null) {
            return false;
        }
        try {
            ((IWirelessCharger.Stub.Proxy) iWirelessCharger).registerRtxCallback(this);
        } catch (Exception e) {
            Log.w("ReverseWirelessCharger", "registerRtxCallback fail: ", e);
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode == 5) {
                Log.d("ReverseWirelessCharger", "RtxCallback is already registered...");
            } else {
                Log.w("ReverseWirelessCharger", "RtxCallback registration error: " + e2.errorCode);
            }
        }
        return this.mWirelessCharger != null;
    }
}
