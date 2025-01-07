package androidx.compose.foundation.contextmenu;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextMenuColors {
    public final long backgroundColor;
    public final long disabledIconColor;
    public final long disabledTextColor;
    public final long iconColor;
    public final long textColor;

    public ContextMenuColors(long j, long j2, long j3, long j4, long j5) {
        this.backgroundColor = j;
        this.textColor = j2;
        this.iconColor = j3;
        this.disabledTextColor = j4;
        this.disabledIconColor = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ContextMenuColors)) {
            return false;
        }
        ContextMenuColors contextMenuColors = (ContextMenuColors) obj;
        return Color.m363equalsimpl0(this.backgroundColor, contextMenuColors.backgroundColor) && Color.m363equalsimpl0(this.textColor, contextMenuColors.textColor) && Color.m363equalsimpl0(this.iconColor, contextMenuColors.iconColor) && Color.m363equalsimpl0(this.disabledTextColor, contextMenuColors.disabledTextColor) && Color.m363equalsimpl0(this.disabledIconColor, contextMenuColors.disabledIconColor);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.disabledIconColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.backgroundColor) * 31, 31, this.textColor), 31, this.iconColor), 31, this.disabledTextColor);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ContextMenuColors(backgroundColor=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.backgroundColor, ", textColor=", sb);
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.textColor, ", iconColor=", sb);
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.iconColor, ", disabledTextColor=", sb);
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.disabledTextColor, ", disabledIconColor=", sb);
        sb.append((Object) Color.m369toStringimpl(this.disabledIconColor));
        sb.append(')');
        return sb.toString();
    }
}
