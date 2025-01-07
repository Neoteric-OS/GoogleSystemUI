package com.android.systemui.statusbar.notification.stack.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeScrimRounding {
    public final boolean isBottomRounded;
    public final boolean isTopRounded;

    public ShadeScrimRounding(boolean z, boolean z2) {
        this.isTopRounded = z;
        this.isBottomRounded = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadeScrimRounding)) {
            return false;
        }
        ShadeScrimRounding shadeScrimRounding = (ShadeScrimRounding) obj;
        return this.isTopRounded == shadeScrimRounding.isTopRounded && this.isBottomRounded == shadeScrimRounding.isBottomRounded;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isBottomRounded) + (Boolean.hashCode(this.isTopRounded) * 31);
    }

    public final String toString() {
        return "ShadeScrimRounding(isTopRounded=" + this.isTopRounded + ", isBottomRounded=" + this.isBottomRounded + ")";
    }
}
