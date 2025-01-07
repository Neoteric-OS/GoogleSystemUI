package com.android.systemui.display.data.repository;

import android.R;
import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceStateRepositoryImpl {
    public final List deviceStateMap;
    public final ReadonlyStateFlow state;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IdsPerDeviceState {
        public final DeviceStateRepository$DeviceState deviceState;
        public final Set ids;

        public IdsPerDeviceState(Set set, DeviceStateRepository$DeviceState deviceStateRepository$DeviceState) {
            this.ids = set;
            this.deviceState = deviceStateRepository$DeviceState;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdsPerDeviceState)) {
                return false;
            }
            IdsPerDeviceState idsPerDeviceState = (IdsPerDeviceState) obj;
            return Intrinsics.areEqual(this.ids, idsPerDeviceState.ids) && this.deviceState == idsPerDeviceState.deviceState;
        }

        public final int hashCode() {
            return this.deviceState.hashCode() + (this.ids.hashCode() * 31);
        }

        public final String toString() {
            return "IdsPerDeviceState(ids=" + this.ids + ", deviceState=" + this.deviceState + ")";
        }
    }

    public DeviceStateRepositoryImpl(Context context, DeviceStateManager deviceStateManager, CoroutineScope coroutineScope, Executor executor) {
        this.state = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new DeviceStateRepositoryImpl$state$1(deviceStateManager, executor, this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), DeviceStateRepository$DeviceState.UNKNOWN);
        List<Pair> listOf = CollectionsKt__CollectionsKt.listOf(new Pair(Integer.valueOf(R.array.config_fontManagerServiceCerts), DeviceStateRepository$DeviceState.FOLDED), new Pair(Integer.valueOf(R.array.config_healthConnectMigrationKnownSigners), DeviceStateRepository$DeviceState.HALF_FOLDED), new Pair(Integer.valueOf(R.array.config_packagesExemptFromSuspension), DeviceStateRepository$DeviceState.UNFOLDED), new Pair(Integer.valueOf(R.array.config_requestVibrationParamsForUsages), DeviceStateRepository$DeviceState.REAR_DISPLAY), new Pair(Integer.valueOf(R.array.config_concurrentDisplayDeviceStates), DeviceStateRepository$DeviceState.CONCURRENT_DISPLAY));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        for (Pair pair : listOf) {
            arrayList.add(new IdsPerDeviceState(ArraysKt.toSet(context.getResources().getIntArray(((Number) pair.getFirst()).intValue())), (DeviceStateRepository$DeviceState) pair.getSecond()));
        }
        this.deviceStateMap = arrayList;
    }
}
