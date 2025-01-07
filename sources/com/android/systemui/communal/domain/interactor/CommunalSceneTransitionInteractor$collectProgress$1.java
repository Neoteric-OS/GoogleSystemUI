package com.android.systemui.communal.domain.interactor;

import com.android.compose.animation.scene.ObservableTransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneTransitionInteractor$collectProgress$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ObservableTransitionState.Transition $transition;
    int label;
    final /* synthetic */ CommunalSceneTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$collectProgress$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ CommunalSceneTransitionInteractor this$0;

        public /* synthetic */ AnonymousClass1(CommunalSceneTransitionInteractor communalSceneTransitionInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = communalSceneTransitionInteractor;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
        
            if (r4 == kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L20;
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                int r0 = r4.$r8$classId
                switch(r0) {
                    case 0: goto L33;
                    default: goto L5;
                }
            L5:
                com.android.systemui.util.kotlin.WithPrev r5 = (com.android.systemui.util.kotlin.WithPrev) r5
                java.lang.Object r0 = r5.previousValue
                com.android.compose.animation.scene.ObservableTransitionState r0 = (com.android.compose.animation.scene.ObservableTransitionState) r0
                java.lang.Object r5 = r5.newValue
                com.android.compose.animation.scene.ObservableTransitionState r5 = (com.android.compose.animation.scene.ObservableTransitionState) r5
                boolean r1 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r4 = r4.this$0
                if (r1 == 0) goto L23
                com.android.compose.animation.scene.ObservableTransitionState$Idle r5 = (com.android.compose.animation.scene.ObservableTransitionState.Idle) r5
                java.lang.Object r4 = com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.access$handleIdle(r4, r0, r5, r6)
                kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                if (r4 != r5) goto L32
            L21:
                r2 = r4
                goto L32
            L23:
                boolean r1 = r5 instanceof com.android.compose.animation.scene.ObservableTransitionState.Transition
                if (r1 == 0) goto L32
                com.android.compose.animation.scene.ObservableTransitionState$Transition r5 = (com.android.compose.animation.scene.ObservableTransitionState.Transition) r5
                java.lang.Object r4 = com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor.access$handleTransition(r4, r0, r5, r6)
                kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                if (r4 != r5) goto L32
                goto L21
            L32:
                return r2
            L33:
                java.lang.Number r5 = (java.lang.Number) r5
                float r5 = r5.floatValue()
                com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor r4 = r4.this$0
                java.util.UUID r0 = r4.currentTransitionId
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                if (r0 != 0) goto L43
            L41:
                r4 = r1
                goto L56
            L43:
                r2 = 0
                r3 = 1065353216(0x3f800000, float:1.0)
                float r5 = kotlin.ranges.RangesKt.coerceIn(r5, r2, r3)
                com.android.systemui.keyguard.shared.model.TransitionState r2 = com.android.systemui.keyguard.shared.model.TransitionState.RUNNING
                com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r4 = r4.internalTransitionInteractor
                java.lang.Object r4 = r4.updateTransition(r0, r5, r2, r6)
                kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                if (r4 != r5) goto L41
            L56:
                kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                if (r4 != r5) goto L5b
                r1 = r4
            L5b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor$collectProgress$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalSceneTransitionInteractor$collectProgress$1(ObservableTransitionState.Transition transition, CommunalSceneTransitionInteractor communalSceneTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.$transition = transition;
        this.this$0 = communalSceneTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalSceneTransitionInteractor$collectProgress$1(this.$transition, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalSceneTransitionInteractor$collectProgress$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SafeFlow safeFlow = this.$transition.progress;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, 0);
            this.label = 1;
            if (safeFlow.collect(anonymousClass1, this) == coroutineSingletons) {
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
