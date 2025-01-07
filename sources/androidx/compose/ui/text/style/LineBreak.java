package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LineBreak {
    public static final int Simple = 66305;
    public final int mask;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Strategy {
        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m635equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Strictness {
        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m636equalsimpl0(int i, int i2) {
            return i == i2;
        }
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m634toStringimpl(int i) {
        StringBuilder sb = new StringBuilder("LineBreak(strategy=");
        int i2 = i & 255;
        String str = "Invalid";
        sb.append((Object) (Strategy.m635equalsimpl0(i2, 1) ? "Strategy.Simple" : Strategy.m635equalsimpl0(i2, 2) ? "Strategy.HighQuality" : Strategy.m635equalsimpl0(i2, 3) ? "Strategy.Balanced" : Strategy.m635equalsimpl0(i2, 0) ? "Strategy.Unspecified" : "Invalid"));
        sb.append(", strictness=");
        int i3 = (i >> 8) & 255;
        sb.append((Object) (Strictness.m636equalsimpl0(i3, 1) ? "Strictness.None" : Strictness.m636equalsimpl0(i3, 2) ? "Strictness.Loose" : Strictness.m636equalsimpl0(i3, 3) ? "Strictness.Normal" : Strictness.m636equalsimpl0(i3, 4) ? "Strictness.Strict" : Strictness.m636equalsimpl0(i3, 0) ? "Strictness.Unspecified" : "Invalid"));
        sb.append(", wordBreak=");
        int i4 = (i >> 16) & 255;
        if (i4 == 1) {
            str = "WordBreak.None";
        } else if (i4 == 2) {
            str = "WordBreak.Phrase";
        } else if (i4 == 0) {
            str = "WordBreak.Unspecified";
        }
        sb.append((Object) str);
        sb.append(')');
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof LineBreak) {
            return this.mask == ((LineBreak) obj).mask;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.mask);
    }

    public final String toString() {
        return m634toStringimpl(this.mask);
    }
}
