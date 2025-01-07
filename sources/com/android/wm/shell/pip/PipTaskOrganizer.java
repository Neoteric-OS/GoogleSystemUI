package com.android.wm.shell.pip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.RotationUtils;
import android.view.Choreographer;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.window.TaskSnapshot;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ScreenshotUtils;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PipAnimationController;
import com.android.wm.shell.pip.PipSurfaceTransactionHelper;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip.phone.PhonePipMenuController;
import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda6;
import com.android.wm.shell.pip.phone.PipMenuView;
import com.android.wm.shell.pip.phone.PipMotionHelper$$ExternalSyntheticLambda1;
import com.android.wm.shell.pip.phone.PipResizeGestureHandler$$ExternalSyntheticLambda0;
import com.android.wm.shell.pip.phone.PipResizeGestureHandler$$ExternalSyntheticLambda2;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.pip.PipContentOverlay;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTaskOrganizer implements ShellTaskOrganizer.TaskListener, DisplayController.OnDisplaysChangedListener {
    public static final int EXTRA_CONTENT_OVERLAY_FADE_OUT_DELAY_MS = SystemProperties.getInt("persist.wm.debug.extra_content_overlay_fade_out_delay_ms", 400);
    public final Rect mAppBounds;
    public final Context mContext;
    public final int mCrossFadeAnimationDuration;
    public int mCurrentRotation;
    public SurfaceControl.Transaction mDeferredAnimEndTransaction;
    public ActivityManager.RunningTaskInfo mDeferredTaskInfo;
    public final int mEnterAnimationDuration;
    public final int mExitAnimationDuration;
    public boolean mHasFadeOut;
    public SurfaceControl mLeash;
    public final ShellExecutor mMainExecutor;
    public int mNextRotation;
    public PipController$$ExternalSyntheticLambda6 mOnDisplayIdChangeCallback;
    public PictureInPictureParams mPictureInPictureParams;
    public final AnonymousClass1 mPipAnimationCallback = new AnonymousClass1();
    public final PipAnimationController mPipAnimationController;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public PipResizeGestureHandler$$ExternalSyntheticLambda2 mPipFinishResizeWCTRunnable;
    public final PhonePipMenuController mPipMenuController;
    public SurfaceControl mPipOverlay;
    public final PipParamsChangedForwarder mPipParamsChangedForwarder;
    public final PipPerfHintController mPipPerfHintController;
    public final AnonymousClass3 mPipTransactionHandler;
    final PipTransitionController.PipTransitionCallback mPipTransitionCallback;
    public final PipTransitionController mPipTransitionController;
    public final PipTransitionState mPipTransitionState;
    public final PipUiEventLogger mPipUiEventLoggerLogger;
    public final Optional mSplitScreenOptional;
    public PipSurfaceTransactionHelper.SurfaceControlTransactionFactory mSurfaceControlTransactionFactory;
    public final PipSurfaceTransactionHelper mSurfaceTransactionHelper;
    public Rect mSwipeSourceRectHint;
    public final SyncTransactionQueue mSyncTransactionQueue;
    public ActivityManager.RunningTaskInfo mTaskInfo;
    public final ShellTaskOrganizer mTaskOrganizer;
    public WindowContainerToken mToken;
    public boolean mWaitForFixedRotation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.PipTaskOrganizer$1, reason: invalid class name */
    public final class AnonymousClass1 extends PipAnimationController.PipAnimationCallback {
        public boolean mIsCancelled;
        public PipPerfHintController.PipHighPerfSession mPipHighPerfSession;

        public AnonymousClass1() {
        }

        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationCancel(PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            SurfaceControl surfaceControl;
            int transitionDirection = pipTransitionAnimator.getTransitionDirection();
            this.mIsCancelled = true;
            boolean isInPipDirection = PipAnimationController.isInPipDirection(transitionDirection);
            PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
            if (isInPipDirection && (surfaceControl = pipTaskOrganizer.mPipOverlay) != null) {
                pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl, true);
            }
            pipTaskOrganizer.mPipTransitionController.sendOnPipTransitionCancelled(transitionDirection);
        }

        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationEnd(TaskInfo taskInfo, final SurfaceControl.Transaction transaction, PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            SurfaceControl surfaceControl;
            PipPerfHintController.PipHighPerfSession pipHighPerfSession = this.mPipHighPerfSession;
            if (pipHighPerfSession != null) {
                pipHighPerfSession.close();
                this.mPipHighPerfSession = null;
            }
            final int transitionDirection = pipTransitionAnimator.getTransitionDirection();
            boolean z = this.mIsCancelled;
            final PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
            if (z) {
                pipTaskOrganizer.sendOnPipTransitionFinished(transitionDirection);
                PipResizeGestureHandler$$ExternalSyntheticLambda2 pipResizeGestureHandler$$ExternalSyntheticLambda2 = pipTaskOrganizer.mPipFinishResizeWCTRunnable;
                if (pipResizeGestureHandler$$ExternalSyntheticLambda2 != null) {
                    pipResizeGestureHandler$$ExternalSyntheticLambda2.run();
                    pipTaskOrganizer.mPipFinishResizeWCTRunnable = null;
                    return;
                }
                return;
            }
            final int animationType = pipTransitionAnimator.getAnimationType();
            final Rect rect = pipTransitionAnimator.mDestinationBounds;
            boolean z2 = true;
            if (PipAnimationController.isInPipDirection(transitionDirection) && (surfaceControl = pipTaskOrganizer.mPipOverlay) != null) {
                pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl, true);
            }
            if (pipTaskOrganizer.mWaitForFixedRotation && animationType == 0 && transitionDirection == 2) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.scheduleFinishEnterPip(pipTaskOrganizer.mToken, rect);
                pipTaskOrganizer.mTaskOrganizer.applyTransaction(windowContainerTransaction);
                pipTaskOrganizer.mSurfaceTransactionHelper.round(transaction, pipTaskOrganizer.mLeash, pipTaskOrganizer.isInPip());
                pipTaskOrganizer.mDeferredAnimEndTransaction = transaction;
                return;
            }
            if (!PipAnimationController.isOutPipDirection(transitionDirection) && transitionDirection != 5) {
                z2 = false;
            }
            if (pipTaskOrganizer.mPipTransitionState.mState != 5 || z2) {
                transaction.addTransactionCommittedListener(pipTaskOrganizer.mMainExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$1$$ExternalSyntheticLambda1
                    @Override // android.view.SurfaceControl.TransactionCommittedListener
                    public final void onTransactionCommitted() {
                        PipTaskOrganizer pipTaskOrganizer2 = PipTaskOrganizer.this;
                        int i = PipTaskOrganizer.EXTRA_CONTENT_OVERLAY_FADE_OUT_DELAY_MS;
                        PipResizeGestureHandler$$ExternalSyntheticLambda2 pipResizeGestureHandler$$ExternalSyntheticLambda22 = pipTaskOrganizer2.mPipFinishResizeWCTRunnable;
                        if (pipResizeGestureHandler$$ExternalSyntheticLambda22 != null) {
                            pipResizeGestureHandler$$ExternalSyntheticLambda22.run();
                            pipTaskOrganizer2.mPipFinishResizeWCTRunnable = null;
                        }
                    }
                });
                Runnable runnable = new Runnable() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        PipTaskOrganizer.AnonymousClass1 anonymousClass1 = PipTaskOrganizer.AnonymousClass1.this;
                        SurfaceControl.Transaction transaction2 = transaction;
                        Rect rect2 = rect;
                        int i = transitionDirection;
                        int i2 = animationType;
                        PipTaskOrganizer pipTaskOrganizer2 = PipTaskOrganizer.this;
                        pipTaskOrganizer2.finishResize(transaction2, rect2, i, i2);
                        pipTaskOrganizer2.sendOnPipTransitionFinished(i);
                    }
                };
                if (pipTaskOrganizer.mPipMenuController.isMenuVisible()) {
                    Choreographer.getInstance().postCallback(4, new PipTaskOrganizer$$ExternalSyntheticLambda9(pipTaskOrganizer, runnable, 0), null);
                } else {
                    runnable.run();
                }
            }
        }

        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationStart(PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
            PipPerfHintController pipPerfHintController = pipTaskOrganizer.mPipPerfHintController;
            if (pipPerfHintController != null) {
                this.mPipHighPerfSession = pipPerfHintController.startSession(new PipTaskOrganizer$$ExternalSyntheticLambda1(1, this), "PipTaskOrganizer::mPipAnimationCallback");
            }
            int transitionDirection = pipTransitionAnimator.getTransitionDirection();
            this.mIsCancelled = false;
            if (transitionDirection == 2) {
                pipTaskOrganizer.mPipTransitionState.setTransitionState(3);
            }
            pipTaskOrganizer.mPipTransitionController.sendOnPipTransitionStarted(transitionDirection);
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.android.wm.shell.pip.PipTaskOrganizer$3] */
    public PipTaskOrganizer(Context context, SyncTransactionQueue syncTransactionQueue, PipTransitionState pipTransitionState, PipBoundsState pipBoundsState, PipDisplayLayoutState pipDisplayLayoutState, PipBoundsAlgorithm pipBoundsAlgorithm, PhonePipMenuController phonePipMenuController, PipAnimationController pipAnimationController, PipSurfaceTransactionHelper pipSurfaceTransactionHelper, PipTransitionController pipTransitionController, PipParamsChangedForwarder pipParamsChangedForwarder, Optional optional, Optional optional2, DisplayController displayController, PipUiEventLogger pipUiEventLogger, ShellTaskOrganizer shellTaskOrganizer, ShellExecutor shellExecutor) {
        PipTransitionController.PipTransitionCallback pipTransitionCallback = new PipTransitionController.PipTransitionCallback() { // from class: com.android.wm.shell.pip.PipTaskOrganizer.2
            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionFinished(int i) {
                PipTaskOrganizer pipTaskOrganizer;
                ActivityManager.RunningTaskInfo runningTaskInfo;
                if (i != 2 || (runningTaskInfo = (pipTaskOrganizer = PipTaskOrganizer.this).mDeferredTaskInfo) == null) {
                    return;
                }
                pipTaskOrganizer.onTaskInfoChanged(runningTaskInfo);
                pipTaskOrganizer.mDeferredTaskInfo = null;
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionCanceled(int i) {
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionStarted(int i, Rect rect) {
            }
        };
        this.mPipTransitionCallback = pipTransitionCallback;
        this.mPipTransactionHandler = new PipAnimationController.PipTransactionHandler() { // from class: com.android.wm.shell.pip.PipTaskOrganizer.3
            @Override // com.android.wm.shell.pip.PipAnimationController.PipTransactionHandler
            public final boolean handlePipTransaction(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, Rect rect, float f) {
                PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                if (!pipTaskOrganizer.mPipMenuController.isMenuVisible()) {
                    return false;
                }
                pipTaskOrganizer.mPipMenuController.movePipMenu(rect, transaction, surfaceControl);
                return true;
            }
        };
        this.mAppBounds = new Rect();
        this.mContext = context;
        this.mSyncTransactionQueue = syncTransactionQueue;
        this.mPipTransitionState = pipTransitionState;
        this.mPipBoundsState = pipBoundsState;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipMenuController = phonePipMenuController;
        this.mPipTransitionController = pipTransitionController;
        this.mPipParamsChangedForwarder = pipParamsChangedForwarder;
        this.mEnterAnimationDuration = context.getResources().getInteger(R.integer.config_pipEnterAnimationDuration);
        this.mExitAnimationDuration = context.getResources().getInteger(R.integer.config_pipExitAnimationDuration);
        this.mCrossFadeAnimationDuration = context.getResources().getInteger(R.integer.config_pipCrossfadeAnimationDuration);
        this.mSurfaceTransactionHelper = pipSurfaceTransactionHelper;
        this.mPipAnimationController = pipAnimationController;
        this.mPipUiEventLoggerLogger = pipUiEventLogger;
        this.mSurfaceControlTransactionFactory = new PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory();
        this.mSplitScreenOptional = optional;
        this.mPipPerfHintController = (PipPerfHintController) optional2.orElse(null);
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mMainExecutor = shellExecutor;
        if (PipUtils.isPip2ExperimentEnabled()) {
            return;
        }
        ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                pipTaskOrganizer.mTaskOrganizer.addListenerForType(pipTaskOrganizer, -4);
            }
        });
        pipTransitionController.mPipOrganizer = this;
        displayController.addDisplayWindowListener(this);
        pipTransitionController.mPipTransitionCallbacks.put(pipTransitionCallback, shellExecutor);
    }

    public static void logRemoteActions(PictureInPictureParams pictureInPictureParams) {
        StringJoiner stringJoiner = new StringJoiner("|", "[", "]");
        if (pictureInPictureParams.hasSetActions()) {
            pictureInPictureParams.getActions().forEach(new PipTaskOrganizer$$ExternalSyntheticLambda1(0, stringJoiner));
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -348090866542953596L, 0, "PipTaskOrganizer", String.valueOf(stringJoiner.toString()));
        }
    }

    public final PipAnimationController.PipTransitionAnimator animateResizePip(Rect rect, Rect rect2, Rect rect3, int i, int i2, float f) {
        Rect rect4;
        Rect rect5;
        if (this.mToken == null || this.mLeash == null) {
            if (!ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                return null;
            }
            ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 5014614599801648599L, 0, "PipTaskOrganizer");
            return null;
        }
        if (PipAnimationController.isInPipDirection(i)) {
            rect4 = rect3;
            if (!PipBoundsAlgorithm.isSourceRectHintValidForEnterPip(rect4, rect2)) {
                rect4 = null;
            }
        } else {
            rect4 = rect3;
        }
        int deltaRotation = this.mWaitForFixedRotation ? RotationUtils.deltaRotation(this.mCurrentRotation, this.mNextRotation) : 0;
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (deltaRotation != 0) {
            if (i == 2) {
                this.mPipDisplayLayoutState.rotateTo(this.mNextRotation);
                Rect displayBounds = pipBoundsState.mPipDisplayLayoutState.getDisplayBounds();
                rect2.set(pipBoundsAlgorithm.getEntryDestinationBounds());
                RotationUtils.rotateBounds(rect2, displayBounds, this.mNextRotation, this.mCurrentRotation);
                if (rect4 != null && (rect5 = this.mTaskInfo.displayCutoutInsets) != null && deltaRotation == 3) {
                    rect4.offset(rect5.left, rect5.top);
                }
            } else if (i == 3) {
                Rect rect6 = new Rect(rect2);
                RotationUtils.rotateBounds(rect6, pipBoundsState.mPipDisplayLayoutState.getDisplayBounds(), deltaRotation);
                rect4 = PipBoundsAlgorithm.getValidSourceHintRect(this.mPictureInPictureParams, rect6);
            }
        }
        Rect rect7 = rect4;
        Rect bounds = i == 6 ? pipBoundsState.getBounds() : rect;
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = this.mPipAnimationController.mCurrentAnimator;
        boolean z = pipTransitionAnimator != null && pipTransitionAnimator.isRunning();
        int i3 = deltaRotation;
        PipAnimationController.PipTransitionAnimator animator = this.mPipAnimationController.getAnimator(this.mTaskInfo, this.mLeash, bounds, rect, rect2, rect7, i, f, i3);
        PipAnimationController.PipTransitionAnimator transitionDirection = animator.setTransitionDirection(i);
        transitionDirection.mPipTransactionHandler = this.mPipTransactionHandler;
        transitionDirection.setDuration(i2);
        if (!z) {
            animator.setPipAnimationCallback(this.mPipAnimationCallback);
        }
        if (PipAnimationController.isInPipDirection(i)) {
            if (rect7 == null) {
                ActivityInfo activityInfo = this.mTaskInfo.topActivityInfo;
                if (activityInfo != null) {
                    Context context = this.mContext;
                    animator.reattachContentOverlay(new PipContentOverlay.PipAppIconOverlay(context, rect, rect2, new IconProvider(context).getIcon(activityInfo), pipBoundsState.mLauncherState.mAppIconSizePx));
                } else {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[3]) {
                        ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5787572776858302708L, 0, "PipTaskOrganizer");
                    }
                    animator.reattachContentOverlay(new PipContentOverlay.PipColorOverlay(this.mContext));
                }
            } else {
                TaskSnapshot taskSnapshot = PipUtils.getTaskSnapshot(this.mTaskInfo.launchIntoPipHostTaskId);
                if (taskSnapshot != null) {
                    animator.reattachContentOverlay(new PipContentOverlay.PipSnapshotOverlay(taskSnapshot, rect7));
                }
            }
            PipContentOverlay pipContentOverlay = animator.mContentOverlay;
            this.mPipOverlay = pipContentOverlay == null ? null : pipContentOverlay.mLeash;
            if (i3 != 0) {
                animator.setDestinationBounds(pipBoundsAlgorithm.getEntryDestinationBounds());
            }
        }
        animator.start();
        return animator;
    }

    public final void applyEnterPipSyncTransaction(Rect rect, Runnable runnable, SurfaceControl.Transaction transaction) {
        this.mPipMenuController.attach(this.mLeash);
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setActivityWindowingMode(this.mToken, 0);
        windowContainerTransaction.setBounds(this.mToken, rect);
        if (transaction != null) {
            windowContainerTransaction.setBoundsChangeTransaction(this.mToken, transaction);
        }
        SyncTransactionQueue syncTransactionQueue = this.mSyncTransactionQueue;
        syncTransactionQueue.queue(windowContainerTransaction);
        syncTransactionQueue.runInSync(new PipTaskOrganizer$$ExternalSyntheticLambda4(0, runnable));
    }

    public final void applyFinishBoundsResize(final WindowContainerTransaction windowContainerTransaction, int i, final boolean z) {
        if (i == 4) {
            this.mSplitScreenOptional.ifPresent(new Consumer() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                    boolean z2 = z;
                    ((SplitScreenController) obj).moveToStage(pipTaskOrganizer.mTaskInfo.taskId, !z2 ? 1 : 0, windowContainerTransaction);
                }
            });
        } else {
            this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
        builder.setParent(findTaskSurface$2(i));
    }

    public final SurfaceControl.Transaction createFinishResizeSurfaceTransaction(Rect rect) {
        SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
        SurfaceControl surfaceControl = this.mLeash;
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.crop(rect, transaction, surfaceControl);
        pipSurfaceTransactionHelper.resetScale(rect, transaction, this.mLeash);
        pipSurfaceTransactionHelper.round(transaction, this.mLeash, PipTransitionState.isInPip(this.mPipTransitionState.mState));
        return transaction;
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        printWriter.println(str + "PipTaskOrganizer");
        printWriter.println(m + "mTaskInfo=" + this.mTaskInfo);
        StringBuilder sb = new StringBuilder();
        sb.append(m);
        sb.append("mToken=");
        sb.append(this.mToken);
        sb.append(" binder=");
        WindowContainerToken windowContainerToken = this.mToken;
        sb.append(windowContainerToken != null ? windowContainerToken.asBinder() : null);
        printWriter.println(sb.toString());
        printWriter.println(m + "mLeash=" + this.mLeash);
        printWriter.println(m + "mPipOverlay=" + this.mPipOverlay);
        printWriter.println(m + "mState=" + this.mPipTransitionState.mState);
        printWriter.println(m + "mPictureInPictureParams=" + this.mPictureInPictureParams);
        this.mPipTransitionController.dump$1(printWriter, m);
        if (this.mPipPerfHintController != null) {
            String m2 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, "  ");
            printWriter.println(m + "PipPerfHintController");
            printWriter.println(m2 + "activeSessionCount=" + PipPerfHintController.PipHighPerfSession.sActiveSessions.size());
        }
    }

    public void enterPipWithAlphaAnimation(final Rect rect, final long j) {
        SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
        transaction.setAlpha(this.mLeash, 0.0f);
        transaction.apply();
        SurfaceControl.Transaction transaction2 = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
        SurfaceControl surfaceControl = this.mLeash;
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.crop(rect, transaction2, surfaceControl);
        pipSurfaceTransactionHelper.round(transaction2, this.mLeash, true);
        this.mPipTransitionState.setTransitionState(2);
        applyEnterPipSyncTransaction(rect, new Runnable() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                Rect rect2 = rect;
                long j2 = j;
                PipAnimationController.PipTransitionAnimator pipAnimationCallback = pipTaskOrganizer.mPipAnimationController.getAnimator(pipTaskOrganizer.mTaskInfo, pipTaskOrganizer.mLeash, rect2, 0.0f, 1.0f).setTransitionDirection(2).setPipAnimationCallback(pipTaskOrganizer.mPipAnimationCallback);
                pipAnimationCallback.mPipTransactionHandler = pipTaskOrganizer.mPipTransactionHandler;
                pipAnimationCallback.setDuration(j2).start();
                pipTaskOrganizer.mPipTransitionState.setTransitionState(3);
            }
        }, transaction2);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void exitPip(final int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.PipTaskOrganizer.exitPip(int, boolean):void");
    }

    public final void fadeExistingPip$1(boolean z) {
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3294132111576646088L, 0, "PipTaskOrganizer", String.valueOf(this.mLeash));
            }
        } else {
            PipAnimationController.PipTransitionAnimator transitionDirection = this.mPipAnimationController.getAnimator(this.mTaskInfo, this.mLeash, this.mPipBoundsState.getBounds(), z ? 0.0f : 1.0f, z ? 1.0f : 0.0f).setTransitionDirection(1);
            transitionDirection.mPipTransactionHandler = this.mPipTransactionHandler;
            transitionDirection.setDuration(z ? this.mEnterAnimationDuration : this.mExitAnimationDuration).start();
            this.mHasFadeOut = !z;
        }
    }

    public final void fadeOutAndRemoveOverlay(final SurfaceControl surfaceControl, boolean z) {
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(this.mCrossFadeAnimationDuration);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                SurfaceControl surfaceControl2 = surfaceControl;
                if (pipTaskOrganizer.mPipTransitionState.mState == 0) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3979649941011313715L, 0, "PipTaskOrganizer");
                    }
                    valueAnimator.removeAllUpdateListeners();
                    valueAnimator.removeAllListeners();
                    valueAnimator.cancel();
                    return;
                }
                if (surfaceControl2.isValid()) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) pipTaskOrganizer.mSurfaceControlTransactionFactory).getTransaction();
                    transaction.setAlpha(surfaceControl2, floatValue);
                    transaction.apply();
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.pip.PipTaskOrganizer.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                PipTaskOrganizer.this.removeContentOverlay(surfaceControl);
            }
        });
        ofFloat.setStartDelay(z ? 500L : EXTRA_CONTENT_OVERLAY_FADE_OUT_DELAY_MS);
        ofFloat.start();
    }

    public final SurfaceControl findTaskSurface$2(int i) {
        SurfaceControl surfaceControl;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        if (runningTaskInfo == null || (surfaceControl = this.mLeash) == null || runningTaskInfo.taskId != i) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "There is no surface for taskId="));
        }
        return surfaceControl;
    }

    public final void finishResize(SurfaceControl.Transaction transaction, Rect rect, int i, int i2) {
        PictureInPictureParams pictureInPictureParams;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        final Rect rect2 = new Rect(pipBoundsState.getBounds());
        pipBoundsState.setBounds(rect);
        boolean z = false;
        if (i == 5) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2824616701459812156L, 0, String.valueOf(this.mTaskInfo.topActivity), String.valueOf(this.mPipTransitionState));
            }
            if (Transitions.ENABLE_SHELL_TRANSITIONS) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.setBounds(this.mToken, (Rect) null);
                windowContainerTransaction.setWindowingMode(this.mToken, 0);
                windowContainerTransaction.reorder(this.mToken, false);
                this.mPipTransitionController.startExitTransition(1003, windowContainerTransaction, null);
                return;
            }
            try {
                WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                windowContainerTransaction2.setBounds(this.mToken, (Rect) null);
                this.mTaskOrganizer.applyTransaction(windowContainerTransaction2);
                ActivityTaskManager.getService().removeRootTasksInWindowingModes(new int[]{2});
                return;
            } catch (RemoteException e) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -6126564667458047953L, 0, "PipTaskOrganizer", String.valueOf(e));
                    return;
                }
                return;
            }
        }
        if (PipAnimationController.isInPipDirection(i) && i2 == 1) {
            finishResizeForMenu$1(rect);
            return;
        }
        WindowContainerTransaction windowContainerTransaction3 = new WindowContainerTransaction();
        prepareFinishResizeTransaction(rect, i, transaction, windowContainerTransaction3);
        SyncTransactionQueue syncTransactionQueue = this.mSyncTransactionQueue;
        if ((i != 7 && i != 6 && i != 8) || (pictureInPictureParams = this.mPictureInPictureParams) == null || pictureInPictureParams.isSeamlessResizeEnabled()) {
            if (this.mSplitScreenOptional.isPresent() && ((SplitScreenController) this.mSplitScreenOptional.get()).getActivateSplitPosition(this.mTaskInfo) == 0) {
                z = true;
            }
            applyFinishBoundsResize(windowContainerTransaction3, i, z);
            if (i == 4) {
                syncTransactionQueue.runInSync(new PipTaskOrganizer$$ExternalSyntheticLambda4(1, transaction));
            }
        } else {
            rect2.offsetTo(0, 0);
            final Rect rect3 = new Rect(0, 0, rect.width(), rect.height());
            SurfaceControl.Transaction transaction2 = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            SurfaceControl surfaceControl = this.mLeash;
            final SurfaceControl takeScreenshot = ScreenshotUtils.takeScreenshot(transaction2, surfaceControl, surfaceControl, rect2, 2147483645);
            if (takeScreenshot != null) {
                syncTransactionQueue.queue(windowContainerTransaction3);
                syncTransactionQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda6
                    @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
                    public final void runWithTransaction(SurfaceControl.Transaction transaction3) {
                        SurfaceControl surfaceControl2 = takeScreenshot;
                        Rect rect4 = rect2;
                        Rect rect5 = rect3;
                        PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                        PipResizeGestureHandler$$ExternalSyntheticLambda2 pipResizeGestureHandler$$ExternalSyntheticLambda2 = pipTaskOrganizer.mPipFinishResizeWCTRunnable;
                        if (pipResizeGestureHandler$$ExternalSyntheticLambda2 != null) {
                            pipResizeGestureHandler$$ExternalSyntheticLambda2.run();
                            pipTaskOrganizer.mPipFinishResizeWCTRunnable = null;
                        }
                        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = pipTaskOrganizer.mSurfaceTransactionHelper;
                        pipSurfaceTransactionHelper.mTmpDestinationRectF.set(rect5);
                        pipSurfaceTransactionHelper.scale(transaction3, surfaceControl2, rect4, pipSurfaceTransactionHelper.mTmpDestinationRectF, 0.0f);
                        pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl2, false);
                    }
                });
            } else {
                applyFinishBoundsResize(windowContainerTransaction3, i, false);
            }
        }
        finishResizeForMenu$1(rect);
    }

    public final void finishResizeForMenu$1(Rect rect) {
        if (isInPip()) {
            PhonePipMenuController phonePipMenuController = this.mPipMenuController;
            phonePipMenuController.movePipMenu(rect, null, null);
            phonePipMenuController.updateMenuBounds(rect);
        }
    }

    public final boolean isInPip() {
        return PipTransitionState.isInPip(this.mPipTransitionState.mState);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
        this.mCurrentRotation = configuration.windowConfiguration.getRotation();
    }

    public final void onEndOfSwipePipToHomeTransition() {
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        final Rect bounds = this.mPipBoundsState.getBounds();
        final SurfaceControl surfaceControl = this.mPipOverlay;
        SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
        SurfaceControl surfaceControl2 = this.mLeash;
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.resetScale(bounds, transaction, surfaceControl2);
        pipSurfaceTransactionHelper.crop(bounds, transaction, this.mLeash);
        pipSurfaceTransactionHelper.round(transaction, this.mLeash, isInPip());
        applyEnterPipSyncTransaction(bounds, new Runnable() { // from class: com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PipTaskOrganizer pipTaskOrganizer = PipTaskOrganizer.this;
                Rect rect = bounds;
                SurfaceControl surfaceControl3 = surfaceControl;
                pipTaskOrganizer.finishResizeForMenu$1(rect);
                pipTaskOrganizer.sendOnPipTransitionFinished(2);
                if (surfaceControl3 != null) {
                    pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl3, false);
                }
            }
        }, transaction);
        this.mPipTransitionState.mInSwipePipToHomeTransition = false;
        this.mPipOverlay = null;
    }

    public final void onExitPipFinished(TaskInfo taskInfo) {
        PipController$$ExternalSyntheticLambda6 pipController$$ExternalSyntheticLambda6;
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 7149452265929981864L, 0, String.valueOf(taskInfo.topActivity), String.valueOf(pipTransitionState), String.valueOf(this.mLeash));
        }
        if (this.mLeash == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3082465182618566532L, 0, null);
                return;
            }
            return;
        }
        this.mWaitForFixedRotation = false;
        this.mDeferredAnimEndTransaction = null;
        SurfaceControl surfaceControl = this.mPipOverlay;
        if (surfaceControl != null) {
            removeContentOverlay(surfaceControl);
            this.mPipOverlay = null;
        }
        if (pipTransitionState.mState != 0) {
            SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            transaction.setShadowRadius(this.mLeash, 0.0f);
            transaction.apply();
        }
        pipTransitionState.mInSwipePipToHomeTransition = false;
        this.mPictureInPictureParams = null;
        pipTransitionState.setTransitionState(0);
        this.mPipBoundsState.setBounds(new Rect());
        this.mPipUiEventLoggerLogger.setTaskInfo(null);
        PhonePipMenuController phonePipMenuController = this.mPipMenuController;
        phonePipMenuController.hideMenu();
        PipMenuView pipMenuView = phonePipMenuController.mPipMenuView;
        if (pipMenuView != null) {
            ((SurfaceControlViewHost) phonePipMenuController.mSystemWindows.mViewRoots.remove(pipMenuView)).release();
            phonePipMenuController.mPipMenuView = null;
        }
        phonePipMenuController.mLeash = null;
        this.mLeash = null;
        if (taskInfo.displayId == 0 || (pipController$$ExternalSyntheticLambda6 = this.mOnDisplayIdChangeCallback) == null) {
            return;
        }
        pipController$$ExternalSyntheticLambda6.accept(0);
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onFixedRotationFinished() {
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 8640602037472934694L, 0, String.valueOf(this.mTaskInfo), String.valueOf(pipTransitionState));
        }
        if (this.mWaitForFixedRotation) {
            if (Transitions.ENABLE_SHELL_TRANSITIONS) {
                this.mPipTransitionController.onFixedRotationFinished$1();
                this.mWaitForFixedRotation = false;
                this.mDeferredAnimEndTransaction = null;
                return;
            }
            int i = pipTransitionState.mState;
            if (i == 1) {
                if (pipTransitionState.mInSwipePipToHomeTransition) {
                    onEndOfSwipePipToHomeTransition();
                } else {
                    enterPipWithAlphaAnimation(this.mPipBoundsAlgorithm.getEntryDestinationBounds(), this.mEnterAnimationDuration);
                }
            } else if (i == 4 && this.mHasFadeOut) {
                fadeExistingPip$1(true);
            } else if (i == 3 && this.mDeferredAnimEndTransaction != null) {
                Rect rect = this.mPipAnimationController.mCurrentAnimator.mDestinationBounds;
                this.mPipBoundsState.setBounds(rect);
                applyEnterPipSyncTransaction(rect, new PipTaskOrganizer$$ExternalSyntheticLambda9(this, rect, 1), this.mDeferredAnimEndTransaction);
            }
            this.mWaitForFixedRotation = false;
            this.mDeferredAnimEndTransaction = null;
        }
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onFixedRotationStarted(int i) {
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -356536982456617445L, 0, String.valueOf(this.mTaskInfo), String.valueOf(pipTransitionState));
        }
        this.mNextRotation = i;
        this.mWaitForFixedRotation = true;
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.mPipTransitionController.onFixedRotationStarted();
        } else if (PipTransitionState.isInPip(pipTransitionState.mState)) {
            fadeExistingPip$1(false);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        PipController$$ExternalSyntheticLambda6 pipController$$ExternalSyntheticLambda6;
        Objects.requireNonNull(runningTaskInfo, "Requires RunningTaskInfo");
        this.mTaskInfo = runningTaskInfo;
        this.mToken = runningTaskInfo.token;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        pipTransitionState.setTransitionState(1);
        this.mLeash = surfaceControl;
        ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mTaskInfo;
        PictureInPictureParams pictureInPictureParams = runningTaskInfo2.pictureInPictureParams;
        this.mPictureInPictureParams = pictureInPictureParams;
        ComponentName componentName = runningTaskInfo2.topActivity;
        ActivityInfo activityInfo = runningTaskInfo2.topActivityInfo;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        pipBoundsState.setBoundsStateForEntry(componentName, activityInfo, pictureInPictureParams, pipBoundsAlgorithm);
        PictureInPictureParams pictureInPictureParams2 = this.mPictureInPictureParams;
        if (pictureInPictureParams2 != null) {
            List<RemoteAction> actions = pictureInPictureParams2.getActions();
            RemoteAction closeAction = this.mPictureInPictureParams.getCloseAction();
            PipParamsChangedForwarder pipParamsChangedForwarder = this.mPipParamsChangedForwarder;
            Iterator it = pipParamsChangedForwarder.mPipParamsChangedListeners.iterator();
            while (it.hasNext()) {
                PhonePipMenuController phonePipMenuController = PipController.this.mMenuController;
                phonePipMenuController.mAppActions = actions;
                phonePipMenuController.mCloseAction = closeAction;
                phonePipMenuController.updateMenuActions();
            }
            CharSequence title = this.mPictureInPictureParams.getTitle();
            if (title != null) {
                title.toString();
            }
            Iterator it2 = pipParamsChangedForwarder.mPipParamsChangedListeners.iterator();
            while (it2.hasNext()) {
                ((PipController.AnonymousClass3) it2.next()).getClass();
            }
            CharSequence subtitle = this.mPictureInPictureParams.getSubtitle();
            if (subtitle != null) {
                subtitle.toString();
            }
            Iterator it3 = pipParamsChangedForwarder.mPipParamsChangedListeners.iterator();
            while (it3.hasNext()) {
                ((PipController.AnonymousClass3) it3.next()).getClass();
            }
            logRemoteActions(this.mPictureInPictureParams);
        }
        ActivityManager.RunningTaskInfo runningTaskInfo3 = this.mTaskInfo;
        PipUiEventLogger pipUiEventLogger = this.mPipUiEventLoggerLogger;
        pipUiEventLogger.setTaskInfo(runningTaskInfo3);
        int i = runningTaskInfo.displayId;
        if (i != this.mPipDisplayLayoutState.mDisplayId && (pipController$$ExternalSyntheticLambda6 = this.mOnDisplayIdChangeCallback) != null) {
            pipController$$ExternalSyntheticLambda6.accept(i);
        }
        PictureInPictureParams pictureInPictureParams3 = this.mPictureInPictureParams;
        pipUiEventLogger.log((pictureInPictureParams3 == null || !pictureInPictureParams3.isLaunchIntoPip()) ? pipTransitionState.mInSwipePipToHomeTransition ? PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_AUTO_ENTER : PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_ENTER : PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_ENTER_CONTENT_PIP);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3303783365144426993L, 0, String.valueOf(this.mTaskInfo.topActivity), String.valueOf(pipTransitionState), String.valueOf(this.mTaskInfo.taskId));
        }
        if (pipTransitionState.mInSwipePipToHomeTransition) {
            if (!this.mWaitForFixedRotation) {
                onEndOfSwipePipToHomeTransition();
                return;
            } else {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -4076966838573842892L, 0, "PipTaskOrganizer");
                    return;
                }
                return;
            }
        }
        PipAnimationController pipAnimationController = this.mPipAnimationController;
        int i2 = pipAnimationController.mOneShotAnimationType;
        if (i2 == 1) {
            pipAnimationController.mOneShotAnimationType = 0;
            if (SystemClock.uptimeMillis() - pipAnimationController.mLastOneShotAlphaAnimationTime > 800) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -4268084010746694349L, 0, null);
                }
                i2 = 0;
            }
        }
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.mPipTransitionController.setEnterAnimationType(i2);
            return;
        }
        if (!this.mWaitForFixedRotation) {
            Rect entryDestinationBounds = pipBoundsAlgorithm.getEntryDestinationBounds();
            Rect bounds = this.mTaskInfo.configuration.windowConfiguration.getBounds();
            if (i2 == 0) {
                this.mPipMenuController.attach(this.mLeash);
                scheduleAnimateResizePip(bounds, entryDestinationBounds, 0.0f, PipBoundsAlgorithm.getValidSourceHintRect(runningTaskInfo.pictureInPictureParams, bounds), 2, this.mEnterAnimationDuration, null);
                pipTransitionState.setTransitionState(3);
                return;
            } else {
                if (i2 != 1) {
                    throw new RuntimeException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i2, "Unrecognized animation type: "));
                }
                enterPipWithAlphaAnimation(entryDestinationBounds, this.mEnterAnimationDuration);
                return;
            }
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1124617534213078549L, 16, String.valueOf(this.mTaskInfo.topActivity), String.valueOf(pipTransitionState), Long.valueOf(i2));
        }
        if (i2 != 1) {
            Rect bounds2 = this.mTaskInfo.configuration.windowConfiguration.getBounds();
            animateResizePip(bounds2, pipBoundsAlgorithm.getEntryDestinationBounds(), PipBoundsAlgorithm.getValidSourceHintRect(this.mPictureInPictureParams, bounds2), 2, this.mEnterAnimationDuration, 0.0f);
            pipTransitionState.setTransitionState(3);
        } else {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1784174170070791161L, 0, "PipTaskOrganizer");
            }
            SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            transaction.setAlpha(this.mLeash, 0.0f);
            transaction.show(this.mLeash);
            transaction.apply();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0185, code lost:
    
        if (com.android.wm.shell.common.pip.PipUtils.remoteActionsMatch(r14.getCloseAction(), r13.mPictureInPictureParams.getCloseAction()) == false) goto L61;
     */
    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onTaskInfoChanged(android.app.ActivityManager.RunningTaskInfo r14) {
        /*
            Method dump skipped, instructions count: 435
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.PipTaskOrganizer.onTaskInfoChanged(android.app.ActivityManager$RunningTaskInfo):void");
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -1012745480904164740L, 0, String.valueOf(this.mTaskInfo.topActivity), String.valueOf(pipTransitionState));
        }
        int i = pipTransitionState.mState;
        if (i == 0) {
            return;
        }
        boolean z2 = Transitions.ENABLE_SHELL_TRANSITIONS;
        if (z2 && i == 5) {
            return;
        }
        WindowContainerToken windowContainerToken = runningTaskInfo.token;
        Objects.requireNonNull(windowContainerToken, "Requires valid WindowContainerToken");
        if (windowContainerToken.asBinder() != this.mToken.asBinder()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[5]) {
                ProtoLogImpl_411527699.wtf(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -6968527793433002429L, 0, "PipTaskOrganizer", String.valueOf(windowContainerToken));
                return;
            }
            return;
        }
        PipAnimationController pipAnimationController = this.mPipAnimationController;
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipAnimationController.mCurrentAnimator;
        SurfaceControl surfaceControl = this.mPipOverlay;
        if (surfaceControl != null) {
            removeContentOverlay(surfaceControl);
        }
        if (pipTransitionAnimator != null) {
            pipTransitionAnimator.cancel();
            pipAnimationController.mCurrentAnimator = null;
        }
        onExitPipFinished(runningTaskInfo);
        if (z2) {
            this.mPipTransitionController.forceFinishTransition();
        }
    }

    public final void prepareFinishResizeTransaction(Rect rect, int i, SurfaceControl.Transaction transaction, WindowContainerTransaction windowContainerTransaction) {
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 5269185598020532283L, 0, "PipTaskOrganizer", String.valueOf(this.mLeash));
                return;
            }
            return;
        }
        if (PipAnimationController.isInPipDirection(i)) {
            windowContainerTransaction.setActivityWindowingMode(this.mToken, 0);
        } else if (PipAnimationController.isOutPipDirection(i)) {
            windowContainerTransaction.setWindowingMode(this.mToken, 0);
            windowContainerTransaction.setActivityWindowingMode(this.mToken, 0);
            rect = null;
        }
        this.mSurfaceTransactionHelper.round(transaction, this.mLeash, isInPip());
        windowContainerTransaction.setBounds(this.mToken, rect);
        if (i != 4) {
            windowContainerTransaction.setBoundsChangeTransaction(this.mToken, transaction);
        }
    }

    public final void removeContentOverlay(SurfaceControl surfaceControl) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
            ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -120396860233338433L, 0, String.valueOf(this.mTaskInfo), String.valueOf(this.mPipTransitionState), String.valueOf(surfaceControl));
        }
        SurfaceControl surfaceControl2 = this.mPipOverlay;
        if (surfaceControl2 != null) {
            if (surfaceControl2 != surfaceControl && ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 8642786175402916205L, 0, "PipTaskOrganizer", String.valueOf(surfaceControl), String.valueOf(this.mPipOverlay));
            }
            this.mPipOverlay = null;
            this.mAppBounds.setEmpty();
        }
        if (surfaceControl != null && surfaceControl.isValid()) {
            SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
            transaction.remove(surfaceControl);
            transaction.apply();
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
            ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -7547412454914050634L, 0, "PipTaskOrganizer", String.valueOf(surfaceControl));
        }
    }

    public final void removePip() {
        SurfaceControl surfaceControl;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (!PipTransitionState.isInPip(pipTransitionState.mState) || this.mToken == null || (surfaceControl = this.mLeash) == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[5]) {
                ProtoLogImpl_411527699.wtf(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -6380143430162039061L, 4, "PipTaskOrganizer", Long.valueOf(pipTransitionState.mState), String.valueOf(this.mToken), String.valueOf(this.mLeash));
                return;
            }
            return;
        }
        PipAnimationController.PipTransitionAnimator transitionDirection = this.mPipAnimationController.getAnimator(this.mTaskInfo, surfaceControl, this.mPipBoundsState.getBounds(), 1.0f, 0.0f).setTransitionDirection(5);
        transitionDirection.mPipTransactionHandler = this.mPipTransactionHandler;
        PipAnimationController.PipTransitionAnimator pipAnimationCallback = transitionDirection.setPipAnimationCallback(this.mPipAnimationCallback);
        pipAnimationCallback.setDuration(this.mExitAnimationDuration);
        pipAnimationCallback.setInterpolator(Interpolators.ALPHA_OUT);
        pipAnimationCallback.start();
        pipTransitionState.setTransitionState(5);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 7643269468650160002L, 0, String.valueOf(this.mTaskInfo.topActivity), String.valueOf(pipTransitionState));
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.reparent(surfaceControl, findTaskSurface$2(i));
    }

    public final void scheduleAnimateResizePip(Rect rect, Rect rect2, float f, Rect rect3, int i, int i2, PipResizeGestureHandler$$ExternalSyntheticLambda0 pipResizeGestureHandler$$ExternalSyntheticLambda0) {
        if (PipTransitionState.isInPip(this.mPipTransitionState.mState)) {
            animateResizePip(rect, rect2, rect3, i, i2, f);
            if (pipResizeGestureHandler$$ExternalSyntheticLambda0 != null) {
                pipResizeGestureHandler$$ExternalSyntheticLambda0.accept(rect2);
            }
        }
    }

    public final void scheduleFinishResizePip(Rect rect, int i, Consumer consumer) {
        int i2 = this.mPipTransitionState.mState;
        if (i2 < 3 || i2 == 5) {
            return;
        }
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 259530626753245214L, 4, "PipTaskOrganizer", Long.valueOf(r0.mState));
                return;
            }
            return;
        }
        finishResize(createFinishResizeSurfaceTransaction(rect), rect, i, -1);
        if (consumer != null) {
            consumer.accept(rect);
        }
    }

    public final void scheduleUserResizePip(Rect rect, Rect rect2, float f, PipMotionHelper$$ExternalSyntheticLambda1 pipMotionHelper$$ExternalSyntheticLambda1) {
        if (this.mToken == null || this.mLeash == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 5014614599801648599L, 0, "PipTaskOrganizer");
                return;
            }
            return;
        }
        if (rect.isEmpty() || rect2.isEmpty()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3648754248347208168L, 0, "PipTaskOrganizer");
                return;
            }
            return;
        }
        SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
        SurfaceControl surfaceControl = this.mLeash;
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.mTmpDestinationRectF.set(rect2);
        pipSurfaceTransactionHelper.scale(transaction, surfaceControl, rect, pipSurfaceTransactionHelper.mTmpDestinationRectF, f);
        pipSurfaceTransactionHelper.round(transaction, this.mLeash, rect, rect2);
        PhonePipMenuController phonePipMenuController = this.mPipMenuController;
        if (phonePipMenuController.isMenuVisible()) {
            phonePipMenuController.movePipMenu(rect2, transaction, this.mLeash);
        } else {
            transaction.apply();
        }
        if (pipMotionHelper$$ExternalSyntheticLambda1 != null) {
            pipMotionHelper$$ExternalSyntheticLambda1.accept(rect2);
        }
    }

    public void sendOnPipTransitionFinished(int i) {
        if (i == 2) {
            this.mPipTransitionState.setTransitionState(4);
        }
        this.mPipTransitionController.sendOnPipTransitionFinished(i);
    }

    public final void setPipVisibility(boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1416117663524534113L, 0, String.valueOf(runningTaskInfo != null ? runningTaskInfo.topActivity : null), String.valueOf(this.mPipTransitionState), String.valueOf(z));
        }
        if (isInPip()) {
            SurfaceControl surfaceControl = this.mLeash;
            if (surfaceControl == null || !surfaceControl.isValid()) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                    ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 5221631017727243831L, 0, "PipTaskOrganizer", String.valueOf(this.mLeash));
                }
            } else {
                SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) this.mSurfaceControlTransactionFactory).getTransaction();
                SurfaceControl surfaceControl2 = this.mLeash;
                float f = z ? 1.0f : 0.0f;
                this.mSurfaceTransactionHelper.getClass();
                transaction.setAlpha(surfaceControl2, f);
                transaction.apply();
            }
        }
    }

    public void setSurfaceControlTransactionFactory(PipSurfaceTransactionHelper.SurfaceControlTransactionFactory surfaceControlTransactionFactory) {
        this.mSurfaceControlTransactionFactory = surfaceControlTransactionFactory;
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final boolean supportCompatUI() {
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PipTaskOrganizer:");
        int i = ShellTaskOrganizer.$r8$clinit;
        sb.append("TASK_LISTENER_TYPE_PIP");
        return sb.toString();
    }

    public final void updateAnimatorBounds(Rect rect) {
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = this.mPipAnimationController.mCurrentAnimator;
        if (pipTransitionAnimator == null || !pipTransitionAnimator.isRunning()) {
            return;
        }
        if (pipTransitionAnimator.getAnimationType() == 0) {
            if (this.mWaitForFixedRotation) {
                Rect displayBounds = this.mPipBoundsState.mPipDisplayLayoutState.getDisplayBounds();
                Rect rect2 = new Rect(rect);
                RotationUtils.rotateBounds(rect2, displayBounds, this.mNextRotation, this.mCurrentRotation);
                pipTransitionAnimator.updateEndValue(rect2);
            } else {
                pipTransitionAnimator.updateEndValue(rect);
            }
        }
        pipTransitionAnimator.setDestinationBounds(rect);
    }

    public final void scheduleAnimateResizePip(Rect rect, int i, int i2) {
        if (this.mWaitForFixedRotation) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 3575265343742099329L, 0, "PipTaskOrganizer");
                return;
            }
            return;
        }
        scheduleAnimateResizePip(this.mPipBoundsState.getBounds(), rect, 0.0f, null, i2, i, null);
    }
}
