package androidx.compose.ui.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScaleFactorKt {
    public static final long ScaleFactor(float f, float f2) {
        long floatToRawIntBits = (Float.floatToRawIntBits(f2) & 4294967295L) | (Float.floatToRawIntBits(f) << 32);
        int i = ScaleFactor.$r8$clinit;
        return floatToRawIntBits;
    }

    /* renamed from: times-UQTWf7w, reason: not valid java name */
    public static final long m501timesUQTWf7w(long j, long j2) {
        float m499getScaleXimpl = ScaleFactor.m499getScaleXimpl(j2) * Float.intBitsToFloat((int) (j >> 32));
        float m500getScaleYimpl = ScaleFactor.m500getScaleYimpl(j2) * Float.intBitsToFloat((int) (j & 4294967295L));
        return (Float.floatToRawIntBits(m500getScaleYimpl) & 4294967295L) | (Float.floatToRawIntBits(m499getScaleXimpl) << 32);
    }
}
