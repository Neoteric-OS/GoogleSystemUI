package androidx.compose.foundation.text.input.internal;

import android.graphics.PointF;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HandwritingGesture_androidKt {
    /* renamed from: access$getOffsetForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m172access$getOffsetForHandwritingGestured4ec7I(LegacyTextFieldState legacyTextFieldState, long j, ViewConfiguration viewConfiguration) {
        TextLayoutResult textLayoutResult;
        long mo487screenToLocalMKHz9U;
        int m174getLineForHandwritingGestured4ec7I;
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (layoutResult == null || (textLayoutResult = layoutResult.value) == null) {
            return -1;
        }
        MultiParagraph multiParagraph = textLayoutResult.multiParagraph;
        LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
        if (layoutCoordinates == null || (m174getLineForHandwritingGestured4ec7I = m174getLineForHandwritingGestured4ec7I(multiParagraph, (mo487screenToLocalMKHz9U = layoutCoordinates.mo487screenToLocalMKHz9U(j)), viewConfiguration)) == -1) {
            return -1;
        }
        return multiParagraph.m588getOffsetForPositionk4lQ0M(Offset.m308copydBAh8RU$default((multiParagraph.getLineBottom(m174getLineForHandwritingGestured4ec7I) + multiParagraph.getLineTop(m174getLineForHandwritingGestured4ec7I)) / 2.0f, 1, mo487screenToLocalMKHz9U));
    }

    /* renamed from: access$getRangeForScreenRects-O048IG0, reason: not valid java name */
    public static final long m173access$getRangeForScreenRectsO048IG0(LegacyTextFieldState legacyTextFieldState, Rect rect, Rect rect2, int i) {
        long m175getRangeForScreenRectOH9lIzo = m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, rect, i);
        if (TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo)) {
            return TextRange.Zero;
        }
        long m175getRangeForScreenRectOH9lIzo2 = m175getRangeForScreenRectOH9lIzo(legacyTextFieldState, rect2, i);
        if (TextRange.m598getCollapsedimpl(m175getRangeForScreenRectOH9lIzo2)) {
            return TextRange.Zero;
        }
        int i2 = (int) (m175getRangeForScreenRectOH9lIzo >> 32);
        int i3 = (int) (m175getRangeForScreenRectOH9lIzo2 & 4294967295L);
        return TextRangeKt.TextRange(Math.min(i2, i2), Math.max(i3, i3));
    }

    public static final boolean access$isBiDiBoundary(TextLayoutResult textLayoutResult, int i) {
        int lineForOffset = textLayoutResult.getLineForOffset(i);
        if (i == textLayoutResult.getLineStart(lineForOffset) || i == textLayoutResult.getLineEnd(lineForOffset, false)) {
            if (textLayoutResult.getParagraphDirection(i) == textLayoutResult.getBidiRunDirection(i)) {
                return false;
            }
        } else if (textLayoutResult.getBidiRunDirection(i) == textLayoutResult.getBidiRunDirection(i - 1)) {
            return false;
        }
        return true;
    }

    public static final long access$toOffset(PointF pointF) {
        float f = pointF.x;
        float f2 = pointF.y;
        return (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(f2) & 4294967295L);
    }

    /* renamed from: getLineForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m174getLineForHandwritingGestured4ec7I(MultiParagraph multiParagraph, long j, ViewConfiguration viewConfiguration) {
        float handwritingGestureLineMargin = viewConfiguration != null ? viewConfiguration.getHandwritingGestureLineMargin() : 0.0f;
        int i = (int) (4294967295L & j);
        int lineForVerticalPosition = multiParagraph.getLineForVerticalPosition(Float.intBitsToFloat(i));
        if (Float.intBitsToFloat(i) >= multiParagraph.getLineTop(lineForVerticalPosition) - handwritingGestureLineMargin && Float.intBitsToFloat(i) <= multiParagraph.getLineBottom(lineForVerticalPosition) + handwritingGestureLineMargin) {
            int i2 = (int) (j >> 32);
            if (Float.intBitsToFloat(i2) >= (-handwritingGestureLineMargin) && Float.intBitsToFloat(i2) <= multiParagraph.width + handwritingGestureLineMargin) {
                return lineForVerticalPosition;
            }
        }
        return -1;
    }

    /* renamed from: getRangeForScreenRect-OH9lIzo, reason: not valid java name */
    public static final long m175getRangeForScreenRectOH9lIzo(LegacyTextFieldState legacyTextFieldState, Rect rect, int i) {
        TextLayoutResult textLayoutResult;
        TextInclusionStrategy$Companion$$ExternalSyntheticLambda0 textInclusionStrategy$Companion$$ExternalSyntheticLambda0 = TextInclusionStrategy.Companion.ContainsCenter;
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        MultiParagraph multiParagraph = (layoutResult == null || (textLayoutResult = layoutResult.value) == null) ? null : textLayoutResult.multiParagraph;
        LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
        return (multiParagraph == null || layoutCoordinates == null) ? TextRange.Zero : multiParagraph.m589getRangeForRect86BmAI(rect.m323translatek4lQ0M(layoutCoordinates.mo487screenToLocalMKHz9U(0L)), i, textInclusionStrategy$Companion$$ExternalSyntheticLambda0);
    }

    public static final boolean isPunctuation(int i) {
        int type = Character.getType(i);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    public static final boolean isWhitespace(int i) {
        return Character.isWhitespace(i) || i == 160;
    }

    public static final boolean isWhitespaceExceptNewline(int i) {
        int type;
        return (!isWhitespace(i) || (type = Character.getType(i)) == 14 || type == 13 || i == 10) ? false : true;
    }
}
