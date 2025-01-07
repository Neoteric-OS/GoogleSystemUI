package com.android.systemui.screenshot.ui;

import android.animation.Animator;
import com.android.systemui.screenshot.scroll.LongScreenshotActivity$$ExternalSyntheticLambda4;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public final /* synthetic */ Object $onTransitionEnd$inlined;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(int i, Object obj) {
        this.$r8$classId = i;
        this.$onTransitionEnd$inlined = obj;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                ((LongScreenshotActivity$$ExternalSyntheticLambda4) this.$onTransitionEnd$inlined).run();
                break;
            default:
                ((Function0) this.$onTransitionEnd$inlined).invoke();
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i = this.$r8$classId;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.$r8$classId;
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$lambda$6$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationCancel$com$android$systemui$screenshot$ui$ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$lambda$6$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationRepeat$com$android$systemui$screenshot$ui$ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ui$ScreenshotAnimationController$getEntranceAnimation$lambda$6$$inlined$doOnEnd$1(Animator animator) {
    }

    private final void onAnimationStart$com$android$systemui$screenshot$ui$ScreenshotAnimationController$runLongScreenshotTransition$$inlined$doOnEnd$1(Animator animator) {
    }
}
