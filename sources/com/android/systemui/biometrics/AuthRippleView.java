package com.android.systemui.biometrics;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthRippleView extends View {
    public boolean drawDwell;
    public boolean drawRipple;
    public final long dwellExpandDuration;
    public Point dwellOrigin;
    public final Paint dwellPaint;
    public final long dwellPulseDuration;
    public Animator dwellPulseOutAnimator;
    public float dwellRadius;
    public final DwellRippleShader dwellShader;
    public final long fadeDuration;
    public Animator fadeDwellAnimator;
    public int lockScreenColorVal;
    public Point origin;
    public final long retractDuration;
    public Animator retractDwellAnimator;
    public final PathInterpolator retractInterpolator;
    public final Paint ripplePaint;
    public final RippleShader rippleShader;
    public Animator unlockedRippleAnimator;

    public AuthRippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.retractInterpolator = new PathInterpolator(0.05f, 0.93f, 0.1f, 1.0f);
        this.dwellPulseDuration = 100L;
        this.dwellExpandDuration = 1900L;
        this.lockScreenColorVal = -1;
        this.fadeDuration = 83L;
        this.retractDuration = 400L;
        DwellRippleShader dwellRippleShader = new DwellRippleShader("uniform vec2 in_origin;\n                uniform float in_time;\n                uniform float in_radius;\n                uniform float in_blur;\n                layout(color) uniform vec4 in_color;\n                uniform float in_phase1;\n                uniform float in_phase2;\n                uniform float in_distortion_strength;\n                float softCircle(vec2 uv, vec2 xy, float radius, float blur) {\n                  float blurHalf = blur * 0.5;\n                  float d = distance(uv, xy);\n                  return 1. - smoothstep(1. - blurHalf, 1. + blurHalf, d / radius);\n                }\n\n                float softRing(vec2 uv, vec2 xy, float radius, float blur) {\n                  float thickness_half = radius * 0.25;\n                  float circle_outer = softCircle(uv, xy, radius + thickness_half, blur);\n                  float circle_inner = softCircle(uv, xy, radius - thickness_half, blur);\n                  return circle_outer - circle_inner;\n                }\n\n                vec2 distort(vec2 p, float time, float distort_amount_xy, float frequency) {\n                    return p + vec2(sin(p.y * frequency + in_phase1),\n                                    cos(p.x * frequency * -1.23 + in_phase2)) * distort_amount_xy;\n                }\n\n                vec4 ripple(vec2 p, float distort_xy, float frequency) {\n                    vec2 p_distorted = distort(p, in_time, distort_xy, frequency);\n                    float circle = softCircle(p_distorted, in_origin, in_radius * 1.2, in_blur);\n                    float rippleAlpha = max(circle,\n                        softRing(p_distorted, in_origin, in_radius, in_blur)) * 0.25;\n                    return in_color * rippleAlpha;\n                }\n                vec4 main(vec2 p) {\n                    vec4 color1 = ripple(p,\n                        34 * in_distortion_strength, // distort_xy\n                        0.012 // frequency\n                    );\n                    vec4 color2 = ripple(p,\n                        49 * in_distortion_strength, // distort_xy\n                        0.018 // frequency\n                    );\n                    // Alpha blend between two layers.\n                    return vec4(color1.xyz + color2.xyz\n                        * (1 - color1.w), color1.w + color2.w * (1-color1.w));\n                }");
        new Point();
        dwellRippleShader.color = 16777215;
        this.dwellShader = dwellRippleShader;
        Paint paint = new Paint();
        this.dwellPaint = paint;
        RippleShader rippleShader = new RippleShader(RippleShader.RippleShape.CIRCLE);
        this.rippleShader = rippleShader;
        Paint paint2 = new Paint();
        this.ripplePaint = paint2;
        this.dwellOrigin = new Point();
        this.origin = new Point();
        rippleShader.setRawProgress(0.0f);
        rippleShader.setPixelDensity(getResources().getDisplayMetrics().density);
        rippleShader.setFloatUniform("in_sparkle_strength", 0.3f);
        RippleShader.FadeParams fadeParams = rippleShader.baseRingFadeParams;
        fadeParams.getClass();
        fadeParams.fadeInEnd = 0.2f;
        fadeParams.fadeOutStart = 0.2f;
        fadeParams.fadeOutEnd = 1.0f;
        RippleShader.FadeParams fadeParams2 = rippleShader.centerFillFadeParams;
        fadeParams2.getClass();
        fadeParams2.fadeInEnd = 0.15f;
        fadeParams2.fadeOutStart = 0.15f;
        fadeParams2.fadeOutEnd = 0.56f;
        paint2.setShader(rippleShader);
        this.lockScreenColorVal = -1;
        rippleShader.setColorUniform("in_color", ColorUtils.setAlphaComponent(-1, 62));
        dwellRippleShader.setColor(-1);
        dwellRippleShader.setProgress(0.0f);
        dwellRippleShader.setFloatUniform("in_distortion_strength", 0.4f);
        paint.setShader(dwellRippleShader);
        setVisibility(8);
    }

    public final void fadeDwellRipple() {
        Animator animator;
        Animator animator2 = this.fadeDwellAnimator;
        if (animator2 == null || !animator2.isRunning()) {
            Animator animator3 = this.dwellPulseOutAnimator;
            if ((animator3 == null || !animator3.isRunning()) && ((animator = this.retractDwellAnimator) == null || !animator.isRunning())) {
                return;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(Color.alpha(this.dwellShader.color), 0);
            ofInt.setInterpolator(Interpolators.LINEAR);
            ofInt.setDuration(this.fadeDuration);
            ofInt.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(this, 0));
            ofInt.addListener(new AuthRippleView$fadeDwellRipple$1$2(this, 0));
            ofInt.start();
            this.fadeDwellAnimator = ofInt;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.drawDwell) {
            float f = 1;
            float f2 = f - this.dwellShader.progress;
            float f3 = (f - ((f2 * f2) * f2)) * this.dwellRadius * 2.0f;
            Point point = this.dwellOrigin;
            canvas.drawCircle(point.x, point.y, f3, this.dwellPaint);
        }
        if (this.drawRipple) {
            Point point2 = this.origin;
            canvas.drawCircle(point2.x, point2.y, this.rippleShader.rippleSize.currentWidth, this.ripplePaint);
        }
    }

    public final void retractDwellRipple() {
        Animator animator;
        int i = 2;
        Animator animator2 = this.retractDwellAnimator;
        int i2 = 1;
        if (animator2 == null || !animator2.isRunning()) {
            Animator animator3 = this.fadeDwellAnimator;
            if ((animator3 == null || !animator3.isRunning()) && (animator = this.dwellPulseOutAnimator) != null && animator.isRunning()) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.dwellShader.progress, 0.0f);
                ofFloat.setInterpolator(this.retractInterpolator);
                ofFloat.setDuration(this.retractDuration);
                ofFloat.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(this, i));
                ValueAnimator ofInt = ValueAnimator.ofInt(255, 0);
                ofInt.setInterpolator(Interpolators.LINEAR);
                ofInt.setDuration(this.retractDuration);
                ofInt.addUpdateListener(new AuthRippleView$fadeDwellRipple$1$1(this, i2));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(ofFloat, ofInt);
                animatorSet.addListener(new AuthRippleView$fadeDwellRipple$1$2(this, 1));
                animatorSet.start();
                this.retractDwellAnimator = animatorSet;
            }
        }
    }

    public final void setFingerprintSensorLocation(Point point, float f) {
        this.rippleShader.setFloatUniform("in_center", point.x, point.y);
        this.origin = point;
        RippleShader.RippleSize rippleSize = this.rippleShader.rippleSize;
        float maxOf = ComparisonsKt___ComparisonsJvmKt.maxOf(new int[]{point.y, getWidth() - point.x, getHeight() - point.y}, point.x) * 0.9f * 2.0f;
        rippleSize.getClass();
        rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, maxOf, maxOf));
        DwellRippleShader dwellRippleShader = this.dwellShader;
        dwellRippleShader.getClass();
        dwellRippleShader.setFloatUniform("in_origin", point.x, point.y);
        this.dwellOrigin = point;
        float f2 = f * 1.5f;
        this.dwellShader.maxRadius = f2;
        this.dwellRadius = f2;
    }

    public final void setSensorLocation(Point point) {
        this.rippleShader.setFloatUniform("in_center", point.x, point.y);
        this.origin = point;
        float maxOf = ComparisonsKt___ComparisonsJvmKt.maxOf(new int[]{point.y, getWidth() - point.x, getHeight() - point.y}, point.x) * 0.9f;
        RippleShader.RippleSize rippleSize = this.rippleShader.rippleSize;
        float f = maxOf * 2.0f;
        rippleSize.getClass();
        rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, f, f));
    }
}
