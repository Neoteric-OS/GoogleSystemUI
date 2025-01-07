package androidx.compose.ui.focus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusDirection {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m284equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m285toStringimpl(int i) {
        return m284equalsimpl0(i, 1) ? "Next" : m284equalsimpl0(i, 2) ? "Previous" : m284equalsimpl0(i, 3) ? "Left" : m284equalsimpl0(i, 4) ? "Right" : m284equalsimpl0(i, 5) ? "Up" : m284equalsimpl0(i, 6) ? "Down" : m284equalsimpl0(i, 7) ? "Enter" : m284equalsimpl0(i, 8) ? "Exit" : "Invalid FocusDirection";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof FocusDirection) {
            return this.value == ((FocusDirection) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m285toStringimpl(this.value);
    }
}
