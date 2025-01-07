package com.android.systemui.statusbar.chips.casttootherdevice.ui.view;

import android.app.Dialog;
import android.content.Context;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndGenericCastToOtherDeviceDialogDelegate implements SystemUIDialog.Delegate {
    public final Context context;
    public final String deviceName;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final Function0 stopAction;

    public EndGenericCastToOtherDeviceDialogDelegate(EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, Context context, String str, Function0 function0) {
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.context = context;
        this.deviceName = str;
        this.stopAction = function0;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        String str = this.deviceName;
        String string = str != null ? this.context.getString(R.string.cast_to_other_device_stop_dialog_message_generic_with_device, str) : this.context.getString(R.string.cast_to_other_device_stop_dialog_message_generic);
        Intrinsics.checkNotNull(string);
        systemUIDialog.setIcon(R.drawable.ic_cast_connected);
        systemUIDialog.setTitle(R.string.cast_to_other_device_stop_dialog_title);
        systemUIDialog.setMessage(string);
        systemUIDialog.setNegativeButton(R.string.close_dialog_button, null);
        EndMediaProjectionDialogHelper endMediaProjectionDialogHelper = this.endMediaProjectionDialogHelper;
        endMediaProjectionDialogHelper.getClass();
        systemUIDialog.setPositiveButton(R.string.cast_to_other_device_stop_dialog_button, new EndMediaProjectionDialogHelper$wrapStopAction$1(endMediaProjectionDialogHelper, this.stopAction));
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.endMediaProjectionDialogHelper.dialogFactory;
        return factory.create(this, factory.mContext);
    }
}
