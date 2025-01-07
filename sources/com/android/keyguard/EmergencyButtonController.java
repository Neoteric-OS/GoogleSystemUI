package com.android.keyguard;

import android.app.ActivityTaskManager;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.os.SystemClock;
import android.telecom.TelecomManager;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.DejankUtils;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.ViewController;
import com.google.android.msdl.domain.MSDLPlayer;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class EmergencyButtonController extends ViewController {
    public final ActivityTaskManager mActivityTaskManager;
    public final Executor mBackgroundExecutor;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass2 mConfigurationListener;
    public EmergencyButtonCallback mEmergencyButtonCallback;
    public final KeyguardUpdateMonitorCallback mInfoCallback;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LockPatternUtils mLockPatternUtils;
    public final Executor mMainExecutor;
    public final MetricsLogger mMetricsLogger;
    public final PowerManager mPowerManager;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final ShadeController mShadeController;
    public final TelecomManager mTelecomManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface EmergencyButtonCallback {
        void onEmergencyButtonClickedWhenInCall();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ActivityTaskManager mActivityTaskManager;
        public final Executor mBackgroundExecutor;
        public final ConfigurationController mConfigurationController;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
        public final LockPatternUtils mLockPatternUtils;
        public final MSDLPlayer mMSDLPlayer;
        public final Executor mMainExecutor;
        public final MetricsLogger mMetricsLogger;
        public final PowerManager mPowerManager;
        public final SelectedUserInteractor mSelectedUserInteractor;
        public final ShadeController mShadeController;
        public final TelecomManager mTelecomManager;

        public Factory(ConfigurationController configurationController, KeyguardUpdateMonitor keyguardUpdateMonitor, PowerManager powerManager, ActivityTaskManager activityTaskManager, ShadeController shadeController, TelecomManager telecomManager, MetricsLogger metricsLogger, LockPatternUtils lockPatternUtils, Executor executor, Executor executor2, SelectedUserInteractor selectedUserInteractor, MSDLPlayer mSDLPlayer) {
            this.mConfigurationController = configurationController;
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mPowerManager = powerManager;
            this.mActivityTaskManager = activityTaskManager;
            this.mShadeController = shadeController;
            this.mTelecomManager = telecomManager;
            this.mMetricsLogger = metricsLogger;
            this.mLockPatternUtils = lockPatternUtils;
            this.mMainExecutor = executor;
            this.mBackgroundExecutor = executor2;
            this.mSelectedUserInteractor = selectedUserInteractor;
            this.mMSDLPlayer = mSDLPlayer;
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.keyguard.EmergencyButtonController$2] */
    public EmergencyButtonController(EmergencyButton emergencyButton, ConfigurationController configurationController, KeyguardUpdateMonitor keyguardUpdateMonitor, PowerManager powerManager, ActivityTaskManager activityTaskManager, ShadeController shadeController, TelecomManager telecomManager, MetricsLogger metricsLogger, LockPatternUtils lockPatternUtils, Executor executor, Executor executor2, SelectedUserInteractor selectedUserInteractor, MSDLPlayer mSDLPlayer) {
        super(emergencyButton);
        this.mInfoCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.EmergencyButtonController.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onPhoneStateChanged() {
                EmergencyButtonController.this.updateEmergencyCallButton();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onSimStateChanged(int i, int i2, int i3) {
                EmergencyButtonController.this.updateEmergencyCallButton();
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.EmergencyButtonController.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                EmergencyButtonController.this.updateEmergencyCallButton();
            }
        };
        this.mConfigurationController = configurationController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mPowerManager = powerManager;
        this.mActivityTaskManager = activityTaskManager;
        this.mShadeController = shadeController;
        this.mTelecomManager = telecomManager;
        this.mMetricsLogger = metricsLogger;
        this.mLockPatternUtils = lockPatternUtils;
        this.mMainExecutor = executor;
        this.mBackgroundExecutor = executor2;
        this.mSelectedUserInteractor = selectedUserInteractor;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        DejankUtils.whitelistIpcs(new EmergencyButtonController$$ExternalSyntheticLambda0(this, 0));
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mKeyguardUpdateMonitor.registerCallback(this.mInfoCallback);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        ((EmergencyButton) this.mView).setOnClickListener(new View.OnClickListener() { // from class: com.android.keyguard.EmergencyButtonController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmergencyButtonController emergencyButtonController = EmergencyButtonController.this;
                emergencyButtonController.mMetricsLogger.action(200);
                PowerManager powerManager = emergencyButtonController.mPowerManager;
                if (powerManager != null) {
                    powerManager.userActivity(SystemClock.uptimeMillis(), true);
                }
                emergencyButtonController.mActivityTaskManager.stopSystemLockTaskMode();
                emergencyButtonController.mShadeController.collapseShade(false);
                emergencyButtonController.mBackgroundExecutor.execute(new EmergencyButtonController$$ExternalSyntheticLambda0(emergencyButtonController, 2));
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mKeyguardUpdateMonitor.removeCallback(this.mInfoCallback);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
    }

    public void updateEmergencyCallButton() {
        if (this.mView != null) {
            this.mBackgroundExecutor.execute(new EmergencyButtonController$$ExternalSyntheticLambda0(this, 1));
        }
    }
}
