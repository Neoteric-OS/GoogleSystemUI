package com.android.systemui.screenshot;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IScreenshotProxy$Stub$Proxy implements IScreenshotProxy {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.android.systemui.screenshot.IScreenshotProxy
    public final void dismissKeyguard(IOnDoneCallback iOnDoneCallback) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.systemui.screenshot.IScreenshotProxy");
            obtain.writeStrongInterface(iOnDoneCallback);
            this.mRemote.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.android.systemui.screenshot.IScreenshotProxy
    public final boolean isNotificationShadeExpanded() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.systemui.screenshot.IScreenshotProxy");
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readBoolean();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
