package com.android.systemui.animation;

import android.app.Dialog;
import android.graphics.PorterDuffXfermode;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.Window;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.dagger.CentralSurfacesDependenciesModule$1;
import com.android.systemui.statusbar.dagger.CentralSurfacesDependenciesModule$2;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimatedDialog {
    public final AnimatedDialog$start$2 backgroundLayoutListener;
    public final CentralSurfacesDependenciesModule$1 callback;
    public final DialogTransitionAnimator.Controller controller;
    public View.OnLayoutChangeListener decorViewLayoutListener;
    public final Dialog dialog;
    public ViewGroup dialogContentWithBackground;
    public boolean dismissRequested;
    public boolean exitAnimationDisabled;
    public final CentralSurfacesDependenciesModule$2 featureFlags;
    public boolean hasInstrumentedJank;
    public final InteractionJankMonitor interactionJankMonitor;
    public boolean isDismissing;
    public boolean isOriginalDialogViewLaidOut;
    public boolean isSourceDrawnInDialog;
    public final Function1 onDialogDismissed;
    public final AnimatedDialog parentAnimatedDialog;
    public final TransitionAnimator transitionAnimator;
    public final Lazy decorView$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.animation.AnimatedDialog$decorView$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Window window = AnimatedDialog.this.dialog.getWindow();
            Intrinsics.checkNotNull(window);
            return (ViewGroup) window.getDecorView();
        }
    });
    public int originalDialogBackgroundColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
    public boolean isLaunching = true;

    public AnimatedDialog(TransitionAnimator transitionAnimator, CentralSurfacesDependenciesModule$1 centralSurfacesDependenciesModule$1, InteractionJankMonitor interactionJankMonitor, DialogTransitionAnimator.Controller controller, Function1 function1, Dialog dialog, boolean z, AnimatedDialog animatedDialog, CentralSurfacesDependenciesModule$2 centralSurfacesDependenciesModule$2) {
        this.transitionAnimator = transitionAnimator;
        this.callback = centralSurfacesDependenciesModule$1;
        this.interactionJankMonitor = interactionJankMonitor;
        this.controller = controller;
        this.onDialogDismissed = function1;
        this.dialog = dialog;
        this.parentAnimatedDialog = animatedDialog;
        this.featureFlags = centralSurfacesDependenciesModule$2;
        this.backgroundLayoutListener = z ? new AnimatedDialog$start$2() : null;
    }

    public static final void access$maybeStartLaunchAnimation(final AnimatedDialog animatedDialog) {
        if (animatedDialog.isSourceDrawnInDialog && animatedDialog.isOriginalDialogViewLaidOut) {
            Window window = animatedDialog.dialog.getWindow();
            if (window != null) {
                window.addFlags(2);
            }
            animatedDialog.startAnimation(new Function0() { // from class: com.android.systemui.animation.AnimatedDialog$startAnimation$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function0() { // from class: com.android.systemui.animation.AnimatedDialog$maybeStartLaunchAnimation$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AnimatedDialog animatedDialog2 = AnimatedDialog.this;
                    animatedDialog2.isLaunching = false;
                    if (animatedDialog2.dismissRequested) {
                        animatedDialog2.dialog.dismiss();
                    }
                    AnimatedDialog animatedDialog3 = AnimatedDialog.this;
                    if (animatedDialog3.backgroundLayoutListener != null) {
                        ViewGroup viewGroup = animatedDialog3.dialogContentWithBackground;
                        Intrinsics.checkNotNull(viewGroup);
                        viewGroup.addOnLayoutChangeListener(AnimatedDialog.this.backgroundLayoutListener);
                    }
                    AnimatedDialog animatedDialog4 = AnimatedDialog.this;
                    if (animatedDialog4.hasInstrumentedJank) {
                        InteractionJankMonitor interactionJankMonitor = animatedDialog4.interactionJankMonitor;
                        DialogCuj cuj = animatedDialog4.controller.getCuj();
                        Intrinsics.checkNotNull(cuj);
                        interactionJankMonitor.end(cuj.cujType);
                    }
                    return Unit.INSTANCE;
                }
            }, true);
        }
    }

    public static ViewGroup findFirstViewGroupWithBackground(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getBackground() != null) {
            return viewGroup;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup findFirstViewGroupWithBackground = findFirstViewGroupWithBackground(viewGroup.getChildAt(i));
            if (findFirstViewGroupWithBackground != null) {
                return findFirstViewGroupWithBackground;
            }
        }
        return null;
    }

    public final ViewGroup getDecorView() {
        return (ViewGroup) this.decorView$delegate.getValue();
    }

    public final void moveSourceDrawingToDialog() {
        if (getDecorView().getViewRootImpl() == null) {
            getDecorView().post(new AnimatedDialog$start$3(this, 1));
        } else {
            this.controller.startDrawingInOverlayOf(getDecorView());
            synchronizeNextDraw(new Function0() { // from class: com.android.systemui.animation.AnimatedDialog$moveSourceDrawingToDialog$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AnimatedDialog animatedDialog = AnimatedDialog.this;
                    animatedDialog.isSourceDrawnInDialog = true;
                    AnimatedDialog.access$maybeStartLaunchAnimation(animatedDialog);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDialogDismissed() {
        /*
            r6 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L22
            android.app.Dialog r0 = r6.dialog
            android.content.Context r0 = r0.getContext()
            java.util.concurrent.Executor r0 = r0.getMainExecutor()
            com.android.systemui.animation.AnimatedDialog$start$3 r1 = new com.android.systemui.animation.AnimatedDialog$start$3
            r2 = 2
            r1.<init>(r6, r2)
            r0.execute(r1)
            return
        L22:
            boolean r0 = r6.isLaunching
            r1 = 1
            if (r0 == 0) goto L2a
            r6.dismissRequested = r1
            return
        L2a:
            boolean r0 = r6.isDismissing
            if (r0 == 0) goto L2f
            return
        L2f:
            r6.isDismissing = r1
            com.android.systemui.animation.AnimatedDialog$onDialogDismissed$2 r0 = new com.android.systemui.animation.AnimatedDialog$onDialogDismissed$2
            r0.<init>()
            android.view.View$OnLayoutChangeListener r1 = r6.decorViewLayoutListener
            if (r1 == 0) goto L43
            android.view.ViewGroup r1 = r6.getDecorView()
            android.view.View$OnLayoutChangeListener r2 = r6.decorViewLayoutListener
            r1.removeOnLayoutChangeListener(r2)
        L43:
            boolean r1 = r6.exitAnimationDisabled
            com.android.systemui.animation.DialogTransitionAnimator$Controller r2 = r6.controller
            r3 = 0
            if (r1 != 0) goto L6a
            android.app.Dialog r1 = r6.dialog
            boolean r1 = r1.isShowing()
            if (r1 != 0) goto L53
            goto L6a
        L53:
            com.android.systemui.statusbar.dagger.CentralSurfacesDependenciesModule$1 r1 = r6.callback
            r1.getClass()
            android.service.dreams.IDreamManager r1 = r1.val$dreamManager     // Catch: android.os.RemoteException -> L5f
            boolean r1 = r1.isDreaming()     // Catch: android.os.RemoteException -> L5f
            goto L68
        L5f:
            r1 = move-exception
            java.lang.String r4 = "DialogTransitionAnimator.Callback"
            java.lang.String r5 = "dreamManager.isDreaming failed"
            android.util.Log.e(r4, r5, r1)
            r1 = r3
        L68:
            if (r1 == 0) goto L6c
        L6a:
            r1 = r3
            goto L70
        L6c:
            boolean r1 = r2.shouldAnimateExit()
        L70:
            if (r1 != 0) goto L89
            java.lang.String r1 = "DialogTransitionAnimator"
            java.lang.String r3 = "Skipping animation of dialog into the source"
            android.util.Log.i(r1, r3)
            r2.onExitAnimationCancelled()
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            r0.invoke(r1)
            kotlin.jvm.functions.Function1 r0 = r6.onDialogDismissed
            com.android.systemui.animation.DialogTransitionAnimator$show$animatedDialog$1 r0 = (com.android.systemui.animation.DialogTransitionAnimator$show$animatedDialog$1) r0
            r0.invoke(r6)
            goto L96
        L89:
            com.android.systemui.animation.AnimatedDialog$hideDialogIntoView$1 r1 = new com.android.systemui.animation.AnimatedDialog$hideDialogIntoView$1
            r1.<init>()
            com.android.systemui.animation.AnimatedDialog$hideDialogIntoView$2 r2 = new com.android.systemui.animation.AnimatedDialog$hideDialogIntoView$2
            r2.<init>()
            r6.startAnimation(r1, r2, r3)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.AnimatedDialog.onDialogDismissed():void");
    }

    public final void prepareForStackDismiss() {
        AnimatedDialog animatedDialog = this.parentAnimatedDialog;
        if (animatedDialog == null) {
            return;
        }
        animatedDialog.exitAnimationDisabled = true;
        animatedDialog.dialog.hide();
        animatedDialog.prepareForStackDismiss();
        animatedDialog.dialog.dismiss();
    }

    public final void startAnimation(final Function0 function0, final Function0 function02, boolean z) {
        TransitionAnimator.Controller ghostedViewTransitionAnimatorController;
        TransitionAnimator.Controller createExitController;
        DialogTransitionAnimator.Controller controller = this.controller;
        if (z) {
            ghostedViewTransitionAnimatorController = controller.createTransitionController();
        } else {
            ViewGroup viewGroup = this.dialogContentWithBackground;
            Intrinsics.checkNotNull(viewGroup);
            ghostedViewTransitionAnimatorController = new GhostedViewTransitionAnimatorController(viewGroup, null, 62);
        }
        final TransitionAnimator.Controller controller2 = ghostedViewTransitionAnimatorController;
        if (z) {
            ViewGroup viewGroup2 = this.dialogContentWithBackground;
            Intrinsics.checkNotNull(viewGroup2);
            createExitController = new GhostedViewTransitionAnimatorController(viewGroup2, null, 62);
        } else {
            createExitController = controller.createExitController();
        }
        final TransitionAnimator.Controller controller3 = createExitController;
        controller2.setTransitionContainer(getDecorView());
        controller3.setTransitionContainer(getDecorView());
        final TransitionAnimator.State createAnimatorState = controller3.createAnimatorState();
        TransitionAnimator.Controller controller4 = new TransitionAnimator.Controller() { // from class: com.android.systemui.animation.AnimatedDialog$startAnimation$controller$1
            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final TransitionAnimator.State createAnimatorState() {
                return TransitionAnimator.Controller.this.createAnimatorState();
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final ViewGroup getTransitionContainer() {
                return TransitionAnimator.Controller.this.getTransitionContainer();
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final boolean isLaunching() {
                return true;
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void onTransitionAnimationEnd(boolean z2) {
                TransitionAnimator.Controller.this.onTransitionAnimationEnd(z2);
                controller3.onTransitionAnimationEnd(z2);
                function02.invoke();
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
                TransitionAnimator.Controller.this.onTransitionAnimationProgress(state, f, f2);
                state.visible = !state.visible;
                TransitionAnimator.Controller controller5 = controller3;
                controller5.onTransitionAnimationProgress(state, f, f2);
                if (controller5 instanceof GhostedViewTransitionAnimatorController) {
                    ((GhostedViewTransitionAnimatorController) controller5).fillGhostedViewState(createAnimatorState);
                }
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void onTransitionAnimationStart(boolean z2) {
                function0.invoke();
                TransitionAnimator.Controller.this.onTransitionAnimationStart(z2);
                controller3.onTransitionAnimationStart(z2);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void setTransitionContainer(ViewGroup viewGroup3) {
                TransitionAnimator.Controller.this.setTransitionContainer(viewGroup3);
                controller3.setTransitionContainer(viewGroup3);
            }
        };
        int i = this.originalDialogBackgroundColor;
        PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
        this.transitionAnimator.startAnimation(controller4, createAnimatorState, i, true, false);
    }

    public final void synchronizeNextDraw(Function0 function0) {
        ViewRootImpl viewRoot = this.controller.getViewRoot();
        View view = viewRoot != null ? viewRoot.getView() : null;
        if (view == null) {
            function0.invoke();
            return;
        }
        ViewRootSync.synchronizeNextDraw(view, getDecorView(), function0);
        getDecorView().invalidate();
        view.invalidate();
    }
}
