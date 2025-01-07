package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$request$1 extends FunctionReferenceImpl implements Function1 {
    final /* synthetic */ LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode $node;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$request$1(LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode legacyPlatformTextInputNode) {
        super(1, Intrinsics.Kotlin.class, "localToScreen", "startInput$localToScreen(Landroidx/compose/foundation/text/input/internal/LegacyPlatformTextInputServiceAdapter$LegacyPlatformTextInputNode;[F)V", 0);
        this.$node = legacyPlatformTextInputNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NodeCoordinator nodeCoordinator;
        LayoutNode layoutNode;
        float[] fArr = ((Matrix) obj).values;
        LayoutCoordinates layoutCoordinates = (LayoutCoordinates) ((SnapshotMutableStateImpl) ((LegacyAdaptingPlatformTextInputModifierNode) this.$node).layoutCoordinates$delegate).getValue();
        if (layoutCoordinates != null) {
            if (!layoutCoordinates.isAttached()) {
                layoutCoordinates = null;
            }
            if (layoutCoordinates != null) {
                LayoutCoordinates findRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates);
                float[] m379constructorimpl$default = Matrix.m379constructorimpl$default();
                findRootCoordinates.mo488transformFromEL8BTi8(layoutCoordinates, m379constructorimpl$default);
                Matrix.m383timesAssign58bKbWc(fArr, m379constructorimpl$default);
                LookaheadLayoutCoordinates lookaheadLayoutCoordinates = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
                if (lookaheadLayoutCoordinates == null || (nodeCoordinator = lookaheadLayoutCoordinates.lookaheadDelegate.coordinator) == null) {
                    nodeCoordinator = layoutCoordinates instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates : null;
                }
                Object requireOwner = (nodeCoordinator == null || (layoutNode = nodeCoordinator.layoutNode) == null) ? null : LayoutNodeKt.requireOwner(layoutNode);
                MatrixPositionCalculator matrixPositionCalculator = requireOwner instanceof MatrixPositionCalculator ? (MatrixPositionCalculator) requireOwner : null;
                if (matrixPositionCalculator != null) {
                    ((AndroidComposeView) matrixPositionCalculator).m554localToScreen58bKbWc(fArr);
                } else {
                    long mo485localToScreenMKHz9U = findRootCoordinates.mo485localToScreenMKHz9U(0L);
                    if ((9223372034707292159L & mo485localToScreenMKHz9U) != 9205357640488583168L) {
                        Matrix.m384translateimpl(fArr, Float.intBitsToFloat((int) (mo485localToScreenMKHz9U >> 32)), Float.intBitsToFloat((int) (mo485localToScreenMKHz9U & 4294967295L)));
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
