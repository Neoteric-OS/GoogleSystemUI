package com.android.wm.shell.pip.phone;

import android.os.SystemClock;
import com.android.wm.shell.pip.PipAnimationController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$IPipImpl$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ PipController$IPipImpl$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        PipController pipController = (PipController) obj;
        switch (this.$r8$classId) {
            case 0:
                PipAnimationController pipAnimationController = pipController.mPipAnimationController;
                pipAnimationController.mOneShotAnimationType = 1;
                pipAnimationController.mLastOneShotAlphaAnimationTime = SystemClock.uptimeMillis();
                break;
            default:
                pipController.setPinnedStackAnimationListener(null);
                break;
        }
    }
}
