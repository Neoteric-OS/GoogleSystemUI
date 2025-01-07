package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PagerWrapperFlingBehavior implements FlingBehavior {
    public final TargetedFlingBehavior originalFlingBehavior;
    public final PagerState pagerState;

    public PagerWrapperFlingBehavior(TargetedFlingBehavior targetedFlingBehavior, PagerState pagerState) {
        this.originalFlingBehavior = targetedFlingBehavior;
        this.pagerState = pagerState;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // androidx.compose.foundation.gestures.FlingBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object performFling(final androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1 r5, float r6, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$1 r0 = (androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$1 r0 = new androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r7 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r7
            r0.<init>(r4, r7)
        L1a:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            androidx.compose.foundation.pager.PagerWrapperFlingBehavior r4 = (androidx.compose.foundation.pager.PagerWrapperFlingBehavior) r4
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4c
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$resultVelocity$1$1 r7 = new androidx.compose.foundation.pager.PagerWrapperFlingBehavior$performFling$resultVelocity$1$1
            r7.<init>()
            r0.L$0 = r4
            r0.label = r3
            androidx.compose.foundation.gestures.TargetedFlingBehavior r2 = r4.originalFlingBehavior
            androidx.compose.foundation.gestures.snapping.SnapFlingBehavior r2 = (androidx.compose.foundation.gestures.snapping.SnapFlingBehavior) r2
            java.lang.Object r7 = r2.performFling(r5, r6, r7, r0)
            if (r7 != r1) goto L4c
            return r1
        L4c:
            java.lang.Number r7 = (java.lang.Number) r7
            float r5 = r7.floatValue()
            androidx.compose.foundation.pager.PagerState r6 = r4.pagerState
            androidx.compose.foundation.pager.PagerScrollPosition r6 = r6.scrollPosition
            float r6 = r6.getCurrentPageOffsetFraction()
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            androidx.compose.foundation.pager.PagerState r4 = r4.pagerState
            if (r6 != 0) goto L62
            goto L9d
        L62:
            androidx.compose.foundation.pager.PagerScrollPosition r6 = r4.scrollPosition
            float r6 = r6.getCurrentPageOffsetFraction()
            float r6 = java.lang.Math.abs(r6)
            double r0 = (double) r6
            r2 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L9d
            int r6 = r4.getCurrentPage()
            androidx.compose.foundation.gestures.ScrollableState r0 = r4.scrollableState
            boolean r0 = r0.isScrollInProgress()
            if (r0 == 0) goto L98
            androidx.compose.runtime.MutableState r0 = r4.pagerLayoutInfoState
            androidx.compose.runtime.SnapshotMutableStateImpl r0 = (androidx.compose.runtime.SnapshotMutableStateImpl) r0
            java.lang.Object r0 = r0.getValue()
            androidx.compose.foundation.pager.PagerMeasureResult r0 = (androidx.compose.foundation.pager.PagerMeasureResult) r0
            kotlinx.coroutines.CoroutineScope r0 = r0.coroutineScope
            androidx.compose.foundation.pager.PagerState$requestScrollToPage$1 r1 = new androidx.compose.foundation.pager.PagerState$requestScrollToPage$1
            r2 = 0
            r1.<init>(r4, r2)
            r3 = 3
            kotlinx.coroutines.BuildersKt.launch$default(r0, r2, r2, r1, r3)
        L98:
            r0 = 0
            r4.snapToItem$foundation_release(r7, r0, r6)
            goto La2
        L9d:
            androidx.compose.foundation.pager.PagerScrollPosition r4 = r4.scrollPosition
            r4.getCurrentPageOffsetFraction()
        La2:
            java.lang.Float r4 = new java.lang.Float
            r4.<init>(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerWrapperFlingBehavior.performFling(androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1, float, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
