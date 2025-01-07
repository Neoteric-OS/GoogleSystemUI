package com.android.systemui.biometrics.udfps;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.EllipseOverlapDetectorParams;
import kotlin.internal.ProgressionUtilKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EllipseOverlapDetector implements OverlapDetector {
    public final EllipseOverlapDetectorParams params;

    public EllipseOverlapDetector(EllipseOverlapDetectorParams ellipseOverlapDetectorParams) {
        this.params = ellipseOverlapDetectorParams;
    }

    @Override // com.android.systemui.biometrics.udfps.OverlapDetector
    public final boolean isGoodOverlap(NormalizedTouchData normalizedTouchData, Rect rect, Rect rect2) {
        EllipseOverlapDetectorParams ellipseOverlapDetectorParams;
        boolean z;
        int i;
        int i2;
        int i3;
        String str;
        String str2;
        int i4;
        int i5;
        int i6;
        Rect rect3 = rect;
        if (normalizedTouchData.isWithinBounds(rect)) {
            return true;
        }
        if (!normalizedTouchData.isWithinBounds(rect2)) {
            return false;
        }
        int i7 = rect3.top;
        int i8 = rect3.bottom;
        EllipseOverlapDetectorParams ellipseOverlapDetectorParams2 = this.params;
        int i9 = ellipseOverlapDetectorParams2.stepSize;
        String str3 = ".";
        String str4 = "Step must be positive, was: ";
        if (i9 <= 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Step must be positive, was: ", ".", i9));
        }
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i7, i8, i9);
        if (i7 <= progressionLastElement) {
            z = false;
            i = 0;
            i2 = 0;
            while (true) {
                int i10 = rect3.left;
                int i11 = rect3.right;
                int i12 = ellipseOverlapDetectorParams2.stepSize;
                if (i12 <= 0) {
                    throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m(str4, str3, i12));
                }
                int progressionLastElement2 = ProgressionUtilKt.getProgressionLastElement(i10, i11, i12);
                if (i10 <= progressionLastElement2) {
                    while (true) {
                        int centerX = rect.centerX();
                        int centerY = rect.centerY();
                        int width = rect.width() / 2;
                        int i13 = centerX - i10;
                        int i14 = centerY - i7;
                        int i15 = (i14 * i14) + (i13 * i13);
                        float f = i15;
                        str = str3;
                        str2 = str4;
                        float f2 = width * ellipseOverlapDetectorParams2.targetSize;
                        SensorPixelPosition sensorPixelPosition = f <= f2 * f2 ? SensorPixelPosition.TARGET : i15 <= width * width ? SensorPixelPosition.SENSOR : SensorPixelPosition.OUTSIDE;
                        if (sensorPixelPosition != SensorPixelPosition.OUTSIDE) {
                            int i16 = i + 1;
                            Point point = new Point(i10, i7);
                            float f3 = normalizedTouchData.orientation;
                            float cos = (float) Math.cos(f3);
                            float f4 = point.x;
                            float f5 = normalizedTouchData.x;
                            float f6 = (f4 - f5) * cos;
                            EllipseOverlapDetectorParams ellipseOverlapDetectorParams3 = ellipseOverlapDetectorParams2;
                            i3 = i9;
                            double d = f3;
                            i5 = progressionLastElement;
                            float sin = (float) Math.sin(d);
                            float f7 = point.y;
                            ellipseOverlapDetectorParams = ellipseOverlapDetectorParams3;
                            float f8 = normalizedTouchData.y;
                            float f9 = (f7 - f8) * sin;
                            int i17 = i10;
                            i6 = progressionLastElement2;
                            float sin2 = (point.x - f5) * ((float) Math.sin(d));
                            float cos2 = (point.y - f8) * ((float) Math.cos(d));
                            float f10 = f6 + f9;
                            float f11 = 2;
                            float f12 = normalizedTouchData.minor / f11;
                            float f13 = sin2 - cos2;
                            float f14 = normalizedTouchData.major / f11;
                            if (((f13 * f13) / (f14 * f14)) + ((f10 * f10) / (f12 * f12)) <= 1.0f) {
                                i2++;
                                z = (sensorPixelPosition == SensorPixelPosition.TARGET) | z;
                            }
                            i = i16;
                            i10 = i17;
                        } else {
                            i5 = progressionLastElement;
                            ellipseOverlapDetectorParams = ellipseOverlapDetectorParams2;
                            i3 = i9;
                            i6 = progressionLastElement2;
                        }
                        if (i10 == i6) {
                            break;
                        }
                        i10 += i12;
                        progressionLastElement2 = i6;
                        str3 = str;
                        str4 = str2;
                        i9 = i3;
                        progressionLastElement = i5;
                        ellipseOverlapDetectorParams2 = ellipseOverlapDetectorParams;
                    }
                    i4 = i5;
                } else {
                    ellipseOverlapDetectorParams = ellipseOverlapDetectorParams2;
                    i3 = i9;
                    str = str3;
                    str2 = str4;
                    i4 = progressionLastElement;
                }
                if (i7 == i4) {
                    break;
                }
                i7 += i3;
                progressionLastElement = i4;
                str3 = str;
                str4 = str2;
                i9 = i3;
                ellipseOverlapDetectorParams2 = ellipseOverlapDetectorParams;
                rect3 = rect;
            }
        } else {
            ellipseOverlapDetectorParams = ellipseOverlapDetectorParams2;
            z = false;
            i = 0;
            i2 = 0;
        }
        return ((float) i2) / ((float) i) >= ellipseOverlapDetectorParams.minOverlap && z;
    }
}
