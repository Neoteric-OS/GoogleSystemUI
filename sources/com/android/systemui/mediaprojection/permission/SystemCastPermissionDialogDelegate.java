package com.android.systemui.mediaprojection.permission;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemCastPermissionDialogDelegate extends BaseMediaProjectionPermissionDialogDelegate {
    public final MediaProjectionPermissionActivity$$ExternalSyntheticLambda1 onCancelClicked;
    public final MediaProjectionPermissionActivity$$ExternalSyntheticLambda0 onStartRecordingClicked;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SystemCastPermissionDialogDelegate(android.content.Context r19, android.media.projection.MediaProjectionConfig r20, com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda0 r21, com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda1 r22, java.lang.String r23, boolean r24, int r25, com.android.systemui.mediaprojection.MediaProjectionMetricsLogger r26) {
        /*
            r18 = this;
            r6 = r18
            if (r24 != 0) goto L1b
            if (r20 == 0) goto L1b
            int r0 = r20.getRegionToCapture()
            r1 = 1
            if (r0 != r1) goto L1b
            r0 = 2131953353(0x7f1306c9, float:1.9543175E38)
            java.lang.Object[] r1 = new java.lang.Object[]{r23}
            r2 = r19
            java.lang.String r0 = r2.getString(r0, r1)
            goto L1c
        L1b:
            r0 = 0
        L1c:
            com.android.systemui.mediaprojection.permission.ScreenShareOption r1 = new com.android.systemui.mediaprojection.permission.ScreenShareOption
            r8 = 0
            r9 = 2131953360(0x7f1306d0, float:1.9543189E38)
            r10 = 2131953363(0x7f1306d3, float:1.9543195E38)
            r11 = 2131953365(0x7f1306d5, float:1.9543199E38)
            r7 = r1
            r12 = r0
            r7.<init>(r8, r9, r10, r11, r12)
            com.android.systemui.mediaprojection.permission.ScreenShareOption r2 = new com.android.systemui.mediaprojection.permission.ScreenShareOption
            r17 = 0
            r13 = 1
            r14 = 2131953359(0x7f1306cf, float:1.9543187E38)
            r15 = 2131953362(0x7f1306d2, float:1.9543193E38)
            r16 = 2131953358(0x7f1306ce, float:1.9543185E38)
            r12 = r2
            r12.<init>(r13, r14, r15, r16, r17)
            com.android.systemui.mediaprojection.permission.ScreenShareOption[] r1 = new com.android.systemui.mediaprojection.permission.ScreenShareOption[]{r1, r2}
            java.util.List r1 = kotlin.collections.CollectionsKt__CollectionsKt.listOf(r1)
            if (r0 == 0) goto L4e
            java.util.List r0 = kotlin.collections.CollectionsKt.reversed(r1)
            r1 = r0
        L4e:
            r0 = 2131232336(0x7f080650, float:1.8080778E38)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r0 = r18
            r2 = r23
            r3 = r25
            r4 = r26
            r0.<init>(r1, r2, r3, r4, r5)
            r0 = r21
            r6.onStartRecordingClicked = r0
            r0 = r22
            r6.onCancelClicked = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.mediaprojection.permission.SystemCastPermissionDialogDelegate.<init>(android.content.Context, android.media.projection.MediaProjectionConfig, com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda0, com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda1, java.lang.String, boolean, int, com.android.systemui.mediaprojection.MediaProjectionMetricsLogger):void");
    }

    @Override // com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate
    public final void onCreate(AlertDialog alertDialog) {
        throw null;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        final AlertDialog alertDialog = (AlertDialog) dialog;
        super.onCreate(alertDialog);
        setDialogTitle(R.string.media_projection_entry_cast_permission_dialog_title);
        final int i = 0;
        View.OnClickListener onClickListener = new View.OnClickListener(this) { // from class: com.android.systemui.mediaprojection.permission.SystemCastPermissionDialogDelegate$onCreate$1
            public final /* synthetic */ SystemCastPermissionDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        SystemCastPermissionDialogDelegate systemCastPermissionDialogDelegate = this.this$0;
                        systemCastPermissionDialogDelegate.onStartRecordingClicked.accept(systemCastPermissionDialogDelegate);
                        alertDialog.dismiss();
                        break;
                    default:
                        this.this$0.onCancelClicked.run();
                        alertDialog.dismiss();
                        break;
                }
            }
        };
        TextView textView = this.startButton;
        if (textView == null) {
            textView = null;
        }
        textView.setOnClickListener(new BaseMediaProjectionPermissionDialogDelegate$setStartButtonOnClickListener$1(this, onClickListener));
        final int i2 = 1;
        View.OnClickListener onClickListener2 = new View.OnClickListener(this) { // from class: com.android.systemui.mediaprojection.permission.SystemCastPermissionDialogDelegate$onCreate$1
            public final /* synthetic */ SystemCastPermissionDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        SystemCastPermissionDialogDelegate systemCastPermissionDialogDelegate = this.this$0;
                        systemCastPermissionDialogDelegate.onStartRecordingClicked.accept(systemCastPermissionDialogDelegate);
                        alertDialog.dismiss();
                        break;
                    default:
                        this.this$0.onCancelClicked.run();
                        alertDialog.dismiss();
                        break;
                }
            }
        };
        TextView textView2 = this.cancelButton;
        (textView2 != null ? textView2 : null).setOnClickListener(onClickListener2);
    }
}
