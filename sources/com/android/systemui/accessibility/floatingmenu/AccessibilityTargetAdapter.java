package com.android.systemui.accessibility.floatingmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.accessibility.dialog.AccessibilityTarget;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityTargetAdapter extends RecyclerView.Adapter {
    public int mIconWidthHeight;
    public int mItemPadding;
    public final List mTargets;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TopViewHolder extends ViewHolder {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TopViewHolder(View view, int i) {
            super(view);
            this.$r8$classId = i;
        }

        @Override // com.android.systemui.accessibility.floatingmenu.AccessibilityTargetAdapter.ViewHolder
        public final void updateItemPadding(int i, int i2) {
            switch (this.$r8$classId) {
                case 0:
                    this.itemView.setPaddingRelative(i, i, i, i2 <= 1 ? i : 0);
                    break;
                default:
                    this.itemView.setPaddingRelative(i, i, i, i);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mIconView;

        public ViewHolder(View view) {
            super(view);
            this.mIconView = view.findViewById(R.id.icon_view);
        }

        public void updateItemPadding(int i, int i2) {
            this.itemView.setPaddingRelative(i, i, i, 0);
        }
    }

    public AccessibilityTargetAdapter(List list) {
        this.mTargets = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mTargets.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (i == this.mTargets.size() - 1) {
            return 2;
        }
        return i == 0 ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        final AccessibilityTarget accessibilityTarget = (AccessibilityTarget) ((ArrayList) this.mTargets).get(i);
        viewHolder2.mIconView.setBackground(accessibilityTarget.getIcon());
        int i2 = this.mIconWidthHeight;
        ViewGroup.LayoutParams layoutParams = viewHolder2.mIconView.getLayoutParams();
        if (layoutParams.width != i2) {
            layoutParams.width = i2;
            layoutParams.height = i2;
            viewHolder2.mIconView.setLayoutParams(layoutParams);
        }
        viewHolder2.updateItemPadding(this.mItemPadding, ((ArrayList) this.mTargets).size());
        viewHolder2.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.accessibility.floatingmenu.AccessibilityTargetAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                accessibilityTarget.onSelected();
            }
        });
        viewHolder2.itemView.setStateDescription(accessibilityTarget.getStateDescription());
        viewHolder2.itemView.setContentDescription(accessibilityTarget.getLabel());
        ViewCompat.replaceAccessibilityAction(viewHolder2.itemView, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, accessibilityTarget.getFragmentType() == 2 ? viewHolder2.itemView.getResources().getString(R.string.accessibility_floating_button_action_double_tap_to_toggle) : null, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        int i2 = 0;
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.accessibility_floating_menu_item, viewGroup, false);
        return i == 0 ? new TopViewHolder(inflate, i2) : i == 2 ? new TopViewHolder(inflate, 1) : new ViewHolder(inflate);
    }
}
