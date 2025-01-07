package com.android.systemui.communal.widgets;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DelegateTransitionAnimatorController;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTransitionAnimatorController extends DelegateTransitionAnimatorController {
    public final CommunalSceneInteractor communalSceneInteractor;

    public CommunalTransitionAnimatorController(GhostedViewTransitionAnimatorController ghostedViewTransitionAnimatorController, CommunalSceneInteractor communalSceneInteractor) {
        super(ghostedViewTransitionAnimatorController);
        this.communalSceneInteractor = communalSceneInteractor;
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onIntentStarted(boolean z) {
        if (!z) {
            this.communalSceneInteractor.setIsLaunchingWidget(false);
        }
        this.delegate.onIntentStarted(z);
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onTransitionAnimationCancelled() {
        this.communalSceneInteractor.setIsLaunchingWidget(false);
        this.delegate.onTransitionAnimationCancelled();
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        this.communalSceneInteractor.setIsLaunchingWidget(false);
        this.delegate.onTransitionAnimationEnd(z);
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        this.delegate.onTransitionAnimationStart(z);
        SceneKey sceneKey = CommunalScenes.Blank;
        TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
        CommunalSceneInteractor.snapToScene$default(this.communalSceneInteractor, sceneKey, "CommunalTransitionAnimatorController", 8);
    }
}
