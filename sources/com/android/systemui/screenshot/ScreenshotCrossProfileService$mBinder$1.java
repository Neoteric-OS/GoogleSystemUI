package com.android.systemui.screenshot;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotCrossProfileService$mBinder$1 extends Binder implements ICrossProfileService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ ScreenshotCrossProfileService this$0;

    public ScreenshotCrossProfileService$mBinder$1(ScreenshotCrossProfileService screenshotCrossProfileService) {
        this.this$0 = screenshotCrossProfileService;
        attachInterface(this, "com.android.systemui.screenshot.ICrossProfileService");
    }

    @Override // com.android.systemui.screenshot.ICrossProfileService
    public final void launchIntent(Intent intent, Bundle bundle) {
        this.this$0.startActivity(intent, bundle);
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.screenshot.ICrossProfileService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.screenshot.ICrossProfileService");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
        Bundle bundle = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
        parcel.enforceNoDataAvail();
        launchIntent(intent, bundle);
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
