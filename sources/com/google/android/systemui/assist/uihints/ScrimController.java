package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScrimController {
    public static final LinearInterpolator ALPHA_INTERPOLATOR = new LinearInterpolator();
    public final LightnessProvider mLightnessProvider;
    public float mMedianLightness;
    public final OverlappedElementController mOverlappedElement;
    public final View mScrimView;
    public NgaUiController$$ExternalSyntheticLambda2 mVisibilityListener;
    public ValueAnimator mAlphaAnimator = new ValueAnimator();
    public float mInvocationProgress = 0.0f;
    public boolean mHaveAccurateLightness = false;
    public boolean mIsDozing = false;

    public ScrimController(ViewGroup viewGroup, OverlappedElementController overlappedElementController, LightnessProvider lightnessProvider, TouchInsideHandler touchInsideHandler) {
        View findViewById = viewGroup.findViewById(R.id.scrim);
        this.mScrimView = findViewById;
        findViewById.setBackgroundTintBlendMode(BlendMode.SRC_IN);
        this.mLightnessProvider = lightnessProvider;
        findViewById.setOnClickListener(touchInsideHandler);
        findViewById.setOnTouchListener(touchInsideHandler);
        this.mOverlappedElement = overlappedElementController;
    }

    public final ValueAnimator createRelativeAlphaAnimator(float f) {
        ValueAnimator duration = ValueAnimator.ofFloat(this.mScrimView.getAlpha(), f).setDuration((long) (Math.abs(f - this.mScrimView.getAlpha()) * 300.0f));
        duration.setInterpolator(ALPHA_INTERPOLATOR);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.systemui.assist.uihints.ScrimController$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScrimController scrimController = ScrimController.this;
                scrimController.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                scrimController.mScrimView.setAlpha(floatValue);
                scrimController.mOverlappedElement.setAlpha(1.0f - floatValue);
            }
        });
        return duration;
    }

    public final void refresh() {
        if (!this.mHaveAccurateLightness || this.mIsDozing) {
            setRelativeAlpha(0.0f, false);
            return;
        }
        float f = this.mInvocationProgress;
        if (f > 0.0f) {
            setRelativeAlpha(Math.min(1.0f, f), false);
        } else {
            setRelativeAlpha(0.0f, true);
        }
    }

    public final void setRelativeAlpha(float f, boolean z) {
        if (this.mHaveAccurateLightness || f <= 0.0f) {
            if (f < 0.0f || f > 1.0f) {
                Log.e("ScrimController", "Got unexpected alpha: " + f + ", ignoring");
                return;
            }
            if (this.mAlphaAnimator.isRunning()) {
                this.mAlphaAnimator.cancel();
            }
            OverlappedElementController overlappedElementController = this.mOverlappedElement;
            if (f <= 0.0f) {
                if (!z) {
                    this.mScrimView.setAlpha(0.0f);
                    overlappedElementController.setAlpha(1.0f);
                    setVisibility(8);
                    return;
                } else {
                    ValueAnimator createRelativeAlphaAnimator = createRelativeAlphaAnimator(f);
                    this.mAlphaAnimator = createRelativeAlphaAnimator;
                    createRelativeAlphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.systemui.assist.uihints.ScrimController.1
                        public boolean mCancelled = false;

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            super.onAnimationCancel(animator);
                            this.mCancelled = true;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            if (this.mCancelled) {
                                return;
                            }
                            ScrimController.this.setVisibility(8);
                        }
                    });
                    this.mAlphaAnimator.start();
                    return;
                }
            }
            if (this.mScrimView.getVisibility() != 0) {
                this.mScrimView.setBackgroundTintList(ColorStateList.valueOf(this.mMedianLightness <= 0.4f ? DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT : -1));
                setVisibility(0);
            }
            if (!z) {
                this.mScrimView.setAlpha(f);
                overlappedElementController.setAlpha(1.0f - f);
            } else {
                ValueAnimator createRelativeAlphaAnimator2 = createRelativeAlphaAnimator(f);
                this.mAlphaAnimator = createRelativeAlphaAnimator2;
                createRelativeAlphaAnimator2.start();
            }
        }
    }

    public final void setVisibility(int i) {
        if (i == this.mScrimView.getVisibility()) {
            return;
        }
        this.mScrimView.setVisibility(i);
        NgaUiController$$ExternalSyntheticLambda2 ngaUiController$$ExternalSyntheticLambda2 = this.mVisibilityListener;
        if (ngaUiController$$ExternalSyntheticLambda2 != null) {
            ngaUiController$$ExternalSyntheticLambda2.f$0.refresh$1();
        }
        this.mLightnessProvider.mMuted = i == 0;
        View view = this.mScrimView;
        view.setBackground(i == 0 ? view.getContext().getDrawable(R.drawable.scrim_strip) : null);
        if (i != 0) {
            this.mOverlappedElement.setAlpha(1.0f);
            refresh();
        }
    }
}
