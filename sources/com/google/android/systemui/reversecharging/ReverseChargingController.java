package com.google.android.systemui.reversecharging;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IThermalEventListener;
import android.os.IThermalService;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Temperature;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.systemui.BootCompleteCache$BootCompleteListener;
import com.android.systemui.BootCompleteCacheImpl;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.wm.shell.R;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import com.google.android.systemui.statusbar.policy.BatteryControllerImplGoogle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReverseChargingController extends BroadcastReceiver implements CallbackController {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingControl", 3);
    public static final long DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT;
    public static final long DURATION_TO_REVERSE_AC_TIME_OUT;
    public static final long DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT;
    public static final long DURATION_TO_REVERSE_TIME_OUT;
    public static final long DURATION_WAIT_NFC_SERVICE;
    public final ReverseChargingController$$ExternalSyntheticLambda3 mAccessoryDeviceRemovedTimeoutAlarmAction;
    public final AlarmManager mAlarmManager;
    public final Executor mBgExecutor;
    public final BootCompleteCacheImpl mBootCompleteCache;
    boolean mBootCompleted;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public boolean mCacheIsReverseSupported;
    public final ReverseChargingController$$ExternalSyntheticLambda3 mCheckNfcConflictWithUsbAudioAlarmAction;
    public final Context mContext;
    final boolean mDoesNfcConflictWithUsbAudio;
    public final boolean mDoesNfcConflictWithWlc;
    public boolean mIsReverseSupported;
    int mLevel;
    public final Executor mMainExecutor;
    public String mName;
    final int[] mNfcUsbProductIds;
    final int[] mNfcUsbVendorIds;
    public boolean mPluggedAc;
    public boolean mPowerSave;
    public final ReverseChargingController$$ExternalSyntheticLambda3 mReconnectedTimeoutAlarmAction;
    boolean mRestoreUsbNfcPollingMode;
    public boolean mRestoreWlcNfcPollingMode;
    boolean mReverseChargingEnabled;
    public final Optional mRtxChargerManagerOptional;
    public final ReverseChargingController$$ExternalSyntheticLambda3 mRtxFinishAlarmAction;
    public final ReverseChargingController$$ExternalSyntheticLambda3 mRtxFinishRxFullAlarmAction;
    public int mRtxLevel;
    IThermalEventListener mSkinThermalEventListener;
    public boolean mStartReconnected;
    public boolean mStopReverseAtAcUnplug;
    public final IThermalService mThermalService;
    public final Optional mUsbManagerOptional;
    public boolean mUseRxRemovalTimeOut;
    public boolean mWirelessCharging;
    public final ArrayList mChangeCallbacks = new ArrayList();
    int mCurrentRtxMode = 0;
    boolean mIsUsbPlugIn = false;
    public int mCurrentRtxReceiverType = 0;
    public boolean mProvidingBattery = false;
    public long mReverseStartTime = 0;
    public final ReverseChargingController$$ExternalSyntheticLambda2 mBootCompleteListener = new BootCompleteCache$BootCompleteListener() { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda2
        @Override // com.android.systemui.BootCompleteCache$BootCompleteListener
        public final void onBootComplete() {
            boolean z = ReverseChargingController.DEBUG;
            ReverseChargingController reverseChargingController = ReverseChargingController.this;
            if (z) {
                reverseChargingController.getClass();
                Log.d("ReverseChargingControl", "onBootComplete(): ACTION_BOOT_COMPLETED");
            }
            reverseChargingController.mBootCompleted = true;
            reverseChargingController.setRtxTimer(ReverseChargingController.DURATION_WAIT_NFC_SERVICE, 2);
        }
    };
    final BatteryController.BatteryStateChangeCallback mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() { // from class: com.google.android.systemui.reversecharging.ReverseChargingController.1
        @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
        public final void onPowerSaveChanged(boolean z) {
            ReverseChargingController.this.mPowerSave = z;
        }

        @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
        public final void onWirelessChargingChanged(boolean z) {
            ReverseChargingController.this.mWirelessCharging = z;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ReverseChargingChangeCallback {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SkinThermalEventListener extends IThermalEventListener.Stub {
        public SkinThermalEventListener() {
        }

        public final void notifyThrottling(Temperature temperature) {
            int status = temperature.getStatus();
            Log.i("ReverseChargingControl", "notifyThrottling(): thermal status=" + status);
            ReverseChargingController reverseChargingController = ReverseChargingController.this;
            if (!reverseChargingController.mReverseChargingEnabled || status < 4) {
                return;
            }
            reverseChargingController.setReverseStateInternal(3, false);
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        DURATION_TO_REVERSE_TIME_OUT = timeUnit.toMillis(1L);
        DURATION_TO_REVERSE_AC_TIME_OUT = timeUnit.toMillis(1L);
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT = timeUnit2.toMillis(30L);
        DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120L);
        DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120L);
        DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT = timeUnit2.toMillis(120L);
        DURATION_WAIT_NFC_SERVICE = timeUnit2.toMillis(10L);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3] */
    public ReverseChargingController(Context context, BroadcastDispatcher broadcastDispatcher, Optional optional, AlarmManager alarmManager, Optional optional2, Executor executor, Executor executor2, BootCompleteCacheImpl bootCompleteCacheImpl, IThermalService iThermalService) {
        final int i = 0;
        this.mRtxFinishAlarmAction = new AlarmManager.OnAlarmListener(this) { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3
            public final /* synthetic */ ReverseChargingController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i2 = i;
                ReverseChargingController reverseChargingController = this.f$0;
                switch (i2) {
                    case 0:
                        reverseChargingController.onAlarmRtxFinish(5);
                        break;
                    case 1:
                        reverseChargingController.onAlarmRtxFinish(103);
                        break;
                    case 2:
                        if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                            Iterator<UsbDevice> it = ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values().iterator();
                            while (it.hasNext()) {
                                reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, it.next());
                            }
                            break;
                        }
                        break;
                    case 3:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.mStartReconnected = false;
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                    default:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mRtxFinishRxFullAlarmAction = new AlarmManager.OnAlarmListener(this) { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3
            public final /* synthetic */ ReverseChargingController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i22 = i2;
                ReverseChargingController reverseChargingController = this.f$0;
                switch (i22) {
                    case 0:
                        reverseChargingController.onAlarmRtxFinish(5);
                        break;
                    case 1:
                        reverseChargingController.onAlarmRtxFinish(103);
                        break;
                    case 2:
                        if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                            Iterator<UsbDevice> it = ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values().iterator();
                            while (it.hasNext()) {
                                reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, it.next());
                            }
                            break;
                        }
                        break;
                    case 3:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.mStartReconnected = false;
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                    default:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mCheckNfcConflictWithUsbAudioAlarmAction = new AlarmManager.OnAlarmListener(this) { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3
            public final /* synthetic */ ReverseChargingController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i22 = i3;
                ReverseChargingController reverseChargingController = this.f$0;
                switch (i22) {
                    case 0:
                        reverseChargingController.onAlarmRtxFinish(5);
                        break;
                    case 1:
                        reverseChargingController.onAlarmRtxFinish(103);
                        break;
                    case 2:
                        if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                            Iterator<UsbDevice> it = ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values().iterator();
                            while (it.hasNext()) {
                                reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, it.next());
                            }
                            break;
                        }
                        break;
                    case 3:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.mStartReconnected = false;
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                    default:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                }
            }
        };
        final int i4 = 3;
        this.mReconnectedTimeoutAlarmAction = new AlarmManager.OnAlarmListener(this) { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3
            public final /* synthetic */ ReverseChargingController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i22 = i4;
                ReverseChargingController reverseChargingController = this.f$0;
                switch (i22) {
                    case 0:
                        reverseChargingController.onAlarmRtxFinish(5);
                        break;
                    case 1:
                        reverseChargingController.onAlarmRtxFinish(103);
                        break;
                    case 2:
                        if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                            Iterator<UsbDevice> it = ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values().iterator();
                            while (it.hasNext()) {
                                reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, it.next());
                            }
                            break;
                        }
                        break;
                    case 3:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.mStartReconnected = false;
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                    default:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                }
            }
        };
        final int i5 = 4;
        this.mAccessoryDeviceRemovedTimeoutAlarmAction = new AlarmManager.OnAlarmListener(this) { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda3
            public final /* synthetic */ ReverseChargingController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i22 = i5;
                ReverseChargingController reverseChargingController = this.f$0;
                switch (i22) {
                    case 0:
                        reverseChargingController.onAlarmRtxFinish(5);
                        break;
                    case 1:
                        reverseChargingController.onAlarmRtxFinish(103);
                        break;
                    case 2:
                        if (reverseChargingController.mUsbManagerOptional.isPresent()) {
                            Iterator<UsbDevice> it = ((UsbManager) reverseChargingController.mUsbManagerOptional.get()).getDeviceList().values().iterator();
                            while (it.hasNext()) {
                                reverseChargingController.checkAndChangeNfcPollingAgainstUsbAudioDevice(false, it.next());
                            }
                            break;
                        }
                        break;
                    case 3:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mReConnectedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.mStartReconnected = false;
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                    default:
                        if (ReverseChargingController.DEBUG) {
                            reverseChargingController.getClass();
                            Log.w("ReverseChargingControl", "mAccessoryDeviceRemovedTimeoutAlarmAction() timeout");
                        }
                        reverseChargingController.onAlarmRtxFinish(6);
                        break;
                }
            }
        };
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mRtxChargerManagerOptional = optional;
        this.mAlarmManager = alarmManager;
        this.mDoesNfcConflictWithWlc = context.getResources().getBoolean(R.bool.config_nfc_conflict_with_wlc);
        this.mUsbManagerOptional = optional2;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mBootCompleteCache = bootCompleteCacheImpl;
        this.mThermalService = iThermalService;
        int[] intArray = context.getResources().getIntArray(R.array.config_nfc_conflict_with_usb_audio_vendorid);
        this.mNfcUsbVendorIds = intArray;
        int[] intArray2 = context.getResources().getIntArray(R.array.config_nfc_conflict_with_usb_audio_productid);
        this.mNfcUsbProductIds = intArray2;
        if (intArray.length != intArray2.length) {
            throw new IllegalStateException("VendorIds and ProductIds must be the same length");
        }
        this.mDoesNfcConflictWithUsbAudio = context.getResources().getBoolean(R.bool.config_nfc_conflict_with_usb_audio);
    }

    public final void cancelRtxTimer(int i) {
        if (i == 0) {
            this.mAlarmManager.cancel(this.mRtxFinishAlarmAction);
            return;
        }
        if (i == 1) {
            this.mAlarmManager.cancel(this.mRtxFinishRxFullAlarmAction);
        } else if (i == 3) {
            this.mAlarmManager.cancel(this.mReconnectedTimeoutAlarmAction);
        } else {
            if (i != 4) {
                return;
            }
            this.mAlarmManager.cancel(this.mAccessoryDeviceRemovedTimeoutAlarmAction);
        }
    }

    public final void checkAndChangeNfcPollingAgainstUsbAudioDevice(boolean z, UsbDevice usbDevice) {
        boolean z2 = false;
        for (int i = 0; i < this.mNfcUsbVendorIds.length; i++) {
            if (usbDevice.getVendorId() == this.mNfcUsbVendorIds[i] && usbDevice.getProductId() == this.mNfcUsbProductIds[i]) {
                this.mRestoreUsbNfcPollingMode = !z;
                if (!this.mRestoreWlcNfcPollingMode && z) {
                    z2 = true;
                }
                enableNfcPollingMode(z2);
                return;
            }
        }
    }

    public final void enableNfcPollingMode(boolean z) {
        int i = z ? 0 : 4096;
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("Change NFC reader mode to flags: ", "ReverseChargingControl", i);
        }
        this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, z, 1));
    }

    public final void fireReverseChanged$1() {
        synchronized (this.mChangeCallbacks) {
            this.mMainExecutor.execute(new Runnable() { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    final ReverseChargingController reverseChargingController = ReverseChargingController.this;
                    reverseChargingController.getClass();
                    new ArrayList(reverseChargingController.mChangeCallbacks).forEach(new Consumer() { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda11
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ReverseChargingController reverseChargingController2 = ReverseChargingController.this;
                            BatteryControllerImplGoogle batteryControllerImplGoogle = (BatteryControllerImplGoogle) ((ReverseChargingController.ReverseChargingChangeCallback) obj);
                            batteryControllerImplGoogle.onReverseChargingChanged(reverseChargingController2.mRtxLevel, reverseChargingController2.mName, reverseChargingController2.mReverseChargingEnabled);
                        }
                    });
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void handleIntentForReverseCharging(Intent intent) {
        UsbDevice usbDevice;
        Object[] objArr;
        Object[] objArr2;
        if (isReverseSupported()) {
            String action = intent.getAction();
            boolean z = true;
            if (!"android.intent.action.BATTERY_CHANGED".equals(action)) {
                if ("android.os.action.POWER_SAVE_MODE_CHANGED".equals(action)) {
                    if (this.mReverseChargingEnabled && this.mPowerSave) {
                        Log.i("ReverseChargingControl", "handleIntentForReverseCharging(): power save, stop");
                        setReverseStateInternal(105, false);
                        return;
                    }
                    return;
                }
                if (!TextUtils.equals(action, "android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                    if (TextUtils.equals(action, "android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                        if (this.mDoesNfcConflictWithUsbAudio && (usbDevice = (UsbDevice) intent.getParcelableExtra("device")) != null) {
                            checkAndChangeNfcPollingAgainstUsbAudioDevice(true, usbDevice);
                        }
                        this.mIsUsbPlugIn = false;
                        return;
                    }
                    return;
                }
                UsbDevice usbDevice2 = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice2 == null) {
                    Log.w("ReverseChargingControl", "handleIntentForReverseCharging() UsbDevice is null!");
                    this.mIsUsbPlugIn = false;
                    return;
                }
                if (this.mDoesNfcConflictWithUsbAudio) {
                    checkAndChangeNfcPollingAgainstUsbAudioDevice(false, usbDevice2);
                }
                int i = 0;
                while (true) {
                    if (i >= usbDevice2.getInterfaceCount()) {
                        objArr = false;
                        break;
                    } else {
                        if (usbDevice2.getInterface(i).getInterfaceClass() == 1) {
                            objArr = true;
                            break;
                        }
                        i++;
                    }
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= usbDevice2.getConfigurationCount()) {
                        objArr2 = false;
                        break;
                    } else {
                        if (usbDevice2.getConfiguration(i2).getMaxPower() < 100) {
                            objArr2 = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (objArr != false && objArr2 != false) {
                    z = false;
                }
                this.mIsUsbPlugIn = z;
                if (this.mReverseChargingEnabled && z) {
                    setReverseStateInternal(108, false);
                    Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): stop reverse charging because USB-C plugin!");
                    return;
                }
                return;
            }
            boolean z2 = this.mPluggedAc;
            this.mLevel = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100));
            int intExtra = intent.getIntExtra("plugged", 0);
            this.mPluggedAc = intExtra == 1;
            StringBuilder sb = new StringBuilder("handleIntentForReverseCharging(): rtx=");
            sb.append(this.mReverseChargingEnabled ? 1 : 0);
            sb.append(" wlc=");
            ViewPager$$ExternalSyntheticOutline0.m(sb, this.mWirelessCharging ? 1 : 0, " plgac=", z2 ? 1 : 0, " ac=");
            sb.append(this.mPluggedAc ? 1 : 0);
            sb.append(" acrtx=");
            ViewPager$$ExternalSyntheticOutline0.m(sb, this.mStopReverseAtAcUnplug ? 1 : 0, " extra=", intExtra, " this=");
            sb.append(this);
            Log.i("ReverseChargingControl", sb.toString());
            boolean z3 = this.mReverseChargingEnabled;
            if (z3 && this.mWirelessCharging) {
                if (DEBUG) {
                    Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wireless charging, stop");
                }
                setReverseStateInternal(102, false);
                return;
            }
            if (z3 && z2 && !this.mPluggedAc && this.mStopReverseAtAcUnplug) {
                if (DEBUG) {
                    Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wired charging, stop");
                }
                this.mStopReverseAtAcUnplug = false;
                setReverseStateInternal(106, false);
                return;
            }
            if (z3 && isLowBattery()) {
                if (DEBUG) {
                    Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): lower then battery threshold, stop");
                }
                setReverseStateInternal(4, false);
                return;
            }
            if (this.mReverseChargingEnabled || z2 || !this.mPluggedAc) {
                return;
            }
            if (this.mCurrentRtxMode == 0) {
                Log.d("ReverseChargingControl", "RTX is disabled");
                return;
            }
            if (Settings.Global.getInt(this.mContext.getContentResolver(), "settings_key_reverse_charging_auto_turn_on", 0) != 1) {
                Log.d("ReverseChargingControl", "auto turn on is disabled");
                return;
            }
            if (!this.mBootCompleted) {
                Log.i("ReverseChargingControl", "skip auto turn on");
                return;
            }
            if (DEBUG) {
                Log.d("ReverseChargingControl", "handleIntentForReverseCharging(): wired charging, start");
            }
            this.mStopReverseAtAcUnplug = true;
            setReverseStateInternal(3, true);
        }
    }

    public final void init(BatteryControllerImplGoogle batteryControllerImplGoogle) {
        if (!((UserManager) this.mContext.getSystemService(UserManager.class)).isSystemUser()) {
            Log.i("ReverseChargingControl", "Skip initialization for non system user");
            this.mCacheIsReverseSupported = true;
            this.mIsReverseSupported = false;
            return;
        }
        batteryControllerImplGoogle.addCallback(this.mBatteryStateChangeCallback);
        this.mCacheIsReverseSupported = false;
        this.mReverseChargingEnabled = false;
        this.mRtxLevel = -1;
        this.mName = null;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.mBroadcastDispatcher.registerReceiver(this, intentFilter);
        this.mBootCompleteCache.addListener(this.mBootCompleteListener);
        if (this.mRtxChargerManagerOptional.isPresent()) {
            if (this.mRtxChargerManagerOptional.isPresent()) {
                this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, false, 0));
            } else {
                Log.i("ReverseChargingControl", "setRtxMode(): rtx not available");
            }
            ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
            ReverseChargingController$$ExternalSyntheticLambda9 reverseChargingController$$ExternalSyntheticLambda9 = new ReverseChargingController$$ExternalSyntheticLambda9(this);
            synchronized (reverseWirelessCharger.mLock) {
                reverseWirelessCharger.mRtxStatusCallbacks.add(reverseChargingController$$ExternalSyntheticLambda9);
            }
            try {
                if (this.mSkinThermalEventListener == null) {
                    this.mSkinThermalEventListener = new SkinThermalEventListener();
                }
                this.mThermalService.registerThermalEventListenerWithType(this.mSkinThermalEventListener, 3);
            } catch (RemoteException e) {
                Log.e("ReverseChargingControl", "Could not register thermal event listener, exception: " + e);
            }
        }
    }

    public final boolean isLowBattery() {
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), "advanced_battery_usage_amount", 2) * 5;
        if (this.mLevel > i) {
            return false;
        }
        Log.w("ReverseChargingControl", "The battery is lower than threshold turn off reverse charging ! level : " + this.mLevel + ", threshold : " + i);
        return true;
    }

    public final boolean isReverseSupported() {
        if (this.mCacheIsReverseSupported) {
            return this.mIsReverseSupported;
        }
        boolean z = false;
        if (!this.mRtxChargerManagerOptional.isPresent()) {
            if (DEBUG) {
                Log.d("ReverseChargingControl", "isReverseSupported(): mRtxChargerManagerOptional is not present!");
            }
            return false;
        }
        ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) this.mRtxChargerManagerOptional.get();
        if (reverseWirelessCharger.initHALInterface()) {
            try {
                z = ((IWirelessCharger.Stub.Proxy) reverseWirelessCharger.mWirelessCharger).isRtxSupported();
            } catch (Exception e) {
                Log.w("ReverseWirelessCharger", "isRtxSupported fail: ", e);
            }
        }
        this.mIsReverseSupported = z;
        this.mCacheIsReverseSupported = true;
        return z;
    }

    public final void logReverseStartEvent(int i) {
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("logReverseStartEvent: ", "ReverseChargingControl", i);
        }
        this.mReverseStartTime = SystemClock.uptimeMillis();
        int i2 = this.mLevel;
        boolean z = ReverseChargingMetrics.DEBUG;
        VendorAtom vendorAtom = new VendorAtom();
        vendorAtom.reverseDomainName = "";
        VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[2];
        vendorAtom.values = vendorAtomValueArr;
        vendorAtom.atomId = 100037;
        vendorAtomValueArr[0] = VendorAtomValue.intValue(i);
        vendorAtom.values[1] = VendorAtomValue.intValue(i2);
        ReverseChargingMetrics.reportVendorAtom(vendorAtom);
    }

    public final void logReverseStopEvent(int i) {
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("logReverseStopEvent: ", "ReverseChargingControl", i);
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        int i2 = this.mLevel;
        long j = (uptimeMillis - this.mReverseStartTime) / 1000;
        boolean z = ReverseChargingMetrics.DEBUG;
        VendorAtom vendorAtom = new VendorAtom();
        vendorAtom.reverseDomainName = "";
        VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[3];
        vendorAtom.values = vendorAtomValueArr;
        vendorAtom.atomId = 100038;
        vendorAtomValueArr[0] = VendorAtomValue.intValue(i);
        vendorAtom.values[1] = VendorAtomValue.intValue(i2);
        vendorAtom.values[2] = new VendorAtomValue(1, Long.valueOf(j));
        ReverseChargingMetrics.reportVendorAtom(vendorAtom);
    }

    public final void onAlarmRtxFinish(int i) {
        Log.i("ReverseChargingControl", "onAlarmRtxFinish(): rtx=0, reason: " + i);
        setReverseStateInternal(i, false);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        handleIntentForReverseCharging(intent);
    }

    public void onReverseStateChanged(final Bundle bundle) {
        StringBuilder sb = new StringBuilder("onReverseStateChanged(): rtx=");
        sb.append(bundle.getInt("key_rtx_mode") != 1 ? 0 : 1);
        sb.append(" bundle=");
        sb.append(bundle);
        sb.append(" this=");
        sb.append(this);
        Log.i("ReverseChargingControl", sb.toString());
        this.mBgExecutor.execute(new Runnable() { // from class: com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                ReverseChargingController reverseChargingController = ReverseChargingController.this;
                Bundle bundle2 = bundle;
                boolean z = ReverseChargingController.DEBUG;
                int i = 0;
                if (z) {
                    StringBuilder sb2 = new StringBuilder("onReverseStateChangedOnBackgroundThread(): rtx=");
                    sb2.append(bundle2.getInt("key_rtx_mode") == 1 ? 1 : 0);
                    sb2.append(" bundle=");
                    sb2.append(bundle2);
                    sb2.append(" this=");
                    sb2.append(reverseChargingController);
                    Log.d("ReverseChargingControl", sb2.toString());
                }
                int i2 = bundle2.getInt("key_rtx_mode");
                int i3 = bundle2.getInt("key_reason_type");
                boolean z2 = bundle2.getBoolean("key_rtx_connection");
                int i4 = bundle2.getInt("key_accessory_type");
                int i5 = bundle2.getInt("key_rtx_level");
                if (!reverseChargingController.mReverseChargingEnabled && reverseChargingController.mWirelessCharging && i2 == 0 && i5 > 0) {
                    reverseChargingController.mRtxLevel = i5;
                    if (TextUtils.isEmpty(reverseChargingController.mName)) {
                        reverseChargingController.mName = reverseChargingController.mContext.getString(R.string.reverse_charging_device_name_text);
                    }
                    reverseChargingController.fireReverseChanged$1();
                    return;
                }
                if (!reverseChargingController.isReverseSupported()) {
                    reverseChargingController.mReverseChargingEnabled = false;
                    reverseChargingController.mRtxLevel = -1;
                    reverseChargingController.mName = null;
                    reverseChargingController.fireReverseChanged$1();
                    return;
                }
                int i6 = reverseChargingController.mCurrentRtxMode;
                if (i6 == 1 && i2 != 1 && reverseChargingController.mReverseChargingEnabled) {
                    if (i3 != 0) {
                        if (i3 == 1) {
                            reverseChargingController.logReverseStopEvent(4);
                        } else if (i3 == 2) {
                            reverseChargingController.logReverseStopEvent(3);
                        } else if (i3 == 3) {
                            reverseChargingController.logReverseStopEvent(102);
                        } else if (i3 == 4) {
                            reverseChargingController.logReverseStopEvent(110);
                        } else if (i3 == 15) {
                            reverseChargingController.logReverseStopEvent(8);
                        }
                    } else if (i2 != 2 || reverseChargingController.mCurrentRtxReceiverType == 0) {
                        reverseChargingController.logReverseStopEvent(1);
                    } else {
                        reverseChargingController.logReverseStopEvent(8);
                    }
                    RecordingInputConnection$$ExternalSyntheticOutline0.m("Reverse charging error happened : ", "ReverseChargingControl", i3);
                } else if (i6 != 1 && i2 == 1 && !reverseChargingController.mReverseChargingEnabled) {
                    reverseChargingController.logReverseStartEvent(1);
                }
                if (reverseChargingController.mCurrentRtxMode != 1 && i2 == 1 && !reverseChargingController.mReverseChargingEnabled && reverseChargingController.mDoesNfcConflictWithWlc && !reverseChargingController.mRestoreWlcNfcPollingMode) {
                    reverseChargingController.enableNfcPollingMode(false);
                    reverseChargingController.mRestoreWlcNfcPollingMode = true;
                }
                reverseChargingController.mCurrentRtxMode = i2;
                reverseChargingController.mReverseChargingEnabled = false;
                reverseChargingController.mRtxLevel = -1;
                reverseChargingController.mName = null;
                if (i2 == 1) {
                    boolean z3 = reverseChargingController.mProvidingBattery;
                    if (z3 || !z2) {
                        if (z3 && !z2) {
                            if (z) {
                                StringBuilder sb3 = new StringBuilder("playSoundIfNecessary() play end charging sound: ");
                                sb3.append(z2);
                                sb3.append(", accType : ");
                                sb3.append(i4);
                                sb3.append(", mStartReConnected : ");
                                CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb3, reverseChargingController.mStartReconnected, "ReverseChargingControl");
                            }
                            if (!reverseChargingController.mStartReconnected && (i4 == 16 || i4 == 90 || i4 == 114)) {
                                reverseChargingController.mStartReconnected = true;
                                if (z) {
                                    Log.w("ReverseChargingControl", "playSoundIfNecessary() start reconnected");
                                }
                            }
                        }
                        str = null;
                    } else {
                        if (z) {
                            StringBuilder sb4 = new StringBuilder("playSoundIfNecessary() play start charging sound: ");
                            sb4.append(z2);
                            sb4.append(", accType : ");
                            sb4.append(i4);
                            sb4.append(", mStartReconnected : ");
                            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb4, reverseChargingController.mStartReconnected, "ReverseChargingControl");
                        }
                        str = (reverseChargingController.mStartReconnected && (i4 == 16 || i4 == 90 || i4 == 114)) ? null : reverseChargingController.mContext.getString(R.string.reverse_charging_started_sound);
                        reverseChargingController.mStartReconnected = false;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        reverseChargingController.playSound(RingtoneManager.getRingtone(reverseChargingController.mContext, new Uri.Builder().scheme("file").appendPath(str).build()));
                    }
                    reverseChargingController.mProvidingBattery = z2;
                    reverseChargingController.mReverseChargingEnabled = true;
                    if (z2) {
                        reverseChargingController.mStopReverseAtAcUnplug = false;
                        reverseChargingController.mRtxLevel = i5;
                        reverseChargingController.mUseRxRemovalTimeOut = true;
                        if (reverseChargingController.mCurrentRtxReceiverType != i4) {
                            if (z) {
                                Log.d("ReverseChargingControl", "receiver type updated: " + reverseChargingController.mCurrentRtxReceiverType + " " + i4);
                            }
                            if (z) {
                                ExifInterface$$ExternalSyntheticOutline0.m("logReverseAccessoryType: ", "ReverseChargingControl", i4);
                            }
                            if (i4 != 0) {
                                boolean z4 = ReverseChargingMetrics.DEBUG;
                                VendorAtom vendorAtom = new VendorAtom();
                                vendorAtom.reverseDomainName = "";
                                VendorAtomValue[] vendorAtomValueArr = new VendorAtomValue[1];
                                vendorAtom.values = vendorAtomValueArr;
                                vendorAtom.atomId = 100040;
                                vendorAtomValueArr[0] = VendorAtomValue.intValue((i4 == 16 || i4 == 114) ? 1 : 0);
                                ReverseChargingMetrics.reportVendorAtom(vendorAtom);
                            }
                            reverseChargingController.mCurrentRtxReceiverType = i4;
                        }
                    } else {
                        if (z) {
                            Log.d("ReverseChargingControl", "receiver is not available");
                        }
                        reverseChargingController.mRtxLevel = -1;
                        reverseChargingController.mCurrentRtxReceiverType = 0;
                    }
                } else {
                    reverseChargingController.mStopReverseAtAcUnplug = false;
                    reverseChargingController.mProvidingBattery = false;
                    reverseChargingController.mUseRxRemovalTimeOut = false;
                    reverseChargingController.mStartReconnected = false;
                    if (reverseChargingController.mDoesNfcConflictWithWlc && reverseChargingController.mRestoreWlcNfcPollingMode) {
                        reverseChargingController.mRestoreWlcNfcPollingMode = false;
                        reverseChargingController.enableNfcPollingMode(!reverseChargingController.mRestoreUsbNfcPollingMode);
                    }
                }
                if (i2 == 0 && (i3 == 4 || i3 == 5)) {
                    Log.i("ReverseChargingControl", "disable RTX by reason: " + i3);
                    reverseChargingController.mRtxLevel = -100;
                }
                reverseChargingController.fireReverseChanged$1();
                reverseChargingController.cancelRtxTimer(0);
                reverseChargingController.cancelRtxTimer(1);
                reverseChargingController.cancelRtxTimer(4);
                if (!reverseChargingController.mStartReconnected) {
                    reverseChargingController.cancelRtxTimer(3);
                }
                boolean z5 = reverseChargingController.mReverseChargingEnabled;
                if (!z5 || reverseChargingController.mRtxLevel != -1) {
                    if (!z5 || reverseChargingController.mRtxLevel < 100) {
                        return;
                    }
                    if (z) {
                        Log.d("ReverseChargingControl", "onReverseStateChangedOnBackgroundThread(): rtx=" + (reverseChargingController.mReverseChargingEnabled ? 1 : 0) + ", Rx fully charged, setRtxTimer, REVERSE_FINISH_RX_FULL");
                    }
                    reverseChargingController.setRtxTimer(0L, 1);
                    return;
                }
                long j = reverseChargingController.mStartReconnected ? i4 == 16 ? ReverseChargingController.DURATION_TO_ADVANCED_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT : i4 == 114 ? ReverseChargingController.DURATION_TO_ADVANCED_PHONE_RECONNECTED_TIME_OUT : ReverseChargingController.DURATION_TO_ADVANCED_PLUS_ACCESSORY_DEVICE_RECONNECTED_TIME_OUT : reverseChargingController.mStopReverseAtAcUnplug ? ReverseChargingController.DURATION_TO_REVERSE_AC_TIME_OUT : reverseChargingController.mUseRxRemovalTimeOut ? ReverseChargingController.DURATION_TO_REVERSE_RX_REMOVAL_TIME_OUT : ReverseChargingController.DURATION_TO_REVERSE_TIME_OUT;
                String str2 = SystemProperties.get(reverseChargingController.mStopReverseAtAcUnplug ? "rtx.ac.timeout" : "rtx.timeout");
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        j = Long.parseLong(str2);
                    } catch (NumberFormatException e) {
                        Log.w("ReverseChargingControl", "getRtxTimeOut(): invalid timeout, " + e);
                    }
                }
                if (ReverseChargingController.DEBUG) {
                    Log.d("ReverseChargingControl", "onReverseStateChangedOnBackgroundThread(): time out, setRtxTimer, duration=" + j);
                }
                if (reverseChargingController.mStartReconnected) {
                    i = 3;
                } else if (reverseChargingController.mUseRxRemovalTimeOut && !reverseChargingController.mStopReverseAtAcUnplug) {
                    i = 4;
                }
                reverseChargingController.setRtxTimer(j, i);
            }
        });
    }

    public void playSound(Ringtone ringtone) {
        if (ringtone != null) {
            ringtone.setStreamType(1);
            ringtone.play();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        ReverseChargingChangeCallback reverseChargingChangeCallback = (ReverseChargingChangeCallback) obj;
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.remove(reverseChargingChangeCallback);
        }
    }

    public final void setReverseStateInternal(int i, boolean z) {
        if (isReverseSupported()) {
            Log.i("ReverseChargingControl", "setReverseStateInternal(): rtx=" + (z ? 1 : 0) + ",reason=" + i);
            if (!z || this.mReverseChargingEnabled) {
                logReverseStopEvent(i);
            } else {
                logReverseStartEvent(i);
                if (this.mPowerSave) {
                    logReverseStopEvent(104);
                    return;
                } else if (isLowBattery()) {
                    logReverseStopEvent(100);
                    return;
                } else if (this.mIsUsbPlugIn) {
                    logReverseStopEvent(107);
                    return;
                }
            }
            if (z != this.mReverseChargingEnabled) {
                if (z && this.mDoesNfcConflictWithWlc && !this.mRestoreWlcNfcPollingMode) {
                    enableNfcPollingMode(false);
                    this.mRestoreWlcNfcPollingMode = true;
                }
                this.mReverseChargingEnabled = z;
                if (z) {
                    setRtxTimer(DURATION_TO_REVERSE_TIME_OUT, 0);
                }
                if (this.mRtxChargerManagerOptional.isPresent()) {
                    this.mBgExecutor.execute(new ReverseChargingController$$ExternalSyntheticLambda0(this, z, 0));
                } else {
                    Log.i("ReverseChargingControl", "setRtxMode(): rtx not available");
                }
            }
        }
    }

    public final void setRtxTimer(long j, int i) {
        if (i == 0) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mRtxFinishAlarmAction, null);
            return;
        }
        if (i == 1) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mRtxFinishRxFullAlarmAction, null);
            return;
        }
        if (i == 2) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mCheckNfcConflictWithUsbAudioAlarmAction, null);
        } else if (i == 3) {
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mReconnectedTimeoutAlarmAction, null);
        } else {
            if (i != 4) {
                return;
            }
            this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + j, "ReverseChargingControl", this.mAccessoryDeviceRemovedTimeoutAlarmAction, null);
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(ReverseChargingChangeCallback reverseChargingChangeCallback) {
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.add(reverseChargingChangeCallback);
        }
        BatteryControllerImplGoogle batteryControllerImplGoogle = (BatteryControllerImplGoogle) reverseChargingChangeCallback;
        batteryControllerImplGoogle.onReverseChargingChanged(this.mRtxLevel, this.mName, this.mReverseChargingEnabled);
    }
}
