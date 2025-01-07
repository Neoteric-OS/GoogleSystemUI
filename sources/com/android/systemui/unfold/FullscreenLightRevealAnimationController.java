package com.android.systemui.unfold;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.Choreographer;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceSession;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.LightRevealEffect;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.unfold.FullscreenLightRevealAnimationController$init$1;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider$addCallback$1;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.displayareahelper.DisplayAreaHelperController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullscreenLightRevealAnimationController {
    public final CoroutineScope applicationScope;
    public ExecutorImpl bgExecutor;
    public final Handler bgHandler;
    public final Context context;
    public int currentRotation;
    public final Optional displayAreaHelper;
    public final Lambda displaySelector;
    public final DisplayTracker displayTracker;
    public final Executor executor;
    public final List internalDisplayInfos;
    public boolean isTouchBlocked;
    public final Lambda lightRevealEffectFactory;
    public final String overlayTitle;
    public SurfaceControlViewHost root;
    public final RotationChangeProvider rotationChangeProvider;
    public final RotationWatcher rotationWatcher = new RotationWatcher();
    public LightRevealScrim scrimView;
    public final ThreadFactoryImpl threadFactory;
    public WindowlessWindowManager wwm;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RotationWatcher implements RotationChangeProvider.RotationListener {
        public RotationWatcher() {
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        @Override // com.android.systemui.unfold.updates.RotationChangeProvider.RotationListener
        public final void onRotationChanged(int i) {
            FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = FullscreenLightRevealAnimationController.this;
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("FullscreenLightRevealAnimation#onRotationChanged");
            }
            try {
                fullscreenLightRevealAnimationController.ensureInBackground();
                if (fullscreenLightRevealAnimationController.currentRotation != i) {
                    fullscreenLightRevealAnimationController.currentRotation = i;
                    LightRevealScrim lightRevealScrim = fullscreenLightRevealAnimationController.scrimView;
                    if (lightRevealScrim != null) {
                        lightRevealScrim.setRevealEffect((LightRevealEffect) fullscreenLightRevealAnimationController.lightRevealEffectFactory.invoke(Integer.valueOf(i)));
                    }
                    SurfaceControlViewHost surfaceControlViewHost = fullscreenLightRevealAnimationController.root;
                    if (surfaceControlViewHost != null) {
                        surfaceControlViewHost.relayout(fullscreenLightRevealAnimationController.getLayoutParams());
                    }
                }
            } finally {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FullscreenLightRevealAnimationController(Context context, DisplayManager displayManager, ThreadFactoryImpl threadFactoryImpl, Handler handler, RotationChangeProvider rotationChangeProvider, Optional optional, DisplayTracker displayTracker, CoroutineScope coroutineScope, Executor executor, Function1 function1, Function1 function12, String str) {
        this.context = context;
        this.bgHandler = handler;
        this.rotationChangeProvider = rotationChangeProvider;
        this.displayAreaHelper = optional;
        this.displayTracker = displayTracker;
        this.applicationScope = coroutineScope;
        this.executor = executor;
        this.displaySelector = (Lambda) function1;
        this.lightRevealEffectFactory = (Lambda) function12;
        this.overlayTitle = str;
        this.currentRotation = context.getDisplay().getRotation();
        Display[] displays = displayManager.getDisplays("android.hardware.display.category.ALL_INCLUDING_DISABLED");
        ArrayList arrayList = new ArrayList(displays.length);
        for (Display display : displays) {
            DisplayInfo displayInfo = new DisplayInfo();
            display.getDisplayInfo(displayInfo);
            arrayList.add(displayInfo);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((DisplayInfo) obj).type == 1) {
                arrayList2.add(obj);
            }
        }
        this.internalDisplayInfos = arrayList2;
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final void addOverlay(float f, final Runnable runnable) {
        if (this.wwm == null) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        ensureInBackground();
        ensureOverlayRemoved();
        WindowlessWindowManager windowlessWindowManager = this.wwm;
        if (windowlessWindowManager == null) {
            windowlessWindowManager = null;
        }
        final ExecutorImpl executorImpl = this.bgExecutor;
        if (executorImpl == null) {
            executorImpl = null;
        }
        Context context = this.context;
        SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(context, context.getDisplay(), windowlessWindowManager, "FullscreenLightRevealAnimationController");
        WindowManager.LayoutParams layoutParams = getLayoutParams();
        LightRevealScrim lightRevealScrim = new LightRevealScrim(this.context, null, Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height));
        lightRevealScrim.setRevealEffect((LightRevealEffect) this.lightRevealEffectFactory.invoke(Integer.valueOf(this.currentRotation)));
        lightRevealScrim.setRevealAmount(f);
        surfaceControlViewHost.setView(lightRevealScrim, layoutParams);
        if (runnable != null) {
            Trace.beginAsyncSection("FullscreenLightRevealAnimation#relayout", 0);
            surfaceControlViewHost.relayout(layoutParams, new WindowlessWindowManager.ResizeCompleteCallback() { // from class: com.android.systemui.unfold.FullscreenLightRevealAnimationController$prepareOverlay$1
                public final void finished(SurfaceControl.Transaction transaction) {
                    long vsyncId = Choreographer.getSfInstance().getVsyncId();
                    transaction.setFrameTimelineVsync(vsyncId).apply();
                    SurfaceControl.Transaction frameTimelineVsync = transaction.setFrameTimelineVsync(vsyncId + 1);
                    ExecutorImpl executorImpl2 = ExecutorImpl.this;
                    final Runnable runnable2 = runnable;
                    frameTimelineVsync.addTransactionCommittedListener(executorImpl2, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.systemui.unfold.FullscreenLightRevealAnimationController$prepareOverlay$1.1
                        @Override // android.view.SurfaceControl.TransactionCommittedListener
                        public final void onTransactionCommitted() {
                            Trace.endAsyncSection("FullscreenLightRevealAnimation#relayout", 0);
                            runnable2.run();
                        }
                    }).apply();
                }
            });
        }
        this.root = surfaceControlViewHost;
        this.scrimView = lightRevealScrim;
    }

    public final void ensureInBackground() {
        if (!Intrinsics.areEqual(Looper.myLooper(), this.bgHandler.getLooper())) {
            throw new IllegalStateException("Not being executed in the background!");
        }
    }

    public final void ensureOverlayRemoved() {
        ensureInBackground();
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("ensureOverlayRemoved");
        }
        try {
            SurfaceControlViewHost surfaceControlViewHost = this.root;
            if (surfaceControlViewHost != null) {
                surfaceControlViewHost.release();
            }
            this.root = null;
            this.scrimView = null;
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final WindowManager.LayoutParams getLayoutParams() {
        DisplayInfo displayInfo = (DisplayInfo) this.displaySelector.invoke(this.internalDisplayInfos);
        if (displayInfo == null) {
            throw new IllegalArgumentException("No internal displays found!");
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        int i = this.currentRotation;
        if (i == 0 || i == 2) {
            layoutParams.height = displayInfo.getNaturalHeight();
            layoutParams.width = displayInfo.getNaturalWidth();
        } else {
            layoutParams.height = displayInfo.getNaturalWidth();
            layoutParams.width = displayInfo.getNaturalHeight();
        }
        layoutParams.format = -3;
        layoutParams.type = 2026;
        layoutParams.setTitle(this.overlayTitle);
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.flags = 24;
        layoutParams.setTrustedOverlay();
        layoutParams.packageName = this.context.getOpPackageName();
        return layoutParams;
    }

    public final void init() {
        this.bgExecutor = new ExecutorImpl(this.bgHandler.getLooper());
        RotationChangeProvider rotationChangeProvider = this.rotationChangeProvider;
        rotationChangeProvider.getClass();
        rotationChangeProvider.bgHandler.post(new RotationChangeProvider$addCallback$1(rotationChangeProvider, this.rotationWatcher));
        final FullscreenLightRevealAnimationController$init$1 fullscreenLightRevealAnimationController$init$1 = new FullscreenLightRevealAnimationController$init$1(this);
        final SurfaceControl.Builder name = new SurfaceControl.Builder(new SurfaceSession()).setContainerLayer().setName("FoldUnfoldAnimationContainer");
        final DisplayAreaHelperController displayAreaHelperController = (DisplayAreaHelperController) this.displayAreaHelper.get();
        this.displayTracker.getClass();
        ((HandlerExecutor) displayAreaHelperController.mExecutor).execute(new Runnable() { // from class: com.android.wm.shell.displayareahelper.DisplayAreaHelperController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DisplayAreaHelperController displayAreaHelperController2 = DisplayAreaHelperController.this;
                SurfaceControl.Builder builder = name;
                FullscreenLightRevealAnimationController$init$1 fullscreenLightRevealAnimationController$init$12 = fullscreenLightRevealAnimationController$init$1;
                SurfaceControl surfaceControl = (SurfaceControl) displayAreaHelperController2.mRootDisplayAreaOrganizer.mLeashes.get(0);
                if (surfaceControl != null) {
                    builder.setParent(surfaceControl);
                }
                fullscreenLightRevealAnimationController$init$12.accept(builder);
            }
        });
    }
}
