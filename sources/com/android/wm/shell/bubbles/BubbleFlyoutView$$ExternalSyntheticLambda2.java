package com.android.wm.shell.bubbles;

import android.graphics.PointF;
import com.android.wm.shell.bubbles.Bubble;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleFlyoutView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ BubbleFlyoutView f$0;
    public final /* synthetic */ PointF f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Object f$3;

    public /* synthetic */ BubbleFlyoutView$$ExternalSyntheticLambda2(BubbleFlyoutView bubbleFlyoutView, PointF pointF, boolean z, BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2) {
        this.f$0 = bubbleFlyoutView;
        this.f$1 = pointF;
        this.f$2 = z;
        this.f$3 = bubbleStackView$$ExternalSyntheticLambda2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleFlyoutView bubbleFlyoutView = this.f$0;
                PointF pointF = this.f$1;
                boolean z = this.f$2;
                BubbleStackView$$ExternalSyntheticLambda2 bubbleStackView$$ExternalSyntheticLambda2 = (BubbleStackView$$ExternalSyntheticLambda2) this.f$3;
                bubbleFlyoutView.getClass();
                float height = ((bubbleFlyoutView.mBubbleSize - bubbleFlyoutView.mFlyoutTextContainer.getHeight()) / 2.0f) + pointF.y;
                bubbleFlyoutView.mFlyoutY = height;
                bubbleFlyoutView.setTranslationY(height);
                bubbleFlyoutView.updateFlyoutX(pointF.x);
                bubbleFlyoutView.updateDot(pointF, z);
                bubbleStackView$$ExternalSyntheticLambda2.run();
                break;
            default:
                final BubbleFlyoutView bubbleFlyoutView2 = this.f$0;
                Bubble.FlyoutMessage flyoutMessage = (Bubble.FlyoutMessage) this.f$3;
                final PointF pointF2 = this.f$1;
                final boolean z2 = this.f$2;
                bubbleFlyoutView2.updateFlyoutMessage(flyoutMessage);
                bubbleFlyoutView2.post(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleFlyoutView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BubbleFlyoutView bubbleFlyoutView3 = BubbleFlyoutView.this;
                        PointF pointF3 = pointF2;
                        boolean z3 = z2;
                        bubbleFlyoutView3.getClass();
                        bubbleFlyoutView3.fade(true, pointF3, z3, new BubbleFlyoutView$$ExternalSyntheticLambda1());
                    }
                });
                break;
        }
    }

    public /* synthetic */ BubbleFlyoutView$$ExternalSyntheticLambda2(BubbleFlyoutView bubbleFlyoutView, Bubble.FlyoutMessage flyoutMessage, PointF pointF, boolean z) {
        this.f$0 = bubbleFlyoutView;
        this.f$3 = flyoutMessage;
        this.f$1 = pointF;
        this.f$2 = z;
    }
}
