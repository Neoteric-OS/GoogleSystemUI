package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RestartDialogWindowManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ RestartDialogWindowManager f$0;

    public /* synthetic */ RestartDialogWindowManager$$ExternalSyntheticLambda0(RestartDialogWindowManager restartDialogWindowManager, int i) {
        this.$r8$classId = i;
        this.f$0 = restartDialogWindowManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        RestartDialogWindowManager restartDialogWindowManager = this.f$0;
        switch (i) {
            case 0:
                RestartDialogLayout restartDialogLayout = restartDialogWindowManager.mLayout;
                if (restartDialogLayout != null) {
                    restartDialogWindowManager.mAnimationController.startEnterAnimation(restartDialogLayout, new RestartDialogWindowManager$$ExternalSyntheticLambda0(restartDialogWindowManager, 1));
                    break;
                }
                break;
            case 1:
                RestartDialogLayout restartDialogLayout2 = restartDialogWindowManager.mLayout;
                if (restartDialogLayout2 != null) {
                    TaskInfo taskInfo = restartDialogWindowManager.mTaskInfo;
                    restartDialogLayout2.setDismissOnClickListener(new RestartDialogWindowManager$$ExternalSyntheticLambda0(restartDialogWindowManager, 2));
                    RestartDialogLayout restartDialogLayout3 = restartDialogWindowManager.mLayout;
                    final RestartDialogWindowManager$$ExternalSyntheticLambda3 restartDialogWindowManager$$ExternalSyntheticLambda3 = new RestartDialogWindowManager$$ExternalSyntheticLambda3(restartDialogWindowManager, taskInfo);
                    final CheckBox checkBox = (CheckBox) restartDialogLayout3.findViewById(R.id.letterbox_restart_dialog_checkbox);
                    restartDialogLayout3.findViewById(R.id.letterbox_restart_dialog_restart_button).setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.compatui.RestartDialogLayout$$ExternalSyntheticLambda2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            RestartDialogWindowManager$$ExternalSyntheticLambda3 restartDialogWindowManager$$ExternalSyntheticLambda32 = RestartDialogWindowManager$$ExternalSyntheticLambda3.this;
                            CheckBox checkBox2 = checkBox;
                            int i2 = RestartDialogLayout.$r8$clinit;
                            restartDialogWindowManager$$ExternalSyntheticLambda32.accept(Boolean.valueOf(checkBox2.isChecked()));
                        }
                    });
                    restartDialogWindowManager.mLayout.mDialogTitle.sendAccessibilityEvent(8);
                    break;
                }
                break;
            case 2:
                RestartDialogLayout restartDialogLayout4 = restartDialogWindowManager.mLayout;
                if (restartDialogLayout4 != null) {
                    restartDialogLayout4.setDismissOnClickListener(null);
                    restartDialogWindowManager.mAnimationController.startExitAnimation(restartDialogWindowManager.mLayout, new RestartDialogWindowManager$$ExternalSyntheticLambda0(restartDialogWindowManager, 3));
                    break;
                }
                break;
            case 3:
                int i2 = RestartDialogWindowManager.$r8$clinit;
                restartDialogWindowManager.release();
                restartDialogWindowManager.mOnDismissCallback.accept(Pair.create(restartDialogWindowManager.mTaskInfo, restartDialogWindowManager.mTaskListener));
                break;
            default:
                int i3 = RestartDialogWindowManager.$r8$clinit;
                restartDialogWindowManager.release();
                break;
        }
    }
}
