package com.android.systemui.globalactions;

import com.android.systemui.globalactions.GlobalActionsDialogLite;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GlobalActionsDialogLite.ActionsDialogLite f$0;

    public /* synthetic */ GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3(GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite, int i) {
        this.$r8$classId = i;
        this.f$0 = actionsDialogLite;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite = this.f$0;
        switch (i) {
            case 0:
                int i2 = GlobalActionsDialogLite.ActionsDialogLite.$r8$clinit;
                actionsDialogLite.startAnimation(false, new GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda3(actionsDialogLite, 1));
                break;
            default:
                int i3 = GlobalActionsDialogLite.ActionsDialogLite.$r8$clinit;
                actionsDialogLite.setDismissOverride(null);
                actionsDialogLite.hide();
                actionsDialogLite.dismiss();
                break;
        }
    }
}
