package androidx.compose.foundation.text.selection;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SelectableInfo {
    public final int rawEndHandleOffset;
    public final int rawPreviousHandleOffset;
    public final int rawStartHandleOffset;
    public final TextLayoutResult textLayoutResult;

    public SelectableInfo(int i, int i2, int i3, TextLayoutResult textLayoutResult) {
        this.rawStartHandleOffset = i;
        this.rawEndHandleOffset = i2;
        this.rawPreviousHandleOffset = i3;
        this.textLayoutResult = textLayoutResult;
    }

    public final Selection.AnchorInfo anchorForOffset(int i) {
        return new Selection.AnchorInfo(SelectionLayoutKt.getTextDirectionForOffset(this.textLayoutResult, i), i, 1L);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SelectionInfo(id=1, range=(");
        int i = this.rawStartHandleOffset;
        sb.append(i);
        sb.append('-');
        TextLayoutResult textLayoutResult = this.textLayoutResult;
        sb.append(SelectionLayoutKt.getTextDirectionForOffset(textLayoutResult, i));
        sb.append(',');
        int i2 = this.rawEndHandleOffset;
        sb.append(i2);
        sb.append('-');
        sb.append(SelectionLayoutKt.getTextDirectionForOffset(textLayoutResult, i2));
        sb.append("), prevOffset=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.rawPreviousHandleOffset, ')');
    }
}
