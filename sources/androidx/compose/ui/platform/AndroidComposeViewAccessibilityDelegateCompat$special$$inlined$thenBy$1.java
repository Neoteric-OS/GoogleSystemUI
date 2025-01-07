package androidx.compose.ui.platform;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.semantics.SemanticsNode;
import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1 implements Comparator {
    public final /* synthetic */ Comparator $this_thenBy;

    public AndroidComposeViewAccessibilityDelegateCompat$special$$inlined$thenBy$1(Comparator comparator) {
        LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
        this.$this_thenBy = comparator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare = this.$this_thenBy.compare(obj, obj2);
        return compare != 0 ? compare : LayoutNode.ZComparator.compare(((SemanticsNode) obj).layoutNode, ((SemanticsNode) obj2).layoutNode);
    }
}
