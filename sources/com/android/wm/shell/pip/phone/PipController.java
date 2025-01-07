package com.android.wm.shell.pip.phone;

import android.app.ActivityTaskManager;
import android.app.PictureInPictureParams;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Size;
import android.util.Slog;
import android.view.InsetsState;
import android.view.SurfaceControl;
import android.view.ViewConfiguration;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.R;
import com.android.wm.shell.WindowManagerShellWrapper;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.TabletopModeController;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.common.pip.IPip;
import com.android.wm.shell.common.pip.IPipAnimationListener$Stub$Proxy;
import com.android.wm.shell.common.pip.PhonePipKeepClearAlgorithm;
import com.android.wm.shell.common.pip.PipAppOpsListener;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipDisplayLayoutState;
import com.android.wm.shell.common.pip.PipMediaController;
import com.android.wm.shell.common.pip.PipMediaController$mSessionsChangedListener$1;
import com.android.wm.shell.common.pip.PipSnapAlgorithm;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PinnedStackListenerForwarder;
import com.android.wm.shell.pip.Pip;
import com.android.wm.shell.pip.PipAnimationController;
import com.android.wm.shell.pip.PipParamsChangedForwarder;
import com.android.wm.shell.pip.PipSurfaceTransactionHelper;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip.PipTransitionState;
import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ConfigurationChangeListener;
import com.android.wm.shell.sysui.KeyguardChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.sysui.UserChangeListener;
import com.android.wm.shell.transition.Transitions;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipController implements PipTransitionController.PipTransitionCallback, RemoteCallable, ConfigurationChangeListener, KeyguardChangeListener, UserChangeListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long PIP_KEEP_CLEAR_AREAS_DELAY = SystemProperties.getLong("persist.wm.debug.pip_keep_clear_areas_delay", 200);
    public final PipAppOpsListener mAppOpsListener;
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final int mEnterAnimationDuration;
    public final Handler mHandler;
    public boolean mIsInFixedRotation;
    public boolean mIsKeyguardShowingOrAnimating;
    public final ShellExecutor mMainExecutor;
    public final PipMediaController mMediaController;
    public final PhonePipMenuController mMenuController;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda1 mOnIsInPipStateChangedListener;
    public final Optional mOneHandedController;
    public PipAnimationListener mPinnedStackAnimationRecentsCallback;
    public final PipAnimationController mPipAnimationController;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public PipInputConsumer mPipInputConsumer;
    public final PhonePipKeepClearAlgorithm mPipKeepClearAlgorithm;
    public final PipParamsChangedForwarder mPipParamsChangedForwarder;
    public final PipTaskOrganizer mPipTaskOrganizer;
    public final PipTransitionController mPipTransitionController;
    public final PipTransitionState mPipTransitionState;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final TabletopModeController mTabletopModeController;
    public final TaskStackListenerImpl mTaskStackListener;
    public final PipTouchHandler mTouchHandler;
    public final WindowManagerShellWrapper mWindowManagerShellWrapper;
    public final Rect mTmpInsetBounds = new Rect();
    public final PipController$$ExternalSyntheticLambda3 mMovePipInResponseToKeepClearAreasChangeCallback = new PipController$$ExternalSyntheticLambda3(this, 1);
    public final PipController$$ExternalSyntheticLambda3 mEnableTouchCallback = new PipController$$ExternalSyntheticLambda3(this, 2);
    public final PipControllerPinnedTaskListener mPinnedTaskListener = new PipControllerPinnedTaskListener();
    public final PipController$$ExternalSyntheticLambda2 mRotationController = new DisplayChangeController.OnDisplayChangingListener() { // from class: com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda2
        @Override // com.android.wm.shell.common.DisplayChangeController.OnDisplayChangingListener
        public final void onDisplayChange$1(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
            PipController pipController = PipController.this;
            if (i2 == i3) {
                pipController.getClass();
                return;
            }
            if (pipController.mPipTransitionController.handleRotateDisplay(i2, i3, windowContainerTransaction)) {
                return;
            }
            PipBoundsState pipBoundsState = pipController.mPipBoundsState;
            if (pipBoundsState.mPipDisplayLayoutState.getDisplayLayout().mRotation == i3) {
                pipBoundsState.mBoundsScale = Math.min(pipBoundsState.mBounds.width() / pipBoundsState.mMaxSize.x, 1.0f);
                pipController.updateMovementBounds(null, false, false, false, windowContainerTransaction);
                return;
            }
            PipTaskOrganizer pipTaskOrganizer = pipController.mPipTaskOrganizer;
            if (!pipTaskOrganizer.isInPip() || pipTaskOrganizer.mPipTransitionState.mState == 2) {
                pipController.mPipDisplayLayoutState.rotateTo(i3);
                pipController.updateMovementBounds(pipBoundsState.mNormalBounds, true, false, false, windowContainerTransaction);
                if (pipTaskOrganizer.mPipTransitionState.mState == 2) {
                    pipTaskOrganizer.enterPipWithAlphaAnimation(pipTaskOrganizer.mPipBoundsAlgorithm.getEntryDestinationBounds(), pipTaskOrganizer.mEnterAnimationDuration);
                    return;
                }
                return;
            }
            PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipTaskOrganizer.mPipAnimationController.mCurrentAnimator;
            Rect bounds = (pipTransitionAnimator == null || !pipTransitionAnimator.isRunning()) ? pipTaskOrganizer.mPipBoundsState.getBounds() : new Rect(pipTransitionAnimator.mDestinationBounds);
            Rect rect = new Rect();
            Rect rect2 = pipController.mTmpInsetBounds;
            PipDisplayLayoutState pipDisplayLayoutState = pipController.mPipDisplayLayoutState;
            if (i != pipDisplayLayoutState.mDisplayId || i2 == i3) {
                return;
            }
            try {
                ActivityTaskManager.RootTaskInfo rootTaskInfo = ActivityTaskManager.getService().getRootTaskInfo(2, 0);
                if (rootTaskInfo == null) {
                    return;
                }
                PipBoundsAlgorithm pipBoundsAlgorithm = pipController.mPipBoundsAlgorithm;
                PipSnapAlgorithm pipSnapAlgorithm = pipBoundsAlgorithm.mSnapAlgorithm;
                Rect rect3 = new Rect(bounds);
                float snapFraction = pipSnapAlgorithm.getSnapFraction(pipBoundsState.mStashedState, rect3, pipBoundsAlgorithm.getMovementBounds(rect3, true));
                pipDisplayLayoutState.rotateTo(i3);
                float f = pipBoundsState.mAspectRatio;
                PipTouchHandler pipTouchHandler = pipController.mTouchHandler;
                pipTouchHandler.updatePipSizeConstraints(pipTouchHandler.mPipBoundsState.mNormalBounds, f);
                float f2 = pipBoundsState.mMaxSize.x;
                float f3 = pipBoundsState.mBoundsScale;
                rect3.set(0, 0, (int) (f2 * f3), (int) (r1.y * f3));
                PipSnapAlgorithm.applySnapFraction(rect3, pipBoundsAlgorithm.getMovementBounds(rect3, false), snapFraction, pipBoundsState.mStashedState, pipBoundsState.mStashOffset, pipDisplayLayoutState.getDisplayBounds(), pipDisplayLayoutState.getDisplayLayout().mStableInsets);
                pipBoundsAlgorithm.getInsetBounds(rect2);
                rect.set(rect3);
                windowContainerTransaction.setBounds(rootTaskInfo.token, rect);
                pipController.mMenuController.hideMenu();
                Rect bounds2 = pipBoundsState.getBounds();
                Rect rect4 = pipController.mTmpInsetBounds;
                Rect rect5 = new Rect();
                pipTouchHandler.mPipBoundsAlgorithm.getClass();
                PipBoundsAlgorithm.getMovementBounds(rect, rect4, rect5, 0);
                if ((pipTouchHandler.mPipBoundsState.mMovementBounds.bottom - pipTouchHandler.mMovementBoundsExtraOffsets) - pipTouchHandler.mBottomOffsetBufferPx <= bounds2.top) {
                    rect.offsetTo(rect.left, rect5.bottom);
                }
                if (!pipController.mIsInFixedRotation) {
                    pipBoundsState.mIsImeShowing = false;
                    pipBoundsState.mImeHeight = 0;
                    pipTouchHandler.mIsShelfShowing = false;
                    pipTouchHandler.mShelfHeight = 0;
                    pipTouchHandler.mIsImeShowing = false;
                    pipTouchHandler.mImeHeight = 0;
                }
                pipController.updateMovementBounds(rect, true, false, false, windowContainerTransaction);
            } catch (RemoteException e) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1384159325629332473L, 0, "PipController", String.valueOf(e));
                }
            }
        }
    };
    final DisplayController.OnDisplaysChangedListener mDisplaysChangedListener = new DisplayController.OnDisplaysChangedListener() { // from class: com.android.wm.shell.pip.phone.PipController.1
        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onDisplayAdded(int i) {
            PipController pipController = PipController.this;
            if (i != pipController.mPipDisplayLayoutState.mDisplayId) {
                return;
            }
            pipController.onDisplayChanged(pipController.mDisplayController.getDisplayLayout(i), true);
        }

        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onDisplayConfigurationChanged(int i, Configuration configuration) {
            PipController pipController = PipController.this;
            if (i != pipController.mPipDisplayLayoutState.mDisplayId) {
                return;
            }
            pipController.onDisplayChanged(pipController.mDisplayController.getDisplayLayout(i), true);
        }

        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onFixedRotationFinished() {
            PipController.this.mIsInFixedRotation = false;
        }

        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onFixedRotationStarted(int i) {
            PipController.this.mIsInFixedRotation = true;
        }

        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onKeepClearAreasChanged(int i, Set set, Set set2) {
            PipController pipController = PipController.this;
            if (pipController.mPipDisplayLayoutState.mDisplayId == i) {
                PipBoundsState pipBoundsState = pipController.mPipBoundsState;
                ((ArraySet) pipBoundsState.mRestrictedKeepClearAreas).clear();
                ((ArraySet) pipBoundsState.mRestrictedKeepClearAreas).addAll(set);
                ((ArraySet) pipBoundsState.mUnrestrictedKeepClearAreas).clear();
                ((ArraySet) pipBoundsState.mUnrestrictedKeepClearAreas).addAll(set2);
                HandlerExecutor handlerExecutor = (HandlerExecutor) pipController.mMainExecutor;
                PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda3 = pipController.mMovePipInResponseToKeepClearAreasChangeCallback;
                handlerExecutor.removeCallbacks(pipController$$ExternalSyntheticLambda3);
                ((HandlerExecutor) pipController.mMainExecutor).executeDelayed(pipController$$ExternalSyntheticLambda3, PipController.PIP_KEEP_CLEAR_AREAS_DELAY);
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -5881928171608984360L, 0, String.valueOf(set), String.valueOf(set2));
                }
            }
        }
    };
    public final PipImpl mImpl = new PipImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.phone.PipController$3, reason: invalid class name */
    public final class AnonymousClass3 {
        public AnonymousClass3() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.phone.PipController$4, reason: invalid class name */
    public final class AnonymousClass4 implements DisplayInsetsController.OnInsetsChangedListener {
        public AnonymousClass4() {
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsChanged(InsetsState insetsState) {
            PipController pipController = PipController.this;
            DisplayLayout displayLayout = pipController.mDisplayController.getDisplayLayout(pipController.mPipDisplayLayoutState.mDisplayId);
            if (displayLayout == null) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -6947857911936697226L, 1, Long.valueOf(pipController.mPipDisplayLayoutState.mDisplayId));
                    return;
                }
                return;
            }
            if (pipController.mIsInFixedRotation || pipController.mIsKeyguardShowingOrAnimating || displayLayout.mRotation != pipController.mPipBoundsState.mPipDisplayLayoutState.getDisplayLayout().mRotation) {
                return;
            }
            ((HandlerExecutor) pipController.mMainExecutor).executeDelayed(new PipController$4$$ExternalSyntheticLambda0(0, this), PipController.PIP_KEEP_CLEAR_AREAS_DELAY);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IPipImpl extends IPip.Stub implements ExternalInterfaceBinder {
        public PipController mController;
        public final SingleInstanceRemoteListener mListener;
        public final AnonymousClass1 mPipAnimationListener = new PipAnimationListener() { // from class: com.android.wm.shell.pip.phone.PipController.IPipImpl.1
            @Override // com.android.wm.shell.pip.phone.PipController.PipAnimationListener
            public final void onExpandPip() {
                IInterface iInterface = IPipImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy = (IPipAnimationListener$Stub$Proxy) iInterface;
                    Parcel obtain = Parcel.obtain(iPipAnimationListener$Stub$Proxy.mRemote);
                    try {
                        obtain.writeInterfaceToken("com.android.wm.shell.common.pip.IPipAnimationListener");
                        iPipAnimationListener$Stub$Proxy.mRemote.transact(3, obtain, null, 1);
                        obtain.recycle();
                    } catch (Throwable th) {
                        obtain.recycle();
                        throw th;
                    }
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }

            @Override // com.android.wm.shell.pip.phone.PipController.PipAnimationListener
            public final void onPipAnimationStarted() {
                IInterface iInterface = IPipImpl.this.mListener.mListener;
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

            @Override // com.android.wm.shell.pip.phone.PipController.PipAnimationListener
            public final void onPipResourceDimensionsChanged(int i, int i2) {
                IInterface iInterface = IPipImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    ((IPipAnimationListener$Stub$Proxy) iInterface).onPipResourceDimensionsChanged(i, i2);
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }
        };

        /* JADX WARN: Type inference failed for: r0v0, types: [com.android.wm.shell.pip.phone.PipController$IPipImpl$1] */
        public IPipImpl(PipController pipController) {
            this.mController = pipController;
            this.mListener = new SingleInstanceRemoteListener(pipController, new PipController$$ExternalSyntheticLambda13(1, this), new PipController$IPipImpl$$ExternalSyntheticLambda2(1));
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void abortSwipePipToHome(int i, ComponentName componentName) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "abortSwipePipToHome", new PipController$$ExternalSyntheticLambda13(i, componentName), false);
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setLauncherAppIconSize(final int i) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setLauncherAppIconSize", new Consumer() { // from class: com.android.wm.shell.pip.phone.PipController$IPipImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((PipController) obj).mPipBoundsState.mLauncherState.mAppIconSizePx = i;
                }
            }, false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setLauncherKeepClearAreaHeight(int i, boolean z) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setLauncherKeepClearAreaHeight", new PipController$IPipImpl$$ExternalSyntheticLambda6(i, 1, z), false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setPipAnimationListener(final IPipAnimationListener$Stub$Proxy iPipAnimationListener$Stub$Proxy) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setPipAnimationListener", new Consumer() { // from class: com.android.wm.shell.pip.phone.PipController$IPipImpl$$ExternalSyntheticLambda9
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
        public final void setPipAnimationTypeToAlpha() {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setPipAnimationTypeToAlpha", new PipController$IPipImpl$$ExternalSyntheticLambda2(0), false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void setShelfHeight(int i, boolean z) {
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "setShelfHeight", new PipController$IPipImpl$$ExternalSyntheticLambda6(i, 0, z), false);
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final Rect startSwipePipToHome(ComponentName componentName, ActivityInfo activityInfo, PictureInPictureParams pictureInPictureParams, int i, Rect rect) {
            Rect[] rectArr = new Rect[1];
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startSwipePipToHome", new PipController$IPipImpl$$ExternalSyntheticLambda0(rectArr, componentName, activityInfo, pictureInPictureParams, i, rect), true);
            return rectArr[0];
        }

        @Override // com.android.wm.shell.common.pip.IPip
        public final void stopSwipePipToHome(int i, ComponentName componentName, Rect rect, SurfaceControl surfaceControl, Rect rect2, Rect rect3) {
            if (surfaceControl != null) {
                surfaceControl.setUnreleasedWarningCallSite("PipController.stopSwipePipToHome");
            }
            ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "stopSwipePipToHome", new PipController$IPipImpl$$ExternalSyntheticLambda0(i, componentName, rect, surfaceControl, rect2, rect3), false);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    interface PipAnimationListener {
        void onExpandPip();

        void onPipAnimationStarted();

        void onPipResourceDimensionsChanged(int i, int i2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipControllerPinnedTaskListener extends PinnedStackListenerForwarder.PinnedTaskListener {
        public PipControllerPinnedTaskListener() {
        }

        @Override // com.android.wm.shell.pip.PinnedStackListenerForwarder.PinnedTaskListener
        public final void onImeVisibilityChanged(boolean z, int i) {
            PipController pipController = PipController.this;
            PipBoundsState pipBoundsState = pipController.mPipBoundsState;
            pipBoundsState.mIsImeShowing = z;
            pipBoundsState.mImeHeight = i;
            if (z) {
                pipBoundsState.mRestoreBounds.set(pipBoundsState.getBounds());
            }
            PipTouchHandler pipTouchHandler = pipController.mTouchHandler;
            pipTouchHandler.mIsImeShowing = z;
            pipTouchHandler.mImeHeight = i;
            if (z) {
                pipController.updatePipPositionForKeepClearAreas();
            }
        }

        @Override // com.android.wm.shell.pip.PinnedStackListenerForwarder.PinnedTaskListener
        public final void onMovementBoundsChanged(boolean z) {
            PipController.this.updateMovementBounds(null, false, z, false, null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipImpl implements Pip {
        public PipImpl() {
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void addPipExclusionBoundsChangeListener(Consumer consumer) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$PipImpl$$ExternalSyntheticLambda2(this, consumer, 1));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void onSystemUiStateChanged(final long j, final boolean z) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new Runnable(z, j) { // from class: com.android.wm.shell.pip.phone.PipController$PipImpl$$ExternalSyntheticLambda0
                public final /* synthetic */ boolean f$1;

                @Override // java.lang.Runnable
                public final void run() {
                    PipController.this.mTouchHandler.mPipResizeGestureHandler.mIsSysUiStateValid = this.f$1;
                }
            });
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void registerPipTransitionCallback(final WMShell.AnonymousClass6 anonymousClass6, final Executor executor) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip.phone.PipController$PipImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PipController.PipImpl pipImpl = PipController.PipImpl.this;
                    PipController.this.mPipTransitionController.mPipTransitionCallbacks.put(anonymousClass6, executor);
                }
            });
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void removePipExclusionBoundsChangeListener(Consumer consumer) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$PipImpl$$ExternalSyntheticLambda2(this, consumer, 0));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void setOnIsInPipStateChangedListener(EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1) {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$$ExternalSyntheticLambda4(1, this, edgeBackGestureHandler$$ExternalSyntheticLambda1));
        }

        @Override // com.android.wm.shell.pip.Pip
        public final void showPictureInPictureMenu() {
            ((HandlerExecutor) PipController.this.mMainExecutor).execute(new PipController$4$$ExternalSyntheticLambda0(1, this));
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda2] */
    public PipController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, DisplayController displayController, PipAnimationController pipAnimationController, PipAppOpsListener pipAppOpsListener, PipBoundsAlgorithm pipBoundsAlgorithm, PhonePipKeepClearAlgorithm phonePipKeepClearAlgorithm, PipBoundsState pipBoundsState, PipDisplayLayoutState pipDisplayLayoutState, PipMediaController pipMediaController, PhonePipMenuController phonePipMenuController, PipTaskOrganizer pipTaskOrganizer, PipTransitionState pipTransitionState, PipTouchHandler pipTouchHandler, PipTransitionController pipTransitionController, WindowManagerShellWrapper windowManagerShellWrapper, TaskStackListenerImpl taskStackListenerImpl, PipParamsChangedForwarder pipParamsChangedForwarder, DisplayInsetsController displayInsetsController, TabletopModeController tabletopModeController, Optional optional, ShellExecutor shellExecutor, Handler handler) {
        this.mContext = context;
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellController = shellController;
        this.mHandler = handler;
        this.mWindowManagerShellWrapper = windowManagerShellWrapper;
        this.mDisplayController = displayController;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipKeepClearAlgorithm = phonePipKeepClearAlgorithm;
        this.mPipBoundsState = pipBoundsState;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mPipTaskOrganizer = pipTaskOrganizer;
        this.mPipTransitionState = pipTransitionState;
        this.mMainExecutor = shellExecutor;
        this.mMediaController = pipMediaController;
        this.mMenuController = phonePipMenuController;
        this.mTouchHandler = pipTouchHandler;
        this.mPipAnimationController = pipAnimationController;
        this.mAppOpsListener = pipAppOpsListener;
        this.mOneHandedController = optional;
        this.mPipTransitionController = pipTransitionController;
        this.mTaskStackListener = taskStackListenerImpl;
        this.mEnterAnimationDuration = context.getResources().getInteger(R.integer.config_pipEnterAnimationDuration);
        this.mPipParamsChangedForwarder = pipParamsChangedForwarder;
        this.mDisplayInsetsController = displayInsetsController;
        this.mTabletopModeController = tabletopModeController;
        if (PipUtils.isPip2ExperimentEnabled()) {
            return;
        }
        shellInit.addInitCallback(new PipController$$ExternalSyntheticLambda3(this, 0), this);
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public boolean hasPinnedStackAnimationListener() {
        return this.mPinnedStackAnimationRecentsCallback != null;
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onConfigurationChanged(Configuration configuration) {
        this.mPipBoundsAlgorithm.reloadResources(this.mContext);
        PipTouchHandler pipTouchHandler = this.mTouchHandler;
        pipTouchHandler.mPipResizeGestureHandler.mTouchSlop = ViewConfiguration.get(r0.mContext).getScaledTouchSlop();
        pipTouchHandler.mMotionHelper.synchronizePinnedStackBounds();
        Resources resources = pipTouchHandler.mContext.getResources();
        pipTouchHandler.mBottomOffsetBufferPx = resources.getDimensionPixelSize(R.dimen.pip_bottom_offset_buffer);
        pipTouchHandler.mImeOffset = resources.getDimensionPixelSize(R.dimen.pip_ime_offset);
        PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler.mPipDismissTargetHandler;
        pipDismissTargetHandler.updateMagneticTargetSize();
        if (pipTouchHandler.mPipTaskOrganizer.isInPip()) {
            pipDismissTargetHandler.createOrUpdateDismissTarget();
        }
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        pipBoundsState.mStashOffset = pipBoundsState.mContext.getResources().getDimensionPixelSize(R.dimen.pip_stash_offset);
        pipBoundsState.mSizeSpecSource.reloadResources();
        this.mPipDisplayLayoutState.reloadResources();
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onDensityOrFontScaleChanged$1() {
        Context context = this.mContext;
        PipSurfaceTransactionHelper pipSurfaceTransactionHelper = this.mPipTaskOrganizer.mSurfaceTransactionHelper;
        pipSurfaceTransactionHelper.getClass();
        pipSurfaceTransactionHelper.mCornerRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius);
        pipSurfaceTransactionHelper.mShadowRadius = context.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius);
        PipAnimationListener pipAnimationListener = this.mPinnedStackAnimationRecentsCallback;
        if (pipAnimationListener != null) {
            pipAnimationListener.onPipResourceDimensionsChanged(this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius), this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius));
        }
    }

    public final void onDisplayChanged(DisplayLayout displayLayout, boolean z) {
        DisplayLayout displayLayout2 = this.mPipDisplayLayoutState.getDisplayLayout();
        if (displayLayout2.mWidth == displayLayout.mWidth && displayLayout2.mHeight == displayLayout.mHeight && displayLayout2.mRotation == displayLayout.mRotation && displayLayout2.mDensityDpi == displayLayout.mDensityDpi && Objects.equals(displayLayout2.mCutout, displayLayout.mCutout)) {
            return;
        }
        PipAnimationController.PipTransitionAnimator pipTransitionAnimator = this.mPipAnimationController.mCurrentAnimator;
        if (pipTransitionAnimator != null && pipTransitionAnimator.isRunning()) {
            pipTransitionAnimator.cancel();
        }
        onDisplayChangedUncheck(displayLayout, z);
    }

    public final void onDisplayChangedUncheck(DisplayLayout displayLayout, boolean z) {
        int i = 0;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (pipTransitionState.mInSwipePipToHomeTransition) {
            return;
        }
        PipController$$ExternalSyntheticLambda4 pipController$$ExternalSyntheticLambda4 = new PipController$$ExternalSyntheticLambda4(i, this, displayLayout);
        if (!pipTransitionState.hasEnteredPip() || !z) {
            pipController$$ExternalSyntheticLambda4.run();
            return;
        }
        this.mMenuController.attachPipMenuView();
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        PipSnapAlgorithm pipSnapAlgorithm = pipBoundsAlgorithm.mSnapAlgorithm;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        Rect rect = new Rect(pipBoundsState.getBounds());
        float snapFraction = pipSnapAlgorithm.getSnapFraction(pipBoundsState.mStashedState, rect, pipBoundsAlgorithm.getMovementBounds(rect, true));
        pipController$$ExternalSyntheticLambda4.run();
        float f = pipBoundsState.mMaxSize.x;
        float f2 = pipBoundsState.mBoundsScale;
        rect.set(0, 0, (int) (f * f2), (int) (r1.y * f2));
        Rect movementBounds = pipBoundsAlgorithm.getMovementBounds(rect, false);
        int i2 = pipBoundsState.mStashedState;
        int i3 = pipBoundsState.mStashOffset;
        PipDisplayLayoutState pipDisplayLayoutState = this.mPipDisplayLayoutState;
        PipSnapAlgorithm.applySnapFraction(rect, movementBounds, snapFraction, i2, i3, pipDisplayLayoutState.getDisplayBounds(), pipDisplayLayoutState.getDisplayLayout().mStableInsets);
        pipBoundsState.mHasUserResizedPip = true;
        if (pipBoundsState.mIsImeShowing) {
            pipBoundsState.mRestoreBounds.setEmpty();
        }
        this.mTouchHandler.mPipResizeGestureHandler.setUserResizeBounds(rect);
        int i4 = pipDisplayLayoutState.getDisplayLayout().mDensityDpi;
        PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
        if (i4 == 0 || pipDisplayLayoutState.getDisplayLayout().mDensityDpi == displayLayout.mDensityDpi) {
            pipTaskOrganizer.scheduleFinishResizePip(rect, 0, null);
        } else {
            pipTaskOrganizer.scheduleAnimateResizePip(rect, this.mContext.getResources().getInteger(R.integer.config_pipEnterAnimationDuration), 0);
        }
    }

    @Override // com.android.wm.shell.sysui.KeyguardChangeListener
    public final void onKeyguardDismissAnimationFinished() {
        PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
        if (pipTaskOrganizer.isInPip()) {
            this.mIsKeyguardShowingOrAnimating = false;
            pipTaskOrganizer.setPipVisibility(true);
        }
    }

    @Override // com.android.wm.shell.sysui.KeyguardChangeListener
    public final void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
        if (this.mPipTransitionState.hasEnteredPip()) {
            PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
            if (!z) {
                if (z3) {
                    return;
                }
                this.mIsKeyguardShowingOrAnimating = false;
                pipTaskOrganizer.setPipVisibility(true);
                return;
            }
            this.mIsKeyguardShowingOrAnimating = true;
            PhonePipMenuController phonePipMenuController = this.mMenuController;
            if (phonePipMenuController.isMenuVisible()) {
                phonePipMenuController.mPipMenuView.hideMenu$1();
            }
            pipTaskOrganizer.setPipVisibility(false);
        }
    }

    @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
    public final void onPipTransitionCanceled(int i) {
        onPipTransitionFinishedOrCanceled(i);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
    public final void onPipTransitionFinished(int i) {
        onPipTransitionFinishedOrCanceled(i);
    }

    public final void onPipTransitionFinishedOrCanceled(int i) {
        InteractionJankMonitor.getInstance().end(35);
        ((HandlerExecutor) this.mMainExecutor).executeDelayed(this.mEnableTouchCallback, 200L);
        PipTouchHandler pipTouchHandler = this.mTouchHandler;
        pipTouchHandler.mMotionHelper.synchronizePinnedStackBounds();
        pipTouchHandler.updateMovementBounds();
        if (i == 2) {
            pipTouchHandler.mPipResizeGestureHandler.setUserResizeBounds(pipTouchHandler.mPipBoundsState.getBounds());
        }
    }

    @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
    public final void onPipTransitionStarted(int i, Rect rect) {
        String str;
        InteractionJankMonitor.Configuration.Builder withSurface = InteractionJankMonitor.Configuration.Builder.withSurface(35, this.mContext, this.mPipTaskOrganizer.mLeash, this.mHandler);
        switch (i) {
            case 2:
                str = "TRANSITION_TO_PIP";
                break;
            case 3:
                str = "TRANSITION_LEAVE_PIP";
                break;
            case 4:
                str = "TRANSITION_LEAVE_PIP_TO_SPLIT_SCREEN";
                break;
            case 5:
                str = "TRANSITION_REMOVE_STACK";
                break;
            case 6:
                str = "TRANSITION_SNAP_AFTER_RESIZE";
                break;
            case 7:
                str = "TRANSITION_USER_RESIZE";
                break;
            case 8:
                str = "TRANSITION_EXPAND_OR_UNEXPAND";
                break;
            default:
                str = "TRANSITION_LEAVE_UNKNOWN";
                break;
        }
        InteractionJankMonitor.getInstance().begin(withSurface.setTag(str).setTimeout(2000L));
        if (PipAnimationController.isOutPipDirection(i)) {
            PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
            float snapFraction = pipBoundsAlgorithm.mSnapAlgorithm.getSnapFraction(0, rect, pipBoundsAlgorithm.getMovementBounds(rect, true));
            PipBoundsState pipBoundsState = this.mPipBoundsState;
            pipBoundsState.mPipReentryState = new PipBoundsState.PipReentryState(pipBoundsState.mBoundsScale, snapFraction);
        }
        ((HandlerExecutor) this.mMainExecutor).removeCallbacks(this.mEnableTouchCallback);
        PipTouchState pipTouchState = this.mTouchHandler.mTouchState;
        pipTouchState.mAllowTouches = false;
        if (pipTouchState.mIsUserInteracting) {
            pipTouchState.reset();
        }
        PipAnimationListener pipAnimationListener = this.mPinnedStackAnimationRecentsCallback;
        if (pipAnimationListener != null) {
            pipAnimationListener.onPipAnimationStarted();
            if (i == 3) {
                this.mPinnedStackAnimationRecentsCallback.onExpandPip();
            }
        }
    }

    @Override // com.android.wm.shell.sysui.ConfigurationChangeListener
    public final void onThemeChanged() {
        this.mTouchHandler.mPipDismissTargetHandler.init();
        Context context = this.mContext;
        onDisplayChanged(new DisplayLayout(context, context.getDisplay()), false);
    }

    @Override // com.android.wm.shell.sysui.UserChangeListener
    public final void onUserChanged$1(int i) {
        PipMediaController pipMediaController = this.mMediaController;
        MediaSessionManager mediaSessionManager = pipMediaController.mMediaSessionManager;
        Intrinsics.checkNotNull(mediaSessionManager);
        PipMediaController$mSessionsChangedListener$1 pipMediaController$mSessionsChangedListener$1 = pipMediaController.mSessionsChangedListener;
        mediaSessionManager.removeOnActiveSessionsChangedListener(pipMediaController$mSessionsChangedListener$1);
        pipMediaController.mMediaSessionManager.addOnActiveSessionsChangedListener(null, UserHandle.CURRENT, pipMediaController.mHandlerExecutor, pipMediaController$mSessionsChangedListener$1);
    }

    public final void setLauncherKeepClearAreaHeight$1(int i, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2945141395292145768L, 7, Boolean.valueOf(z), Long.valueOf(i));
        }
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (z) {
            int i2 = pipBoundsState.mPipDisplayLayoutState.getDisplayBounds().bottom - i;
            PipDisplayLayoutState pipDisplayLayoutState = pipBoundsState.mPipDisplayLayoutState;
            pipBoundsState.setNamedUnrestrictedKeepClearArea(0, new Rect(0, i2, pipDisplayLayoutState.getDisplayBounds().right, pipDisplayLayoutState.getDisplayBounds().bottom));
            updatePipPositionForKeepClearAreas();
            return;
        }
        pipBoundsState.setNamedUnrestrictedKeepClearArea(0, null);
        HandlerExecutor handlerExecutor = (HandlerExecutor) this.mMainExecutor;
        PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda3 = this.mMovePipInResponseToKeepClearAreasChangeCallback;
        handlerExecutor.removeCallbacks(pipController$$ExternalSyntheticLambda3);
        handlerExecutor.executeDelayed(pipController$$ExternalSyntheticLambda3, PIP_KEEP_CLEAR_AREAS_DELAY);
    }

    public void setPinnedStackAnimationListener(PipAnimationListener pipAnimationListener) {
        this.mPinnedStackAnimationRecentsCallback = pipAnimationListener;
        if (pipAnimationListener != null) {
            pipAnimationListener.onPipResourceDimensionsChanged(this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_corner_radius), this.mContext.getResources().getDimensionPixelSize(R.dimen.pip_shadow_radius));
        }
    }

    public final void updateMovementBounds(Rect rect, boolean z, boolean z2, boolean z3, WindowContainerTransaction windowContainerTransaction) {
        int i;
        Rect rect2 = new Rect(rect);
        int i2 = this.mPipDisplayLayoutState.getDisplayLayout().mRotation;
        Rect rect3 = this.mTmpInsetBounds;
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        pipBoundsAlgorithm.getInsetBounds(rect3);
        int i3 = 0;
        Rect transformBoundsToAspectRatioIfValid = pipBoundsAlgorithm.transformBoundsToAspectRatioIfValid(pipBoundsAlgorithm.getDefaultBounds(-1.0f, null), pipBoundsAlgorithm.mPipBoundsState.mAspectRatio, false, false);
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        pipBoundsState.mNormalBounds.set(transformBoundsToAspectRatioIfValid);
        if (rect2.isEmpty()) {
            rect2.set(pipBoundsAlgorithm.getDefaultBounds(-1.0f, null));
        }
        PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
        boolean z4 = pipTaskOrganizer.mWaitForFixedRotation;
        PipTransitionState pipTransitionState = pipTaskOrganizer.mPipTransitionState;
        boolean z5 = z4 && pipTransitionState.mState != 4;
        boolean z6 = pipTransitionState.mInSwipePipToHomeTransition;
        if ((!z6 && !z5) || !z) {
            PipAnimationController.PipTransitionAnimator pipTransitionAnimator = pipTaskOrganizer.mPipAnimationController.mCurrentAnimator;
            PipBoundsState pipBoundsState2 = pipTaskOrganizer.mPipBoundsState;
            if (pipTransitionAnimator != null && pipTransitionAnimator.isRunning() && pipTransitionAnimator.getTransitionDirection() == 2) {
                Rect rect4 = pipTransitionAnimator.mDestinationBounds;
                rect2.set(rect4);
                if (z2 || z3 || !pipBoundsState2.mPipDisplayLayoutState.getDisplayBounds().contains(rect4)) {
                    Rect entryDestinationBounds = pipTaskOrganizer.mPipBoundsAlgorithm.getEntryDestinationBounds();
                    if (!entryDestinationBounds.equals(rect4)) {
                        pipTaskOrganizer.updateAnimatorBounds(entryDestinationBounds);
                        rect2.set(entryDestinationBounds);
                    }
                }
            } else {
                boolean z7 = PipTransitionState.isInPip(pipTransitionState.mState) && z;
                if (z7 && Transitions.ENABLE_SHELL_TRANSITIONS) {
                    pipBoundsState2.setBounds(rect2);
                } else if (z7 && pipTaskOrganizer.mWaitForFixedRotation && pipTaskOrganizer.mHasFadeOut) {
                    pipBoundsState2.setBounds(rect2);
                } else if (z7) {
                    pipBoundsState2.setBounds(rect2);
                    if (pipTransitionAnimator != null) {
                        i = pipTransitionAnimator.getTransitionDirection();
                        pipTransitionAnimator.removeAllUpdateListeners();
                        pipTransitionAnimator.removeAllListeners();
                        pipTransitionAnimator.cancel();
                        pipTaskOrganizer.mPipTransitionController.sendOnPipTransitionCancelled(i);
                        pipTaskOrganizer.sendOnPipTransitionFinished(i);
                    } else {
                        i = 0;
                    }
                    pipTaskOrganizer.prepareFinishResizeTransaction(rect2, i, pipTaskOrganizer.createFinishResizeSurfaceTransaction(rect2), windowContainerTransaction);
                } else if (pipTransitionAnimator == null || !pipTransitionAnimator.isRunning()) {
                    if (!pipBoundsState2.getBounds().isEmpty()) {
                        rect2.set(pipBoundsState2.getBounds());
                    }
                } else if (!pipTransitionAnimator.mDestinationBounds.isEmpty()) {
                    rect2.set(pipTransitionAnimator.mDestinationBounds);
                }
            }
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1955999652116970868L, 124, "PipTaskOrganizer", Boolean.valueOf(z6), Boolean.valueOf(z4), Long.valueOf(pipTransitionState.mState));
        }
        pipTaskOrganizer.finishResizeForMenu$1(rect2);
        Rect rect5 = this.mTmpInsetBounds;
        Rect rect6 = pipBoundsState.mNormalBounds;
        PipTouchHandler pipTouchHandler = this.mTouchHandler;
        if (pipTouchHandler.mPipResizeGestureHandler.mUserResizeBounds.isEmpty()) {
            pipTouchHandler.mPipResizeGestureHandler.setUserResizeBounds(rect6);
        }
        int i4 = pipTouchHandler.mIsImeShowing ? pipTouchHandler.mImeHeight : 0;
        if (pipTouchHandler.mDisplayRotation != i2) {
            pipTouchHandler.mTouchState.reset();
        }
        Rect rect7 = new Rect();
        pipTouchHandler.mPipBoundsAlgorithm.getClass();
        PipBoundsAlgorithm.getMovementBounds(rect6, rect5, rect7, i4);
        PipBoundsState pipBoundsState3 = pipTouchHandler.mPipBoundsState;
        if (pipBoundsState3.mMovementBounds.isEmpty()) {
            PipBoundsAlgorithm.getMovementBounds(rect2, rect5, pipBoundsState3.mMovementBounds, 0);
        }
        float width = rect6.width() / rect6.height();
        Size defaultSize = pipTouchHandler.mSizeSpecSource.getDefaultSize(width);
        pipBoundsState3.mExpandedBounds.set(new Rect(0, 0, defaultSize.getWidth(), defaultSize.getHeight()));
        Rect rect8 = new Rect();
        PipBoundsAlgorithm.getMovementBounds(pipBoundsState3.mExpandedBounds, rect5, rect8, i4);
        pipTouchHandler.updatePipSizeConstraints(rect6, width);
        boolean z8 = pipTouchHandler.mIsImeShowing;
        int i5 = z8 ? pipTouchHandler.mImeOffset : 0;
        if (!z8 && pipTouchHandler.mIsShelfShowing) {
            i3 = pipTouchHandler.mShelfHeight;
        }
        int max = Math.max(i5, i3);
        pipBoundsState3.mNormalMovementBounds.set(rect7);
        pipBoundsState3.mExpandedMovementBounds.set(rect8);
        pipTouchHandler.mDisplayRotation = i2;
        pipTouchHandler.mInsetBounds.set(rect5);
        pipTouchHandler.updateMovementBounds();
        pipTouchHandler.mMovementBoundsExtraOffsets = max;
        Rect rect9 = pipBoundsState3.mExpandedBounds;
        Rect rect10 = pipBoundsState3.mNormalMovementBounds;
        Rect rect11 = pipBoundsState3.mExpandedMovementBounds;
        PipAccessibilityInteractionConnection pipAccessibilityInteractionConnection = pipTouchHandler.mConnection;
        pipAccessibilityInteractionConnection.mNormalBounds.set(rect6);
        pipAccessibilityInteractionConnection.mExpandedBounds.set(rect9);
        pipAccessibilityInteractionConnection.mNormalMovementBounds.set(rect10);
        pipAccessibilityInteractionConnection.mExpandedMovementBounds.set(rect11);
        if (pipTouchHandler.mDeferResizeToNormalBoundsUntilRotation == i2) {
            pipTouchHandler.mMotionHelper.animateToUnexpandedState(rect6, pipTouchHandler.mSavedSnapFraction, pipBoundsState3.mNormalMovementBounds, pipBoundsState3.mMovementBounds, true);
            pipTouchHandler.mSavedSnapFraction = -1.0f;
            pipTouchHandler.mDeferResizeToNormalBoundsUntilRotation = -1;
        }
    }

    public final void updatePipPositionForKeepClearAreas() {
        PipTransitionState pipTransitionState;
        int i;
        if (this.mIsKeyguardShowingOrAnimating || (i = (pipTransitionState = this.mPipTransitionState).mState) < 3 || i == 5) {
            return;
        }
        PhonePipKeepClearAlgorithm phonePipKeepClearAlgorithm = this.mPipKeepClearAlgorithm;
        phonePipKeepClearAlgorithm.getClass();
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        boolean isEmpty = pipBoundsState.getBounds().isEmpty();
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        Rect entryDestinationBoundsIgnoringKeepClearAreas = isEmpty ? pipBoundsAlgorithm.getEntryDestinationBoundsIgnoringKeepClearAreas() : pipBoundsState.getBounds();
        if (!pipBoundsState.mIsImeShowing && !pipBoundsState.mRestoreBounds.isEmpty()) {
            entryDestinationBoundsIgnoringKeepClearAreas.set(pipBoundsState.mRestoreBounds);
            pipBoundsState.mRestoreBounds.setEmpty();
        }
        Rect rect = new Rect();
        pipBoundsAlgorithm.getInsetBounds(rect);
        if (pipBoundsState.mIsImeShowing) {
            rect.bottom -= pipBoundsState.mImeHeight + phonePipKeepClearAlgorithm.mImeOffset;
        }
        if (pipBoundsState.isStashed()) {
            int i2 = entryDestinationBoundsIgnoringKeepClearAreas.bottom;
            int i3 = rect.bottom;
            if (i2 > i3 || entryDestinationBoundsIgnoringKeepClearAreas.top < rect.top) {
                entryDestinationBoundsIgnoringKeepClearAreas.offset(0, i3 - i2);
            }
        } else {
            Rect rect2 = new Rect(entryDestinationBoundsIgnoringKeepClearAreas);
            boolean z = !rect.contains(rect2);
            if (!pipBoundsState.mHasUserMovedPip && !pipBoundsState.mHasUserResizedPip) {
                z = true;
            }
            if (phonePipKeepClearAlgorithm.mKeepClearAreaGravityEnabled || z) {
                float snapFraction = pipBoundsAlgorithm.mSnapAlgorithm.getSnapFraction(0, entryDestinationBoundsIgnoringKeepClearAreas, pipBoundsAlgorithm.getMovementBounds(entryDestinationBoundsIgnoringKeepClearAreas, true));
                char c = (snapFraction < 0.5f || snapFraction >= 2.5f) ? (char) 3 : (char) 5;
                rect2.offsetTo(rect2.left, rect.bottom - rect2.height());
                if (c == 5) {
                    rect2.offsetTo(rect.right - rect2.width(), rect2.top);
                } else {
                    rect2.offsetTo(rect.left, rect2.top);
                }
            }
            entryDestinationBoundsIgnoringKeepClearAreas = phonePipKeepClearAlgorithm.findUnoccludedPosition(rect2, pipBoundsState.mRestrictedKeepClearAreas, pipBoundsState.getUnrestrictedKeepClearAreas(), rect);
        }
        if (entryDestinationBoundsIgnoringKeepClearAreas.equals(pipBoundsState.getBounds())) {
            return;
        }
        boolean hasEnteredPip = pipTransitionState.hasEnteredPip();
        PipTaskOrganizer pipTaskOrganizer = this.mPipTaskOrganizer;
        if (hasEnteredPip) {
            pipTaskOrganizer.scheduleAnimateResizePip(entryDestinationBoundsIgnoringKeepClearAreas, this.mEnterAnimationDuration, 0);
        } else if (pipTransitionState.mState == 3) {
            pipTaskOrganizer.updateAnimatorBounds(entryDestinationBoundsIgnoringKeepClearAreas);
        }
    }
}
