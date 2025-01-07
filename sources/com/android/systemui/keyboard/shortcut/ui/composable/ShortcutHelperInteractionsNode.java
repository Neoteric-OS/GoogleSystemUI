package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperInteractionsNode extends Modifier.Node implements DrawModifierNode {
    public final InteractionSource interactionSource;
    public final InteractionsConfig interactionsConfig;
    public final MutableState isFocused;
    public final MutableState isHovered;
    public final MutableState isPressed;

    public ShortcutHelperInteractionsNode(InteractionSource interactionSource, InteractionsConfig interactionsConfig) {
        this.interactionSource = interactionSource;
        this.interactionsConfig = interactionsConfig;
        Boolean bool = Boolean.FALSE;
        this.isFocused = SnapshotStateKt.mutableStateOf$default(bool);
        this.isHovered = SnapshotStateKt.mutableStateOf$default(bool);
        this.isPressed = SnapshotStateKt.mutableStateOf$default(bool);
    }

    public static final Rect draw$getRectangleWithPadding(ShortcutHelperInteractionsNode shortcutHelperInteractionsNode, LayoutNodeDrawScope layoutNodeDrawScope, float f, long j) {
        Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(0L, j);
        return Float.compare(shortcutHelperInteractionsNode.interactionsConfig.focusOutlinePadding, (float) 0) > 0 ? m324Recttz77jQw.inflate(layoutNodeDrawScope.mo51toPx0680j_4(f)) : m324Recttz77jQw.inflate(-layoutNodeDrawScope.mo51toPx0680j_4(-f));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        CanvasDrawScope canvasDrawScope;
        InteractionsConfig interactionsConfig;
        layoutNodeDrawScope.drawContent();
        boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) this.isHovered).getValue()).booleanValue();
        InteractionsConfig interactionsConfig2 = this.interactionsConfig;
        CanvasDrawScope canvasDrawScope2 = layoutNodeDrawScope.canvasDrawScope;
        if (booleanValue) {
            Rect draw$getRectangleWithPadding = draw$getRectangleWithPadding(this, layoutNodeDrawScope, interactionsConfig2.pressedPadding, canvasDrawScope2.mo432getSizeNHjbRc());
            float mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(interactionsConfig2.surfaceCornerRadius);
            canvasDrawScope = canvasDrawScope2;
            interactionsConfig = interactionsConfig2;
            DrawScope.m429drawRoundRectuAw5IA$default(layoutNodeDrawScope, interactionsConfig2.hoverOverlayColor, draw$getRectangleWithPadding.m322getTopLeftF1C5BW0(), draw$getRectangleWithPadding.m321getSizeNHjbRc(), (Float.floatToRawIntBits(mo51toPx0680j_4) << 32) | (Float.floatToRawIntBits(mo51toPx0680j_4) & 4294967295L), null, interactionsConfig2.hoverOverlayAlpha, 208);
        } else {
            canvasDrawScope = canvasDrawScope2;
            interactionsConfig = interactionsConfig2;
        }
        if (((Boolean) ((SnapshotMutableStateImpl) this.isPressed).getValue()).booleanValue()) {
            Rect draw$getRectangleWithPadding2 = draw$getRectangleWithPadding(this, layoutNodeDrawScope, interactionsConfig.pressedPadding, canvasDrawScope.mo432getSizeNHjbRc());
            float mo51toPx0680j_42 = layoutNodeDrawScope.mo51toPx0680j_4(interactionsConfig.surfaceCornerRadius);
            DrawScope.m429drawRoundRectuAw5IA$default(layoutNodeDrawScope, interactionsConfig.pressedOverlayColor, draw$getRectangleWithPadding2.m322getTopLeftF1C5BW0(), draw$getRectangleWithPadding2.m321getSizeNHjbRc(), (Float.floatToRawIntBits(mo51toPx0680j_42) << 32) | (Float.floatToRawIntBits(mo51toPx0680j_42) & 4294967295L), null, interactionsConfig.pressedOverlayAlpha, 208);
        }
        if (((Boolean) ((SnapshotMutableStateImpl) this.isFocused).getValue()).booleanValue()) {
            Rect draw$getRectangleWithPadding3 = draw$getRectangleWithPadding(this, layoutNodeDrawScope, interactionsConfig.focusOutlinePadding, canvasDrawScope.mo432getSizeNHjbRc());
            Stroke stroke = new Stroke(layoutNodeDrawScope.mo51toPx0680j_4(interactionsConfig.focusOutlineStrokeWidth), 0.0f, 0, 0, 30);
            DrawScope.m429drawRoundRectuAw5IA$default(layoutNodeDrawScope, interactionsConfig.focusOutlineColor, draw$getRectangleWithPadding3.m322getTopLeftF1C5BW0(), draw$getRectangleWithPadding3.m321getSizeNHjbRc(), (Float.floatToRawIntBits(r0) & 4294967295L) | (Float.floatToRawIntBits(layoutNodeDrawScope.mo51toPx0680j_4(interactionsConfig.focusOutlineCornerRadius)) << 32), stroke, 0.0f, 224);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        BuildersKt.launch$default(getCoroutineScope(), null, null, new ShortcutHelperInteractionsNode$onAttach$1(this, null), 3);
    }
}
