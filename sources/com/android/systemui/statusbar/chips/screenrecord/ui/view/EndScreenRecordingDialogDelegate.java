package com.android.systemui.statusbar.chips.screenrecord.ui.view;

import android.app.ActivityManager;
import android.content.Context;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndScreenRecordingDialogDelegate implements SystemUIDialog.Delegate {
    public final Context context;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final ActivityManager.RunningTaskInfo recordedTask;
    public final Function0 stopAction;

    public EndScreenRecordingDialogDelegate(EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, Context context, Function0 function0, ActivityManager.RunningTaskInfo runningTaskInfo) {
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.context = context;
        this.stopAction = function0;
        this.recordedTask = runningTaskInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void beforeCreate(android.app.Dialog r6) {
        /*
            r5 = this;
            com.android.systemui.statusbar.phone.SystemUIDialog r6 = (com.android.systemui.statusbar.phone.SystemUIDialog) r6
            android.app.ActivityManager$RunningTaskInfo r0 = r5.recordedTask
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper r1 = r5.endMediaProjectionDialogHelper
            r1.getClass()
            r2 = 0
            if (r0 == 0) goto L2b
            android.content.Intent r0 = r0.baseIntent
            if (r0 == 0) goto L2b
            android.content.ComponentName r0 = r0.getComponent()
            if (r0 == 0) goto L2b
            java.lang.String r0 = r0.getPackageName()
            if (r0 != 0) goto L1d
            goto L2b
        L1d:
            android.content.pm.PackageManager r3 = r1.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            r4 = 0
            android.content.pm.ApplicationInfo r0 = r3.getApplicationInfo(r0, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            android.content.pm.PackageManager r3 = r1.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            java.lang.CharSequence r0 = r0.loadLabel(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            goto L2c
        L2b:
            r0 = r2
        L2c:
            if (r0 == 0) goto L3c
            android.content.Context r3 = r5.context
            r4 = 2131954059(0x7f13098b, float:1.9544607E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r0 = r3.getString(r4, r0)
            goto L45
        L3c:
            android.content.Context r0 = r5.context
            r3 = 2131954058(0x7f13098a, float:1.9544605E38)
            java.lang.String r0 = r0.getString(r3)
        L45:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r3 = 2131232889(0x7f080879, float:1.80819E38)
            r6.setIcon(r3)
            r3 = 2131954060(0x7f13098c, float:1.9544609E38)
            r6.setTitle(r3)
            r6.setMessage(r0)
            r0 = 2131952310(0x7f1302b6, float:1.954106E38)
            r6.setNegativeButton(r0, r2)
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1 r0 = new com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1
            kotlin.jvm.functions.Function0 r5 = r5.stopAction
            r0.<init>(r1, r5)
            r5 = 2131954057(0x7f130989, float:1.9544602E38)
            r6.setPositiveButton(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.screenrecord.ui.view.EndScreenRecordingDialogDelegate.beforeCreate(android.app.Dialog):void");
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.endMediaProjectionDialogHelper.dialogFactory;
        return factory.create(this, factory.mContext);
    }
}
