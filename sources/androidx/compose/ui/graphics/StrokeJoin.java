package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StrokeJoin {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m394equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m395toStringimpl(int i) {
        return m394equalsimpl0(i, 0) ? "Miter" : m394equalsimpl0(i, 1) ? "Round" : m394equalsimpl0(i, 2) ? "Bevel" : "Unknown";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof StrokeJoin) {
            return this.value == ((StrokeJoin) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m395toStringimpl(this.value);
    }
}
