package androidx.compose.ui.node;

import androidx.compose.ui.internal.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DepthSortedSet {
    public final TreeSet set = new TreeSet(DepthSortedSetKt.DepthComparator);

    public final void add(LayoutNode layoutNode) {
        if (!layoutNode.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.add called on an unattached node");
        }
        this.set.add(layoutNode);
    }

    public final boolean remove(LayoutNode layoutNode) {
        if (!layoutNode.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.remove called on an unattached node");
        }
        return this.set.remove(layoutNode);
    }

    public final String toString() {
        return this.set.toString();
    }
}
