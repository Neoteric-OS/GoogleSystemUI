package com.android.systemui.brightness.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LinearBrightness {
    public final float floatValue;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m791toStringimpl(float f) {
        return "LinearBrightness(floatValue=" + f + ")";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof LinearBrightness) {
            return Float.compare(this.floatValue, ((LinearBrightness) obj).floatValue) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.floatValue);
    }

    public final String toString() {
        return m791toStringimpl(this.floatValue);
    }
}
