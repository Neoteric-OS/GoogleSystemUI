package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeakDetector {
    public float mNoiseTolerate;
    public int mPeakId = -1;
    public long mTimestamp = 0;
    public float mAmplitude = 0.0f;
    public int mWindowSize = 0;
    public float mMinNoiseTolerate = 0.0f;
    public float mAmplitudeReference = 0.0f;

    public final void update(float f, long j) {
        int i = this.mPeakId - 1;
        this.mPeakId = i;
        if (i < 0) {
            this.mAmplitude = 0.0f;
            this.mAmplitudeReference = 0.0f;
            this.mTimestamp = 0L;
            this.mPeakId = 0;
        }
        this.mNoiseTolerate = this.mMinNoiseTolerate;
        float max = Math.max(this.mAmplitude, f) / 5.0f;
        if (max > this.mNoiseTolerate) {
            this.mNoiseTolerate = max;
        }
        float f2 = this.mAmplitudeReference - f;
        if (f2 >= 0.0f) {
            if (f2 > this.mNoiseTolerate) {
                this.mAmplitudeReference = f;
                return;
            }
            return;
        }
        this.mAmplitudeReference = f;
        long j2 = this.mTimestamp;
        long j3 = j - j2;
        if ((j2 == 0 || (j3 < 120000000 && this.mAmplitude < f)) && f >= this.mNoiseTolerate) {
            this.mPeakId = this.mWindowSize - 1;
            this.mAmplitude = f;
            this.mTimestamp = j;
        }
    }
}
