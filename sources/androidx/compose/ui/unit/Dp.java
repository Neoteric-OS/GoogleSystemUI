package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Dp implements Comparable {
    public final float value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m668equalsimpl0(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m669toStringimpl(float f) {
        if (Float.isNaN(f)) {
            return "Dp.Unspecified";
        }
        return f + ".dp";
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.value, ((Dp) obj).value);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Dp) && Float.compare(this.value, ((Dp) obj).value) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.value);
    }

    public final String toString() {
        return m669toStringimpl(this.value);
    }
}
