package androidx.compose.ui.text.input;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommitTextCommand implements EditCommand {
    public final AnnotatedString annotatedString;
    public final int newCursorPosition;

    public CommitTextCommand(AnnotatedString annotatedString, int i) {
        this.annotatedString = annotatedString;
        this.newCursorPosition = i;
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public final void applyTo(EditingBuffer editingBuffer) {
        int i = editingBuffer.compositionStart;
        boolean z = i != -1;
        AnnotatedString annotatedString = this.annotatedString;
        if (z) {
            editingBuffer.replace$ui_text_release(annotatedString.text, i, editingBuffer.compositionEnd);
        } else {
            editingBuffer.replace$ui_text_release(annotatedString.text, editingBuffer.selectionStart, editingBuffer.selectionEnd);
        }
        int i2 = editingBuffer.selectionStart;
        int i3 = editingBuffer.selectionEnd;
        int i4 = i2 == i3 ? i3 : -1;
        int i5 = this.newCursorPosition;
        int coerceIn = RangesKt.coerceIn(i5 > 0 ? (i4 + i5) - 1 : (i4 + i5) - annotatedString.text.length(), 0, editingBuffer.gapBuffer.getLength());
        editingBuffer.setSelection$ui_text_release(coerceIn, coerceIn);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommitTextCommand)) {
            return false;
        }
        CommitTextCommand commitTextCommand = (CommitTextCommand) obj;
        return Intrinsics.areEqual(this.annotatedString.text, commitTextCommand.annotatedString.text) && this.newCursorPosition == commitTextCommand.newCursorPosition;
    }

    public final int hashCode() {
        return (this.annotatedString.text.hashCode() * 31) + this.newCursorPosition;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CommitTextCommand(text='");
        sb.append(this.annotatedString.text);
        sb.append("', newCursorPosition=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.newCursorPosition, ')');
    }

    public CommitTextCommand(String str, int i) {
        this(new AnnotatedString(str), i);
    }
}
