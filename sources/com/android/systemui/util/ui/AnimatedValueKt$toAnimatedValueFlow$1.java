package com.android.systemui.util.ui;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AnimatedValueKt$toAnimatedValueFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AnimatedValueKt$toAnimatedValueFlow$1 animatedValueKt$toAnimatedValueFlow$1 = new AnimatedValueKt$toAnimatedValueFlow$1(3, (Continuation) obj3);
        animatedValueKt$toAnimatedValueFlow$1.L$0 = (FlowCollector) obj;
        animatedValueKt$toAnimatedValueFlow$1.L$1 = (AnimatableEvent) obj2;
        return animatedValueKt$toAnimatedValueFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x008b A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L36
            if (r1 == r4) goto L27
            if (r1 == r3) goto L1d
            if (r1 != r2) goto L15
            kotlin.ResultKt.throwOnFailure(r10)
            goto L8c
        L15:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L1d:
            java.lang.Object r1 = r9.L$1
            java.lang.Object r3 = r9.L$0
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r10)
            goto L78
        L27:
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.CompletableDeferred r1 = (kotlinx.coroutines.CompletableDeferred) r1
            java.lang.Object r4 = r9.L$1
            java.lang.Object r6 = r9.L$0
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r6
            goto L65
        L36:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r1 = r9.L$1
            com.android.systemui.util.ui.AnimatableEvent r1 = (com.android.systemui.util.ui.AnimatableEvent) r1
            java.lang.Object r6 = r1.value
            boolean r1 = r1.startAnimating
            if (r1 == 0) goto L7a
            kotlinx.coroutines.CompletableDeferredImpl r1 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default()
            com.android.systemui.util.ui.AnimatedValue$Animating r7 = new com.android.systemui.util.ui.AnimatedValue$Animating
            com.android.systemui.util.ui.AnimatedValueKt$toAnimatedValueFlow$1$1 r8 = new com.android.systemui.util.ui.AnimatedValueKt$toAnimatedValueFlow$1$1
            r8.<init>()
            r7.<init>(r6, r8)
            r9.L$0 = r10
            r9.L$1 = r6
            r9.L$2 = r1
            r9.label = r4
            java.lang.Object r4 = r10.emit(r7, r9)
            if (r4 != r0) goto L64
            return r0
        L64:
            r4 = r6
        L65:
            r9.L$0 = r10
            r9.L$1 = r4
            r9.L$2 = r5
            r9.label = r3
            kotlinx.coroutines.CompletableDeferredImpl r1 = (kotlinx.coroutines.CompletableDeferredImpl) r1
            java.lang.Object r1 = r1.awaitInternal(r9)
            if (r1 != r0) goto L76
            return r0
        L76:
            r3 = r10
            r1 = r4
        L78:
            r6 = r1
            r10 = r3
        L7a:
            com.android.systemui.util.ui.AnimatedValue$NotAnimating r1 = new com.android.systemui.util.ui.AnimatedValue$NotAnimating
            r1.<init>(r6)
            r9.L$0 = r5
            r9.L$1 = r5
            r9.label = r2
            java.lang.Object r9 = r10.emit(r1, r9)
            if (r9 != r0) goto L8c
            return r0
        L8c:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.ui.AnimatedValueKt$toAnimatedValueFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
