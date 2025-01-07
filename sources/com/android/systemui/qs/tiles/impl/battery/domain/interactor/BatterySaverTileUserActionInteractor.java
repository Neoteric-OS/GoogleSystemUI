package com.android.systemui.qs.tiles.impl.battery.domain.interactor;

import android.content.Intent;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.battery.domain.model.BatterySaverTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatterySaverTileUserActionInteractor implements QSTileUserActionInteractor {
    public final BatteryController batteryController;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public BatterySaverTileUserActionInteractor(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, BatteryController batteryController) {
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.batteryController = batteryController;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            BatterySaverTileModel batterySaverTileModel = (BatterySaverTileModel) qSTileInput.data;
            if (!batterySaverTileModel.isPluggedIn()) {
                boolean isPowerSaving = batterySaverTileModel.isPowerSaving();
                boolean z = !isPowerSaving;
                Expandable expandable = qSTileUserAction.getExpandable();
                BatteryControllerImpl batteryControllerImpl = (BatteryControllerImpl) this.batteryController;
                if (!isPowerSaving) {
                    batteryControllerImpl.mPowerSaverStartExpandable.set(new WeakReference(expandable));
                }
                BatterySaverUtils.setPowerSaveMode(batteryControllerImpl.mContext, z, true, 4);
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.BATTERY_SAVER_SETTINGS"));
        } else {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
