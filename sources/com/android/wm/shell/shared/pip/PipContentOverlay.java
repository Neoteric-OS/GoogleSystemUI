package com.android.wm.shell.shared.pip;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.SurfaceControl;
import android.window.TaskSnapshot;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipContentOverlay {
    public SurfaceControl mLeash;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipAppIconOverlay extends PipContentOverlay {
        public final Rect mAppBounds;
        public final Bitmap mBitmap;
        public final int mOverlayHalfSize;
        public final Matrix mTmpTransform = new Matrix();
        public final float[] mTmpFloat9 = new float[9];

        public PipAppIconOverlay(Context context, Rect rect, Rect rect2, Drawable drawable, int i) {
            int min = Math.min((int) TypedValue.applyDimension(1, 72.0f, context.getResources().getDisplayMetrics()), i);
            int overlaySize = getOverlaySize(rect, rect2);
            int i2 = overlaySize >> 1;
            this.mOverlayHalfSize = i2;
            this.mAppBounds = new Rect(0, 0, rect.width(), rect.height());
            this.mBitmap = Bitmap.createBitmap(overlaySize, overlaySize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(this.mBitmap);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.colorBackground});
            try {
                int color = obtainStyledAttributes.getColor(0, 0);
                canvas.drawRGB(Color.red(color), Color.green(color), Color.blue(color));
                obtainStyledAttributes.recycle();
                int i3 = min / 2;
                int i4 = i2 - i3;
                int i5 = i3 + i2;
                drawable.setBounds(new Rect(i4, i4, i5, i5));
                drawable.draw(canvas);
                this.mBitmap = this.mBitmap.copy(Bitmap.Config.HARDWARE, false);
                this.mLeash = new SurfaceControl.Builder().setCallsite("PipContentOverlay$PipAppIconOverlay").setName("PipContentOverlay").build();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }

        public static int getOverlaySize(Rect rect, Rect rect2) {
            return Math.max(Math.max(rect.width(), rect.height()), Math.max(rect2.width(), rect2.height())) + 1;
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void attach(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
            transaction.show(this.mLeash);
            transaction.setLayer(this.mLeash, Integer.MAX_VALUE);
            transaction.setBuffer(this.mLeash, this.mBitmap.getHardwareBuffer());
            transaction.setAlpha(this.mLeash, 0.0f);
            transaction.reparent(this.mLeash, surfaceControl);
            transaction.apply();
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void detach(SurfaceControl.Transaction transaction) {
            super.detach(transaction);
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
            this.mBitmap.recycle();
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void onAnimationUpdate(SurfaceControl.Transaction transaction, Rect rect, float f) {
            this.mTmpTransform.reset();
            int centerX = this.mAppBounds.centerX();
            int centerY = this.mAppBounds.centerY();
            Matrix matrix = this.mTmpTransform;
            int i = this.mOverlayHalfSize;
            matrix.setTranslate(centerX - i, centerY - i);
            float min = Math.min(this.mAppBounds.width() / rect.width(), this.mAppBounds.height() / rect.height());
            this.mTmpTransform.postScale(min, min, centerX, centerY);
            transaction.setMatrix(this.mLeash, this.mTmpTransform, this.mTmpFloat9).setAlpha(this.mLeash, f < 0.5f ? 0.0f : 2.0f * (f - 0.5f));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipColorOverlay extends PipContentOverlay {
        public final Context mContext;

        public PipColorOverlay(Context context) {
            this.mContext = context;
            this.mLeash = new SurfaceControl.Builder().setCallsite("PipContentOverlay$PipColorOverlay").setName("PipContentOverlay").setColorLayer().build();
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void attach(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
            transaction.show(this.mLeash);
            transaction.setLayer(this.mLeash, Integer.MAX_VALUE);
            SurfaceControl surfaceControl2 = this.mLeash;
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.colorBackground});
            try {
                int color = obtainStyledAttributes.getColor(0, 0);
                float[] fArr = {Color.red(color) / 255.0f, Color.green(color) / 255.0f, Color.blue(color) / 255.0f};
                obtainStyledAttributes.recycle();
                transaction.setColor(surfaceControl2, fArr);
                transaction.setAlpha(this.mLeash, 0.0f);
                transaction.reparent(this.mLeash, surfaceControl);
                transaction.apply();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void onAnimationUpdate(SurfaceControl.Transaction transaction, Rect rect, float f) {
            transaction.setAlpha(this.mLeash, f < 0.5f ? 0.0f : 2.0f * (f - 0.5f));
        }
    }

    public abstract void attach(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl);

    public void detach(SurfaceControl.Transaction transaction) {
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        transaction.remove(this.mLeash);
        transaction.apply();
    }

    public abstract void onAnimationUpdate(SurfaceControl.Transaction transaction, Rect rect, float f);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipSnapshotOverlay extends PipContentOverlay {
        public final TaskSnapshot mSnapshot;
        public final Rect mSourceRectHint;

        public PipSnapshotOverlay(TaskSnapshot taskSnapshot, Rect rect) {
            this.mSnapshot = taskSnapshot;
            this.mSourceRectHint = new Rect(rect);
            this.mLeash = new SurfaceControl.Builder().setCallsite("PipContentOverlay$PipSnapshotOverlay").setName("PipContentOverlay").build();
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void attach(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
            transaction.show(this.mLeash);
            transaction.setLayer(this.mLeash, Integer.MAX_VALUE);
            transaction.setBuffer(this.mLeash, this.mSnapshot.getHardwareBuffer());
            SurfaceControl surfaceControl2 = this.mLeash;
            Rect rect = this.mSourceRectHint;
            transaction.setPosition(surfaceControl2, -rect.left, -rect.top);
            transaction.setScale(this.mLeash, this.mSnapshot.getTaskSize().x / this.mSnapshot.getHardwareBuffer().getWidth(), this.mSnapshot.getTaskSize().y / this.mSnapshot.getHardwareBuffer().getHeight());
            transaction.reparent(this.mLeash, surfaceControl);
            transaction.apply();
        }

        @Override // com.android.wm.shell.shared.pip.PipContentOverlay
        public final void onAnimationUpdate(SurfaceControl.Transaction transaction, Rect rect, float f) {
        }
    }
}
