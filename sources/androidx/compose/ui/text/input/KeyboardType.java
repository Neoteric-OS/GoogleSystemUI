package androidx.compose.ui.text.input;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardType {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m621equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m622toStringimpl(int i) {
        return m621equalsimpl0(i, 0) ? "Unspecified" : m621equalsimpl0(i, 1) ? "Text" : m621equalsimpl0(i, 2) ? "Ascii" : m621equalsimpl0(i, 3) ? "Number" : m621equalsimpl0(i, 4) ? "Phone" : m621equalsimpl0(i, 5) ? "Uri" : m621equalsimpl0(i, 6) ? "Email" : m621equalsimpl0(i, 7) ? "Password" : m621equalsimpl0(i, 8) ? "NumberPassword" : m621equalsimpl0(i, 9) ? "Decimal" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof KeyboardType) {
            return this.value == ((KeyboardType) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m622toStringimpl(this.value);
    }
}
