package com.android.launcher3.icons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.InsetDrawable;
import android.util.SparseArray;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.ClockDrawableWrapper;
import com.android.launcher3.util.FlagOp;
import com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda0;
import com.android.launcher3.util.UserIconInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BaseIconFactory implements AutoCloseable {
    public static final float LEGACY_ICON_SCALE = (1.0f / ((AdaptiveIconDrawable.getExtraInsetFraction() * 2.0f) + 1.0f)) * 0.7f;
    public final Canvas mCanvas;
    public final ColorExtractor mColorExtractor;
    public final Context mContext;
    public final int mFullResIconDpi;
    public final int mIconBitmapSize;
    public IconNormalizer mNormalizer;
    public final Rect mOldBounds = new Rect();
    public ShadowGenerator mShadowGenerator;
    public final boolean mShapeDetection;
    public int mWrapperBackgroundColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmptyWrapper extends DrawableWrapper {
        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public final Drawable.ConstantState getConstantState() {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return null;
            }
            return drawable.getConstantState();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IconOptions {
        public UserIconInfo mUserIconInfo;
    }

    static {
        Color.rgb(245, 245, 245);
    }

    public BaseIconFactory(Context context, int i, int i2, boolean z) {
        new SparseArray();
        this.mWrapperBackgroundColor = -1;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mShapeDetection = z;
        this.mFullResIconDpi = i;
        this.mIconBitmapSize = i2;
        applicationContext.getPackageManager();
        this.mColorExtractor = new ColorExtractor();
        Canvas canvas = new Canvas();
        this.mCanvas = canvas;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
        this.mWrapperBackgroundColor = -1;
    }

    public static Drawable createScaledDrawable(Drawable drawable, float f) {
        float f2;
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicHeight <= intrinsicWidth || intrinsicWidth <= 0.0f) {
            f2 = (intrinsicWidth <= intrinsicHeight || intrinsicHeight <= 0.0f) ? f : (intrinsicHeight / intrinsicWidth) * f;
        } else {
            float f3 = (intrinsicWidth / intrinsicHeight) * f;
            f2 = f;
            f = f3;
        }
        float f4 = (1.0f - f) / 2.0f;
        float f5 = (1.0f - f2) / 2.0f;
        return new InsetDrawable(drawable, f4, f5, f4, f5);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mWrapperBackgroundColor = -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final BitmapInfo createBadgedIconBitmap(Drawable drawable, IconOptions iconOptions) {
        FlagOp flagOp;
        UserIconInfo userIconInfo;
        final int i = 1;
        float[] fArr = new float[1];
        AdaptiveIconDrawable normalizeAndWrapToAdaptiveIcon = normalizeAndWrapToAdaptiveIcon(drawable, null, fArr);
        final int i2 = 0;
        Bitmap createIconBitmap = createIconBitmap(normalizeAndWrapToAdaptiveIcon, fArr[0], 2);
        int findDominantColorByHue = this.mColorExtractor.findDominantColorByHue(createIconBitmap);
        BitmapInfo bitmapInfo = new BitmapInfo(createIconBitmap, findDominantColorByHue);
        final int i3 = 4;
        if (normalizeAndWrapToAdaptiveIcon instanceof BitmapInfo.Extender) {
            float f = fArr[0];
            ClockDrawableWrapper clockDrawableWrapper = (ClockDrawableWrapper) ((BitmapInfo.Extender) normalizeAndWrapToAdaptiveIcon);
            clockDrawableWrapper.getClass();
            bitmapInfo = new ClockDrawableWrapper.ClockBitmapInfo(createIconBitmap, findDominantColorByHue, f, clockDrawableWrapper.mAnimationInfo, createScaledBitmap(4, new AdaptiveIconDrawable(clockDrawableWrapper.getBackground().getConstantState().newDrawable(), null)));
        } else {
            int i4 = IconProvider.CONFIG_ICON_MASK_RES_ID;
        }
        final FlagOp$$ExternalSyntheticLambda0 flagOp$$ExternalSyntheticLambda0 = FlagOp.NO_OP;
        if (iconOptions == null || (userIconInfo = iconOptions.mUserIconInfo) == null) {
            flagOp = flagOp$$ExternalSyntheticLambda0;
        } else {
            int i5 = userIconInfo.type;
            final FlagOp flagOp2 = i5 == 1 ? new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i6) {
                    switch (i2) {
                        case 0:
                            return i | flagOp$$ExternalSyntheticLambda0.apply(i6);
                        default:
                            return (~i) & flagOp$$ExternalSyntheticLambda0.apply(i6);
                    }
                }
            } : new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i6) {
                    switch (i) {
                        case 0:
                            return i | flagOp$$ExternalSyntheticLambda0.apply(i6);
                        default:
                            return (~i) & flagOp$$ExternalSyntheticLambda0.apply(i6);
                    }
                }
            };
            final FlagOp flagOp3 = i5 == 2 ? new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i6) {
                    switch (i2) {
                        case 0:
                            return i3 | flagOp2.apply(i6);
                        default:
                            return (~i3) & flagOp2.apply(i6);
                    }
                }
            } : new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i6) {
                    switch (i) {
                        case 0:
                            return i3 | flagOp2.apply(i6);
                        default:
                            return (~i3) & flagOp2.apply(i6);
                    }
                }
            };
            final int i6 = 8;
            flagOp = i5 == 3 ? new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i62) {
                    switch (i2) {
                        case 0:
                            return i6 | flagOp3.apply(i62);
                        default:
                            return (~i6) & flagOp3.apply(i62);
                    }
                }
            } : new FlagOp() { // from class: com.android.launcher3.util.FlagOp$$ExternalSyntheticLambda1
                @Override // com.android.launcher3.util.FlagOp
                public final int apply(int i62) {
                    switch (i) {
                        case 0:
                            return i6 | flagOp3.apply(i62);
                        default:
                            return (~i6) & flagOp3.apply(i62);
                    }
                }
            };
        }
        if (flagOp == flagOp$$ExternalSyntheticLambda0) {
            return bitmapInfo;
        }
        BitmapInfo mo756clone = bitmapInfo.mo756clone();
        mo756clone.flags = flagOp.apply(mo756clone.flags);
        return mo756clone;
    }

    public final Bitmap createIconBitmap(Drawable drawable, float f, int i) {
        Bitmap createBitmap;
        int i2 = this.mIconBitmapSize;
        if (i == 1) {
            createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ALPHA_8);
        } else {
            if (i == 3 || i == 4) {
                GraphicsUtils$$ExternalSyntheticLambda0 graphicsUtils$$ExternalSyntheticLambda0 = GraphicsUtils.sOnNewBitmapRunnable;
                Picture picture = new Picture();
                drawIconBitmap(picture.beginRecording(i2, i2), drawable, f, i, null);
                picture.endRecording();
                return Bitmap.createBitmap(picture);
            }
            createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        }
        if (drawable == null) {
            return createBitmap;
        }
        this.mCanvas.setBitmap(createBitmap);
        drawIconBitmap(this.mCanvas, drawable, f, i, createBitmap);
        this.mCanvas.setBitmap(null);
        return createBitmap;
    }

    public final Bitmap createScaledBitmap(int i, Drawable drawable) {
        RectF rectF = new RectF();
        float[] fArr = new float[1];
        AdaptiveIconDrawable normalizeAndWrapToAdaptiveIcon = normalizeAndWrapToAdaptiveIcon(drawable, rectF, fArr);
        float f = fArr[0];
        float min = Math.min(Math.min(rectF.left, rectF.right), rectF.top);
        float f2 = min < 0.035f ? 0.465f / (0.5f - min) : 1.0f;
        float f3 = rectF.bottom;
        if (f3 < 0.035f) {
            f2 = Math.min(f2, 0.465f / (0.5f - f3));
        }
        return createIconBitmap(normalizeAndWrapToAdaptiveIcon, Math.min(f, f2), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0148 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void drawIconBitmap(android.graphics.Canvas r11, android.graphics.drawable.Drawable r12, float r13, int r14, android.graphics.Bitmap r15) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.BaseIconFactory.drawIconBitmap(android.graphics.Canvas, android.graphics.drawable.Drawable, float, int, android.graphics.Bitmap):void");
    }

    public final IconNormalizer getNormalizer() {
        if (this.mNormalizer == null) {
            this.mNormalizer = new IconNormalizer(this.mContext, this.mIconBitmapSize, this.mShapeDetection);
        }
        return this.mNormalizer;
    }

    public final AdaptiveIconDrawable normalizeAndWrapToAdaptiveIcon(Drawable drawable, RectF rectF, float[] fArr) {
        AdaptiveIconDrawable adaptiveIconDrawable;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof AdaptiveIconDrawable) {
            adaptiveIconDrawable = (AdaptiveIconDrawable) drawable;
        } else {
            EmptyWrapper emptyWrapper = new EmptyWrapper(new ColorDrawable());
            AdaptiveIconDrawable adaptiveIconDrawable2 = new AdaptiveIconDrawable(new ColorDrawable(this.mWrapperBackgroundColor), emptyWrapper);
            adaptiveIconDrawable2.setBounds(0, 0, 1, 1);
            boolean[] zArr = new boolean[1];
            float scale = getNormalizer().getScale(drawable, rectF, adaptiveIconDrawable2.getIconMask(), zArr);
            if (zArr[0]) {
                emptyWrapper.setDrawable(createScaledDrawable(drawable, 1.0f - AdaptiveIconDrawable.getExtraInsetFraction()));
            } else {
                emptyWrapper.setDrawable(createScaledDrawable(drawable, scale * LEGACY_ICON_SCALE));
            }
            adaptiveIconDrawable = adaptiveIconDrawable2;
        }
        fArr[0] = getNormalizer().getScale(adaptiveIconDrawable, rectF, null, null);
        return adaptiveIconDrawable;
    }
}
