package com.android.systemui.navigationbar.gestural;

import android.os.Handler;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.systemui.navigationbar.gestural.BackPanel;
import com.android.systemui.navigationbar.gestural.BackPanelController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackPanelController$failsafeRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BackPanelController this$0;

    public /* synthetic */ BackPanelController$failsafeRunnable$1(BackPanelController backPanelController, int i) {
        this.$r8$classId = i;
        this.this$0 = backPanelController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BackPanelController backPanelController = this.this$0;
                backPanelController.getClass();
                backPanelController.updateArrowState(BackPanelController.GestureState.GONE, true);
                break;
            case 1:
                this.this$0.updateRestingArrowDimens();
                BackPanelController backPanelController2 = this.this$0;
                BackPanel backPanel = (BackPanel) backPanelController2.mView;
                BackPanel.AnimatedFloat animatedFloat = backPanel.backgroundAlpha;
                backPanel.getClass();
                SpringAnimation springAnimation = animatedFloat.animation;
                boolean z = springAnimation.mRunning;
                BackPanelController.DelayedOnAnimationEndListener delayedOnAnimationEndListener = backPanelController2.onEndSetGoneStateListener;
                if (!z) {
                    delayedOnAnimationEndListener.runnable.run();
                    BackPanelController backPanelController3 = this.this$0;
                    Handler handler = backPanelController3.mainHandler;
                    BackPanelController$failsafeRunnable$1 backPanelController$failsafeRunnable$1 = backPanelController3.failsafeRunnable;
                    handler.removeCallbacks(backPanelController$failsafeRunnable$1);
                    handler.postDelayed(backPanelController$failsafeRunnable$1, 350L);
                    break;
                } else {
                    springAnimation.addEndListener(delayedOnAnimationEndListener);
                    break;
                }
            case 2:
                this.this$0.updateArrowState(BackPanelController.GestureState.COMMITTED, false);
                break;
            case 3:
                BackPanelController backPanelController4 = this.this$0;
                backPanelController4.mainHandler.removeCallbacks(backPanelController4.failsafeRunnable);
                this.this$0.updateArrowState(BackPanelController.GestureState.GONE, false);
                break;
            case 4:
                this.this$0.updateArrowState(BackPanelController.GestureState.FLUNG, false);
                break;
            case 5:
                this.this$0.updateArrowState(BackPanelController.GestureState.COMMITTED, false);
                break;
            default:
                BackPanel backPanel2 = (BackPanel) this.this$0.mView;
                backPanel2.scalePivotX.snapTo(backPanel2.backgroundWidth.pos / 2);
                BackPanel.AnimatedFloat.stretchTo$default(backPanel2.scale, 0.0f, Float.valueOf(2.0f), 4);
                break;
        }
    }
}
