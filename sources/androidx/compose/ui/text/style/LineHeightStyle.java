package androidx.compose.ui.text.style;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LineHeightStyle {
    public static final LineHeightStyle Default = new LineHeightStyle(17, Alignment.Proportional);
    public final float alignment;
    public final int trim;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Alignment {
        public static final float Bottom;
        public static final float Center;
        public static final float Proportional;
        public final float topRatio;

        static {
            m637constructorimpl(0.0f);
            m637constructorimpl(0.5f);
            Center = 0.5f;
            m637constructorimpl(-1.0f);
            Proportional = -1.0f;
            m637constructorimpl(1.0f);
            Bottom = 1.0f;
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static void m637constructorimpl(float f) {
            if ((0.0f > f || f > 1.0f) && f != -1.0f) {
                throw new IllegalStateException("topRatio should be in [0..1] range or -1");
            }
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m638toStringimpl(float f) {
            if (f == 0.0f) {
                return "LineHeightStyle.Alignment.Top";
            }
            if (f == Center) {
                return "LineHeightStyle.Alignment.Center";
            }
            if (f == Proportional) {
                return "LineHeightStyle.Alignment.Proportional";
            }
            if (f == Bottom) {
                return "LineHeightStyle.Alignment.Bottom";
            }
            return "LineHeightStyle.Alignment(topPercentage = " + f + ')';
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Alignment) {
                return Float.compare(this.topRatio, ((Alignment) obj).topRatio) == 0;
            }
            return false;
        }

        public final int hashCode() {
            return Float.hashCode(this.topRatio);
        }

        public final String toString() {
            return m638toStringimpl(this.topRatio);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Mode {
        public final boolean equals(Object obj) {
            if (!(obj instanceof Mode)) {
                return false;
            }
            ((Mode) obj).getClass();
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(0);
        }

        public final String toString() {
            return "Mode(value=0)";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Trim {
        public final int value;

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m639toStringimpl(int i) {
            return i == 1 ? "LineHeightStyle.Trim.FirstLineTop" : i == 16 ? "LineHeightStyle.Trim.LastLineBottom" : i == 17 ? "LineHeightStyle.Trim.Both" : i == 0 ? "LineHeightStyle.Trim.None" : "Invalid";
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Trim) {
                return this.value == ((Trim) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            return m639toStringimpl(this.value);
        }
    }

    public LineHeightStyle(int i, float f) {
        this.alignment = f;
        this.trim = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineHeightStyle)) {
            return false;
        }
        LineHeightStyle lineHeightStyle = (LineHeightStyle) obj;
        float f = lineHeightStyle.alignment;
        float f2 = Alignment.Center;
        return Float.compare(this.alignment, f) == 0 && this.trim == lineHeightStyle.trim;
    }

    public final int hashCode() {
        float f = Alignment.Center;
        return Integer.hashCode(0) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.trim, Float.hashCode(this.alignment) * 31, 31);
    }

    public final String toString() {
        return "LineHeightStyle(alignment=" + ((Object) Alignment.m638toStringimpl(this.alignment)) + ", trim=" + ((Object) Trim.m639toStringimpl(this.trim)) + ",mode=Mode(value=0))";
    }
}
