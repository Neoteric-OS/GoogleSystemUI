package com.android.keyguard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardSimPukView extends KeyguardSimInputView {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;

    public KeyguardSimPukView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputView
    public final int getPasswordTextViewId() {
        return R.id.pukEntry;
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputView, com.android.keyguard.KeyguardAbsKeyInputView
    public final int getPromptReasonStringRes(int i) {
        return 0;
    }

    public final String getPukPasswordErrorMessage(int i, boolean z, boolean z2) {
        String string;
        if (i == 0) {
            string = getContext().getString(R.string.kg_password_wrong_puk_code_dead);
        } else if (i > 0) {
            string = PluralMessageFormaterKt.icuMessageFormat(getResources(), z ? R.string.kg_password_default_puk_message : R.string.kg_password_wrong_puk_code, i);
        } else {
            string = getContext().getString(z ? R.string.kg_puk_enter_puk_hint : R.string.kg_password_puk_failed);
        }
        if (z2) {
            string = getResources().getString(R.string.kg_sim_lock_esim_instructions, string);
        }
        if (DEBUG) {
            Log.d("KeyguardSimPukView", "getPukPasswordErrorMessage: attemptsRemaining=" + i + " displayMessage=" + string);
        }
        return string;
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputView, com.android.keyguard.KeyguardInputView
    public final CharSequence getTitle() {
        return getContext().getString(android.R.string.kg_failed_attempts_now_wiping);
    }

    @Override // com.android.keyguard.KeyguardSimInputView, com.android.keyguard.KeyguardPinBasedInputView, com.android.keyguard.KeyguardAbsKeyInputView, com.android.keyguard.KeyguardInputView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        View view = this.mEcaView;
        if (view instanceof EmergencyCarrierArea) {
            ((EmergencyCarrierArea) view).mCarrierText.setVisibility(0);
        }
    }

    public KeyguardSimPukView(Context context) {
        this(context, null);
    }

    @Override // com.android.keyguard.KeyguardInputView
    public final void startAppearAnimation() {
    }
}
