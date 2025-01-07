package com.android.systemui.qs.composefragment;

import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutState;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSFragmentComposeKt$synchronizeQsState$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Flow $expansion;
    final /* synthetic */ MutableSceneTransitionLayoutState $state;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.composefragment.QSFragmentComposeKt$synchronizeQsState$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineScope $animationScope;
        final /* synthetic */ Ref$ObjectRef $currentTransition;
        final /* synthetic */ MutableSceneTransitionLayoutState $state;
        /* synthetic */ float F$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Ref$ObjectRef ref$ObjectRef, MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState, CoroutineScope coroutineScope, Continuation continuation) {
            super(2, continuation);
            this.$currentTransition = ref$ObjectRef;
            this.$state = mutableSceneTransitionLayoutState;
            this.$animationScope = coroutineScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$currentTransition, this.$state, this.$animationScope, continuation);
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
            Unit unit = Unit.INSTANCE;
            if (f == 0.0f) {
                MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState = this.$state;
                Ref$ObjectRef ref$ObjectRef = this.$currentTransition;
                MutableSceneTransitionLayoutState.snapToScene$default(mutableSceneTransitionLayoutState, SceneKeys.QuickQuickSettings);
                ref$ObjectRef.element = null;
            } else if (f == 1.0f) {
                MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState2 = this.$state;
                Ref$ObjectRef ref$ObjectRef2 = this.$currentTransition;
                MutableSceneTransitionLayoutState.snapToScene$default(mutableSceneTransitionLayoutState2, SceneKeys.QuickSettings);
                ref$ObjectRef2.element = null;
            } else {
                ExpansionTransition expansionTransition = (ExpansionTransition) this.$currentTransition.element;
                if (expansionTransition != null) {
                    ((SnapshotMutableFloatStateImpl) expansionTransition.progress$delegate).setFloatValue(f);
                    return unit;
                }
                ExpansionTransition expansionTransition2 = new ExpansionTransition(f);
                this.$currentTransition.element = expansionTransition2;
                ((MutableSceneTransitionLayoutStateImpl) this.$state).startTransitionImmediately(this.$animationScope, expansionTransition2, true);
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSFragmentComposeKt$synchronizeQsState$2(Flow flow, MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState, Continuation continuation) {
        super(2, continuation);
        this.$expansion = flow;
        this.$state = mutableSceneTransitionLayoutState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        QSFragmentComposeKt$synchronizeQsState$2 qSFragmentComposeKt$synchronizeQsState$2 = new QSFragmentComposeKt$synchronizeQsState$2(this.$expansion, this.$state, continuation);
        qSFragmentComposeKt$synchronizeQsState$2.L$0 = obj;
        return qSFragmentComposeKt$synchronizeQsState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((QSFragmentComposeKt$synchronizeQsState$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Flow flow = this.$expansion;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(ref$ObjectRef, this.$state, coroutineScope, null);
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
        return Unit.INSTANCE;
    }
}
