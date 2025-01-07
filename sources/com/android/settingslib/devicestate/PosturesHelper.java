package com.android.settingslib.devicestate;

import android.R;
import android.content.Context;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PosturesHelper {
    public final int[] foldedDeviceStates;
    public final int[] halfFoldedDeviceStates;
    public final int[] rearDisplayDeviceStates;
    public final int[] unfoldedDeviceStates;

    public PosturesHelper(Context context) {
        this.foldedDeviceStates = context.getResources().getIntArray(R.array.config_fontManagerServiceCerts);
        this.halfFoldedDeviceStates = context.getResources().getIntArray(R.array.config_healthConnectMigrationKnownSigners);
        this.unfoldedDeviceStates = context.getResources().getIntArray(R.array.config_packagesExemptFromSuspension);
        this.rearDisplayDeviceStates = context.getResources().getIntArray(R.array.config_requestVibrationParamsForUsages);
    }

    public final int deviceStateToPosture(int i) {
        if (ArraysKt.contains(this.foldedDeviceStates, i)) {
            return 0;
        }
        if (ArraysKt.contains(this.halfFoldedDeviceStates, i)) {
            return 1;
        }
        if (ArraysKt.contains(this.unfoldedDeviceStates, i)) {
            return 2;
        }
        return ArraysKt.contains(this.rearDisplayDeviceStates, i) ? 3 : -1;
    }
}
