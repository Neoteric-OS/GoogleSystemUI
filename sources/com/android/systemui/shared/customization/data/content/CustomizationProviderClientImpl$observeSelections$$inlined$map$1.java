package com.android.systemui.shared.customization.data.content;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomizationProviderClientImpl$observeSelections$$inlined$map$1 implements Flow {
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ CustomizationProviderClientImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ CustomizationProviderClientImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, CustomizationProviderClientImpl customizationProviderClientImpl) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = customizationProviderClientImpl;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r7
                com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1$2$1 r0 = (com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1$2$1 r0 = new com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1$2$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3a
                if (r2 == r4) goto L32
                if (r2 != r3) goto L2a
                kotlin.ResultKt.throwOnFailure(r7)
                goto L5b
            L2a:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L32:
                java.lang.Object r5 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
                kotlin.ResultKt.throwOnFailure(r7)
                goto L4f
            L3a:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlin.Unit r6 = (kotlin.Unit) r6
                kotlinx.coroutines.flow.FlowCollector r6 = r5.$this_unsafeFlow
                r0.L$0 = r6
                r0.label = r4
                com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl r5 = r5.this$0
                java.lang.Object r7 = r5.querySelections(r0)
                if (r7 != r1) goto L4e
                return r1
            L4e:
                r5 = r6
            L4f:
                r6 = 0
                r0.L$0 = r6
                r0.label = r3
                java.lang.Object r5 = r5.emit(r7, r0)
                if (r5 != r1) goto L5b
                return r1
            L5b:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl$observeSelections$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public CustomizationProviderClientImpl$observeSelections$$inlined$map$1(Flow flow, CustomizationProviderClientImpl customizationProviderClientImpl) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = customizationProviderClientImpl;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this.this$0), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
