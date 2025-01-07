package com.android.systemui.biometrics.ui.binder;

import android.content.Context;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricViewBinderKt {
    public static final String access$asDefaultHelpMessage(BiometricModalities biometricModalities, Context context) {
        return biometricModalities.getHasFingerprint() ? context.getString(R.string.fingerprint_dialog_touch_sensor) : "";
    }
}
