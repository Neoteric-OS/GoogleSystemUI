package com.google.android.systemui.columbus.legacy.sensors;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Highpass1C {
    public float mPara = 1.0f;
    public float mLastX = 0.0f;
    public float mLastY = 0.0f;

    public final float update(float f) {
        float f2 = this.mPara;
        if (f2 == 1.0f) {
            return f;
        }
        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f, this.mLastX, f2, this.mLastY * f2);
        this.mLastY = m;
        this.mLastX = f;
        return m;
    }
}
