package com.android.wm.shell.unfold;

import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.unfold.animation.UnfoldTaskAnimator;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class UnfoldAnimationController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ UnfoldAnimationController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                UnfoldAnimationController unfoldAnimationController = (UnfoldAnimationController) obj;
                ShellUnfoldProgressProvider shellUnfoldProgressProvider = unfoldAnimationController.mUnfoldProgressProvider;
                ShellExecutor shellExecutor = unfoldAnimationController.mExecutor;
                shellUnfoldProgressProvider.addListener(shellExecutor, unfoldAnimationController);
                for (int i2 = 0; i2 < ((ArrayList) unfoldAnimationController.mAnimators).size(); i2++) {
                    UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) ((ArrayList) unfoldAnimationController.mAnimators).get(i2);
                    unfoldTaskAnimator.init();
                    ((HandlerExecutor) shellExecutor).executeDelayed(new UnfoldAnimationController$$ExternalSyntheticLambda0(1, unfoldTaskAnimator), 0L);
                }
                break;
            default:
                ((UnfoldTaskAnimator) obj).start();
                break;
        }
    }
}
