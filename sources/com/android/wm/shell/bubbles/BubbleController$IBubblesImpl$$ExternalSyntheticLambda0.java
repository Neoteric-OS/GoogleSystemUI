package com.android.wm.shell.bubbles;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$IBubblesImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController.IBubblesImpl f$0;

    public /* synthetic */ BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(BubbleController.IBubblesImpl iBubblesImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = iBubblesImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BubbleViewProvider bubbleViewProvider;
        int i = this.$r8$classId;
        BubbleController.IBubblesImpl iBubblesImpl = this.f$0;
        switch (i) {
            case 0:
                iBubblesImpl.mController.getClass();
                break;
            case 1:
                iBubblesImpl.mController.getClass();
                break;
            case 2:
                iBubblesImpl.mController.removeAllBubbles(1);
                break;
            case 3:
                iBubblesImpl.mController.collapseStack();
                break;
            default:
                BubbleController bubbleController = BubbleController.this;
                BubbleBarLayerView bubbleBarLayerView = bubbleController.mLayerView;
                if (bubbleBarLayerView != null && (bubbleViewProvider = bubbleController.mBubbleData.mSelectedBubble) != null && bubbleBarLayerView != null) {
                    bubbleBarLayerView.showExpandedView(bubbleViewProvider);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(BubbleController.IBubblesImpl iBubblesImpl, Intent intent) {
        this.$r8$classId = 0;
        this.f$0 = iBubblesImpl;
    }

    public /* synthetic */ BubbleController$IBubblesImpl$$ExternalSyntheticLambda0(BubbleController.IBubblesImpl iBubblesImpl, ShortcutInfo shortcutInfo) {
        this.$r8$classId = 1;
        this.f$0 = iBubblesImpl;
    }
}
