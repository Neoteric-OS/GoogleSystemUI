package androidx.compose.foundation.relocation;

import androidx.compose.runtime.collection.MutableVector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BringIntoViewRequesterImpl implements BringIntoViewRequester {
    public final MutableVector modifiers = new MutableVector(new BringIntoViewRequesterNode[16]);

    /* JADX WARN: Code restructure failed: missing block: B:11:0x007b, code lost:
    
        if (r8 < r10) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0075, code lost:
    
        if (r5 != kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007a, code lost:
    
        if (r5 != r1) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // androidx.compose.foundation.relocation.BringIntoViewRequester
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object bringIntoView(androidx.compose.ui.geometry.Rect r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = (androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = new androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 1
            if (r2 == 0) goto L3f
            if (r2 != r4) goto L37
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$1
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.geometry.Rect r5 = (androidx.compose.ui.geometry.Rect) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            r9 = r5
            goto L7a
        L37:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3f:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.compose.runtime.collection.MutableVector r8 = r8.modifiers
            int r10 = r8.size
            if (r10 <= 0) goto L7d
            java.lang.Object[] r8 = r8.content
            r2 = 0
            r7 = r2
            r2 = r8
            r8 = r7
        L4e:
            r5 = r2[r8]
            androidx.compose.foundation.relocation.BringIntoViewRequesterNode r5 = (androidx.compose.foundation.relocation.BringIntoViewRequesterNode) r5
            r0.L$0 = r9
            r0.L$1 = r2
            r0.I$0 = r10
            r0.I$1 = r8
            r0.label = r4
            androidx.compose.ui.Modifier$Node r6 = r5.node
            boolean r6 = r6.isAttached
            if (r6 != 0) goto L64
        L62:
            r5 = r3
            goto L77
        L64:
            androidx.compose.ui.node.NodeCoordinator r6 = androidx.compose.ui.node.DelegatableNodeKt.requireLayoutCoordinates(r5)
            androidx.compose.foundation.relocation.BringIntoViewParent r5 = androidx.compose.foundation.relocation.BringIntoViewRequesterKt.findBringIntoViewParent(r5)
            if (r5 != 0) goto L6f
            goto L62
        L6f:
            java.lang.Object r5 = androidx.compose.foundation.relocation.ScrollIntoView.scrollIntoView(r5, r6, r9, r0)
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r5 != r6) goto L62
        L77:
            if (r5 != r1) goto L7a
            return r1
        L7a:
            int r8 = r8 + r4
            if (r8 < r10) goto L4e
        L7d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.bringIntoView(androidx.compose.ui.geometry.Rect, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
