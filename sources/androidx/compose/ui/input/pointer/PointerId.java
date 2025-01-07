package androidx.compose.ui.input.pointer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerId {
    public final long value;

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m462equalsimpl(long j, Object obj) {
        return (obj instanceof PointerId) && j == ((PointerId) obj).value;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m463equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m464toStringimpl(long j) {
        return "PointerId(value=" + j + ')';
    }

    public final boolean equals(Object obj) {
        return m462equalsimpl(this.value, obj);
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m464toStringimpl(this.value);
    }
}
