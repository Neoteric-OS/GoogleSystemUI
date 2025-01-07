package com.android.wm.shell.bubbles.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ExpandedAnimationController$$ExternalSyntheticLambda1 implements PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator {
    @Override // com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout.PhysicsAnimationController.ChildAnimationConfigurator
    public final void configureAnimationForChildAtIndex(int i, PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator) {
        physicsPropertyAnimator.property(DynamicAnimation.SCALE_X, 1.0f, new Runnable[0]);
        physicsPropertyAnimator.property(DynamicAnimation.SCALE_Y, 1.0f, new Runnable[0]);
        physicsPropertyAnimator.property(DynamicAnimation.ALPHA, 1.0f, new Runnable[0]);
    }
}
