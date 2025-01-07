package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapePath;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShapeAppearancePathProvider {
    public final ShapePath[] cornerPaths = new ShapePath[4];
    public final Matrix[] cornerTransforms = new Matrix[4];
    public final Matrix[] edgeTransforms = new Matrix[4];
    public final PointF pointF = new PointF();
    public final Path overlappedEdgePath = new Path();
    public final Path boundsPath = new Path();
    public final ShapePath shapePath = new ShapePath();
    public final float[] scratch = new float[2];
    public final float[] scratch2 = new float[2];
    public final Path edgePath = new Path();
    public final Path cornerPath = new Path();
    public final boolean edgeIntersectionCheckEnabled = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Lazy {
        public static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.cornerPaths[i] = new ShapePath();
            this.cornerTransforms[i] = new Matrix();
            this.edgeTransforms[i] = new Matrix();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [boolean] */
    public final void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, MaterialShapeDrawable.AnonymousClass1 anonymousClass1, Path path) {
        ShapePath[] shapePathArr;
        char c;
        int i;
        float[] fArr;
        char c2;
        boolean z;
        path.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF, Path.Direction.CW);
        ?? r5 = 0;
        int i2 = 0;
        while (true) {
            shapePathArr = this.cornerPaths;
            c = 1;
            fArr = this.scratch;
            if (i2 >= 4) {
                break;
            }
            CornerSize cornerSize = i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.topRightCornerSize : shapeAppearanceModel.topLeftCornerSize : shapeAppearanceModel.bottomLeftCornerSize : shapeAppearanceModel.bottomRightCornerSize;
            CornerTreatment cornerTreatment = i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.topRightCorner : shapeAppearanceModel.topLeftCorner : shapeAppearanceModel.bottomLeftCorner : shapeAppearanceModel.bottomRightCorner;
            ShapePath shapePath = shapePathArr[i2];
            cornerTreatment.getClass();
            cornerTreatment.getCornerPath(shapePath, f, cornerSize.getCornerSize(rectF));
            int i3 = i2 + 1;
            float f2 = (i3 % 4) * 90;
            this.cornerTransforms[i2].reset();
            PointF pointF = this.pointF;
            if (i2 == 1) {
                pointF.set(rectF.right, rectF.bottom);
            } else if (i2 == 2) {
                pointF.set(rectF.left, rectF.bottom);
            } else if (i2 != 3) {
                pointF.set(rectF.right, rectF.top);
            } else {
                pointF.set(rectF.left, rectF.top);
            }
            Matrix matrix = this.cornerTransforms[i2];
            PointF pointF2 = this.pointF;
            matrix.setTranslate(pointF2.x, pointF2.y);
            this.cornerTransforms[i2].preRotate(f2);
            ShapePath shapePath2 = shapePathArr[i2];
            fArr[0] = shapePath2.endX;
            fArr[1] = shapePath2.endY;
            this.cornerTransforms[i2].mapPoints(fArr);
            this.edgeTransforms[i2].reset();
            this.edgeTransforms[i2].setTranslate(fArr[0], fArr[1]);
            this.edgeTransforms[i2].preRotate(f2);
            i2 = i3;
        }
        int i4 = 0;
        for (i = 4; i4 < i; i = 4) {
            ShapePath shapePath3 = shapePathArr[i4];
            shapePath3.getClass();
            fArr[r5] = 0.0f;
            fArr[c] = shapePath3.startY;
            this.cornerTransforms[i4].mapPoints(fArr);
            if (i4 == 0) {
                path.moveTo(fArr[r5], fArr[c]);
            } else {
                path.lineTo(fArr[r5], fArr[c]);
            }
            shapePathArr[i4].applyToPath(this.cornerTransforms[i4], path);
            if (anonymousClass1 != null) {
                ShapePath shapePath4 = shapePathArr[i4];
                Matrix matrix2 = this.cornerTransforms[i4];
                MaterialShapeDrawable materialShapeDrawable = MaterialShapeDrawable.this;
                BitSet bitSet = materialShapeDrawable.containsIncompatibleShadowOp;
                shapePath4.getClass();
                bitSet.set(i4, (boolean) r5);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr = materialShapeDrawable.cornerShadowOperation;
                shapePath4.addConnectingShadowIfNecessary(shapePath4.endShadowAngle);
                shadowCompatOperationArr[i4] = new ShapePath.ShadowCompatOperation(new ArrayList(shapePath4.shadowCompatOperations), new Matrix(matrix2)) { // from class: com.google.android.material.shape.ShapePath.1
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    public AnonymousClass1(List list, Matrix matrix3) {
                    }
                };
            }
            int i5 = i4 + 1;
            int i6 = i5 % 4;
            ShapePath shapePath5 = shapePathArr[i4];
            fArr[r5] = shapePath5.endX;
            fArr[c] = shapePath5.endY;
            this.cornerTransforms[i4].mapPoints(fArr);
            ShapePath shapePath6 = shapePathArr[i6];
            shapePath6.getClass();
            float[] fArr2 = this.scratch2;
            fArr2[r5] = 0.0f;
            fArr2[c] = shapePath6.startY;
            this.cornerTransforms[i6].mapPoints(fArr2);
            int i7 = i4;
            float max = Math.max(((float) Math.hypot(fArr[r5] - fArr2[r5], fArr[c] - fArr2[c])) - 0.001f, 0.0f);
            ShapePath shapePath7 = shapePathArr[i7];
            fArr[0] = shapePath7.endX;
            fArr[1] = shapePath7.endY;
            this.cornerTransforms[i7].mapPoints(fArr);
            if (i7 == 1 || i7 == 3) {
                Math.abs(rectF.centerX() - fArr[0]);
            } else {
                Math.abs(rectF.centerY() - fArr[1]);
            }
            ShapePath shapePath8 = this.shapePath;
            shapePath8.reset(0.0f, 270.0f, 0.0f);
            (i7 != 1 ? i7 != 2 ? i7 != 3 ? shapeAppearanceModel.rightEdge : shapeAppearanceModel.topEdge : shapeAppearanceModel.leftEdge : shapeAppearanceModel.bottomEdge).getClass();
            shapePath8.lineTo(max, 0.0f);
            this.edgePath.reset();
            shapePath8.applyToPath(this.edgeTransforms[i7], this.edgePath);
            if (this.edgeIntersectionCheckEnabled && (pathOverlapsCorner(i7, this.edgePath) || pathOverlapsCorner(i6, this.edgePath))) {
                Path path2 = this.edgePath;
                path2.op(path2, this.boundsPath, Path.Op.DIFFERENCE);
                fArr[0] = 0.0f;
                c2 = 1;
                fArr[1] = shapePath8.startY;
                this.edgeTransforms[i7].mapPoints(fArr);
                this.overlappedEdgePath.moveTo(fArr[0], fArr[1]);
                shapePath8.applyToPath(this.edgeTransforms[i7], this.overlappedEdgePath);
            } else {
                c2 = 1;
                shapePath8.applyToPath(this.edgeTransforms[i7], path);
            }
            if (anonymousClass1 != null) {
                Matrix matrix3 = this.edgeTransforms[i7];
                MaterialShapeDrawable materialShapeDrawable2 = MaterialShapeDrawable.this;
                z = false;
                materialShapeDrawable2.containsIncompatibleShadowOp.set(i7 + 4, false);
                ShapePath.ShadowCompatOperation[] shadowCompatOperationArr2 = materialShapeDrawable2.edgeShadowOperation;
                shapePath8.addConnectingShadowIfNecessary(shapePath8.endShadowAngle);
                shadowCompatOperationArr2[i7] = new ShapePath.ShadowCompatOperation(new ArrayList(shapePath8.shadowCompatOperations), new Matrix(matrix3)) { // from class: com.google.android.material.shape.ShapePath.1
                    public final /* synthetic */ List val$operations;
                    public final /* synthetic */ Matrix val$transformCopy;

                    public AnonymousClass1(List list, Matrix matrix32) {
                    }
                };
            } else {
                z = false;
            }
            r5 = z;
            c = c2;
            i4 = i5;
        }
        path.close();
        this.overlappedEdgePath.close();
        if (this.overlappedEdgePath.isEmpty()) {
            return;
        }
        path.op(this.overlappedEdgePath, Path.Op.UNION);
    }

    public final boolean pathOverlapsCorner(int i, Path path) {
        this.cornerPath.reset();
        this.cornerPaths[i].applyToPath(this.cornerTransforms[i], this.cornerPath);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.cornerPath.computeBounds(rectF, true);
        path.op(this.cornerPath, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }
}
