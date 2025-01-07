package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.satellite.SatelliteManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class DeviceBasedSatelliteRepositoryImpl$signalStrength$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Flow invoke(SatelliteManager satelliteManager) {
        DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = (DeviceBasedSatelliteRepositoryImpl) this.receiver;
        deviceBasedSatelliteRepositoryImpl.getClass();
        return FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1(satelliteManager, deviceBasedSatelliteRepositoryImpl, null)), deviceBasedSatelliteRepositoryImpl.bgDispatcher);
    }
}
