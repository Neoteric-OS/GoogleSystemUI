package com.android.systemui.assist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistDisclosure {
    public final Context mContext;
    public final Handler mHandler;
    public final AnonymousClass1 mShowRunnable = new Runnable() { // from class: com.android.systemui.assist.AssistDisclosure.1
        @Override // java.lang.Runnable
        public final void run() {
            AssistDisclosure assistDisclosure = AssistDisclosure.this;
            if (assistDisclosure.mView == null) {
                assistDisclosure.mView = assistDisclosure.new AssistDisclosureView(assistDisclosure.mContext);
            }
            if (assistDisclosure.mViewAdded) {
                return;
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2015, 525576, -3);
            layoutParams.setTitle("AssistDisclosure");
            layoutParams.setFitInsetsTypes(0);
            assistDisclosure.mWm.addView(assistDisclosure.mView, layoutParams);
            assistDisclosure.mViewAdded = true;
        }
    };
    public AssistDisclosureView mView;
    public boolean mViewAdded;
    public final ViewCaptureAwareWindowManager mWm;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AssistDisclosureView extends View implements ValueAnimator.AnimatorUpdateListener {
        public int mAlpha;
        public final ValueAnimator mAlphaInAnimator;
        public final ValueAnimator mAlphaOutAnimator;
        public final AnimatorSet mAnimator;
        public final Paint mPaint;
        public final Paint mShadowPaint;
        public final float mShadowThickness;
        public final float mThickness;

        public AssistDisclosureView(Context context) {
            super(context);
            Paint paint = new Paint();
            this.mPaint = paint;
            Paint paint2 = new Paint();
            this.mShadowPaint = paint2;
            this.mAlpha = 0;
            ValueAnimator duration = ValueAnimator.ofInt(0, 222).setDuration(400L);
            this.mAlphaInAnimator = duration;
            duration.addUpdateListener(this);
            Interpolator interpolator = Interpolators.CUSTOM_40_40;
            duration.setInterpolator(interpolator);
            ValueAnimator duration2 = ValueAnimator.ofInt(222, 0).setDuration(300L);
            this.mAlphaOutAnimator = duration2;
            duration2.addUpdateListener(this);
            duration2.setInterpolator(interpolator);
            AnimatorSet animatorSet = new AnimatorSet();
            this.mAnimator = animatorSet;
            animatorSet.play(duration).before(duration2);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.assist.AssistDisclosure.AssistDisclosureView.1
                public boolean mCancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.mCancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    if (this.mCancelled) {
                        return;
                    }
                    AssistDisclosure assistDisclosure = AssistDisclosure.this;
                    if (assistDisclosure.mViewAdded) {
                        assistDisclosure.mWm.removeView(assistDisclosure.mView);
                        assistDisclosure.mViewAdded = false;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    this.mCancelled = false;
                }
            });
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
            paint.setColor(-1);
            paint.setXfermode(porterDuffXfermode);
            paint2.setColor(-12303292);
            paint2.setXfermode(porterDuffXfermode);
            this.mThickness = getResources().getDimension(R.dimen.assist_disclosure_thickness);
            this.mShadowThickness = getResources().getDimension(R.dimen.assist_disclosure_shadow_thickness);
        }

        public final void drawGeometry(Canvas canvas, Paint paint, float f) {
            int width = getWidth();
            int height = getHeight();
            float f2 = this.mThickness;
            float f3 = height;
            float f4 = f3 - f2;
            float f5 = width;
            float f6 = 0.0f - f;
            float f7 = f5 + f;
            canvas.drawRect(f6, f4 - f, f7, f3 + f, paint);
            float f8 = f2 + f;
            float f9 = f4 + f;
            canvas.drawRect(f6, f6, f8, f9, paint);
            float f10 = f5 - f2;
            canvas.drawRect(f10 - f, f6, f7, f9, paint);
            canvas.drawRect(f2 - f, f6, f10 + f, f8, paint);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            ValueAnimator valueAnimator2 = this.mAlphaOutAnimator;
            if (valueAnimator == valueAnimator2) {
                this.mAlpha = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
            } else {
                ValueAnimator valueAnimator3 = this.mAlphaInAnimator;
                if (valueAnimator == valueAnimator3) {
                    this.mAlpha = ((Integer) valueAnimator3.getAnimatedValue()).intValue();
                }
            }
            invalidate();
        }

        @Override // android.view.View
        public final void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.mAnimator.cancel();
            this.mAnimator.start();
            sendAccessibilityEvent(16777216);
        }

        @Override // android.view.View
        public final void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mAnimator.cancel();
            this.mAlpha = 0;
        }

        @Override // android.view.View
        public final void onDraw(Canvas canvas) {
            this.mPaint.setAlpha(this.mAlpha);
            this.mShadowPaint.setAlpha(this.mAlpha / 4);
            drawGeometry(canvas, this.mShadowPaint, this.mShadowThickness);
            drawGeometry(canvas, this.mPaint, 0.0f);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.assist.AssistDisclosure$1] */
    public AssistDisclosure(Context context, Handler handler, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mContext = context;
        this.mHandler = handler;
        this.mWm = viewCaptureAwareWindowManager;
    }
}
