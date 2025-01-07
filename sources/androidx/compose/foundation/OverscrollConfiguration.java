package androidx.compose.foundation;

import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OverscrollConfiguration {
    public final PaddingValuesImpl drawPadding;
    public final long glowColor;

    public OverscrollConfiguration() {
        long Color = ColorKt.Color(4284900966L);
        float f = 0;
        float f2 = 0;
        PaddingValuesImpl paddingValuesImpl = new PaddingValuesImpl(f, f2, f, f2);
        this.glowColor = Color;
        this.drawPadding = paddingValuesImpl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!OverscrollConfiguration.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        OverscrollConfiguration overscrollConfiguration = (OverscrollConfiguration) obj;
        return Color.m363equalsimpl0(this.glowColor, overscrollConfiguration.glowColor) && Intrinsics.areEqual(this.drawPadding, overscrollConfiguration.drawPadding);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return this.drawPadding.hashCode() + (Long.hashCode(this.glowColor) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OverscrollConfiguration(glowColor=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.glowColor, ", drawPadding=", sb);
        sb.append(this.drawPadding);
        sb.append(')');
        return sb.toString();
    }
}
