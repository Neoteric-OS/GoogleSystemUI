package com.google.android.systemui.screenshot.surfaceeffects.gloweffect;

import android.animation.ValueAnimator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlowPieEffect {
    public static final BaseGlow baseGlow;
    public static final FirstGlowPie firstGlowPie;
    public static final SecondGlowPie secondGlowPie;
    public final GlowPieShader glowPieShader;
    public final ValueAnimator mainAnimator;
    public final ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 renderEffectDrawCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BaseGlow implements GlowPie {
        public float progress;
        public float time;

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float alpha() {
            float f = (this.time - 2750.0f) / 700.0f;
            if (f < 0.0f) {
                f = 0.0f;
            } else if (f > 1.0f) {
                f = 1.0f;
            }
            return (0.6f * f) + 0.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float angle() {
            throw null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BaseGlow)) {
                return false;
            }
            ((BaseGlow) obj).getClass();
            return Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(2750.0f, 2750.0f) == 0 && Float.compare(3450.0f, 3450.0f) == 0 && Float.compare(0.6f, 0.6f) == 0;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeEndMs() {
            return 3450.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeStartMs() {
            return 2750.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndAngle() {
            return 0.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndMs() {
            return 0.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getMaxOpacity() {
            return 0.6f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getProgress() {
            return this.progress;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartAngle() {
            return 0.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartMs() {
            return 0.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getTime() {
            return this.time;
        }

        public final int hashCode() {
            return Float.hashCode(0.6f) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(0.0f) * 31, 0.0f, 31), 0.0f, 31), 0.0f, 31), 2750.0f, 31), 3450.0f, 31);
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setProgress(float f) {
            this.progress = f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setTime(float f) {
            this.time = f;
        }

        public final String toString() {
            return "BaseGlow(startMs=0.0, endMs=0.0, startAngle=0.0, endAngle=0.0, alphaFadeStartMs=2750.0, alphaFadeEndMs=3450.0, maxOpacity=0.6)";
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void updateProgress(float f) {
            throw null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FirstGlowPie implements GlowPie {
        public float progress;
        public float time;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FirstGlowPie)) {
                return false;
            }
            FirstGlowPie firstGlowPie = (FirstGlowPie) obj;
            firstGlowPie.getClass();
            return Float.compare(250.0f, 250.0f) == 0 && Float.compare(3090.0f, 3090.0f) == 0 && Float.compare(-1.5707964f, -1.5707964f) == 0 && Float.compare(12.566371f, 12.566371f) == 0 && Float.compare(2840.0f, 2840.0f) == 0 && Float.compare(3090.0f, 3090.0f) == 0 && Float.compare(this.progress, firstGlowPie.progress) == 0 && Float.compare(this.time, firstGlowPie.time) == 0 && Float.compare(0.5f, 0.5f) == 0;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeEndMs() {
            return 3090.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeStartMs() {
            return 2840.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndAngle() {
            return 12.566371f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndMs() {
            return 3090.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getMaxOpacity() {
            return 0.5f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getProgress() {
            return this.progress;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartAngle() {
            return -1.5707964f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartMs() {
            return 250.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getTime() {
            return this.time;
        }

        public final int hashCode() {
            return Float.hashCode(0.5f) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(250.0f) * 31, 3090.0f, 31), -1.5707964f, 31), 12.566371f, 31), 2840.0f, 31), 3090.0f, 31), this.progress, 31), this.time, 31);
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setProgress(float f) {
            this.progress = f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setTime(float f) {
            this.time = f;
        }

        public final String toString() {
            return "FirstGlowPie(startMs=250.0, endMs=3090.0, startAngle=-1.5707964, endAngle=12.566371, alphaFadeStartMs=2840.0, alphaFadeEndMs=3090.0, progress=" + this.progress + ", time=" + this.time + ", maxOpacity=0.5)";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface GlowPie {
        default float alpha() {
            float maxOpacity = getMaxOpacity();
            float alphaFadeStartMs = getAlphaFadeStartMs();
            float alphaFadeEndMs = getAlphaFadeEndMs();
            float time = alphaFadeStartMs == alphaFadeEndMs ? 0.0f : (getTime() - alphaFadeStartMs) / (alphaFadeEndMs - alphaFadeStartMs);
            if (time < 0.0f) {
                time = 0.0f;
            } else if (time > 1.0f) {
                time = 1.0f;
            }
            return AndroidFlingSpline$$ExternalSyntheticOutline0.m(0.0f, maxOpacity, time, maxOpacity);
        }

        default float angle() {
            float startAngle = getStartAngle();
            float endAngle = getEndAngle();
            float progress = (getProgress() - 0.0f) / 1.0f;
            return -((getProgress() * 3.1415927f) + AndroidFlingSpline$$ExternalSyntheticOutline0.m(endAngle, startAngle, progress >= 0.0f ? progress > 1.0f ? 1.0f : progress : 0.0f, startAngle));
        }

        float getAlphaFadeEndMs();

        float getAlphaFadeStartMs();

        float getEndAngle();

        float getEndMs();

        float getMaxOpacity();

        float getProgress();

        float getStartAngle();

        float getStartMs();

        float getTime();

        void setProgress(float f);

        void setTime(float f);

        default void updateProgress(float f) {
            float startMs = getStartMs();
            float endMs = getEndMs();
            float f2 = startMs == endMs ? 0.0f : (f - startMs) / (endMs - startMs);
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            setProgress((1.0f * f2) + 0.0f);
            setTime(f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SecondGlowPie implements GlowPie {
        public float progress;
        public float time;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SecondGlowPie)) {
                return false;
            }
            SecondGlowPie secondGlowPie = (SecondGlowPie) obj;
            secondGlowPie.getClass();
            return Float.compare(410.0f, 410.0f) == 0 && Float.compare(3250.0f, 3250.0f) == 0 && Float.compare(-1.5707964f, -1.5707964f) == 0 && Float.compare(9.424778f, 9.424778f) == 0 && Float.compare(3000.0f, 3000.0f) == 0 && Float.compare(3250.0f, 3250.0f) == 0 && Float.compare(this.progress, secondGlowPie.progress) == 0 && Float.compare(this.time, secondGlowPie.time) == 0 && Float.compare(0.5f, 0.5f) == 0;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeEndMs() {
            return 3250.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getAlphaFadeStartMs() {
            return 3000.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndAngle() {
            return 9.424778f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getEndMs() {
            return 3250.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getMaxOpacity() {
            return 0.5f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getProgress() {
            return this.progress;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartAngle() {
            return -1.5707964f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getStartMs() {
            return 410.0f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final float getTime() {
            return this.time;
        }

        public final int hashCode() {
            return Float.hashCode(0.5f) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(410.0f) * 31, 3250.0f, 31), -1.5707964f, 31), 9.424778f, 31), 3000.0f, 31), 3250.0f, 31), this.progress, 31), this.time, 31);
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setProgress(float f) {
            this.progress = f;
        }

        @Override // com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect.GlowPie
        public final void setTime(float f) {
            this.time = f;
        }

        public final String toString() {
            return "SecondGlowPie(startMs=410.0, endMs=3250.0, startAngle=-1.5707964, endAngle=9.424778, alphaFadeStartMs=3000.0, alphaFadeEndMs=3250.0, progress=" + this.progress + ", time=" + this.time + ", maxOpacity=0.5)";
        }
    }

    static {
        BaseGlow baseGlow2 = new BaseGlow();
        baseGlow2.progress = 1.0f;
        baseGlow = baseGlow2;
        FirstGlowPie firstGlowPie2 = new FirstGlowPie();
        firstGlowPie2.progress = 0.0f;
        firstGlowPie2.time = 0.0f;
        firstGlowPie = firstGlowPie2;
        SecondGlowPie secondGlowPie2 = new SecondGlowPie();
        secondGlowPie2.progress = 0.0f;
        secondGlowPie2.time = 0.0f;
        secondGlowPie = secondGlowPie2;
    }

    public GlowPieEffect(GlowPieEffectConfig glowPieEffectConfig, ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1) {
        this.renderEffectDrawCallback = thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1;
        GlowPieShader glowPieShader = new GlowPieShader("\n        float triangleNoise(vec2 n) {\n            n  = fract(n * vec2(5.3987, 5.4421));\n            n += dot(n.yx, n.xy + vec2(21.5351, 14.3137));\n            float xy = n.x * n.y;\n            // compute in [0..2[ and remap to [-1.0..1.0[\n            return fract(xy * 95.4307) + fract(xy * 75.04961) - 1.0;\n        }\n\n        const float PI = 3.1415926535897932384626;\n\n        float sparkles(vec2 uv, float t) {\n            float n = triangleNoise(uv);\n            float s = 0.0;\n            for (float i = 0; i < 4; i += 1) {\n                float l = i * 0.01;\n                float h = l + 0.1;\n                float o = smoothstep(n - l, h, n);\n                o *= abs(sin(PI * o * (t + 0.55 * i)));\n                s += o;\n            }\n            return s;\n        }\n\n        vec2 distort(vec2 p, float time, float distort_amount_radial,\n            float distort_amount_xy) {\n                float angle = atan(p.y, p.x);\n                  return p + vec2(sin(angle * 8 + time * 0.003 + 1.641),\n                            cos(angle * 5 + 2.14 + time * 0.00412)) * distort_amount_radial\n                     + vec2(sin(p.x * 0.01 + time * 0.00215 + 0.8123),\n                            cos(p.y * 0.01 + time * 0.005931)) * distort_amount_xy;\n        }\n\n        // Perceived luminosity (L′), not absolute luminosity.\n        half getLuminosity(vec3 c) {\n            return 0.3 * c.r + 0.59 * c.g + 0.11 * c.b;\n        }\n\n        // Creates a luminosity mask and clamp to the legal range.\n        vec3 maskLuminosity(vec3 dest, float lum) {\n            dest.rgb *= vec3(lum);\n            // Clip back into the legal range\n            dest = clamp(dest, vec3(0.), vec3(1.0));\n            return dest;\n        }\n\n        // Integer mod. GLSL es 1.0 doesn't have integer mod :(\n        int imod(int a, int b) {\n            return a - (b * (a / b));\n        }\n\n        ivec3 imod(ivec3 a, int b) {\n            return ivec3(imod(a.x, b), imod(a.y, b), imod(a.z, b));\n        }\n\n        // Integer based hash function with the return range of [-1, 1].\n        vec3 hash(vec3 p) {\n            ivec3 v = ivec3(p);\n            v = v * 1671731 + 10139267;\n\n            v.x += v.y * v.z;\n            v.y += v.z * v.x;\n            v.z += v.x * v.y;\n\n            ivec3 v2 = v / 65536; // v >> 16\n            v = imod((10 - imod((v + v2), 10)), 10); // v ^ v2\n\n            v.x += v.y * v.z;\n            v.y += v.z * v.x;\n            v.z += v.x * v.y;\n\n            // Use sin and cos to map the range to [-1, 1].\n            return vec3(sin(float(v.x)), cos(float(v.y)), sin(float(v.z)));\n        }\n\n        // Skew factors (non-uniform).\n        const half SKEW = 0.3333333;  // 1/3\n        const half UNSKEW = 0.1666667;  // 1/6\n\n        // Return range roughly [-1,1].\n        // It's because the hash function (that returns a random gradient vector) returns\n        // different magnitude of vectors. Noise doesn't have to be in the precise range thus\n        // skipped normalize.\n        half simplex3d(vec3 p) {\n            // Skew the input coordinate, so that we get squashed cubical grid\n            vec3 s = floor(p + (p.x + p.y + p.z) * SKEW);\n\n            // Unskew back\n            vec3 u = s - (s.x + s.y + s.z) * UNSKEW;\n\n            // Unskewed coordinate that is relative to p, to compute the noise contribution\n            // based on the distance.\n            vec3 c0 = p - u;\n\n            // We have six simplices (in this case tetrahedron, since we are in 3D) that we\n            // could possibly in.\n            // Here, we are finding the correct tetrahedron (simplex shape), and traverse its\n            // four vertices (c0..3) when computing noise contribution.\n            // The way we find them is by comparing c0's x,y,z values.\n            // For example in 2D, we can find the triangle (simplex shape in 2D) that we are in\n            // by comparing x and y values. i.e. x>y lower, x<y, upper triangle.\n            // Same applies in 3D.\n            //\n            // Below indicates the offsets (or offset directions) when c0=(x0,y0,z0)\n            // x0>y0>z0: (1,0,0), (1,1,0), (1,1,1)\n            // x0>z0>y0: (1,0,0), (1,0,1), (1,1,1)\n            // z0>x0>y0: (0,0,1), (1,0,1), (1,1,1)\n            // z0>y0>x0: (0,0,1), (0,1,1), (1,1,1)\n            // y0>z0>x0: (0,1,0), (0,1,1), (1,1,1)\n            // y0>x0>z0: (0,1,0), (1,1,0), (1,1,1)\n            //\n            // The rule is:\n            // * For offset1, set 1 at the max component, otherwise 0.\n            // * For offset2, set 0 at the min component, otherwise 1.\n            // * For offset3, set 1 for all.\n            //\n            // Encode x0-y0, y0-z0, z0-x0 in a vec3\n            vec3 en = c0 - c0.yzx;\n            // Each represents whether x0>y0, y0>z0, z0>x0\n            en = step(vec3(0.), en);\n            // en.zxy encodes z0>x0, x0>y0, y0>x0\n            vec3 offset1 = en * (1. - en.zxy); // find max\n            vec3 offset2 = 1. - en.zxy * (1. - en); // 1-(find min)\n            vec3 offset3 = vec3(1.);\n\n            vec3 c1 = c0 - offset1 + UNSKEW;\n            vec3 c2 = c0 - offset2 + UNSKEW * 2.;\n            vec3 c3 = c0 - offset3 + UNSKEW * 3.;\n\n            // Kernel summation: dot(max(0, r^2-d^2))^4, noise contribution)\n            //\n            // First compute d^2, squared distance to the point.\n            vec4 w; // w = max(0, r^2 - d^2))\n            w.x = dot(c0, c0);\n            w.y = dot(c1, c1);\n            w.z = dot(c2, c2);\n            w.w = dot(c3, c3);\n\n            // Noise contribution should decay to zero before they cross the simplex boundary.\n            // Usually r^2 is 0.5 or 0.6;\n            // 0.5 ensures continuity but 0.6 increases the visual quality for the application\n            // where discontinuity isn't noticeable.\n            w = max(0.6 - w, 0.);\n\n            // Noise contribution from each point.\n            vec4 nc;\n            nc.x = dot(hash(s), c0);\n            nc.y = dot(hash(s + offset1), c1);\n            nc.z = dot(hash(s + offset2), c2);\n            nc.w = dot(hash(s + offset3), c3);\n\n            nc *= w*w*w*w;\n\n            // Add all the noise contributions.\n            // Should multiply by the possible max contribution to adjust the range in [-1,1].\n            return dot(vec4(32.), nc);\n        }\n\n        // Random rotations.\n        // The way you create fractal noise is layering simplex noise with some rotation.\n        // To make random cloud looking noise, the rotations should not align. (Otherwise it\n        // creates patterned noise).\n        // Below rotations only rotate in one axis.\n        const mat3 rot1 = mat3(1.0, 0. ,0., 0., 0.15, -0.98, 0., 0.98, 0.15);\n        const mat3 rot2 = mat3(-0.95, 0. ,-0.3, 0., 1., 0., 0.3, 0., -0.95);\n        const mat3 rot3 = mat3(1.0, 0. ,0., 0., -0.44, -0.89, 0., 0.89, -0.44);\n\n        // Octave = 4\n        // Divide each coefficient by 3 to produce more grainy noise.\n        half simplex3d_fractal(vec3 p) {\n            return 0.675 * simplex3d(p * rot1) + 0.225 * simplex3d(2.0 * p * rot2)\n                    + 0.075 * simplex3d(4.0 * p * rot3) + 0.025 * simplex3d(8.0 * p);\n        }\n\n        // Screen blend\n        vec3 screen(vec3 dest, vec3 src) {\n            return dest + src - dest * src;\n        }\n    \n            float soften(float d, float blur) {\n                float blurHalf = blur * 0.5;\n                return smoothstep(-blurHalf, blurHalf, d);\n            }\n\n            float subtract(float outer, float inner) {\n                return max(outer, -inner);\n            }\n        \n            float sdRoundedBox(vec2 p, vec2 size, float cornerRadius) {\n                size *= 0.5;\n                cornerRadius *= 0.5;\n                vec2 d = abs(p) - size + cornerRadius;\n\n                float outside = length(max(d, 0.0));\n                float inside = min(max(d.x, d.y), 0.0);\n\n                return (outside + inside - cornerRadius) / size.y;\n            }\n\n            float roundedBoxRing(vec2 p, vec2 size, float cornerRadius,\n                float borderThickness) {\n                float outerRoundBox = sdRoundedBox(p, size + vec2(borderThickness),\n                    cornerRadius + borderThickness);\n                float innerRoundBox = sdRoundedBox(p, size, cornerRadius);\n                return subtract(outerRoundBox, innerRoundBox);\n            }\n        \n            uniform shader in_dst;\n            uniform vec2 in_center;\n            uniform vec2 in_size;\n            uniform half in_cornerRad;\n            uniform float[3] in_angles;\n            uniform float[3] in_alphas;\n            uniform float[3] in_bottomThresholds;\n            uniform float[3] in_topThresholds;\n            layout(color) uniform vec4 in_colors0;\n            layout(color) uniform vec4 in_colors1;\n            layout(color) uniform vec4 in_colors2;\n        \n            float remap(float in_start, float in_end, float out_start, float out_end, float x) {\n                x = (x - in_start) / (in_end - in_start);\n                x = clamp(x, 0., 1.);\n                return x * (out_end - out_start) + out_start;\n            }\n        \n            void pieGlow(\n                half box,\n                vec2 c,\n                half angle,\n                vec3 color,\n                half pieAlpha,\n                half glowAlpha,\n                vec2 angleThresholds,\n                inout vec4 inout_pie) {\n\n                // Apply angular rotation.\n                half co = cos(angle), si = sin(angle);\n                mat2 rotM = mat2(co, -si, si, co); // 2D rotation matrix\n                c *= rotM;\n\n                // We rotate based on the cosine value, since we want to avoid using inverse\n                // trig function, which in this case is atan.\n\n                // Dot product with vec2(1., 0.) and bring the range to [0,1].\n                // Same as dot(normalize(c), vec2(1.,0) * 0.5 + 0.5\n                half d = normalize(c).x * 0.5 + 0.5;\n\n                // Those thresholds represents each end of the pie.\n                float bottomThreshold = angleThresholds[0];\n                float topThreshold = angleThresholds[1];\n                float angleMask = remap(bottomThreshold, topThreshold, 0., 1., d);\n\n                half boxMask = step(box, 0.);\n                vec4 pie = vec4(color, 1.0) * boxMask * pieAlpha;\n\n                // We are drawing the same pie but with more blur.\n                half glowMask = 1. - smoothstep(0., 0.4, box);\n                // Apply some curve for the glow. (Can take out)\n                glowMask *= glowMask * glowMask;\n                vec4 glow = vec4(color, 1.0) * glowMask * glowAlpha;\n\n                // Blend glow and pie.\n                vec4 comp = glow + pie * (1. - glow.a);\n                // Apply angle mask at the end together.\n                comp *= angleMask;\n\n                inout_pie = comp + inout_pie * (1. - comp.a);\n            }\n            \n        vec4 main(vec2 p) {\n            vec4 pie = vec4(0.);\n\n            vec2 c = p - in_center;\n            half box = sdRoundedBox(c, in_size, in_cornerRad);\n\n            // Base glow (drawn at the bottom)\n            pieGlow(\n                box,\n                c,\n                in_angles[0],\n                in_colors0.rgb,\n                /* pieAlpha= */ 1., // We always show the base color.\n                /* glowAlpha= */ in_alphas[0],\n                vec2(in_bottomThresholds[0], in_topThresholds[0]),\n                pie\n            );\n\n            // First pie\n            pieGlow(\n                box,\n                c,\n                in_angles[1],\n                in_colors1.rgb,\n                /* pieAlpha= */ in_alphas[1],\n                /* glowAlpha= */ in_alphas[1],\n                vec2(in_bottomThresholds[1], in_topThresholds[1]),\n                pie\n            );\n\n            // Second pie (drawn on top)\n            pieGlow(\n                box,\n                c,\n                in_angles[2],\n                in_colors2.rgb,\n                /* pieAlpha= */ in_alphas[2],\n                /* glowAlpha= */ in_alphas[2],\n                vec2(in_bottomThresholds[2], in_topThresholds[2]),\n                pie\n            );\n\n            return pie;\n        }\n        ");
        glowPieShader.applyConfig(glowPieEffectConfig);
        this.glowPieShader = glowPieShader;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(3500L);
        this.mainAnimator = ofFloat;
    }

    public static /* synthetic */ void getMainAnimator$annotations() {
    }
}
