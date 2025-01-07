package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextAlign {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m640equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m641toStringimpl(int i) {
        return m640equalsimpl0(i, 1) ? "Left" : m640equalsimpl0(i, 2) ? "Right" : m640equalsimpl0(i, 3) ? "Center" : m640equalsimpl0(i, 4) ? "Justify" : m640equalsimpl0(i, 5) ? "Start" : m640equalsimpl0(i, 6) ? "End" : m640equalsimpl0(i, Integer.MIN_VALUE) ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextAlign) {
            return this.value == ((TextAlign) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m641toStringimpl(this.value);
    }
}
