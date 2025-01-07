package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseTextPreparedSelection {
    public final AnnotatedString annotatedString;
    public final TextLayoutResult layoutResult;
    public final OffsetMapping offsetMapping;
    public final long originalSelection;
    public final AnnotatedString originalText;
    public long selection;
    public final TextPreparedSelectionState state;

    public BaseTextPreparedSelection(AnnotatedString annotatedString, long j, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, TextPreparedSelectionState textPreparedSelectionState) {
        this.originalText = annotatedString;
        this.originalSelection = j;
        this.layoutResult = textLayoutResult;
        this.offsetMapping = offsetMapping;
        this.state = textPreparedSelectionState;
        this.selection = j;
        this.annotatedString = annotatedString;
    }

    public final Integer getLineEndByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            return null;
        }
        int m600getMaximpl = TextRange.m600getMaximpl(this.selection);
        OffsetMapping offsetMapping = this.offsetMapping;
        return Integer.valueOf(offsetMapping.transformedToOriginal(textLayoutResult.getLineEnd(textLayoutResult.getLineForOffset(offsetMapping.originalToTransformed(m600getMaximpl)), true)));
    }

    public final Integer getLineStartByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            return null;
        }
        int m601getMinimpl = TextRange.m601getMinimpl(this.selection);
        OffsetMapping offsetMapping = this.offsetMapping;
        return Integer.valueOf(offsetMapping.transformedToOriginal(textLayoutResult.getLineStart(textLayoutResult.getLineForOffset(offsetMapping.originalToTransformed(m601getMinimpl)))));
    }

    public final Integer getNextWordOffset() {
        int length;
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            return null;
        }
        int transformedEndOffset = transformedEndOffset();
        while (true) {
            AnnotatedString annotatedString = this.originalText;
            if (transformedEndOffset < annotatedString.text.length()) {
                int length2 = this.annotatedString.text.length() - 1;
                if (transformedEndOffset <= length2) {
                    length2 = transformedEndOffset;
                }
                long m596getWordBoundaryjx7JFs = textLayoutResult.m596getWordBoundaryjx7JFs(length2);
                int i = TextRange.$r8$clinit;
                int i2 = (int) (m596getWordBoundaryjx7JFs & 4294967295L);
                if (i2 > transformedEndOffset) {
                    length = this.offsetMapping.transformedToOriginal(i2);
                    break;
                }
                transformedEndOffset++;
            } else {
                length = annotatedString.text.length();
                break;
            }
        }
        return Integer.valueOf(length);
    }

    public final Integer getPreviousWordOffset() {
        int i;
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult == null) {
            return null;
        }
        int transformedEndOffset = transformedEndOffset();
        while (true) {
            if (transformedEndOffset <= 0) {
                i = 0;
                break;
            }
            int length = this.annotatedString.text.length() - 1;
            if (transformedEndOffset <= length) {
                length = transformedEndOffset;
            }
            long m596getWordBoundaryjx7JFs = textLayoutResult.m596getWordBoundaryjx7JFs(length);
            int i2 = TextRange.$r8$clinit;
            int i3 = (int) (m596getWordBoundaryjx7JFs >> 32);
            if (i3 < transformedEndOffset) {
                i = this.offsetMapping.transformedToOriginal(i3);
                break;
            }
            transformedEndOffset--;
        }
        return Integer.valueOf(i);
    }

    public final boolean isLtr() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        return (textLayoutResult != null ? textLayoutResult.getParagraphDirection(transformedEndOffset()) : null) != ResolvedTextDirection.Rtl;
    }

    public final int jumpByLinesOffset(TextLayoutResult textLayoutResult, int i) {
        int transformedEndOffset = transformedEndOffset();
        TextPreparedSelectionState textPreparedSelectionState = this.state;
        if (textPreparedSelectionState.cachedX == null) {
            textPreparedSelectionState.cachedX = Float.valueOf(textLayoutResult.getCursorRect(transformedEndOffset).left);
        }
        int lineForOffset = textLayoutResult.getLineForOffset(transformedEndOffset) + i;
        if (lineForOffset < 0) {
            return 0;
        }
        MultiParagraph multiParagraph = textLayoutResult.multiParagraph;
        if (lineForOffset >= multiParagraph.lineCount) {
            return this.annotatedString.text.length();
        }
        float lineBottom = multiParagraph.getLineBottom(lineForOffset) - 1;
        Float f = textPreparedSelectionState.cachedX;
        Intrinsics.checkNotNull(f);
        float floatValue = f.floatValue();
        if ((isLtr() && floatValue >= textLayoutResult.getLineRight(lineForOffset)) || (!isLtr() && floatValue <= textLayoutResult.getLineLeft(lineForOffset))) {
            return textLayoutResult.getLineEnd(lineForOffset, true);
        }
        return this.offsetMapping.transformedToOriginal(multiParagraph.m588getOffsetForPositionk4lQ0M((Float.floatToRawIntBits(f.floatValue()) << 32) | (Float.floatToRawIntBits(lineBottom) & 4294967295L)));
    }

    public final void moveCursorNextByParagraph() {
        this.state.cachedX = null;
        AnnotatedString annotatedString = this.annotatedString;
        if (annotatedString.text.length() > 0) {
            int findParagraphEnd = StringHelpersKt.findParagraphEnd(TextRange.m600getMaximpl(this.selection), annotatedString.text);
            if (findParagraphEnd == TextRange.m600getMaximpl(this.selection) && findParagraphEnd != annotatedString.text.length()) {
                findParagraphEnd = StringHelpersKt.findParagraphEnd(findParagraphEnd + 1, annotatedString.text);
            }
            setSelection(findParagraphEnd, findParagraphEnd);
        }
    }

    public final void moveCursorPrevByParagraph() {
        this.state.cachedX = null;
        AnnotatedString annotatedString = this.annotatedString;
        if (annotatedString.text.length() > 0) {
            int findParagraphStart = StringHelpersKt.findParagraphStart(TextRange.m601getMinimpl(this.selection), annotatedString.text);
            if (findParagraphStart == TextRange.m601getMinimpl(this.selection) && findParagraphStart != 0) {
                findParagraphStart = StringHelpersKt.findParagraphStart(findParagraphStart - 1, annotatedString.text);
            }
            setSelection(findParagraphStart, findParagraphStart);
        }
    }

    public final void moveCursorToLineEnd() {
        Integer lineEndByOffset;
        this.state.cachedX = null;
        if (this.annotatedString.text.length() <= 0 || (lineEndByOffset = getLineEndByOffset()) == null) {
            return;
        }
        int intValue = lineEndByOffset.intValue();
        setSelection(intValue, intValue);
    }

    public final void moveCursorToLineStart() {
        Integer lineStartByOffset;
        this.state.cachedX = null;
        if (this.annotatedString.text.length() <= 0 || (lineStartByOffset = getLineStartByOffset()) == null) {
            return;
        }
        int intValue = lineStartByOffset.intValue();
        setSelection(intValue, intValue);
    }

    public final void selectMovement() {
        if (this.annotatedString.text.length() > 0) {
            int i = TextRange.$r8$clinit;
            this.selection = TextRangeKt.TextRange((int) (this.originalSelection >> 32), (int) (this.selection & 4294967295L));
        }
    }

    public final void setSelection(int i, int i2) {
        this.selection = TextRangeKt.TextRange(i, i2);
    }

    public final int transformedEndOffset() {
        long j = this.selection;
        int i = TextRange.$r8$clinit;
        return this.offsetMapping.originalToTransformed((int) (j & 4294967295L));
    }
}
