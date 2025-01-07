package com.android.wm.shell.pip;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Choreographer;
import android.view.SurfaceControl;
import com.android.wm.shell.R;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipSurfaceTransactionHelper {
    public int mCornerRadius;
    public int mShadowRadius;
    public final Matrix mTmpTransform = new Matrix();
    public final float[] mTmpFloat9 = new float[9];
    public final RectF mTmpSourceRectF = new RectF();
    public final RectF mTmpDestinationRectF = new RectF();
    public final Rect mTmpDestinationRect = new Rect();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SurfaceControlTransactionFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VsyncSurfaceControlTransactionFactory implements SurfaceControlTransactionFactory {
        public final SurfaceControl.Transaction getTransaction() {
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
            return transaction;
        }
    }

    public PipSurfaceTransactionHelper(Context context) {
        this.mCornerRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius);
        this.mShadowRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius);
    }

    public final void crop(Rect rect, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.setWindowCrop(surfaceControl, rect.width(), rect.height()).setPosition(surfaceControl, rect.left, rect.top);
    }

    public final void resetScale(Rect rect, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.setMatrix(surfaceControl, Matrix.IDENTITY_MATRIX, this.mTmpFloat9).setPosition(surfaceControl, rect.left, rect.top);
    }

    public final void rotateAndScaleWithCrop(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, Rect rect2, Rect rect3, float f, float f2, float f3, boolean z, boolean z2) {
        float f4;
        int i;
        float f5;
        this.mTmpDestinationRect.set(rect);
        this.mTmpDestinationRect.inset(rect3);
        int width = this.mTmpDestinationRect.width();
        int height = this.mTmpDestinationRect.height();
        int width2 = rect2.width();
        int height2 = rect2.height();
        float f6 = width <= height ? width2 / width : height2 / height;
        Rect rect4 = this.mTmpDestinationRect;
        boolean z3 = Transitions.SHELL_TRANSITIONS_ROTATION;
        int i2 = z3 ? height2 : width2;
        if (!z3) {
            width2 = height2;
        }
        rect4.set(0, 0, i2, width2);
        rect4.scale(1.0f / f6);
        rect4.offset(rect3.left, rect3.top);
        if (z) {
            f4 = f2 - (rect3.left * f6);
            i = rect3.top;
        } else {
            if (z2) {
                f4 = f2 - (rect3.top * f6);
                f5 = f3 + (rect3.left * f6);
                this.mTmpTransform.setScale(f6, f6);
                this.mTmpTransform.postRotate(f);
                this.mTmpTransform.postTranslate(f4, f5);
                transaction.setMatrix(surfaceControl, this.mTmpTransform, this.mTmpFloat9).setCrop(surfaceControl, rect4);
            }
            f4 = f2 + (rect3.top * f6);
            i = rect3.left;
        }
        f5 = f3 - (i * f6);
        this.mTmpTransform.setScale(f6, f6);
        this.mTmpTransform.postRotate(f);
        this.mTmpTransform.postTranslate(f4, f5);
        transaction.setMatrix(surfaceControl, this.mTmpTransform, this.mTmpFloat9).setCrop(surfaceControl, rect4);
    }

    public final void round(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, boolean z) {
        transaction.setCornerRadius(surfaceControl, z ? this.mCornerRadius : 0.0f);
    }

    public final void scale(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, RectF rectF, float f) {
        this.mTmpSourceRectF.set(rect);
        this.mTmpSourceRectF.offsetTo(0.0f, 0.0f);
        this.mTmpDestinationRectF.set(rectF);
        this.mTmpTransform.setRectToRect(this.mTmpSourceRectF, this.mTmpDestinationRectF, Matrix.ScaleToFit.FILL);
        this.mTmpTransform.postRotate(f, this.mTmpDestinationRectF.centerX(), this.mTmpDestinationRectF.centerY());
        transaction.setMatrix(surfaceControl, this.mTmpTransform, this.mTmpFloat9);
    }

    public final void shadow(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, boolean z) {
        transaction.setShadowRadius(surfaceControl, z ? this.mShadowRadius : 0.0f);
    }

    public final void round(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, Rect rect2) {
        transaction.setCornerRadius(surfaceControl, this.mCornerRadius * ((float) (Math.hypot(rect.width(), rect.height()) / Math.hypot(rect2.width(), rect2.height()))));
    }
}
