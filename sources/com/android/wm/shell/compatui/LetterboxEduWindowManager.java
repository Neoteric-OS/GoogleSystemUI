package com.android.wm.shell.compatui;

import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
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
import com.android.wm.shell.common.DockStateReader;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.transition.Transitions;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LetterboxEduWindowManager extends CompatUIWindowManagerAbstract {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final DialogAnimationController mAnimationController;
    public final CompatUIConfiguration mCompatUIConfiguration;
    public final CompatUIStatusManager mCompatUIStatusManager;
    public final int mDialogVerticalMargin;
    public final DockStateReader mDockStateReader;
    public boolean mEligibleForLetterboxEducation;
    LetterboxEduDialogLayout mLayout;
    public final Consumer mOnDismissCallback;
    public final Transitions mTransitions;
    public final int mUserId;

    public LetterboxEduWindowManager(Context context, TaskInfo taskInfo, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer.TaskListener taskListener, DisplayLayout displayLayout, Transitions transitions, Consumer consumer, DialogAnimationController dialogAnimationController, DockStateReader dockStateReader, CompatUIConfiguration compatUIConfiguration, CompatUIStatusManager compatUIStatusManager) {
        super(context, taskInfo, syncTransactionQueue, taskListener, displayLayout);
        this.mTransitions = transitions;
        this.mOnDismissCallback = consumer;
        this.mAnimationController = dialogAnimationController;
        this.mUserId = taskInfo.userId;
        this.mDialogVerticalMargin = (int) this.mContext.getResources().getDimension(R.dimen.letterbox_education_dialog_margin);
        this.mDockStateReader = dockStateReader;
        this.mCompatUIConfiguration = compatUIConfiguration;
        this.mCompatUIStatusManager = compatUIStatusManager;
        this.mEligibleForLetterboxEducation = taskInfo.appCompatTaskInfo.eligibleForLetterboxEducation();
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View createLayout() {
        this.mLayout = (LetterboxEduDialogLayout) LayoutInflater.from(this.mContext).inflate(R.layout.letterbox_education_dialog_layout, (ViewGroup) null);
        updateDialogMargins();
        CompatUIStatusManager$$ExternalSyntheticLambda0 compatUIStatusManager$$ExternalSyntheticLambda0 = this.mCompatUIStatusManager.mWriter;
        this.mTransitions.runOnIdle(new LetterboxEduWindowManager$$ExternalSyntheticLambda0(this, 0));
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean eligibleToShowLayout() {
        if (!this.mEligibleForLetterboxEducation || isTaskbarEduShowing()) {
            return false;
        }
        if (this.mLayout == null) {
            if (this.mCompatUIConfiguration.mLetterboxEduSharedPreferences.getBoolean(String.valueOf(this.mUserId), false)) {
                return false;
            }
        }
        Intent registerReceiver = this.mDockStateReader.mContext.registerReceiver(null, DockStateReader.DOCK_INTENT_FILTER);
        return registerReceiver == null || registerReceiver.getIntExtra("android.intent.extra.DOCK_STATE", 0) == 0;
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
    public final boolean needsToBeRecreated(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener) {
        if (super.needsToBeRecreated(taskInfo, taskListener)) {
            return !this.mCompatUIConfiguration.mLetterboxEduSharedPreferences.getBoolean(String.valueOf(this.mUserId), false);
        }
        return false;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void onParentBoundsChanged() {
        if (this.mLayout == null) {
            return;
        }
        WindowManager.LayoutParams windowLayoutParams = getWindowLayoutParams();
        this.mLayout.setLayoutParams(windowLayoutParams);
        updateDialogMargins();
        SurfaceControlViewHost surfaceControlViewHost = this.mViewHost;
        if (surfaceControlViewHost == null) {
            return;
        }
        surfaceControlViewHost.relayout(windowLayoutParams);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void release() {
        this.mAnimationController.cancelAnimation();
        CompatUIStatusManager$$ExternalSyntheticLambda0 compatUIStatusManager$$ExternalSyntheticLambda0 = this.mCompatUIStatusManager.mWriter;
        super.release();
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void removeLayout() {
        this.mLayout = null;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean updateCompatInfo(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener, boolean z) {
        this.mEligibleForLetterboxEducation = taskInfo.appCompatTaskInfo.eligibleForLetterboxEducation();
        return super.updateCompatInfo(taskInfo, taskListener, z);
    }

    public final void updateDialogMargins() {
        LetterboxEduDialogLayout letterboxEduDialogLayout = this.mLayout;
        if (letterboxEduDialogLayout == null) {
            return;
        }
        View view = letterboxEduDialogLayout.mDialogContainer;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        Rect taskBounds = getTaskBounds();
        Rect taskStableBounds = getTaskStableBounds();
        int i = taskStableBounds.top - taskBounds.top;
        int i2 = this.mDialogVerticalMargin;
        marginLayoutParams.topMargin = i + i2;
        marginLayoutParams.bottomMargin = (taskBounds.bottom - taskStableBounds.bottom) + i2;
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void updateSurfacePosition() {
    }
}
