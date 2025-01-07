package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SelectionLayoutKt {
    public static final ResolvedTextDirection getTextDirectionForOffset(TextLayoutResult textLayoutResult, int i) {
        if (textLayoutResult.layoutInput.text.length() != 0) {
            int lineForOffset = textLayoutResult.getLineForOffset(i);
            if ((i != 0 && lineForOffset == textLayoutResult.getLineForOffset(i - 1)) || (i != textLayoutResult.layoutInput.text.text.length() && lineForOffset == textLayoutResult.getLineForOffset(i + 1))) {
                return textLayoutResult.getBidiRunDirection(i);
            }
        }
        return textLayoutResult.getParagraphDirection(i);
    }
}
