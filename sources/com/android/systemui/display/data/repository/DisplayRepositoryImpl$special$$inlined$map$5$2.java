package com.android.systemui.display.data.repository;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayRepositoryImpl$special$$inlined$map$5$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ DisplayRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2$1, reason: invalid class name */
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
            return DisplayRepositoryImpl$special$$inlined$map$5$2.this.emit(null, this);
        }
    }

    public DisplayRepositoryImpl$special$$inlined$map$5$2(FlowCollector flowCollector, DisplayRepositoryImpl displayRepositoryImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = displayRepositoryImpl;
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
            boolean r0 = r6 instanceof com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2$1 r0 = (com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2$1 r0 = new com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4e
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Integer r5 = (java.lang.Integer) r5
            if (r5 == 0) goto L42
            int r5 = r5.intValue()
            com.android.systemui.display.data.repository.DisplayRepositoryImpl$pendingDisplay$1$1 r6 = new com.android.systemui.display.data.repository.DisplayRepositoryImpl$pendingDisplay$1$1
            com.android.systemui.display.data.repository.DisplayRepositoryImpl r2 = r4.this$0
            r6.<init>(r5, r2)
            goto L43
        L42:
            r6 = 0
        L43:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
            java.lang.Object r4 = r4.emit(r6, r0)
            if (r4 != r1) goto L4e
            return r1
        L4e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.data.repository.DisplayRepositoryImpl$special$$inlined$map$5$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
