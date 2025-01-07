package com.google.android.systemui.elmyra.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.systemui.util.concurrency.DelayableExecutor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UsbState extends TransientGate {
    public final Context mContext;
    public boolean mUsbConnected;
    public final AnonymousClass1 mUsbReceiver;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.elmyra.gates.UsbState$1] */
    public UsbState(Context context, DelayableExecutor delayableExecutor, int i) {
        super(delayableExecutor, i);
        this.mUsbReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.elmyra.gates.UsbState.1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                boolean booleanExtra = intent.getBooleanExtra("connected", false);
                UsbState usbState = UsbState.this;
                if (booleanExtra != usbState.mUsbConnected) {
                    usbState.mUsbConnected = booleanExtra;
                    usbState.block();
                }
            }
        };
        this.mContext = context;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        IntentFilter intentFilter = new IntentFilter("android.hardware.usb.action.USB_STATE");
        Intent registerReceiver = this.mContext.registerReceiver(null, intentFilter);
        if (registerReceiver != null) {
            this.mUsbConnected = registerReceiver.getBooleanExtra("connected", false);
        }
        this.mContext.registerReceiver(this.mUsbReceiver, intentFilter);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mContext.unregisterReceiver(this.mUsbReceiver);
    }
}
