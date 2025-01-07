package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextDirection {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m642equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m643toStringimpl(int i) {
        return m642equalsimpl0(i, 1) ? "Ltr" : m642equalsimpl0(i, 2) ? "Rtl" : m642equalsimpl0(i, 3) ? "Content" : m642equalsimpl0(i, 4) ? "ContentOrLtr" : m642equalsimpl0(i, 5) ? "ContentOrRtl" : m642equalsimpl0(i, Integer.MIN_VALUE) ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextDirection) {
            return this.value == ((TextDirection) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m643toStringimpl(this.value);
    }
}
