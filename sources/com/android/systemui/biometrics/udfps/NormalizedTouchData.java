package com.android.systemui.biometrics.udfps;

import android.graphics.Rect;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NormalizedTouchData {
    public final long gestureStart;
    public final float major;
    public final float minor;
    public final float orientation;
    public final int pointerId;
    public final long time;
    public final float x;
    public final float y;

    public /* synthetic */ NormalizedTouchData() {
        this(-1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, 0L);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NormalizedTouchData)) {
            return false;
        }
        NormalizedTouchData normalizedTouchData = (NormalizedTouchData) obj;
        return this.pointerId == normalizedTouchData.pointerId && Float.compare(this.x, normalizedTouchData.x) == 0 && Float.compare(this.y, normalizedTouchData.y) == 0 && Float.compare(this.minor, normalizedTouchData.minor) == 0 && Float.compare(this.major, normalizedTouchData.major) == 0 && Float.compare(this.orientation, normalizedTouchData.orientation) == 0 && this.time == normalizedTouchData.time && this.gestureStart == normalizedTouchData.gestureStart;
    }

    public final int hashCode() {
        return Long.hashCode(this.gestureStart) + Scale$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Integer.hashCode(this.pointerId) * 31, this.x, 31), this.y, 31), this.minor, 31), this.major, 31), this.orientation, 31), 31, this.time);
    }

    public final boolean isWithinBounds(Rect rect) {
        float f = rect.left;
        float f2 = this.x;
        if (f <= f2 && rect.right >= f2) {
            float f3 = rect.top;
            float f4 = this.y;
            if (f3 <= f4 && rect.bottom >= f4) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return "NormalizedTouchData(pointerId=" + this.pointerId + ", x=" + this.x + ", y=" + this.y + ", minor=" + this.minor + ", major=" + this.major + ", orientation=" + this.orientation + ", time=" + this.time + ", gestureStart=" + this.gestureStart + ")";
    }

    public NormalizedTouchData(int i, float f, float f2, float f3, float f4, float f5, long j, long j2) {
        this.pointerId = i;
        this.x = f;
        this.y = f2;
        this.minor = f3;
        this.major = f4;
        this.orientation = f5;
        this.time = j;
        this.gestureStart = j2;
    }
}
