package androidx.compose.foundation;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultDebugIndication implements IndicationNodeFactory {
    public static final DefaultDebugIndication INSTANCE = new DefaultDebugIndication();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class DefaultDebugIndicationInstance extends Modifier.Node implements DrawModifierNode {
        public final InteractionSource interactionSource;
        public boolean isFocused;
        public boolean isHovered;
        public boolean isPressed;

        public DefaultDebugIndicationInstance(InteractionSource interactionSource) {
            this.interactionSource = interactionSource;
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
            long Color;
            long Color2;
            layoutNodeDrawScope.drawContent();
            boolean z = this.isPressed;
            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
            if (z) {
                Color2 = ColorKt.Color(Color.m368getRedimpl(r2), Color.m367getGreenimpl(r2), Color.m365getBlueimpl(r2), 0.3f, Color.m366getColorSpaceimpl(Color.Black));
                layoutNodeDrawScope.mo415drawRectnJ9OG0(Color2, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(layoutNodeDrawScope.mo432getSizeNHjbRc(), 0L) : canvasDrawScope.mo432getSizeNHjbRc(), (r19 & 8) != 0 ? 1.0f : 0.0f, (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
            } else if (this.isHovered || this.isFocused) {
                Color = ColorKt.Color(Color.m368getRedimpl(r2), Color.m367getGreenimpl(r2), Color.m365getBlueimpl(r2), 0.1f, Color.m366getColorSpaceimpl(Color.Black));
                layoutNodeDrawScope.mo415drawRectnJ9OG0(Color, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(layoutNodeDrawScope.mo432getSizeNHjbRc(), 0L) : canvasDrawScope.mo432getSizeNHjbRc(), (r19 & 8) != 0 ? 1.0f : 0.0f, (r19 & 32) != 0 ? null : null, (r19 & 64) != 0 ? 3 : 0);
            }
        }

        @Override // androidx.compose.ui.Modifier.Node
        public final void onAttach() {
            BuildersKt.launch$default(getCoroutineScope(), null, null, new DefaultDebugIndication$DefaultDebugIndicationInstance$onAttach$1(this, null), 3);
        }
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final DelegatableNode create(InteractionSource interactionSource) {
        return new DefaultDebugIndicationInstance(interactionSource);
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final int hashCode() {
        return -1;
    }
}
