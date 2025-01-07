package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.KeyguardIndication;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda6;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class KeyguardIndicationTextView extends TextView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mAnimationsEnabled;
    public KeyguardIndication mKeyguardIndicationInfo;
    public Animator mLastAnimator;
    public CharSequence mMessage;

    public KeyguardIndicationTextView(Context context) {
        super(context);
        this.mAnimationsEnabled = true;
    }

    public final AnimatorSet getOutAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<KeyguardIndicationTextView, Float>) View.ALPHA, 0.0f);
        ofFloat.setDuration(!this.mAnimationsEnabled ? 0L : 167L);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.KeyguardIndicationTextView.3
            public boolean mCancelled = false;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.mCancelled = true;
                KeyguardIndicationTextView.this.setAlpha(0.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                boolean z;
                super.onAnimationEnd(animator);
                if (this.mCancelled) {
                    return;
                }
                KeyguardIndicationTextView keyguardIndicationTextView = KeyguardIndicationTextView.this;
                int i = KeyguardIndicationTextView.$r8$clinit;
                KeyguardIndication keyguardIndication = keyguardIndicationTextView.mKeyguardIndicationInfo;
                if (keyguardIndication != null) {
                    if (keyguardIndication.mBackground != null) {
                        keyguardIndicationTextView.setTextAppearance(R.style.TextAppearance_Keyguard_BottomArea_Button);
                    } else {
                        keyguardIndicationTextView.setTextAppearance(R.style.TextAppearance_Keyguard_BottomArea);
                    }
                    keyguardIndicationTextView.setBackground(keyguardIndicationTextView.mKeyguardIndicationInfo.mBackground);
                    keyguardIndicationTextView.setTextColor(keyguardIndicationTextView.mKeyguardIndicationInfo.mTextColor);
                    keyguardIndicationTextView.setOnClickListener(keyguardIndicationTextView.mKeyguardIndicationInfo.mOnClickListener);
                    keyguardIndicationTextView.setClickable(keyguardIndicationTextView.mKeyguardIndicationInfo.mOnClickListener != null);
                    Drawable drawable = keyguardIndicationTextView.mKeyguardIndicationInfo.mIcon;
                    if (drawable != null) {
                        drawable.setTint(keyguardIndicationTextView.getCurrentTextColor());
                        if (drawable instanceof AnimatedVectorDrawable) {
                            ((AnimatedVectorDrawable) drawable).start();
                        }
                    }
                    keyguardIndicationTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                    z = keyguardIndicationTextView.mKeyguardIndicationInfo.mForceAccessibilityLiveRegionAssertive;
                } else {
                    z = false;
                }
                if (!z) {
                    keyguardIndicationTextView.setAccessibilityLiveRegion(0);
                }
                keyguardIndicationTextView.setText(keyguardIndicationTextView.mMessage);
                if (z) {
                    keyguardIndicationTextView.setAccessibilityLiveRegion(2);
                }
            }
        });
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<KeyguardIndicationTextView, Float>) View.TRANSLATION_Y, 0.0f, -((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_indication_y_translation));
        ofFloat2.setDuration(this.mAnimationsEnabled ? 167L : 0L);
        animatorSet.playTogether(ofFloat, ofFloat2);
        return animatorSet;
    }

    public void setAnimationsEnabled(boolean z) {
        this.mAnimationsEnabled = z;
    }

    public final void switchIndication(CharSequence charSequence, KeyguardIndication keyguardIndication, final KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda6) {
        final int i = 2;
        final int i2 = 1;
        final int i3 = 0;
        this.mMessage = charSequence;
        this.mKeyguardIndicationInfo = keyguardIndication;
        boolean z = (keyguardIndication == null || keyguardIndication.mIcon == null) ? false : true;
        AnimatorSet animatorSet = new AnimatorSet();
        if (!TextUtils.isEmpty(this.mMessage) || z) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, (Property<KeyguardIndicationTextView, Float>) View.ALPHA, 1.0f);
            ofFloat.setStartDelay(!this.mAnimationsEnabled ? 0L : 150L);
            ofFloat.setDuration(!this.mAnimationsEnabled ? 0L : 317L);
            ofFloat.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, (Property<KeyguardIndicationTextView, Float>) View.TRANSLATION_Y, ((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_indication_y_translation), 0.0f);
            ofFloat2.setDuration(this.mAnimationsEnabled ? 600L : 0L);
            ofFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.KeyguardIndicationTextView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    switch (i) {
                        case 2:
                            super.onAnimationCancel(animator);
                            ((KeyguardIndicationTextView) this).setTranslationY(0.0f);
                            ((KeyguardIndicationTextView) this).setAlpha(1.0f);
                            break;
                        default:
                            super.onAnimationCancel(animator);
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    switch (i) {
                        case 0:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda62 = (KeyguardIndicationController$$ExternalSyntheticLambda6) this;
                            if (keyguardIndicationController$$ExternalSyntheticLambda62 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda62.run();
                                break;
                            }
                            break;
                        case 1:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda63 = (KeyguardIndicationController$$ExternalSyntheticLambda6) this;
                            if (keyguardIndicationController$$ExternalSyntheticLambda63 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda63.run();
                                break;
                            }
                            break;
                        default:
                            super.onAnimationEnd(animator);
                            break;
                    }
                }
            });
            animatorSet2.playTogether(ofFloat2, ofFloat);
            animatorSet2.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.KeyguardIndicationTextView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    switch (i3) {
                        case 2:
                            super.onAnimationCancel(animator);
                            ((KeyguardIndicationTextView) keyguardIndicationController$$ExternalSyntheticLambda6).setTranslationY(0.0f);
                            ((KeyguardIndicationTextView) keyguardIndicationController$$ExternalSyntheticLambda6).setAlpha(1.0f);
                            break;
                        default:
                            super.onAnimationCancel(animator);
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    switch (i3) {
                        case 0:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda62 = (KeyguardIndicationController$$ExternalSyntheticLambda6) keyguardIndicationController$$ExternalSyntheticLambda6;
                            if (keyguardIndicationController$$ExternalSyntheticLambda62 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda62.run();
                                break;
                            }
                            break;
                        case 1:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda63 = (KeyguardIndicationController$$ExternalSyntheticLambda6) keyguardIndicationController$$ExternalSyntheticLambda6;
                            if (keyguardIndicationController$$ExternalSyntheticLambda63 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda63.run();
                                break;
                            }
                            break;
                        default:
                            super.onAnimationEnd(animator);
                            break;
                    }
                }
            });
            animatorSet.playSequentially(getOutAnimator(), animatorSet2);
        } else {
            Animator outAnimator = getOutAnimator();
            outAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.KeyguardIndicationTextView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    switch (i2) {
                        case 2:
                            super.onAnimationCancel(animator);
                            ((KeyguardIndicationTextView) keyguardIndicationController$$ExternalSyntheticLambda6).setTranslationY(0.0f);
                            ((KeyguardIndicationTextView) keyguardIndicationController$$ExternalSyntheticLambda6).setAlpha(1.0f);
                            break;
                        default:
                            super.onAnimationCancel(animator);
                            break;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    switch (i2) {
                        case 0:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda62 = (KeyguardIndicationController$$ExternalSyntheticLambda6) keyguardIndicationController$$ExternalSyntheticLambda6;
                            if (keyguardIndicationController$$ExternalSyntheticLambda62 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda62.run();
                                break;
                            }
                            break;
                        case 1:
                            super.onAnimationEnd(animator);
                            KeyguardIndicationController$$ExternalSyntheticLambda6 keyguardIndicationController$$ExternalSyntheticLambda63 = (KeyguardIndicationController$$ExternalSyntheticLambda6) keyguardIndicationController$$ExternalSyntheticLambda6;
                            if (keyguardIndicationController$$ExternalSyntheticLambda63 != null) {
                                keyguardIndicationController$$ExternalSyntheticLambda63.run();
                                break;
                            }
                            break;
                        default:
                            super.onAnimationEnd(animator);
                            break;
                    }
                }
            });
            animatorSet.play(outAnimator);
        }
        Animator animator = this.mLastAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.mLastAnimator = animatorSet;
        animatorSet.start();
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimationsEnabled = true;
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimationsEnabled = true;
    }

    public KeyguardIndicationTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mAnimationsEnabled = true;
    }
}
