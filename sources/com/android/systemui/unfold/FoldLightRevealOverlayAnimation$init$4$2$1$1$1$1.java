package com.android.systemui.unfold;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
        super(2, continuation);
        this.this$0 = foldLightRevealOverlayAnimation;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0056 A[PHI: r6
      0x0056: PHI (r6v11 java.lang.Object) = (r6v8 java.lang.Object), (r6v0 java.lang.Object) binds: [B:16:0x0053, B:5:0x000d] A[DONT_GENERATE, DONT_INLINE], RETURN] */
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
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L1d
            if (r1 == r3) goto L19
            if (r1 != r2) goto L11
            kotlin.ResultKt.throwOnFailure(r6)
            goto L56
        L11:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L19:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L37
        L1d:
            kotlin.ResultKt.throwOnFailure(r6)
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r6 = r5.this$0
            kotlinx.coroutines.CompletableDeferredImpl r1 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default()
            r6.readyCallback = r1
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r6 = r5.this$0
            kotlinx.coroutines.CompletableDeferredImpl r6 = r6.readyCallback
            if (r6 == 0) goto L3a
            r5.label = r3
            java.lang.Object r6 = r6.awaitInternal(r5)
            if (r6 != r0) goto L37
            return r0
        L37:
            java.lang.Runnable r6 = (java.lang.Runnable) r6
            goto L3b
        L3a:
            r6 = r4
        L3b:
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r1 = r5.this$0
            r1.readyCallback = r4
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r1 = r5.this$0
            com.android.systemui.unfold.FullscreenLightRevealAnimationController r1 = r1.controller
            if (r1 != 0) goto L46
            goto L47
        L46:
            r4 = r1
        L47:
            r1 = 0
            r4.addOverlay(r1, r6)
            com.android.systemui.unfold.FoldLightRevealOverlayAnimation r6 = r5.this$0
            r5.label = r2
            java.lang.Object r6 = com.android.systemui.unfold.FoldLightRevealOverlayAnimation.access$waitForScreenTurnedOn(r6, r5)
            if (r6 != r0) goto L56
            return r0
        L56:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$init$4$2$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
