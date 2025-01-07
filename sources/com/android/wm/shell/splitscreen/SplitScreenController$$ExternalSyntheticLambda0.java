package com.android.wm.shell.splitscreen;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplitScreenController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SplitScreenController f$0;

    public /* synthetic */ SplitScreenController$$ExternalSyntheticLambda0(SplitScreenController splitScreenController, int i) {
        this.$r8$classId = i;
        this.f$0 = splitScreenController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        SplitScreenController splitScreenController = this.f$0;
        switch (i) {
            case 0:
                splitScreenController.onInit();
                break;
            case 1:
                splitScreenController.mStageCoordinator.recordLastActiveStage();
                break;
            case 2:
                StageCoordinator stageCoordinator = splitScreenController.mStageCoordinator;
                if (stageCoordinator.mMainStage.mIsActive) {
                    boolean z = false;
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -2439376142264352980L, 0, null);
                    }
                    StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
                    stageTaskListener.getClass();
                    if (!stageTaskListener.contains(new StageTaskListener$$ExternalSyntheticLambda2(2)) ? stageCoordinator.mSideStagePosition == 0 : stageCoordinator.mSideStagePosition == 1) {
                        z = true;
                    }
                    stageCoordinator.mSplitLayout.flingDividerToDismiss(11, z);
                    break;
                }
                break;
            default:
                StageCoordinator stageCoordinator2 = splitScreenController.mStageCoordinator;
                stageCoordinator2.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2180125002225367388L, 0, null);
                }
                if (stageCoordinator2.mBreakOnNextWake) {
                    stageCoordinator2.dismissSplitKeepingLastActiveStage(3);
                    break;
                }
                break;
        }
    }
}
