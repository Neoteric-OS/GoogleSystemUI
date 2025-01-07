package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.semantics.SemanticsNode;
import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RtlBoundsComparator implements Comparator {
    public static final RtlBoundsComparator INSTANCE = new RtlBoundsComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Rect boundsInWindow = ((SemanticsNode) obj).getBoundsInWindow();
        Rect boundsInWindow2 = ((SemanticsNode) obj2).getBoundsInWindow();
        int compare = Float.compare(boundsInWindow2.right, boundsInWindow.right);
        if (compare != 0) {
            return compare;
        }
        int compare2 = Float.compare(boundsInWindow.top, boundsInWindow2.top);
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = Float.compare(boundsInWindow.bottom, boundsInWindow2.bottom);
        return compare3 != 0 ? compare3 : Float.compare(boundsInWindow2.left, boundsInWindow.left);
    }
}
