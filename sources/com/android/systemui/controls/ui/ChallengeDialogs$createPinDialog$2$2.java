package com.android.systemui.controls.ui;

import android.content.DialogInterface;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChallengeDialogs$createPinDialog$2$2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Function0 $onCancel;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ChallengeDialogs$createPinDialog$2$2(int i, Function0 function0) {
        this.$r8$classId = i;
        this.$onCancel = function0;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.$r8$classId) {
            case 0:
                this.$onCancel.invoke();
                dialogInterface.cancel();
                break;
            default:
                this.$onCancel.invoke();
                dialogInterface.cancel();
                break;
        }
    }
}
