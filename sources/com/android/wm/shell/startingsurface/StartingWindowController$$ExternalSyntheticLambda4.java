package com.android.wm.shell.startingsurface;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.startingsurface.StartingWindowController;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StartingWindowController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StartingWindowController f$0;

    public /* synthetic */ StartingWindowController$$ExternalSyntheticLambda4(StartingWindowController startingWindowController, int i) {
        this.$r8$classId = i;
        this.f$0 = startingWindowController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final StartingWindowController startingWindowController = this.f$0;
        switch (i) {
            case 0:
                StartingSurfaceDrawer startingSurfaceDrawer = startingWindowController.mStartingSurfaceDrawer;
                startingSurfaceDrawer.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 5962205083984802852L, 0, null);
                }
                startingSurfaceDrawer.mWindowRecords.clearAllWindows();
                startingSurfaceDrawer.mWindowlessRecords.clearAllWindows();
                synchronized (startingWindowController.mTaskBackgroundColors) {
                    startingWindowController.mTaskBackgroundColors.clear();
                }
                return;
            default:
                startingWindowController.mShellTaskOrganizer.mStartingWindow = startingWindowController;
                startingWindowController.mShellController.addExternalInterface("extra_shell_starting_window", new Supplier() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda8
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        StartingWindowController startingWindowController2 = StartingWindowController.this;
                        startingWindowController2.getClass();
                        return new StartingWindowController.IStartingWindowImpl(startingWindowController2);
                    }
                }, startingWindowController);
                return;
        }
    }
}
