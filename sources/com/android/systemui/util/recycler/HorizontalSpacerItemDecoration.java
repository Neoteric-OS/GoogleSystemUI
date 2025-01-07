package com.android.systemui.util.recycler;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HorizontalSpacerItemDecoration extends RecyclerView.ItemDecoration {
    public final int offset;

    public HorizontalSpacerItemDecoration(int i) {
        this.offset = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        recyclerView.getClass();
        int childAdapterPosition = RecyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.mAdapter;
        int itemCount = adapter != null ? adapter.getItemCount() : 0;
        int i = this.offset;
        int i2 = childAdapterPosition == 0 ? i * 2 : i;
        if (childAdapterPosition == itemCount - 1) {
            i *= 2;
        }
        rect.set(i2, 0, i, 0);
    }
}
