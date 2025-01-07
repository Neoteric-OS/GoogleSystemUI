package com.android.wm.shell.compatui;

import android.view.View;
import android.widget.CheckBox;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RestartDialogLayout$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ RestartDialogLayout$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                int i2 = RestartDialogLayout.$r8$clinit;
                ((CheckBox) obj).performClick();
                break;
            default:
                int i3 = RestartDialogLayout.$r8$clinit;
                ((RestartDialogWindowManager$$ExternalSyntheticLambda0) obj).run();
                break;
        }
    }
}
