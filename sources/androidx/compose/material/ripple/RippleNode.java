package androidx.compose.material.ripple;

import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RippleNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, DrawModifierNode {
    public final boolean bounded;
    public final ColorProducer color;
    public final InteractionSource interactionSource;
    public final float radius;
    public final Function0 rippleAlpha;
    public StateLayer stateLayer;
    public float targetRadius;

    public RippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0) {
        this.interactionSource = interactionSource;
        this.bounded = z;
        this.radius = f;
        this.color = colorProducer;
        this.rippleAlpha = function0;
    }

    public abstract void addRipple(PressInteraction$Press pressInteraction$Press);

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        float mo51toPx0680j_4;
        long Color;
        float f = this.radius;
        boolean isNaN = Float.isNaN(f);
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        if (isNaN) {
            long mo432getSizeNHjbRc = canvasDrawScope.mo432getSizeNHjbRc();
            float f2 = RippleAnimationKt.BoundedRippleExtraRadius;
            mo51toPx0680j_4 = Offset.m311getDistanceimpl(OffsetKt.Offset(Size.m329getWidthimpl(mo432getSizeNHjbRc), Size.m327getHeightimpl(mo432getSizeNHjbRc))) / 2.0f;
            if (this.bounded) {
                mo51toPx0680j_4 += layoutNodeDrawScope.mo51toPx0680j_4(RippleAnimationKt.BoundedRippleExtraRadius);
            }
        } else {
            mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(f);
        }
        this.targetRadius = mo51toPx0680j_4;
        layoutNodeDrawScope.drawContent();
        StateLayer stateLayer = this.stateLayer;
        if (stateLayer != null) {
            float f3 = this.targetRadius;
            long mo206invoke0d7_KjU = this.color.mo206invoke0d7_KjU();
            float floatValue = ((Number) stateLayer.animatedAlpha.internalState.getValue()).floatValue();
            if (floatValue > 0.0f) {
                Color = ColorKt.Color(Color.m368getRedimpl(mo206invoke0d7_KjU), Color.m367getGreenimpl(mo206invoke0d7_KjU), Color.m365getBlueimpl(mo206invoke0d7_KjU), floatValue, Color.m366getColorSpaceimpl(mo206invoke0d7_KjU));
                if (stateLayer.bounded) {
                    float m329getWidthimpl = Size.m329getWidthimpl(canvasDrawScope.mo432getSizeNHjbRc());
                    float m327getHeightimpl = Size.m327getHeightimpl(canvasDrawScope.mo432getSizeNHjbRc());
                    CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                    long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                    canvasDrawScope$drawContext$1.getCanvas().save();
                    canvasDrawScope$drawContext$1.transform.m420clipRectN_I0leg(0.0f, 0.0f, m329getWidthimpl, m327getHeightimpl, 1);
                    DrawScope.m422drawCircleVaOC9Bg$default(layoutNodeDrawScope, Color, f3, 0L, 0.0f, 124);
                    BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                } else {
                    DrawScope.m422drawCircleVaOC9Bg$default(layoutNodeDrawScope, Color, f3, 0L, 0.0f, 124);
                }
            }
        }
        drawRipples(layoutNodeDrawScope);
    }

    public abstract void drawRipples(LayoutNodeDrawScope layoutNodeDrawScope);

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        BuildersKt.launch$default(getCoroutineScope(), null, null, new RippleNode$onAttach$1(this, null), 3);
    }

    public abstract void removeRipple(PressInteraction$Press pressInteraction$Press);
}
