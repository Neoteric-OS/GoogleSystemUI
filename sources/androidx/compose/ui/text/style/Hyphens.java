package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Hyphens {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m632equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m633toStringimpl(int i) {
        return m632equalsimpl0(i, 1) ? "Hyphens.None" : m632equalsimpl0(i, 2) ? "Hyphens.Auto" : m632equalsimpl0(i, Integer.MIN_VALUE) ? "Hyphens.Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Hyphens) {
            return this.value == ((Hyphens) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m633toStringimpl(this.value);
    }
}
