package androidx.compose.ui.unit;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntOffset {
    public static final Companion Companion = null;
    public final long packedValue;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* renamed from: copy-iSbpLlY$default, reason: not valid java name */
    public static long m673copyiSbpLlY$default(int i, int i2, long j, int i3) {
        if ((i3 & 1) != 0) {
            i = (int) (j >> 32);
        }
        if ((i3 & 2) != 0) {
            i2 = (int) (j & 4294967295L);
        }
        return (i2 & 4294967295L) | (i << 32);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m674equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: minus-qkQi6aY, reason: not valid java name */
    public static final long m675minusqkQi6aY(long j, long j2) {
        return ((((int) (j >> 32)) - ((int) (j2 >> 32))) << 32) | ((((int) (j & 4294967295L)) - ((int) (j2 & 4294967295L))) & 4294967295L);
    }

    /* renamed from: plus-qkQi6aY, reason: not valid java name */
    public static final long m676plusqkQi6aY(long j, long j2) {
        return ((((int) (j >> 32)) + ((int) (j2 >> 32))) << 32) | ((((int) (j & 4294967295L)) + ((int) (j2 & 4294967295L))) & 4294967295L);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m677toStringimpl(long j) {
        StringBuilder sb = new StringBuilder("(");
        sb.append((int) (j >> 32));
        sb.append(", ");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, (int) (j & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof IntOffset) {
            return this.packedValue == ((IntOffset) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m677toStringimpl(this.packedValue);
    }
}
