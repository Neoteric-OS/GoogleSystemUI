package com.android.systemui.assist.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.PathParser;
import android.view.Display;
import com.android.wm.shell.R;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathSpecCornerPathRenderer {
    public final int mBottomCornerRadius;
    public final int mHeight;
    public final float mPathScale;
    public final Path mRoundedPath;
    public final int mTopCornerRadius;
    public final int mWidth;
    public final Path mPath = new Path();
    public final Matrix mMatrix = new Matrix();

    public PathSpecCornerPathRenderer(Context context) {
        Display display = context.getDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        int rotation = display.getRotation();
        this.mWidth = (rotation == 0 || rotation == 2) ? displayMetrics.widthPixels : displayMetrics.heightPixels;
        Display display2 = context.getDisplay();
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        display2.getRealMetrics(displayMetrics2);
        int rotation2 = display2.getRotation();
        this.mHeight = (rotation2 == 0 || rotation2 == 2) ? displayMetrics2.heightPixels : displayMetrics2.widthPixels;
        this.mBottomCornerRadius = DisplayUtils.getCornerRadiusBottom(context);
        this.mTopCornerRadius = DisplayUtils.getCornerRadiusTop(context);
        Path createPathFromPathData = PathParser.createPathFromPathData(context.getResources().getString(R.string.config_rounded_mask));
        if (createPathFromPathData == null) {
            Log.e("PathSpecCornerPathRenderer", "No rounded corner path found!");
            this.mRoundedPath = new Path();
        } else {
            this.mRoundedPath = createPathFromPathData;
        }
        RectF rectF = new RectF();
        this.mRoundedPath.computeBounds(rectF, true);
        this.mPathScale = Math.min(Math.abs(rectF.right - rectF.left), Math.abs(rectF.top - rectF.bottom));
    }

    public final Path getInsetPath(CornerPathRenderer$Corner cornerPathRenderer$Corner, float f) {
        int i;
        Path path;
        PointF pointF;
        PointF pointF2;
        if (this.mRoundedPath.isEmpty()) {
            path = this.mRoundedPath;
        } else {
            int ordinal = cornerPathRenderer$Corner.ordinal();
            int i2 = this.mWidth;
            int i3 = this.mHeight;
            int i4 = this.mBottomCornerRadius;
            if (ordinal != 1) {
                int i5 = this.mTopCornerRadius;
                if (ordinal == 2) {
                    i = 90;
                    i3 = 0;
                } else if (ordinal != 3) {
                    i = 270;
                    i2 = 0;
                } else {
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                }
                i4 = i5;
            } else {
                i = 180;
            }
            this.mPath.reset();
            this.mMatrix.reset();
            this.mPath.addPath(this.mRoundedPath);
            Matrix matrix = this.mMatrix;
            float f2 = i4 / this.mPathScale;
            matrix.preScale(f2, f2);
            this.mMatrix.postRotate(i);
            this.mMatrix.postTranslate(i2, i3);
            this.mPath.transform(this.mMatrix);
            path = this.mPath;
        }
        float f3 = -f;
        float[] approximate = path.approximate(0.1f);
        ArrayList arrayList = new ArrayList();
        for (int i6 = 0; i6 < approximate.length; i6 += 3) {
            arrayList.add(new PointF(approximate[i6 + 1], approximate[i6 + 2]));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            PointF pointF3 = (PointF) arrayList.get(i7);
            if (i7 == 0) {
                pointF = new PointF(0.0f, 0.0f);
            } else {
                PointF pointF4 = (PointF) arrayList.get(i7);
                PointF pointF5 = (PointF) arrayList.get(i7 - 1);
                pointF = new PointF(pointF4.x - pointF5.x, pointF4.y - pointF5.y);
            }
            if (i7 == arrayList.size() - 1) {
                pointF2 = new PointF(0.0f, 0.0f);
            } else {
                PointF pointF6 = (PointF) arrayList.get(i7);
                PointF pointF7 = (PointF) arrayList.get(i7 + 1);
                pointF2 = new PointF(pointF7.x - pointF6.x, pointF7.y - pointF6.y);
            }
            PointF pointF8 = new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
            float f4 = pointF8.x;
            float f5 = pointF8.y;
            float sqrt = (float) Math.sqrt((f5 * f5) + (f4 * f4));
            if (sqrt != 0.0f) {
                float f6 = 1.0f / sqrt;
                pointF8 = new PointF(pointF8.x * f6, pointF8.y * f6);
            }
            PointF pointF9 = new PointF(-pointF8.y, pointF8.x);
            arrayList2.add(new PointF((pointF9.x * f3) + pointF3.x, (pointF9.y * f3) + pointF3.y));
        }
        Path path2 = new Path();
        if (arrayList2.size() > 0) {
            path2.moveTo(((PointF) arrayList2.get(0)).x, ((PointF) arrayList2.get(0)).y);
            for (PointF pointF10 : arrayList2.subList(1, arrayList2.size())) {
                path2.lineTo(pointF10.x, pointF10.y);
            }
        }
        return path2;
    }
}
