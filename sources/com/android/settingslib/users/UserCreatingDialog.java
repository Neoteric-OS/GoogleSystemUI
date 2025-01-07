package com.android.settingslib.users;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UserCreatingDialog extends AlertDialog {
    public UserCreatingDialog(Context context, boolean z) {
        super(context, R.style.Theme.DeviceDefault.Light.Dialog.Alert);
        setCancelable(false);
        View inflate = LayoutInflater.from(getContext()).inflate(com.android.wm.shell.R.layout.user_creation_progress_dialog, (ViewGroup) null);
        String string = getContext().getString(z ? com.android.wm.shell.R.string.creating_new_guest_dialog_message : com.android.wm.shell.R.string.creating_new_user_dialog_message);
        inflate.setAccessibilityPaneTitle(string);
        ((TextView) inflate.findViewById(com.android.wm.shell.R.id.message)).setText(string);
        setView(inflate);
        getWindow().setType(2010);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.privateFlags = 272;
        getWindow().setAttributes(attributes);
    }
}
