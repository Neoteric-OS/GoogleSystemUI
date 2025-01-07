package androidx.compose.ui.layout;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LookaheadDelegate;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LookaheadLayoutCoordinatesKt {
    public static final LookaheadDelegate getRootLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        LayoutNode layoutNode = lookaheadDelegate.coordinator.layoutNode;
        while (true) {
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if ((parent$ui_release != null ? parent$ui_release.lookaheadRoot : null) == null) {
                LookaheadDelegate lookaheadDelegate2 = layoutNode.nodes.outerCoordinator.getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate2);
                return lookaheadDelegate2;
            }
            LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
            LayoutNode layoutNode2 = parent$ui_release2 != null ? parent$ui_release2.lookaheadRoot : null;
            Intrinsics.checkNotNull(layoutNode2);
            if (layoutNode2.isVirtualLookaheadRoot) {
                layoutNode = layoutNode.getParent$ui_release();
                Intrinsics.checkNotNull(layoutNode);
            } else {
                LayoutNode parent$ui_release3 = layoutNode.getParent$ui_release();
                Intrinsics.checkNotNull(parent$ui_release3);
                layoutNode = parent$ui_release3.lookaheadRoot;
                Intrinsics.checkNotNull(layoutNode);
            }
        }
    }
}
