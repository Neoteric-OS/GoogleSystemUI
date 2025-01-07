package androidx.compose.ui.layout;

import androidx.compose.ui.layout.LookaheadScopeKt$LookaheadScope$2$2;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LookaheadScopeImpl implements LookaheadScope {
    public Function0 scopeCoordinates;

    @Override // androidx.compose.ui.layout.LookaheadScope
    public final LayoutCoordinates getLookaheadScopeCoordinates() {
        Function0 function0 = this.scopeCoordinates;
        Intrinsics.checkNotNull(function0);
        return (LayoutCoordinates) ((LookaheadScopeKt$LookaheadScope$2$2.AnonymousClass1) function0).invoke();
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public final LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates2 = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        if (lookaheadLayoutCoordinates2 != null) {
            return lookaheadLayoutCoordinates2;
        }
        NodeCoordinator nodeCoordinator = (NodeCoordinator) layoutCoordinates;
        LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
        return (lookaheadDelegate == null || (lookaheadLayoutCoordinates = lookaheadDelegate.lookaheadLayoutCoordinates) == null) ? nodeCoordinator : lookaheadLayoutCoordinates;
    }
}
