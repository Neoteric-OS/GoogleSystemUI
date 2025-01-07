package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusableChildrenComparator implements Comparator {
    public static final FocusableChildrenComparator INSTANCE = new FocusableChildrenComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) obj2;
        int i = 0;
        if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode) && FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
            LayoutNode requireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
            if (!Intrinsics.areEqual(requireLayoutNode, requireLayoutNode2)) {
                MutableVector mutableVector = new MutableVector(new LayoutNode[16]);
                while (requireLayoutNode != null) {
                    mutableVector.add(0, requireLayoutNode);
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                }
                MutableVector mutableVector2 = new MutableVector(new LayoutNode[16]);
                while (requireLayoutNode2 != null) {
                    mutableVector2.add(0, requireLayoutNode2);
                    requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                }
                int min = Math.min(mutableVector.size - 1, mutableVector2.size - 1);
                if (min >= 0) {
                    while (Intrinsics.areEqual(mutableVector.content[i], mutableVector2.content[i])) {
                        if (i != min) {
                            i++;
                        }
                    }
                    return Intrinsics.compare(((LayoutNode) mutableVector.content[i]).getPlaceOrder$ui_release(), ((LayoutNode) mutableVector2.content[i]).getPlaceOrder$ui_release());
                }
                throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.");
            }
        } else {
            if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode)) {
                return -1;
            }
            if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
                return 1;
            }
        }
        return 0;
    }
}
