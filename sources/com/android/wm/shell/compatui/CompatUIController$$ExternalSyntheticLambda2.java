package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.SharedPreferences;
import android.util.Pair;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.compatui.api.CompatUIInfo;
import com.android.wm.shell.compatui.impl.CompatUIEvents;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CompatUIController$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CompatUIController f$0;

    public /* synthetic */ CompatUIController$$ExternalSyntheticLambda2(CompatUIController compatUIController, int i) {
        this.$r8$classId = i;
        this.f$0 = compatUIController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        CompatUIController compatUIController = this.f$0;
        switch (i) {
            case 0:
                compatUIController.mHasShownUserAspectRatioSettingsButton = ((Boolean) obj).booleanValue();
                break;
            case 1:
                Pair pair = (Pair) obj;
                CompatUIConfiguration compatUIConfiguration = compatUIController.mCompatUIConfiguration;
                if (compatUIConfiguration.mIsRestartDialogOverrideEnabled || (compatUIConfiguration.mIsRestartDialogEnabled && compatUIConfiguration.mIsLetterboxRestartDialogAllowed)) {
                    TaskInfo taskInfo = (TaskInfo) pair.first;
                    SharedPreferences sharedPreferences = compatUIConfiguration.mCompatUISharedPreferences;
                    int i2 = taskInfo.userId;
                    if (!sharedPreferences.getBoolean(taskInfo.topActivity.getPackageName() + "@" + i2, false)) {
                        compatUIController.mSetOfTaskIdsShowingRestartDialog.add(Integer.valueOf(((TaskInfo) pair.first).taskId));
                        compatUIController.onCompatInfoChanged(new CompatUIInfo((TaskInfo) pair.first, (ShellTaskOrganizer.TaskListener) pair.second));
                        break;
                    }
                }
                compatUIController.mCallback.accept(new CompatUIEvents.SizeCompatRestartButtonClicked(((TaskInfo) pair.first).taskId));
                break;
            case 2:
                Pair pair2 = (Pair) obj;
                compatUIController.mTaskIdToRestartDialogWindowManagerMap.remove(((TaskInfo) pair2.first).taskId);
                compatUIController.mCallback.accept(new CompatUIEvents.SizeCompatRestartButtonClicked(((TaskInfo) pair2.first).taskId));
                break;
            case 3:
                Pair pair3 = (Pair) obj;
                compatUIController.mSetOfTaskIdsShowingRestartDialog.remove(Integer.valueOf(((TaskInfo) pair3.first).taskId));
                compatUIController.onCompatInfoChanged(new CompatUIInfo((TaskInfo) pair3.first, (ShellTaskOrganizer.TaskListener) pair3.second));
                break;
            case 4:
                Pair pair4 = (Pair) obj;
                compatUIController.getClass();
                compatUIController.createOrUpdateReachabilityEduLayout((TaskInfo) pair4.first, (ShellTaskOrganizer.TaskListener) pair4.second);
                break;
            default:
                CompatUIWindowManagerAbstract compatUIWindowManagerAbstract = (CompatUIWindowManagerAbstract) obj;
                compatUIController.getClass();
                compatUIWindowManagerAbstract.updateVisibility(compatUIController.showOnDisplay(compatUIWindowManagerAbstract.mDisplayId));
                break;
        }
    }
}
