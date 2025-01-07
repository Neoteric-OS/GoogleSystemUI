package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CompositionLayer extends BaseLayer {
    public boolean clipToCompositionBounds;
    public Boolean hasMasks;
    public Boolean hasMatte;
    public final Paint layerPaint;
    public final List layers;
    public final RectF newClipRect;
    public float progress;
    public final RectF rect;
    public BaseKeyframeAnimation timeRemapping;

    public CompositionLayer(LottieDrawable lottieDrawable, Layer layer, List list, LottieComposition lottieComposition) {
        super(lottieDrawable, layer);
        int i;
        BaseLayer baseLayer;
        BaseLayer compositionLayer;
        this.layers = new ArrayList();
        this.rect = new RectF();
        this.newClipRect = new RectF();
        this.layerPaint = new Paint();
        this.clipToCompositionBounds = true;
        AnimatableFloatValue animatableFloatValue = layer.timeRemapping;
        if (animatableFloatValue != null) {
            BaseKeyframeAnimation createAnimation = animatableFloatValue.createAnimation();
            this.timeRemapping = createAnimation;
            addAnimation(createAnimation);
            this.timeRemapping.addUpdateListener(this);
        } else {
            this.timeRemapping = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(((ArrayList) lottieComposition.layers).size());
        int size = list.size() - 1;
        BaseLayer baseLayer2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = (Layer) list.get(size);
            int ordinal = layer2.layerType.ordinal();
            if (ordinal == 0) {
                compositionLayer = new CompositionLayer(lottieDrawable, layer2, (List) lottieComposition.precomps.get(layer2.refId), lottieComposition);
            } else if (ordinal == 1) {
                compositionLayer = new SolidLayer(lottieDrawable, layer2);
            } else if (ordinal == 2) {
                compositionLayer = new ImageLayer(lottieDrawable, layer2);
            } else if (ordinal == 3) {
                compositionLayer = new NullLayer(lottieDrawable, layer2);
            } else if (ordinal == 4) {
                compositionLayer = new ShapeLayer(lottieDrawable, layer2, this, lottieComposition);
            } else if (ordinal != 5) {
                Logger.warning("Unknown layer type " + layer2.layerType);
                compositionLayer = null;
            } else {
                compositionLayer = new TextLayer(lottieDrawable, layer2);
            }
            if (compositionLayer != null) {
                longSparseArray.put(compositionLayer.layerModel.layerId, compositionLayer);
                if (baseLayer2 != null) {
                    baseLayer2.matteLayer = compositionLayer;
                    baseLayer2 = null;
                } else {
                    this.layers.add(0, compositionLayer);
                    int ordinal2 = layer2.matteType.ordinal();
                    if (ordinal2 == 1 || ordinal2 == 2) {
                        baseLayer2 = compositionLayer;
                    }
                }
            }
            size--;
        }
        for (i = 0; i < longSparseArray.size(); i++) {
            BaseLayer baseLayer3 = (BaseLayer) longSparseArray.get(longSparseArray.keyAt(i));
            if (baseLayer3 != null && (baseLayer = (BaseLayer) longSparseArray.get(baseLayer3.layerModel.parentId)) != null) {
                baseLayer3.parentLayer = baseLayer;
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        super.addValueCallback(lottieValueCallback, obj);
        if (obj == LottieProperty.TIME_REMAP) {
            if (lottieValueCallback == null) {
                BaseKeyframeAnimation baseKeyframeAnimation = this.timeRemapping;
                if (baseKeyframeAnimation != null) {
                    baseKeyframeAnimation.setValueCallback(null);
                    return;
                }
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.timeRemapping = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.timeRemapping);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void drawLayer(Canvas canvas, Matrix matrix, int i) {
        RectF rectF = this.newClipRect;
        Layer layer = this.layerModel;
        rectF.set(0.0f, 0.0f, layer.preCompWidth, layer.preCompHeight);
        matrix.mapRect(this.newClipRect);
        boolean z = this.lottieDrawable.isApplyingOpacityToLayersEnabled && ((ArrayList) this.layers).size() > 1 && i != 255;
        if (z) {
            this.layerPaint.setAlpha(i);
            RectF rectF2 = this.newClipRect;
            Paint paint = this.layerPaint;
            Utils.AnonymousClass1 anonymousClass1 = Utils.threadLocalPathMeasure;
            canvas.saveLayer(rectF2, paint);
        } else {
            canvas.save();
        }
        if (z) {
            i = 255;
        }
        for (int size = ((ArrayList) this.layers).size() - 1; size >= 0; size--) {
            if (((this.clipToCompositionBounds || !"__container".equals(layer.layerName)) && !this.newClipRect.isEmpty()) ? canvas.clipRect(this.newClipRect) : true) {
                ((BaseLayer) ((ArrayList) this.layers).get(size)).draw(canvas, matrix, i);
            }
        }
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        for (int size = ((ArrayList) this.layers).size() - 1; size >= 0; size--) {
            this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((BaseLayer) ((ArrayList) this.layers).get(size)).getBounds(this.rect, this.boundsMatrix, true);
            rectF.union(this.rect);
        }
    }

    public final boolean hasMasks() {
        if (this.hasMasks == null) {
            for (int size = ((ArrayList) this.layers).size() - 1; size >= 0; size--) {
                BaseLayer baseLayer = (BaseLayer) ((ArrayList) this.layers).get(size);
                if (baseLayer instanceof ShapeLayer) {
                    if (baseLayer.hasMasksOnThisLayer()) {
                        this.hasMasks = Boolean.TRUE;
                        return true;
                    }
                } else if ((baseLayer instanceof CompositionLayer) && ((CompositionLayer) baseLayer).hasMasks()) {
                    this.hasMasks = Boolean.TRUE;
                    return true;
                }
            }
            this.hasMasks = Boolean.FALSE;
        }
        return this.hasMasks.booleanValue();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void resolveChildKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        for (int i2 = 0; i2 < ((ArrayList) this.layers).size(); i2++) {
            ((BaseLayer) ((ArrayList) this.layers).get(i2)).resolveKeyPath(keyPath, i, list, keyPath2);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void setOutlineMasksAndMattes(boolean z) {
        super.setOutlineMasksAndMattes(z);
        Iterator it = this.layers.iterator();
        while (it.hasNext()) {
            ((BaseLayer) it.next()).setOutlineMasksAndMattes(z);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public final void setProgress(float f) {
        this.progress = f;
        super.setProgress(f);
        BaseKeyframeAnimation baseKeyframeAnimation = this.timeRemapping;
        Layer layer = this.layerModel;
        if (baseKeyframeAnimation != null) {
            LottieComposition lottieComposition = this.lottieDrawable.composition;
            f = ((((Float) baseKeyframeAnimation.getValue()).floatValue() * layer.composition.frameRate) - layer.composition.startFrame) / ((lottieComposition.endFrame - lottieComposition.startFrame) + 0.01f);
        }
        if (this.timeRemapping == null) {
            LottieComposition lottieComposition2 = layer.composition;
            f -= layer.startFrame / (lottieComposition2.endFrame - lottieComposition2.startFrame);
        }
        if (layer.timeStretch != 0.0f && !"__container".equals(layer.layerName)) {
            f /= layer.timeStretch;
        }
        for (int size = ((ArrayList) this.layers).size() - 1; size >= 0; size--) {
            ((BaseLayer) ((ArrayList) this.layers).get(size)).setProgress(f);
        }
    }
}
