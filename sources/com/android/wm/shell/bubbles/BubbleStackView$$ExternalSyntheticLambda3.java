package com.android.wm.shell.bubbles;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda3(BubbleStackView bubbleStackView, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleStackView bubbleStackView = this.f$0;
                boolean z = this.f$1;
                BubbleOverflow bubbleOverflow = bubbleStackView.mBubbleOverflow;
                int i = z ? 0 : 8;
                BadgedImageView badgedImageView = bubbleOverflow.overflowBtn;
                if (badgedImageView != null) {
                    badgedImageView.setVisibility(i);
                    break;
                }
                break;
            case 1:
                BubbleStackView bubbleStackView2 = this.f$0;
                if (!this.f$1) {
                    bubbleStackView2.mManageMenuScrim.setVisibility(4);
                    bubbleStackView2.mManageMenuScrim.setTranslationZ(0.0f);
                    break;
                } else {
                    bubbleStackView2.getClass();
                    break;
                }
            default:
                BubbleStackView bubbleStackView3 = this.f$0;
                boolean z2 = this.f$1;
                bubbleStackView3.updatePointerPosition(false);
                bubbleStackView3.mIsExpansionAnimating = false;
                bubbleStackView3.updateExpandedView();
                bubbleStackView3.requestUpdate();
                bubbleStackView3.mExpandedViewContainer.setVisibility(0);
                bubbleStackView3.mExpandedViewAnimationController.animateForImeVisibilityChange(z2);
                break;
        }
    }
}
