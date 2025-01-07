package com.android.settingslib.drawable;

import android.R;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserIconDrawable extends Drawable implements Drawable.Callback {
    public Drawable mBadge;
    public float mBadgeMargin;
    public float mBadgeRadius;
    public Bitmap mBitmap;
    public Paint mClearPaint;
    public float mDisplayRadius;
    public ColorStateList mFrameColor;
    public float mFramePadding;
    public Paint mFramePaint;
    public float mFrameWidth;
    public final Matrix mIconMatrix;
    public final Paint mIconPaint;
    public float mIntrinsicRadius;
    public boolean mInvalidated;
    public float mPadding;
    public final Paint mPaint;
    public final int mSize;
    public ColorStateList mTintColor;
    public PorterDuff.Mode mTintMode;
    public Drawable mUserDrawable;
    public Bitmap mUserIcon;

    public UserIconDrawable(int i) {
        Paint paint = new Paint();
        this.mIconPaint = paint;
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        this.mIconMatrix = new Matrix();
        this.mPadding = 0.0f;
        this.mSize = 0;
        this.mInvalidated = true;
        this.mTintColor = null;
        this.mTintMode = PorterDuff.Mode.SRC_ATOP;
        this.mFrameColor = null;
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint2.setFilterBitmap(true);
        paint2.setAntiAlias(true);
        if (i > 0) {
            setBounds(0, 0, i, i);
            this.mSize = i;
        }
        setIcon(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
    
        if (r2 == r1) goto L16;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(android.graphics.Canvas r5) {
        /*
            r4 = this;
            boolean r0 = r4.mInvalidated
            if (r0 == 0) goto L7
            r4.rebake()
        L7:
            android.graphics.Bitmap r0 = r4.mBitmap
            if (r0 == 0) goto L52
            android.content.res.ColorStateList r0 = r4.mTintColor
            if (r0 != 0) goto L16
            android.graphics.Paint r0 = r4.mPaint
            r1 = 0
            r0.setColorFilter(r1)
            goto L4a
        L16:
            int[] r1 = r4.getState()
            android.content.res.ColorStateList r2 = r4.mTintColor
            int r2 = r2.getDefaultColor()
            int r0 = r0.getColorForState(r1, r2)
            android.graphics.PorterDuff$Mode r1 = r4.mTintMode
            android.graphics.Paint r2 = r4.mPaint
            android.graphics.ColorFilter r2 = r2.getColorFilter()
            boolean r3 = r2 instanceof android.graphics.PorterDuffColorFilter
            if (r3 == 0) goto L3e
            android.graphics.PorterDuffColorFilter r2 = (android.graphics.PorterDuffColorFilter) r2
            int r3 = r2.getColor()
            android.graphics.PorterDuff$Mode r2 = r2.getMode()
            if (r3 != r0) goto L3e
            if (r2 == r1) goto L4a
        L3e:
            android.graphics.Paint r1 = r4.mPaint
            android.graphics.PorterDuffColorFilter r2 = new android.graphics.PorterDuffColorFilter
            android.graphics.PorterDuff$Mode r3 = r4.mTintMode
            r2.<init>(r0, r3)
            r1.setColorFilter(r2)
        L4a:
            android.graphics.Bitmap r0 = r4.mBitmap
            android.graphics.Paint r4 = r4.mPaint
            r1 = 0
            r5.drawBitmap(r0, r1, r1, r4)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.drawable.UserIconDrawable.draw(android.graphics.Canvas):void");
    }

    public Drawable getBadge() {
        return this.mBadge;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return new BitmapDrawable(this.mBitmap).getConstantState();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        int i = this.mSize;
        return i <= 0 ? ((int) this.mIntrinsicRadius) * 2 : i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public Drawable getUserDrawable() {
        return this.mUserDrawable;
    }

    public Bitmap getUserIcon() {
        return this.mUserIcon;
    }

    public final void initFramePaint() {
        if (this.mFramePaint == null) {
            Paint paint = new Paint();
            this.mFramePaint = paint;
            paint.setStyle(Paint.Style.STROKE);
            this.mFramePaint.setAntiAlias(true);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        super.invalidateSelf();
        this.mInvalidated = true;
    }

    public boolean isInvalidated() {
        return this.mInvalidated;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList = this.mFrameColor;
        return colorStateList != null && colorStateList.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        if (rect.isEmpty()) {
            return;
        }
        if (this.mUserIcon == null && this.mUserDrawable == null) {
            return;
        }
        float min = Math.min(rect.width(), rect.height()) * 0.5f;
        int i = (int) (min * 2.0f);
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || i != ((int) (this.mDisplayRadius * 2.0f))) {
            this.mDisplayRadius = min;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        }
        float min2 = Math.min(rect.width(), rect.height()) * 0.5f;
        this.mDisplayRadius = min2;
        float f = ((min2 - this.mFrameWidth) - this.mFramePadding) - this.mPadding;
        RectF rectF = new RectF(rect.exactCenterX() - f, rect.exactCenterY() - f, rect.exactCenterX() + f, rect.exactCenterY() + f);
        if (this.mUserDrawable != null) {
            Rect rect2 = new Rect();
            rectF.round(rect2);
            this.mIntrinsicRadius = Math.min(this.mUserDrawable.getIntrinsicWidth(), this.mUserDrawable.getIntrinsicHeight()) * 0.5f;
            this.mUserDrawable.setBounds(rect2);
        } else {
            if (this.mUserIcon != null) {
                float width = r7.getWidth() * 0.5f;
                float height = this.mUserIcon.getHeight() * 0.5f;
                this.mIntrinsicRadius = Math.min(width, height);
                float f2 = this.mIntrinsicRadius;
                this.mIconMatrix.setRectToRect(new RectF(width - f2, height - f2, width + f2, height + f2), rectF, Matrix.ScaleToFit.FILL);
            }
        }
        invalidateSelf();
    }

    public final void rebake() {
        this.mInvalidated = false;
        if (this.mBitmap != null) {
            if (this.mUserDrawable == null && this.mUserIcon == null) {
                return;
            }
            Canvas canvas = new Canvas(this.mBitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Drawable drawable = this.mUserDrawable;
            if (drawable != null) {
                drawable.draw(canvas);
            } else if (this.mUserIcon != null) {
                int save = canvas.save();
                canvas.concat(this.mIconMatrix);
                canvas.drawCircle(this.mUserIcon.getWidth() * 0.5f, this.mUserIcon.getHeight() * 0.5f, this.mIntrinsicRadius, this.mIconPaint);
                canvas.restoreToCount(save);
            }
            ColorStateList colorStateList = this.mFrameColor;
            if (colorStateList != null) {
                this.mFramePaint.setColor(colorStateList.getColorForState(getState(), 0));
            }
            float f = this.mFrameWidth;
            if (this.mFramePadding + f > 0.001f) {
                canvas.drawCircle(getBounds().exactCenterX(), getBounds().exactCenterY(), (this.mDisplayRadius - this.mPadding) - (f * 0.5f), this.mFramePaint);
            }
            if (this.mBadge != null) {
                float f2 = this.mBadgeRadius;
                if (f2 > 0.001f) {
                    float f3 = f2 * 2.0f;
                    float height = this.mBitmap.getHeight() - f3;
                    float width = this.mBitmap.getWidth() - f3;
                    this.mBadge.setBounds((int) width, (int) height, (int) (width + f3), (int) (f3 + height));
                    float width2 = (this.mBadge.getBounds().width() * 0.5f) + this.mBadgeMargin;
                    float f4 = this.mBadgeRadius;
                    canvas.drawCircle(width + f4, height + f4, width2, this.mClearPaint);
                    this.mBadge.draw(canvas);
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        super.invalidateSelf();
    }

    public final void setBadge(Drawable drawable) {
        this.mBadge = drawable;
        if (drawable == null) {
            invalidateSelf();
            return;
        }
        if (this.mClearPaint == null) {
            Paint paint = new Paint();
            this.mClearPaint = paint;
            paint.setAntiAlias(true);
            this.mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            this.mClearPaint.setStyle(Paint.Style.FILL);
        }
        onBoundsChange(getBounds());
    }

    public final void setBadgeIfManagedUser(int i, final Context context) {
        Drawable drawable;
        if (i != -10000) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
            if (devicePolicyManager.getProfileOwnerAsUser(i) != null && devicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle.of(i)) == null) {
                drawable = ((DevicePolicyManager) context.getSystemService(DevicePolicyManager.class)).getResources().getDrawableForDensity("WORK_PROFILE_ICON", "SOLID_COLORED", context.getResources().getDisplayMetrics().densityDpi, new Supplier() { // from class: com.android.settingslib.drawable.UserIconDrawable$$ExternalSyntheticLambda0
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        Context context2 = context;
                        return context2.getResources().getDrawableForDensity(R.drawable.ic_contact_picture, context2.getResources().getDisplayMetrics().densityDpi, context2.getTheme());
                    }
                });
                setBadge(drawable);
            }
        }
        drawable = null;
        setBadge(drawable);
    }

    public final void setIcon(Bitmap bitmap) {
        Drawable drawable = this.mUserDrawable;
        if (drawable != null) {
            drawable.setCallback(null);
            this.mUserDrawable = null;
        }
        this.mUserIcon = bitmap;
        if (bitmap == null) {
            this.mIconPaint.setShader(null);
            this.mBitmap = null;
        } else {
            Paint paint = this.mIconPaint;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        }
        onBoundsChange(getBounds());
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        this.mTintColor = colorStateList;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
