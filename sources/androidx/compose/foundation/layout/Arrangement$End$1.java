package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Arrangement$End$1 implements Arrangement.Horizontal {
    @Override // androidx.compose.foundation.layout.Arrangement.Horizontal
    public final void arrange(Density density, int i, int[] iArr, LayoutDirection layoutDirection, int[] iArr2) {
        if (layoutDirection == LayoutDirection.Ltr) {
            Arrangement.placeRightOrBottom$foundation_layout_release(i, iArr, iArr2, false);
        } else {
            Arrangement.placeLeftOrTop$foundation_layout_release(iArr, iArr2, true);
        }
    }

    public final String toString() {
        return "Arrangement#End";
    }
}
