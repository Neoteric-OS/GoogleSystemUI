package androidx.compose.foundation.layout;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FixedDpInsets implements WindowInsets {
    public final float bottomDp;
    public final float leftDp;
    public final float rightDp;
    public final float topDp;

    public FixedDpInsets(float f, float f2, float f3, float f4) {
        this.leftDp = f;
        this.topDp = f2;
        this.rightDp = f3;
        this.bottomDp = f4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FixedDpInsets)) {
            return false;
        }
        FixedDpInsets fixedDpInsets = (FixedDpInsets) obj;
        return Dp.m668equalsimpl0(this.leftDp, fixedDpInsets.leftDp) && Dp.m668equalsimpl0(this.topDp, fixedDpInsets.topDp) && Dp.m668equalsimpl0(this.rightDp, fixedDpInsets.rightDp) && Dp.m668equalsimpl0(this.bottomDp, fixedDpInsets.bottomDp);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getBottom(Density density) {
        return density.mo45roundToPx0680j_4(this.bottomDp);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getLeft(Density density, LayoutDirection layoutDirection) {
        return density.mo45roundToPx0680j_4(this.leftDp);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getRight(Density density, LayoutDirection layoutDirection) {
        return density.mo45roundToPx0680j_4(this.rightDp);
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getTop(Density density) {
        return density.mo45roundToPx0680j_4(this.topDp);
    }

    public final int hashCode() {
        return Float.hashCode(this.bottomDp) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.leftDp) * 31, this.topDp, 31), this.rightDp, 31);
    }

    public final String toString() {
        return "Insets(left=" + ((Object) Dp.m669toStringimpl(this.leftDp)) + ", top=" + ((Object) Dp.m669toStringimpl(this.topDp)) + ", right=" + ((Object) Dp.m669toStringimpl(this.rightDp)) + ", bottom=" + ((Object) Dp.m669toStringimpl(this.bottomDp)) + ')';
    }
}
