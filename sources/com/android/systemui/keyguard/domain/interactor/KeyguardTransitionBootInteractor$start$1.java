package com.android.systemui.keyguard.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionBootInteractor$start$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ KeyguardTransitionBootInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTransitionBootInteractor$start$1(KeyguardTransitionBootInteractor keyguardTransitionBootInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardTransitionBootInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardTransitionBootInteractor$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardTransitionBootInteractor$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r11.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 3
            r4 = 2
            if (r1 == 0) goto L27
            r5 = 1
            if (r1 == r5) goto L22
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L12
            goto L22
        L12:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L1a:
            java.lang.Object r1 = r11.L$0
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r1 = (com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L5b
        L22:
            kotlin.ResultKt.throwOnFailure(r12)
            goto La6
        L27:
            kotlin.ResultKt.throwOnFailure(r12)
            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor r12 = r11.this$0
            com.android.systemui.keyguard.domain.interactor.InternalKeyguardTransitionInteractor r12 = r12.internalTransitionInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r12 = r12.currentTransitionInfoInternal
            java.lang.Object r12 = r12.getValue()
            com.android.systemui.keyguard.shared.model.TransitionInfo r12 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r12
            com.android.systemui.keyguard.shared.model.KeyguardState r12 = r12.from
            com.android.systemui.keyguard.shared.model.KeyguardState r1 = com.android.systemui.keyguard.shared.model.KeyguardState.OFF
            if (r12 == r1) goto L44
            java.lang.String r11 = "KeyguardTransitionInteractor"
            java.lang.String r12 = "showLockscreenOnBoot emitted, but we've already transitioned to a state other than OFF. We'll respect that transition, but this should not happen."
            android.util.Log.e(r11, r12)
            goto La6
        L44:
            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor r12 = r11.this$0
            com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r1 = r12.repository
            kotlin.Lazy r12 = r12.showLockscreenOnBoot$delegate
            java.lang.Object r12 = r12.getValue()
            kotlinx.coroutines.flow.Flow r12 = (kotlinx.coroutines.flow.Flow) r12
            r11.L$0 = r1
            r11.label = r4
            java.lang.Object r12 = kotlinx.coroutines.flow.FlowKt.first(r12, r11)
            if (r12 != r0) goto L5b
            return r0
        L5b:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L66
            com.android.systemui.keyguard.shared.model.KeyguardState r12 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
            goto L68
        L66:
            com.android.systemui.keyguard.shared.model.KeyguardState r12 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
        L68:
            r4 = 0
            r11.L$0 = r4
            r11.label = r3
            r1.getClass()
            com.android.systemui.keyguard.shared.model.TransitionInfo r11 = new com.android.systemui.keyguard.shared.model.TransitionInfo
            com.android.systemui.keyguard.shared.model.KeyguardState r3 = com.android.systemui.keyguard.shared.model.KeyguardState.OFF
            java.lang.String r5 = "KeyguardTransitionRepository(boot)"
            r11.<init>(r5, r3, r12, r4)
            kotlinx.coroutines.flow.StateFlowImpl r5 = r1._currentTransitionInfo
            r5.getClass()
            r5.updateState(r4, r11)
            com.android.systemui.keyguard.shared.model.TransitionStep r11 = new com.android.systemui.keyguard.shared.model.TransitionStep
            com.android.systemui.keyguard.shared.model.TransitionState r8 = com.android.systemui.keyguard.shared.model.TransitionState.STARTED
            java.lang.String r9 = "KeyguardTransitionRepository(boot)"
            r7 = 0
            r4 = r11
            r5 = r3
            r6 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r10 = 0
            r1.emitTransition(r11, r10)
            com.android.systemui.keyguard.shared.model.TransitionStep r11 = new com.android.systemui.keyguard.shared.model.TransitionStep
            com.android.systemui.keyguard.shared.model.TransitionState r8 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
            java.lang.String r9 = "KeyguardTransitionRepository(boot)"
            r7 = 1065353216(0x3f800000, float:1.0)
            r4 = r11
            r5 = r3
            r6 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r1.emitTransition(r11, r10)
            if (r2 != r0) goto La6
            return r0
        La6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$start$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
