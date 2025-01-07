package com.android.systemui.screenshot.proxy;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.android.systemui.screenshot.IOnDoneCallback;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferredImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUiProxyClient$dismissKeyguard$onDoneBinder$1 extends Binder implements IOnDoneCallback {
    public final /* synthetic */ CompletableDeferredImpl $completion;

    public SystemUiProxyClient$dismissKeyguard$onDoneBinder$1(CompletableDeferredImpl completableDeferredImpl) {
        this.$completion = completableDeferredImpl;
        attachInterface(this, "com.android.systemui.screenshot.IOnDoneCallback");
    }

    @Override // com.android.systemui.screenshot.IOnDoneCallback
    public final void onDone() {
        this.$completion.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Unit.INSTANCE);
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.screenshot.IOnDoneCallback");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.screenshot.IOnDoneCallback");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.readBoolean();
        parcel.enforceNoDataAvail();
        onDone();
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
