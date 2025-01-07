package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShapeContainingUtilKt {
    public static final boolean isInOutline(Outline outline, float f, float f2) {
        float f3;
        float f4;
        boolean m573isWithinEllipseVE1yxkc;
        float f5 = f;
        if (!(outline instanceof Outline.Rectangle)) {
            if (!(outline instanceof Outline.Rounded)) {
                if (outline instanceof Outline.Generic) {
                    return isInPath(((Outline.Generic) outline).path, f5, f2);
                }
                throw new NoWhenBranchMatchedException();
            }
            RoundRect roundRect = ((Outline.Rounded) outline).roundRect;
            float f6 = roundRect.left;
            if (f5 < f6) {
                return false;
            }
            float f7 = roundRect.right;
            if (f5 >= f7) {
                return false;
            }
            float f8 = roundRect.top;
            if (f2 < f8) {
                return false;
            }
            float f9 = roundRect.bottom;
            if (f2 >= f9) {
                return false;
            }
            long j = roundRect.topLeftCornerRadius;
            int i = (int) (j >> 32);
            float intBitsToFloat = Float.intBitsToFloat(i);
            long j2 = roundRect.topRightCornerRadius;
            int i2 = (int) (j2 >> 32);
            if (Float.intBitsToFloat(i2) + intBitsToFloat <= roundRect.getWidth()) {
                long j3 = roundRect.bottomLeftCornerRadius;
                int i3 = (int) (j3 >> 32);
                float intBitsToFloat2 = Float.intBitsToFloat(i3);
                long j4 = roundRect.bottomRightCornerRadius;
                int i4 = (int) (j4 >> 32);
                if (Float.intBitsToFloat(i4) + intBitsToFloat2 <= roundRect.getWidth()) {
                    int i5 = (int) (j & 4294967295L);
                    int i6 = (int) (j3 & 4294967295L);
                    if (Float.intBitsToFloat(i6) + Float.intBitsToFloat(i5) <= roundRect.getHeight()) {
                        int i7 = (int) (j2 & 4294967295L);
                        int i8 = (int) (4294967295L & j4);
                        if (Float.intBitsToFloat(i8) + Float.intBitsToFloat(i7) <= roundRect.getHeight()) {
                            float intBitsToFloat3 = Float.intBitsToFloat(i) + f6;
                            float intBitsToFloat4 = Float.intBitsToFloat(i5) + f8;
                            float intBitsToFloat5 = f7 - Float.intBitsToFloat(i2);
                            float intBitsToFloat6 = Float.intBitsToFloat(i7) + f8;
                            float intBitsToFloat7 = f7 - Float.intBitsToFloat(i4);
                            float intBitsToFloat8 = f9 - Float.intBitsToFloat(i8);
                            float intBitsToFloat9 = f9 - Float.intBitsToFloat(i6);
                            float intBitsToFloat10 = f6 + Float.intBitsToFloat(i3);
                            if (f < intBitsToFloat3) {
                                f4 = f2;
                                if (f4 < intBitsToFloat4) {
                                    m573isWithinEllipseVE1yxkc = m573isWithinEllipseVE1yxkc(f, f2, intBitsToFloat3, intBitsToFloat4, roundRect.topLeftCornerRadius);
                                    return m573isWithinEllipseVE1yxkc;
                                }
                            } else {
                                f4 = f2;
                            }
                            if (f < intBitsToFloat10 && f4 > intBitsToFloat9) {
                                m573isWithinEllipseVE1yxkc = m573isWithinEllipseVE1yxkc(f, f2, intBitsToFloat10, intBitsToFloat9, roundRect.bottomLeftCornerRadius);
                            } else if (f > intBitsToFloat5 && f4 < intBitsToFloat6) {
                                m573isWithinEllipseVE1yxkc = m573isWithinEllipseVE1yxkc(f, f2, intBitsToFloat5, intBitsToFloat6, roundRect.topRightCornerRadius);
                            } else if (f > intBitsToFloat7 && f4 > intBitsToFloat8) {
                                m573isWithinEllipseVE1yxkc = m573isWithinEllipseVE1yxkc(f, f2, intBitsToFloat7, intBitsToFloat8, roundRect.bottomRightCornerRadius);
                            }
                            return m573isWithinEllipseVE1yxkc;
                        }
                    }
                }
                f5 = f;
                f3 = f2;
            } else {
                f3 = f2;
            }
            AndroidPath Path = AndroidPath_androidKt.Path();
            Path.addRoundRect$default(Path, roundRect);
            return isInPath(Path, f5, f3);
        }
        Rect rect = ((Outline.Rectangle) outline).rect;
        if (rect.left > f5 || f5 >= rect.right || rect.top > f2 || f2 >= rect.bottom) {
            return false;
        }
        return true;
    }

    public static final boolean isInPath(Path path, float f, float f2) {
        Rect rect = new Rect(f - 0.005f, f2 - 0.005f, f + 0.005f, f2 + 0.005f);
        AndroidPath Path = AndroidPath_androidKt.Path();
        Path.addRect$default(Path, rect);
        AndroidPath Path2 = AndroidPath_androidKt.Path();
        Path2.m353opN5in7k0(path, Path, 1);
        boolean isEmpty = Path2.internalPath.isEmpty();
        Path2.reset();
        Path.reset();
        return !isEmpty;
    }

    /* renamed from: isWithinEllipse-VE1yxkc, reason: not valid java name */
    public static final boolean m573isWithinEllipseVE1yxkc(float f, float f2, float f3, float f4, long j) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        return ((f6 * f6) / (intBitsToFloat2 * intBitsToFloat2)) + ((f5 * f5) / (intBitsToFloat * intBitsToFloat)) <= 1.0f;
    }
}
