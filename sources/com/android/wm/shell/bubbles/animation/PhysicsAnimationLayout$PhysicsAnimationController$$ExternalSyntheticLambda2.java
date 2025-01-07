package com.android.wm.shell.bubbles.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhysicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda2 {
    public final /* synthetic */ PhysicsAnimationLayout.PhysicsAnimationController f$0;
    public final /* synthetic */ Set f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ PhysicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda2(PhysicsAnimationLayout.PhysicsAnimationController physicsAnimationController, Set set, List list) {
        this.f$0 = physicsAnimationController;
        this.f$1 = set;
        this.f$2 = list;
    }

    public final void startAll(Runnable[] runnableArr) {
        Set set = this.f$1;
        List list = this.f$2;
        PhysicsAnimationLayout.PhysicsAnimationController physicsAnimationController = this.f$0;
        physicsAnimationController.getClass();
        PhysicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3 physicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3 = new PhysicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3(1, runnableArr);
        if (physicsAnimationController.mLayout.getChildCount() == 0) {
            physicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3.run();
            return;
        }
        DynamicAnimation.ViewProperty[] viewPropertyArr = (DynamicAnimation.ViewProperty[]) set.toArray(new DynamicAnimation.ViewProperty[0]);
        PhysicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda1 physicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda1 = new PhysicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda1(physicsAnimationController, viewPropertyArr, physicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3);
        for (DynamicAnimation.ViewProperty viewProperty : viewPropertyArr) {
            physicsAnimationController.mLayout.mEndActionForProperty.put(viewProperty, physicsAnimationLayout$PhysicsAnimationController$$ExternalSyntheticLambda1);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((PhysicsAnimationLayout.PhysicsPropertyAnimator) it.next()).start(new Runnable[0]);
        }
    }
}
