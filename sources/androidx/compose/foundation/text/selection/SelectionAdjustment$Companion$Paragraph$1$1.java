package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.ui.text.TextRangeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SelectionAdjustment$Companion$Paragraph$1$1 implements BoundaryFunction {
    public static final SelectionAdjustment$Companion$Paragraph$1$1 INSTANCE = new SelectionAdjustment$Companion$Paragraph$1$1();

    @Override // androidx.compose.foundation.text.selection.BoundaryFunction
    /* renamed from: getBoundary-fzxv0v0 */
    public final long mo182getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i) {
        String str = selectableInfo.textLayoutResult.layoutInput.text.text;
        return TextRangeKt.TextRange(StringHelpersKt.findParagraphStart(i, str), StringHelpersKt.findParagraphEnd(i, str));
    }
}
