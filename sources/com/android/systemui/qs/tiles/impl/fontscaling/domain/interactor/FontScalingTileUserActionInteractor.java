package com.android.systemui.qs.tiles.impl.fontscaling.domain.interactor;

import android.content.Intent;
import com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FontScalingTileUserActionInteractor implements QSTileUserActionInteractor {
    public final ActivityStarter activityStarter;
    public final CoroutineContext coroutineContext;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingDialogDelegateProvider;
    public final KeyguardStateController keyguardStateController;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public FontScalingTileUserActionInteractor(CoroutineContext coroutineContext, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, KeyguardStateController keyguardStateController, DialogTransitionAnimator dialogTransitionAnimator, ActivityStarter activityStarter) {
        this.coroutineContext = coroutineContext;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.fontScalingDialogDelegateProvider = switchingProvider;
        this.keyguardStateController = keyguardStateController;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.activityStarter = activityStarter;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(final QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            final boolean z = (((QSTileUserAction.Click) qSTileUserAction).expandable == null || ((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) ? false : true;
            Object withContext = BuildersKt.withContext(this.coroutineContext, new FontScalingTileUserActionInteractor$handleInput$2$1(this, new Runnable() { // from class: com.android.systemui.qs.tiles.impl.fontscaling.domain.interactor.FontScalingTileUserActionInteractor$handleInput$2$runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    Unit unit;
                    DialogTransitionAnimator.Controller m;
                    SystemUIDialog createDialog = ((FontScalingDialogDelegate) FontScalingTileUserActionInteractor.this.fontScalingDialogDelegateProvider.get()).createDialog();
                    if (!z) {
                        createDialog.show();
                        return;
                    }
                    Expandable expandable = qSTileInput.action.getExpandable();
                    if (expandable == null || (m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "font_scaling", expandable)) == null) {
                        unit = null;
                    } else {
                        DialogTransitionAnimator dialogTransitionAnimator = FontScalingTileUserActionInteractor.this.dialogTransitionAnimator;
                        TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                        dialogTransitionAnimator.show(createDialog, m, false);
                        unit = Unit.INSTANCE;
                    }
                    if (unit == null) {
                        createDialog.show();
                    }
                }
            }, null), continuation);
            if (withContext == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return withContext;
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.TEXT_READING_SETTINGS"));
        } else {
            boolean z2 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
