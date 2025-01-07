package kotlin.time;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Duration implements Comparable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long INFINITE;
    public static final long NEG_INFINITE;

    static {
        int i = DurationJvmKt.$r8$clinit;
        INFINITE = DurationKt.durationOfMillis(4611686018427387903L);
        NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    public static final long m1774addValuesMixedRangesUwyO8pc(long j, long j2) {
        long j3 = 1000000;
        long j4 = j2 / j3;
        long j5 = j + j4;
        if (!new LongRange(-4611686018426L, 4611686018426L).contains(j5)) {
            return DurationKt.durationOfMillis(RangesKt.coerceIn(j5, -4611686018427387903L, 4611686018427387903L));
        }
        return DurationKt.durationOfNanos((j5 * j3) + (j2 - (j4 * j3)));
    }

    /* renamed from: appendFractional-impl, reason: not valid java name */
    public static final void m1775appendFractionalimpl(StringBuilder sb, int i, int i2, int i3, String str) {
        CharSequence charSequence;
        sb.append(i);
        if (i2 != 0) {
            sb.append('.');
            String valueOf = String.valueOf(i2);
            if (i3 < 0) {
                throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Desired length ", " is less than zero.", i3));
            }
            if (i3 <= valueOf.length()) {
                charSequence = valueOf.subSequence(0, valueOf.length());
            } else {
                StringBuilder sb2 = new StringBuilder(i3);
                IntProgressionIterator it = new IntRange(1, i3 - valueOf.length(), 1).iterator();
                while (it.hasNext) {
                    it.nextInt();
                    sb2.append('0');
                }
                sb2.append((CharSequence) valueOf);
                charSequence = sb2;
            }
            String obj = charSequence.toString();
            int i4 = -1;
            int length = obj.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i5 = length - 1;
                    if (obj.charAt(length) != '0') {
                        i4 = length;
                        break;
                    } else if (i5 < 0) {
                        break;
                    } else {
                        length = i5;
                    }
                }
            }
            int i6 = i4 + 1;
            if (i6 < 3) {
                sb.append((CharSequence) obj, 0, i6);
            } else {
                sb.append((CharSequence) obj, 0, ((i4 + 3) / 3) * 3);
            }
        }
        sb.append(str);
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m1776compareToLRDsOJo(long j, long j2) {
        long j3 = j ^ j2;
        if (j3 >= 0 && (((int) j3) & 1) != 0) {
            int i = (((int) j) & 1) - (((int) j2) & 1);
            return j < 0 ? -i : i;
        }
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    /* renamed from: getInWholeMilliseconds-impl, reason: not valid java name */
    public static final long m1777getInWholeMillisecondsimpl(long j) {
        return ((((int) j) & 1) != 1 || m1779isInfiniteimpl(j)) ? m1782toLongimpl(j, DurationUnit.MILLISECONDS) : j >> 1;
    }

    /* renamed from: getStorageUnit-impl, reason: not valid java name */
    public static final DurationUnit m1778getStorageUnitimpl(long j) {
        return (((int) j) & 1) == 0 ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m1779isInfiniteimpl(long j) {
        return j == INFINITE || j == NEG_INFINITE;
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m1780plusLRDsOJo(long j, long j2) {
        if (m1779isInfiniteimpl(j)) {
            if (!m1779isInfiniteimpl(j2) || (j2 ^ j) >= 0) {
                return j;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (m1779isInfiniteimpl(j2)) {
            return j2;
        }
        int i = ((int) j) & 1;
        if (i != (((int) j2) & 1)) {
            return i == 1 ? m1774addValuesMixedRangesUwyO8pc(j >> 1, j2 >> 1) : m1774addValuesMixedRangesUwyO8pc(j2 >> 1, j >> 1);
        }
        long j3 = (j >> 1) + (j2 >> 1);
        return i == 0 ? new LongRange(-4611686018426999999L, 4611686018426999999L).contains(j3) ? DurationKt.durationOfNanos(j3) : DurationKt.durationOfMillis(j3 / 1000000) : DurationKt.durationOfMillisNormalized(j3);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m1781toDoubleimpl(long j, DurationUnit durationUnit) {
        if (j == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (j == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(j >> 1, m1778getStorageUnitimpl(j), durationUnit);
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m1782toLongimpl(long j, DurationUnit durationUnit) {
        if (j == INFINITE) {
            return Long.MAX_VALUE;
        }
        if (j == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return durationUnit.getTimeUnit$kotlin_stdlib().convert(j >> 1, m1778getStorageUnitimpl(j).getTimeUnit$kotlin_stdlib());
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m1783toStringimpl(long j) {
        boolean z;
        int m1782toLongimpl;
        int i;
        int i2;
        long j2 = j;
        if (j2 == 0) {
            return "0s";
        }
        if (j2 == INFINITE) {
            return "Infinity";
        }
        if (j2 == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean z2 = j2 < 0;
        StringBuilder sb = new StringBuilder();
        if (z2) {
            sb.append('-');
        }
        if (j2 < 0) {
            j2 = (((int) j2) & 1) + ((-(j2 >> 1)) << 1);
            int i3 = DurationJvmKt.$r8$clinit;
        }
        long m1782toLongimpl2 = m1782toLongimpl(j2, DurationUnit.DAYS);
        int m1782toLongimpl3 = m1779isInfiniteimpl(j2) ? 0 : (int) (m1782toLongimpl(j2, DurationUnit.HOURS) % 24);
        if (m1779isInfiniteimpl(j2)) {
            z = z2;
            m1782toLongimpl = 0;
        } else {
            z = z2;
            m1782toLongimpl = (int) (m1782toLongimpl(j2, DurationUnit.MINUTES) % 60);
        }
        int m1782toLongimpl4 = m1779isInfiniteimpl(j2) ? 0 : (int) (m1782toLongimpl(j2, DurationUnit.SECONDS) % 60);
        if (m1779isInfiniteimpl(j2)) {
            i = 0;
        } else {
            i = (int) ((((int) j2) & 1) == 1 ? ((j2 >> 1) % 1000) * 1000000 : (j2 >> 1) % 1000000000);
        }
        boolean z3 = m1782toLongimpl2 != 0;
        boolean z4 = m1782toLongimpl3 != 0;
        boolean z5 = m1782toLongimpl != 0;
        boolean z6 = (m1782toLongimpl4 == 0 && i == 0) ? false : true;
        if (z3) {
            sb.append(m1782toLongimpl2);
            sb.append('d');
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (z4 || (z3 && (z5 || z6))) {
            int i4 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m1782toLongimpl3);
            sb.append('h');
            i2 = i4;
        }
        if (z5 || (z6 && (z4 || z3))) {
            int i5 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m1782toLongimpl);
            sb.append('m');
            i2 = i5;
        }
        if (z6) {
            int i6 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            if (m1782toLongimpl4 != 0 || z3 || z4 || z5) {
                m1775appendFractionalimpl(sb, m1782toLongimpl4, i, 9, "s");
            } else if (i >= 1000000) {
                m1775appendFractionalimpl(sb, i / 1000000, i % 1000000, 6, "ms");
            } else if (i >= 1000) {
                m1775appendFractionalimpl(sb, i / 1000, i % 1000, 3, "us");
            } else {
                sb.append(i);
                sb.append("ns");
            }
            i2 = i6;
        }
        if (z && i2 > 1) {
            sb.insert(1, '(').append(')');
        }
        return sb.toString();
    }
}
