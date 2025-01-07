package androidx.compose.ui.text;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextRange {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Zero = TextRangeKt.TextRange(0, 0);
    public final long packedValue;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m597equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getCollapsed-impl, reason: not valid java name */
    public static final boolean m598getCollapsedimpl(long j) {
        return ((int) (j >> 32)) == ((int) (j & 4294967295L));
    }

    /* renamed from: getLength-impl, reason: not valid java name */
    public static final int m599getLengthimpl(long j) {
        return m600getMaximpl(j) - m601getMinimpl(j);
    }

    /* renamed from: getMax-impl, reason: not valid java name */
    public static final int m600getMaximpl(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        return i > i2 ? i : i2;
    }

    /* renamed from: getMin-impl, reason: not valid java name */
    public static final int m601getMinimpl(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        return i > i2 ? i2 : i;
    }

    /* renamed from: getReversed-impl, reason: not valid java name */
    public static final boolean m602getReversedimpl(long j) {
        return ((int) (j >> 32)) > ((int) (j & 4294967295L));
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m603toStringimpl(long j) {
        StringBuilder sb = new StringBuilder("TextRange(");
        sb.append((int) (j >> 32));
        sb.append(", ");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, (int) (j & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TextRange) {
            return this.packedValue == ((TextRange) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        return m603toStringimpl(this.packedValue);
    }
}
