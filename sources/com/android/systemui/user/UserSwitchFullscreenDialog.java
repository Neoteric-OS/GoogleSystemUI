package com.android.systemui.user;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.user.ui.binder.UserSwitcherViewBinder;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitchFullscreenDialog extends SystemUIDialog {
    public final FalsingCollector falsingCollector;
    public final UserSwitcherViewModel userSwitcherViewModel;

    public UserSwitchFullscreenDialog(Context context, FalsingCollector falsingCollector, UserSwitcherViewModel userSwitcherViewModel) {
        super(context, R.style.Theme_UserSwitcherFullscreenDialog, true);
        this.falsingCollector = falsingCollector;
        this.userSwitcherViewModel = userSwitcherViewModel;
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final int getHeight() {
        return -1;
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final int getWidth() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Display display = getContext().getDisplay();
        if (display == null) {
            throw new IllegalStateException("Required value was null.");
        }
        display.getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        View decorView;
        WindowInsetsController windowInsetsController;
        super.onCreate(bundle);
        SystemUIDialog.setShowForAllUsers(this);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null && (decorView = window.getDecorView()) != null && (windowInsetsController = decorView.getWindowInsetsController()) != null) {
            windowInsetsController.setSystemBarsBehavior(2);
            windowInsetsController.hide(WindowInsets.Type.systemBars());
        }
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.user_switcher_fullscreen, (ViewGroup) null));
        UserSwitcherViewBinder.bind((ViewGroup) requireViewById(R.id.user_switcher_root), this.userSwitcherViewModel, getLayoutInflater(), this.falsingCollector, new UserSwitchFullscreenDialog$onCreate$2(0, this, UserSwitchFullscreenDialog.class, "dismiss", "dismiss()V", 0));
    }
}
