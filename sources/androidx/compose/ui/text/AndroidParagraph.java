package androidx.compose.ui.text;

import android.graphics.RectF;
import android.text.GraphemeClusterSegmentFinder;
import android.text.Layout;
import android.text.SegmentFinder;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextAndroidCanvas;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.TextLayout_androidKt;
import androidx.compose.ui.text.android.selection.WordSegmentFinder;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidParagraph {
    public final CharSequence charSequence;
    public final long constraints;
    public final TextLayout layout;
    public final int maxLines;
    public final AndroidParagraphIntrinsics paragraphIntrinsics;
    public final List placeholderRects;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:146:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02c9  */
    /* JADX WARN: Type inference failed for: r10v19, types: [androidx.compose.ui.text.android.TextLayout] */
    /* JADX WARN: Type inference failed for: r10v24, types: [androidx.compose.ui.text.android.TextLayout] */
    /* JADX WARN: Type inference failed for: r2v33, types: [android.text.Spannable] */
    /* JADX WARN: Type inference failed for: r3v22, types: [android.text.Spanned] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AndroidParagraph(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics r32, int r33, int r34, long r35) {
        /*
            Method dump skipped, instructions count: 928
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.AndroidParagraph.<init>(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics, int, int, long):void");
    }

    public final float getHeight() {
        return this.layout.getHeight();
    }

    /* renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public final long m582getRangeForRect86BmAI(Rect rect, int i, final TextInclusionStrategy textInclusionStrategy) {
        SegmentFinder graphemeClusterSegmentFinder;
        RectF androidRectF = RectHelper_androidKt.toAndroidRectF(rect);
        boolean z = !(i == 0) && i == 1;
        final Function2 function2 = new Function2() { // from class: androidx.compose.ui.text.AndroidParagraph$getRangeForRect$range$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(TextInclusionStrategy.this.isIncluded(RectHelper_androidKt.toComposeRect((RectF) obj), RectHelper_androidKt.toComposeRect((RectF) obj2)));
            }
        };
        TextLayout textLayout = this.layout;
        if (z) {
            final WordSegmentFinder wordSegmentFinder = new WordSegmentFinder(textLayout.layout.getText(), textLayout.getWordIterator());
            graphemeClusterSegmentFinder = new SegmentFinder() { // from class: androidx.compose.ui.text.android.selection.Api34SegmentFinder$toAndroidSegmentFinder$1
                @Override // android.text.SegmentFinder
                public final int nextEndBoundary(int i2) {
                    WordSegmentFinder wordSegmentFinder2 = WordSegmentFinder.this;
                    do {
                        i2 = wordSegmentFinder2.wordIterator.nextBoundary(i2);
                        if (i2 == -1) {
                            return -1;
                        }
                    } while (Character.isWhitespace(wordSegmentFinder2.text.charAt(i2 - 1)));
                    return i2;
                }

                @Override // android.text.SegmentFinder
                public final int nextStartBoundary(int i2) {
                    WordSegmentFinder wordSegmentFinder2 = WordSegmentFinder.this;
                    do {
                        i2 = wordSegmentFinder2.wordIterator.nextBoundary(i2);
                        if (i2 == -1 || i2 == wordSegmentFinder2.text.length()) {
                            return -1;
                        }
                    } while (Character.isWhitespace(wordSegmentFinder2.text.charAt(i2)));
                    return i2;
                }

                @Override // android.text.SegmentFinder
                public final int previousEndBoundary(int i2) {
                    WordSegmentFinder wordSegmentFinder2 = WordSegmentFinder.this;
                    do {
                        i2 = wordSegmentFinder2.wordIterator.prevBoundary(i2);
                        if (i2 == -1 || i2 == 0) {
                            return -1;
                        }
                    } while (Character.isWhitespace(wordSegmentFinder2.text.charAt(i2 - 1)));
                    return i2;
                }

                @Override // android.text.SegmentFinder
                public final int previousStartBoundary(int i2) {
                    WordSegmentFinder wordSegmentFinder2 = WordSegmentFinder.this;
                    do {
                        i2 = wordSegmentFinder2.wordIterator.prevBoundary(i2);
                        if (i2 == -1) {
                            return -1;
                        }
                    } while (Character.isWhitespace(wordSegmentFinder2.text.charAt(i2)));
                    return i2;
                }
            };
        } else {
            textLayout.getClass();
            graphemeClusterSegmentFinder = new GraphemeClusterSegmentFinder(textLayout.layout.getText(), textLayout.textPaint);
        }
        int[] rangeForRect = textLayout.layout.getRangeForRect(androidRectF, graphemeClusterSegmentFinder, new Layout.TextInclusionStrategy() { // from class: androidx.compose.ui.text.android.AndroidLayoutApi34$$ExternalSyntheticLambda0
            @Override // android.text.Layout.TextInclusionStrategy
            public final boolean isSegmentInside(RectF rectF, RectF rectF2) {
                return ((Boolean) Function2.this.invoke(rectF, rectF2)).booleanValue();
            }
        });
        return rangeForRect == null ? TextRange.Zero : TextRangeKt.TextRange(rangeForRect[0], rangeForRect[1]);
    }

    public final float getWidth() {
        return Constraints.m655getMaxWidthimpl(this.constraints);
    }

    public final void paint(Canvas canvas) {
        android.graphics.Canvas canvas2 = AndroidCanvas_androidKt.EmptyCanvas;
        android.graphics.Canvas canvas3 = ((AndroidCanvas) canvas).internalCanvas;
        TextLayout textLayout = this.layout;
        if (textLayout.didExceedMaxLines) {
            canvas3.save();
            canvas3.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        if (canvas3.getClipBounds(textLayout.rect)) {
            int i = textLayout.topPadding;
            if (i != 0) {
                canvas3.translate(0.0f, i);
            }
            TextAndroidCanvas textAndroidCanvas = TextLayout_androidKt.SharedTextAndroidCanvas;
            textAndroidCanvas.nativeCanvas = canvas3;
            textLayout.layout.draw(textAndroidCanvas);
            if (i != 0) {
                canvas3.translate(0.0f, (-1) * i);
            }
        }
        if (textLayout.didExceedMaxLines) {
            canvas3.restore();
        }
    }

    /* renamed from: paint-LG529CI, reason: not valid java name */
    public final void m583paintLG529CI(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        AndroidTextPaint androidTextPaint = this.paragraphIntrinsics.textPaint;
        int i = androidTextPaint.backingBlendMode;
        androidTextPaint.m627setColor8_81llA(j);
        androidTextPaint.setShadow(shadow);
        androidTextPaint.setTextDecoration(textDecoration);
        androidTextPaint.setDrawStyle(drawStyle);
        androidTextPaint.m625setBlendModes9anfk8(3);
        paint(canvas);
        androidTextPaint.m625setBlendModes9anfk8(i);
    }

    /* renamed from: paint-hn5TExg, reason: not valid java name */
    public final void m584painthn5TExg(Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        AndroidTextPaint androidTextPaint = this.paragraphIntrinsics.textPaint;
        int i = androidTextPaint.backingBlendMode;
        float width = getWidth();
        float height = getHeight();
        androidTextPaint.m626setBrush12SF9DM(brush, (Float.floatToRawIntBits(height) & 4294967295L) | (Float.floatToRawIntBits(width) << 32), f);
        androidTextPaint.setShadow(shadow);
        androidTextPaint.setTextDecoration(textDecoration);
        androidTextPaint.setDrawStyle(drawStyle);
        androidTextPaint.m625setBlendModes9anfk8(3);
        paint(canvas);
        androidTextPaint.m625setBlendModes9anfk8(i);
    }
}
