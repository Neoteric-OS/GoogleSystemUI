package com.android.systemui.shared.statusbar.phone;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BarTransitions {
    public final BarBackgroundDrawable mBarBackground;
    public int mMode;

    public BarTransitions(View view, int i) {
        view.getClass();
        BarBackgroundDrawable barBackgroundDrawable = new BarBackgroundDrawable(i, view.getContext());
        this.mBarBackground = barBackgroundDrawable;
        view.setBackground(barBackgroundDrawable);
    }

    public static String modeToString$1(int i) {
        if (i == 4) {
            return "MODE_OPAQUE";
        }
        if (i == 1) {
            return "MODE_SEMI_TRANSPARENT";
        }
        if (i == 2) {
            return "MODE_TRANSLUCENT";
        }
        if (i == 3) {
            return "MODE_LIGHTS_OUT";
        }
        if (i == 0) {
            return "MODE_TRANSPARENT";
        }
        if (i == 5) {
            return "MODE_WARNING";
        }
        if (i == 6) {
            return "MODE_LIGHTS_OUT_TRANSPARENT";
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown mode "));
    }

    public final void applyModeBackground(int i, boolean z) {
        BarBackgroundDrawable barBackgroundDrawable = this.mBarBackground;
        if (barBackgroundDrawable.mMode == i) {
            return;
        }
        barBackgroundDrawable.mMode = i;
        barBackgroundDrawable.mAnimating = z;
        if (z) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            barBackgroundDrawable.mStartTime = elapsedRealtime;
            barBackgroundDrawable.mEndTime = elapsedRealtime + 200;
            barBackgroundDrawable.mGradientAlphaStart = barBackgroundDrawable.mGradientAlpha;
            barBackgroundDrawable.mColorStart = barBackgroundDrawable.mColor;
        }
        barBackgroundDrawable.invalidateSelf();
    }

    public boolean isLightsOut(int i) {
        return i == 3 || i == 6;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BarBackgroundDrawable extends Drawable {
        public boolean mAnimating;
        public int mColor;
        public int mColorStart;
        public long mEndTime;
        public Rect mFrame;
        public final Drawable mGradient;
        public int mGradientAlpha;
        public int mGradientAlphaStart;
        public final int mOpaque;
        public final int mSemiTransparent;
        public long mStartTime;
        public PorterDuffColorFilter mTintFilter;
        public final int mWarning;
        public int mMode = -1;
        public float mOverrideAlpha = 1.0f;
        public final Paint mPaint = new Paint();

        public BarBackgroundDrawable(int i, Context context) {
            context.getResources();
            this.mOpaque = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
            this.mSemiTransparent = context.getColor(R.color.system_brand_a_dark);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.colorError});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            this.mWarning = color;
            this.mGradient = context.getDrawable(i);
        }

        @Override // android.graphics.drawable.Drawable
        public final void draw(Canvas canvas) {
            int i = this.mMode;
            int i2 = i == 5 ? this.mWarning : i == 2 ? this.mSemiTransparent : i == 1 ? this.mSemiTransparent : (i == 0 || i == 6) ? 0 : this.mOpaque;
            if (this.mAnimating) {
                if (SystemClock.elapsedRealtime() >= this.mEndTime) {
                    this.mAnimating = false;
                    this.mColor = i2;
                    this.mGradientAlpha = 0;
                } else {
                    long j = this.mStartTime;
                    float max = Math.max(0.0f, Math.min(((LinearInterpolator) Interpolators.LINEAR).getInterpolation((r3 - j) / (r5 - j)), 1.0f));
                    float f = 1.0f - max;
                    this.mGradientAlpha = (int) ((this.mGradientAlphaStart * f) + (0 * max));
                    this.mColor = Color.argb((int) ((Color.alpha(this.mColorStart) * f) + (Color.alpha(i2) * max)), (int) ((Color.red(this.mColorStart) * f) + (Color.red(i2) * max)), (int) ((Color.green(this.mColorStart) * f) + (Color.green(i2) * max)), (int) ((Color.blue(this.mColorStart) * f) + (max * Color.blue(i2))));
                }
            } else {
                this.mColor = i2;
                this.mGradientAlpha = 0;
            }
            int i3 = this.mGradientAlpha;
            if (i3 > 0) {
                this.mGradient.setAlpha(i3);
                this.mGradient.draw(canvas);
            }
            if (Color.alpha(this.mColor) > 0) {
                this.mPaint.setColor(this.mColor);
                PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
                if (porterDuffColorFilter != null) {
                    this.mPaint.setColorFilter(porterDuffColorFilter);
                }
                this.mPaint.setAlpha((int) (Color.alpha(this.mColor) * this.mOverrideAlpha));
                Rect rect = this.mFrame;
                if (rect != null) {
                    canvas.drawRect(rect, this.mPaint);
                } else {
                    canvas.drawPaint(this.mPaint);
                }
            }
            if (this.mAnimating) {
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public final int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public final void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.mGradient.setBounds(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public final void setTint(int i) {
            PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
            PorterDuff.Mode mode = porterDuffColorFilter == null ? PorterDuff.Mode.SRC_IN : porterDuffColorFilter.getMode();
            PorterDuffColorFilter porterDuffColorFilter2 = this.mTintFilter;
            if (porterDuffColorFilter2 == null || porterDuffColorFilter2.getColor() != i) {
                this.mTintFilter = new PorterDuffColorFilter(i, mode);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public final void setTintMode(PorterDuff.Mode mode) {
            PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
            int color = porterDuffColorFilter == null ? 0 : porterDuffColorFilter.getColor();
            PorterDuffColorFilter porterDuffColorFilter2 = this.mTintFilter;
            if (porterDuffColorFilter2 == null || porterDuffColorFilter2.getMode() != mode) {
                this.mTintFilter = new PorterDuffColorFilter(color, mode);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public final void setAlpha(int i) {
        }

        @Override // android.graphics.drawable.Drawable
        public final void setColorFilter(ColorFilter colorFilter) {
        }
    }
}
