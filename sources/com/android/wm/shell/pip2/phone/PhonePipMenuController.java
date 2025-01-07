package com.android.wm.shell.pip2.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Property;
import android.util.Size;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SystemWindows;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipMediaController;
import com.android.wm.shell.common.pip.PipMenuController;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.pip2.phone.PipTouchHandler;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhonePipMenuController implements PipMenuController {
    public final Context mContext;
    public final ArrayList mListeners = new ArrayList();
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final AnonymousClass1 mMediaActionListener;
    public List mMediaActions;
    public final PipMediaController mMediaController;
    public int mMenuState;
    public final PipBoundsState mPipBoundsState;
    public final PipMenuView mPipMenuView;
    public final PipUiEventLogger mPipUiEventLogger;
    public final SystemWindows mSystemWindows;

    public PhonePipMenuController(Context context, PipBoundsState pipBoundsState, PipMediaController pipMediaController, SystemWindows systemWindows, PipUiEventLogger pipUiEventLogger, ShellExecutor shellExecutor, Handler handler) {
        new PipMediaController.ActionListener() { // from class: com.android.wm.shell.pip2.phone.PhonePipMenuController.1
            @Override // com.android.wm.shell.common.pip.PipMediaController.ActionListener
            public final void onMediaActionsChanged(List list) {
                ArrayList arrayList = new ArrayList(list);
                PhonePipMenuController phonePipMenuController = PhonePipMenuController.this;
                phonePipMenuController.mMediaActions = arrayList;
                phonePipMenuController.getClass();
            }
        };
        this.mContext = context;
        this.mPipBoundsState = pipBoundsState;
        this.mSystemWindows = systemWindows;
    }

    public final boolean checkPipMenuState$1() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 7716599426178299392L, 0, "PhonePipMenuController");
        }
        return false;
    }

    public final Size getEstimatedMinMenuSize() {
        return null;
    }

    public final void hideMenu() {
        if (isMenuVisible()) {
            PipMenuView pipMenuView = this.mPipMenuView;
            pipMenuView.hideMenu(null, true, pipMenuView.mDidLastShowMenuResize, 1);
        }
    }

    public final boolean isMenuVisible() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.wm.shell.pip2.phone.PipMenuView$$ExternalSyntheticLambda0] */
    public final void showMenuInternal$1(int i, Rect rect, boolean z, boolean z2, boolean z3) {
        View view = null;
        if (checkPipMenuState$1()) {
            if (!rect.isEmpty()) {
                checkPipMenuState$1();
            }
            WindowManager.LayoutParams pipMenuLayoutParams = PipMenuController.getPipMenuLayoutParams(rect.width(), rect.height(), this.mContext);
            SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) this.mSystemWindows.mViewRoots.get(null);
            if (surfaceControlViewHost != null) {
                view.setLayoutParams(pipMenuLayoutParams);
                surfaceControlViewHost.relayout(pipMenuLayoutParams);
            }
            if (isMenuVisible()) {
                view.getClass();
                view.getClass();
            }
            final PipMenuView pipMenuView = this.mPipMenuView;
            pipMenuView.getClass();
            pipMenuView.mDidLastShowMenuResize = z2;
            if (pipMenuView.mMenuState == i) {
                if (z) {
                    pipMenuView.repostDelayedHide(2000);
                    return;
                }
                return;
            }
            ((HandlerExecutor) pipMenuView.mMainExecutor).removeCallbacks(pipMenuView.mHideMenuRunnable);
            AnimatorSet animatorSet = pipMenuView.mMenuContainerAnimator;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            pipMenuView.mMenuContainerAnimator = new AnimatorSet();
            View view2 = pipMenuView.mMenuContainer;
            Property property = View.ALPHA;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) property, view2.getAlpha(), 1.0f);
            ofFloat.addUpdateListener(pipMenuView.mMenuBgUpdateListener);
            View view3 = pipMenuView.mSettingsButton;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view3, (Property<View, Float>) property, view3.getAlpha(), 1.0f);
            View view4 = pipMenuView.mDismissButton;
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view4, (Property<View, Float>) property, view4.getAlpha(), 1.0f);
            if (i == 1) {
                pipMenuView.mMenuContainerAnimator.playTogether(ofFloat, ofFloat2, ofFloat3);
            }
            pipMenuView.mMenuContainerAnimator.setInterpolator(Interpolators.ALPHA_IN);
            pipMenuView.mMenuContainerAnimator.setDuration(125L);
            pipMenuView.mMenuContainerAnimator.addListener(new AnimatorListenerAdapter(pipMenuView, i, z) { // from class: com.android.wm.shell.pip2.phone.PipMenuView.3
                public final /* synthetic */ PipMenuView this$0;
                public final /* synthetic */ boolean val$allowMenuTimeout;
                public final /* synthetic */ int val$menuState;

                {
                    this.val$menuState = i;
                    this.val$allowMenuTimeout = z;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    this.this$0.getClass();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    PipMenuView pipMenuView2 = this.this$0;
                    pipMenuView2.getClass();
                    final int i2 = this.val$menuState;
                    pipMenuView2.mMenuState = i2;
                    PhonePipMenuController phonePipMenuController = pipMenuView2.mController;
                    if (i2 != phonePipMenuController.mMenuState) {
                        phonePipMenuController.mListeners.forEach(new Consumer() { // from class: com.android.wm.shell.pip2.phone.PhonePipMenuController$$ExternalSyntheticLambda0
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                int i3 = i2;
                                PipTouchHandler pipTouchHandler = ((PipTouchHandler.PipMenuListener) obj).this$0;
                                pipTouchHandler.mMenuState = i3;
                                pipTouchHandler.updateMovementBounds();
                                boolean z4 = i3 == 0;
                                if (!z4) {
                                    pipTouchHandler.mAccessibilityManager.setPictureInPictureActionReplacingConnection(null);
                                }
                                if (!z4 && pipTouchHandler.mTouchState.mIsUserInteracting) {
                                    PipDismissTargetHandler pipDismissTargetHandler = pipTouchHandler.mPipDismissTargetHandler;
                                    if (pipDismissTargetHandler.mTargetViewContainer.getParent() != null) {
                                        pipDismissTargetHandler.mWindowManager.removeViewImmediate(pipDismissTargetHandler.mTargetViewContainer);
                                    }
                                }
                                PipUiEventLogger pipUiEventLogger = pipTouchHandler.mPipUiEventLogger;
                                if (i3 == 0) {
                                    pipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_HIDE_MENU);
                                } else if (i3 == 1) {
                                    pipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_SHOW_MENU);
                                }
                            }
                        });
                    }
                    phonePipMenuController.mMenuState = i2;
                    SystemWindows systemWindows = phonePipMenuController.mSystemWindows;
                    if (i2 != 0) {
                        systemWindows.setShellRootAccessibilityWindow(phonePipMenuController.mPipMenuView);
                    } else {
                        systemWindows.setShellRootAccessibilityWindow(null);
                    }
                    if (this.val$allowMenuTimeout) {
                        this.this$0.repostDelayedHide(3500);
                    }
                }
            });
            if (z3) {
                pipMenuView.notifyMenuStateChangeStart(i, z2, new Runnable(pipMenuView) { // from class: com.android.wm.shell.pip2.phone.PipMenuView$$ExternalSyntheticLambda0
                    public final /* synthetic */ PipMenuView f$0;

                    @Override // java.lang.Runnable
                    public final void run() {
                        PipMenuView pipMenuView2 = this.f$0;
                        AnimatorSet animatorSet2 = pipMenuView2.mMenuContainerAnimator;
                        if (animatorSet2 == null) {
                            return;
                        }
                        animatorSet2.setStartDelay(30L);
                        pipMenuView2.setVisibility(0);
                        pipMenuView2.mMenuContainerAnimator.start();
                    }
                });
            } else {
                pipMenuView.notifyMenuStateChangeStart(i, z2, null);
                pipMenuView.setVisibility(0);
                pipMenuView.mMenuContainerAnimator.start();
            }
            pipMenuView.updateActionViews(i, rect);
        }
    }
}
