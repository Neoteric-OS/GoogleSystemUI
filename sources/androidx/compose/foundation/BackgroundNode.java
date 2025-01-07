package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BackgroundNode extends Modifier.Node implements DrawModifierNode, ObserverModifierNode {
    public float alpha;
    public Brush brush;
    public long color;
    public LayoutDirection lastLayoutDirection;
    public Outline lastOutline;
    public Shape lastShape;
    public long lastSize;
    public Shape shape;
    public Outline tmpOutline;

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(final LayoutNodeDrawScope layoutNodeDrawScope) {
        Outline outline;
        if (this.shape == RectangleShapeKt.RectangleShape) {
            if (!Color.m363equalsimpl0(this.color, Color.Unspecified)) {
                layoutNodeDrawScope.mo415drawRectnJ9OG0(this.color, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(layoutNodeDrawScope.mo432getSizeNHjbRc(), 0L) : 0L, (r19 & 8) != 0 ? 1.0f : 0.0f, (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
            }
            Brush brush = this.brush;
            if (brush != null) {
                DrawScope.m426drawRectAsUm42w$default(layoutNodeDrawScope, brush, 0L, 0L, this.alpha, null, 118);
            }
        } else {
            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
            if (Size.m326equalsimpl0(canvasDrawScope.mo432getSizeNHjbRc(), this.lastSize) && layoutNodeDrawScope.getLayoutDirection() == this.lastLayoutDirection && Intrinsics.areEqual(this.lastShape, this.shape)) {
                outline = this.lastOutline;
                Intrinsics.checkNotNull(outline);
            } else {
                ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.BackgroundNode$getOutline$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        BackgroundNode backgroundNode = BackgroundNode.this;
                        backgroundNode.tmpOutline = backgroundNode.shape.mo37createOutlinePq9zytI(((LayoutNodeDrawScope) layoutNodeDrawScope).canvasDrawScope.mo432getSizeNHjbRc(), ((LayoutNodeDrawScope) layoutNodeDrawScope).getLayoutDirection(), layoutNodeDrawScope);
                        return Unit.INSTANCE;
                    }
                });
                outline = this.tmpOutline;
                this.tmpOutline = null;
            }
            this.lastOutline = outline;
            this.lastSize = canvasDrawScope.mo432getSizeNHjbRc();
            this.lastLayoutDirection = layoutNodeDrawScope.getLayoutDirection();
            this.lastShape = this.shape;
            Intrinsics.checkNotNull(outline);
            if (!Color.m363equalsimpl0(this.color, Color.Unspecified)) {
                OutlineKt.m387drawOutlinewDX37Ww$default(layoutNodeDrawScope, outline, this.color);
            }
            Brush brush2 = this.brush;
            if (brush2 != null) {
                OutlineKt.m386drawOutlinehn5TExg$default(layoutNodeDrawScope, outline, brush2, this.alpha);
            }
        }
        layoutNodeDrawScope.drawContent();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        this.lastSize = 9205357640488583168L;
        this.lastLayoutDirection = null;
        this.lastOutline = null;
        this.lastShape = null;
        DrawModifierNodeKt.invalidateDraw(this);
    }
}
