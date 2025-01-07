package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothAutoOnRepository {
    public final CoroutineDispatcher backgroundDispatcher;
    public final BluetoothAdapter bluetoothAdapter;
    public final Flow isAutoOn;

    public BluetoothAutoOnRepository(LocalBluetoothManager localBluetoothManager, BluetoothAdapter bluetoothAdapter, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        BluetoothEventManager bluetoothEventManager;
        this.bluetoothAdapter = bluetoothAdapter;
        this.backgroundDispatcher = coroutineDispatcher;
        this.isAutoOn = (localBluetoothManager == null || (bluetoothEventManager = localBluetoothManager.mEventManager) == null) ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE) : FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BluetoothAutoOnRepository$isAutoOn$1$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new BluetoothAutoOnRepository$isAutoOn$1$1(bluetoothEventManager, null))), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(1), Boolean.FALSE);
    }
}
