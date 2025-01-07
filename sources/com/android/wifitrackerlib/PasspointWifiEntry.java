package com.android.wifitrackerlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.BuildCompat;
import androidx.core.util.Preconditions;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PasspointWifiEntry extends WifiEntry implements WifiEntry.WifiEntryCallback {
    public final List mCurrentHomeScanResults;
    public final List mCurrentRoamingScanResults;
    public final String mFqdn;
    public final String mFriendlyName;
    public final String mKey;
    public int mMeteredOverride;
    public OsuWifiEntry mOsuWifiEntry;
    public PasspointConfiguration mPasspointConfig;
    public boolean mShouldAutoOpenCaptivePortal;
    public long mSubscriptionExpirationTimeInMillis;
    public List mTargetSecurityTypes;
    public final String mUniqueId;
    public WifiConfiguration mWifiConfig;

    public PasspointWifiEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, PasspointConfiguration passpointConfiguration, WifiManager wifiManager) {
        super(wifiTrackerInjector, handler, wifiManager);
        this.mCurrentHomeScanResults = new ArrayList();
        this.mCurrentRoamingScanResults = new ArrayList();
        this.mTargetSecurityTypes = Arrays.asList(11, 12);
        this.mShouldAutoOpenCaptivePortal = false;
        this.mMeteredOverride = 0;
        Preconditions.checkNotNull(passpointConfiguration, "Cannot construct with null PasspointConfiguration!");
        this.mPasspointConfig = passpointConfiguration;
        String uniqueId = passpointConfiguration.getUniqueId();
        this.mUniqueId = uniqueId;
        this.mKey = uniqueIdToPasspointWifiEntryKey(uniqueId);
        String fqdn = passpointConfiguration.getHomeSp().getFqdn();
        this.mFqdn = fqdn;
        Preconditions.checkNotNull(fqdn, "Cannot construct with null PasspointConfiguration FQDN!");
        this.mFriendlyName = passpointConfiguration.getHomeSp().getFriendlyName();
        this.mSubscriptionExpirationTimeInMillis = passpointConfiguration.getSubscriptionExpirationTimeMillis();
        this.mMeteredOverride = this.mPasspointConfig.getMeteredOverride();
    }

    public static String uniqueIdToPasspointWifiEntryKey(String str) {
        Preconditions.checkNotNull(str, "Cannot create key with null unique id!");
        return "PasspointWifiEntry:".concat(str);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canConnect() {
        boolean z = false;
        if (isExpired()) {
            OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
            if (osuWifiEntry != null && osuWifiEntry.canConnect()) {
                z = true;
            }
            return z;
        }
        if (this.mScanResultLevel != -1 && getConnectedState() == 0 && this.mWifiConfig != null) {
            z = true;
        }
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final boolean canDisconnect() {
        return getConnectedState() == 2;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canSetMeteredChoice() {
        boolean z;
        if (!isSuggestion()) {
            z = this.mPasspointConfig != null;
        }
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canSignIn() {
        boolean z;
        NetworkCapabilities networkCapabilities;
        if (this.mNetwork != null && (networkCapabilities = this.mNetworkCapabilities) != null) {
            z = networkCapabilities.hasCapability(17);
        }
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback) {
        OsuWifiEntry osuWifiEntry;
        if (isExpired() && (osuWifiEntry = this.mOsuWifiEntry) != null) {
            osuWifiEntry.connect(wifiEntryConnectCallback);
            return;
        }
        this.mShouldAutoOpenCaptivePortal = true;
        this.mConnectCallback = wifiEntryConnectCallback;
        if (this.mWifiConfig == null) {
            new WifiEntry.ConnectActionListener().onFailure(0);
        }
        this.mWifiManager.stopRestrictingAutoJoinToSubscriptionId();
        this.mWifiManager.connect(this.mWifiConfig, new WifiEntry.ConnectActionListener());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final boolean connectionInfoMatches(WifiInfo wifiInfo) {
        if (!wifiInfo.isPasspointAp()) {
            return false;
        }
        int i = BuildCompat.$r8$clinit;
        return TextUtils.equals(this.mUniqueId, wifiInfo.getPasspointUniqueId());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized int getConnectedState() {
        OsuWifiEntry osuWifiEntry;
        return (isExpired() && super.getConnectedState() == 0 && (osuWifiEntry = this.mOsuWifiEntry) != null) ? osuWifiEntry.getConnectedState() : super.getConnectedState();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized int getMeteredChoice() {
        int i = this.mMeteredOverride;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getNetworkSelectionDescription() {
        return Utils.getNetworkSelectionDescription(this.mWifiConfig);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getScanResultDescription() {
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getSecurityString() {
        return this.mContext.getString(R.string.wifitrackerlib_wifi_security_passpoint);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized List getSecurityTypes() {
        return new ArrayList(this.mTargetSecurityTypes);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSsid() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null) {
            return WifiInfo.sanitizeSsid(wifiInfo.getSSID());
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null ? WifiInfo.sanitizeSsid(wifiConfiguration.SSID) : null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getStandardString() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null) {
            return Utils.getStandardString(wifiInfo.getWifiStandard(), this.mContext);
        }
        if (!this.mCurrentHomeScanResults.isEmpty()) {
            return Utils.getStandardString(((ScanResult) ((ArrayList) this.mCurrentHomeScanResults).get(0)).getWifiStandard(), this.mContext);
        }
        if (this.mCurrentRoamingScanResults.isEmpty()) {
            return "";
        }
        return Utils.getStandardString(((ScanResult) ((ArrayList) this.mCurrentRoamingScanResults).get(0)).getWifiStandard(), this.mContext);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSummary(boolean z) {
        String disconnectedDescription;
        String obj;
        boolean z2 = true;
        synchronized (this) {
            try {
                StringJoiner stringJoiner = new StringJoiner(this.mContext.getString(R.string.wifitrackerlib_summary_separator));
                if (isExpired()) {
                    OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
                    if (osuWifiEntry != null) {
                        stringJoiner.add(osuWifiEntry.getSummary(z));
                    } else {
                        stringJoiner.add(this.mContext.getString(R.string.wifitrackerlib_wifi_passpoint_expired));
                    }
                } else {
                    int connectedState = getConnectedState();
                    if (connectedState == 0) {
                        disconnectedDescription = Utils.getDisconnectedDescription(this.mInjector, this.mContext, this.mWifiConfig, z);
                    } else if (connectedState != 1) {
                        disconnectedDescription = null;
                        if (connectedState != 2) {
                            Log.e("PasspointWifiEntry", "getConnectedState() returned unknown state: " + connectedState);
                        } else {
                            NetworkCapabilities networkCapabilities = this.mNetworkCapabilities;
                            if (networkCapabilities == null) {
                                Log.e("PasspointWifiEntry", "Tried to get CONNECTED description, but mNetworkCapabilities was unexpectedly null!");
                            } else {
                                disconnectedDescription = Utils.getConnectedDescription(this.mContext, this.mWifiConfig, networkCapabilities, this.mWifiInfo, isDefaultNetwork(), isLowQuality(), this.mConnectivityReport);
                            }
                        }
                    } else {
                        disconnectedDescription = Utils.getConnectingDescription(this.mContext, this.mNetworkInfo);
                    }
                    if (!TextUtils.isEmpty(disconnectedDescription)) {
                        stringJoiner.add(disconnectedDescription);
                    }
                }
                Context context = this.mContext;
                String str = "";
                if (context != null) {
                    synchronized (this) {
                        if (this.mPasspointConfig == null) {
                            if (this.mWifiConfig == null) {
                                z2 = false;
                            }
                        }
                        if (z2 && !isAutoJoinEnabled()) {
                            str = context.getString(R.string.wifitrackerlib_auto_connect_disable);
                        }
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    stringJoiner.add(str);
                }
                String meteredDescription = Utils.getMeteredDescription(this.mContext, this);
                if (!TextUtils.isEmpty(meteredDescription)) {
                    stringJoiner.add(meteredDescription);
                }
                if (!z && isVerboseSummaryEnabled()) {
                    String verboseSummary = Utils.getVerboseSummary(this);
                    if (!TextUtils.isEmpty(verboseSummary)) {
                        stringJoiner.add(verboseSummary);
                    }
                }
                obj = stringJoiner.toString();
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getTitle() {
        return this.mFriendlyName;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isAutoJoinEnabled() {
        PasspointConfiguration passpointConfiguration = this.mPasspointConfig;
        if (passpointConfiguration != null) {
            return passpointConfiguration.isAutojoinEnabled();
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration == null) {
            return false;
        }
        return wifiConfiguration.allowAutojoin;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isExpired() {
        if (this.mSubscriptionExpirationTimeInMillis <= 0) {
            return false;
        }
        return System.currentTimeMillis() >= this.mSubscriptionExpirationTimeInMillis;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000e, code lost:
    
        if (r0.meteredHint != false) goto L13;
     */
    @Override // com.android.wifitrackerlib.WifiEntry
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean isMetered() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.getMeteredChoice()     // Catch: java.lang.Throwable -> L11
            r1 = 1
            if (r0 == r1) goto L14
            android.net.wifi.WifiConfiguration r0 = r2.mWifiConfig     // Catch: java.lang.Throwable -> L11
            if (r0 == 0) goto L13
            boolean r0 = r0.meteredHint     // Catch: java.lang.Throwable -> L11
            if (r0 == 0) goto L13
            goto L14
        L11:
            r0 = move-exception
            goto L16
        L13:
            r1 = 0
        L14:
            monitor-exit(r2)
            return r1
        L16:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L11
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.PasspointWifiEntry.isMetered():boolean");
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isSubscription() {
        return this.mPasspointConfig != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isSuggestion() {
        boolean z;
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null) {
            z = wifiConfiguration.fromWifiNetworkSuggestion;
        }
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void onNetworkCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onNetworkCapabilitiesChanged(network, networkCapabilities);
        if (canSignIn() && this.mShouldAutoOpenCaptivePortal) {
            this.mShouldAutoOpenCaptivePortal = false;
            if (canSignIn()) {
                ((ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class)).startCaptivePortalApp(this.mNetwork);
            }
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String toString() {
        StringJoiner stringJoiner = new StringJoiner("][", "[", "]");
        stringJoiner.add("FQDN:" + this.mFqdn);
        stringJoiner.add("FriendlyName:" + this.mFriendlyName);
        if (this.mPasspointConfig != null) {
            stringJoiner.add("UniqueId:" + this.mPasspointConfig.getUniqueId());
        } else if (this.mWifiConfig != null) {
            stringJoiner.add("UniqueId:" + this.mWifiConfig.getKey());
        }
        return super.toString() + stringJoiner;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void updateSecurityTypes() {
        int currentSecurityType;
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null || (currentSecurityType = wifiInfo.getCurrentSecurityType()) == -1) {
            return;
        }
        this.mTargetSecurityTypes = Collections.singletonList(Integer.valueOf(currentSecurityType));
    }

    public PasspointWifiEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, WifiConfiguration wifiConfiguration, WifiManager wifiManager) {
        super(wifiTrackerInjector, handler, wifiManager);
        this.mCurrentHomeScanResults = new ArrayList();
        this.mCurrentRoamingScanResults = new ArrayList();
        this.mTargetSecurityTypes = Arrays.asList(11, 12);
        this.mShouldAutoOpenCaptivePortal = false;
        this.mMeteredOverride = 0;
        if (wifiConfiguration.isPasspoint()) {
            this.mWifiConfig = wifiConfiguration;
            String key = wifiConfiguration.getKey();
            this.mUniqueId = key;
            this.mKey = uniqueIdToPasspointWifiEntryKey(key);
            String str = wifiConfiguration.FQDN;
            this.mFqdn = str;
            Preconditions.checkNotNull(str, "Cannot construct with null WifiConfiguration FQDN!");
            this.mFriendlyName = this.mWifiConfig.providerFriendlyName;
            return;
        }
        throw new IllegalArgumentException("Given WifiConfiguration is not for Passpoint!");
    }
}
