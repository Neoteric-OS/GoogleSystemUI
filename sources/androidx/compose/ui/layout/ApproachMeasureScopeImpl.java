package androidx.compose.ui.layout;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutModifierNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ApproachMeasureScopeImpl implements ApproachMeasureScope, MeasureScope, LookaheadScope {
    public boolean approachMeasureRequired;
    public ApproachLayoutModifierNode approachNode;
    public final LayoutModifierNodeCoordinator coordinator;

    public ApproachMeasureScopeImpl(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator, ApproachLayoutModifierNode approachLayoutModifierNode) {
        this.coordinator = layoutModifierNodeCoordinator;
        this.approachNode = approachLayoutModifierNode;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public final LayoutDirection getLayoutDirection() {
        return this.coordinator.layoutNode.layoutDirection;
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public final LayoutCoordinates getLookaheadScopeCoordinates() {
        InnerNodeCoordinator innerNodeCoordinator;
        LayoutNode layoutNode = this.coordinator.layoutNode.lookaheadRoot;
        if (layoutNode == null) {
            throw new IllegalArgumentException("Error: Requesting LookaheadScopeCoordinates is not permitted from outside of a LookaheadScope.");
        }
        if (!layoutNode.isVirtualLookaheadRoot) {
            return layoutNode.nodes.outerCoordinator;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        return (parent$ui_release == null || (innerNodeCoordinator = parent$ui_release.nodes.innerCoordinator) == null) ? ((LayoutNode) layoutNode.getChildren$ui_release().get(0)).nodes.outerCoordinator : innerNodeCoordinator;
    }

    @Override // androidx.compose.ui.layout.ApproachIntrinsicMeasureScope
    /* renamed from: getLookaheadSize-YbymL2g */
    public final long mo471getLookaheadSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.coordinator.lookaheadDelegate;
        Intrinsics.checkNotNull(lookaheadDelegate);
        MeasureResult measureResult$ui_release = lookaheadDelegate.getMeasureResult$ui_release();
        return (measureResult$ui_release.getWidth() << 32) | (measureResult$ui_release.getHeight() & 4294967295L);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public final boolean isLookingAhead() {
        return false;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final MeasureResult layout(final int i, final int i2, final Map map, final Function1 function1) {
        if ((i & DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT) != 0 || ((-16777216) & i2) != 0) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult(i, i2, map, function1, this) { // from class: androidx.compose.ui.layout.ApproachMeasureScopeImpl$layout$1
            public final /* synthetic */ Function1 $placementBlock;
            public final Map alignmentLines;
            public final int height;
            public final /* synthetic */ ApproachMeasureScopeImpl this$0;
            public final int width;

            {
                this.$placementBlock = function1;
                this.this$0 = this;
                this.width = i;
                this.height = i2;
                this.alignmentLines = map;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final Map getAlignmentLines() {
                return this.alignmentLines;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getHeight() {
                return this.height;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final Function1 getRulers() {
                return null;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final int getWidth() {
                return this.width;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public final void placeChildren() {
                this.$placementBlock.invoke(this.this$0.coordinator.placementScope);
            }
        };
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final MeasureResult layout$1(int i, int i2, Map map, Function1 function1) {
        return this.coordinator.layout(i, i2, map, function1);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public final int mo45roundToPx0680j_4(float f) {
        return this.coordinator.mo45roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toDp-GaN1DYA */
    public final float mo46toDpGaN1DYA(long j) {
        return this.coordinator.mo46toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public final float mo48toDpu2uoSUM(int i) {
        return this.coordinator.mo48toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDpSize-k-rfVVM */
    public final long mo49toDpSizekrfVVM(long j) {
        return this.coordinator.mo49toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public final LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates;
        if (layoutCoordinates instanceof LookaheadLayoutCoordinates) {
            return layoutCoordinates;
        }
        if (layoutCoordinates instanceof NodeCoordinator) {
            LookaheadDelegate lookaheadDelegate = ((NodeCoordinator) layoutCoordinates).getLookaheadDelegate();
            return (lookaheadDelegate == null || (lookaheadLayoutCoordinates = lookaheadDelegate.lookaheadLayoutCoordinates) == null) ? layoutCoordinates : lookaheadLayoutCoordinates;
        }
        throw new IllegalArgumentException("Unsupported LayoutCoordinates: " + layoutCoordinates);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public final float mo50toPxR2X_6o(long j) {
        return this.coordinator.mo50toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public final float mo51toPx0680j_4(float f) {
        return this.coordinator.getDensity() * f;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public final long mo52toSizeXkaWNTQ(long j) {
        return this.coordinator.mo52toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toSp-0xMU5do */
    public final long mo53toSp0xMU5do(float f) {
        return this.coordinator.mo53toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public final long mo54toSpkPz2Gy4(float f) {
        return this.coordinator.mo54toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public final float mo47toDpu2uoSUM(float f) {
        return f / this.coordinator.getDensity();
    }
}
