package com.airbnb.lottie.animation.content;

import android.graphics.BlendMode;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.graphics.PaintCompat;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import com.android.app.viewcapture.data.ViewNode;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    public BaseKeyframeAnimation blurAnimation;
    public float blurMaskFilterRadius;
    public final ColorKeyframeAnimation colorAnimation;
    public ValueCallbackKeyframeAnimation colorFilterAnimation;
    public final DropShadowKeyframeAnimation dropShadowAnimation;
    public final boolean hidden;
    public final BaseLayer layer;
    public final LottieDrawable lottieDrawable;
    public final String name;
    public final IntegerKeyframeAnimation opacityAnimation;
    public final LPaint paint;
    public final Path path;
    public final List paths;

    public FillContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeFill shapeFill) {
        Path path = new Path();
        this.path = path;
        LPaint lPaint = new LPaint(1);
        this.paint = lPaint;
        this.paths = new ArrayList();
        this.layer = baseLayer;
        this.name = shapeFill.name;
        this.hidden = shapeFill.hidden;
        this.lottieDrawable = lottieDrawable;
        if (baseLayer.getBlurEffect() != null) {
            BaseKeyframeAnimation createAnimation = baseLayer.getBlurEffect().blurriness.createAnimation();
            this.blurAnimation = createAnimation;
            createAnimation.addUpdateListener(this);
            baseLayer.addAnimation(this.blurAnimation);
        }
        if (baseLayer.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.getDropShadowEffect());
        }
        BlendMode blendMode = null;
        AnimatableColorValue animatableColorValue = shapeFill.color;
        if (animatableColorValue == null) {
            this.colorAnimation = null;
            this.opacityAnimation = null;
            return;
        }
        AnimatableIntegerValue animatableIntegerValue = shapeFill.opacity;
        int ordinal = baseLayer.layerModel.blendMode.ordinal();
        BlendModeCompat blendModeCompat = ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? ordinal != 5 ? ordinal != 16 ? null : BlendModeCompat.PLUS : BlendModeCompat.LIGHTEN : BlendModeCompat.DARKEN : BlendModeCompat.OVERLAY : BlendModeCompat.SCREEN;
        int i = PaintCompat.$r8$clinit;
        if (blendModeCompat != null) {
            switch (blendModeCompat.ordinal()) {
                case 0:
                    blendMode = BlendMode.CLEAR;
                    break;
                case 1:
                    blendMode = BlendMode.SRC;
                    break;
                case 2:
                    blendMode = BlendMode.DST;
                    break;
                case 3:
                    blendMode = BlendMode.SRC_OVER;
                    break;
                case 4:
                    blendMode = BlendMode.DST_OVER;
                    break;
                case 5:
                    blendMode = BlendMode.SRC_IN;
                    break;
                case 6:
                    blendMode = BlendMode.DST_IN;
                    break;
                case 7:
                    blendMode = BlendMode.SRC_OUT;
                    break;
                case 8:
                    blendMode = BlendMode.DST_OUT;
                    break;
                case 9:
                    blendMode = BlendMode.SRC_ATOP;
                    break;
                case 10:
                    blendMode = BlendMode.DST_ATOP;
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    blendMode = BlendMode.XOR;
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    blendMode = BlendMode.PLUS;
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    blendMode = BlendMode.MODULATE;
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    blendMode = BlendMode.SCREEN;
                    break;
                case 15:
                    blendMode = BlendMode.OVERLAY;
                    break;
                case 16:
                    blendMode = BlendMode.DARKEN;
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    blendMode = BlendMode.LIGHTEN;
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    blendMode = BlendMode.COLOR_DODGE;
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    blendMode = BlendMode.COLOR_BURN;
                    break;
                case 20:
                    blendMode = BlendMode.HARD_LIGHT;
                    break;
                case 21:
                    blendMode = BlendMode.SOFT_LIGHT;
                    break;
                case 22:
                    blendMode = BlendMode.DIFFERENCE;
                    break;
                case 23:
                    blendMode = BlendMode.EXCLUSION;
                    break;
                case 24:
                    blendMode = BlendMode.MULTIPLY;
                    break;
                case 25:
                    blendMode = BlendMode.HUE;
                    break;
                case 26:
                    blendMode = BlendMode.SATURATION;
                    break;
                case 27:
                    blendMode = BlendMode.COLOR;
                    break;
                case 28:
                    blendMode = BlendMode.LUMINOSITY;
                    break;
            }
        }
        lPaint.setBlendMode(blendMode);
        path.setFillType(shapeFill.fillType);
        BaseKeyframeAnimation createAnimation2 = animatableColorValue.createAnimation();
        this.colorAnimation = (ColorKeyframeAnimation) createAnimation2;
        createAnimation2.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation2);
        BaseKeyframeAnimation createAnimation3 = animatableIntegerValue.createAnimation();
        this.opacityAnimation = (IntegerKeyframeAnimation) createAnimation3;
        createAnimation3.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation3);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        PointF pointF = LottieProperty.TRANSFORM_ANCHOR_POINT;
        if (obj == 1) {
            this.colorAnimation.setValueCallback(lottieValueCallback);
            return;
        }
        if (obj == 4) {
            this.opacityAnimation.setValueCallback(lottieValueCallback);
            return;
        }
        ColorFilter colorFilter = LottieProperty.COLOR_FILTER;
        BaseLayer baseLayer = this.layer;
        if (obj == colorFilter) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorFilterAnimation;
            if (valueCallbackKeyframeAnimation != null) {
                baseLayer.removeAnimation(valueCallbackKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.colorFilterAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            baseLayer.addAnimation(this.colorFilterAnimation);
            return;
        }
        if (obj == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.blurAnimation;
            if (baseKeyframeAnimation != null) {
                baseKeyframeAnimation.setValueCallback(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.blurAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            baseLayer.addAnimation(this.blurAnimation);
            return;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
        if (obj == 5 && dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.color.setValueCallback(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.DROP_SHADOW_OPACITY && dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.setOpacityCallback(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.DROP_SHADOW_DIRECTION && dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.direction.setValueCallback(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.DROP_SHADOW_DISTANCE && dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.distance.setValueCallback(lottieValueCallback);
        } else {
            if (obj != LottieProperty.DROP_SHADOW_RADIUS || dropShadowKeyframeAnimation == null) {
                return;
            }
            dropShadowKeyframeAnimation.radius.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public final void draw(Canvas canvas, Matrix matrix, int i) {
        BlurMaskFilter blurMaskFilter;
        if (this.hidden) {
            return;
        }
        ColorKeyframeAnimation colorKeyframeAnimation = this.colorAnimation;
        int intValue = colorKeyframeAnimation.getIntValue(colorKeyframeAnimation.keyframesWrapper.getCurrentKeyframe(), colorKeyframeAnimation.getInterpolatedCurrentKeyframeProgress());
        PointF pointF = MiscUtils.pathFromDataCurrentPoint;
        int max = (Math.max(0, Math.min(255, (int) ((((i / 255.0f) * ((Integer) this.opacityAnimation.getValue()).intValue()) / 100.0f) * 255.0f))) << 24) | (intValue & 16777215);
        LPaint lPaint = this.paint;
        lPaint.setColor(max);
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorFilterAnimation;
        if (valueCallbackKeyframeAnimation != null) {
            lPaint.setColorFilter((ColorFilter) valueCallbackKeyframeAnimation.getValue());
        }
        BaseKeyframeAnimation baseKeyframeAnimation = this.blurAnimation;
        if (baseKeyframeAnimation != null) {
            float floatValue = ((Float) baseKeyframeAnimation.getValue()).floatValue();
            if (floatValue == 0.0f) {
                lPaint.setMaskFilter(null);
            } else if (floatValue != this.blurMaskFilterRadius) {
                BaseLayer baseLayer = this.layer;
                if (baseLayer.blurMaskFilterRadius == floatValue) {
                    blurMaskFilter = baseLayer.blurMaskFilter;
                } else {
                    BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(floatValue / 2.0f, BlurMaskFilter.Blur.NORMAL);
                    baseLayer.blurMaskFilter = blurMaskFilter2;
                    baseLayer.blurMaskFilterRadius = floatValue;
                    blurMaskFilter = blurMaskFilter2;
                }
                lPaint.setMaskFilter(blurMaskFilter);
            }
            this.blurMaskFilterRadius = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.applyTo(lPaint);
        }
        this.path.reset();
        for (int i2 = 0; i2 < ((ArrayList) this.paths).size(); i2++) {
            this.path.addPath(((PathContent) ((ArrayList) this.paths).get(i2)).getPath(), matrix);
        }
        canvas.drawPath(this.path, lPaint);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.path.reset();
        for (int i = 0; i < ((ArrayList) this.paths).size(); i++) {
            this.path.addPath(((PathContent) ((ArrayList) this.paths).get(i)).getPath(), matrix);
        }
        this.path.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public final void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public final void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final void setContents(List list, List list2) {
        for (int i = 0; i < list2.size(); i++) {
            Content content = (Content) list2.get(i);
            if (content instanceof PathContent) {
                this.paths.add((PathContent) content);
            }
        }
    }
}
