package androidx.compose.ui.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Path {
    static void addRect$default(Path path, Rect rect) {
        AndroidPath androidPath = (AndroidPath) path;
        float f = rect.left;
        boolean isNaN = Float.isNaN(f);
        float f2 = rect.bottom;
        float f3 = rect.right;
        float f4 = rect.top;
        if (isNaN || Float.isNaN(f4) || Float.isNaN(f3) || Float.isNaN(f2)) {
            AndroidPath_androidKt.throwIllegalStateException("Invalid rectangle, make sure no value is NaN");
        }
        if (androidPath.rectF == null) {
            androidPath.rectF = new RectF();
        }
        RectF rectF = androidPath.rectF;
        Intrinsics.checkNotNull(rectF);
        rectF.set(f, f4, f3, f2);
        android.graphics.Path path2 = androidPath.internalPath;
        RectF rectF2 = androidPath.rectF;
        Intrinsics.checkNotNull(rectF2);
        path2.addRect(rectF2, Path.Direction.CCW);
    }

    static void addRoundRect$default(Path path, RoundRect roundRect) {
        AndroidPath androidPath = (AndroidPath) path;
        if (androidPath.rectF == null) {
            androidPath.rectF = new RectF();
        }
        RectF rectF = androidPath.rectF;
        Intrinsics.checkNotNull(rectF);
        float f = roundRect.bottom;
        rectF.set(roundRect.left, roundRect.top, roundRect.right, f);
        if (androidPath.radii == null) {
            androidPath.radii = new float[8];
        }
        float[] fArr = androidPath.radii;
        Intrinsics.checkNotNull(fArr);
        long j = roundRect.topLeftCornerRadius;
        fArr[0] = Float.intBitsToFloat((int) (j >> 32));
        fArr[1] = Float.intBitsToFloat((int) (j & 4294967295L));
        long j2 = roundRect.topRightCornerRadius;
        fArr[2] = Float.intBitsToFloat((int) (j2 >> 32));
        fArr[3] = Float.intBitsToFloat((int) (j2 & 4294967295L));
        long j3 = roundRect.bottomRightCornerRadius;
        fArr[4] = Float.intBitsToFloat((int) (j3 >> 32));
        fArr[5] = Float.intBitsToFloat((int) (j3 & 4294967295L));
        long j4 = roundRect.bottomLeftCornerRadius;
        fArr[6] = Float.intBitsToFloat((int) (j4 >> 32));
        fArr[7] = Float.intBitsToFloat((int) (j4 & 4294967295L));
        android.graphics.Path path2 = androidPath.internalPath;
        RectF rectF2 = androidPath.rectF;
        Intrinsics.checkNotNull(rectF2);
        float[] fArr2 = androidPath.radii;
        Intrinsics.checkNotNull(fArr2);
        path2.addRoundRect(rectF2, fArr2, Path.Direction.CCW);
    }
}
