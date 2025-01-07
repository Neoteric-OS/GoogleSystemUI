package androidx.compose.ui.text.input;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeleteSurroundingTextCommand implements EditCommand {
    public final int lengthAfterCursor;
    public final int lengthBeforeCursor;

    public DeleteSurroundingTextCommand(int i, int i2) {
        this.lengthBeforeCursor = i;
        this.lengthAfterCursor = i2;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(MutableVectorKt$$ExternalSyntheticOutline0.m(i, i2, "Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were ", " and ", " respectively.").toString());
        }
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public final void applyTo(EditingBuffer editingBuffer) {
        int i = editingBuffer.selectionEnd;
        int i2 = this.lengthAfterCursor;
        int i3 = i + i2;
        int i4 = (i ^ i3) & (i2 ^ i3);
        PartialGapBuffer partialGapBuffer = editingBuffer.gapBuffer;
        if (i4 < 0) {
            i3 = partialGapBuffer.getLength();
        }
        editingBuffer.delete$ui_text_release(editingBuffer.selectionEnd, Math.min(i3, partialGapBuffer.getLength()));
        int i5 = editingBuffer.selectionStart;
        int i6 = this.lengthBeforeCursor;
        int i7 = i5 - i6;
        if (((i6 ^ i5) & (i5 ^ i7)) < 0) {
            i7 = 0;
        }
        editingBuffer.delete$ui_text_release(Math.max(0, i7), editingBuffer.selectionStart);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeleteSurroundingTextCommand)) {
            return false;
        }
        DeleteSurroundingTextCommand deleteSurroundingTextCommand = (DeleteSurroundingTextCommand) obj;
        return this.lengthBeforeCursor == deleteSurroundingTextCommand.lengthBeforeCursor && this.lengthAfterCursor == deleteSurroundingTextCommand.lengthAfterCursor;
    }

    public final int hashCode() {
        return (this.lengthBeforeCursor * 31) + this.lengthAfterCursor;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeleteSurroundingTextCommand(lengthBeforeCursor=");
        sb.append(this.lengthBeforeCursor);
        sb.append(", lengthAfterCursor=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.lengthAfterCursor, ')');
    }
}
