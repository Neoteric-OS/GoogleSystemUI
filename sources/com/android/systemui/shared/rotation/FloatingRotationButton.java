package com.android.systemui.shared.rotation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.shared.navigationbar.KeyButtonRipple;
import com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FloatingRotationButton {
    public AnimatedVectorDrawable mAnimatedDrawable;
    public final int mButtonDiameterResource;
    public int mContainerSize;
    public final int mContentDescriptionResource;
    public final Context mContext;
    public int mDisplayRotation;
    public final int mFloatingRotationBtnPositionLeftResource;
    public boolean mIsShowing;
    public final ViewGroup mKeyButtonContainer;
    public final FloatingRotationButtonView mKeyButtonView;
    public final int mMinMarginResource;
    public FloatingRotationButtonPositionCalculator.Position mPosition;
    public FloatingRotationButtonPositionCalculator mPositionCalculator;
    public RotationButtonController mRotationButtonController;
    public final int mRoundedContentPaddingResource;
    public final int mTaskbarBottomMarginResource;
    public final int mTaskbarLeftMarginResource;
    public NavigationBarView.AnonymousClass2 mUpdatesCallback;
    public final WindowManager mWindowManager;
    public boolean mIsTaskbarVisible = false;
    public boolean mIsTaskbarStashed = false;

    public FloatingRotationButton(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.rotate_suggestion, (ViewGroup) null);
        this.mKeyButtonContainer = viewGroup;
        FloatingRotationButtonView floatingRotationButtonView = (FloatingRotationButtonView) viewGroup.findViewById(R.id.rotate_suggestion);
        this.mKeyButtonView = floatingRotationButtonView;
        floatingRotationButtonView.setVisibility(0);
        floatingRotationButtonView.setContentDescription(context.getString(R.string.accessibility_rotate_button));
        KeyButtonRipple keyButtonRipple = new KeyButtonRipple(floatingRotationButtonView.getContext(), floatingRotationButtonView);
        floatingRotationButtonView.mRipple = keyButtonRipple;
        floatingRotationButtonView.setBackground(keyButtonRipple);
        this.mContentDescriptionResource = R.string.accessibility_rotate_button;
        this.mMinMarginResource = R.dimen.floating_rotation_button_min_margin;
        this.mRoundedContentPaddingResource = R.dimen.rounded_corner_content_padding;
        this.mTaskbarLeftMarginResource = R.dimen.floating_rotation_button_taskbar_left_margin;
        this.mTaskbarBottomMarginResource = R.dimen.floating_rotation_button_taskbar_bottom_margin;
        this.mButtonDiameterResource = R.dimen.floating_rotation_button_diameter;
        this.mFloatingRotationBtnPositionLeftResource = R.bool.floating_rotation_button_position_left;
        updateDimensionResources();
    }

    public final WindowManager.LayoutParams adjustViewPositionAndCreateLayoutParams() {
        int i = this.mContainerSize;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, i, 0, 0, 2024, 8, -3);
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle("FloatingRotationButton");
        layoutParams.setFitInsetsTypes(0);
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        this.mDisplayRotation = rotation;
        FloatingRotationButtonPositionCalculator.Position calculatePosition = this.mPositionCalculator.calculatePosition(rotation, this.mIsTaskbarVisible, this.mIsTaskbarStashed);
        this.mPosition = calculatePosition;
        layoutParams.gravity = calculatePosition.gravity;
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mKeyButtonView.getLayoutParams();
        FloatingRotationButtonPositionCalculator.Position position = this.mPosition;
        layoutParams2.gravity = position.gravity;
        updateTranslation(position, false);
        return layoutParams;
    }

    public final void hide() {
        if (this.mIsShowing) {
            this.mWindowManager.removeViewImmediate(this.mKeyButtonContainer);
            this.mIsShowing = false;
            NavigationBarView.AnonymousClass2 anonymousClass2 = this.mUpdatesCallback;
            if (anonymousClass2 != null) {
                NavigationBarView.this.notifyActiveTouchRegions();
            }
        }
    }

    public final void updateDimensionResources() {
        Resources resources = this.mContext.getResources();
        int max = Math.max(resources.getDimensionPixelSize(this.mMinMarginResource), resources.getDimensionPixelSize(this.mRoundedContentPaddingResource));
        int dimensionPixelSize = resources.getDimensionPixelSize(this.mTaskbarLeftMarginResource);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(this.mTaskbarBottomMarginResource);
        this.mPositionCalculator = new FloatingRotationButtonPositionCalculator(max, dimensionPixelSize, dimensionPixelSize2, resources.getBoolean(this.mFloatingRotationBtnPositionLeftResource));
        int dimensionPixelSize3 = resources.getDimensionPixelSize(this.mButtonDiameterResource);
        this.mKeyButtonView.mDiameter = dimensionPixelSize3;
        this.mContainerSize = Math.max(max, Math.max(dimensionPixelSize, dimensionPixelSize2)) + dimensionPixelSize3;
    }

    public final void updateIcon(int i, int i2) {
        FloatingRotationButtonView floatingRotationButtonView = this.mKeyButtonView;
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) floatingRotationButtonView.getContext().getDrawable(this.mRotationButtonController.mIconResId);
        this.mAnimatedDrawable = animatedVectorDrawable;
        animatedVectorDrawable.setBounds(0, 0, floatingRotationButtonView.getWidth(), floatingRotationButtonView.getHeight());
        floatingRotationButtonView.setImageDrawable(this.mAnimatedDrawable);
        floatingRotationButtonView.getDrawable().setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        floatingRotationButtonView.mOvalBgPaint.setColor(Color.valueOf(Color.red(i2), Color.green(i2), Color.blue(i2), 0.92f).toArgb());
        floatingRotationButtonView.mRipple.mType = KeyButtonRipple.Type.OVAL;
    }

    public final void updateTranslation(FloatingRotationButtonPositionCalculator.Position position, boolean z) {
        int i = position.translationX;
        FloatingRotationButtonView floatingRotationButtonView = this.mKeyButtonView;
        int i2 = position.translationY;
        if (z) {
            floatingRotationButtonView.animate().translationX(i).translationY(i2).setDuration(300L).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(new FloatingRotationButton$$ExternalSyntheticLambda0(this, 0)).start();
        } else {
            floatingRotationButtonView.setTranslationX(i);
            floatingRotationButtonView.setTranslationY(i2);
        }
    }
}
