package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextMotion {
    public final int linearity;
    public final boolean subpixelTextPositioning;
    public static final TextMotion Static = new TextMotion(2, false);
    public static final TextMotion Animated = new TextMotion(1, true);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Linearity {
        public final int value;

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m646equalsimpl0(int i, int i2) {
            return i == i2;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Linearity) {
                return this.value == ((Linearity) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            int i = this.value;
            return m646equalsimpl0(i, 1) ? "Linearity.Linear" : m646equalsimpl0(i, 2) ? "Linearity.FontHinting" : m646equalsimpl0(i, 3) ? "Linearity.None" : "Invalid";
        }
    }

    public TextMotion(int i, boolean z) {
        this.linearity = i;
        this.subpixelTextPositioning = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextMotion)) {
            return false;
        }
        TextMotion textMotion = (TextMotion) obj;
        return Linearity.m646equalsimpl0(this.linearity, textMotion.linearity) && this.subpixelTextPositioning == textMotion.subpixelTextPositioning;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.subpixelTextPositioning) + (Integer.hashCode(this.linearity) * 31);
    }

    public final String toString() {
        return equals(Static) ? "TextMotion.Static" : equals(Animated) ? "TextMotion.Animated" : "Invalid";
    }
}
