package com.android.compose.animation;

import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.systemui.animation.TransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExpandableControllerImpl$dialogController$1$createExitController$1 implements TransitionAnimator.Controller {
    public final /* synthetic */ ExpandableControllerImpl$transitionController$1 $$delegate_0;
    public final /* synthetic */ ExpandableControllerImpl$transitionController$1 $delegate;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ExpandableControllerImpl this$0;

    public /* synthetic */ ExpandableControllerImpl$dialogController$1$createExitController$1(ExpandableControllerImpl$transitionController$1 expandableControllerImpl$transitionController$1, ExpandableControllerImpl expandableControllerImpl, int i) {
        this.$r8$classId = i;
        this.$delegate = expandableControllerImpl$transitionController$1;
        this.this$0 = expandableControllerImpl;
        this.$$delegate_0 = expandableControllerImpl$transitionController$1;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final TransitionAnimator.State createAnimatorState() {
        switch (this.$r8$classId) {
        }
        return this.$$delegate_0.createAnimatorState();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final View getOpeningWindowSyncView() {
        switch (this.$r8$classId) {
        }
        return null;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final ViewGroup getTransitionContainer() {
        int i = this.$r8$classId;
        ExpandableControllerImpl$transitionController$1 expandableControllerImpl$transitionController$1 = this.$$delegate_0;
        switch (i) {
        }
        return expandableControllerImpl$transitionController$1.transitionContainer;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final WindowAnimationState getWindowAnimatorState() {
        switch (this.$r8$classId) {
        }
        return null;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        switch (this.$r8$classId) {
        }
        return this.$$delegate_0.isLaunching;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        switch (this.$r8$classId) {
            case 0:
                this.$delegate.onTransitionAnimationEnd(z);
                this.this$0.isDialogShowing.setValue(Boolean.FALSE);
                break;
            default:
                this.$delegate.onTransitionAnimationEnd(z);
                this.this$0.isDialogShowing.setValue(Boolean.TRUE);
                break;
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                this.$$delegate_0.onTransitionAnimationProgress(state, f, f2);
                break;
            default:
                this.$$delegate_0.onTransitionAnimationProgress(state, f, f2);
                break;
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        int i = this.$r8$classId;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
        int i = this.$r8$classId;
        ExpandableControllerImpl$transitionController$1 expandableControllerImpl$transitionController$1 = this.$$delegate_0;
        switch (i) {
            case 0:
                expandableControllerImpl$transitionController$1.transitionContainer = viewGroup;
                break;
            default:
                expandableControllerImpl$transitionController$1.transitionContainer = viewGroup;
                break;
        }
    }

    private final void onTransitionAnimationStart$com$android$compose$animation$ExpandableControllerImpl$dialogController$1$createExitController$1(boolean z) {
    }

    private final void onTransitionAnimationStart$com$android$compose$animation$ExpandableControllerImpl$dialogController$1$createTransitionController$1(boolean z) {
    }
}
