package com.android.systemui.screenshot.ui;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransitioningIconDrawable extends Drawable {
    public int alpha;
    public ColorFilter colorFilter;
    public Drawable drawable;
    public Drawable enteringDrawable;
    public ColorStateList tint;
    public ValueAnimator transitionAnimator;

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.drawable;
        if (drawable != null) {
            drawScaledDrawable(drawable, canvas, this.transitionAnimator.isRunning() ? 1.0f - this.transitionAnimator.getAnimatedFraction() : 1.0f);
        }
        Drawable drawable2 = this.enteringDrawable;
        if (drawable2 != null) {
            drawScaledDrawable(drawable2, canvas, this.transitionAnimator.getAnimatedFraction());
        }
        if (this.transitionAnimator.isRunning()) {
            invalidateSelf();
        }
    }

    public final void drawScaledDrawable(Drawable drawable, Canvas canvas, float f) {
        drawable.setBounds(getBounds());
        canvas.save();
        canvas.scale(f, f, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.alpha = i;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        Drawable drawable = this.drawable;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        Drawable drawable2 = this.enteringDrawable;
        if (drawable2 == null) {
            return;
        }
        drawable2.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        Drawable drawable = this.drawable;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
        }
        Drawable drawable2 = this.enteringDrawable;
        if (drawable2 != null) {
            drawable2.setTintList(colorStateList);
        }
        this.tint = colorStateList;
    }
}
