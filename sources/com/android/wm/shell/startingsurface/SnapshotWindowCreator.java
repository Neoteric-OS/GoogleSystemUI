package com.android.wm.shell.startingsurface;

import android.os.RemoteException;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.startingsurface.StartingSurfaceDrawer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotWindowCreator {
    public final ShellExecutor mMainExecutor;
    public final StartingSurfaceDrawer.StartingWindowRecordManager mStartingWindowRecordManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SnapshotWindowRecord extends StartingSurfaceDrawer.SnapshotRecord {
        public final TaskSnapshotWindow mTaskSnapshotWindow;

        public SnapshotWindowRecord(TaskSnapshotWindow taskSnapshotWindow, int i, ShellExecutor shellExecutor, int i2, StartingSurfaceDrawer.StartingWindowRecordManager startingWindowRecordManager) {
            super(i, shellExecutor, i2, startingWindowRecordManager);
            this.mTaskSnapshotWindow = taskSnapshotWindow;
            this.mBGColor = taskSnapshotWindow.mBackgroundPaint.getColor();
        }

        @Override // com.android.wm.shell.startingsurface.StartingSurfaceDrawer.SnapshotRecord
        public final boolean hasImeSurface() {
            return this.mTaskSnapshotWindow.mHasImeSurface;
        }

        @Override // com.android.wm.shell.startingsurface.StartingSurfaceDrawer.SnapshotRecord
        public final void removeImmediately() {
            super.removeImmediately();
            TaskSnapshotWindow taskSnapshotWindow = this.mTaskSnapshotWindow;
            taskSnapshotWindow.getClass();
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -2148041522326587115L, 3, Boolean.valueOf(taskSnapshotWindow.mHasDrawn));
                }
                taskSnapshotWindow.mSession.remove(taskSnapshotWindow.mWindow.asBinder());
            } catch (RemoteException unused) {
            }
        }
    }

    public SnapshotWindowCreator(ShellExecutor shellExecutor, StartingSurfaceDrawer.StartingWindowRecordManager startingWindowRecordManager) {
        this.mMainExecutor = shellExecutor;
        this.mStartingWindowRecordManager = startingWindowRecordManager;
    }
}
