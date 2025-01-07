package com.android.systemui.globalactions;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsPopupMenu extends ListPopupWindow {
    public ListAdapter mAdapter;
    public Context mContext;
    public int mGlobalActionsSidePadding;
    public int mMaximumWidthThresholdDp;
    public int mMenuVerticalPadding;
    public GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda5 mOnItemLongClickListener;

    @Override // android.widget.ListPopupWindow
    public final void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        super.setAdapter(listAdapter);
    }

    @Override // android.widget.ListPopupWindow
    public final void show() {
        super.show();
        if (this.mOnItemLongClickListener != null) {
            getListView().setOnItemLongClickListener(this.mOnItemLongClickListener);
        }
        ListView listView = getListView();
        this.mContext.getResources();
        setVerticalOffset((-getAnchorView().getHeight()) / 2);
        if (this.mAdapter == null) {
            return;
        }
        int i = Resources.getSystem().getDisplayMetrics().widthPixels;
        float f = i / Resources.getSystem().getDisplayMetrics().density;
        double d = i;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (0.9d * d), Integer.MIN_VALUE);
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAdapter.getCount(); i3++) {
            View view = this.mAdapter.getView(i3, null, listView);
            view.measure(makeMeasureSpec, 0);
            i2 = Math.max(view.getMeasuredWidth(), i2);
        }
        if (f < this.mMaximumWidthThresholdDp) {
            i2 = Math.max(i2, (int) (d * 0.5d));
        }
        int i4 = this.mMenuVerticalPadding;
        listView.setPadding(0, i4, 0, i4);
        setWidth(i2);
        if (getAnchorView().getLayoutDirection() == 0) {
            setHorizontalOffset((getAnchorView().getWidth() - this.mGlobalActionsSidePadding) - i2);
        } else {
            setHorizontalOffset(this.mGlobalActionsSidePadding);
        }
        super.show();
    }
}
