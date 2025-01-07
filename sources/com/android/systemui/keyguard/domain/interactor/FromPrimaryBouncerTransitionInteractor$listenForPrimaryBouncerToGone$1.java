package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromPrimaryBouncerTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FromPrimaryBouncerTransitionInteractor this$0;

        public AnonymousClass2(FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor) {
            this.this$0 = fromPrimaryBouncerTransitionInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit(((Boolean) obj).booleanValue(), continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(boolean r9, kotlin.coroutines.Continuation r10) {
            /*
                r8 = this;
                boolean r9 = r10 instanceof com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2$emit$1
                if (r9 == 0) goto L14
                r9 = r10
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2$emit$1 r9 = (com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2$emit$1) r9
                int r0 = r9.label
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r0 & r1
                if (r2 == 0) goto L14
                int r0 = r0 - r1
                r9.label = r0
            L12:
                r5 = r9
                goto L1a
            L14:
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2$emit$1 r9 = new com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2$emit$1
                r9.<init>(r8, r10)
                goto L12
            L1a:
                java.lang.Object r9 = r5.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r10 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r0 = r5.label
                r1 = 1
                if (r0 == 0) goto L35
                if (r0 != r1) goto L2d
                java.lang.Object r8 = r5.L$0
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1$2 r8 = (com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1.AnonymousClass2) r8
                kotlin.ResultKt.throwOnFailure(r9)
                goto L70
            L2d:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L35:
                kotlin.ResultKt.throwOnFailure(r9)
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor r0 = r8.this$0
                com.android.keyguard.KeyguardSecurityModel r9 = r0.keyguardSecurityModel
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r2 = r0.selectedUserInteractor
                int r2 = r2.getSelectedUserId()
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r9 = r9.getSecurityMode(r2)
                com.android.keyguard.KeyguardSecurityModel$SecurityMode r2 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.Password
                if (r9 != r2) goto L4d
                long r2 = com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor.TO_GONE_SHORT_DURATION
                goto L4f
            L4d:
                long r2 = com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor.TO_GONE_DURATION
            L4f:
                com.android.systemui.keyguard.shared.model.KeyguardState r9 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
                android.animation.ValueAnimator r4 = r0.getDefaultAnimatorForTransitionsToState(r9)
                long r2 = kotlin.time.Duration.m1777getInWholeMillisecondsimpl(r2)
                r4.setDuration(r2)
                com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled r3 = com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled.RESET
                r5.L$0 = r8
                r5.label = r1
                r6 = 0
                r7 = 8
                r1 = r9
                r2 = r4
                r4 = r6
                r6 = r7
                java.lang.Object r9 = com.android.systemui.keyguard.domain.interactor.TransitionInteractor.startTransitionTo$default(r0, r1, r2, r3, r4, r5, r6)
                if (r9 != r10) goto L70
                return r10
            L70:
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor r8 = r8.this$0
                com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor.access$closeHubImmediatelyIfNeeded(r8)
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1.AnonymousClass2.emit(boolean, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1(FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromPrimaryBouncerTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor = this.this$0;
        MutableStateFlow mutableStateFlow = fromPrimaryBouncerTransitionInteractor.keyguardInteractor.isKeyguardGoingAway;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromPrimaryBouncerTransitionInteractor$listenForPrimaryBouncerToGone$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                return bool;
            }
        };
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(fromPrimaryBouncerTransitionInteractor);
        this.label = 1;
        ((StateFlowImpl) mutableStateFlow).collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass2, fromPrimaryBouncerTransitionInteractor, anonymousClass1), this);
        return coroutineSingletons;
    }
}
