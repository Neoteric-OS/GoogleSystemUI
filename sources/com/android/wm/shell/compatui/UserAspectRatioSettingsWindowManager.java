package com.android.wm.shell.compatui;

import android.app.AppCompatTaskInfo;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.CompatUIController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserAspectRatioSettingsWindowManager extends CompatUIWindowManagerAbstract {
    final CompatUIController.CompatUIHintsState mCompatUIHintsState;
    public final CompatUIController$$ExternalSyntheticLambda7 mDisappearTimeSupplier;
    boolean mHasUserAspectRatioSettingsButton;
    public UserAspectRatioSettingsLayout mLayout;
    public long mNextButtonHideTimeMs;
    public final CompatUIController$$ExternalSyntheticLambda0 mOnButtonClicked;
    public final ShellExecutor mShellExecutor;
    public final CompatUIController$$ExternalSyntheticLambda1 mUserAspectRatioButtonShownChecker;
    public final CompatUIController$$ExternalSyntheticLambda2 mUserAspectRatioButtonStateConsumer;

    public static void $r8$lambda$mgsdsccZUsXLSfO5dZyQ22DCT6g(UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager) {
        if (userAspectRatioSettingsWindowManager.mLayout != null) {
            if (SystemClock.uptimeMillis() >= userAspectRatioSettingsWindowManager.mNextButtonHideTimeMs) {
                UserAspectRatioSettingsLayout userAspectRatioSettingsLayout = userAspectRatioSettingsWindowManager.mLayout;
                userAspectRatioSettingsLayout.setViewVisibility(R.id.user_aspect_ratio_settings_button, false);
                userAspectRatioSettingsLayout.setViewVisibility(R.id.user_aspect_ratio_settings_hint, false);
            }
        }
    }

    /* renamed from: $r8$lambda$qcC5U0asMBEasgAnScT0e3j-tE0, reason: not valid java name */
    public static void m903$r8$lambda$qcC5U0asMBEasgAnScT0e3jtE0(UserAspectRatioSettingsWindowManager userAspectRatioSettingsWindowManager) {
        UserAspectRatioSettingsLayout userAspectRatioSettingsLayout = userAspectRatioSettingsWindowManager.mLayout;
        if (userAspectRatioSettingsLayout == null) {
            return;
        }
        userAspectRatioSettingsLayout.setViewVisibility(R.id.user_aspect_ratio_settings_button, true);
        userAspectRatioSettingsWindowManager.mUserAspectRatioButtonStateConsumer.accept(Boolean.TRUE);
        if (userAspectRatioSettingsWindowManager.mCompatUIHintsState.mHasShownUserAspectRatioSettingsButtonHint) {
            return;
        }
        userAspectRatioSettingsWindowManager.mLayout.setViewVisibility(R.id.user_aspect_ratio_settings_hint, true);
        userAspectRatioSettingsWindowManager.mCompatUIHintsState.mHasShownUserAspectRatioSettingsButtonHint = true;
    }

    public UserAspectRatioSettingsWindowManager(Context context, TaskInfo taskInfo, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer.TaskListener taskListener, DisplayLayout displayLayout, CompatUIController.CompatUIHintsState compatUIHintsState, CompatUIController$$ExternalSyntheticLambda0 compatUIController$$ExternalSyntheticLambda0, ShellExecutor shellExecutor, CompatUIController$$ExternalSyntheticLambda7 compatUIController$$ExternalSyntheticLambda7, CompatUIController$$ExternalSyntheticLambda1 compatUIController$$ExternalSyntheticLambda1, CompatUIController$$ExternalSyntheticLambda2 compatUIController$$ExternalSyntheticLambda2) {
        super(context, taskInfo, syncTransactionQueue, taskListener, displayLayout);
        this.mNextButtonHideTimeMs = -1L;
        this.mShellExecutor = shellExecutor;
        this.mUserAspectRatioButtonShownChecker = compatUIController$$ExternalSyntheticLambda1;
        this.mUserAspectRatioButtonStateConsumer = compatUIController$$ExternalSyntheticLambda2;
        this.mHasUserAspectRatioSettingsButton = shouldShowUserAspectRatioSettingsButton(taskInfo.appCompatTaskInfo, taskInfo.baseIntent);
        this.mCompatUIHintsState = compatUIHintsState;
        this.mOnButtonClicked = compatUIController$$ExternalSyntheticLambda0;
        this.mDisappearTimeSupplier = compatUIController$$ExternalSyntheticLambda7;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View createLayout() {
        UserAspectRatioSettingsLayout inflateLayout = inflateLayout();
        this.mLayout = inflateLayout;
        inflateLayout.mWindowManager = this;
        updateVisibilityOfViews();
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean eligibleToShowLayout() {
        return this.mHasUserAspectRatioSettingsButton;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final View getLayout() {
        return this.mLayout;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final int getZOrder() {
        return 10001;
    }

    public UserAspectRatioSettingsLayout inflateLayout() {
        return (UserAspectRatioSettingsLayout) LayoutInflater.from(this.mContext).inflate(R.layout.user_aspect_ratio_settings_layout, (ViewGroup) null);
    }

    public boolean isShowingButton() {
        if (((Boolean) this.mUserAspectRatioButtonShownChecker.get()).booleanValue()) {
            if (SystemClock.uptimeMillis() < this.mNextButtonHideTimeMs) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final void removeLayout() {
        this.mLayout = null;
    }

    public final boolean shouldShowUserAspectRatioSettingsButton(AppCompatTaskInfo appCompatTaskInfo, Intent intent) {
        Rect taskStableBounds = getTaskStableBounds();
        int i = appCompatTaskInfo.topActivityLetterboxHeight;
        int i2 = appCompatTaskInfo.topActivityLetterboxWidth;
        if ((taskStableBounds.height() <= i && taskStableBounds.width() <= i2 && !appCompatTaskInfo.isUserFullscreenOverrideEnabled()) || !appCompatTaskInfo.eligibleForUserAspectRatioButton()) {
            return false;
        }
        if ((appCompatTaskInfo.isTopActivityLetterboxed() || appCompatTaskInfo.isUserFullscreenOverrideEnabled()) && !appCompatTaskInfo.isSystemFullscreenOverrideEnabled() && "android.intent.action.MAIN".equals(intent.getAction()) && intent.hasCategory("android.intent.category.LAUNCHER")) {
            return !((Boolean) this.mUserAspectRatioButtonShownChecker.get()).booleanValue() || isShowingButton();
        }
        return false;
    }

    @Override // com.android.wm.shell.compatui.CompatUIWindowManagerAbstract
    public final boolean updateCompatInfo(TaskInfo taskInfo, ShellTaskOrganizer.TaskListener taskListener, boolean z) {
        boolean z2 = this.mHasUserAspectRatioSettingsButton;
        this.mHasUserAspectRatioSettingsButton = shouldShowUserAspectRatioSettingsButton(taskInfo.appCompatTaskInfo, taskInfo.baseIntent);
        if (!super.updateCompatInfo(taskInfo, taskListener, z)) {
            return false;
        }
        if (z2 == this.mHasUserAspectRatioSettingsButton) {
            return true;
        }
        updateVisibilityOfViews();
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

    public void updateVisibilityOfViews() {
        if (!this.mHasUserAspectRatioSettingsButton) {
            ((HandlerExecutor) this.mShellExecutor).removeCallbacks(new UserAspectRatioSettingsWindowManager$$ExternalSyntheticLambda0(this, 0));
            ((HandlerExecutor) this.mShellExecutor).execute(new UserAspectRatioSettingsWindowManager$$ExternalSyntheticLambda0(this, 1));
            return;
        }
        ((HandlerExecutor) this.mShellExecutor).executeDelayed(new UserAspectRatioSettingsWindowManager$$ExternalSyntheticLambda0(this, 0), 500L);
        long intValue = ((Integer) this.mDisappearTimeSupplier.apply(4)).intValue();
        this.mNextButtonHideTimeMs = SystemClock.uptimeMillis() + intValue;
        ((HandlerExecutor) this.mShellExecutor).executeDelayed(new UserAspectRatioSettingsWindowManager$$ExternalSyntheticLambda0(this, 1), intValue);
    }
}
