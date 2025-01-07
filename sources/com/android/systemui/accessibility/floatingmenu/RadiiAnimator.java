package com.android.systemui.accessibility.floatingmenu;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.util.MathUtils;
import android.view.animation.DecelerateInterpolator;
import com.android.systemui.accessibility.floatingmenu.MenuAnimationController;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RadiiAnimator {
    public final ValueAnimator mAnimationDriver;
    public float[] mEndValues;
    public float[] mStartValues;

    public RadiiAnimator(float[] fArr, final MenuAnimationController.AnonymousClass1 anonymousClass1) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnimationDriver = ofFloat;
        fArr = fArr.length != 8 ? Arrays.copyOf(fArr, 8) : fArr;
        this.mStartValues = fArr;
        this.mEndValues = fArr;
        ofFloat.setRepeatCount(0);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.accessibility.floatingmenu.RadiiAnimator$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                RadiiAnimator radiiAnimator = RadiiAnimator.this;
                MenuAnimationController.AnonymousClass1 anonymousClass12 = anonymousClass1;
                radiiAnimator.getClass();
                ((GradientDrawable) ((InstantInsetLayerDrawable) MenuAnimationController.this.mMenuView.getBackground()).getDrawable(0)).setCornerRadii(radiiAnimator.evaluate(valueAnimator.getAnimatedFraction()));
            }
        });
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.accessibility.floatingmenu.RadiiAnimator.1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                MenuAnimationController.AnonymousClass1 anonymousClass12 = anonymousClass1;
                RadiiAnimator radiiAnimator = RadiiAnimator.this;
                ((GradientDrawable) ((InstantInsetLayerDrawable) MenuAnimationController.this.mMenuView.getBackground()).getDrawable(0)).setCornerRadii(radiiAnimator.evaluate(radiiAnimator.mAnimationDriver.getAnimatedFraction()));
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                anonymousClass1.getClass();
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                MenuAnimationController.AnonymousClass1 anonymousClass12 = anonymousClass1;
                ((GradientDrawable) ((InstantInsetLayerDrawable) MenuAnimationController.this.mMenuView.getBackground()).getDrawable(0)).setCornerRadii(RadiiAnimator.this.evaluate(0.0f));
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
            }
        });
        ofFloat.setInterpolator(new DecelerateInterpolator());
    }

    public float[] evaluate(float f) {
        float[] fArr = new float[8];
        for (int i = 0; i < 8; i++) {
            fArr[i] = MathUtils.lerp(this.mStartValues[i], this.mEndValues[i], f);
        }
        return fArr;
    }
}
