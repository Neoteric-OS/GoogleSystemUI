package com.google.android.systemui.power;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryInfoBroadcast {
    public final BatteryManager mBatteryManager;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    final ContentObserver mDeviceNameObserver;
    public final EnhancedEstimates mEnhancedEstimates;
    public final Executor mExecutor;
    public boolean mIsPowerSaveMode;
    final BluetoothAdapter.OnMetadataChangedListener mMetadataListener;
    public final PowerManager mPowerManager;
    final ContentObserver mRemainingTimeObserver;
    public final SharedPreferences mSharedPreferences;
    final ContentObserver mTimeToFullObserver;
    public final UserTracker mUserTracker;
    final ContentObserver mWidgetEnableObserver;
    public int mBatteryLevel = -1;
    public int mBatteryPlugged = 0;
    public int mBatteryStatus = 1;
    public int mBatteryChargingStatus = 1;
    public long mRemainingTimeMillis = -1;
    public long mTimeToFullMillis = -1;
    public boolean mWidgetEnabled = true;

    public BatteryInfoBroadcast(Context context, BroadcastSender broadcastSender, EnhancedEstimates enhancedEstimates, Executor executor, UserTracker userTracker) {
        this.mIsPowerSaveMode = false;
        final int i = 0;
        ContentObserver contentObserver = new ContentObserver(this) { // from class: com.google.android.systemui.power.BatteryInfoBroadcast.1
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        boolean z2 = Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1;
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        break;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            break;
                        }
                        break;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        break;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        break;
                }
            }
        };
        this.mWidgetEnableObserver = contentObserver;
        final int i2 = 1;
        ContentObserver contentObserver2 = new ContentObserver(this) { // from class: com.google.android.systemui.power.BatteryInfoBroadcast.1
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        boolean z2 = Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1;
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        break;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            break;
                        }
                        break;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        break;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        break;
                }
            }
        };
        this.mTimeToFullObserver = contentObserver2;
        final int i3 = 2;
        ContentObserver contentObserver3 = new ContentObserver(this) { // from class: com.google.android.systemui.power.BatteryInfoBroadcast.1
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i3) {
                    case 0:
                        boolean z2 = Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1;
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        break;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            break;
                        }
                        break;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        break;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        break;
                }
            }
        };
        this.mDeviceNameObserver = contentObserver3;
        final int i4 = 3;
        ContentObserver contentObserver4 = new ContentObserver(this) { // from class: com.google.android.systemui.power.BatteryInfoBroadcast.1
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i4) {
                    case 0:
                        boolean z2 = Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1;
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        break;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            break;
                        }
                        break;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        break;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        break;
                }
            }
        };
        this.mRemainingTimeObserver = contentObserver4;
        this.mMetadataListener = new BluetoothAdapter.OnMetadataChangedListener() { // from class: com.google.android.systemui.power.BatteryInfoBroadcast.5
            public final void onMetadataChanged(BluetoothDevice bluetoothDevice, int i5, byte[] bArr) {
                ExifInterface$$ExternalSyntheticOutline0.m("onMetadataChanged: ", "BatteryInfoBroadcast", i5);
                BatteryInfoBroadcast.this.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.bluetoothStatusChanged"));
            }
        };
        this.mContext = context;
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mPowerManager = powerManager;
        this.mBatteryManager = (BatteryManager) context.getSystemService(BatteryManager.class);
        this.mIsPowerSaveMode = powerManager.isPowerSaveMode();
        this.mBroadcastSender = broadcastSender;
        this.mEnhancedEstimates = executor == null ? null : enhancedEstimates;
        this.mExecutor = executor;
        this.mUserTracker = userTracker;
        contentObserver.onChange(true);
        this.mSharedPreferences = context.getApplicationContext().getSharedPreferences("battery_info_shared_prefs", 0);
        registerObserver(Settings.Global.getUriFor("device_name"), contentObserver3, "device name");
        registerObserver(new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").build(), contentObserver4, "remaining time");
        registerObserver(Settings.Secure.getUriFor("battery_widget_enabled"), contentObserver, "enabled widget");
        registerObserver(Settings.Global.getUriFor("time_to_full_millis"), contentObserver2, "time to full");
    }

    public static Intent createIntentForSI(String str) {
        return new Intent(str).setPackage("com.google.android.settings.intelligence");
    }

    public final void recordDateTime(String str) {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            Uri uri = DumpUtils.PROVIDER_URI;
            edit.putString(str, DumpUtils.toReadableDateTime(System.currentTimeMillis())).apply();
        }
    }

    public final void registerObserver(Uri uri, ContentObserver contentObserver, String str) {
        try {
            this.mContext.getContentResolver().registerContentObserver(uri, false, contentObserver, -1);
        } catch (Exception e) {
            Log.e("BatteryInfoBroadcast", "failed to register observer for ".concat(str), e);
        }
    }

    public final void sendBatteryChangeIntent(Intent intent, boolean z, boolean z2) {
        long j;
        if (intent == null || intent.getAction() == null) {
            Log.w("BatteryInfoBroadcast", "sendBatteryIntent() with invalid intent");
            return;
        }
        String action = intent.getAction();
        Intent putExtra = createIntentForSI("PNW.batteryStatusChanged").putExtra("battery_save", this.mIsPowerSaveMode);
        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
            putExtra.putExtra("battery_changed_intent", intent);
        }
        if (BatteryStatus.isPluggedIn(this.mBatteryPlugged)) {
            try {
                j = this.mBatteryManager.computeChargeTimeRemaining();
            } catch (Exception e) {
                Log.w("BatteryInfoBroadcast", "computeChargeTimeRemaining failed.", e);
                j = -1;
            }
            Log.d("BatteryInfoBroadcast", "computeChargeTimeRemaining=" + j);
            if (z2 && this.mTimeToFullMillis == j) {
                Log.w("BatteryInfoBroadcast", "sendBroadcastIntent() ignore from the same timeToFull");
                return;
            }
            if (this.mTimeToFullMillis != j) {
                this.mTimeToFullMillis = j != 0 ? j : -1L;
                Settings.Global.putLong(this.mContext.getContentResolver(), "time_to_full_millis", this.mTimeToFullMillis);
            }
            putExtra.putExtra("time_to_full", this.mTimeToFullMillis);
            r5 = j;
        } else {
            EnhancedEstimates enhancedEstimates = this.mEnhancedEstimates;
            if (enhancedEstimates != null) {
                Estimate estimate = ((EnhancedEstimatesGoogleImpl) enhancedEstimates).getEstimate();
                long j2 = estimate.estimateMillis;
                if (z && this.mRemainingTimeMillis == j2) {
                    Log.w("BatteryInfoBroadcast", "sendBatteryIntent() ignore from the same remaining time");
                    return;
                }
                this.mRemainingTimeMillis = j2;
                putExtra.putExtra("remaining_time", j2);
                Settings.Global.putLong(this.mContext.getContentResolver(), "remaining_time_millis", this.mRemainingTimeMillis);
                if (j2 > 0) {
                    Estimate.storeCachedEstimate(this.mContext, estimate);
                }
            }
        }
        Log.d("BatteryInfoBroadcast", String.format("onReceive: %s, saverMode: %b, remainingTime: %d, timeToFull: %d", action, Boolean.valueOf(this.mIsPowerSaveMode), Long.valueOf(this.mRemainingTimeMillis), Long.valueOf(r5)));
        sendBroadcast(putExtra);
    }

    public final void sendBroadcast(Intent intent) {
        BroadcastSender broadcastSender = this.mBroadcastSender;
        if (broadcastSender == null || intent == null) {
            return;
        }
        broadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    public final void sendPluggedInStateIntent(boolean z) {
        sendBroadcast(new Intent(z ? "com.android.settings.battery.action.ACTION_BATTERY_PLUGGING" : "com.android.settings.battery.action.ACTION_BATTERY_UNPLUGGING").setComponent(new ComponentName("com.android.settings", "com.android.settings.fuelgauge.batteryusage.BatteryUsageBroadcastReceiver")));
        if (z) {
            return;
        }
        Intent registerReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (BatteryStatus.isCharged(registerReceiver.getIntExtra("status", 1), BatteryStatus.getBatteryLevel(registerReceiver))) {
            recordDateTime("last_data_reset_time");
        }
    }

    public final void writeString(PrintWriter printWriter, String str, String str2) {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            printWriter.println("\t\t" + str + ": " + sharedPreferences.getString(str2, ""));
        }
    }
}
