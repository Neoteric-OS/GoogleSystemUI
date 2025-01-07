package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.SharedPreferences;
import android.util.Pair;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RestartDialogWindowManager$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ RestartDialogWindowManager f$0;
    public final /* synthetic */ TaskInfo f$1;

    public /* synthetic */ RestartDialogWindowManager$$ExternalSyntheticLambda3(RestartDialogWindowManager restartDialogWindowManager, TaskInfo taskInfo) {
        this.f$0 = restartDialogWindowManager;
        this.f$1 = taskInfo;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        RestartDialogWindowManager restartDialogWindowManager = this.f$0;
        TaskInfo taskInfo = this.f$1;
        Boolean bool = (Boolean) obj;
        RestartDialogLayout restartDialogLayout = restartDialogWindowManager.mLayout;
        if (restartDialogLayout != null) {
            restartDialogLayout.setDismissOnClickListener(null);
            restartDialogWindowManager.mAnimationController.startExitAnimation(restartDialogWindowManager.mLayout, new RestartDialogWindowManager$$ExternalSyntheticLambda0(restartDialogWindowManager, 4));
        }
        if (bool.booleanValue()) {
            SharedPreferences.Editor edit = restartDialogWindowManager.mCompatUIConfiguration.mCompatUISharedPreferences.edit();
            int i = taskInfo.userId;
            edit.putBoolean(taskInfo.topActivity.getPackageName() + "@" + i, true).apply();
        }
        restartDialogWindowManager.mOnRestartCallback.accept(Pair.create(taskInfo, restartDialogWindowManager.mTaskListener));
    }
}
