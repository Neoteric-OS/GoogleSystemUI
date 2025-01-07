package com.android.systemui.qs.tiles;

import android.app.Dialog;
import android.view.View;
import com.android.internal.app.MediaRouteDialogPresenter;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.qs.tiles.CastTile;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.DialogKt;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CastTile$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CastTile f$0;
    public final /* synthetic */ Expandable f$1;

    public /* synthetic */ CastTile$$ExternalSyntheticLambda2(CastTile castTile, Expandable expandable) {
        this.f$0 = castTile;
        this.f$1 = expandable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final CastTile castTile = this.f$0;
        final Expandable expandable = this.f$1;
        final CastTile.DialogHolder dialogHolder = new CastTile.DialogHolder();
        final Dialog createDialog = MediaRouteDialogPresenter.createDialog(castTile.mContext, 4, new View.OnClickListener() { // from class: com.android.systemui.qs.tiles.CastTile$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CastTile castTile2 = CastTile.this;
                CastTile.DialogHolder dialogHolder2 = dialogHolder;
                DialogTransitionAnimator dialogTransitionAnimator = castTile2.mDialogTransitionAnimator;
                dialogTransitionAnimator.getClass();
                DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view);
                if (createActivityTransitionController$default == null) {
                    dialogHolder2.mDialog.dismiss();
                }
                castTile2.mActivityStarter.postStartActivityDismissingKeyguard(castTile2.getLongClickIntent(), 0, createActivityTransitionController$default);
            }
        }, R.style.Theme_SystemUI_Dialog_Cast, false);
        dialogHolder.mDialog = createDialog;
        SystemUIDialog.setShowForAllUsers(createDialog);
        SystemUIDialog.registerDismissListener(createDialog, null);
        SystemUIDialog.setWindowOnTop(createDialog, ((KeyguardStateControllerImpl) castTile.mKeyguard).mShowing);
        SystemUIDialog.setDialogSize(createDialog);
        castTile.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.CastTile$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DialogTransitionAnimator.Controller m;
                CastTile castTile2 = CastTile.this;
                Expandable expandable2 = expandable;
                Dialog dialog = createDialog;
                castTile2.getClass();
                if (expandable2 != null && (m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "cast", expandable2)) != null) {
                    castTile2.mDialogTransitionAnimator.show(dialog, m, false);
                    return;
                }
                if (dialog.getWindow() != null) {
                    DialogKt.registerAnimationOnBackInvoked$default(dialog, dialog.getWindow().getDecorView());
                }
                dialog.show();
            }
        });
    }
}
