package com.android.keyguard;

import android.app.IActivityTaskManager;
import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorPrivacyManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Looper;
import android.os.UserManager;
import android.service.dreams.IDreamManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfig;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import dagger.internal.DelegateFactory;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardUpdateMonitor_Factory implements Provider {
    public static KeyguardUpdateMonitor newInstance(Context context, UserTracker userTracker, Looper looper, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, Executor executor, Executor executor2, StatusBarStateController statusBarStateController, LockPatternUtils lockPatternUtils, AuthController authController, TelephonyListenerManager telephonyListenerManager, InteractionJankMonitor interactionJankMonitor, LatencyTracker latencyTracker, ActiveUnlockConfig activeUnlockConfig, KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger, UiEventLogger uiEventLogger, Provider provider, TrustManager trustManager, SubscriptionManager subscriptionManager, UserManager userManager, IDreamManager iDreamManager, DevicePolicyManager devicePolicyManager, SensorPrivacyManager sensorPrivacyManager, TelephonyManager telephonyManager, PackageManager packageManager, FingerprintManager fingerprintManager, BiometricManager biometricManager, FaceWakeUpTriggersConfig faceWakeUpTriggersConfig, DevicePostureController devicePostureController, Optional optional, TaskStackChangeListeners taskStackChangeListeners, SelectedUserInteractor selectedUserInteractor, IActivityTaskManager iActivityTaskManager, Provider provider2, Provider provider3, DelegateFactory delegateFactory) {
        return new KeyguardUpdateMonitor(context, userTracker, looper, broadcastDispatcher, dumpManager, executor, executor2, statusBarStateController, lockPatternUtils, authController, telephonyListenerManager, interactionJankMonitor, latencyTracker, activeUnlockConfig, keyguardUpdateMonitorLogger, uiEventLogger, provider, trustManager, subscriptionManager, userManager, iDreamManager, devicePolicyManager, sensorPrivacyManager, telephonyManager, packageManager, fingerprintManager, biometricManager, faceWakeUpTriggersConfig, devicePostureController, optional, taskStackChangeListeners, selectedUserInteractor, iActivityTaskManager, provider2, provider3, delegateFactory);
    }
}
