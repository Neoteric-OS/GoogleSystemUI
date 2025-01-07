package com.android.systemui.animation;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationTarget;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.window.WindowAnimationState;
import com.android.app.animation.Interpolators;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.wm.shell.startingsurface.SplashscreenContentDrawer;
import com.android.wm.shell.startingsurface.StartingWindowController;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityTransitionAnimator {
    public CentralSurfacesImpl.AnonymousClass4 callback;
    public final TransitionAnimator dialogToAppAnimator;
    public final ActivityTransitionAnimator$lifecycleListener$1 lifecycleListener;
    public final LinkedHashSet listeners;
    public final Executor mainExecutor;
    public final TransitionAnimator transitionAnimator;
    public static final TransitionAnimator.Timings TIMINGS = new TransitionAnimator.Timings(500, 0, 150, 150, 183);
    public static final TransitionAnimator.Timings DIALOG_TIMINGS = new TransitionAnimator.Timings(500, 0, 200, 200, 183);
    public static final TransitionAnimator.Interpolators INTERPOLATORS = new TransitionAnimator.Interpolators(Interpolators.EMPHASIZED, Interpolators.EMPHASIZED_COMPLEMENT, Interpolators.LINEAR_OUT_SLOW_IN, new PathInterpolator(0.0f, 0.0f, 0.6f, 1.0f));
    public static final boolean DEBUG_TRANSITION_ANIMATION = Build.IS_DEBUGGABLE;
    public static final long ANIMATION_DELAY_NAV_FADE_IN = 234;
    public static final Interpolator NAV_FADE_IN_INTERPOLATOR = Interpolators.STANDARD_DECELERATE;
    public static final PathInterpolator NAV_FADE_OUT_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 1.0f, 1.0f);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationDelegate {
        public TransitionAnimator$startAnimation$1 animation;
        public final CentralSurfacesImpl.AnonymousClass4 callback;
        public boolean cancelled;
        public final Context context;
        public final Controller controller;
        public final Matrix invertMatrix;
        public final DelegatingAnimationCompletionListener listener;
        public final Matrix matrix;
        public final ActivityTransitionAnimator$AnimationDelegate$onLongTimeout$1 onLongTimeout;
        public final ActivityTransitionAnimator$Runner$dispose$1 onTimeout;
        public boolean timedOut;
        public final Handler timeoutHandler;
        public final SyncRtSurfaceTransactionApplier transactionApplier;
        public final View transactionApplierView;
        public final TransitionAnimator transitionAnimator;
        public final Rect windowCrop;
        public final RectF windowCropF;

        public AnimationDelegate(Controller controller, CentralSurfacesImpl.AnonymousClass4 anonymousClass4, DelegatingAnimationCompletionListener delegatingAnimationCompletionListener, TransitionAnimator transitionAnimator) {
            this.controller = controller;
            this.callback = anonymousClass4;
            this.listener = delegatingAnimationCompletionListener;
            this.transitionAnimator = transitionAnimator;
            this.context = controller.getTransitionContainer().getContext();
            View openingWindowSyncView = controller.getOpeningWindowSyncView();
            openingWindowSyncView = openingWindowSyncView == null ? controller.getTransitionContainer() : openingWindowSyncView;
            this.transactionApplierView = openingWindowSyncView;
            this.transactionApplier = new SyncRtSurfaceTransactionApplier(openingWindowSyncView);
            this.timeoutHandler = new Handler(Looper.getMainLooper());
            this.matrix = new Matrix();
            this.invertMatrix = new Matrix();
            this.windowCrop = new Rect();
            this.windowCropF = new RectF();
            this.onTimeout = new ActivityTransitionAnimator$Runner$dispose$1(1, this);
            this.onLongTimeout = ActivityTransitionAnimator$AnimationDelegate$onLongTimeout$1.INSTANCE;
            if (controller.isLaunching()) {
                return;
            }
            PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
            throw new IllegalStateException("isLaunching cannot be false when the returnAnimationFrameworkLibrary flag is disabled");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DelegatingAnimationCompletionListener implements Listener {
        public boolean cancelled;
        public final Listener delegate;
        public final Function0 onAnimationComplete;

        public DelegatingAnimationCompletionListener(Listener listener, Function0 function0) {
            this.delegate = listener;
            this.onAnimationComplete = function0;
        }

        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationCancelled() {
            this.cancelled = true;
            Listener listener = this.delegate;
            if (listener != null) {
                listener.onTransitionAnimationCancelled();
            }
            this.onAnimationComplete.invoke();
        }

        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationEnd() {
            Listener listener = this.delegate;
            if (listener != null) {
                listener.onTransitionAnimationEnd();
            }
            if (this.cancelled) {
                return;
            }
            ((Runner.AnonymousClass1) this.onAnimationComplete).invoke();
        }

        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationProgress(float f) {
            Listener listener = this.delegate;
            if (listener != null) {
                listener.onTransitionAnimationProgress(f);
            }
        }

        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationStart() {
            Listener listener = this.delegate;
            if (listener != null) {
                listener.onTransitionAnimationStart();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PendingIntentStarter {
        int startPendingIntent(RemoteAnimationAdapter remoteAnimationAdapter);
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.systemui.animation.ActivityTransitionAnimator$lifecycleListener$1] */
    public ActivityTransitionAnimator(Executor executor) {
        TransitionAnimator.Timings timings = TIMINGS;
        TransitionAnimator.Interpolators interpolators = INTERPOLATORS;
        TransitionAnimator transitionAnimator = new TransitionAnimator(executor, timings, interpolators);
        TransitionAnimator transitionAnimator2 = new TransitionAnimator(executor, DIALOG_TIMINGS, interpolators);
        this.mainExecutor = executor;
        this.transitionAnimator = transitionAnimator;
        this.dialogToAppAnimator = transitionAnimator2;
        this.listeners = new LinkedHashSet();
        this.lifecycleListener = new Listener() { // from class: com.android.systemui.animation.ActivityTransitionAnimator$lifecycleListener$1
            @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
            public final void onTransitionAnimationCancelled() {
                Iterator it = ActivityTransitionAnimator.this.listeners.iterator();
                while (it.hasNext()) {
                    ((ActivityTransitionAnimator.Listener) it.next()).onTransitionAnimationCancelled();
                }
            }

            @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
            public final void onTransitionAnimationEnd() {
                Iterator it = ActivityTransitionAnimator.this.listeners.iterator();
                while (it.hasNext()) {
                    ((ActivityTransitionAnimator.Listener) it.next()).onTransitionAnimationEnd();
                }
            }

            @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
            public final void onTransitionAnimationProgress(float f) {
                Iterator it = ActivityTransitionAnimator.this.listeners.iterator();
                while (it.hasNext()) {
                    ((ActivityTransitionAnimator.Listener) it.next()).onTransitionAnimationProgress(f);
                }
            }

            @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
            public final void onTransitionAnimationStart() {
                Iterator it = ActivityTransitionAnimator.this.listeners.iterator();
                while (it.hasNext()) {
                    ((ActivityTransitionAnimator.Listener) it.next()).onTransitionAnimationStart();
                }
            }
        };
        new HashMap();
    }

    public final void callOnIntentStartedOnMainThread(final Controller controller, final boolean z) {
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            this.mainExecutor.execute(new Runnable() { // from class: com.android.systemui.animation.ActivityTransitionAnimator$callOnIntentStartedOnMainThread$1
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityTransitionAnimator.this.callOnIntentStartedOnMainThread(controller, z);
                }
            });
            return;
        }
        if (DEBUG_TRANSITION_ANIMATION) {
            Log.d("ActivityTransitionAnimator", "Calling controller.onIntentStarted(willAnimate=" + z + ") [controller=" + controller + "]");
        }
        controller.onIntentStarted(z);
    }

    public final Runner createRunner(Controller controller) {
        TransitionAnimator transitionAnimator = controller.isDialogLaunch() ? this.dialogToAppAnimator : this.transitionAnimator;
        CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = this.callback;
        Intrinsics.checkNotNull(anonymousClass4);
        return new Runner(controller, anonymousClass4, transitionAnimator, this.lifecycleListener);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
    
        if (r3 != false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startIntentWithAnimation(com.android.systemui.animation.ActivityTransitionAnimator.Controller r22, boolean r23, java.lang.String r24, boolean r25, kotlin.jvm.functions.Function1 r26) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.ActivityTransitionAnimator.startIntentWithAnimation(com.android.systemui.animation.ActivityTransitionAnimator$Controller, boolean, java.lang.String, boolean, kotlin.jvm.functions.Function1):void");
    }

    public final void startPendingIntentWithAnimation(Controller controller, boolean z, String str, boolean z2, final PendingIntentStarter pendingIntentStarter) {
        startIntentWithAnimation(controller, z, str, z2, new Function1() { // from class: com.android.systemui.animation.ActivityTransitionAnimator$startPendingIntentWithAnimation$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(ActivityTransitionAnimator.PendingIntentStarter.this.startPendingIntent((RemoteAnimationAdapter) obj));
            }
        });
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Controller extends TransitionAnimator.Controller {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Companion {
            public static final /* synthetic */ Companion $$INSTANCE = null;

            public static GhostedViewTransitionAnimatorController fromView$default(View view, Integer num, int i) {
                if ((i & 2) != 0) {
                    num = null;
                }
                if (!(view instanceof LaunchableView)) {
                    throw new IllegalArgumentException("An ActivityTransitionAnimator.Controller was created from a View that does not implement LaunchableView. This can lead to subtle bugs where the visibility of the View we are launching from is not what we expected.");
                }
                if (view.getParent() instanceof ViewGroup) {
                    return new GhostedViewTransitionAnimatorController(view, num, 32);
                }
                Log.e("ActivityTransitionAnimator", "Skipping animation as view " + view + " is not attached to a ViewGroup", new Exception());
                return null;
            }
        }

        default boolean isBelowAnimatingWindow() {
            return false;
        }

        default boolean isDialogLaunch() {
            return false;
        }

        default void getTransitionCookie() {
        }

        default void onTransitionAnimationCancelled() {
        }

        default void onIntentStarted(boolean z) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onTransitionAnimationEnd();

        void onTransitionAnimationStart();

        default void onTransitionAnimationCancelled() {
        }

        default void onTransitionAnimationProgress(float f) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Runner extends IRemoteAnimationRunner.Stub {
        public AnimationDelegate delegate;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.animation.ActivityTransitionAnimator$Runner$1, reason: invalid class name */
        final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Runner runner = (Runner) this.receiver;
                ActivityTransitionAnimator.this.mainExecutor.execute(new ActivityTransitionAnimator$Runner$dispose$1(0, runner));
                return Unit.INSTANCE;
            }
        }

        public Runner(Controller controller, CentralSurfacesImpl.AnonymousClass4 anonymousClass4, TransitionAnimator transitionAnimator, Listener listener) {
            this.delegate = new AnimationDelegate(controller, anonymousClass4, new DelegatingAnimationCompletionListener(listener, new AnonymousClass1(0, this, Runner.class, "dispose", "dispose()V", 0)), transitionAnimator);
        }

        public final void onAnimationCancelled() {
            ActivityTransitionAnimator.this.mainExecutor.execute(new ActivityTransitionAnimator$Runner$dispose$1(2, this.delegate));
        }

        public final void onAnimationStart(final int i, final RemoteAnimationTarget[] remoteAnimationTargetArr, final RemoteAnimationTarget[] remoteAnimationTargetArr2, final RemoteAnimationTarget[] remoteAnimationTargetArr3, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            final AnimationDelegate animationDelegate = this.delegate;
            ActivityTransitionAnimator.this.mainExecutor.execute(new Runnable(iRemoteAnimationFinishedCallback, i, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3) { // from class: com.android.systemui.animation.ActivityTransitionAnimator$Runner$onAnimationStart$1
                public final /* synthetic */ RemoteAnimationTarget[] $apps;
                public final /* synthetic */ IRemoteAnimationFinishedCallback $finishedCallback;
                public final /* synthetic */ RemoteAnimationTarget[] $nonApps;

                {
                    this.$apps = remoteAnimationTargetArr;
                    this.$nonApps = remoteAnimationTargetArr3;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    RemoteAnimationTarget remoteAnimationTarget;
                    boolean z;
                    RemoteAnimationTarget remoteAnimationTarget2;
                    TransitionAnimator.State createAnimatorState;
                    ActivityTransitionAnimator.AnimationDelegate animationDelegate2 = ActivityTransitionAnimator.AnimationDelegate.this;
                    if (animationDelegate2 == null) {
                        Log.i("ActivityTransitionAnimator", "onAnimationStart called after completion");
                        IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback2 = this.$finishedCallback;
                        if (iRemoteAnimationFinishedCallback2 != null) {
                            iRemoteAnimationFinishedCallback2.onAnimationFinished();
                            return;
                        }
                        return;
                    }
                    RemoteAnimationTarget[] remoteAnimationTargetArr4 = this.$apps;
                    RemoteAnimationTarget[] remoteAnimationTargetArr5 = this.$nonApps;
                    IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback3 = this.$finishedCallback;
                    Handler handler = animationDelegate2.timeoutHandler;
                    if (handler != null) {
                        handler.removeCallbacks(animationDelegate2.onTimeout);
                        handler.removeCallbacks(animationDelegate2.onLongTimeout);
                    }
                    if (animationDelegate2.timedOut) {
                        if (iRemoteAnimationFinishedCallback3 != null) {
                            try {
                                iRemoteAnimationFinishedCallback3.onAnimationFinished();
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        return;
                    }
                    if (animationDelegate2.cancelled) {
                        return;
                    }
                    ActivityTransitionAnimator.Controller controller = animationDelegate2.controller;
                    int i2 = 0;
                    if (remoteAnimationTargetArr4 == null) {
                        remoteAnimationTarget = null;
                    } else {
                        boolean z2 = !controller.isLaunching();
                        int i3 = 0;
                        RemoteAnimationTarget remoteAnimationTarget3 = null;
                        while (i3 < remoteAnimationTargetArr4.length) {
                            int i4 = i3 + 1;
                            try {
                                RemoteAnimationTarget remoteAnimationTarget4 = remoteAnimationTargetArr4[i3];
                                if (remoteAnimationTarget4.mode == z2) {
                                    if (remoteAnimationTarget3 != null && ((z = remoteAnimationTarget4.hasAnimatingParent) || !remoteAnimationTarget3.hasAnimatingParent)) {
                                        if (!z) {
                                            Rect rect = remoteAnimationTarget4.screenSpaceBounds;
                                            Rect rect2 = remoteAnimationTarget3.screenSpaceBounds;
                                            if (rect.height() * rect.width() <= rect2.height() * rect2.width()) {
                                            }
                                        }
                                    }
                                    remoteAnimationTarget3 = remoteAnimationTarget4;
                                }
                                i3 = i4;
                            } catch (ArrayIndexOutOfBoundsException e2) {
                                throw new NoSuchElementException(e2.getMessage());
                            }
                        }
                        remoteAnimationTarget = remoteAnimationTarget3;
                    }
                    if (remoteAnimationTarget == null) {
                        Log.i("ActivityTransitionAnimator", "Aborting the animation as no window is opening");
                        if (iRemoteAnimationFinishedCallback3 != null) {
                            try {
                                iRemoteAnimationFinishedCallback3.onAnimationFinished();
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
                            Log.d("ActivityTransitionAnimator", "Calling controller.onTransitionAnimationCancelled() [no window opening]");
                        }
                        controller.onTransitionAnimationCancelled();
                        animationDelegate2.listener.onTransitionAnimationCancelled();
                        return;
                    }
                    if (remoteAnimationTargetArr5 != null) {
                        int length = remoteAnimationTargetArr5.length;
                        for (int i5 = 0; i5 < length; i5++) {
                            remoteAnimationTarget2 = remoteAnimationTargetArr5[i5];
                            if (remoteAnimationTarget2.windowType == 2019) {
                                break;
                            }
                        }
                    }
                    remoteAnimationTarget2 = null;
                    Rect rect3 = remoteAnimationTarget.screenSpaceBounds;
                    boolean isLaunching = controller.isLaunching();
                    TransitionAnimator transitionAnimator = animationDelegate2.transitionAnimator;
                    if (isLaunching) {
                        WindowAnimationState windowAnimatorState = controller.getWindowAnimatorState();
                        if (windowAnimatorState != null) {
                            PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
                            createAnimatorState = TransitionAnimator.Companion.toTransitionState$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(windowAnimatorState);
                        } else {
                            createAnimatorState = new TransitionAnimator.State(rect3.top, rect3.bottom, rect3.left, rect3.right, 0.0f, 0.0f, 48);
                            float windowCornerRadius = transitionAnimator.isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(controller.getTransitionContainer(), createAnimatorState) ? ScreenDecorationsUtils.getWindowCornerRadius(animationDelegate2.context) : 0.0f;
                            createAnimatorState.topCornerRadius = windowCornerRadius;
                            createAnimatorState.bottomCornerRadius = windowCornerRadius;
                        }
                    } else {
                        createAnimatorState = controller.createAnimatorState();
                    }
                    TransitionAnimator.State state = createAnimatorState;
                    if (!remoteAnimationTarget.isTranslucent) {
                        ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget.taskInfo;
                        if (runningTaskInfo != null) {
                            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                            if (centralSurfacesImpl.mStartingSurfaceOptional.isPresent()) {
                                i2 = ((StartingWindowController.StartingSurfaceImpl) centralSurfacesImpl.mStartingSurfaceOptional.get()).getBackgroundColor(runningTaskInfo);
                            } else {
                                Log.w("CentralSurfaces", "No starting surface, defaulting to SystemBGColor");
                                i2 = SplashscreenContentDrawer.getSystemBGColor();
                            }
                        } else {
                            i2 = remoteAnimationTarget.backgroundColor;
                        }
                    }
                    animationDelegate2.animation = animationDelegate2.transitionAnimator.startAnimation(new ActivityTransitionAnimator.Controller(animationDelegate2, transitionAnimator.isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(controller.getTransitionContainer(), state), rect3, iRemoteAnimationFinishedCallback3, remoteAnimationTarget, remoteAnimationTarget2) { // from class: com.android.systemui.animation.ActivityTransitionAnimator$AnimationDelegate$startAnimation$controller$1
                        public final /* synthetic */ ActivityTransitionAnimator.Controller $$delegate_0;
                        public final /* synthetic */ IRemoteAnimationFinishedCallback $iCallback;
                        public final /* synthetic */ boolean $isExpandingFullyAbove;
                        public final /* synthetic */ RemoteAnimationTarget $navigationBar;
                        public final /* synthetic */ RemoteAnimationTarget $window;
                        public final /* synthetic */ Rect $windowBounds;
                        public final /* synthetic */ ActivityTransitionAnimator.AnimationDelegate this$0;

                        {
                            this.this$0 = animationDelegate2;
                            this.$isExpandingFullyAbove = r3;
                            this.$windowBounds = rect3;
                            this.$iCallback = iRemoteAnimationFinishedCallback3;
                            this.$window = remoteAnimationTarget;
                            this.$navigationBar = remoteAnimationTarget2;
                            this.$$delegate_0 = ActivityTransitionAnimator.Controller.this;
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final TransitionAnimator.State createAnimatorState() {
                            float f;
                            boolean isLaunching2 = this.$$delegate_0.isLaunching();
                            ActivityTransitionAnimator.Controller controller2 = ActivityTransitionAnimator.Controller.this;
                            if (isLaunching2) {
                                return controller2.createAnimatorState();
                            }
                            WindowAnimationState windowAnimatorState2 = controller2.getWindowAnimatorState();
                            if (windowAnimatorState2 != null) {
                                PorterDuffXfermode porterDuffXfermode2 = TransitionAnimator.SRC_MODE;
                                return TransitionAnimator.Companion.toTransitionState$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(windowAnimatorState2);
                            }
                            ActivityTransitionAnimator.AnimationDelegate animationDelegate3 = this.this$0;
                            if (this.$isExpandingFullyAbove) {
                                f = ScreenDecorationsUtils.getWindowCornerRadius(animationDelegate3.context);
                            } else {
                                animationDelegate3.getClass();
                                f = 0.0f;
                            }
                            float f2 = f;
                            Rect rect4 = this.$windowBounds;
                            return new TransitionAnimator.State(rect4.top, rect4.bottom, rect4.left, rect4.right, f2, f2);
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final View getOpeningWindowSyncView() {
                            return this.$$delegate_0.getOpeningWindowSyncView();
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final ViewGroup getTransitionContainer() {
                            return this.$$delegate_0.getTransitionContainer();
                        }

                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                        public final void getTransitionCookie() {
                            this.$$delegate_0.getTransitionCookie();
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final WindowAnimationState getWindowAnimatorState() {
                            return this.$$delegate_0.getWindowAnimatorState();
                        }

                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                        public final boolean isBelowAnimatingWindow() {
                            return this.$$delegate_0.isBelowAnimatingWindow();
                        }

                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                        public final boolean isDialogLaunch() {
                            return this.$$delegate_0.isDialogLaunch();
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final boolean isLaunching() {
                            return this.$$delegate_0.isLaunching();
                        }

                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                        public final void onIntentStarted(boolean z3) {
                            this.$$delegate_0.onIntentStarted(z3);
                        }

                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                        public final void onTransitionAnimationCancelled() {
                            this.$$delegate_0.onTransitionAnimationCancelled();
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final void onTransitionAnimationEnd(boolean z3) {
                            this.this$0.listener.onTransitionAnimationEnd();
                            IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback4 = this.$iCallback;
                            if (iRemoteAnimationFinishedCallback4 != null) {
                                try {
                                    iRemoteAnimationFinishedCallback4.onAnimationFinished();
                                } catch (RemoteException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            boolean z4 = ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION;
                            ActivityTransitionAnimator.Controller controller2 = ActivityTransitionAnimator.Controller.this;
                            if (z4) {
                                Log.d("ActivityTransitionAnimator", "Calling controller.onTransitionAnimationEnd(isExpandingFullyAbove=" + z3 + ") [controller=" + controller2 + "]");
                            }
                            controller2.onTransitionAnimationEnd(z3);
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final void onTransitionAnimationProgress(TransitionAnimator.State state2, float f, float f2) {
                            long j;
                            RemoteAnimationTarget remoteAnimationTarget5 = this.$window;
                            ActivityTransitionAnimator.AnimationDelegate animationDelegate3 = this.this$0;
                            if (animationDelegate3.transactionApplierView.getViewRootImpl() != null && remoteAnimationTarget5.leash.isValid()) {
                                Rect rect4 = remoteAnimationTarget5.screenSpaceBounds;
                                int i6 = rect4.left;
                                int i7 = rect4.right;
                                float f3 = (i6 + i7) / 2.0f;
                                int i8 = rect4.top;
                                float f4 = (i8 + r14) / 2.0f;
                                float f5 = rect4.bottom - i8;
                                float max = Math.max(state2.getWidth() / (i7 - i6), state2.getHeight() / f5);
                                animationDelegate3.matrix.reset();
                                animationDelegate3.matrix.setScale(max, max, f3, f4);
                                animationDelegate3.matrix.postTranslate(((state2.getWidth() / 2.0f) + state2.left) - f3, (((f5 * max) - f5) / 2.0f) + (state2.top - rect4.top));
                                float f6 = state2.left - rect4.left;
                                float f7 = state2.top - rect4.top;
                                animationDelegate3.windowCropF.set(f6, f7, state2.getWidth() + f6, state2.getHeight() + f7);
                                animationDelegate3.matrix.invert(animationDelegate3.invertMatrix);
                                animationDelegate3.invertMatrix.mapRect(animationDelegate3.windowCropF);
                                animationDelegate3.windowCrop.set(MathKt.roundToInt(animationDelegate3.windowCropF.left), MathKt.roundToInt(animationDelegate3.windowCropF.top), MathKt.roundToInt(animationDelegate3.windowCropF.right), MathKt.roundToInt(animationDelegate3.windowCropF.bottom));
                                ActivityTransitionAnimator.Controller controller2 = animationDelegate3.controller;
                                if (controller2.isLaunching()) {
                                    TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
                                    j = 150;
                                } else {
                                    TransitionAnimator.Timings timings2 = ActivityTransitionAnimator.TIMINGS;
                                    j = 0;
                                }
                                long j2 = controller2.isLaunching() ? 183L : 150L;
                                PorterDuffXfermode porterDuffXfermode2 = TransitionAnimator.SRC_MODE;
                                float progress = TransitionAnimator.Companion.getProgress(ActivityTransitionAnimator.TIMINGS, f2, j, j2);
                                animationDelegate3.transactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(remoteAnimationTarget5.leash).withAlpha(controller2.isBelowAnimatingWindow() ? controller2.isLaunching() ? ActivityTransitionAnimator.INTERPOLATORS.contentAfterFadeInInterpolator.getInterpolation(progress) : 1 - ActivityTransitionAnimator.INTERPOLATORS.contentBeforeFadeOutInterpolator.getInterpolation(progress) : 1.0f).withMatrix(animationDelegate3.matrix).withWindowCrop(animationDelegate3.windowCrop).withCornerRadius(Math.max(state2.topCornerRadius, state2.bottomCornerRadius) / max).withVisibility(true).build()});
                            }
                            RemoteAnimationTarget remoteAnimationTarget6 = this.$navigationBar;
                            if (remoteAnimationTarget6 != null && animationDelegate3.transactionApplierView.getViewRootImpl() != null && remoteAnimationTarget6.leash.isValid()) {
                                PorterDuffXfermode porterDuffXfermode3 = TransitionAnimator.SRC_MODE;
                                TransitionAnimator.Timings timings3 = ActivityTransitionAnimator.TIMINGS;
                                float progress2 = TransitionAnimator.Companion.getProgress(timings3, f2, ActivityTransitionAnimator.ANIMATION_DELAY_NAV_FADE_IN, 133L);
                                SyncRtSurfaceTransactionApplier.SurfaceParams.Builder builder = new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(remoteAnimationTarget6.leash);
                                if (progress2 > 0.0f) {
                                    animationDelegate3.matrix.reset();
                                    animationDelegate3.matrix.setTranslate(0.0f, state2.top - remoteAnimationTarget6.sourceContainerBounds.top);
                                    animationDelegate3.windowCrop.set(state2.left, 0, state2.right, state2.getHeight());
                                    builder.withAlpha(((PathInterpolator) ActivityTransitionAnimator.NAV_FADE_IN_INTERPOLATOR).getInterpolation(progress2)).withMatrix(animationDelegate3.matrix).withWindowCrop(animationDelegate3.windowCrop).withVisibility(true);
                                } else {
                                    builder.withAlpha(1.0f - ActivityTransitionAnimator.NAV_FADE_OUT_INTERPOLATOR.getInterpolation(TransitionAnimator.Companion.getProgress(timings3, f2, 0L, 133L)));
                                }
                                animationDelegate3.transactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{builder.build()});
                            }
                            animationDelegate3.listener.onTransitionAnimationProgress(f2);
                            ActivityTransitionAnimator.Controller.this.onTransitionAnimationProgress(state2, f, f2);
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final void onTransitionAnimationStart(boolean z3) {
                            this.this$0.listener.onTransitionAnimationStart();
                            boolean z4 = ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION;
                            ActivityTransitionAnimator.Controller controller2 = ActivityTransitionAnimator.Controller.this;
                            if (z4) {
                                Log.d("ActivityTransitionAnimator", "Calling controller.onTransitionAnimationStart(isExpandingFullyAbove=" + z3 + ") [controller=" + controller2 + "]");
                            }
                            controller2.onTransitionAnimationStart(z3);
                        }

                        @Override // com.android.systemui.animation.TransitionAnimator.Controller
                        public final void setTransitionContainer(ViewGroup viewGroup) {
                            this.$$delegate_0.setTransitionContainer(viewGroup);
                        }
                    }, state, i2, !controller.isBelowAnimatingWindow(), !controller.isBelowAnimatingWindow());
                }
            });
        }

        public static /* synthetic */ void getDelegate$annotations() {
        }
    }
}
