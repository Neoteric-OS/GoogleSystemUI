package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PointerEventKt {
    public static final boolean changedToDown(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || pointerInputChange.previousPressed || !pointerInputChange.pressed) ? false : true;
    }

    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !pointerInputChange.previousPressed && pointerInputChange.pressed;
    }

    public static final boolean changedToUp(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || !pointerInputChange.previousPressed || pointerInputChange.pressed) ? false : true;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.previousPressed && !pointerInputChange.pressed;
    }

    /* renamed from: isOutOfBounds-O0kMr_c, reason: not valid java name */
    public static final boolean m459isOutOfBoundsO0kMr_c(PointerInputChange pointerInputChange, long j) {
        long j2 = pointerInputChange.position;
        float intBitsToFloat = Float.intBitsToFloat((int) (j2 >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j2 & 4294967295L));
        return intBitsToFloat < 0.0f || intBitsToFloat > ((float) ((int) (j >> 32))) || intBitsToFloat2 < 0.0f || intBitsToFloat2 > ((float) ((int) (j & 4294967295L)));
    }

    /* renamed from: isOutOfBounds-jwHxaWs, reason: not valid java name */
    public static final boolean m460isOutOfBoundsjwHxaWs(PointerInputChange pointerInputChange, long j, long j2) {
        if (!PointerType.m468equalsimpl0(pointerInputChange.type, 1)) {
            return m459isOutOfBoundsO0kMr_c(pointerInputChange, j);
        }
        long j3 = pointerInputChange.position;
        float intBitsToFloat = Float.intBitsToFloat((int) (j3 >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j3 & 4294967295L));
        int i = (int) (j2 >> 32);
        int i2 = (int) (j2 & 4294967295L);
        return intBitsToFloat < (-Float.intBitsToFloat(i)) || intBitsToFloat > Float.intBitsToFloat(i) + ((float) ((int) (j >> 32))) || intBitsToFloat2 < (-Float.intBitsToFloat(i2)) || intBitsToFloat2 > Float.intBitsToFloat(i2) + ((float) ((int) (j & 4294967295L)));
    }

    public static final long positionChangeInternal(PointerInputChange pointerInputChange, boolean z) {
        long m314minusMKHz9U = Offset.m314minusMKHz9U(pointerInputChange.position, pointerInputChange.previousPosition);
        if (z || !pointerInputChange.isConsumed()) {
            return m314minusMKHz9U;
        }
        return 0L;
    }
}
