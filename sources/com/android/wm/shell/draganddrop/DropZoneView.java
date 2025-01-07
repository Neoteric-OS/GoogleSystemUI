package com.android.wm.shell.draganddrop;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.FloatProperty;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DropZoneView extends FrameLayout {
    public static final AnonymousClass1 INSETS = new AnonymousClass1("insets");
    public ObjectAnimator mBackgroundAnimator;
    public float mBottomInset;
    public final ColorDrawable mColorDrawable;
    public final float[] mContainerMargin;
    public float mCornerRadius;
    public int mHighlightColor;
    public boolean mIgnoreBottomMargin;
    public ObjectAnimator mMarginAnimator;
    public int mMarginColor;
    public float mMarginPercent;
    public final MarginView mMarginView;
    public final Path mPath;
    public boolean mShowingHighlight;
    public boolean mShowingMargin;
    public boolean mShowingSplash;
    public int mSplashScreenColor;
    public final ImageView mSplashScreenView;
    public int mTargetBackgroundColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.draganddrop.DropZoneView$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((DropZoneView) obj).mMarginPercent);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            DropZoneView dropZoneView = (DropZoneView) obj;
            if (f != dropZoneView.mMarginPercent) {
                dropZoneView.mMarginPercent = f;
                dropZoneView.mMarginView.invalidate();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MarginView extends View {
        public MarginView(Context context) {
            super(context);
        }

        @Override // android.view.View
        public final void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            DropZoneView.this.mPath.reset();
            DropZoneView dropZoneView = DropZoneView.this;
            Path path = dropZoneView.mPath;
            float[] fArr = dropZoneView.mContainerMargin;
            float f = fArr[0];
            float f2 = dropZoneView.mMarginPercent;
            float f3 = f * f2;
            float f4 = f2 * fArr[1];
            float width = getWidth();
            DropZoneView dropZoneView2 = DropZoneView.this;
            float f5 = width - (dropZoneView2.mContainerMargin[2] * dropZoneView2.mMarginPercent);
            float height = getHeight();
            DropZoneView dropZoneView3 = DropZoneView.this;
            float f6 = dropZoneView3.mContainerMargin[3];
            float f7 = dropZoneView3.mMarginPercent;
            float f8 = (height - (f6 * f7)) - (dropZoneView3.mIgnoreBottomMargin ? 0.0f : dropZoneView3.mBottomInset);
            float f9 = f7 * dropZoneView3.mCornerRadius;
            path.addRoundRect(f3, f4, f5, f8, f9, f9, Path.Direction.CW);
            DropZoneView.this.mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
            canvas.clipPath(DropZoneView.this.mPath);
            canvas.drawColor(DropZoneView.this.mMarginColor);
        }
    }

    public DropZoneView(Context context) {
        super(context, null, 0, 0);
        this.mPath = new Path();
        this.mContainerMargin = new float[4];
        setContainerMargin(0.0f, 0.0f, 0.0f, 0.0f);
        this.mCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(context);
        this.mMarginColor = getResources().getColor(R.color.taskbar_background_dark);
        int color = getResources().getColor(android.R.color.system_accent1_500);
        this.mHighlightColor = Color.argb(1.0f, Color.red(color), Color.green(color), Color.blue(color));
        this.mSplashScreenColor = Color.argb(0.9f, 0.0f, 0.0f, 0.0f);
        ColorDrawable colorDrawable = new ColorDrawable();
        this.mColorDrawable = colorDrawable;
        setBackgroundDrawable(colorDrawable);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.split_icon_size);
        ImageView imageView = new ImageView(context);
        this.mSplashScreenView = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(imageView, new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 17));
        imageView.setAlpha(0.0f);
        MarginView marginView = new MarginView(context);
        this.mMarginView = marginView;
        addView(marginView, new FrameLayout.LayoutParams(-1, -1));
    }

    public final void animateBackground(int i, int i2) {
        if (i2 == this.mTargetBackgroundColor) {
            return;
        }
        ObjectAnimator objectAnimator = this.mBackgroundAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator ofArgb = ObjectAnimator.ofArgb(this.mColorDrawable, "color", i, i2);
        this.mBackgroundAnimator = ofArgb;
        if (!this.mShowingSplash && !this.mShowingHighlight) {
            ofArgb.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        }
        this.mBackgroundAnimator.start();
        this.mTargetBackgroundColor = i2;
    }

    public final void animateSplashScreenIcon() {
        this.mSplashScreenView.animate().alpha(this.mShowingSplash ? 1.0f : 0.0f).start();
    }

    public final void animateSwitch() {
        boolean z = this.mShowingHighlight;
        this.mShowingHighlight = !z;
        this.mShowingSplash = z;
        animateBackground(this.mColorDrawable.getColor(), !z ? this.mHighlightColor : this.mSplashScreenColor);
        animateSplashScreenIcon();
    }

    public final void onThemeChange() {
        this.mCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(getContext());
        this.mMarginColor = getResources().getColor(R.color.taskbar_background_dark);
        this.mHighlightColor = getResources().getColor(android.R.color.system_accent1_500);
        if (this.mMarginPercent > 0.0f) {
            this.mMarginView.invalidate();
        }
    }

    public final void setAppInfo(int i, Drawable drawable) {
        Color valueOf = Color.valueOf(i);
        this.mSplashScreenColor = Color.argb(0.9f, valueOf.red(), valueOf.green(), valueOf.blue());
        this.mSplashScreenView.setImageDrawable(drawable);
    }

    public final void setBottomInset(float f) {
        this.mBottomInset = f;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mSplashScreenView.getLayoutParams();
        layoutParams.bottomMargin = (int) f;
        this.mSplashScreenView.setLayoutParams(layoutParams);
        if (this.mMarginPercent > 0.0f) {
            this.mMarginView.invalidate();
        }
    }

    public final void setContainerMargin(float f, float f2, float f3, float f4) {
        float[] fArr = this.mContainerMargin;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        if (this.mMarginPercent > 0.0f) {
            this.mMarginView.invalidate();
        }
    }

    public final void setForceIgnoreBottomMargin(boolean z) {
        this.mIgnoreBottomMargin = z;
        if (this.mMarginPercent > 0.0f) {
            this.mMarginView.invalidate();
        }
    }

    public final void setShowingHighlight(boolean z) {
        this.mShowingHighlight = z;
        this.mShowingSplash = !z;
        animateBackground(0, z ? this.mHighlightColor : this.mSplashScreenColor);
        animateSplashScreenIcon();
    }

    public final void setShowingMargin(boolean z) {
        if (this.mShowingMargin != z) {
            this.mShowingMargin = z;
            ObjectAnimator objectAnimator = this.mMarginAnimator;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, INSETS, this.mMarginPercent, this.mShowingMargin ? 1.0f : 0.0f);
            this.mMarginAnimator = ofFloat;
            ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            this.mMarginAnimator.setDuration(this.mShowingMargin ? 400L : 250L);
            this.mMarginAnimator.start();
        }
        if (this.mShowingMargin) {
            return;
        }
        this.mShowingHighlight = false;
        this.mShowingSplash = false;
        animateBackground(this.mColorDrawable.getColor(), 0);
        animateSplashScreenIcon();
    }
}
