package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.transition.Transitions;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class RestartDialogWindowManager extends CompatUIWindowManagerAbstract {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final DialogAnimationController mAnimationController;
    public final CompatUIConfiguration mCompatUIConfiguration;
    public final int mDialogVerticalMargin;
    RestartDialogLayout mLayout;
    public final Consumer mOnDismissCallback;
    public final Consumer mOnRestartCallback;
    public boolean mRequestRestartDialog;
    public final Transitions mTransitions;

    public RestartDialogWindowManager(Context context, TaskInfo taskInfo, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer.TaskListener taskListener, DisplayLayout displayLayout, Transitions transitions, Consumer consumer, Consumer consumer2, DialogAnimationController dialogAnimationController, CompatUIConfiguration compatUIConfiguration) {
        super(context, taskInfo, syncTransactionQueue, taskListener, displayLayout);
        this.mTransitions = transitions;
        this.mOnDismissCallback = consumer2;
        this.mOnRestartCallback = consumer;
        this.mAnimationController = dialogAnimationController;
        this.mDialogVerticalMargin = (int) this.mContext.getResources().getDimension(R.dimen.letterbox_restart_dialog_margin);
        this.mCompatUIConfiguration = compatUIConfiguration;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View createLayout() {
        this.mLayout = (RestartDialogLayout) LayoutInflater.from(this.mContext).inflate(R.layout.letterbox_restart_dialog_layout, (ViewGroup) null);
        updateDialogMargins$1();
        this.mTransitions.runOnIdle(new RestartDialogWindowManager$$ExternalSyntheticLambda0(this, 0));
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean eligibleToShowLayout() {
        if (!this.mRequestRestartDialog || isTaskbarEduShowing()) {
            return false;
        }
        if (this.mLayout == null) {
            CompatUIConfiguration compatUIConfiguration = this.mCompatUIConfiguration;
            TaskInfo taskInfo = this.mTaskInfo;
            SharedPreferences sharedPreferences = compatUIConfiguration.mCompatUISharedPreferences;
            int i = taskInfo.userId;
            if (sharedPreferences.getBoolean(taskInfo.topActivity.getPackageName() + "@" + i, false)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View getLayout() {
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final WindowManager.LayoutParams getWindowLayoutParams() {
        Rect taskBounds = getTaskBounds();
        return getWindowLayoutParams(taskBounds.width(), taskBounds.height());
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final int getZOrder() {
        return 10002;
    }

    public boolean isTaskbarEduShowing() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "launcher_taskbar_education_showing", 0) == 1;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void onParentBoundsChanged() {
        if (this.mLayout == null) {
            return;
        }
        WindowManager.LayoutParams windowLayoutParams = getWindowLayoutParams();
        this.mLayout.setLayoutParams(windowLayoutParams);
        updateDialogMargins$1();
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost == null) {
            return;
        }
        surfaceControlViewHost.relayout(windowLayoutParams);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void release() {
        this.mAnimationController.cancelAnimation();
        super.release();
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void removeLayout() {
        this.mLayout = null;
    }

    public final void updateDialogMargins$1() {
        RestartDialogLayout restartDialogLayout = this.mLayout;
        if (restartDialogLayout == null) {
            return;
        }
        View view = restartDialogLayout.mDialogContainer;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        Rect taskBounds = getTaskBounds();
        Rect taskStableBounds = getTaskStableBounds();
        int i = this.mDialogVerticalMargin;
        marginLayoutParams.topMargin = i;
        marginLayoutParams.bottomMargin = (taskBounds.bottom - taskStableBounds.bottom) + i;
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void updateSurfacePosition() {
    }
}
