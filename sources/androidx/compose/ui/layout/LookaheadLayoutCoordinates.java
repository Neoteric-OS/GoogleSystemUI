package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LookaheadLayoutCoordinates implements LayoutCoordinates {
    public final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinates(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    /* renamed from: getLookaheadOffset-F1C5BW0, reason: not valid java name */
    public final long m491getLookaheadOffsetF1C5BW0() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
        return Offset.m314minusMKHz9U(mo483localPositionOfS_NoaFU(rootLookaheadDelegate.lookaheadLayoutCoordinates, 0L), lookaheadDelegate.coordinator.mo483localPositionOfS_NoaFU(rootLookaheadDelegate.coordinator, 0L));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        NodeCoordinator nodeCoordinator = this.lookaheadDelegate.coordinator.layoutNode.nodes.outerCoordinator.wrappedBy;
        if (nodeCoordinator == null || (lookaheadDelegate = nodeCoordinator.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.lookaheadLayoutCoordinates;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public final long mo481getSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        return (lookaheadDelegate.width << 32) | (lookaheadDelegate.height & 4294967295L);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final boolean isAttached() {
        return this.lookaheadDelegate.coordinator.getTail().isAttached;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final Rect localBoundingBoxOf(LayoutCoordinates layoutCoordinates, boolean z) {
        return this.lookaheadDelegate.coordinator.localBoundingBoxOf(layoutCoordinates, z);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public final long mo482localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j) {
        return mo483localPositionOfS_NoaFU(layoutCoordinates, j);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-S_NoaFU */
    public final long mo483localPositionOfS_NoaFU(LayoutCoordinates layoutCoordinates, long j) {
        boolean z = layoutCoordinates instanceof LookaheadLayoutCoordinates;
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (!z) {
            LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
            long mo483localPositionOfS_NoaFU = mo483localPositionOfS_NoaFU(rootLookaheadDelegate.lookaheadLayoutCoordinates, j);
            float f = (int) (rootLookaheadDelegate.position & 4294967295L);
            long m314minusMKHz9U = Offset.m314minusMKHz9U(mo483localPositionOfS_NoaFU, (Float.floatToRawIntBits(f) & 4294967295L) | (Float.floatToRawIntBits((int) (r5 >> 32)) << 32));
            NodeCoordinator nodeCoordinator = rootLookaheadDelegate.coordinator;
            if (!nodeCoordinator.getTail().isAttached) {
                InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
            }
            nodeCoordinator.onCoordinatesUsed$ui_release();
            NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrappedBy;
            if (nodeCoordinator2 != null) {
                nodeCoordinator = nodeCoordinator2;
            }
            return Offset.m315plusMKHz9U(m314minusMKHz9U, nodeCoordinator.mo483localPositionOfS_NoaFU(layoutCoordinates, 0L));
        }
        LookaheadDelegate lookaheadDelegate2 = ((LookaheadLayoutCoordinates) layoutCoordinates).lookaheadDelegate;
        lookaheadDelegate2.coordinator.onCoordinatesUsed$ui_release();
        LookaheadDelegate lookaheadDelegate3 = lookaheadDelegate.coordinator.findCommonAncestor$ui_release(lookaheadDelegate2.coordinator).getLookaheadDelegate();
        if (lookaheadDelegate3 != null) {
            long m675minusqkQi6aY = IntOffset.m675minusqkQi6aY(IntOffset.m676plusqkQi6aY(lookaheadDelegate2.m520positionIniSbpLlY$ui_release(lookaheadDelegate3, false), IntOffsetKt.m679roundk4lQ0M(j)), lookaheadDelegate.m520positionIniSbpLlY$ui_release(lookaheadDelegate3, false));
            return (Float.floatToRawIntBits((int) (m675minusqkQi6aY >> 32)) << 32) | (Float.floatToRawIntBits((int) (m675minusqkQi6aY & 4294967295L)) & 4294967295L);
        }
        LookaheadDelegate rootLookaheadDelegate2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate2);
        long m676plusqkQi6aY = IntOffset.m676plusqkQi6aY(IntOffset.m676plusqkQi6aY(lookaheadDelegate2.m520positionIniSbpLlY$ui_release(rootLookaheadDelegate2, false), rootLookaheadDelegate2.position), IntOffsetKt.m679roundk4lQ0M(j));
        LookaheadDelegate rootLookaheadDelegate3 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
        long m675minusqkQi6aY2 = IntOffset.m675minusqkQi6aY(m676plusqkQi6aY, IntOffset.m676plusqkQi6aY(lookaheadDelegate.m520positionIniSbpLlY$ui_release(rootLookaheadDelegate3, false), rootLookaheadDelegate3.position));
        long floatToRawIntBits = Float.floatToRawIntBits((int) (m675minusqkQi6aY2 >> 32));
        long floatToRawIntBits2 = Float.floatToRawIntBits((int) (m675minusqkQi6aY2 & 4294967295L)) & 4294967295L;
        NodeCoordinator nodeCoordinator3 = rootLookaheadDelegate3.coordinator.wrappedBy;
        Intrinsics.checkNotNull(nodeCoordinator3);
        NodeCoordinator nodeCoordinator4 = rootLookaheadDelegate2.coordinator.wrappedBy;
        Intrinsics.checkNotNull(nodeCoordinator4);
        return nodeCoordinator3.mo483localPositionOfS_NoaFU(nodeCoordinator4, floatToRawIntBits2 | (floatToRawIntBits << 32));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public final long mo484localToRootMKHz9U(long j) {
        return this.lookaheadDelegate.coordinator.mo484localToRootMKHz9U(Offset.m315plusMKHz9U(j, m491getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToScreen-MK-Hz9U */
    public final long mo485localToScreenMKHz9U(long j) {
        return this.lookaheadDelegate.coordinator.mo485localToScreenMKHz9U(Offset.m315plusMKHz9U(0L, m491getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public final long mo486localToWindowMKHz9U(long j) {
        return this.lookaheadDelegate.coordinator.mo486localToWindowMKHz9U(Offset.m315plusMKHz9U(j, m491getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: screenToLocal-MK-Hz9U */
    public final long mo487screenToLocalMKHz9U(long j) {
        return Offset.m315plusMKHz9U(this.lookaheadDelegate.coordinator.mo487screenToLocalMKHz9U(j), m491getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: transformFrom-EL8BTi8 */
    public final void mo488transformFromEL8BTi8(LayoutCoordinates layoutCoordinates, float[] fArr) {
        this.lookaheadDelegate.coordinator.mo488transformFromEL8BTi8(layoutCoordinates, fArr);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: windowToLocal-MK-Hz9U */
    public final long mo489windowToLocalMKHz9U(long j) {
        return Offset.m315plusMKHz9U(this.lookaheadDelegate.coordinator.mo489windowToLocalMKHz9U(j), m491getLookaheadOffsetF1C5BW0());
    }
}
