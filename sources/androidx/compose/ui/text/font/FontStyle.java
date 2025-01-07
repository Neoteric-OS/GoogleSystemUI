package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontStyle {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m609equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m610toStringimpl(int i) {
        return m609equalsimpl0(i, 0) ? "Normal" : m609equalsimpl0(i, 1) ? "Italic" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FontStyle) {
            return this.value == ((FontStyle) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m610toStringimpl(this.value);
    }
}
