package com.android.systemui.notetask;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.IBinder;
import android.os.Parcel;
import android.os.UserHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class INoteTaskBubblesService$Stub$Proxy implements INoteTaskBubblesService {
    public IBinder mRemote;

    @Override // com.android.systemui.notetask.INoteTaskBubblesService
    public final boolean areBubblesAvailable() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.systemui.notetask.INoteTaskBubblesService");
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readBoolean();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.android.systemui.notetask.INoteTaskBubblesService
    public final void showOrHideAppBubble(Intent intent, UserHandle userHandle, Icon icon) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.android.systemui.notetask.INoteTaskBubblesService");
            obtain.writeTypedObject(intent, 0);
            obtain.writeTypedObject(userHandle, 0);
            obtain.writeTypedObject(icon, 0);
            this.mRemote.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
