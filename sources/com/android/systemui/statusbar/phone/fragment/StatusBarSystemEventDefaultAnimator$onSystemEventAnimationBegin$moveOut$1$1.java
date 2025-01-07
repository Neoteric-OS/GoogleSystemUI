package com.android.systemui.statusbar.phone.fragment;

import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1 implements Animator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ValueAnimator $this_apply;
    public final /* synthetic */ StatusBarSystemEventDefaultAnimator this$0;

    public /* synthetic */ StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1(StatusBarSystemEventDefaultAnimator statusBarSystemEventDefaultAnimator, ValueAnimator valueAnimator, int i) {
        this.$r8$classId = i;
        this.this$0 = statusBarSystemEventDefaultAnimator;
        this.$this_apply = valueAnimator;
    }

    @Override // androidx.core.animation.Animator.AnimatorUpdateListener
    public final void onAnimationUpdate(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.onTranslationXChanged.invoke(Float.valueOf(-(((Float) this.$this_apply.getAnimatedValue()).floatValue() * r2.translationXIn)));
                break;
            case 1:
                this.this$0.onAlphaChanged.invoke((Float) this.$this_apply.getAnimatedValue());
                break;
            case 2:
                this.this$0.onAlphaChanged.invoke((Float) this.$this_apply.getAnimatedValue());
                break;
            default:
                this.this$0.onTranslationXChanged.invoke(Float.valueOf(((Float) this.$this_apply.getAnimatedValue()).floatValue() * r2.translationXOut));
                break;
        }
    }
}
