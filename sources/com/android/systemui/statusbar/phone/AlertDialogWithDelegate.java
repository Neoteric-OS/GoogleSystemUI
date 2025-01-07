package com.android.systemui.statusbar.phone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewRootImpl;
import com.android.systemui.mediaprojection.permission.BaseMediaProjectionPermissionDialogDelegate;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AlertDialogWithDelegate extends AlertDialog implements ViewRootImpl.ConfigChangedCallback {
    public final BaseMediaProjectionPermissionDialogDelegate delegate;

    public AlertDialogWithDelegate(Context context, BaseMediaProjectionPermissionDialogDelegate baseMediaProjectionPermissionDialogDelegate) {
        super(context, R.style.Theme_SystemUI_Dialog);
        this.delegate = baseMediaProjectionPermissionDialogDelegate;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.delegate.getClass();
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        this.delegate.getClass();
        super.onCreate(bundle);
        this.delegate.onCreate(this, bundle);
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        ViewRootImpl.addConfigCallback(this);
        this.delegate.getClass();
    }

    @Override // android.app.Dialog
    public final void onStop() {
        super.onStop();
        ViewRootImpl.removeConfigCallback(this);
        this.delegate.onStop(this);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.delegate.getClass();
    }
}
