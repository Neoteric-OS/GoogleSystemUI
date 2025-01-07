package com.android.wm.shell.shared.bubbles;

import android.view.View;
import com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BaseBubblePinController$animateOut$1 implements Runnable {
    public final /* synthetic */ Object $endAction;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ BubbleExpandedViewPinController this$0;

    public BaseBubblePinController$animateOut$1(BubbleExpandedViewPinController bubbleExpandedViewPinController, View view) {
        this.this$0 = bubbleExpandedViewPinController;
        this.$endAction = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Runnable runnable = (Runnable) this.$endAction;
                if (runnable != null) {
                    runnable.run();
                }
                this.this$0.dropTargetAnimator = null;
                break;
            default:
                BubbleExpandedViewPinController bubbleExpandedViewPinController = this.this$0;
                bubbleExpandedViewPinController.container.removeView((View) this.$endAction);
                bubbleExpandedViewPinController.dropTargetView = null;
                break;
        }
    }

    public BaseBubblePinController$animateOut$1(Runnable runnable, BubbleExpandedViewPinController bubbleExpandedViewPinController) {
        this.$endAction = runnable;
        this.this$0 = bubbleExpandedViewPinController;
    }
}
