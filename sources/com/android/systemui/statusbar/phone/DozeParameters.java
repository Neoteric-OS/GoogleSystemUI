package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.hardware.display.AmbientDisplayConfiguration;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.MathUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dumpable;
import com.android.systemui.doze.AlwaysOnDisplayPolicy;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.DozeInteractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DozeParameters implements TunerService.Tunable, com.android.systemui.plugins.statusbar.DozeParameters, Dumpable, ConfigurationController.ConfigurationListener, StatusBarStateController.StateListener, FoldAodAnimationController.FoldAodAnimationStatus {
    public final AlwaysOnDisplayPolicy mAlwaysOnPolicy;
    public final AmbientDisplayConfiguration mAmbientDisplayConfiguration;
    public final BatteryController mBatteryController;
    public boolean mControlScreenOffAnimation;
    public boolean mDozeAlwaysOn;
    public final DozeInteractor mDozeInteractor;
    public boolean mIsQuickPickupEnabled;
    final KeyguardUpdateMonitorCallback mKeyguardVisibilityCallback;
    public boolean mKeyguardVisible;
    public final PowerManager mPowerManager;
    public final Resources mResources;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public final UnlockedScreenOffAnimationController mUnlockedScreenOffAnimationController;
    public final UserTracker mUserTracker;
    public static final boolean FORCE_NO_BLANKING = SystemProperties.getBoolean("debug.force_no_blanking", false);
    public static final boolean FORCE_BLANKING = SystemProperties.getBoolean("debug.force_blanking", false);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SettingsObserver extends ContentObserver {
        public final Uri mAlwaysOnEnabled;
        public final Handler mHandler;
        public final Uri mPickupGesture;
        public final Uri mQuickPickupGesture;
        public final SecureSettings mSecureSettings;

        public SettingsObserver(Context context, Handler handler, SecureSettings secureSettings) {
            super(handler);
            this.mQuickPickupGesture = Settings.Secure.getUriFor("doze_quick_pickup_gesture");
            this.mPickupGesture = Settings.Secure.getUriFor("doze_pulse_on_pick_up");
            this.mAlwaysOnEnabled = Settings.Secure.getUriFor("doze_always_on");
            this.mHandler = handler;
            this.mSecureSettings = secureSettings;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            update(uri);
        }

        public final void update(Uri uri) {
            if (uri == null || this.mQuickPickupGesture.equals(uri) || this.mPickupGesture.equals(uri) || this.mAlwaysOnEnabled.equals(uri)) {
                DozeParameters dozeParameters = DozeParameters.this;
                dozeParameters.mIsQuickPickupEnabled = dozeParameters.mAmbientDisplayConfiguration.quickPickupSensorEnabled(((UserTrackerImpl) dozeParameters.mUserTracker).getUserId());
            }
        }
    }

    public DozeParameters(Context context, Handler handler, Resources resources, AmbientDisplayConfiguration ambientDisplayConfiguration, AlwaysOnDisplayPolicy alwaysOnDisplayPolicy, PowerManager powerManager, BatteryController batteryController, TunerService tunerService, DumpManager dumpManager, ScreenOffAnimationController screenOffAnimationController, Optional optional, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, KeyguardUpdateMonitor keyguardUpdateMonitor, ConfigurationController configurationController, StatusBarStateController statusBarStateController, UserTracker userTracker, DozeInteractor dozeInteractor, SecureSettings secureSettings) {
        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.phone.DozeParameters.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardVisibilityChanged(boolean z) {
                DozeParameters dozeParameters = DozeParameters.this;
                dozeParameters.mKeyguardVisible = z;
                dozeParameters.updateControlScreenOff();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onShadeExpandedChanged(boolean z) {
                DozeParameters.this.updateControlScreenOff();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserSwitchComplete(int i) {
                DozeParameters dozeParameters = DozeParameters.this;
                dozeParameters.mIsQuickPickupEnabled = dozeParameters.mAmbientDisplayConfiguration.quickPickupSensorEnabled(((UserTrackerImpl) dozeParameters.mUserTracker).getUserId());
            }
        };
        this.mKeyguardVisibilityCallback = keyguardUpdateMonitorCallback;
        this.mResources = resources;
        this.mAmbientDisplayConfiguration = ambientDisplayConfiguration;
        this.mAlwaysOnPolicy = alwaysOnDisplayPolicy;
        this.mBatteryController = batteryController;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "DozeParameters", this);
        boolean displayNeedsBlanking = getDisplayNeedsBlanking();
        this.mControlScreenOffAnimation = !displayNeedsBlanking;
        this.mPowerManager = powerManager;
        powerManager.setDozeAfterScreenOff(displayNeedsBlanking);
        this.mScreenOffAnimationController = screenOffAnimationController;
        this.mUnlockedScreenOffAnimationController = unlockedScreenOffAnimationController;
        this.mUserTracker = userTracker;
        this.mDozeInteractor = dozeInteractor;
        keyguardUpdateMonitor.registerCallback(keyguardUpdateMonitorCallback);
        tunerService.addTunable(this, "doze_always_on", "accessibility_display_inversion_enabled");
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        statusBarStateController.addCallback(this);
        FoldAodAnimationController foldAodAnimationController = (FoldAodAnimationController) optional.map(new DozeParameters$$ExternalSyntheticLambda0()).orElse(null);
        if (foldAodAnimationController != null) {
            foldAodAnimationController.statusListeners.add(this);
        }
        SettingsObserver settingsObserver = new SettingsObserver(context, handler, secureSettings);
        settingsObserver.mSecureSettings.registerContentObserverForUserAsync(settingsObserver.mQuickPickupGesture, settingsObserver);
        settingsObserver.mSecureSettings.registerContentObserverForUserAsync(settingsObserver.mPickupGesture, settingsObserver);
        settingsObserver.mSecureSettings.registerContentObserverForUserAsync(settingsObserver.mAlwaysOnEnabled, settingsObserver, -1, new DozeParameters$SettingsObserver$$ExternalSyntheticLambda0(settingsObserver, 0));
        batteryController.addCallback(new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.statusbar.phone.DozeParameters.2
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onPowerSaveChanged(boolean z) {
                DozeParameters.this.dispatchAlwaysOnEvent();
            }
        });
    }

    public final boolean canControlUnlockedScreenOff() {
        return getAlwaysOn() && !getDisplayNeedsBlanking();
    }

    public final void dispatchAlwaysOnEvent() {
        boolean alwaysOn = getAlwaysOn();
        Iterator it = this.mScreenOffAnimationController.animations.iterator();
        while (it.hasNext()) {
            ((ScreenOffAnimation) it.next()).onAlwaysOnChanged(alwaysOn);
        }
        boolean alwaysOn2 = getAlwaysOn();
        KeyguardRepositoryImpl keyguardRepositoryImpl = this.mDozeInteractor.keyguardRepository;
        Boolean valueOf = Boolean.valueOf(alwaysOn2);
        StateFlowImpl stateFlowImpl = keyguardRepositoryImpl._isAodAvailable;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("getAlwaysOn(): ");
        printWriter.println(getAlwaysOn());
        printWriter.print("getDisplayStateSupported(): ");
        printWriter.println(SystemProperties.getBoolean("doze.display.supported", this.mResources.getBoolean(R.bool.doze_display_state_supported)));
        printWriter.print("getPulseDuration(): ");
        printWriter.println(getInt(R.integer.doze_pulse_duration_out, "doze.pulse.duration.out") + getInt(R.integer.doze_pulse_duration_visible, "doze.pulse.duration.visible") + getInt(R.integer.doze_pulse_duration_in, "doze.pulse.duration.in"));
        printWriter.print("getPulseInDuration(): ");
        printWriter.println(getInt(R.integer.doze_pulse_duration_in, "doze.pulse.duration.in"));
        printWriter.print("getPulseInVisibleDuration(): ");
        printWriter.println(getInt(R.integer.doze_pulse_duration_visible, "doze.pulse.duration.visible"));
        printWriter.print("getPulseOutDuration(): ");
        printWriter.println(getInt(R.integer.doze_pulse_duration_out, "doze.pulse.duration.out"));
        printWriter.print("getPulseOnSigMotion(): ");
        printWriter.println(SystemProperties.getBoolean("doze.pulse.sigmotion", this.mResources.getBoolean(R.bool.doze_pulse_on_significant_motion)));
        printWriter.print("getVibrateOnSigMotion(): ");
        printWriter.println(SystemProperties.getBoolean("doze.vibrate.sigmotion", false));
        printWriter.print("getVibrateOnPickup(): ");
        printWriter.println(SystemProperties.getBoolean("doze.vibrate.pickup", false));
        printWriter.print("getProxCheckBeforePulse(): ");
        printWriter.println(SystemProperties.getBoolean("doze.pulse.proxcheck", this.mResources.getBoolean(R.bool.doze_proximity_check_before_pulse)));
        printWriter.print("getPickupVibrationThreshold(): ");
        printWriter.println(getInt(R.integer.doze_pickup_vibration_threshold, "doze.pickup.vibration.threshold"));
        printWriter.print("getSelectivelyRegisterSensorsUsingProx(): ");
        printWriter.println(SystemProperties.getBoolean("doze.prox.selectively_register", this.mResources.getBoolean(R.bool.doze_selectively_register_prox)));
        printWriter.print("isQuickPickupEnabled(): ");
        printWriter.println(this.mIsQuickPickupEnabled);
    }

    public final boolean getAlwaysOn() {
        return this.mDozeAlwaysOn && !((BatteryControllerImpl) this.mBatteryController).mAodPowerSave;
    }

    public final boolean getDisplayNeedsBlanking() {
        return FORCE_BLANKING || (!FORCE_NO_BLANKING && this.mResources.getBoolean(android.R.bool.config_displayBrightnessBucketsInDoze));
    }

    public final int getInt(int i, String str) {
        return MathUtils.constrain(SystemProperties.getInt(str, this.mResources.getInteger(i)), 0, 60000);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        updateControlScreenOff();
    }

    @Override // com.android.systemui.unfold.FoldAodAnimationController.FoldAodAnimationStatus
    public final void onFoldToAodAnimationChanged() {
        updateControlScreenOff();
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStatePostChange() {
        updateControlScreenOff();
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        this.mDozeAlwaysOn = this.mAmbientDisplayConfiguration.alwaysOnEnabled(((UserTrackerImpl) this.mUserTracker).getUserId());
        if (str.equals("doze_always_on")) {
            updateControlScreenOff();
        }
        dispatchAlwaysOnEvent();
    }

    public final boolean shouldClampToDimBrightness() {
        List list = this.mScreenOffAnimationController.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).shouldPlayAnimation()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.plugins.statusbar.DozeParameters
    public final boolean shouldControlScreenOff() {
        return this.mControlScreenOffAnimation;
    }

    public final boolean shouldShowLightRevealScrim() {
        List list = this.mScreenOffAnimationController.animations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((ScreenOffAnimation) it.next()).shouldPlayAnimation()) {
                return true;
            }
        }
        return false;
    }

    public final void updateControlScreenOff() {
        if (getDisplayNeedsBlanking()) {
            return;
        }
        boolean z = getAlwaysOn() && (this.mKeyguardVisible || this.mUnlockedScreenOffAnimationController.shouldPlayUnlockedScreenOffAnimation());
        if (this.mControlScreenOffAnimation == z) {
            return;
        }
        this.mControlScreenOffAnimation = z;
        this.mPowerManager.setDozeAfterScreenOff(!z);
    }
}
