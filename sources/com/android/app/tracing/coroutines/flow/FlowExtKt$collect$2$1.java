package com.android.app.tracing.coroutines.flow;

import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowExtKt$collect$2$1 implements FlowCollector {
    public final /* synthetic */ FlowCollector $block;

    public FlowExtKt$collect$2$1(String str, FlowCollector flowCollector) {
        this.$block = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1$emit$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1$emit$1 r0 = (com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1$emit$1 r0 = new com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1$emit$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            com.android.app.tracing.coroutines.TraceData r4 = (com.android.app.tracing.coroutines.TraceData) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L2b
            goto L47
        L2b:
            r5 = move-exception
            goto L50
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.flow.FlowCollector r4 = r4.$block
            r6 = 0
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L4e
            r0.label = r3     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r4 = r4.emit(r5, r0)     // Catch: java.lang.Throwable -> L4e
            if (r4 != r1) goto L46
            return r1
        L46:
            r4 = r6
        L47:
            if (r4 == 0) goto L4b
            int r4 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L4b:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L4e:
            r5 = move-exception
            r4 = r6
        L50:
            if (r4 == 0) goto L54
            int r4 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L54:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
