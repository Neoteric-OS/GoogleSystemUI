package com.android.systemui.wallet.controller;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WalletContextualLocationsService$binder$1 extends Binder implements IInterface {
    public final /* synthetic */ WalletContextualLocationsService this$0;

    public WalletContextualLocationsService$binder$1(WalletContextualLocationsService walletContextualLocationsService) {
        this.this$0 = walletContextualLocationsService;
        attachInterface(this, "com.android.systemui.wallet.controller.IWalletContextualLocationsService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IWalletCardsUpdatedListener iWalletCardsUpdatedListener;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.android.systemui.wallet.controller.IWalletContextualLocationsService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.android.systemui.wallet.controller.IWalletContextualLocationsService");
            return true;
        }
        if (i == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iWalletCardsUpdatedListener = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.android.systemui.wallet.controller.IWalletCardsUpdatedListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IWalletCardsUpdatedListener)) {
                    IWalletCardsUpdatedListener$Stub$Proxy iWalletCardsUpdatedListener$Stub$Proxy = new IWalletCardsUpdatedListener$Stub$Proxy();
                    iWalletCardsUpdatedListener$Stub$Proxy.mRemote = readStrongBinder;
                    iWalletCardsUpdatedListener = iWalletCardsUpdatedListener$Stub$Proxy;
                } else {
                    iWalletCardsUpdatedListener = (IWalletCardsUpdatedListener) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            this.this$0.addWalletCardsUpdatedListenerInternal(iWalletCardsUpdatedListener);
            parcel2.writeNoException();
        } else {
            if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            parcel.enforceNoDataAvail();
            this.this$0.onWalletContextualLocationsStateUpdatedInternal(createStringArrayList);
            parcel2.writeNoException();
        }
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
