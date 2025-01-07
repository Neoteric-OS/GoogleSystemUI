package com.android.wm.shell.splitscreen;

import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.split.SplitDecorManager;
import com.android.wm.shell.common.split.SplitScreenUtils;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda13 implements Consumer {
    public final /* synthetic */ StageCoordinator f$0;
    public final /* synthetic */ StageTaskListener f$1;
    public final /* synthetic */ StageTaskListener f$2;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda13(StageCoordinator stageCoordinator, StageTaskListener stageTaskListener, StageTaskListener stageTaskListener2) {
        this.f$0 = stageCoordinator;
        this.f$1 = stageTaskListener;
        this.f$2 = stageTaskListener2;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        final StageCoordinator stageCoordinator = this.f$0;
        StageTaskListener stageTaskListener = this.f$1;
        StageTaskListener stageTaskListener2 = this.f$2;
        stageCoordinator.getClass();
        final SplitDecorManager splitDecorManager = stageTaskListener.mSplitDecorManager;
        final SplitDecorManager splitDecorManager2 = stageTaskListener2.mSplitDecorManager;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setFocusable(stageCoordinator.mRootTaskInfo.token, true);
        stageCoordinator.setSideStagePosition(SplitScreenUtils.reverseSplitPosition(stageCoordinator.mSideStagePosition), windowContainerTransaction);
        SyncTransactionQueue syncTransactionQueue = stageCoordinator.mSyncQueue;
        syncTransactionQueue.queue(windowContainerTransaction);
        syncTransactionQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda16
            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                StageCoordinator stageCoordinator2 = StageCoordinator.this;
                stageCoordinator2.updateSurfaceBounds(stageCoordinator2.mSplitLayout, transaction, false);
                splitDecorManager.fadeOutVeilAndCleanUp(transaction);
                splitDecorManager2.fadeOutVeilAndCleanUp(transaction);
            }
        });
    }
}
