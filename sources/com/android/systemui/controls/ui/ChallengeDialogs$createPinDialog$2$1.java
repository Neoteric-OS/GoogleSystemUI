package com.android.systemui.controls.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.service.controls.actions.ControlAction;
import android.widget.EditText;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChallengeDialogs$createPinDialog$2$1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ControlViewHolder $cvh;
    public final /* synthetic */ ControlAction $lastAction;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ChallengeDialogs$createPinDialog$2$1(ControlViewHolder controlViewHolder, ControlAction controlAction, int i) {
        this.$r8$classId = i;
        this.$cvh = controlViewHolder;
        this.$lastAction = controlAction;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.$r8$classId) {
            case 0:
                if (dialogInterface instanceof Dialog) {
                    Dialog dialog = (Dialog) dialogInterface;
                    dialog.requireViewById(R.id.controls_pin_input);
                    this.$cvh.action(ChallengeDialogs.access$addChallengeValue(this.$lastAction, ((EditText) dialog.requireViewById(R.id.controls_pin_input)).getText().toString()));
                    dialogInterface.dismiss();
                    break;
                }
                break;
            default:
                this.$cvh.action(ChallengeDialogs.access$addChallengeValue(this.$lastAction, "true"));
                dialogInterface.dismiss();
                break;
        }
    }
}
