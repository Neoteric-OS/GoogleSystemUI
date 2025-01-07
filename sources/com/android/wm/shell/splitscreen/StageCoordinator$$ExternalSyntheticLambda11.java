package com.android.wm.shell.splitscreen;

import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.split.DividerView;
import com.android.wm.shell.splitscreen.SplitScreenTransitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda11 implements SplitScreenTransitions.TransitionFinishedCallback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StageCoordinator f$0;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda11(StageCoordinator stageCoordinator, int i) {
        this.$r8$classId = i;
        this.f$0 = stageCoordinator;
    }

    @Override // com.android.wm.shell.splitscreen.SplitScreenTransitions.TransitionFinishedCallback
    public void onFinished(WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction) {
        switch (this.$r8$classId) {
            case 1:
                StageCoordinator stageCoordinator = this.f$0;
                stageCoordinator.mMainStage.mSplitDecorManager.release(transaction);
                stageCoordinator.mSideStage.mSplitDecorManager.release(transaction);
                windowContainerTransaction.setReparentLeafTaskIfRelaunch(stageCoordinator.mRootTaskInfo.token, false);
                break;
            default:
                DividerView dividerView = this.f$0.mSplitLayout.mSplitWindowManager.mDividerView;
                if (dividerView != null) {
                    dividerView.setInteractive("onSplitResizeFinish", true, false);
                    break;
                }
                break;
        }
    }
}
