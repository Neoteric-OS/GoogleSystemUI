package com.android.systemui.qs.tiles.impl.work.domain.interactor;

import android.content.Intent;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.work.domain.model.WorkModeTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.phone.ManagedProfileControllerImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkModeTileUserActionInteractor implements QSTileUserActionInteractor {
    public final ManagedProfileController profileController;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public WorkModeTileUserActionInteractor(ManagedProfileController managedProfileController, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.profileController = managedProfileController;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        boolean z = qSTileUserAction instanceof QSTileUserAction.Click;
        Object obj = qSTileInput.data;
        if (z) {
            if (obj instanceof WorkModeTileModel.HasActiveProfile) {
                ((ManagedProfileControllerImpl) this.profileController).setWorkModeEnabled(!((WorkModeTileModel.HasActiveProfile) obj).isEnabled);
            }
        } else if (!(qSTileUserAction instanceof QSTileUserAction.LongClick)) {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        } else if (obj instanceof WorkModeTileModel.HasActiveProfile) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.MANAGED_PROFILE_SETTINGS"));
        }
        return Unit.INSTANCE;
    }
}
