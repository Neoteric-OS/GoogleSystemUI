package com.android.wm.shell.desktopmode;

import android.view.SurfaceControl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1 implements Runnable {
    public final /* synthetic */ SurfaceControl.Transaction $tx;
    public final /* synthetic */ DesktopTasksController this$0;

    public DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1(DesktopTasksController desktopTasksController, SurfaceControl.Transaction transaction) {
        this.this$0 = desktopTasksController;
        this.$tx = transaction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DesktopModeVisualIndicator desktopModeVisualIndicator = this.this$0.visualIndicator;
        if (desktopModeVisualIndicator != null) {
            desktopModeVisualIndicator.releaseVisualIndicator(this.$tx);
        }
        this.this$0.visualIndicator = null;
    }
}
