package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImageBitmapConfig {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m377equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ImageBitmapConfig) {
            return this.value == ((ImageBitmapConfig) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        int i = this.value;
        return m377equalsimpl0(i, 0) ? "Argb8888" : m377equalsimpl0(i, 1) ? "Alpha8" : m377equalsimpl0(i, 2) ? "Rgb565" : m377equalsimpl0(i, 3) ? "F16" : m377equalsimpl0(i, 4) ? "Gpu" : "Unknown";
    }
}
