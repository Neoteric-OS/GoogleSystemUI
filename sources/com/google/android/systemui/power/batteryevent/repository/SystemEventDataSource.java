package com.google.android.systemui.power.batteryevent.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryevent.common.BatteryEventSubscriber;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.FrameworkApiEventData;
import com.google.android.systemui.power.batteryevent.common.data.HalEventData;
import com.google.android.systemui.power.batteryevent.common.data.SettingsEventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemEventDataSource extends BroadcastReceiver implements EventSourceMonitor {
    public static final Function2 CALLBACK_INITIAL_STATE = new Function2() { // from class: com.google.android.systemui.power.batteryevent.repository.SystemEventDataSource$Companion$CALLBACK_INITIAL_STATE$1
        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return Unit.INSTANCE;
        }
    };
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public final FrameworkDataSource frameworkDataSource;
    public final HalDataSource halDataSource;
    public SystemEventData lastSystemEventData;
    public final SettingsDataSource settingsDataSource;
    public Lambda onEventSourceUpdate = (Lambda) CALLBACK_INITIAL_STATE;
    public List subscribers = EmptyList.INSTANCE;
    public final Map actionToEventDataTypeCache = new LinkedHashMap();

    public SystemEventDataSource(HalDataSource halDataSource, SettingsDataSource settingsDataSource, FrameworkDataSource frameworkDataSource, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.halDataSource = halDataSource;
        this.settingsDataSource = settingsDataSource;
        this.frameworkDataSource = frameworkDataSource;
        this.broadcastDispatcher = broadcastDispatcher;
        this.backgroundDispatcher = coroutineDispatcher;
        this.applicationScope = coroutineScope;
        EventData eventData = new EventData(0);
        Boolean bool = Boolean.FALSE;
        this.lastSystemEventData = new SystemEventData("", new EventData(0), new EventData(0), new EventData(0), new EventData(0), new EventData(0), new EventData(0), new EventData(0), new HalEventData(eventData, new EventData(bool), new EventData(bool)), new SettingsEventData(new EventData(0), new EventData(0), new EventData(bool), new EventData(bool)), new FrameworkApiEventData(new EventData(bool), new EventData(bool)));
    }

    public static final List access$getAllEventDataType(SystemEventDataSource systemEventDataSource, String str) {
        if (!systemEventDataSource.actionToEventDataTypeCache.isEmpty() && systemEventDataSource.actionToEventDataTypeCache.keySet().contains(str)) {
            Object obj = systemEventDataSource.actionToEventDataTypeCache.get(str);
            Intrinsics.checkNotNull(obj);
            return (List) obj;
        }
        ArrayList arrayList = new ArrayList();
        for (BatteryEventSubscriber batteryEventSubscriber : systemEventDataSource.subscribers) {
            if (batteryEventSubscriber.actions.contains(str)) {
                arrayList.addAll(batteryEventSubscriber.eventDataType);
            }
        }
        List distinct = CollectionsKt.distinct(arrayList);
        systemEventDataSource.actionToEventDataTypeCache.put(str, distinct);
        return distinct;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || intent.getAction() == null || ((action = intent.getAction()) != null && action.length() == 0)) {
            Log.w("SystemEventDataSource", "onReceive, unexpected intent " + intent);
        } else {
            String action2 = intent.getAction();
            Intrinsics.checkNotNull(action2);
            Log.d("SystemEventDataSource", "onReceive: intentAction");
            BuildersKt.launch$default(this.applicationScope, null, null, new SystemEventDataSource$onReceive$1(context, intent, this, action2, null), 3);
        }
    }
}
