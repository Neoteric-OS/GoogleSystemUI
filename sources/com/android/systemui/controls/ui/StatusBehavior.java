package com.android.systemui.controls.ui;

import android.R;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.view.Window;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatusBehavior implements Behavior {
    public ControlViewHolder cvh;

    public static final void access$showNotFoundDialog(StatusBehavior statusBehavior, final ControlViewHolder controlViewHolder, final ControlWithState controlWithState) {
        statusBehavior.getClass();
        PackageManager packageManager = controlViewHolder.context.getPackageManager();
        CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(controlWithState.componentName.getPackageName(), 128));
        final AlertDialog.Builder builder = new AlertDialog.Builder(controlViewHolder.context, R.style.Theme.DeviceDefault.Dialog.Alert);
        Resources resources = controlViewHolder.context.getResources();
        builder.setTitle(resources.getString(com.android.wm.shell.R.string.controls_error_removed_title));
        builder.setMessage(resources.getString(com.android.wm.shell.R.string.controls_error_removed_message, controlViewHolder.title.getText(), applicationLabel));
        builder.setPositiveButton(com.android.wm.shell.R.string.controls_open_app, new DialogInterface.OnClickListener() { // from class: com.android.systemui.controls.ui.StatusBehavior$showNotFoundDialog$builder$1$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PendingIntent appIntent;
                try {
                    Bundle bundle = ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle();
                    Control control = ControlWithState.this.control;
                    if (control != null && (appIntent = control.getAppIntent()) != null) {
                        appIntent.send(bundle);
                    }
                    builder.getContext().sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                } catch (PendingIntent.CanceledException unused) {
                    controlViewHolder.setErrorStatus();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, StatusBehavior$showNotFoundDialog$builder$1$2.INSTANCE);
        AlertDialog create = builder.create();
        Window window = create.getWindow();
        if (window != null) {
            window.setType(2020);
        }
        create.show();
        controlViewHolder.visibleDialog = create;
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void bind(final ControlWithState controlWithState, int i) {
        int i2;
        Control control = controlWithState.control;
        int status = control != null ? control.getStatus() : 0;
        if (status == 2) {
            ControlViewHolder controlViewHolder = this.cvh;
            if (controlViewHolder == null) {
                controlViewHolder = null;
            }
            controlViewHolder.layout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.StatusBehavior$bind$msg$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StatusBehavior statusBehavior = StatusBehavior.this;
                    ControlViewHolder controlViewHolder2 = statusBehavior.cvh;
                    if (controlViewHolder2 == null) {
                        controlViewHolder2 = null;
                    }
                    StatusBehavior.access$showNotFoundDialog(statusBehavior, controlViewHolder2, controlWithState);
                }
            });
            ControlViewHolder controlViewHolder2 = this.cvh;
            if (controlViewHolder2 == null) {
                controlViewHolder2 = null;
            }
            controlViewHolder2.layout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.systemui.controls.ui.StatusBehavior$bind$msg$2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    StatusBehavior statusBehavior = StatusBehavior.this;
                    ControlViewHolder controlViewHolder3 = statusBehavior.cvh;
                    if (controlViewHolder3 == null) {
                        controlViewHolder3 = null;
                    }
                    StatusBehavior.access$showNotFoundDialog(statusBehavior, controlViewHolder3, controlWithState);
                    return true;
                }
            });
            i2 = com.android.wm.shell.R.string.controls_error_removed;
        } else if (status == 3) {
            i2 = com.android.wm.shell.R.string.controls_error_generic;
        } else if (status != 4) {
            ControlViewHolder controlViewHolder3 = this.cvh;
            if (controlViewHolder3 == null) {
                controlViewHolder3 = null;
            }
            controlViewHolder3.isLoading = true;
            i2 = R.string.lockscreen_glogin_instructions;
        } else {
            i2 = com.android.wm.shell.R.string.controls_error_timeout;
        }
        ControlViewHolder controlViewHolder4 = this.cvh;
        ControlViewHolder controlViewHolder5 = controlViewHolder4 != null ? controlViewHolder4 : null;
        if (controlViewHolder4 == null) {
            controlViewHolder4 = null;
        }
        controlViewHolder5.setStatusText(controlViewHolder4.context.getString(i2), false);
        ControlViewHolder controlViewHolder6 = this.cvh;
        (controlViewHolder6 != null ? controlViewHolder6 : null).applyRenderInfo$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, false, true);
    }

    @Override // com.android.systemui.controls.ui.Behavior
    public final void initialize(ControlViewHolder controlViewHolder) {
        this.cvh = controlViewHolder;
    }
}
