package androidx.constraintlayout.core.motion.utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyCycleOscillator$WavePoint {
    public final float mOffset;
    public final float mPeriod;
    public final float mPhase;
    public final int mPosition;
    public final float mValue;

    public KeyCycleOscillator$WavePoint(float f, float f2, float f3, float f4, int i) {
        this.mPosition = i;
        this.mValue = f4;
        this.mOffset = f2;
        this.mPeriod = f;
        this.mPhase = f3;
    }
}
