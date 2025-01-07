package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StrokeCap {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m392equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m393toStringimpl(int i) {
        return m392equalsimpl0(i, 0) ? "Butt" : m392equalsimpl0(i, 1) ? "Round" : m392equalsimpl0(i, 2) ? "Square" : "Unknown";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof StrokeCap) {
            return this.value == ((StrokeCap) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m393toStringimpl(this.value);
    }
}
