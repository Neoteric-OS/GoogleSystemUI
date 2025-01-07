package com.android.wm.shell.pip2;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceControl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipSurfaceTransactionHelper {
    public final int mCornerRadius;
    public final int mShadowRadius;
    public final Rect mTmpDestinationRect;
    public final Matrix mTmpTransform = new Matrix();
    public final float[] mTmpFloat9 = new float[9];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VsyncSurfaceControlTransactionFactory {
    }

    public PipSurfaceTransactionHelper(Context context) {
        new RectF();
        new RectF();
        this.mTmpDestinationRect = new Rect();
        this.mCornerRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius);
        this.mShadowRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius);
    }

    public final void scaleAndCrop(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z, float f) {
        float max;
        float height;
        int height2;
        float height3;
        int height4;
        this.mTmpDestinationRect.set(rect2);
        this.mTmpDestinationRect.offsetTo(0, 0);
        this.mTmpDestinationRect.inset(rect4);
        if (!z || rect == null || rect.width() >= rect2.width()) {
            max = Math.max(rect3.width() / rect2.width(), rect3.height() / rect2.height());
        } else {
            if (rect2.width() <= rect2.height()) {
                height = rect3.width();
                height2 = rect.width();
            } else {
                height = rect3.height();
                height2 = rect.height();
            }
            float f2 = height / height2;
            if (rect2.width() <= rect2.height()) {
                height3 = rect3.width();
                height4 = rect2.width();
            } else {
                height3 = rect3.height();
                height4 = rect2.height();
            }
            max = (f * f2) + ((1.0f - f) * (height3 / height4));
        }
        this.mTmpTransform.setScale(max, max);
        transaction.setMatrix(surfaceControl, this.mTmpTransform, this.mTmpFloat9).setCrop(surfaceControl, this.mTmpDestinationRect).setPosition(surfaceControl, rect3.left - (rect4.left * max), rect3.top - (rect4.top * max));
    }
}
