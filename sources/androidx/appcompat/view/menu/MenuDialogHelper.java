package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.ListMenuPresenter.MenuAdapter;
import androidx.appcompat.view.menu.MenuPresenter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
    public AlertDialog mDialog;
    public SubMenuBuilder mMenu;
    public ListMenuPresenter mPresenter;

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        SubMenuBuilder subMenuBuilder = this.mMenu;
        ListMenuPresenter listMenuPresenter = this.mPresenter;
        if (listMenuPresenter.mAdapter == null) {
            listMenuPresenter.mAdapter = listMenuPresenter.new MenuAdapter();
        }
        subMenuBuilder.performItemAction(listMenuPresenter.mAdapter.getItem(i), null, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        AlertDialog alertDialog;
        if ((z || menuBuilder == this.mMenu) && (alertDialog = this.mDialog) != null) {
            alertDialog.dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.mDialog.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.mDialog.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.mMenu.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.mMenu.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public final boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        return false;
    }
}
