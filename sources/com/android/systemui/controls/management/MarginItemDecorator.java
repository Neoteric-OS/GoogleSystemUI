package com.android.systemui.controls.management;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MarginItemDecorator extends RecyclerView.ItemDecoration {
    public final int sideMargins;
    public final int topMargin;

    public MarginItemDecorator(int i, int i2) {
        this.topMargin = i;
        this.sideMargins = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        recyclerView.getClass();
        int childAdapterPosition = RecyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1) {
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.mAdapter;
        Integer valueOf = adapter != null ? Integer.valueOf(adapter.getItemViewType(childAdapterPosition)) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            rect.top = this.topMargin * 2;
            int i = this.sideMargins;
            rect.left = i;
            rect.right = i;
            rect.bottom = 0;
            return;
        }
        if (valueOf != null && valueOf.intValue() == 0 && childAdapterPosition == 0) {
            rect.top = -((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
            rect.left = 0;
            rect.right = 0;
            rect.bottom = 0;
        }
    }
}
