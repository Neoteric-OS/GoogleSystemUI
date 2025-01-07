package com.android.wm.shell.bubbles.animation;

import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ExpandedAnimationController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ExpandedAnimationController f$0;

    public /* synthetic */ ExpandedAnimationController$$ExternalSyntheticLambda0(ExpandedAnimationController expandedAnimationController, int i) {
        this.$r8$classId = i;
        this.f$0 = expandedAnimationController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ExpandedAnimationController expandedAnimationController = this.f$0;
        switch (i) {
            case 0:
                expandedAnimationController.getClass();
                break;
            case 1:
                expandedAnimationController.mAnimatingExpand = false;
                Runnable runnable = expandedAnimationController.mAfterExpand;
                if (runnable != null) {
                    runnable.run();
                }
                expandedAnimationController.mAfterExpand = null;
                expandedAnimationController.updateBubblePositions();
                break;
            default:
                expandedAnimationController.mAnimatingCollapse = false;
                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = expandedAnimationController.mAfterCollapse;
                if (bubbleStackView$$ExternalSyntheticLambda2 != null) {
                    bubbleStackView$$ExternalSyntheticLambda2.run();
                }
                expandedAnimationController.mAfterCollapse = null;
                expandedAnimationController.mFadeBubblesDuringCollapse = false;
                break;
        }
    }
}
