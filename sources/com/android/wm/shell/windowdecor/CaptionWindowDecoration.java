package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.Size;
import android.view.Choreographer;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.window.WindowContainerTransaction;
import android.window.flags.DesktopModeFlags;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
import com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel;
import com.android.wm.shell.windowdecor.WindowDecoration;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CaptionWindowDecoration extends WindowDecoration {
    public final ShellExecutor mBgExecutor;
    public final Choreographer mChoreographer;
    public FluidResizeTaskPositioner mDragPositioningCallback;
    public DragResizeInputListener mDragResizeListener;
    public final Handler mHandler;
    public CaptionWindowDecorViewModel.CaptionTouchEventListener mOnCaptionButtonClickListener;
    public CaptionWindowDecorViewModel.CaptionTouchEventListener mOnCaptionTouchListener;
    public final WindowDecoration.RelayoutParams mRelayoutParams;
    public final WindowDecoration.RelayoutResult mResult;

    public CaptionWindowDecoration(Context context, Context context2, DisplayController displayController, ShellTaskOrganizer shellTaskOrganizer, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, Handler handler, ShellExecutor shellExecutor, Choreographer choreographer, WindowDecorViewHostSupplier windowDecorViewHostSupplier) {
        super(context, context2, displayController, shellTaskOrganizer, runningTaskInfo, surfaceControl, new DesktopModeWindowDecoration$$ExternalSyntheticLambda0(0), new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0(), new DesktopModeWindowDecoration$$ExternalSyntheticLambda0(1), new DesktopModeWindowDecoration$$ExternalSyntheticLambda0(2), new WindowDecoration.AnonymousClass2(), windowDecorViewHostSupplier);
        this.mRelayoutParams = new WindowDecoration.RelayoutParams();
        this.mResult = new WindowDecoration.RelayoutResult();
        this.mHandler = handler;
        this.mBgExecutor = shellExecutor;
        this.mChoreographer = choreographer;
    }

    public static void updateRelayoutParams(WindowDecoration.RelayoutParams relayoutParams, ActivityManager.RunningTaskInfo runningTaskInfo, boolean z, boolean z2, InsetsState insetsState) {
        relayoutParams.reset();
        relayoutParams.mRunningTaskInfo = runningTaskInfo;
        relayoutParams.mLayoutResId = R.layout.caption_window_decor;
        runningTaskInfo.getWindowingMode();
        relayoutParams.mCaptionHeightId = R.dimen.freeform_decor_caption_height;
        relayoutParams.mShadowRadiusId = runningTaskInfo.isFocused ? R.dimen.freeform_decor_shadow_focused_thickness : R.dimen.freeform_decor_shadow_unfocused_thickness;
        relayoutParams.mApplyStartTransactionOnDraw = z;
        relayoutParams.mSetTaskPositionAndCrop = z2;
        if (TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo)) {
            relayoutParams.mInputFeatures |= 4;
        }
        WindowDecoration.RelayoutParams.OccludingCaptionElement occludingCaptionElement = new WindowDecoration.RelayoutParams.OccludingCaptionElement();
        occludingCaptionElement.mWidthResId = R.dimen.caption_left_buttons_width;
        occludingCaptionElement.mAlignment = WindowDecoration.RelayoutParams.OccludingCaptionElement.Alignment.START;
        relayoutParams.mOccludingCaptionElements.add(occludingCaptionElement);
        WindowDecoration.RelayoutParams.OccludingCaptionElement occludingCaptionElement2 = new WindowDecoration.RelayoutParams.OccludingCaptionElement();
        occludingCaptionElement2.mWidthResId = R.dimen.caption_right_buttons_width;
        occludingCaptionElement2.mAlignment = WindowDecoration.RelayoutParams.OccludingCaptionElement.Alignment.END;
        relayoutParams.mOccludingCaptionElements.add(occludingCaptionElement2);
        relayoutParams.mCaptionTopPadding = relayoutParams.mRunningTaskInfo.isFreeform() ? 0 : insetsState.calculateInsets(runningTaskInfo.getConfiguration().windowConfiguration.getBounds(), WindowInsets.Type.systemBars() & (~WindowInsets.Type.captionBar()), false).top;
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration, java.lang.AutoCloseable
    public final void close() {
        DragResizeInputListener dragResizeInputListener = this.mDragResizeListener;
        if (dragResizeInputListener != null) {
            dragResizeInputListener.close();
            this.mDragResizeListener = null;
        }
        super.close();
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration
    public final int getCaptionViewId() {
        return R.id.caption;
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration
    public final void relayout(ActivityManager.RunningTaskInfo runningTaskInfo) {
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        relayout(runningTaskInfo, transaction, transaction, true, this.mTaskDragResizer.isResizingOrAnimating());
    }

    public final void setCaptionColor(int i) {
        View view = this.mResult.mRootView;
        if (view == null) {
            return;
        }
        View findViewById = ((WindowDecorLinearLayout) view).findViewById(R.id.caption);
        ((GradientDrawable) findViewById.getBackground()).setColor(i);
        ColorStateList colorStateList = findViewById.getResources().getColorStateList(((double) Color.valueOf(i).luminance()) < 0.5d ? R.color.decor_button_light_color : R.color.decor_button_dark_color, null);
        findViewById.findViewById(R.id.back_button).setBackgroundTintList(colorStateList);
        findViewById.findViewById(R.id.minimize_window).setBackgroundTintList(colorStateList);
        findViewById.findViewById(R.id.maximize_window).setBackgroundTintList(colorStateList);
        findViewById.findViewById(R.id.close_window).setBackgroundTintList(colorStateList);
    }

    public final void relayout(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        InsetsState insetsState;
        int dimensionPixelSize;
        boolean z6 = runningTaskInfo.getWindowingMode() == 5;
        if (DesktopModeFlags.ENABLE_WINDOWING_SCALED_RESIZING.isTrue()) {
            z3 = z6;
        } else {
            z3 = z6 && runningTaskInfo.isResizeable;
        }
        WindowDecorLinearLayout windowDecorLinearLayout = (WindowDecorLinearLayout) this.mResult.mRootView;
        SurfaceControl surfaceControl = this.mDecorationContainerSurface;
        final WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        WindowDecoration.RelayoutParams relayoutParams = this.mRelayoutParams;
        DisplayController.DisplayRecord displayRecord = (DisplayController.DisplayRecord) this.mDisplayController.mDisplays.get(runningTaskInfo.displayId);
        if (displayRecord != null) {
            insetsState = displayRecord.mInsetsState;
            z4 = z;
            z5 = z2;
        } else {
            z4 = z;
            z5 = z2;
            insetsState = null;
        }
        updateRelayoutParams(relayoutParams, runningTaskInfo, z4, z5, insetsState);
        relayout(this.mRelayoutParams, transaction, transaction2, windowContainerTransaction, windowDecorLinearLayout, this.mResult);
        ((HandlerExecutor) this.mBgExecutor).execute(new Runnable() { // from class: com.android.wm.shell.windowdecor.CaptionWindowDecoration$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CaptionWindowDecoration captionWindowDecoration = CaptionWindowDecoration.this;
                captionWindowDecoration.mTaskOrganizer.applyTransaction(windowContainerTransaction);
            }
        });
        View view = this.mResult.mRootView;
        if (view == null) {
            return;
        }
        if (windowDecorLinearLayout != view) {
            View findViewById = ((WindowDecorLinearLayout) view).findViewById(R.id.caption);
            findViewById.setOnTouchListener(this.mOnCaptionTouchListener);
            findViewById.findViewById(R.id.close_window).setOnClickListener(this.mOnCaptionButtonClickListener);
            findViewById.findViewById(R.id.back_button).setOnClickListener(this.mOnCaptionButtonClickListener);
            findViewById.findViewById(R.id.minimize_window).setOnClickListener(this.mOnCaptionButtonClickListener);
            findViewById.findViewById(R.id.maximize_window).setOnClickListener(this.mOnCaptionButtonClickListener);
        }
        View view2 = this.mResult.mRootView;
        if (TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo)) {
            setCaptionColor(0);
        } else {
            setCaptionColor(runningTaskInfo.taskDescription.getStatusBarColor());
        }
        view2.findViewById(R.id.maximize_window).setBackgroundResource(runningTaskInfo.getWindowingMode() == 1 ? R.drawable.decor_restore_button_dark : R.drawable.decor_maximize_button_dark);
        if (!z3) {
            DragResizeInputListener dragResizeInputListener = this.mDragResizeListener;
            if (dragResizeInputListener == null) {
                return;
            }
            dragResizeInputListener.close();
            this.mDragResizeListener = null;
            return;
        }
        if (surfaceControl != this.mDecorationContainerSurface || this.mDragResizeListener == null) {
            DragResizeInputListener dragResizeInputListener2 = this.mDragResizeListener;
            if (dragResizeInputListener2 != null) {
                dragResizeInputListener2.close();
                this.mDragResizeListener = null;
            }
            this.mDragResizeListener = new DragResizeInputListener(this.mContext, this.mHandler, this.mChoreographer, this.mDisplay.getDisplayId(), this.mDecorationContainerSurface, this.mDragPositioningCallback, this.mSurfaceControlBuilderSupplier, this.mSurfaceControlTransactionSupplier, this.mDisplayController);
        }
        int scaledTouchSlop = ViewConfiguration.get(((WindowDecorLinearLayout) this.mResult.mRootView).getContext()).getScaledTouchSlop();
        Resources resources = ((WindowDecorLinearLayout) this.mResult.mRootView).getResources();
        DragResizeInputListener dragResizeInputListener3 = this.mDragResizeListener;
        WindowDecoration.RelayoutResult relayoutResult = this.mResult;
        Size size = new Size(relayoutResult.mWidth, relayoutResult.mHeight);
        if (DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE.isTrue()) {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.freeform_edge_handle_outset);
        } else {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.freeform_resize_handle);
        }
        dragResizeInputListener3.setGeometry(new DragResizeWindowGeometry(0, size, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.freeform_edge_handle_inset), resources.getDimensionPixelSize(R.dimen.freeform_resize_corner), resources.getDimensionPixelSize(R.dimen.desktop_mode_corner_resize_large)), scaledTouchSlop);
    }
}
