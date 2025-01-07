package com.android.wm.shell.startingsurface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Trace;
import java.io.Closeable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplashscreenIconDrawableFactory$ImmobileIconDrawable extends Drawable implements Closeable {
    public Bitmap mIconBitmap;
    public final Matrix mMatrix;
    public final Paint mPaint = new Paint(7);

    public SplashscreenIconDrawableFactory$ImmobileIconDrawable(final Drawable drawable, final int i, final int i2, boolean z, Handler handler) {
        Matrix matrix = new Matrix();
        this.mMatrix = matrix;
        if (z) {
            final int i3 = 0;
            handler.post(new Runnable(this) { // from class: com.android.wm.shell.startingsurface.SplashscreenIconDrawableFactory$ImmobileIconDrawable$$ExternalSyntheticLambda0
                public final /* synthetic */ SplashscreenIconDrawableFactory$ImmobileIconDrawable f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i3) {
                        case 0:
                            this.f$0.preDrawIcon(i2, drawable);
                            break;
                        default:
                            this.f$0.preDrawIcon(i2, drawable);
                            break;
                    }
                }
            });
        } else {
            float f = i2 / i;
            matrix.setScale(f, f);
            final int i4 = 1;
            handler.post(new Runnable(this) { // from class: com.android.wm.shell.startingsurface.SplashscreenIconDrawableFactory$ImmobileIconDrawable$$ExternalSyntheticLambda0
                public final /* synthetic */ SplashscreenIconDrawableFactory$ImmobileIconDrawable f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i4) {
                        case 0:
                            this.f$0.preDrawIcon(i, drawable);
                            break;
                        default:
                            this.f$0.preDrawIcon(i, drawable);
                            break;
                    }
                }
            });
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.mPaint) {
            try {
                Bitmap bitmap = this.mIconBitmap;
                if (bitmap != null) {
                    bitmap.recycle();
                    this.mIconBitmap = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        synchronized (this.mPaint) {
            try {
                Bitmap bitmap = this.mIconBitmap;
                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, this.mMatrix, this.mPaint);
                } else {
                    invalidateSelf();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return 1;
    }

    public final void preDrawIcon(int i, Drawable drawable) {
        synchronized (this.mPaint) {
            Trace.traceBegin(32L, "preDrawIcon");
            this.mIconBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.mIconBitmap);
            drawable.setBounds(0, 0, i, i);
            drawable.draw(canvas);
            Trace.traceEnd(32L);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
