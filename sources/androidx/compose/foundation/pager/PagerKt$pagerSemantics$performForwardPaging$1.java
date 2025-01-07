package androidx.compose.foundation.pager;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PagerKt$pagerSemantics$performForwardPaging$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ PagerState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerKt$pagerSemantics$performForwardPaging$1(PagerState pagerState, Continuation continuation) {
        super(2, continuation);
        this.$state = pagerState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new PagerKt$pagerSemantics$performForwardPaging$1(this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PagerKt$pagerSemantics$performForwardPaging$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        r5 = r6.animateScrollToPage(r6.getCurrentPage() + 1, androidx.compose.animation.core.AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7), r5);
     */
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
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3b
        Lf:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L17:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.pager.PagerState r6 = r5.$state
            r5.label = r3
            float r1 = androidx.compose.foundation.pager.PagerStateKt.DefaultPositionThreshold
            int r1 = r6.getCurrentPage()
            int r1 = r1 + r3
            int r4 = r6.getPageCount()
            if (r1 >= r4) goto L37
            int r1 = r6.getCurrentPage()
            int r1 = r1 + r3
            java.lang.Object r5 = androidx.compose.foundation.pager.PagerState.animateScrollToPage$default(r6, r1, r5)
            if (r5 != r0) goto L37
            goto L38
        L37:
            r5 = r2
        L38:
            if (r5 != r0) goto L3b
            return r0
        L3b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt$pagerSemantics$performForwardPaging$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
