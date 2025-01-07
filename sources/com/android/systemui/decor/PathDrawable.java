package com.android.systemui.decor;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PathDrawable extends Drawable {
    public final int height;
    public final Paint paint;
    public final Path path;
    public final float scaleX;
    public final float scaleY;
    public final int width;

    public PathDrawable(Path path, int i, int i2, float f, float f2, Paint paint) {
        this.path = path;
        this.width = i;
        this.height = i2;
        this.scaleX = f;
        this.scaleY = f2;
        this.paint = paint;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float f = this.scaleX;
        if (f != 1.0f || this.scaleY != 1.0f) {
            canvas.scale(f, this.scaleY);
        }
        canvas.drawPath(this.path, this.paint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
