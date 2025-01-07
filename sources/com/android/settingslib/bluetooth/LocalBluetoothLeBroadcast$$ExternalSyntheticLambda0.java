package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LocalBluetoothLeBroadcast$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LocalBluetoothLeBroadcast f$0;

    public /* synthetic */ LocalBluetoothLeBroadcast$$ExternalSyntheticLambda0(LocalBluetoothLeBroadcast localBluetoothLeBroadcast, int i) {
        this.$r8$classId = i;
        this.f$0 = localBluetoothLeBroadcast;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.f$0;
        switch (i) {
            case 0:
                return localBluetoothLeBroadcast.mServiceBroadcast.isPlaying(((BluetoothLeBroadcastMetadata) obj).getBroadcastId());
            case 1:
                List allSources = localBluetoothLeBroadcast.mServiceBroadcastAssistant.getAllSources((BluetoothDevice) obj);
                return !allSources.isEmpty() && allSources.stream().anyMatch(new BluetoothUtils$$ExternalSyntheticLambda0(0));
            default:
                localBluetoothLeBroadcast.getClass();
                return ((BluetoothLeBroadcastMetadata) obj).getBroadcastId() == localBluetoothLeBroadcast.mBroadcastId;
        }
    }
}
