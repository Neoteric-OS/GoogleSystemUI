package com.android.systemui.qs.tiles.impl.rotation.domain.interactor;

import android.content.Intent;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.rotation.domain.model.RotationLockTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationLockTileUserActionInteractor implements QSTileUserActionInteractor {
    public final RotationLockController controller;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public RotationLockTileUserActionInteractor(RotationLockController rotationLockController, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.controller = rotationLockController;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            ((RotationLockControllerImpl) this.controller).mRotationPolicy.setRotationLock("QSTileUserActionInteractor#handleInput", !((RotationLockTileModel) qSTileInput.data).isRotationLocked);
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.AUTO_ROTATE_SETTINGS"));
        } else {
            boolean z = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
