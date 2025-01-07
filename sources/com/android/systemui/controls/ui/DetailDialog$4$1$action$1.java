package com.android.systemui.controls.ui;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.wm.shell.R;
import com.android.wm.shell.taskview.TaskView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DetailDialog$4$1$action$1 implements ActivityStarter.OnDismissAction, TaskView.Listener {
    public final /* synthetic */ DetailDialog this$0;

    @Override // com.android.wm.shell.taskview.TaskView.Listener
    public void onBackPressedOnTaskRoot(int i) {
        this.this$0.dismiss();
    }

    @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
    public boolean onDismiss() {
        DetailDialog detailDialog = this.this$0;
        detailDialog.broadcastSender.closeSystemDialogs();
        detailDialog.pendingIntent.send(ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle());
        return false;
    }

    @Override // com.android.wm.shell.taskview.TaskView.Listener
    public void onInitialized() {
        DetailDialog detailDialog = this.this$0;
        View view = detailDialog.taskViewContainer;
        if (view == null) {
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (view.getWidth() * detailDialog.taskWidthPercentWidth);
        view.setLayoutParams(layoutParams);
        ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(detailDialog.activityContext, 0, 0);
        makeCustomAnimation.setPendingIntentBackgroundActivityStartMode(3);
        makeCustomAnimation.setTaskAlwaysOnTop(true);
        TaskView taskView = detailDialog.taskView;
        taskView.startActivity(detailDialog.pendingIntent, detailDialog.fillInIntent, makeCustomAnimation, ConvenienceExtensionsKt.getBoundsOnScreen(taskView));
    }

    @Override // com.android.wm.shell.taskview.TaskView.Listener
    public void onTaskCreated(int i, ComponentName componentName) {
        ((ViewGroup) this.this$0.requireViewById(R.id.controls_activity_view)).setAlpha(1.0f);
    }

    @Override // com.android.wm.shell.taskview.TaskView.Listener
    public void onTaskRemovalStarted(int i) {
        TaskView taskView = this.this$0.taskView;
        taskView.getHolder().removeCallback(taskView);
        taskView.mTaskViewTaskController.performRelease();
    }
}
