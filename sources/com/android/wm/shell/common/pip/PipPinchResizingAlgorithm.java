package com.android.wm.shell.common.pip;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipPinchResizingAlgorithm {
    public final PointF mTmpDownVector = new PointF();
    public final PointF mTmpLastVector = new PointF();
    public final PointF mTmpDownCentroid = new PointF();
    public final PointF mTmpLastCentroid = new PointF();

    public final float calculateBoundsAndAngle(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, Point point, Point point2, Rect rect, Rect rect2) {
        float f;
        float hypot = (float) Math.hypot(pointF2.x - pointF.x, pointF2.y - pointF.y);
        float hypot2 = (float) Math.hypot(pointF4.x - pointF3.x, pointF4.y - pointF3.y);
        float max = Math.max(point.x / rect.width(), point.y / rect.height());
        float min = Math.min(point2.x / rect.width(), point2.y / rect.height());
        float f2 = hypot2 / hypot;
        float f3 = max - f2;
        if (f3 <= 0.0f) {
            f3 = 0.0f;
        }
        float f4 = f2 - min;
        if (f4 <= 0.0f) {
            f4 = 0.0f;
        }
        float max2 = Math.max(max - (f3 * 0.25f), Math.min((f4 * 0.25f) + min, f2));
        rect2.set(rect);
        if (max2 != 1.0f) {
            int centerX = rect2.centerX();
            int centerY = rect2.centerY();
            rect2.offset(-centerX, -centerY);
            rect2.scale(max2);
            rect2.offset(centerX, centerY);
        }
        this.mTmpDownCentroid.set((pointF2.x + pointF.x) / 2.0f, (pointF2.y + pointF.y) / 2.0f);
        this.mTmpLastCentroid.set((pointF4.x + pointF3.x) / 2.0f, (pointF4.y + pointF3.y) / 2.0f);
        PointF pointF5 = this.mTmpLastCentroid;
        float f5 = pointF5.x;
        PointF pointF6 = this.mTmpDownCentroid;
        rect2.offset((int) (f5 - pointF6.x), (int) (pointF5.y - pointF6.y));
        this.mTmpDownVector.set(pointF2.x - pointF.x, pointF2.y - pointF.y);
        this.mTmpLastVector.set(pointF4.x - pointF3.x, pointF4.y - pointF3.y);
        PointF pointF7 = this.mTmpDownVector;
        PointF pointF8 = this.mTmpLastVector;
        float f6 = pointF7.x;
        float f7 = pointF8.y;
        float f8 = pointF7.y;
        float f9 = pointF8.x;
        float degrees = (float) Math.toDegrees((float) Math.atan2((f6 * f7) - (f8 * f9), (f8 * f7) + (f6 * f9)));
        float signum = Math.signum(degrees);
        if (Float.compare(degrees, 0.0f) == 0) {
            f = 0.0f;
        } else {
            float f10 = degrees / 45.0f;
            float abs = f10 / Math.abs(f10);
            float abs2 = Math.abs(f10) - 1.0f;
            float f11 = ((abs2 * abs2 * abs2) + 1.0f) * abs;
            if (Math.abs(f11) >= 1.0f) {
                f11 /= Math.abs(f11);
            }
            f = f11 * 0.4f * 45.0f;
        }
        return Math.max(0.0f, Math.abs(f) - 5.0f) * signum;
    }
}
