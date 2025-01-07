package androidx.compose.ui.platform;

import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NestedScrollInteropConnectionKt {
    /* renamed from: access$getScrollAxes-k-4lQ0M, reason: not valid java name */
    public static final int m568access$getScrollAxesk4lQ0M(long j) {
        int i = Math.abs(Float.intBitsToFloat((int) (j >> 32))) >= 0.5f ? 1 : 0;
        return Math.abs(Float.intBitsToFloat((int) (j & 4294967295L))) >= 0.5f ? i | 2 : i;
    }

    /* renamed from: access$toOffset-Uv8p0NA, reason: not valid java name */
    public static final long m569access$toOffsetUv8p0NA(int[] iArr, long j) {
        return (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32)) >= 0.0f ? RangesKt.coerceAtMost(iArr[0] * (-1.0f), Float.intBitsToFloat(r1)) : RangesKt.coerceAtLeast(iArr[0] * (-1.0f), Float.intBitsToFloat(r1))) << 32) | (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) >= 0.0f ? RangesKt.coerceAtMost(iArr[1] * (-1.0f), Float.intBitsToFloat(r9)) : RangesKt.coerceAtLeast(iArr[1] * (-1.0f), Float.intBitsToFloat(r9))) & 4294967295L);
    }

    public static final int composeToViewOffset(float f) {
        return ((int) (f >= 0.0f ? Math.ceil(f) : Math.floor(f))) * (-1);
    }
}
