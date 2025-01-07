package androidx.compose.ui.platform;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SemanticsNodeCopy {
    public final MutableIntSet children;
    public final boolean isTransparent;
    public final SemanticsConfiguration unmergedConfig;

    public SemanticsNodeCopy(SemanticsNode semanticsNode, MutableIntObjectMap mutableIntObjectMap) {
        NodeCoordinator findCoordinatorToGetBounds$ui_release;
        this.unmergedConfig = semanticsNode.unmergedConfig;
        this.isTransparent = ((semanticsNode.getParent() == null) || (findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release()) == null) ? false : findCoordinatorToGetBounds$ui_release.isTransparent();
        this.children = new MutableIntSet(SemanticsNode.getChildren$ui_release$default(semanticsNode, 4).size());
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, 4);
        int size = children$ui_release$default.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode2 = (SemanticsNode) children$ui_release$default.get(i);
            if (mutableIntObjectMap.contains(semanticsNode2.id)) {
                this.children.add(semanticsNode2.id);
            }
        }
    }
}
