package com.android.wm.shell.bubbles;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda37 implements Consumer {
    public final /* synthetic */ BubbleStackView f$0;
    public final /* synthetic */ BubbleViewProvider f$1;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda37(BubbleStackView bubbleStackView, BubbleViewProvider bubbleViewProvider) {
        this.f$0 = bubbleStackView;
        this.f$1 = bubbleViewProvider;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        BubbleStackView bubbleStackView = this.f$0;
        BubbleViewProvider bubbleViewProvider = this.f$1;
        bubbleStackView.mAnimatingOutSurfaceContainer.setVisibility(((Boolean) obj).booleanValue() ? 0 : 4);
        bubbleStackView.showNewlySelectedBubble(bubbleViewProvider);
    }
}
