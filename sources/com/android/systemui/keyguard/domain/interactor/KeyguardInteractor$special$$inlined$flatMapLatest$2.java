package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.power.domain.interactor.PowerInteractor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardInteractor$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ PowerInteractor $powerInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ KeyguardInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardInteractor$special$$inlined$flatMapLatest$2(Continuation continuation, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor) {
        super(3, continuation);
        this.this$0 = keyguardInteractor;
        this.$powerInteractor$inlined = powerInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardInteractor$special$$inlined$flatMapLatest$2 keyguardInteractor$special$$inlined$flatMapLatest$2 = new KeyguardInteractor$special$$inlined$flatMapLatest$2((Continuation) obj3, this.this$0, this.$powerInteractor$inlined);
        keyguardInteractor$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        keyguardInteractor$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return keyguardInteractor$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0074 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L21
            if (r1 == r4) goto L19
            if (r1 != r3) goto L11
            kotlin.ResultKt.throwOnFailure(r8)
            goto L75
        L11:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L19:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L52
        L21:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            java.lang.Object r8 = r7.L$1
            com.android.systemui.keyguard.shared.model.DozeTransitionModel r8 = (com.android.systemui.keyguard.shared.model.DozeTransitionModel) r8
            com.android.systemui.keyguard.shared.model.DozeStateModel$Companion r5 = com.android.systemui.keyguard.shared.model.DozeStateModel.Companion
            com.android.systemui.keyguard.shared.model.DozeStateModel r8 = r8.to
            r5.getClass()
            com.android.systemui.keyguard.shared.model.DozeStateModel r5 = com.android.systemui.keyguard.shared.model.DozeStateModel.UNINITIALIZED
            if (r8 == r5) goto L45
            com.android.systemui.keyguard.shared.model.DozeStateModel r5 = com.android.systemui.keyguard.shared.model.DozeStateModel.FINISH
            if (r8 != r5) goto L3d
            goto L45
        L3d:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r4 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r4.<init>(r8)
            goto L6a
        L45:
            r7.L$0 = r1
            r7.label = r4
            r4 = 500(0x1f4, double:2.47E-321)
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r4, r7)
            if (r8 != r0) goto L52
            return r0
        L52:
            com.android.systemui.keyguard.domain.interactor.KeyguardInteractor r8 = r7.this$0
            kotlinx.coroutines.flow.ReadonlyStateFlow r8 = r8.isDreaming
            com.android.systemui.power.domain.interactor.PowerInteractor r4 = r7.$powerInteractor$inlined
            kotlinx.coroutines.flow.DistinctFlowImpl r4 = r4.isAwake
            com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$isAbleToDream$1$1 r5 = new com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$isAbleToDream$1$1
            r6 = 3
            r5.<init>(r6, r2)
            kotlinx.coroutines.flow.SafeFlow r8 = com.android.systemui.util.kotlin.FlowKt.sample(r8, r4, r5)
            r4 = 50
            kotlinx.coroutines.flow.Flow r4 = kotlinx.coroutines.flow.FlowKt.debounce(r8, r4)
        L6a:
            r7.L$0 = r2
            r7.label = r3
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.emitAll(r1, r4, r7)
            if (r7 != r0) goto L75
            return r0
        L75:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardInteractor$special$$inlined$flatMapLatest$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
