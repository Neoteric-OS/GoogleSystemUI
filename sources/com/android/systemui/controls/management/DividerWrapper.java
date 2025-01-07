package com.android.systemui.controls.management;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DividerWrapper extends ElementWrapper {
    public boolean showDivider;
    public boolean showNone;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DividerWrapper)) {
            return false;
        }
        DividerWrapper dividerWrapper = (DividerWrapper) obj;
        return this.showNone == dividerWrapper.showNone && this.showDivider == dividerWrapper.showDivider;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.showDivider) + (Boolean.hashCode(this.showNone) * 31);
    }

    public final String toString() {
        return "DividerWrapper(showNone=" + this.showNone + ", showDivider=" + this.showDivider + ")";
    }
}
