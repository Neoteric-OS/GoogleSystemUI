package com.android.wm.shell.pip.phone;

import android.animation.AnimatorSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipMenuView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PipMenuView$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((PipMenuView) obj).hideMenu$1();
                break;
            case 1:
                PipMenuView pipMenuView = (PipMenuView) obj;
                pipMenuView.hideMenu$1();
                pipMenuView.mController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(0));
                pipMenuView.mAllowTouches = true;
                break;
            case 2:
                PipMenuView pipMenuView2 = (PipMenuView) obj;
                AnimatorSet animatorSet = pipMenuView2.mMenuContainerAnimator;
                if (animatorSet != null) {
                    animatorSet.setStartDelay(30L);
                    pipMenuView2.setVisibility(0);
                    pipMenuView2.mMenuContainerAnimator.start();
                    break;
                }
                break;
            default:
                ((PhonePipMenuController) obj).mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(2));
                break;
        }
    }
}
