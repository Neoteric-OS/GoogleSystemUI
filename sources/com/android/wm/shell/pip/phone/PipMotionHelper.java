package com.android.wm.shell.pip.phone;

import android.content.Context;
import android.graphics.Rect;
import android.view.SurfaceControl;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.animation.FloatProperties;
import com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipSnapAlgorithm;
import com.android.wm.shell.pip.PipSurfaceTransactionHelper;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip.PipTransitionState;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipMotionHelper implements FloatingContentCoordinator.FloatingContent {
    public final Context mContext;
    public PhysicsAnimator.FlingConfig mFlingConfigX;
    public PhysicsAnimator.FlingConfig mFlingConfigY;
    public final FloatingContentCoordinator mFloatingContentCoordinator;
    public AnonymousClass2 mMagnetizedPip;
    public final PhonePipMenuController mMenuController;
    public final PipBoundsState mPipBoundsState;
    public PipPerfHintController.PipHighPerfSession mPipHighPerfSession;
    public final PipPerfHintController mPipPerfHintController;
    public final PipTaskOrganizer mPipTaskOrganizer;
    public final AnonymousClass1 mPipTransitionCallback;
    public PipMenuView$$ExternalSyntheticLambda0 mPostPipTransitionCallback;
    public final PipMotionHelper$$ExternalSyntheticLambda4 mResizePipUpdateListener;
    public final PipSnapAlgorithm mSnapAlgorithm;
    public PhysicsAnimator.FlingConfig mStashConfigX;
    public PhysicsAnimator mTemporaryBoundsPhysicsAnimator;
    public final Rect mFloatingAllowedArea = new Rect();
    public final PhysicsAnimator.SpringConfig mSpringConfig = new PhysicsAnimator.SpringConfig(700.0f, 1.0f);
    public final PhysicsAnimator.SpringConfig mAnimateToDismissSpringConfig = new PhysicsAnimator.SpringConfig(1500.0f, 1.0f);
    public final PhysicsAnimator.SpringConfig mCatchUpSpringConfig = new PhysicsAnimator.SpringConfig(5000.0f, 1.0f);
    public final PhysicsAnimator.SpringConfig mConflictResolutionSpringConfig = new PhysicsAnimator.SpringConfig(200.0f, 1.0f);
    public final PipMotionHelper$$ExternalSyntheticLambda1 mUpdateBoundsCallback = new PipMotionHelper$$ExternalSyntheticLambda1(this, 1);
    public boolean mSpringingToTouch = false;
    public boolean mDismissalPending = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.phone.PipMotionHelper$2, reason: invalid class name */
    public final class AnonymousClass2 extends MagnetizedObject {
        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final float getHeight(Object obj) {
            return ((Rect) obj).height();
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final void getLocationOnScreen(Object obj, int[] iArr) {
            Rect rect = (Rect) obj;
            iArr[0] = rect.left;
            iArr[1] = rect.top;
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
        public final float getWidth(Object obj) {
            return ((Rect) obj).width();
        }
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [com.android.wm.shell.pip.phone.PipMotionHelper$$ExternalSyntheticLambda4] */
    public PipMotionHelper(Context context, ShellExecutor shellExecutor, PipBoundsState pipBoundsState, PipTaskOrganizer pipTaskOrganizer, PhonePipMenuController phonePipMenuController, PipSnapAlgorithm pipSnapAlgorithm, PipTransitionController pipTransitionController, FloatingContentCoordinator floatingContentCoordinator, Optional optional) {
        PipTransitionController.PipTransitionCallback pipTransitionCallback = new PipTransitionController.PipTransitionCallback() { // from class: com.android.wm.shell.pip.phone.PipMotionHelper.1
            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionFinished(int i) {
                PipMotionHelper pipMotionHelper = PipMotionHelper.this;
                PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda0 = pipMotionHelper.mPostPipTransitionCallback;
                if (pipMenuView$$ExternalSyntheticLambda0 != null) {
                    pipMenuView$$ExternalSyntheticLambda0.run();
                    pipMotionHelper.mPostPipTransitionCallback = null;
                }
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionCanceled(int i) {
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionStarted(int i, Rect rect) {
            }
        };
        this.mContext = context;
        this.mPipTaskOrganizer = pipTaskOrganizer;
        this.mPipBoundsState = pipBoundsState;
        this.mMenuController = phonePipMenuController;
        this.mSnapAlgorithm = pipSnapAlgorithm;
        this.mFloatingContentCoordinator = floatingContentCoordinator;
        this.mPipPerfHintController = (PipPerfHintController) optional.orElse(null);
        pipTransitionController.mPipTransitionCallbacks.put(pipTransitionCallback, shellExecutor);
        this.mResizePipUpdateListener = new PhysicsAnimator.UpdateListener() { // from class: com.android.wm.shell.pip.phone.PipMotionHelper$$ExternalSyntheticLambda4
            @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.UpdateListener
            public final void onAnimationUpdateForProperty(Object obj) {
                PipMotionHelper pipMotionHelper = PipMotionHelper.this;
                PipBoundsState pipBoundsState2 = pipMotionHelper.mPipBoundsState;
                if (pipBoundsState2.mMotionBoundsState.isInMotion()) {
                    pipMotionHelper.mPipTaskOrganizer.scheduleUserResizePip(pipBoundsState2.getBounds(), pipBoundsState2.mMotionBoundsState.mBoundsInMotion, 0.0f, null);
                }
            }
        };
    }

    public final void animateToUnexpandedState(Rect rect, float f, Rect rect2, Rect rect3, boolean z) {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (f < 0.0f) {
            Rect rect4 = new Rect(pipBoundsState.getBounds());
            f = this.mSnapAlgorithm.getSnapFraction(pipBoundsState.mStashedState, rect4, rect3);
        }
        float f2 = f;
        int i = pipBoundsState.mStashedState;
        int i2 = pipBoundsState.mStashOffset;
        PipDisplayLayoutState pipDisplayLayoutState = pipBoundsState.mPipDisplayLayoutState;
        Rect displayBounds = pipDisplayLayoutState.getDisplayBounds();
        Rect rect5 = pipDisplayLayoutState.getDisplayLayout().mStableInsets;
        this.mSnapAlgorithm.getClass();
        PipSnapAlgorithm.applySnapFraction(rect, rect2, f2, i, i2, displayBounds, rect5);
        if (z) {
            movePip(rect, false);
        } else {
            resizeAndAnimatePipUnchecked$1(rect);
        }
    }

    public final void cancelPhysicsAnimation$1() {
        this.mTemporaryBoundsPhysicsAnimator.cancel();
        this.mPipBoundsState.mMotionBoundsState.mAnimatingToBounds.setEmpty();
        this.mSpringingToTouch = false;
    }

    public final void dismissPip() {
        cancelPhysicsAnimation$1();
        PhonePipMenuController phonePipMenuController = this.mMenuController;
        if (phonePipMenuController.isMenuVisible()) {
            phonePipMenuController.mPipMenuView.hideMenu(null, true, false, 2);
        }
        this.mPipTaskOrganizer.removePip();
    }

    public final void expandLeavePip$1(boolean z, boolean z2) {
        cancelPhysicsAnimation$1();
        PhonePipMenuController phonePipMenuController = this.mMenuController;
        if (phonePipMenuController.isMenuVisible()) {
            phonePipMenuController.mPipMenuView.hideMenu(null, true, false, 0);
        }
        this.mPipTaskOrganizer.exitPip(z ? 0 : 300, z2);
    }

    @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
    public final Rect getAllowedFloatingBoundsRegion() {
        return this.mFloatingAllowedArea;
    }

    @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
    public final Rect getFloatingBoundsOnScreen() {
        return this.mPipBoundsState.getBounds();
    }

    public final void movePip(Rect rect, boolean z) {
        if (!z) {
            this.mFloatingContentCoordinator.onContentMoved(this);
        }
        boolean z2 = this.mSpringingToTouch;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (z2) {
            PhysicsAnimator physicsAnimator = this.mTemporaryBoundsPhysicsAnimator;
            FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_WIDTH;
            float width = pipBoundsState.getBounds().width();
            PhysicsAnimator.SpringConfig springConfig = this.mCatchUpSpringConfig;
            physicsAnimator.spring(floatProperties$Companion$RECT_X$1, width, 0.0f, springConfig);
            physicsAnimator.spring(FloatProperties.RECT_HEIGHT, pipBoundsState.getBounds().height(), 0.0f, springConfig);
            physicsAnimator.spring(FloatProperties.RECT_X, rect.left, 0.0f, springConfig);
            physicsAnimator.spring(FloatProperties.RECT_Y, rect.top, 0.0f, springConfig);
            startBoundsAnimator$1(rect.left, rect.top, null);
            return;
        }
        cancelPhysicsAnimation$1();
        PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
        if (z) {
            pipBoundsState.mMotionBoundsState.setBoundsInMotion(rect);
            pipTaskOrganizer.scheduleUserResizePip(pipBoundsState.getBounds(), rect, 0.0f, new PipMotionHelper$$ExternalSyntheticLambda1(this, 2));
            return;
        }
        if (!rect.equals(pipBoundsState.getBounds())) {
            if (pipTaskOrganizer.mToken != null && pipTaskOrganizer.mLeash != null) {
                pipTaskOrganizer.mPipBoundsState.setBounds(rect);
                SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) pipTaskOrganizer.mSurfaceControlTransactionFactory).getTransaction();
                SurfaceControl surfaceControl = pipTaskOrganizer.mLeash;
                PipSurfaceTransactionHelper pipSurfaceTransactionHelper = pipTaskOrganizer.mSurfaceTransactionHelper;
                pipSurfaceTransactionHelper.crop(rect, transaction, surfaceControl);
                pipSurfaceTransactionHelper.round(transaction, pipTaskOrganizer.mLeash, PipTransitionState.isInPip(pipTaskOrganizer.mPipTransitionState.mState));
                PhonePipMenuController phonePipMenuController = pipTaskOrganizer.mPipMenuController;
                if (phonePipMenuController.isMenuVisible()) {
                    SurfaceControl surfaceControl2 = pipTaskOrganizer.mLeash;
                    if (!rect.isEmpty() && phonePipMenuController.checkPipMenuState() && surfaceControl2 != null) {
                        transaction.apply();
                    }
                } else {
                    transaction.apply();
                }
                PipMotionHelper$$ExternalSyntheticLambda1 pipMotionHelper$$ExternalSyntheticLambda1 = this.mUpdateBoundsCallback;
                if (pipMotionHelper$$ExternalSyntheticLambda1 != null) {
                    pipMotionHelper$$ExternalSyntheticLambda1.accept(rect);
                }
            } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 5014614599801648599L, 0, "PipTaskOrganizer");
            }
        }
        pipBoundsState.setBounds(rect);
    }

    @Override // com.android.wm.shell.common.FloatingContentCoordinator.FloatingContent
    public final void moveToBounds(Rect rect) {
        if (!this.mTemporaryBoundsPhysicsAnimator.isRunning()) {
            PipBoundsState pipBoundsState = this.mPipBoundsState;
            pipBoundsState.mMotionBoundsState.setBoundsInMotion(pipBoundsState.getBounds());
        }
        PhysicsAnimator physicsAnimator = this.mTemporaryBoundsPhysicsAnimator;
        FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_X;
        float f = rect.left;
        PhysicsAnimator.SpringConfig springConfig = this.mConflictResolutionSpringConfig;
        physicsAnimator.spring(floatProperties$Companion$RECT_X$1, f, 0.0f, springConfig);
        physicsAnimator.spring(FloatProperties.RECT_Y, rect.top, 0.0f, springConfig);
        startBoundsAnimator$1(rect.left, rect.top, null);
    }

    public final void movetoTarget$1(float f, float f2, Runnable runnable, boolean z) {
        this.mSpringingToTouch = false;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (f == 0.0f) {
            f = pipBoundsState.mMotionBoundsState.mBoundsInMotion.centerX() < pipBoundsState.mPipDisplayLayoutState.getDisplayBounds().centerX() ? -0.001f : 0.001f;
        }
        PhysicsAnimator physicsAnimator = this.mTemporaryBoundsPhysicsAnimator;
        FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_WIDTH;
        float width = pipBoundsState.getBounds().width();
        PhysicsAnimator.SpringConfig springConfig = this.mSpringConfig;
        physicsAnimator.spring(floatProperties$Companion$RECT_X$1, width, 0.0f, springConfig);
        physicsAnimator.spring(FloatProperties.RECT_HEIGHT, pipBoundsState.getBounds().height(), 0.0f, springConfig);
        physicsAnimator.flingThenSpring(FloatProperties.RECT_X, f, z ? this.mStashConfigX : this.mFlingConfigX, this.mSpringConfig, true);
        physicsAnimator.flingThenSpring(FloatProperties.RECT_Y, f2, this.mFlingConfigY, this.mSpringConfig, false);
        PipDisplayLayoutState pipDisplayLayoutState = pipBoundsState.mPipDisplayLayoutState;
        Rect rect = pipDisplayLayoutState.getDisplayLayout().mStableInsets;
        float width2 = z ? (pipBoundsState.mStashOffset - pipBoundsState.getBounds().width()) + rect.left : pipBoundsState.mMovementBounds.left;
        float f3 = z ? (pipDisplayLayoutState.getDisplayBounds().right - pipBoundsState.mStashOffset) - rect.right : pipBoundsState.mMovementBounds.right;
        if (f >= 0.0f) {
            width2 = f3;
        }
        float f4 = pipBoundsState.mMotionBoundsState.mBoundsInMotion.top;
        PhysicsAnimator.FlingConfig flingConfig = this.mFlingConfigY;
        startBoundsAnimator$1(width2, Math.min(flingConfig.max, Math.max(flingConfig.min, f4 + (f2 / (flingConfig.friction * 4.2f)))), runnable);
    }

    public final void resizeAndAnimatePipUnchecked$1(Rect rect) {
        this.mPipTaskOrganizer.scheduleAnimateResizePip(rect, 250, 8);
        this.mPipBoundsState.mMotionBoundsState.mAnimatingToBounds.set(rect);
    }

    public final void startBoundsAnimator$1(float f, float f2, Runnable runnable) {
        if (!this.mSpringingToTouch) {
            cancelPhysicsAnimation$1();
        }
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        int i = (int) f;
        int i2 = (int) f2;
        pipBoundsState.mMotionBoundsState.mAnimatingToBounds.set(new Rect(i, i2, pipBoundsState.getBounds().width() + i, pipBoundsState.getBounds().height() + i2));
        if (!this.mTemporaryBoundsPhysicsAnimator.isRunning()) {
            PipPerfHintController pipPerfHintController = this.mPipPerfHintController;
            if (pipPerfHintController != null) {
                this.mPipHighPerfSession = pipPerfHintController.startSession(new PipMotionHelper$$ExternalSyntheticLambda1(this, 0), "startBoundsAnimator");
            }
            PipMotionHelper$$ExternalSyntheticLambda4 pipMotionHelper$$ExternalSyntheticLambda4 = this.mResizePipUpdateListener;
            if (runnable != null) {
                PhysicsAnimator physicsAnimator = this.mTemporaryBoundsPhysicsAnimator;
                physicsAnimator.updateListeners.add(pipMotionHelper$$ExternalSyntheticLambda4);
                physicsAnimator.withEndActions(new PipMotionHelper$$ExternalSyntheticLambda0(this, 1), runnable);
            } else {
                PhysicsAnimator physicsAnimator2 = this.mTemporaryBoundsPhysicsAnimator;
                physicsAnimator2.updateListeners.add(pipMotionHelper$$ExternalSyntheticLambda4);
                physicsAnimator2.withEndActions(new PipMotionHelper$$ExternalSyntheticLambda0(this, 1));
            }
        }
        this.mTemporaryBoundsPhysicsAnimator.start();
    }

    public final void synchronizePinnedStackBounds() {
        cancelPhysicsAnimation$1();
        this.mPipBoundsState.mMotionBoundsState.mBoundsInMotion.setEmpty();
        if (this.mPipTaskOrganizer.isInPip()) {
            this.mFloatingContentCoordinator.onContentMoved(this);
        }
    }
}
