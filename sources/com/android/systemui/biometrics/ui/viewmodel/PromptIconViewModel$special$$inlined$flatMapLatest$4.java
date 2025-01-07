package com.android.systemui.biometrics.ui.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel$special$$inlined$flatMapLatest$4 extends SuspendLambda implements Function3 {
    final /* synthetic */ PromptViewModel $promptViewModel$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$special$$inlined$flatMapLatest$4(PromptViewModel promptViewModel, Continuation continuation) {
        super(3, continuation);
        this.$promptViewModel$inlined = promptViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptIconViewModel$special$$inlined$flatMapLatest$4 promptIconViewModel$special$$inlined$flatMapLatest$4 = new PromptIconViewModel$special$$inlined$flatMapLatest$4(this.$promptViewModel$inlined, (Continuation) obj3);
        promptIconViewModel$special$$inlined$flatMapLatest$4.L$0 = (FlowCollector) obj;
        promptIconViewModel$special$$inlined$flatMapLatest$4.L$1 = obj2;
        return promptIconViewModel$special$$inlined$flatMapLatest$4.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0047 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            kotlin.ResultKt.throwOnFailure(r5)
            goto L48
        Ld:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L15:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r1 = r4.L$1
            com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$AuthType r1 = (com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel.AuthType) r1
            int r1 = r1.ordinal()
            if (r1 == 0) goto L37
            if (r1 == r2) goto L32
            r3 = 2
            if (r1 != r3) goto L2c
            goto L37
        L2c:
            kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
            r4.<init>()
            throw r4
        L32:
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel r1 = r4.$promptViewModel$inlined
            kotlinx.coroutines.flow.ReadonlyStateFlow r1 = r1.isAuthenticating
            goto L3f
        L37:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 r3 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            r3.<init>(r1)
            r1 = r3
        L3f:
            r4.label = r2
            java.lang.Object r4 = kotlinx.coroutines.flow.FlowKt.emitAll(r5, r1, r4)
            if (r4 != r0) goto L48
            return r0
        L48:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$special$$inlined$flatMapLatest$4.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
