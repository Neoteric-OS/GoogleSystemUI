package com.android.wm.shell.bubbles;

import com.android.wm.shell.shared.animation.PhysicsAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda8 implements PhysicsAnimator.UpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda8(BubbleStackView bubbleStackView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
    }

    @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.UpdateListener
    public final void onAnimationUpdateForProperty(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                BubbleStackView bubbleStackView = this.f$0;
                bubbleStackView.mExpandedViewContainer.setAnimationMatrix(bubbleStackView.mExpandedViewContainerMatrix);
                break;
            case 1:
                BubbleStackView bubbleStackView2 = this.f$0;
                bubbleStackView2.mExpandedViewContainer.setAnimationMatrix(bubbleStackView2.mExpandedViewContainerMatrix);
                break;
            default:
                BubbleStackView bubbleStackView3 = this.f$0;
                bubbleStackView3.mExpandedViewContainer.setAnimationMatrix(bubbleStackView3.mExpandedViewContainerMatrix);
                break;
        }
    }
}
