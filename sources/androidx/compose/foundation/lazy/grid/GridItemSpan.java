package androidx.compose.foundation.lazy.grid;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GridItemSpan {
    public final long packedValue;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m127toStringimpl(long j) {
        return "GridItemSpan(packedValue=" + j + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof GridItemSpan) {
            return this.packedValue == ((GridItemSpan) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m127toStringimpl(this.packedValue);
    }
}
