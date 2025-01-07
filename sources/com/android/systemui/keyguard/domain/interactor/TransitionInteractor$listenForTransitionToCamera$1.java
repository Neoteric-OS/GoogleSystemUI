package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TransitionInteractor$listenForTransitionToCamera$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardInteractor $keyguardInteractor;
    int label;
    final /* synthetic */ TransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ TransitionInteractor this$0;

        public AnonymousClass1(TransitionInteractor transitionInteractor) {
            this.this$0 = transitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit$1(continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0056  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit$1(kotlin.coroutines.Continuation r10) {
            /*
                r9 = this;
                boolean r0 = r10 instanceof com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1$emit$1
                if (r0 == 0) goto L14
                r0 = r10
                com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1$emit$1 r0 = (com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L14
                int r1 = r1 - r2
                r0.label = r1
            L12:
                r6 = r0
                goto L1a
            L14:
                com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1$emit$1 r0 = new com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1$emit$1
                r0.<init>(r9, r10)
                goto L12
            L1a:
                java.lang.Object r10 = r6.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r6.label
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L3e
                if (r1 == r3) goto L36
                if (r1 != r2) goto L2e
                kotlin.ResultKt.throwOnFailure(r10)
                goto L6d
            L2e:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L36:
                java.lang.Object r9 = r6.L$0
                com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1$1 r9 = (com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1.AnonymousClass1) r9
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4e
            L3e:
                kotlin.ResultKt.throwOnFailure(r10)
                r6.L$0 = r9
                r6.label = r3
                com.android.systemui.keyguard.domain.interactor.TransitionInteractor r10 = r9.this$0
                java.lang.Object r10 = r10.maybeHandleInsecurePowerGesture(r6)
                if (r10 != r0) goto L4e
                return r0
            L4e:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 != 0) goto L6d
                com.android.systemui.keyguard.domain.interactor.TransitionInteractor r1 = r9.this$0
                com.android.systemui.keyguard.shared.model.KeyguardState r9 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled r4 = com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled.RESET
                r10 = 0
                r6.L$0 = r10
                r6.label = r2
                java.lang.String r5 = "keyguardInteractor.onCameraLaunchDetected"
                r7 = 2
                r3 = 0
                r2 = r9
                java.lang.Object r9 = com.android.systemui.keyguard.domain.interactor.TransitionInteractor.startTransitionTo$default(r1, r2, r3, r4, r5, r6, r7)
                if (r9 != r0) goto L6d
                return r0
            L6d:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.TransitionInteractor$listenForTransitionToCamera$1.AnonymousClass1.emit$1(kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransitionInteractor$listenForTransitionToCamera$1(TransitionInteractor transitionInteractor, KeyguardInteractor keyguardInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = transitionInteractor;
        this.$keyguardInteractor = keyguardInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TransitionInteractor$listenForTransitionToCamera$1(this.this$0, this.$keyguardInteractor, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TransitionInteractor$listenForTransitionToCamera$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TransitionInteractor transitionInteractor = this.this$0;
            KeyguardInteractor$special$$inlined$map$2 keyguardInteractor$special$$inlined$map$2 = this.$keyguardInteractor.onCameraLaunchDetected;
            transitionInteractor.getClass();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
            this.label = 1;
            Object collect = keyguardInteractor$special$$inlined$map$2.collect(new TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1.AnonymousClass2(anonymousClass1, transitionInteractor), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
