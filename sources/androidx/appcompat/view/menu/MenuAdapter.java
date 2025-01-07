package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuAdapter extends BaseAdapter {
    public final MenuBuilder mAdapterMenu;
    public int mExpandedIndex = -1;
    public boolean mForceShowIcon;
    public final LayoutInflater mInflater;
    public final int mItemLayoutRes;
    public final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = menuBuilder;
        this.mItemLayoutRes = i;
        findExpandedIndex();
    }

    public final void findExpandedIndex() {
        MenuBuilder menuBuilder = this.mAdapterMenu;
        MenuItemImpl menuItemImpl = menuBuilder.mExpandedItem;
        if (menuItemImpl != null) {
            menuBuilder.flagActionItems();
            ArrayList arrayList = menuBuilder.mNonActionItems;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((MenuItemImpl) arrayList.get(i)) == menuItemImpl) {
                    this.mExpandedIndex = i;
                    return;
                }
            }
        }
        this.mExpandedIndex = -1;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList visibleItems;
        if (this.mOverflowOnly) {
            MenuBuilder menuBuilder = this.mAdapterMenu;
            menuBuilder.flagActionItems();
            visibleItems = menuBuilder.mNonActionItems;
        } else {
            visibleItems = this.mAdapterMenu.getVisibleItems();
        }
        return this.mExpandedIndex < 0 ? visibleItems.size() : visibleItems.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
        }
        int i2 = getItem(i).mGroup;
        int i3 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        boolean z = this.mAdapterMenu.isGroupDividerEnabled() && i2 != (i3 >= 0 ? getItem(i3).mGroup : i2);
        ImageView imageView = listMenuItemView.mGroupDivider;
        if (imageView != null) {
            imageView.setVisibility((listMenuItemView.mHasListDivider || !z) ? 8 : 0);
        }
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.mForceShowIcon) {
            listMenuItemView.mForceShowIcon = true;
            listMenuItemView.mPreserveIconSpacing = true;
        }
        itemView.initialize(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public final MenuItemImpl getItem(int i) {
        ArrayList visibleItems;
        if (this.mOverflowOnly) {
            MenuBuilder menuBuilder = this.mAdapterMenu;
            menuBuilder.flagActionItems();
            visibleItems = menuBuilder.mNonActionItems;
        } else {
            visibleItems = this.mAdapterMenu.getVisibleItems();
        }
        int i2 = this.mExpandedIndex;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (MenuItemImpl) visibleItems.get(i);
    }
}
