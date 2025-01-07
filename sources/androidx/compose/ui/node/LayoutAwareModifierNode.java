package androidx.compose.ui.node;

import androidx.compose.ui.layout.LayoutCoordinates;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LayoutAwareModifierNode extends DelegatableNode {
    default void onPlaced(LayoutCoordinates layoutCoordinates) {
    }

    /* renamed from: onRemeasured-ozmzZPI */
    default void mo43onRemeasuredozmzZPI(long j) {
    }
}
