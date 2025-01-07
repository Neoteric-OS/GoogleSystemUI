package com.android.systemui.biometrics;

import android.content.res.Resources;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricNotificationDialogFactory_Factory implements Provider {
    public static BiometricNotificationDialogFactory newInstance(Resources resources, SystemUIDialog.Factory factory, FingerprintManager fingerprintManager, FaceManager faceManager) {
        return new BiometricNotificationDialogFactory(resources, factory, fingerprintManager, faceManager);
    }
}
