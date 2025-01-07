package com.android.systemui.qs.footer.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterActionsInteractorImpl$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ CoroutineDispatcher $bgDispatcher$inlined;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ FooterActionsInteractorImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ CoroutineDispatcher $bgDispatcher$inlined;
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ FooterActionsInteractorImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, CoroutineDispatcher coroutineDispatcher, FooterActionsInteractorImpl footerActionsInteractorImpl) {
            this.$this_unsafeFlow = flowCollector;
            this.$bgDispatcher$inlined = coroutineDispatcher;
            this.this$0 = footerActionsInteractorImpl;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0061 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r8
                com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1$2$1
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 0
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L3b
                if (r2 == r5) goto L33
                if (r2 != r4) goto L2b
                kotlin.ResultKt.throwOnFailure(r8)
                goto L62
            L2b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L33:
                java.lang.Object r6 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                kotlin.ResultKt.throwOnFailure(r8)
                goto L57
            L3b:
                kotlin.ResultKt.throwOnFailure(r8)
                com.android.systemui.security.data.model.SecurityModel r7 = (com.android.systemui.security.data.model.SecurityModel) r7
                com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$securityButtonConfig$1$1 r8 = new com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$securityButtonConfig$1$1
                com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl r2 = r6.this$0
                r8.<init>(r2, r7, r3)
                kotlinx.coroutines.flow.FlowCollector r7 = r6.$this_unsafeFlow
                r0.L$0 = r7
                r0.label = r5
                kotlinx.coroutines.CoroutineDispatcher r6 = r6.$bgDispatcher$inlined
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r8, r0)
                if (r8 != r1) goto L56
                return r1
            L56:
                r6 = r7
            L57:
                r0.L$0 = r3
                r0.label = r4
                java.lang.Object r6 = r6.emit(r8, r0)
                if (r6 != r1) goto L62
                return r1
            L62:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.footer.domain.interactor.FooterActionsInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public FooterActionsInteractorImpl$special$$inlined$map$1(Flow flow, CoroutineDispatcher coroutineDispatcher, FooterActionsInteractorImpl footerActionsInteractorImpl) {
        this.$this_unsafeTransform$inlined = flow;
        this.$bgDispatcher$inlined = coroutineDispatcher;
        this.this$0 = footerActionsInteractorImpl;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.$bgDispatcher$inlined, this.this$0), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
