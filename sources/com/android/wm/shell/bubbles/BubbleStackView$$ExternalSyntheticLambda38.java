package com.android.wm.shell.bubbles;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda38 implements Runnable {
    public final /* synthetic */ BubbleStackView f$0;
    public final /* synthetic */ Bubble f$1;
    public final /* synthetic */ BadgedImageView f$2;
    public final /* synthetic */ BubbleViewProvider f$3;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda38(BubbleStackView bubbleStackView, Bubble bubble, BadgedImageView badgedImageView, BubbleViewProvider bubbleViewProvider) {
        this.f$0 = bubbleStackView;
        this.f$1 = bubble;
        this.f$2 = badgedImageView;
        this.f$3 = bubbleViewProvider;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BubbleStackView bubbleStackView = this.f$0;
        Bubble bubble = this.f$1;
        BadgedImageView badgedImageView = this.f$2;
        BubbleViewProvider bubbleViewProvider = this.f$3;
        bubbleStackView.mRemovingLastBubbleWhileExpanded = false;
        bubble.cleanupExpandedView(true);
        if (badgedImageView != null) {
            bubbleStackView.mBubbleContainer.removeView(badgedImageView);
        }
        bubble.cleanupViews();
        bubbleStackView.updateExpandedView();
        if (bubbleViewProvider == bubbleStackView.mExpandedBubble) {
            bubbleStackView.mExpandedBubble = null;
        }
    }
}
