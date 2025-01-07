package com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor;

import android.content.Intent;
import com.android.systemui.accessibility.data.repository.ColorCorrectionRepositoryImpl;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.model.ColorCorrectionTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColorCorrectionUserActionInteractor implements QSTileUserActionInteractor {
    public final ColorCorrectionRepositoryImpl colorCorrectionRepository;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public ColorCorrectionUserActionInteractor(ColorCorrectionRepositoryImpl colorCorrectionRepositoryImpl, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.colorCorrectionRepository = colorCorrectionRepositoryImpl;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            Object isEnabled = this.colorCorrectionRepository.setIsEnabled(!((ColorCorrectionTileModel) qSTileInput.data).isEnabled, qSTileInput.user, continuation);
            if (isEnabled == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return isEnabled;
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("com.android.settings.ACCESSIBILITY_COLOR_SPACE_SETTINGS"));
        } else {
            boolean z = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
