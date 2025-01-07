package androidx.compose.ui.unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Density extends FontScaling {
    float getDensity();

    /* renamed from: roundToPx-0680j_4 */
    default int mo45roundToPx0680j_4(float f) {
        float mo51toPx0680j_4 = mo51toPx0680j_4(f);
        if (Float.isInfinite(mo51toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(mo51toPx0680j_4);
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo48toDpu2uoSUM(int i) {
        return i / getDensity();
    }

    /* renamed from: toDpSize-k-rfVVM */
    default long mo49toDpSizekrfVVM(long j) {
        if (j != 9205357640488583168L) {
            return DpKt.m670DpSizeYgX7TsA(mo47toDpu2uoSUM(Float.intBitsToFloat((int) (j >> 32))), mo47toDpu2uoSUM(Float.intBitsToFloat((int) (j & 4294967295L))));
        }
        return 9205357640488583168L;
    }

    /* renamed from: toPx--R2X_6o */
    default float mo50toPxR2X_6o(long j) {
        if (TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(j), 4294967296L)) {
            return mo51toPx0680j_4(mo46toDpGaN1DYA(j));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    /* renamed from: toPx-0680j_4 */
    default float mo51toPx0680j_4(float f) {
        return getDensity() * f;
    }

    /* renamed from: toSize-XkaWNTQ */
    default long mo52toSizeXkaWNTQ(long j) {
        if (j == 9205357640488583168L) {
            return 9205357640488583168L;
        }
        float mo51toPx0680j_4 = mo51toPx0680j_4(DpSize.m672getWidthD9Ej5fM(j));
        float mo51toPx0680j_42 = mo51toPx0680j_4(DpSize.m671getHeightD9Ej5fM(j));
        return (Float.floatToRawIntBits(mo51toPx0680j_42) & 4294967295L) | (Float.floatToRawIntBits(mo51toPx0680j_4) << 32);
    }

    /* renamed from: toSp-kPz2Gy4 */
    default long mo54toSpkPz2Gy4(float f) {
        return mo53toSp0xMU5do(mo47toDpu2uoSUM(f));
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo47toDpu2uoSUM(float f) {
        return f / getDensity();
    }
}
