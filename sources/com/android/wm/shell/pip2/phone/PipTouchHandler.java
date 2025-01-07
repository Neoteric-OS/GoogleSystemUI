package com.android.wm.shell.pip2.phone;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Size;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PhoneSizeSpecSource;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip2.phone.PipInputConsumer.InputEventReceiver;
import com.android.wm.shell.pip2.phone.PipTransitionState;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellInit;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTouchHandler implements PipTransitionState.PipTransitionStateChangedListener {
    public static final long PIP_KEEP_CLEAR_AREAS_DELAY = SystemProperties.getLong("persist.wm.debug.pip_keep_clear_areas_delay", 200);
    public final AccessibilityManager mAccessibilityManager;
    public final Context mContext;
    public boolean mEnableResize;
    public final FloatingContentCoordinator mFloatingContentCoordinator;
    public final DefaultPipTouchGesture mGesture;
    public int mImeHeight;
    public boolean mIsImeShowing;
    public boolean mIsShelfShowing;
    public final ShellExecutor mMainExecutor;
    public final PhonePipMenuController mMenuController;
    public PipMotionHelper mMotionHelper;
    public final PipTouchHandler$$ExternalSyntheticLambda2 mMoveOnShelVisibilityChanged;
    public boolean mMovementWithinDismiss;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDismissTargetHandler mPipDismissTargetHandler;
    public PipInputConsumer mPipInputConsumer;
    public final PipPerfHintController mPipPerfHintController;
    public PipResizeGestureHandler mPipResizeGestureHandler;
    public final PipTransitionState mPipTransitionState;
    public final PipUiEventLogger mPipUiEventLogger;
    public boolean mSendingHoverAccessibilityEvents;
    public int mShelfHeight;
    public final ShellCommandHandler mShellCommandHandler;
    public final PhoneSizeSpecSource mSizeSpecSource;
    public float mStashVelocityThreshold;
    public final PipTouchState mTouchState;
    public boolean mEnableStash = true;
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

    public PipTouchHandler(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, PhonePipMenuController phonePipMenuController, PipBoundsAlgorithm pipBoundsAlgorithm, PipBoundsState pipBoundsState, PipTransitionState pipTransitionState, PipScheduler pipScheduler, PhoneSizeSpecSource phoneSizeSpecSource, PipMotionHelper pipMotionHelper, FloatingContentCoordinator floatingContentCoordinator, PipUiEventLogger pipUiEventLogger, ShellExecutor shellExecutor, Optional optional) {
        this.mContext = context;
        this.mShellCommandHandler = shellCommandHandler;
        this.mMainExecutor = shellExecutor;
        PipPerfHintController pipPerfHintController = (PipPerfHintController) optional.orElse(null);
        this.mPipPerfHintController = pipPerfHintController;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipBoundsState = pipBoundsState;
        this.mPipTransitionState = pipTransitionState;
        pipTransitionState.addPipTransitionStateChangedListener(new PipTransitionState.PipTransitionStateChangedListener() { // from class: com.android.wm.shell.pip2.phone.PipTouchHandler$$ExternalSyntheticLambda1
            @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
            public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
                PipTouchHandler.this.onPipTransitionStateChanged(i, i2, bundle);
            }
        });
        this.mSizeSpecSource = phoneSizeSpecSource;
        this.mMenuController = phonePipMenuController;
        this.mPipUiEventLogger = pipUiEventLogger;
        this.mFloatingContentCoordinator = floatingContentCoordinator;
        PipMenuListener pipMenuListener = new PipMenuListener();
        if (!phonePipMenuController.mListeners.contains(pipMenuListener)) {
            phonePipMenuController.mListeners.add(pipMenuListener);
        }
        this.mGesture = new DefaultPipTouchGesture();
        this.mMotionHelper = pipMotionHelper;
        pipScheduler.mUpdateMovementBoundsRunnable = new PipTouchHandler$$ExternalSyntheticLambda2(this, 0);
        this.mPipDismissTargetHandler = new PipDismissTargetHandler(context, pipUiEventLogger, pipMotionHelper, shellExecutor);
        PipTouchState pipTouchState = new PipTouchState(ViewConfiguration.get(context), new PipTouchHandler$$ExternalSyntheticLambda2(this, 1), new PipTouchHandler$$ExternalSyntheticLambda4(0, phonePipMenuController), shellExecutor);
        this.mTouchState = pipTouchState;
        this.mPipResizeGestureHandler = new PipResizeGestureHandler(context, pipBoundsAlgorithm, pipBoundsState, pipTouchState, pipScheduler, pipTransitionState, pipUiEventLogger, phonePipMenuController, shellExecutor, pipPerfHintController);
        PipTouchHandler$$ExternalSyntheticLambda5 pipTouchHandler$$ExternalSyntheticLambda5 = new PipTouchHandler$$ExternalSyntheticLambda5(0, this);
        if (!pipBoundsState.mOnAspectRatioChangedCallbacks.contains(pipTouchHandler$$ExternalSyntheticLambda5)) {
            pipBoundsState.mOnAspectRatioChangedCallbacks.add(pipTouchHandler$$ExternalSyntheticLambda5);
            pipTouchHandler$$ExternalSyntheticLambda5.accept(Float.valueOf(pipBoundsState.mAspectRatio));
        }
        this.mMoveOnShelVisibilityChanged = new PipTouchHandler$$ExternalSyntheticLambda2(this, 2);
        if (PipUtils.isPip2ExperimentEnabled()) {
            shellInit.addInitCallback(new PipTouchHandler$$ExternalSyntheticLambda2(this, 3), this);
        }
    }

    public final Rect getMovementBounds(Rect rect) {
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        this.mPipBoundsAlgorithm.getInsetBounds(rect3);
        PipBoundsAlgorithm.getMovementBounds(rect, rect3, rect2, this.mIsImeShowing ? this.mImeHeight : 0);
        return rect2;
    }

    public PipResizeGestureHandler getPipResizeGestureHandler() {
        return this.mPipResizeGestureHandler;
    }

    public final Rect getPossiblyMotionBounds() {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        return pipBoundsState.mMotionBoundsState.isInMotion() ? pipBoundsState.mMotionBoundsState.mBoundsInMotion : pipBoundsState.getBounds();
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        FloatingContentCoordinator floatingContentCoordinator = this.mFloatingContentCoordinator;
        PipDismissTargetHandler pipDismissTargetHandler = this.mPipDismissTargetHandler;
        PipTouchState pipTouchState = this.mTouchState;
        if (i2 == 3) {
            pipDismissTargetHandler.createOrUpdateDismissTarget();
            PipResizeGestureHandler pipResizeGestureHandler = this.mPipResizeGestureHandler;
            pipResizeGestureHandler.mIsAttached = true;
            pipResizeGestureHandler.updateIsEnabled();
            PipMotionHelper pipMotionHelper = this.mMotionHelper;
            floatingContentCoordinator.updateContentBounds();
            floatingContentCoordinator.allContentBounds.put(pipMotionHelper, pipMotionHelper.getFloatingBoundsOnScreen());
            floatingContentCoordinator.maybeMoveConflictingContent(pipMotionHelper);
            final PipInputConsumer pipInputConsumer = this.mPipInputConsumer;
            if (pipInputConsumer.mInputEventReceiver == null) {
                final InputChannel inputChannel = new InputChannel();
                try {
                    pipInputConsumer.mWindowManager.destroyInputConsumer(pipInputConsumer.mToken, 0);
                    pipInputConsumer.mWindowManager.createInputConsumer(pipInputConsumer.mToken, pipInputConsumer.mName, 0, inputChannel);
                } catch (RemoteException e) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                        ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2700829650412097031L, 0, "PipInputConsumer", String.valueOf(e));
                    }
                }
                ((HandlerExecutor) pipInputConsumer.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip2.phone.PipInputConsumer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PipInputConsumer pipInputConsumer2 = PipInputConsumer.this;
                        InputChannel inputChannel2 = inputChannel;
                        pipInputConsumer2.getClass();
                        pipInputConsumer2.mInputEventReceiver = pipInputConsumer2.new InputEventReceiver(inputChannel2, Looper.myLooper(), Choreographer.getInstance());
                        PipTouchHandler$$ExternalSyntheticLambda10 pipTouchHandler$$ExternalSyntheticLambda10 = pipInputConsumer2.mRegistrationListener;
                        if (pipTouchHandler$$ExternalSyntheticLambda10 != null) {
                            pipTouchHandler$$ExternalSyntheticLambda10.f$0.getClass();
                        }
                    }
                });
            }
            updateMovementBounds();
            pipTouchState.mAllowInputEvents = true;
            pipTouchState.reset();
            return;
        }
        if (i2 == 4) {
            pipTouchState.mAllowInputEvents = false;
            return;
        }
        if (i2 == 6) {
            pipTouchState.mAllowInputEvents = true;
            pipTouchState.reset();
            return;
        }
        if (i2 != 8) {
            return;
        }
        pipTouchState.mAllowInputEvents = false;
        if (pipDismissTargetHandler.mTargetViewContainer.getParent() != null) {
            pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
        }
        floatingContentCoordinator.allContentBounds.remove(this.mMotionHelper);
        PipResizeGestureHandler pipResizeGestureHandler2 = this.mPipResizeGestureHandler;
        pipResizeGestureHandler2.mIsAttached = false;
        pipResizeGestureHandler2.mUserResizeBounds.setEmpty();
        pipResizeGestureHandler2.updateIsEnabled();
        PipInputConsumer pipInputConsumer2 = this.mPipInputConsumer;
        if (pipInputConsumer2.mInputEventReceiver != null) {
            try {
                pipInputConsumer2.mWindowManager.destroyInputConsumer(pipInputConsumer2.mToken, 0);
            } catch (RemoteException e2) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3552531324387735237L, 0, "PipInputConsumer", String.valueOf(e2));
                }
            }
            pipInputConsumer2.mInputEventReceiver.dispose();
            pipInputConsumer2.mInputEventReceiver = null;
            ((HandlerExecutor) pipInputConsumer2.mMainExecutor).execute(new PipInputConsumer$$ExternalSyntheticLambda1(pipInputConsumer2, 1));
        }
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        pipBoundsState.mHasUserMovedPip = false;
        pipBoundsState.mHasUserResizedPip = false;
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

    public final void updateMinMaxSize(float f) {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        Rect rect = pipBoundsState.mNormalBounds;
        PipResizeGestureHandler pipResizeGestureHandler = this.mPipResizeGestureHandler;
        if (!pipResizeGestureHandler.mEnablePinchResize) {
            pipResizeGestureHandler.mMinSize.set(rect.width(), rect.height());
            PipResizeGestureHandler pipResizeGestureHandler2 = this.mPipResizeGestureHandler;
            pipResizeGestureHandler2.mMaxSize.set(pipBoundsState.mExpandedBounds.width(), pipBoundsState.mExpandedBounds.height());
            return;
        }
        pipBoundsState.updateMinMaxSize(f);
        PipResizeGestureHandler pipResizeGestureHandler3 = this.mPipResizeGestureHandler;
        Point point = pipBoundsState.mMinSize;
        pipResizeGestureHandler3.mMinSize.set(point.x, point.y);
        PipResizeGestureHandler pipResizeGestureHandler4 = this.mPipResizeGestureHandler;
        Point point2 = pipBoundsState.mMaxSize;
        pipResizeGestureHandler4.mMaxSize.set(point2.x, point2.y);
    }

    public final void updateMovementBounds() {
        Rect rect = new Rect();
        this.mPipBoundsAlgorithm.getInsetBounds(rect);
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        PipBoundsAlgorithm.getMovementBounds(pipBoundsState.getBounds(), rect, pipBoundsState.mMovementBounds, this.mIsImeShowing ? this.mImeHeight : 0);
        PipMotionHelper pipMotionHelper = this.mMotionHelper;
        PipBoundsState pipBoundsState2 = pipMotionHelper.mPipBoundsState;
        Rect rect2 = pipBoundsState2.mMovementBounds;
        pipMotionHelper.mFlingConfigX = new PhysicsAnimator.FlingConfig(1.9f, rect2.left, rect2.right);
        pipMotionHelper.mFlingConfigY = new PhysicsAnimator.FlingConfig(1.9f, rect2.top, rect2.bottom);
        Rect rect3 = pipBoundsState2.mPipDisplayLayoutState.getDisplayLayout().mStableInsets;
        pipMotionHelper.mStashConfigX = new PhysicsAnimator.FlingConfig(1.9f, (pipBoundsState2.mStashOffset - pipBoundsState2.getBounds().width()) + rect3.left, (r0.getDisplayBounds().right - pipBoundsState2.mStashOffset) - rect3.right);
        pipMotionHelper.mFloatingAllowedArea.set(pipBoundsState2.mMovementBounds);
        Rect rect4 = pipMotionHelper.mFloatingAllowedArea;
        rect4.right = pipBoundsState2.getBounds().width() + rect4.right;
        Rect rect5 = pipMotionHelper.mFloatingAllowedArea;
        rect5.bottom = pipBoundsState2.getBounds().height() + rect5.bottom;
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
            ProtoLogImpl_411527699.wtf(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3285037411188224571L, 0, "PipTouchHandler");
        }
        return false;
    }
}
