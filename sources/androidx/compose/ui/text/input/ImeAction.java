package androidx.compose.ui.text.input;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ImeAction {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m617equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m618toStringimpl(int i) {
        return m617equalsimpl0(i, -1) ? "Unspecified" : m617equalsimpl0(i, 0) ? "None" : m617equalsimpl0(i, 1) ? "Default" : m617equalsimpl0(i, 2) ? "Go" : m617equalsimpl0(i, 3) ? "Search" : m617equalsimpl0(i, 4) ? "Send" : m617equalsimpl0(i, 5) ? "Previous" : m617equalsimpl0(i, 6) ? "Next" : m617equalsimpl0(i, 7) ? "Done" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ImeAction) {
            return this.value == ((ImeAction) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m618toStringimpl(this.value);
    }
}
