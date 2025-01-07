package androidx.core.animation;

import android.graphics.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathInterpolator implements Interpolator {
    public float[] mData;

    public PathInterpolator(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        initPath(path);
    }

    public static boolean floatEquals(float f, float f2) {
        return Math.abs(f - f2) < 0.01f;
    }

    @Override // androidx.core.animation.Interpolator
    public final float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int length = (this.mData.length / 3) - 1;
        int i = 0;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < getXAtIndex(i2)) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float xAtIndex = getXAtIndex(length) - getXAtIndex(i);
        if (xAtIndex == 0.0f) {
            return getYAtIndex(i);
        }
        float xAtIndex2 = (f - getXAtIndex(i)) / xAtIndex;
        float yAtIndex = getYAtIndex(i);
        return ((getYAtIndex(length) - yAtIndex) * xAtIndex2) + yAtIndex;
    }

    public final float getXAtIndex(int i) {
        return this.mData[(i * 3) + 1];
    }

    public final float getYAtIndex(int i) {
        return this.mData[(i * 3) + 2];
    }

    public final void initPath(Path path) {
        float[] approximate = path.approximate(0.002f);
        this.mData = approximate;
        int length = approximate.length / 3;
        int i = 0;
        float f = 0.0f;
        if (floatEquals(getXAtIndex(0), 0.0f) && floatEquals(getYAtIndex(0), 0.0f)) {
            int i2 = length - 1;
            if (floatEquals(getXAtIndex(i2), 1.0f) && floatEquals(getYAtIndex(i2), 1.0f)) {
                float f2 = 0.0f;
                while (i < length) {
                    float f3 = this.mData[i * 3];
                    float xAtIndex = getXAtIndex(i);
                    if (f3 == f && xAtIndex != f2) {
                        throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis.");
                    }
                    if (xAtIndex < f2) {
                        throw new IllegalArgumentException("The Path cannot loop back on itself.");
                    }
                    i++;
                    f = f3;
                    f2 = xAtIndex;
                }
                return;
            }
        }
        throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");
    }
}
