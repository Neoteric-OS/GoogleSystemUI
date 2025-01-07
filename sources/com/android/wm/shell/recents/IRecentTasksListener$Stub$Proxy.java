package com.android.wm.shell.recents;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IRecentTasksListener$Stub$Proxy implements IRecentTasksListener {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.android.wm.shell.recents.IRecentTasksListener
    public final void onRecentTasksChanged() {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentTasksListener");
            this.mRemote.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.android.wm.shell.recents.IRecentTasksListener
    public final void onRunningTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentTasksListener");
            obtain.writeTypedObject(runningTaskInfo, 0);
            this.mRemote.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.android.wm.shell.recents.IRecentTasksListener
    public final void onRunningTaskChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentTasksListener");
            obtain.writeTypedObject(runningTaskInfo, 0);
            this.mRemote.transact(4, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.android.wm.shell.recents.IRecentTasksListener
    public final void onRunningTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentTasksListener");
            obtain.writeTypedObject(runningTaskInfo, 0);
            this.mRemote.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // com.android.wm.shell.recents.IRecentTasksListener
    public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentTasksListener");
            obtain.writeTypedObject(runningTaskInfo, 0);
            this.mRemote.transact(5, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
