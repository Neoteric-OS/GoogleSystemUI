package com.android.wm.shell.shared.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhysicsAnimator$configureDynamicAnimation$1 implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ FloatPropertyCompat $property;
    public final /* synthetic */ PhysicsAnimator this$0;

    public PhysicsAnimator$configureDynamicAnimation$1(PhysicsAnimator physicsAnimator, FloatPropertyCompat floatPropertyCompat) {
        this.this$0 = physicsAnimator;
        this.$property = floatPropertyCompat;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
    public final void onAnimationUpdate(float f, float f2) {
        PhysicsAnimator physicsAnimator = this.this$0;
        int size = physicsAnimator.internalListeners.size();
        for (int i = 0; i < size; i++) {
            PhysicsAnimator.InternalListener internalListener = (PhysicsAnimator.InternalListener) physicsAnimator.internalListeners.get(i);
            Set set = internalListener.properties;
            FloatPropertyCompat floatPropertyCompat = this.$property;
            if (set.contains(floatPropertyCompat)) {
                internalListener.undispatchedUpdates.put(floatPropertyCompat, new PhysicsAnimator.AnimationUpdate(f, f2));
                internalListener.maybeDispatchUpdates();
            }
        }
    }
}
