package com.android.systemui.util.kotlin;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DisposableHandleExtKt {
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void awaitCancellationThenDispose(kotlinx.coroutines.DisposableHandle r4, kotlin.coroutines.jvm.internal.ContinuationImpl r5) {
        /*
            boolean r0 = r5 instanceof com.android.systemui.util.kotlin.DisposableHandleExtKt$awaitCancellationThenDispose$1
            if (r0 == 0) goto L13
            r0 = r5
            com.android.systemui.util.kotlin.DisposableHandleExtKt$awaitCancellationThenDispose$1 r0 = (com.android.systemui.util.kotlin.DisposableHandleExtKt$awaitCancellationThenDispose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.util.kotlin.DisposableHandleExtKt$awaitCancellationThenDispose$1 r0 = new com.android.systemui.util.kotlin.DisposableHandleExtKt$awaitCancellationThenDispose$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 == r2) goto L2b
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2b:
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.DisposableHandle r4 = (kotlinx.coroutines.DisposableHandle) r4
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L38
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L38
            r5.<init>()     // Catch: java.lang.Throwable -> L38
            throw r5     // Catch: java.lang.Throwable -> L38
        L38:
            r5 = move-exception
            goto L45
        L3a:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L38
            r0.label = r2     // Catch: java.lang.Throwable -> L38
            kotlinx.coroutines.DelayKt.awaitCancellation(r0)     // Catch: java.lang.Throwable -> L38
            return
        L45:
            r4.dispose()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.kotlin.DisposableHandleExtKt.awaitCancellationThenDispose(kotlinx.coroutines.DisposableHandle, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }
}
