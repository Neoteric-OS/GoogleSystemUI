package com.android.systemui.qs.tiles.impl.saver.domain.interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.saver.domain.model.DataSaverTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataSaverTileUserActionInteractor implements QSTileUserActionInteractor {
    public final CoroutineContext backgroundContext;
    public final Context context;
    public final CoroutineContext coroutineContext;
    public final DataSaverControllerImpl dataSaverController;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;
    public final SharedPreferences sharedPreferences;
    public final SystemUIDialog.Factory systemUIDialogFactory;

    public DataSaverTileUserActionInteractor(Context context, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, DataSaverControllerImpl dataSaverControllerImpl, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, DialogTransitionAnimator dialogTransitionAnimator, SystemUIDialog.Factory factory, UserFileManager userFileManager) {
        this.context = context;
        this.coroutineContext = coroutineContext;
        this.backgroundContext = coroutineContext2;
        this.dataSaverController = dataSaverControllerImpl;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.systemUIDialogFactory = factory;
        this.sharedPreferences = ((UserFileManagerImpl) userFileManager).getSharedPreferences$1(context.getUserId(), "data_saver");
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            boolean z = ((DataSaverTileModel) qSTileInput.data).isEnabled;
            if (z || this.sharedPreferences.getBoolean("data_saver_dialog_shown", false)) {
                Object withContext = BuildersKt.withContext(this.backgroundContext, new DataSaverTileUserActionInteractor$handleInput$2$1(this, z, null), continuation);
                if (withContext == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return withContext;
                }
            } else {
                Object withContext2 = BuildersKt.withContext(this.coroutineContext, new DataSaverTileUserActionInteractor$handleInput$2$2(this, qSTileInput, null), continuation);
                if (withContext2 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return withContext2;
                }
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.DATA_SAVER_SETTINGS"));
        } else {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
