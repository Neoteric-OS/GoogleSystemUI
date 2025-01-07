package com.android.systemui.user.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserRepositoryImpl$refreshUsers$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ UserRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ UserRepositoryImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UserRepositoryImpl userRepositoryImpl, Continuation continuation) {
            super(2, continuation);
            this.this$0 = userRepositoryImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.this$0.manager.getAliveUsers();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserRepositoryImpl$refreshUsers$1(UserRepositoryImpl userRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserRepositoryImpl$refreshUsers$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserRepositoryImpl$refreshUsers$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0073  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L21
            if (r1 == r3) goto L19
            if (r1 != r2) goto L11
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6f
        L11:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L19:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.MutableStateFlow r1 = (kotlinx.coroutines.flow.MutableStateFlow) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L3a
        L21:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.user.data.repository.UserRepositoryImpl r7 = r6.this$0
            kotlinx.coroutines.flow.StateFlowImpl r1 = r7._userInfos
            com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$1 r5 = new com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$1
            r5.<init>(r7, r4)
            r6.L$0 = r1
            r6.label = r3
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r5, r6)
            if (r7 != r0) goto L3a
            return r0
        L3a:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$invokeSuspend$$inlined$sortedBy$1 r3 = new com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$invokeSuspend$$inlined$sortedBy$1
            r5 = 0
            r3.<init>()
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r7, r3)
            com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$invokeSuspend$$inlined$sortedBy$1 r3 = new com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$invokeSuspend$$inlined$sortedBy$1
            r5 = 1
            r3.<init>()
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r7, r3)
            kotlinx.coroutines.flow.StateFlowImpl r1 = (kotlinx.coroutines.flow.StateFlowImpl) r1
            r1.setValue(r7)
            com.android.systemui.user.data.repository.UserRepositoryImpl r7 = r6.this$0
            int r1 = r7.mainUserId
            r3 = -10000(0xffffffffffffd8f0, float:NaN)
            if (r1 != r3) goto L7b
            com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$mainUser$1 r1 = new com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1$mainUser$1
            r1.<init>(r7, r4)
            r6.L$0 = r4
            r6.label = r2
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r1, r6)
            if (r7 != r0) goto L6f
            return r0
        L6f:
            android.os.UserHandle r7 = (android.os.UserHandle) r7
            if (r7 == 0) goto L7b
            com.android.systemui.user.data.repository.UserRepositoryImpl r6 = r6.this$0
            int r7 = r7.getIdentifier()
            r6.mainUserId = r7
        L7b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.user.data.repository.UserRepositoryImpl$refreshUsers$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
