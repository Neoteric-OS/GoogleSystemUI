package com.google.android.systemui.power;

import android.content.Intent;
import com.android.app.viewcapture.data.ViewNode;
import com.android.settingslib.fuelgauge.BatteryStatus;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryInfoBroadcast$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BatteryInfoBroadcast f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ BatteryInfoBroadcast$$ExternalSyntheticLambda0(BatteryInfoBroadcast batteryInfoBroadcast, Intent intent) {
        this.f$0 = batteryInfoBroadcast;
        this.f$1 = intent;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.lang.Runnable
    public final void run() {
        char c;
        BatteryInfoBroadcast batteryInfoBroadcast = this.f$0;
        Intent intent = this.f$1;
        batteryInfoBroadcast.getClass();
        String action = intent.getAction();
        if (batteryInfoBroadcast.mWidgetEnabled || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            switch (action.hashCode()) {
                case -1886648615:
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1538406691:
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -612790895:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case 579327048:
                    if (action.equals("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 798292259:
                    if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1019184907:
                    if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1020204554:
                    if (action.equals("PNW.batteryStatusChanged")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1123270207:
                    if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1174571750:
                    if (action.equals("android.bluetooth.device.action.ALIAS_CHANGED")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1779291251:
                    if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    batteryInfoBroadcast.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), false, false);
                    break;
                case 2:
                    int batteryLevel = BatteryStatus.getBatteryLevel(intent);
                    int intExtra = intent.getIntExtra("status", 1);
                    int intExtra2 = intent.getIntExtra("plugged", 0);
                    int intExtra3 = intent.getIntExtra("android.os.extra.CHARGING_STATUS", 1);
                    if (batteryInfoBroadcast.mBatteryLevel != batteryLevel || batteryInfoBroadcast.mBatteryStatus != intExtra || batteryInfoBroadcast.mBatteryPlugged != intExtra2 || batteryInfoBroadcast.mBatteryChargingStatus != intExtra3) {
                        batteryInfoBroadcast.mBatteryLevel = batteryLevel;
                        batteryInfoBroadcast.mBatteryStatus = intExtra;
                        batteryInfoBroadcast.mBatteryPlugged = intExtra2;
                        batteryInfoBroadcast.mBatteryChargingStatus = intExtra3;
                        batteryInfoBroadcast.sendBatteryChangeIntent(intent, false, false);
                        break;
                    }
                    break;
                case 3:
                    batteryInfoBroadcast.sendPluggedInStateIntent(true);
                    batteryInfoBroadcast.recordDateTime("last_phone_connected_time");
                    break;
                case 4:
                    batteryInfoBroadcast.sendPluggedInStateIntent(false);
                    batteryInfoBroadcast.recordDateTime("last_phone_disconnected_time");
                    break;
                case 5:
                    boolean isPowerSaveMode = batteryInfoBroadcast.mPowerManager.isPowerSaveMode();
                    if (batteryInfoBroadcast.mIsPowerSaveMode != isPowerSaveMode) {
                        batteryInfoBroadcast.mIsPowerSaveMode = isPowerSaveMode;
                        batteryInfoBroadcast.sendBatteryChangeIntent(intent, false, false);
                        break;
                    }
                    break;
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    Intent createIntentForSI = BatteryInfoBroadcast.createIntentForSI("PNW.bluetoothStatusChanged");
                    createIntentForSI.putExtra(action, intent);
                    batteryInfoBroadcast.sendBroadcast(createIntentForSI);
                    break;
            }
        }
    }
}
