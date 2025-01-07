package androidx.compose.ui.text.input;

import androidx.compose.ui.geometry.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CursorAnchorInfoBuilder_androidKt {
    public static final boolean containsInclusive(Rect rect, float f, float f2) {
        return f <= rect.right && rect.left <= f && f2 <= rect.bottom && rect.top <= f2;
    }
}
