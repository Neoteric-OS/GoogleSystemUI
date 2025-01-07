package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Animatable {
    public final SpringSpec defaultSpringSpec;
    public final AnimationState internalState;
    public final MutableState isRunning$delegate;
    public final AnimationVector lowerBoundVector;
    public final MutatorMutex mutatorMutex;
    public final AnimationVector negativeInfinityBounds;
    public final AnimationVector positiveInfinityBounds;
    public final MutableState targetValue$delegate;
    public final TwoWayConverter typeConverter;
    public final AnimationVector upperBoundVector;
    public final Object visibilityThreshold;

    public Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2) {
        this.typeConverter = twoWayConverter;
        this.visibilityThreshold = obj2;
        AnimationState animationState = new AnimationState(twoWayConverter, obj, null, 60);
        this.internalState = animationState;
        this.isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
        this.targetValue$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        this.mutatorMutex = new MutatorMutex();
        this.defaultSpringSpec = new SpringSpec(3, obj2);
        AnimationVector animationVector = animationState.velocityVector;
        boolean z = animationVector instanceof AnimationVector1D;
        AnimationVector animationVector2 = z ? AnimatableKt.negativeInfinityBounds1D : animationVector instanceof AnimationVector2D ? AnimatableKt.negativeInfinityBounds2D : animationVector instanceof AnimationVector3D ? AnimatableKt.negativeInfinityBounds3D : AnimatableKt.negativeInfinityBounds4D;
        this.negativeInfinityBounds = animationVector2;
        AnimationVector animationVector3 = z ? AnimatableKt.positiveInfinityBounds1D : animationVector instanceof AnimationVector2D ? AnimatableKt.positiveInfinityBounds2D : animationVector instanceof AnimationVector3D ? AnimatableKt.positiveInfinityBounds3D : AnimatableKt.positiveInfinityBounds4D;
        this.positiveInfinityBounds = animationVector3;
        this.lowerBoundVector = animationVector2;
        this.upperBoundVector = animationVector3;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r8v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public static final Object access$clampToBounds(Animatable animatable, Object obj) {
        AnimationVector animationVector = animatable.negativeInfinityBounds;
        AnimationVector animationVector2 = animatable.lowerBoundVector;
        boolean areEqual = Intrinsics.areEqual(animationVector2, animationVector);
        AnimationVector animationVector3 = animatable.upperBoundVector;
        if (areEqual && Intrinsics.areEqual(animationVector3, animatable.positiveInfinityBounds)) {
            return obj;
        }
        TwoWayConverterImpl twoWayConverterImpl = (TwoWayConverterImpl) animatable.typeConverter;
        AnimationVector animationVector4 = (AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj);
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        boolean z = false;
        for (int i = 0; i < size$animation_core_release; i++) {
            if (animationVector4.get$animation_core_release(i) < animationVector2.get$animation_core_release(i) || animationVector4.get$animation_core_release(i) > animationVector3.get$animation_core_release(i)) {
                animationVector4.set$animation_core_release(i, RangesKt.coerceIn(animationVector4.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)));
                z = true;
            }
        }
        return z ? twoWayConverterImpl.convertFromVector.invoke(animationVector4) : obj;
    }

    public static final void access$endAnimation(Animatable animatable) {
        AnimationState animationState = animatable.internalState;
        animationState.velocityVector.reset$animation_core_release();
        animationState.lastFrameTimeNanos = Long.MIN_VALUE;
        ((SnapshotMutableStateImpl) animatable.isRunning$delegate).setValue(Boolean.FALSE);
    }

    public static /* synthetic */ Object animateTo$default(Animatable animatable, Object obj, AnimationSpec animationSpec, Object obj2, Function1 function1, Continuation continuation, int i) {
        if ((i & 2) != 0) {
            animationSpec = animatable.defaultSpringSpec;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i & 4) != 0) {
            obj2 = animatable.getVelocity();
        }
        Object obj3 = obj2;
        if ((i & 8) != 0) {
            function1 = null;
        }
        return animatable.animateTo(obj, animationSpec2, obj3, function1, continuation);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final Object animateTo(Object obj, AnimationSpec animationSpec, Object obj2, Function1 function1, Continuation continuation) {
        AnimationState animationState = this.internalState;
        Object value = animationState.getValue();
        TwoWayConverter twoWayConverter = this.typeConverter;
        return MutatorMutex.mutate$default(this.mutatorMutex, new Animatable$runAnimation$2(this, obj2, new TargetBasedAnimation(animationSpec, twoWayConverter, value, obj, (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj2)), animationState.lastFrameTimeNanos, function1, null), continuation);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final Object getVelocity() {
        return ((TwoWayConverterImpl) this.typeConverter).convertFromVector.invoke(this.internalState.velocityVector);
    }

    public final Object snapTo(Object obj, Continuation continuation) {
        Object mutate$default = MutatorMutex.mutate$default(this.mutatorMutex, new Animatable$snapTo$2(this, obj, null), continuation);
        return mutate$default == CoroutineSingletons.COROUTINE_SUSPENDED ? mutate$default : Unit.INSTANCE;
    }

    public final Object stop(SuspendLambda suspendLambda) {
        Object mutate$default = MutatorMutex.mutate$default(this.mutatorMutex, new Animatable$stop$2(this, null), suspendLambda);
        return mutate$default == CoroutineSingletons.COROUTINE_SUSPENDED ? mutate$default : Unit.INSTANCE;
    }

    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, String str, int i) {
        this(obj, twoWayConverter, (i & 4) != 0 ? null : obj2);
    }
}
