package com.google.android.systemui.biometrics.domain;

import android.frameworks.stats.IStats;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.google.pixel.vendor.PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType;
import android.hardware.google.pixel.vendor.PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType;
import android.os.ServiceManager;
import com.android.systemui.biometrics.shared.model.FingerprintSensorType;
import com.google.android.systemui.biometrics.DeviceEntryUnlockEvent;
import com.google.android.systemui.biometrics.DeviceEntryUnlockStage;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeviceEntryUnlockMetricsLogger {
    public static final IStats statsService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Session {
        public long authenticationDuration;
        public long exitKeyguardDuration;
        public PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType fpsSensorType;
        public final int modality;
        public boolean touchAnytimeEnabled;
        public PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType unlockAnimType;
        public boolean valid = false;
        public final List acquisitionDurationList = new ArrayList();
        public final List acquiredInfoList = new ArrayList();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BiometricSourceType.values().length];
                try {
                    iArr[BiometricSourceType.FACE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[DeviceEntryUnlockStage.values().length];
                try {
                    iArr2[4] = 1;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.CANCELED;
                    iArr2[1] = 2;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    DeviceEntryUnlockStage deviceEntryUnlockStage2 = DeviceEntryUnlockStage.CANCELED;
                    iArr2[2] = 3;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    DeviceEntryUnlockStage deviceEntryUnlockStage3 = DeviceEntryUnlockStage.CANCELED;
                    iArr2[3] = 4;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    DeviceEntryUnlockStage deviceEntryUnlockStage4 = DeviceEntryUnlockStage.CANCELED;
                    iArr2[7] = 5;
                } catch (NoSuchFieldError unused6) {
                }
                int[] iArr3 = new int[FingerprintSensorType.values().length];
                try {
                    iArr3[1] = 1;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    FingerprintSensorType fingerprintSensorType = FingerprintSensorType.UNKNOWN;
                    iArr3[2] = 2;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    FingerprintSensorType fingerprintSensorType2 = FingerprintSensorType.UNKNOWN;
                    iArr3[3] = 3;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    FingerprintSensorType fingerprintSensorType3 = FingerprintSensorType.UNKNOWN;
                    iArr3[4] = 4;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    FingerprintSensorType fingerprintSensorType4 = FingerprintSensorType.UNKNOWN;
                    iArr3[5] = 5;
                } catch (NoSuchFieldError unused11) {
                }
            }
        }

        public Session(BiometricSourceType biometricSourceType) {
            this.modality = WhenMappings.$EnumSwitchMapping$0[biometricSourceType.ordinal()] == 1 ? 4 : 1;
            this.fpsSensorType = PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.UNKNOWN;
            this.unlockAnimType = PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType.NONE;
        }

        public final void log(DeviceEntryUnlockEvent deviceEntryUnlockEvent, long j) {
            if (this.valid) {
                int ordinal = deviceEntryUnlockEvent.stage.ordinal();
                if (ordinal == 1) {
                    this.acquisitionDurationList.add(Long.valueOf(j));
                    this.acquiredInfoList.add(Long.valueOf(((DeviceEntryUnlockEvent.Acquired) deviceEntryUnlockEvent).authInfo.getAcquiredInfo()));
                    return;
                }
                if (ordinal == 2) {
                    this.authenticationDuration = j;
                    return;
                }
                if (ordinal == 3) {
                    DeviceEntryUnlockEvent.ExitKeyguard exitKeyguard = (DeviceEntryUnlockEvent.ExitKeyguard) deviceEntryUnlockEvent;
                    DeviceEntryUnlockEvent.ExitKeyguardInfo exitKeyguardInfo = exitKeyguard.info;
                    boolean z = exitKeyguard.unlockToLauncher;
                    boolean z2 = exitKeyguardInfo.isWakeAndUnlockNotFromDream;
                    this.unlockAnimType = (z2 && z) ? PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType.WAKE_TO_LAUNCHER : (!z2 || z) ? (z2 || !z) ? PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType.UNLOCK_TO_APP : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType.UNLOCK_TO_LAUNCHER : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType.WAKE_TO_APP;
                    return;
                }
                if (ordinal == 4) {
                    int ordinal2 = ((DeviceEntryUnlockEvent.Started) deviceEntryUnlockEvent).fpsSensorType.ordinal();
                    this.fpsSensorType = ordinal2 != 1 ? ordinal2 != 2 ? ordinal2 != 3 ? ordinal2 != 4 ? ordinal2 != 5 ? PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.UNKNOWN : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.HOME_BUTTON : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.POWER_BUTTON : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.UDFPS_OPTICAL : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.UDFPS_ULTRASONIC : PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.REAR;
                } else {
                    if (ordinal != 7) {
                        return;
                    }
                    this.exitKeyguardDuration = j;
                }
            }
        }
    }

    static {
        String str = DeviceEntryUnlockMetricsLoggerKt.ISTATS_INSTANCE_NAME;
        statsService = !ServiceManager.isDeclared(str) ? null : IStats.Stub.asInterface(ServiceManager.waitForDeclaredService(str));
    }
}
