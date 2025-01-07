package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextLayer extends BaseLayer {
    public final LongSparseArray codePointCache;
    public final ColorKeyframeAnimation colorAnimation;
    public ValueCallbackKeyframeAnimation colorCallbackAnimation;
    public final LottieComposition composition;
    public final Map contentsForCharacter;
    public final AnonymousClass1 fillPaint;
    public final LottieDrawable lottieDrawable;
    public final Matrix matrix;
    public final RectF rectF;
    public final StringBuilder stringBuilder;
    public final ColorKeyframeAnimation strokeColorAnimation;
    public ValueCallbackKeyframeAnimation strokeColorCallbackAnimation;
    public final AnonymousClass1 strokePaint;
    public final FloatKeyframeAnimation strokeWidthAnimation;
    public ValueCallbackKeyframeAnimation strokeWidthCallbackAnimation;
    public final TextKeyframeAnimation textAnimation;
    public ValueCallbackKeyframeAnimation textSizeCallbackAnimation;
    public final List textSubLines;
    public final FloatKeyframeAnimation trackingAnimation;
    public ValueCallbackKeyframeAnimation trackingCallbackAnimation;
    public ValueCallbackKeyframeAnimation typefaceCallbackAnimation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$1, reason: invalid class name */
    public final class AnonymousClass1 extends Paint {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TextSubLine {
        public String text;
        public float width;
    }

    public TextLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableColorValue animatableColorValue;
        AnimatableColorValue animatableColorValue2;
        this.stringBuilder = new StringBuilder(2);
        this.rectF = new RectF();
        this.matrix = new Matrix();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.setStyle(Paint.Style.FILL);
        this.fillPaint = anonymousClass1;
        AnonymousClass1 anonymousClass12 = new AnonymousClass1(1);
        anonymousClass12.setStyle(Paint.Style.STROKE);
        this.strokePaint = anonymousClass12;
        this.contentsForCharacter = new HashMap();
        this.codePointCache = new LongSparseArray((Object) null);
        this.textSubLines = new ArrayList();
        this.lottieDrawable = lottieDrawable;
        this.composition = layer.composition;
        TextKeyframeAnimation textKeyframeAnimation = new TextKeyframeAnimation(layer.text.keyframes);
        this.textAnimation = textKeyframeAnimation;
        textKeyframeAnimation.addUpdateListener(this);
        addAnimation(textKeyframeAnimation);
        AnimatableTextProperties animatableTextProperties = layer.textProperties;
        if (animatableTextProperties != null && (animatableColorValue2 = animatableTextProperties.color) != null) {
            BaseKeyframeAnimation createAnimation = animatableColorValue2.createAnimation();
            this.colorAnimation = (ColorKeyframeAnimation) createAnimation;
            createAnimation.addUpdateListener(this);
            addAnimation(createAnimation);
        }
        if (animatableTextProperties != null && (animatableColorValue = animatableTextProperties.stroke) != null) {
            BaseKeyframeAnimation createAnimation2 = animatableColorValue.createAnimation();
            this.strokeColorAnimation = (ColorKeyframeAnimation) createAnimation2;
            createAnimation2.addUpdateListener(this);
            addAnimation(createAnimation2);
        }
        if (animatableTextProperties != null && (animatableFloatValue2 = animatableTextProperties.strokeWidth) != null) {
            BaseKeyframeAnimation createAnimation3 = animatableFloatValue2.createAnimation();
            this.strokeWidthAnimation = (FloatKeyframeAnimation) createAnimation3;
            createAnimation3.addUpdateListener(this);
            addAnimation(createAnimation3);
        }
        if (animatableTextProperties == null || (animatableFloatValue = animatableTextProperties.tracking) == null) {
            return;
        }
        BaseKeyframeAnimation createAnimation4 = animatableFloatValue.createAnimation();
        this.trackingAnimation = (FloatKeyframeAnimation) createAnimation4;
        createAnimation4.addUpdateListener(this);
        addAnimation(createAnimation4);
    }

    public static void drawCharacter(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    public static void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public final void addValueCallback(LottieValueCallback lottieValueCallback, Object obj) {
        super.addValueCallback(lottieValueCallback, obj);
        PointF pointF = LottieProperty.TRANSFORM_ANCHOR_POINT;
        if (obj == 1) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
            if (valueCallbackKeyframeAnimation != null) {
                removeAnimation(valueCallbackKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
            return;
        }
        if (obj == 2) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = this.strokeColorCallbackAnimation;
            if (valueCallbackKeyframeAnimation3 != null) {
                removeAnimation(valueCallbackKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.strokeColorCallbackAnimation = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
            return;
        }
        if (obj == LottieProperty.STROKE_WIDTH) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = this.strokeWidthCallbackAnimation;
            if (valueCallbackKeyframeAnimation5 != null) {
                removeAnimation(valueCallbackKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.strokeWidthCallbackAnimation = valueCallbackKeyframeAnimation6;
            valueCallbackKeyframeAnimation6.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
            return;
        }
        if (obj == LottieProperty.TEXT_TRACKING) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation7 = this.trackingCallbackAnimation;
            if (valueCallbackKeyframeAnimation7 != null) {
                removeAnimation(valueCallbackKeyframeAnimation7);
            }
            if (lottieValueCallback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation8 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.trackingCallbackAnimation = valueCallbackKeyframeAnimation8;
            valueCallbackKeyframeAnimation8.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
            return;
        }
        if (obj == LottieProperty.TEXT_SIZE) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation9 = this.textSizeCallbackAnimation;
            if (valueCallbackKeyframeAnimation9 != null) {
                removeAnimation(valueCallbackKeyframeAnimation9);
            }
            if (lottieValueCallback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation10 = new ValueCallbackKeyframeAnimation(lottieValueCallback, null);
            this.textSizeCallbackAnimation = valueCallbackKeyframeAnimation10;
            valueCallbackKeyframeAnimation10.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
            return;
        }
        if (obj != LottieProperty.TYPEFACE) {
            if (obj == LottieProperty.TEXT) {
                TextKeyframeAnimation textKeyframeAnimation = this.textAnimation;
                textKeyframeAnimation.getClass();
                textKeyframeAnimation.setValueCallback(new LottieValueCallback() { // from class: com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation.1
                    public final /* synthetic */ DocumentData val$documentData;
                    public final /* synthetic */ LottieValueCallback val$valueCallback;

                    public AnonymousClass1(LottieValueCallback lottieValueCallback2, DocumentData documentData) {
                        r2 = lottieValueCallback2;
                        r3 = documentData;
                    }

                    @Override // com.airbnb.lottie.value.LottieValueCallback
                    public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                        float f = lottieFrameInfo.startFrame;
                        float f2 = lottieFrameInfo.endFrame;
                        String str = ((DocumentData) lottieFrameInfo.startValue).text;
                        String str2 = ((DocumentData) lottieFrameInfo.endValue).text;
                        float f3 = lottieFrameInfo.linearKeyframeProgress;
                        float f4 = lottieFrameInfo.interpolatedKeyframeProgress;
                        float f5 = lottieFrameInfo.overallProgress;
                        LottieFrameInfo lottieFrameInfo2 = LottieFrameInfo.this;
                        lottieFrameInfo2.startFrame = f;
                        lottieFrameInfo2.endFrame = f2;
                        lottieFrameInfo2.startValue = str;
                        lottieFrameInfo2.endValue = str2;
                        lottieFrameInfo2.linearKeyframeProgress = f3;
                        lottieFrameInfo2.interpolatedKeyframeProgress = f4;
                        lottieFrameInfo2.overallProgress = f5;
                        String str3 = (String) r2.getValue(lottieFrameInfo2);
                        DocumentData documentData = (DocumentData) (lottieFrameInfo.interpolatedKeyframeProgress == 1.0f ? lottieFrameInfo.endValue : lottieFrameInfo.startValue);
                        String str4 = documentData.fontName;
                        float f6 = documentData.size;
                        DocumentData.Justification justification = documentData.justification;
                        int i = documentData.tracking;
                        float f7 = documentData.lineHeight;
                        float f8 = documentData.baselineShift;
                        int i2 = documentData.color;
                        int i3 = documentData.strokeColor;
                        float f9 = documentData.strokeWidth;
                        boolean z = documentData.strokeOverFill;
                        PointF pointF2 = documentData.boxPosition;
                        PointF pointF3 = documentData.boxSize;
                        DocumentData documentData2 = r3;
                        documentData2.text = str3;
                        documentData2.fontName = str4;
                        documentData2.size = f6;
                        documentData2.justification = justification;
                        documentData2.tracking = i;
                        documentData2.lineHeight = f7;
                        documentData2.baselineShift = f8;
                        documentData2.color = i2;
                        documentData2.strokeColor = i3;
                        documentData2.strokeWidth = f9;
                        documentData2.strokeOverFill = z;
                        documentData2.boxPosition = pointF2;
                        documentData2.boxSize = pointF3;
                        return documentData2;
                    }
                });
                return;
            }
            return;
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation11 = this.typefaceCallbackAnimation;
        if (valueCallbackKeyframeAnimation11 != null) {
            removeAnimation(valueCallbackKeyframeAnimation11);
        }
        if (lottieValueCallback2 == null) {
            this.typefaceCallbackAnimation = null;
            return;
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation12 = new ValueCallbackKeyframeAnimation(lottieValueCallback2, null);
        this.typefaceCallbackAnimation = valueCallbackKeyframeAnimation12;
        valueCallbackKeyframeAnimation12.addUpdateListener(this);
        addAnimation(this.typefaceCallbackAnimation);
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03de  */
    @Override // com.airbnb.lottie.model.layer.BaseLayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void drawLayer(android.graphics.Canvas r27, android.graphics.Matrix r28, int r29) {
        /*
            Method dump skipped, instructions count: 1282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawLayer(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }

    public final TextSubLine ensureEnoughSubLines(int i) {
        for (int size = ((ArrayList) this.textSubLines).size(); size < i; size++) {
            List list = this.textSubLines;
            TextSubLine textSubLine = new TextSubLine();
            textSubLine.text = "";
            textSubLine.width = 0.0f;
            list.add(textSubLine);
        }
        return (TextSubLine) ((ArrayList) this.textSubLines).get(i - 1);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public final void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        LottieComposition lottieComposition = this.composition;
        rectF.set(0.0f, 0.0f, lottieComposition.bounds.width(), lottieComposition.bounds.height());
    }

    public final boolean offsetCanvas(Canvas canvas, DocumentData documentData, int i, float f) {
        PointF pointF = documentData.boxPosition;
        PointF pointF2 = documentData.boxSize;
        float dpScale = Utils.dpScale();
        float f2 = (i * documentData.lineHeight * dpScale) + (pointF == null ? 0.0f : (documentData.lineHeight * dpScale) + pointF.y);
        if (this.lottieDrawable.clipTextToBoundingBox && pointF2 != null && pointF != null && f2 >= pointF.y + pointF2.y + documentData.size) {
            return false;
        }
        float f3 = pointF == null ? 0.0f : pointF.x;
        float f4 = pointF2 != null ? pointF2.x : 0.0f;
        int ordinal = documentData.justification.ordinal();
        if (ordinal == 0) {
            canvas.translate(f3, f2);
        } else if (ordinal == 1) {
            canvas.translate((f3 + f4) - f, f2);
        } else if (ordinal == 2) {
            canvas.translate(((f4 / 2.0f) + f3) - (f / 2.0f), f2);
        }
        return true;
    }

    public final List splitGlyphTextIntoLines(String str, float f, Font font, float f2, float f3, boolean z) {
        float measureText;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt = str.charAt(i4);
            if (z) {
                FontCharacter fontCharacter = (FontCharacter) this.composition.characters.get(FontCharacter.hashFor(charAt, font.family, font.style));
                if (fontCharacter != null) {
                    measureText = (Utils.dpScale() * ((float) fontCharacter.width) * f2) + f3;
                }
            } else {
                measureText = this.fillPaint.measureText(str.substring(i4, i4 + 1)) + f3;
            }
            if (charAt == ' ') {
                z2 = true;
                f6 = measureText;
            } else if (z2) {
                z2 = false;
                i3 = i4;
                f5 = measureText;
            } else {
                f5 += measureText;
            }
            f4 += measureText;
            if (f > 0.0f && f4 >= f && charAt != ' ') {
                i++;
                TextSubLine ensureEnoughSubLines = ensureEnoughSubLines(i);
                if (i3 == i2) {
                    ensureEnoughSubLines.text = str.substring(i2, i4).trim();
                    ensureEnoughSubLines.width = (f4 - measureText) - ((r10.length() - r8.length()) * f6);
                    i2 = i4;
                    i3 = i2;
                    f4 = measureText;
                    f5 = f4;
                } else {
                    ensureEnoughSubLines.text = str.substring(i2, i3 - 1).trim();
                    ensureEnoughSubLines.width = ((f4 - f5) - ((r8.length() - r14.length()) * f6)) - f6;
                    f4 = f5;
                    i2 = i3;
                }
            }
        }
        if (f4 > 0.0f) {
            i++;
            TextSubLine ensureEnoughSubLines2 = ensureEnoughSubLines(i);
            ensureEnoughSubLines2.text = str.substring(i2);
            ensureEnoughSubLines2.width = f4;
        }
        return this.textSubLines.subList(0, i);
    }
}
