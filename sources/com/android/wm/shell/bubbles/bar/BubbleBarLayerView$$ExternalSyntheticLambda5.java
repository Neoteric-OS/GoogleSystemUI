package com.android.wm.shell.bubbles.bar;

import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleStackView;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarLayerView$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ BubbleBarLayerView f$0;
    public final /* synthetic */ BubbleBarExpandedView f$1;
    public final /* synthetic */ BubbleBarLayerView$$ExternalSyntheticLambda7 f$2;

    public /* synthetic */ BubbleBarLayerView$$ExternalSyntheticLambda5(BubbleBarLayerView bubbleBarLayerView, BubbleBarExpandedView bubbleBarExpandedView, BubbleBarLayerView$$ExternalSyntheticLambda7 bubbleBarLayerView$$ExternalSyntheticLambda7) {
        this.f$0 = bubbleBarLayerView;
        this.f$1 = bubbleBarExpandedView;
        this.f$2 = bubbleBarLayerView$$ExternalSyntheticLambda7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BubbleBarLayerView bubbleBarLayerView = this.f$0;
        BubbleBarExpandedView bubbleBarExpandedView = this.f$1;
        BubbleBarLayerView$$ExternalSyntheticLambda7 bubbleBarLayerView$$ExternalSyntheticLambda7 = this.f$2;
        bubbleBarLayerView.removeView(bubbleBarExpandedView);
        if (bubbleBarLayerView$$ExternalSyntheticLambda7 != null) {
            bubbleBarLayerView$$ExternalSyntheticLambda7.run();
        }
        if (Collections.unmodifiableList(bubbleBarLayerView.mBubbleData.mBubbles).isEmpty()) {
            BubbleController bubbleController = bubbleBarLayerView.mBubbleController;
            BubbleStackView bubbleStackView = bubbleController.mStackView;
            if (bubbleStackView != null) {
                bubbleStackView.setVisibility(4);
                bubbleController.removeFromWindowManagerMaybe();
                return;
            }
            BubbleBarLayerView bubbleBarLayerView2 = bubbleController.mLayerView;
            if (bubbleBarLayerView2 != null) {
                bubbleBarLayerView2.setVisibility(4);
                bubbleController.removeFromWindowManagerMaybe();
            }
        }
    }
}
