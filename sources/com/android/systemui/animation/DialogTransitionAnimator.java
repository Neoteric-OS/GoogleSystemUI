package com.android.systemui.animation;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.animation.Interpolator;
import android.window.WindowAnimationState;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.statusbar.dagger.CentralSurfacesDependenciesModule$1;
import com.android.systemui.statusbar.dagger.CentralSurfacesDependenciesModule$2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DialogTransitionAnimator {
    public static final TransitionAnimator.Interpolators INTERPOLATORS;
    public static final TransitionAnimator.Timings TIMINGS = ActivityTransitionAnimator.TIMINGS;
    public final CentralSurfacesDependenciesModule$1 callback;
    public final CentralSurfacesDependenciesModule$2 featureFlags;
    public final InteractionJankMonitor interactionJankMonitor;
    public final HashSet openedDialogs;
    public final TransitionAnimator transitionAnimator;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Controller {
        TransitionAnimator.Controller createExitController();

        TransitionAnimator.Controller createTransitionController();

        DialogCuj getCuj();

        Object getSourceIdentity();

        ViewRootImpl getViewRoot();

        InteractionJankMonitor.Configuration.Builder jankConfigurationBuilder();

        void onExitAnimationCancelled();

        boolean shouldAnimateExit();

        void startDrawingInOverlayOf(ViewGroup viewGroup);

        void stopDrawingInOverlay();
    }

    static {
        TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
        TransitionAnimator.Interpolators interpolators = ActivityTransitionAnimator.INTERPOLATORS;
        Interpolator interpolator = interpolators.positionInterpolator;
        Interpolator interpolator2 = interpolators.contentBeforeFadeOutInterpolator;
        Interpolator interpolator3 = interpolators.contentAfterFadeInInterpolator;
        interpolators.getClass();
        INTERPOLATORS = new TransitionAnimator.Interpolators(interpolator, interpolator, interpolator2, interpolator3);
    }

    public DialogTransitionAnimator(Executor executor, CentralSurfacesDependenciesModule$1 centralSurfacesDependenciesModule$1, InteractionJankMonitor interactionJankMonitor, CentralSurfacesDependenciesModule$2 centralSurfacesDependenciesModule$2) {
        TransitionAnimator transitionAnimator = new TransitionAnimator(executor, TIMINGS, INTERPOLATORS);
        this.callback = centralSurfacesDependenciesModule$1;
        this.interactionJankMonitor = interactionJankMonitor;
        this.featureFlags = centralSurfacesDependenciesModule$2;
        this.transitionAnimator = transitionAnimator;
        this.openedDialogs = new HashSet();
    }

    public static DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default(DialogTransitionAnimator dialogTransitionAnimator, View view) {
        Object obj;
        View decorView;
        Iterator it = dialogTransitionAnimator.openedDialogs.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Window window = ((AnimatedDialog) obj).dialog.getWindow();
            if (Intrinsics.areEqual((window == null || (decorView = window.getDecorView()) == null) ? null : decorView.getViewRootImpl(), view.getViewRootImpl())) {
                break;
            }
        }
        AnimatedDialog animatedDialog = (AnimatedDialog) obj;
        if (animatedDialog == null) {
            return null;
        }
        return dialogTransitionAnimator.createActivityTransitionController(animatedDialog);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1] */
    public final DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController(final AnimatedDialog animatedDialog) {
        ViewGroup viewGroup;
        final GhostedViewTransitionAnimatorController fromView$default;
        animatedDialog.exitAnimationDisabled = true;
        final Dialog dialog = animatedDialog.dialog;
        if (dialog.isShowing()) {
            CentralSurfacesDependenciesModule$1 centralSurfacesDependenciesModule$1 = this.callback;
            if ((!centralSurfacesDependenciesModule$1.val$keyguardStateController.isUnlocked() && !((Boolean) ((AlternateBouncerInteractor) centralSurfacesDependenciesModule$1.val$alternateBouncerInteractor.get()).canShowAlternateBouncer.getValue()).booleanValue()) || (viewGroup = animatedDialog.dialogContentWithBackground) == null || (fromView$default = ActivityTransitionAnimator.Controller.Companion.fromView$default(viewGroup, null, 28)) == null) {
                return null;
            }
            return new ActivityTransitionAnimator.Controller(dialog, animatedDialog) { // from class: com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1
                public final /* synthetic */ GhostedViewTransitionAnimatorController $$delegate_0;
                public final /* synthetic */ AnimatedDialog $animatedDialog;
                public final /* synthetic */ Dialog $dialog;

                {
                    this.$dialog = dialog;
                    this.$animatedDialog = animatedDialog;
                    this.$$delegate_0 = GhostedViewTransitionAnimatorController.this;
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final TransitionAnimator.State createAnimatorState() {
                    return this.$$delegate_0.createAnimatorState();
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final View getOpeningWindowSyncView() {
                    return null;
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final ViewGroup getTransitionContainer() {
                    return this.$$delegate_0.transitionContainer;
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final WindowAnimationState getWindowAnimatorState() {
                    return null;
                }

                @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                public final boolean isBelowAnimatingWindow() {
                    return false;
                }

                @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                public final boolean isDialogLaunch() {
                    return true;
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final boolean isLaunching() {
                    return this.$$delegate_0.isLaunching;
                }

                @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                public final void onIntentStarted(boolean z) {
                    if (z) {
                        return;
                    }
                    this.$dialog.dismiss();
                }

                @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                public final void onTransitionAnimationCancelled() {
                    GhostedViewTransitionAnimatorController.this.onTransitionAnimationCancelled();
                    this.$dialog.setDismissOverride(new DialogTransitionAnimator$createActivityTransitionController$1$enableDialogDismiss$1(this.$animatedDialog));
                    this.$dialog.dismiss();
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final void onTransitionAnimationEnd(boolean z) {
                    GhostedViewTransitionAnimatorController.this.onTransitionAnimationEnd(z);
                    this.$dialog.hide();
                    this.$dialog.setDismissOverride(new DialogTransitionAnimator$createActivityTransitionController$1$enableDialogDismiss$1(this.$animatedDialog));
                    this.$dialog.dismiss();
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
                    this.$$delegate_0.onTransitionAnimationProgress(state, f, f2);
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final void onTransitionAnimationStart(boolean z) {
                    GhostedViewTransitionAnimatorController.this.onTransitionAnimationStart(z);
                    this.$dialog.setDismissOverride(DialogTransitionAnimator$createActivityTransitionController$1$disableDialogDismiss$1.INSTANCE);
                    this.$animatedDialog.prepareForStackDismiss();
                    Window window = this.$dialog.getWindow();
                    if (window != null) {
                        window.clearFlags(2);
                    }
                }

                @Override // com.android.systemui.animation.TransitionAnimator.Controller
                public final void setTransitionContainer(ViewGroup viewGroup2) {
                    this.$$delegate_0.transitionContainer = viewGroup2;
                }

                @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
                public final void getTransitionCookie() {
                }
            };
        }
        return null;
    }

    public final void disableAllCurrentDialogsExitAnimations() {
        Iterator it = this.openedDialogs.iterator();
        while (it.hasNext()) {
            ((AnimatedDialog) it.next()).exitAnimationDisabled = true;
        }
    }

    public final void dismissStack(Dialog dialog) {
        Object obj;
        Iterator it = this.openedDialogs.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((AnimatedDialog) obj).dialog, dialog)) {
                    break;
                }
            }
        }
        AnimatedDialog animatedDialog = (AnimatedDialog) obj;
        if (animatedDialog != null) {
            animatedDialog.prepareForStackDismiss();
        }
        dialog.dismiss();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void show(android.app.Dialog r18, com.android.systemui.animation.DialogTransitionAnimator.Controller r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.DialogTransitionAnimator.show(android.app.Dialog, com.android.systemui.animation.DialogTransitionAnimator$Controller, boolean):void");
    }

    public final void showFromDialog(Dialog dialog, Dialog dialog2, DialogCuj dialogCuj) {
        Object obj;
        Iterator it = this.openedDialogs.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((AnimatedDialog) obj).dialog, dialog2)) {
                    break;
                }
            }
        }
        AnimatedDialog animatedDialog = (AnimatedDialog) obj;
        ViewGroup viewGroup = animatedDialog != null ? animatedDialog.dialogContentWithBackground : null;
        if (viewGroup != null) {
            showFromView(dialog, viewGroup, dialogCuj, false);
            return;
        }
        Log.w("DialogTransitionAnimator", "Showing dialog " + dialog + " normally as the dialog it is shown from was not shown using DialogTransitionAnimator");
        dialog.show();
    }

    public final void showFromView(Dialog dialog, View view, DialogCuj dialogCuj, boolean z) {
        ViewDialogTransitionAnimatorController viewDialogTransitionAnimatorController;
        if (!(view instanceof LaunchableView)) {
            throw new IllegalArgumentException("A DialogTransitionAnimator.Controller was created from a View that does not implement LaunchableView. This can lead to subtle bugs where the visibility of the View we are launching from is not what we expected.");
        }
        if (view.getParent() instanceof ViewGroup) {
            viewDialogTransitionAnimatorController = new ViewDialogTransitionAnimatorController(view, dialogCuj);
        } else {
            Log.e("DialogTransitionAnimator", "Skipping animation as view " + view + " is not attached to a ViewGroup", new Exception());
            viewDialogTransitionAnimatorController = null;
        }
        if (viewDialogTransitionAnimatorController == null) {
            dialog.show();
        } else {
            show(dialog, viewDialogTransitionAnimatorController, z);
        }
    }

    public static DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default(DialogTransitionAnimator dialogTransitionAnimator, Dialog dialog) {
        Object obj;
        Iterator it = dialogTransitionAnimator.openedDialogs.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((AnimatedDialog) obj).dialog, dialog)) {
                break;
            }
        }
        AnimatedDialog animatedDialog = (AnimatedDialog) obj;
        if (animatedDialog == null) {
            return null;
        }
        return dialogTransitionAnimator.createActivityTransitionController(animatedDialog);
    }
}
