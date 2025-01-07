package com.android.systemui.bluetooth.qsdialog;

import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioSharingInteractor {
    public final ReadonlyStateFlow audioSharingButtonStateUpdate;
    public final LocalBluetoothManager localBluetoothManager;

    public AudioSharingInteractor(LocalBluetoothManager localBluetoothManager, BluetoothStateInteractor bluetoothStateInteractor, DeviceItemInteractor deviceItemInteractor, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.localBluetoothManager = localBluetoothManager;
        FlowKt.stateIn(FlowKt.flowOn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(bluetoothStateInteractor.bluetoothStateUpdate, new ReadonlySharedFlow(deviceItemInteractor.mutableDeviceItemUpdate), new AudioSharingInteractor$audioSharingButtonStateUpdate$1(this, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(1), AudioSharingButtonState.Gone.INSTANCE);
    }
}
