package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SolidLayer extends BaseLayer {
    public ValueCallbackKeyframeAnimation colorAnimation;
    public ValueCallbackKeyframeAnimation colorFilterAnimation;
    public final Layer layerModel;
    public final LPaint paint;
    public final Path path;
    public final float[] points;
    public final RectF rect;

    public SolidLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.rect = new RectF();
        LPaint lPaint = new LPaint();
        this.paint = lPaint;
        this.points = new float[8];
        this.path = new Path();
        this.layerModel = layer;
        lPaint.setAlpha(0);
        lPaint.setStyle(Paint.Style.FILL);
        lPaint.setColor(layer.solidColor);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        super.addValueCallback(lottieValueCallback, obj);
        if (obj == LottieProperty.COLOR_FILTER) {
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
                return;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
                return;
            }
        }
        if (obj == 1) {
            if (lottieValueCallback != null) {
                this.colorAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
                return;
            }
            this.colorAnimation = null;
            this.paint.setColor(this.layerModel.solidColor);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void drawLayer(Canvas canvas, Matrix matrix, int i) {
        Layer layer = this.layerModel;
        int alpha = Color.alpha(layer.solidColor);
        if (alpha == 0) {
            return;
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorAnimation;
        Integer num = valueCallbackKeyframeAnimation == null ? null : (Integer) valueCallbackKeyframeAnimation.getValue();
        LPaint lPaint = this.paint;
        if (num != null) {
            lPaint.setColor(num.intValue());
        } else {
            lPaint.setColor(layer.solidColor);
        }
        int intValue = (int) ((((alpha / 255.0f) * (this.transform.opacity == null ? 100 : ((Integer) r2.getValue()).intValue())) / 100.0f) * (i / 255.0f) * 255.0f);
        lPaint.setAlpha(intValue);
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = this.colorFilterAnimation;
        if (valueCallbackKeyframeAnimation2 != null) {
            lPaint.setColorFilter((ColorFilter) valueCallbackKeyframeAnimation2.getValue());
        }
        if (intValue > 0) {
            float[] fArr = this.points;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            float f = layer.solidWidth;
            fArr[2] = f;
            fArr[3] = 0.0f;
            fArr[4] = f;
            float f2 = layer.solidHeight;
            fArr[5] = f2;
            fArr[6] = 0.0f;
            fArr[7] = f2;
            matrix.mapPoints(fArr);
            this.path.reset();
            this.path.moveTo(fArr[0], fArr[1]);
            this.path.lineTo(fArr[2], fArr[3]);
            this.path.lineTo(fArr[4], fArr[5]);
            this.path.lineTo(fArr[6], fArr[7]);
            this.path.lineTo(fArr[0], fArr[1]);
            this.path.close();
            canvas.drawPath(this.path, lPaint);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        RectF rectF2 = this.rect;
        Layer layer = this.layerModel;
        rectF2.set(0.0f, 0.0f, layer.solidWidth, layer.solidHeight);
        this.boundsMatrix.mapRect(this.rect);
        rectF.set(this.rect);
    }
}
