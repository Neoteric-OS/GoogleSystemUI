package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Arrangement$Center$1 implements Arrangement.Horizontal, Arrangement.Vertical {
    public final float spacing = 0;

    @Override // androidx.compose.foundation.layout.Arrangement.Horizontal
    public final void arrange(Density density, int i, int[] iArr, LayoutDirection layoutDirection, int[] iArr2) {
        if (layoutDirection == LayoutDirection.Ltr) {
            Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, false);
        } else {
            Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, true);
        }
    }

    @Override // androidx.compose.foundation.layout.Arrangement.Horizontal, androidx.compose.foundation.layout.Arrangement.Vertical
    /* renamed from: getSpacing-D9Ej5fM, reason: not valid java name */
    public final float mo81getSpacingD9Ej5fM() {
        return this.spacing;
    }

    public final String toString() {
        return "Arrangement#Center";
    }

    @Override // androidx.compose.foundation.layout.Arrangement.Vertical
    public final void arrange(Density density, int i, int[] iArr, int[] iArr2) {
        Arrangement.placeCenter$foundation_layout_release(i, iArr, iArr2, false);
    }
}
