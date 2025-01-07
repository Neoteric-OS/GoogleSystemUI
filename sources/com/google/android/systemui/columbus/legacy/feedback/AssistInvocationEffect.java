package com.google.android.systemui.columbus.legacy.feedback;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AssistInvocationEffect implements FeedbackEffect {
    public Animator animation;
    public final AssistManagerGoogle assistManager;
    public float progress;
    public final AssistInvocationEffect$animatorUpdateListener$1 animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.systemui.columbus.legacy.feedback.AssistInvocationEffect$animatorUpdateListener$1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            AssistInvocationEffect assistInvocationEffect = AssistInvocationEffect.this;
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            assistInvocationEffect.progress = floatValue;
            AssistManagerGoogle assistManagerGoogle = assistInvocationEffect.assistManager;
            if (assistManagerGoogle != null) {
                assistManagerGoogle.onInvocationProgress(2, floatValue);
            }
        }
    };
    public final AssistInvocationEffect$animatorListener$1 animatorListener = new Animator.AnimatorListener() { // from class: com.google.android.systemui.columbus.legacy.feedback.AssistInvocationEffect$animatorListener$1
        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            AssistInvocationEffect.this.progress = 0.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            AssistInvocationEffect.this.progress = 0.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.feedback.AssistInvocationEffect$animatorUpdateListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.feedback.AssistInvocationEffect$animatorListener$1] */
    public AssistInvocationEffect(AssistManagerGoogle assistManagerGoogle) {
        this.assistManager = assistManagerGoogle;
    }

    @Override // com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect
    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        Animator animator;
        Animator animator2;
        if (i == 0) {
            Animator animator3 = this.animation;
            if (animator3 != null && animator3.isRunning() && (animator = this.animation) != null) {
                animator.cancel();
            }
            this.animation = null;
            return;
        }
        if (i != 1) {
            return;
        }
        Animator animator4 = this.animation;
        if (animator4 != null && animator4.isRunning() && (animator2 = this.animation) != null) {
            animator2.cancel();
        }
        this.animation = null;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.progress, 1.0f);
        ofFloat.setDuration(200L);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(this.animatorUpdateListener);
        ofFloat.addListener(this.animatorListener);
        this.animation = ofFloat;
        ofFloat.start();
    }
}
