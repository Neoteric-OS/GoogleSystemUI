package com.android.compose.animation.scene.transition;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import com.android.compose.animation.scene.SwipeAnimation;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SeekKt$animateProgress$2$collectionJob$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SwipeAnimation $animation;
    final /* synthetic */ AnimationSpec $cancelSpec;
    final /* synthetic */ AnimationSpec $commitSpec;
    final /* synthetic */ Flow $progress;
    final /* synthetic */ MutableSceneTransitionLayoutStateImpl $state;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.compose.animation.scene.transition.SeekKt$animateProgress$2$collectionJob$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ SwipeAnimation $animation;
        /* synthetic */ float F$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SwipeAnimation swipeAnimation, Continuation continuation) {
            super(2, continuation);
            this.$animation = swipeAnimation;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animation, continuation);
            anonymousClass1.F$0 = ((Number) obj).floatValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create(Float.valueOf(((Number) obj).floatValue()), (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            float f = this.F$0;
            SwipeAnimation swipeAnimation = this.$animation;
            if (f < 0.0f) {
                f = 0.0f;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            ((SnapshotMutableFloatStateImpl) swipeAnimation.dragOffset$delegate).setFloatValue(f);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeekKt$animateProgress$2$collectionJob$1(AnimationSpec animationSpec, AnimationSpec animationSpec2, MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, Continuation continuation, Flow flow) {
        super(2, continuation);
        this.$progress = flow;
        this.$animation = swipeAnimation;
        this.$commitSpec = animationSpec;
        this.$cancelSpec = animationSpec2;
        this.$state = mutableSceneTransitionLayoutStateImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        Flow flow = this.$progress;
        return new SeekKt$animateProgress$2$collectionJob$1(this.$commitSpec, this.$cancelSpec, this.$state, this.$animation, continuation, flow);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SeekKt$animateProgress$2$collectionJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.$progress;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animation, null);
                this.label = 1;
                if (FlowKt.collectLatest(flow, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = this.$state;
            SwipeAnimation swipeAnimation = this.$animation;
            SeekKt.access$animateProgress$animateOffset(mutableSceneTransitionLayoutStateImpl, swipeAnimation, swipeAnimation.toContent, this.$commitSpec);
        } catch (CancellationException unused) {
            MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl2 = this.$state;
            SwipeAnimation swipeAnimation2 = this.$animation;
            SeekKt.access$animateProgress$animateOffset(mutableSceneTransitionLayoutStateImpl2, swipeAnimation2, swipeAnimation2.fromContent, this.$cancelSpec);
        }
        return Unit.INSTANCE;
    }
}
