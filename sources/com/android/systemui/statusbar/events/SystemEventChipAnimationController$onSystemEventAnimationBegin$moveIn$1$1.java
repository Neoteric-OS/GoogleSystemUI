package com.android.systemui.statusbar.events;

import android.view.View;
import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1 implements Animator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ValueAnimator $this_apply;
    public final /* synthetic */ SystemEventChipAnimationController this$0;

    public /* synthetic */ SystemEventChipAnimationController$onSystemEventAnimationBegin$moveIn$1$1(SystemEventChipAnimationController systemEventChipAnimationController, ValueAnimator valueAnimator, int i) {
        this.$r8$classId = i;
        this.this$0 = systemEventChipAnimationController;
        this.$this_apply = valueAnimator;
    }

    @Override // androidx.core.animation.Animator.AnimatorUpdateListener
    public final void onAnimationUpdate(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                SystemEventChipAnimationController.access$updateAnimatedViewBoundsWidth(this.this$0, ((Integer) this.$this_apply.getAnimatedValue()).intValue());
                break;
            case 1:
                Object obj = this.this$0.currentAnimatedView;
                View view = obj != null ? (View) obj : null;
                if (view != null) {
                    view.setAlpha(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                    break;
                }
                break;
            case 2:
                BackgroundAnimatableView backgroundAnimatableView = this.this$0.currentAnimatedView;
                View contentView = backgroundAnimatableView != null ? backgroundAnimatableView.getContentView() : null;
                if (contentView != null) {
                    contentView.setAlpha(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                    break;
                }
                break;
            case 3:
                SystemEventChipAnimationController systemEventChipAnimationController = this.this$0;
                if (systemEventChipAnimationController.currentAnimatedView != null) {
                    SystemEventChipAnimationController.access$updateAnimatedViewBoundsWidth(systemEventChipAnimationController, ((Integer) this.$this_apply.getAnimatedValue()).intValue());
                    break;
                }
                break;
            case 4:
                SystemEventChipAnimationController systemEventChipAnimationController2 = this.this$0;
                int i = systemEventChipAnimationController2.animationDirection;
                ValueAnimator valueAnimator = this.$this_apply;
                int intValue = i == 1 ? ((Integer) valueAnimator.getAnimatedValue()).intValue() : -((Integer) valueAnimator.getAnimatedValue()).intValue();
                Object obj2 = systemEventChipAnimationController2.currentAnimatedView;
                View view2 = obj2 != null ? (View) obj2 : null;
                if (view2 != null) {
                    view2.setTranslationX(intValue);
                    break;
                }
                break;
            case 5:
                SystemEventChipAnimationController.access$updateAnimatedViewBoundsWidth(this.this$0, ((Integer) this.$this_apply.getAnimatedValue()).intValue());
                break;
            case 6:
                SystemEventChipAnimationController.access$updateAnimatedViewBoundsWidth(this.this$0, ((Integer) this.$this_apply.getAnimatedValue()).intValue());
                break;
            case 7:
                Object obj3 = this.this$0.currentAnimatedView;
                View view3 = obj3 != null ? (View) obj3 : null;
                if (view3 != null) {
                    view3.setAlpha(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                    break;
                }
                break;
            default:
                BackgroundAnimatableView backgroundAnimatableView2 = this.this$0.currentAnimatedView;
                View contentView2 = backgroundAnimatableView2 != null ? backgroundAnimatableView2.getContentView() : null;
                if (contentView2 != null) {
                    contentView2.setAlpha(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                    break;
                }
                break;
        }
    }
}
