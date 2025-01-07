package com.android.systemui.qs;

import android.view.animation.BaseInterpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PathInterpolatorBuilder {
    public float[] mDist;
    public float[] mX;
    public float[] mY;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PathInterpolator extends BaseInterpolator {
        public final float[] mX;
        public final float[] mY;

        public PathInterpolator(float[] fArr, float[] fArr2) {
            this.mX = fArr;
            this.mY = fArr2;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            if (f <= 0.0f) {
                return 0.0f;
            }
            if (f >= 1.0f) {
                return 1.0f;
            }
            int length = this.mX.length - 1;
            int i = 0;
            while (length - i > 1) {
                int i2 = (i + length) / 2;
                if (f < this.mX[i2]) {
                    length = i2;
                } else {
                    i = i2;
                }
            }
            float[] fArr = this.mX;
            float f2 = fArr[length];
            float f3 = fArr[i];
            float f4 = f2 - f3;
            if (f4 == 0.0f) {
                return this.mY[i];
            }
            float[] fArr2 = this.mY;
            float f5 = fArr2[i];
            return AndroidFlingSpline$$ExternalSyntheticOutline0.m(fArr2[length], f5, (f - f3) / f4, f5);
        }
    }
}
