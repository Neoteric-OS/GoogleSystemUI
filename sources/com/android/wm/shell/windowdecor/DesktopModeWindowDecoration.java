package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.WindowConfiguration;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Trace;
import android.os.UserHandle;
import android.util.Property;
import android.util.Size;
import android.util.Slog;
import android.util.StateSet;
import android.view.Choreographer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.window.InputTransferToken;
import android.window.SurfaceSyncGroup;
import android.window.WindowContainerTransaction;
import android.window.flags.DesktopModeFlags;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.material3.ColorScheme;
import androidx.compose.ui.graphics.ColorKt;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.apptoweb.AppToWebGenericLinksParser;
import com.android.wm.shell.apptoweb.AppToWebUtils;
import com.android.wm.shell.apptoweb.AssistContentRequester;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DragResizeInputListener;
import com.android.wm.shell.windowdecor.HandleMenu;
import com.android.wm.shell.windowdecor.HandleMenuAnimator$animateOpen$1;
import com.android.wm.shell.windowdecor.MaximizeMenu;
import com.android.wm.shell.windowdecor.WindowDecoration;
import com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalViewHostViewContainer;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import com.android.wm.shell.windowdecor.viewholder.AppHandleViewHolder;
import com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder;
import com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeWindowDecoration extends WindowDecoration {
    static final long CLOSE_MAXIMIZE_MENU_DELAY_MS = 150;
    public final AppHeaderViewHolder.Factory mAppHeaderViewHolderFactory;
    public Bitmap mAppIconBitmap;
    public CharSequence mAppName;
    public final AssistContentRequester mAssistContentRequester;
    public final ShellExecutor mBgExecutor;
    public final DesktopModeWindowDecoration$$ExternalSyntheticLambda10 mCapturedLinkExpiredRunnable;
    public final Choreographer mChoreographer;
    public final DesktopModeWindowDecoration$$ExternalSyntheticLambda10 mCloseMaximizeWindowRunnable;
    public TaskPositioner mDragPositioningCallback;
    public DragResizeInputListener mDragResizeListener;
    public DesktopModeWindowDecorViewModel.DragStartListenerImpl mExclusionRegionListener;
    public Uri mGenericLink;
    public final AppToWebGenericLinksParser mGenericLinksParser;
    public HandleMenu mHandleMenu;
    public final DefaultHandleMenuFactory mHandleMenuFactory;
    public final Handler mHandler;
    public boolean mIsAppHeaderMaximizeButtonHovered;
    public boolean mIsMaximizeMenuHovered;
    public ManageWindowsViewContainer mManageWindowsMenu;
    public MaximizeMenu mMaximizeMenu;
    public final DefaultMaximizeMenuFactory mMaximizeMenuFactory;
    public final MultiInstanceHelper mMultiInstanceHelper;
    public DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener mOnCaptionButtonClickListener;
    public DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener mOnCaptionGenericMotionListener;
    public DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener mOnCaptionLongClickListener;
    public DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener mOnCaptionTouchListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnLeftSnapClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnManageWindowsClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnMaximizeOrRestoreClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnNewWindowClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnRightSnapClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6 mOnToDesktopClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnToFullscreenClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 mOnToSplitscreenClickListener;
    public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6 mOpenInBrowserClickListener;
    public final Point mPositionInParent;
    public final WindowDecoration.RelayoutParams mRelayoutParams;
    public ResizeVeil mResizeVeil;
    public Bitmap mResizeVeilBitmap;
    public final WindowDecoration.RelayoutResult mResult;
    public final RootTaskDisplayAreaOrganizer mRootTaskDisplayAreaOrganizer;
    public final SplitScreenController mSplitScreenController;
    public final SyncTransactionQueue mSyncQueue;
    public Uri mWebUri;
    public WindowDecorationViewHolder mWindowDecorViewHolder;
    public final WindowManagerWrapper mWindowManagerWrapper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$1, reason: invalid class name */
    public final class AnonymousClass1 implements WindowDecoration.SurfaceControlViewHostFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class CapturedLink {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10] */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DesktopModeWindowDecoration(android.content.Context r17, android.content.Context r18, com.android.wm.shell.common.DisplayController r19, com.android.wm.shell.splitscreen.SplitScreenController r20, com.android.wm.shell.ShellTaskOrganizer r21, android.app.ActivityManager.RunningTaskInfo r22, android.view.SurfaceControl r23, android.os.Handler r24, com.android.wm.shell.common.ShellExecutor r25, android.view.Choreographer r26, com.android.wm.shell.common.SyncTransactionQueue r27, com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder.Factory r28, com.android.wm.shell.RootTaskDisplayAreaOrganizer r29, com.android.wm.shell.apptoweb.AppToWebGenericLinksParser r30, com.android.wm.shell.apptoweb.AssistContentRequester r31, com.android.wm.shell.common.MultiInstanceHelper r32, com.android.wm.shell.desktopmode.WindowDecorCaptionHandleRepository r33, com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier r34) {
        /*
            r16 = this;
            r13 = r16
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0 r7 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0
            r0 = 0
            r7.<init>(r0)
            com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 r8 = new com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0
            r8.<init>()
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0 r9 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0
            r0 = 1
            r9.<init>(r0)
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0 r10 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda0
            r0 = 2
            r10.<init>(r0)
            com.android.wm.shell.windowdecor.WindowManagerWrapper r14 = new com.android.wm.shell.windowdecor.WindowManagerWrapper
            java.lang.Class<android.view.WindowManager> r0 = android.view.WindowManager.class
            r1 = r17
            java.lang.Object r0 = r1.getSystemService(r0)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            r14.<init>(r0)
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$1 r11 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$1
            r11.<init>()
            com.android.wm.shell.windowdecor.DefaultMaximizeMenuFactory r15 = com.android.wm.shell.windowdecor.DefaultMaximizeMenuFactory.INSTANCE
            com.android.wm.shell.windowdecor.DefaultHandleMenuFactory r12 = com.android.wm.shell.windowdecor.DefaultHandleMenuFactory.INSTANCE
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r21
            r5 = r22
            r6 = r23
            r33 = r14
            r14 = r12
            r12 = r34
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            com.android.wm.shell.windowdecor.WindowDecoration$RelayoutParams r0 = new com.android.wm.shell.windowdecor.WindowDecoration$RelayoutParams
            r0.<init>()
            r13.mRelayoutParams = r0
            com.android.wm.shell.windowdecor.WindowDecoration$RelayoutResult r0 = new com.android.wm.shell.windowdecor.WindowDecoration$RelayoutResult
            r0.<init>()
            r13.mResult = r0
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            r13.mPositionInParent = r0
            r0 = 0
            r13.mIsAppHeaderMaximizeButtonHovered = r0
            r13.mIsMaximizeMenuHovered = r0
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10 r0 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10
            r1 = 0
            r0.<init>(r13)
            r13.mCloseMaximizeWindowRunnable = r0
            com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10 r0 = new com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda10
            r1 = 1
            r0.<init>(r13)
            r13.mCapturedLinkExpiredRunnable = r0
            r0 = r20
            r13.mSplitScreenController = r0
            r0 = r24
            r13.mHandler = r0
            r0 = r25
            r13.mBgExecutor = r0
            r0 = r26
            r13.mChoreographer = r0
            r0 = r27
            r13.mSyncQueue = r0
            r0 = r28
            r13.mAppHeaderViewHolderFactory = r0
            r0 = r29
            r13.mRootTaskDisplayAreaOrganizer = r0
            r0 = r30
            r13.mGenericLinksParser = r0
            r0 = r31
            r13.mAssistContentRequester = r0
            r13.mMaximizeMenuFactory = r15
            r13.mHandleMenuFactory = r14
            r0 = r32
            r13.mMultiInstanceHelper = r0
            r0 = r33
            r13.mWindowManagerWrapper = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DesktopModeWindowDecoration.<init>(android.content.Context, android.content.Context, com.android.wm.shell.common.DisplayController, com.android.wm.shell.splitscreen.SplitScreenController, com.android.wm.shell.ShellTaskOrganizer, android.app.ActivityManager$RunningTaskInfo, android.view.SurfaceControl, android.os.Handler, com.android.wm.shell.common.ShellExecutor, android.view.Choreographer, com.android.wm.shell.common.SyncTransactionQueue, com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder$Factory, com.android.wm.shell.RootTaskDisplayAreaOrganizer, com.android.wm.shell.apptoweb.AppToWebGenericLinksParser, com.android.wm.shell.apptoweb.AssistContentRequester, com.android.wm.shell.common.MultiInstanceHelper, com.android.wm.shell.desktopmode.WindowDecorCaptionHandleRepository, com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier):void");
    }

    public static boolean isDragResizable(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return DesktopModeFlags.ENABLE_WINDOWING_SCALED_RESIZING.isTrue() ? runningTaskInfo.isFreeform() : runningTaskInfo.isFreeform() && runningTaskInfo.isResizeable;
    }

    public static void updateRelayoutParams(WindowDecoration.RelayoutParams relayoutParams, Context context, ActivityManager.RunningTaskInfo runningTaskInfo, boolean z, boolean z2) {
        int i = runningTaskInfo.getWindowingMode() == 5 ? R.layout.desktop_mode_app_header : R.layout.desktop_mode_app_handle;
        boolean z3 = i == R.layout.desktop_mode_app_header;
        boolean z4 = i == R.layout.desktop_mode_app_handle;
        relayoutParams.reset();
        relayoutParams.mRunningTaskInfo = runningTaskInfo;
        relayoutParams.mLayoutResId = i;
        relayoutParams.mCaptionHeightId = runningTaskInfo.getWindowingMode() == 1 ? R.dimen.desktop_mode_fullscreen_decor_caption_height : R.dimen.desktop_mode_freeform_decor_caption_height;
        relayoutParams.mCaptionWidthId = relayoutParams.mLayoutResId == R.layout.desktop_mode_app_handle ? R.dimen.desktop_mode_fullscreen_decor_caption_width : 0;
        relayoutParams.mAsyncViewHost = z4;
        if (z3) {
            if (TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo)) {
                relayoutParams.mInputFeatures |= 4;
            } else if (DesktopModeFlags.ENABLE_CAPTION_COMPAT_INSET_FORCE_CONSUMPTION.isTrue()) {
                relayoutParams.mInsetSourceFlags |= 4;
            }
            if (DesktopModeFlags.ENABLE_CAPTION_COMPAT_INSET_FORCE_CONSUMPTION_ALWAYS.isTrue()) {
                relayoutParams.mInsetSourceFlags |= 16;
            }
            WindowDecoration.RelayoutParams.OccludingCaptionElement occludingCaptionElement = new WindowDecoration.RelayoutParams.OccludingCaptionElement();
            occludingCaptionElement.mWidthResId = R.dimen.desktop_mode_customizable_caption_margin_start;
            occludingCaptionElement.mAlignment = WindowDecoration.RelayoutParams.OccludingCaptionElement.Alignment.START;
            relayoutParams.mOccludingCaptionElements.add(occludingCaptionElement);
            WindowDecoration.RelayoutParams.OccludingCaptionElement occludingCaptionElement2 = new WindowDecoration.RelayoutParams.OccludingCaptionElement();
            occludingCaptionElement2.mWidthResId = R.dimen.desktop_mode_customizable_caption_margin_end;
            occludingCaptionElement2.mAlignment = WindowDecoration.RelayoutParams.OccludingCaptionElement.Alignment.END;
            relayoutParams.mOccludingCaptionElements.add(occludingCaptionElement2);
        } else if (z4) {
            relayoutParams.mInputFeatures |= 1;
        }
        boolean z5 = runningTaskInfo.isFocused;
        if (DesktopModeStatus.USE_WINDOW_SHADOWS || (DesktopModeStatus.USE_WINDOW_SHADOWS_FOCUSED_WINDOW && z5)) {
            relayoutParams.mShadowRadiusId = z5 ? R.dimen.freeform_decor_shadow_focused_thickness : R.dimen.freeform_decor_shadow_unfocused_thickness;
        }
        relayoutParams.mApplyStartTransactionOnDraw = z;
        relayoutParams.mSetTaskPositionAndCrop = z2;
        Configuration configuration = new Configuration();
        if (DesktopModeFlags.ENABLE_APP_HEADER_WITH_TASK_DENSITY.isTrue() && z3) {
            configuration.setTo(runningTaskInfo.configuration);
        } else if (DesktopModeStatus.useDesktopOverrideDensity()) {
            configuration.setTo(context.getResources().getConfiguration());
        } else {
            configuration.setTo(runningTaskInfo.configuration);
        }
        relayoutParams.mWindowDecorConfig = configuration;
        if (DesktopModeStatus.USE_ROUNDED_CORNERS) {
            relayoutParams.mCornerRadius = (int) ScreenDecorationsUtils.getWindowCornerRadius(context);
        }
    }

    public final PointF calculateMaximizeMenuPosition() {
        PointF pointF = new PointF();
        Resources resources = this.mContext.getResources();
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(this.mTaskInfo.displayId);
        if (displayLayout == null) {
            return pointF;
        }
        int i = displayLayout.mWidth;
        int i2 = displayLayout.mHeight;
        int loadDimensionPixelSize = WindowDecoration.loadDimensionPixelSize(this.mTaskInfo.getWindowingMode() == 1 ? R.dimen.desktop_mode_fullscreen_decor_caption_height : R.dimen.desktop_mode_freeform_decor_caption_height, this.mContext.getResources());
        ((ImageButton) ((WindowDecorLinearLayout) this.mResult.mRootView).findViewById(R.id.maximize_window)).getLocationInWindow(new int[2]);
        int loadDimensionPixelSize2 = WindowDecoration.loadDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_width, resources);
        int loadDimensionPixelSize3 = WindowDecoration.loadDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_height, resources);
        float width = (this.mPositionInParent.x + r5[0]) - ((loadDimensionPixelSize2 - r4.getWidth()) / 2.0f);
        float f = this.mPositionInParent.y + loadDimensionPixelSize;
        float f2 = loadDimensionPixelSize3 + f;
        if (loadDimensionPixelSize2 + width > i) {
            width = i - loadDimensionPixelSize2;
        }
        if (f2 > i2) {
            f = i2 - loadDimensionPixelSize3;
        }
        return new PointF(width, f);
    }

    public final Rect calculateValidDragArea() {
        int loadDimensionPixelSize = WindowDecoration.loadDimensionPixelSize(R.dimen.desktop_mode_app_details_width_minus_text, this.mContext.getResources()) + ((AppHeaderViewHolder) this.mWindowDecorViewHolder).appNameTextView.getWidth();
        int loadDimensionPixelSize2 = WindowDecoration.loadDimensionPixelSize(R.dimen.freeform_required_visible_empty_space_in_header, this.mContext.getResources());
        int loadDimensionPixelSize3 = WindowDecoration.loadDimensionPixelSize(R.dimen.desktop_mode_right_edge_buttons_width, this.mContext.getResources());
        int width = this.mTaskInfo.configuration.windowConfiguration.getBounds().width();
        DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(this.mTaskInfo.displayId);
        int i = displayLayout.mWidth;
        Rect rect = new Rect();
        displayLayout.getStableBounds(rect);
        int i2 = loadDimensionPixelSize + loadDimensionPixelSize3 + loadDimensionPixelSize2;
        return new Rect(i2 > width ? 0 : loadDimensionPixelSize3 + (-width) + loadDimensionPixelSize2, rect.top, i2 > width ? i - width : (i - loadDimensionPixelSize2) - loadDimensionPixelSize, rect.bottom - loadDimensionPixelSize2);
    }

    public final void checkTouchEvent(MotionEvent motionEvent) {
        View view = this.mResult.mRootView;
        if (view != null) {
            View findViewById = ((WindowDecorLinearLayout) view).findViewById(R.id.desktop_mode_caption).findViewById(R.id.caption_handle);
            boolean z = !isHandleMenuActive() && checkTouchEventInFocusedCaptionHandle(motionEvent);
            if (motionEvent.getActionMasked() == 1 && z) {
                findViewById.performClick();
            }
            if (isHandleMenuActive() && (this.mWindowDecorViewHolder instanceof AppHandleViewHolder)) {
                this.mHandleMenu.checkMotionEvent(motionEvent);
                closeHandleMenuIfNeeded(motionEvent);
            }
        }
    }

    public final boolean checkTouchEventInCaption(MotionEvent motionEvent) {
        PointF offsetCaptionLocation = offsetCaptionLocation(motionEvent);
        float f = offsetCaptionLocation.x;
        if (f >= this.mResult.mCaptionX && f <= r1 + r3.mCaptionWidth) {
            float f2 = offsetCaptionLocation.y;
            if (f2 >= 0.0f && f2 <= r3.mCaptionHeight) {
                return true;
            }
        }
        return false;
    }

    public final boolean checkTouchEventInFocusedCaptionHandle(MotionEvent motionEvent) {
        if (isHandleMenuActive()) {
            return false;
        }
        WindowDecorationViewHolder windowDecorationViewHolder = this.mWindowDecorViewHolder;
        if (!(windowDecorationViewHolder instanceof AppHandleViewHolder)) {
            return false;
        }
        boolean z = windowDecorationViewHolder instanceof AppHandleViewHolder;
        return checkTouchEventInCaption(motionEvent);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration, java.lang.AutoCloseable
    public final void close() {
        DragResizeInputListener dragResizeInputListener = this.mDragResizeListener;
        if (dragResizeInputListener != null) {
            dragResizeInputListener.close();
            this.mDragResizeListener = null;
        }
        closeHandleMenu();
        closeManageWindowsMenu();
        DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.mExclusionRegionListener;
        int i = this.mTaskInfo.taskId;
        DesktopModeTaskRepository desktopModeTaskRepository = DesktopModeWindowDecorViewModel.this.mDesktopTasksController.taskRepository;
        desktopModeTaskRepository.desktopExclusionRegions.delete(i);
        Executor executor = desktopModeTaskRepository.desktopGestureExclusionExecutor;
        if (executor != null) {
            executor.execute(new DesktopModeTaskRepository.AnonymousClass1(desktopModeTaskRepository, 1));
        }
        ResizeVeil resizeVeil = this.mResizeVeil;
        if (resizeVeil != null) {
            ValueAnimator valueAnimator = resizeVeil.veilAnimator;
            if (valueAnimator != null) {
                valueAnimator.removeAllUpdateListeners();
            }
            ValueAnimator valueAnimator2 = resizeVeil.veilAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            resizeVeil.veilAnimator = null;
            resizeVeil.isVisible = false;
            SurfaceControlViewHost surfaceControlViewHost = resizeVeil.viewHost;
            if (surfaceControlViewHost != null) {
                surfaceControlViewHost.release();
            }
            resizeVeil.viewHost = null;
            SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) resizeVeil.surfaceControlTransactionSupplier.get();
            SurfaceControl surfaceControl = resizeVeil.backgroundSurface;
            if (surfaceControl != null) {
                transaction.remove(surfaceControl);
            }
            resizeVeil.backgroundSurface = null;
            SurfaceControl surfaceControl2 = resizeVeil.iconSurface;
            if (surfaceControl2 != null) {
                transaction.remove(surfaceControl2);
            }
            resizeVeil.iconSurface = null;
            SurfaceControl surfaceControl3 = resizeVeil.veilSurface;
            if (surfaceControl3 != null) {
                transaction.remove(surfaceControl3);
            }
            resizeVeil.veilSurface = null;
            transaction.apply();
            resizeVeil.displayController.removeDisplayWindowListener(resizeVeil.onDisplaysChangedListener);
            this.mResizeVeil = null;
        }
        boolean z = this.mWindowDecorViewHolder instanceof AppHandleViewHolder;
        DesktopModeStatus.canEnterDesktopMode(this.mContext);
        super.close();
    }

    public final void closeHandleMenu() {
        if (isHandleMenuActive()) {
            this.mWindowDecorViewHolder.onHandleMenuClosed();
            final HandleMenu handleMenu = this.mHandleMenu;
            HandleMenu.HandleMenuView handleMenuView = handleMenu.handleMenuView;
            if (handleMenuView != null) {
                Function0 function0 = new Function0() { // from class: com.android.wm.shell.windowdecor.HandleMenu$close$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        AdditionalViewHostViewContainer additionalViewHostViewContainer = HandleMenu.this.handleMenuViewContainer;
                        if (additionalViewHostViewContainer != null) {
                            additionalViewHostViewContainer.releaseView();
                        }
                        HandleMenu.this.handleMenuViewContainer = null;
                        return Unit.INSTANCE;
                    }
                };
                ActivityManager.RunningTaskInfo runningTaskInfo = handleMenuView.taskInfo;
                if (runningTaskInfo == null) {
                    runningTaskInfo = null;
                }
                boolean isFullscreen = TaskInfoKt.isFullscreen(runningTaskInfo);
                HandleMenuAnimator handleMenuAnimator = handleMenuView.animator;
                if (!isFullscreen) {
                    ActivityManager.RunningTaskInfo runningTaskInfo2 = handleMenuView.taskInfo;
                    if (runningTaskInfo2 == null) {
                        runningTaskInfo2 = null;
                    }
                    if (!TaskInfoKt.isMultiWindow(runningTaskInfo2)) {
                        List list = handleMenuAnimator.animators;
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.0f);
                        ofFloat.setStartDelay(20L);
                        ofFloat.setDuration(50L);
                        list.add(ofFloat);
                        List list2 = handleMenuAnimator.animators;
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.0f);
                        ofFloat2.setStartDelay(20L);
                        ofFloat2.setDuration(50L);
                        list2.add(ofFloat2);
                        handleMenuAnimator.animateAppInfoPillFadeOut();
                        handleMenuAnimator.windowingPillClose();
                        handleMenuAnimator.moreActionsPillClose();
                        handleMenuAnimator.openInBrowserPillClose();
                        handleMenuAnimator.runAnimations(function0);
                    }
                }
                List list3 = handleMenuAnimator.animators;
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.6f);
                ofFloat3.setStartDelay(20L);
                ofFloat3.setDuration(50L);
                list3.add(ofFloat3);
                List list4 = handleMenuAnimator.animators;
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.05f);
                ofFloat4.setStartDelay(20L);
                ofFloat4.setDuration(50L);
                list4.add(ofFloat4);
                float f = (-handleMenuAnimator.captionHeight) / 2;
                List list5 = handleMenuAnimator.animators;
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.TRANSLATION_Y, f);
                ofFloat5.setStartDelay(20L);
                ofFloat5.setDuration(50L);
                list5.add(ofFloat5);
                handleMenuAnimator.animateAppInfoPillFadeOut();
                handleMenuAnimator.windowingPillClose();
                handleMenuAnimator.moreActionsPillClose();
                handleMenuAnimator.openInBrowserPillClose();
                handleMenuAnimator.runAnimations(function0);
            }
            this.mHandleMenu = null;
            DesktopModeStatus.canEnterDesktopMode(this.mContext);
        }
    }

    public final void closeHandleMenuIfNeeded(MotionEvent motionEvent) {
        View view;
        if (isHandleMenuActive()) {
            PointF offsetCaptionLocation = offsetCaptionLocation(motionEvent);
            View findViewById = ((WindowDecorLinearLayout) this.mResult.mRootView).findViewById(R.id.open_menu_button);
            float f = offsetCaptionLocation.x;
            float f2 = offsetCaptionLocation.y;
            boolean z = true;
            boolean z2 = findViewById != null && ((float) findViewById.getLeft()) <= f && ((float) findViewById.getRight()) >= f && ((float) findViewById.getTop()) <= f2 && ((float) findViewById.getBottom()) >= f2;
            HandleMenu handleMenu = this.mHandleMenu;
            AdditionalViewHostViewContainer additionalViewHostViewContainer = handleMenu.handleMenuViewContainer;
            if ((additionalViewHostViewContainer == null || (view = additionalViewHostViewContainer.windowViewHost.getView()) == null) ? false : view.isLaidOut()) {
                AdditionalViewHostViewContainer additionalViewHostViewContainer2 = handleMenu.handleMenuViewContainer;
                View view2 = additionalViewHostViewContainer2 != null ? additionalViewHostViewContainer2.windowViewHost.getView() : null;
                float f3 = offsetCaptionLocation.x;
                PointF pointF = handleMenu.handleMenuPosition;
                float f4 = f3 - pointF.x;
                float f5 = offsetCaptionLocation.y - pointF.y;
                if (view2 == null || view2.getLeft() > f4 || view2.getRight() < f4 || view2.getTop() > f5 || view2.getBottom() < f5) {
                    z = false;
                }
            }
            if (z || z2) {
                return;
            }
            closeHandleMenu();
        }
    }

    public final void closeManageWindowsMenu() {
        ManageWindowsViewContainer manageWindowsViewContainer = this.mManageWindowsMenu;
        if (manageWindowsViewContainer != null) {
            manageWindowsViewContainer.close();
        }
        this.mManageWindowsMenu = null;
    }

    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.android.wm.shell.windowdecor.MaximizeMenu, com.android.wm.shell.windowdecor.MaximizeMenu$MaximizeMenuView, com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalViewHostViewContainer] */
    /* JADX WARN: Type inference failed for: r1v13 */
    public final void closeMaximizeMenu() {
        ?? r1;
        int i = 0;
        int i2 = 1;
        int i3 = 2;
        if (isMaximizeMenuActive()) {
            MaximizeMenu maximizeMenu = this.mMaximizeMenu;
            final DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, i);
            MaximizeMenu.MaximizeMenuView maximizeMenuView = maximizeMenu.maximizeMenuView;
            final AdditionalViewHostViewContainer additionalViewHostViewContainer = maximizeMenu.maximizeMenu;
            if (maximizeMenuView == null) {
                if (additionalViewHostViewContainer != null) {
                    additionalViewHostViewContainer.releaseView();
                }
                r1 = 0;
            } else {
                Function0 function0 = new Function0() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu$close$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        AdditionalViewHostViewContainer additionalViewHostViewContainer2 = AdditionalViewHostViewContainer.this;
                        if (additionalViewHostViewContainer2 != null) {
                            additionalViewHostViewContainer2.releaseView();
                        }
                        desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                        return Unit.INSTANCE;
                    }
                };
                maximizeMenuView.maximizeButton.setLayerType(2, null);
                maximizeMenuView.maximizeText.setLayerType(2, null);
                AnimatorSet animatorSet = maximizeMenuView.menuAnimatorSet;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                AnimatorSet animatorSet2 = new AnimatorSet();
                maximizeMenuView.menuAnimatorSet = animatorSet2;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(maximizeMenuView.rootView, (Property<ViewGroup, Float>) View.SCALE_Y, 1.0f, 0.8f);
                ofFloat.setDuration(200L);
                Interpolator interpolator = Interpolators.FAST_OUT_LINEAR_IN;
                ofFloat.setInterpolator(interpolator);
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.8f);
                ofFloat2.setDuration(200L);
                ofFloat2.setInterpolator(interpolator);
                ofFloat2.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat2, maximizeMenuView, i2));
                ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, 1.25f);
                ofFloat3.setDuration(200L);
                ofFloat3.setInterpolator(interpolator);
                ofFloat3.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat3, maximizeMenuView, i3));
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(maximizeMenuView.rootView, (Property<ViewGroup, Float>) View.TRANSLATION_Y, 0.0f, maximizeMenuView.menuHeight * (-0.19999999f));
                ofFloat4.setDuration(200L);
                ofFloat4.setInterpolator(interpolator);
                ObjectAnimator ofInt = ObjectAnimator.ofInt(maximizeMenuView.rootView.getBackground(), "alpha", 255, 0);
                ofInt.setStartDelay(33L);
                ofInt.setDuration(50L);
                ValueAnimator ofFloat5 = ValueAnimator.ofFloat(1.0f, 0.0f);
                ofFloat5.setDuration(50L);
                ofFloat5.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat5, maximizeMenuView, 3));
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(maximizeMenuView.rootView, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f, 0.0f);
                ofFloat6.setDuration(50L);
                animatorSet2.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofInt, ofFloat5, ofFloat6);
                AnimatorSet animatorSet3 = maximizeMenuView.menuAnimatorSet;
                if (animatorSet3 != null) {
                    animatorSet3.addListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(maximizeMenuView, function0, i2));
                }
                AnimatorSet animatorSet4 = maximizeMenuView.menuAnimatorSet;
                if (animatorSet4 != null) {
                    animatorSet4.start();
                }
                r1 = 0;
            }
            maximizeMenu.maximizeMenu = r1;
            maximizeMenu.maximizeMenuView = r1;
            this.mMaximizeMenu = r1;
        }
    }

    public final void createMaximizeMenu() {
        DefaultMaximizeMenuFactory defaultMaximizeMenuFactory = this.mMaximizeMenuFactory;
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer = this.mRootTaskDisplayAreaOrganizer;
        DisplayController displayController = this.mDisplayController;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        Context context = this.mContext;
        PointF calculateMaximizeMenuPosition = calculateMaximizeMenuPosition();
        Supplier supplier = this.mSurfaceControlTransactionSupplier;
        defaultMaximizeMenuFactory.getClass();
        MaximizeMenu maximizeMenu = new MaximizeMenu(syncTransactionQueue, rootTaskDisplayAreaOrganizer, displayController, runningTaskInfo, context, calculateMaximizeMenuPosition, supplier);
        this.mMaximizeMenu = maximizeMenu;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.mOnMaximizeOrRestoreClickListener;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.mOnLeftSnapClickListener;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.mOnRightSnapClickListener;
        DesktopModeWindowDecoration$$ExternalSyntheticLambda6 desktopModeWindowDecoration$$ExternalSyntheticLambda6 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda6(this, 1);
        DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, 3);
        if (maximizeMenu.maximizeMenu != null) {
            return;
        }
        final SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) supplier.get();
        SurfaceControl.Builder builder = new SurfaceControl.Builder();
        rootTaskDisplayAreaOrganizer.attachToDisplayArea(runningTaskInfo.displayId, builder);
        maximizeMenu.leash = builder.setName("Maximize Menu").setContainerLayer().build();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(maximizeMenu.menuWidth, maximizeMenu.menuHeight, 2, 8650760, -2);
        layoutParams.setTitle("Maximize Menu for Task=" + runningTaskInfo.taskId);
        layoutParams.setTrustedOverlay();
        Configuration configuration = runningTaskInfo.configuration;
        SurfaceControl surfaceControl = maximizeMenu.leash;
        if (surfaceControl == null) {
            surfaceControl = null;
        }
        maximizeMenu.viewHost = new SurfaceControlViewHost(context, displayController.getDisplay(runningTaskInfo.displayId), new WindowlessWindowManager(configuration, surfaceControl, (InputTransferToken) null), "MaximizeMenu");
        MaximizeMenu.MaximizeMenuView maximizeMenuView = new MaximizeMenu.MaximizeMenuView(maximizeMenu.menuHeight, maximizeMenu.menuPadding, context);
        ColorScheme colorScheme = maximizeMenuView.decorThemeUtil.getColorScheme(runningTaskInfo);
        int m373toArgb8_81llA = ColorKt.m373toArgb8_81llA(colorScheme.surfaceContainerLow);
        int m373toArgb8_81llA2 = ColorKt.m373toArgb8_81llA(colorScheme.onSurface);
        long j = colorScheme.primary;
        int m373toArgb8_81llA3 = ColorKt.m373toArgb8_81llA(j);
        int m373toArgb8_81llA4 = ColorKt.m373toArgb8_81llA(j);
        LayerDrawable createMaximizeButtonDrawable = maximizeMenuView.createMaximizeButtonDrawable(m373toArgb8_81llA3, Color.argb(31, Color.red(m373toArgb8_81llA4), Color.green(m373toArgb8_81llA4), Color.blue(m373toArgb8_81llA4)), Integer.valueOf(m373toArgb8_81llA));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, createMaximizeButtonDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, createMaximizeButtonDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, createMaximizeButtonDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_hovered}, createMaximizeButtonDrawable);
        int[] iArr = StateSet.WILD_CARD;
        long j2 = colorScheme.outlineVariant;
        stateListDrawable.addState(iArr, maximizeMenuView.createMaximizeButtonDrawable(ColorKt.m373toArgb8_81llA(j2), ColorKt.m373toArgb8_81llA(colorScheme.surfaceContainerLow), null));
        MaximizeMenu.MaximizeMenuView.MenuStyle.MaximizeOption maximizeOption = new MaximizeMenu.MaximizeMenuView.MenuStyle.MaximizeOption(stateListDrawable);
        int m373toArgb8_81llA5 = ColorKt.m373toArgb8_81llA(j2);
        int m373toArgb8_81llA6 = ColorKt.m373toArgb8_81llA(j);
        int argb = Color.argb(102, Color.red(m373toArgb8_81llA6), Color.green(m373toArgb8_81llA6), Color.blue(m373toArgb8_81llA6));
        int m373toArgb8_81llA7 = ColorKt.m373toArgb8_81llA(j);
        int m373toArgb8_81llA8 = ColorKt.m373toArgb8_81llA(j2);
        int m373toArgb8_81llA9 = ColorKt.m373toArgb8_81llA(j);
        int m373toArgb8_81llA10 = ColorKt.m373toArgb8_81llA(j);
        maximizeMenuView.style = new MaximizeMenu.MaximizeMenuView.MenuStyle(m373toArgb8_81llA, m373toArgb8_81llA2, maximizeOption, new MaximizeMenu.MaximizeMenuView.MenuStyle.SnapOptions(m373toArgb8_81llA5, argb, m373toArgb8_81llA7, m373toArgb8_81llA8, m373toArgb8_81llA9, m373toArgb8_81llA, Color.argb(31, Color.red(m373toArgb8_81llA10), Color.green(m373toArgb8_81llA10), Color.blue(m373toArgb8_81llA10))));
        Drawable background = maximizeMenuView.rootView.getBackground();
        MaximizeMenu.MaximizeMenuView.MenuStyle menuStyle = maximizeMenuView.style;
        if (menuStyle == null) {
            menuStyle = null;
        }
        background.setTint(menuStyle.backgroundColor);
        Button button = maximizeMenuView.maximizeButton;
        MaximizeMenu.MaximizeMenuView.MenuStyle menuStyle2 = maximizeMenuView.style;
        if (menuStyle2 == null) {
            menuStyle2 = null;
        }
        button.setBackground(menuStyle2.maximizeOption.drawable);
        TextView textView = maximizeMenuView.maximizeText;
        MaximizeMenu.MaximizeMenuView.MenuStyle menuStyle3 = maximizeMenuView.style;
        if (menuStyle3 == null) {
            menuStyle3 = null;
        }
        textView.setTextColor(menuStyle3.textColor);
        TextView textView2 = maximizeMenuView.snapWindowText;
        MaximizeMenu.MaximizeMenuView.MenuStyle menuStyle4 = maximizeMenuView.style;
        if (menuStyle4 == null) {
            menuStyle4 = null;
        }
        textView2.setTextColor(menuStyle4.textColor);
        maximizeMenuView.updateSplitSnapSelection(MaximizeMenu.MaximizeMenuView.SnapToHalfSelection.NONE);
        maximizeMenuView.onMaximizeClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3;
        maximizeMenuView.onLeftSnapClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32;
        maximizeMenuView.onRightSnapClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33;
        maximizeMenuView.onMenuHoverListener = desktopModeWindowDecoration$$ExternalSyntheticLambda6;
        maximizeMenuView.onOutsideTouchListener = desktopModeWindowDecoration$$ExternalSyntheticLambda3;
        SurfaceControlViewHost surfaceControlViewHost = maximizeMenu.viewHost;
        if (surfaceControlViewHost == null) {
            surfaceControlViewHost = null;
        }
        surfaceControlViewHost.setView(maximizeMenuView.rootView, layoutParams);
        maximizeMenu.maximizeMenuView = maximizeMenuView;
        SurfaceControl surfaceControl2 = maximizeMenu.leash;
        if (surfaceControl2 == null) {
            surfaceControl2 = null;
        }
        SurfaceControl.Transaction layer = transaction.setLayer(surfaceControl2, 70000);
        SurfaceControl surfaceControl3 = maximizeMenu.leash;
        if (surfaceControl3 == null) {
            surfaceControl3 = null;
        }
        SurfaceControl.Transaction position = layer.setPosition(surfaceControl3, calculateMaximizeMenuPosition.x, calculateMaximizeMenuPosition.y);
        SurfaceControl surfaceControl4 = maximizeMenu.leash;
        if (surfaceControl4 == null) {
            surfaceControl4 = null;
        }
        SurfaceControl.Transaction cornerRadius = position.setCornerRadius(surfaceControl4, maximizeMenu.cornerRadius);
        SurfaceControl surfaceControl5 = maximizeMenu.leash;
        if (surfaceControl5 == null) {
            surfaceControl5 = null;
        }
        cornerRadius.show(surfaceControl5);
        SurfaceControl surfaceControl6 = maximizeMenu.leash;
        if (surfaceControl6 == null) {
            surfaceControl6 = null;
        }
        SurfaceControlViewHost surfaceControlViewHost2 = maximizeMenu.viewHost;
        if (surfaceControlViewHost2 == null) {
            surfaceControlViewHost2 = null;
        }
        maximizeMenu.maximizeMenu = new AdditionalViewHostViewContainer(surfaceControl6, surfaceControlViewHost2, supplier);
        syncTransactionQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu$createMaximizeMenu$2
            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction2) {
                transaction2.merge(transaction);
                transaction.close();
            }
        });
        final MaximizeMenu.MaximizeMenuView maximizeMenuView2 = maximizeMenu.maximizeMenuView;
        if (maximizeMenuView2 != null) {
            Function0 function0 = new Function0() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu$show$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    final MaximizeMenu.MaximizeMenuView maximizeMenuView3 = MaximizeMenu.MaximizeMenuView.this;
                    maximizeMenuView3.maximizeButton.post(new Runnable() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu$MaximizeMenuView$requestAccessibilityFocus$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MaximizeMenu.MaximizeMenuView.this.maximizeButton.sendAccessibilityEvent(8);
                        }
                    });
                    return Unit.INSTANCE;
                }
            };
            maximizeMenuView2.maximizeButton.setLayerType(2, null);
            maximizeMenuView2.maximizeText.setLayerType(2, null);
            AnimatorSet animatorSet = new AnimatorSet();
            maximizeMenuView2.menuAnimatorSet = animatorSet;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(maximizeMenuView2.rootView, (Property<ViewGroup, Float>) View.SCALE_Y, 0.8f, 1.0f);
            ofFloat.setDuration(300L);
            Interpolator interpolator = Interpolators.EMPHASIZED_DECELERATE;
            ofFloat.setInterpolator(interpolator);
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.8f, 1.0f);
            ofFloat2.setDuration(300L);
            ofFloat2.setInterpolator(interpolator);
            ofFloat2.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat2, maximizeMenuView2, 0));
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.25f, 1.0f);
            ofFloat3.setDuration(300L);
            ofFloat3.setInterpolator(interpolator);
            ofFloat3.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat3, maximizeMenuView2, 4));
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(maximizeMenuView2.rootView, (Property<ViewGroup, Float>) View.TRANSLATION_Y, maximizeMenuView2.menuHeight * (-0.19999999f), 0.0f);
            ofFloat4.setDuration(300L);
            ofFloat4.setInterpolator(interpolator);
            ObjectAnimator ofInt = ObjectAnimator.ofInt(maximizeMenuView2.rootView.getBackground(), "alpha", 255);
            ofInt.setDuration(50L);
            ValueAnimator ofFloat5 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat5.setDuration(50L);
            ofFloat5.setStartDelay(33L);
            ofFloat5.addUpdateListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$2$1(ofFloat5, maximizeMenuView2, 5));
            int i = 0;
            ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(maximizeMenuView2.rootView, (Property<ViewGroup, Float>) View.TRANSLATION_Z, 1.0f);
            ofFloat6.setDuration(50L);
            ofFloat6.setStartDelay(33L);
            animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofInt, ofFloat5, ofFloat6);
            AnimatorSet animatorSet2 = maximizeMenuView2.menuAnimatorSet;
            if (animatorSet2 != null) {
                animatorSet2.addListener(new MaximizeMenu$MaximizeMenuView$animateOpenMenu$$inlined$addListener$default$1(maximizeMenuView2, function0, i));
            }
            AnimatorSet animatorSet3 = maximizeMenuView2.menuAnimatorSet;
            if (animatorSet3 != null) {
                animatorSet3.start();
            }
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration
    public final int getCaptionViewId() {
        return R.id.desktop_mode_caption;
    }

    public final void hideResizeVeil() {
        final ResizeVeil resizeVeil = this.mResizeVeil;
        if (resizeVeil.isVisible) {
            ValueAnimator valueAnimator = resizeVeil.veilAnimator;
            if (valueAnimator != null) {
                valueAnimator.removeAllUpdateListeners();
            }
            ValueAnimator valueAnimator2 = resizeVeil.veilAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            final SurfaceControl surfaceControl = resizeVeil.backgroundSurface;
            final SurfaceControl surfaceControl2 = resizeVeil.iconSurface;
            if (surfaceControl == null || surfaceControl2 == null) {
                return;
            }
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setDuration(100L);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$hideVeil$1$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    ((SurfaceControl.Transaction) ResizeVeil.this.surfaceControlTransactionSupplier.get()).setAlpha(surfaceControl, ((Float) ofFloat.getAnimatedValue()).floatValue()).setAlpha(surfaceControl2, ((Float) ofFloat.getAnimatedValue()).floatValue()).apply();
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.windowdecor.ResizeVeil$hideVeil$1$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ((SurfaceControl.Transaction) ResizeVeil.this.surfaceControlTransactionSupplier.get()).hide(surfaceControl).hide(surfaceControl2).apply();
                }
            });
            resizeVeil.veilAnimator = ofFloat;
            ofFloat.start();
            resizeVeil.isVisible = false;
        }
    }

    public final boolean isHandleMenuActive() {
        return this.mHandleMenu != null;
    }

    public final boolean isMaximizeMenuActive() {
        return this.mMaximizeMenu != null;
    }

    public final void loadAppInfoIfNeeded() {
        try {
            try {
                Trace.beginSection("DesktopModeWindowDecoration#loadAppInfoIfNeeded");
            } catch (PackageManager.NameNotFoundException e) {
                Slog.e("DesktopModeWindowDecoration", "Base activity's component name cannot be found on the system", e);
            }
            if (this.mAppIconBitmap == null || this.mAppName == null) {
                ComponentName componentName = this.mTaskInfo.baseActivity;
                if (componentName == null) {
                    Slog.e("DesktopModeWindowDecoration", "Base activity component not found in task");
                    return;
                }
                PackageManager packageManager = this.mUserContext.getPackageManager();
                ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 0);
                Drawable icon = new IconProvider(this.mContext).getIcon(activityInfo);
                Drawable userBadgedIcon = packageManager.getUserBadgedIcon(icon, UserHandle.of(this.mTaskInfo.userId));
                Context context = this.mContext;
                Resources resources = context.getResources();
                this.mAppIconBitmap = new BaseIconFactory(context, resources.getDisplayMetrics().densityDpi, resources.getDimensionPixelSize(R.dimen.desktop_mode_caption_icon_radius), false).createIconBitmap(userBadgedIcon, 1.0f, 0);
                Context context2 = this.mContext;
                Resources resources2 = context2.getResources();
                this.mResizeVeilBitmap = new BaseIconFactory(context2, resources2.getDisplayMetrics().densityDpi, resources2.getDimensionPixelSize(R.dimen.desktop_mode_resize_veil_icon_size), false).createScaledBitmap(0, icon);
                this.mAppName = packageManager.getApplicationLabel(activityInfo.applicationInfo);
            }
        } finally {
            Trace.endSection();
        }
    }

    public final PointF offsetCaptionLocation(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskOrganizer.getRunningTaskInfo(this.mTaskInfo.taskId);
        if (runningTaskInfo == null) {
            return pointF;
        }
        Point point = runningTaskInfo.positionInParent;
        pointF.offset(-point.x, -point.y);
        return pointF;
    }

    public void onAssistContentReceived(AssistContent assistContent) {
        Uri uri;
        int i = 0;
        this.mWebUri = assistContent == null ? null : assistContent.getWebUri();
        loadAppInfoIfNeeded();
        ComponentName componentName = this.mTaskInfo.baseActivity;
        if (componentName != null) {
            String str = (String) this.mGenericLinksParser.genericLinksMap.get(componentName.getPackageName());
            this.mGenericLink = str == null ? null : Uri.parse(str);
        }
        this.mMultiInstanceHelper.supportsMultiInstanceSplit(this.mTaskInfo.baseActivity);
        DefaultHandleMenuFactory defaultHandleMenuFactory = this.mHandleMenuFactory;
        WindowManagerWrapper windowManagerWrapper = this.mWindowManagerWrapper;
        int i2 = this.mRelayoutParams.mLayoutResId;
        Bitmap bitmap = this.mAppIconBitmap;
        CharSequence charSequence = this.mAppName;
        SplitScreenController splitScreenController = this.mSplitScreenController;
        boolean canEnterDesktopMode = DesktopModeStatus.canEnterDesktopMode(this.mContext);
        ComponentName componentName2 = this.mTaskInfo.baseActivity;
        if (componentName2 != null) {
            Context context = this.mContext;
            String packageName = componentName2.getPackageName();
            int userId = this.mUserContext.getUserId();
            Intent intent = AppToWebUtils.browserIntent;
            intent.setPackage(packageName);
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivitiesAsUser(intent, 131072, userId)) {
                if (resolveInfo.activityInfo != null && resolveInfo.handleAllWebDataURI) {
                    uri = null;
                    break;
                }
            }
        }
        Uri uri2 = this.mWebUri;
        if (uri2 == null) {
            uri2 = this.mGenericLink;
        }
        uri = uri2;
        WindowDecoration.RelayoutResult relayoutResult = this.mResult;
        int i3 = relayoutResult.mCaptionWidth;
        int i4 = relayoutResult.mCaptionHeight;
        int i5 = relayoutResult.mCaptionX;
        defaultHandleMenuFactory.getClass();
        this.mHandleMenu = new HandleMenu(this, windowManagerWrapper, i2, bitmap, charSequence, splitScreenController, canEnterDesktopMode, uri, i3, i4, i5);
        this.mWindowDecorViewHolder.onHandleMenuOpened();
        final HandleMenu handleMenu = this.mHandleMenu;
        DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, 4);
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.mOnToFullscreenClickListener;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.mOnToSplitscreenClickListener;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.mOnNewWindowClickListener;
        DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.mOnManageWindowsClickListener;
        final DesktopModeWindowDecoration$$ExternalSyntheticLambda6 desktopModeWindowDecoration$$ExternalSyntheticLambda6 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda6(this, i);
        DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, 5);
        DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda33 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, 6);
        handleMenu.getClass();
        SurfaceSyncGroup surfaceSyncGroup = new SurfaceSyncGroup("HandleMenu");
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        Context context2 = handleMenu.context;
        boolean z = handleMenu.openInBrowserLink != null;
        int i6 = handleMenu.menuWidth;
        int i7 = handleMenu.captionHeight;
        boolean z2 = handleMenu.shouldShowWindowingPill;
        HandleMenu.HandleMenuView handleMenuView = new HandleMenu.HandleMenuView(context2, i6, i7, z2, z);
        ActivityManager.RunningTaskInfo runningTaskInfo = handleMenu.taskInfo;
        Bitmap bitmap2 = handleMenu.appIconBitmap;
        CharSequence charSequence2 = handleMenu.appName;
        handleMenuView.taskInfo = runningTaskInfo;
        ColorScheme colorScheme = handleMenuView.decorThemeUtil.getColorScheme(runningTaskInfo);
        int m373toArgb8_81llA = ColorKt.m373toArgb8_81llA(colorScheme.surfaceBright);
        long j = colorScheme.onSurface;
        int m373toArgb8_81llA2 = ColorKt.m373toArgb8_81llA(j);
        boolean z3 = z;
        handleMenuView.style = new HandleMenu.HandleMenuView.MenuStyle(m373toArgb8_81llA, m373toArgb8_81llA2, new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_selected}, new int[0]}, new int[]{ColorKt.m373toArgb8_81llA(j), ColorKt.m373toArgb8_81llA(j), ColorKt.m373toArgb8_81llA(colorScheme.primary), ColorKt.m373toArgb8_81llA(j)}));
        handleMenuView.appInfoPill.getBackground().setTint(m373toArgb8_81llA);
        ColorStateList valueOf = ColorStateList.valueOf(m373toArgb8_81llA2);
        HandleMenuImageButton handleMenuImageButton = handleMenuView.collapseMenuButton;
        handleMenuImageButton.setImageTintList(valueOf);
        ActivityManager.RunningTaskInfo runningTaskInfo2 = handleMenuView.taskInfo;
        if (runningTaskInfo2 == null) {
            runningTaskInfo2 = null;
        }
        handleMenuImageButton.taskInfo = runningTaskInfo2;
        handleMenuView.appIconView.setImageBitmap(bitmap2);
        TextView textView = handleMenuView.appNameView;
        textView.setText(charSequence2);
        textView.setTextColor(m373toArgb8_81llA2);
        if (z2) {
            HandleMenu.HandleMenuView.MenuStyle menuStyle = handleMenuView.style;
            if (menuStyle == null) {
                menuStyle = null;
            }
            handleMenuView.windowingPill.getBackground().setTint(menuStyle.backgroundColor);
            handleMenuView.floatingBtn.setVisibility(8);
            ImageButton imageButton = handleMenuView.fullscreenBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo3 = handleMenuView.taskInfo;
            if (runningTaskInfo3 == null) {
                runningTaskInfo3 = null;
            }
            imageButton.setSelected(TaskInfoKt.isFullscreen(runningTaskInfo3));
            ImageButton imageButton2 = handleMenuView.fullscreenBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo4 = handleMenuView.taskInfo;
            if (runningTaskInfo4 == null) {
                runningTaskInfo4 = null;
            }
            imageButton2.setEnabled(!TaskInfoKt.isFullscreen(runningTaskInfo4));
            handleMenuView.fullscreenBtn.setImageTintList(menuStyle.windowingButtonColor);
            ImageButton imageButton3 = handleMenuView.splitscreenBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo5 = handleMenuView.taskInfo;
            if (runningTaskInfo5 == null) {
                runningTaskInfo5 = null;
            }
            imageButton3.setSelected(TaskInfoKt.isMultiWindow(runningTaskInfo5));
            ImageButton imageButton4 = handleMenuView.splitscreenBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo6 = handleMenuView.taskInfo;
            if (runningTaskInfo6 == null) {
                runningTaskInfo6 = null;
            }
            imageButton4.setEnabled(!TaskInfoKt.isMultiWindow(runningTaskInfo6));
            handleMenuView.splitscreenBtn.setImageTintList(menuStyle.windowingButtonColor);
            ImageButton imageButton5 = handleMenuView.floatingBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo7 = handleMenuView.taskInfo;
            if (runningTaskInfo7 == null) {
                runningTaskInfo7 = null;
            }
            imageButton5.setSelected(runningTaskInfo7.getWindowingMode() == 2);
            ImageButton imageButton6 = handleMenuView.floatingBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo8 = handleMenuView.taskInfo;
            if (runningTaskInfo8 == null) {
                runningTaskInfo8 = null;
            }
            imageButton6.setEnabled(!(runningTaskInfo8.getWindowingMode() == 2));
            handleMenuView.floatingBtn.setImageTintList(menuStyle.windowingButtonColor);
            ImageButton imageButton7 = handleMenuView.desktopBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo9 = handleMenuView.taskInfo;
            if (runningTaskInfo9 == null) {
                runningTaskInfo9 = null;
            }
            imageButton7.setSelected(runningTaskInfo9.isFreeform());
            ImageButton imageButton8 = handleMenuView.desktopBtn;
            ActivityManager.RunningTaskInfo runningTaskInfo10 = handleMenuView.taskInfo;
            if (runningTaskInfo10 == null) {
                runningTaskInfo10 = null;
            }
            imageButton8.setEnabled(!runningTaskInfo10.isFreeform());
            handleMenuView.desktopBtn.setImageTintList(menuStyle.windowingButtonColor);
        }
        HandleMenu.HandleMenuView.MenuStyle menuStyle2 = handleMenuView.style;
        if (menuStyle2 == null) {
            menuStyle2 = null;
        }
        handleMenuView.moreActionsPill.setVisibility(8);
        Button button = handleMenuView.screenshotBtn;
        button.setVisibility(8);
        button.getBackground().setTint(menuStyle2.backgroundColor);
        int i8 = menuStyle2.textColor;
        button.setTextColor(i8);
        button.setCompoundDrawableTintList(ColorStateList.valueOf(i8));
        Button button2 = handleMenuView.newWindowBtn;
        button2.setVisibility(8);
        Drawable background = button2.getBackground();
        int i9 = menuStyle2.backgroundColor;
        background.setTint(i9);
        button2.setTextColor(i8);
        button2.setCompoundDrawableTintList(ColorStateList.valueOf(i8));
        Button button3 = handleMenuView.manageWindowBtn;
        button3.setVisibility(8);
        button3.getBackground().setTint(i9);
        button3.setTextColor(i8);
        button3.setCompoundDrawableTintList(ColorStateList.valueOf(i8));
        HandleMenu.HandleMenuView.MenuStyle menuStyle3 = handleMenuView.style;
        if (menuStyle3 == null) {
            menuStyle3 = null;
        }
        View view = handleMenuView.openInBrowserPill;
        view.setVisibility(z3 ? 0 : 8);
        view.getBackground().setTint(menuStyle3.backgroundColor);
        Button button4 = handleMenuView.browserBtn;
        int i10 = menuStyle3.textColor;
        button4.setTextColor(i10);
        button4.setCompoundDrawableTintList(ColorStateList.valueOf(i10));
        handleMenuView.onToDesktopClickListener = desktopModeWindowDecoration$$ExternalSyntheticLambda3;
        handleMenuView.onToFullscreenClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3;
        handleMenuView.onToSplitScreenClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32;
        handleMenuView.onNewWindowClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33;
        handleMenuView.onManageWindowsClickListener = desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34;
        handleMenuView.onOpenInBrowserClickListener = new Function0() { // from class: com.android.wm.shell.windowdecor.HandleMenu$createHandleMenu$handleMenuView$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Function1 function1 = desktopModeWindowDecoration$$ExternalSyntheticLambda6;
                Uri uri3 = handleMenu.openInBrowserLink;
                Intrinsics.checkNotNull(uri3);
                function1.invoke(uri3);
                return Unit.INSTANCE;
            }
        };
        handleMenuView.onCloseMenuClickListener = desktopModeWindowDecoration$$ExternalSyntheticLambda32;
        handleMenuView.onOutsideTouchListener = desktopModeWindowDecoration$$ExternalSyntheticLambda33;
        PointF pointF = handleMenu.handleMenuPosition;
        int i11 = (int) pointF.x;
        int i12 = (int) pointF.y;
        handleMenu.taskInfo.isFreeform();
        final View view2 = handleMenuView.rootView;
        DesktopModeWindowDecoration desktopModeWindowDecoration = handleMenu.parentDecor;
        SurfaceControl build = ((SurfaceControl.Builder) desktopModeWindowDecoration.mSurfaceControlBuilderSupplier.get()).setName("Handle Menu of Task=" + desktopModeWindowDecoration.mTaskInfo.taskId).setContainerLayer().setParent(desktopModeWindowDecoration.mDecorationContainerSurface).setCallsite("WindowDecoration.addWindow").build();
        SurfaceControl.Transaction position = transaction.setPosition(build, (float) i11, (float) i12);
        int i13 = handleMenu.menuWidth;
        int i14 = handleMenu.menuHeight;
        position.setWindowCrop(build, i13, i14).show(build);
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i13, i14, 2, 8650760, -2);
        layoutParams.setTitle("Additional window of Task=" + desktopModeWindowDecoration.mTaskInfo.taskId);
        layoutParams.setTrustedOverlay();
        WindowlessWindowManager windowlessWindowManager = new WindowlessWindowManager(desktopModeWindowDecoration.mTaskInfo.configuration, build, (InputTransferToken) null);
        WindowDecoration.SurfaceControlViewHostFactory surfaceControlViewHostFactory = desktopModeWindowDecoration.mSurfaceControlViewHostFactory;
        Context context3 = desktopModeWindowDecoration.mDecorWindowContext;
        Display display = desktopModeWindowDecoration.mDisplay;
        surfaceControlViewHostFactory.getClass();
        final SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(context3, display, windowlessWindowManager, "WindowDecoration");
        surfaceSyncGroup.add(surfaceControlViewHost.getSurfacePackage(), new Runnable() { // from class: com.android.wm.shell.windowdecor.WindowDecoration$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                surfaceControlViewHost.setView(view2, layoutParams);
            }
        });
        handleMenu.handleMenuViewContainer = new AdditionalViewHostViewContainer(build, surfaceControlViewHost, desktopModeWindowDecoration.mSurfaceControlTransactionSupplier);
        handleMenu.handleMenuView = handleMenuView;
        surfaceSyncGroup.addTransaction(transaction);
        surfaceSyncGroup.markSyncReady();
        HandleMenu.HandleMenuView handleMenuView2 = handleMenu.handleMenuView;
        if (handleMenuView2 != null) {
            ActivityManager.RunningTaskInfo runningTaskInfo11 = handleMenuView2.taskInfo;
            if (runningTaskInfo11 == null) {
                runningTaskInfo11 = null;
            }
            boolean isFullscreen = TaskInfoKt.isFullscreen(runningTaskInfo11);
            final HandleMenuAnimator handleMenuAnimator = handleMenuView2.animator;
            if (!isFullscreen) {
                ActivityManager.RunningTaskInfo runningTaskInfo12 = handleMenuView2.taskInfo;
                if (runningTaskInfo12 == null) {
                    runningTaskInfo12 = null;
                }
                if (!TaskInfoKt.isMultiWindow(runningTaskInfo12)) {
                    handleMenuAnimator.prepareMenuForAnimation();
                    List list = handleMenuAnimator.animators;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.5f, 1.0f);
                    ofFloat.setDuration(217L);
                    list.add(ofFloat);
                    List list2 = handleMenuAnimator.animators;
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.5f, 1.0f);
                    ofFloat2.setDuration(217L);
                    list2.add(ofFloat2);
                    handleMenuAnimator.animateAppInfoPillOpen();
                    handleMenuAnimator.animateWindowingPillOpen();
                    handleMenuAnimator.animateMoreActionsPillOpen();
                    handleMenuAnimator.animateOpenInBrowserPill();
                    handleMenuAnimator.runAnimations(new Function0() { // from class: com.android.wm.shell.windowdecor.HandleMenuAnimator$animateOpen$1

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.wm.shell.windowdecor.HandleMenuAnimator$animateOpen$1$1, reason: invalid class name */
                        public final class AnonymousClass1 implements Runnable {
                            public final /* synthetic */ int $r8$classId;
                            public final /* synthetic */ HandleMenuAnimator this$0;

                            public /* synthetic */ AnonymousClass1(HandleMenuAnimator handleMenuAnimator, int i) {
                                this.$r8$classId = i;
                                this.this$0 = handleMenuAnimator;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (this.$r8$classId) {
                                    case 0:
                                        this.this$0.appInfoPill.requireViewById(R.id.collapse_menu_button).sendAccessibilityEvent(8);
                                        break;
                                    default:
                                        this.this$0.appInfoPill.requireViewById(R.id.collapse_menu_button).sendAccessibilityEvent(8);
                                        break;
                                }
                            }
                        }

                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            HandleMenuAnimator handleMenuAnimator2 = HandleMenuAnimator.this;
                            handleMenuAnimator2.appInfoPill.post(new AnonymousClass1(handleMenuAnimator2, 0));
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            handleMenuAnimator.prepareMenuForAnimation();
            List list3 = handleMenuAnimator.animators;
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_X, 0.6f, 1.0f);
            ofFloat3.setDuration(CLOSE_MAXIMIZE_MENU_DELAY_MS);
            list3.add(ofFloat3);
            List list4 = handleMenuAnimator.animators;
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(handleMenuAnimator.appInfoPill, (Property<ViewGroup, Float>) View.SCALE_Y, 0.05f, 1.0f);
            ofFloat4.setDuration(CLOSE_MAXIMIZE_MENU_DELAY_MS);
            list4.add(ofFloat4);
            float f = (-handleMenuAnimator.captionHeight) / 2;
            List list5 = handleMenuAnimator.animators;
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(handleMenuAnimator.handleMenu, (Property<View, Float>) View.TRANSLATION_Y, f, 0.0f);
            ofFloat5.setDuration(CLOSE_MAXIMIZE_MENU_DELAY_MS);
            list5.add(ofFloat5);
            handleMenuAnimator.animateAppInfoPillOpen();
            handleMenuAnimator.animateWindowingPillOpen();
            handleMenuAnimator.animateMoreActionsPillOpen();
            handleMenuAnimator.animateOpenInBrowserPill();
            handleMenuAnimator.runAnimations(new Function0() { // from class: com.android.wm.shell.windowdecor.HandleMenuAnimator$animateCaptionHandleExpandToOpen$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    HandleMenuAnimator handleMenuAnimator2 = HandleMenuAnimator.this;
                    handleMenuAnimator2.appInfoPill.post(new HandleMenuAnimator$animateOpen$1.AnonymousClass1(handleMenuAnimator2, 1));
                    return Unit.INSTANCE;
                }
            });
        }
        DesktopModeStatus.canEnterDesktopMode(this.mContext);
    }

    public final void onMaximizeHoverStateChanged() {
        if (this.mIsMaximizeMenuHovered || this.mIsAppHeaderMaximizeButtonHovered) {
            this.mHandler.removeCallbacks(this.mCloseMaximizeWindowRunnable);
        } else if (isMaximizeMenuActive()) {
            this.mHandler.postDelayed(this.mCloseMaximizeWindowRunnable, CLOSE_MAXIMIZE_MENU_DELAY_MS);
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration
    public final void relayout(ActivityManager.RunningTaskInfo runningTaskInfo) {
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.mSurfaceControlTransactionSupplier.get();
        boolean z = !DesktopModeStatus.IS_VEILED_RESIZE_ENABLED && this.mTaskDragResizer.isResizingOrAnimating();
        boolean isFreeform = runningTaskInfo.isFreeform();
        relayout(runningTaskInfo, transaction, transaction, isFreeform, z);
        if (isFreeform) {
            return;
        }
        transaction.apply();
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecoration
    public final void releaseViews(WindowContainerTransaction windowContainerTransaction) {
        closeHandleMenu();
        closeManageWindowsMenu();
        closeMaximizeMenu();
        super.releaseViews(windowContainerTransaction);
    }

    public final void setAnimatingTaskResizeOrReposition(boolean z) {
        if (this.mRelayoutParams.mLayoutResId == R.layout.desktop_mode_app_handle) {
            return;
        }
        WindowDecorationViewHolder windowDecorationViewHolder = this.mWindowDecorViewHolder;
        MaximizeButtonView maximizeButtonView = (windowDecorationViewHolder instanceof AppHeaderViewHolder ? (AppHeaderViewHolder) windowDecorationViewHolder : null).maximizeButtonView;
        if (z) {
            maximizeButtonView.cancelHoverAnimation();
        }
        maximizeButtonView.hoverDisabled = z;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{mPositionInParent=");
        sb.append(this.mPositionInParent);
        sb.append(", taskId=");
        sb.append(this.mTaskInfo.taskId);
        sb.append(", windowingMode=");
        sb.append(WindowConfiguration.windowingModeToString(this.mTaskInfo.getWindowingMode()));
        sb.append(", isFocused=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mTaskInfo.isFocused, "}");
    }

    public final void updateExclusionRegion() {
        Region region;
        this.mPositionInParent.set(this.mTaskInfo.positionInParent);
        DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.mExclusionRegionListener;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        int i = runningTaskInfo.taskId;
        if (this.mDragResizeListener == null || !isDragResizable(runningTaskInfo)) {
            region = new Region();
        } else {
            DragResizeInputListener.TaskResizeInputEventReceiver taskResizeInputEventReceiver = this.mDragResizeListener.mInputEventReceiver;
            taskResizeInputEventReceiver.getClass();
            region = new Region();
            taskResizeInputEventReceiver.mDragResizeWindowGeometry.union(region);
        }
        int i2 = this.mResult.mWidth;
        int windowingMode = this.mTaskInfo.getWindowingMode();
        region.union(new Rect(0, 0, i2, WindowDecoration.loadDimensionPixelSize(windowingMode == 1 ? R.dimen.desktop_mode_fullscreen_decor_caption_height : R.dimen.desktop_mode_freeform_decor_caption_height, this.mContext.getResources())));
        Point point = this.mPositionInParent;
        region.translate(point.x, point.y);
        DesktopModeTaskRepository desktopModeTaskRepository = DesktopModeWindowDecorViewModel.this.mDesktopTasksController.taskRepository;
        desktopModeTaskRepository.desktopExclusionRegions.put(i, region);
        Executor executor = desktopModeTaskRepository.desktopGestureExclusionExecutor;
        if (executor != null) {
            executor.execute(new DesktopModeTaskRepository.AnonymousClass1(desktopModeTaskRepository, 3));
        }
    }

    public final void updateHoverAndPressStatus(MotionEvent motionEvent) {
        View view = this.mResult.mRootView;
        if (view != null) {
            View findViewById = ((WindowDecorLinearLayout) view).findViewById(R.id.caption_handle);
            boolean z = false;
            boolean z2 = !isHandleMenuActive() && checkTouchEventInFocusedCaptionHandle(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            findViewById.setHovered(z2 && actionMasked != 1);
            if ((z2 && actionMasked == 0) || (findViewById.isPressed() && actionMasked != 1 && actionMasked != 3)) {
                z = true;
            }
            findViewById.setPressed(z);
            if (isHandleMenuActive()) {
                this.mHandleMenu.checkMotionEvent(motionEvent);
            }
        }
    }

    public final void relayout(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, boolean z, boolean z2) {
        int dimensionPixelSize;
        WindowDecorationViewHolder appHeaderViewHolder;
        Trace.beginSection("DesktopModeWindowDecoration#relayout");
        if (isHandleMenuActive()) {
            HandleMenu handleMenu = this.mHandleMenu;
            int i = this.mResult.mCaptionX;
            AdditionalViewHostViewContainer additionalViewHostViewContainer = handleMenu.handleMenuViewContainer;
            if (additionalViewHostViewContainer != null) {
                handleMenu.updateHandleMenuPillPositions(i);
                PointF pointF = handleMenu.handleMenuPosition;
                transaction.setPosition(additionalViewHostViewContainer.windowSurface, pointF.x, pointF.y);
            }
        }
        updateRelayoutParams(this.mRelayoutParams, this.mContext, runningTaskInfo, z, z2);
        WindowDecorLinearLayout windowDecorLinearLayout = (WindowDecorLinearLayout) this.mResult.mRootView;
        SurfaceControl surfaceControl = this.mDecorationContainerSurface;
        final WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        Trace.beginSection("DesktopModeWindowDecoration#relayout-inner");
        relayout(this.mRelayoutParams, transaction, transaction2, windowContainerTransaction, windowDecorLinearLayout, this.mResult);
        Trace.endSection();
        Trace.beginSection("DesktopModeWindowDecoration#relayout-applyWCT");
        ((HandlerExecutor) this.mBgExecutor).execute(new Runnable() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DesktopModeWindowDecoration desktopModeWindowDecoration = DesktopModeWindowDecoration.this;
                desktopModeWindowDecoration.mTaskOrganizer.applyTransaction(windowContainerTransaction);
            }
        });
        Trace.endSection();
        View view = this.mResult.mRootView;
        if (view == null) {
            DesktopModeStatus.canEnterDesktopMode(this.mContext);
            boolean z3 = this.mWindowDecorViewHolder instanceof AppHandleViewHolder;
            Trace.endSection();
            return;
        }
        if (windowDecorLinearLayout != view) {
            boolean z4 = this.mWindowDecorViewHolder instanceof AppHandleViewHolder;
            int i2 = this.mRelayoutParams.mLayoutResId;
            if (i2 == R.layout.desktop_mode_app_handle) {
                appHeaderViewHolder = new AppHandleViewHolder(view, this.mOnCaptionTouchListener, this.mOnCaptionButtonClickListener, this.mWindowManagerWrapper, this.mHandler);
            } else if (i2 == R.layout.desktop_mode_app_header) {
                loadAppInfoIfNeeded();
                AppHeaderViewHolder.Factory factory = this.mAppHeaderViewHolderFactory;
                View view2 = this.mResult.mRootView;
                DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener = this.mOnCaptionTouchListener;
                DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener2 = this.mOnCaptionButtonClickListener;
                DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener3 = this.mOnCaptionLongClickListener;
                DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener4 = this.mOnCaptionGenericMotionListener;
                CharSequence charSequence = this.mAppName;
                Bitmap bitmap = this.mAppIconBitmap;
                DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(this, 7);
                factory.getClass();
                appHeaderViewHolder = new AppHeaderViewHolder(view2, desktopModeTouchEventListener, desktopModeTouchEventListener2, desktopModeTouchEventListener3, desktopModeTouchEventListener4, charSequence, bitmap, desktopModeWindowDecoration$$ExternalSyntheticLambda3);
            } else {
                throw new IllegalArgumentException("Unexpected layout resource id");
            }
            this.mWindowDecorViewHolder = appHeaderViewHolder;
        }
        Point point = new Point();
        if (this.mWindowDecorViewHolder instanceof AppHandleViewHolder) {
            Point point2 = new Point(this.mResult.mCaptionX, 0);
            if (this.mSplitScreenController.getSplitPosition(this.mTaskInfo.taskId) == 1) {
                DisplayLayout displayLayout = this.mDisplayController.getDisplayLayout(this.mTaskInfo.displayId);
                if (displayLayout.mWidth > displayLayout.mHeight) {
                    Rect rect = new Rect();
                    this.mSplitScreenController.getStageBounds(rect, new Rect());
                    point2.x = rect.width() + point2.x;
                }
            }
            point.set(point2);
        }
        Trace.beginSection("DesktopModeWindowDecoration#relayout-bindData");
        DesktopModeStatus.canEnterDesktopMode(this.mContext);
        WindowDecorationViewHolder windowDecorationViewHolder = this.mWindowDecorViewHolder;
        ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mTaskInfo;
        WindowDecoration.RelayoutResult relayoutResult = this.mResult;
        windowDecorationViewHolder.bindData(runningTaskInfo2, point, relayoutResult.mCaptionWidth, relayoutResult.mCaptionHeight, runningTaskInfo2.isVisible && this.mIsCaptionVisible);
        Trace.endSection();
        if (!this.mTaskInfo.isFocused) {
            closeHandleMenu();
            closeManageWindowsMenu();
            closeMaximizeMenu();
        }
        if (!isDragResizable(this.mTaskInfo)) {
            if (!this.mTaskInfo.positionInParent.equals(this.mPositionInParent)) {
                updateExclusionRegion();
            }
            DragResizeInputListener dragResizeInputListener = this.mDragResizeListener;
            if (dragResizeInputListener != null) {
                dragResizeInputListener.close();
                this.mDragResizeListener = null;
            }
        } else {
            if (surfaceControl != this.mDecorationContainerSurface || this.mDragResizeListener == null) {
                DragResizeInputListener dragResizeInputListener2 = this.mDragResizeListener;
                if (dragResizeInputListener2 != null) {
                    dragResizeInputListener2.close();
                    this.mDragResizeListener = null;
                }
                Trace.beginSection("DesktopModeWindowDecoration#relayout-DragResizeInputListener");
                this.mDragResizeListener = new DragResizeInputListener(this.mContext, this.mHandler, this.mChoreographer, this.mDisplay.getDisplayId(), this.mDecorationContainerSurface, this.mDragPositioningCallback, this.mSurfaceControlBuilderSupplier, this.mSurfaceControlTransactionSupplier, this.mDisplayController);
                Trace.endSection();
            }
            int scaledTouchSlop = ViewConfiguration.get(((WindowDecorLinearLayout) this.mResult.mRootView).getContext()).getScaledTouchSlop();
            Resources resources = ((WindowDecorLinearLayout) this.mResult.mRootView).getResources();
            DragResizeInputListener dragResizeInputListener3 = this.mDragResizeListener;
            int i3 = this.mRelayoutParams.mCornerRadius;
            WindowDecoration.RelayoutResult relayoutResult2 = this.mResult;
            Size size = new Size(relayoutResult2.mWidth, relayoutResult2.mHeight);
            if (DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE.isTrue()) {
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.freeform_edge_handle_outset);
            } else {
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.freeform_resize_handle);
            }
            if (dragResizeInputListener3.setGeometry(new DragResizeWindowGeometry(i3, size, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.freeform_edge_handle_inset), resources.getDimensionPixelSize(R.dimen.freeform_resize_corner), resources.getDimensionPixelSize(R.dimen.desktop_mode_corner_resize_large)), scaledTouchSlop) || !this.mTaskInfo.positionInParent.equals(this.mPositionInParent)) {
                updateExclusionRegion();
            }
        }
        if (isDragResizable(this.mTaskInfo) && isMaximizeMenuActive()) {
            if (!this.mTaskInfo.isVisible()) {
                closeMaximizeMenu();
            } else {
                MaximizeMenu maximizeMenu = this.mMaximizeMenu;
                maximizeMenu.menuPosition.set(calculateMaximizeMenuPosition());
                SurfaceControl surfaceControl2 = maximizeMenu.leash;
                SurfaceControl surfaceControl3 = surfaceControl2 != null ? surfaceControl2 : null;
                PointF pointF2 = maximizeMenu.menuPosition;
                transaction.setPosition(surfaceControl3, pointF2.x, pointF2.y);
            }
        }
        Trace.endSection();
    }
}
