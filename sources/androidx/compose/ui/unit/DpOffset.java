package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DpOffset {
    public final long packedValue;

    public final boolean equals(Object obj) {
        if (obj instanceof DpOffset) {
            return this.packedValue == ((DpOffset) obj).packedValue;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.packedValue);
    }

    public final String toString() {
        long j = this.packedValue;
        if (j == 9205357640488583168L) {
            return "DpOffset.Unspecified";
        }
        return "(" + ((Object) Dp.m669toStringimpl(Float.intBitsToFloat((int) (j >> 32)))) + ", " + ((Object) Dp.m669toStringimpl(Float.intBitsToFloat((int) (j & 4294967295L)))) + ')';
    }
}
