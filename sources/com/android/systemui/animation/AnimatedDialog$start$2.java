package com.android.systemui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import com.android.app.animation.Interpolators;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimatedDialog$start$2 implements View.OnLayoutChangeListener {
    public Object $dialogContentWithBackground;
    public final /* synthetic */ int $r8$classId = 1;
    public Object this$0;

    public /* synthetic */ AnimatedDialog$start$2() {
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(final View view, final int i, final int i2, final int i3, final int i4, int i5, int i6, int i7, int i8) {
        switch (this.$r8$classId) {
            case 0:
                ((ViewGroup) this.$dialogContentWithBackground).removeOnLayoutChangeListener(this);
                AnimatedDialog animatedDialog = (AnimatedDialog) this.this$0;
                animatedDialog.isOriginalDialogViewLaidOut = true;
                AnimatedDialog.access$maybeStartLaunchAnimation(animatedDialog);
                break;
            default:
                if (i == i5 && i2 == i6) {
                    if (i3 == i7 && i4 == i8) {
                        Rect rect = (Rect) this.$dialogContentWithBackground;
                        if (rect != null) {
                            view.setLeft(rect.left);
                            view.setTop(rect.top);
                            view.setRight(rect.right);
                            view.setBottom(rect.bottom);
                            break;
                        }
                    }
                }
                if (((Rect) this.$dialogContentWithBackground) == null) {
                    this.$dialogContentWithBackground = new Rect(i5, i6, i7, i8);
                }
                final Rect rect2 = (Rect) this.$dialogContentWithBackground;
                Intrinsics.checkNotNull(rect2);
                final int i9 = rect2.left;
                final int i10 = rect2.top;
                final int i11 = rect2.right;
                final int i12 = rect2.bottom;
                ValueAnimator valueAnimator = (ValueAnimator) this.this$0;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
                this.this$0 = null;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat.setDuration(500L);
                ofFloat.setInterpolator(Interpolators.STANDARD);
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.animation.AnimatedDialog$AnimatedBoundsLayoutListener$onLayoutChange$animator$1$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        AnimatedDialog$start$2.this.this$0 = null;
                    }
                });
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.animation.AnimatedDialog$AnimatedBoundsLayoutListener$onLayoutChange$animator$1$2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        float animatedFraction = valueAnimator2.getAnimatedFraction();
                        rect2.left = MathKt.roundToInt(MathUtils.lerp(i9, i, animatedFraction));
                        rect2.top = MathKt.roundToInt(MathUtils.lerp(i10, i2, animatedFraction));
                        rect2.right = MathKt.roundToInt(MathUtils.lerp(i11, i3, animatedFraction));
                        rect2.bottom = MathKt.roundToInt(MathUtils.lerp(i12, i4, animatedFraction));
                        view.setLeft(rect2.left);
                        view.setTop(rect2.top);
                        view.setRight(rect2.right);
                        view.setBottom(rect2.bottom);
                    }
                });
                this.this$0 = ofFloat;
                ofFloat.start();
                break;
        }
    }

    public AnimatedDialog$start$2(Object obj, AnimatedDialog animatedDialog) {
        this.$dialogContentWithBackground = obj;
        this.this$0 = animatedDialog;
    }
}
