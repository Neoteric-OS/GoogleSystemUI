package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LimitInsets implements WindowInsets {
    public final WindowInsets insets;
    public final int sides;

    public LimitInsets(WindowInsets windowInsets, int i) {
        this.insets = windowInsets;
        this.sides = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimitInsets)) {
            return false;
        }
        LimitInsets limitInsets = (LimitInsets) obj;
        if (Intrinsics.areEqual(this.insets, limitInsets.insets)) {
            if (this.sides == limitInsets.sides) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getBottom(Density density) {
        if ((this.sides & 32) != 0) {
            return this.insets.getBottom(density);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getLeft(Density density, LayoutDirection layoutDirection) {
        if (((layoutDirection == LayoutDirection.Ltr ? 8 : 2) & this.sides) != 0) {
            return this.insets.getLeft(density, layoutDirection);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getRight(Density density, LayoutDirection layoutDirection) {
        if (((layoutDirection == LayoutDirection.Ltr ? 4 : 1) & this.sides) != 0) {
            return this.insets.getRight(density, layoutDirection);
        }
        return 0;
    }

    @Override // androidx.compose.foundation.layout.WindowInsets
    public final int getTop(Density density) {
        if ((this.sides & 16) != 0) {
            return this.insets.getTop(density);
        }
        return 0;
    }

    public final int hashCode() {
        return Integer.hashCode(this.sides) + (this.insets.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.insets);
        sb.append(" only ");
        StringBuilder sb2 = new StringBuilder("WindowInsetsSides(");
        StringBuilder sb3 = new StringBuilder();
        int i = this.sides;
        int i2 = WindowInsetsSides.Start;
        if ((i & i2) == i2) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "Start");
        }
        int i3 = WindowInsetsSides.Left;
        if ((i & i3) == i3) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "Left");
        }
        if ((i & 16) == 16) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "Top");
        }
        int i4 = WindowInsetsSides.End;
        if ((i & i4) == i4) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "End");
        }
        int i5 = WindowInsetsSides.Right;
        if ((i & i5) == i5) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "Right");
        }
        if ((i & 32) == 32) {
            WindowInsetsSides.valueToString_impl$lambda$0$appendPlus(sb3, "Bottom");
        }
        sb2.append(sb3.toString());
        sb2.append(')');
        sb.append((Object) sb2.toString());
        sb.append(')');
        return sb.toString();
    }
}
