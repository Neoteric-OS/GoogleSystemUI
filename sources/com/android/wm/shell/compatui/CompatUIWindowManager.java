package com.android.wm.shell.compatui;

import android.app.AppCompatTaskInfo;
import android.app.TaskInfo;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.flags.DesktopModeFlags;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.ShellTaskOrganizer$$ExternalSyntheticLambda0;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.CompatUIController;
import com.android.wm.shell.compatui.impl.CompatUIEvents;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CompatUIWindowManager extends CompatUIWindowManagerAbstract {
    public final ShellTaskOrganizer$$ExternalSyntheticLambda0 mCallback;
    public final CompatUIConfiguration mCompatUIConfiguration;
    CompatUIController.CompatUIHintsState mCompatUIHintsState;
    boolean mHasSizeCompat;
    public final float mHideScmTolerance;
    CompatUILayout mLayout;
    public final CompatUIController$$ExternalSyntheticLambda2 mOnRestartButtonClicked;

    public CompatUIWindowManager(Context context, TaskInfo taskInfo, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer$$ExternalSyntheticLambda0 shellTaskOrganizer$$ExternalSyntheticLambda0, ShellTaskOrganizer.TaskListener taskListener, DisplayLayout displayLayout, CompatUIController.CompatUIHintsState compatUIHintsState, CompatUIConfiguration compatUIConfiguration, CompatUIController$$ExternalSyntheticLambda2 compatUIController$$ExternalSyntheticLambda2) {
        super(context, taskInfo, syncTransactionQueue, taskListener, displayLayout);
        this.mCallback = shellTaskOrganizer$$ExternalSyntheticLambda0;
        this.mHasSizeCompat = taskInfo.appCompatTaskInfo.isTopActivityInSizeCompat();
        if (DesktopModeStatus.canEnterDesktopMode(context) && DesktopModeFlags.ENABLE_WINDOWING_DYNAMIC_INITIAL_BOUNDS.isTrue()) {
            this.mHasSizeCompat &= !taskInfo.isFreeform();
        }
        this.mCompatUIHintsState = compatUIHintsState;
        this.mCompatUIConfiguration = compatUIConfiguration;
        this.mOnRestartButtonClicked = compatUIController$$ExternalSyntheticLambda2;
        this.mHideScmTolerance = compatUIConfiguration.mHideSizeCompatRestartButtonTolerance;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View createLayout() {
        CompatUILayout inflateLayout = inflateLayout();
        this.mLayout = inflateLayout;
        inflateLayout.mWindowManager = this;
        updateVisibilityOfViews$1();
        if (this.mHasSizeCompat) {
            this.mCallback.accept(new CompatUIEvents.SizeCompatRestartButtonAppeared(this.mTaskId));
        }
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean eligibleToShowLayout() {
        return this.mHasSizeCompat && shouldShowSizeCompatRestartButton(this.mTaskInfo);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View getLayout() {
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final int getZOrder() {
        return 10001;
    }

    public CompatUILayout inflateLayout() {
        return (CompatUILayout) LayoutInflater.from(this.mContext).inflate(R.layout.compat_ui_layout, (ViewGroup) null);
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void removeLayout() {
        this.mLayout = null;
    }

    public boolean shouldShowSizeCompatRestartButton(TaskInfo taskInfo) {
        if (taskInfo.configuration.smallestScreenWidthDp < 600) {
            return true;
        }
        AppCompatTaskInfo appCompatTaskInfo = taskInfo.appCompatTaskInfo;
        int i = appCompatTaskInfo.topActivityLetterboxWidth;
        int i2 = appCompatTaskInfo.topActivityLetterboxHeight;
        Rect taskStableBounds = getTaskStableBounds();
        int width = taskStableBounds.width();
        int height = taskStableBounds.height();
        if (width > i && height > i2) {
            return true;
        }
        float f = this.mHideScmTolerance;
        this.mCompatUIConfiguration.getClass();
        if (f != 100 && width == i) {
            return false;
        }
        int i3 = i * i2;
        int i4 = width * height;
        return (i3 == 0 || i4 == 0 || (((float) i3) / ((float) i4)) * 100.0f >= this.mHideScmTolerance) ? false : true;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean updateCompatInfo(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener, boolean z) {
        boolean z2 = this.mHasSizeCompat;
        this.mHasSizeCompat = taskInfo.appCompatTaskInfo.isTopActivityInSizeCompat();
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext) && DesktopModeFlags.ENABLE_WINDOWING_DYNAMIC_INITIAL_BOUNDS.isTrue()) {
            this.mHasSizeCompat &= !taskInfo.isFreeform();
        }
        if (!super.updateCompatInfo(taskInfo, taskListener, z)) {
            return false;
        }
        if (z2 != this.mHasSizeCompat) {
            updateVisibilityOfViews$1();
        }
        return true;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public void updateSurfacePosition() {
        int i;
        int measuredWidth;
        if (this.mLayout == null) {
            return;
        }
        Rect taskBounds = getTaskBounds();
        Rect taskStableBounds = getTaskStableBounds();
        if (this.mContext.getResources().getConfiguration().getLayoutDirection() == 1) {
            i = taskStableBounds.left;
            measuredWidth = taskBounds.left;
        } else {
            i = taskStableBounds.right - taskBounds.left;
            measuredWidth = this.mLayout.getMeasuredWidth();
        }
        int i2 = i - measuredWidth;
        int measuredHeight = (taskStableBounds.bottom - taskBounds.top) - this.mLayout.getMeasuredHeight();
        if (this.mLeash == null) {
            return;
        }
        this.mSyncQueue.runInSync(new CompatUIWindowManagerAbstract$$ExternalSyntheticLambda2(this, i2, measuredHeight));
    }

    public final void updateVisibilityOfViews$1() {
        CompatUILayout compatUILayout = this.mLayout;
        if (compatUILayout == null) {
            return;
        }
        boolean z = this.mHasSizeCompat;
        compatUILayout.setViewVisibility(R.id.size_compat_restart_button, z);
        if (!z) {
            compatUILayout.setViewVisibility(R.id.size_compat_hint, false);
        }
        if (!this.mHasSizeCompat || this.mCompatUIHintsState.mHasShownSizeCompatHint) {
            return;
        }
        this.mLayout.setViewVisibility(R.id.size_compat_hint, true);
        this.mCompatUIHintsState.mHasShownSizeCompatHint = true;
    }
}
