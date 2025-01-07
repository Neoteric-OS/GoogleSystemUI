package com.android.wm.shell.draganddrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.R;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.draganddrop.SplitDragPolicy;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.splitscreen.SplitScreenController;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragLayout extends LinearLayout implements ViewTreeObserver.OnComputeInternalInsetsListener, DragLayoutProvider {
    public final boolean mAllowLeftRightSplitInPortrait;
    public SplitDragPolicy.Target mCurrentTarget;
    public final int mDisplayMargin;
    public final int mDividerSize;
    public final DropZoneView mDropZoneView1;
    public final DropZoneView mDropZoneView2;
    public boolean mHasDropped;
    public final IconProvider mIconProvider;
    public Insets mInsets;
    public boolean mIsLeftRightSplit;
    public boolean mIsShowing;
    public final Configuration mLastConfiguration;
    public final Point mLastPosition;
    public final int mLaunchIntentEdgeMargin;
    public final SplitDragPolicy mPolicy;
    public DragSession mSession;
    public final SplitScreenController mSplitScreenController;
    public final StatusBarManager mStatusBarManager;
    public Region mTouchableRegion;

    public DragLayout(Context context, SplitScreenController splitScreenController, IconProvider iconProvider) {
        super(context);
        Configuration configuration = new Configuration();
        this.mLastConfiguration = configuration;
        this.mCurrentTarget = null;
        this.mInsets = Insets.NONE;
        this.mLastPosition = new Point();
        this.mSplitScreenController = splitScreenController;
        this.mIconProvider = iconProvider;
        this.mPolicy = new SplitDragPolicy(context, splitScreenController, new SplitDragPolicy.DefaultStarter(context));
        this.mStatusBarManager = (StatusBarManager) context.getSystemService(StatusBarManager.class);
        configuration.setTo(context.getResources().getConfiguration());
        Resources resources = context.getResources();
        this.mDisplayMargin = resources.getDimensionPixelSize(R.dimen.drop_layout_display_margin);
        this.mDividerSize = resources.getDimensionPixelSize(R.dimen.split_divider_bar_width);
        this.mLaunchIntentEdgeMargin = resources.getDimensionPixelSize(R.dimen.drag_launchable_intent_edge_margin);
        setLayoutDirection(0);
        DropZoneView dropZoneView = new DropZoneView(context);
        this.mDropZoneView1 = dropZoneView;
        DropZoneView dropZoneView2 = new DropZoneView(context);
        this.mDropZoneView2 = dropZoneView2;
        addView(dropZoneView, new LinearLayout.LayoutParams(-1, -1));
        addView(dropZoneView2, new LinearLayout.LayoutParams(-1, -1));
        ((LinearLayout.LayoutParams) dropZoneView.getLayoutParams()).weight = 1.0f;
        ((LinearLayout.LayoutParams) dropZoneView2.getLayoutParams()).weight = 1.0f;
        boolean z = context.getResources().getBoolean(android.R.bool.config_letterboxIsVerticalReachabilityEnabled);
        this.mAllowLeftRightSplitInPortrait = z;
        boolean isLeftRightSplit = SplitScreenUtils.isLeftRightSplit(z, getResources().getConfiguration());
        this.mIsLeftRightSplit = isLeftRightSplit;
        setOrientation(!isLeftRightSplit ? 1 : 0);
        updateContainerMargins(this.mIsLeftRightSplit);
    }

    public static int getResizingBackgroundColor(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int backgroundColor = runningTaskInfo.taskDescription.getBackgroundColor();
        if (backgroundColor == -1) {
            backgroundColor = -1;
        }
        return Color.valueOf(backgroundColor).toArgb();
    }

    public final void animateSplitContainers(boolean z, final DragLayout$$ExternalSyntheticLambda0 dragLayout$$ExternalSyntheticLambda0) {
        ObjectAnimator objectAnimator;
        this.mStatusBarManager.disable(z ? 9830400 : 0);
        this.mDropZoneView1.setShowingMargin(z);
        this.mDropZoneView2.setShowingMargin(z);
        DropZoneView dropZoneView = this.mDropZoneView1;
        ObjectAnimator objectAnimator2 = dropZoneView.mMarginAnimator;
        if (objectAnimator2 == null || !objectAnimator2.isRunning()) {
            ObjectAnimator objectAnimator3 = dropZoneView.mBackgroundAnimator;
            objectAnimator = (objectAnimator3 == null || !objectAnimator3.isRunning()) ? null : dropZoneView.mBackgroundAnimator;
        } else {
            objectAnimator = dropZoneView.mMarginAnimator;
        }
        if (dragLayout$$ExternalSyntheticLambda0 != null) {
            if (objectAnimator != null) {
                objectAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.draganddrop.DragLayout.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        DragLayout$$ExternalSyntheticLambda0.this.run();
                    }
                });
            } else {
                dragLayout$$ExternalSyntheticLambda0.run();
            }
        }
    }

    public final void hide(DragEvent dragEvent, Runnable runnable) {
        this.mIsShowing = false;
        this.mLastPosition.set(-1, -1);
        animateSplitContainers(false, new DragLayout$$ExternalSyntheticLambda0(this, runnable, dragEvent));
        this.mDropZoneView1.setForceIgnoreBottomMargin(false);
        this.mDropZoneView2.setForceIgnoreBottomMargin(false);
        updateContainerMargins(this.mIsLeftRightSplit);
        this.mCurrentTarget = null;
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this.mInsets = windowInsets.getInsets(WindowInsets.Type.tappableElement() | WindowInsets.Type.displayCutout());
        recomputeDropTargets();
        SplitScreenController splitScreenController = this.mSplitScreenController;
        if (splitScreenController == null || !splitScreenController.isLeftRightSplit()) {
            this.mDropZoneView1.setBottomInset(0.0f);
            this.mDropZoneView2.setBottomInset(this.mInsets.bottom);
        } else {
            this.mDropZoneView1.setBottomInset(this.mInsets.bottom);
            this.mDropZoneView2.setBottomInset(this.mInsets.bottom);
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mTouchableRegion = Region.obtain();
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        DragSession dragSession = this.mSession;
        if (dragSession == null || dragSession.launchableIntent == null) {
            return;
        }
        internalInsetsInfo.touchableRegion.set(this.mTouchableRegion);
        internalInsetsInfo.setTouchableInsets(3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        this.mTouchableRegion.recycle();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mTouchableRegion.setEmpty();
        DragSession dragSession = this.mSession;
        if (dragSession == null || dragSession.launchableIntent == null) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.mIsLeftRightSplit) {
            this.mTouchableRegion.union(new Rect(0, 0, this.mInsets.left + this.mLaunchIntentEdgeMargin, measuredHeight));
            this.mTouchableRegion.union(new Rect((measuredWidth - this.mInsets.right) - this.mLaunchIntentEdgeMargin, 0, measuredWidth, measuredHeight));
        } else {
            this.mTouchableRegion.union(new Rect(0, 0, measuredWidth, this.mInsets.top + this.mLaunchIntentEdgeMargin));
            this.mTouchableRegion.union(new Rect(0, (measuredHeight - this.mInsets.bottom) - this.mLaunchIntentEdgeMargin, measuredWidth, measuredHeight));
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 1623513373423843019L, 5, Long.valueOf(measuredWidth), Long.valueOf(measuredHeight), String.valueOf(this.mTouchableRegion));
        }
        requestApplyInsets();
    }

    public final void recomputeDropTargets() {
        ArrayList arrayList;
        if (this.mIsShowing) {
            SplitDragPolicy splitDragPolicy = this.mPolicy;
            Insets insets = this.mInsets;
            splitDragPolicy.mTargets.clear();
            DragSession dragSession = splitDragPolicy.mSession;
            if (dragSession == null) {
                arrayList = splitDragPolicy.mTargets;
            } else {
                DisplayLayout displayLayout = dragSession.displayLayout;
                int i = displayLayout.mWidth;
                int i2 = displayLayout.mHeight;
                int i3 = insets.left;
                int i4 = (i - i3) - insets.right;
                int i5 = insets.top;
                Rect rect = new Rect(i3, i5, i4 + i3, ((i2 - i5) - insets.bottom) + i5);
                Rect rect2 = new Rect(rect);
                Rect rect3 = new Rect(rect);
                SplitScreenController splitScreenController = splitDragPolicy.mSplitScreen;
                boolean z = splitScreenController != null && splitScreenController.isLeftRightSplit();
                boolean z2 = splitScreenController != null && splitScreenController.isSplitScreenVisible();
                float dimensionPixelSize = splitDragPolicy.mContext.getResources().getDimensionPixelSize(R.dimen.split_divider_bar_width);
                if (!z2) {
                    DragSession dragSession2 = splitDragPolicy.mSession;
                    if (dragSession2.runningTaskActType != 1 || dragSession2.runningTaskWinMode != 1) {
                        splitDragPolicy.mTargets.add(new SplitDragPolicy.Target(0, rect3, rect2));
                        arrayList = splitDragPolicy.mTargets;
                    }
                }
                Rect rect4 = new Rect();
                Rect rect5 = new Rect();
                splitScreenController.getStageBounds(rect4, rect5);
                rect4.intersect(rect);
                rect5.intersect(rect);
                if (z) {
                    Rect rect6 = new Rect();
                    Rect rect7 = new Rect();
                    if (z2) {
                        rect6.set(rect);
                        int i6 = (int) ((dimensionPixelSize / 2.0f) + rect4.right);
                        rect6.right = i6;
                        rect7.set(rect);
                        rect7.left = i6;
                    } else {
                        rect.splitVertically(new Rect[]{rect6, rect7});
                    }
                    splitDragPolicy.mTargets.add(new SplitDragPolicy.Target(1, rect6, rect4));
                    splitDragPolicy.mTargets.add(new SplitDragPolicy.Target(3, rect7, rect5));
                } else {
                    Rect rect8 = new Rect();
                    Rect rect9 = new Rect();
                    if (z2) {
                        rect8.set(rect);
                        int i7 = (int) ((dimensionPixelSize / 2.0f) + rect4.bottom);
                        rect8.bottom = i7;
                        rect9.set(rect);
                        rect9.top = i7;
                    } else {
                        rect.splitHorizontally(new Rect[]{rect8, rect9});
                    }
                    splitDragPolicy.mTargets.add(new SplitDragPolicy.Target(2, rect8, rect4));
                    splitDragPolicy.mTargets.add(new SplitDragPolicy.Target(4, rect9, rect5));
                }
                arrayList = splitDragPolicy.mTargets;
            }
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                SplitDragPolicy.Target target = (SplitDragPolicy.Target) arrayList.get(i8);
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -4402086228976389419L, 0, String.valueOf(target));
                }
                Rect rect10 = target.drawRegion;
                int i9 = this.mDisplayMargin;
                rect10.inset(i9, i9);
            }
        }
    }

    public final void update(int i, int i2) {
        SplitDragPolicy.Target target;
        if (this.mHasDropped) {
            return;
        }
        SplitDragPolicy splitDragPolicy = this.mPolicy;
        if (!splitDragPolicy.mDisallowHitRegion.contains(i, i2)) {
            for (int size = splitDragPolicy.mTargets.size() - 1; size >= 0; size--) {
                target = (SplitDragPolicy.Target) splitDragPolicy.mTargets.get(size);
                if (target.hitRegion.contains(i, i2)) {
                    break;
                }
            }
        }
        target = null;
        if (this.mCurrentTarget != target) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 7852787567981310676L, 0, String.valueOf(target));
            }
            if (target == null) {
                animateSplitContainers(false, null);
            } else {
                SplitDragPolicy.Target target2 = this.mCurrentTarget;
                int i3 = target.type;
                if (target2 == null) {
                    if (this.mPolicy.mTargets.size() == 1) {
                        this.mStatusBarManager.disable(9830400);
                        this.mDropZoneView1.setShowingMargin(true);
                        this.mDropZoneView1.setShowingHighlight(true);
                    } else {
                        animateSplitContainers(true, null);
                        if (i3 == 1 || i3 == 2) {
                            this.mDropZoneView1.setShowingHighlight(true);
                            this.mDropZoneView2.setShowingHighlight(false);
                        } else if (i3 == 3 || i3 == 4) {
                            this.mDropZoneView1.setShowingHighlight(false);
                            this.mDropZoneView2.setShowingHighlight(true);
                        }
                    }
                } else if (target2.type != i3) {
                    this.mDropZoneView1.animateSwitch();
                    this.mDropZoneView2.animateSwitch();
                    if (i3 == 1) {
                        this.mDropZoneView1.announceForAccessibility(((LinearLayout) this).mContext.getString(R.string.accessibility_split_left));
                    } else if (i3 == 2) {
                        this.mDropZoneView1.announceForAccessibility(((LinearLayout) this).mContext.getString(R.string.accessibility_split_top));
                    } else if (i3 == 3) {
                        this.mDropZoneView2.announceForAccessibility(((LinearLayout) this).mContext.getString(R.string.accessibility_split_right));
                    } else if (i3 == 4) {
                        this.mDropZoneView2.announceForAccessibility(((LinearLayout) this).mContext.getString(R.string.accessibility_split_bottom));
                    }
                }
            }
            this.mCurrentTarget = target;
        }
        this.mLastPosition.set(i, i2);
    }

    public final void updateContainerMargins(boolean z) {
        int i = this.mDisplayMargin;
        float f = i / 2.0f;
        if (z) {
            this.mDropZoneView1.setContainerMargin(i, i, f, i);
            DropZoneView dropZoneView = this.mDropZoneView2;
            int i2 = this.mDisplayMargin;
            dropZoneView.setContainerMargin(f, i2, i2, i2);
            return;
        }
        this.mDropZoneView1.setContainerMargin(i, i, i, f);
        DropZoneView dropZoneView2 = this.mDropZoneView2;
        int i3 = this.mDisplayMargin;
        dropZoneView2.setContainerMargin(i3, f, i3, i3);
    }

    public final void updateDropZoneSizes(Rect rect, Rect rect2) {
        int i = this.mDividerSize / 2;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mDropZoneView1.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mDropZoneView2.getLayoutParams();
        if (this.mIsLeftRightSplit) {
            layoutParams.width = rect != null ? rect.width() + i : -1;
            layoutParams2.width = rect2 != null ? rect2.width() + i : -1;
            layoutParams.height = -1;
            layoutParams2.height = -1;
        } else {
            layoutParams.width = -1;
            layoutParams2.width = -1;
            layoutParams.height = rect != null ? rect.height() + i : -1;
            layoutParams2.height = rect2 != null ? rect2.height() + i : -1;
        }
        layoutParams.weight = rect != null ? 0.0f : 1.0f;
        layoutParams2.weight = rect2 != null ? 0.0f : 1.0f;
        this.mDropZoneView1.setLayoutParams(layoutParams);
        this.mDropZoneView2.setLayoutParams(layoutParams2);
    }

    public final void updateSession(DragSession dragSession) {
        boolean z = this.mSession != null;
        this.mSession = dragSession;
        this.mHasDropped = false;
        this.mCurrentTarget = null;
        SplitScreenController splitScreenController = this.mSplitScreenController;
        if (splitScreenController == null || !splitScreenController.isSplitScreenVisible()) {
            ActivityManager.RunningTaskInfo runningTaskInfo = this.mSession.runningTaskInfo;
            if (runningTaskInfo != null) {
                if (runningTaskInfo.getActivityType() == 1) {
                    Drawable icon = this.mIconProvider.getIcon(runningTaskInfo.topActivityInfo);
                    int resizingBackgroundColor = getResizingBackgroundColor(runningTaskInfo);
                    this.mDropZoneView1.setAppInfo(resizingBackgroundColor, icon);
                    this.mDropZoneView2.setAppInfo(resizingBackgroundColor, icon);
                    this.mDropZoneView1.setForceIgnoreBottomMargin(false);
                    this.mDropZoneView2.setForceIgnoreBottomMargin(false);
                    updateDropZoneSizes(null, null);
                } else {
                    this.mDropZoneView1.setForceIgnoreBottomMargin(true);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mDropZoneView1.getLayoutParams();
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mDropZoneView2.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    layoutParams2.width = 0;
                    layoutParams2.height = 0;
                    layoutParams.weight = 1.0f;
                    layoutParams2.weight = 0.0f;
                    this.mDropZoneView1.setLayoutParams(layoutParams);
                    this.mDropZoneView2.setLayoutParams(layoutParams2);
                    DropZoneView dropZoneView = this.mDropZoneView1;
                    float f = this.mDisplayMargin;
                    dropZoneView.setContainerMargin(f, f, f, f);
                    this.mDropZoneView2.setContainerMargin(0.0f, 0.0f, 0.0f, 0.0f);
                }
            }
        } else {
            ActivityManager.RunningTaskInfo taskInfo = this.mSplitScreenController.getTaskInfo(0);
            ActivityManager.RunningTaskInfo taskInfo2 = this.mSplitScreenController.getTaskInfo(1);
            if (taskInfo != null && taskInfo2 != null) {
                Drawable icon2 = this.mIconProvider.getIcon(taskInfo.topActivityInfo);
                int resizingBackgroundColor2 = getResizingBackgroundColor(taskInfo);
                Drawable icon3 = this.mIconProvider.getIcon(taskInfo2.topActivityInfo);
                int resizingBackgroundColor3 = getResizingBackgroundColor(taskInfo2);
                this.mDropZoneView1.setAppInfo(resizingBackgroundColor2, icon2);
                this.mDropZoneView2.setAppInfo(resizingBackgroundColor3, icon3);
            }
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            this.mSplitScreenController.getStageBounds(rect, rect2);
            updateDropZoneSizes(rect, rect2);
        }
        requestLayout();
        if (z) {
            recomputeDropTargets();
            Point point = this.mLastPosition;
            update(point.x, point.y);
        }
    }
}
