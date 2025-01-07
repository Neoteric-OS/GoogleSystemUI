package com.android.systemui.recordissue;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Window;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenCapturePermissionDialogDelegate implements SystemUIDialog.Delegate {
    public final SystemUIDialog.Factory dialogFactory;
    public final IssueRecordingState state;

    public ScreenCapturePermissionDialogDelegate(SystemUIDialog.Factory factory, IssueRecordingState issueRecordingState) {
        this.dialogFactory = factory;
        this.state = issueRecordingState;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        final SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setIcon(R.drawable.ic_screenrecord);
        systemUIDialog.setTitle(R.string.screenrecord_permission_dialog_title);
        systemUIDialog.setMessage(R.string.screenrecord_permission_dialog_warning_entire_screen);
        systemUIDialog.setNegativeButton(R.string.slice_permission_deny, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.ScreenCapturePermissionDialogDelegate$beforeCreate$1$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SystemUIDialog.this.cancel();
            }
        });
        systemUIDialog.setPositiveButton(R.string.slice_permission_allow, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.ScreenCapturePermissionDialogDelegate$beforeCreate$1$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ScreenCapturePermissionDialogDelegate.this.state.prefs.edit().putBoolean("HasApprovedScreenRecord", true).apply();
                systemUIDialog.dismiss();
            }
        });
        Window window = systemUIDialog.getWindow();
        if (window != null) {
            window.addPrivateFlags(16);
        }
        Window window2 = systemUIDialog.getWindow();
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.dialogFactory;
        return factory.create(this, factory.mContext);
    }
}
