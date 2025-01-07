package com.android.settingslib.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.ProvisioningCallback;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.wm.shell.R;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessPoint implements Comparable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public String bssid;
    public WifiConfiguration mConfig;
    public final Context mContext;
    public final ArraySet mExtraScanResults;
    public WifiInfo mInfo;
    public boolean mIsOweTransitionMode;
    public boolean mIsPskSaeTransitionMode;
    public boolean mIsScoredNetworkMetered;
    public String mKey;
    public final Object mLock;
    public NetworkInfo mNetworkInfo;
    public final OsuProvider mOsuProvider;
    public final String mPasspointUniqueId;
    public final String mProviderFriendlyName;
    public int mRssi;
    public final ArraySet mScanResults;
    public final Map mScoredNetworkCache;
    public int mSpeed;
    public WifiManager mWifiManager;
    public int networkId;
    public int pskType;
    public int security;
    public String ssid;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class AccessPointProvisioningCallback extends ProvisioningCallback {
        public final void onProvisioningComplete() {
            int i = AccessPoint.$r8$clinit;
            throw null;
        }

        public final void onProvisioningFailure(int i) {
            throw null;
        }

        public final void onProvisioningStatus(int i) {
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    throw null;
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    throw null;
                default:
                    throw null;
            }
        }
    }

    static {
        new AtomicInteger(0);
    }

    public AccessPoint(Context context, Bundle bundle) {
        this.mLock = new Object();
        ArraySet arraySet = new ArraySet();
        this.mScanResults = arraySet;
        this.mExtraScanResults = new ArraySet();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        if (bundle.containsKey("key_config")) {
            this.mConfig = (WifiConfiguration) bundle.getParcelable("key_config");
        }
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null) {
            loadConfig(wifiConfiguration);
        }
        if (bundle.containsKey("key_ssid")) {
            this.ssid = bundle.getString("key_ssid");
        }
        if (bundle.containsKey("key_security")) {
            this.security = bundle.getInt("key_security");
        }
        if (bundle.containsKey("key_speed")) {
            this.mSpeed = bundle.getInt("key_speed");
        }
        if (bundle.containsKey("key_psktype")) {
            this.pskType = bundle.getInt("key_psktype");
        }
        if (bundle.containsKey("eap_psktype")) {
            bundle.getInt("eap_psktype");
        }
        this.mInfo = (WifiInfo) bundle.getParcelable("key_wifiinfo");
        if (bundle.containsKey("key_networkinfo")) {
            this.mNetworkInfo = (NetworkInfo) bundle.getParcelable("key_networkinfo");
        }
        if (bundle.containsKey("key_scanresults")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("key_scanresults");
            arraySet.clear();
            for (Parcelable parcelable : parcelableArray) {
                this.mScanResults.add((ScanResult) parcelable);
            }
        }
        if (bundle.containsKey("key_scorednetworkcache")) {
            Iterator it = bundle.getParcelableArrayList("key_scorednetworkcache").iterator();
            while (it.hasNext()) {
                TimestampedScoredNetwork timestampedScoredNetwork = (TimestampedScoredNetwork) it.next();
                this.mScoredNetworkCache.put(timestampedScoredNetwork.mScore.networkKey.wifiKey.bssid, timestampedScoredNetwork);
            }
        }
        if (bundle.containsKey("key_passpoint_unique_id")) {
            this.mPasspointUniqueId = bundle.getString("key_passpoint_unique_id");
        }
        if (bundle.containsKey("key_fqdn")) {
            bundle.getString("key_fqdn");
        }
        if (bundle.containsKey("key_provider_friendly_name")) {
            this.mProviderFriendlyName = bundle.getString("key_provider_friendly_name");
        }
        if (bundle.containsKey("key_subscription_expiration_time_in_millis")) {
            bundle.getLong("key_subscription_expiration_time_in_millis");
        }
        if (bundle.containsKey("key_passpoint_configuration_version")) {
            bundle.getInt("key_passpoint_configuration_version");
        }
        if (bundle.containsKey("key_is_psk_sae_transition_mode")) {
            this.mIsPskSaeTransitionMode = bundle.getBoolean("key_is_psk_sae_transition_mode");
        }
        if (bundle.containsKey("key_is_owe_transition_mode")) {
            this.mIsOweTransitionMode = bundle.getBoolean("key_is_owe_transition_mode");
        }
        update(this.mConfig, this.mInfo, this.mNetworkInfo);
        updateKey();
        updateBestRssiInfo();
    }

    public static String getKey(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder("AP:");
        if (TextUtils.isEmpty(str)) {
            sb.append(str2);
        } else {
            sb.append(str);
        }
        sb.append(',');
        sb.append(i);
        return sb.toString();
    }

    public static int getSecurity(Context context, ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("WEP");
        boolean contains2 = scanResult.capabilities.contains("SAE");
        boolean contains3 = scanResult.capabilities.contains("PSK");
        boolean contains4 = scanResult.capabilities.contains("EAP_SUITE_B_192");
        boolean contains5 = scanResult.capabilities.contains("EAP");
        boolean contains6 = scanResult.capabilities.contains("OWE");
        boolean contains7 = scanResult.capabilities.contains("OWE_TRANSITION");
        if (contains2 && contains3) {
            return ((WifiManager) context.getSystemService("wifi")).isWpa3SaeSupported() ? 5 : 2;
        }
        if (contains7) {
            return ((WifiManager) context.getSystemService("wifi")).isEnhancedOpenSupported() ? 4 : 0;
        }
        if (contains) {
            return 1;
        }
        if (contains2) {
            return 5;
        }
        if (contains3) {
            return 2;
        }
        if (contains4) {
            return 6;
        }
        if (contains5) {
            return 3;
        }
        return contains6 ? 4 : 0;
    }

    public static String getSpeedLabel(int i, Context context) {
        if (i == 5) {
            return context.getString(R.string.speed_label_slow);
        }
        if (i == 10) {
            return context.getString(R.string.speed_label_okay);
        }
        if (i == 20) {
            return context.getString(R.string.speed_label_fast);
        }
        if (i != 30) {
            return null;
        }
        return context.getString(R.string.speed_label_very_fast);
    }

    public static String removeDoubleQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof AccessPoint) && compareTo((AccessPoint) obj) == 0;
    }

    public final int getLevel() {
        return getWifiManager().calculateSignalLevel(this.mRssi);
    }

    public final String getTitle() {
        if (isPasspoint() && !TextUtils.isEmpty(this.mConfig.providerFriendlyName)) {
            return this.mConfig.providerFriendlyName;
        }
        if (this.mPasspointUniqueId != null && this.mConfig == null && !TextUtils.isEmpty(this.mProviderFriendlyName)) {
            return this.mProviderFriendlyName;
        }
        OsuProvider osuProvider = this.mOsuProvider;
        return (osuProvider == null || TextUtils.isEmpty(osuProvider.getFriendlyName())) ? !TextUtils.isEmpty(this.ssid) ? this.ssid : "" : this.mOsuProvider.getFriendlyName();
    }

    public final WifiManager getWifiManager() {
        if (this.mWifiManager == null) {
            this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        }
        return this.mWifiManager;
    }

    public final int hashCode() {
        WifiInfo wifiInfo = this.mInfo;
        return (this.ssid.hashCode() * 29) + (this.networkId * 23) + (this.mRssi * 19) + (wifiInfo != null ? wifiInfo.hashCode() * 13 : 0);
    }

    public final boolean isActive() {
        NetworkInfo networkInfo = this.mNetworkInfo;
        return (networkInfo == null || (this.networkId == -1 && networkInfo.getState() == NetworkInfo.State.DISCONNECTED)) ? false : true;
    }

    public final boolean isPasspoint() {
        WifiConfiguration wifiConfiguration = this.mConfig;
        return wifiConfiguration != null && wifiConfiguration.isPasspoint();
    }

    public void loadConfig(WifiConfiguration wifiConfiguration) {
        String str = wifiConfiguration.SSID;
        this.ssid = str == null ? "" : removeDoubleQuotes(str);
        this.bssid = wifiConfiguration.BSSID;
        this.security = getSecurity(wifiConfiguration);
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
    }

    public final boolean matches(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            return isPasspoint() && wifiConfiguration.getKey().equals(this.mConfig.getKey());
        }
        if (!this.ssid.equals(removeDoubleQuotes(wifiConfiguration.SSID))) {
            return false;
        }
        WifiConfiguration wifiConfiguration2 = this.mConfig;
        if (wifiConfiguration2 != null && wifiConfiguration2.shared != wifiConfiguration.shared) {
            return false;
        }
        int security = getSecurity(wifiConfiguration);
        if (this.mIsPskSaeTransitionMode && ((security == 5 && getWifiManager().isWpa3SaeSupported()) || security == 2)) {
            return true;
        }
        return (this.mIsOweTransitionMode && ((security == 4 && getWifiManager().isEnhancedOpenSupported()) || security == 0)) || this.security == getSecurity(wifiConfiguration);
    }

    public void setRssi(int i) {
        this.mRssi = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setScanResults(java.util.Collection r8) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.wifi.AccessPoint.setScanResults(java.util.Collection):void");
    }

    public final void setScanResultsPasspoint(Collection collection, Collection collection2) {
        synchronized (this.mLock) {
            try {
                this.mExtraScanResults.clear();
                if (!CollectionUtils.isEmpty(collection)) {
                    if (!CollectionUtils.isEmpty(collection2)) {
                        this.mExtraScanResults.addAll(collection2);
                    }
                    setScanResults(collection);
                } else if (!CollectionUtils.isEmpty(collection2)) {
                    setScanResults(collection2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String toString() {
        NetworkInfo.DetailedState detailedState;
        NetworkInfo networkInfo;
        StringBuilder sb = new StringBuilder("AccessPoint(");
        sb.append(this.ssid);
        if (this.bssid != null) {
            sb.append(":");
            sb.append(this.bssid);
        }
        if (this.mConfig != null) {
            sb.append(",saved");
        }
        if (isActive()) {
            sb.append(",active");
        }
        WifiInfo wifiInfo = this.mInfo;
        if (wifiInfo != null && wifiInfo.isEphemeral() && (networkInfo = this.mNetworkInfo) != null && networkInfo.getState() != NetworkInfo.State.DISCONNECTED) {
            sb.append(",ephemeral");
        }
        if (getLevel() != -1) {
            NetworkInfo networkInfo2 = this.mNetworkInfo;
            if (networkInfo2 != null) {
                detailedState = networkInfo2.getDetailedState();
            } else {
                Log.w("SettingsLib.AccessPoint", "NetworkInfo is null, cannot return detailed state");
                detailedState = null;
            }
            if (detailedState == null) {
                sb.append(",connectable");
            }
        }
        int i = this.security;
        boolean z = true;
        if (i != 0 && i != 4) {
            sb.append(',');
            int i2 = this.security;
            int i3 = this.pskType;
            sb.append(i2 == 1 ? "WEP" : i2 == 2 ? i3 == 1 ? "WPA" : i3 == 2 ? "WPA2" : i3 == 3 ? "WPA_WPA2" : "PSK" : i2 == 3 ? "EAP" : i2 == 5 ? "SAE" : i2 == 6 ? "SUITE_B" : i2 == 4 ? "OWE" : "NONE");
        }
        sb.append(",level=");
        sb.append(getLevel());
        if (this.mSpeed != 0) {
            sb.append(",speed=");
            sb.append(this.mSpeed);
        }
        sb.append(",metered=");
        if (!this.mIsScoredNetworkMetered && !WifiConfiguration.isMetered(this.mConfig, this.mInfo)) {
            z = false;
        }
        sb.append(z);
        if (WifiTracker.sVerboseLogging) {
            sb.append(",rssi=");
            sb.append(this.mRssi);
            synchronized (this.mLock) {
                sb.append(",scan cache size=");
                sb.append(this.mScanResults.size() + this.mExtraScanResults.size());
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public final boolean update(WifiNetworkScoreCache wifiNetworkScoreCache) {
        WifiInfo wifiInfo;
        boolean z = this.mIsScoredNetworkMetered;
        this.mIsScoredNetworkMetered = false;
        if (!isActive() || (wifiInfo = this.mInfo) == null) {
            synchronized (this.mLock) {
                try {
                    Iterator it = this.mScanResults.iterator();
                    while (it.hasNext()) {
                        ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork((ScanResult) it.next());
                        if (scoredNetwork != null) {
                            this.mIsScoredNetworkMetered = scoredNetwork.meteredHint | this.mIsScoredNetworkMetered;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            ScoredNetwork scoredNetwork2 = wifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
            if (scoredNetwork2 != null) {
                this.mIsScoredNetworkMetered = scoredNetwork2.meteredHint | this.mIsScoredNetworkMetered;
            }
        }
        return z != this.mIsScoredNetworkMetered;
    }

    public final void updateBestRssiInfo() {
        ScanResult scanResult;
        int i;
        int i2;
        if (isActive()) {
            return;
        }
        synchronized (this.mLock) {
            try {
                Iterator it = this.mScanResults.iterator();
                scanResult = null;
                i = Integer.MIN_VALUE;
                while (it.hasNext()) {
                    ScanResult scanResult2 = (ScanResult) it.next();
                    int i3 = scanResult2.level;
                    if (i3 > i) {
                        scanResult = scanResult2;
                        i = i3;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        int i4 = 2;
        if (i == Integer.MIN_VALUE || (i2 = this.mRssi) == Integer.MIN_VALUE) {
            this.mRssi = i;
        } else {
            this.mRssi = (i2 + i) / 2;
        }
        if (scanResult != null) {
            this.ssid = scanResult.SSID;
            this.bssid = scanResult.BSSID;
            int security = getSecurity(this.mContext, scanResult);
            this.security = security;
            boolean z = false;
            if (security == 2 || security == 5) {
                boolean contains = scanResult.capabilities.contains("WPA-PSK");
                boolean contains2 = scanResult.capabilities.contains("RSN-PSK");
                boolean contains3 = scanResult.capabilities.contains("RSN-SAE");
                if (contains2 && contains) {
                    i4 = 3;
                } else if (!contains2) {
                    if (contains) {
                        i4 = 1;
                    } else {
                        if (!contains3) {
                            Log.w("SettingsLib.AccessPoint", "Received abnormal flag string: " + scanResult.capabilities);
                        }
                        i4 = 0;
                    }
                }
                this.pskType = i4;
            }
            if (this.security == 3 && !scanResult.capabilities.contains("RSN-EAP")) {
                scanResult.capabilities.contains("WPA-EAP");
            }
            if (scanResult.capabilities.contains("PSK") && scanResult.capabilities.contains("SAE")) {
                z = true;
            }
            this.mIsPskSaeTransitionMode = z;
            this.mIsOweTransitionMode = scanResult.capabilities.contains("OWE_TRANSITION");
        }
        if (isPasspoint()) {
            this.mConfig.SSID = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("\"", this.ssid, "\"");
        }
    }

    public final void updateKey() {
        if (isPasspoint()) {
            WifiConfiguration wifiConfiguration = this.mConfig;
            this.mKey = wifiConfiguration.isPasspoint() ? AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("PASSPOINT:", wifiConfiguration.getKey()) : getKey(removeDoubleQuotes(wifiConfiguration.SSID), wifiConfiguration.BSSID, getSecurity(wifiConfiguration));
            return;
        }
        String str = this.mPasspointUniqueId;
        if (str != null && this.mConfig == null) {
            this.mKey = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("PASSPOINT:", str);
            return;
        }
        OsuProvider osuProvider = this.mOsuProvider;
        if (osuProvider == null) {
            this.mKey = getKey(this.ssid, this.bssid, this.security);
            return;
        }
        this.mKey = "OSU:" + osuProvider.getFriendlyName() + ',' + osuProvider.getServerUri();
    }

    @Override // java.lang.Comparable
    public final int compareTo(AccessPoint accessPoint) {
        if (isActive() && !accessPoint.isActive()) {
            return -1;
        }
        if (!isActive() && accessPoint.isActive()) {
            return 1;
        }
        int i = this.mRssi;
        if (i != Integer.MIN_VALUE && accessPoint.mRssi == Integer.MIN_VALUE) {
            return -1;
        }
        if (i == Integer.MIN_VALUE && accessPoint.mRssi != Integer.MIN_VALUE) {
            return 1;
        }
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null && accessPoint.mConfig == null) {
            return -1;
        }
        if (wifiConfiguration == null && accessPoint.mConfig != null) {
            return 1;
        }
        int i2 = this.mSpeed;
        int i3 = accessPoint.mSpeed;
        if (i2 != i3) {
            return i3 - i2;
        }
        WifiManager wifiManager = getWifiManager();
        int calculateSignalLevel = wifiManager.calculateSignalLevel(accessPoint.mRssi) - wifiManager.calculateSignalLevel(this.mRssi);
        if (calculateSignalLevel != 0) {
            return calculateSignalLevel;
        }
        int compareToIgnoreCase = getTitle().compareToIgnoreCase(accessPoint.getTitle());
        return compareToIgnoreCase != 0 ? compareToIgnoreCase : this.ssid.compareTo(accessPoint.ssid);
    }

    public boolean matches(ScanResult scanResult) {
        String str;
        if (scanResult == null) {
            return false;
        }
        if (!isPasspoint() && this.mOsuProvider == null) {
            if (!TextUtils.equals(this.ssid, scanResult.SSID) && ((str = scanResult.BSSID) == null || !TextUtils.equals(this.bssid, str))) {
                return false;
            }
            if (this.mIsPskSaeTransitionMode) {
                if ((scanResult.capabilities.contains("SAE") && getWifiManager().isWpa3SaeSupported()) || scanResult.capabilities.contains("PSK")) {
                    return true;
                }
            } else {
                int i = this.security;
                if ((i == 5 || i == 2) && scanResult.capabilities.contains("PSK") && scanResult.capabilities.contains("SAE")) {
                    return true;
                }
            }
            if (this.mIsOweTransitionMode) {
                int security = getSecurity(this.mContext, scanResult);
                if ((security == 4 && getWifiManager().isEnhancedOpenSupported()) || security == 0) {
                    return true;
                }
            } else {
                int i2 = this.security;
                if ((i2 == 4 || i2 == 0) && scanResult.capabilities.contains("OWE_TRANSITION")) {
                    return true;
                }
            }
            return this.security == getSecurity(this.mContext, scanResult);
        }
        throw new IllegalStateException("Should not matches a Passpoint by ScanResult");
    }

    public static int getSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        int i = wifiConfiguration.wepTxKeyIndex;
        if (i >= 0) {
            String[] strArr = wifiConfiguration.wepKeys;
            if (i < strArr.length && strArr[i] != null) {
                return 1;
            }
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        if (r2 == r6.getNetworkId()) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008d, code lost:
    
        if (android.text.TextUtils.equals(r6.getPasspointProviderFriendlyName(), r4.mConfig.providerFriendlyName) != false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean update(android.net.wifi.WifiConfiguration r5, android.net.wifi.WifiInfo r6, android.net.NetworkInfo r7) {
        /*
            r4 = this;
            r4.getLevel()
            r0 = 1
            r1 = 0
            if (r6 == 0) goto Ld6
            boolean r2 = r6.isOsuAp()
            if (r2 != 0) goto L90
            boolean r2 = r6.isPasspointAp()
            if (r2 != 0) goto L67
            boolean r2 = r4.isPasspoint()
            if (r2 == 0) goto L1a
            goto L67
        L1a:
            int r2 = r4.networkId
            r3 = -1
            if (r2 == r3) goto L28
            int r3 = r6.getNetworkId()
            if (r2 != r3) goto L93
        L25:
            r2 = r0
            goto L94
        L28:
            if (r5 == 0) goto L58
            boolean r2 = r5.isPasspoint()
            if (r2 != 0) goto L53
            java.lang.String r2 = r4.ssid
            java.lang.String r3 = r6.getSSID()
            java.lang.String r3 = removeDoubleQuotes(r3)
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L41
            goto L53
        L41:
            java.lang.String r2 = r6.getBSSID()
            if (r2 == 0) goto L93
            java.lang.String r2 = r4.bssid
            java.lang.String r3 = r6.getBSSID()
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L93
        L53:
            boolean r2 = r4.matches(r5)
            goto L94
        L58:
            java.lang.String r2 = r6.getSSID()
            java.lang.String r2 = removeDoubleQuotes(r2)
            java.lang.String r3 = r4.ssid
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            goto L94
        L67:
            boolean r2 = r6.isPasspointAp()
            if (r2 == 0) goto L93
            boolean r2 = r4.isPasspoint()
            if (r2 == 0) goto L93
            java.lang.String r2 = r6.getPasspointFqdn()
            android.net.wifi.WifiConfiguration r3 = r4.mConfig
            java.lang.String r3 = r3.FQDN
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L93
            java.lang.String r2 = r6.getPasspointProviderFriendlyName()
            android.net.wifi.WifiConfiguration r3 = r4.mConfig
            java.lang.String r3 = r3.providerFriendlyName
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L93
            goto L25
        L90:
            r6.isOsuAp()
        L93:
            r2 = r1
        L94:
            if (r2 == 0) goto Ld6
            android.net.wifi.WifiInfo r2 = r4.mInfo
            if (r2 != 0) goto L9b
            r1 = r0
        L9b:
            boolean r2 = r4.isPasspoint()
            if (r2 != 0) goto La8
            android.net.wifi.WifiConfiguration r2 = r4.mConfig
            if (r2 == r5) goto La8
            r4.update(r5)
        La8:
            int r5 = r4.mRssi
            int r2 = r6.getRssi()
            if (r5 == r2) goto Lbf
            int r5 = r6.getRssi()
            r2 = -127(0xffffffffffffff81, float:NaN)
            if (r5 == r2) goto Lbf
            int r5 = r6.getRssi()
            r4.mRssi = r5
            goto Ld1
        Lbf:
            android.net.NetworkInfo r5 = r4.mNetworkInfo
            if (r5 == 0) goto Ld0
            if (r7 == 0) goto Ld0
            android.net.NetworkInfo$DetailedState r5 = r5.getDetailedState()
            android.net.NetworkInfo$DetailedState r2 = r7.getDetailedState()
            if (r5 == r2) goto Ld0
            goto Ld1
        Ld0:
            r0 = r1
        Ld1:
            r4.mInfo = r6
            r4.mNetworkInfo = r7
            goto Le1
        Ld6:
            android.net.wifi.WifiInfo r5 = r4.mInfo
            if (r5 == 0) goto Le0
            r5 = 0
            r4.mInfo = r5
            r4.mNetworkInfo = r5
            goto Le1
        Le0:
            r0 = r1
        Le1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.wifi.AccessPoint.update(android.net.wifi.WifiConfiguration, android.net.wifi.WifiInfo, android.net.NetworkInfo):boolean");
    }

    public final void update(WifiConfiguration wifiConfiguration) {
        this.mConfig = wifiConfiguration;
        if (wifiConfiguration != null && !isPasspoint()) {
            this.ssid = removeDoubleQuotes(this.mConfig.SSID);
        }
        this.networkId = wifiConfiguration != null ? wifiConfiguration.networkId : -1;
        ThreadUtils.postOnMainThread(new AccessPoint$$ExternalSyntheticLambda0(this));
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet();
        this.mExtraScanResults = new ArraySet();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        loadConfig(wifiConfiguration);
        updateKey();
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration, Collection collection, Collection collection2) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet();
        this.mExtraScanResults = new ArraySet();
        this.mScoredNetworkCache = new HashMap();
        this.pskType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
        this.mPasspointUniqueId = wifiConfiguration.getKey();
        setScanResultsPasspoint(collection, collection2);
        updateKey();
    }

    public AccessPoint(Context context, OsuProvider osuProvider, Collection collection) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet();
        this.mExtraScanResults = new ArraySet();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        this.mOsuProvider = osuProvider;
        setScanResults(collection);
        updateKey();
    }

    public AccessPoint(Context context, Collection collection) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet();
        this.mExtraScanResults = new ArraySet();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        setScanResults(collection);
        updateKey();
    }
}
