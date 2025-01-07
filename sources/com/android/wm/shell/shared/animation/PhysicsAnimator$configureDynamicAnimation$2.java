package com.android.wm.shell.shared.animation;

import android.util.ArrayMap;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhysicsAnimator$configureDynamicAnimation$2 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ DynamicAnimation $anim;
    public final /* synthetic */ FloatPropertyCompat $property;
    public final /* synthetic */ PhysicsAnimator this$0;

    public PhysicsAnimator$configureDynamicAnimation$2(PhysicsAnimator physicsAnimator, FloatPropertyCompat floatPropertyCompat, DynamicAnimation dynamicAnimation) {
        this.this$0 = physicsAnimator;
        this.$property = floatPropertyCompat;
        this.$anim = dynamicAnimation;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, final boolean z, final float f, final float f2) {
        PhysicsAnimator physicsAnimator = this.this$0;
        ArrayList arrayList = physicsAnimator.internalListeners;
        final FloatPropertyCompat floatPropertyCompat = this.$property;
        final DynamicAnimation dynamicAnimation2 = this.$anim;
        CollectionsKt__MutableCollectionsKt.removeAll(arrayList, new Function1() { // from class: com.android.wm.shell.shared.animation.PhysicsAnimator$configureDynamicAnimation$2.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                PhysicsAnimator.InternalListener internalListener = (PhysicsAnimator.InternalListener) obj;
                FloatPropertyCompat floatPropertyCompat2 = FloatPropertyCompat.this;
                boolean z2 = z;
                float f3 = f;
                float f4 = f2;
                boolean z3 = dynamicAnimation2 instanceof FlingAnimation;
                boolean z4 = false;
                if (internalListener.properties.contains(floatPropertyCompat2)) {
                    internalListener.numPropertiesAnimating--;
                    internalListener.maybeDispatchUpdates();
                    boolean containsKey = internalListener.undispatchedUpdates.containsKey(floatPropertyCompat2);
                    Object obj2 = internalListener.target;
                    if (containsKey) {
                        for (PhysicsAnimator.UpdateListener updateListener : internalListener.updateListeners) {
                            new ArrayMap().put(floatPropertyCompat2, internalListener.undispatchedUpdates.get(floatPropertyCompat2));
                            updateListener.onAnimationUpdateForProperty(obj2);
                        }
                        internalListener.undispatchedUpdates.remove(floatPropertyCompat2);
                    }
                    Set set = internalListener.properties;
                    PhysicsAnimator physicsAnimator2 = PhysicsAnimator.this;
                    boolean arePropertiesAnimating = physicsAnimator2.arePropertiesAnimating(set);
                    boolean z5 = !arePropertiesAnimating;
                    Iterator it = internalListener.endListeners.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            ((PhysicsAnimator.EndListener) it.next()).onAnimationEnd(obj2, floatPropertyCompat2, z3, z2, f3, f4);
                            if (physicsAnimator2.isPropertyAnimating(floatPropertyCompat2)) {
                                break;
                            }
                        } else {
                            if (!arePropertiesAnimating && !z2) {
                                Iterator it2 = internalListener.endActions.iterator();
                                while (it2.hasNext()) {
                                    ((Function0) it2.next()).invoke();
                                }
                            }
                            z4 = z5;
                        }
                    }
                }
                return Boolean.valueOf(z4);
            }
        });
        ArrayMap arrayMap = physicsAnimator.springAnimations;
        FloatPropertyCompat floatPropertyCompat2 = this.$property;
        if (Intrinsics.areEqual(arrayMap.get(floatPropertyCompat2), dynamicAnimation2)) {
            physicsAnimator.springAnimations.remove(floatPropertyCompat2);
        }
        if (Intrinsics.areEqual(physicsAnimator.flingAnimations.get(floatPropertyCompat2), dynamicAnimation2)) {
            physicsAnimator.flingAnimations.remove(floatPropertyCompat2);
        }
    }
}
