package com.android.wm.shell.splitscreen;

import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.unfold.animation.SplitTaskUnfoldAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SplitScreenController.SplitScreenImpl f$0;
    public final /* synthetic */ SplitTaskUnfoldAnimator f$1;

    public /* synthetic */ SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda0(SplitScreenController.SplitScreenImpl splitScreenImpl, SplitTaskUnfoldAnimator splitTaskUnfoldAnimator, int i) {
        this.$r8$classId = i;
        this.f$0 = splitScreenImpl;
        this.f$1 = splitTaskUnfoldAnimator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                SplitScreenController.SplitScreenImpl splitScreenImpl = this.f$0;
                splitScreenImpl.mExecutors.remove(this.f$1);
                if (splitScreenImpl.mExecutors.size() == 0) {
                    SplitScreenController.this.mStageCoordinator.mListeners.remove(splitScreenImpl.mListener);
                    break;
                }
                break;
            default:
                SplitScreenController.SplitScreenImpl splitScreenImpl2 = this.f$0;
                SplitScreenController.this.mStageCoordinator.sendStatusToListener(this.f$1);
                break;
        }
    }
}
