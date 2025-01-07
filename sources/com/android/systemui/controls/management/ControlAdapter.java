package com.android.systemui.controls.management;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.controls.ControlInterface;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlAdapter extends RecyclerView.Adapter {
    public final int currentUserId;
    public final float elevation;
    public ControlsModel model;

    public ControlAdapter(int i, float f) {
        this.elevation = f;
        this.currentUserId = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List elements;
        ControlsModel controlsModel = this.model;
        if (controlsModel == null || (elements = controlsModel.getElements()) == null) {
            return 0;
        }
        return elements.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        ControlsModel controlsModel = this.model;
        if (controlsModel == null) {
            throw new IllegalStateException("Getting item type for null model");
        }
        ElementWrapper elementWrapper = (ElementWrapper) controlsModel.getElements().get(i);
        if (elementWrapper instanceof ZoneNameWrapper) {
            return 0;
        }
        if ((elementWrapper instanceof ControlStatusWrapper) || (elementWrapper instanceof ControlInfoWrapper)) {
            return 1;
        }
        if (elementWrapper instanceof DividerWrapper) {
            return 2;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder) viewHolder;
        ControlsModel controlsModel = this.model;
        if (controlsModel != null) {
            holder.bindData((ElementWrapper) controlsModel.getElements().get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            return new ZoneHolder(from.inflate(R.layout.controls_zone_header, viewGroup, false));
        }
        if (i != 1) {
            if (i == 2) {
                return new DividerHolder(from.inflate(R.layout.controls_horizontal_divider_with_empty, viewGroup, false));
            }
            throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Wrong viewType: "));
        }
        View inflate = from.inflate(R.layout.controls_base_item, viewGroup, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) inflate.getLayoutParams();
        marginLayoutParams.width = -1;
        marginLayoutParams.topMargin = 0;
        marginLayoutParams.bottomMargin = 0;
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
        inflate.setElevation(this.elevation);
        inflate.setBackground(viewGroup.getContext().getDrawable(R.drawable.control_background_ripple));
        ControlsModel controlsModel = this.model;
        return new ControlHolder(inflate, this.currentUserId, controlsModel != null ? controlsModel.getMoveHelper() : null, new Function2() { // from class: com.android.systemui.controls.management.ControlAdapter$onCreateViewHolder$2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                String str = (String) obj;
                boolean booleanValue = ((Boolean) obj2).booleanValue();
                ControlsModel controlsModel2 = ControlAdapter.this.model;
                if (controlsModel2 != null) {
                    controlsModel2.changeFavoriteStatus(str, booleanValue);
                }
                return Unit.INSTANCE;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        Holder holder = (Holder) viewHolder;
        if (list.isEmpty()) {
            onBindViewHolder(holder, i);
            return;
        }
        ControlsModel controlsModel = this.model;
        if (controlsModel != null) {
            Object obj = (ElementWrapper) controlsModel.getElements().get(i);
            if (obj instanceof ControlInterface) {
                holder.updateFavorite(((ControlInterface) obj).getFavorite());
            }
        }
    }
}
