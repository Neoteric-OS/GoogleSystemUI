package androidx.compose.ui.node;

import androidx.compose.foundation.text.handwriting.StylusHandwritingNodeWithNegativePadding;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PointerInputModifierNode extends DelegatableNode {
    void onCancelPointerInput();

    default void onDensityChange() {
        onCancelPointerInput();
    }

    /* renamed from: onPointerEvent-H0pRuoY */
    void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j);

    default void onViewConfigurationChange() {
        onCancelPointerInput();
    }

    default boolean sharePointerInputWithSiblings() {
        return this instanceof StylusHandwritingNodeWithNegativePadding;
    }

    default void interceptOutOfBoundsChildEvents() {
    }
}
