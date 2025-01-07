package com.android.systemui.accessibility.floatingmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuViewAppearance {
    public InstantInsetLayerDrawable mBackgroundDrawable;
    public String mContentDescription;
    public int mElevation;
    public int mImeShiftingSpace;
    public float mImeTop;
    public int mInset;
    public boolean mIsImeShowing;
    public int mLargeIconSize;
    public int mLargeMultipleRadius;
    public int mLargePadding;
    public int mLargeSingleRadius;
    public int mMargin;
    public final Position mPercentagePosition = new Position(0.0f, 0.0f);
    public float[] mRadii;
    public final Resources mRes;
    public int mSizeType;
    public int mSmallIconSize;
    public int mSmallMultipleRadius;
    public int mSmallPadding;
    public int mSmallSingleRadius;
    public int mStrokeColor;
    public int mStrokeWidth;
    public int mTargetFeaturesSize;
    public final WindowManager mWindowManager;

    public MenuViewAppearance(Context context, WindowManager windowManager) {
        this.mWindowManager = windowManager;
        this.mRes = context.getResources();
        update();
    }

    public static float[] createRadii(float f, boolean z) {
        return z ? new float[]{0.0f, 0.0f, f, f, f, f, 0.0f, 0.0f} : new float[]{f, f, 0.0f, 0.0f, 0.0f, 0.0f, f, f};
    }

    public final Rect getMenuDraggableBoundsWith(boolean z) {
        int i = this.mMargin;
        Rect rect = new Rect(getWindowAvailableBounds());
        int i2 = rect.top + i;
        rect.top = i2;
        int i3 = rect.right;
        int i4 = this.mSizeType;
        rect.right = i3 - ((i4 == 0 ? this.mSmallIconSize : this.mLargeIconSize) + ((i4 == 0 ? this.mSmallPadding : this.mLargePadding) * 2));
        if (z && this.mIsImeShowing) {
            int i5 = rect.bottom;
            rect.bottom = i5 - (((int) (i5 - this.mImeTop)) + this.mImeShiftingSpace);
        }
        int i6 = rect.bottom;
        int i7 = i4 == 0 ? this.mSmallPadding : this.mLargePadding;
        int i8 = i6 - (((((i4 == 0 ? this.mSmallIconSize : this.mLargeIconSize) + i7) * this.mTargetFeaturesSize) + i7) + i);
        rect.bottom = i8;
        rect.bottom = Math.max(i2, i8);
        return rect;
    }

    public final int getMenuHeight() {
        int height = getWindowAvailableBounds().height() - (this.mMargin * 2);
        int i = this.mSizeType;
        int i2 = i == 0 ? this.mSmallPadding : this.mLargePadding;
        return Math.min(height, (((i == 0 ? this.mSmallIconSize : this.mLargeIconSize) + i2) * this.mTargetFeaturesSize) + i2);
    }

    public final PointF getMenuPosition() {
        Rect menuDraggableBoundsWith = getMenuDraggableBoundsWith(false);
        float f = menuDraggableBoundsWith.left;
        float width = menuDraggableBoundsWith.width();
        Position position = this.mPercentagePosition;
        float f2 = (width * position.mPercentageX) + f;
        float height = (menuDraggableBoundsWith.height() * position.mPercentageY) + menuDraggableBoundsWith.top;
        float menuHeight = getMenuHeight() + height + this.mMargin;
        if (this.mIsImeShowing) {
            float f3 = this.mImeTop;
            if (menuHeight >= f3) {
                height = Math.max(menuDraggableBoundsWith.top, ((f3 - getMenuHeight()) - this.mMargin) - this.mImeShiftingSpace);
            }
        }
        return new PointF(f2, height);
    }

    public final int getMenuRadius(int i) {
        return this.mSizeType == 0 ? i > 1 ? this.mSmallMultipleRadius : this.mSmallSingleRadius : i > 1 ? this.mLargeMultipleRadius : this.mLargeSingleRadius;
    }

    public final Rect getWindowAvailableBounds() {
        WindowMetrics currentWindowMetrics = this.mWindowManager.getCurrentWindowMetrics();
        Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
        Rect rect = new Rect(currentWindowMetrics.getBounds());
        rect.left += insetsIgnoringVisibility.left;
        rect.right -= insetsIgnoringVisibility.right;
        rect.top += insetsIgnoringVisibility.top;
        rect.bottom -= insetsIgnoringVisibility.bottom;
        return rect;
    }

    public final boolean isMenuOnLeftSide() {
        return this.mPercentagePosition.mPercentageX < 0.5f;
    }

    public final void update() {
        this.mMargin = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_margin);
        this.mSmallPadding = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_small_padding);
        this.mLargePadding = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_large_padding);
        this.mSmallIconSize = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_small_width_height);
        this.mLargeIconSize = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_large_width_height);
        this.mSmallSingleRadius = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_small_single_radius);
        this.mSmallMultipleRadius = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_small_multiple_radius);
        this.mRadii = createRadii(getMenuRadius(this.mTargetFeaturesSize), isMenuOnLeftSide());
        this.mLargeSingleRadius = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_large_single_radius);
        this.mLargeMultipleRadius = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_large_multiple_radius);
        this.mStrokeWidth = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_stroke_width);
        this.mStrokeColor = this.mRes.getColor(R.color.accessibility_floating_menu_stroke_dark);
        this.mInset = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_stroke_inset);
        this.mElevation = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_elevation);
        this.mImeShiftingSpace = this.mRes.getDimensionPixelSize(R.dimen.accessibility_floating_menu_ime_shifting_space);
        this.mBackgroundDrawable = new InstantInsetLayerDrawable(new Drawable[]{this.mRes.getDrawable(R.drawable.accessibility_floating_menu_background)});
        this.mContentDescription = this.mRes.getString(android.R.string.accessibility_magnification_chooser_text);
    }
}
