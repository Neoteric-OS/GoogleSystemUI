package com.android.settingslib.bluetooth;

import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LocalBluetoothManagerExtKt {
    public static final CallbackFlowBuilder getHeadsetAudioModeChanges(LocalBluetoothManager localBluetoothManager) {
        return FlowKt.callbackFlow(new LocalBluetoothManagerExtKt$headsetAudioModeChanges$1(localBluetoothManager, null));
    }
}
