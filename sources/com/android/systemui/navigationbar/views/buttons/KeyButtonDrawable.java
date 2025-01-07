package com.android.systemui.navigationbar.views.buttons;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.FloatProperty;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyButtonDrawable extends Drawable {
    public static final AnonymousClass1 KEY_DRAWABLE_ROTATE;
    public static final AnonymousClass1 KEY_DRAWABLE_TRANSLATE_Y;
    public final AnimatedVectorDrawable mAnimatedDrawable;
    public final AnonymousClass3 mAnimatedDrawableCallback;
    public final Paint mIconPaint = new Paint(3);
    public final Paint mShadowPaint = new Paint(3);
    public final ShadowDrawableState mState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShadowDrawableState extends Drawable.ConstantState {
        public int mAlpha = 255;
        public int mBaseHeight;
        public int mBaseWidth;
        public int mChangingConfigurations;
        public Drawable.ConstantState mChildState;
        public final int mDarkColor;
        public float mDarkIntensity;
        public final boolean mHorizontalFlip;
        public boolean mIsHardwareBitmap;
        public Bitmap mLastDrawnIcon;
        public Bitmap mLastDrawnShadow;
        public final int mLightColor;
        public float mRotateDegrees;
        public int mShadowColor;
        public int mShadowOffsetX;
        public int mShadowOffsetY;
        public int mShadowSize;
        public final boolean mSupportsAnimation;
        public float mTranslationY;

        public ShadowDrawableState(int i, int i2, boolean z, boolean z2) {
            this.mLightColor = i;
            this.mDarkColor = i2;
            this.mSupportsAnimation = z;
            this.mHorizontalFlip = z2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new KeyButtonDrawable(null, this);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable$1] */
    static {
        final int i = 0;
        KEY_DRAWABLE_ROTATE = new FloatProperty("KeyButtonRotation") { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i) {
                    case 0:
                        return Float.valueOf(((KeyButtonDrawable) obj).mState.mRotateDegrees);
                    default:
                        return Float.valueOf(((KeyButtonDrawable) obj).mState.mTranslationY);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        ((KeyButtonDrawable) obj).setRotation(f);
                        break;
                    default:
                        KeyButtonDrawable keyButtonDrawable = (KeyButtonDrawable) obj;
                        ShadowDrawableState shadowDrawableState = keyButtonDrawable.mState;
                        shadowDrawableState.getClass();
                        if (shadowDrawableState.mTranslationY != f) {
                            shadowDrawableState.mTranslationY = f;
                            keyButtonDrawable.invalidateSelf();
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        KEY_DRAWABLE_TRANSLATE_Y = new FloatProperty("KeyButtonTranslateY") { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i2) {
                    case 0:
                        return Float.valueOf(((KeyButtonDrawable) obj).mState.mRotateDegrees);
                    default:
                        return Float.valueOf(((KeyButtonDrawable) obj).mState.mTranslationY);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        ((KeyButtonDrawable) obj).setRotation(f);
                        break;
                    default:
                        KeyButtonDrawable keyButtonDrawable = (KeyButtonDrawable) obj;
                        ShadowDrawableState shadowDrawableState = keyButtonDrawable.mState;
                        shadowDrawableState.getClass();
                        if (shadowDrawableState.mTranslationY != f) {
                            shadowDrawableState.mTranslationY = f;
                            keyButtonDrawable.invalidateSelf();
                            break;
                        }
                        break;
                }
            }
        };
    }

    public KeyButtonDrawable(Drawable drawable, ShadowDrawableState shadowDrawableState) {
        Drawable.Callback callback = new Drawable.Callback() { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonDrawable.3
            @Override // android.graphics.drawable.Drawable.Callback
            public final void invalidateDrawable(Drawable drawable2) {
                KeyButtonDrawable.this.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public final void scheduleDrawable(Drawable drawable2, Runnable runnable, long j) {
                KeyButtonDrawable.this.scheduleSelf(runnable, j);
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public final void unscheduleDrawable(Drawable drawable2, Runnable runnable) {
                KeyButtonDrawable.this.unscheduleSelf(runnable);
            }
        };
        this.mState = shadowDrawableState;
        if (drawable != null) {
            shadowDrawableState.mBaseHeight = drawable.getIntrinsicHeight();
            shadowDrawableState.mBaseWidth = drawable.getIntrinsicWidth();
            shadowDrawableState.mChangingConfigurations = drawable.getChangingConfigurations();
            shadowDrawableState.mChildState = drawable.getConstantState();
        }
        if (shadowDrawableState.mSupportsAnimation) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) shadowDrawableState.mChildState.newDrawable().mutate();
            this.mAnimatedDrawable = animatedVectorDrawable;
            animatedVectorDrawable.setCallback(callback);
            setDrawableBounds(animatedVectorDrawable);
        }
    }

    public static KeyButtonDrawable create(Context context, int i, int i2, int i3, boolean z) {
        Resources resources = context.getResources();
        boolean z2 = false;
        boolean z3 = resources.getConfiguration().getLayoutDirection() == 1;
        Drawable drawable = context.getDrawable(i3);
        if (z3 && drawable.isAutoMirrored()) {
            z2 = true;
        }
        KeyButtonDrawable keyButtonDrawable = new KeyButtonDrawable(drawable, new ShadowDrawableState(i, i2, drawable instanceof AnimatedVectorDrawable, z2));
        if (z) {
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.nav_key_button_shadow_offset_x);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.nav_key_button_shadow_offset_y);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.nav_key_button_shadow_radius);
            int color = context.getColor(R.color.nav_key_button_shadow_color);
            ShadowDrawableState shadowDrawableState = keyButtonDrawable.mState;
            if (!shadowDrawableState.mSupportsAnimation && (shadowDrawableState.mShadowOffsetX != dimensionPixelSize || shadowDrawableState.mShadowOffsetY != dimensionPixelSize2 || shadowDrawableState.mShadowSize != dimensionPixelSize3 || shadowDrawableState.mShadowColor != color)) {
                shadowDrawableState.mShadowOffsetX = dimensionPixelSize;
                shadowDrawableState.mShadowOffsetY = dimensionPixelSize2;
                shadowDrawableState.mShadowSize = dimensionPixelSize3;
                shadowDrawableState.mShadowColor = color;
                keyButtonDrawable.mShadowPaint.setColorFilter(new PorterDuffColorFilter(keyButtonDrawable.mState.mShadowColor, PorterDuff.Mode.SRC_ATOP));
                keyButtonDrawable.updateShadowAlpha();
                keyButtonDrawable.invalidateSelf();
            }
        }
        return keyButtonDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        this.mState.getClass();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            return;
        }
        AnimatedVectorDrawable animatedVectorDrawable = this.mAnimatedDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.draw(canvas);
            return;
        }
        boolean z = this.mState.mIsHardwareBitmap != canvas.isHardwareAccelerated();
        if (z) {
            this.mState.mIsHardwareBitmap = canvas.isHardwareAccelerated();
        }
        if (this.mState.mLastDrawnIcon == null || z) {
            int intrinsicWidth = getIntrinsicWidth();
            int intrinsicHeight = getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            Drawable mutate = this.mState.mChildState.newDrawable().mutate();
            setDrawableBounds(mutate);
            canvas2.save();
            if (this.mState.mHorizontalFlip) {
                canvas2.scale(-1.0f, 1.0f, intrinsicWidth * 0.5f, intrinsicHeight * 0.5f);
            }
            mutate.draw(canvas2);
            canvas2.restore();
            if (this.mState.mIsHardwareBitmap) {
                createBitmap = createBitmap.copy(Bitmap.Config.HARDWARE, false);
            }
            this.mState.mLastDrawnIcon = createBitmap;
        }
        canvas.save();
        ShadowDrawableState shadowDrawableState = this.mState;
        shadowDrawableState.getClass();
        canvas.translate(0.0f, shadowDrawableState.mTranslationY);
        canvas.rotate(this.mState.mRotateDegrees, getIntrinsicWidth() / 2, getIntrinsicHeight() / 2);
        ShadowDrawableState shadowDrawableState2 = this.mState;
        int i = shadowDrawableState2.mShadowSize;
        if (i > 0) {
            if (shadowDrawableState2.mLastDrawnShadow == null || z) {
                if (i == 0) {
                    shadowDrawableState2.mLastDrawnIcon = null;
                } else {
                    int intrinsicWidth2 = getIntrinsicWidth();
                    int intrinsicHeight2 = getIntrinsicHeight();
                    Bitmap createBitmap2 = Bitmap.createBitmap(intrinsicWidth2, intrinsicHeight2, Bitmap.Config.ARGB_8888);
                    Canvas canvas3 = new Canvas(createBitmap2);
                    Drawable mutate2 = this.mState.mChildState.newDrawable().mutate();
                    setDrawableBounds(mutate2);
                    canvas3.save();
                    if (this.mState.mHorizontalFlip) {
                        canvas3.scale(-1.0f, 1.0f, intrinsicWidth2 * 0.5f, intrinsicHeight2 * 0.5f);
                    }
                    mutate2.draw(canvas3);
                    canvas3.restore();
                    Paint paint = new Paint(3);
                    paint.setMaskFilter(new BlurMaskFilter(this.mState.mShadowSize, BlurMaskFilter.Blur.NORMAL));
                    Bitmap extractAlpha = createBitmap2.extractAlpha(paint, new int[2]);
                    paint.setMaskFilter(null);
                    createBitmap2.eraseColor(0);
                    canvas3.drawBitmap(extractAlpha, r4[0], r4[1], paint);
                    if (this.mState.mIsHardwareBitmap) {
                        createBitmap2 = createBitmap2.copy(Bitmap.Config.HARDWARE, false);
                    }
                    this.mState.mLastDrawnShadow = createBitmap2;
                }
            }
            double d = (float) ((this.mState.mRotateDegrees * 3.141592653589793d) / 180.0d);
            float cos = ((float) ((Math.cos(d) * this.mState.mShadowOffsetX) + (Math.sin(d) * this.mState.mShadowOffsetY))) - 0.0f;
            double cos2 = Math.cos(d) * this.mState.mShadowOffsetY;
            double sin = Math.sin(d);
            ShadowDrawableState shadowDrawableState3 = this.mState;
            canvas.drawBitmap(shadowDrawableState3.mLastDrawnShadow, cos, ((float) (cos2 - (sin * shadowDrawableState3.mShadowOffsetX))) - shadowDrawableState3.mTranslationY, this.mShadowPaint);
        }
        canvas.drawBitmap(this.mState.mLastDrawnIcon, (Rect) null, bounds, this.mIconPaint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.mState;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        ShadowDrawableState shadowDrawableState = this.mState;
        return ((Math.abs(shadowDrawableState.mShadowOffsetY) + shadowDrawableState.mShadowSize) * 2) + shadowDrawableState.mBaseHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        ShadowDrawableState shadowDrawableState = this.mState;
        return ((Math.abs(shadowDrawableState.mShadowOffsetX) + shadowDrawableState.mShadowSize) * 2) + shadowDrawableState.mBaseWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        AnimatedVectorDrawable animatedVectorDrawable = this.mAnimatedDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.jumpToCurrentState();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.mState.mAlpha = i;
        this.mIconPaint.setAlpha(i);
        updateShadowAlpha();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.mIconPaint.setColorFilter(colorFilter);
        if (this.mAnimatedDrawable != null) {
            this.mState.getClass();
            this.mAnimatedDrawable.setColorFilter(colorFilter);
        }
        invalidateSelf();
    }

    public final void setDarkIntensity(float f) {
        this.mState.mDarkIntensity = f;
        int intValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mState.mLightColor), Integer.valueOf(this.mState.mDarkColor))).intValue();
        updateShadowAlpha();
        setColorFilter(new PorterDuffColorFilter(intValue, PorterDuff.Mode.SRC_ATOP));
    }

    public final void setDrawableBounds(Drawable drawable) {
        ShadowDrawableState shadowDrawableState = this.mState;
        int abs = Math.abs(shadowDrawableState.mShadowOffsetX) + shadowDrawableState.mShadowSize;
        ShadowDrawableState shadowDrawableState2 = this.mState;
        int abs2 = Math.abs(shadowDrawableState2.mShadowOffsetY) + shadowDrawableState2.mShadowSize;
        drawable.setBounds(abs, abs2, getIntrinsicWidth() - abs, getIntrinsicHeight() - abs2);
    }

    public final void setRotation(float f) {
        ShadowDrawableState shadowDrawableState = this.mState;
        if (shadowDrawableState.mSupportsAnimation || shadowDrawableState.mRotateDegrees == f) {
            return;
        }
        shadowDrawableState.mRotateDegrees = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (visible) {
            jumpToCurrentState();
        }
        return visible;
    }

    public final void updateShadowAlpha() {
        int alpha = Color.alpha(this.mState.mShadowColor);
        this.mShadowPaint.setAlpha(Math.round((1.0f - this.mState.mDarkIntensity) * (r4.mAlpha / 255.0f) * alpha));
    }
}
