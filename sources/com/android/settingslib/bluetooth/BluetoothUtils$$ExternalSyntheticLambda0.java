package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothLeBroadcastReceiveState;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class BluetoothUtils$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return BluetoothUtils.isConnected((BluetoothLeBroadcastReceiveState) obj);
            default:
                return ((Long) obj).longValue() != 0;
        }
    }
}
