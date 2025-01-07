package com.android.systemui.statusbar.pipeline.mobile.ui.binder;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Colors {
    public final int contrast;
    public final int tint;

    public Colors(int i, int i2) {
        this.tint = i;
        this.contrast = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Colors)) {
            return false;
        }
        Colors colors = (Colors) obj;
        return this.tint == colors.tint && this.contrast == colors.contrast;
    }

    public final int hashCode() {
        return Integer.hashCode(this.contrast) + (Integer.hashCode(this.tint) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Colors(tint=");
        sb.append(this.tint);
        sb.append(", contrast=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.contrast, ")");
    }
}
