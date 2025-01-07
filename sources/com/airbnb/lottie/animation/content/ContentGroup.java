package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentGroup implements DrawingContent, PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    public final List contents;
    public final boolean hidden;
    public final LottieDrawable lottieDrawable;
    public final Matrix matrix;
    public final String name;
    public final LPaint offScreenPaint;
    public final RectF offScreenRectF;
    public final Path path;
    public List pathContents;
    public final RectF rect;
    public final TransformKeyframeAnimation transformAnimation;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ContentGroup(com.airbnb.lottie.LottieDrawable r8, com.airbnb.lottie.model.layer.BaseLayer r9, com.airbnb.lottie.model.content.ShapeGroup r10, com.airbnb.lottie.LottieComposition r11) {
        /*
            r7 = this;
            java.lang.String r3 = r10.name
            java.util.List r0 = r10.items
            java.util.ArrayList r5 = new java.util.ArrayList
            int r1 = r0.size()
            r5.<init>(r1)
            r1 = 0
            r2 = r1
        Lf:
            int r4 = r0.size()
            if (r2 >= r4) goto L27
            java.lang.Object r4 = r0.get(r2)
            com.airbnb.lottie.model.content.ContentModel r4 = (com.airbnb.lottie.model.content.ContentModel) r4
            com.airbnb.lottie.animation.content.Content r4 = r4.toContent(r8, r11, r9)
            if (r4 == 0) goto L24
            r5.add(r4)
        L24:
            int r2 = r2 + 1
            goto Lf
        L27:
            java.util.List r11 = r10.items
        L29:
            int r0 = r11.size()
            if (r1 >= r0) goto L40
            java.lang.Object r0 = r11.get(r1)
            com.airbnb.lottie.model.content.ContentModel r0 = (com.airbnb.lottie.model.content.ContentModel) r0
            boolean r2 = r0 instanceof com.airbnb.lottie.model.animatable.AnimatableTransform
            if (r2 == 0) goto L3d
            com.airbnb.lottie.model.animatable.AnimatableTransform r0 = (com.airbnb.lottie.model.animatable.AnimatableTransform) r0
            r6 = r0
            goto L42
        L3d:
            int r1 = r1 + 1
            goto L29
        L40:
            r11 = 0
            r6 = r11
        L42:
            boolean r4 = r10.hidden
            r0 = r7
            r1 = r8
            r2 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.ContentGroup.<init>(com.airbnb.lottie.LottieDrawable, com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.content.ShapeGroup, com.airbnb.lottie.LottieComposition):void");
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            transformKeyframeAnimation.applyValueCallback(lottieValueCallback, obj);
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public final void draw(Canvas canvas, Matrix matrix, int i) {
        if (this.hidden) {
            return;
        }
        this.matrix.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
            i = (int) (((((transformKeyframeAnimation.opacity == null ? 100 : ((Integer) r7.getValue()).intValue()) / 100.0f) * i) / 255.0f) * 255.0f);
        }
        boolean z = false;
        if (this.lottieDrawable.isApplyingOpacityToLayersEnabled) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 >= ((ArrayList) this.contents).size()) {
                    break;
                }
                if (!(((ArrayList) this.contents).get(i2) instanceof DrawingContent) || (i3 = i3 + 1) < 2) {
                    i2++;
                } else if (i != 255) {
                    z = true;
                }
            }
        }
        if (z) {
            this.offScreenRectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            getBounds(this.offScreenRectF, this.matrix, true);
            LPaint lPaint = this.offScreenPaint;
            lPaint.setAlpha(i);
            RectF rectF = this.offScreenRectF;
            Utils.AnonymousClass1 anonymousClass1 = Utils.threadLocalPathMeasure;
            canvas.saveLayer(rectF, lPaint);
        }
        if (z) {
            i = 255;
        }
        for (int size = ((ArrayList) this.contents).size() - 1; size >= 0; size--) {
            Object obj = ((ArrayList) this.contents).get(size);
            if (obj instanceof DrawingContent) {
                ((DrawingContent) obj).draw(canvas, this.matrix, i);
            }
        }
        if (z) {
            canvas.restore();
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.matrix.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
        }
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = ((ArrayList) this.contents).size() - 1; size >= 0; size--) {
            Content content = (Content) ((ArrayList) this.contents).get(size);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).getBounds(this.rect, this.matrix, z);
                rectF.union(this.rect);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final String getName() {
        throw null;
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public final Path getPath() {
        this.matrix.reset();
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.set(transformKeyframeAnimation.getMatrix());
        }
        this.path.reset();
        if (this.hidden) {
            return this.path;
        }
        for (int size = ((ArrayList) this.contents).size() - 1; size >= 0; size--) {
            Content content = (Content) ((ArrayList) this.contents).get(size);
            if (content instanceof PathContent) {
                this.path.addPath(((PathContent) content).getPath(), this.matrix);
            }
        }
        return this.path;
    }

    public final List getPathList() {
        if (this.pathContents == null) {
            this.pathContents = new ArrayList();
            for (int i = 0; i < this.contents.size(); i++) {
                Content content = (Content) this.contents.get(i);
                if (content instanceof PathContent) {
                    this.pathContents.add((PathContent) content);
                }
            }
        }
        return this.pathContents;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public final void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public final void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        String str = this.name;
        if (keyPath.matches(i, str) || "__container".equals(str)) {
            if (!"__container".equals(str)) {
                KeyPath keyPath3 = new KeyPath(keyPath2);
                keyPath3.keys.add(str);
                if (keyPath.fullyResolvesTo(i, str)) {
                    KeyPath keyPath4 = new KeyPath(keyPath3);
                    keyPath4.resolvedElement = this;
                    list.add(keyPath4);
                }
                keyPath2 = keyPath3;
            }
            if (keyPath.propagateToChildren(i, str)) {
                int incrementDepthBy = keyPath.incrementDepthBy(i, str) + i;
                for (int i2 = 0; i2 < ((ArrayList) this.contents).size(); i2++) {
                    Content content = (Content) ((ArrayList) this.contents).get(i2);
                    if (content instanceof KeyPathElement) {
                        ((KeyPathElement) content).resolveKeyPath(keyPath, incrementDepthBy, list, keyPath2);
                    }
                }
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final void setContents(List list, List list2) {
        ArrayList arrayList = new ArrayList(((ArrayList) this.contents).size() + list.size());
        arrayList.addAll(list);
        for (int size = ((ArrayList) this.contents).size() - 1; size >= 0; size--) {
            Content content = (Content) ((ArrayList) this.contents).get(size);
            content.setContents(arrayList, this.contents.subList(0, size));
            arrayList.add(content);
        }
    }

    public ContentGroup(LottieDrawable lottieDrawable, BaseLayer baseLayer, String str, boolean z, List list, AnimatableTransform animatableTransform) {
        this.offScreenPaint = new LPaint();
        this.offScreenRectF = new RectF();
        this.matrix = new Matrix();
        this.path = new Path();
        this.rect = new RectF();
        this.name = str;
        this.lottieDrawable = lottieDrawable;
        this.hidden = z;
        this.contents = list;
        if (animatableTransform != null) {
            TransformKeyframeAnimation transformKeyframeAnimation = new TransformKeyframeAnimation(animatableTransform);
            this.transformAnimation = transformKeyframeAnimation;
            transformKeyframeAnimation.addAnimationsToLayer(baseLayer);
            transformKeyframeAnimation.addListener(this);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) list;
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            Content content = (Content) arrayList2.get(size);
            if (content instanceof GreedyContent) {
                arrayList.add((GreedyContent) content);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((GreedyContent) arrayList.get(size2)).absorbContent(list.listIterator(arrayList2.size()));
        }
    }
}
