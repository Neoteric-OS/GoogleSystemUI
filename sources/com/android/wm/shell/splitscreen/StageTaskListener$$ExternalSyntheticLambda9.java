package com.android.wm.shell.splitscreen;

import android.app.ActivityManager;
import android.graphics.Point;
import android.util.Slog;
import android.view.SurfaceControl;
import com.android.wm.shell.common.SyncTransactionQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageTaskListener$$ExternalSyntheticLambda9 implements SyncTransactionQueue.TransactionRunnable {
    public final /* synthetic */ SurfaceControl f$0;
    public final /* synthetic */ ActivityManager.RunningTaskInfo f$1;
    public final /* synthetic */ Point f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ StageTaskListener$$ExternalSyntheticLambda9(SurfaceControl surfaceControl, ActivityManager.RunningTaskInfo runningTaskInfo, Point point, boolean z) {
        this.f$0 = surfaceControl;
        this.f$1 = runningTaskInfo;
        this.f$2 = point;
        this.f$3 = z;
    }

    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
    public final void runWithTransaction(SurfaceControl.Transaction transaction) {
        SurfaceControl surfaceControl = this.f$0;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.f$1;
        Point point = this.f$2;
        if (!surfaceControl.isValid()) {
            Slog.w("StageTaskListener", "Skip updating invalid child task surface of task#" + runningTaskInfo.taskId);
            return;
        }
        transaction.setCrop(surfaceControl, null);
        transaction.setPosition(surfaceControl, point.x, point.y);
        if (this.f$3) {
            transaction.setAlpha(surfaceControl, 1.0f);
            transaction.setMatrix(surfaceControl, 1.0f, 0.0f, 0.0f, 1.0f);
            transaction.show(surfaceControl);
        }
    }
}
