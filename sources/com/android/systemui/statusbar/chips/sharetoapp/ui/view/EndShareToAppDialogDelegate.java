package com.android.systemui.statusbar.chips.sharetoapp.ui.view;

import android.content.Context;
import com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel;
import com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndShareToAppDialogDelegate implements SystemUIDialog.Delegate {
    public final Context context;
    public final EndMediaProjectionDialogHelper endMediaProjectionDialogHelper;
    public final ProjectionChipModel.Projecting state;
    public final Function0 stopAction;

    public EndShareToAppDialogDelegate(EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, Context context, Function0 function0, ProjectionChipModel.Projecting projecting) {
        this.endMediaProjectionDialogHelper = endMediaProjectionDialogHelper;
        this.context = context;
        this.stopAction = function0;
        this.state = projecting;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0058  */
    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void beforeCreate(android.app.Dialog r6) {
        /*
            r5 = this;
            com.android.systemui.statusbar.phone.SystemUIDialog r6 = (com.android.systemui.statusbar.phone.SystemUIDialog) r6
            r0 = 2131232845(0x7f08084d, float:1.808181E38)
            r6.setIcon(r0)
            r0 = 2131954173(0x7f1309fd, float:1.9544838E38)
            r6.setTitle(r0)
            com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Projecting r0 = r5.state
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting r0 = r0.projectionState
            boolean r1 = r0 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask
            r2 = 0
            r3 = 0
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper r4 = r5.endMediaProjectionDialogHelper
            if (r1 == 0) goto L65
            r4.getClass()
            boolean r1 = r0 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask
            if (r1 == 0) goto L26
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$SingleTask r0 = (com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting.SingleTask) r0
            android.app.ActivityManager$RunningTaskInfo r0 = r0.task
            goto L27
        L26:
            r0 = r3
        L27:
            if (r0 == 0) goto L47
            android.content.Intent r0 = r0.baseIntent
            if (r0 == 0) goto L47
            android.content.ComponentName r0 = r0.getComponent()
            if (r0 == 0) goto L47
            java.lang.String r0 = r0.getPackageName()
            if (r0 != 0) goto L3a
            goto L47
        L3a:
            android.content.pm.PackageManager r1 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L47
            android.content.pm.ApplicationInfo r0 = r1.getApplicationInfo(r0, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L47
            android.content.pm.PackageManager r1 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L47
            java.lang.CharSequence r0 = r0.loadLabel(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L47
            goto L48
        L47:
            r0 = r3
        L48:
            if (r0 == 0) goto L58
            android.content.Context r1 = r5.context
            r2 = 2131954172(0x7f1309fc, float:1.9544836E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r0 = r1.getString(r2, r0)
            goto L61
        L58:
            android.content.Context r0 = r5.context
            r1 = 2131954171(0x7f1309fb, float:1.9544834E38)
            java.lang.String r0 = r0.getString(r1)
        L61:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            goto L96
        L65:
            java.lang.String r0 = r0.getHostPackage()
            r4.getClass()
            android.content.pm.PackageManager r1 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L79
            android.content.pm.ApplicationInfo r0 = r1.getApplicationInfo(r0, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L79
            android.content.pm.PackageManager r1 = r4.packageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L79
            java.lang.CharSequence r0 = r0.loadLabel(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L79
            goto L7a
        L79:
            r0 = r3
        L7a:
            if (r0 == 0) goto L8a
            android.content.Context r1 = r5.context
            r2 = 2131954170(0x7f1309fa, float:1.9544832E38)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r0 = r1.getString(r2, r0)
            goto L93
        L8a:
            android.content.Context r0 = r5.context
            r1 = 2131954169(0x7f1309f9, float:1.954483E38)
            java.lang.String r0 = r0.getString(r1)
        L93:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
        L96:
            r6.setMessage(r0)
            r0 = 2131952310(0x7f1302b6, float:1.954106E38)
            r6.setNegativeButton(r0, r3)
            r4.getClass()
            com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1 r0 = new com.android.systemui.statusbar.chips.mediaprojection.ui.view.EndMediaProjectionDialogHelper$wrapStopAction$1
            kotlin.jvm.functions.Function0 r5 = r5.stopAction
            r0.<init>(r4, r5)
            r5 = 2131954168(0x7f1309f8, float:1.9544828E38)
            r6.setPositiveButton(r5, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.sharetoapp.ui.view.EndShareToAppDialogDelegate.beforeCreate(android.app.Dialog):void");
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.endMediaProjectionDialogHelper.dialogFactory;
        return factory.create(this, factory.mContext);
    }
}
