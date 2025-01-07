package com.android.systemui.user.ui.dialog;

import android.content.DialogInterface;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.qs.user.UserSwitchDialogController;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DialogShowerImpl implements DialogInterface, UserSwitchDialogController.DialogShower {
    public final SystemUIDialog animateFrom;
    public final DialogTransitionAnimator dialogTransitionAnimator;

    public DialogShowerImpl(SystemUIDialog systemUIDialog, DialogTransitionAnimator dialogTransitionAnimator) {
        this.animateFrom = systemUIDialog;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
    }

    @Override // android.content.DialogInterface
    public final void cancel() {
        this.animateFrom.cancel();
    }

    @Override // android.content.DialogInterface
    public final void dismiss() {
        this.animateFrom.dismiss();
    }
}
