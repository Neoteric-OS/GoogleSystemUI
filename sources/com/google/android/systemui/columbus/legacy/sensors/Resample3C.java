package com.google.android.systemui.columbus.legacy.sensors;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Resample3C {
    public long mRawLastT;
    public float mRawLastX;
    public float mRawLastY;
    public float mRawLastZ;
    public long mResampledLastT;
    public float mResampledThisY;
    public float mResampledThisZ;
    public float mResampledThisX = 0.0f;
    public long mInterval = 0;

    public final Sample3C getResults() {
        float f = this.mResampledThisX;
        float f2 = this.mResampledThisY;
        float f3 = this.mResampledThisZ;
        long j = this.mResampledLastT;
        Sample3C sample3C = new Sample3C();
        Point3f point3f = new Point3f();
        point3f.x = 0.0f;
        point3f.y = 0.0f;
        point3f.z = 0.0f;
        sample3C.mPoint = point3f;
        point3f.x = f;
        point3f.y = f2;
        point3f.z = f3;
        sample3C.mT = j;
        return sample3C;
    }

    public final boolean update(float f, float f2, float f3, long j) {
        long j2 = this.mRawLastT;
        if (j == j2) {
            return false;
        }
        long j3 = this.mInterval;
        if (j3 <= 0) {
            j3 = j - j2;
        }
        long j4 = this.mResampledLastT + j3;
        if (j < j4) {
            this.mRawLastT = j;
            this.mRawLastX = f;
            this.mRawLastY = f2;
            this.mRawLastZ = f3;
            return false;
        }
        float f4 = (j4 - j2) / (j - j2);
        float f5 = this.mRawLastX;
        this.mResampledThisX = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, f5, f4, f5);
        float f6 = this.mRawLastY;
        this.mResampledThisY = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f6, f4, f6);
        float f7 = this.mRawLastZ;
        this.mResampledThisZ = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f3, f7, f4, f7);
        this.mResampledLastT = j4;
        if (j2 >= j4) {
            return true;
        }
        this.mRawLastT = j;
        this.mRawLastX = f;
        this.mRawLastY = f2;
        this.mRawLastZ = f3;
        return true;
    }
}
