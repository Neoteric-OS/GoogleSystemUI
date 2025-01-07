package com.google.android.systemui.dagger;

import android.hardware.SensorPrivacyManager;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SystemUIGoogleModule_ProvideSensorPrivacyControllerFactory implements Provider {
    public static SensorPrivacyControllerImpl provideSensorPrivacyController(SensorPrivacyManager sensorPrivacyManager) {
        SensorPrivacyControllerImpl sensorPrivacyControllerImpl = new SensorPrivacyControllerImpl(sensorPrivacyManager);
        sensorPrivacyControllerImpl.mSensorPrivacyEnabled = sensorPrivacyControllerImpl.mSensorPrivacyManager.isAllSensorPrivacyEnabled();
        sensorPrivacyControllerImpl.mSensorPrivacyManager.addAllSensorPrivacyListener(sensorPrivacyControllerImpl);
        return sensorPrivacyControllerImpl;
    }
}
