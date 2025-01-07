package com.android.keyguard;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.TextViewInputDisabler;
import com.android.keyguard.KeyguardPasswordView;
import com.android.systemui.DejankUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardPasswordView extends KeyguardAbsKeyInputView {
    public static final int[] DISABLE_STATE_SET = {-16842910};
    public static final int[] ENABLE_STATE_SET = {R.attr.state_enabled};
    public boolean mAlreadyUsingSplitBouncer;
    public KeyguardSecurityContainer$$ExternalSyntheticLambda0 mDisappearAnimationListener;
    public final boolean mIsLockScreenLandscapeEnabled;
    public int mLastDevicePosture;
    public TextView mPasswordEntry;
    public TextViewInputDisabler mPasswordEntryDisabler;

    public KeyguardPasswordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAlreadyUsingSplitBouncer = false;
        this.mIsLockScreenLandscapeEnabled = false;
        this.mLastDevicePosture = 0;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final LockscreenCredential getEnteredCredential() {
        return LockscreenCredential.createPasswordOrNone(this.mPasswordEntry.getText());
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getPasswordTextViewId() {
        throw null;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getPromptReasonStringRes(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return com.android.wm.shell.R.string.kg_prompt_reason_restart_password;
        }
        if (i == 2) {
            return com.android.wm.shell.R.string.kg_prompt_reason_timeout_password;
        }
        if (i == 3) {
            return com.android.wm.shell.R.string.kg_prompt_reason_device_admin;
        }
        if (i == 4) {
            return com.android.wm.shell.R.string.kg_prompt_after_user_lockdown_password;
        }
        if (i == 16) {
            return com.android.wm.shell.R.string.kg_prompt_after_update_password;
        }
        switch (i) {
        }
        return com.android.wm.shell.R.string.kg_prompt_reason_timeout_password;
    }

    @Override // com.android.keyguard.KeyguardInputView
    public final CharSequence getTitle() {
        return getResources().getString(R.string.keyguard_password_entry_touch_hint);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getWrongPasswordStringId() {
        return com.android.wm.shell.R.string.kg_wrong_password;
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (!this.mPasswordEntry.isFocused() && isVisibleToUser()) {
            this.mPasswordEntry.requestFocus();
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    public final void onDevicePostureChanged(int i) {
        if (this.mLastDevicePosture == i) {
            return;
        }
        this.mLastDevicePosture = i;
        if (this.mIsLockScreenLandscapeEnabled) {
            boolean z = i == 1 && getResources().getConfiguration().orientation == 2 && getResources().getBoolean(com.android.wm.shell.R.bool.update_bouncer_constraints);
            if (this.mAlreadyUsingSplitBouncer != z) {
                this.mAlreadyUsingSplitBouncer = z;
                if (!z) {
                    throw null;
                }
                throw null;
            }
        }
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView, com.android.keyguard.KeyguardInputView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mPasswordEntry = (TextView) findViewById(com.android.wm.shell.R.id.passwordEntry);
        this.mPasswordEntryDisabler = new TextViewInputDisabler(this.mPasswordEntry);
        if (ActivityManager.isRunningInTestHarness()) {
            this.mPasswordEntry.setCursorVisible(false);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        return this.mPasswordEntry.requestFocus(i, rect);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            if (isVisibleToUser()) {
                post(new KeyguardPasswordView$$ExternalSyntheticLambda0(this, 0));
            } else {
                post(new KeyguardPasswordView$$ExternalSyntheticLambda0(this, 1));
            }
        }
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void resetPasswordText(boolean z, boolean z2) {
        this.mPasswordEntry.setText("");
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void setPasswordEntryEnabled(boolean z) {
        this.mPasswordEntry.setBackgroundTintList(ColorStateList.valueOf(this.mPasswordEntry.getTextColors().getColorForState(z ? ENABLE_STATE_SET : DISABLE_STATE_SET, 0)));
        this.mPasswordEntry.setCursorVisible(z);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void setPasswordEntryInputEnabled(boolean z) {
        this.mPasswordEntryDisabler.setInputEnabled(z);
    }

    @Override // com.android.keyguard.KeyguardInputView
    public final void startAppearAnimation() {
        setAlpha(0.0f);
        animate().alpha(1.0f).setDuration(300L).start();
        setTranslationY(0.0f);
    }

    @Override // com.android.keyguard.KeyguardInputView
    public final boolean startDisappearAnimation(Runnable runnable) {
        getWindowInsetsController().controlWindowInsetsAnimation(WindowInsets.Type.ime(), 100L, Interpolators.LINEAR, null, new AnonymousClass1(runnable));
        return true;
    }

    public KeyguardPasswordView(Context context) {
        this(context, null);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardPasswordView$1, reason: invalid class name */
    public final class AnonymousClass1 implements WindowInsetsAnimationControlListener {
        public final /* synthetic */ Runnable val$finishRunnable;

        public AnonymousClass1(Runnable runnable) {
            this.val$finishRunnable = runnable;
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public final void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
            KeyguardPasswordView keyguardPasswordView = KeyguardPasswordView.this;
            KeyguardPasswordViewController$$ExternalSyntheticLambda6 keyguardPasswordViewController$$ExternalSyntheticLambda6 = keyguardPasswordView.mOnFinishImeAnimationRunnable;
            if (keyguardPasswordViewController$$ExternalSyntheticLambda6 != null) {
                keyguardPasswordViewController$$ExternalSyntheticLambda6.run();
                keyguardPasswordView.mOnFinishImeAnimationRunnable = null;
            }
            this.val$finishRunnable.run();
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public final void onReady(final WindowInsetsAnimationController windowInsetsAnimationController, int i) {
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardPasswordView$1$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    KeyguardPasswordView.AnonymousClass1 anonymousClass1 = KeyguardPasswordView.AnonymousClass1.this;
                    WindowInsetsAnimationController windowInsetsAnimationController2 = windowInsetsAnimationController;
                    ValueAnimator valueAnimator2 = ofFloat;
                    anonymousClass1.getClass();
                    if (windowInsetsAnimationController2.isCancelled()) {
                        return;
                    }
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float animatedFraction = valueAnimator2.getAnimatedFraction();
                    Insets add = Insets.add(windowInsetsAnimationController2.getShownStateInsets(), Insets.of(0, 0, 0, (int) (((-r2.bottom) / 4) * animatedFraction)));
                    KeyguardSecurityContainer$$ExternalSyntheticLambda0 keyguardSecurityContainer$$ExternalSyntheticLambda0 = KeyguardPasswordView.this.mDisappearAnimationListener;
                    if (keyguardSecurityContainer$$ExternalSyntheticLambda0 != null) {
                        keyguardSecurityContainer$$ExternalSyntheticLambda0.f$0.setTranslationY(-r3);
                    }
                    windowInsetsAnimationController2.setInsetsAndAlpha(add, floatValue, animatedFraction);
                    KeyguardPasswordView.this.setAlpha(floatValue);
                }
            });
            ofFloat.addListener(new C00201(windowInsetsAnimationController));
            ofFloat.setInterpolator(Interpolators.FAST_OUT_LINEAR_IN);
            ofFloat.start();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.keyguard.KeyguardPasswordView$1$1, reason: invalid class name and collision with other inner class name */
        public final class C00201 extends AnimatorListenerAdapter {
            public final /* synthetic */ WindowInsetsAnimationController val$controller;

            public C00201(WindowInsetsAnimationController windowInsetsAnimationController) {
                this.val$controller = windowInsetsAnimationController;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                final WindowInsetsAnimationController windowInsetsAnimationController = this.val$controller;
                final Runnable runnable = AnonymousClass1.this.val$finishRunnable;
                DejankUtils.postAfterTraversal(new Runnable() { // from class: com.android.keyguard.KeyguardPasswordView$1$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        KeyguardPasswordView.AnonymousClass1.C00201 c00201 = KeyguardPasswordView.AnonymousClass1.C00201.this;
                        WindowInsetsAnimationController windowInsetsAnimationController2 = windowInsetsAnimationController;
                        Runnable runnable2 = runnable;
                        Trace.beginSection("KeyguardPasswordView#onAnimationEnd");
                        windowInsetsAnimationController2.finish(false);
                        KeyguardPasswordView keyguardPasswordView = KeyguardPasswordView.this;
                        KeyguardPasswordViewController$$ExternalSyntheticLambda6 keyguardPasswordViewController$$ExternalSyntheticLambda6 = keyguardPasswordView.mOnFinishImeAnimationRunnable;
                        if (keyguardPasswordViewController$$ExternalSyntheticLambda6 != null) {
                            keyguardPasswordViewController$$ExternalSyntheticLambda6.run();
                            keyguardPasswordView.mOnFinishImeAnimationRunnable = null;
                        }
                        runnable2.run();
                        KeyguardPasswordView.this.mDisappearAnimationListener = null;
                        Trace.endSection();
                    }
                });
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public final void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
        }
    }
}
