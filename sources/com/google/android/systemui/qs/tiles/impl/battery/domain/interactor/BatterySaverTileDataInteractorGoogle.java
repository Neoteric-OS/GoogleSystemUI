package com.google.android.systemui.qs.tiles.impl.battery.domain.interactor;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.impl.battery.domain.interactor.BatterySaverTileDataInteractor;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.kotlin.BatteryControllerExtKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatterySaverTileDataInteractorGoogle extends BatterySaverTileDataInteractor {
    public final BatteryController batteryController;
    public final CoroutineContext bgCoroutineContext;

    public BatterySaverTileDataInteractorGoogle(CoroutineContext coroutineContext, BatteryController batteryController) {
        super(coroutineContext, batteryController);
        this.bgCoroutineContext = coroutineContext;
        this.batteryController = batteryController;
    }

    @Override // com.android.systemui.qs.tiles.impl.battery.domain.interactor.BatterySaverTileDataInteractor, com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(super.tileData(userHandle, readonlyStateFlow), FlowKt.flowOn(FlowKt.distinctUntilChanged(BatteryControllerExtKt.isExtremePowerSaverEnabled(this.batteryController)), this.bgCoroutineContext), new BatterySaverTileDataInteractorGoogle$tileData$1(3, null));
    }
}
