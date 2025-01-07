package androidx.compose.ui.text.input;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextFieldValue {
    public final AnnotatedString annotatedString;
    public final TextRange composition;
    public final long selection;

    static {
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
    }

    public TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange) {
        TextRange textRange2;
        this.annotatedString = annotatedString;
        int length = annotatedString.text.length();
        int i = TextRange.$r8$clinit;
        int i2 = (int) (j >> 32);
        int coerceIn = RangesKt.coerceIn(i2, 0, length);
        int i3 = (int) (j & 4294967295L);
        int coerceIn2 = RangesKt.coerceIn(i3, 0, length);
        this.selection = (coerceIn == i2 && coerceIn2 == i3) ? j : TextRangeKt.TextRange(coerceIn, coerceIn2);
        if (textRange != null) {
            int length2 = annotatedString.text.length();
            long j2 = textRange.packedValue;
            int i4 = (int) (j2 >> 32);
            int coerceIn3 = RangesKt.coerceIn(i4, 0, length2);
            int i5 = (int) (j2 & 4294967295L);
            int coerceIn4 = RangesKt.coerceIn(i5, 0, length2);
            textRange2 = new TextRange((coerceIn3 == i4 && coerceIn4 == i5) ? j2 : TextRangeKt.TextRange(coerceIn3, coerceIn4));
        } else {
            textRange2 = null;
        }
        this.composition = textRange2;
    }

    /* renamed from: copy-3r_uNRQ$default, reason: not valid java name */
    public static TextFieldValue m623copy3r_uNRQ$default(TextFieldValue textFieldValue, AnnotatedString annotatedString, long j, int i) {
        if ((i & 1) != 0) {
            annotatedString = textFieldValue.annotatedString;
        }
        if ((i & 2) != 0) {
            j = textFieldValue.selection;
        }
        TextRange textRange = (i & 4) != 0 ? textFieldValue.composition : null;
        textFieldValue.getClass();
        return new TextFieldValue(annotatedString, j, textRange);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextFieldValue)) {
            return false;
        }
        TextFieldValue textFieldValue = (TextFieldValue) obj;
        return TextRange.m597equalsimpl0(this.selection, textFieldValue.selection) && Intrinsics.areEqual(this.composition, textFieldValue.composition) && Intrinsics.areEqual(this.annotatedString, textFieldValue.annotatedString);
    }

    public final int hashCode() {
        int hashCode = this.annotatedString.hashCode() * 31;
        int i = TextRange.$r8$clinit;
        int m = Scale$$ExternalSyntheticOutline0.m(hashCode, 31, this.selection);
        TextRange textRange = this.composition;
        return m + (textRange != null ? Long.hashCode(textRange.packedValue) : 0);
    }

    public final String toString() {
        return "TextFieldValue(text='" + ((Object) this.annotatedString) + "', selection=" + ((Object) TextRange.m603toStringimpl(this.selection)) + ", composition=" + this.composition + ')';
    }

    public TextFieldValue(int i, long j, String str) {
        this(new AnnotatedString((i & 1) != 0 ? "" : str), (i & 2) != 0 ? TextRange.Zero : j, (TextRange) null);
    }
}
