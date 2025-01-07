package com.android.wm.shell.bubbles;

import android.graphics.PointF;
import com.android.wm.shell.bubbles.BadgedImageView;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.animation.StackAnimationController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda27 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;
    public final /* synthetic */ Bubble f$1;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda27(BubbleStackView bubbleStackView, Bubble bubble, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
        this.f$1 = bubble;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleStackView bubbleStackView = this.f$0;
                Bubble bubble = this.f$1;
                bubbleStackView.mAfterFlyoutHidden = null;
                BubbleViewProvider bubbleViewProvider = bubbleStackView.mBubbleToExpandAfterFlyoutCollapse;
                if (bubbleViewProvider != null) {
                    BubbleData bubbleData = bubbleStackView.mBubbleData;
                    bubbleData.setSelectedBubbleInternal(bubbleViewProvider);
                    bubbleData.dispatchPendingChanges();
                    bubbleStackView.mBubbleData.setExpanded(true);
                    bubbleStackView.mBubbleToExpandAfterFlyoutCollapse = null;
                }
                BadgedImageView badgedImageView = bubble.mIconView;
                if (badgedImageView != null) {
                    badgedImageView.removeDotSuppressionFlag(BadgedImageView.SuppressionFlag.FLYOUT_VISIBLE);
                }
                bubbleStackView.updateTemporarilyInvisibleAnimation(false);
                break;
            default:
                BubbleStackView bubbleStackView2 = this.f$0;
                Bubble bubble2 = this.f$1;
                if (!bubbleStackView2.mIsExpanded && bubble2.mIconView != null) {
                    BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = new BubbleStackView$$ExternalSyntheticLambda2(bubbleStackView2, 11);
                    if (bubbleStackView2.mFlyout.getVisibility() == 0) {
                        BubbleFlyoutView bubbleFlyoutView = bubbleStackView2.mFlyout;
                        Bubble.FlyoutMessage flyoutMessage = bubble2.mFlyoutMessage;
                        PointF pointF = bubbleStackView2.mStackAnimationController.mStackPosition;
                        boolean z = !bubble2.showDot();
                        float[] dotCenter = bubble2.mIconView.getDotCenter();
                        bubbleFlyoutView.mOnHide = bubbleStackView2.mAfterFlyoutHidden;
                        bubbleFlyoutView.mDotCenter = dotCenter;
                        bubbleFlyoutView.fade(false, pointF, z, new BubbleFlyoutView$$ExternalSyntheticLambda2(bubbleFlyoutView, flyoutMessage, pointF, z));
                    } else {
                        bubbleStackView2.mFlyout.setVisibility(4);
                        BubbleFlyoutView bubbleFlyoutView2 = bubbleStackView2.mFlyout;
                        Bubble.FlyoutMessage flyoutMessage2 = bubble2.mFlyoutMessage;
                        StackAnimationController stackAnimationController = bubbleStackView2.mStackAnimationController;
                        PointF pointF2 = stackAnimationController.mStackPosition;
                        boolean isStackOnLeftSide = stackAnimationController.isStackOnLeftSide();
                        BadgedImageView badgedImageView2 = bubble2.mIconView;
                        int i = badgedImageView2.mDotColor;
                        BubbleStackView$$ExternalSyntheticLambda27 bubbleStackView$$ExternalSyntheticLambda27 = bubbleStackView2.mAfterFlyoutHidden;
                        float[] dotCenter2 = badgedImageView2.getDotCenter();
                        boolean z2 = !bubble2.showDot();
                        int i2 = bubbleFlyoutView2.mPositioner.mBubbleSize;
                        bubbleFlyoutView2.mBubbleSize = i2;
                        float f = i2 * 0.228f;
                        bubbleFlyoutView2.mOriginalDotSize = f;
                        float f2 = (f * 1.0f) / 2.0f;
                        bubbleFlyoutView2.mNewDotRadius = f2;
                        bubbleFlyoutView2.mNewDotSize = f2 * 2.0f;
                        bubbleFlyoutView2.updateFlyoutMessage(flyoutMessage2);
                        bubbleFlyoutView2.mArrowPointingLeft = isStackOnLeftSide;
                        bubbleFlyoutView2.mDotColor = i;
                        bubbleFlyoutView2.mOnHide = bubbleStackView$$ExternalSyntheticLambda27;
                        bubbleFlyoutView2.mDotCenter = dotCenter2;
                        bubbleFlyoutView2.setCollapsePercent(1.0f);
                        bubbleFlyoutView2.post(new BubbleFlyoutView$$ExternalSyntheticLambda2(bubbleFlyoutView2, pointF2, z2, bubbleStackView$$ExternalSyntheticLambda2));
                    }
                    bubbleStackView2.mFlyout.bringToFront();
                    break;
                }
                break;
        }
    }
}
