package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedScrollNode extends Modifier.Node implements TraversableNode, NestedScrollConnection {
    public NestedScrollConnection connection;
    public NestedScrollDispatcher resolvedDispatcher;
    public final Object traverseKey;

    public NestedScrollNode(NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher) {
        this.connection = nestedScrollConnection;
        this.resolvedDispatcher = nestedScrollDispatcher == null ? new NestedScrollDispatcher() : nestedScrollDispatcher;
        this.traverseKey = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";
    }

    public final CoroutineScope getNestedCoroutineScope() {
        NestedScrollNode nestedScrollNode = this.isAttached ? (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this) : null;
        if (nestedScrollNode != null) {
            return nestedScrollNode.getNestedCoroutineScope();
        }
        CoroutineScope coroutineScope = this.resolvedDispatcher.scope;
        if (coroutineScope != null) {
            return coroutineScope;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return this.traverseKey;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        NestedScrollDispatcher nestedScrollDispatcher = this.resolvedDispatcher;
        nestedScrollDispatcher.nestedScrollNode = this;
        nestedScrollDispatcher.calculateNestedScrollScope = new NestedScrollNode$updateDispatcherFields$1(this);
        nestedScrollDispatcher.scope = getCoroutineScope();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        NestedScrollDispatcher nestedScrollDispatcher = this.resolvedDispatcher;
        if (nestedScrollDispatcher.nestedScrollNode == this) {
            nestedScrollDispatcher.nestedScrollNode = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo69onPostFlingRZ2iAVY(long r16, long r18, kotlin.coroutines.Continuation r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r20
            boolean r2 = r1 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            if (r2 == 0) goto L16
            r2 = r1
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.label = r3
            goto L1d
        L16:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r2 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r1 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r1
            r2.<init>(r15, r1)
        L1d:
            java.lang.Object r1 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r9 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r2.label
            r10 = 2
            r4 = 1
            if (r3 == 0) goto L48
            if (r3 == r4) goto L3a
            if (r3 != r10) goto L32
            long r2 = r2.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L97
        L32:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3a:
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r0 = r2.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r0
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r3
            r11 = r5
            goto L65
        L48:
            kotlin.ResultKt.throwOnFailure(r1)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r0.connection
            r2.L$0 = r0
            r11 = r16
            r2.J$0 = r11
            r13 = r18
            r2.J$1 = r13
            r2.label = r4
            r4 = r16
            r6 = r18
            r8 = r2
            java.lang.Object r1 = r3.mo69onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L65
            return r9
        L65:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r3 = r1.packedValue
            boolean r1 = r0.isAttached
            r5 = 0
            if (r1 == 0) goto L77
            if (r1 == 0) goto L77
            androidx.compose.ui.node.TraversableNode r0 = androidx.compose.ui.node.TraversableNodeKt.findNearestAncestor(r0)
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r0
            goto L78
        L77:
            r0 = r5
        L78:
            if (r0 == 0) goto L9d
            long r6 = androidx.compose.ui.unit.Velocity.m697plusAH228Gc(r11, r3)
            long r11 = androidx.compose.ui.unit.Velocity.m696minusAH228Gc(r13, r3)
            r2.L$0 = r5
            r2.J$0 = r3
            r2.label = r10
            r15 = r0
            r16 = r6
            r18 = r11
            r20 = r2
            java.lang.Object r1 = r15.mo69onPostFlingRZ2iAVY(r16, r18, r20)
            if (r1 != r9) goto L96
            return r9
        L96:
            r2 = r3
        L97:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r0 = r1.packedValue
            r3 = r2
            goto L9f
        L9d:
            r0 = 0
        L9f:
            long r0 = androidx.compose.ui.unit.Velocity.m697plusAH228Gc(r3, r0)
            androidx.compose.ui.unit.Velocity r2 = new androidx.compose.ui.unit.Velocity
            r2.<init>(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo69onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public final long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
        long mo70onPostScrollDzOQY0M = this.connection.mo70onPostScrollDzOQY0M(i, j, j2);
        boolean z = this.isAttached;
        NestedScrollNode nestedScrollNode = null;
        if (z && z) {
            nestedScrollNode = (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this);
        }
        NestedScrollNode nestedScrollNode2 = nestedScrollNode;
        return Offset.m315plusMKHz9U(mo70onPostScrollDzOQY0M, nestedScrollNode2 != null ? nestedScrollNode2.mo70onPostScrollDzOQY0M(i, Offset.m315plusMKHz9U(j, mo70onPostScrollDzOQY0M), Offset.m314minusMKHz9U(j2, mo70onPostScrollDzOQY0M)) : 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo227onPreFlingQWom1Mo(long r10, kotlin.coroutines.Continuation r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            if (r0 == 0) goto L13
            r0 = r12
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r12 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r12
            r0.<init>(r9, r12)
        L1a:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L41
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            long r9 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L80
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L37:
            long r10 = r0.J$0
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r9 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L61
        L41:
            kotlin.ResultKt.throwOnFailure(r12)
            boolean r12 = r9.isAttached
            if (r12 == 0) goto L51
            if (r12 == 0) goto L51
            androidx.compose.ui.node.TraversableNode r12 = androidx.compose.ui.node.TraversableNodeKt.findNearestAncestor(r9)
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r12 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r12
            goto L52
        L51:
            r12 = r3
        L52:
            if (r12 == 0) goto L6a
            r0.L$0 = r9
            r0.J$0 = r10
            r0.label = r5
            java.lang.Object r12 = r12.mo227onPreFlingQWom1Mo(r10, r0)
            if (r12 != r1) goto L61
            return r1
        L61:
            androidx.compose.ui.unit.Velocity r12 = (androidx.compose.ui.unit.Velocity) r12
            long r5 = r12.packedValue
        L65:
            r7 = r10
            r11 = r9
            r9 = r5
            r5 = r7
            goto L6d
        L6a:
            r5 = 0
            goto L65
        L6d:
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r11 = r11.connection
            long r5 = androidx.compose.ui.unit.Velocity.m696minusAH228Gc(r5, r9)
            r0.L$0 = r3
            r0.J$0 = r9
            r0.label = r4
            java.lang.Object r12 = r11.mo227onPreFlingQWom1Mo(r5, r0)
            if (r12 != r1) goto L80
            return r1
        L80:
            androidx.compose.ui.unit.Velocity r12 = (androidx.compose.ui.unit.Velocity) r12
            long r11 = r12.packedValue
            long r9 = androidx.compose.ui.unit.Velocity.m697plusAH228Gc(r9, r11)
            androidx.compose.ui.unit.Velocity r11 = new androidx.compose.ui.unit.Velocity
            r11.<init>(r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo227onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public final long mo139onPreScrollOzD1aCk(long j, int i) {
        boolean z = this.isAttached;
        NestedScrollNode nestedScrollNode = null;
        if (z && z) {
            nestedScrollNode = (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this);
        }
        long mo139onPreScrollOzD1aCk = nestedScrollNode != null ? nestedScrollNode.mo139onPreScrollOzD1aCk(j, i) : 0L;
        return Offset.m315plusMKHz9U(mo139onPreScrollOzD1aCk, this.connection.mo139onPreScrollOzD1aCk(Offset.m314minusMKHz9U(j, mo139onPreScrollOzD1aCk), i));
    }
}
