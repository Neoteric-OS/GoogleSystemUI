package com.android.systemui.statusbar.chips.casttootherdevice.ui.view;

import android.content.Context;
import com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndCastScreenToOtherDeviceDialogDelegate implements SystemUIDialog.Delegate {
    public final Context context;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final ProjectionChipModel.Projecting state;
    public final Function0 stopAction;

    public EndCastScreenToOtherDeviceDialogDelegate(EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, Context context, Function0 function0, ProjectionChipModel.Projecting projecting) {
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.context = context;
        this.stopAction = function0;
        this.state = projecting;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void beforeCreate(android.app.Dialog r7) {
        /*
            r6 = this;
            com.android.systemui.statusbar.phone.SystemUIDialog r7 = (com.android.systemui.statusbar.phone.SystemUIDialog) r7
            r0 = 2131232336(0x7f080650, float:1.8080778E38)
            r7.setIcon(r0)
            r0 = 2131952255(0x7f13027f, float:1.9540948E38)
            r7.setTitle(r0)
            com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Projecting r0 = r6.state
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting r1 = r0.projectionState
            java.lang.String r1 = r1.getHostDeviceName()
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting r0 = r0.projectionState
            boolean r2 = r0 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask
            r3 = 0
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper r4 = r6.endMediaProjectionDialogHelper
            if (r2 == 0) goto L8d
            r4.getClass()
            boolean r2 = r0 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask
            if (r2 == 0) goto L2b
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$SingleTask r0 = (com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask) r0
            android.app.ActivityManager$RunningTaskInfo r0 = r0.task
            goto L2c
        L2b:
            r0 = r3
        L2c:
            if (r0 == 0) goto L4d
            android.content.Intent r0 = r0.baseIntent
            if (r0 == 0) goto L4d
            android.content.ComponentName r0 = r0.getComponent()
            if (r0 == 0) goto L4d
            java.lang.String r0 = r0.getPackageName()
            if (r0 != 0) goto L3f
            goto L4d
        L3f:
            android.content.pm.PackageManager r2 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4d
            r5 = 0
            android.content.pm.ApplicationInfo r0 = r2.getApplicationInfo(r0, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4d
            android.content.pm.PackageManager r2 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4d
            java.lang.CharSequence r0 = r0.loadLabel(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4d
            goto L4e
        L4d:
            r0 = r3
        L4e:
            if (r0 == 0) goto L60
            if (r1 == 0) goto L60
            android.content.Context r2 = r6.context
            r5 = 2131952254(0x7f13027e, float:1.9540946E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r0 = r2.getString(r5, r0)
            goto L89
        L60:
            if (r0 == 0) goto L70
            android.content.Context r1 = r6.context
            r2 = 2131952253(0x7f13027d, float:1.9540944E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r0 = r1.getString(r2, r0)
            goto L89
        L70:
            if (r1 == 0) goto L80
            android.content.Context r0 = r6.context
            r2 = 2131952252(0x7f13027c, float:1.9540941E38)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r0 = r0.getString(r2, r1)
            goto L89
        L80:
            android.content.Context r0 = r6.context
            r1 = 2131952251(0x7f13027b, float:1.954094E38)
            java.lang.String r0 = r0.getString(r1)
        L89:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            goto La9
        L8d:
            if (r1 == 0) goto L9d
            android.content.Context r0 = r6.context
            r2 = 2131952250(0x7f13027a, float:1.9540937E38)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r0 = r0.getString(r2, r1)
            goto La6
        L9d:
            android.content.Context r0 = r6.context
            r1 = 2131952249(0x7f130279, float:1.9540935E38)
            java.lang.String r0 = r0.getString(r1)
        La6:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
        La9:
            r7.setMessage(r0)
            r0 = 2131952310(0x7f1302b6, float:1.954106E38)
            r7.setNegativeButton(r0, r3)
            r4.getClass()
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1 r0 = new com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1
            kotlin.jvm.functions.Function0 r6 = r6.stopAction
            r0.<init>(r4, r6)
            r6 = 2131952248(0x7f130278, float:1.9540933E38)
            r7.setPositiveButton(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.casttootherdevice.ui.view.EndCastScreenToOtherDeviceDialogDelegate.beforeCreate(android.app.Dialog):void");
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.endMediaProjectionDialogHelper.dialogFactory;
        return factory.create(this, factory.mContext);
    }
}
