package com.android.systemui.shared.system.smartspace;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ISysuiUnlockAnimationController extends IInterface {
    void onLauncherSmartspaceStateUpdated(SmartspaceState smartspaceState);

    void setLauncherUnlockController(String str, ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements ISysuiUnlockAnimationController {
        public Stub() {
            attachInterface(this, "com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController");
                return true;
            }
            if (i == 1) {
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    iLauncherUnlockAnimationController$Stub$Proxy = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.systemui.shared.system.smartspace.ILauncherUnlockAnimationController");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof ILauncherUnlockAnimationController$Stub$Proxy)) {
                        ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy2 = new ILauncherUnlockAnimationController$Stub$Proxy();
                        iLauncherUnlockAnimationController$Stub$Proxy2.mRemote = readStrongBinder;
                        iLauncherUnlockAnimationController$Stub$Proxy = iLauncherUnlockAnimationController$Stub$Proxy2;
                    } else {
                        iLauncherUnlockAnimationController$Stub$Proxy = (ILauncherUnlockAnimationController$Stub$Proxy) queryLocalInterface;
                    }
                }
                parcel.enforceNoDataAvail();
                setLauncherUnlockController(readString, iLauncherUnlockAnimationController$Stub$Proxy);
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                SmartspaceState smartspaceState = (SmartspaceState) parcel.readTypedObject(SmartspaceState.CREATOR);
                parcel.enforceNoDataAvail();
                onLauncherSmartspaceStateUpdated(smartspaceState);
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
