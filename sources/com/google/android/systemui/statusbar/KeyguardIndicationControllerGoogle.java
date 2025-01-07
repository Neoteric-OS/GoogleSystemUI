package com.google.android.systemui.statusbar;

import android.app.AlarmManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.app.IBatteryStats;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.settingslib.fuelgauge.BatteryUtils;
import com.android.settingslib.utils.PowerUtil;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.FaceHelpMessageDeferralFactory;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFingerprintAuthInteractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.util.IndicationHelper;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda14;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.statusbar.util.KeyguardStringV2;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardIndicationControllerGoogle extends KeyguardIndicationController {
    public boolean mAdaptiveChargingActive;
    public boolean mAdaptiveChargingEnabledInSettings;
    protected AdaptiveChargingManager mAdaptiveChargingManager;
    protected AdaptiveChargingManager.AdaptiveChargingStatusReceiver mAdaptiveChargingStatusReceiver;
    public int mBatteryLevel;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass1 mBroadcastReceiver;
    public Boolean mChargingStringV2Enabled;
    public final Context mContext;
    public final DeviceConfigProxy mDeviceConfig;
    public long mEstimatedChargeCompletion;
    public final GlobalSettings mGlobalSettings;
    public boolean mInited;
    public final KeyguardStringV2 mKeyguardStringV2;
    public final SecureSettings mSecureSettings;
    public final SystemClock mSystemClock;
    public final TunerService mTunerService;
    public KeyguardUpdateMonitorCallback mUpdateMonitorCallback;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class GoogleKeyguardCallback extends KeyguardIndicationController.BaseKeyguardCallback {
        public GoogleKeyguardCallback() {
            super(KeyguardIndicationControllerGoogle.this);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x007a, code lost:
        
            if (r15 == 4) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x007c, code lost:
        
            r4 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0086, code lost:
        
            if (r2 != false) goto L30;
         */
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onRefreshBatteryInfo(com.android.settingslib.fuelgauge.BatteryStatus r17) {
            /*
                Method dump skipped, instructions count: 274
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.GoogleKeyguardCallback.onRefreshBatteryInfo(com.android.settingslib.fuelgauge.BatteryStatus):void");
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle$1] */
    public KeyguardIndicationControllerGoogle(Context context, Looper looper, WakeLock.Builder builder, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DockManager dockManager, BroadcastDispatcher broadcastDispatcher, DevicePolicyManager devicePolicyManager, IBatteryStats iBatteryStats, UserManager userManager, TunerService tunerService, DeviceConfigProxy deviceConfigProxy, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, FalsingManager falsingManager, AuthController authController, LockPatternUtils lockPatternUtils, ScreenLifecycle screenLifecycle, KeyguardBypassController keyguardBypassController, AccessibilityManager accessibilityManager, FaceHelpMessageDeferralFactory faceHelpMessageDeferralFactory, KeyguardLogger keyguardLogger, GlobalSettings globalSettings, AlternateBouncerInteractor alternateBouncerInteractor, AlarmManager alarmManager, UserTracker userTracker, BouncerMessageInteractor bouncerMessageInteractor, FeatureFlags featureFlags, IndicationHelper indicationHelper, KeyguardInteractor keyguardInteractor, SystemClock systemClock, SecureSettings secureSettings, BiometricMessageInteractor biometricMessageInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        super(context, looper, builder, keyguardStateController, statusBarStateController, keyguardUpdateMonitor, dockManager, broadcastDispatcher, devicePolicyManager, iBatteryStats, userManager, delayableExecutor, delayableExecutor2, falsingManager, authController, lockPatternUtils, screenLifecycle, keyguardBypassController, accessibilityManager, faceHelpMessageDeferralFactory, keyguardLogger, alternateBouncerInteractor, alarmManager, userTracker, bouncerMessageInteractor, featureFlags, indicationHelper, keyguardInteractor, biometricMessageInteractor, deviceEntryFingerprintAuthInteractor, deviceEntryFaceAuthInteractor);
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if ("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET".equals(intent.getAction())) {
                    KeyguardIndicationControllerGoogle.this.triggerAdaptiveChargingStatusUpdate();
                }
            }
        };
        this.mAdaptiveChargingStatusReceiver = new AdaptiveChargingManager.AdaptiveChargingStatusReceiver() { // from class: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.2
            @Override // com.google.android.systemui.googlebattery.AdaptiveChargingManager.AdaptiveChargingStatusReceiver
            public final void onReceiveStatus(int i, String str) {
                KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = KeyguardIndicationControllerGoogle.this;
                boolean z = keyguardIndicationControllerGoogle.mAdaptiveChargingActive;
                boolean z2 = AdaptiveChargingManager.DEBUG;
                keyguardIndicationControllerGoogle.mAdaptiveChargingActive = ("Active".equals(str) || "Enabled".equals(str)) && i > 0;
                long j = keyguardIndicationControllerGoogle.mEstimatedChargeCompletion;
                long currentTimeMillis = System.currentTimeMillis();
                TimeUnit timeUnit = TimeUnit.SECONDS;
                long millis = timeUnit.toMillis(i + 29) + currentTimeMillis;
                keyguardIndicationControllerGoogle.mEstimatedChargeCompletion = millis;
                long abs = Math.abs(millis - j);
                boolean z3 = keyguardIndicationControllerGoogle.mAdaptiveChargingActive;
                if (z != z3 || (z3 && abs > timeUnit.toMillis(30L))) {
                    keyguardIndicationControllerGoogle.updateDeviceEntryIndication(true);
                }
            }
        };
        this.mChargingStringV2Enabled = null;
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mTunerService = tunerService;
        this.mDeviceConfig = deviceConfigProxy;
        this.mAdaptiveChargingManager = new AdaptiveChargingManager(context);
        this.mGlobalSettings = globalSettings;
        this.mSystemClock = systemClock;
        this.mKeyguardStringV2 = new KeyguardStringV2();
        this.mSecureSettings = secureSettings;
        this.mUserTracker = userTracker;
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x01e2, code lost:
    
        if (r3 != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x011f, code lost:
    
        if (r2 != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0121, code lost:
    
        r3 = com.android.wm.shell.R.string.keyguard_indication_charging_time_v2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0163, code lost:
    
        if (r2 != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01ad, code lost:
    
        if (r3 != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01af, code lost:
    
        r2 = com.android.wm.shell.R.string.keyguard_indication_charging_time;
     */
    @Override // com.android.systemui.statusbar.KeyguardIndicationController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String computePowerIndication() {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.computePowerIndication():java.lang.String");
    }

    public final String getChargingTimeFormatted(Context context, long j) {
        if (!isChargingStringV2Enabled()) {
            return Formatter.formatShortElapsedTimeRoundingUpToMinutes(context, j);
        }
        ((SystemClockImpl) this.mSystemClock).getClass();
        long currentTimeMillis = System.currentTimeMillis() + j;
        long j2 = PowerUtil.FIFTEEN_MINUTES_MILLIS;
        if (j >= j2) {
            long abs = Math.abs(currentTimeMillis);
            long abs2 = Math.abs(j2);
            currentTimeMillis = abs2 * (((abs + abs2) - 1) / abs2);
        }
        return DateFormat.getInstanceForSkeleton(android.text.format.DateFormat.getTimeFormatString(context)).format(Date.from(Instant.ofEpochMilli(currentTimeMillis)));
    }

    @Override // com.android.systemui.statusbar.KeyguardIndicationController
    public final void init() {
        if (!super.mInited) {
            super.mInited = true;
            KeyguardIndicationController$$ExternalSyntheticLambda14 keyguardIndicationController$$ExternalSyntheticLambda14 = new KeyguardIndicationController$$ExternalSyntheticLambda14(this);
            DockObserver dockObserver = (DockObserver) this.mDockManager;
            dockObserver.getClass();
            Log.d("DLObserver", "add alignment listener: " + keyguardIndicationController$$ExternalSyntheticLambda14);
            if (!dockObserver.mAlignmentStateListeners.contains(keyguardIndicationController$$ExternalSyntheticLambda14)) {
                dockObserver.mAlignmentStateListeners.add(keyguardIndicationController$$ExternalSyntheticLambda14);
            }
            if (this.mUpdateMonitorCallback == null) {
                this.mUpdateMonitorCallback = new GoogleKeyguardCallback();
            }
            this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateMonitorCallback);
            KeyguardIndicationController.AnonymousClass4 anonymousClass4 = this.mStatusBarStateListener;
            StatusBarStateController statusBarStateController = this.mStatusBarStateController;
            statusBarStateController.addCallback(anonymousClass4);
            ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateCallback);
            anonymousClass4.onDozingChanged(statusBarStateController.isDozing());
        }
        if (this.mInited) {
            return;
        }
        this.mInited = true;
        this.mTunerService.addTunable(new TunerService.Tunable() { // from class: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda0
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                KeyguardIndicationControllerGoogle.this.refreshAdaptiveChargingEnabled();
            }
        }, "adaptive_charging_enabled");
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle$$ExternalSyntheticLambda1
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = KeyguardIndicationControllerGoogle.this;
                keyguardIndicationControllerGoogle.getClass();
                if (properties.getKeyset().contains("adaptive_charging_enabled")) {
                    keyguardIndicationControllerGoogle.triggerAdaptiveChargingStatusUpdate();
                }
            }
        };
        this.mDeviceConfig.getClass();
        DeviceConfig.addOnPropertiesChangedListener("adaptive_charging", this.mExecutor, onPropertiesChangedListener);
        triggerAdaptiveChargingStatusUpdate();
        this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, new IntentFilter("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET"), null, UserHandle.ALL);
    }

    public final boolean isChargingStringV2Enabled() {
        if (this.mChargingStringV2Enabled == null) {
            if (BatteryUtils.sChargingStringV2Enabled == null) {
                BatteryUtils.sChargingStringV2Enabled = Boolean.valueOf(SystemProperties.getBoolean("charging_string.apply_v2", false));
            }
            Boolean bool = BatteryUtils.sChargingStringV2Enabled;
            bool.booleanValue();
            this.mChargingStringV2Enabled = bool;
        }
        return this.mChargingStringV2Enabled.booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:
    
        if (r3.mAdaptiveChargingManager.isEnabled() != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void refreshAdaptiveChargingEnabled() {
        /*
            r3 = this;
            com.google.android.systemui.googlebattery.AdaptiveChargingManager r0 = r3.mAdaptiveChargingManager
            boolean r0 = r0.hasAdaptiveChargingFeature()
            if (r0 == 0) goto L1c
            java.lang.String r0 = "adaptive_charging"
            java.lang.String r1 = "adaptive_charging_enabled"
            r2 = 1
            boolean r0 = android.provider.DeviceConfig.getBoolean(r0, r1, r2)
            if (r0 == 0) goto L1c
            com.google.android.systemui.googlebattery.AdaptiveChargingManager r0 = r3.mAdaptiveChargingManager
            boolean r0 = r0.isEnabled()
            if (r0 == 0) goto L1c
            goto L1d
        L1c:
            r2 = 0
        L1d:
            r3.mAdaptiveChargingEnabledInSettings = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle.refreshAdaptiveChargingEnabled():void");
    }

    public final void setReverseChargingMessage(CharSequence charSequence) {
        if (this.mStatusBarStateController.isDozing()) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.mRotateTextViewController.hideIndication(10);
            return;
        }
        KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.mRotateTextViewController;
        Drawable drawable = this.mContext.getDrawable(R.anim.reverse_charging_animation);
        ColorStateList colorStateList = this.mInitialTextColorState;
        if (TextUtils.isEmpty(charSequence) && drawable == null) {
            throw new IllegalStateException("message or icon must be set");
        }
        if (colorStateList == null) {
            throw new IllegalStateException("text color must be set");
        }
        keyguardIndicationRotateTextViewController.getClass();
    }

    public final void triggerAdaptiveChargingStatusUpdate() {
        refreshAdaptiveChargingEnabled();
        if (!this.mAdaptiveChargingEnabledInSettings) {
            this.mAdaptiveChargingActive = false;
            return;
        }
        AdaptiveChargingManager adaptiveChargingManager = this.mAdaptiveChargingManager;
        AdaptiveChargingManager.AdaptiveChargingStatusReceiver adaptiveChargingStatusReceiver = this.mAdaptiveChargingStatusReceiver;
        adaptiveChargingManager.getClass();
        AdaptiveChargingManager.queryStatus(adaptiveChargingStatusReceiver);
    }
}
