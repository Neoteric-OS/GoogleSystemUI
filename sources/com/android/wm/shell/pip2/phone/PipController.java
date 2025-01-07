package com.android.wm.shell.pip2.phone;

import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Slog;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.Preconditions;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.common.pip.IPip;
import com.android.wm.shell.common.pip.IPipAnimationListener$Stub$Proxy;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipSnapAlgorithm;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.Pip;
import com.android.wm.shell.pip2.phone.PipController;
import com.android.wm.shell.pip2.phone.PipTransitionState;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipController implements ConfigurationChangeListener, PipTransitionState.PipTransitionStateChangedListener, DisplayController.OnDisplaysChangedListener, DisplayChangeController.OnDisplayChangingListener, RemoteCallable {
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final PipImpl mImpl;
    public final ShellExecutor mMainExecutor;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda1 mOnIsInPipStateChangedListener;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public IPipImpl.AnonymousClass1 mPipRecentsAnimationListener;
    public final PipScheduler mPipScheduler;
    public final PipTouchHandler mPipTouchHandler;
    public final PipTransitionState mPipTransitionState;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final TaskStackListenerImpl mTaskStackListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    interface PipAnimationListener {
    }

    public PipController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, DisplayController displayController, DisplayInsetsController displayInsetsController, PipBoundsState pipBoundsState, PipBoundsAlgorithm pipBoundsAlgorithm, PipDisplayLayoutState pipDisplayLayoutState, PipScheduler pipScheduler, TaskStackListenerImpl taskStackListenerImpl, ShellTaskOrganizer shellTaskOrganizer, PipTransitionState pipTransitionState, PipTouchHandler pipTouchHandler, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellController = shellController;
        this.mDisplayController = displayController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mPipBoundsState = pipBoundsState;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mPipScheduler = pipScheduler;
        this.mTaskStackListener = taskStackListenerImpl;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mPipTransitionState = pipTransitionState;
        pipTransitionState.addPipTransitionStateChangedListener(this);
        this.mPipTouchHandler = pipTouchHandler;
        this.mMainExecutor = shellExecutor;
        this.mImpl = new PipImpl();
        if (PipUtils.isPip2ExperimentEnabled()) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.pip2.phone.PipController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    final PipController pipController = PipController.this;
                    pipController.getClass();
                    pipController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.pip2.phone.PipController$$ExternalSyntheticLambda1
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            PipController pipController2 = PipController.this;
                            PrintWriter printWriter = (PrintWriter) obj;
                            pipController2.getClass();
                            printWriter.println("PipController");
                            pipController2.mPipBoundsAlgorithm.dump(printWriter, "  ");
                            pipController2.mPipBoundsState.dump(printWriter);
                            pipController2.mPipDisplayLayoutState.dump(printWriter);
                            PipTransitionState pipTransitionState2 = pipController2.mPipTransitionState;
                            pipTransitionState2.getClass();
                            printWriter.println("  PipTransitionState");
                            printWriter.println("    mState=".concat(PipTransitionState.stateToString(pipTransitionState2.mState)));
                        }
                    }, pipController);
                    int displayId = pipController.mContext.getDisplayId();
                    PipDisplayLayoutState pipDisplayLayoutState2 = pipController.mPipDisplayLayoutState;
                    pipDisplayLayoutState2.mDisplayId = displayId;
                    Context context2 = pipController.mContext;
                    pipDisplayLayoutState2.mDisplayLayout.set(new DisplayLayout(context2, context2.getDisplay()));
                    DisplayController displayController2 = pipController.mDisplayController;
                    displayController2.addDisplayChangingController(pipController);
                    int i = pipDisplayLayoutState2.mDisplayId;
                    pipController.mDisplayInsetsController.addInsetsChangedListener(i, new DisplayInsetsController.OnInsetsChangedListener(displayController2, i) { // from class: com.android.wm.shell.pip2.phone.PipController.1
                        public final DisplayController mDisplayController;
                        public final int mDisplayId;
                        public final InsetsState mInsetsState = new InsetsState();
                        public final Rect mTmpBounds = new Rect();

                        {
                            this.mDisplayController = displayController2;
                            this.mDisplayId = i;
                        }

                        public final Pair getImeVisibilityAndHeight(InsetsState insetsState) {
                            InsetsSource peekSource = insetsState.peekSource(InsetsSource.ID_IME);
                            Rect frame = (peekSource == null || !peekSource.isVisible()) ? null : peekSource.getFrame();
                            return new Pair(Boolean.valueOf(peekSource != null ? peekSource.isVisible() : false), Integer.valueOf(frame != null ? this.mTmpBounds.bottom - frame.top : 0));
                        }

                        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
                        public final void insetsChanged(InsetsState insetsState) {
                            DisplayLayout displayLayout;
                            if (Intrinsics.areEqual(this.mInsetsState, insetsState) || (displayLayout = this.mDisplayController.getDisplayLayout(this.mDisplayId)) == null) {
                                return;
                            }
                            displayLayout.getStableBounds(this.mTmpBounds);
                            boolean booleanValue = ((Boolean) getImeVisibilityAndHeight(this.mInsetsState).getFirst()).booleanValue();
                            int intValue = ((Number) getImeVisibilityAndHeight(this.mInsetsState).getSecond()).intValue();
                            final boolean booleanValue2 = ((Boolean) getImeVisibilityAndHeight(insetsState).getFirst()).booleanValue();
                            int intValue2 = ((Number) getImeVisibilityAndHeight(insetsState).getSecond()).intValue();
                            this.mInsetsState.set(insetsState, true);
                            if (booleanValue == booleanValue2 && intValue == intValue2) {
                                return;
                            }
                            final PipTouchHandler pipTouchHandler2 = PipController.this.mPipTouchHandler;
                            pipTouchHandler2.mIsImeShowing = booleanValue2;
                            pipTouchHandler2.mImeHeight = intValue2;
                            pipTouchHandler2.updateMovementBounds();
                            Runnable runnable = new Runnable() { // from class: com.android.wm.shell.pip2.phone.PipTouchHandler$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    PipTouchHandler pipTouchHandler3 = PipTouchHandler.this;
                                    boolean z = booleanValue2;
                                    PipBoundsState pipBoundsState2 = pipTouchHandler3.mPipBoundsState;
                                    int i2 = pipBoundsState2.mMovementBounds.bottom - pipBoundsState2.getBounds().top;
                                    boolean z2 = pipBoundsState2.mHasUserMovedPip || pipBoundsState2.mHasUserResizedPip;
                                    if (!z && !z2) {
                                        i2 = pipTouchHandler3.mPipBoundsAlgorithm.getEntryDestinationBounds().top - pipBoundsState2.getBounds().top;
                                    }
                                    if ((!z || i2 >= 0) && (z || z2)) {
                                        return;
                                    }
                                    pipTouchHandler3.mMotionHelper.animateToOffset(i2, pipBoundsState2.getBounds());
                                }
                            };
                            PipTransitionState pipTransitionState2 = pipTouchHandler2.mPipTransitionState;
                            pipTransitionState2.mOnIdlePipTransitionStateRunnable = runnable;
                            int i2 = pipTransitionState2.mState;
                            if (i2 == 3 || i2 == 6) {
                                runnable.run();
                                pipTransitionState2.mOnIdlePipTransitionStateRunnable = null;
                            }
                        }
                    });
                    Supplier supplier = new Supplier() { // from class: com.android.wm.shell.pip2.phone.PipController$$ExternalSyntheticLambda2
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            PipController pipController2 = PipController.this;
                            pipController2.getClass();
                            return new PipController.IPipImpl(pipController2);
                        }
                    };
                    ShellController shellController2 = pipController.mShellController;
                    shellController2.addExternalInterface("extra_shell_pip", supplier, pipController);
                    shellController2.addConfigurationChangeListener(pipController);
                    pipController.mTaskStackListener.addListener(new TaskStackListenerCallback() { // from class: com.android.wm.shell.pip2.phone.PipController.2
                        @Override // com.android.wm.shell.common.TaskStackListenerCallback
                        public final void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
                            if (runningTaskInfo.getWindowingMode() != 2) {
                                return;
                            }
                            PipController.this.mPipScheduler.scheduleExitPipViaExpand();
                        }
                    });
                }
            }, this);
        }
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        this.mPipDisplayLayoutState.reloadResources();
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onDensityOrFontScaleChanged$1() {
        IPipImpl.AnonymousClass1 anonymousClass1 = this.mPipRecentsAnimationListener;
        if (anonymousClass1 != null) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius);
            int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius);
            IInterface iInterface = IPipImpl.this.mListener.mListener;
            if (iInterface == null) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                return;
            }
            try {
                ((IPipAnimationListener$Stub$Proxy) iInterface).onPipResourceDimensionsChanged(dimensionPixelSize, dimensionPixelSize2);
            } catch (RemoteException e) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
            }
        }
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        PipDisplayLayoutState pipDisplayLayoutState = this.mPipDisplayLayoutState;
        if (i != pipDisplayLayoutState.mDisplayId) {
            return;
        }
        pipDisplayLayoutState.mDisplayLayout.set(this.mDisplayController.getDisplayLayout(i));
    }

    @Override // com.android.wm.shell.common.DisplayChangeController.OnDisplayChangingListener
    public final void onDisplayChange$1(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        PipDisplayLayoutState pipDisplayLayoutState = this.mPipDisplayLayoutState;
        if (i != pipDisplayLayoutState.mDisplayId) {
            return;
        }
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        Rect bounds = pipBoundsState.getBounds();
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        Rect movementBounds = pipBoundsAlgorithm.getMovementBounds(bounds, true);
        PipSnapAlgorithm pipSnapAlgorithm = pipBoundsAlgorithm.mSnapAlgorithm;
        float snapFraction = pipSnapAlgorithm.getSnapFraction(0, bounds, movementBounds);
        float f = pipBoundsState.mBoundsScale;
        pipDisplayLayoutState.mDisplayLayout.set(this.mDisplayController.getDisplayLayout(i));
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        int i4 = pipTransitionState.mState;
        if (i4 <= 2 || i4 >= 7) {
            return;
        }
        float f2 = pipBoundsState.mAspectRatio;
        PipTouchHandler pipTouchHandler = this.mPipTouchHandler;
        pipTouchHandler.updateMinMaxSize(f2);
        Rect rect = new Rect(0, 0, (int) Math.ceil(pipBoundsState.mMaxSize.x * f), (int) Math.ceil(pipBoundsState.mMaxSize.y * f));
        pipBoundsState.setBounds(rect);
        pipTouchHandler.updateMovementBounds();
        Rect movementBounds2 = pipBoundsAlgorithm.getMovementBounds(rect, true);
        pipSnapAlgorithm.getClass();
        PipSnapAlgorithm.applySnapFraction(rect, movementBounds2, snapFraction);
        pipBoundsState.setBounds(rect);
        windowContainerTransaction.setBounds(pipTransitionState.mPipTaskToken, rect);
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (i2 != 1) {
            if (i2 != 3) {
                if (i2 == 8 && (edgeBackGestureHandler$$ExternalSyntheticLambda1 = this.mOnIsInPipStateChangedListener) != null) {
                    edgeBackGestureHandler$$ExternalSyntheticLambda1.accept(Boolean.FALSE);
                    return;
                }
                return;
            }
            if (pipTransitionState.mInSwipePipToHomeTransition) {
                pipTransitionState.mInSwipePipToHomeTransition = false;
                pipTransitionState.mSwipePipToHomeOverlay = null;
                pipTransitionState.mSwipePipToHomeAppBounds.setEmpty();
            }
            EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda12 = this.mOnIsInPipStateChangedListener;
            if (edgeBackGestureHandler$$ExternalSyntheticLambda12 != null) {
                edgeBackGestureHandler$$ExternalSyntheticLambda12.accept(Boolean.TRUE);
                return;
            }
            return;
        }
        Preconditions.checkState(bundle != null, "No extra bundle for " + pipTransitionState);
        SurfaceControl surfaceControl = (SurfaceControl) bundle.getParcelable("swipe_to_pip_overlay", SurfaceControl.class);
        Rect rect = (Rect) bundle.getParcelable("pip_app_bounds", Rect.class);
        Preconditions.checkState(rect != null, "App bounds can't be null for " + pipTransitionState);
        pipTransitionState.mInSwipePipToHomeTransition = true;
        if (surfaceControl == null || rect.isEmpty()) {
            return;
        }
        pipTransitionState.mSwipePipToHomeOverlay = surfaceControl;
        pipTransitionState.mSwipePipToHomeAppBounds.set(rect);
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onThemeChanged() {
        Context context = this.mContext;
        this.mPipDisplayLayoutState.mDisplayLayout.set(new DisplayLayout(context, context.getDisplay()));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IPipImpl extends IPip.Stub implements ExternalInterfaceBinder {
        public PipController mController;
        public final SingleInstanceRemoteListener mListener;
        public final AnonymousClass1 mPipAnimationListener = new AnonymousClass1();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.pip2.phone.PipController$IPipImpl$1, reason: invalid class name */
        public final class AnonymousClass1 implements PipAnimationListener {
            public AnonymousClass1() {
            }
        }

        public IPipImpl(PipController pipController) {
            this.mController = pipController;
            this.mListener = new SingleInstanceRemoteListener(pipController, new Consumer() { // from class: com.android.wm.shell.pip2.phone.PipController$IPipImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PipController pipController2 = (PipController) obj;
                    PipController.IPipImpl.AnonymousClass1 anonymousClass1 = PipController.IPipImpl.this.mPipAnimationListener;
                    pipController2.mPipRecentsAnimationListener = anonymousClass1;
                    if (anonymousClass1 != null) {
                        int dimensionPixelSize = pipController2.mContext.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius);
                        int dimensionPixelSize2 = pipController2.mContext.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius);
                        IInterface iInterface = PipController.IPipImpl.this.mListener.mListener;
                        if (iInterface == null) {
                            Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                            return;
                        }
                        try {
                            ((IPipAnimationListener$Stub$Proxy) iInterface).onPipResourceDimensionsChanged(dimensionPixelSize, dimensionPixelSize2);
                        } catch (RemoteException e) {
                            Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                        }
                    }
                }
            }, new PipController$IPipImpl$$ExternalSyntheticLambda5());
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setLauncherKeepClearAreaHeight(final int i, final boolean z) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setLauncherKeepClearAreaHeight", new Consumer() { // from class: com.android.wm.shell.pip2.phone.PipController$IPipImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    boolean z2 = z;
                    int i2 = i;
                    PipController pipController = (PipController) obj;
                    pipController.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3459105394737332524L, 7, Boolean.valueOf(z2), Long.valueOf(i2));
                    }
                    PipBoundsState pipBoundsState = pipController.mPipBoundsState;
                    if (z2) {
                        PipDisplayLayoutState pipDisplayLayoutState = pipController.mPipDisplayLayoutState;
                        pipBoundsState.setNamedUnrestrictedKeepClearArea(0, new Rect(0, pipDisplayLayoutState.getDisplayBounds().bottom - i2, pipDisplayLayoutState.getDisplayBounds().right, pipDisplayLayoutState.getDisplayBounds().bottom));
                    } else {
                        pipBoundsState.setNamedUnrestrictedKeepClearArea(0, null);
                    }
                    PipTouchHandler pipTouchHandler = pipController.mPipTouchHandler;
                    pipTouchHandler.mIsShelfShowing = z2;
                    pipTouchHandler.mShelfHeight = i2;
                    HandlerExecutor handlerExecutor = (HandlerExecutor) pipTouchHandler.mMainExecutor;
                    PipTouchHandler$$ExternalSyntheticLambda2 pipTouchHandler$$ExternalSyntheticLambda2 = pipTouchHandler.mMoveOnShelVisibilityChanged;
                    handlerExecutor.removeCallbacks(pipTouchHandler$$ExternalSyntheticLambda2);
                    if (z2) {
                        pipTouchHandler$$ExternalSyntheticLambda2.run();
                    } else {
                        handlerExecutor.executeDelayed(pipTouchHandler$$ExternalSyntheticLambda2, PipTouchHandler.PIP_KEEP_CLEAR_AREAS_DELAY);
                    }
                }
            }, false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setPipAnimationListener(final IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setPipAnimationListener", new Consumer() { // from class: com.android.wm.shell.pip2.phone.PipController$IPipImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PipController.IPipImpl iPipImpl = PipController.IPipImpl.this;
                    IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy2 = iPipAnimationListener$Stub$Proxy;
                    if (iPipAnimationListener$Stub$Proxy2 != null) {
                        iPipImpl.mListener.register(iPipAnimationListener$Stub$Proxy2);
                    } else {
                        iPipImpl.mListener.unregister();
                    }
                }
            }, false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final Rect startSwipePipToHome(final ComponentName componentName, final ActivityInfo activityInfo, final PictureInPictureParams pictureInPictureParams, final int i, final Rect rect) {
            final Rect[] rectArr = new Rect[1];
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startSwipePipToHome", new Consumer(rectArr, componentName, activityInfo, pictureInPictureParams, i, rect) { // from class: com.android.wm.shell.pip2.phone.PipController$IPipImpl$$ExternalSyntheticLambda0
                public final /* synthetic */ Rect[] f$0;
                public final /* synthetic */ ComponentName f$1;
                public final /* synthetic */ ActivityInfo f$2;
                public final /* synthetic */ PictureInPictureParams f$3;
                public final /* synthetic */ Rect f$5;

                {
                    this.f$5 = rect;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Rect[] rectArr2 = this.f$0;
                    ComponentName componentName2 = this.f$1;
                    ActivityInfo activityInfo2 = this.f$2;
                    PictureInPictureParams pictureInPictureParams2 = this.f$3;
                    Rect rect2 = this.f$5;
                    PipController pipController = (PipController) obj;
                    pipController.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 8767559398966243625L, 0, String.valueOf(componentName2));
                    }
                    PipBoundsState pipBoundsState = pipController.mPipBoundsState;
                    pipBoundsState.setNamedUnrestrictedKeepClearArea(0, rect2);
                    PipBoundsAlgorithm pipBoundsAlgorithm = pipController.mPipBoundsAlgorithm;
                    pipBoundsState.setBoundsStateForEntry(componentName2, activityInfo2, pictureInPictureParams2, pipBoundsAlgorithm);
                    rectArr2[0] = pipBoundsAlgorithm.getEntryDestinationBounds();
                }
            }, true);
            return rectArr[0];
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void stopSwipePipToHome(final int i, final ComponentName componentName, final Rect rect, final SurfaceControl surfaceControl, final Rect rect2, final Rect rect3) {
            if (surfaceControl != null) {
                surfaceControl.setUnreleasedWarningCallSite("PipController.stopSwipePipToHome");
            }
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "stopSwipePipToHome", new Consumer(i, componentName, rect, surfaceControl, rect2, rect3) { // from class: com.android.wm.shell.pip2.phone.PipController$IPipImpl$$ExternalSyntheticLambda1
                public final /* synthetic */ int f$0;
                public final /* synthetic */ ComponentName f$1;
                public final /* synthetic */ SurfaceControl f$3;
                public final /* synthetic */ Rect f$4;

                {
                    this.f$3 = surfaceControl;
                    this.f$4 = rect2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    int i2 = this.f$0;
                    ComponentName componentName2 = this.f$1;
                    SurfaceControl surfaceControl2 = this.f$3;
                    Rect rect4 = this.f$4;
                    PipController pipController = (PipController) obj;
                    pipController.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -394357184756239568L, 0, String.valueOf(componentName2));
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("swipe_to_pip_overlay", surfaceControl2);
                    bundle.putParcelable("pip_app_bounds", rect4);
                    pipController.mPipTransitionState.setState(1, bundle);
                    if (surfaceControl2 != null) {
                        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                        pipController.mShellTaskOrganizer.reparentChildSurfaceToTask(i2, transaction, surfaceControl2);
                        transaction.setLayer(surfaceControl2, Integer.MAX_VALUE);
                        transaction.apply();
                    }
                    IInterface iInterface = PipController.IPipImpl.this.mListener.mListener;
                    if (iInterface == null) {
                        Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                        return;
                    }
                    try {
                        ((IPipAnimationListener$Stub$Proxy) iInterface).onPipAnimationStarted();
                    } catch (RemoteException e) {
                        Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                    }
                }
            }, false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setLauncherAppIconSize(int i) {
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setPipAnimationTypeToAlpha() {
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void abortSwipePipToHome(int i, ComponentName componentName) {
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setShelfHeight(int i, boolean z) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipImpl implements Pip {
        public PipImpl() {
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void addPipExclusionBoundsChangeListener(Consumer consumer) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$PipImpl$$ExternalSyntheticLambda0(this, consumer, 0));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void removePipExclusionBoundsChangeListener(Consumer consumer) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$PipImpl$$ExternalSyntheticLambda0(this, consumer, 1));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void setOnIsInPipStateChangedListener(EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$PipImpl$$ExternalSyntheticLambda0(this, edgeBackGestureHandler$$ExternalSyntheticLambda1));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void showPictureInPictureMenu() {
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void onSystemUiStateChanged(long j, boolean z) {
        }
    }
}
