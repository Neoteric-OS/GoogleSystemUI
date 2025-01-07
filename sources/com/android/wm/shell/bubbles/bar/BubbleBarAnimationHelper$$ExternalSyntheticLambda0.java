package com.android.wm.shell.bubbles.bar;

import com.android.wm.shell.shared.animation.PhysicsAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarAnimationHelper$$ExternalSyntheticLambda0 implements PhysicsAnimator.UpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleBarAnimationHelper f$0;
    public final /* synthetic */ BubbleBarExpandedView f$1;

    public /* synthetic */ BubbleBarAnimationHelper$$ExternalSyntheticLambda0(BubbleBarAnimationHelper bubbleBarAnimationHelper, BubbleBarExpandedView bubbleBarExpandedView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleBarAnimationHelper;
        this.f$1 = bubbleBarExpandedView;
    }

    @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.UpdateListener
    public final void onAnimationUpdateForProperty(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                this.f$1.setAnimationMatrix(this.f$0.mExpandedViewContainerMatrix);
                break;
            default:
                this.f$1.setAnimationMatrix(this.f$0.mExpandedViewContainerMatrix);
                break;
        }
    }
}
