package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseStrokeContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, DrawingContent {
    public BaseKeyframeAnimation blurAnimation;
    public float blurMaskFilterRadius;
    public ValueCallbackKeyframeAnimation colorFilterAnimation;
    public final List dashPatternAnimations;
    public final FloatKeyframeAnimation dashPatternOffsetAnimation;
    public final float[] dashPatternValues;
    public final DropShadowKeyframeAnimation dropShadowAnimation;
    public final BaseLayer layer;
    public final LottieDrawable lottieDrawable;
    public final IntegerKeyframeAnimation opacityAnimation;
    public final LPaint paint;
    public final FloatKeyframeAnimation widthAnimation;
    public final PathMeasure pm = new PathMeasure();
    public final Path path = new Path();
    public final Path trimPathPath = new Path();
    public final RectF rect = new RectF();
    public final List pathGroups = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathGroup {
        public final List paths = new ArrayList();
        public final TrimPathContent trimPath;

        public PathGroup(TrimPathContent trimPathContent) {
            this.trimPath = trimPathContent;
        }
    }

    public BaseStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, Paint.Cap cap, Paint.Join join, float f, AnimatableIntegerValue animatableIntegerValue, AnimatableFloatValue animatableFloatValue, List list, AnimatableFloatValue animatableFloatValue2) {
        LPaint lPaint = new LPaint(1);
        this.paint = lPaint;
        this.blurMaskFilterRadius = 0.0f;
        this.lottieDrawable = lottieDrawable;
        this.layer = baseLayer;
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeCap(cap);
        lPaint.setStrokeJoin(join);
        lPaint.setStrokeMiter(f);
        this.opacityAnimation = (IntegerKeyframeAnimation) animatableIntegerValue.createAnimation();
        this.widthAnimation = (FloatKeyframeAnimation) animatableFloatValue.createAnimation();
        if (animatableFloatValue2 == null) {
            this.dashPatternOffsetAnimation = null;
        } else {
            this.dashPatternOffsetAnimation = (FloatKeyframeAnimation) animatableFloatValue2.createAnimation();
        }
        ArrayList arrayList = (ArrayList) list;
        this.dashPatternAnimations = new ArrayList(arrayList.size());
        this.dashPatternValues = new float[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            this.dashPatternAnimations.add(((AnimatableFloatValue) arrayList.get(i)).createAnimation());
        }
        baseLayer.addAnimation(this.opacityAnimation);
        baseLayer.addAnimation(this.widthAnimation);
        for (int i2 = 0; i2 < ((ArrayList) this.dashPatternAnimations).size(); i2++) {
            baseLayer.addAnimation((BaseKeyframeAnimation) ((ArrayList) this.dashPatternAnimations).get(i2));
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.dashPatternOffsetAnimation;
        if (floatKeyframeAnimation != null) {
            baseLayer.addAnimation(floatKeyframeAnimation);
        }
        this.opacityAnimation.addUpdateListener(this);
        this.widthAnimation.addUpdateListener(this);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ((BaseKeyframeAnimation) ((ArrayList) this.dashPatternAnimations).get(i3)).addUpdateListener(this);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.dashPatternOffsetAnimation;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.addUpdateListener(this);
        }
        if (baseLayer.getBlurEffect() != null) {
            BaseKeyframeAnimation createAnimation = baseLayer.getBlurEffect().blurriness.createAnimation();
            this.blurAnimation = createAnimation;
            createAnimation.addUpdateListener(this);
            baseLayer.addAnimation(this.blurAnimation);
        }
        if (baseLayer.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.getDropShadowEffect());
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        PointF pointF = LottieProperty.TRANSFORM_ANCHOR_POINT;
        if (obj == 4) {
            this.opacityAnimation.setValueCallback(lottieValueCallback);
            return;
        }
        if (obj == LottieProperty.STROKE_WIDTH) {
            this.widthAnimation.setValueCallback(lottieValueCallback);
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
    public void draw(Canvas canvas, Matrix matrix, int i) {
        BlurMaskFilter blurMaskFilter;
        float[] fArr;
        int i2 = 1;
        float[] fArr2 = (float[]) Utils.threadLocalPoints.get();
        boolean z = false;
        fArr2[0] = 0.0f;
        fArr2[1] = 0.0f;
        fArr2[2] = 37394.73f;
        fArr2[3] = 39575.234f;
        matrix.mapPoints(fArr2);
        if (fArr2[0] == fArr2[2] || fArr2[1] == fArr2[3]) {
            return;
        }
        IntegerKeyframeAnimation integerKeyframeAnimation = this.opacityAnimation;
        float intValue = (i / 255.0f) * integerKeyframeAnimation.getIntValue(integerKeyframeAnimation.keyframesWrapper.getCurrentKeyframe(), integerKeyframeAnimation.getInterpolatedCurrentKeyframeProgress());
        float f = 100.0f;
        PointF pointF = MiscUtils.pathFromDataCurrentPoint;
        int max = Math.max(0, Math.min(255, (int) ((intValue / 100.0f) * 255.0f)));
        LPaint lPaint = this.paint;
        lPaint.setAlpha(max);
        lPaint.setStrokeWidth(Utils.getScale(matrix) * this.widthAnimation.getFloatValue());
        if (lPaint.getStrokeWidth() <= 0.0f) {
            return;
        }
        float f2 = 1.0f;
        if (!this.dashPatternAnimations.isEmpty()) {
            float scale = Utils.getScale(matrix);
            int i3 = 0;
            while (true) {
                int size = ((ArrayList) this.dashPatternAnimations).size();
                fArr = this.dashPatternValues;
                if (i3 >= size) {
                    break;
                }
                float floatValue = ((Float) ((BaseKeyframeAnimation) ((ArrayList) this.dashPatternAnimations).get(i3)).getValue()).floatValue();
                fArr[i3] = floatValue;
                if (i3 % 2 == 0) {
                    if (floatValue < 1.0f) {
                        fArr[i3] = 1.0f;
                    }
                } else if (floatValue < 0.1f) {
                    fArr[i3] = 0.1f;
                }
                fArr[i3] = fArr[i3] * scale;
                i3++;
            }
            FloatKeyframeAnimation floatKeyframeAnimation = this.dashPatternOffsetAnimation;
            lPaint.setPathEffect(new DashPathEffect(fArr, floatKeyframeAnimation == null ? 0.0f : ((Float) floatKeyframeAnimation.getValue()).floatValue() * scale));
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorFilterAnimation;
        if (valueCallbackKeyframeAnimation != null) {
            lPaint.setColorFilter((ColorFilter) valueCallbackKeyframeAnimation.getValue());
        }
        BaseKeyframeAnimation baseKeyframeAnimation = this.blurAnimation;
        if (baseKeyframeAnimation != null) {
            float floatValue2 = ((Float) baseKeyframeAnimation.getValue()).floatValue();
            if (floatValue2 == 0.0f) {
                lPaint.setMaskFilter(null);
            } else if (floatValue2 != this.blurMaskFilterRadius) {
                BaseLayer baseLayer = this.layer;
                if (baseLayer.blurMaskFilterRadius == floatValue2) {
                    blurMaskFilter = baseLayer.blurMaskFilter;
                } else {
                    BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(floatValue2 / 2.0f, BlurMaskFilter.Blur.NORMAL);
                    baseLayer.blurMaskFilter = blurMaskFilter2;
                    baseLayer.blurMaskFilterRadius = floatValue2;
                    blurMaskFilter = blurMaskFilter2;
                }
                lPaint.setMaskFilter(blurMaskFilter);
            }
            this.blurMaskFilterRadius = floatValue2;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.applyTo(lPaint);
        }
        int i4 = 0;
        while (i4 < ((ArrayList) this.pathGroups).size()) {
            PathGroup pathGroup = (PathGroup) ((ArrayList) this.pathGroups).get(i4);
            if (pathGroup.trimPath != null) {
                this.path.reset();
                for (int size2 = ((ArrayList) pathGroup.paths).size() - i2; size2 >= 0; size2--) {
                    this.path.addPath(((PathContent) ((ArrayList) pathGroup.paths).get(size2)).getPath(), matrix);
                }
                TrimPathContent trimPathContent = pathGroup.trimPath;
                float floatValue3 = ((Float) trimPathContent.startAnimation.getValue()).floatValue() / f;
                float floatValue4 = ((Float) trimPathContent.endAnimation.getValue()).floatValue() / f;
                float floatValue5 = ((Float) trimPathContent.offsetAnimation.getValue()).floatValue() / 360.0f;
                if (floatValue3 >= 0.01f || floatValue4 <= 0.99f) {
                    this.pm.setPath(this.path, z);
                    float length = this.pm.getLength();
                    while (this.pm.nextContour()) {
                        length += this.pm.getLength();
                    }
                    float f3 = floatValue5 * length;
                    float f4 = (floatValue3 * length) + f3;
                    float min = Math.min((floatValue4 * length) + f3, (f4 + length) - f2);
                    int size3 = ((ArrayList) pathGroup.paths).size() - i2;
                    float f5 = 0.0f;
                    while (size3 >= 0) {
                        this.trimPathPath.set(((PathContent) ((ArrayList) pathGroup.paths).get(size3)).getPath());
                        this.trimPathPath.transform(matrix);
                        this.pm.setPath(this.trimPathPath, z);
                        float length2 = this.pm.getLength();
                        if (min > length) {
                            float f6 = min - length;
                            if (f6 < f5 + length2 && f5 < f6) {
                                Utils.applyTrimPathIfNeeded(this.trimPathPath, f4 > length ? (f4 - length) / length2 : 0.0f, Math.min(f6 / length2, f2), 0.0f);
                                canvas.drawPath(this.trimPathPath, lPaint);
                                f5 += length2;
                                size3--;
                                z = false;
                                f2 = 1.0f;
                            }
                        }
                        float f7 = f5 + length2;
                        if (f7 >= f4 && f5 <= min) {
                            if (f7 > min || f4 >= f5) {
                                Utils.applyTrimPathIfNeeded(this.trimPathPath, f4 < f5 ? 0.0f : (f4 - f5) / length2, min > f7 ? 1.0f : (min - f5) / length2, 0.0f);
                                canvas.drawPath(this.trimPathPath, lPaint);
                            } else {
                                canvas.drawPath(this.trimPathPath, lPaint);
                            }
                        }
                        f5 += length2;
                        size3--;
                        z = false;
                        f2 = 1.0f;
                    }
                } else {
                    canvas.drawPath(this.path, lPaint);
                }
            } else {
                this.path.reset();
                for (int size4 = ((ArrayList) pathGroup.paths).size() - 1; size4 >= 0; size4--) {
                    this.path.addPath(((PathContent) ((ArrayList) pathGroup.paths).get(size4)).getPath(), matrix);
                }
                canvas.drawPath(this.path, lPaint);
            }
            i2 = 1;
            i4++;
            z = false;
            f = 100.0f;
            f2 = 1.0f;
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.path.reset();
        for (int i = 0; i < ((ArrayList) this.pathGroups).size(); i++) {
            PathGroup pathGroup = (PathGroup) ((ArrayList) this.pathGroups).get(i);
            for (int i2 = 0; i2 < ((ArrayList) pathGroup.paths).size(); i2++) {
                this.path.addPath(((PathContent) ((ArrayList) pathGroup.paths).get(i2)).getPath(), matrix);
            }
        }
        this.path.computeBounds(this.rect, false);
        float floatValue = this.widthAnimation.getFloatValue();
        RectF rectF2 = this.rect;
        float f = floatValue / 2.0f;
        rectF2.set(rectF2.left - f, rectF2.top - f, rectF2.right + f, rectF2.bottom + f);
        rectF.set(this.rect);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
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
        ShapeTrimPath.Type type;
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size() - 1;
        PathGroup pathGroup = null;
        TrimPathContent trimPathContent = null;
        while (true) {
            type = ShapeTrimPath.Type.INDIVIDUALLY;
            if (size < 0) {
                break;
            }
            Content content = (Content) arrayList.get(size);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent2 = (TrimPathContent) content;
                if (trimPathContent2.type == type) {
                    trimPathContent = trimPathContent2;
                }
            }
            size--;
        }
        if (trimPathContent != null) {
            trimPathContent.addListener(this);
        }
        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
            Content content2 = (Content) list2.get(size2);
            if (content2 instanceof TrimPathContent) {
                TrimPathContent trimPathContent3 = (TrimPathContent) content2;
                if (trimPathContent3.type == type) {
                    if (pathGroup != null) {
                        this.pathGroups.add(pathGroup);
                    }
                    PathGroup pathGroup2 = new PathGroup(trimPathContent3);
                    trimPathContent3.addListener(this);
                    pathGroup = pathGroup2;
                }
            }
            if (content2 instanceof PathContent) {
                if (pathGroup == null) {
                    pathGroup = new PathGroup(trimPathContent);
                }
                pathGroup.paths.add((PathContent) content2);
            }
        }
        if (pathGroup != null) {
            this.pathGroups.add(pathGroup);
        }
    }
}
