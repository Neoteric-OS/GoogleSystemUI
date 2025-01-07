package com.android.wm.shell.shared.animation;

import android.util.ArrayMap;
import android.util.Log;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.animation.PhysicsAnimator.InternalListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class PhysicsAnimator$startAction$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        m906invoke();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: collision with other method in class */
    public final void m906invoke() {
        final PhysicsAnimator physicsAnimator = (PhysicsAnimator) this.receiver;
        final Object obj = physicsAnimator.weakTarget.get();
        if (obj == null) {
            Log.w("PhysicsAnimator", "Trying to animate a GC-ed object.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Set keySet = physicsAnimator.springConfigs.keySet();
        Set keySet2 = physicsAnimator.flingConfigs.keySet();
        Set<FloatPropertyCompat> mutableSet = CollectionsKt.toMutableSet(keySet);
        CollectionsKt__MutableCollectionsKt.addAll(keySet2, mutableSet);
        for (final FloatPropertyCompat floatPropertyCompat : mutableSet) {
            final PhysicsAnimator.FlingConfig flingConfig = (PhysicsAnimator.FlingConfig) physicsAnimator.flingConfigs.get(floatPropertyCompat);
            final PhysicsAnimator.SpringConfig springConfig = (PhysicsAnimator.SpringConfig) physicsAnimator.springConfigs.get(floatPropertyCompat);
            final float value = floatPropertyCompat.getValue(obj);
            if (flingConfig != null) {
                arrayList.add(new Function0() { // from class: com.android.wm.shell.shared.animation.PhysicsAnimator$startInternal$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        PhysicsAnimator.FlingConfig flingConfig2 = PhysicsAnimator.FlingConfig.this;
                        float f = value;
                        flingConfig2.min = Math.min(f, flingConfig2.min);
                        flingConfig2.max = Math.max(f, flingConfig2.max);
                        physicsAnimator.cancel(floatPropertyCompat);
                        PhysicsAnimator physicsAnimator2 = physicsAnimator;
                        FloatPropertyCompat floatPropertyCompat2 = floatPropertyCompat;
                        Object obj2 = obj;
                        ArrayMap arrayMap = physicsAnimator2.flingAnimations;
                        Object obj3 = arrayMap.get(floatPropertyCompat2);
                        Object obj4 = obj3;
                        if (obj3 == null) {
                            FlingAnimation flingAnimation = new FlingAnimation(obj2, floatPropertyCompat2);
                            flingAnimation.addUpdateListener(new PhysicsAnimator$configureDynamicAnimation$1(physicsAnimator2, floatPropertyCompat2));
                            flingAnimation.addEndListener(new PhysicsAnimator$configureDynamicAnimation$2(physicsAnimator2, floatPropertyCompat2, flingAnimation));
                            arrayMap.put(floatPropertyCompat2, flingAnimation);
                            obj4 = flingAnimation;
                        }
                        FlingAnimation flingAnimation2 = (FlingAnimation) obj4;
                        PhysicsAnimator.FlingConfig flingConfig3 = PhysicsAnimator.FlingConfig.this;
                        flingAnimation2.setFriction(flingConfig3.friction);
                        flingAnimation2.mMinValue = flingConfig3.min;
                        flingAnimation2.mMaxValue = flingConfig3.max;
                        flingAnimation2.mVelocity = flingConfig3.startVelocity;
                        flingAnimation2.start();
                        return Unit.INSTANCE;
                    }
                });
            }
            if (springConfig != null) {
                if (flingConfig == null) {
                    ArrayMap arrayMap = physicsAnimator.springAnimations;
                    Object obj2 = arrayMap.get(floatPropertyCompat);
                    Object obj3 = obj2;
                    if (obj2 == null) {
                        SpringAnimation springAnimation = new SpringAnimation(obj, floatPropertyCompat);
                        springAnimation.addUpdateListener(new PhysicsAnimator$configureDynamicAnimation$1(physicsAnimator, floatPropertyCompat));
                        springAnimation.addEndListener(new PhysicsAnimator$configureDynamicAnimation$2(physicsAnimator, floatPropertyCompat, springAnimation));
                        arrayMap.put(floatPropertyCompat, springAnimation);
                        obj3 = springAnimation;
                    }
                    SpringAnimation springAnimation2 = (SpringAnimation) obj3;
                    springConfig.applyToAnimation$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared(springAnimation2);
                    arrayList.add(new PhysicsAnimator$startInternal$2(0, springAnimation2, SpringAnimation.class, "start", "start()V", 0));
                } else {
                    final float f = flingConfig.min;
                    final float f2 = flingConfig.max;
                    physicsAnimator.endListeners.add(0, new PhysicsAnimator.EndListener() { // from class: com.android.wm.shell.shared.animation.PhysicsAnimator$startInternal$3
                        @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.EndListener
                        public final void onAnimationEnd(Object obj4, FloatPropertyCompat floatPropertyCompat2, boolean z, boolean z2, float f3, float f4) {
                            FloatPropertyCompat floatPropertyCompat3 = FloatPropertyCompat.this;
                            if (floatPropertyCompat2.equals(floatPropertyCompat3) && z && !z2) {
                                boolean z3 = Math.abs(f4) > 0.0f;
                                float f5 = f;
                                float f6 = f2;
                                boolean z4 = f5 <= f3 && f3 <= f6;
                                if (z3 || !z4) {
                                    PhysicsAnimator.SpringConfig springConfig2 = springConfig;
                                    springConfig2.startVelocity = f4;
                                    float f7 = springConfig2.finalPosition;
                                    WeakHashMap weakHashMap = PhysicsAnimatorKt.animators;
                                    if (f7 == -3.4028235E38f) {
                                        if (z3) {
                                            if (f4 >= 0.0f) {
                                                f5 = f6;
                                            }
                                            springConfig2.finalPosition = f5;
                                        } else if (!z4) {
                                            if (f3 >= f5) {
                                                f5 = f6;
                                            }
                                            springConfig2.finalPosition = f5;
                                        }
                                    }
                                    PhysicsAnimator physicsAnimator2 = physicsAnimator;
                                    ArrayMap arrayMap2 = physicsAnimator2.springAnimations;
                                    Object obj5 = arrayMap2.get(floatPropertyCompat3);
                                    Object obj6 = obj5;
                                    if (obj5 == null) {
                                        SpringAnimation springAnimation3 = new SpringAnimation(obj4, floatPropertyCompat3);
                                        springAnimation3.addUpdateListener(new PhysicsAnimator$configureDynamicAnimation$1(physicsAnimator2, floatPropertyCompat3));
                                        springAnimation3.addEndListener(new PhysicsAnimator$configureDynamicAnimation$2(physicsAnimator2, floatPropertyCompat3, springAnimation3));
                                        arrayMap2.put(floatPropertyCompat3, springAnimation3);
                                        obj6 = springAnimation3;
                                    }
                                    SpringAnimation springAnimation4 = (SpringAnimation) obj6;
                                    springConfig2.applyToAnimation$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared(springAnimation4);
                                    springAnimation4.start();
                                }
                            }
                        }
                    });
                }
            }
        }
        ArrayList arrayList2 = physicsAnimator.internalListeners;
        Set keySet3 = physicsAnimator.springConfigs.keySet();
        Set keySet4 = physicsAnimator.flingConfigs.keySet();
        Set mutableSet2 = CollectionsKt.toMutableSet(keySet3);
        CollectionsKt__MutableCollectionsKt.addAll(keySet4, mutableSet2);
        arrayList2.add(physicsAnimator.new InternalListener(obj, mutableSet2, new ArrayList(physicsAnimator.updateListeners), new ArrayList(physicsAnimator.endListeners), new ArrayList(physicsAnimator.endActions)));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        physicsAnimator.springConfigs.clear();
        physicsAnimator.flingConfigs.clear();
        physicsAnimator.updateListeners.clear();
        physicsAnimator.endListeners.clear();
        physicsAnimator.endActions.clear();
    }
}
