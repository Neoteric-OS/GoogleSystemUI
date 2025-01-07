package com.android.systemui.qs.tiles.impl.flashlight.domain.interactor;

import android.app.ActivityManager;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.flashlight.domain.model.FlashlightTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlashlightTileUserActionInteractor implements QSTileUserActionInteractor {
    public final FlashlightControllerImpl flashlightController;

    public FlashlightTileUserActionInteractor(FlashlightControllerImpl flashlightControllerImpl) {
        this.flashlightController = flashlightControllerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (!(qSTileUserAction instanceof QSTileUserAction.Click)) {
            boolean z = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        } else if (!ActivityManager.isUserAMonkey()) {
            if (qSTileInput.data instanceof FlashlightTileModel.FlashlightAvailable) {
                this.flashlightController.setFlashlight(!((FlashlightTileModel.FlashlightAvailable) r2).isEnabled);
            }
        }
        return Unit.INSTANCE;
    }
}
