package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.Rect;
import java.util.Comparator;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TopBottomBoundsComparator implements Comparator {
    public static final TopBottomBoundsComparator INSTANCE = new TopBottomBoundsComparator();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Pair pair = (Pair) obj;
        Pair pair2 = (Pair) obj2;
        int compare = Float.compare(((Rect) pair.getFirst()).top, ((Rect) pair2.getFirst()).top);
        return compare != 0 ? compare : Float.compare(((Rect) pair.getFirst()).bottom, ((Rect) pair2.getFirst()).bottom);
    }
}
