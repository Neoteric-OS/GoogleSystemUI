package com.android.systemui.qs.tiles.dialog;

import android.content.Intent;
import android.view.View;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class InternetDialogDelegate$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ InternetDialogDelegate$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                InternetDialogController internetDialogController = (InternetDialogController) obj;
                internetDialogController.getClass();
                Intent intent = new Intent("android.settings.WIFI_SCANNING_SETTINGS");
                intent.addFlags(268435456);
                internetDialogController.startActivity(intent, view);
                break;
            default:
                ((SystemUIDialog) obj).dismiss();
                break;
        }
    }
}
