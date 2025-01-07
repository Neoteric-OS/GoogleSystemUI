package com.android.systemui.qs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewAnimationUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSDetailClipper {
    public Animator mAnimator;
    public final TransitionDrawable mBackground;
    public final View mDetail;
    public final AnonymousClass2 mGoneOnEnd;
    public final AnonymousClass1 mReverseBackground = new Runnable() { // from class: com.android.systemui.qs.QSDetailClipper.1
        @Override // java.lang.Runnable
        public final void run() {
            QSDetailClipper qSDetailClipper = QSDetailClipper.this;
            if (qSDetailClipper.mAnimator != null) {
                qSDetailClipper.mBackground.reverseTransition((int) (r0.getDuration() * 0.35d));
            }
        }
    };
    public final AnonymousClass2 mVisibleOnStart;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.qs.QSDetailClipper$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.qs.QSDetailClipper$2] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.qs.QSDetailClipper$2] */
    public QSDetailClipper(View view) {
        final int i = 0;
        this.mVisibleOnStart = new AnimatorListenerAdapter(this) { // from class: com.android.systemui.qs.QSDetailClipper.2
            public final /* synthetic */ QSDetailClipper this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i) {
                    case 0:
                        this.this$0.mAnimator = null;
                        break;
                    default:
                        this.this$0.mDetail.setVisibility(8);
                        this.this$0.mBackground.resetTransition();
                        this.this$0.mAnimator = null;
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                switch (i) {
                    case 0:
                        this.this$0.mDetail.setVisibility(0);
                        break;
                    default:
                        super.onAnimationStart(animator);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mGoneOnEnd = new AnimatorListenerAdapter(this) { // from class: com.android.systemui.qs.QSDetailClipper.2
            public final /* synthetic */ QSDetailClipper this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        this.this$0.mAnimator = null;
                        break;
                    default:
                        this.this$0.mDetail.setVisibility(8);
                        this.this$0.mBackground.resetTransition();
                        this.this$0.mAnimator = null;
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                switch (i2) {
                    case 0:
                        this.this$0.mDetail.setVisibility(0);
                        break;
                    default:
                        super.onAnimationStart(animator);
                        break;
                }
            }
        };
        this.mDetail = view;
        this.mBackground = (TransitionDrawable) view.getBackground();
    }

    public final long animateCircularClip(int i, int i2, boolean z, Animator.AnimatorListener animatorListener) {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.cancel();
        }
        int width = this.mDetail.getWidth() - i;
        int height = this.mDetail.getHeight() - i2;
        int min = (i < 0 || width < 0 || i2 < 0 || height < 0) ? Math.min(Math.min(Math.min(Math.abs(i), Math.abs(i2)), Math.abs(width)), Math.abs(height)) : 0;
        int i3 = i * i;
        int i4 = i2 * i2;
        int i5 = width * width;
        int i6 = height * height;
        int max = (int) Math.max((int) Math.max((int) Math.max((int) Math.ceil(Math.sqrt(i3 + i4)), Math.ceil(Math.sqrt(i4 + i5))), Math.ceil(Math.sqrt(i5 + i6))), Math.ceil(Math.sqrt(i3 + i6)));
        if (z) {
            this.mAnimator = ViewAnimationUtils.createCircularReveal(this.mDetail, i, i2, min, max);
        } else {
            this.mAnimator = ViewAnimationUtils.createCircularReveal(this.mDetail, i, i2, max, min);
        }
        this.mAnimator.setDuration((long) (r10.getDuration() * 1.5d));
        if (animatorListener != null) {
            this.mAnimator.addListener(animatorListener);
        }
        if (z) {
            this.mBackground.startTransition((int) (this.mAnimator.getDuration() * 0.6d));
            this.mAnimator.addListener(this.mVisibleOnStart);
        } else {
            this.mDetail.postDelayed(this.mReverseBackground, (long) (this.mAnimator.getDuration() * 0.65d));
            this.mAnimator.addListener(this.mGoneOnEnd);
        }
        this.mAnimator.start();
        return this.mAnimator.getDuration();
    }
}
