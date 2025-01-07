package com.android.wm.shell.shared.animation;

import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class PhysicsAnimator$cancelAction$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        PhysicsAnimator physicsAnimator = (PhysicsAnimator) this.receiver;
        physicsAnimator.getClass();
        for (FloatPropertyCompat floatPropertyCompat : (Set) obj) {
            FlingAnimation flingAnimation = (FlingAnimation) physicsAnimator.flingAnimations.get(floatPropertyCompat);
            if (flingAnimation != null) {
                flingAnimation.cancel();
            }
            SpringAnimation springAnimation = (SpringAnimation) physicsAnimator.springAnimations.get(floatPropertyCompat);
            if (springAnimation != null) {
                springAnimation.cancel();
            }
        }
        return Unit.INSTANCE;
    }
}
