package com.android.wm.shell.bubbles;

import android.graphics.PointF;
import android.view.View;
import com.android.wm.shell.bubbles.BubbleStackView;
import com.android.wm.shell.bubbles.animation.ExpandedAnimationController;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda25(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleStackView bubbleStackView = (BubbleStackView) this.f$0;
                List list = (List) this.f$1;
                bubbleStackView.getClass();
                for (int i = 0; i < list.size(); i++) {
                    bubbleStackView.mBubbleContainer.reorderView(((Bubble) list.get(i)).mIconView, i);
                }
                break;
            case 1:
                BubbleStackView bubbleStackView2 = (BubbleStackView) this.f$0;
                PointF pointF = (PointF) this.f$1;
                ExpandedAnimationController expandedAnimationController = bubbleStackView2.mExpandedAnimationController;
                boolean z = bubbleStackView2.mRemovingLastBubbleWhileExpanded;
                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView2, 14);
                expandedAnimationController.mAnimatingExpand = false;
                expandedAnimationController.mPreparingToCollapse = false;
                expandedAnimationController.mAnimatingCollapse = true;
                expandedAnimationController.mAfterCollapse = bubbleStackView$$ExternalSyntheticLambda2;
                expandedAnimationController.mCollapsePoint = pointF;
                expandedAnimationController.mFadeBubblesDuringCollapse = z;
                expandedAnimationController.startOrUpdatePathAnimation(false);
                break;
            default:
                BubbleStackView.AnonymousClass4 anonymousClass4 = (BubbleStackView.AnonymousClass4) this.f$0;
                View view = (View) this.f$1;
                BubbleStackView bubbleStackView3 = anonymousClass4.this$0;
                bubbleStackView3.dismissBubbleIfExists(bubbleStackView3.mBubbleData.getBubbleWithView(view));
                break;
        }
    }
}
