package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DotRenderer {
    public final Bitmap mBackgroundWithShadow;
    public final float mBitmapOffset;
    public final Paint mCirclePaint = new Paint(3);
    public final float mCircleRadius;
    public final float[] mLeftDotPosition;
    public final float[] mRightDotPosition;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DrawParams {
        public int dotColor;
        public Rect iconBounds;
        public boolean leftAlign;
        public float scale;
    }

    public DotRenderer(int i, Path path) {
        int round = Math.round(i * 0.228f);
        round = round <= 0 ? 1 : round;
        RectF rectF = new RectF();
        float f = round;
        float f2 = f * 1.0f;
        float f3 = f2 / 24.0f;
        float f4 = f2 / 16.0f;
        float f5 = f / 2.0f;
        int max = Math.max(Math.round(f5 + f3), Math.round(f5 + f3 + f4));
        rectF.set(0.0f, 0.0f, f, f);
        float f6 = max - f5;
        rectF.offsetTo(f6, f6);
        int i2 = max * 2;
        GraphicsUtils$$ExternalSyntheticLambda0 graphicsUtils$$ExternalSyntheticLambda0 = GraphicsUtils.sOnNewBitmapRunnable;
        Picture picture = new Picture();
        Canvas beginRecording = picture.beginRecording(i2, i2);
        Paint paint = new Paint(3);
        paint.setColor(0);
        paint.setShadowLayer(f3, 0.0f, f4, 7 << 24);
        beginRecording.drawRoundRect(rectF, f5, f5, paint);
        paint.setShadowLayer(f3, 0.0f, 0.0f, 88 << 24);
        beginRecording.drawRoundRect(rectF, f5, f5, paint);
        if (Color.alpha(0) < 255) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            paint.clearShadowLayer();
            paint.setColor(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
            beginRecording.drawRoundRect(rectF, f5, f5, paint);
            paint.setXfermode(null);
            paint.setColor(0);
            beginRecording.drawRoundRect(rectF, f5, f5, paint);
        }
        picture.endRecording();
        this.mBackgroundWithShadow = Bitmap.createBitmap(picture);
        this.mCircleRadius = f5;
        this.mBitmapOffset = (-r11.getHeight()) * 0.5f;
        float f7 = 100;
        this.mLeftDotPosition = getPathPoint(path, f7, -1.0f);
        this.mRightDotPosition = getPathPoint(path, f7, 1.0f);
    }

    public static float[] getPathPoint(Path path, float f, float f2) {
        float f3 = f / 2.0f;
        float f4 = (f2 * f3) + f3;
        Path path2 = new Path();
        path2.moveTo(f3, f3);
        path2.lineTo((f2 * 1.0f) + f4, 0.0f);
        path2.lineTo(f4, -1.0f);
        path2.close();
        path2.op(path, Path.Op.INTERSECT);
        float[] fArr = new float[2];
        new PathMeasure(path2, false).getPosTan(0.0f, fArr, null);
        fArr[0] = fArr[0] / f;
        fArr[1] = fArr[1] / f;
        return fArr;
    }
}
