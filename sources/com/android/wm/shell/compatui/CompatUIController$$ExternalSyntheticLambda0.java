package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.UserHandle;
import com.android.wm.shell.ShellTaskOrganizer;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CompatUIController$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CompatUIController f$0;

    public /* synthetic */ CompatUIController$$ExternalSyntheticLambda0(CompatUIController compatUIController, int i) {
        this.$r8$classId = i;
        this.f$0 = compatUIController;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        int i = this.$r8$classId;
        CompatUIController compatUIController = this.f$0;
        TaskInfo taskInfo = (TaskInfo) obj;
        ShellTaskOrganizer.TaskListener taskListener = (ShellTaskOrganizer.TaskListener) obj2;
        switch (i) {
            case 0:
                compatUIController.getClass();
                Intent intent = new Intent("android.settings.MANAGE_USER_ASPECT_RATIO_SETTINGS");
                intent.addFlags(268435456);
                intent.addFlags(32768);
                ComponentName componentName = taskInfo.topActivity;
                if (componentName != null) {
                    intent.setData(Uri.parse("package:" + componentName.getPackageName()));
                }
                compatUIController.mContext.startActivityAsUser(intent, UserHandle.of(taskInfo.userId));
                break;
            default:
                compatUIController.mIsFirstReachabilityEducationRunning = false;
                compatUIController.createOrUpdateUserAspectRatioSettingsLayout(taskInfo, taskListener);
                break;
        }
    }
}
