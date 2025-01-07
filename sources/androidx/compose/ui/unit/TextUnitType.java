package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextUnitType {
    public final long type;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m691equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m692toStringimpl(long j) {
        return m691equalsimpl0(j, 0L) ? "Unspecified" : m691equalsimpl0(j, 4294967296L) ? "Sp" : m691equalsimpl0(j, 8589934592L) ? "Em" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextUnitType) {
            return this.type == ((TextUnitType) obj).type;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.type);
    }

    public final String toString() {
        return m692toStringimpl(this.type);
    }
}
