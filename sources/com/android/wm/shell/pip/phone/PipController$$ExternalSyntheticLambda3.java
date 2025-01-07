package com.android.wm.shell.pip.phone;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.function.TriConsumer;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.wm.shell.WindowManagerShellWrapper;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.FloatingContentCoordinator;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.TabletopModeController;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.common.pip.PipAppOpsListener;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipMediaController;
import com.android.wm.shell.common.pip.PipMediaController$mSessionsChangedListener$1;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PinnedStackListenerForwarder;
import com.android.wm.shell.pip.PipAnimationController;
import com.android.wm.shell.pip.PipParamsChangedForwarder;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.pip.phone.PipController.AnonymousClass3;
import com.android.wm.shell.pip.phone.PipController.AnonymousClass4;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.sysui.ShellController;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipController f$0;

    public /* synthetic */ PipController$$ExternalSyntheticLambda3(PipController pipController, int i) {
        this.$r8$classId = i;
        this.f$0 = pipController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                final PipController pipController = this.f$0;
                pipController.getClass();
                pipController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda5
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        PipController pipController2 = PipController.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        pipController2.getClass();
                        printWriter.println("PipController");
                        PhonePipMenuController phonePipMenuController = pipController2.mMenuController;
                        phonePipMenuController.getClass();
                        printWriter.println("  PhonePipMenuController");
                        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("    mMenuState="), phonePipMenuController.mMenuState, printWriter, "    mPipMenuView=");
                        m.append(phonePipMenuController.mPipMenuView);
                        printWriter.println(m.toString());
                        printWriter.println("    mListeners=" + phonePipMenuController.mListeners.size());
                        PipTouchHandler pipTouchHandler = pipController2.mTouchHandler;
                        pipTouchHandler.getClass();
                        printWriter.println("  PipTouchHandler");
                        StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("    mMenuState="), pipTouchHandler.mMenuState, printWriter, "    mIsImeShowing="), pipTouchHandler.mIsImeShowing, printWriter, "    mImeHeight="), pipTouchHandler.mImeHeight, printWriter, "    mIsShelfShowing="), pipTouchHandler.mIsShelfShowing, printWriter, "    mShelfHeight="), pipTouchHandler.mShelfHeight, printWriter, "    mSavedSnapFraction="), pipTouchHandler.mSavedSnapFraction, printWriter, "    mMovementBoundsExtraOffsets=");
                        m2.append(pipTouchHandler.mMovementBoundsExtraOffsets);
                        printWriter.println(m2.toString());
                        pipTouchHandler.mPipBoundsAlgorithm.dump(printWriter, "    ");
                        PipTouchState pipTouchState = pipTouchHandler.mTouchState;
                        pipTouchState.getClass();
                        printWriter.println("    PipTouchState");
                        StringBuilder m3 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("      mAllowTouches="), pipTouchState.mAllowTouches, printWriter, "      mAllowInputEvents="), pipTouchState.mAllowInputEvents, printWriter, "      mActivePointerId="), pipTouchState.mActivePointerId, printWriter, "      mLastTouchDisplayId="), pipTouchState.mLastTouchDisplayId, printWriter, "      mDownTouch=");
                        m3.append(pipTouchState.mDownTouch);
                        printWriter.println(m3.toString());
                        printWriter.println("      mDownDelta=" + pipTouchState.mDownDelta);
                        printWriter.println("      mLastTouch=" + pipTouchState.mLastTouch);
                        printWriter.println("      mLastDelta=" + pipTouchState.mLastDelta);
                        printWriter.println("      mVelocity=" + pipTouchState.mVelocity);
                        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("      mIsUserInteracting="), pipTouchState.mIsUserInteracting, printWriter, "      mIsDragging="), pipTouchState.mIsDragging, printWriter, "      mStartedDragging="), pipTouchState.mStartedDragging, printWriter, "      mAllowDraggingOffscreen="), pipTouchState.mAllowDraggingOffscreen, printWriter);
                        PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler.mPipResizeGestureHandler;
                        if (pipResizeGestureHandler != null) {
                            StringBuilder m4 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "    PipResizeGestureHandler", "      mAllowGesture="), pipResizeGestureHandler.mAllowGesture, printWriter, "      mIsAttached="), pipResizeGestureHandler.mIsAttached, printWriter, "      mIsEnabled="), pipResizeGestureHandler.mIsEnabled, printWriter, "      mEnablePinchResize="), pipResizeGestureHandler.mEnablePinchResize, printWriter, "      mThresholdCrossed="), pipResizeGestureHandler.mThresholdCrossed, printWriter, "      mOhmOffset="), pipResizeGestureHandler.mOhmOffset, printWriter, "      mMinSize=");
                            m4.append(pipResizeGestureHandler.mMinSize);
                            printWriter.println(m4.toString());
                            printWriter.println("      mMaxSize=" + pipResizeGestureHandler.mMaxSize);
                        }
                        pipController2.mPipBoundsAlgorithm.dump(printWriter, "  ");
                        pipController2.mPipTaskOrganizer.dump$1(printWriter, "  ");
                        pipController2.mPipBoundsState.dump(printWriter);
                        PipInputConsumer pipInputConsumer = pipController2.mPipInputConsumer;
                        pipInputConsumer.getClass();
                        printWriter.println("  PipInputConsumer");
                        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    registered="), pipInputConsumer.mInputEventReceiver != null, printWriter);
                        pipController2.mPipDisplayLayoutState.dump(printWriter);
                    }
                }, pipController);
                IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
                ShellExecutor shellExecutor = pipController.mMainExecutor;
                pipController.mPipInputConsumer = new PipInputConsumer(windowManagerService, shellExecutor);
                pipController.mPipTransitionController.mPipTransitionCallbacks.put(pipController, shellExecutor);
                pipController.mPipTaskOrganizer.mOnDisplayIdChangeCallback = new PipController$$ExternalSyntheticLambda6(pipController);
                pipController.mPipTransitionState.mOnPipTransitionStateChangedListeners.add(new PipController$$ExternalSyntheticLambda7(pipController));
                PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda3 = new PipController$$ExternalSyntheticLambda3(pipController, 3);
                PipBoundsState pipBoundsState = pipController.mPipBoundsState;
                pipBoundsState.mOnMinimalSizeChangeCallback = pipController$$ExternalSyntheticLambda3;
                new TriConsumer() { // from class: com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda9
                    public final void accept(Object obj, Object obj2, Object obj3) {
                        PipController pipController2 = PipController.this;
                        pipController2.getClass();
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        int intValue = ((Integer) obj2).intValue();
                        PipTouchHandler pipTouchHandler = pipController2.mTouchHandler;
                        pipTouchHandler.mIsShelfShowing = booleanValue;
                        pipTouchHandler.mShelfHeight = intValue;
                        if (((Boolean) obj3).booleanValue()) {
                            pipController2.updateMovementBounds(pipController2.mPipBoundsState.getBounds(), false, false, true, null);
                        }
                    }
                };
                pipBoundsState.getClass();
                PipTouchHandler pipTouchHandler = pipController.mTouchHandler;
                if (pipTouchHandler != null) {
                    PipInputConsumer pipInputConsumer = pipController.mPipInputConsumer;
                    pipInputConsumer.mListener = new PipController$$ExternalSyntheticLambda10(pipTouchHandler);
                    pipInputConsumer.mRegistrationListener = new PipController$$ExternalSyntheticLambda10(pipTouchHandler);
                    ((HandlerExecutor) pipInputConsumer.mMainExecutor).execute(new PipInputConsumer$$ExternalSyntheticLambda1(pipInputConsumer, 0));
                }
                DisplayController displayController = pipController.mDisplayController;
                displayController.addDisplayChangingController(pipController.mRotationController);
                displayController.addDisplayWindowListener(pipController.mDisplaysChangedListener);
                int displayId = pipController.mContext.getDisplayId();
                PipDisplayLayoutState pipDisplayLayoutState = pipController.mPipDisplayLayoutState;
                pipDisplayLayoutState.mDisplayId = displayId;
                Context context = pipController.mContext;
                pipDisplayLayoutState.mDisplayLayout.set(new DisplayLayout(context, context.getDisplay()));
                try {
                    WindowManagerShellWrapper windowManagerShellWrapper = pipController.mWindowManagerShellWrapper;
                    PipController.PipControllerPinnedTaskListener pipControllerPinnedTaskListener = pipController.mPinnedTaskListener;
                    PinnedStackListenerForwarder pinnedStackListenerForwarder = windowManagerShellWrapper.mPinnedStackListenerForwarder;
                    pinnedStackListenerForwarder.mListeners.add(pipControllerPinnedTaskListener);
                    WindowManagerGlobal.getWindowManagerService().registerPinnedTaskListener(0, pinnedStackListenerForwarder.mListenerImpl);
                } catch (RemoteException e) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                        ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3110474237468854425L, 0, "PipController", String.valueOf(e));
                    }
                }
                try {
                    if (ActivityTaskManager.getService().getRootTaskInfo(2, 0) != null) {
                        pipController.mPipInputConsumer.registerInputConsumer();
                    }
                } catch (RemoteException | UnsupportedOperationException e2) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                        ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3110474237468854425L, 0, "PipController", String.valueOf(e2));
                    }
                    e2.printStackTrace();
                }
                pipController.mTaskStackListener.addListener(new TaskStackListenerCallback() { // from class: com.android.wm.shell.pip.phone.PipController.2
                    public AnonymousClass2() {
                    }

                    @Override // com.android.wm.shell.common.TaskStackListenerCallback
                    public final void onActivityPinned(String str) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -26415654613861651L, 0, String.valueOf(str));
                        }
                        PipController pipController2 = PipController.this;
                        PipTouchHandler pipTouchHandler2 = pipController2.mTouchHandler;
                        pipTouchHandler2.mPipDismissTargetHandler.createOrUpdateDismissTarget();
                        PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler2.mPipResizeGestureHandler;
                        pipResizeGestureHandler.mIsAttached = true;
                        pipResizeGestureHandler.updateIsEnabled();
                        PipMotionHelper pipMotionHelper = pipTouchHandler2.mMotionHelper;
                        FloatingContentCoordinator floatingContentCoordinator = pipTouchHandler2.mFloatingContentCoordinator;
                        floatingContentCoordinator.updateContentBounds();
                        floatingContentCoordinator.allContentBounds.put(pipMotionHelper, pipMotionHelper.getFloatingBoundsOnScreen());
                        floatingContentCoordinator.maybeMoveConflictingContent(pipMotionHelper);
                        PipMediaController pipMediaController = pipController2.mMediaController;
                        MediaSessionManager mediaSessionManager = pipMediaController.mMediaSessionManager;
                        Intrinsics.checkNotNull(mediaSessionManager);
                        pipMediaController.resolveActiveMediaController(mediaSessionManager.getActiveSessionsForUser(null, UserHandle.CURRENT));
                        PipAppOpsListener pipAppOpsListener = pipController2.mAppOpsListener;
                        pipAppOpsListener.mAppOpsManager.startWatchingMode(67, str, pipAppOpsListener.mAppOpsChangedListener);
                        pipController2.mPipInputConsumer.registerInputConsumer();
                    }

                    @Override // com.android.wm.shell.common.TaskStackListenerCallback
                    public final void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2103539361546619397L, 0, String.valueOf(runningTaskInfo.topActivity));
                        }
                        if (runningTaskInfo.getWindowingMode() != 2) {
                            return;
                        }
                        PipController pipController2 = PipController.this;
                        PipTaskOrganizer pipTaskOrganizer = pipController2.mPipTaskOrganizer;
                        if (pipTaskOrganizer.mSplitScreenOptional.isPresent() && ((SplitScreenController) pipTaskOrganizer.mSplitScreenOptional.get()).isLaunchToSplit(runningTaskInfo)) {
                            pipController2.mTouchHandler.mMotionHelper.expandLeavePip$1(false, true);
                        } else {
                            pipController2.mTouchHandler.mMotionHelper.expandLeavePip$1(z, false);
                        }
                    }

                    @Override // com.android.wm.shell.common.TaskStackListenerCallback
                    public final void onActivityUnpinned() {
                        PipController pipController2 = PipController.this;
                        ComponentName componentName = (ComponentName) PipUtils.getTopPipActivity(pipController2.mContext).first;
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 6066697896891529186L, 0, String.valueOf(componentName));
                        }
                        PipTouchHandler pipTouchHandler2 = pipController2.mTouchHandler;
                        if (componentName == null) {
                            PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler2.mPipDismissTargetHandler;
                            if (pipDismissTargetHandler.mTargetViewContainer.getParent() != null) {
                                pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
                            }
                            pipTouchHandler2.mFloatingContentCoordinator.allContentBounds.remove(pipTouchHandler2.mMotionHelper);
                        }
                        PipResizeGestureHandler pipResizeGestureHandler = pipTouchHandler2.mPipResizeGestureHandler;
                        pipResizeGestureHandler.mIsAttached = false;
                        pipResizeGestureHandler.mUserResizeBounds.setEmpty();
                        pipResizeGestureHandler.updateIsEnabled();
                        PipAppOpsListener pipAppOpsListener = pipController2.mAppOpsListener;
                        pipAppOpsListener.mAppOpsManager.stopWatchingMode(pipAppOpsListener.mAppOpsChangedListener);
                        PipInputConsumer pipInputConsumer2 = pipController2.mPipInputConsumer;
                        if (pipInputConsumer2.mInputEventReceiver == null) {
                            return;
                        }
                        try {
                            pipInputConsumer2.mWindowManager.destroyInputConsumer(pipInputConsumer2.mToken, 0);
                        } catch (RemoteException e3) {
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2654699908319398243L, 0, "PipInputConsumer", String.valueOf(e3));
                            }
                        }
                        pipInputConsumer2.mInputEventReceiver.dispose();
                        pipInputConsumer2.mInputEventReceiver = null;
                        ((HandlerExecutor) pipInputConsumer2.mMainExecutor).execute(new PipInputConsumer$$ExternalSyntheticLambda1(pipInputConsumer2, 1));
                    }
                });
                PipController.AnonymousClass3 anonymousClass3 = pipController.new AnonymousClass3();
                PipParamsChangedForwarder pipParamsChangedForwarder = pipController.mPipParamsChangedForwarder;
                if (!pipParamsChangedForwarder.mPipParamsChangedListeners.contains(anonymousClass3)) {
                    pipParamsChangedForwarder.mPipParamsChangedListeners.add(anonymousClass3);
                }
                pipController.mDisplayInsetsController.addInsetsChangedListener(pipDisplayLayoutState.mDisplayId, pipController.new AnonymousClass4());
                PipController$$ExternalSyntheticLambda12 pipController$$ExternalSyntheticLambda12 = new PipController$$ExternalSyntheticLambda12(pipController);
                TabletopModeController tabletopModeController = pipController.mTabletopModeController;
                if (!tabletopModeController.mListeners.contains(pipController$$ExternalSyntheticLambda12)) {
                    tabletopModeController.mListeners.add(pipController$$ExternalSyntheticLambda12);
                    pipController$$ExternalSyntheticLambda12.onTabletopModeChanged(tabletopModeController.isInTabletopMode());
                }
                pipController.mOneHandedController.ifPresent(new PipController$$ExternalSyntheticLambda13(0, pipController));
                PipMediaController pipMediaController = pipController.mMediaController;
                MediaSessionManager mediaSessionManager = pipMediaController.mMediaSessionManager;
                Intrinsics.checkNotNull(mediaSessionManager);
                PipMediaController$mSessionsChangedListener$1 pipMediaController$mSessionsChangedListener$1 = pipMediaController.mSessionsChangedListener;
                mediaSessionManager.removeOnActiveSessionsChangedListener(pipMediaController$mSessionsChangedListener$1);
                pipMediaController.mMediaSessionManager.addOnActiveSessionsChangedListener(null, UserHandle.CURRENT, pipMediaController.mHandlerExecutor, pipMediaController$mSessionsChangedListener$1);
                ShellController shellController = pipController.mShellController;
                shellController.addConfigurationChangeListener(pipController);
                shellController.addKeyguardChangeListener(pipController);
                shellController.mUserChangeListeners.remove(pipController);
                shellController.mUserChangeListeners.add(pipController);
                shellController.addExternalInterface("extra_shell_pip", new Supplier() { // from class: com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda14
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        PipController pipController2 = PipController.this;
                        pipController2.getClass();
                        return new PipController.IPipImpl(pipController2);
                    }
                }, pipController);
                break;
            case 1:
                PipController pipController2 = this.f$0;
                if (!pipController2.mIsKeyguardShowingOrAnimating && !pipController2.mPipBoundsState.isStashed()) {
                    PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipController2.mPipAnimationController.mCurrentAnimator;
                    if (pipTransitionAnimator != null && pipTransitionAnimator.isRunning()) {
                        HandlerExecutor handlerExecutor = (HandlerExecutor) pipController2.mMainExecutor;
                        PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda32 = pipController2.mMovePipInResponseToKeepClearAreasChangeCallback;
                        handlerExecutor.removeCallbacks(pipController$$ExternalSyntheticLambda32);
                        handlerExecutor.executeDelayed(pipController$$ExternalSyntheticLambda32, PipController.PIP_KEEP_CLEAR_AREAS_DELAY);
                        break;
                    } else {
                        pipController2.updatePipPositionForKeepClearAreas();
                        break;
                    }
                }
                break;
            case 2:
                PipTouchState pipTouchState = this.f$0.mTouchHandler.mTouchState;
                pipTouchState.mAllowTouches = true;
                if (pipTouchState.mIsUserInteracting) {
                    pipTouchState.reset();
                    break;
                }
                break;
            default:
                this.f$0.updateMovementBounds(null, false, false, false, null);
                break;
        }
    }
}
