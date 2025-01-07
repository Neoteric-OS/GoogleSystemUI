package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DpSize {
    public final long packedValue;

    /* renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public static final float m671getHeightD9Ej5fM(long j) {
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }

    /* renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public static final float m672getWidthD9Ej5fM(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof DpSize) {
            return this.packedValue == ((DpSize) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        long j = this.packedValue;
        if (j == 9205357640488583168L) {
            return "DpSize.Unspecified";
        }
        return ((Object) Dp.m669toStringimpl(m672getWidthD9Ej5fM(j))) + " x " + ((Object) Dp.m669toStringimpl(m671getHeightD9Ej5fM(j)));
    }
}
