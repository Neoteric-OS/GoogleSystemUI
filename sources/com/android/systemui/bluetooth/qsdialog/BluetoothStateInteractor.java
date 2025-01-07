package com.android.systemui.bluetooth.qsdialog;

import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothStateInteractor {
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow bluetoothStateUpdate;
    public final LocalBluetoothManager localBluetoothManager;
    public final BluetoothTileDialogLogger logger;

    public BluetoothStateInteractor(LocalBluetoothManager localBluetoothManager, BluetoothTileDialogLogger bluetoothTileDialogLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.localBluetoothManager = localBluetoothManager;
        this.logger = bluetoothTileDialogLogger;
        this.backgroundDispatcher = coroutineDispatcher;
        this.bluetoothStateUpdate = FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BluetoothStateInteractor$bluetoothStateUpdate$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new BluetoothStateInteractor$bluetoothStateUpdate$1(this, null))), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(1), Boolean.FALSE);
    }
}
