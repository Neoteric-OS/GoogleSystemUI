package com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor;

import android.app.UiModeManager;
import android.content.Context;
import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.model.UiModeNightTileModel;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiModeNightTileDataInteractor implements QSTileDataInteractor {
    public final BatteryController batteryController;
    public final ConfigurationController configurationController;
    public final Context context;
    public final DateFormatUtil dateFormatUtil;
    public final LocationController locationController;
    public final UiModeManager uiModeManager;

    public UiModeNightTileDataInteractor(Context context, ConfigurationController configurationController, UiModeManager uiModeManager, BatteryController batteryController, LocationController locationController, DateFormatUtil dateFormatUtil) {
        this.context = context;
        this.configurationController = configurationController;
        this.uiModeManager = uiModeManager;
        this.batteryController = batteryController;
        this.locationController = locationController;
        this.dateFormatUtil = dateFormatUtil;
    }

    public static final UiModeNightTileModel access$createModel(UiModeNightTileDataInteractor uiModeNightTileDataInteractor) {
        return new UiModeNightTileModel(uiModeNightTileDataInteractor.uiModeManager.getNightMode(), (uiModeNightTileDataInteractor.context.getResources().getConfiguration().uiMode & 48) == 32, ((BatteryControllerImpl) uiModeNightTileDataInteractor.batteryController).mPowerSave, ((LocationControllerImpl) uiModeNightTileDataInteractor.locationController).isLocationEnabled$1(), uiModeNightTileDataInteractor.uiModeManager.getNightModeCustomType(), uiModeNightTileDataInteractor.dateFormatUtil.is24HourFormat(), uiModeNightTileDataInteractor.uiModeManager.getCustomNightModeEnd(), uiModeNightTileDataInteractor.uiModeManager.getCustomNightModeStart());
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowConflatedKt.conflatedCallbackFlow(new UiModeNightTileDataInteractor$tileData$1(this, null));
    }
}
