package com.android.wm.shell.bubbles;

import android.view.Choreographer;
import android.window.ScreenCapture;
import com.android.wm.shell.bubbles.BubbleStackView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;
    public final /* synthetic */ BubbleStackView$$ExternalSyntheticLambda37 f$1;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda0(BubbleStackView bubbleStackView, BubbleStackView$$ExternalSyntheticLambda37 bubbleStackView$$ExternalSyntheticLambda37, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
        this.f$1 = bubbleStackView$$ExternalSyntheticLambda37;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleStackView bubbleStackView = this.f$0;
                BubbleStackView$$ExternalSyntheticLambda37 bubbleStackView$$ExternalSyntheticLambda37 = this.f$1;
                bubbleStackView.getClass();
                bubbleStackView.post(new BubbleStackView$$ExternalSyntheticLambda7(0, bubbleStackView$$ExternalSyntheticLambda37));
                break;
            default:
                BubbleStackView bubbleStackView2 = this.f$0;
                BubbleStackView$$ExternalSyntheticLambda37 bubbleStackView$$ExternalSyntheticLambda372 = this.f$1;
                ScreenCapture.ScreenshotHardwareBuffer screenshotHardwareBuffer = bubbleStackView2.mAnimatingOutBubbleBuffer;
                if (screenshotHardwareBuffer != null && screenshotHardwareBuffer.getHardwareBuffer() != null && !bubbleStackView2.mAnimatingOutBubbleBuffer.getHardwareBuffer().isClosed()) {
                    if (!bubbleStackView2.mIsExpanded || !bubbleStackView2.mAnimatingOutSurfaceReady) {
                        bubbleStackView$$ExternalSyntheticLambda372.accept(Boolean.FALSE);
                        break;
                    } else {
                        bubbleStackView2.mAnimatingOutSurfaceView.getHolder().getSurface().attachAndQueueBufferWithColorSpace(bubbleStackView2.mAnimatingOutBubbleBuffer.getHardwareBuffer(), bubbleStackView2.mAnimatingOutBubbleBuffer.getColorSpace());
                        bubbleStackView2.mAnimatingOutSurfaceView.setAlpha(1.0f);
                        bubbleStackView2.mExpandedViewContainer.setVisibility(4);
                        BubbleStackView.AnonymousClass1 anonymousClass1 = bubbleStackView2.mSurfaceSynchronizer;
                        BubbleStackView$$ExternalSyntheticLambda0 bubbleStackView$$ExternalSyntheticLambda0 = new BubbleStackView$$ExternalSyntheticLambda0(bubbleStackView2, bubbleStackView$$ExternalSyntheticLambda372, 0);
                        anonymousClass1.getClass();
                        Choreographer.getInstance().postFrameCallback(new BubbleStackView.AnonymousClass1.ChoreographerFrameCallbackC02691(bubbleStackView$$ExternalSyntheticLambda0));
                        break;
                    }
                } else {
                    bubbleStackView$$ExternalSyntheticLambda372.accept(Boolean.FALSE);
                    break;
                }
                break;
        }
    }
}
