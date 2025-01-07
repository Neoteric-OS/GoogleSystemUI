package com.android.systemui.qs.tiles.impl.sensorprivacy;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorPrivacyToggleTileDataInteractor implements QSTileDataInteractor {
    public final CoroutineContext bgCoroutineContext;
    public final IndividualSensorPrivacyController privacyController;
    public final int sensorId;

    public SensorPrivacyToggleTileDataInteractor(CoroutineContext coroutineContext, IndividualSensorPrivacyController individualSensorPrivacyController, int i) {
        this.bgCoroutineContext = coroutineContext;
        this.privacyController = individualSensorPrivacyController;
        this.sensorId = i;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return FlowKt.flowOn(new SafeFlow(new SensorPrivacyToggleTileDataInteractor$availability$1(this, null)), this.bgCoroutineContext);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.flowOn(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SensorPrivacyToggleTileDataInteractor$tileData$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new SensorPrivacyToggleTileDataInteractor$tileData$1(this, null)))), this.bgCoroutineContext);
    }
}
