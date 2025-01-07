package com.android.systemui.qs.tiles.impl.modes.domain.interactor;

import android.content.Intent;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.ui.dialog.ModesDialogDelegate;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesTileUserActionInteractor implements QSTileUserActionInteractor {
    public final ModesDialogDelegate dialogDelegate;
    public final Intent longClickIntent = new Intent("android.settings.ZEN_MODE_SETTINGS");
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserInputHandler;

    public ModesTileUserActionInteractor(QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, ModesDialogDelegate modesDialogDelegate) {
        this.qsTileIntentUserInputHandler = qSTileIntentUserInputHandlerImpl;
        this.dialogDelegate = modesDialogDelegate;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        boolean z = qSTileUserAction instanceof QSTileUserAction.Click ? true : qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        Unit unit = Unit.INSTANCE;
        if (z) {
            Object showDialog = this.dialogDelegate.showDialog(qSTileUserAction.getExpandable(), (SuspendLambda) continuation);
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (showDialog != coroutineSingletons) {
                showDialog = unit;
            }
            if (showDialog == coroutineSingletons) {
                return showDialog;
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserInputHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, this.longClickIntent);
        }
        return unit;
    }
}
