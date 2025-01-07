package com.android.systemui.lifecycle;

import android.view.View;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function3 $block;
    final /* synthetic */ String $nameForTrace;
    final /* synthetic */ ViewLifecycleOwner $this_apply;
    final /* synthetic */ View $view;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(String str, Function3 function3, ViewLifecycleOwner viewLifecycleOwner, View view, Continuation continuation) {
        super(2, continuation);
        this.$nameForTrace = str;
        this.$block = function3;
        this.$this_apply = viewLifecycleOwner;
        this.$view = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(this.$nameForTrace, this.$block, this.$this_apply, this.$view, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            java.lang.Object r5 = r5.L$0
            com.android.app.tracing.coroutines.TraceData r5 = (com.android.app.tracing.coroutines.TraceData) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L11
            goto L31
        L11:
            r6 = move-exception
            goto L3a
        L13:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function3 r6 = r5.$block
            com.android.systemui.lifecycle.ViewLifecycleOwner r1 = r5.$this_apply
            android.view.View r3 = r5.$view
            r4 = 0
            r5.L$0 = r4     // Catch: java.lang.Throwable -> L38
            r5.label = r2     // Catch: java.lang.Throwable -> L38
            java.lang.Object r5 = r6.invoke(r1, r3, r5)     // Catch: java.lang.Throwable -> L38
            if (r5 != r0) goto L30
            return r0
        L30:
            r5 = r4
        L31:
            if (r5 == 0) goto L35
            int r5 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L35:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L38:
            r6 = move-exception
            r5 = r4
        L3a:
            if (r5 == 0) goto L3e
            int r5 = com.android.app.tracing.coroutines.TraceDataKt.$r8$clinit
        L3e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.lifecycle.RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
