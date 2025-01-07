package com.android.systemui.user.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExitGuestDialog extends SystemUIDialog {
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final FalsingManager falsingManager;
    public final int guestUserId;
    public final boolean isGuestEphemeral;
    public final UserSwitcherDialogCoordinator$sam$com_android_systemui_user_ui_dialog_ExitGuestDialog_OnExitGuestUserListener$0 onExitGuestUserListener;
    public final int targetUserId;

    public ExitGuestDialog(Context context, int i, boolean z, int i2, boolean z2, FalsingManager falsingManager, DialogTransitionAnimator dialogTransitionAnimator, UserSwitcherDialogCoordinator$sam$com_android_systemui_user_ui_dialog_ExitGuestDialog_OnExitGuestUserListener$0 userSwitcherDialogCoordinator$sam$com_android_systemui_user_ui_dialog_ExitGuestDialog_OnExitGuestUserListener$0) {
        super(context);
        this.guestUserId = i;
        this.isGuestEphemeral = z;
        this.targetUserId = i2;
        this.falsingManager = falsingManager;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.onExitGuestUserListener = userSwitcherDialogCoordinator$sam$com_android_systemui_user_ui_dialog_ExitGuestDialog_OnExitGuestUserListener$0;
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.user.ui.dialog.ExitGuestDialog$onClickListener$1
            /* JADX WARN: Type inference failed for: r4v10, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.FunctionReferenceImpl] */
            /* JADX WARN: Type inference failed for: r4v4, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.FunctionReferenceImpl] */
            /* JADX WARN: Type inference failed for: r4v7, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.FunctionReferenceImpl] */
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                if (ExitGuestDialog.this.falsingManager.isFalseTap(i3 == -2 ? 0 : 2)) {
                    return;
                }
                ExitGuestDialog exitGuestDialog = ExitGuestDialog.this;
                if (exitGuestDialog.isGuestEphemeral) {
                    if (i3 == -2) {
                        exitGuestDialog.cancel();
                        return;
                    }
                    if (i3 != -1) {
                        return;
                    }
                    exitGuestDialog.dialogTransitionAnimator.dismissStack(exitGuestDialog);
                    ExitGuestDialog exitGuestDialog2 = ExitGuestDialog.this;
                    exitGuestDialog2.onExitGuestUserListener.function.invoke(Integer.valueOf(exitGuestDialog2.guestUserId), Integer.valueOf(exitGuestDialog2.targetUserId), Boolean.FALSE);
                    return;
                }
                if (i3 == -3) {
                    exitGuestDialog.cancel();
                    return;
                }
                if (i3 == -2) {
                    exitGuestDialog.dialogTransitionAnimator.dismissStack(exitGuestDialog);
                    ExitGuestDialog exitGuestDialog3 = ExitGuestDialog.this;
                    exitGuestDialog3.onExitGuestUserListener.function.invoke(Integer.valueOf(exitGuestDialog3.guestUserId), Integer.valueOf(exitGuestDialog3.targetUserId), Boolean.TRUE);
                    return;
                }
                if (i3 != -1) {
                    return;
                }
                exitGuestDialog.dialogTransitionAnimator.dismissStack(exitGuestDialog);
                ExitGuestDialog exitGuestDialog4 = ExitGuestDialog.this;
                exitGuestDialog4.onExitGuestUserListener.function.invoke(Integer.valueOf(exitGuestDialog4.guestUserId), Integer.valueOf(exitGuestDialog4.targetUserId), Boolean.FALSE);
            }
        };
        if (z) {
            setTitle(context.getString(R.string.guest_exit_dialog_title));
            setMessage(context.getString(R.string.guest_exit_dialog_message));
            setButton(-3, context.getString(android.R.string.cancel), onClickListener);
            setButton(-1, context.getString(R.string.guest_exit_dialog_button), onClickListener);
        } else {
            setTitle(context.getString(R.string.guest_exit_dialog_title_non_ephemeral));
            setMessage(context.getString(R.string.guest_exit_dialog_message_non_ephemeral));
            setButton(-3, context.getString(android.R.string.cancel), onClickListener);
            setButton(-2, context.getString(R.string.guest_exit_clear_data_button), onClickListener);
            setButton(-1, context.getString(R.string.guest_exit_save_data_button), onClickListener);
        }
        SystemUIDialog.setWindowOnTop(this, z2);
        setCanceledOnTouchOutside(false);
    }
}
