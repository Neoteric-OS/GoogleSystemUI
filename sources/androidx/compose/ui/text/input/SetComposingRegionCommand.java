package androidx.compose.ui.text.input;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SetComposingRegionCommand implements EditCommand {
    public final int end;
    public final int start;

    public SetComposingRegionCommand(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    @Override // androidx.compose.ui.text.input.EditCommand
    public final void applyTo(EditingBuffer editingBuffer) {
        if (editingBuffer.compositionStart != -1) {
            editingBuffer.compositionStart = -1;
            editingBuffer.compositionEnd = -1;
        }
        PartialGapBuffer partialGapBuffer = editingBuffer.gapBuffer;
        int coerceIn = RangesKt.coerceIn(this.start, 0, partialGapBuffer.getLength());
        int coerceIn2 = RangesKt.coerceIn(this.end, 0, partialGapBuffer.getLength());
        if (coerceIn != coerceIn2) {
            if (coerceIn < coerceIn2) {
                editingBuffer.setComposition$ui_text_release(coerceIn, coerceIn2);
            } else {
                editingBuffer.setComposition$ui_text_release(coerceIn2, coerceIn);
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SetComposingRegionCommand)) {
            return false;
        }
        SetComposingRegionCommand setComposingRegionCommand = (SetComposingRegionCommand) obj;
        return this.start == setComposingRegionCommand.start && this.end == setComposingRegionCommand.end;
    }

    public final int hashCode() {
        return (this.start * 31) + this.end;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingRegionCommand(start=");
        sb.append(this.start);
        sb.append(", end=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.end, ')');
    }
}
