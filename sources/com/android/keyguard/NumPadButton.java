package com.android.keyguard;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NumPadButton extends AlphaOptimizedImageButton implements NumPadAnimationListener {
    public NumPadAnimator mAnimator;
    public boolean mIsTransparentMode;
    public int mOrientation;
    public final int mStyleAttr;

    public NumPadButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStyleAttr = attributeSet.getStyleAttribute();
        setupAnimator();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        this.mOrientation = configuration.orientation;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setTextEntryKey(true);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        int i6 = i4 - i2;
        NumPadAnimator numPadAnimator = this.mAnimator;
        if (numPadAnimator != null) {
            numPadAnimator.onLayout(i5, i6);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        if (this.mAnimator == null || this.mOrientation == 2) {
            measuredWidth = (int) (measuredWidth * 0.66f);
        }
        setMeasuredDimension(getMeasuredWidth(), measuredWidth);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        NumPadAnimator numPadAnimator;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            NumPadAnimator numPadAnimator2 = this.mAnimator;
            if (numPadAnimator2 != null) {
                numPadAnimator2.mExpandAnimatorSet.cancel();
                numPadAnimator2.mContractAnimatorSet.cancel();
                numPadAnimator2.mExpandAnimatorSet.start();
            }
        } else if ((actionMasked == 1 || actionMasked == 3) && (numPadAnimator = this.mAnimator) != null) {
            numPadAnimator.mExpandAnimatorSet.cancel();
            numPadAnimator.mContractAnimatorSet.cancel();
            numPadAnimator.mContractAnimatorSet.start();
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void reloadColors() {
        NumPadAnimator numPadAnimator = this.mAnimator;
        if (numPadAnimator != null) {
            numPadAnimator.reloadColors(getContext());
        }
        ((VectorDrawable) getDrawable()).setTintList(ColorStateList.valueOf(Utils.getColorAttrDefaultColor(this.mIsTransparentMode ? R.^attr-private.materialColorOnSurface : R.^attr-private.materialColorOnSecondaryFixed, 0, getContext())));
    }

    @Override // com.android.keyguard.NumPadAnimationListener
    public final void setProgress(float f) {
        NumPadAnimator numPadAnimator = this.mAnimator;
        if (numPadAnimator != null) {
            numPadAnimator.setProgress(f);
        }
    }

    public final void setupAnimator() {
        Drawable background = getBackground();
        if (background instanceof GradientDrawable) {
            this.mAnimator = new NumPadAnimator(getContext(), background.mutate(), this.mStyleAttr, null, getDrawable());
        } else {
            this.mAnimator = null;
        }
    }
}
