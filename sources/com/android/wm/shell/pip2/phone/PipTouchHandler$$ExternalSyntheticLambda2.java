package com.android.wm.shell.pip2.phone;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.provider.DeviceConfig;
import android.view.IWindowManager;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.view.WindowManagerGlobal;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.animation.FloatProperties;
import com.android.wm.shell.animation.FloatProperties$Companion$RECT_X$1;
import com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticOutline0;
import com.android.wm.shell.bubbles.DismissViewUtils;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.pip2.phone.PipDismissTargetHandler.AnonymousClass1;
import com.android.wm.shell.pip2.phone.PipMotionHelper;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$MagneticTarget$updateLocationOnScreen$1;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTouchHandler$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipTouchHandler f$0;

    public /* synthetic */ PipTouchHandler$$ExternalSyntheticLambda2(PipTouchHandler pipTouchHandler, int i) {
        this.$r8$classId = i;
        this.f$0 = pipTouchHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final int i = 1;
        final int i2 = 0;
        View view = null;
        int i3 = this.$r8$classId;
        final PipTouchHandler pipTouchHandler = this.f$0;
        switch (i3) {
            case 0:
                pipTouchHandler.updateMovementBounds();
                break;
            case 1:
                Rect bounds = pipTouchHandler.mPipBoundsState.getBounds();
                boolean willResizeMenu = pipTouchHandler.willResizeMenu();
                PhonePipMenuController phonePipMenuController = pipTouchHandler.mMenuController;
                if (willResizeMenu && phonePipMenuController.isMenuVisible()) {
                    phonePipMenuController.getClass();
                    view.getClass();
                    view.setAlpha(0.0f);
                    view.getClass();
                    view.setAlpha(0.0f);
                    view.getClass();
                    view.setAlpha(0.0f);
                }
                phonePipMenuController.showMenuInternal$1(1, bounds, true, willResizeMenu, willResizeMenu);
                break;
            case 2:
                if (!pipTouchHandler.mIsImeShowing || pipTouchHandler.mImeHeight <= pipTouchHandler.mShelfHeight) {
                    PipTouchHandler$$ExternalSyntheticLambda2 pipTouchHandler$$ExternalSyntheticLambda2 = new PipTouchHandler$$ExternalSyntheticLambda2(pipTouchHandler, 4);
                    PipTransitionState pipTransitionState = pipTouchHandler.mPipTransitionState;
                    pipTransitionState.mOnIdlePipTransitionStateRunnable = pipTouchHandler$$ExternalSyntheticLambda2;
                    int i4 = pipTransitionState.mState;
                    if (i4 == 3 || i4 == 6) {
                        pipTouchHandler$$ExternalSyntheticLambda2.run();
                        pipTransitionState.mOnIdlePipTransitionStateRunnable = null;
                        break;
                    }
                }
                break;
            case 3:
                pipTouchHandler.mEnableResize = pipTouchHandler.mContext.getResources().getBoolean(R.bool.config_pipEnableResizeForMenu);
                Resources resources = pipTouchHandler.mContext.getResources();
                resources.getDimensionPixelSize(R.dimen.pip_bottom_offset_buffer);
                resources.getDimensionPixelSize(R.dimen.pip_ime_offset);
                final PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler.mPipDismissTargetHandler;
                pipDismissTargetHandler.updateMagneticTargetSize();
                pipTouchHandler.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.pip2.phone.PipTouchHandler$$ExternalSyntheticLambda9
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        PipTouchHandler pipTouchHandler2 = PipTouchHandler.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        pipTouchHandler2.getClass();
                        String str2 = str + "  ";
                        printWriter.println(str + "PipTouchHandler");
                        printWriter.println(str2 + "mMenuState=" + pipTouchHandler2.mMenuState);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append("mIsImeShowing=");
                        StringBuilder m = BackAnimationController$$ExternalSyntheticOutline0.m(sb, pipTouchHandler2.mIsImeShowing, printWriter, str2, "mImeHeight=");
                        m.append(pipTouchHandler2.mImeHeight);
                        printWriter.println(m.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str2);
                        sb2.append("mIsShelfShowing=");
                        StringBuilder m2 = BackAnimationController$$ExternalSyntheticOutline0.m(sb2, pipTouchHandler2.mIsShelfShowing, printWriter, str2, "mShelfHeight=");
                        m2.append(pipTouchHandler2.mShelfHeight);
                        printWriter.println(m2.toString());
                        printWriter.println(str2 + "mSavedSnapFraction=" + pipTouchHandler2.mSavedSnapFraction);
                        printWriter.println(str2 + "mMovementBoundsExtraOffsets=0");
                        pipTouchHandler2.mPipBoundsAlgorithm.dump(printWriter, str2);
                        PipTouchState pipTouchState = pipTouchHandler2.mTouchState;
                        pipTouchState.getClass();
                        String str3 = str2 + "  ";
                        printWriter.println(str2 + "PipTouchState");
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str3);
                        sb3.append("mAllowTouches=");
                        StringBuilder m3 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb3, pipTouchState.mAllowTouches, printWriter, str3, "mAllowInputEvents="), pipTouchState.mAllowInputEvents, printWriter, str3, "mActivePointerId=");
                        m3.append(pipTouchState.mActivePointerId);
                        printWriter.println(m3.toString());
                        printWriter.println(str3 + "mLastTouchDisplayId=" + pipTouchState.mLastTouchDisplayId);
                        printWriter.println(str3 + "mDownTouch=" + pipTouchState.mDownTouch);
                        printWriter.println(str3 + "mDownDelta=" + pipTouchState.mDownDelta);
                        printWriter.println(str3 + "mLastTouch=" + pipTouchState.mLastTouch);
                        printWriter.println(str3 + "mLastDelta=" + pipTouchState.mLastDelta);
                        printWriter.println(str3 + "mVelocity=" + pipTouchState.mVelocity);
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(str3);
                        sb4.append("mIsUserInteracting=");
                        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb4, pipTouchState.mIsUserInteracting, printWriter, str3, "mIsDragging="), pipTouchState.mIsDragging, printWriter, str3, "mStartedDragging="), pipTouchState.mStartedDragging, printWriter, str3, "mAllowDraggingOffscreen="), pipTouchState.mAllowDraggingOffscreen, printWriter);
                        PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler2.mPipResizeGestureHandler;
                        if (pipResizeGestureHandler != null) {
                            String m4 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str2, "  ");
                            printWriter.println(str2 + "PipResizeGestureHandler");
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(m4);
                            sb5.append("mAllowGesture=");
                            StringBuilder m5 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb5, pipResizeGestureHandler.mAllowGesture, printWriter, m4, "mIsAttached="), pipResizeGestureHandler.mIsAttached, printWriter, m4, "mIsEnabled="), pipResizeGestureHandler.mIsEnabled, printWriter, m4, "mEnablePinchResize="), pipResizeGestureHandler.mEnablePinchResize, printWriter, m4, "mThresholdCrossed=");
                            m5.append(pipResizeGestureHandler.mThresholdCrossed);
                            printWriter.println(m5.toString());
                            printWriter.println(m4 + "mOhmOffset=0");
                            printWriter.println(m4 + "mMinSize=" + pipResizeGestureHandler.mMinSize);
                            printWriter.println(m4 + "mMaxSize=" + pipResizeGestureHandler.mMaxSize);
                        }
                    }
                }, pipTouchHandler);
                PipMotionHelper pipMotionHelper = pipTouchHandler.mMotionHelper;
                Rect rect = pipMotionHelper.mPipBoundsState.mMotionBoundsState.mBoundsInMotion;
                Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                pipMotionHelper.mTemporaryBoundsPhysicsAnimator = PhysicsAnimator.Companion.getInstance(rect);
                PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler.mPipResizeGestureHandler;
                pipResizeGestureHandler.mContext.getDisplay().getRealSize(pipResizeGestureHandler.mMaxSize);
                pipResizeGestureHandler.mTouchSlop = ViewConfiguration.get(pipResizeGestureHandler.mContext).getScaledTouchSlop();
                pipResizeGestureHandler.mEnablePinchResize = pipResizeGestureHandler.mContext.getResources().getBoolean(R.bool.config_pipEnablePinchResize);
                Resources resources2 = pipDismissTargetHandler.mContext.getResources();
                pipDismissTargetHandler.mEnableDismissDragToEdge = resources2.getBoolean(R.bool.config_pipEnableDismissDragToEdge);
                pipDismissTargetHandler.mDismissAreaHeight = resources2.getDimensionPixelSize(R.dimen.floating_dismiss_gradient_height);
                DismissView dismissView = pipDismissTargetHandler.mTargetViewContainer;
                if (dismissView != null && dismissView.getParent() != null) {
                    pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
                }
                DismissView dismissView2 = new DismissView(pipDismissTargetHandler.mContext);
                pipDismissTargetHandler.mTargetViewContainer = dismissView2;
                DismissViewUtils.setup(dismissView2);
                DismissView dismissView3 = pipDismissTargetHandler.mTargetViewContainer;
                pipDismissTargetHandler.mTargetView = dismissView3.circle;
                dismissView3.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.wm.shell.pip2.phone.PipDismissTargetHandler$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        PipDismissTargetHandler pipDismissTargetHandler2 = PipDismissTargetHandler.this;
                        if (!windowInsets.equals(pipDismissTargetHandler2.mWindowInsets)) {
                            pipDismissTargetHandler2.mWindowInsets = windowInsets;
                            pipDismissTargetHandler2.updateMagneticTargetSize();
                        }
                        return windowInsets;
                    }
                });
                PipMotionHelper pipMotionHelper2 = pipDismissTargetHandler.mMotionHelper;
                if (pipMotionHelper2.mMagnetizedPip == null) {
                    PipMotionHelper.AnonymousClass1 anonymousClass1 = new PipMotionHelper.AnonymousClass1(pipMotionHelper2.mContext, pipMotionHelper2.mPipBoundsState.mMotionBoundsState.mBoundsInMotion, FloatProperties.RECT_X, FloatProperties.RECT_Y);
                    pipMotionHelper2.mMagnetizedPip = anonymousClass1;
                    anonymousClass1.flingToTargetEnabled = false;
                }
                PipMotionHelper.AnonymousClass1 anonymousClass12 = pipMotionHelper2.mMagnetizedPip;
                pipDismissTargetHandler.mMagnetizedPip = anonymousClass12;
                anonymousClass12.associatedTargets.clear();
                PipMotionHelper.AnonymousClass1 anonymousClass13 = pipDismissTargetHandler.mMagnetizedPip;
                DismissCircleView dismissCircleView = pipDismissTargetHandler.mTargetView;
                anonymousClass13.getClass();
                MagnetizedObject.MagneticTarget magneticTarget = new MagnetizedObject.MagneticTarget(dismissCircleView, 0);
                anonymousClass13.associatedTargets.add(magneticTarget);
                dismissCircleView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
                pipDismissTargetHandler.mMagneticTarget = magneticTarget;
                pipDismissTargetHandler.updateMagneticTargetSize();
                PipMotionHelper.AnonymousClass1 anonymousClass14 = pipDismissTargetHandler.mMagnetizedPip;
                anonymousClass14.animateStuckToTarget = new Function5() { // from class: com.android.wm.shell.pip2.phone.PipDismissTargetHandler$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function5
                    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                        MagnetizedObject.MagneticTarget magneticTarget2 = (MagnetizedObject.MagneticTarget) obj;
                        Float f = (Float) obj2;
                        Float f2 = (Float) obj3;
                        Boolean bool = (Boolean) obj4;
                        Function0 function0 = (Function0) obj5;
                        PipDismissTargetHandler pipDismissTargetHandler2 = PipDismissTargetHandler.this;
                        if (pipDismissTargetHandler2.mEnableDismissDragToEdge) {
                            PipMotionHelper pipMotionHelper3 = pipDismissTargetHandler2.mMotionHelper;
                            float floatValue = f.floatValue();
                            float floatValue2 = f2.floatValue();
                            bool.getClass();
                            pipMotionHelper3.getClass();
                            PointF pointF = magneticTarget2.centerOnScreen;
                            float dimensionPixelSize = pipMotionHelper3.mContext.getResources().getDimensionPixelSize(R.dimen.dismiss_circle_size);
                            PipBoundsState pipBoundsState = pipMotionHelper3.mPipBoundsState;
                            float f3 = dimensionPixelSize * 0.85f;
                            float width = f3 / (pipBoundsState.getBounds().width() / pipBoundsState.getBounds().height());
                            float f4 = pointF.x - (f3 / 2.0f);
                            float f5 = pointF.y - (width / 2.0f);
                            PipBoundsState.MotionBoundsState motionBoundsState = pipBoundsState.mMotionBoundsState;
                            if (!motionBoundsState.isInMotion()) {
                                motionBoundsState.setBoundsInMotion(pipBoundsState.getBounds());
                            }
                            PhysicsAnimator physicsAnimator = pipMotionHelper3.mTemporaryBoundsPhysicsAnimator;
                            FloatProperties$Companion$RECT_X$1 floatProperties$Companion$RECT_X$1 = FloatProperties.RECT_X;
                            PhysicsAnimator.SpringConfig springConfig = pipMotionHelper3.mAnimateToDismissSpringConfig;
                            physicsAnimator.spring(floatProperties$Companion$RECT_X$1, f4, floatValue, springConfig);
                            physicsAnimator.spring(FloatProperties.RECT_Y, f5, floatValue2, springConfig);
                            physicsAnimator.spring(FloatProperties.RECT_WIDTH, f3, 0.0f, springConfig);
                            physicsAnimator.spring(FloatProperties.RECT_HEIGHT, width, 0.0f, springConfig);
                            physicsAnimator.withEndActions(function0);
                            pipMotionHelper3.startBoundsAnimator(f4, f5, null);
                        }
                        return Unit.INSTANCE;
                    }
                };
                anonymousClass14.magnetListener = pipDismissTargetHandler.new AnonymousClass1();
                IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
                ShellExecutor shellExecutor = pipTouchHandler.mMainExecutor;
                PipInputConsumer pipInputConsumer = new PipInputConsumer(windowManagerService, shellExecutor);
                pipTouchHandler.mPipInputConsumer = pipInputConsumer;
                pipInputConsumer.mListener = new PipTouchHandler$$ExternalSyntheticLambda10(pipTouchHandler);
                pipInputConsumer.mRegistrationListener = new PipTouchHandler$$ExternalSyntheticLambda10(pipTouchHandler);
                ((HandlerExecutor) pipInputConsumer.mMainExecutor).execute(new PipInputConsumer$$ExternalSyntheticLambda1(pipInputConsumer, i2));
                pipTouchHandler.mEnableStash = DeviceConfig.getBoolean("systemui", "pip_stashing", true);
                DeviceConfig.addOnPropertiesChangedListener("systemui", shellExecutor, new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.wm.shell.pip2.phone.PipTouchHandler$$ExternalSyntheticLambda12
                    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                        int i5 = i2;
                        PipTouchHandler pipTouchHandler2 = pipTouchHandler;
                        pipTouchHandler2.getClass();
                        switch (i5) {
                            case 0:
                                if (properties.getKeyset().contains("pip_stashing")) {
                                    pipTouchHandler2.mEnableStash = properties.getBoolean("pip_stashing", true);
                                    break;
                                }
                                break;
                            default:
                                if (properties.getKeyset().contains("pip_velocity_threshold")) {
                                    pipTouchHandler2.mStashVelocityThreshold = properties.getFloat("pip_velocity_threshold", 18000.0f);
                                    break;
                                }
                                break;
                        }
                    }
                });
                pipTouchHandler.mStashVelocityThreshold = DeviceConfig.getFloat("systemui", "pip_velocity_threshold", 18000.0f);
                DeviceConfig.addOnPropertiesChangedListener("systemui", shellExecutor, new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.wm.shell.pip2.phone.PipTouchHandler$$ExternalSyntheticLambda12
                    public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                        int i5 = i;
                        PipTouchHandler pipTouchHandler2 = pipTouchHandler;
                        pipTouchHandler2.getClass();
                        switch (i5) {
                            case 0:
                                if (properties.getKeyset().contains("pip_stashing")) {
                                    pipTouchHandler2.mEnableStash = properties.getBoolean("pip_stashing", true);
                                    break;
                                }
                                break;
                            default:
                                if (properties.getKeyset().contains("pip_velocity_threshold")) {
                                    pipTouchHandler2.mStashVelocityThreshold = properties.getFloat("pip_velocity_threshold", 18000.0f);
                                    break;
                                }
                                break;
                        }
                    }
                });
                break;
            default:
                PipBoundsState pipBoundsState = pipTouchHandler.mPipBoundsState;
                if (!pipBoundsState.mHasUserMovedPip && !pipBoundsState.mHasUserResizedPip) {
                    i = 0;
                }
                int i5 = pipTouchHandler.mPipBoundsAlgorithm.getEntryDestinationBounds().top - pipBoundsState.getBounds().top;
                if (!pipTouchHandler.mIsImeShowing && i == 0 && i5 != 0) {
                    pipTouchHandler.mMotionHelper.animateToOffset(i5, pipBoundsState.getBounds());
                    break;
                }
                break;
        }
    }
}
