package com.google.android.systemui.biometrics.domain;

import android.content.Context;
import android.frameworks.stats.IStats;
import android.hardware.biometrics.BiometricSourceType;
import com.android.app.tracing.TraceStateLogger;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.google.android.systemui.biometrics.domain.DeviceEntryUnlockMetricsLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BiometricUnlockTrackerImpl {
    public final Context context;
    public final FingerprintPropertyRepository fingerprintPropertyRepository;
    public final KeyguardUnlockAnimationController keyguardUnlockAnimationController;
    public long lastAcquiredTimeStamp;
    public long lastExitKeyguardTimeStamp;
    public final LatencyTrackerWrapper latencyTrackerWrapper;
    public DeviceEntryUnlockMetricsLogger.Session metricsLoggerSession;
    public final DeviceEntryUnlockStateMachine stateMachine;
    public final SysuiStatusBarStateController statusBarStateController;
    public final TraceStateLogger traceStateLogger;
    public final BiometricSourceType type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BiometricSourceType.values().length];
            try {
                iArr[BiometricSourceType.FACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BiometricSourceType.FINGERPRINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BiometricUnlockTrackerImpl(BiometricSourceType biometricSourceType, Context context, LatencyTracker latencyTracker, DeviceEntryUnlockStateMachine deviceEntryUnlockStateMachine, SysuiStatusBarStateController sysuiStatusBarStateController, FingerprintPropertyRepository fingerprintPropertyRepository, KeyguardUnlockAnimationController keyguardUnlockAnimationController) {
        this.type = biometricSourceType;
        this.context = context;
        this.stateMachine = deviceEntryUnlockStateMachine;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.fingerprintPropertyRepository = fingerprintPropertyRepository;
        this.keyguardUnlockAnimationController = keyguardUnlockAnimationController;
        IStats iStats = DeviceEntryUnlockMetricsLogger.statsService;
        this.metricsLoggerSession = new DeviceEntryUnlockMetricsLogger.Session(BiometricSourceType.FINGERPRINT);
        BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
        this.traceStateLogger = new TraceStateLogger(biometricSourceType == biometricSourceType2 ? "FaceUnlockStages" : "FpsUnlockStages", 14);
        this.latencyTrackerWrapper = new LatencyTrackerWrapper(latencyTracker, biometricSourceType == biometricSourceType2 ? 28 : 24);
    }
}
