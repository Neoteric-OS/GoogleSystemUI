package com.android.systemui.biometrics.ui.viewmodel;

import android.content.Context;
import com.android.systemui.biometrics.domain.model.BiometricPromptRequest;
import com.android.wm.shell.R;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CredentialViewModelKt {
    public static final String asBadCredentialErrorMessage(Context context, ClassReference classReference) {
        return context.getString(classReference.equals(Reflection.getOrCreateKotlinClass(BiometricPromptRequest.Credential.Pin.class)) ? R.string.biometric_dialog_wrong_pin : (!classReference.equals(Reflection.getOrCreateKotlinClass(BiometricPromptRequest.Credential.Password.class)) && classReference.equals(Reflection.getOrCreateKotlinClass(BiometricPromptRequest.Credential.Pattern.class))) ? R.string.biometric_dialog_wrong_pattern : R.string.biometric_dialog_wrong_password);
    }
}
