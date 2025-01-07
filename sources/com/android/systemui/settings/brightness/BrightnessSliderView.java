package com.android.systemui.settings.brightness;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.wm.shell.R;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BrightnessSliderView extends FrameLayout {
    public BrightnessSliderController$$ExternalSyntheticLambda0 mListener;
    public BrightnessSliderController.AnonymousClass1 mOnInterceptListener;
    public Drawable mProgressDrawable;
    public float mScale;
    public ToggleSeekBar mSlider;
    public final Rect mSystemGestureExclusionRect;

    public BrightnessSliderView(Context context) {
        this(context, null);
    }

    public final void applySliderScale() {
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int intrinsicHeight = (int) (this.mProgressDrawable.getIntrinsicHeight() * this.mScale);
            int intrinsicHeight2 = (this.mProgressDrawable.getIntrinsicHeight() - intrinsicHeight) / 2;
            this.mProgressDrawable.setBounds(bounds.left, intrinsicHeight2, bounds.right, intrinsicHeight + intrinsicHeight2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        BrightnessSliderController$$ExternalSyntheticLambda0 brightnessSliderController$$ExternalSyntheticLambda0 = this.mListener;
        if (brightnessSliderController$$ExternalSyntheticLambda0 != null) {
            brightnessSliderController$$ExternalSyntheticLambda0.f$0.mirrorTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public float getSliderScaleY() {
        return this.mScale;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setLayerType(2, null);
        ToggleSeekBar toggleSeekBar = (ToggleSeekBar) requireViewById(R.id.slider);
        this.mSlider = toggleSeekBar;
        toggleSeekBar.mAccessibilityLabel = getContentDescription().toString();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.rounded_slider_boundary_offset);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int i = -dimensionPixelSize;
        marginLayoutParams.setMargins(i, i, i, i);
        setLayoutParams(marginLayoutParams);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        try {
            this.mProgressDrawable = ((LayerDrawable) ((DrawableWrapper) ((LayerDrawable) this.mSlider.getProgressDrawable()).findDrawableByLayerId(android.R.id.progress)).getDrawable()).findDrawableByLayerId(R.id.slider_foreground);
        } catch (Exception unused) {
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        BrightnessSliderController.AnonymousClass1 anonymousClass1 = this.mOnInterceptListener;
        if (anonymousClass1 == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        anonymousClass1.onInterceptTouchEvent(motionEvent);
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        applySliderScale();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.notification_side_paddings);
        this.mSystemGestureExclusionRect.set(-dimensionPixelSize, 0, (i3 - i) + dimensionPixelSize, i4 - i2);
        setSystemGestureExclusionRects(Collections.singletonList(this.mSystemGestureExclusionRect));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent viewParent = ((FrameLayout) this).mParent;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void setSliderScaleY(float f) {
        if (f != this.mScale) {
            this.mScale = f;
            applySliderScale();
        }
    }

    public BrightnessSliderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        this.mSystemGestureExclusionRect = new Rect();
    }
}
