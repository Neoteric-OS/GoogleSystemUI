package com.android.systemui.statusbar.policy.bluetooth;

import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl$$ExternalSyntheticLambda0;
import java.util.Collection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BluetoothRepositoryImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final LocalBluetoothManager localBluetoothManager;
    public final CoroutineScope scope;

    public BluetoothRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, LocalBluetoothManager localBluetoothManager) {
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.localBluetoothManager = localBluetoothManager;
    }

    public final void fetchConnectionStatusInBackground(Collection collection, BluetoothControllerImpl$$ExternalSyntheticLambda0 bluetoothControllerImpl$$ExternalSyntheticLambda0) {
        BuildersKt.launch$default(this.scope, null, null, new BluetoothRepositoryImpl$fetchConnectionStatusInBackground$1(this, collection, bluetoothControllerImpl$$ExternalSyntheticLambda0, null), 3);
    }
}
