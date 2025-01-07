package com.android.systemui.screenshot.ui.binder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getActionsAnimator$1;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotShelfViewBinder$bind$swipeGestureListener$2 extends Lambda implements Function0 {
    final /* synthetic */ ScreenshotAnimationController $animationController;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenshotShelfViewBinder$bind$swipeGestureListener$2(ScreenshotAnimationController screenshotAnimationController) {
        super(0);
        this.$animationController = screenshotAnimationController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ScreenshotAnimationController screenshotAnimationController = this.$animationController;
        Animator animator = screenshotAnimationController.animator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(screenshotAnimationController.view.getTranslationX(), 0.0f);
        ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 6));
        screenshotAnimationController.animator = ofFloat;
        ofFloat.start();
        return Unit.INSTANCE;
    }
}
