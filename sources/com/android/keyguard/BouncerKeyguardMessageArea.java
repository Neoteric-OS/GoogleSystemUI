package com.android.keyguard;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.settingslib.Utils;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BouncerKeyguardMessageArea extends KeyguardMessageArea {
    public final int DEFAULT_COLOR;
    public final long HIDE_DURATION_MILLIS;
    public final long SHOW_DURATION_MILLIS;
    public final AnimatorSet animatorSet;
    public ColorStateList mDefaultColorState;
    public ColorStateList mNextMessageColorState;
    public CharSequence textAboutToShow;

    public BouncerKeyguardMessageArea(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_COLOR = -1;
        this.mNextMessageColorState = ColorStateList.valueOf(-1);
        this.animatorSet = new AnimatorSet();
        this.SHOW_DURATION_MILLIS = 150L;
        this.HIDE_DURATION_MILLIS = 200L;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(this.mStyleResId, new int[]{R.attr.textColor});
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        this.mDefaultColorState = colorStateList;
    }

    @Override // com.android.keyguard.KeyguardMessageArea
    public final void onThemeChanged() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(this.mStyleResId, new int[]{R.attr.textColor});
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        if (colorStateList == null) {
            colorStateList = Utils.getColorAttr(R.^attr-private.materialColorOnSurface, getContext());
        }
        this.mDefaultColorState = colorStateList;
        update();
    }

    @Override // com.android.keyguard.KeyguardMessageArea
    public final void setMessage(final CharSequence charSequence, final boolean z) {
        if ((!Intrinsics.areEqual(charSequence, this.textAboutToShow) || charSequence == null) && !Intrinsics.areEqual(charSequence, getText())) {
            if (!z) {
                super.setMessage(charSequence, z);
                return;
            }
            this.textAboutToShow = charSequence;
            if (this.animatorSet.isRunning()) {
                this.animatorSet.cancel();
                this.textAboutToShow = null;
            }
            Property property = View.ALPHA;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<BouncerKeyguardMessageArea, Float>) property, 1.0f, 0.0f);
            ofFloat.setDuration(this.HIDE_DURATION_MILLIS);
            ofFloat.setInterpolator(Interpolators.STANDARD_ACCELERATE);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.keyguard.BouncerKeyguardMessageArea$setMessage$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    super/*com.android.keyguard.KeyguardMessageArea*/.setMessage(charSequence, z);
                }
            });
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<BouncerKeyguardMessageArea, Float>) property, 0.0f, 1.0f);
            ofFloat2.setDuration(this.SHOW_DURATION_MILLIS);
            ofFloat2.setInterpolator(Interpolators.STANDARD_DECELERATE);
            ofFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.android.keyguard.BouncerKeyguardMessageArea$setMessage$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    BouncerKeyguardMessageArea.this.textAboutToShow = null;
                }
            });
            this.animatorSet.playSequentially(ofFloat, ofFloat2);
            this.animatorSet.start();
        }
    }

    @Override // com.android.keyguard.SecurityMessageDisplay
    public final void setNextMessageColor(ColorStateList colorStateList) {
        this.mNextMessageColorState = colorStateList;
    }

    @Override // com.android.keyguard.KeyguardMessageArea
    public final void updateTextColor() {
        ColorStateList colorStateList = this.mDefaultColorState;
        ColorStateList colorStateList2 = this.mNextMessageColorState;
        if (colorStateList2 != null) {
            int defaultColor = colorStateList2.getDefaultColor();
            int i = this.DEFAULT_COLOR;
            if (defaultColor != i) {
                colorStateList = this.mNextMessageColorState;
                ColorStateList colorStateList3 = this.mDefaultColorState;
                if (colorStateList3 == null) {
                    colorStateList3 = ColorStateList.valueOf(i);
                }
                this.mNextMessageColorState = colorStateList3;
            }
        }
        setTextColor(colorStateList);
    }
}
