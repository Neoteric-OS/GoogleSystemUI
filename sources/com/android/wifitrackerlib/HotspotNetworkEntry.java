package com.android.wifitrackerlib;

import android.content.Context;
import android.icu.text.MessageFormat;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.net.wifi.sharedconnectivity.app.SharedConnectivityManager;
import android.os.Handler;
import android.text.BidiFormatter;
import android.util.Log;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wifitrackerlib.StandardWifiEntry;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HotspotNetworkEntry extends WifiEntry {
    public boolean mConnectionError;
    public final Context mContext;
    public HotspotNetwork mHotspotNetworkData;
    public HotspotNetworkEntryKey mKey;
    public int mLastStatus;
    public final SharedConnectivityManager mSharedConnectivityManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HotspotNetworkEntryKey {
        public final long mDeviceId;
        public final boolean mIsVirtualEntry;
        public final StandardWifiEntry.ScanResultKey mScanResultKey;

        public HotspotNetworkEntryKey(HotspotNetwork hotspotNetwork) {
            this.mDeviceId = hotspotNetwork.getDeviceId();
            if (hotspotNetwork.getHotspotSsid() == null || hotspotNetwork.getHotspotSecurityTypes() == null) {
                this.mIsVirtualEntry = true;
                this.mScanResultKey = null;
            } else {
                this.mIsVirtualEntry = false;
                this.mScanResultKey = new StandardWifiEntry.ScanResultKey(hotspotNetwork.getHotspotSsid(), new ArrayList(hotspotNetwork.getHotspotSecurityTypes()));
            }
        }

        public final String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("IS_VIRTUAL_ENTRY_KEY", this.mIsVirtualEntry);
                jSONObject.put("DEVICE_ID_KEY", this.mDeviceId);
                StandardWifiEntry.ScanResultKey scanResultKey = this.mScanResultKey;
                if (scanResultKey != null) {
                    jSONObject.put("SCAN_RESULT_KEY", scanResultKey.toString());
                }
            } catch (JSONException e) {
                Log.wtf("HotspotNetworkEntry", "JSONException while converting HotspotNetworkEntryKey to string: " + e);
            }
            return "HotspotNetworkEntry:" + jSONObject.toString();
        }
    }

    public HotspotNetworkEntry(WifiTrackerInjector wifiTrackerInjector, Context context, Handler handler, WifiManager wifiManager, SharedConnectivityManager sharedConnectivityManager, HotspotNetwork hotspotNetwork) {
        super(wifiTrackerInjector, handler, wifiManager);
        this.mLastStatus = 0;
        this.mConnectionError = false;
        this.mContext = context;
        this.mSharedConnectivityManager = sharedConnectivityManager;
        this.mHotspotNetworkData = hotspotNetwork;
        this.mKey = new HotspotNetworkEntryKey(hotspotNetwork);
    }

    public static String getDeviceTypeId(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN" : "VEHICLE" : "WATCH" : "COMPUTER" : "TABLET" : "PHONE";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canConnect() {
        return getConnectedState() == 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canDisconnect() {
        return getConnectedState() != 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback) {
        this.mConnectCallback = wifiEntryConnectCallback;
        SharedConnectivityManager sharedConnectivityManager = this.mSharedConnectivityManager;
        if (sharedConnectivityManager == null) {
            this.mCallbackHandler.post(new HotspotNetworkEntry$$ExternalSyntheticLambda0(2, wifiEntryConnectCallback));
        } else {
            sharedConnectivityManager.connectHotspotNetwork(this.mHotspotNetworkData);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean connectionInfoMatches(WifiInfo wifiInfo) {
        HotspotNetworkEntryKey hotspotNetworkEntryKey = this.mKey;
        if (hotspotNetworkEntryKey.mIsVirtualEntry) {
            return false;
        }
        return Objects.equals(hotspotNetworkEntryKey.mScanResultKey, new StandardWifiEntry.ScanResultKey(WifiInfo.sanitizeSsid(wifiInfo.getSSID()), Collections.singletonList(Integer.valueOf(wifiInfo.getCurrentSecurityType()))));
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized int getConnectedState() {
        return super.getConnectedState();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getKey() {
        return this.mKey.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final int getLevel() {
        if (getConnectedState() == 0) {
            return 4;
        }
        return super.getLevel();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSecurityString() {
        if (this.mHotspotNetworkData == null) {
            return "";
        }
        return Utils.getSecurityString(this.mContext, new ArrayList(this.mHotspotNetworkData.getHotspotSecurityTypes()), true);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSsid() {
        StandardWifiEntry.ScanResultKey scanResultKey = this.mKey.mScanResultKey;
        if (scanResultKey == null) {
            return null;
        }
        return scanResultKey.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getStandardString() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return "";
        }
        return Utils.getStandardString(wifiInfo.getWifiStandard(), this.mContext);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSummary(boolean z) {
        if (this.mHotspotNetworkData == null) {
            return "";
        }
        if (this.mCalledConnect) {
            return this.mContext.getString(R.string.wifitrackerlib_hotspot_network_connecting);
        }
        if (!this.mConnectionError) {
            MessageFormat messageFormat = new MessageFormat(this.mContext.getString(R.string.wifitrackerlib_hotspot_network_summary_new));
            HashMap hashMap = new HashMap();
            hashMap.put("DEVICE_TYPE", getDeviceTypeId(this.mHotspotNetworkData.getNetworkProviderInfo().getDeviceType()));
            hashMap.put("NETWORK_NAME", this.mHotspotNetworkData.getNetworkName());
            return messageFormat.format(hashMap);
        }
        switch (this.mLastStatus) {
            case 3:
            case 4:
                return this.mContext.getString(R.string.wifitrackerlib_hotspot_network_summary_error_carrier_incomplete, BidiFormatter.getInstance().unicodeWrap(this.mHotspotNetworkData.getNetworkName()));
            case 5:
                return this.mContext.getString(R.string.wifitrackerlib_hotspot_network_summary_error_carrier_block, BidiFormatter.getInstance().unicodeWrap(this.mHotspotNetworkData.getNetworkName()));
            case 6:
            case 7:
            case 8:
            case 9:
                MessageFormat messageFormat2 = new MessageFormat(this.mContext.getString(R.string.wifitrackerlib_hotspot_network_summary_error_settings));
                HashMap hashMap2 = new HashMap();
                hashMap2.put("DEVICE_TYPE", getDeviceTypeId(this.mHotspotNetworkData.getNetworkProviderInfo().getDeviceType()));
                return messageFormat2.format(hashMap2);
            default:
                return this.mContext.getString(R.string.wifitrackerlib_hotspot_network_summary_error_generic);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getTitle() {
        HotspotNetwork hotspotNetwork = this.mHotspotNetworkData;
        if (hotspotNetwork == null) {
            return "";
        }
        return hotspotNetwork.getNetworkProviderInfo().getDeviceName();
    }

    public final void onConnectionStatusChanged(int i) {
        this.mLastStatus = i;
        Handler handler = this.mCallbackHandler;
        switch (i) {
            case 1:
                this.mCalledConnect = true;
                this.mConnectionError = false;
                notifyOnUpdated();
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                handler.post(new HotspotNetworkEntry$$ExternalSyntheticLambda0(0, this));
                this.mCalledConnect = false;
                this.mConnectionError = true;
                notifyOnUpdated();
                break;
            case 10:
                handler.post(new HotspotNetworkEntry$$ExternalSyntheticLambda0(1, this));
                this.mCalledConnect = false;
                this.mConnectionError = false;
                notifyOnUpdated();
                break;
        }
    }
}
