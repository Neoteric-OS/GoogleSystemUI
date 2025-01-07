package com.android.wm.shell.pip;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.RotationUtils;
import android.util.Slog;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PipAnimationController;
import com.android.wm.shell.pip.phone.PhonePipMenuController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.pip.PipContentOverlay;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.HomeTransitionObserver;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTransition extends PipTransitionController {
    public IBinder mCleanupTransition;
    public final Context mContext;
    public WindowContainerToken mCurrentPipTaskToken;
    public int mEndFixedRotation;
    public int mEnterAnimationType;
    public final int mEnterExitAnimationDuration;
    public final Rect mExitDestinationBounds;
    public IBinder mExitTransition;
    public Transitions.TransitionFinishCallback mFinishCallback;
    public SurfaceControl.Transaction mFinishTransaction;
    public int mFixedRotationState;
    public boolean mHasFadeOut;
    public final HomeTransitionObserver mHomeTransitionObserver;
    public IBinder mMoveToBackTransition;
    public final PipAnimationController mPipAnimationController;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public final PipTransitionState mPipTransitionState;
    public WindowContainerToken mRequestedEnterTask;
    public IBinder mRequestedEnterTransition;
    public final Optional mSplitScreenOptional;
    public final PipSurfaceTransactionHelper mSurfaceTransactionHelper;
    public final AnonymousClass1 mTransactionConsumer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.PipTransition$1, reason: invalid class name */
    public final class AnonymousClass1 extends PipAnimationController.PipTransactionHandler {
        @Override // com.android.wm.shell.pip.PipAnimationController.PipTransactionHandler
        public final boolean handlePipTransaction(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, Rect rect, float f) {
            return true;
        }
    }

    public PipTransition(Context context, ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, Transitions transitions, PipBoundsState pipBoundsState, PipDisplayLayoutState pipDisplayLayoutState, PipTransitionState pipTransitionState, PhonePipMenuController phonePipMenuController, PipBoundsAlgorithm pipBoundsAlgorithm, PipAnimationController pipAnimationController, PipSurfaceTransactionHelper pipSurfaceTransactionHelper, HomeTransitionObserver homeTransitionObserver, Optional optional) {
        super(shellInit, shellTaskOrganizer, transitions, pipBoundsState, phonePipMenuController, pipBoundsAlgorithm);
        this.mEnterAnimationType = 0;
        this.mExitDestinationBounds = new Rect();
        this.mFixedRotationState = 0;
        this.mTransactionConsumer = new AnonymousClass1();
        this.mContext = context;
        this.mPipTransitionState = pipTransitionState;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mPipAnimationController = pipAnimationController;
        this.mEnterExitAnimationDuration = context.getResources().getInteger(R.integer.config_pipResizeAnimationDuration);
        this.mSurfaceTransactionHelper = pipSurfaceTransactionHelper;
        this.mHomeTransitionObserver = homeTransitionObserver;
        this.mSplitScreenOptional = optional;
    }

    public static TransitionInfo.Change getPipChange(TransitionInfo transitionInfo) {
        for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
            if (change.getTaskInfo() != null && change.getTaskInfo().getWindowingMode() == 2) {
                return change;
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void augmentRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo, WindowContainerTransaction windowContainerTransaction) {
        if (transitionRequestInfo.getType() != 10) {
            throw new IllegalStateException("Called PiP augmentRequest when request has no PiP");
        }
        if (this.mEnterAnimationType == 1) {
            this.mRequestedEnterTransition = iBinder;
            this.mRequestedEnterTask = transitionRequestInfo.getTriggerTask().token;
            windowContainerTransaction.setActivityWindowingMode(transitionRequestInfo.getTriggerTask().token, 0);
            windowContainerTransaction.setBounds(transitionRequestInfo.getTriggerTask().token, this.mPipBoundsAlgorithm.getEntryDestinationBounds());
        }
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        printWriter.println(str + "PipTransition");
        printWriter.println(m + "mCurrentPipTaskToken=" + this.mCurrentPipTaskToken);
        printWriter.println(m + "mFinishCallback=" + this.mFinishCallback);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void end(PipTaskOrganizer$$ExternalSyntheticLambda11 pipTaskOrganizer$$ExternalSyntheticLambda11) {
        PipAnimationController pipAnimationController = this.mPipAnimationController;
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipAnimationController.mCurrentAnimator;
        if (pipTransitionAnimator != null && pipTransitionAnimator.isRunning()) {
            pipAnimationController.mCurrentAnimator.end();
        }
        if (pipTaskOrganizer$$ExternalSyntheticLambda11 != null) {
            pipTaskOrganizer$$ExternalSyntheticLambda11.run();
        }
    }

    public final void fadeEnteredPipIfNeed(boolean z) {
        if (this.mPipTransitionState.hasEnteredPip()) {
            if (z && this.mHasFadeOut) {
                this.mTransitions.runOnIdle(new Runnable() { // from class: com.android.wm.shell.pip.PipTransition$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        PipTransition pipTransition = PipTransition.this;
                        if (pipTransition.mHasFadeOut && pipTransition.mPipTransitionState.hasEnteredPip()) {
                            pipTransition.fadeExistingPip(true);
                        }
                    }
                });
            } else {
                if (z || this.mHasFadeOut) {
                    return;
                }
                fadeExistingPip(false);
            }
        }
    }

    public final void fadeExistingPip(final boolean z) {
        PipTaskOrganizer pipTaskOrganizer = this.mPipOrganizer;
        SurfaceControl surfaceControl = pipTaskOrganizer.mLeash;
        ActivityManager.RunningTaskInfo runningTaskInfo = pipTaskOrganizer.mTaskInfo;
        if (surfaceControl == null || !surfaceControl.isValid() || runningTaskInfo == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -7332491239584809957L, 0, "PipTransition", String.valueOf(surfaceControl));
                return;
            }
            return;
        }
        float f = z ? 0.0f : 1.0f;
        float f2 = z ? 1.0f : 0.0f;
        PipAnimationController.PipTransactionHandler pipTransactionHandler = new PipAnimationController.PipTransactionHandler() { // from class: com.android.wm.shell.pip.PipTransition.2
            @Override // com.android.wm.shell.pip.PipAnimationController.PipTransactionHandler
            public final boolean handlePipTransaction(SurfaceControl surfaceControl2, SurfaceControl.Transaction transaction, Rect rect, float f3) {
                if (f3 != 0.0f) {
                    return false;
                }
                if (z) {
                    transaction.setPosition(surfaceControl2, rect.left, rect.top);
                    return false;
                }
                Rect displayBounds = PipTransition.this.mPipDisplayLayoutState.getDisplayBounds();
                float max = Math.max(displayBounds.width(), displayBounds.height());
                transaction.setPosition(surfaceControl2, max, max);
                return false;
            }
        };
        PipAnimationController.PipTransitionAnimator transitionDirection = this.mPipAnimationController.getAnimator(runningTaskInfo, surfaceControl, this.mPipBoundsState.getBounds(), f, f2).setTransitionDirection(1);
        transitionDirection.mPipTransactionHandler = pipTransactionHandler;
        transitionDirection.setDuration(this.mEnterExitAnimationDuration).start();
        this.mHasFadeOut = !z;
    }

    public final TransitionInfo.Change findCurrentPipTaskChange(TransitionInfo transitionInfo) {
        if (this.mCurrentPipTaskToken == null) {
            return null;
        }
        for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
            if (this.mCurrentPipTaskToken.equals(change.getContainer())) {
                return change;
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void forceFinishTransition() {
        this.mCurrentPipTaskToken = null;
        this.mFixedRotationState = 0;
        Transitions.TransitionFinishCallback transitionFinishCallback = this.mFinishCallback;
        if (transitionFinishCallback == null) {
            return;
        }
        transitionFinishCallback.onTransitionFinished(null);
        this.mFinishCallback = null;
        this.mFinishTransaction = null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        if (transitionRequestInfo.getType() == 10) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 407213498667646561L, 0, "PipTransition");
            }
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            augmentRequest(iBinder, transitionRequestInfo, windowContainerTransaction);
            return windowContainerTransaction;
        }
        if (transitionRequestInfo.getType() != 4 || transitionRequestInfo.getTriggerTask() == null || transitionRequestInfo.getTriggerTask().getWindowingMode() != 2) {
            return null;
        }
        this.mMoveToBackTransition = iBinder;
        this.mPipTransitionState.setTransitionState(5);
        return new WindowContainerTransaction();
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final boolean handleRotateDisplay(int i, int i2, WindowContainerTransaction windowContainerTransaction) {
        if (this.mRequestedEnterTransition == null || this.mEnterAnimationType != 1 || RotationUtils.deltaRotation(i, i2) == 0) {
            return false;
        }
        this.mPipDisplayLayoutState.rotateTo(i2);
        windowContainerTransaction.setBounds(this.mRequestedEnterTask, this.mPipBoundsAlgorithm.getEntryDestinationBounds());
        return true;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final boolean isEnteringPip(TransitionInfo.Change change, int i) {
        if (change.getTaskInfo() == null || change.getTaskInfo().getWindowingMode() != 2 || change.getContainer().equals(this.mCurrentPipTaskToken)) {
            return false;
        }
        if (i == 10 || i == 1 || i == 3 || i == 6) {
            return true;
        }
        Slog.e("PipTransition", "Found new PIP in transition with mis-matched type=" + WindowManager.transitTypeToString(i), new Throwable());
        return false;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final boolean isInSwipePipToHomeTransition() {
        return this.mPipTransitionState.mInSwipePipToHomeTransition;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        end(null);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void onFinishResize(TaskInfo taskInfo, Rect rect, int i, SurfaceControl.Transaction transaction) {
        WindowContainerTransaction windowContainerTransaction;
        SurfaceControl.Transaction transaction2;
        boolean isInPipDirection = PipAnimationController.isInPipDirection(i);
        if (isInPipDirection) {
            this.mPipTransitionState.setTransitionState(4);
        }
        if ((this.mExitTransition == null || this.mFinishTransaction != null) && this.mFinishCallback != null) {
            SurfaceControl surfaceControl = this.mPipOrganizer.mLeash;
            boolean z = surfaceControl != null && surfaceControl.isValid();
            boolean isOutPipDirection = PipAnimationController.isOutPipDirection(i);
            PipAnimationController pipAnimationController = this.mPipAnimationController;
            PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
            if (isOutPipDirection) {
                if (this.mFixedRotationState != 2 && (transaction2 = this.mFinishTransaction) != null) {
                    transaction2.merge(transaction);
                }
                windowContainerTransaction = null;
            } else {
                windowContainerTransaction = new WindowContainerTransaction();
                if (PipAnimationController.isInPipDirection(i)) {
                    windowContainerTransaction.setActivityWindowingMode(taskInfo.token, 0);
                    windowContainerTransaction.setBounds(taskInfo.token, rect);
                    PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipAnimationController.mCurrentAnimator;
                    if (pipTransitionAnimator != null && pipTransitionAnimator.getEndValue().equals(Float.valueOf(0.0f))) {
                        transaction.addTransactionCommittedListener(this.mTransitions.mMainExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.wm.shell.pip.PipTransition$$ExternalSyntheticLambda0
                            @Override // android.view.SurfaceControl.TransactionCommittedListener
                            public final void onTransactionCommitted() {
                                PipTransition.this.fadeExistingPip(true);
                            }
                        });
                    }
                } else {
                    windowContainerTransaction.setBounds(taskInfo.token, (Rect) null);
                }
                if (z) {
                    pipSurfaceTransactionHelper.crop(rect, transaction, surfaceControl);
                    pipSurfaceTransactionHelper.resetScale(rect, transaction, surfaceControl);
                    pipSurfaceTransactionHelper.round(transaction, surfaceControl, true);
                    PipTaskOrganizer pipTaskOrganizer = this.mPipOrganizer;
                    Rect rect2 = pipTaskOrganizer.mAppBounds;
                    if (pipTaskOrganizer.mPipOverlay != null && !rect2.isEmpty()) {
                        Rect rect3 = new Rect(rect);
                        int overlaySize = PipContentOverlay.PipAppIconOverlay.getOverlaySize(rect2, rect);
                        rect3.offsetTo((rect.width() - overlaySize) / 2, (rect.height() - overlaySize) / 2);
                        pipSurfaceTransactionHelper.resetScale(rect3, transaction, this.mPipOrganizer.mPipOverlay);
                    }
                }
                windowContainerTransaction.setBoundsChangeTransaction(taskInfo.token, transaction);
            }
            int displayRotation = taskInfo.getConfiguration().windowConfiguration.getDisplayRotation();
            if (isInPipDirection && this.mFixedRotationState == 2 && this.mEndFixedRotation != displayRotation && z) {
                PipAnimationController.PipTransitionAnimator pipTransitionAnimator2 = pipAnimationController.mCurrentAnimator;
                Rect displayBounds = this.mPipDisplayLayoutState.getDisplayBounds();
                Rect rect4 = new Rect(rect);
                RotationUtils.rotateBounds(rect4, displayBounds, this.mEndFixedRotation, displayRotation);
                if (!rect4.equals(pipTransitionAnimator2.getEndValue())) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                        ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2953606837466350454L, 0, "PipTransition");
                    }
                    RotationUtils.rotateBounds(rect4, displayBounds, this.mEndFixedRotation, displayRotation);
                    pipSurfaceTransactionHelper.crop(rect4, this.mFinishTransaction, surfaceControl);
                }
            }
            this.mFinishTransaction = null;
            Transitions.TransitionFinishCallback transitionFinishCallback = this.mFinishCallback;
            this.mFinishCallback = null;
            transitionFinishCallback.onTransitionFinished(windowContainerTransaction);
        }
        this.mFixedRotationState = 0;
        PhonePipMenuController phonePipMenuController = this.mPipMenuController;
        phonePipMenuController.movePipMenu(rect, null, null);
        phonePipMenuController.updateMenuBounds(rect);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void onFixedRotationFinished$1() {
        fadeEnteredPipIfNeed(true);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void onFixedRotationStarted() {
        if (this.mFixedRotationState == 0) {
            this.mFixedRotationState = 1;
        }
        fadeEnteredPipIfNeed(false);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void onInit() {
        if (PipUtils.isPip2ExperimentEnabled()) {
            return;
        }
        this.mTransitions.addHandler(this);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        boolean z2;
        this.mFixedRotationState = 0;
        if (iBinder != this.mExitTransition) {
            return;
        }
        PipAnimationController pipAnimationController = this.mPipAnimationController;
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipAnimationController.mCurrentAnimator;
        if (pipTransitionAnimator != null) {
            pipTransitionAnimator.cancel();
            pipAnimationController.mCurrentAnimator = null;
            z2 = true;
        } else {
            z2 = false;
        }
        this.mExitTransition = null;
        if (z2) {
            PipTaskOrganizer pipTaskOrganizer = this.mPipOrganizer;
            ActivityManager.RunningTaskInfo runningTaskInfo = pipTaskOrganizer.mTaskInfo;
            if (runningTaskInfo != null) {
                if (z) {
                    sendOnPipTransitionFinished(3);
                    this.mPipOrganizer.onExitPipFinished(runningTaskInfo);
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    PipTaskOrganizer pipTaskOrganizer2 = this.mPipOrganizer;
                    windowContainerTransaction.setWindowingMode(pipTaskOrganizer2.mToken, 0);
                    windowContainerTransaction.setActivityWindowingMode(pipTaskOrganizer2.mToken, 0);
                    windowContainerTransaction.setBounds(runningTaskInfo.token, (Rect) null);
                    this.mPipOrganizer.applyFinishBoundsResize(windowContainerTransaction, 3, false);
                } else {
                    SurfaceControl surfaceControl = pipTaskOrganizer.mLeash;
                    PipBoundsState pipBoundsState = this.mPipBoundsState;
                    startExpandAnimation(runningTaskInfo, surfaceControl, pipBoundsState.getBounds(), pipBoundsState.getBounds(), new Rect(this.mExitDestinationBounds), 0, null);
                }
            }
            this.mExitDestinationBounds.setEmpty();
            this.mCurrentPipTaskToken = null;
        }
    }

    public final void removePipImmediately(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, Transitions.TransitionFinishCallback transitionFinishCallback, TaskInfo taskInfo) {
        transaction.apply();
        if (findCurrentPipTaskChange(transitionInfo) == null && ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
            ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 4544289263338730413L, 0, null);
        }
        this.mPipOrganizer.onExitPipFinished(taskInfo);
        transitionFinishCallback.onTransitionFinished(null);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void setEnterAnimationType(int i) {
        this.mEnterAnimationType = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x037c  */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r35, android.window.TransitionInfo r36, android.view.SurfaceControl.Transaction r37, final android.view.SurfaceControl.Transaction r38, final com.android.wm.shell.transition.Transitions.TransitionFinishCallback r39) {
        /*
            Method dump skipped, instructions count: 1532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.PipTransition.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0089, code lost:
    
        if (r3.mPipTransitionState.mInSwipePipToHomeTransition != false) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0269  */
    @Override // com.android.wm.shell.pip.PipTransitionController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startEnterAnimation(android.window.TransitionInfo.Change r33, android.view.SurfaceControl.Transaction r34, android.view.SurfaceControl.Transaction r35, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r36) {
        /*
            Method dump skipped, instructions count: 776
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.PipTransition.startEnterAnimation(android.window.TransitionInfo$Change, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):void");
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void startExitTransition(int i, WindowContainerTransaction windowContainerTransaction, Rect rect) {
        if (rect != null) {
            this.mExitDestinationBounds.set(rect);
        }
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = this.mPipAnimationController.mCurrentAnimator;
        if (pipTransitionAnimator != null && pipTransitionAnimator.isRunning()) {
            pipTransitionAnimator.cancel();
        }
        this.mExitTransition = this.mTransitions.startTransition(i, windowContainerTransaction, this);
    }

    public final void startExpandAnimation(TaskInfo taskInfo, SurfaceControl surfaceControl, Rect rect, Rect rect2, Rect rect3, int i, SurfaceControl.Transaction transaction) {
        PipAnimationController.PipTransitionAnimator animator = this.mPipAnimationController.getAnimator(taskInfo, surfaceControl, rect, rect2, rect3, PipBoundsAlgorithm.getValidSourceHintRect(taskInfo.pictureInPictureParams, rect3), 3, 0.0f, i);
        animator.setTransitionDirection(3).setDuration(this.mEnterExitAnimationDuration);
        if (transaction != null) {
            animator.mPipTransactionHandler = this.mTransactionConsumer;
            animator.applySurfaceControlTransaction(surfaceControl, transaction, 0.0f);
            transaction.apply();
        }
        PipAnimationController.PipTransitionAnimator pipAnimationCallback = animator.setPipAnimationCallback(this.mPipAnimationCallback);
        pipAnimationCallback.mPipTransactionHandler = this.mPipOrganizer.mPipTransactionHandler;
        pipAnimationCallback.start();
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void syncPipSurfaceState(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        TransitionInfo.Change findCurrentPipTaskChange = findCurrentPipTaskChange(transitionInfo);
        if (findCurrentPipTaskChange == null) {
            return;
        }
        updatePipForUnhandledTransition(findCurrentPipTaskChange, transaction, transaction2);
    }

    public final void updatePipForUnhandledTransition(TransitionInfo.Change change, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        SurfaceControl leash = change.getLeash();
        PipTaskOrganizer pipTaskOrganizer = this.mPipOrganizer;
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipTaskOrganizer.mPipAnimationController.mCurrentAnimator;
        Rect bounds = (pipTransitionAnimator == null || !pipTransitionAnimator.isRunning()) ? pipTaskOrganizer.mPipBoundsState.getBounds() : new Rect(pipTransitionAnimator.mDestinationBounds);
        boolean isInPip = PipTransitionState.isInPip(this.mPipTransitionState.mState);
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.crop(bounds, transaction, leash);
        pipSurfaceTransactionHelper.round(transaction, leash, isInPip);
        pipSurfaceTransactionHelper.shadow(transaction, leash, isInPip);
        pipSurfaceTransactionHelper.crop(bounds, transaction2, leash);
        pipSurfaceTransactionHelper.round(transaction2, leash, isInPip);
        pipSurfaceTransactionHelper.shadow(transaction2, leash, isInPip);
        if (isInPip && this.mHasFadeOut) {
            transaction.setAlpha(leash, 0.0f);
            transaction2.setAlpha(leash, 0.0f);
        }
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void end() {
        end(null);
    }
}
