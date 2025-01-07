package androidx.compose.ui.node;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DepthSortedSetsForDifferentPasses {
    public final DepthSortedSet lookaheadSet = new DepthSortedSet();
    public final DepthSortedSet set = new DepthSortedSet();

    public final void add(LayoutNode layoutNode, boolean z) {
        DepthSortedSet depthSortedSet = this.set;
        DepthSortedSet depthSortedSet2 = this.lookaheadSet;
        if (z) {
            depthSortedSet2.add(layoutNode);
            depthSortedSet.add(layoutNode);
        } else {
            if (depthSortedSet2.set.contains(layoutNode)) {
                return;
            }
            depthSortedSet.add(layoutNode);
        }
    }

    public final boolean contains(LayoutNode layoutNode, boolean z) {
        boolean contains = this.lookaheadSet.set.contains(layoutNode);
        return z ? contains : contains || this.set.set.contains(layoutNode);
    }

    public final boolean isNotEmpty() {
        return !(this.set.set.isEmpty() && this.lookaheadSet.set.isEmpty());
    }
}
