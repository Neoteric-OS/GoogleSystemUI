package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.SystemClock;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceItemInteractor$updateDeviceItems$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ DeviceFetchTrigger $trigger;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceItemInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceItemInteractor$updateDeviceItems$2(DeviceItemInteractor deviceItemInteractor, DeviceFetchTrigger deviceFetchTrigger, Context context, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceItemInteractor;
        this.$trigger = deviceFetchTrigger;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceItemInteractor$updateDeviceItems$2 deviceItemInteractor$updateDeviceItems$2 = new DeviceItemInteractor$updateDeviceItems$2(this.this$0, this.$trigger, this.$context, continuation);
        deviceItemInteractor$updateDeviceItems$2.L$0 = obj;
        return deviceItemInteractor$updateDeviceItems$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DeviceItemInteractor$updateDeviceItems$2 deviceItemInteractor$updateDeviceItems$2 = (DeviceItemInteractor$updateDeviceItems$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        deviceItemInteractor$updateDeviceItems$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        BluetoothAdapter bluetoothAdapter;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        ((SystemClockImpl) this.this$0.systemClock).getClass();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        BluetoothTileDialogRepository bluetoothTileDialogRepository = this.this$0.bluetoothTileDialogRepository;
        LocalBluetoothManager localBluetoothManager = bluetoothTileDialogRepository.localBluetoothManager;
        Collection cachedDevicesCopy = (localBluetoothManager == null || (bluetoothAdapter = bluetoothTileDialogRepository.bluetoothAdapter) == null || !bluetoothAdapter.isEnabled()) ? EmptyList.INSTANCE : localBluetoothManager.mCachedDeviceManager.getCachedDevicesCopy();
        DeviceItemInteractor deviceItemInteractor = this.this$0;
        Context context = this.$context;
        ArrayList arrayList = new ArrayList();
        Iterator it = cachedDevicesCopy.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) it.next();
            Iterator it2 = deviceItemInteractor.deviceItemFactoryList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it2.next();
                if (((DeviceItemFactory) obj2).isFilterMatched(context, cachedBluetoothDevice, deviceItemInteractor.audioManager)) {
                    break;
                }
            }
            DeviceItemFactory deviceItemFactory = (DeviceItemFactory) obj2;
            DeviceItem create = deviceItemFactory != null ? deviceItemFactory.create(context, cachedBluetoothDevice) : null;
            if (create != null) {
                arrayList.add(create);
            }
        }
        DeviceItemInteractor deviceItemInteractor2 = this.this$0;
        List list = deviceItemInteractor2.displayPriority;
        BluetoothAdapter bluetoothAdapter2 = deviceItemInteractor2.bluetoothAdapter;
        final List mostRecentlyConnectedDevices = bluetoothAdapter2 != null ? bluetoothAdapter2.getMostRecentlyConnectedDevices() : null;
        final DeviceItemInteractor$sort$$inlined$compareBy$1 deviceItemInteractor$sort$$inlined$compareBy$1 = new DeviceItemInteractor$sort$$inlined$compareBy$1(list);
        List sortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.android.systemui.bluetooth.qsdialog.DeviceItemInteractor$sort$$inlined$thenBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj3, Object obj4) {
                int compare = DeviceItemInteractor$sort$$inlined$compareBy$1.this.compare(obj3, obj4);
                if (compare != 0) {
                    return compare;
                }
                DeviceItem deviceItem = (DeviceItem) obj3;
                List list2 = mostRecentlyConnectedDevices;
                int valueOf = list2 != null ? Integer.valueOf(list2.indexOf(deviceItem.cachedBluetoothDevice.mDevice)) : 0;
                DeviceItem deviceItem2 = (DeviceItem) obj4;
                List list3 = mostRecentlyConnectedDevices;
                return ComparisonsKt___ComparisonsJvmKt.compareValues(valueOf, list3 != null ? Integer.valueOf(list3.indexOf(deviceItem2.cachedBluetoothDevice.mDevice)) : 0);
            }
        });
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            this.this$0.mutableDeviceItemUpdate.tryEmit(CollectionsKt.take(sortedWith, 3));
            AuthContainerView$$ExternalSyntheticOutline0.m(sortedWith.size() > 3, this.this$0.mutableShowSeeAllUpdate, null);
            DeviceItemInteractor deviceItemInteractor3 = this.this$0;
            BluetoothTileDialogLogger bluetoothTileDialogLogger = deviceItemInteractor3.logger;
            JobStatus jobStatus = JobStatus.FINISHED;
            DeviceFetchTrigger deviceFetchTrigger = this.$trigger;
            ((SystemClockImpl) deviceItemInteractor3.systemClock).getClass();
            bluetoothTileDialogLogger.logDeviceFetch(jobStatus, deviceFetchTrigger, SystemClock.elapsedRealtime() - elapsedRealtime);
        } else {
            DeviceItemInteractor deviceItemInteractor4 = this.this$0;
            BluetoothTileDialogLogger bluetoothTileDialogLogger2 = deviceItemInteractor4.logger;
            JobStatus jobStatus2 = JobStatus.CANCELLED;
            DeviceFetchTrigger deviceFetchTrigger2 = this.$trigger;
            ((SystemClockImpl) deviceItemInteractor4.systemClock).getClass();
            bluetoothTileDialogLogger2.logDeviceFetch(jobStatus2, deviceFetchTrigger2, SystemClock.elapsedRealtime() - elapsedRealtime);
        }
        return Unit.INSTANCE;
    }
}
