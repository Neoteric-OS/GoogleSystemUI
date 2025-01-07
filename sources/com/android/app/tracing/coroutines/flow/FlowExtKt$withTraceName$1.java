package com.android.app.tracing.coroutines.flow;

import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowExtKt$withTraceName$1 implements Flow {
    public final /* synthetic */ String $name;
    public final /* synthetic */ Flow $this_withTraceName;

    public FlowExtKt$withTraceName$1(Flow flow, String str) {
        this.$this_withTraceName = flow;
        this.$name = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1$collect$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1$collect$1 r0 = (com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1$collect$1 r0 = new com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1$collect$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r6 = r0.L$0
            com.android.app.tracing.coroutines.TraceData r6 = (com.android.app.tracing.coroutines.TraceData) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L2b
            goto L71
        L2b:
            r7 = move-exception
            goto L7a
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.flow.Flow r8 = r6.$this_withTraceName
            java.lang.String r6 = r6.$name
            if (r6 != 0) goto L42
            java.lang.String r6 = com.android.app.tracing.coroutines.flow.FlowExtKt.walkStackForClassName()
        L42:
            kotlin.Pair r2 = new kotlin.Pair
            java.lang.String r4 = ":collect"
            java.lang.String r4 = r6.concat(r4)
            java.lang.String r5 = ":emit"
            java.lang.String r6 = r6.concat(r5)
            r2.<init>(r4, r6)
            java.lang.Object r6 = r2.component1()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r2.component2()
            java.lang.String r6 = (java.lang.String) r6
            r2 = 0
            com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1 r4 = new com.android.app.tracing.coroutines.flow.FlowExtKt$collect$2$1     // Catch: java.lang.Throwable -> L78
            r4.<init>(r6, r7)     // Catch: java.lang.Throwable -> L78
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L78
            r0.label = r3     // Catch: java.lang.Throwable -> L78
            java.lang.Object r6 = r8.collect(r4, r0)     // Catch: java.lang.Throwable -> L78
            if (r6 != r1) goto L70
            return r1
        L70:
            r6 = r2
        L71:
            if (r6 == 0) goto L75
            int r6 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L75:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L78:
            r7 = move-exception
            r6 = r2
        L7a:
            if (r6 == 0) goto L7e
            int r6 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L7e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
