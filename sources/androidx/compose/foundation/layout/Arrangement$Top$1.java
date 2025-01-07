package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Arrangement$Top$1 implements Arrangement.Vertical {
    @Override // androidx.compose.foundation.layout.Arrangement.Vertical
    public final void arrange(Density density, int i, int[] iArr, int[] iArr2) {
        Arrangement.placeLeftOrTop$foundation_layout_release(iArr, iArr2, false);
    }

    public final String toString() {
        return "Arrangement#Top";
    }
}
