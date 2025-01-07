package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseMenuWrapper {
    public final Context mContext;
    public SimpleArrayMap mMenuItems;

    public BaseMenuWrapper(Context context) {
        this.mContext = context;
    }

    public final MenuItem getMenuItemWrapper(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.mMenuItems == null) {
            this.mMenuItems = new SimpleArrayMap(0);
        }
        MenuItem menuItem2 = (MenuItem) this.mMenuItems.get(supportMenuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.mContext, supportMenuItem);
        this.mMenuItems.put(supportMenuItem, menuItemWrapperICS);
        return menuItemWrapperICS;
    }
}
