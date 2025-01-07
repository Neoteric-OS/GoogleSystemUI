package com.android.wm.shell.pip.phone;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Size;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PhoneSizeSpecSource;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipSnapAlgorithm;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.sysui.ShellInit;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTouchHandler {
    public final AccessibilityManager mAccessibilityManager;
    public int mBottomOffsetBufferPx;
    public final PipAccessibilityInteractionConnection mConnection;
    public final Context mContext;
    public int mDisplayRotation;
    public boolean mEnableResize;
    public final FloatingContentCoordinator mFloatingContentCoordinator;
    public final DefaultPipTouchGesture mGesture;
    public int mImeHeight;
    public int mImeOffset;
    public boolean mIsImeShowing;
    public boolean mIsShelfShowing;
    public final ShellExecutor mMainExecutor;
    public final PhonePipMenuController mMenuController;
    public PipMotionHelper mMotionHelper;
    public int mMovementBoundsExtraOffsets;
    public boolean mMovementWithinDismiss;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDismissTargetHandler mPipDismissTargetHandler;
    public final PipPerfHintController mPipPerfHintController;
    public PipResizeGestureHandler mPipResizeGestureHandler;
    public final PipTaskOrganizer mPipTaskOrganizer;
    public final PipUiEventLogger mPipUiEventLogger;
    public boolean mSendingHoverAccessibilityEvents;
    public int mShelfHeight;
    public final PhoneSizeSpecSource mSizeSpecSource;
    public float mStashVelocityThreshold;
    public final PipTouchState mTouchState;
    public boolean mEnableStash = true;
    public final Rect mInsetBounds = new Rect();
    public int mDeferResizeToNormalBoundsUntilRotation = -1;
    public int mMenuState = 0;
    public float mSavedSnapFraction = -1.0f;
    public final Rect mTmpBounds = new Rect();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultPipTouchGesture {
        public PipPerfHintController.PipHighPerfSession mPipHighPerfSession;
        public boolean mShouldHideMenuAfterFling;
        public final Point mStartPosition = new Point();
        public final PointF mDelta = new PointF();

        public DefaultPipTouchGesture() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipMenuListener {
        public PipMenuListener() {
        }
    }

    public PipTouchHandler(Context context, ShellInit shellInit, PhonePipMenuController phonePipMenuController, PipBoundsAlgorithm pipBoundsAlgorithm, PipBoundsState pipBoundsState, PhoneSizeSpecSource phoneSizeSpecSource, PipTaskOrganizer pipTaskOrganizer, PipMotionHelper pipMotionHelper, FloatingContentCoordinator floatingContentCoordinator, PipUiEventLogger pipUiEventLogger, ShellExecutor shellExecutor, Optional optional) {
        this.mContext = context;
        this.mMainExecutor = shellExecutor;
        PipPerfHintController pipPerfHintController = (PipPerfHintController) optional.orElse(null);
        this.mPipPerfHintController = pipPerfHintController;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipBoundsState = pipBoundsState;
        this.mSizeSpecSource = phoneSizeSpecSource;
        this.mPipTaskOrganizer = pipTaskOrganizer;
        this.mMenuController = phonePipMenuController;
        this.mPipUiEventLogger = pipUiEventLogger;
        this.mFloatingContentCoordinator = floatingContentCoordinator;
        PipMenuListener pipMenuListener = new PipMenuListener();
        if (!phonePipMenuController.mListeners.contains(pipMenuListener)) {
            phonePipMenuController.mListeners.add(pipMenuListener);
        }
        this.mGesture = new DefaultPipTouchGesture();
        this.mMotionHelper = pipMotionHelper;
        PipDismissTargetHandler pipDismissTargetHandler = new PipDismissTargetHandler(context, pipUiEventLogger, pipMotionHelper, shellExecutor);
        this.mPipDismissTargetHandler = pipDismissTargetHandler;
        PipTouchState pipTouchState = new PipTouchState(ViewConfiguration.get(context), new PipTouchHandler$$ExternalSyntheticLambda0(this, 0), new PipTouchHandler$$ExternalSyntheticLambda1(0, phonePipMenuController), shellExecutor);
        this.mTouchState = pipTouchState;
        this.mPipResizeGestureHandler = new PipResizeGestureHandler(context, pipBoundsAlgorithm, pipBoundsState, this.mMotionHelper, pipTouchState, pipTaskOrganizer, pipDismissTargetHandler, new PipTouchHandler$$ExternalSyntheticLambda0(this, 1), pipUiEventLogger, phonePipMenuController, shellExecutor, pipPerfHintController);
        this.mConnection = new PipAccessibilityInteractionConnection(context, pipBoundsState, this.mMotionHelper, pipTaskOrganizer, pipBoundsAlgorithm.mSnapAlgorithm, new PipTouchHandler$$ExternalSyntheticLambda3(this), new PipTouchHandler$$ExternalSyntheticLambda0(this, 1), new PipTouchHandler$$ExternalSyntheticLambda0(this, 2), shellExecutor);
        PipTouchHandler$$ExternalSyntheticLambda5 pipTouchHandler$$ExternalSyntheticLambda5 = new PipTouchHandler$$ExternalSyntheticLambda5(0, this);
        if (!pipBoundsState.mOnAspectRatioChangedCallbacks.contains(pipTouchHandler$$ExternalSyntheticLambda5)) {
            pipBoundsState.mOnAspectRatioChangedCallbacks.add(pipTouchHandler$$ExternalSyntheticLambda5);
            pipTouchHandler$$ExternalSyntheticLambda5.accept(Float.valueOf(pipBoundsState.mAspectRatio));
        }
        if (PipUtils.isPip2ExperimentEnabled()) {
            return;
        }
        shellInit.addInitCallback(new PipTouchHandler$$ExternalSyntheticLambda0(this, 3), this);
    }

    public final void animateToNormalSize(PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda0) {
        PipResizeGestureHandler pipResizeGestureHandler = this.mPipResizeGestureHandler;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        pipResizeGestureHandler.setUserResizeBounds(pipBoundsState.getBounds());
        Rect adjustNormalBoundsToFitMenu = this.mPipBoundsAlgorithm.adjustNormalBoundsToFitMenu(pipBoundsState.mNormalBounds, this.mMenuController.getEstimatedMinMenuSize());
        Rect rect = new Rect();
        PipBoundsAlgorithm.getMovementBounds(adjustNormalBoundsToFitMenu, this.mInsetBounds, rect, this.mIsImeShowing ? this.mImeHeight : 0);
        PipMotionHelper pipMotionHelper = this.mMotionHelper;
        Rect rect2 = pipBoundsState.mMovementBounds;
        pipMotionHelper.getClass();
        float snapFraction = pipMotionHelper.mSnapAlgorithm.getSnapFraction(0, new Rect(pipMotionHelper.mPipBoundsState.getBounds()), rect2);
        PipSnapAlgorithm.applySnapFraction(adjustNormalBoundsToFitMenu, rect, snapFraction);
        pipMotionHelper.mPostPipTransitionCallback = pipMenuView$$ExternalSyntheticLambda0;
        pipMotionHelper.resizeAndAnimatePipUnchecked$1(adjustNormalBoundsToFitMenu);
        this.mSavedSnapFraction = snapFraction;
    }

    public final void animateToUnStashedState() {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        Rect bounds = pipBoundsState.getBounds();
        boolean z = bounds.left < pipBoundsState.mPipDisplayLayoutState.getDisplayBounds().left;
        Rect rect = new Rect(0, bounds.top, 0, bounds.bottom);
        rect.left = z ? this.mInsetBounds.left : this.mInsetBounds.right - bounds.width();
        rect.right = z ? bounds.width() + this.mInsetBounds.left : this.mInsetBounds.right;
        this.mMotionHelper.resizeAndAnimatePipUnchecked$1(rect);
    }

    public final void animateToUnexpandedState(Rect rect) {
        Rect rect2 = new Rect();
        Rect rect3 = this.mInsetBounds;
        int i = this.mIsImeShowing ? this.mImeHeight : 0;
        this.mPipBoundsAlgorithm.getClass();
        PipBoundsAlgorithm.getMovementBounds(rect, rect3, rect2, i);
        this.mMotionHelper.animateToUnexpandedState(rect, this.mSavedSnapFraction, rect2, this.mPipBoundsState.mMovementBounds, false);
        this.mSavedSnapFraction = -1.0f;
    }

    public PipResizeGestureHandler getPipResizeGestureHandler() {
        return this.mPipResizeGestureHandler;
    }

    public final Rect getPossiblyMotionBounds() {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        return pipBoundsState.mMotionBoundsState.isInMotion() ? pipBoundsState.mMotionBoundsState.mBoundsInMotion : pipBoundsState.getBounds();
    }

    public final void onRegistrationChanged(boolean z) {
        if (z) {
            this.mAccessibilityManager.setPictureInPictureActionReplacingConnection(this.mConnection.mConnectionImpl);
        } else {
            this.mAccessibilityManager.setPictureInPictureActionReplacingConnection(null);
        }
        if (z || !this.mTouchState.mIsUserInteracting) {
            return;
        }
        PipDismissTargetHandler pipDismissTargetHandler = this.mPipDismissTargetHandler;
        if (pipDismissTargetHandler.mTargetViewContainer.getParent() != null) {
            pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
        }
    }

    public final void sendAccessibilityHoverEvent(int i) {
        if (this.mAccessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
            obtain.setImportantForAccessibility(true);
            obtain.setSourceNodeId(AccessibilityNodeInfo.ROOT_NODE_ID);
            obtain.setWindowId(-3);
            this.mAccessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    public void setPipMotionHelper(PipMotionHelper pipMotionHelper) {
        this.mMotionHelper = pipMotionHelper;
    }

    public void setPipResizeGestureHandler(PipResizeGestureHandler pipResizeGestureHandler) {
        this.mPipResizeGestureHandler = pipResizeGestureHandler;
    }

    public final void updateMovementBounds() {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        Rect bounds = pipBoundsState.getBounds();
        Rect rect = this.mInsetBounds;
        Rect rect2 = pipBoundsState.mMovementBounds;
        int i = this.mIsImeShowing ? this.mImeHeight : 0;
        this.mPipBoundsAlgorithm.getClass();
        PipBoundsAlgorithm.getMovementBounds(bounds, rect, rect2, i);
        PipMotionHelper pipMotionHelper = this.mMotionHelper;
        PipBoundsState pipBoundsState2 = pipMotionHelper.mPipBoundsState;
        Rect rect3 = pipBoundsState2.mMovementBounds;
        pipMotionHelper.mFlingConfigX = new PhysicsAnimator.FlingConfig(1.9f, rect3.left, rect3.right);
        pipMotionHelper.mFlingConfigY = new PhysicsAnimator.FlingConfig(1.9f, rect3.top, rect3.bottom);
        Rect rect4 = pipBoundsState2.mPipDisplayLayoutState.getDisplayLayout().mStableInsets;
        pipMotionHelper.mStashConfigX = new PhysicsAnimator.FlingConfig(1.9f, (pipBoundsState2.mStashOffset - pipBoundsState2.getBounds().width()) + rect4.left, (r0.getDisplayBounds().right - pipBoundsState2.mStashOffset) - rect4.right);
        pipMotionHelper.mFloatingAllowedArea.set(pipBoundsState2.mMovementBounds);
        Rect rect5 = pipMotionHelper.mFloatingAllowedArea;
        rect5.right = pipBoundsState2.getBounds().width() + rect5.right;
        Rect rect6 = pipMotionHelper.mFloatingAllowedArea;
        rect6.bottom = pipBoundsState2.getBounds().height() + rect6.bottom;
    }

    public final void updatePipSizeConstraints(Rect rect, float f) {
        PipResizeGestureHandler pipResizeGestureHandler = this.mPipResizeGestureHandler;
        boolean z = pipResizeGestureHandler.mEnablePinchResize;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (!z) {
            pipResizeGestureHandler.updateMinSize(rect.width(), rect.height());
            this.mPipResizeGestureHandler.updateMaxSize(pipBoundsState.mExpandedBounds.width(), pipBoundsState.mExpandedBounds.height());
            return;
        }
        pipBoundsState.updateMinMaxSize(f);
        PipResizeGestureHandler pipResizeGestureHandler2 = this.mPipResizeGestureHandler;
        Point point = pipBoundsState.mMinSize;
        pipResizeGestureHandler2.updateMinSize(point.x, point.y);
        PipResizeGestureHandler pipResizeGestureHandler3 = this.mPipResizeGestureHandler;
        Point point2 = pipBoundsState.mMaxSize;
        pipResizeGestureHandler3.updateMaxSize(point2.x, point2.y);
    }

    public final boolean willResizeMenu() {
        if (!this.mEnableResize) {
            return false;
        }
        Size estimatedMinMenuSize = this.mMenuController.getEstimatedMinMenuSize();
        if (estimatedMinMenuSize != null) {
            Rect bounds = this.mPipBoundsState.getBounds();
            return bounds.width() < estimatedMinMenuSize.getWidth() || bounds.height() < estimatedMinMenuSize.getHeight();
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[5]) {
            ProtoLogImpl_411527699.wtf(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 4319316146978633493L, 0, "PipTouchHandler");
        }
        return false;
    }
}
