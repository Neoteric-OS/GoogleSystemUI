package com.google.input.algos.spd;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IScreenProtectorDetectorService extends IInterface {
    public static final String DESCRIPTOR = "com$google$input$algos$spd$IScreenProtectorDetectorService".replace('$', '.');

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements IScreenProtectorDetectorService {
        public static final /* synthetic */ int $r8$clinit = 0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Proxy implements IScreenProtectorDetectorService {
            public IBinder mRemote;

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void updateNotifierPacket(ScreenProtectorNotifierPacket screenProtectorNotifierPacket) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken(IScreenProtectorDetectorService.DESCRIPTOR);
                    obtain.writeTypedObject(screenProtectorNotifierPacket, 0);
                    if (this.mRemote.transact(5, obtain, null, 1)) {
                    } else {
                        throw new RemoteException("Method updateNotifierPacket is unimplemented.");
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public final void updateScreenProtectorMode(boolean z) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken(IScreenProtectorDetectorService.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    if (this.mRemote.transact(3, obtain, null, 1)) {
                    } else {
                        throw new RemoteException("Method updateScreenProtectorMode is unimplemented.");
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public final void updateScreenProtectorNotificationPreference(boolean z) {
                Parcel obtain = Parcel.obtain(this.mRemote);
                try {
                    obtain.writeInterfaceToken(IScreenProtectorDetectorService.DESCRIPTOR);
                    obtain.writeBoolean(z);
                    if (this.mRemote.transact(4, obtain, null, 1)) {
                    } else {
                        throw new RemoteException("Method updateScreenProtectorNotificationPreference is unimplemented.");
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
