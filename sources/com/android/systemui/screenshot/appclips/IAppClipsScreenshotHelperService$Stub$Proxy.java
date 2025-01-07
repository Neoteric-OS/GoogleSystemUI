package com.android.systemui.screenshot.appclips;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IAppClipsScreenshotHelperService$Stub$Proxy implements IAppClipsScreenshotHelperService {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService
    public final ScreenshotHardwareBufferInternal takeScreenshot(int i) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService");
            obtain.writeInt(i);
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return (ScreenshotHardwareBufferInternal) obtain2.readTypedObject(ScreenshotHardwareBufferInternal.CREATOR);
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
