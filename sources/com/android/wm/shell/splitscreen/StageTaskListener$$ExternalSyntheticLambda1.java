package com.android.wm.shell.splitscreen;

import android.view.SurfaceControl;
import com.android.wm.shell.common.SurfaceUtils;
import com.android.wm.shell.common.SyncTransactionQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageTaskListener$$ExternalSyntheticLambda1 implements SyncTransactionQueue.TransactionRunnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StageTaskListener f$0;

    public /* synthetic */ StageTaskListener$$ExternalSyntheticLambda1(StageTaskListener stageTaskListener, int i) {
        this.$r8$classId = i;
        this.f$0 = stageTaskListener;
    }

    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
    public final void runWithTransaction(SurfaceControl.Transaction transaction) {
        StageTaskListener stageTaskListener = this.f$0;
        switch (this.$r8$classId) {
            case 0:
                SurfaceControl makeColorLayer = SurfaceUtils.makeColorLayer(stageTaskListener.mRootLeash, "Dim layer");
                transaction.setLayer(makeColorLayer, Integer.MAX_VALUE).setColor(makeColorLayer, new float[]{0.0f, 0.0f, 0.0f});
                stageTaskListener.mDimLayer = makeColorLayer;
                break;
            case 1:
                stageTaskListener.mSplitDecorManager.release(transaction);
                break;
            default:
                transaction.remove(stageTaskListener.mDimLayer);
                stageTaskListener.mSplitDecorManager.release(transaction);
                break;
        }
    }
}
