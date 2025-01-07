package com.google.android.systemui.columbus.legacy;

import android.content.DialogInterface;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ColumbusTargetRequestDialog$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ColumbusTargetRequestDialog f$0;
    public final /* synthetic */ DialogInterface.OnClickListener f$1;

    public /* synthetic */ ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(ColumbusTargetRequestDialog columbusTargetRequestDialog, DialogInterface.OnClickListener onClickListener, int i) {
        this.$r8$classId = i;
        this.f$0 = columbusTargetRequestDialog;
        this.f$1 = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                ColumbusTargetRequestDialog columbusTargetRequestDialog = this.f$0;
                DialogInterface.OnClickListener onClickListener = this.f$1;
                columbusTargetRequestDialog.getClass();
                onClickListener.onClick(columbusTargetRequestDialog, -2);
                columbusTargetRequestDialog.dismiss();
                break;
            default:
                ColumbusTargetRequestDialog columbusTargetRequestDialog2 = this.f$0;
                DialogInterface.OnClickListener onClickListener2 = this.f$1;
                columbusTargetRequestDialog2.getClass();
                onClickListener2.onClick(columbusTargetRequestDialog2, -1);
                columbusTargetRequestDialog2.dismiss();
                break;
        }
    }
}
