package com.android.systemui.screenshot;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.lifecycle.LifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotProxyService$mBinder$1 extends Binder implements IScreenshotProxy {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ ScreenshotProxyService this$0;

    public ScreenshotProxyService$mBinder$1(ScreenshotProxyService screenshotProxyService) {
        this.this$0 = screenshotProxyService;
        attachInterface(this, "com.android.systemui.screenshot.IScreenshotProxy");
    }

    @Override // com.android.systemui.screenshot.IScreenshotProxy
    public final void dismissKeyguard(IOnDoneCallback iOnDoneCallback) {
        CoroutineTracingKt.launch$default(LifecycleKt.getCoroutineScope(this.this$0.getLifecycle()), null, new ScreenshotProxyService$mBinder$1$dismissKeyguard$1(this.this$0, iOnDoneCallback, null), 6);
    }

    @Override // com.android.systemui.screenshot.IScreenshotProxy
    public final boolean isNotificationShadeExpanded() {
        boolean z = !(this.this$0.mExpansionMgr.state == 0);
        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("isNotificationShadeExpanded(): ", "ScreenshotProxyService", z);
        return z;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IOnDoneCallback iOnDoneCallback;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.screenshot.IScreenshotProxy");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.screenshot.IScreenshotProxy");
            return true;
        }
        if (i == 1) {
            boolean isNotificationShadeExpanded = isNotificationShadeExpanded();
            parcel2.writeNoException();
            parcel2.writeBoolean(isNotificationShadeExpanded);
        } else {
            if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iOnDoneCallback = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.systemui.screenshot.IOnDoneCallback");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnDoneCallback)) {
                    IOnDoneCallback$Stub$Proxy iOnDoneCallback$Stub$Proxy = new IOnDoneCallback$Stub$Proxy();
                    iOnDoneCallback$Stub$Proxy.mRemote = readStrongBinder;
                    iOnDoneCallback = iOnDoneCallback$Stub$Proxy;
                } else {
                    iOnDoneCallback = (IOnDoneCallback) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            dismissKeyguard(iOnDoneCallback);
            parcel2.writeNoException();
        }
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
