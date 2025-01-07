package com.android.wm.shell.bubbles;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleOverflowContainerView$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ BubbleExpandedViewManager f$0;

    public /* synthetic */ BubbleOverflowContainerView$$ExternalSyntheticLambda1(BubbleExpandedViewManager bubbleExpandedViewManager) {
        this.f$0 = bubbleExpandedViewManager;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((BubbleExpandedViewManager$Companion$fromBubbleController$1) this.f$0).$controller.promoteBubbleFromOverflow((Bubble) obj);
    }
}
