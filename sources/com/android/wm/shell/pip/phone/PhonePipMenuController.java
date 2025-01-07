package com.android.wm.shell.pip.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Property;
import android.util.Size;
import android.util.Slog;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SystemWindows;
import com.android.wm.shell.common.SystemWindows.PerDisplay;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipMediaController;
import com.android.wm.shell.common.pip.PipMenuController;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhonePipMenuController implements PipMenuController {
    public List mAppActions;
    public RemoteAction mCloseAction;
    public final Context mContext;
    public SurfaceControl mLeash;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public List mMediaActions;
    public final PipMediaController mMediaController;
    public int mMenuState;
    public final PipBoundsState mPipBoundsState;
    public PipMenuView mPipMenuView;
    public final PipUiEventLogger mPipUiEventLogger;
    public final SystemWindows mSystemWindows;
    public final ArrayList mListeners = new ArrayList();
    public final AnonymousClass1 mMediaActionListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip.phone.PhonePipMenuController$1, reason: invalid class name */
    public final class AnonymousClass1 implements PipMediaController.ActionListener {
        public AnonymousClass1() {
        }

        @Override // com.android.wm.shell.common.pip.PipMediaController.ActionListener
        public final void onMediaActionsChanged(List list) {
            ArrayList arrayList = new ArrayList(list);
            PhonePipMenuController phonePipMenuController = PhonePipMenuController.this;
            phonePipMenuController.mMediaActions = arrayList;
            phonePipMenuController.updateMenuActions();
        }
    }

    public PhonePipMenuController(Context context, PipBoundsState pipBoundsState, PipMediaController pipMediaController, SystemWindows systemWindows, PipUiEventLogger pipUiEventLogger, ShellExecutor shellExecutor, Handler handler) {
        this.mContext = context;
        this.mPipBoundsState = pipBoundsState;
        this.mMediaController = pipMediaController;
        this.mSystemWindows = systemWindows;
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mPipUiEventLogger = pipUiEventLogger;
    }

    public final void attach(SurfaceControl surfaceControl) {
        this.mLeash = surfaceControl;
        attachPipMenuView();
    }

    public final void attachPipMenuView() {
        SurfaceControl surfaceControl;
        PipMenuView pipMenuView = this.mPipMenuView;
        SystemWindows systemWindows = this.mSystemWindows;
        if (pipMenuView != null && pipMenuView != null) {
            ((SurfaceControlViewHost) systemWindows.mViewRoots.remove(pipMenuView)).release();
            this.mPipMenuView = null;
        }
        PipMenuView pipMenuView2 = new PipMenuView(this.mContext, this, this.mMainExecutor, this.mMainHandler, this.mPipUiEventLogger);
        this.mPipMenuView = pipMenuView2;
        pipMenuView2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.wm.shell.pip.phone.PhonePipMenuController.2
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                view.getViewRootImpl().addSurfaceChangedCallback(new ViewRootImpl.SurfaceChangedCallback() { // from class: com.android.wm.shell.pip.phone.PhonePipMenuController.2.1
                    public final void surfaceCreated(SurfaceControl.Transaction transaction) {
                        SurfaceControl surfaceControl2;
                        PhonePipMenuController phonePipMenuController = PhonePipMenuController.this;
                        SystemWindows systemWindows2 = phonePipMenuController.mSystemWindows;
                        PipMenuView pipMenuView3 = phonePipMenuController.mPipMenuView;
                        int i = 0;
                        loop0: while (true) {
                            if (i >= systemWindows2.mPerDisplay.size()) {
                                surfaceControl2 = null;
                                break;
                            }
                            for (int i2 = 0; i2 < ((SystemWindows.PerDisplay) systemWindows2.mPerDisplay.valueAt(i)).mWwms.size(); i2++) {
                                surfaceControl2 = ((SystemWindows.SysUiWindowManager) ((SystemWindows.PerDisplay) systemWindows2.mPerDisplay.valueAt(i)).mWwms.valueAt(i2)).getSurfaceControlForWindow(pipMenuView3);
                                if (surfaceControl2 != null) {
                                    break loop0;
                                }
                            }
                            i++;
                        }
                        if (surfaceControl2 != null) {
                            transaction.reparent(surfaceControl2, PhonePipMenuController.this.mLeash);
                            transaction.setLayer(surfaceControl2, Integer.MAX_VALUE);
                        }
                    }

                    public final void surfaceDestroyed() {
                    }

                    public final void surfaceReplaced(SurfaceControl.Transaction transaction) {
                    }
                });
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
            }
        });
        PipMenuView pipMenuView3 = this.mPipMenuView;
        WindowManager.LayoutParams pipMenuLayoutParams = PipMenuController.getPipMenuLayoutParams(0, 0, this.mContext);
        SystemWindows.PerDisplay perDisplay = (SystemWindows.PerDisplay) systemWindows.mPerDisplay.get(0);
        if (perDisplay == null) {
            perDisplay = systemWindows.new PerDisplay();
            systemWindows.mPerDisplay.put(0, perDisplay);
        }
        SystemWindows.SysUiWindowManager sysUiWindowManager = (SystemWindows.SysUiWindowManager) perDisplay.mWwms.get(1);
        SystemWindows systemWindows2 = SystemWindows.this;
        if (sysUiWindowManager == null) {
            try {
                surfaceControl = systemWindows2.mWmService.addShellRoot(0, new SystemWindows.ContainerWindow(), 1);
            } catch (RemoteException unused) {
                surfaceControl = null;
            }
            if (surfaceControl == null) {
                Slog.e("SystemWindows", "Unable to get root surfacecontrol for systemui");
                sysUiWindowManager = null;
            } else {
                SystemWindows.SysUiWindowManager sysUiWindowManager2 = new SystemWindows.SysUiWindowManager(systemWindows2.mDisplayController.getDisplayContext(0), surfaceControl);
                perDisplay.mWwms.put(1, sysUiWindowManager2);
                sysUiWindowManager = sysUiWindowManager2;
            }
        }
        if (sysUiWindowManager == null) {
            Slog.e("SystemWindows", "Unable to create systemui root");
        } else {
            SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(pipMenuView3.getContext(), systemWindows2.mDisplayController.getDisplay(0), sysUiWindowManager, "SystemWindows");
            pipMenuLayoutParams.flags |= 16777216;
            surfaceControlViewHost.setView(pipMenuView3, pipMenuLayoutParams);
            systemWindows2.mViewRoots.put(pipMenuView3, surfaceControlViewHost);
            perDisplay.setShellRootAccessibilityWindow(pipMenuView3);
        }
        if (this.mMenuState != 0) {
            systemWindows.setShellRootAccessibilityWindow(this.mPipMenuView);
        } else {
            systemWindows.setShellRootAccessibilityWindow(null);
        }
        updateMenuActions();
    }

    public final boolean checkPipMenuState() {
        PipMenuView pipMenuView = this.mPipMenuView;
        if (pipMenuView != null && pipMenuView.getViewRootImpl() != null) {
            return true;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 1781819382020650963L, 0, "PhonePipMenuController");
        }
        return false;
    }

    public final Size getEstimatedMinMenuSize() {
        PipMenuView pipMenuView = this.mPipMenuView;
        if (pipMenuView == null) {
            return null;
        }
        return new Size(Math.max(2, ((ArrayList) pipMenuView.mActions).size()) * pipMenuView.getResources().getDimensionPixelSize(R.dimen.pip_action_size), pipMenuView.getResources().getDimensionPixelSize(R.dimen.pip_expand_container_edge_margin) + pipMenuView.getResources().getDimensionPixelSize(R.dimen.pip_action_padding) + pipMenuView.getResources().getDimensionPixelSize(R.dimen.pip_expand_action_size));
    }

    public final void hideMenu() {
        if (isMenuVisible()) {
            this.mPipMenuView.hideMenu$1();
        }
    }

    public final boolean isMenuVisible() {
        return (this.mPipMenuView == null || this.mMenuState == 0) ? false : true;
    }

    public final void movePipMenu(Rect rect, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        if (rect.isEmpty() || !checkPipMenuState() || surfaceControl == null || transaction == null) {
            return;
        }
        transaction.apply();
    }

    public final void showMenuInternal(final int i, Rect rect, final boolean z, boolean z2, boolean z3) {
        int i2 = 2;
        if (checkPipMenuState()) {
            movePipMenu(rect, null, null);
            updateMenuBounds(rect);
            final PipMenuView pipMenuView = this.mPipMenuView;
            pipMenuView.mAllowMenuTimeout = z;
            pipMenuView.mDidLastShowMenuResize = z2;
            int i3 = pipMenuView.mMenuState;
            if (i3 == i) {
                if (z) {
                    pipMenuView.repostDelayedHide(2000);
                    return;
                }
                return;
            }
            pipMenuView.mAllowTouches = !(z2 && (i3 == 1 || i == 1));
            ((HandlerExecutor) pipMenuView.mMainExecutor).removeCallbacks(pipMenuView.mHideMenuRunnable);
            AnimatorSet animatorSet = pipMenuView.mMenuContainerAnimator;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            pipMenuView.mMenuContainerAnimator = new AnimatorSet();
            View view = pipMenuView.mMenuContainer;
            Property property = View.ALPHA;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, view.getAlpha(), 1.0f);
            ofFloat.addUpdateListener(pipMenuView.mMenuBgUpdateListener);
            View view2 = pipMenuView.mSettingsButton;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) property, view2.getAlpha(), 1.0f);
            View view3 = pipMenuView.mDismissButton;
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view3, (Property<View, Float>) property, view3.getAlpha(), 1.0f);
            if (i == 1) {
                pipMenuView.mMenuContainerAnimator.playTogether(ofFloat, ofFloat2, ofFloat3);
            }
            pipMenuView.mMenuContainerAnimator.setInterpolator(Interpolators.ALPHA_IN);
            pipMenuView.mMenuContainerAnimator.setDuration(125L);
            pipMenuView.mMenuContainerAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.pip.phone.PipMenuView.3
                public final /* synthetic */ boolean val$allowMenuTimeout;
                public final /* synthetic */ int val$menuState;

                public AnonymousClass3(final int i4, final boolean z4) {
                    r2 = i4;
                    r3 = z4;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    PipMenuView.this.mAllowTouches = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    PipMenuView pipMenuView2 = PipMenuView.this;
                    pipMenuView2.mAllowTouches = true;
                    int i4 = r2;
                    pipMenuView2.mMenuState = i4;
                    PhonePipMenuController phonePipMenuController = pipMenuView2.mController;
                    if (i4 != phonePipMenuController.mMenuState) {
                        phonePipMenuController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda1(i4));
                    }
                    phonePipMenuController.mMenuState = i4;
                    SystemWindows systemWindows = phonePipMenuController.mSystemWindows;
                    if (i4 != 0) {
                        systemWindows.setShellRootAccessibilityWindow(phonePipMenuController.mPipMenuView);
                    } else {
                        systemWindows.setShellRootAccessibilityWindow(null);
                    }
                    if (r3) {
                        PipMenuView.this.repostDelayedHide(3500);
                    }
                }
            });
            if (z3) {
                pipMenuView.notifyMenuStateChangeStart(i4, z2, new PipMenuView$$ExternalSyntheticLambda0(i2, pipMenuView));
            } else {
                pipMenuView.notifyMenuStateChangeStart(i4, z2, null);
                pipMenuView.setVisibility(0);
                pipMenuView.mMenuContainerAnimator.start();
            }
            pipMenuView.updateActionViews(i4, rect);
        }
    }

    public final void updateMenuActions() {
        PipMenuView pipMenuView = this.mPipMenuView;
        if (pipMenuView != null) {
            Rect bounds = this.mPipBoundsState.getBounds();
            List list = this.mAppActions;
            List list2 = (list == null || list.size() <= 0) ? this.mMediaActions : this.mAppActions;
            RemoteAction remoteAction = this.mCloseAction;
            pipMenuView.mActions.clear();
            if (list2 != null && !list2.isEmpty()) {
                pipMenuView.mActions.addAll(list2);
            }
            pipMenuView.mCloseAction = remoteAction;
            int i = pipMenuView.mMenuState;
            if (i == 1) {
                pipMenuView.updateActionViews(i, bounds);
            }
        }
    }

    public final void updateMenuBounds(Rect rect) {
        PipMenuView pipMenuView = this.mPipMenuView;
        WindowManager.LayoutParams pipMenuLayoutParams = PipMenuController.getPipMenuLayoutParams(rect.width(), rect.height(), this.mContext);
        SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) this.mSystemWindows.mViewRoots.get(pipMenuView);
        if (surfaceControlViewHost != null) {
            pipMenuView.setLayoutParams(pipMenuLayoutParams);
            surfaceControlViewHost.relayout(pipMenuLayoutParams);
        }
        if (isMenuVisible()) {
            this.mPipMenuView.mPipMenuIconsAlgorithm.getClass();
        }
    }
}
