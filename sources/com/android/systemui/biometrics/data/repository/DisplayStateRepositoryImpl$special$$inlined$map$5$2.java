package com.android.systemui.biometrics.data.repository;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayStateRepositoryImpl$special$$inlined$map$5$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ DisplayStateRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2$1, reason: invalid class name */
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
            return DisplayStateRepositoryImpl$special$$inlined$map$5$2.this.emit(null, this);
        }
    }

    public DisplayStateRepositoryImpl$special$$inlined$map$5$2(FlowCollector flowCollector, DisplayStateRepositoryImpl displayStateRepositoryImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = displayStateRepositoryImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2$1 r0 = (com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2$1 r0 = new com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r6)
            goto L69
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r6)
            android.view.DisplayInfo r5 = (android.view.DisplayInfo) r5
            int r6 = r5.logicalWidth
            int r5 = r5.logicalHeight
            int r5 = java.lang.Math.min(r6, r5)
            float r5 = (float) r5
            com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl r6 = r4.this$0
            android.content.Context r6 = r6.context
            android.content.res.Resources r6 = r6.getResources()
            android.content.res.Configuration r6 = r6.getConfiguration()
            int r6 = r6.densityDpi
            float r6 = (float) r6
            r2 = 160(0xa0, float:2.24E-43)
            float r2 = (float) r2
            float r6 = r6 / r2
            float r5 = r5 / r6
            r6 = 1142292480(0x44160000, float:600.0)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 < 0) goto L59
            r5 = r3
            goto L5a
        L59:
            r5 = 0
        L5a:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
            java.lang.Object r4 = r4.emit(r5, r0)
            if (r4 != r1) goto L69
            return r1
        L69:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.DisplayStateRepositoryImpl$special$$inlined$map$5$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
