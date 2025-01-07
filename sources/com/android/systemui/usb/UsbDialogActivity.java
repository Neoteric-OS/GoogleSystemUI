package com.android.systemui.usb;

import android.R;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UsbDialogActivity extends AlertActivity implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public CheckBox mAlwaysUse;
    public TextView mClearDefaultHint;
    public UsbDialogHelper mDialogHelper;

    public final void addAlwaysUseCheckbox() {
        AlertController.AlertParams alertParams = ((AlertActivity) this).mAlertParams;
        View inflate = ((LayoutInflater) getSystemService(LayoutInflater.class)).inflate(R.layout.always_use_checkbox, (ViewGroup) null);
        alertParams.mView = inflate;
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.atThumb);
        this.mAlwaysUse = checkBox;
        UsbDialogHelper usbDialogHelper = this.mDialogHelper;
        if (usbDialogHelper.mIsUsbDevice) {
            checkBox.setText(getString(com.android.wm.shell.R.string.always_use_device, new Object[]{usbDialogHelper.mAppName, usbDialogHelper.getDeviceDescription()}));
        } else {
            checkBox.setText(getString(com.android.wm.shell.R.string.always_use_accessory, new Object[]{usbDialogHelper.mAppName, usbDialogHelper.getDeviceDescription()}));
        }
        this.mAlwaysUse.setOnCheckedChangeListener(this);
        TextView textView = (TextView) alertParams.mView.findViewById(R.id.compat_checkbox);
        this.mClearDefaultHint = textView;
        textView.setVisibility(8);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        TextView textView = this.mClearDefaultHint;
        if (textView == null) {
            return;
        }
        if (z) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            onConfirm();
        } else {
            finish();
        }
    }

    public abstract void onConfirm();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(524288);
        try {
            this.mDialogHelper = new UsbDialogHelper(getApplicationContext(), getIntent());
        } catch (IllegalStateException e) {
            Log.e("UsbDialogActivity", "unable to initialize", e);
            finish();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPause() {
        UsbDisconnectedReceiver usbDisconnectedReceiver;
        UsbDialogHelper usbDialogHelper = this.mDialogHelper;
        if (usbDialogHelper != null && (usbDisconnectedReceiver = usbDialogHelper.mDisconnectedReceiver) != null) {
            try {
                unregisterReceiver(usbDisconnectedReceiver);
            } catch (Exception unused) {
            }
            usbDialogHelper.mDisconnectedReceiver = null;
        }
        super.onPause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        super.onResume();
        UsbDialogHelper usbDialogHelper = this.mDialogHelper;
        if (usbDialogHelper.mIsUsbDevice) {
            usbDialogHelper.mDisconnectedReceiver = new UsbDisconnectedReceiver((Activity) this, usbDialogHelper.mDevice);
        } else {
            usbDialogHelper.mDisconnectedReceiver = new UsbDisconnectedReceiver((Activity) this, usbDialogHelper.mAccessory);
        }
    }
}
