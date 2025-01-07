package com.android.systemui.statusbar.policy;

import android.R;
import android.content.Context;
import com.android.systemui.log.LogBuffer;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceStateRotationLockSettingControllerLogger {
    public final int[] foldedStates;
    public final int[] halfFoldedStates;
    public final LogBuffer logBuffer;
    public final int[] rearDisplayStates;
    public final int[] unfoldedStates;

    public DeviceStateRotationLockSettingControllerLogger(LogBuffer logBuffer, Context context) {
        this.logBuffer = logBuffer;
        this.foldedStates = context.getResources().getIntArray(R.array.config_fontManagerServiceCerts);
        this.halfFoldedStates = context.getResources().getIntArray(R.array.config_healthConnectMigrationKnownSigners);
        this.unfoldedStates = context.getResources().getIntArray(R.array.config_packagesExemptFromSuspension);
        this.rearDisplayStates = context.getResources().getIntArray(R.array.config_requestVibrationParamsForUsages);
    }

    public static final String access$toDevicePostureString(DeviceStateRotationLockSettingControllerLogger deviceStateRotationLockSettingControllerLogger, int i) {
        return ArraysKt.contains(deviceStateRotationLockSettingControllerLogger.foldedStates, i) ? "Folded" : ArraysKt.contains(deviceStateRotationLockSettingControllerLogger.unfoldedStates, i) ? "Unfolded" : ArraysKt.contains(deviceStateRotationLockSettingControllerLogger.halfFoldedStates, i) ? "Half-Folded" : ArraysKt.contains(deviceStateRotationLockSettingControllerLogger.rearDisplayStates, i) ? "Rear display" : i == -1 ? "Uninitialized" : "Unknown";
    }
}
