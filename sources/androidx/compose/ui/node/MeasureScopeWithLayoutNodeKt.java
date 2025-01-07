package androidx.compose.ui.node;

import androidx.compose.ui.layout.IntrinsicMeasureScope;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MeasureScopeWithLayoutNodeKt {
    public static final List getChildrenOfVirtualChildren(IntrinsicMeasureScope intrinsicMeasureScope) {
        LayoutNode layoutNode = ((MeasureScopeWithLayoutNode) intrinsicMeasureScope).getLayoutNode();
        boolean isInLookaheadPass = isInLookaheadPass(layoutNode);
        List foldedChildren$ui_release = layoutNode.getFoldedChildren$ui_release();
        ArrayList arrayList = new ArrayList(foldedChildren$ui_release.size());
        int size = foldedChildren$ui_release.size();
        for (int i = 0; i < size; i++) {
            LayoutNode layoutNode2 = (LayoutNode) foldedChildren$ui_release.get(i);
            arrayList.add(isInLookaheadPass ? layoutNode2.getChildLookaheadMeasurables$ui_release() : layoutNode2.getChildMeasurables$ui_release());
        }
        return arrayList;
    }

    public static final boolean isInLookaheadPass(LayoutNode layoutNode) {
        int ordinal = layoutNode.layoutDelegate.layoutState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return true;
            }
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return true;
                }
                if (ordinal != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if (parent$ui_release != null) {
                    return isInLookaheadPass(parent$ui_release);
                }
                throw new IllegalArgumentException("no parent for idle node");
            }
        }
        return false;
    }
}
