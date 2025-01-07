package androidx.compose.material3;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CardColors {
    public final long containerColor;
    public final long contentColor;
    public final long disabledContainerColor;
    public final long disabledContentColor;

    public CardColors(long j, long j2, long j3, long j4) {
        this.containerColor = j;
        this.contentColor = j2;
        this.disabledContainerColor = j3;
        this.disabledContentColor = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CardColors)) {
            return false;
        }
        CardColors cardColors = (CardColors) obj;
        return Color.m363equalsimpl0(this.containerColor, cardColors.containerColor) && Color.m363equalsimpl0(this.contentColor, cardColors.contentColor) && Color.m363equalsimpl0(this.disabledContainerColor, cardColors.disabledContainerColor) && Color.m363equalsimpl0(this.disabledContentColor, cardColors.disabledContentColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.disabledContentColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.containerColor) * 31, 31, this.contentColor), 31, this.disabledContainerColor);
    }
}
