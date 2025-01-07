package com.android.systemui.authentication.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationInteractor$special$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ AuthenticationInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthenticationInteractor$special$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public AuthenticationInteractor$special$$inlined$map$2$2(FlowCollector flowCollector, AuthenticationInteractor authenticationInteractor) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = authenticationInteractor;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L55
            if (r2 == r5) goto L43
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lb1
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L9d
        L43:
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            java.lang.Object r2 = r0.L$0
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2 r2 = (com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r9
            r9 = r8
            r8 = r2
            r2 = r7
            goto L73
        L55:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r10 = r8.this$0
            com.android.systemui.authentication.data.repository.AuthenticationRepositoryImpl r10 = r10.repository
            r0.L$0 = r8
            kotlinx.coroutines.flow.FlowCollector r2 = r8.$this_unsafeFlow
            r0.L$1 = r2
            r0.I$0 = r9
            r0.label = r5
            java.lang.Object r10 = r10.getMaxFailedUnlockAttemptsForWipe(r0)
            if (r10 != r1) goto L73
            return r1
        L73:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            if (r10 != 0) goto L7d
        L7b:
            r4 = r6
            goto La4
        L7d:
            r5 = 0
            int r10 = r10 - r9
            int r10 = java.lang.Math.max(r5, r10)
            r5 = 5
            if (r10 < r5) goto L87
            goto L7b
        L87:
            com.android.systemui.authentication.domain.interactor.AuthenticationInteractor r8 = r8.this$0
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r9
            r0.I$1 = r10
            r0.label = r4
            java.lang.Object r8 = com.android.systemui.authentication.domain.interactor.AuthenticationInteractor.access$getWipeTarget(r8, r0)
            if (r8 != r1) goto L9a
            return r1
        L9a:
            r7 = r10
            r10 = r8
            r8 = r7
        L9d:
            com.android.systemui.authentication.shared.model.AuthenticationWipeModel$WipeTarget r10 = (com.android.systemui.authentication.shared.model.AuthenticationWipeModel.WipeTarget) r10
            com.android.systemui.authentication.shared.model.AuthenticationWipeModel r4 = new com.android.systemui.authentication.shared.model.AuthenticationWipeModel
            r4.<init>(r10, r9, r8)
        La4:
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r8 = r2.emit(r4, r0)
            if (r8 != r1) goto Lb1
            return r1
        Lb1:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.authentication.domain.interactor.AuthenticationInteractor$special$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
