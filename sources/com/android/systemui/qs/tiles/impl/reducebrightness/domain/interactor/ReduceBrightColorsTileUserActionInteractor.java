package com.android.systemui.qs.tiles.impl.reducebrightness.domain.interactor;

import android.content.Intent;
import android.content.res.Resources;
import com.android.systemui.accessibility.extradim.ExtraDimDialogManager;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.qs.ReduceBrightColorsControllerImpl;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.reducebrightness.domain.model.ReduceBrightColorsTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReduceBrightColorsTileUserActionInteractor implements QSTileUserActionInteractor {
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;
    public final ReduceBrightColorsController reduceBrightColorsController;

    public ReduceBrightColorsTileUserActionInteractor(Resources resources, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, ReduceBrightColorsController reduceBrightColorsController, ExtraDimDialogManager extraDimDialogManager) {
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.reduceBrightColorsController = reduceBrightColorsController;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            ((ReduceBrightColorsControllerImpl) this.reduceBrightColorsController).mManager.setReduceBrightColorsActivated(!((ReduceBrightColorsTileModel) qSTileInput.data).isEnabled);
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.REDUCE_BRIGHT_COLORS_SETTINGS"));
        } else {
            boolean z = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
