package com.android.systemui.communal.data.repository;

import android.app.smartspace.SmartspaceTarget;
import android.widget.RemoteViews;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.communal.data.model.CommunalSmartspaceTimer;
import com.android.systemui.communal.smartspace.CommunalSmartspaceController;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSmartspaceRepositoryImpl implements BcSmartspaceDataPlugin.SmartspaceTargetListener {
    public static final Companion Companion = new Companion();
    public final StateFlowImpl _timers;
    public final CommunalSmartspaceController communalSmartspaceController;
    public final Logger logger;
    public final SystemClock systemClock;
    public Map targetCreationTimes;
    public final StateFlowImpl timers;
    public final Executor uiExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public final String stableId(String str) {
            return CollectionsKt.joinToString$default(CollectionsKt.take(StringsKt.split$default(str, new String[]{"-"}, 0, 6), 2), "-", null, null, null, 62);
        }
    }

    public CommunalSmartspaceRepositoryImpl(CommunalSmartspaceController communalSmartspaceController, Executor executor, SystemClock systemClock, LogBuffer logBuffer) {
        this.communalSmartspaceController = communalSmartspaceController;
        this.uiExecutor = executor;
        this.systemClock = systemClock;
        this.logger = new Logger(logBuffer, "CommunalSmartspaceRepository");
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(EmptyList.INSTANCE);
        this._timers = MutableStateFlow;
        this.timers = MutableStateFlow;
        this.targetCreationTimes = MapsKt.emptyMap();
    }

    @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceTargetListener
    public final void onSmartspaceTargetsUpdated(List list) {
        List list2;
        long currentTimeMillis;
        if (list != null) {
            list2 = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof SmartspaceTarget) {
                    list2.add(obj);
                }
            }
        } else {
            list2 = EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list2) {
            SmartspaceTarget smartspaceTarget = (SmartspaceTarget) obj2;
            if (smartspaceTarget.getFeatureType() == 21 && smartspaceTarget.getRemoteViews() != null) {
                arrayList.add(obj2);
            }
        }
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Object obj3 : arrayList) {
            linkedHashMap.put(Companion.stableId(((SmartspaceTarget) obj3).getSmartspaceTargetId()), obj3);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            Long l = (Long) this.targetCreationTimes.get((String) entry.getKey());
            if (l != null) {
                currentTimeMillis = l.longValue();
            } else {
                ((SystemClockImpl) this.systemClock).getClass();
                currentTimeMillis = System.currentTimeMillis();
            }
            linkedHashMap2.put(key, Long.valueOf(currentTimeMillis));
        }
        this.targetCreationTimes = linkedHashMap2;
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            String str = (String) entry2.getKey();
            SmartspaceTarget smartspaceTarget2 = (SmartspaceTarget) entry2.getValue();
            Object obj4 = this.targetCreationTimes.get(str);
            Intrinsics.checkNotNull(obj4);
            long longValue = ((Number) obj4).longValue();
            RemoteViews remoteViews = smartspaceTarget2.getRemoteViews();
            Intrinsics.checkNotNull(remoteViews);
            arrayList2.add(new CommunalSmartspaceTimer(longValue, remoteViews, str));
        }
        StateFlowImpl stateFlowImpl = this._timers;
        if (!arrayList2.equals(stateFlowImpl.getValue())) {
            Logger logger = this.logger;
            LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl$onSmartspaceTargetsUpdated$3$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj5) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Smartspace timers updated: ", ((LogMessage) obj5).getStr1());
                }
            }, null);
            obtain.setStr1(arrayList2.toString());
            logger.getBuffer().commit(obtain);
        }
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, arrayList2);
    }
}
