package androidx.compose.ui.unit;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ConstraintsKt {
    public static final long Constraints(int i, int i2, int i3, int i4) {
        if (!((i3 >= 0) & (i2 >= i) & (i4 >= i3) & (i >= 0))) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth must be >= than minWidth,\nmaxHeight must be >= than minHeight,\nminWidth and minHeight must be >= 0");
        }
        return createConstraints(i, i2, i3, i4);
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3, int i4, int i5) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return Constraints(i, i2, i3, i4);
    }

    public static final int bitsNeedForSizeUnchecked(int i) {
        int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i + 1);
        if (numberOfLeadingZeros >= 19) {
            return 13;
        }
        if (numberOfLeadingZeros >= 17) {
            return 15;
        }
        if (numberOfLeadingZeros >= 16) {
            return 16;
        }
        return numberOfLeadingZeros >= 14 ? 18 : 255;
    }

    /* renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m662constrain4WqzIAM(long j, long j2) {
        int i = (int) (j2 >> 32);
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        if (i < m657getMinWidthimpl) {
            i = m657getMinWidthimpl;
        }
        if (i <= m655getMaxWidthimpl) {
            m655getMaxWidthimpl = i;
        }
        int i2 = (int) (j2 & 4294967295L);
        int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        if (i2 < m656getMinHeightimpl) {
            i2 = m656getMinHeightimpl;
        }
        if (i2 <= m654getMaxHeightimpl) {
            m654getMaxHeightimpl = i2;
        }
        return (m655getMaxWidthimpl << 32) | (m654getMaxHeightimpl & 4294967295L);
    }

    /* renamed from: constrain-N9IONVI, reason: not valid java name */
    public static final long m663constrainN9IONVI(long j, long j2) {
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        int m657getMinWidthimpl2 = Constraints.m657getMinWidthimpl(j2);
        if (m657getMinWidthimpl2 < m657getMinWidthimpl) {
            m657getMinWidthimpl2 = m657getMinWidthimpl;
        }
        if (m657getMinWidthimpl2 > m655getMaxWidthimpl) {
            m657getMinWidthimpl2 = m655getMaxWidthimpl;
        }
        int m655getMaxWidthimpl2 = Constraints.m655getMaxWidthimpl(j2);
        if (m655getMaxWidthimpl2 >= m657getMinWidthimpl) {
            m657getMinWidthimpl = m655getMaxWidthimpl2;
        }
        if (m657getMinWidthimpl <= m655getMaxWidthimpl) {
            m655getMaxWidthimpl = m657getMinWidthimpl;
        }
        int m656getMinHeightimpl2 = Constraints.m656getMinHeightimpl(j2);
        if (m656getMinHeightimpl2 < m656getMinHeightimpl) {
            m656getMinHeightimpl2 = m656getMinHeightimpl;
        }
        if (m656getMinHeightimpl2 > m654getMaxHeightimpl) {
            m656getMinHeightimpl2 = m654getMaxHeightimpl;
        }
        int m654getMaxHeightimpl2 = Constraints.m654getMaxHeightimpl(j2);
        if (m654getMaxHeightimpl2 >= m656getMinHeightimpl) {
            m656getMinHeightimpl = m654getMaxHeightimpl2;
        }
        if (m656getMinHeightimpl <= m654getMaxHeightimpl) {
            m654getMaxHeightimpl = m656getMinHeightimpl;
        }
        return Constraints(m657getMinWidthimpl2, m655getMaxWidthimpl, m656getMinHeightimpl2, m654getMaxHeightimpl);
    }

    /* renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final int m664constrainHeightK40F9xA(long j, int i) {
        int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        if (i < m656getMinHeightimpl) {
            i = m656getMinHeightimpl;
        }
        return i > m654getMaxHeightimpl ? m654getMaxHeightimpl : i;
    }

    /* renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final int m665constrainWidthK40F9xA(long j, int i) {
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        if (i < m657getMinWidthimpl) {
            i = m657getMinWidthimpl;
        }
        return i > m655getMaxWidthimpl ? m655getMaxWidthimpl : i;
    }

    public static final long createConstraints(int i, int i2, int i3, int i4) {
        int i5 = i4 == Integer.MAX_VALUE ? i3 : i4;
        int bitsNeedForSizeUnchecked = bitsNeedForSizeUnchecked(i5);
        int i6 = i2 == Integer.MAX_VALUE ? i : i2;
        int bitsNeedForSizeUnchecked2 = bitsNeedForSizeUnchecked(i6);
        if (bitsNeedForSizeUnchecked + bitsNeedForSizeUnchecked2 > 31) {
            throwInvalidConstraintException(i6, i5);
        }
        int i7 = i2 + 1;
        int i8 = i4 + 1;
        int i9 = bitsNeedForSizeUnchecked2 - 13;
        return ((i7 & (~(i7 >> 31))) << 33) | ((i9 >> 1) + (i9 & 1)) | (i << 2) | (i3 << (bitsNeedForSizeUnchecked2 + 2)) | ((i8 & (~(i8 >> 31))) << (bitsNeedForSizeUnchecked2 + 33));
    }

    /* renamed from: offset-NN6Ew-U, reason: not valid java name */
    public static final long m666offsetNN6EwU(int i, int i2, long j) {
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j) + i;
        if (m657getMinWidthimpl < 0) {
            m657getMinWidthimpl = 0;
        }
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        if (m655getMaxWidthimpl != Integer.MAX_VALUE && (m655getMaxWidthimpl = m655getMaxWidthimpl + i) < 0) {
            m655getMaxWidthimpl = 0;
        }
        int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j) + i2;
        if (m656getMinHeightimpl < 0) {
            m656getMinHeightimpl = 0;
        }
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        return Constraints(m657getMinWidthimpl, m655getMaxWidthimpl, m656getMinHeightimpl, (m654getMaxHeightimpl == Integer.MAX_VALUE || (m654getMaxHeightimpl = m654getMaxHeightimpl + i2) >= 0) ? m654getMaxHeightimpl : 0);
    }

    /* renamed from: offset-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ long m667offsetNN6EwU$default(int i, int i2, long j, int i3) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m666offsetNN6EwU(i, i2, j);
    }

    public static final void throwInvalidConstraintException(int i, int i2) {
        throw new IllegalArgumentException(MutableVectorKt$$ExternalSyntheticOutline0.m(i, i2, "Can't represent a width of ", " and height of ", " in Constraints"));
    }

    public static final Void throwInvalidConstraintsSizeException(int i) {
        throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Can't represent a size of ", " in Constraints", i));
    }
}
