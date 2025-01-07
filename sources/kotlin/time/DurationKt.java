package kotlin.time;

import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DurationKt {
    public static final long durationOfMillis(long j) {
        long j2 = (j << 1) + 1;
        int i = Duration.$r8$clinit;
        int i2 = DurationJvmKt.$r8$clinit;
        return j2;
    }

    public static final long durationOfMillisNormalized(long j) {
        return new LongRange(-4611686018426L, 4611686018426L).contains(j) ? durationOfNanos(j * 1000000) : durationOfMillis(RangesKt.coerceIn(j, -4611686018427387903L, 4611686018427387903L));
    }

    public static final long durationOfNanos(long j) {
        long j2 = j << 1;
        int i = Duration.$r8$clinit;
        int i2 = DurationJvmKt.$r8$clinit;
        return j2;
    }

    public static final long toDuration(int i, DurationUnit durationUnit) {
        if (durationUnit.compareTo(DurationUnit.SECONDS) > 0) {
            return toDuration(i, durationUnit);
        }
        return durationOfNanos(DurationUnit.NANOSECONDS.getTimeUnit$kotlin_stdlib().convert(i, durationUnit.getTimeUnit$kotlin_stdlib()));
    }

    public static final long toDuration(long j, DurationUnit durationUnit) {
        DurationUnit durationUnit2 = DurationUnit.NANOSECONDS;
        long convert = durationUnit.getTimeUnit$kotlin_stdlib().convert(4611686018426999999L, durationUnit2.getTimeUnit$kotlin_stdlib());
        if (new LongRange(-convert, convert).contains(j)) {
            return durationOfNanos(durationUnit2.getTimeUnit$kotlin_stdlib().convert(j, durationUnit.getTimeUnit$kotlin_stdlib()));
        }
        return durationOfMillis(RangesKt.coerceIn(DurationUnit.MILLISECONDS.getTimeUnit$kotlin_stdlib().convert(j, durationUnit.getTimeUnit$kotlin_stdlib()), -4611686018427387903L, 4611686018427387903L));
    }

    public static final long toDuration(double d, DurationUnit durationUnit) {
        double convertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, durationUnit, DurationUnit.NANOSECONDS);
        if (!Double.isNaN(convertDurationUnit)) {
            long roundToLong = MathKt.roundToLong(convertDurationUnit);
            if (new LongRange(-4611686018426999999L, 4611686018426999999L).contains(roundToLong)) {
                return durationOfNanos(roundToLong);
            }
            return durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, durationUnit, DurationUnit.MILLISECONDS)));
        }
        throw new IllegalArgumentException("Duration value cannot be NaN.");
    }
}
