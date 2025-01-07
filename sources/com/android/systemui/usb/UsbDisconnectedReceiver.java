package com.android.systemui.usb;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UsbDisconnectedReceiver extends BroadcastReceiver {
    public final UsbAccessory mAccessory;
    public final Activity mActivity;
    public final UsbDevice mDevice;

    public UsbDisconnectedReceiver(Activity activity, UsbDevice usbDevice) {
        this.mActivity = activity;
        this.mDevice = usbDevice;
        activity.registerReceiver(this, new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED"));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        UsbAccessory usbAccessory;
        String action = intent.getAction();
        if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            if (usbDevice == null || !usbDevice.equals(this.mDevice)) {
                return;
            }
            this.mActivity.finish();
            return;
        }
        if ("android.hardware.usb.action.USB_ACCESSORY_DETACHED".equals(action) && (usbAccessory = (UsbAccessory) intent.getParcelableExtra("accessory")) != null && usbAccessory.equals(this.mAccessory)) {
            this.mActivity.finish();
        }
    }

    public UsbDisconnectedReceiver(Activity activity, UsbAccessory usbAccessory) {
        this.mActivity = activity;
        this.mAccessory = usbAccessory;
        activity.registerReceiver(this, new IntentFilter("android.hardware.usb.action.USB_ACCESSORY_DETACHED"));
    }
}
