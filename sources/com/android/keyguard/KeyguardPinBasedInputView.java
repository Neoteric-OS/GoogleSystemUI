package com.android.keyguard;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import com.android.internal.widget.LockscreenCredential;
import com.android.keyguard.PasswordTextView;
import com.android.settingslib.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardPinBasedInputView extends KeyguardAbsKeyInputView {
    public final NumPadKey[] mButtons;
    public NumPadButton mDeleteButton;
    public NumPadButton mOkButton;
    public PasswordTextView mPasswordEntry;

    public KeyguardPinBasedInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mButtons = new NumPadKey[10];
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final LockscreenCredential getEnteredCredential() {
        return LockscreenCredential.createPinOrNone(this.mPasswordEntry.mText);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public int getPromptReasonStringRes(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return R.string.kg_prompt_reason_restart_pin;
        }
        if (i == 2) {
            return R.string.kg_prompt_reason_timeout_pin;
        }
        if (i == 3) {
            return R.string.kg_prompt_reason_device_admin;
        }
        if (i == 4) {
            return R.string.kg_prompt_after_user_lockdown_pin;
        }
        if (i == 16) {
            return R.string.kg_prompt_after_update_pin;
        }
        switch (i) {
        }
        return R.string.kg_prompt_reason_timeout_pin;
    }

    @Override // com.android.keyguard.KeyguardInputView
    public CharSequence getTitle() {
        return getContext().getString(android.R.string.kg_failed_attempts_almost_at_login);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView, com.android.keyguard.KeyguardInputView, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        PasswordTextView passwordTextView = (PasswordTextView) findViewById(getPasswordTextViewId());
        this.mPasswordEntry = passwordTextView;
        passwordTextView.setSelected(true);
        this.mOkButton = (NumPadButton) findViewById(R.id.key_enter);
        NumPadButton numPadButton = (NumPadButton) findViewById(R.id.delete_button);
        this.mDeleteButton = numPadButton;
        numPadButton.setVisibility(0);
        this.mButtons[0] = (NumPadKey) findViewById(R.id.key0);
        this.mButtons[1] = (NumPadKey) findViewById(R.id.key1);
        this.mButtons[2] = (NumPadKey) findViewById(R.id.key2);
        this.mButtons[3] = (NumPadKey) findViewById(R.id.key3);
        this.mButtons[4] = (NumPadKey) findViewById(R.id.key4);
        this.mButtons[5] = (NumPadKey) findViewById(R.id.key5);
        this.mButtons[6] = (NumPadKey) findViewById(R.id.key6);
        this.mButtons[7] = (NumPadKey) findViewById(R.id.key7);
        this.mButtons[8] = (NumPadKey) findViewById(R.id.key8);
        this.mButtons[9] = (NumPadKey) findViewById(R.id.key9);
        this.mPasswordEntry.requestFocus();
        super.onFinishInflate();
        reloadColors();
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 67) {
            this.mDeleteButton.performClick();
            return true;
        }
        if (i >= 7 && i <= 16) {
            int i2 = i - 7;
            if (i2 >= 0 && i2 <= 9) {
                this.mButtons[i2].performClick();
            }
            return true;
        }
        if (i < 144 || i > 153) {
            super.onKeyDown(i, keyEvent);
            return false;
        }
        int i3 = i - 144;
        if (i3 >= 0 && i3 <= 9) {
            this.mButtons[i3].performClick();
        }
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!KeyEvent.isConfirmKey(i)) {
            return super.onKeyUp(i, keyEvent);
        }
        this.mOkButton.performClick();
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        return this.mPasswordEntry.requestFocus(i, rect);
    }

    public void reloadColors() {
        for (NumPadKey numPadKey : this.mButtons) {
            int defaultColor = Utils.getColorAttr(android.R.^attr-private.materialColorOnSurface, numPadKey.getContext()).getDefaultColor();
            int defaultColor2 = Utils.getColorAttr(android.R.attr.textColorSecondary, numPadKey.getContext()).getDefaultColor();
            numPadKey.mDigitText.setTextColor(defaultColor);
            numPadKey.mKlondikeText.setTextColor(defaultColor2);
            NumPadAnimator numPadAnimator = numPadKey.mAnimator;
            if (numPadAnimator != null) {
                numPadAnimator.reloadColors(numPadKey.getContext());
            }
        }
        PasswordTextView passwordTextView = this.mPasswordEntry;
        int defaultColor3 = Utils.getColorAttr(android.R.attr.textColorPrimary, passwordTextView.getContext()).getDefaultColor();
        passwordTextView.mDrawColor = defaultColor3;
        passwordTextView.mDrawPaint.setColor(defaultColor3);
        PinShapeInput pinShapeInput = passwordTextView.mPinShapeInput;
        if (pinShapeInput != null) {
            pinShapeInput.setDrawColor(passwordTextView.mDrawColor);
        }
        this.mDeleteButton.reloadColors();
        this.mOkButton.reloadColors();
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void resetPasswordText(boolean z, boolean z2) {
        PasswordTextView passwordTextView = this.mPasswordEntry;
        CharSequence transformedText = passwordTextView.getTransformedText();
        passwordTextView.mText = "";
        if (z) {
            int size = passwordTextView.mTextChars.size();
            int i = size - 1;
            int i2 = i / 2;
            int i3 = 0;
            while (i3 < size) {
                PasswordTextView.CharState charState = (PasswordTextView.CharState) passwordTextView.mTextChars.get(i3);
                charState.startRemoveAnimation(Math.min((i3 <= i2 ? i3 * 2 : i - (((i3 - i2) - 1) * 2)) * 40, 200L), Math.min(40 * i, 200L) + 160);
                PasswordTextView.this.removeCallbacks(charState.dotSwapperRunnable);
                charState.isDotSwapPending = false;
                i3++;
            }
        } else {
            passwordTextView.mTextChars.clear();
        }
        if (z) {
            passwordTextView.onUserActivity();
        }
        PinShapeInput pinShapeInput = passwordTextView.mPinShapeInput;
        if (pinShapeInput != null) {
            pinShapeInput.reset();
        }
        if (z2) {
            passwordTextView.sendAccessibilityEventTypeViewTextChanged(0, ((StringBuilder) transformedText).length(), 0, transformedText);
        }
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void setPasswordEntryEnabled(boolean z) {
        this.mPasswordEntry.setEnabled(z);
        this.mOkButton.setEnabled(z);
        if (!z || this.mPasswordEntry.hasFocus()) {
            return;
        }
        this.mPasswordEntry.requestFocus();
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final void setPasswordEntryInputEnabled(boolean z) {
        this.mPasswordEntry.setEnabled(z);
        this.mOkButton.setEnabled(z);
        if (z) {
            this.mPasswordEntry.requestFocus();
        }
    }
}
