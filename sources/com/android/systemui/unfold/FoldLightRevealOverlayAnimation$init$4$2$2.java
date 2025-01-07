package com.android.systemui.unfold;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$init$4$2$2 extends SuspendLambda implements Function3 {
    int label;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$init$4$2$2(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
        super(3, continuation);
        this.this$0 = foldLightRevealOverlayAnimation;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FoldLightRevealOverlayAnimation$init$4$2$2 foldLightRevealOverlayAnimation$init$4$2$2 = new FoldLightRevealOverlayAnimation$init$4$2$2(this.this$0, (Continuation) obj3);
        Unit unit = Unit.INSTANCE;
        foldLightRevealOverlayAnimation$init$4$2$2.invokeSuspend(unit);
        return unit;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) {
        /*
            r2 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r2.label
            if (r0 != 0) goto L51
            kotlin.ResultKt.throwOnFailure(r3)
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r3 = r2.this$0
            com.android.systemui.unfold.FullscreenLightRevealAnimationController r3 = r3.controller
            r0 = 0
            if (r3 != 0) goto L11
            r3 = r0
        L11:
            r3.ensureOverlayRemoved()
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r3 = r2.this$0
            kotlinx.coroutines.CompletableDeferredImpl r3 = r3.readyCallback
            if (r3 == 0) goto L44
            boolean r1 = r3.isCompleted()
            if (r1 == 0) goto L21
            goto L22
        L21:
            r3 = r0
        L22:
            if (r3 == 0) goto L44
            java.lang.Object r3 = r3.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()
            boolean r1 = r3 instanceof kotlinx.coroutines.Incomplete
            if (r1 != 0) goto L3c
            boolean r1 = r3 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r1 != 0) goto L37
            java.lang.Object r3 = kotlinx.coroutines.JobSupportKt.unboxState(r3)
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            goto L45
        L37:
            kotlinx.coroutines.CompletedExceptionally r3 = (kotlinx.coroutines.CompletedExceptionally) r3
            java.lang.Throwable r2 = r3.cause
            throw r2
        L3c:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "This job has not completed yet"
            r2.<init>(r3)
            throw r2
        L44:
            r3 = r0
        L45:
            if (r3 == 0) goto L4a
            r3.run()
        L4a:
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r2 = r2.this$0
            r2.readyCallback = r0
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L51:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "call to 'resume' before 'invoke' with coroutine"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
