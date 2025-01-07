package androidx.compose.ui.platform;

import android.graphics.Matrix;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.MatrixKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayerMatrixCache {
    public Matrix androidMatrixCache;
    public final Lambda getMatrix;
    public boolean isDirty;
    public boolean isInverseDirty;
    public Matrix previousAndroidMatrix;
    public final float[] matrixCache = androidx.compose.ui.graphics.Matrix.m379constructorimpl$default();
    public final float[] inverseMatrixCache = androidx.compose.ui.graphics.Matrix.m379constructorimpl$default();
    public boolean isInverseValid = true;
    public boolean isIdentity = true;

    /* JADX WARN: Multi-variable type inference failed */
    public LayerMatrixCache(Function2 function2) {
        this.getMatrix = (Lambda) function2;
    }

    /* renamed from: calculateInverseMatrix-bWbORWo, reason: not valid java name */
    public final float[] m566calculateInverseMatrixbWbORWo(Object obj) {
        boolean z = this.isInverseDirty;
        float[] fArr = this.inverseMatrixCache;
        if (z) {
            this.isInverseValid = InvertMatrixKt.m565invertToJiSxe2E(m567calculateMatrixGrdbGEg(obj), fArr);
            this.isInverseDirty = false;
        }
        if (this.isInverseValid) {
            return fArr;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
    /* renamed from: calculateMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m567calculateMatrixGrdbGEg(Object obj) {
        boolean z = this.isDirty;
        float[] fArr = this.matrixCache;
        if (!z) {
            return fArr;
        }
        Matrix matrix = this.androidMatrixCache;
        if (matrix == null) {
            matrix = new Matrix();
            this.androidMatrixCache = matrix;
        }
        this.getMatrix.invoke(obj, matrix);
        Matrix matrix2 = this.previousAndroidMatrix;
        if (matrix2 == null || !matrix.equals(matrix2)) {
            AndroidMatrixConversions_androidKt.m344setFromtUYjHk(matrix, fArr);
            this.androidMatrixCache = matrix2;
            this.previousAndroidMatrix = matrix;
        }
        this.isDirty = false;
        this.isIdentity = MatrixKt.m385isIdentity58bKbWc(fArr);
        return fArr;
    }

    public final void invalidate() {
        this.isDirty = true;
        this.isInverseDirty = true;
    }
}
