package com.android.systemui.qs.panels.ui.compose.selection;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileWidths {
    public final int base;
    public final int max;
    public final int min;

    public TileWidths(int i, int i2, int i3) {
        this.base = i;
        this.min = i2;
        this.max = i3;
        if (i3 <= i2) {
            throw new IllegalStateException("The max width needs to be larger than the min width.");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileWidths)) {
            return false;
        }
        TileWidths tileWidths = (TileWidths) obj;
        return this.base == tileWidths.base && this.min == tileWidths.min && this.max == tileWidths.max;
    }

    public final int hashCode() {
        return Integer.hashCode(this.max) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.min, Integer.hashCode(this.base) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TileWidths(base=");
        sb.append(this.base);
        sb.append(", min=");
        sb.append(this.min);
        sb.append(", max=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.max, ")");
    }
}
