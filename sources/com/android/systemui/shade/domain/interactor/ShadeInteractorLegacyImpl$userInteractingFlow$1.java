package com.android.systemui.shade.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ShadeInteractorLegacyImpl$userInteractingFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ StateFlow $expansion;
    final /* synthetic */ Flow $tracking;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ boolean Z$0;
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, continuation);
            anonymousClass1.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(this.Z$0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ boolean Z$0;
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(!this.Z$0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        /* synthetic */ float F$0;
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(2, continuation);
            anonymousClass3.F$0 = ((Number) obj).floatValue();
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass3) create(Float.valueOf(((Number) obj).floatValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            float f = this.F$0;
            return Boolean.valueOf(f <= 0.0f || f >= 1.0f);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadeInteractorLegacyImpl$userInteractingFlow$1(Flow flow, StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.$tracking = flow;
        this.$expansion = stateFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ShadeInteractorLegacyImpl$userInteractingFlow$1 shadeInteractorLegacyImpl$userInteractingFlow$1 = new ShadeInteractorLegacyImpl$userInteractingFlow$1(this.$tracking, this.$expansion, continuation);
        shadeInteractorLegacyImpl$userInteractingFlow$1.L$0 = obj;
        return shadeInteractorLegacyImpl$userInteractingFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ShadeInteractorLegacyImpl$userInteractingFlow$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0079 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ad A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x009d -> B:7:0x00a0). Please report as a decompilation issue!!! */
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
            r2 = 0
            r3 = 2
            switch(r1) {
                case 0: goto L3a;
                case 1: goto L32;
                case 2: goto L2a;
                case 3: goto L22;
                case 4: goto L1a;
                case 5: goto L11;
                case 6: goto L32;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L11:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto La0
        L1a:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L8d
        L22:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7a
        L2a:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6c
        L32:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L50
        L3a:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            r1 = r7
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r6.L$0 = r1
            r4 = 1
            r6.label = r4
            java.lang.Object r7 = r1.emit(r7, r6)
            if (r7 != r0) goto L50
            return r0
        L50:
            kotlin.coroutines.CoroutineContext r7 = r6.getContext()
            boolean r7 = kotlinx.coroutines.JobKt.isActive(r7)
            if (r7 == 0) goto Lae
            kotlinx.coroutines.flow.Flow r7 = r6.$tracking
            com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$1 r4 = new com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$1
            r4.<init>(r3, r2)
            r6.L$0 = r1
            r6.label = r3
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.first(r7, r4, r6)
            if (r7 != r0) goto L6c
            return r0
        L6c:
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r6.L$0 = r1
            r4 = 3
            r6.label = r4
            java.lang.Object r7 = r1.emit(r7, r6)
            if (r7 != r0) goto L7a
            return r0
        L7a:
            kotlinx.coroutines.flow.Flow r7 = r6.$tracking
            com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$2 r4 = new com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$2
            r4.<init>(r3, r2)
            r6.L$0 = r1
            r5 = 4
            r6.label = r5
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.first(r7, r4, r6)
            if (r7 != r0) goto L8d
            return r0
        L8d:
            kotlinx.coroutines.flow.StateFlow r7 = r6.$expansion
            com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$3 r4 = new com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1$3
            r4.<init>(r3, r2)
            r6.L$0 = r1
            r5 = 5
            r6.label = r5
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.first(r7, r4, r6)
            if (r7 != r0) goto La0
            return r0
        La0:
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r6.L$0 = r1
            r4 = 6
            r6.label = r4
            java.lang.Object r7 = r1.emit(r7, r6)
            if (r7 != r0) goto L50
            return r0
        Lae:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.domain.interactor.ShadeInteractorLegacyImpl$userInteractingFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
