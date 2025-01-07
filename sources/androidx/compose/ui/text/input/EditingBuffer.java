package androidx.compose.ui.text.input;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditingBuffer {
    public int compositionEnd;
    public int compositionStart;
    public final PartialGapBuffer gapBuffer;
    public int selectionEnd;
    public int selectionStart;

    public EditingBuffer(AnnotatedString annotatedString, long j) {
        String str = annotatedString.text;
        PartialGapBuffer partialGapBuffer = new PartialGapBuffer();
        partialGapBuffer.text = str;
        partialGapBuffer.bufStart = -1;
        partialGapBuffer.bufEnd = -1;
        this.gapBuffer = partialGapBuffer;
        this.selectionStart = TextRange.m601getMinimpl(j);
        this.selectionEnd = TextRange.m600getMaximpl(j);
        this.compositionStart = -1;
        this.compositionEnd = -1;
        int m601getMinimpl = TextRange.m601getMinimpl(j);
        int m600getMaximpl = TextRange.m600getMaximpl(j);
        if (m601getMinimpl < 0 || m601getMinimpl > annotatedString.text.length()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("start (", ") offset is outside of text region ", m601getMinimpl);
            m.append(annotatedString.text.length());
            throw new IndexOutOfBoundsException(m.toString());
        }
        if (m600getMaximpl < 0 || m600getMaximpl > annotatedString.text.length()) {
            StringBuilder m2 = MutableObjectList$$ExternalSyntheticOutline0.m("end (", ") offset is outside of text region ", m600getMaximpl);
            m2.append(annotatedString.text.length());
            throw new IndexOutOfBoundsException(m2.toString());
        }
        if (m601getMinimpl > m600getMaximpl) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Do not set reversed range: ", m601getMinimpl, m600getMaximpl, " > "));
        }
    }

    public final void delete$ui_text_release(int i, int i2) {
        long TextRange = TextRangeKt.TextRange(i, i2);
        this.gapBuffer.replace("", i, i2);
        long m616updateRangeAfterDeletepWDy79M = EditingBufferKt.m616updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.selectionStart, this.selectionEnd), TextRange);
        setSelectionStart(TextRange.m601getMinimpl(m616updateRangeAfterDeletepWDy79M));
        setSelectionEnd(TextRange.m600getMaximpl(m616updateRangeAfterDeletepWDy79M));
        int i3 = this.compositionStart;
        if (i3 != -1) {
            long m616updateRangeAfterDeletepWDy79M2 = EditingBufferKt.m616updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(i3, this.compositionEnd), TextRange);
            if (TextRange.m598getCollapsedimpl(m616updateRangeAfterDeletepWDy79M2)) {
                this.compositionStart = -1;
                this.compositionEnd = -1;
            } else {
                this.compositionStart = TextRange.m601getMinimpl(m616updateRangeAfterDeletepWDy79M2);
                this.compositionEnd = TextRange.m600getMaximpl(m616updateRangeAfterDeletepWDy79M2);
            }
        }
    }

    public final char get$ui_text_release(int i) {
        PartialGapBuffer partialGapBuffer = this.gapBuffer;
        GapBuffer gapBuffer = partialGapBuffer.buffer;
        if (gapBuffer == null) {
            return partialGapBuffer.text.charAt(i);
        }
        if (i < partialGapBuffer.bufStart) {
            return partialGapBuffer.text.charAt(i);
        }
        int gapLength = gapBuffer.capacity - gapBuffer.gapLength();
        int i2 = partialGapBuffer.bufStart;
        if (i >= gapLength + i2) {
            return partialGapBuffer.text.charAt(i - ((gapLength - partialGapBuffer.bufEnd) + i2));
        }
        int i3 = i - i2;
        int i4 = gapBuffer.gapStart;
        return i3 < i4 ? gapBuffer.buffer[i3] : gapBuffer.buffer[(i3 - i4) + gapBuffer.gapEnd];
    }

    /* renamed from: getComposition-MzsxiRA$ui_text_release, reason: not valid java name */
    public final TextRange m615getCompositionMzsxiRA$ui_text_release() {
        int i = this.compositionStart;
        if (i != -1) {
            return new TextRange(TextRangeKt.TextRange(i, this.compositionEnd));
        }
        return null;
    }

    public final void replace$ui_text_release(String str, int i, int i2) {
        PartialGapBuffer partialGapBuffer = this.gapBuffer;
        if (i < 0 || i > partialGapBuffer.getLength()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("start (", ") offset is outside of text region ", i);
            m.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m.toString());
        }
        if (i2 < 0 || i2 > partialGapBuffer.getLength()) {
            StringBuilder m2 = MutableObjectList$$ExternalSyntheticOutline0.m("end (", ") offset is outside of text region ", i2);
            m2.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m2.toString());
        }
        if (i > i2) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Do not set reversed range: ", i, i2, " > "));
        }
        partialGapBuffer.replace(str, i, i2);
        setSelectionStart(str.length() + i);
        setSelectionEnd(str.length() + i);
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void setComposition$ui_text_release(int i, int i2) {
        PartialGapBuffer partialGapBuffer = this.gapBuffer;
        if (i < 0 || i > partialGapBuffer.getLength()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("start (", ") offset is outside of text region ", i);
            m.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m.toString());
        }
        if (i2 < 0 || i2 > partialGapBuffer.getLength()) {
            StringBuilder m2 = MutableObjectList$$ExternalSyntheticOutline0.m("end (", ") offset is outside of text region ", i2);
            m2.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m2.toString());
        }
        if (i >= i2) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Do not set reversed or empty range: ", i, i2, " > "));
        }
        this.compositionStart = i;
        this.compositionEnd = i2;
    }

    public final void setSelection$ui_text_release(int i, int i2) {
        PartialGapBuffer partialGapBuffer = this.gapBuffer;
        if (i < 0 || i > partialGapBuffer.getLength()) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("start (", ") offset is outside of text region ", i);
            m.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m.toString());
        }
        if (i2 < 0 || i2 > partialGapBuffer.getLength()) {
            StringBuilder m2 = MutableObjectList$$ExternalSyntheticOutline0.m("end (", ") offset is outside of text region ", i2);
            m2.append(partialGapBuffer.getLength());
            throw new IndexOutOfBoundsException(m2.toString());
        }
        if (i > i2) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Do not set reversed range: ", i, i2, " > "));
        }
        setSelectionStart(i);
        setSelectionEnd(i2);
    }

    public final void setSelectionEnd(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Cannot set selectionEnd to a negative value: ").toString());
        }
        this.selectionEnd = i;
    }

    public final void setSelectionStart(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Cannot set selectionStart to a negative value: ").toString());
        }
        this.selectionStart = i;
    }

    public final String toString() {
        return this.gapBuffer.toString();
    }
}
