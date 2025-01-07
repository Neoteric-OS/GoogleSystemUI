package com.android.systemui.statusbar.policy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardUserSwitcherScrim extends Drawable implements View.OnLayoutChangeListener {
    public int mAlpha;
    public int mCircleX;
    public int mCircleY;
    public int mDarkColor;
    public Paint mRadialGradientPaint;
    public int mSize;

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.mAlpha == 0) {
            return;
        }
        Rect bounds = getBounds();
        canvas.drawRect(bounds.left, bounds.top, bounds.right, bounds.bottom, this.mRadialGradientPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        this.mSize = Math.max(i3 - i, i4 - i2);
        updatePaint();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.mAlpha = i;
        updatePaint();
        invalidateSelf();
    }

    public final void updatePaint() {
        int i = this.mSize;
        if (i == 0) {
            return;
        }
        this.mRadialGradientPaint.setShader(new RadialGradient(this.mCircleX, this.mCircleY, i * 2.5f, new int[]{Color.argb((int) ((Color.alpha(this.mDarkColor) * this.mAlpha) / 255.0f), 0, 0, 0), 0}, new float[]{Math.max(0.0f, 0.1f), 1.0f}, Shader.TileMode.CLAMP));
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
