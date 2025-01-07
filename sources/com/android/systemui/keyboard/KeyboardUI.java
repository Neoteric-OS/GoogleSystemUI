package com.android.systemui.keyboard;

import android.R;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.Toast;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.settings.SecureSettings;
import dagger.internal.Provider;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardUI implements CoreStartable, InputManager.OnTabletModeChangedListener {
    public final BluetoothDialogDelegate mBluetoothDialogDelegate;
    public final Provider mBluetoothManagerProvider;
    public boolean mBootCompleted;
    public long mBootCompletedTime;
    public CachedBluetoothDeviceManager mCachedDeviceManager;
    public volatile Context mContext;
    public SystemUIDialog mDialog;
    public boolean mEnabled;
    public volatile KeyboardHandler mHandler;
    public String mKeyboardName;
    public LocalBluetoothAdapter mLocalBluetoothAdapter;
    public KeyboardScanCallback mScanCallback;
    public final SecureSettings mSecureSettings;
    public int mState;
    public volatile KeyboardHandler mUIHandler;
    public int mInTabletMode = -1;
    public int mScanAttempt = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BluetoothCallbackHandler implements BluetoothCallback {
        public BluetoothCallbackHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothCallback
        public final void onBluetoothStateChanged(int i) {
            KeyboardUI.this.mHandler.obtainMessage(4, i, 0).sendToTarget();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothCallback
        public final void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
            KeyboardUI.this.mHandler.obtainMessage(5, i, 0, cachedBluetoothDevice).sendToTarget();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BluetoothDialogClickListener implements DialogInterface.OnClickListener {
        public BluetoothDialogClickListener() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            KeyboardUI.this.mHandler.obtainMessage(3, -1 == i ? 1 : 0, 0).sendToTarget();
            KeyboardUI.this.mDialog = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BluetoothDialogDismissListener implements DialogInterface.OnDismissListener {
        public BluetoothDialogDismissListener() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            KeyboardUI.this.mDialog = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BluetoothErrorListener {
        public BluetoothErrorListener() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyboardScanCallback extends ScanCallback {
        public KeyboardScanCallback() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onBatchScanResults(List list) {
            Iterator it = list.iterator();
            BluetoothDevice bluetoothDevice = null;
            int i = Integer.MIN_VALUE;
            while (it.hasNext()) {
                ScanResult scanResult = (ScanResult) it.next();
                if ((scanResult.getScanRecord().getAdvertiseFlags() & 3) != 0 && scanResult.getRssi() > i) {
                    bluetoothDevice = scanResult.getDevice();
                    i = scanResult.getRssi();
                }
            }
            if (bluetoothDevice != null) {
                KeyboardUI.this.mHandler.obtainMessage(6, bluetoothDevice).sendToTarget();
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanFailed(int i) {
            KeyboardUI.this.mHandler.obtainMessage(7).sendToTarget();
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanResult(int i, ScanResult scanResult) {
            if ((scanResult.getScanRecord().getAdvertiseFlags() & 3) != 0) {
                KeyboardUI.this.mHandler.obtainMessage(6, scanResult.getDevice()).sendToTarget();
            }
        }
    }

    public KeyboardUI(Context context, Provider provider, SecureSettings secureSettings, BluetoothDialogDelegate bluetoothDialogDelegate) {
        this.mContext = context;
        this.mBluetoothManagerProvider = provider;
        this.mSecureSettings = secureSettings;
        this.mBluetoothDialogDelegate = bluetoothDialogDelegate;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "KeyboardUI:", "  mEnabled="), this.mEnabled, printWriter, "  mBootCompleted="), this.mEnabled, printWriter, "  mBootCompletedTime=");
        m.append(this.mBootCompletedTime);
        printWriter.println(m.toString());
        printWriter.println("  mKeyboardName=" + this.mKeyboardName);
        StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("  mInTabletMode="), this.mInTabletMode, printWriter, "  mState=");
        int i = this.mState;
        switch (i) {
            case -1:
                str = "STATE_NOT_ENABLED";
                break;
            case 0:
            default:
                str = GenericDocument$$ExternalSyntheticOutline0.m("STATE_UNKNOWN (", ")", i);
                break;
            case 1:
                str = "STATE_WAITING_FOR_BOOT_COMPLETED";
                break;
            case 2:
                str = "STATE_WAITING_FOR_TABLET_MODE_EXIT";
                break;
            case 3:
                str = "STATE_WAITING_FOR_DEVICE_DISCOVERY";
                break;
            case 4:
                str = "STATE_WAITING_FOR_BLUETOOTH";
                break;
            case 5:
                str = "STATE_PAIRING";
                break;
            case 6:
                str = "STATE_PAIRED";
                break;
            case 7:
                str = "STATE_PAIRING_FAILED";
                break;
            case 8:
                str = "STATE_USER_CANCELLED";
                break;
            case 9:
                str = "STATE_DEVICE_NOT_FOUND";
                break;
        }
        m2.append(str);
        printWriter.println(m2.toString());
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        this.mHandler.sendEmptyMessage(1);
    }

    public final void onTabletModeChanged(long j, boolean z) {
        if ((!z || this.mInTabletMode == 1) && (z || this.mInTabletMode == 0)) {
            return;
        }
        this.mInTabletMode = z ? 1 : 0;
        processKeyboardState();
    }

    public final void processKeyboardState() {
        CachedBluetoothDevice cachedBluetoothDevice;
        CachedBluetoothDevice cachedBluetoothDevice2;
        this.mHandler.removeMessages(2);
        if (!this.mEnabled) {
            this.mState = -1;
            return;
        }
        if (!this.mBootCompleted) {
            this.mState = 1;
            return;
        }
        if (this.mInTabletMode != 0) {
            int i = this.mState;
            if (i == 3) {
                stopScanning();
            } else if (i == 4) {
                this.mUIHandler.sendEmptyMessage(9);
            }
            this.mState = 2;
            return;
        }
        int state = this.mLocalBluetoothAdapter.mAdapter.getState();
        if ((state == 11 || state == 12) && this.mState == 4) {
            this.mUIHandler.sendEmptyMessage(9);
        }
        if (state == 11) {
            this.mState = 4;
            return;
        }
        if (state != 12) {
            this.mState = 4;
            if (this.mSecureSettings.getIntForUser("user_setup_complete", 0, -2) == 0) {
                this.mLocalBluetoothAdapter.mAdapter.enable();
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            long j = this.mBootCompletedTime + 10000;
            if (j < uptimeMillis) {
                this.mUIHandler.sendEmptyMessage(8);
                return;
            } else {
                this.mHandler.sendEmptyMessageAtTime(2, j);
                return;
            }
        }
        Iterator<BluetoothDevice> it = this.mLocalBluetoothAdapter.mAdapter.getBondedDevices().iterator();
        while (true) {
            cachedBluetoothDevice = null;
            if (!it.hasNext()) {
                cachedBluetoothDevice2 = null;
                break;
            }
            BluetoothDevice next = it.next();
            if (this.mKeyboardName.equals(next.getName())) {
                cachedBluetoothDevice2 = this.mCachedDeviceManager.findDevice(next);
                if (cachedBluetoothDevice2 == null) {
                    cachedBluetoothDevice2 = this.mCachedDeviceManager.addDevice(next);
                }
            }
        }
        int i2 = this.mState;
        if (i2 == 2 || i2 == 4) {
            if (cachedBluetoothDevice2 != null) {
                this.mState = 6;
                cachedBluetoothDevice2.connect$1();
                return;
            }
            this.mCachedDeviceManager.clearNonBondedDevices();
        }
        Iterator it2 = this.mCachedDeviceManager.getCachedDevicesCopy().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            CachedBluetoothDevice cachedBluetoothDevice3 = (CachedBluetoothDevice) it2.next();
            if (cachedBluetoothDevice3.getName().equals(this.mKeyboardName)) {
                cachedBluetoothDevice = cachedBluetoothDevice3;
                break;
            }
        }
        if (cachedBluetoothDevice != null) {
            this.mState = 5;
            cachedBluetoothDevice.startPairing();
            return;
        }
        this.mState = 3;
        BluetoothLeScanner bluetoothLeScanner = this.mLocalBluetoothAdapter.mAdapter.getBluetoothLeScanner();
        ScanFilter build = new ScanFilter.Builder().setDeviceName(this.mKeyboardName).build();
        ScanSettings build2 = new ScanSettings.Builder().setCallbackType(1).setNumOfMatches(1).setScanMode(2).setReportDelay(0L).build();
        this.mScanCallback = new KeyboardScanCallback();
        bluetoothLeScanner.startScan(Arrays.asList(build), build2, this.mScanCallback);
        KeyboardHandler keyboardHandler = this.mHandler;
        int i3 = this.mScanAttempt + 1;
        this.mScanAttempt = i3;
        this.mHandler.sendMessageDelayed(keyboardHandler.obtainMessage(10, i3, 0), 30000L);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        HandlerThread handlerThread = new HandlerThread("Keyboard", 10);
        handlerThread.start();
        this.mHandler = new KeyboardHandler(handlerThread.getLooper());
        this.mHandler.sendEmptyMessage(0);
    }

    public final void stopScanning() {
        if (this.mScanCallback != null) {
            BluetoothLeScanner bluetoothLeScanner = this.mLocalBluetoothAdapter.mAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.mScanCallback);
            }
            this.mScanCallback = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyboardHandler extends Handler {
        public final /* synthetic */ int $r8$classId = 1;

        public KeyboardHandler() {
            super(Looper.getMainLooper(), null, true);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            LocalBluetoothManager localBluetoothManager;
            SystemUIDialog systemUIDialog;
            switch (this.$r8$classId) {
                case 0:
                    switch (message.what) {
                        case 0:
                            KeyboardUI keyboardUI = KeyboardUI.this;
                            Context context = keyboardUI.mContext;
                            String string = context.getString(R.string.config_platformVpnConfirmDialogComponent);
                            keyboardUI.mKeyboardName = string;
                            if (!TextUtils.isEmpty(string) && (localBluetoothManager = (LocalBluetoothManager) keyboardUI.mBluetoothManagerProvider.get()) != null) {
                                keyboardUI.mEnabled = true;
                                keyboardUI.mCachedDeviceManager = localBluetoothManager.mCachedDeviceManager;
                                keyboardUI.mLocalBluetoothAdapter = localBluetoothManager.mLocalAdapter;
                                localBluetoothManager.mEventManager.registerCallback(keyboardUI.new BluetoothCallbackHandler());
                                BluetoothUtils.sErrorListener = keyboardUI.new BluetoothErrorListener();
                                InputManager inputManager = (InputManager) context.getSystemService(InputManager.class);
                                inputManager.registerOnTabletModeChangedListener(keyboardUI, keyboardUI.mHandler);
                                keyboardUI.mInTabletMode = inputManager.isInTabletMode();
                                keyboardUI.processKeyboardState();
                                keyboardUI.mUIHandler = keyboardUI.new KeyboardHandler();
                                break;
                            }
                            break;
                        case 1:
                            KeyboardUI keyboardUI2 = KeyboardUI.this;
                            keyboardUI2.mBootCompleted = true;
                            keyboardUI2.mBootCompletedTime = SystemClock.uptimeMillis();
                            if (keyboardUI2.mState == 1) {
                                keyboardUI2.processKeyboardState();
                                break;
                            }
                            break;
                        case 2:
                            KeyboardUI.this.processKeyboardState();
                            break;
                        case 3:
                            if (message.arg1 != 1) {
                                KeyboardUI.this.mState = 8;
                                break;
                            } else {
                                KeyboardUI.this.mLocalBluetoothAdapter.mAdapter.enable();
                                break;
                            }
                        case 4:
                            int i = message.arg1;
                            KeyboardUI keyboardUI3 = KeyboardUI.this;
                            if (i != 12) {
                                keyboardUI3.getClass();
                                break;
                            } else if (keyboardUI3.mState == 4) {
                                keyboardUI3.processKeyboardState();
                                break;
                            }
                            break;
                        case 5:
                            CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) message.obj;
                            int i2 = message.arg1;
                            KeyboardUI keyboardUI4 = KeyboardUI.this;
                            if (keyboardUI4.mState == 5 && cachedBluetoothDevice.getName().equals(keyboardUI4.mKeyboardName)) {
                                if (i2 != 12) {
                                    if (i2 == 10) {
                                        keyboardUI4.mState = 7;
                                        break;
                                    }
                                } else {
                                    keyboardUI4.mState = 6;
                                    break;
                                }
                            }
                            break;
                        case 6:
                            BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                            KeyboardUI keyboardUI5 = KeyboardUI.this;
                            CachedBluetoothDevice findDevice = keyboardUI5.mCachedDeviceManager.findDevice(bluetoothDevice);
                            if (findDevice == null) {
                                findDevice = keyboardUI5.mCachedDeviceManager.addDevice(bluetoothDevice);
                            }
                            KeyboardUI keyboardUI6 = KeyboardUI.this;
                            if (keyboardUI6.mState == 3 && findDevice.getName().equals(keyboardUI6.mKeyboardName)) {
                                keyboardUI6.stopScanning();
                                findDevice.startPairing();
                                keyboardUI6.mState = 5;
                                break;
                            }
                            break;
                        case 7:
                            KeyboardUI keyboardUI7 = KeyboardUI.this;
                            keyboardUI7.mScanCallback = null;
                            if (keyboardUI7.mState == 3) {
                                keyboardUI7.mState = 9;
                                break;
                            }
                            break;
                        case 10:
                            int i3 = message.arg1;
                            KeyboardUI keyboardUI8 = KeyboardUI.this;
                            if (keyboardUI8.mState == 3 && i3 == keyboardUI8.mScanAttempt) {
                                keyboardUI8.stopScanning();
                                keyboardUI8.mState = 9;
                                break;
                            }
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            Pair pair = (Pair) message.obj;
                            KeyboardUI keyboardUI9 = KeyboardUI.this;
                            Context context2 = (Context) pair.first;
                            String str = (String) pair.second;
                            int i4 = message.arg1;
                            int i5 = keyboardUI9.mState;
                            if ((i5 == 5 || i5 == 7) && keyboardUI9.mKeyboardName.equals(str)) {
                                Toast.makeText(context2, context2.getString(i4, str), 0).show();
                                break;
                            }
                            break;
                    }
                default:
                    int i6 = message.what;
                    KeyboardUI keyboardUI10 = KeyboardUI.this;
                    if (i6 == 8) {
                        if (keyboardUI10.mDialog == null) {
                            BluetoothDialogClickListener bluetoothDialogClickListener = keyboardUI10.new BluetoothDialogClickListener();
                            BluetoothDialogDismissListener bluetoothDialogDismissListener = keyboardUI10.new BluetoothDialogDismissListener();
                            SystemUIDialog createDialog = keyboardUI10.mBluetoothDialogDelegate.createDialog();
                            keyboardUI10.mDialog = createDialog;
                            createDialog.setTitle(com.android.wm.shell.R.string.enable_bluetooth_title);
                            keyboardUI10.mDialog.setMessage(com.android.wm.shell.R.string.enable_bluetooth_message);
                            keyboardUI10.mDialog.setPositiveButton(com.android.wm.shell.R.string.enable_bluetooth_confirmation_ok, bluetoothDialogClickListener);
                            keyboardUI10.mDialog.setNegativeButton$1(R.string.cancel, bluetoothDialogClickListener);
                            keyboardUI10.mDialog.setOnDismissListener(bluetoothDialogDismissListener);
                            keyboardUI10.mDialog.show();
                            break;
                        }
                    } else if (i6 == 9 && (systemUIDialog = keyboardUI10.mDialog) != null) {
                        systemUIDialog.dismiss();
                        break;
                    }
                    break;
            }
        }

        public KeyboardHandler(Looper looper) {
            super(looper, null, true);
        }
    }
}
