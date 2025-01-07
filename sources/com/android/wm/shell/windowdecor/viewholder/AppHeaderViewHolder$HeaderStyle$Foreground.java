package com.android.wm.shell.windowdecor.viewholder;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHeaderViewHolder$HeaderStyle$Foreground {
    public final int color;
    public final int opacity;

    public AppHeaderViewHolder$HeaderStyle$Foreground(int i, int i2) {
        this.color = i;
        this.opacity = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppHeaderViewHolder$HeaderStyle$Foreground)) {
            return false;
        }
        AppHeaderViewHolder$HeaderStyle$Foreground appHeaderViewHolder$HeaderStyle$Foreground = (AppHeaderViewHolder$HeaderStyle$Foreground) obj;
        return this.color == appHeaderViewHolder$HeaderStyle$Foreground.color && this.opacity == appHeaderViewHolder$HeaderStyle$Foreground.opacity;
    }

    public final int hashCode() {
        return Integer.hashCode(this.opacity) + (Integer.hashCode(this.color) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Foreground(color=");
        sb.append(this.color);
        sb.append(", opacity=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.opacity, ")");
    }
}
