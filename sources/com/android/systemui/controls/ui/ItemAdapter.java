package com.android.systemui.controls.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ItemAdapter extends ArrayAdapter {
    public final LayoutInflater layoutInflater;
    public final int resource;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewHolder {
        public final ImageView iconView;
        public final TextView titleView;

        public ViewHolder(View view) {
            this.titleView = (TextView) view.requireViewById(R.id.controls_spinner_item);
            this.iconView = (ImageView) view.requireViewById(R.id.app_icon);
        }
    }

    public ItemAdapter(Context context) {
        super(context, R.layout.controls_spinner_item);
        this.resource = R.layout.controls_spinner_item;
        LayoutInflater from = LayoutInflater.from(getContext());
        Intrinsics.checkNotNull(from);
        this.layoutInflater = from;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        Object item = getItem(i);
        Intrinsics.checkNotNull(item);
        SelectionItem selectionItem = (SelectionItem) item;
        if (view == null) {
            view = this.layoutInflater.inflate(this.resource, viewGroup, false);
        }
        Object tag = view.getTag();
        ViewHolder viewHolder = tag instanceof ViewHolder ? (ViewHolder) tag : null;
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.titleView.setText(selectionItem.getTitle());
        viewHolder.iconView.setImageDrawable(selectionItem.icon);
        return view;
    }
}
