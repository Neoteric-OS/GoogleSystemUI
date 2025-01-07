package com.android.wm.shell.splitscreen;

import com.android.wm.shell.splitscreen.StageCoordinator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StageCoordinator.AnonymousClass2 f$0;

    public /* synthetic */ StageCoordinator$2$$ExternalSyntheticLambda0(StageCoordinator.AnonymousClass2 anonymousClass2, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        StageCoordinator.AnonymousClass2 anonymousClass2 = this.f$0;
        switch (i) {
            case 0:
                StageCoordinator stageCoordinator = StageCoordinator.this;
                int size = stageCoordinator.mSideStage.mChildrenTaskInfo.size();
                StageCoordinator stageCoordinator2 = StageCoordinator.this;
                stageCoordinator.exitSplitScreen(size == 0 ? stageCoordinator2.mMainStage : stageCoordinator2.mSideStage, 0);
                break;
            default:
                StageCoordinator.this.exitSplitScreen(null, 0);
                break;
        }
    }
}
