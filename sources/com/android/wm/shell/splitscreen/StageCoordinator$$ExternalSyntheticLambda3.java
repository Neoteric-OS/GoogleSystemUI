package com.android.wm.shell.splitscreen;

import android.graphics.Rect;
import android.view.SurfaceControl;
import com.android.wm.shell.common.SyncTransactionQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda3 implements SyncTransactionQueue.TransactionRunnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
    public final void runWithTransaction(SurfaceControl.Transaction transaction) {
        switch (this.$r8$classId) {
            case 0:
                StageCoordinator stageCoordinator = (StageCoordinator) this.f$0;
                SurfaceControl surfaceControl = stageCoordinator.mSideStage.mRootLeash;
                Rect rect = stageCoordinator.mTempRect1;
                transaction.setPosition(surfaceControl, rect.left, rect.top);
                break;
            case 1:
                StageCoordinator stageCoordinator2 = (StageCoordinator) this.f$0;
                SurfaceControl surfaceControl2 = stageCoordinator2.mSideStage.mRootLeash;
                Rect rect2 = stageCoordinator2.mTempRect1;
                transaction.setPosition(surfaceControl2, rect2.left, rect2.right);
                break;
            case 2:
                StageCoordinator stageCoordinator3 = (StageCoordinator) this.f$0;
                if (!stageCoordinator3.mIsDropEntering) {
                    stageCoordinator3.mShowDecorImmediately = true;
                    stageCoordinator3.mSplitLayout.flingDividerToCenter(null);
                    break;
                } else {
                    stageCoordinator3.updateSurfaceBounds(stageCoordinator3.mSplitLayout, transaction, false);
                    stageCoordinator3.mIsDropEntering = false;
                    stageCoordinator3.mSkipEvictingMainStageChildren = false;
                    break;
                }
            case 3:
                StageCoordinator stageCoordinator4 = (StageCoordinator) this.f$0;
                if (!stageCoordinator4.mIsDropEntering) {
                    stageCoordinator4.mShowDecorImmediately = true;
                    stageCoordinator4.mSplitLayout.flingDividerToCenter(null);
                    break;
                } else {
                    stageCoordinator4.updateSurfaceBounds(stageCoordinator4.mSplitLayout, transaction, false);
                    stageCoordinator4.mIsDropEntering = false;
                    stageCoordinator4.mSkipEvictingMainStageChildren = false;
                    break;
                }
            default:
                StageCoordinator.this.applyDividerVisibility(transaction);
                break;
        }
    }
}
