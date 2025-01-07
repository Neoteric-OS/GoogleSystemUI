package com.android.systemui.animation;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontVariationAxis;
import android.graphics.text.PositionedGlyphs;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextShaper;
import android.util.MathUtils;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import com.android.internal.graphics.ColorUtils;
import com.android.systemui.animation.FontInterpolator;
import com.android.systemui.animation.TextInterpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextInterpolator {
    public final TextPaint basePaint;
    public Function2 glyphFilter;
    public Layout layout;
    public float progress;
    public final TextPaint targetPaint;
    public final TypefaceVariantCacheImpl typefaceCache;
    public List lines = EmptyList.INSTANCE;
    public final FontInterpolator fontInterpolator = new FontInterpolator();
    public final TextPaint tmpPaint = new TextPaint();
    public final Lazy tmpPaintForGlyph$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.animation.TextInterpolator$tmpPaintForGlyph$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new TextPaint();
        }
    });
    public final Lazy tmpGlyph$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.animation.TextInterpolator$tmpGlyph$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new TextInterpolator.MutablePositionedGlyph();
        }
    });
    public float[] tmpPositionArray = new float[20];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FontRun {
        public Font baseFont;
        public final int end;
        public final int start;
        public Font targetFont;

        public FontRun(int i, int i2, Font font, Font font2) {
            this.start = i;
            this.end = i2;
            this.baseFont = font;
            this.targetFont = font2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FontRun)) {
                return false;
            }
            FontRun fontRun = (FontRun) obj;
            return this.start == fontRun.start && this.end == fontRun.end && Intrinsics.areEqual(this.baseFont, fontRun.baseFont) && Intrinsics.areEqual(this.targetFont, fontRun.targetFont);
        }

        public final int hashCode() {
            return this.targetFont.hashCode() + ((this.baseFont.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.end, Integer.hashCode(this.start) * 31, 31)) * 31);
        }

        public final String toString() {
            return "FontRun(start=" + this.start + ", end=" + this.end + ", baseFont=" + this.baseFont + ", targetFont=" + this.targetFont + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Line {
        public final List runs;

        public Line(List list) {
            this.runs = list;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MutablePositionedGlyph {
        public int color;
        public int glyphIndex;
        public int lineNo;
        public float textSize;
        public float x;
        public float y;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Run {
        public final float[] baseX;
        public final float[] baseY;
        public final List fontRuns;
        public final int[] glyphIds;
        public final float[] targetX;
        public final float[] targetY;

        public Run(int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, List list) {
            this.glyphIds = iArr;
            this.baseX = fArr;
            this.baseY = fArr2;
            this.targetX = fArr3;
            this.targetY = fArr4;
            this.fontRuns = list;
        }
    }

    public TextInterpolator(Layout layout, TypefaceVariantCacheImpl typefaceVariantCacheImpl) {
        this.typefaceCache = typefaceVariantCacheImpl;
        this.basePaint = new TextPaint(layout.getPaint());
        this.targetPaint = new TextPaint(layout.getPaint());
        this.layout = layout;
        shapeText(layout);
    }

    public static void lerp(Paint paint, Paint paint2, float f, Paint paint3) {
        paint3.set(paint);
        paint3.setTextSize(MathUtils.lerp(paint.getTextSize(), paint2.getTextSize(), f));
        paint3.setColor(ColorUtils.blendARGB(paint.getColor(), paint2.getColor(), f));
        paint3.setStrokeWidth(MathUtils.lerp(paint.getStrokeWidth(), paint2.getStrokeWidth(), f));
    }

    public final void drawFontRun(Canvas canvas, Run run, FontRun fontRun, int i, Paint paint) {
        int i2;
        int i3;
        float[] fArr;
        int i4;
        Font lerp = this.fontInterpolator.lerp(fontRun.baseFont, fontRun.targetFont, this.progress);
        Function2 function2 = this.glyphFilter;
        float[] fArr2 = run.targetY;
        float[] fArr3 = run.baseY;
        float[] fArr4 = run.targetX;
        float[] fArr5 = run.baseX;
        int[] iArr = run.glyphIds;
        int i5 = 0;
        int i6 = fontRun.start;
        int i7 = fontRun.end;
        if (function2 == null) {
            while (i6 < i7) {
                int i8 = i5 + 1;
                this.tmpPositionArray[i5] = MathUtils.lerp(fArr5[i6], fArr4[i6], this.progress);
                i5 += 2;
                this.tmpPositionArray[i8] = MathUtils.lerp(fArr3[i6], fArr2[i6], this.progress);
                i6++;
            }
            float[] fArr6 = this.tmpPositionArray;
            int i9 = fontRun.start;
            canvas.drawGlyphs(iArr, i9, fArr6, 0, i7 - i9, lerp, paint);
            return;
        }
        getTmpGlyph().getClass();
        getTmpGlyph().getClass();
        getTmpGlyph().getClass();
        getTmpGlyph().lineNo = i;
        Lazy lazy = this.tmpPaintForGlyph$delegate;
        ((TextPaint) lazy.getValue()).set(paint);
        int i10 = i6;
        int i11 = i10;
        int i12 = 0;
        while (i11 < i7) {
            getTmpGlyph().glyphIndex = i11;
            MutablePositionedGlyph tmpGlyph = getTmpGlyph();
            int i13 = iArr[i11];
            tmpGlyph.getClass();
            int i14 = i12;
            getTmpGlyph().x = MathUtils.lerp(fArr5[i11], fArr4[i11], this.progress);
            getTmpGlyph().y = MathUtils.lerp(fArr3[i11], fArr2[i11], this.progress);
            getTmpGlyph().textSize = paint.getTextSize();
            getTmpGlyph().color = paint.getColor();
            function2.invoke(getTmpGlyph(), Float.valueOf(this.progress));
            if (getTmpGlyph().textSize == paint.getTextSize() && getTmpGlyph().color == paint.getColor()) {
                i4 = i14;
                i2 = i11;
                i3 = i7;
                fArr = fArr5;
            } else {
                ((TextPaint) lazy.getValue()).setTextSize(getTmpGlyph().textSize);
                ((TextPaint) lazy.getValue()).setColor(getTmpGlyph().color);
                i2 = i11;
                i3 = i7;
                fArr = fArr5;
                canvas.drawGlyphs(iArr, i10, this.tmpPositionArray, 0, i11 - i10, lerp, (TextPaint) lazy.getValue());
                i4 = 0;
                i10 = i2;
            }
            int i15 = i4 + 1;
            this.tmpPositionArray[i4] = getTmpGlyph().x;
            i12 = i4 + 2;
            this.tmpPositionArray[i15] = getTmpGlyph().y;
            i11 = i2 + 1;
            i7 = i3;
            fArr5 = fArr;
        }
        canvas.drawGlyphs(iArr, i10, this.tmpPositionArray, 0, i7 - i10, lerp, (TextPaint) lazy.getValue());
    }

    public final MutablePositionedGlyph getTmpGlyph() {
        return (MutablePositionedGlyph) this.tmpGlyph$delegate.getValue();
    }

    public final void rebase() {
        float f = this.progress;
        if (f == 0.0f) {
            return;
        }
        if (f == 1.0f) {
            this.basePaint.set(this.targetPaint);
        } else {
            lerp(this.basePaint, this.targetPaint, f, this.tmpPaint);
            this.basePaint.set(this.tmpPaint);
        }
        Iterator it = this.lines.iterator();
        while (it.hasNext()) {
            for (Run run : ((Line) it.next()).runs) {
                int length = run.baseX.length;
                for (int i = 0; i < length; i++) {
                    float[] fArr = run.baseX;
                    fArr[i] = MathUtils.lerp(fArr[i], run.targetX[i], this.progress);
                    float[] fArr2 = run.baseY;
                    fArr2[i] = MathUtils.lerp(fArr2[i], run.targetY[i], this.progress);
                }
                for (FontRun fontRun : run.fontRuns) {
                    Font lerp = this.fontInterpolator.lerp(fontRun.baseFont, fontRun.targetFont, this.progress);
                    fontRun.baseFont = lerp;
                    this.basePaint.setTypeface(this.typefaceCache.getTypefaceForVariant(FontVariationAxis.toFontVariationSettings(lerp.getAxes())));
                }
            }
        }
        this.progress = 0.0f;
    }

    public final void shapeText(Layout layout) {
        ArrayList arrayList;
        Iterator it;
        Iterator it2;
        ArrayList arrayList2;
        float[] fArr;
        int i;
        int i2;
        PositionedGlyphs positionedGlyphs;
        TextInterpolator textInterpolator = this;
        int i3 = 1;
        List shapeText = shapeText(layout, textInterpolator.basePaint);
        List shapeText2 = shapeText(layout, textInterpolator.targetPaint);
        if (((ArrayList) shapeText).size() != ((ArrayList) shapeText2).size()) {
            throw new IllegalArgumentException("The new layout result has different line count.");
        }
        Iterator it3 = shapeText.iterator();
        Iterator it4 = shapeText2.iterator();
        int i4 = 10;
        ArrayList arrayList3 = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(shapeText, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(shapeText2, 10)));
        int i5 = 0;
        while (it3.hasNext() && it4.hasNext()) {
            Object next = it3.next();
            List list = (List) it4.next();
            List list2 = (List) next;
            Iterator it5 = list2.iterator();
            Iterator it6 = list.iterator();
            ArrayList arrayList4 = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, i4), CollectionsKt__IterablesKt.collectionSizeOrDefault(list, i4)));
            while (it5.hasNext() && it6.hasNext()) {
                Object next2 = it5.next();
                PositionedGlyphs positionedGlyphs2 = (PositionedGlyphs) it6.next();
                PositionedGlyphs positionedGlyphs3 = (PositionedGlyphs) next2;
                if (positionedGlyphs3.glyphCount() != positionedGlyphs2.glyphCount()) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(textInterpolator.lines.size(), "Inconsistent glyph count at line ").toString());
                }
                int glyphCount = positionedGlyphs3.glyphCount();
                int[] iArr = new int[glyphCount];
                for (int i6 = 0; i6 < glyphCount; i6 += i3) {
                    int glyphId = positionedGlyphs3.getGlyphId(i6);
                    if (glyphId != positionedGlyphs2.getGlyphId(i6)) {
                        throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Inconsistent glyph ID at ", i6, textInterpolator.lines.size(), " in line ").toString());
                    }
                    iArr[i6] = glyphId;
                }
                float[] fArr2 = new float[glyphCount];
                for (int i7 = 0; i7 < glyphCount; i7 += i3) {
                    fArr2[i7] = positionedGlyphs3.getGlyphX(i7);
                }
                float[] fArr3 = new float[glyphCount];
                for (int i8 = 0; i8 < glyphCount; i8 += i3) {
                    fArr3[i8] = positionedGlyphs3.getGlyphY(i8);
                }
                float[] fArr4 = new float[glyphCount];
                for (int i9 = 0; i9 < glyphCount; i9++) {
                    fArr4[i9] = positionedGlyphs2.getGlyphX(i9);
                }
                float[] fArr5 = new float[glyphCount];
                int i10 = i5;
                for (int i11 = 0; i11 < glyphCount; i11++) {
                    fArr5[i11] = positionedGlyphs2.getGlyphY(i11);
                }
                ArrayList arrayList5 = new ArrayList();
                Iterator it7 = it3;
                Iterator it8 = it4;
                if (glyphCount != 0) {
                    Font font = positionedGlyphs3.getFont(0);
                    it = it5;
                    Font font2 = positionedGlyphs2.getFont(0);
                    boolean z = FontInterpolator.DEBUG;
                    it2 = it6;
                    if (!FontInterpolator.Companion.canInterpolate(font, font2)) {
                        throw new IllegalArgumentException(("Cannot interpolate font at 0 (" + font + " vs " + font2 + ")").toString());
                    }
                    arrayList = arrayList3;
                    fArr = fArr4;
                    int i12 = 0;
                    int i13 = 1;
                    Font font3 = font2;
                    Font font4 = font;
                    int i14 = i10;
                    while (i13 < glyphCount) {
                        ArrayList arrayList6 = arrayList4;
                        Font font5 = positionedGlyphs3.getFont(i13);
                        PositionedGlyphs positionedGlyphs4 = positionedGlyphs3;
                        Font font6 = positionedGlyphs2.getFont(i13);
                        if (font4 == font5) {
                            positionedGlyphs = positionedGlyphs2;
                            if (font3 != font6) {
                                throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Base font is unchanged at ", " but target font has changed.", i13).toString());
                            }
                        } else {
                            if (font3 == font6) {
                                throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Base font has changed at ", " but target font is unchanged.", i13).toString());
                            }
                            positionedGlyphs = positionedGlyphs2;
                            arrayList5.add(new FontRun(i12, i13, font4, font3));
                            int max = Math.max(i14, i13 - i12);
                            boolean z2 = FontInterpolator.DEBUG;
                            if (!FontInterpolator.Companion.canInterpolate(font5, font6)) {
                                throw new IllegalArgumentException(("Cannot interpolate font at " + i13 + " (" + font5 + " vs " + font6 + ")").toString());
                            }
                            i14 = max;
                            i12 = i13;
                            font3 = font6;
                            font4 = font5;
                        }
                        i13++;
                        positionedGlyphs3 = positionedGlyphs4;
                        positionedGlyphs2 = positionedGlyphs;
                        arrayList4 = arrayList6;
                    }
                    arrayList2 = arrayList4;
                    i = 1;
                    arrayList5.add(new FontRun(i12, glyphCount, font4, font3));
                    i2 = Math.max(i14, glyphCount - i12);
                } else {
                    arrayList = arrayList3;
                    it = it5;
                    it2 = it6;
                    arrayList2 = arrayList4;
                    fArr = fArr4;
                    i = 1;
                    i2 = i10;
                }
                Run run = new Run(iArr, fArr2, fArr3, fArr, fArr5, arrayList5);
                ArrayList arrayList7 = arrayList2;
                arrayList7.add(run);
                i5 = i2;
                arrayList4 = arrayList7;
                i3 = i;
                it4 = it8;
                it3 = it7;
                it5 = it;
                it6 = it2;
                arrayList3 = arrayList;
                textInterpolator = this;
            }
            ArrayList arrayList8 = arrayList3;
            arrayList8.add(new Line(arrayList4));
            arrayList3 = arrayList8;
            i3 = i3;
            i5 = i5;
            it4 = it4;
            it3 = it3;
            i4 = 10;
        }
        textInterpolator.lines = arrayList3;
        int i15 = i5 * 2;
        if (textInterpolator.tmpPositionArray.length < i15) {
            textInterpolator.tmpPositionArray = new float[i15];
        }
    }

    public final void updatePositionsAndFonts(List list, boolean z) {
        if (((ArrayList) list).size() != this.lines.size()) {
            throw new IllegalStateException("The new layout result has different line count.");
        }
        List list2 = this.lines;
        Iterator it = list2.iterator();
        Iterator it2 = list.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10)));
        while (it.hasNext() && it2.hasNext()) {
            Object next = it.next();
            List list3 = (List) it2.next();
            List list4 = ((Line) next).runs;
            Iterator it3 = list4.iterator();
            Iterator it4 = list3.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(CollectionsKt__IterablesKt.collectionSizeOrDefault(list4, 10), CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10)));
            while (it3.hasNext() && it4.hasNext()) {
                Object next2 = it3.next();
                PositionedGlyphs positionedGlyphs = (PositionedGlyphs) it4.next();
                Run run = (Run) next2;
                if (positionedGlyphs.glyphCount() != run.glyphIds.length) {
                    throw new IllegalArgumentException("The new layout has different glyph count.");
                }
                for (FontRun fontRun : run.fontRuns) {
                    Font font = positionedGlyphs.getFont(fontRun.start);
                    int i = fontRun.start;
                    for (int i2 = i; i2 < fontRun.end; i2++) {
                        if (positionedGlyphs.getGlyphId(i) != run.glyphIds[i]) {
                            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "The new layout has different glyph ID at ").toString());
                        }
                        if (font != positionedGlyphs.getFont(i2)) {
                            throw new IllegalArgumentException(("The new layout has different font run. " + font + " vs " + positionedGlyphs.getFont(i2) + " at " + i2).toString());
                        }
                    }
                    boolean z2 = FontInterpolator.DEBUG;
                    if (!FontInterpolator.Companion.canInterpolate(font, fontRun.baseFont)) {
                        throw new IllegalArgumentException(("New font cannot be interpolated with existing font. " + font + ", " + fontRun.baseFont).toString());
                    }
                    if (z) {
                        fontRun.baseFont = font;
                    } else {
                        fontRun.targetFont = font;
                    }
                }
                float[] fArr = run.baseX;
                int i3 = 0;
                if (z) {
                    int length = fArr.length;
                    while (i3 < length) {
                        fArr[i3] = positionedGlyphs.getGlyphX(i3);
                        run.baseY[i3] = positionedGlyphs.getGlyphY(i3);
                        i3++;
                    }
                } else {
                    int length2 = fArr.length;
                    while (i3 < length2) {
                        run.targetX[i3] = positionedGlyphs.getGlyphX(i3);
                        run.targetY[i3] = positionedGlyphs.getGlyphY(i3);
                        i3++;
                    }
                }
                arrayList2.add(Unit.INSTANCE);
            }
            arrayList.add(arrayList2);
        }
    }

    public static List shapeText(Layout layout, TextPaint textPaint) {
        ArrayList arrayList = new ArrayList();
        int lineCount = layout.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            int i2 = lineEnd - lineStart;
            int i3 = (lineStart + i2) - 1;
            if (i3 > lineStart && i3 < layout.getText().length() && layout.getText().charAt(i3) == '\n') {
                i2--;
            }
            final ArrayList arrayList2 = new ArrayList();
            TextShaper.shapeText(layout.getText(), lineStart, i2, layout.getTextDirectionHeuristic(), textPaint, new TextShaper.GlyphsConsumer() { // from class: com.android.systemui.animation.TextInterpolator$shapeText$3
                @Override // android.text.TextShaper.GlyphsConsumer
                public final void accept(int i4, int i5, PositionedGlyphs positionedGlyphs, TextPaint textPaint2) {
                    arrayList2.add(positionedGlyphs);
                }
            });
            arrayList.add(arrayList2);
            layout.getText().subSequence(lineStart, lineEnd).toString();
        }
        return arrayList;
    }
}
