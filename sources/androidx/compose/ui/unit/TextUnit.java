package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextUnit {
    public static final TextUnitType[] TextUnitTypes = {new TextUnitType(0), new TextUnitType(4294967296L), new TextUnitType(8589934592L)};
    public static final long Unspecified = TextUnitKt.pack(Float.NaN, 0);
    public final long packedValue;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m686equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getType-UIouoOA, reason: not valid java name */
    public static final long m687getTypeUIouoOA(long j) {
        return TextUnitTypes[(int) ((j & 1095216660480L) >>> 32)].type;
    }

    /* renamed from: getValue-impl, reason: not valid java name */
    public static final float m688getValueimpl(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: isEm-impl, reason: not valid java name */
    public static final boolean m689isEmimpl(long j) {
        return (j & 1095216660480L) == 8589934592L;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m690toStringimpl(long j) {
        long m687getTypeUIouoOA = m687getTypeUIouoOA(j);
        if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 0L)) {
            return "Unspecified";
        }
        if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 4294967296L)) {
            return m688getValueimpl(j) + ".sp";
        }
        if (!TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 8589934592L)) {
            return "Invalid";
        }
        return m688getValueimpl(j) + ".em";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextUnit) {
            return this.packedValue == ((TextUnit) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m690toStringimpl(this.packedValue);
    }
}
