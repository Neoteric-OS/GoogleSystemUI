package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__DistinctKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothDeviceMetadataInteractor {
    public static final Companion Companion = null;
    public final BluetoothAdapter bluetoothAdapter;
    public final Executor executor;
    public final BluetoothTileDialogLogger logger;
    public final Flow metadataUpdate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final Set access$getBluetoothDevices(List list) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                DeviceItem deviceItem = (DeviceItem) it.next();
                List singletonList = Collections.singletonList(deviceItem.cachedBluetoothDevice.mDevice);
                Set set = deviceItem.cachedBluetoothDevice.mMemberDevices;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
                Iterator it2 = ((HashSet) set).iterator();
                while (it2.hasNext()) {
                    arrayList.add(((CachedBluetoothDevice) it2.next()).mDevice);
                }
                CollectionsKt__MutableCollectionsKt.addAll(CollectionsKt.plus((Iterable) arrayList, (Collection) singletonList), linkedHashSet);
            }
            return linkedHashSet;
        }
    }

    public BluetoothDeviceMetadataInteractor(DeviceItemInteractor deviceItemInteractor, BluetoothAdapter bluetoothAdapter, BluetoothTileDialogLogger bluetoothTileDialogLogger, Executor executor, CoroutineDispatcher coroutineDispatcher) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.logger = bluetoothTileDialogLogger;
        this.executor = executor;
        this.metadataUpdate = FlowKt.flowOn(FlowKt.transformLatest(FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(new ReadonlySharedFlow(deviceItemInteractor.mutableDeviceItemUpdate), new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor$metadataUpdate$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BluetoothDeviceMetadataInteractor.Companion.access$getBluetoothDevices((List) obj);
            }
        }, FlowKt__DistinctKt.defaultAreEquivalent), new BluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1(null, this)), coroutineDispatcher);
    }
}
