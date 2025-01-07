package androidx.compose.ui.text;

import android.graphics.Matrix;
import android.graphics.Shader;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.platform.AndroidMultiParagraphDraw_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultiParagraph {
    public final boolean didExceedMaxLines;
    public final float height;
    public final MultiParagraphIntrinsics intrinsics;
    public final int lineCount;
    public final int maxLines;
    public final List paragraphInfoList;
    public final List placeholderRects;
    public final float width;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    public MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2) {
        boolean z;
        int i3;
        int m654getMaxHeightimpl;
        int i4;
        this.intrinsics = multiParagraphIntrinsics;
        this.maxLines = i;
        if (Constraints.m657getMinWidthimpl(j) != 0 || Constraints.m656getMinHeightimpl(j) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) multiParagraphIntrinsics.infoList;
        int size = arrayList2.size();
        float f = 0.0f;
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) arrayList2.get(i5);
            AndroidParagraphIntrinsics androidParagraphIntrinsics = paragraphIntrinsicInfo.intrinsics;
            int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
            if (Constraints.m650getHasBoundedHeightimpl(j)) {
                i3 = i5;
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j) - ((int) Math.ceil(f));
                if (m654getMaxHeightimpl < 0) {
                    m654getMaxHeightimpl = 0;
                }
            } else {
                i3 = i5;
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
            }
            AndroidParagraph androidParagraph = new AndroidParagraph(androidParagraphIntrinsics, this.maxLines - i6, i2, ConstraintsKt.Constraints$default(0, m655getMaxWidthimpl, 0, m654getMaxHeightimpl, 5));
            float height = androidParagraph.getHeight() + f;
            TextLayout textLayout = androidParagraph.layout;
            int i7 = i6 + textLayout.lineCount;
            arrayList.add(new ParagraphInfo(androidParagraph, paragraphIntrinsicInfo.startIndex, paragraphIntrinsicInfo.endIndex, i6, i7, f, height));
            if (!textLayout.didExceedMaxLines) {
                if (i7 == this.maxLines) {
                    i4 = i3;
                    if (i4 != CollectionsKt__CollectionsKt.getLastIndex(this.intrinsics.infoList)) {
                    }
                } else {
                    i4 = i3;
                }
                i5 = i4 + 1;
                f = height;
                i6 = i7;
            }
            z = true;
            f = height;
            i6 = i7;
            break;
        }
        z = false;
        this.height = f;
        this.lineCount = i6;
        this.didExceedMaxLines = z;
        this.paragraphInfoList = arrayList;
        this.width = Constraints.m655getMaxWidthimpl(j);
        ArrayList arrayList3 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i8 = 0; i8 < size2; i8++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i8);
            List list = paragraphInfo.paragraph.placeholderRects;
            ArrayList arrayList4 = new ArrayList(list.size());
            int size3 = list.size();
            for (int i9 = 0; i9 < size3; i9++) {
                Rect rect = (Rect) list.get(i9);
                arrayList4.add(rect != null ? paragraphInfo.toGlobal(rect) : null);
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList4, arrayList3);
        }
        int size4 = arrayList3.size();
        ArrayList arrayList5 = arrayList3;
        if (size4 < this.intrinsics.placeholders.size()) {
            int size5 = this.intrinsics.placeholders.size() - arrayList3.size();
            ArrayList arrayList6 = new ArrayList(size5);
            for (int i10 = 0; i10 < size5; i10++) {
                arrayList6.add(null);
            }
            arrayList5 = CollectionsKt.plus((Iterable) arrayList6, (Collection) arrayList3);
        }
        this.placeholderRects = arrayList5;
    }

    /* renamed from: paint-LG529CI$default, reason: not valid java name */
    public static void m585paintLG529CI$default(MultiParagraph multiParagraph, Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        canvas.save();
        ArrayList arrayList = (ArrayList) multiParagraph.paragraphInfoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
            paragraphInfo.paragraph.m583paintLG529CI(canvas, j, shadow, textDecoration, drawStyle);
            canvas.translate(0.0f, paragraphInfo.paragraph.getHeight());
        }
        canvas.restore();
    }

    /* renamed from: paint-hn5TExg$default, reason: not valid java name */
    public static void m586painthn5TExg$default(MultiParagraph multiParagraph, Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        canvas.save();
        if (((ArrayList) multiParagraph.paragraphInfoList).size() <= 1) {
            AndroidMultiParagraphDraw_androidKt.m624drawParagraphs7AXcY_I(multiParagraph, canvas, brush, f, shadow, textDecoration, drawStyle);
        } else if (brush instanceof SolidColor) {
            AndroidMultiParagraphDraw_androidKt.m624drawParagraphs7AXcY_I(multiParagraph, canvas, brush, f, shadow, textDecoration, drawStyle);
        } else if (brush instanceof ShaderBrush) {
            ArrayList arrayList = (ArrayList) multiParagraph.paragraphInfoList;
            int size = arrayList.size();
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (int i = 0; i < size; i++) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
                f3 += paragraphInfo.paragraph.getHeight();
                f2 = Math.max(f2, paragraphInfo.paragraph.getWidth());
            }
            Shader mo359createShaderuvyYCjk = ((ShaderBrush) brush).mo359createShaderuvyYCjk((Float.floatToRawIntBits(f2) << 32) | (Float.floatToRawIntBits(f3) & 4294967295L));
            Matrix matrix = new Matrix();
            mo359createShaderuvyYCjk.getLocalMatrix(matrix);
            ArrayList arrayList2 = (ArrayList) multiParagraph.paragraphInfoList;
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ParagraphInfo paragraphInfo2 = (ParagraphInfo) arrayList2.get(i2);
                paragraphInfo2.paragraph.m584painthn5TExg(canvas, new BrushKt$ShaderBrush$1(mo359createShaderuvyYCjk), f, shadow, textDecoration, drawStyle);
                AndroidParagraph androidParagraph = paragraphInfo2.paragraph;
                canvas.translate(0.0f, androidParagraph.getHeight());
                matrix.setTranslate(0.0f, -androidParagraph.getHeight());
                mo359createShaderuvyYCjk.setLocalMatrix(matrix);
            }
        }
        canvas.restore();
    }

    /* renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public final void m587fillBoundingBoxes8ffj60Q(final long j, final float[] fArr) {
        requireIndexInRange(TextRange.m601getMinimpl(j));
        requireIndexInRangeInclusiveEnd(TextRange.m600getMaximpl(j));
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = 0;
        final Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
        MultiParagraphKt.m590findParagraphsByRangeSbBc2M(this.paragraphInfoList, j, new Function1() { // from class: androidx.compose.ui.text.MultiParagraph$fillBoundingBoxes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) obj;
                long j2 = j;
                float[] fArr2 = fArr;
                Ref$IntRef ref$IntRef2 = ref$IntRef;
                Ref$FloatRef ref$FloatRef2 = ref$FloatRef;
                int m601getMinimpl = paragraphInfo.startIndex > TextRange.m601getMinimpl(j2) ? paragraphInfo.startIndex : TextRange.m601getMinimpl(j2);
                int m600getMaximpl = TextRange.m600getMaximpl(j2);
                int i = paragraphInfo.endIndex;
                if (i >= m600getMaximpl) {
                    i = TextRange.m600getMaximpl(j2);
                }
                long TextRange = TextRangeKt.TextRange(paragraphInfo.toLocalIndex(m601getMinimpl), paragraphInfo.toLocalIndex(i));
                int i2 = ref$IntRef2.element;
                AndroidParagraph androidParagraph = paragraphInfo.paragraph;
                androidParagraph.layout.fillBoundingBoxes(TextRange.m601getMinimpl(TextRange), TextRange.m600getMaximpl(TextRange), i2, fArr2);
                int m599getLengthimpl = (TextRange.m599getLengthimpl(TextRange) * 4) + ref$IntRef2.element;
                for (int i3 = ref$IntRef2.element; i3 < m599getLengthimpl; i3 += 4) {
                    int i4 = i3 + 1;
                    float f = fArr2[i4];
                    float f2 = ref$FloatRef2.element;
                    fArr2[i4] = f + f2;
                    int i5 = i3 + 3;
                    fArr2[i5] = fArr2[i5] + f2;
                }
                ref$IntRef2.element = m599getLengthimpl;
                ref$FloatRef2.element = androidParagraph.getHeight() + ref$FloatRef2.element;
                return Unit.INSTANCE;
            }
        });
    }

    public final float getLineBottom(int i) {
        requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, this.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        return androidParagraph.layout.getLineBottom(i - paragraphInfo.startLineIndex) + paragraphInfo.top;
    }

    public final int getLineForVerticalPosition(float f) {
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(MultiParagraphKt.findParagraphByY(this.paragraphInfoList, f));
        int i = paragraphInfo.endIndex - paragraphInfo.startIndex;
        int i2 = paragraphInfo.startLineIndex;
        if (i == 0) {
            return i2;
        }
        float f2 = f - paragraphInfo.top;
        TextLayout textLayout = paragraphInfo.paragraph.layout;
        return i2 + textLayout.layout.getLineForVertical(((int) f2) - textLayout.topPadding);
    }

    public final float getLineTop(int i) {
        requireLineIndexInRange(i);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(MultiParagraphKt.findParagraphByLineIndex(i, this.paragraphInfoList));
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        return androidParagraph.layout.getLineTop(i - paragraphInfo.startLineIndex) + paragraphInfo.top;
    }

    /* renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m588getOffsetForPositionk4lQ0M(long j) {
        int i = (int) (j & 4294967295L);
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(MultiParagraphKt.findParagraphByY(this.paragraphInfoList, Float.intBitsToFloat(i)));
        int i2 = paragraphInfo.endIndex;
        int i3 = paragraphInfo.startIndex;
        if (i2 - i3 == 0) {
            return i3;
        }
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat(i) - paragraphInfo.top;
        long floatToRawIntBits = (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32);
        AndroidParagraph androidParagraph = paragraphInfo.paragraph;
        int intBitsToFloat3 = (int) Float.intBitsToFloat((int) (4294967295L & floatToRawIntBits));
        TextLayout textLayout = androidParagraph.layout;
        int lineForVertical = textLayout.layout.getLineForVertical(intBitsToFloat3 - textLayout.topPadding);
        return i3 + textLayout.layout.getOffsetForHorizontal(lineForVertical, (textLayout.getHorizontalPadding(lineForVertical) * (-1)) + Float.intBitsToFloat((int) (floatToRawIntBits >> 32)));
    }

    /* renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public final long m589getRangeForRect86BmAI(Rect rect, int i, TextInclusionStrategy textInclusionStrategy) {
        long j;
        long j2;
        int findParagraphByY = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, rect.top);
        float f = ((ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(findParagraphByY)).bottom;
        float f2 = rect.bottom;
        if (f >= f2 || findParagraphByY == CollectionsKt__CollectionsKt.getLastIndex(this.paragraphInfoList)) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(findParagraphByY);
            return paragraphInfo.m591toGlobalxdX6G0(paragraphInfo.paragraph.m582getRangeForRect86BmAI(paragraphInfo.toLocal(rect), i, textInclusionStrategy), true);
        }
        int findParagraphByY2 = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, f2);
        long j3 = TextRange.Zero;
        while (true) {
            j = TextRange.Zero;
            if (!TextRange.m597equalsimpl0(j3, j) || findParagraphByY > findParagraphByY2) {
                break;
            }
            ParagraphInfo paragraphInfo2 = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(findParagraphByY);
            j3 = paragraphInfo2.m591toGlobalxdX6G0(paragraphInfo2.paragraph.m582getRangeForRect86BmAI(paragraphInfo2.toLocal(rect), i, textInclusionStrategy), true);
            findParagraphByY++;
        }
        if (TextRange.m597equalsimpl0(j3, j)) {
            return j;
        }
        while (true) {
            j2 = TextRange.Zero;
            if (!TextRange.m597equalsimpl0(j, j2) || findParagraphByY > findParagraphByY2) {
                break;
            }
            ParagraphInfo paragraphInfo3 = (ParagraphInfo) ((ArrayList) this.paragraphInfoList).get(findParagraphByY2);
            j = paragraphInfo3.m591toGlobalxdX6G0(paragraphInfo3.paragraph.m582getRangeForRect86BmAI(paragraphInfo3.toLocal(rect), i, textInclusionStrategy), true);
            findParagraphByY2--;
        }
        return TextRange.m597equalsimpl0(j, j2) ? j3 : TextRangeKt.TextRange((int) (j3 >> 32), (int) (4294967295L & j));
    }

    public final void requireIndexInRange(int i) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.intrinsics;
        if (i < 0 || i >= multiParagraphIntrinsics.annotatedString.text.length()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("offset(", ") is out of bounds [0, ", i);
            m.append(multiParagraphIntrinsics.annotatedString.text.length());
            m.append(')');
            throw new IllegalArgumentException(m.toString().toString());
        }
    }

    public final void requireIndexInRangeInclusiveEnd(int i) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.intrinsics;
        if (i < 0 || i > multiParagraphIntrinsics.annotatedString.text.length()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("offset(", ") is out of bounds [0, ", i);
            m.append(multiParagraphIntrinsics.annotatedString.text.length());
            m.append(']');
            throw new IllegalArgumentException(m.toString().toString());
        }
    }

    public final void requireLineIndexInRange(int i) {
        int i2 = this.lineCount;
        if (i < 0 || i >= i2) {
            throw new IllegalArgumentException(("lineIndex(" + i + ") is out of bounds [0, " + i2 + ')').toString());
        }
    }
}
