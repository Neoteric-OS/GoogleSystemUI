package com.android.keyguard;

import android.R;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ContextThemeWrapper;
import android.view.animation.Interpolator;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NumPadAnimator {
    public final GradientDrawable mBackground;
    public ValueAnimator mContractAnimator;
    public AnimatorSet mContractAnimatorSet;
    public final TextView mDigitTextView;
    public float mEndRadius;
    public ValueAnimator mExpandAnimator;
    public AnimatorSet mExpandAnimatorSet;
    public int mHeight;
    public final Drawable mImageButton;
    public int mNormalBackgroundColor;
    public int mPressedBackgroundColor;
    public float mStartRadius;
    public final int mStyle;
    public int mTextColorPressed;
    public int mTextColorPrimary;
    public int mWidth;

    public NumPadAnimator(Context context, Drawable drawable, int i, TextView textView, Drawable drawable2) {
        this.mStyle = i;
        this.mBackground = (GradientDrawable) drawable;
        this.mDigitTextView = textView;
        this.mImageButton = drawable2;
        reloadColors(context);
    }

    public final void onLayout(int i, int i2) {
        boolean z = i2 != this.mHeight;
        this.mWidth = i;
        this.mHeight = i2;
        float f = i2;
        float f2 = f / 2.0f;
        this.mStartRadius = f2;
        float f3 = f / 4.0f;
        this.mEndRadius = f3;
        this.mExpandAnimator.setFloatValues(f2, f3);
        this.mContractAnimator.setFloatValues(this.mEndRadius, this.mStartRadius);
        if (z) {
            this.mBackground.setCornerRadius(this.mStartRadius);
        }
    }

    public final void reloadColors(Context context) {
        int i;
        final int i2 = 3;
        final int i3 = 2;
        final int i4 = 1;
        final int i5 = 0;
        boolean z = this.mImageButton == null;
        int[] iArr = {R.attr.colorControlNormal};
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.mStyle);
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(iArr);
        if (obtainStyledAttributes.hasValue(0)) {
            i = obtainStyledAttributes.getColor(0, 0);
        } else {
            TypedArray obtainStyledAttributes2 = contextThemeWrapper.obtainStyledAttributes(new int[]{R.^attr-private.materialColorSurfaceContainerHigh});
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            i = color;
        }
        this.mNormalBackgroundColor = i;
        obtainStyledAttributes.recycle();
        this.mPressedBackgroundColor = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorPrimaryFixed, 0, context);
        this.mTextColorPressed = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorOnPrimaryFixed, 0, context);
        this.mBackground.setColor(this.mNormalBackgroundColor);
        this.mTextColorPrimary = z ? Utils.getColorAttrDefaultColor(R.^attr-private.materialColorOnSurface, 0, context) : Utils.getColorAttrDefaultColor(R.^attr-private.materialColorOnSecondaryFixed, 0, context);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mExpandAnimator = ofFloat;
        ofFloat.setDuration(100L);
        ValueAnimator valueAnimator = this.mExpandAnimator;
        Interpolator interpolator = Interpolators.LINEAR;
        valueAnimator.setInterpolator(interpolator);
        this.mExpandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i6 = i5;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i6) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(this.mNormalBackgroundColor), Integer.valueOf(this.mPressedBackgroundColor));
        ofObject.setDuration(50L);
        ofObject.setInterpolator(interpolator);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i6 = i4;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i6) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        ValueAnimator ofObject2 = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(this.mTextColorPrimary), Integer.valueOf(this.mTextColorPressed));
        ofObject2.setInterpolator(interpolator);
        ofObject2.setDuration(50L);
        ofObject2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i6 = i3;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i6) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.mExpandAnimatorSet = animatorSet;
        animatorSet.playTogether(this.mExpandAnimator, ofObject, ofObject2);
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mContractAnimator = ofFloat2;
        ofFloat2.setStartDelay(33L);
        this.mContractAnimator.setDuration(417L);
        this.mContractAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.mContractAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i6 = i2;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i6) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        ValueAnimator ofObject3 = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(this.mPressedBackgroundColor), Integer.valueOf(this.mNormalBackgroundColor));
        ofObject3.setInterpolator(interpolator);
        ofObject3.setStartDelay(33L);
        ofObject3.setDuration(417L);
        final int i6 = 4;
        ofObject3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i62 = i6;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i62) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        ValueAnimator ofObject4 = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(this.mTextColorPressed), Integer.valueOf(this.mTextColorPrimary));
        ofObject4.setInterpolator(interpolator);
        ofObject4.setStartDelay(33L);
        ofObject4.setDuration(417L);
        final int i7 = 5;
        ofObject4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.keyguard.NumPadAnimator$$ExternalSyntheticLambda0
            public final /* synthetic */ NumPadAnimator f$0;

            {
                this.f$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i62 = i7;
                NumPadAnimator numPadAnimator = this.f$0;
                switch (i62) {
                    case 0:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 1:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    case 2:
                        TextView textView = numPadAnimator.mDigitTextView;
                        if (textView != null) {
                            textView.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable = numPadAnimator.mImageButton;
                        if (drawable != null) {
                            drawable.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                    case 3:
                        numPadAnimator.mBackground.setCornerRadius(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        break;
                    case 4:
                        numPadAnimator.mBackground.setColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        break;
                    default:
                        TextView textView2 = numPadAnimator.mDigitTextView;
                        if (textView2 != null) {
                            textView2.setTextColor(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                        }
                        Drawable drawable2 = numPadAnimator.mImageButton;
                        if (drawable2 != null) {
                            drawable2.setTint(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                            break;
                        }
                        break;
                }
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mContractAnimatorSet = animatorSet2;
        animatorSet2.playTogether(this.mContractAnimator, ofObject3, ofObject4);
    }

    public final void setProgress(float f) {
        GradientDrawable gradientDrawable = this.mBackground;
        float f2 = this.mEndRadius;
        gradientDrawable.setCornerRadius(((this.mStartRadius - f2) * f) + f2);
        int i = this.mHeight;
        int i2 = (i - ((int) (((i * 0.3d) * f) + (i * 0.7f)))) / 2;
        this.mBackground.setBounds(0, i2, this.mWidth, i - i2);
    }
}
