package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuPresenter;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MenuPopupHelper {
    public View mAnchorView;
    public final Context mContext;
    public boolean mForceShowIcon;
    public final MenuBuilder mMenu;
    public AnonymousClass1 mOnDismissListener;
    public final boolean mOverflowOnly;
    public MenuPopup mPopup;
    public final int mPopupStyleAttr;
    public MenuPresenter.Callback mPresenterCallback;
    public int mDropDownGravity = 8388611;
    public final AnonymousClass1 mInternalOnDismissListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.appcompat.view.menu.MenuPopupHelper$1, reason: invalid class name */
    public final class AnonymousClass1 implements PopupWindow.OnDismissListener {
        public AnonymousClass1() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            MenuPopupHelper.this.onDismiss();
        }
    }

    public MenuPopupHelper(int i, Context context, View view, MenuBuilder menuBuilder, boolean z) {
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mAnchorView = view;
        this.mOverflowOnly = z;
        this.mPopupStyleAttr = i;
    }

    public final MenuPopup getPopup() {
        MenuPopup standardMenuPopup;
        if (this.mPopup == null) {
            Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                standardMenuPopup = new CascadingMenuPopup(this.mContext, this.mAnchorView, this.mPopupStyleAttr, this.mOverflowOnly);
            } else {
                Context context = this.mContext;
                View view = this.mAnchorView;
                standardMenuPopup = new StandardMenuPopup(this.mPopupStyleAttr, context, view, this.mMenu, this.mOverflowOnly);
            }
            standardMenuPopup.addMenu(this.mMenu);
            standardMenuPopup.setOnDismissListener(this.mInternalOnDismissListener);
            standardMenuPopup.setAnchorView(this.mAnchorView);
            standardMenuPopup.setCallback(this.mPresenterCallback);
            standardMenuPopup.setForceShowIcon(this.mForceShowIcon);
            standardMenuPopup.setGravity(this.mDropDownGravity);
            this.mPopup = standardMenuPopup;
        }
        return this.mPopup;
    }

    public final boolean isShowing() {
        MenuPopup menuPopup = this.mPopup;
        return menuPopup != null && menuPopup.isShowing();
    }

    public void onDismiss() {
        this.mPopup = null;
        AnonymousClass1 anonymousClass1 = this.mOnDismissListener;
        if (anonymousClass1 != null) {
            anonymousClass1.onDismiss();
        }
    }

    public final void showPopup(int i, int i2, boolean z, boolean z2) {
        MenuPopup popup = getPopup();
        popup.setShowTitle(z2);
        if (z) {
            if ((Gravity.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) == 5) {
                i -= this.mAnchorView.getWidth();
            }
            popup.setHorizontalOffset(i);
            popup.setVerticalOffset(i2);
            int i3 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            popup.mEpicenterBounds = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        popup.show();
    }
}
