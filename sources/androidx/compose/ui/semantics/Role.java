package androidx.compose.ui.semantics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Role {
    public final int value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m574equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m575toStringimpl(int i) {
        return m574equalsimpl0(i, 0) ? "Button" : m574equalsimpl0(i, 1) ? "Checkbox" : m574equalsimpl0(i, 2) ? "Switch" : m574equalsimpl0(i, 3) ? "RadioButton" : m574equalsimpl0(i, 4) ? "Tab" : m574equalsimpl0(i, 5) ? "Image" : m574equalsimpl0(i, 6) ? "DropdownList" : m574equalsimpl0(i, 7) ? "Picker" : "Unknown";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Role) {
            return this.value == ((Role) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m575toStringimpl(this.value);
    }
}
