package com.google.android.systemui.power.batteryevent.domain;

import android.content.IntentFilter;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule;
import com.google.android.systemui.power.batteryevent.common.module.BatteryEventModuleProvider;
import com.google.android.systemui.power.batteryevent.repository.EventSourceMonitor;
import com.google.android.systemui.power.batteryevent.repository.SystemEventDataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventStateController {
    public final BatteryEventModuleProvider batteryEventModuleProvider;
    public final StateFlowImpl mutableBatteryEventsFlow = StateFlowKt.MutableStateFlow(null);
    public final EventSourceMonitor systemEventDataSource;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.systemui.power.batteryevent.domain.BatteryEventStateController$1, kotlin.jvm.internal.Lambda] */
    public BatteryEventStateController(BatteryEventModuleProvider batteryEventModuleProvider, EventSourceMonitor eventSourceMonitor) {
        this.batteryEventModuleProvider = batteryEventModuleProvider;
        this.systemEventDataSource = eventSourceMonitor;
        List<BaseBatteryEventModule> list = batteryEventModuleProvider.eventModuleList;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (BaseBatteryEventModule baseBatteryEventModule : list) {
            arrayList.add(new BatteryEventSubscriber(baseBatteryEventModule.getModuleType(), baseBatteryEventModule.getIntentActions(), baseBatteryEventModule.getEventDataTypes()));
        }
        EventSourceMonitor eventSourceMonitor2 = this.systemEventDataSource;
        ?? r0 = new Function2() { // from class: com.google.android.systemui.power.batteryevent.domain.BatteryEventStateController.1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SystemEventData systemEventData = (SystemEventData) obj;
                List list2 = (List) obj2;
                int batteryLevel = BatteryStatus.getBatteryLevel(((Number) systemEventData.batteryLevel.value).intValue(), ((Number) systemEventData.batteryScale.value).intValue());
                List list3 = BatteryEventStateController.this.batteryEventModuleProvider.eventModuleList;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : list3) {
                    BaseBatteryEventModule baseBatteryEventModule2 = (BaseBatteryEventModule) obj3;
                    if (list2.contains(baseBatteryEventModule2.getModuleType()) && baseBatteryEventModule2.validate(systemEventData)) {
                        arrayList2.add(obj3);
                    }
                }
                StateFlowImpl stateFlowImpl = BatteryEventStateController.this.mutableBatteryEventsFlow;
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(((BaseBatteryEventModule) it.next()).getModuleType());
                }
                BatteryEvents batteryEvents = new BatteryEvents(CollectionsKt.toSet(arrayList3), batteryLevel, ((Number) systemEventData.plugged.value).intValue());
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, batteryEvents);
                return Unit.INSTANCE;
            }
        };
        SystemEventDataSource systemEventDataSource = (SystemEventDataSource) eventSourceMonitor2;
        systemEventDataSource.subscribers = arrayList;
        systemEventDataSource.onEventSourceUpdate = r0;
        IntentFilter intentFilter = new IntentFilter();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((BatteryEventSubscriber) it.next()).actions.iterator();
            while (it2.hasNext()) {
                intentFilter.addAction((String) it2.next());
            }
        }
        BroadcastDispatcher.registerReceiver$default(systemEventDataSource.broadcastDispatcher, systemEventDataSource, intentFilter, null, null, 0, 60);
    }
}
