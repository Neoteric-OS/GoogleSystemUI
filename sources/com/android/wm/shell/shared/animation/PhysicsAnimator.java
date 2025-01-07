package com.android.wm.shell.shared.animation;

import android.util.ArrayMap;
import android.util.Log;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhysicsAnimator {
    public static final Function2 onAnimatorCreated = PhysicsAnimator$Companion$onAnimatorCreated$1.INSTANCE;
    public final WeakReference weakTarget;
    public final ArrayMap springAnimations = new ArrayMap();
    public final ArrayMap flingAnimations = new ArrayMap();
    public final ArrayMap springConfigs = new ArrayMap();
    public final ArrayMap flingConfigs = new ArrayMap();
    public final ArrayList updateListeners = new ArrayList();
    public final ArrayList endListeners = new ArrayList();
    public final ArrayList endActions = new ArrayList();
    public SpringConfig defaultSpring = PhysicsAnimatorKt.globalDefaultSpring;
    public final ArrayList internalListeners = new ArrayList();
    public final Function0 startAction = new PhysicsAnimator$startAction$1(0, this, PhysicsAnimator.class, "startInternal", "startInternal$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared()V", 0);
    public final Function1 cancelAction = new PhysicsAnimator$cancelAction$1(1, this, PhysicsAnimator.class, "cancelInternal", "cancelInternal$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared(Ljava/util/Set;)V", 0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationUpdate {
        public final float value;
        public final float velocity;

        public AnimationUpdate(float f, float f2) {
            this.value = f;
            this.velocity = f2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimationUpdate)) {
                return false;
            }
            AnimationUpdate animationUpdate = (AnimationUpdate) obj;
            return Float.compare(this.value, animationUpdate.value) == 0 && Float.compare(this.velocity, animationUpdate.velocity) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.velocity) + (Float.hashCode(this.value) * 31);
        }

        public final String toString() {
            return "AnimationUpdate(value=" + this.value + ", velocity=" + this.velocity + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static PhysicsAnimator getInstance(Object obj) {
            WeakHashMap weakHashMap = PhysicsAnimatorKt.animators;
            if (!weakHashMap.containsKey(obj)) {
                PhysicsAnimator physicsAnimator = new PhysicsAnimator(obj);
                ((PhysicsAnimator$Companion$onAnimatorCreated$1) PhysicsAnimator.onAnimatorCreated).getClass();
                weakHashMap.put(obj, physicsAnimator);
            }
            return (PhysicsAnimator) weakHashMap.get(obj);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface EndListener {
        void onAnimationEnd(Object obj, FloatPropertyCompat floatPropertyCompat, boolean z, boolean z2, float f, float f2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternalListener {
        public final List endActions;
        public final List endListeners;
        public int numPropertiesAnimating;
        public final Set properties;
        public final Object target;
        public final ArrayMap undispatchedUpdates = new ArrayMap();
        public final List updateListeners;

        public InternalListener(Object obj, Set set, List list, List list2, List list3) {
            this.target = obj;
            this.properties = set;
            this.updateListeners = list;
            this.endListeners = list2;
            this.endActions = list3;
            this.numPropertiesAnimating = set.size();
        }

        public final void maybeDispatchUpdates() {
            if (this.undispatchedUpdates.size() < this.numPropertiesAnimating || this.undispatchedUpdates.size() <= 0) {
                return;
            }
            for (UpdateListener updateListener : this.updateListeners) {
                new ArrayMap(this.undispatchedUpdates);
                updateListener.onAnimationUpdateForProperty(this.target);
            }
            this.undispatchedUpdates.clear();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface UpdateListener {
        void onAnimationUpdateForProperty(Object obj);
    }

    public PhysicsAnimator(Object obj) {
        this.weakTarget = new WeakReference(obj);
    }

    public final boolean arePropertiesAnimating(Set set) {
        Set set2 = set;
        if ((set2 instanceof Collection) && set2.isEmpty()) {
            return false;
        }
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            if (isPropertyAnimating((FloatPropertyCompat) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final void cancel() {
        int size = this.flingAnimations.size();
        Function1 function1 = this.cancelAction;
        if (size > 0) {
            ((PhysicsAnimator$cancelAction$1) function1).invoke(this.flingAnimations.keySet());
        }
        if (this.springAnimations.size() > 0) {
            ((PhysicsAnimator$cancelAction$1) function1).invoke(this.springAnimations.keySet());
        }
    }

    public final void flingThenSpring(FloatPropertyCompat floatPropertyCompat, float f, FlingConfig flingConfig, SpringConfig springConfig, boolean z) {
        float f2 = f;
        Object obj = this.weakTarget.get();
        if (obj == null) {
            Log.w("PhysicsAnimator", "Trying to animate a GC-ed target.");
            return;
        }
        float f3 = flingConfig.min;
        float f4 = flingConfig.max;
        float f5 = flingConfig.startVelocity;
        float f6 = flingConfig.friction;
        FlingConfig flingConfig2 = new FlingConfig(f6, f3, f4, f5);
        SpringConfig springConfig2 = new SpringConfig(springConfig.stiffness, springConfig.dampingRatio, springConfig.startVelocity, springConfig.finalPosition);
        if (f2 >= 0.0f) {
            f3 = f4;
        }
        if (!z || f3 >= Float.MAX_VALUE || f3 <= -3.4028235E38f) {
            flingConfig2.startVelocity = f2;
        } else {
            float f7 = f6 * 4.2f;
            float value = (f2 / f7) + floatPropertyCompat.getValue(obj);
            float f8 = flingConfig.min;
            float f9 = flingConfig.max;
            float f10 = (f8 + f9) / 2;
            if ((f2 < 0.0f && value > f10) || (f2 > 0.0f && value < f10)) {
                if (value >= f10) {
                    f8 = f9;
                }
                if (f8 < Float.MAX_VALUE && f8 > -3.4028235E38f) {
                    spring(floatPropertyCompat, f8, f2, springConfig);
                    return;
                }
            }
            float value2 = f3 - floatPropertyCompat.getValue(obj);
            float f11 = f7 * value2;
            if (value2 > 0.0f && f2 >= 0.0f) {
                f2 = Math.max(f11, f2);
            } else if (value2 < 0.0f && f2 <= 0.0f) {
                f2 = Math.min(f11, f2);
            }
            flingConfig2.startVelocity = f2;
            springConfig2.finalPosition = f3;
        }
        this.flingConfigs.put(floatPropertyCompat, flingConfig2);
        this.springConfigs.put(floatPropertyCompat, springConfig2);
    }

    public final boolean isPropertyAnimating(FloatPropertyCompat floatPropertyCompat) {
        SpringAnimation springAnimation = (SpringAnimation) this.springAnimations.get(floatPropertyCompat);
        if (!(springAnimation != null ? springAnimation.mRunning : false)) {
            FlingAnimation flingAnimation = (FlingAnimation) this.flingAnimations.get(floatPropertyCompat);
            if (!(flingAnimation != null ? flingAnimation.mRunning : false)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isRunning() {
        Set keySet = this.springAnimations.keySet();
        Set keySet2 = this.flingAnimations.keySet();
        Set mutableSet = CollectionsKt.toMutableSet(keySet);
        CollectionsKt__MutableCollectionsKt.addAll(keySet2, mutableSet);
        return arePropertiesAnimating(mutableSet);
    }

    public final void spring(FloatPropertyCompat floatPropertyCompat, float f, float f2, SpringConfig springConfig) {
        WeakHashMap weakHashMap = PhysicsAnimatorKt.animators;
        this.springConfigs.put(floatPropertyCompat, new SpringConfig(springConfig.stiffness, springConfig.dampingRatio, f2, f));
    }

    public final void start() {
        ((PhysicsAnimator$startAction$1) this.startAction).invoke();
    }

    public final void withEndActions(Function0... function0Arr) {
        this.endActions.addAll(ArraysKt.filterNotNull(function0Arr));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SpringConfig {
        public final float dampingRatio;
        public float finalPosition;
        public float startVelocity;
        public final float stiffness;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public SpringConfig(float f, float f2) {
            this(f, f2, 0.0f, -3.4028235E38f);
            WeakHashMap weakHashMap = PhysicsAnimatorKt.animators;
        }

        public final void applyToAnimation$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared(SpringAnimation springAnimation) {
            SpringForce springForce = springAnimation.mSpring;
            if (springForce == null) {
                springForce = new SpringForce();
            }
            springForce.setStiffness(this.stiffness);
            springForce.setDampingRatio(this.dampingRatio);
            springForce.mFinalPosition = this.finalPosition;
            springAnimation.mSpring = springForce;
            float f = this.startVelocity;
            if (f == 0.0f) {
                return;
            }
            springAnimation.mVelocity = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpringConfig)) {
                return false;
            }
            SpringConfig springConfig = (SpringConfig) obj;
            return Float.compare(this.stiffness, springConfig.stiffness) == 0 && Float.compare(this.dampingRatio, springConfig.dampingRatio) == 0 && Float.compare(this.startVelocity, springConfig.startVelocity) == 0 && Float.compare(this.finalPosition, springConfig.finalPosition) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.finalPosition) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.stiffness) * 31, this.dampingRatio, 31), this.startVelocity, 31);
        }

        public final String toString() {
            return "SpringConfig(stiffness=" + this.stiffness + ", dampingRatio=" + this.dampingRatio + ", startVelocity=" + this.startVelocity + ", finalPosition=" + this.finalPosition + ")";
        }

        public SpringConfig(float f, float f2, float f3, float f4) {
            this.stiffness = f;
            this.dampingRatio = f2;
            this.startVelocity = f3;
            this.finalPosition = f4;
        }
    }

    public final void withEndActions(Runnable... runnableArr) {
        ArrayList arrayList = this.endActions;
        List filterNotNull = ArraysKt.filterNotNull(runnableArr);
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(filterNotNull, 10));
        Iterator it = filterNotNull.iterator();
        while (it.hasNext()) {
            arrayList2.add(new PhysicsAnimator$withEndActions$1$1(0, (Runnable) it.next(), Runnable.class, "run", "run()V", 0));
        }
        arrayList.addAll(arrayList2);
    }

    public final void spring(DynamicAnimation.ViewProperty viewProperty, float f) {
        spring(viewProperty, f, 0.0f, this.defaultSpring);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlingConfig {
        public final float friction;
        public float max;
        public float min;
        public float startVelocity;

        public FlingConfig(float f, float f2, float f3, float f4) {
            this.friction = f;
            this.min = f2;
            this.max = f3;
            this.startVelocity = f4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlingConfig)) {
                return false;
            }
            FlingConfig flingConfig = (FlingConfig) obj;
            return Float.compare(this.friction, flingConfig.friction) == 0 && Float.compare(this.min, flingConfig.min) == 0 && Float.compare(this.max, flingConfig.max) == 0 && Float.compare(this.startVelocity, flingConfig.startVelocity) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.startVelocity) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.friction) * 31, this.min, 31), this.max, 31);
        }

        public final String toString() {
            return "FlingConfig(friction=" + this.friction + ", min=" + this.min + ", max=" + this.max + ", startVelocity=" + this.startVelocity + ")";
        }

        public FlingConfig(float f, float f2, float f3) {
            this(f, f2, f3, 0.0f);
        }
    }

    public final void cancel(FloatPropertyCompat... floatPropertyCompatArr) {
        ((PhysicsAnimator$cancelAction$1) this.cancelAction).invoke(ArraysKt.toSet(floatPropertyCompatArr));
    }
}
