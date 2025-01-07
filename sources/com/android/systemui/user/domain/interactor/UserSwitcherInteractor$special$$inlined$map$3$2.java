package com.android.systemui.user.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherInteractor$special$$inlined$map$3$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ UserSwitcherInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UserSwitcherInteractor$special$$inlined$map$3$2.this.emit(null, this);
        }
    }

    public UserSwitcherInteractor$special$$inlined$map$3$2(FlowCollector flowCollector, UserSwitcherInteractor userSwitcherInteractor) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = userSwitcherInteractor;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2$1 r0 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2$1 r0 = new com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L54
            if (r2 == r5) goto L3f
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            kotlin.ResultKt.throwOnFailure(r10)
            goto L98
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L8d
        L3f:
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$2
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r9 = (com.android.systemui.user.domain.interactor.UserSwitcherInteractor) r9
            java.lang.Object r2 = r0.L$1
            android.content.pm.UserInfo r2 = (android.content.pm.UserInfo) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r5
            r5 = r10
            r10 = r7
            goto L75
        L54:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r9
            android.content.pm.UserInfo r2 = (android.content.pm.UserInfo) r2
            int r9 = r2.id
            kotlinx.coroutines.flow.FlowCollector r10 = r8.$this_unsafeFlow
            r0.L$0 = r10
            r0.L$1 = r2
            com.android.systemui.user.domain.interactor.UserSwitcherInteractor r8 = r8.this$0
            r0.L$2 = r8
            r0.I$0 = r9
            r0.label = r5
            r5 = 0
            java.lang.Object r5 = r8.canSwitchUsers(r9, r0, r5)
            if (r5 != r1) goto L72
            return r1
        L72:
            r7 = r9
            r9 = r8
            r8 = r7
        L75:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r0.L$0 = r10
            r0.L$1 = r6
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r8 = r9.toUserModel(r2, r8, r5, r0)
            if (r8 != r1) goto L8a
            return r1
        L8a:
            r7 = r10
            r10 = r8
            r8 = r7
        L8d:
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r8 = r8.emit(r10, r0)
            if (r8 != r1) goto L98
            return r1
        L98:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$special$$inlined$map$3$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
