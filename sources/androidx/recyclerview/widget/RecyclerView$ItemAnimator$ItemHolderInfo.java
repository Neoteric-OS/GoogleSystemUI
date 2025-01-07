package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecyclerView$ItemAnimator$ItemHolderInfo {
    public int left;
    public int top;

    public final void setFrom(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        this.left = view.getLeft();
        this.top = view.getTop();
        view.getRight();
        view.getBottom();
    }
}
