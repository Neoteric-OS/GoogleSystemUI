package com.android.systemui.qs.tiles.impl.onehanded.domain;

import android.content.Intent;
import com.android.systemui.accessibility.data.repository.OneHandedModeRepositoryImpl;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.onehanded.domain.model.OneHandedModeTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedModeTileUserActionInteractor implements QSTileUserActionInteractor {
    public final OneHandedModeRepositoryImpl oneHandedModeRepository;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public OneHandedModeTileUserActionInteractor(OneHandedModeRepositoryImpl oneHandedModeRepositoryImpl, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.oneHandedModeRepository = oneHandedModeRepositoryImpl;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            Object isEnabled = this.oneHandedModeRepository.setIsEnabled(!((OneHandedModeTileModel) qSTileInput.data).isEnabled, qSTileInput.user, continuation);
            if (isEnabled == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return isEnabled;
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.action.ONE_HANDED_SETTINGS"));
        } else {
            boolean z = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
