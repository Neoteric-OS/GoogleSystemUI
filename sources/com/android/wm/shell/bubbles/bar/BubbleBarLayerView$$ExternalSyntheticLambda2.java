package com.android.wm.shell.bubbles.bar;

import android.view.TouchDelegate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarLayerView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ BubbleBarLayerView f$0;

    public /* synthetic */ BubbleBarLayerView$$ExternalSyntheticLambda2(BubbleBarLayerView bubbleBarLayerView) {
        this.f$0 = bubbleBarLayerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BubbleBarLayerView bubbleBarLayerView = this.f$0;
        BubbleBarExpandedView bubbleBarExpandedView = bubbleBarLayerView.mExpandedView;
        if (bubbleBarExpandedView == null) {
            return;
        }
        bubbleBarExpandedView.mHandleView.getBoundsOnScreen(bubbleBarLayerView.mHandleTouchBounds);
        bubbleBarLayerView.mHandleTouchBounds.top -= bubbleBarLayerView.mPositioner.mBubblePaddingTop;
        bubbleBarLayerView.setTouchDelegate(new TouchDelegate(bubbleBarLayerView.mHandleTouchBounds, bubbleBarLayerView.mExpandedView.mHandleView));
    }
}
