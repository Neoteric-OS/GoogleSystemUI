package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PagerState$animateScrollToPage$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ AnimationSpec $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ float $targetPageOffsetToSnappedPosition;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PagerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerState$animateScrollToPage$3(PagerState pagerState, int i, float f, AnimationSpec animationSpec, Continuation continuation) {
        super(2, continuation);
        this.this$0 = pagerState;
        this.$targetPage = i;
        this.$targetPageOffsetToSnappedPosition = f;
        this.$animationSpec = animationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PagerState$animateScrollToPage$3 pagerState$animateScrollToPage$3 = new PagerState$animateScrollToPage$3(this.this$0, this.$targetPage, this.$targetPageOffsetToSnappedPosition, this.$animationSpec, continuation);
        pagerState$animateScrollToPage$3.L$0 = obj;
        return pagerState$animateScrollToPage$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PagerState$animateScrollToPage$3) create((ScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006c, code lost:
    
        if (r3 < r1) goto L24;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r12.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 1
            if (r1 == 0) goto L18
            if (r1 != r3) goto L10
            kotlin.ResultKt.throwOnFailure(r13)
            goto L96
        L10:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L18:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            androidx.compose.foundation.gestures.ScrollScope r13 = (androidx.compose.foundation.gestures.ScrollScope) r13
            androidx.compose.foundation.pager.PagerState r1 = r12.this$0
            androidx.compose.foundation.pager.PagerScrollScopeKt$LazyLayoutScrollScope$1 r4 = new androidx.compose.foundation.pager.PagerScrollScopeKt$LazyLayoutScrollScope$1
            r4.<init>(r13, r1)
            int r5 = r12.$targetPage
            float r6 = r12.$targetPageOffsetToSnappedPosition
            androidx.compose.animation.core.AnimationSpec r7 = r12.$animationSpec
            androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1 r8 = new androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1
            r8.<init>()
            r12.label = r3
            float r9 = androidx.compose.foundation.pager.PagerStateKt.DefaultPositionThreshold
            java.lang.Integer r9 = new java.lang.Integer
            r9.<init>(r5)
            r8.invoke(r13, r9)
            int r8 = r1.firstVisiblePage
            r9 = 0
            if (r5 <= r8) goto L44
            r8 = r3
            goto L45
        L44:
            r8 = r9
        L45:
            int r10 = r4.getLastVisibleItemIndex()
            int r11 = r1.firstVisiblePage
            int r10 = r10 - r11
            int r10 = r10 + r3
            if (r8 == 0) goto L55
            int r3 = r4.getLastVisibleItemIndex()
            if (r5 > r3) goto L5b
        L55:
            if (r8 != 0) goto L7a
            int r3 = r1.firstVisiblePage
            if (r5 >= r3) goto L7a
        L5b:
            int r3 = r1.firstVisiblePage
            int r3 = r5 - r3
            int r3 = java.lang.Math.abs(r3)
            r11 = 3
            if (r3 < r11) goto L7a
            if (r8 == 0) goto L70
            int r3 = r5 - r10
            int r1 = r1.firstVisiblePage
            if (r3 >= r1) goto L77
        L6e:
            r3 = r1
            goto L77
        L70:
            int r10 = r10 + r5
            int r1 = r1.firstVisiblePage
            if (r10 <= r1) goto L76
            goto L6e
        L76:
            r3 = r10
        L77:
            r4.snapToItem(r3, r9)
        L7a:
            int r1 = r4.calculateDistanceTo(r5)
            float r1 = (float) r1
            float r1 = r1 + r6
            kotlin.jvm.internal.Ref$FloatRef r3 = new kotlin.jvm.internal.Ref$FloatRef
            r3.<init>()
            androidx.compose.foundation.pager.PagerStateKt$animateScrollToPage$2$3 r4 = new androidx.compose.foundation.pager.PagerStateKt$animateScrollToPage$2$3
            r4.<init>()
            r13 = 4
            java.lang.Object r12 = androidx.compose.animation.core.SuspendAnimationKt.animate$default(r1, r7, r4, r12, r13)
            if (r12 != r0) goto L92
            goto L93
        L92:
            r12 = r2
        L93:
            if (r12 != r0) goto L96
            return r0
        L96:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerState$animateScrollToPage$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
