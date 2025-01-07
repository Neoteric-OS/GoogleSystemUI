package androidx.compose.animation.core;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableLongStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Transition {
    public final SnapshotStateList _animations;
    public final SnapshotStateList _transitions;
    public final MutableState isSeeking$delegate;
    public final String label;
    public final Transition parentTransition;
    public final MutableTransitionState transitionState;
    public final MutableState updateChildrenNeeded$delegate;
    public final MutableState targetState$delegate = SnapshotStateKt.mutableStateOf$default(getCurrentState());
    public final MutableState segment$delegate = SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()));
    public final MutableLongState _playTimeNanos$delegate = SnapshotLongStateKt.mutableLongStateOf(0);
    public final MutableLongState startTimeNanos$delegate = SnapshotLongStateKt.mutableLongStateOf(Long.MIN_VALUE);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeferredAnimation {
        public final MutableState data$delegate = SnapshotStateKt.mutableStateOf$default(null);
        public final String label;
        public final TwoWayConverter typeConverter;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class DeferredAnimationData implements State {
            public final TransitionAnimationState animation;
            public Lambda targetValueByState;
            public Lambda transitionSpec;

            /* JADX WARN: Multi-variable type inference failed */
            public DeferredAnimationData(TransitionAnimationState transitionAnimationState, Function1 function1, Function1 function12) {
                this.animation = transitionAnimationState;
                this.transitionSpec = (Lambda) function1;
                this.targetValueByState = (Lambda) function12;
            }

            @Override // androidx.compose.runtime.State
            public final Object getValue() {
                updateAnimationStates(Transition.this.getSegment());
                return ((SnapshotMutableStateImpl) this.animation.value$delegate).getValue();
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r1v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r4v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r4v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            public final void updateAnimationStates(Segment segment) {
                Object invoke = this.targetValueByState.invoke(segment.getTargetState());
                boolean isSeeking = Transition.this.isSeeking();
                TransitionAnimationState transitionAnimationState = this.animation;
                if (isSeeking) {
                    transitionAnimationState.updateInitialAndTargetValue$animation_core_release(this.targetValueByState.invoke(segment.getInitialState()), invoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                } else {
                    transitionAnimationState.updateTargetValue$animation_core_release(invoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                }
            }
        }

        public DeferredAnimation(TwoWayConverter twoWayConverter, String str) {
            this.typeConverter = twoWayConverter;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        public final DeferredAnimationData animate(Function1 function1, Function1 function12) {
            MutableState mutableState = this.data$delegate;
            DeferredAnimationData deferredAnimationData = (DeferredAnimationData) ((SnapshotMutableStateImpl) mutableState).getValue();
            Transition transition = Transition.this;
            if (deferredAnimationData == null) {
                Object invoke = function12.invoke(transition.getCurrentState());
                Object invoke2 = function12.invoke(transition.getCurrentState());
                TwoWayConverter twoWayConverter = this.typeConverter;
                AnimationVector animationVector = (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(invoke2);
                animationVector.reset$animation_core_release();
                TransitionAnimationState transitionAnimationState = transition.new TransitionAnimationState(invoke, animationVector, twoWayConverter);
                deferredAnimationData = new DeferredAnimationData(transitionAnimationState, function1, function12);
                ((SnapshotMutableStateImpl) mutableState).setValue(deferredAnimationData);
                transition._animations.add(transitionAnimationState);
            }
            deferredAnimationData.targetValueByState = (Lambda) function12;
            deferredAnimationData.transitionSpec = (Lambda) function1;
            deferredAnimationData.updateAnimationStates(transition.getSegment());
            return deferredAnimationData;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Segment {
        Object getInitialState();

        Object getTargetState();

        default boolean isTransitioningTo(Object obj, Object obj2) {
            return obj.equals(getInitialState()) && obj2.equals(getTargetState());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SegmentImpl implements Segment {
        public final Object initialState;
        public final Object targetState;

        public SegmentImpl(Object obj, Object obj2) {
            this.initialState = obj;
            this.targetState = obj2;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Segment) {
                Segment segment = (Segment) obj;
                if (Intrinsics.areEqual(this.initialState, segment.getInitialState())) {
                    if (Intrinsics.areEqual(this.targetState, segment.getTargetState())) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public final Object getInitialState() {
            return this.initialState;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public final Object getTargetState() {
            return this.targetState;
        }

        public final int hashCode() {
            Object obj = this.initialState;
            int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
            Object obj2 = this.targetState;
            return hashCode + (obj2 != null ? obj2.hashCode() : 0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionAnimationState implements State {
        public final MutableState animation$delegate;
        public final MutableState animationSpec$delegate;
        public final MutableLongState durationNanos$delegate;
        public final SpringSpec interruptionSpec;
        public final MutableState isFinished$delegate;
        public boolean isSeeking;
        public final MutableFloatState resetSnapValue$delegate;
        public final MutableState targetValue$delegate;
        public final TwoWayConverter typeConverter;
        public boolean useOnlyInitialValue;
        public final MutableState value$delegate;
        public AnimationVector velocityVector;

        /* JADX WARN: Type inference failed for: r11v19, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        /* JADX WARN: Type inference failed for: r13v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        public TransitionAnimationState(Object obj, AnimationVector animationVector, TwoWayConverter twoWayConverter) {
            this.typeConverter = twoWayConverter;
            MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(obj);
            this.targetValue$delegate = mutableStateOf$default;
            Object obj2 = null;
            MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7));
            this.animationSpec$delegate = mutableStateOf$default2;
            this.animation$delegate = SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation((FiniteAnimationSpec) ((SnapshotMutableStateImpl) mutableStateOf$default2).getValue(), twoWayConverter, obj, ((SnapshotMutableStateImpl) mutableStateOf$default).getValue(), animationVector));
            this.isFinished$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
            this.resetSnapValue$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(-1.0f);
            this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
            this.velocityVector = animationVector;
            this.durationNanos$delegate = SnapshotLongStateKt.mutableLongStateOf(getAnimation().getDurationNanos());
            Float f = (Float) VisibilityThresholdsKt.VisibilityThresholdMap.get(twoWayConverter);
            if (f != null) {
                float floatValue = f.floatValue();
                AnimationVector animationVector2 = (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj);
                int size$animation_core_release = animationVector2.getSize$animation_core_release();
                for (int i = 0; i < size$animation_core_release; i++) {
                    animationVector2.set$animation_core_release(i, floatValue);
                }
                obj2 = ((TwoWayConverterImpl) this.typeConverter).convertFromVector.invoke(animationVector2);
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, obj2, 3);
        }

        public final TargetBasedAnimation getAnimation() {
            return (TargetBasedAnimation) ((SnapshotMutableStateImpl) this.animation$delegate).getValue();
        }

        public final float getResetSnapValue$animation_core_release() {
            return ((SnapshotMutableFloatStateImpl) this.resetSnapValue$delegate).getFloatValue();
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return ((SnapshotMutableStateImpl) this.value$delegate).getValue();
        }

        public final void seekTo$animation_core_release() {
            if (getResetSnapValue$animation_core_release() == -1.0f) {
                this.isSeeking = true;
                boolean areEqual = Intrinsics.areEqual(getAnimation().mutableTargetValue, getAnimation().mutableInitialValue);
                MutableState mutableState = this.value$delegate;
                if (areEqual) {
                    ((SnapshotMutableStateImpl) mutableState).setValue(getAnimation().mutableTargetValue);
                } else {
                    ((SnapshotMutableStateImpl) mutableState).setValue(getAnimation().getValueFromNanos(0L));
                    this.velocityVector = getAnimation().getVelocityVectorFromNanos(0L);
                }
            }
        }

        public final String toString() {
            return "current value: " + ((SnapshotMutableStateImpl) this.value$delegate).getValue() + ", target: " + ((SnapshotMutableStateImpl) this.targetValue$delegate).getValue() + ", spec: " + ((FiniteAnimationSpec) ((SnapshotMutableStateImpl) this.animationSpec$delegate).getValue());
        }

        public final void updateAnimation(Object obj, boolean z) {
            SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) this.targetValue$delegate;
            boolean areEqual = Intrinsics.areEqual((Object) null, snapshotMutableStateImpl.getValue());
            MutableLongState mutableLongState = this.durationNanos$delegate;
            MutableState mutableState = this.animation$delegate;
            FiniteAnimationSpec finiteAnimationSpec = this.interruptionSpec;
            if (areEqual) {
                ((SnapshotMutableStateImpl) mutableState).setValue(new TargetBasedAnimation(finiteAnimationSpec, this.typeConverter, obj, obj, this.velocityVector.newVector$animation_core_release()));
                this.useOnlyInitialValue = true;
                ((SnapshotMutableLongStateImpl) mutableLongState).setLongValue(getAnimation().getDurationNanos());
                return;
            }
            MutableState mutableState2 = this.animationSpec$delegate;
            if (!z || this.isSeeking) {
                finiteAnimationSpec = (FiniteAnimationSpec) ((SnapshotMutableStateImpl) mutableState2).getValue();
            } else if (((FiniteAnimationSpec) ((SnapshotMutableStateImpl) mutableState2).getValue()) instanceof SpringSpec) {
                finiteAnimationSpec = (FiniteAnimationSpec) ((SnapshotMutableStateImpl) mutableState2).getValue();
            }
            Transition transition = Transition.this;
            long j = 0;
            ((SnapshotMutableStateImpl) mutableState).setValue(new TargetBasedAnimation(transition.getPlayTimeNanos() <= 0 ? finiteAnimationSpec : new StartDelayAnimationSpec(finiteAnimationSpec, transition.getPlayTimeNanos()), this.typeConverter, obj, snapshotMutableStateImpl.getValue(), this.velocityVector));
            ((SnapshotMutableLongStateImpl) mutableLongState).setLongValue(getAnimation().getDurationNanos());
            this.useOnlyInitialValue = false;
            Boolean bool = Boolean.TRUE;
            MutableState mutableState3 = transition.updateChildrenNeeded$delegate;
            ((SnapshotMutableStateImpl) mutableState3).setValue(bool);
            if (transition.isSeeking()) {
                SnapshotStateList snapshotStateList = transition._animations;
                int size = snapshotStateList.size();
                for (int i = 0; i < size; i++) {
                    TransitionAnimationState transitionAnimationState = (TransitionAnimationState) snapshotStateList.get(i);
                    j = Math.max(j, ((SnapshotMutableLongStateImpl) transitionAnimationState.durationNanos$delegate).getLongValue());
                    transitionAnimationState.seekTo$animation_core_release();
                }
                ((SnapshotMutableStateImpl) mutableState3).setValue(Boolean.FALSE);
            }
        }

        public final void updateInitialAndTargetValue$animation_core_release(Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec) {
            ((SnapshotMutableStateImpl) this.targetValue$delegate).setValue(obj2);
            ((SnapshotMutableStateImpl) this.animationSpec$delegate).setValue(finiteAnimationSpec);
            if (Intrinsics.areEqual(getAnimation().mutableInitialValue, obj) && Intrinsics.areEqual(getAnimation().mutableTargetValue, obj2)) {
                return;
            }
            updateAnimation(obj, false);
        }

        public final void updateTargetValue$animation_core_release(Object obj, FiniteAnimationSpec finiteAnimationSpec) {
            if (this.useOnlyInitialValue && Intrinsics.areEqual(obj, (Object) null)) {
                return;
            }
            MutableState mutableState = this.targetValue$delegate;
            if (Intrinsics.areEqual(((SnapshotMutableStateImpl) mutableState).getValue(), obj) && getResetSnapValue$animation_core_release() == -1.0f) {
                return;
            }
            ((SnapshotMutableStateImpl) mutableState).setValue(obj);
            ((SnapshotMutableStateImpl) this.animationSpec$delegate).setValue(finiteAnimationSpec);
            float resetSnapValue$animation_core_release = getResetSnapValue$animation_core_release();
            MutableState mutableState2 = this.value$delegate;
            Object value = resetSnapValue$animation_core_release == -3.0f ? obj : ((SnapshotMutableStateImpl) mutableState2).getValue();
            MutableState mutableState3 = this.isFinished$delegate;
            updateAnimation(value, !((Boolean) ((SnapshotMutableStateImpl) mutableState3).getValue()).booleanValue());
            ((SnapshotMutableStateImpl) mutableState3).setValue(Boolean.valueOf(getResetSnapValue$animation_core_release() == -3.0f));
            if (getResetSnapValue$animation_core_release() >= 0.0f) {
                ((SnapshotMutableStateImpl) mutableState2).setValue(getAnimation().getValueFromNanos((long) (getResetSnapValue$animation_core_release() * getAnimation().getDurationNanos())));
            } else if (getResetSnapValue$animation_core_release() == -3.0f) {
                ((SnapshotMutableStateImpl) mutableState2).setValue(obj);
            }
            this.useOnlyInitialValue = false;
            ((SnapshotMutableFloatStateImpl) this.resetSnapValue$delegate).setFloatValue(-1.0f);
        }
    }

    public Transition(MutableTransitionState mutableTransitionState, Transition transition, String str) {
        this.transitionState = mutableTransitionState;
        this.parentTransition = transition;
        this.label = str;
        Boolean bool = Boolean.FALSE;
        this.updateChildrenNeeded$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        this._animations = new SnapshotStateList();
        this._transitions = new SnapshotStateList();
        this.isSeeking$delegate = SnapshotStateKt.mutableStateOf$default(bool);
        SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.animation.core.Transition$totalDurationNanos$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(Transition.this.calculateTotalDurationNanos());
            }
        });
        mutableTransitionState.getClass();
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007c, code lost:
    
        if (((java.lang.Boolean) ((androidx.compose.runtime.SnapshotMutableStateImpl) r9.updateChildrenNeeded$delegate).getValue()).booleanValue() == false) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void animateTo$animation_core_release(final java.lang.Object r10, androidx.compose.runtime.Composer r11, final int r12) {
        /*
            r9 = this;
            androidx.compose.runtime.ComposerImpl r11 = (androidx.compose.runtime.ComposerImpl) r11
            r0 = -1493585151(0xffffffffa6f9b301, float:-1.7326365E-15)
            r11.startRestartGroup(r0)
            r0 = r12 & 6
            if (r0 != 0) goto L20
            r0 = r12 & 8
            if (r0 != 0) goto L15
            boolean r0 = r11.changed(r10)
            goto L19
        L15:
            boolean r0 = r11.changedInstance(r10)
        L19:
            if (r0 == 0) goto L1d
            r0 = 4
            goto L1e
        L1d:
            r0 = 2
        L1e:
            r0 = r0 | r12
            goto L21
        L20:
            r0 = r12
        L21:
            r1 = r12 & 48
            r2 = 32
            if (r1 != 0) goto L32
            boolean r1 = r11.changed(r9)
            if (r1 == 0) goto L2f
            r1 = r2
            goto L31
        L2f:
            r1 = 16
        L31:
            r0 = r0 | r1
        L32:
            r1 = r0 & 19
            r3 = 18
            if (r1 != r3) goto L44
            boolean r1 = r11.getSkipping()
            if (r1 != 0) goto L3f
            goto L44
        L3f:
            r11.skipToGroupEnd()
            goto Lb4
        L44:
            androidx.compose.runtime.OpaqueKey r1 = androidx.compose.runtime.ComposerKt.invocation
            boolean r1 = r9.isSeeking()
            if (r1 != 0) goto Lb4
            r9.updateTarget$animation_core_release(r10)
            java.lang.Object r1 = r9.getCurrentState()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r1)
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L7e
            androidx.compose.runtime.MutableLongState r1 = r9.startTimeNanos$delegate
            androidx.compose.runtime.SnapshotMutableLongStateImpl r1 = (androidx.compose.runtime.SnapshotMutableLongStateImpl) r1
            long r5 = r1.getLongValue()
            r7 = -9223372036854775808
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 == 0) goto L6b
            r1 = r4
            goto L6c
        L6b:
            r1 = r3
        L6c:
            if (r1 != 0) goto L7e
            androidx.compose.runtime.MutableState r1 = r9.updateChildrenNeeded$delegate
            androidx.compose.runtime.SnapshotMutableStateImpl r1 = (androidx.compose.runtime.SnapshotMutableStateImpl) r1
            java.lang.Object r1 = r1.getValue()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto Lb4
        L7e:
            java.lang.Object r1 = r11.rememberedValue()
            androidx.compose.runtime.Composer$Companion$Empty$1 r5 = androidx.compose.runtime.Composer.Companion.Empty
            if (r1 != r5) goto L90
            kotlin.coroutines.EmptyCoroutineContext r1 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlinx.coroutines.internal.ContextScope r1 = androidx.compose.runtime.EffectsKt.createCompositionCoroutineScope(r1, r11)
            androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller r1 = androidx.activity.compose.PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(r1, r11)
        L90:
            androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller r1 = (androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller) r1
            kotlinx.coroutines.internal.ContextScope r1 = r1.coroutineScope
            boolean r6 = r11.changedInstance(r1)
            r0 = r0 & 112(0x70, float:1.57E-43)
            if (r0 != r2) goto L9d
            r3 = r4
        L9d:
            r0 = r6 | r3
            java.lang.Object r2 = r11.rememberedValue()
            if (r0 != 0) goto La7
            if (r2 != r5) goto Laf
        La7:
            androidx.compose.animation.core.Transition$animateTo$1$1 r2 = new androidx.compose.animation.core.Transition$animateTo$1$1
            r2.<init>()
            r11.updateRememberedValue(r2)
        Laf:
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            androidx.compose.runtime.EffectsKt.DisposableEffect(r1, r9, r2, r11)
        Lb4:
            androidx.compose.runtime.RecomposeScopeImpl r11 = r11.endRestartGroup()
            if (r11 == 0) goto Lc1
            androidx.compose.animation.core.Transition$animateTo$2 r0 = new androidx.compose.animation.core.Transition$animateTo$2
            r0.<init>()
            r11.block = r0
        Lc1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.Transition.animateTo$animation_core_release(java.lang.Object, androidx.compose.runtime.Composer, int):void");
    }

    public final long calculateTotalDurationNanos() {
        SnapshotStateList snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            j = Math.max(j, ((SnapshotMutableLongStateImpl) ((TransitionAnimationState) snapshotStateList.get(i)).durationNanos$delegate).getLongValue());
        }
        SnapshotStateList snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            j = Math.max(j, ((Transition) snapshotStateList2.get(i2)).calculateTotalDurationNanos());
        }
        return j;
    }

    public final Object getCurrentState() {
        return ((SnapshotMutableStateImpl) this.transitionState.currentState$delegate).getValue();
    }

    public final boolean getHasInitialValueAnimations() {
        SnapshotStateList snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).getClass();
        }
        SnapshotStateList snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (((Transition) snapshotStateList2.get(i2)).getHasInitialValueAnimations()) {
                return true;
            }
        }
        return false;
    }

    public final long getPlayTimeNanos() {
        Transition transition = this.parentTransition;
        return transition != null ? transition.getPlayTimeNanos() : ((SnapshotMutableLongStateImpl) this._playTimeNanos$delegate).getLongValue();
    }

    public final Segment getSegment() {
        return (Segment) ((SnapshotMutableStateImpl) this.segment$delegate).getValue();
    }

    public final Object getTargetState() {
        return ((SnapshotMutableStateImpl) this.targetState$delegate).getValue();
    }

    public final boolean isSeeking() {
        return ((Boolean) ((SnapshotMutableStateImpl) this.isSeeking$delegate).getValue()).booleanValue();
    }

    public final void onFrame$animation_core_release(long j, boolean z) {
        MutableLongState mutableLongState = this.startTimeNanos$delegate;
        long longValue = ((SnapshotMutableLongStateImpl) mutableLongState).getLongValue();
        MutableTransitionState mutableTransitionState = this.transitionState;
        if (longValue == Long.MIN_VALUE) {
            ((SnapshotMutableLongStateImpl) mutableLongState).setLongValue(j);
            ((SnapshotMutableStateImpl) mutableTransitionState.isRunning$delegate).setValue(Boolean.TRUE);
        } else if (!((Boolean) ((SnapshotMutableStateImpl) mutableTransitionState.isRunning$delegate).getValue()).booleanValue()) {
            ((SnapshotMutableStateImpl) mutableTransitionState.isRunning$delegate).setValue(Boolean.TRUE);
        }
        ((SnapshotMutableStateImpl) this.updateChildrenNeeded$delegate).setValue(Boolean.FALSE);
        SnapshotStateList snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        boolean z2 = true;
        for (int i = 0; i < size; i++) {
            TransitionAnimationState transitionAnimationState = (TransitionAnimationState) snapshotStateList.get(i);
            boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) transitionAnimationState.isFinished$delegate).getValue()).booleanValue();
            MutableState mutableState = transitionAnimationState.isFinished$delegate;
            if (!booleanValue) {
                long durationNanos = z ? transitionAnimationState.getAnimation().getDurationNanos() : j;
                ((SnapshotMutableStateImpl) transitionAnimationState.value$delegate).setValue(transitionAnimationState.getAnimation().getValueFromNanos(durationNanos));
                transitionAnimationState.velocityVector = transitionAnimationState.getAnimation().getVelocityVectorFromNanos(durationNanos);
                if (transitionAnimationState.getAnimation().isFinishedFromNanos(durationNanos)) {
                    ((SnapshotMutableStateImpl) mutableState).setValue(Boolean.TRUE);
                }
            }
            if (!((Boolean) ((SnapshotMutableStateImpl) mutableState).getValue()).booleanValue()) {
                z2 = false;
            }
        }
        SnapshotStateList snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Transition transition = (Transition) snapshotStateList2.get(i2);
            if (!Intrinsics.areEqual(((SnapshotMutableStateImpl) transition.targetState$delegate).getValue(), transition.getCurrentState())) {
                transition.onFrame$animation_core_release(j, z);
            }
            if (!Intrinsics.areEqual(((SnapshotMutableStateImpl) transition.targetState$delegate).getValue(), transition.getCurrentState())) {
                z2 = false;
            }
        }
        if (z2) {
            onTransitionEnd$animation_core_release();
        }
    }

    public final void onTransitionEnd$animation_core_release() {
        ((SnapshotMutableLongStateImpl) this.startTimeNanos$delegate).setLongValue(Long.MIN_VALUE);
        MutableTransitionState mutableTransitionState = this.transitionState;
        if (mutableTransitionState != null) {
            ((SnapshotMutableStateImpl) mutableTransitionState.currentState$delegate).setValue(((SnapshotMutableStateImpl) this.targetState$delegate).getValue());
        }
        if (this.parentTransition == null) {
            ((SnapshotMutableLongStateImpl) this._playTimeNanos$delegate).setLongValue(0L);
        }
        ((SnapshotMutableStateImpl) mutableTransitionState.isRunning$delegate).setValue(Boolean.FALSE);
        SnapshotStateList snapshotStateList = this._transitions;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((Transition) snapshotStateList.get(i)).onTransitionEnd$animation_core_release();
        }
    }

    public final void resetAnimations() {
        SnapshotStateList snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((SnapshotMutableFloatStateImpl) ((TransitionAnimationState) snapshotStateList.get(i)).resetSnapValue$delegate).setFloatValue(-2.0f);
        }
        SnapshotStateList snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((Transition) snapshotStateList2.get(i2)).resetAnimations();
        }
    }

    public final void seek(Object obj, Object obj2) {
        ((SnapshotMutableLongStateImpl) this.startTimeNanos$delegate).setLongValue(Long.MIN_VALUE);
        MutableTransitionState mutableTransitionState = this.transitionState;
        ((SnapshotMutableStateImpl) mutableTransitionState.isRunning$delegate).setValue(Boolean.FALSE);
        boolean isSeeking = isSeeking();
        MutableState mutableState = this.targetState$delegate;
        if (!isSeeking || !Intrinsics.areEqual(getCurrentState(), obj) || !Intrinsics.areEqual(((SnapshotMutableStateImpl) mutableState).getValue(), obj2)) {
            if (!Intrinsics.areEqual(getCurrentState(), obj)) {
                ((SnapshotMutableStateImpl) mutableTransitionState.currentState$delegate).setValue(obj);
            }
            ((SnapshotMutableStateImpl) mutableState).setValue(obj2);
            ((SnapshotMutableStateImpl) this.isSeeking$delegate).setValue(Boolean.TRUE);
            ((SnapshotMutableStateImpl) this.segment$delegate).setValue(new SegmentImpl(obj, obj2));
        }
        SnapshotStateList snapshotStateList = this._transitions;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            Transition transition = (Transition) snapshotStateList.get(i);
            if (transition.isSeeking()) {
                transition.seek(transition.getCurrentState(), ((SnapshotMutableStateImpl) transition.targetState$delegate).getValue());
            }
        }
        SnapshotStateList snapshotStateList2 = this._animations;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((TransitionAnimationState) snapshotStateList2.get(i2)).seekTo$animation_core_release();
        }
    }

    public final String toString() {
        SnapshotStateList snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        String str = "Transition animation values: ";
        for (int i = 0; i < size; i++) {
            str = str + ((TransitionAnimationState) snapshotStateList.get(i)) + ", ";
        }
        return str;
    }

    public final void updateTarget$animation_core_release(Object obj) {
        MutableState mutableState = this.targetState$delegate;
        SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) mutableState;
        if (Intrinsics.areEqual(snapshotMutableStateImpl.getValue(), obj)) {
            return;
        }
        ((SnapshotMutableStateImpl) this.segment$delegate).setValue(new SegmentImpl(snapshotMutableStateImpl.getValue(), obj));
        if (!Intrinsics.areEqual(getCurrentState(), snapshotMutableStateImpl.getValue())) {
            ((SnapshotMutableStateImpl) this.transitionState.currentState$delegate).setValue(snapshotMutableStateImpl.getValue());
        }
        ((SnapshotMutableStateImpl) mutableState).setValue(obj);
        if (((SnapshotMutableLongStateImpl) this.startTimeNanos$delegate).getLongValue() == Long.MIN_VALUE) {
            ((SnapshotMutableStateImpl) this.updateChildrenNeeded$delegate).setValue(Boolean.TRUE);
        }
        resetAnimations();
    }
}
