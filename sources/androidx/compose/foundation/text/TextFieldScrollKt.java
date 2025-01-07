package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldScrollKt {
    public static final Rect access$getCursorRectInScroller(Density density, int i, TransformedText transformedText, TextLayoutResult textLayoutResult, boolean z, int i2) {
        Rect cursorRect = textLayoutResult != null ? textLayoutResult.getCursorRect(transformedText.offsetMapping.originalToTransformed(i)) : Rect.Zero;
        int mo45roundToPx0680j_4 = density.mo45roundToPx0680j_4(TextFieldCursorKt.DefaultCursorThickness);
        float f = cursorRect.left;
        return new Rect(z ? (i2 - f) - mo45roundToPx0680j_4 : f, cursorRect.top, z ? i2 - f : mo45roundToPx0680j_4 + f, cursorRect.bottom);
    }
}
