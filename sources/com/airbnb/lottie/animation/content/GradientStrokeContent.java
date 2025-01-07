package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GradientStrokeContent extends BaseStrokeContent {
    public final RectF boundsRect;
    public final int cacheSteps;
    public final GradientColorKeyframeAnimation colorAnimation;
    public ValueCallbackKeyframeAnimation colorCallbackAnimation;
    public final PointKeyframeAnimation endPointAnimation;
    public final boolean hidden;
    public final LongSparseArray linearGradientCache;
    public final String name;
    public final LongSparseArray radialGradientCache;
    public final PointKeyframeAnimation startPointAnimation;
    public final GradientType type;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GradientStrokeContent(com.airbnb.lottie.LottieDrawable r15, com.airbnb.lottie.model.layer.BaseLayer r16, com.airbnb.lottie.model.content.GradientStroke r17) {
        /*
            r14 = this;
            r10 = r14
            r11 = r16
            r12 = r17
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType r0 = r12.capType
            int r0 = r0.ordinal()
            r1 = 1
            if (r0 == 0) goto L17
            if (r0 == r1) goto L14
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.SQUARE
        L12:
            r3 = r0
            goto L1a
        L14:
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.ROUND
            goto L12
        L17:
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.BUTT
            goto L12
        L1a:
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType r0 = r12.joinType
            int r0 = r0.ordinal()
            r13 = 0
            if (r0 == 0) goto L31
            if (r0 == r1) goto L2e
            r1 = 2
            if (r0 == r1) goto L2a
            r4 = r13
            goto L34
        L2a:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.BEVEL
        L2c:
            r4 = r0
            goto L34
        L2e:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.ROUND
            goto L2c
        L31:
            android.graphics.Paint$Join r0 = android.graphics.Paint.Join.MITER
            goto L2c
        L34:
            java.util.List r8 = r12.lineDashPattern
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r6 = r12.opacity
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r7 = r12.width
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r9 = r12.dashOffset
            float r5 = r12.miterLimit
            r0 = r14
            r1 = r15
            r2 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            androidx.collection.LongSparseArray r0 = new androidx.collection.LongSparseArray
            r0.<init>(r13)
            r10.linearGradientCache = r0
            androidx.collection.LongSparseArray r0 = new androidx.collection.LongSparseArray
            r0.<init>(r13)
            r10.radialGradientCache = r0
            android.graphics.RectF r0 = new android.graphics.RectF
            r0.<init>()
            r10.boundsRect = r0
            java.lang.String r0 = r12.name
            r10.name = r0
            com.airbnb.lottie.model.content.GradientType r0 = r12.gradientType
            r10.type = r0
            boolean r0 = r12.hidden
            r10.hidden = r0
            r0 = r15
            com.airbnb.lottie.LottieComposition r0 = r0.composition
            float r0 = r0.getDuration()
            r1 = 1107296256(0x42000000, float:32.0)
            float r0 = r0 / r1
            int r0 = (int) r0
            r10.cacheSteps = r0
            com.airbnb.lottie.model.animatable.AnimatableGradientColorValue r0 = r12.gradientColor
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r0.createAnimation()
            r1 = r0
            com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation r1 = (com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation) r1
            r10.colorAnimation = r1
            r0.addUpdateListener(r14)
            r11.addAnimation(r0)
            com.airbnb.lottie.model.animatable.AnimatablePointValue r0 = r12.startPoint
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r0.createAnimation()
            r1 = r0
            com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation r1 = (com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation) r1
            r10.startPointAnimation = r1
            r0.addUpdateListener(r14)
            r11.addAnimation(r0)
            com.airbnb.lottie.model.animatable.AnimatablePointValue r0 = r12.endPoint
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r0.createAnimation()
            r1 = r0
            com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation r1 = (com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation) r1
            r10.endPointAnimation = r1
            r0.addUpdateListener(r14)
            r11.addAnimation(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.GradientStrokeContent.<init>(com.airbnb.lottie.LottieDrawable, com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.content.GradientStroke):void");
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        super.addValueCallback(lottieValueCallback, obj);
        if (obj == LottieProperty.GRADIENT_COLOR) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
            BaseLayer baseLayer = this.layer;
            if (valueCallbackKeyframeAnimation != null) {
                baseLayer.removeAnimation(valueCallbackKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            baseLayer.addAnimation(this.colorCallbackAnimation);
        }
    }

    public final int[] applyDynamicColorsIfNeeded(int[] iArr) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] numArr = (Integer[]) valueCallbackKeyframeAnimation.getValue();
            int i = 0;
            if (iArr.length == numArr.length) {
                while (i < iArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i < numArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            }
        }
        return iArr;
    }

    @Override // com.airbnb.lottie.animation.content.BaseStrokeContent, com.airbnb.lottie.animation.content.DrawingContent
    public final void draw(Canvas canvas, Matrix matrix, int i) {
        Shader shader;
        if (this.hidden) {
            return;
        }
        getBounds(this.boundsRect, matrix, false);
        GradientType gradientType = GradientType.LINEAR;
        GradientType gradientType2 = this.type;
        GradientColorKeyframeAnimation gradientColorKeyframeAnimation = this.colorAnimation;
        PointKeyframeAnimation pointKeyframeAnimation = this.endPointAnimation;
        PointKeyframeAnimation pointKeyframeAnimation2 = this.startPointAnimation;
        if (gradientType2 == gradientType) {
            long gradientHash = getGradientHash();
            LongSparseArray longSparseArray = this.linearGradientCache;
            shader = (LinearGradient) longSparseArray.get(gradientHash);
            if (shader == null) {
                PointF pointF = (PointF) pointKeyframeAnimation2.getValue();
                PointF pointF2 = (PointF) pointKeyframeAnimation.getValue();
                GradientColor gradientColor = (GradientColor) gradientColorKeyframeAnimation.getValue();
                shader = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, applyDynamicColorsIfNeeded(gradientColor.colors), gradientColor.positions, Shader.TileMode.CLAMP);
                longSparseArray.put(gradientHash, shader);
            }
        } else {
            long gradientHash2 = getGradientHash();
            LongSparseArray longSparseArray2 = this.radialGradientCache;
            shader = (RadialGradient) longSparseArray2.get(gradientHash2);
            if (shader == null) {
                PointF pointF3 = (PointF) pointKeyframeAnimation2.getValue();
                PointF pointF4 = (PointF) pointKeyframeAnimation.getValue();
                GradientColor gradientColor2 = (GradientColor) gradientColorKeyframeAnimation.getValue();
                int[] applyDynamicColorsIfNeeded = applyDynamicColorsIfNeeded(gradientColor2.colors);
                RadialGradient radialGradient = new RadialGradient(pointF3.x, pointF3.y, (float) Math.hypot(pointF4.x - r10, pointF4.y - r11), applyDynamicColorsIfNeeded, gradientColor2.positions, Shader.TileMode.CLAMP);
                longSparseArray2.put(gradientHash2, radialGradient);
                shader = radialGradient;
            }
        }
        shader.setLocalMatrix(matrix);
        this.paint.setShader(shader);
        super.draw(canvas, matrix, i);
    }

    public final int getGradientHash() {
        float f = this.startPointAnimation.progress;
        float f2 = this.cacheSteps;
        int round = Math.round(f * f2);
        int round2 = Math.round(this.endPointAnimation.progress * f2);
        int round3 = Math.round(this.colorAnimation.progress * f2);
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = i * 31 * round2;
        }
        return round3 != 0 ? i * 31 * round3 : i;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final String getName() {
        return this.name;
    }
}
