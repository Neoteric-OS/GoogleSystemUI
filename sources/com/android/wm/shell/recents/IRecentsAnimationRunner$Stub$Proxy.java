package com.android.wm.shell.recents;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.view.RemoteAnimationTarget;
import android.window.TaskSnapshot;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IRecentsAnimationRunner$Stub$Proxy implements IRecentsAnimationRunner {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    public final void onAnimationCanceled(int[] iArr, TaskSnapshot[] taskSnapshotArr) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentsAnimationRunner");
            obtain.writeIntArray(iArr);
            obtain.writeTypedArray(taskSnapshotArr, 0);
            this.mRemote.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public final void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2, Bundle bundle) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentsAnimationRunner");
            obtain.writeStrongInterface(iRecentsAnimationController);
            obtain.writeTypedArray(remoteAnimationTargetArr, 0);
            obtain.writeTypedArray(remoteAnimationTargetArr2, 0);
            obtain.writeTypedObject(rect, 0);
            obtain.writeTypedObject(rect2, 0);
            obtain.writeTypedObject(bundle, 0);
            this.mRemote.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
