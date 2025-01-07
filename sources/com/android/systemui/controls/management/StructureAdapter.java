package com.android.systemui.controls.management;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wm.shell.R;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StructureAdapter extends RecyclerView.Adapter {
    public final int currentUserId;
    public final List models;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StructureHolder extends RecyclerView.ViewHolder {
        public final ControlAdapter controlAdapter;

        public StructureHolder(View view, int i) {
            super(view);
            int i2;
            final RecyclerView recyclerView = (RecyclerView) view.requireViewById(R.id.listAll);
            ControlAdapter controlAdapter = new ControlAdapter(i, view.getContext().getResources().getFloat(R.dimen.control_card_elevation));
            this.controlAdapter = controlAdapter;
            int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R.dimen.controls_card_margin);
            MarginItemDecorator marginItemDecorator = new MarginItemDecorator(dimensionPixelSize, dimensionPixelSize);
            Resources resources = view.getResources();
            final int integer = resources.getInteger(R.integer.controls_max_columns);
            int integer2 = resources.getInteger(R.integer.controls_max_columns_adjust_below_width_dp);
            TypedValue typedValue = new TypedValue();
            resources.getValue(R.dimen.controls_max_columns_adjust_above_font_scale, typedValue, true);
            float f = typedValue.getFloat();
            Configuration configuration = resources.getConfiguration();
            if (configuration.orientation == 1 && (i2 = configuration.screenWidthDp) != 0 && i2 <= integer2 && configuration.fontScale >= f) {
                integer--;
            }
            recyclerView.setAdapter(controlAdapter);
            recyclerView.getContext();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(integer);
            gridLayoutManager.mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: com.android.systemui.controls.management.StructureAdapter$StructureHolder$setUpRecyclerView$1$1$1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public final int getSpanSize(int i3) {
                    RecyclerView.Adapter adapter = RecyclerView.this.mAdapter;
                    if (adapter == null || adapter.getItemViewType(i3) != 1) {
                        return integer;
                    }
                    return 1;
                }
            };
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(marginItemDecorator);
        }
    }

    public StructureAdapter(int i, List list) {
        this.models = list;
        this.currentUserId = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.models.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AllModel allModel = ((StructureContainer) this.models.get(i)).model;
        ControlAdapter controlAdapter = ((StructureHolder) viewHolder).controlAdapter;
        controlAdapter.model = allModel;
        controlAdapter.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        return new StructureHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.controls_structure_page, viewGroup, false), this.currentUserId);
    }
}
