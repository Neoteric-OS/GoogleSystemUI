package com.google.android.systemui.columbus.legacy.gates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UsbState extends TransientGate {
    public final Context context;
    public boolean usbConnected;
    public final long gateDuration = 500;
    public final UsbState$usbReceiver$1 usbReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.columbus.legacy.gates.UsbState$usbReceiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                UsbState usbState = UsbState.this;
                BuildersKt.launch$default(usbState.coroutineScope, null, null, new UsbState$usbReceiver$1$onReceive$1$1(intent.getBooleanExtra("connected", false), usbState, null), 3);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.columbus.legacy.gates.UsbState$usbReceiver$1] */
    public UsbState(Context context) {
        this.context = context;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        BuildersKt.launch$default(this.coroutineScope, null, null, new UsbState$onActivate$1(this, this.context.registerReceiver(this.usbReceiver, new IntentFilter("android.hardware.usb.action.USB_STATE")), null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.context.unregisterReceiver(this.usbReceiver);
    }
}
