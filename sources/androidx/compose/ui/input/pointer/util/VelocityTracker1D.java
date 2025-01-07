package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.internal.InlineClassHelperKt;
import java.util.Arrays;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VelocityTracker1D {
    public int index;
    public final int minSampleSize;
    public final float[] reusableDataPointsArray;
    public final float[] reusableTimeArray;
    public final float[] reusableVelocityCoefficients;
    public final DataPointAtTime[] samples;
    public final Strategy strategy;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Strategy {
        public static final /* synthetic */ Strategy[] $VALUES;
        public static final Strategy Impulse;
        public static final Strategy Lsq2;

        static {
            Strategy strategy = new Strategy("Lsq2", 0);
            Lsq2 = strategy;
            Strategy strategy2 = new Strategy("Impulse", 1);
            Impulse = strategy2;
            $VALUES = new Strategy[]{strategy, strategy2};
        }

        public static Strategy valueOf(String str) {
            return (Strategy) Enum.valueOf(Strategy.class, str);
        }

        public static Strategy[] values() {
            return (Strategy[]) $VALUES.clone();
        }
    }

    public VelocityTracker1D(Strategy strategy) {
        int i;
        this.strategy = strategy;
        int ordinal = strategy.ordinal();
        if (ordinal == 0) {
            i = 3;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i = 2;
        }
        this.minSampleSize = i;
        this.samples = new DataPointAtTime[20];
        this.reusableDataPointsArray = new float[20];
        this.reusableTimeArray = new float[20];
        this.reusableVelocityCoefficients = new float[3];
    }

    public final void addDataPoint(float f, long j) {
        int i = (this.index + 1) % 20;
        this.index = i;
        DataPointAtTime[] dataPointAtTimeArr = this.samples;
        DataPointAtTime dataPointAtTime = dataPointAtTimeArr[i];
        if (dataPointAtTime != null) {
            dataPointAtTime.time = j;
            dataPointAtTime.dataPoint = f;
        } else {
            DataPointAtTime dataPointAtTime2 = new DataPointAtTime();
            dataPointAtTime2.time = j;
            dataPointAtTime2.dataPoint = f;
            dataPointAtTimeArr[i] = dataPointAtTime2;
        }
    }

    public final float calculateVelocity() {
        Strategy strategy;
        float[] fArr;
        float[] fArr2;
        int i;
        float f;
        float f2;
        int i2 = this.index;
        DataPointAtTime[] dataPointAtTimeArr = this.samples;
        DataPointAtTime dataPointAtTime = dataPointAtTimeArr[i2];
        if (dataPointAtTime == null) {
            return 0.0f;
        }
        int i3 = 0;
        DataPointAtTime dataPointAtTime2 = dataPointAtTime;
        do {
            DataPointAtTime dataPointAtTime3 = dataPointAtTimeArr[i2];
            strategy = this.strategy;
            fArr = this.reusableDataPointsArray;
            fArr2 = this.reusableTimeArray;
            if (dataPointAtTime3 == null) {
                i = i3;
            } else {
                long j = dataPointAtTime.time;
                long j2 = dataPointAtTime3.time;
                float f3 = j - j2;
                i = i3;
                float abs = Math.abs(j2 - dataPointAtTime2.time);
                dataPointAtTime2 = strategy != Strategy.Lsq2 ? dataPointAtTime : dataPointAtTime3;
                if (f3 <= 100.0f && abs <= 40.0f) {
                    fArr[i] = dataPointAtTime3.dataPoint;
                    fArr2[i] = -f3;
                    if (i2 == 0) {
                        i2 = 20;
                    }
                    i2--;
                    i3 = i + 1;
                }
            }
            i3 = i;
            break;
        } while (i3 < 20);
        if (i3 < this.minSampleSize) {
            return 0.0f;
        }
        int ordinal = strategy.ordinal();
        if (ordinal == 0) {
            try {
                float[] fArr3 = this.reusableVelocityCoefficients;
                VelocityTrackerKt.polyFitLeastSquares(fArr2, fArr, i3, fArr3);
                f = fArr3[1];
            } catch (IllegalArgumentException unused) {
                f = 0.0f;
            }
            f2 = f;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            int i4 = i3 - 1;
            float f4 = fArr2[i4];
            int i5 = i4;
            float f5 = 0.0f;
            while (i5 > 0) {
                int i6 = i5 - 1;
                float f6 = fArr2[i6];
                if (f4 != f6) {
                    float f7 = (fArr[i5] - fArr[i6]) / (f4 - f6);
                    float abs2 = (Math.abs(f7) * (f7 - (Math.signum(f5) * ((float) Math.sqrt(Math.abs(f5) * 2))))) + f5;
                    if (i5 == i4) {
                        abs2 *= 0.5f;
                    }
                    f5 = abs2;
                }
                i5--;
                f4 = f6;
            }
            f2 = Math.signum(f5) * ((float) Math.sqrt(Math.abs(f5) * 2));
        }
        return f2 * 1000;
    }

    public final void resetTracking() {
        DataPointAtTime[] dataPointAtTimeArr = this.samples;
        Arrays.fill(dataPointAtTimeArr, 0, dataPointAtTimeArr.length, (Object) null);
        this.index = 0;
    }

    public /* synthetic */ VelocityTracker1D() {
        this(Strategy.Lsq2);
    }

    public final float calculateVelocity(float f) {
        if (f <= 0.0f) {
            InlineClassHelperKt.throwIllegalStateException("maximumVelocity should be a positive value. You specified=" + f);
        }
        float calculateVelocity = calculateVelocity();
        if (calculateVelocity == 0.0f || Float.isNaN(calculateVelocity)) {
            return 0.0f;
        }
        if (calculateVelocity > 0.0f) {
            return RangesKt.coerceAtMost(calculateVelocity, f);
        }
        return RangesKt.coerceAtLeast(calculateVelocity, -f);
    }
}
