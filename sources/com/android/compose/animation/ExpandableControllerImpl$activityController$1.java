package com.android.compose.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.window.WindowAnimationState;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExpandableControllerImpl$activityController$1 implements ActivityTransitionAnimator.Controller, TransitionAnimator.Controller {
    public final /* synthetic */ ExpandableControllerImpl$transitionController$1 $$delegate_0;
    public final /* synthetic */ ExpandableControllerImpl$transitionController$1 $delegate;
    public final /* synthetic */ Integer $launchCujType;
    public final /* synthetic */ ExpandableControllerImpl this$0;

    public ExpandableControllerImpl$activityController$1(ExpandableControllerImpl$transitionController$1 expandableControllerImpl$transitionController$1, Integer num, ExpandableControllerImpl expandableControllerImpl) {
        this.$delegate = expandableControllerImpl$transitionController$1;
        this.$launchCujType = num;
        this.this$0 = expandableControllerImpl;
        this.$$delegate_0 = expandableControllerImpl$transitionController$1;
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

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        return this.$$delegate_0.isLaunching;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        Integer num = this.$$delegate_0.isLaunching ? this.$launchCujType : null;
        if (num != null) {
            InteractionJankMonitor.getInstance().end(num.intValue());
        }
        this.$delegate.onTransitionAnimationEnd(z);
        this.this$0.overlay.setValue(null);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        this.$$delegate_0.onTransitionAnimationProgress(state, f, f2);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        ExpandableControllerImpl expandableControllerImpl = this.this$0;
        expandableControllerImpl.overlay.setValue((ViewGroupOverlay) expandableControllerImpl.composeViewRoot.getRootView().getOverlay());
        Integer num = this.$$delegate_0.isLaunching ? this.$launchCujType : null;
        if (num != null) {
            InteractionJankMonitor.getInstance().begin(expandableControllerImpl.composeViewRoot, num.intValue());
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
        this.$$delegate_0.transitionContainer = viewGroup;
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void getTransitionCookie() {
    }
}
