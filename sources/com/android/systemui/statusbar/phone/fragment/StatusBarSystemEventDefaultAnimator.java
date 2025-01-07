package com.android.systemui.statusbar.phone.fragment;

import android.content.res.Resources;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerKt;
import com.android.systemui.util.animation.AnimationUtil$Companion;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarSystemEventDefaultAnimator implements SystemStatusAnimationCallback {
    public boolean isAnimationRunning;
    public final Function1 onAlphaChanged;
    public final Function1 onTranslationXChanged;
    public final int translationXIn;
    public final int translationXOut;

    public StatusBarSystemEventDefaultAnimator(Resources resources, Function1 function1, Function1 function12, boolean z) {
        this.onAlphaChanged = function1;
        this.onTranslationXChanged = function12;
        this.isAnimationRunning = z;
        this.translationXIn = resources.getDimensionPixelSize(R.dimen.ongoing_appops_chip_animation_in_status_bar_translation_x);
        this.translationXOut = resources.getDimensionPixelSize(R.dimen.ongoing_appops_chip_animation_out_status_bar_translation_x);
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationBegin() {
        this.isAnimationRunning = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(MathKt.roundToLong((23 * 1000.0f) / 60.0f));
        ofFloat.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_BAR_X_MOVE_OUT);
        ofFloat.addUpdateListener(new StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1(this, ofFloat, 0));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat2.setDuration(MathKt.roundToLong((8 * 1000.0f) / 60.0f));
        ofFloat2.setInterpolator(null);
        ofFloat2.addUpdateListener(new StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1(this, ofFloat2, 1));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        return animatorSet;
    }

    @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
    public final Animator onSystemEventAnimationFinish(boolean z) {
        this.onTranslationXChanged.invoke(Float.valueOf(this.translationXOut));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(AnimationUtil$Companion.getFrames(23));
        ofFloat.setStartDelay(AnimationUtil$Companion.getFrames(7));
        ofFloat.setInterpolator(SystemStatusAnimationSchedulerKt.STATUS_BAR_X_MOVE_IN);
        ofFloat.addUpdateListener(new StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1(this, ofFloat, 3));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat2.setDuration(AnimationUtil$Companion.getFrames(5));
        ofFloat2.setStartDelay(AnimationUtil$Companion.getFrames(11));
        ofFloat2.setInterpolator(null);
        ofFloat2.addUpdateListener(new StatusBarSystemEventDefaultAnimator$onSystemEventAnimationBegin$moveOut$1$1(this, ofFloat2, 2));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        final int i = 0;
        animatorSet.addListener(new Animator.AnimatorListener(this) { // from class: com.android.systemui.statusbar.phone.fragment.StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1
            public final /* synthetic */ StatusBarSystemEventDefaultAnimator this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationCancel() {
                switch (i) {
                    case 0:
                        break;
                    default:
                        this.this$0.isAnimationRunning = false;
                        break;
                }
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationEnd$1(Animator animator) {
                switch (i) {
                    case 0:
                        this.this$0.isAnimationRunning = false;
                        break;
                }
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationStart$1() {
                int i2 = i;
            }

            private final void onAnimationCancel$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1() {
            }

            private final void onAnimationEnd$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationStart$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnCancel$1() {
            }

            private final void onAnimationStart$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1() {
            }
        });
        final int i2 = 1;
        animatorSet.addListener(new Animator.AnimatorListener(this) { // from class: com.android.systemui.statusbar.phone.fragment.StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1
            public final /* synthetic */ StatusBarSystemEventDefaultAnimator this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationCancel() {
                switch (i2) {
                    case 0:
                        break;
                    default:
                        this.this$0.isAnimationRunning = false;
                        break;
                }
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationEnd$1(Animator animator) {
                switch (i2) {
                    case 0:
                        this.this$0.isAnimationRunning = false;
                        break;
                }
            }

            @Override // androidx.core.animation.Animator.AnimatorListener
            public final void onAnimationStart$1() {
                int i22 = i2;
            }

            private final void onAnimationCancel$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1() {
            }

            private final void onAnimationEnd$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationStart$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnCancel$1() {
            }

            private final void onAnimationStart$1$com$android$systemui$statusbar$phone$fragment$StatusBarSystemEventDefaultAnimator$onSystemEventAnimationFinish$$inlined$doOnEnd$1() {
            }
        });
        return animatorSet;
    }
}
