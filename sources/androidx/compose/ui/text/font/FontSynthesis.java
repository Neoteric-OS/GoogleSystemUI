package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontSynthesis {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m611equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m612toStringimpl(int i) {
        return m611equalsimpl0(i, 0) ? "None" : m611equalsimpl0(i, 1) ? "All" : m611equalsimpl0(i, 2) ? "Weight" : m611equalsimpl0(i, 3) ? "Style" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FontSynthesis) {
            return this.value == ((FontSynthesis) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m612toStringimpl(this.value);
    }
}
