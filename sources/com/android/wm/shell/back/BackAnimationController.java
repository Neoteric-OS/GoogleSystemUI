package com.android.wm.shell.back;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.view.IRemoteAnimationRunner;
import android.view.KeyEvent;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.BackAnimationAdapter;
import android.window.BackMotionEvent;
import android.window.BackNavigationInfo;
import android.window.BackTouchTracker;
import android.window.IBackAnimationFinishedCallback;
import android.window.IBackAnimationRunner;
import android.window.IOnBackInvokedCallback;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.policy.SystemBarUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.LatencyTracker;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda6;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda7;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BackAnimationController implements RemoteCallable, ConfigurationChangeListener {
    public static final boolean IS_ENABLED;
    public IOnBackInvokedCallback mActiveCallback;
    public final IActivityTaskManager mActivityTaskManager;
    public final BackAnimationBackground mAnimationBackground;
    public final BackAnimationController$$ExternalSyntheticLambda3 mAnimationTimeoutRunnable;
    RemoteAnimationTarget[] mApps;
    public final BackAnimationImpl mBackAnimation;
    BackAnimationAdapter mBackAnimationAdapter;
    public IBackAnimationFinishedCallback mBackAnimationFinishedCallback;
    public BackNavigationInfo mBackNavigationInfo;
    final BackTransitionHandler mBackTransitionHandler;
    public final Context mContext;
    public BackTouchTracker mCurrentTracker;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda7 mCustomizer;
    public final Handler mHandler;
    public final LatencyTracker mLatencyTracker;
    final RemoteCallback mNavigationObserver;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda6 mPilferPointerCallback;
    public int mPreviousNavigationType;
    public BackTouchTracker mQueuedTracker;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda7 mRequestTopUiCallback;
    public final boolean mRequirePointerPilfer;
    public final ShellBackAnimationRegistry mShellBackAnimationRegistry;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final ShellExecutor mShellExecutor;
    final Rect mTouchableArea;
    public boolean mTrackingLatency;
    public final Transitions mTransitions;
    public final WindowManager mWindowManager;
    public final AtomicBoolean mEnableAnimations = new AtomicBoolean(false);
    public boolean mBackGestureStarted = false;
    public boolean mPostCommitAnimationInProgress = false;
    public boolean mRealCallbackInvoked = false;
    public boolean mShouldStartOnNextMoveEvent = false;
    public boolean mOnBackStartDispatched = false;
    public boolean mThresholdCrossed = false;
    public boolean mPointersPilfered = false;
    public boolean mReceivedNullNavigationInfo = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.back.BackAnimationController$1, reason: invalid class name */
    public final class AnonymousClass1 implements RemoteCallback.OnResultListener {
        public AnonymousClass1() {
        }

        public final void onResult(Bundle bundle) {
            BackAnimationController backAnimationController = BackAnimationController.this;
            ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new BackAnimationController$1$$ExternalSyntheticLambda0(0, this));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.back.BackAnimationController$3, reason: invalid class name */
    public final class AnonymousClass3 extends IBackAnimationRunner.Stub {
        public AnonymousClass3() {
        }

        public final void onAnimationCancelled() {
            BackAnimationController backAnimationController = BackAnimationController.this;
            ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new BackAnimationController$1$$ExternalSyntheticLambda0(1, this));
        }

        public final void onAnimationStart(final RemoteAnimationTarget[] remoteAnimationTargetArr, final IBinder iBinder, final IBackAnimationFinishedCallback iBackAnimationFinishedCallback) {
            BackAnimationController backAnimationController = BackAnimationController.this;
            ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BackAnimationController.AnonymousClass3 anonymousClass3 = BackAnimationController.AnonymousClass3.this;
                    RemoteAnimationTarget[] remoteAnimationTargetArr2 = remoteAnimationTargetArr;
                    IBackAnimationFinishedCallback iBackAnimationFinishedCallback2 = iBackAnimationFinishedCallback;
                    IBinder iBinder2 = iBinder;
                    BackAnimationController backAnimationController2 = BackAnimationController.this;
                    if (backAnimationController2.mTrackingLatency) {
                        backAnimationController2.mLatencyTracker.onActionEnd(25);
                        backAnimationController2.mTrackingLatency = false;
                    }
                    if (!BackAnimationController.validateAnimationTargets(remoteAnimationTargetArr2)) {
                        Log.e("ShellBackPreview", "Invalid animation targets!");
                        return;
                    }
                    BackAnimationController backAnimationController3 = BackAnimationController.this;
                    backAnimationController3.mBackAnimationFinishedCallback = iBackAnimationFinishedCallback2;
                    backAnimationController3.mApps = remoteAnimationTargetArr2;
                    if (iBinder2 != null) {
                        return;
                    }
                    backAnimationController3.startSystemAnimation();
                    backAnimationController3.dispatchOnBackProgressed(backAnimationController3.mActiveCallback, backAnimationController3.mCurrentTracker.createProgressEvent());
                    if (backAnimationController3.mBackGestureStarted) {
                        return;
                    }
                    backAnimationController3.startPostCommitAnimation();
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackAnimationImpl {
        public BackAnimationImpl() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackTransitionHandler implements Transitions.TransitionHandler {
        public IBinder mClosePrepareTransition;
        public boolean mCloseTransitionRequested;
        public SurfaceControl.Transaction mFinishOpenTransaction;
        public Transitions.TransitionFinishCallback mFinishOpenTransitionCallback;
        public Runnable mOnAnimationFinishCallback;
        public TransitionInfo mOpenTransitionInfo;
        public IBinder mPrepareOpenTransition;

        public BackTransitionHandler() {
        }

        public final void applyAndFinish(SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
            applyFinishOpenTransition();
            transaction.apply();
            transaction2.apply();
            transitionFinishCallback.onTransitionFinished(null);
            this.mCloseTransitionRequested = false;
        }

        public final void applyFinishOpenTransition() {
            this.mOpenTransitionInfo = null;
            this.mPrepareOpenTransition = null;
            SurfaceControl.Transaction transaction = this.mFinishOpenTransaction;
            if (transaction != null) {
                this.mFinishOpenTransaction = null;
                transaction.apply();
            }
            Transitions.TransitionFinishCallback transitionFinishCallback = this.mFinishOpenTransitionCallback;
            if (transitionFinishCallback != null) {
                this.mFinishOpenTransitionCallback = null;
                transitionFinishCallback.onTransitionFinished(null);
            }
        }

        public final void createClosePrepareTransition() {
            if (this.mClosePrepareTransition != null) {
                Log.e("ShellBackPreview", "Re-create close prepare transition");
                return;
            }
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.restoreBackNavi();
            BackAnimationController backAnimationController = BackAnimationController.this;
            this.mClosePrepareTransition = backAnimationController.mTransitions.startTransition(14, windowContainerTransaction, backAnimationController.mBackTransitionHandler);
        }

        public boolean handleCloseTransition(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, final SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
            if (!this.mCloseTransitionRequested || !BackAnimationController.hasAnimationInMode(transitionInfo, new BackAnimationController$$ExternalSyntheticLambda6(1))) {
                return false;
            }
            BackAnimationController backAnimationController = BackAnimationController.this;
            RemoteAnimationTarget[] remoteAnimationTargetArr = backAnimationController.mApps;
            if (remoteAnimationTargetArr == null) {
                applyAndFinish(transaction, transaction2, transitionFinishCallback);
                return true;
            }
            SurfaceControl surfaceControl = null;
            SurfaceControl surfaceControl2 = null;
            for (int length = remoteAnimationTargetArr.length - 1; length >= 0; length--) {
                RemoteAnimationTarget remoteAnimationTarget = backAnimationController.mApps[length];
                int i = remoteAnimationTarget.mode;
                if (i == 0) {
                    surfaceControl = remoteAnimationTarget.leash;
                }
                if (i == 1) {
                    surfaceControl2 = remoteAnimationTarget.leash;
                }
            }
            if (surfaceControl != null && surfaceControl2 != null) {
                for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
                    TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
                    if (change.hasFlags(2)) {
                        transaction.setAlpha(change.getLeash(), 1.0f);
                    } else if (TransitionUtil.isOpeningMode(change.getMode())) {
                        Point endRelOffset = change.getEndRelOffset();
                        transaction.setPosition(change.getLeash(), endRelOffset.x, endRelOffset.y);
                        transaction.reparent(change.getLeash(), surfaceControl);
                        transaction.setAlpha(change.getLeash(), 1.0f);
                    } else if (TransitionUtil.isClosingMode(change.getMode())) {
                        transaction.reparent(change.getLeash(), surfaceControl2);
                    }
                }
            }
            transaction.apply();
            this.mOnAnimationFinishCallback = new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$BackTransitionHandler$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    BackAnimationController.BackTransitionHandler backTransitionHandler = BackAnimationController.BackTransitionHandler.this;
                    SurfaceControl.Transaction transaction3 = transaction2;
                    Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                    backTransitionHandler.getClass();
                    transaction3.apply();
                    transitionFinishCallback2.onTransitionFinished(null);
                    backTransitionHandler.mCloseTransitionRequested = false;
                }
            };
            return true;
        }

        public boolean handlePrepareTransition(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
            boolean z = false;
            if (transitionInfo.getType() != 13) {
                return false;
            }
            if (!BackAnimationController.hasAnimationInMode(transitionInfo, new BackAnimationController$$ExternalSyntheticLambda6(1)) && BackAnimationController.hasAnimationInMode(transitionInfo, new BackAnimationController$$ExternalSyntheticLambda6(2))) {
                BackAnimationController backAnimationController = BackAnimationController.this;
                RemoteAnimationTarget[] remoteAnimationTargetArr = backAnimationController.mApps;
                z = true;
                SurfaceControl surfaceControl = null;
                if (remoteAnimationTargetArr != null) {
                    for (int length = remoteAnimationTargetArr.length - 1; length >= 0; length--) {
                        RemoteAnimationTarget remoteAnimationTarget = backAnimationController.mApps[length];
                        if (remoteAnimationTarget.mode == 0) {
                            surfaceControl = remoteAnimationTarget.leash;
                        }
                    }
                }
                if (surfaceControl != null) {
                    int i = -1;
                    for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
                        TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
                        if (TransitionUtil.isOpeningMode(change.getMode())) {
                            Point endRelOffset = change.getEndRelOffset();
                            transaction.setPosition(change.getLeash(), endRelOffset.x, endRelOffset.y);
                            transaction.reparent(change.getLeash(), surfaceControl);
                            transaction.setAlpha(change.getLeash(), 1.0f);
                            i = TransitionUtil.rootIndexFor(change, transitionInfo);
                        }
                    }
                    if (i >= 0 && transitionInfo.getRootCount() > 0) {
                        transaction.setLayer(transitionInfo.getRoot(i).getLeash(), -1);
                    }
                }
                transaction.apply();
                this.mFinishOpenTransaction = transaction2;
                this.mFinishOpenTransitionCallback = transitionFinishCallback;
                this.mOpenTransitionInfo = transitionInfo;
            }
            return z;
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
        public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
            int type = transitionRequestInfo.getType();
            if (type == 13) {
                this.mPrepareOpenTransition = iBinder;
                return new WindowContainerTransaction();
            }
            if (type == 14) {
                return new WindowContainerTransaction();
            }
            if (TransitionUtil.isClosingType(transitionRequestInfo.getType()) && this.mCloseTransitionRequested) {
                return new WindowContainerTransaction();
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:87:0x0172  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0180 A[SYNTHETIC] */
        @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void mergeAnimation(android.os.IBinder r17, android.window.TransitionInfo r18, android.view.SurfaceControl.Transaction r19, android.os.IBinder r20, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r21) {
            /*
                Method dump skipped, instructions count: 573
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.back.BackAnimationController.BackTransitionHandler.mergeAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.os.IBinder, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):void");
        }

        public final boolean shouldCancelAnimation(TransitionInfo transitionInfo) {
            boolean z = false;
            boolean z2 = !this.mCloseTransitionRequested && transitionInfo.getType() == 13;
            boolean z3 = false;
            boolean z4 = false;
            for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
                boolean hasFlags = change.hasFlags(131072);
                if (!hasFlags && !change.hasFlags(2)) {
                    z3 = true;
                    z4 = true;
                } else if (z2 && hasFlags && TransitionUtil.isClosingMode(change.getMode())) {
                    z3 = true;
                }
            }
            if (!z3) {
                return false;
            }
            if (!z4) {
                return true;
            }
            if (TransitionUtil.isOpeningType(transitionInfo.getType()) || TransitionUtil.isClosingType(transitionInfo.getType())) {
                for (int m2 = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m2 >= 0; m2--) {
                    TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(m2);
                    if (change2.hasFlags(131072) && TransitionUtil.isOpeningMode(change2.getMode())) {
                        transitionInfo.getChanges().remove(m2);
                        z |= change2.hasFlags(1);
                    }
                }
                if (z) {
                    for (int m3 = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m3 >= 0; m3--) {
                        if (((TransitionInfo.Change) transitionInfo.getChanges().get(m3)).hasFlags(2)) {
                            transitionInfo.getChanges().remove(m3);
                        }
                    }
                }
            }
            return true;
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
        public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
            boolean z = transitionInfo.getType() == 13;
            BackAnimationController backAnimationController = BackAnimationController.this;
            if (z) {
                boolean z2 = BackAnimationController.IS_ENABLED;
                backAnimationController.startSystemAnimation();
                backAnimationController.dispatchOnBackProgressed(backAnimationController.mActiveCallback, backAnimationController.mCurrentTracker.createProgressEvent());
                if (!backAnimationController.mBackGestureStarted) {
                    backAnimationController.startPostCommitAnimation();
                }
            }
            if (transitionInfo.getType() == 14) {
                if (this.mClosePrepareTransition == null) {
                    return false;
                }
                this.mClosePrepareTransition = null;
                applyAndFinish(transaction, transaction2, transitionFinishCallback);
                return true;
            }
            if ((transitionInfo.getType() != 13 && !BackAnimationController.hasAnimationInMode(transitionInfo, new BackAnimationController$$ExternalSyntheticLambda6(0))) || shouldCancelAnimation(transitionInfo)) {
                return false;
            }
            RemoteAnimationTarget[] remoteAnimationTargetArr = backAnimationController.mApps;
            if (remoteAnimationTargetArr == null || remoteAnimationTargetArr.length == 0) {
                if (this.mCloseTransitionRequested) {
                    applyAndFinish(transaction, transaction2, transitionFinishCallback);
                    return true;
                }
                if (this.mClosePrepareTransition == null && z) {
                    createClosePrepareTransition();
                }
            }
            if (handlePrepareTransition(transitionInfo, transaction, transaction2, transitionFinishCallback)) {
                return true;
            }
            return handleCloseTransition(transitionInfo, transaction, transaction2, transitionFinishCallback);
        }
    }

    /* renamed from: -$$Nest$smisSameChangeTarget, reason: not valid java name */
    public static boolean m897$$Nest$smisSameChangeTarget(ComponentName componentName, int i, WindowContainerToken windowContainerToken, TransitionInfo.Change change) {
        ComponentName findComponentName = findComponentName(change);
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        int i2 = taskInfo != null ? ((TaskInfo) taskInfo).taskId : -1;
        WindowContainerToken container = change.getContainer();
        return (findComponentName != null && findComponentName == componentName) || (i2 != -1 && i2 == i) || (container != null && windowContainerToken == container);
    }

    static {
        IS_ENABLED = SystemProperties.getInt("persist.wm.debug.predictive_back", 1) == 1;
    }

    public BackAnimationController(ShellInit shellInit, ShellController shellController, ShellExecutor shellExecutor, Handler handler, IActivityTaskManager iActivityTaskManager, Context context, ContentResolver contentResolver, BackAnimationBackground backAnimationBackground, ShellBackAnimationRegistry shellBackAnimationRegistry, ShellCommandHandler shellCommandHandler, Transitions transitions, Handler handler2) {
        Rect rect = new Rect();
        this.mTouchableArea = rect;
        this.mCurrentTracker = new BackTouchTracker();
        this.mQueuedTracker = new BackTouchTracker();
        this.mAnimationTimeoutRunnable = new BackAnimationController$$ExternalSyntheticLambda3(this, 2);
        this.mNavigationObserver = new RemoteCallback(new AnonymousClass1());
        this.mBackAnimation = new BackAnimationImpl();
        this.mShellController = shellController;
        this.mShellExecutor = shellExecutor;
        this.mActivityTaskManager = iActivityTaskManager;
        this.mContext = context;
        this.mRequirePointerPilfer = context.getResources().getBoolean(R.bool.config_backAnimationRequiresPointerPilfer);
        shellInit.addInitCallback(new BackAnimationController$$ExternalSyntheticLambda3(this, 0), this);
        this.mAnimationBackground = backAnimationBackground;
        this.mShellBackAnimationRegistry = shellBackAnimationRegistry;
        this.mLatencyTracker = LatencyTracker.getInstance(context);
        this.mShellCommandHandler = shellCommandHandler;
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mWindowManager = windowManager;
        this.mTransitions = transitions;
        BackTransitionHandler backTransitionHandler = new BackTransitionHandler();
        this.mBackTransitionHandler = backTransitionHandler;
        transitions.addHandler(backTransitionHandler);
        this.mHandler = handler2;
        rect.set(windowManager.getCurrentWindowMetrics().getBounds());
    }

    public static ComponentName findComponentName(TransitionInfo.Change change) {
        ComponentName activityComponent = change.getActivityComponent();
        if (activityComponent != null) {
            return activityComponent;
        }
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (taskInfo != null) {
            return ((TaskInfo) taskInfo).topActivity;
        }
        return null;
    }

    public static boolean hasAnimationInMode(TransitionInfo transitionInfo, Predicate predicate) {
        for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
            if (change.hasFlags(131072) && predicate.test(Integer.valueOf(change.getMode()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateAnimationTargets(RemoteAnimationTarget[] remoteAnimationTargetArr) {
        if (remoteAnimationTargetArr == null || remoteAnimationTargetArr.length == 0) {
            return false;
        }
        for (int length = remoteAnimationTargetArr.length - 1; length >= 0; length--) {
            if (!remoteAnimationTargetArr[length].leash.isValid()) {
                return false;
            }
        }
        return true;
    }

    public final void cancelLatencyTracking() {
        if (this.mTrackingLatency) {
            this.mLatencyTracker.onActionCancel(25);
            this.mTrackingLatency = false;
        }
    }

    public final void dispatchOnBackProgressed(IOnBackInvokedCallback iOnBackInvokedCallback, BackMotionEvent backMotionEvent) {
        if (iOnBackInvokedCallback != null) {
            if (shouldDispatchToAnimator() || this.mBackNavigationInfo == null || !isAppProgressGenerationAllowed()) {
                try {
                    iOnBackInvokedCallback.onBackProgressed(backMotionEvent);
                } catch (RemoteException e) {
                    Log.e("ShellBackPreview", "dispatchOnBackProgressed error: ", e);
                }
            }
        }
    }

    public final void finishBackAnimation() {
        ((HandlerExecutor) this.mShellExecutor).removeCallbacks(this.mAnimationTimeoutRunnable);
        this.mPostCommitAnimationInProgress = false;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -8055404622877214646L, 0, null);
        }
        if (this.mCurrentTracker.isActive() || this.mCurrentTracker.isFinished()) {
            invokeOrCancelBack(this.mCurrentTracker);
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -4972748429022513286L, 0, null);
        }
        resetTouchTracker();
        BackTransitionHandler backTransitionHandler = this.mBackTransitionHandler;
        if (!backTransitionHandler.mCloseTransitionRequested && backTransitionHandler.mPrepareOpenTransition != null) {
            backTransitionHandler.createClosePrepareTransition();
        }
        Runnable runnable = backTransitionHandler.mOnAnimationFinishCallback;
        if (runnable != null) {
            runnable.run();
            backTransitionHandler.mOnAnimationFinishCallback = null;
        }
    }

    public void finishBackNavigation(boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 2915969425923977423L, 0, null);
        }
        this.mActiveCallback = null;
        this.mApps = null;
        this.mShouldStartOnNextMoveEvent = false;
        this.mOnBackStartDispatched = false;
        this.mThresholdCrossed = false;
        this.mPointersPilfered = false;
        ShellBackAnimationRegistry shellBackAnimationRegistry = this.mShellBackAnimationRegistry;
        if (shellBackAnimationRegistry.mDefaultCrossActivityAnimation != null && shellBackAnimationRegistry.mAnimationDefinition.contains(2)) {
            shellBackAnimationRegistry.mAnimationDefinition.set(2, shellBackAnimationRegistry.mDefaultCrossActivityAnimation.backAnimationRunner);
        }
        cancelLatencyTracking();
        this.mReceivedNullNavigationInfo = false;
        BackNavigationInfo backNavigationInfo = this.mBackNavigationInfo;
        if (backNavigationInfo != null) {
            this.mPreviousNavigationType = backNavigationInfo.getType();
            this.mBackNavigationInfo.onBackNavigationFinished(z);
            this.mBackNavigationInfo = null;
            requestTopUi(this.mPreviousNavigationType, false);
        }
    }

    public final BackTouchTracker getActiveTracker() {
        if (this.mCurrentTracker.isActive()) {
            return this.mCurrentTracker;
        }
        if (this.mQueuedTracker.isActive()) {
            return this.mQueuedTracker;
        }
        return null;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mShellExecutor;
    }

    public final void invokeOrCancelBack(BackTouchTracker backTouchTracker) {
        IBackAnimationFinishedCallback iBackAnimationFinishedCallback = this.mBackAnimationFinishedCallback;
        if (iBackAnimationFinishedCallback != null) {
            try {
                iBackAnimationFinishedCallback.onAnimationFinished(backTouchTracker.getTriggerBack());
            } catch (RemoteException e) {
                Log.e("ShellBackPreview", "Failed call IBackAnimationFinishedCallback", e);
            }
            this.mBackAnimationFinishedCallback = null;
        }
        BackNavigationInfo backNavigationInfo = this.mBackNavigationInfo;
        if (backNavigationInfo != null && !this.mRealCallbackInvoked) {
            IOnBackInvokedCallback onBackInvokedCallback = backNavigationInfo.getOnBackInvokedCallback();
            if (!backTouchTracker.getTriggerBack()) {
                tryDispatchOnBackCancelled(onBackInvokedCallback);
            } else if (onBackInvokedCallback != null) {
                try {
                    onBackInvokedCallback.onBackInvoked();
                } catch (RemoteException e2) {
                    Log.e("ShellBackPreview", "dispatchOnBackInvoked error: ", e2);
                }
            }
        }
        this.mRealCallbackInvoked = false;
        finishBackNavigation(backTouchTracker.getTriggerBack());
    }

    public final boolean isAppProgressGenerationAllowed() {
        return this.mBackNavigationInfo.isAppProgressGenerationAllowed() && this.mBackNavigationInfo.getTouchableRegion().equals(this.mTouchableArea);
    }

    public void onBackAnimationFinished() {
        if (this.mPostCommitAnimationInProgress) {
            finishBackAnimation();
        }
    }

    public final void onBackNavigationInfoReceived(BackNavigationInfo backNavigationInfo, BackTouchTracker backTouchTracker) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -2720921649798499212L, 0, String.valueOf(backNavigationInfo));
        }
        if (backNavigationInfo == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -5836110555656668314L, 0, null);
            }
            this.mReceivedNullNavigationInfo = true;
            cancelLatencyTracking();
            tryPilferPointers();
            return;
        }
        int type = backNavigationInfo.getType();
        if (shouldDispatchToAnimator()) {
            BackAnimationRunner backAnimationRunner = (BackAnimationRunner) this.mShellBackAnimationRegistry.mAnimationDefinition.get(type);
            if (backAnimationRunner == null) {
                this.mActiveCallback = null;
            } else {
                backAnimationRunner.mWaitingAnimation = true;
                backAnimationRunner.mAnimationCancelled = false;
            }
            requestTopUi(type, true);
            tryPilferPointers();
            return;
        }
        this.mActiveCallback = this.mBackNavigationInfo.getOnBackInvokedCallback();
        cancelLatencyTracking();
        IOnBackInvokedCallback iOnBackInvokedCallback = this.mActiveCallback;
        BackMotionEvent createStartEvent = backTouchTracker.createStartEvent((RemoteAnimationTarget) null);
        if (!this.mOnBackStartDispatched && iOnBackInvokedCallback != null && (this.mThresholdCrossed || !this.mRequirePointerPilfer)) {
            try {
                iOnBackInvokedCallback.onBackStarted(createStartEvent);
                this.mOnBackStartDispatched = true;
            } catch (RemoteException e) {
                Log.e("ShellBackPreview", "dispatchOnBackStarted error: ", e);
            }
        }
        if (isAppProgressGenerationAllowed()) {
            return;
        }
        tryPilferPointers();
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        ShellBackAnimationRegistry shellBackAnimationRegistry = this.mShellBackAnimationRegistry;
        CustomCrossActivityBackAnimation customCrossActivityBackAnimation = shellBackAnimationRegistry.mCustomizeActivityAnimation;
        if (customCrossActivityBackAnimation != null) {
            customCrossActivityBackAnimation.onConfigurationChanged();
        }
        DefaultCrossActivityBackAnimation defaultCrossActivityBackAnimation = shellBackAnimationRegistry.mDefaultCrossActivityAnimation;
        if (defaultCrossActivityBackAnimation != null) {
            defaultCrossActivityBackAnimation.onConfigurationChanged();
        }
        CrossTaskBackAnimation crossTaskBackAnimation = shellBackAnimationRegistry.mCrossTaskAnimation;
        if (crossTaskBackAnimation != null) {
            crossTaskBackAnimation.mCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(crossTaskBackAnimation.mContext);
            crossTaskBackAnimation.mStatusbarHeight = SystemBarUtils.getStatusBarHeight(crossTaskBackAnimation.mContext);
        }
        this.mTouchableArea.set(this.mWindowManager.getCurrentWindowMetrics().getBounds());
    }

    public void onThresholdCrossed() {
        this.mThresholdCrossed = true;
        if (this.mBackNavigationInfo == null && this.mReceivedNullNavigationInfo) {
            tryPilferPointers();
            return;
        }
        boolean shouldDispatchToAnimator = shouldDispatchToAnimator();
        if (shouldDispatchToAnimator || this.mActiveCallback == null) {
            if (shouldDispatchToAnimator) {
                tryPilferPointers();
                return;
            }
            return;
        }
        this.mCurrentTracker.updateStartLocation();
        IOnBackInvokedCallback iOnBackInvokedCallback = this.mActiveCallback;
        BackMotionEvent createStartEvent = this.mCurrentTracker.createStartEvent((RemoteAnimationTarget) null);
        if (!this.mOnBackStartDispatched && iOnBackInvokedCallback != null && (this.mThresholdCrossed || !this.mRequirePointerPilfer)) {
            try {
                iOnBackInvokedCallback.onBackStarted(createStartEvent);
                this.mOnBackStartDispatched = true;
            } catch (RemoteException e) {
                Log.e("ShellBackPreview", "dispatchOnBackStarted error: ", e);
            }
        }
        if (this.mBackNavigationInfo == null || isAppProgressGenerationAllowed()) {
            return;
        }
        tryPilferPointers();
    }

    public final void requestTopUi(int i, final boolean z) {
        EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = this.mRequestTopUiCallback;
        if (edgeBackGestureHandler$$ExternalSyntheticLambda7 != null) {
            if (i == 3 || i == 2) {
                Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                final EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                edgeBackGestureHandler.getClass();
                executor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda19
                    @Override // java.lang.Runnable
                    public final void run() {
                        EdgeBackGestureHandler edgeBackGestureHandler2 = EdgeBackGestureHandler.this;
                        ((NotificationShadeWindowControllerImpl) edgeBackGestureHandler2.mNotificationShadeWindowController).setRequestTopUi("ShellBackPreview", z);
                    }
                });
            }
        }
    }

    public final void resetTouchTracker() {
        BackTouchTracker backTouchTracker = this.mCurrentTracker;
        this.mCurrentTracker = this.mQueuedTracker;
        backTouchTracker.reset();
        this.mQueuedTracker = backTouchTracker;
        if (this.mCurrentTracker.isInInitialState()) {
            if (!this.mBackGestureStarted) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -8000539581969842372L, 0, null);
                    return;
                }
                return;
            } else {
                this.mBackGestureStarted = false;
                tryDispatchOnBackCancelled(this.mActiveCallback);
                finishBackNavigation(false);
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 2388634208657817241L, 0, null);
                    return;
                }
                return;
            }
        }
        if (this.mCurrentTracker.isFinished() && this.mCurrentTracker.getTriggerBack()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 3664543358694404916L, 0, null);
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -1127091700925973460L, 0, null);
            }
            sendBackEvent(0);
            sendBackEvent(1);
            finishBackNavigation(true);
            this.mCurrentTracker.reset();
            return;
        }
        if (this.mCurrentTracker.isFinished()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 3253074859835012044L, 0, null);
            }
            this.mCurrentTracker.reset();
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -1010179004277836389L, 0, null);
        }
    }

    public final void sendBackEvent(int i) {
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i, 4, 0, 0, -1, 0, 72, 257);
        keyEvent.setDisplayId(this.mContext.getDisplay().getDisplayId());
        if (((InputManager) this.mContext.getSystemService(InputManager.class)).injectInputEvent(keyEvent, 0) || !ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[4]) {
            return;
        }
        ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 351589376826358494L, 0, null);
    }

    public final void setTriggerBack(boolean z) {
        IOnBackInvokedCallback iOnBackInvokedCallback = this.mActiveCallback;
        if (iOnBackInvokedCallback != null) {
            try {
                iOnBackInvokedCallback.setTriggerBack(z);
            } catch (RemoteException e) {
                Log.e("ShellBackPreview", "remote setTriggerBack error: ", e);
            }
        }
        BackTouchTracker activeTracker = getActiveTracker();
        if (activeTracker != null) {
            activeTracker.setTriggerBack(z);
        }
    }

    public final boolean shouldDispatchToAnimator() {
        BackNavigationInfo backNavigationInfo;
        return this.mEnableAnimations.get() && (backNavigationInfo = this.mBackNavigationInfo) != null && backNavigationInfo.isPrepareRemoteAnimation();
    }

    public final void startPostCommitAnimation() {
        int type;
        if (this.mPostCommitAnimationInProgress) {
            return;
        }
        HandlerExecutor handlerExecutor = (HandlerExecutor) this.mShellExecutor;
        BackAnimationController$$ExternalSyntheticLambda3 backAnimationController$$ExternalSyntheticLambda3 = this.mAnimationTimeoutRunnable;
        handlerExecutor.removeCallbacks(backAnimationController$$ExternalSyntheticLambda3);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 1780102699045957800L, 0, null);
        }
        this.mPostCommitAnimationInProgress = true;
        handlerExecutor.executeDelayed(backAnimationController$$ExternalSyntheticLambda3, 2000L);
        if (!this.mCurrentTracker.getTriggerBack()) {
            tryDispatchOnBackCancelled(this.mActiveCallback);
            return;
        }
        BackNavigationInfo backNavigationInfo = this.mBackNavigationInfo;
        if (backNavigationInfo != null && ((type = backNavigationInfo.getType()) == 1 || type == 3 || type == 2)) {
            this.mBackTransitionHandler.mCloseTransitionRequested = true;
            IOnBackInvokedCallback onBackInvokedCallback = this.mBackNavigationInfo.getOnBackInvokedCallback();
            if (onBackInvokedCallback != null) {
                try {
                    onBackInvokedCallback.onBackInvoked();
                } catch (RemoteException e) {
                    Log.e("ShellBackPreview", "dispatchOnBackInvoked error: ", e);
                }
            }
            this.mRealCallbackInvoked = true;
        }
        IOnBackInvokedCallback iOnBackInvokedCallback = this.mActiveCallback;
        if (iOnBackInvokedCallback == null) {
            return;
        }
        try {
            iOnBackInvokedCallback.onBackInvoked();
        } catch (RemoteException e2) {
            Log.e("ShellBackPreview", "dispatchOnBackInvoked error: ", e2);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Method arg registers not loaded: com.android.wm.shell.back.BackAnimationRunner.1.<init>(com.android.wm.shell.back.BackAnimationRunner, android.view.RemoteAnimationTarget[], com.android.internal.jank.InteractionJankMonitor, com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticLambda3):void, class status: GENERATED_AND_UNLOADED
        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:290)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isArgUnused(ProcessVariables.java:146)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.lambda$isVarUnused$0(ProcessVariables.java:131)
        	at jadx.core.utils.ListUtils.allMatch(ListUtils.java:193)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.isVarUnused(ProcessVariables.java:131)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables$1.processBlock(ProcessVariables.java:82)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:64)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.removeUnusedResults(ProcessVariables.java:73)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:48)
        */
    public final void startSystemAnimation() {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.back.BackAnimationController.startSystemAnimation():void");
    }

    public final void tryDispatchOnBackCancelled(IOnBackInvokedCallback iOnBackInvokedCallback) {
        if (!this.mOnBackStartDispatched) {
            Log.d("ShellBackPreview", "Skipping dispatching onBackCancelled. Start was never dispatched.");
        } else {
            if (iOnBackInvokedCallback == null) {
                return;
            }
            try {
                iOnBackInvokedCallback.onBackCancelled();
            } catch (RemoteException e) {
                Log.e("ShellBackPreview", "dispatchOnBackCancelled error: ", e);
            }
        }
    }

    public final void tryPilferPointers() {
        if (this.mPointersPilfered || !this.mThresholdCrossed) {
            return;
        }
        EdgeBackGestureHandler$$ExternalSyntheticLambda6 edgeBackGestureHandler$$ExternalSyntheticLambda6 = this.mPilferPointerCallback;
        if (edgeBackGestureHandler$$ExternalSyntheticLambda6 != null) {
            edgeBackGestureHandler$$ExternalSyntheticLambda6.run();
        }
        this.mPointersPilfered = true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IBackAnimationImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public BackAnimationController mController;

        public IBackAnimationImpl(BackAnimationController backAnimationController) {
            attachInterface(this, "com.android.wm.shell.back.IBackAnimation");
            this.mController = backAnimationController;
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.back.IBackAnimation");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.back.IBackAnimation");
                return true;
            }
            if (i == 1) {
                final IOnBackInvokedCallback asInterface = IOnBackInvokedCallback.Stub.asInterface(parcel.readStrongBinder());
                final IRemoteAnimationRunner asInterface2 = IRemoteAnimationRunner.Stub.asInterface(parcel.readStrongBinder());
                parcel.enforceNoDataAvail();
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setBackToLauncherCallback", new Consumer() { // from class: com.android.wm.shell.back.BackAnimationController$IBackAnimationImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        BackAnimationController.IBackAnimationImpl iBackAnimationImpl = BackAnimationController.IBackAnimationImpl.this;
                        IOnBackInvokedCallback iOnBackInvokedCallback = asInterface;
                        IRemoteAnimationRunner iRemoteAnimationRunner = asInterface2;
                        BackAnimationController backAnimationController = (BackAnimationController) obj;
                        iBackAnimationImpl.getClass();
                        BackAnimationRunner backAnimationRunner = new BackAnimationRunner(iOnBackInvokedCallback, iRemoteAnimationRunner, backAnimationController.mContext, 86, BackAnimationController.this.mHandler);
                        ShellBackAnimationRegistry shellBackAnimationRegistry = backAnimationController.mShellBackAnimationRegistry;
                        shellBackAnimationRegistry.mAnimationDefinition.set(1, backAnimationRunner);
                        shellBackAnimationRegistry.updateSupportedAnimators();
                    }
                }, false);
                parcel2.writeNoException();
            } else if (i == 2) {
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "clearBackToLauncherCallback", new BackAnimationController$IBackAnimationImpl$$ExternalSyntheticLambda1(), false);
                parcel2.writeNoException();
            } else {
                if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                final AppearanceRegion appearanceRegion = (AppearanceRegion) parcel.readTypedObject(AppearanceRegion.CREATOR);
                parcel.enforceNoDataAvail();
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "useLauncherSysBarFlags", new Consumer() { // from class: com.android.wm.shell.back.BackAnimationController$IBackAnimationImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AppearanceRegion appearanceRegion2 = appearanceRegion;
                        EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = ((BackAnimationController) obj).mCustomizer;
                        if (edgeBackGestureHandler$$ExternalSyntheticLambda7 != null) {
                            Executor executor = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$1;
                            EdgeBackGestureHandler edgeBackGestureHandler = edgeBackGestureHandler$$ExternalSyntheticLambda7.f$0;
                            edgeBackGestureHandler.getClass();
                            executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda6(edgeBackGestureHandler, appearanceRegion2, 1));
                        }
                    }
                }, false);
                parcel2.writeNoException();
            }
            return true;
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
