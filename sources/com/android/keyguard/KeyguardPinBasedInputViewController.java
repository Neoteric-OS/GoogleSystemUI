package com.android.keyguard;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.PasswordTextView;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardPinBasedInputViewController extends KeyguardAbsKeyInputViewController {
    public final KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0 mActionButtonTouchListener;
    public final FalsingCollector mFalsingCollector;
    public final KeyguardKeyboardInteractor mKeyguardKeyboardInteractor;
    public final KeyguardPinBasedInputViewController$$ExternalSyntheticLambda6 mOnKeyListener;
    public final PasswordTextView mPasswordEntry;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda6] */
    public KeyguardPinBasedInputViewController(KeyguardPinBasedInputView keyguardPinBasedInputView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, LiftToActivateListener liftToActivateListener, EmergencyButtonController emergencyButtonController, FalsingCollector falsingCollector, SelectedUserInteractor selectedUserInteractor, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardPinBasedInputView, keyguardUpdateMonitor, securityMode, lockPatternUtils, keyguardSecurityCallback, factory, latencyTracker, falsingCollector, emergencyButtonController, selectedUserInteractor, bouncerHapticPlayer, userActivityNotifier);
        this.mOnKeyListener = new View.OnKeyListener() { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda6
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = KeyguardPinBasedInputViewController.this;
                keyguardPinBasedInputViewController.getClass();
                if (keyEvent.getAction() == 0) {
                    return ((KeyguardPinBasedInputView) keyguardPinBasedInputViewController.mView).onKeyDown(i, keyEvent);
                }
                if (keyEvent.getAction() == 1) {
                    return ((KeyguardPinBasedInputView) keyguardPinBasedInputViewController.mView).onKeyUp(i, keyEvent);
                }
                return false;
            }
        };
        this.mActionButtonTouchListener = new KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0(this, 1);
        this.mFalsingCollector = falsingCollector;
        this.mKeyguardKeyboardInteractor = keyguardKeyboardInteractor;
        this.mPasswordEntry = (PasswordTextView) keyguardPinBasedInputView.findViewById(keyguardPinBasedInputView.getPasswordTextViewId());
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final int getInitialMessageResId() {
        return R.string.keyguard_enter_your_pin;
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public void onResume(int i) {
        this.mResumed = true;
        PasswordTextView passwordTextView = this.mPasswordEntry;
        passwordTextView.clearFocus();
        passwordTextView.requestFocus();
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public void onViewAttached() {
        final int i = 0;
        final int i2 = 1;
        super.onViewAttached();
        boolean z = !this.mLockPatternUtils.isPinEnhancedPrivacyEnabled(this.mSelectedUserInteractor.getSelectedUserId());
        PasswordTextView passwordTextView = this.mPasswordEntry;
        passwordTextView.mShowPassword = z;
        for (NumPadKey numPadKey : ((KeyguardPinBasedInputView) this.mView).mButtons) {
            numPadKey.setOnTouchListener(new KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0(this, 0));
            numPadKey.mAnimationsEnabled = z;
        }
        passwordTextView.setOnKeyListener(this.mOnKeyListener);
        passwordTextView.mUserActivityListener = new BasePasswordTextView$UserActivityListener() { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda1
            @Override // com.android.keyguard.BasePasswordTextView$UserActivityListener
            public final void onUserActivity() {
                KeyguardPinBasedInputViewController.this.onUserInput();
            }
        };
        View findViewById = ((KeyguardPinBasedInputView) this.mView).findViewById(R.id.delete_button);
        this.mBouncerHapticPlayer.getClass();
        KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0 keyguardPinBasedInputViewController$$ExternalSyntheticLambda0 = this.mActionButtonTouchListener;
        findViewById.setOnTouchListener(keyguardPinBasedInputViewController$$ExternalSyntheticLambda0);
        findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda2
            public final /* synthetic */ KeyguardPinBasedInputViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = this.f$0;
                switch (i3) {
                    case 0:
                        PasswordTextView passwordTextView2 = keyguardPinBasedInputViewController.mPasswordEntry;
                        if (passwordTextView2.isEnabled()) {
                            int length = passwordTextView2.mText.length();
                            if (length > 0) {
                                CharSequence transformedText = passwordTextView2.getTransformedText();
                                int i4 = length - 1;
                                passwordTextView2.mText = passwordTextView2.mText.substring(0, i4);
                                ((PasswordTextView.CharState) passwordTextView2.mTextChars.get(i4)).startRemoveAnimation(0L, 0L);
                                PinShapeInput pinShapeInput = passwordTextView2.mPinShapeInput;
                                if (pinShapeInput != null) {
                                    pinShapeInput.delete();
                                }
                                passwordTextView2.sendAccessibilityEventTypeViewTextChanged(((StringBuilder) transformedText).length() - 1, 1, 0, transformedText);
                            }
                            passwordTextView2.onUserActivity();
                            break;
                        }
                        break;
                    default:
                        if (keyguardPinBasedInputViewController.mPasswordEntry.isEnabled()) {
                            keyguardPinBasedInputViewController.verifyPasswordAndUnlock();
                            break;
                        }
                        break;
                }
            }
        });
        findViewById.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda3
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = KeyguardPinBasedInputViewController.this;
                if (keyguardPinBasedInputViewController.mPasswordEntry.isEnabled()) {
                    ((KeyguardPinBasedInputView) keyguardPinBasedInputViewController.mView).resetPasswordText(true, true);
                }
                keyguardPinBasedInputViewController.mBouncerHapticPlayer.getClass();
                ((KeyguardPinBasedInputView) keyguardPinBasedInputViewController.mView).performHapticFeedback(1, 1);
                return true;
            }
        });
        View findViewById2 = ((KeyguardPinBasedInputView) this.mView).findViewById(R.id.key_enter);
        if (findViewById2 != null) {
            findViewById2.setOnTouchListener(keyguardPinBasedInputViewController$$ExternalSyntheticLambda0);
            findViewById2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda2
                public final /* synthetic */ KeyguardPinBasedInputViewController f$0;

                {
                    this.f$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i3 = i2;
                    KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = this.f$0;
                    switch (i3) {
                        case 0:
                            PasswordTextView passwordTextView2 = keyguardPinBasedInputViewController.mPasswordEntry;
                            if (passwordTextView2.isEnabled()) {
                                int length = passwordTextView2.mText.length();
                                if (length > 0) {
                                    CharSequence transformedText = passwordTextView2.getTransformedText();
                                    int i4 = length - 1;
                                    passwordTextView2.mText = passwordTextView2.mText.substring(0, i4);
                                    ((PasswordTextView.CharState) passwordTextView2.mTextChars.get(i4)).startRemoveAnimation(0L, 0L);
                                    PinShapeInput pinShapeInput = passwordTextView2.mPinShapeInput;
                                    if (pinShapeInput != null) {
                                        pinShapeInput.delete();
                                    }
                                    passwordTextView2.sendAccessibilityEventTypeViewTextChanged(((StringBuilder) transformedText).length() - 1, 1, 0, transformedText);
                                }
                                passwordTextView2.onUserActivity();
                                break;
                            }
                            break;
                        default:
                            if (keyguardPinBasedInputViewController.mPasswordEntry.isEnabled()) {
                                keyguardPinBasedInputViewController.verifyPasswordAndUnlock();
                                break;
                            }
                            break;
                    }
                }
            });
        }
        JavaAdapterKt.collectFlow(passwordTextView, this.mKeyguardKeyboardInteractor.isAnyKeyboardConnected, new Consumer() { // from class: com.android.keyguard.KeyguardPinBasedInputViewController$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = KeyguardPinBasedInputViewController.this;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                Drawable background = keyguardPinBasedInputViewController.mPasswordEntry.getBackground();
                if (background instanceof StateListDrawable) {
                    Drawable stateDrawable = ((StateListDrawable) background).getStateDrawable(0);
                    if (stateDrawable instanceof GradientDrawable) {
                        GradientDrawable gradientDrawable = (GradientDrawable) stateDrawable;
                        int color = keyguardPinBasedInputViewController.mView.getResources().getColor(R.color.bouncer_password_focus_color);
                        if (booleanValue) {
                            gradientDrawable.setStroke((int) TypedValue.applyDimension(1, 3.0f, keyguardPinBasedInputViewController.mView.getResources().getDisplayMetrics()), color);
                        } else {
                            gradientDrawable.setStroke(0, color);
                        }
                    }
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = passwordTextView.getLayoutParams();
        layoutParams.width = (int) this.mView.getResources().getDimension(R.dimen.keyguard_pin_field_width);
        layoutParams.height = (int) this.mView.getResources().getDimension(R.dimen.keyguard_pin_field_height);
    }

    @Override // com.android.systemui.util.ViewController
    public void onViewDetached() {
        for (NumPadKey numPadKey : ((KeyguardPinBasedInputView) this.mView).mButtons) {
            numPadKey.setOnTouchListener(null);
        }
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public void resetState() {
        this.mMessageAreaController.setMessage(R.string.keyguard_enter_your_pin);
        ((KeyguardPinBasedInputView) this.mView).setPasswordEntryEnabled(true);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void startErrorAnimation() {
        final int i = 0;
        final int i2 = 1;
        KeyguardPinBasedInputView keyguardPinBasedInputView = (KeyguardPinBasedInputView) this.mView;
        keyguardPinBasedInputView.getClass();
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 1; i3 <= 9; i3++) {
            arrayList2.add(keyguardPinBasedInputView.mButtons[i3]);
        }
        arrayList2.add(keyguardPinBasedInputView.mDeleteButton);
        arrayList2.add(keyguardPinBasedInputView.mButtons[0]);
        arrayList2.add(keyguardPinBasedInputView.mOkButton);
        int i4 = 0;
        for (int i5 = 0; i5 < arrayList2.size(); i5++) {
            final View view = (View) arrayList2.get(i5);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setStartDelay(i4);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.8f);
            Interpolator interpolator = Interpolators.STANDARD;
            ofFloat.setInterpolator(interpolator);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardPinBasedInputView$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int i6 = i;
                    View view2 = view;
                    switch (i6) {
                        case 0:
                            view2.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            view2.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        default:
                            view2.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            view2.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                    }
                }
            });
            ofFloat.setDuration(50L);
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.8f, 1.0f);
            ofFloat2.setInterpolator(interpolator);
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardPinBasedInputView$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int i6 = i2;
                    View view2 = view;
                    switch (i6) {
                        case 0:
                            view2.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            view2.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        default:
                            view2.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            view2.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                    }
                }
            });
            ofFloat2.setDuration(617L);
            animatorSet2.playSequentially(ofFloat, ofFloat2);
            arrayList.add(animatorSet2);
            i4 += 33;
        }
        animatorSet.playTogether(arrayList);
        animatorSet.start();
    }
}
