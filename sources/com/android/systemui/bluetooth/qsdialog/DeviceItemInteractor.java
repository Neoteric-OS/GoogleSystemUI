package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceItemInteractor {
    public final AudioManager audioManager;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BluetoothAdapter bluetoothAdapter;
    public final BluetoothTileDialogRepository bluetoothTileDialogRepository;
    public final ReadonlySharedFlow deviceItemUpdateRequest;
    public final LocalBluetoothManager localBluetoothManager;
    public final BluetoothTileDialogLogger logger;
    public final SystemClock systemClock;
    public final SharedFlowImpl mutableDeviceItemUpdate = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    public final StateFlowImpl mutableShowSeeAllUpdate = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    public final List deviceItemFactoryList = CollectionsKt__CollectionsKt.listOf(new ActiveMediaDeviceItemFactory(), new AudioSharingMediaDeviceItemFactory(), new AvailableAudioSharingMediaDeviceItemFactory(), new AvailableMediaDeviceItemFactory(), new ConnectedDeviceItemFactory(), new SavedDeviceItemFactory());
    public final List displayPriority = CollectionsKt__CollectionsKt.listOf(DeviceItemType.ACTIVE_MEDIA_BLUETOOTH_DEVICE, DeviceItemType.AUDIO_SHARING_MEDIA_BLUETOOTH_DEVICE, DeviceItemType.AVAILABLE_AUDIO_SHARING_MEDIA_BLUETOOTH_DEVICE, DeviceItemType.AVAILABLE_MEDIA_BLUETOOTH_DEVICE, DeviceItemType.CONNECTED_BLUETOOTH_DEVICE, DeviceItemType.SAVED_BLUETOOTH_DEVICE);

    public DeviceItemInteractor(BluetoothTileDialogRepository bluetoothTileDialogRepository, AudioManager audioManager, BluetoothAdapter bluetoothAdapter, LocalBluetoothManager localBluetoothManager, SystemClock systemClock, BluetoothTileDialogLogger bluetoothTileDialogLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.bluetoothTileDialogRepository = bluetoothTileDialogRepository;
        this.audioManager = audioManager;
        this.bluetoothAdapter = bluetoothAdapter;
        this.localBluetoothManager = localBluetoothManager;
        this.systemClock = systemClock;
        this.logger = bluetoothTileDialogLogger;
        this.backgroundDispatcher = coroutineDispatcher;
        this.deviceItemUpdateRequest = FlowKt.shareIn(FlowConflatedKt.conflatedCallbackFlow(new DeviceItemInteractor$deviceItemUpdateRequest$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(1), 0);
    }

    public final Object updateDeviceItems$frameworks__base__packages__SystemUI__android_common__SystemUI_core(Context context, DeviceFetchTrigger deviceFetchTrigger, SuspendLambda suspendLambda) {
        Object withContext = BuildersKt.withContext(this.backgroundDispatcher, new DeviceItemInteractor$updateDeviceItems$2(this, deviceFetchTrigger, context, null), suspendLambda);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
