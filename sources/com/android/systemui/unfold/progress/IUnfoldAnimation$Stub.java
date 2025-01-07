package com.android.systemui.unfold.progress;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class IUnfoldAnimation$Stub extends Binder implements IInterface {
    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IUnfoldTransitionListener$Stub$Proxy iUnfoldTransitionListener$Stub$Proxy;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.unfold.progress.IUnfoldAnimation");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.unfold.progress.IUnfoldAnimation");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            iUnfoldTransitionListener$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.systemui.unfold.progress.IUnfoldTransitionListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUnfoldTransitionListener$Stub$Proxy)) {
                IUnfoldTransitionListener$Stub$Proxy iUnfoldTransitionListener$Stub$Proxy2 = new IUnfoldTransitionListener$Stub$Proxy();
                iUnfoldTransitionListener$Stub$Proxy2.mRemote = readStrongBinder;
                iUnfoldTransitionListener$Stub$Proxy = iUnfoldTransitionListener$Stub$Proxy2;
            } else {
                iUnfoldTransitionListener$Stub$Proxy = (IUnfoldTransitionListener$Stub$Proxy) queryLocalInterface;
            }
        }
        parcel.enforceNoDataAvail();
        ((UnfoldTransitionProgressForwarder) this).remoteListener = iUnfoldTransitionListener$Stub$Proxy;
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
