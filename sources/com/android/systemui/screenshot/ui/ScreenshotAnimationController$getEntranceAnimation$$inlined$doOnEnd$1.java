package com.android.systemui.screenshot.ui;

import android.animation.Animator;
import android.view.View;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScreenshotAnimationController this$0;

    public /* synthetic */ ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(ScreenshotAnimationController screenshotAnimationController, int i) {
        this.$r8$classId = i;
        this.this$0 = screenshotAnimationController;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.flashView.setVisibility(8);
                break;
            case 1:
                this.this$0.viewModel.setIsAnimating(false);
                break;
            case 6:
                this.this$0.viewModel.setIsAnimating(false);
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
            case 1:
                break;
            case 2:
                this.this$0.flashView.setVisibility(0);
                break;
            case 3:
                this.this$0.screenshotPreview.setVisibility(4);
                break;
            case 4:
                this.this$0.viewModel.setIsAnimating(true);
                Iterator it = this.this$0.staticUI.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setAlpha(0.0f);
                }
                break;
            case 5:
                this.this$0.screenshotPreview.setVisibility(0);
                break;
            case 6:
                break;
            default:
                this.this$0.viewModel.setIsAnimating(true);
                break;
        }
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$2(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$2(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$3(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getPreviewAnimator$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$2(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$3(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getPreviewAnimator$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationEnd$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$2(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$2(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnStart$3(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getPreviewAnimator$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnStart$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$2(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getSwipeDismissAnimation$$inlined$doOnEnd$1(Animator animator) {
    }
}
