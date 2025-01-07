package com.android.systemui.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.debug.IAdbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class UsbDebuggingActivity extends AlertActivity implements DialogInterface.OnClickListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public CheckBox mAlwaysAllow;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public UsbDisconnectedReceiver mDisconnectedReceiver;
    public String mKey;
    public boolean mServiceNotified;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UsbDisconnectedReceiver extends BroadcastReceiver {
        public final UsbDebuggingActivity mActivity;

        public UsbDisconnectedReceiver(UsbDebuggingActivity usbDebuggingActivity) {
            this.mActivity = usbDebuggingActivity;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.hardware.usb.action.USB_STATE".equals(intent.getAction()) && !intent.getBooleanExtra("connected", false)) {
                Log.d("UsbDebuggingActivity", "USB disconnected, notifying service");
                UsbDebuggingActivity usbDebuggingActivity = UsbDebuggingActivity.this;
                int i = UsbDebuggingActivity.$r8$clinit;
                usbDebuggingActivity.notifyService(false, false);
                this.mActivity.finish();
            }
        }
    }

    public UsbDebuggingActivity(BroadcastDispatcher broadcastDispatcher) {
        this.mBroadcastDispatcher = broadcastDispatcher;
    }

    public final void notifyService(boolean z, boolean z2) {
        try {
            IAdbManager asInterface = IAdbManager.Stub.asInterface(ServiceManager.getService("adb"));
            if (z) {
                asInterface.allowDebugging(z2, this.mKey);
            } else {
                asInterface.denyDebugging();
            }
            this.mServiceNotified = true;
        } catch (Exception e) {
            Log.e("UsbDebuggingActivity", "Unable to notify Usb service", e);
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean z = false;
        boolean z2 = i == -1;
        if (z2 && this.mAlwaysAllow.isChecked()) {
            z = true;
        }
        notifyService(z2, z);
        finish();
    }

    public final void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.addSystemFlags(524288);
        window.setType(2008);
        super.onCreate(bundle);
        if (SystemProperties.getInt("service.adb.tcp.port", 0) == 0 && !Build.IS_EMULATOR) {
            this.mDisconnectedReceiver = new UsbDisconnectedReceiver(this);
            this.mBroadcastDispatcher.registerReceiver(this.mDisconnectedReceiver, new IntentFilter("android.hardware.usb.action.USB_STATE"));
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("fingerprints");
        String stringExtra2 = intent.getStringExtra("key");
        this.mKey = stringExtra2;
        if (stringExtra == null || stringExtra2 == null) {
            finish();
            return;
        }
        AlertController.AlertParams alertParams = ((AlertActivity) this).mAlertParams;
        alertParams.mTitle = getString(R.string.usb_debugging_title);
        alertParams.mMessage = getString(R.string.usb_debugging_message, new Object[]{stringExtra});
        alertParams.mPositiveButtonText = getString(R.string.usb_debugging_allow);
        alertParams.mNegativeButtonText = getString(android.R.string.cancel);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonListener = this;
        View inflate = LayoutInflater.from(alertParams.mContext).inflate(android.R.layout.always_use_checkbox, (ViewGroup) null);
        CheckBox checkBox = (CheckBox) inflate.findViewById(android.R.id.atThumb);
        this.mAlwaysAllow = checkBox;
        checkBox.setText(getString(R.string.usb_debugging_always));
        alertParams.mView = inflate;
        window.setCloseOnTouchOutside(false);
        setupAlert();
    }

    public final void onDestroy() {
        UsbDisconnectedReceiver usbDisconnectedReceiver = this.mDisconnectedReceiver;
        if (usbDisconnectedReceiver != null) {
            this.mBroadcastDispatcher.unregisterReceiver(usbDisconnectedReceiver);
        }
        if (isFinishing() && !this.mServiceNotified) {
            notifyService(false, false);
        }
        super.onDestroy();
    }
}
