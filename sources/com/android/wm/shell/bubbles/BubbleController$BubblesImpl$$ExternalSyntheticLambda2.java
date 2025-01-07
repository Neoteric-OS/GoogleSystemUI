package com.android.wm.shell.bubbles;

import com.android.systemui.wmshell.BubblesManager$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda2(int i, int i2, Object obj) {
        this.$r8$classId = i2;
        this.f$0 = obj;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((BubblesManager$$ExternalSyntheticLambda2) this.f$0).accept(this.f$1);
                break;
            default:
                BubbleController.IBubblesImpl iBubblesImpl = (BubbleController.IBubblesImpl) this.f$0;
                int i = this.f$1;
                BubbleController bubbleController = iBubblesImpl.this$0;
                bubbleController.mBubblePositioner.mBubbleBarTopOnScreen = i;
                BubbleBarLayerView bubbleBarLayerView = bubbleController.mLayerView;
                if (bubbleBarLayerView != null) {
                    bubbleBarLayerView.updateExpandedView();
                    break;
                }
                break;
        }
    }
}
