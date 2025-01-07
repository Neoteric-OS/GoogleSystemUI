package androidx.compose.ui.text.input;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SetComposingTextCommand implements EditCommand {
    public final AnnotatedString annotatedString;
    public final int newCursorPosition;

    public SetComposingTextCommand(String str, int i) {
        this.annotatedString = new AnnotatedString(str);
        this.newCursorPosition = i;
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public final void applyTo(EditingBuffer editingBuffer) {
        int i = editingBuffer.compositionStart;
        boolean z = i != -1;
        AnnotatedString annotatedString = this.annotatedString;
        if (z) {
            editingBuffer.replace$ui_text_release(annotatedString.text, i, editingBuffer.compositionEnd);
            if (annotatedString.text.length() > 0) {
                editingBuffer.setComposition$ui_text_release(i, annotatedString.text.length() + i);
            }
        } else {
            int i2 = editingBuffer.selectionStart;
            editingBuffer.replace$ui_text_release(annotatedString.text, i2, editingBuffer.selectionEnd);
            if (annotatedString.text.length() > 0) {
                editingBuffer.setComposition$ui_text_release(i2, annotatedString.text.length() + i2);
            }
        }
        int i3 = editingBuffer.selectionStart;
        int i4 = editingBuffer.selectionEnd;
        int i5 = i3 == i4 ? i4 : -1;
        int i6 = this.newCursorPosition;
        int coerceIn = RangesKt.coerceIn(i6 > 0 ? (i5 + i6) - 1 : (i5 + i6) - annotatedString.text.length(), 0, editingBuffer.gapBuffer.getLength());
        editingBuffer.setSelection$ui_text_release(coerceIn, coerceIn);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetComposingTextCommand)) {
            return false;
        }
        SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) obj;
        return Intrinsics.areEqual(this.annotatedString.text, setComposingTextCommand.annotatedString.text) && this.newCursorPosition == setComposingTextCommand.newCursorPosition;
    }

    public final int hashCode() {
        return (this.annotatedString.text.hashCode() * 31) + this.newCursorPosition;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingTextCommand(text='");
        sb.append(this.annotatedString.text);
        sb.append("', newCursorPosition=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.newCursorPosition, ')');
    }
}
