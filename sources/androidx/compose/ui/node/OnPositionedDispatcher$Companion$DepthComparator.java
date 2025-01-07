package androidx.compose.ui.node;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OnPositionedDispatcher$Companion$DepthComparator implements Comparator {
    public static final OnPositionedDispatcher$Companion$DepthComparator INSTANCE = new OnPositionedDispatcher$Companion$DepthComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        LayoutNode layoutNode = (LayoutNode) obj;
        LayoutNode layoutNode2 = (LayoutNode) obj2;
        int compare = Intrinsics.compare(layoutNode2.depth, layoutNode.depth);
        return compare != 0 ? compare : Intrinsics.compare(layoutNode.hashCode(), layoutNode2.hashCode());
    }
}
