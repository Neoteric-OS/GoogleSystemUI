package com.android.wifitrackerlib;

import android.app.ActivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiScanner;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.os.BuildCompat;
import com.android.wifitrackerlib.BaseWifiTracker$Scanner;
import com.android.wifitrackerlib.WifiPickerTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BaseWifiTracker$Scanner extends Handler {
    public final AnonymousClass1 mFirstScanListener;
    public boolean mIsStartedState;
    public boolean mIsWifiEnabled;
    public final /* synthetic */ WifiPickerTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseWifiTracker$Scanner(WifiPickerTracker wifiPickerTracker, Looper looper) {
        super(looper);
        this.this$0 = wifiPickerTracker;
        this.mIsStartedState = false;
        this.mIsWifiEnabled = false;
        this.mFirstScanListener = new AnonymousClass1();
    }

    public final void possiblyStartScanning() {
        int i = 1;
        if (shouldScan()) {
            WifiPickerTracker wifiPickerTracker = this.this$0;
            String str = wifiPickerTracker.mTag;
            Log.i(str, "Scanning started");
            int i2 = BuildCompat.$r8$clinit;
            WifiScanner.ScanSettings scanSettings = new WifiScanner.ScanSettings();
            scanSettings.band = 3;
            scanSettings.setRnrSetting(1);
            scanSettings.reportEvents = 3;
            WifiScanner wifiScanner = (WifiScanner) wifiPickerTracker.mContext.getSystemService(WifiScanner.class);
            if (wifiScanner == null) {
                Log.e(str, "Failed to retrieve WifiScanner!");
                scanLoop();
                return;
            }
            AnonymousClass1 anonymousClass1 = this.mFirstScanListener;
            wifiScanner.stopScan(anonymousClass1);
            wifiPickerTracker.isVerboseLoggingEnabled();
            wifiScanner.startScan(scanSettings, anonymousClass1);
            WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback = wifiPickerTracker.mListener$1;
            if (wifiPickerTrackerCallback != null) {
                wifiPickerTracker.mMainHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda3(wifiPickerTrackerCallback, i));
            }
        }
    }

    public final void scanLoop() {
        boolean shouldScan = shouldScan();
        WifiPickerTracker wifiPickerTracker = this.this$0;
        String str = wifiPickerTracker.mTag;
        if (!shouldScan) {
            Log.e(str, "Scan loop called even though we shouldn't be scanning! mIsWifiEnabled=" + this.mIsWifiEnabled + " mIsStartedState=" + this.mIsStartedState);
            return;
        }
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        if (runningAppProcessInfo.importance > 200) {
            Log.e(str, "Scan loop called even though app isn't visible anymore! mIsWifiEnabled=" + this.mIsWifiEnabled + " mIsStartedState=" + this.mIsStartedState);
            return;
        }
        wifiPickerTracker.isVerboseLoggingEnabled();
        removeCallbacksAndMessages(null);
        wifiPickerTracker.mWifiManager.startScan();
        WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback = wifiPickerTracker.mListener$1;
        if (wifiPickerTrackerCallback != null) {
            wifiPickerTracker.mMainHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda3(wifiPickerTrackerCallback, 1));
        }
        postDelayed(new BaseWifiTracker$Scanner$$ExternalSyntheticLambda0(this, 2), wifiPickerTracker.mScanIntervalMillis);
    }

    public final boolean shouldScan() {
        return this.mIsWifiEnabled && this.mIsStartedState && !this.this$0.mIsScanningDisabled;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wifitrackerlib.BaseWifiTracker$Scanner$1, reason: invalid class name */
    public final class AnonymousClass1 implements WifiScanner.ScanListener {
        public AnonymousClass1() {
        }

        public final void onFailure(final int i, String str) {
            WifiPickerTracker wifiPickerTracker = BaseWifiTracker$Scanner.this.this$0;
            wifiPickerTracker.mWorkerHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.BaseWifiTracker$Scanner$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BaseWifiTracker$Scanner.AnonymousClass1 anonymousClass1 = BaseWifiTracker$Scanner.AnonymousClass1.this;
                    int i2 = i;
                    BaseWifiTracker$Scanner baseWifiTracker$Scanner = BaseWifiTracker$Scanner.this;
                    if (baseWifiTracker$Scanner.mIsWifiEnabled) {
                        Log.e(baseWifiTracker$Scanner.this$0.mTag, "Failed to scan! Reason: " + i2 + ", ");
                        BaseWifiTracker$Scanner.this.scanLoop();
                    }
                }
            });
        }

        public final void onResults(WifiScanner.ScanData[] scanDataArr) {
            WifiPickerTracker wifiPickerTracker = BaseWifiTracker$Scanner.this.this$0;
            wifiPickerTracker.mWorkerHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda0(1, this, scanDataArr));
        }

        public final void onFullResult(ScanResult scanResult) {
        }

        public final void onPeriodChanged(int i) {
        }

        public final void onSuccess() {
        }
    }
}
