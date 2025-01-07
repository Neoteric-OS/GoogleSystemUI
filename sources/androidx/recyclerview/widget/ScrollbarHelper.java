package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScrollbarHelper {
    public static int computeScrollExtent(RecyclerView.State state, OrientationHelper$1 orientationHelper$1, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(RecyclerView.LayoutManager.getPosition(view) - RecyclerView.LayoutManager.getPosition(view2)) + 1;
        }
        return Math.min(orientationHelper$1.getTotalSpace(), orientationHelper$1.getDecoratedEnd(view2) - orientationHelper$1.getDecoratedStart(view));
    }

    public static int computeScrollOffset(RecyclerView.State state, OrientationHelper$1 orientationHelper$1, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (state.getItemCount() - Math.max(RecyclerView.LayoutManager.getPosition(view), RecyclerView.LayoutManager.getPosition(view2))) - 1) : Math.max(0, Math.min(RecyclerView.LayoutManager.getPosition(view), RecyclerView.LayoutManager.getPosition(view2)));
        if (z) {
            return Math.round((max * (Math.abs(orientationHelper$1.getDecoratedEnd(view2) - orientationHelper$1.getDecoratedStart(view)) / (Math.abs(RecyclerView.LayoutManager.getPosition(view) - RecyclerView.LayoutManager.getPosition(view2)) + 1))) + (orientationHelper$1.getStartAfterPadding() - orientationHelper$1.getDecoratedStart(view)));
        }
        return max;
    }

    public static int computeScrollRange(RecyclerView.State state, OrientationHelper$1 orientationHelper$1, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return state.getItemCount();
        }
        return (int) (((orientationHelper$1.getDecoratedEnd(view2) - orientationHelper$1.getDecoratedStart(view)) / (Math.abs(RecyclerView.LayoutManager.getPosition(view) - RecyclerView.LayoutManager.getPosition(view2)) + 1)) * state.getItemCount());
    }
}
