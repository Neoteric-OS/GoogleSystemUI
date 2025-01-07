package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TouchSlopDetector {
    public final Orientation orientation;
    public long totalPositionChange = 0;

    public TouchSlopDetector(Orientation orientation) {
        this.orientation = orientation;
    }

    /* renamed from: addPointerInputChange-dBAh8RU, reason: not valid java name */
    public final long m76addPointerInputChangedBAh8RU(PointerInputChange pointerInputChange, float f) {
        long floatToRawIntBits;
        int floatToRawIntBits2;
        long m315plusMKHz9U = Offset.m315plusMKHz9U(this.totalPositionChange, Offset.m314minusMKHz9U(pointerInputChange.position, pointerInputChange.previousPosition));
        this.totalPositionChange = m315plusMKHz9U;
        Orientation orientation = this.orientation;
        if ((orientation == null ? Offset.m311getDistanceimpl(m315plusMKHz9U) : Math.abs(m77mainAxisk4lQ0M(m315plusMKHz9U))) < f) {
            return 9205357640488583168L;
        }
        if (orientation == null) {
            long j = this.totalPositionChange;
            return Offset.m314minusMKHz9U(this.totalPositionChange, Offset.m316timestuRUvjQ(f, Offset.m309divtuRUvjQ(Offset.m311getDistanceimpl(j), j)));
        }
        float m77mainAxisk4lQ0M = m77mainAxisk4lQ0M(this.totalPositionChange) - (Math.signum(m77mainAxisk4lQ0M(this.totalPositionChange)) * f);
        long j2 = this.totalPositionChange;
        Orientation orientation2 = Orientation.Horizontal;
        float intBitsToFloat = Float.intBitsToFloat((int) (orientation == orientation2 ? j2 & 4294967295L : j2 >> 32));
        if (orientation == orientation2) {
            floatToRawIntBits = Float.floatToRawIntBits(m77mainAxisk4lQ0M);
            floatToRawIntBits2 = Float.floatToRawIntBits(intBitsToFloat);
        } else {
            floatToRawIntBits = Float.floatToRawIntBits(intBitsToFloat);
            floatToRawIntBits2 = Float.floatToRawIntBits(m77mainAxisk4lQ0M);
        }
        return (floatToRawIntBits << 32) | (floatToRawIntBits2 & 4294967295L);
    }

    /* renamed from: mainAxis-k-4lQ0M, reason: not valid java name */
    public final float m77mainAxisk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
    }
}
