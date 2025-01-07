package com.android.systemui.qs.tiles.impl.qr.domain.interactor;

import android.content.Intent;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.qr.domain.model.QRCodeScannerTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QRCodeScannerTileUserActionInteractor implements QSTileUserActionInteractor {
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public QRCodeScannerTileUserActionInteractor(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl) {
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        ActivityTransitionAnimator.Controller controller;
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            Object obj = qSTileInput.data;
            QRCodeScannerTileModel qRCodeScannerTileModel = (QRCodeScannerTileModel) obj;
            if (qRCodeScannerTileModel instanceof QRCodeScannerTileModel.Available) {
                Expandable expandable = ((QSTileUserAction.Click) qSTileUserAction).expandable;
                Intent intent = ((QRCodeScannerTileModel.Available) obj).intent;
                QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl = this.qsTileIntentUserActionHandler;
                if (expandable != null) {
                    qSTileIntentUserInputHandlerImpl.getClass();
                    controller = expandable.activityTransitionController(32);
                } else {
                    controller = null;
                }
                qSTileIntentUserInputHandlerImpl.activityStarter.startActivity(intent, true, controller, true);
            } else {
                boolean z = qRCodeScannerTileModel instanceof QRCodeScannerTileModel.TemporarilyUnavailable;
            }
        } else if (!(qSTileUserAction instanceof QSTileUserAction.LongClick)) {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
