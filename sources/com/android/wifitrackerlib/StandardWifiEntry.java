package com.android.wifitrackerlib;

import android.app.admin.DevicePolicyManager;
import android.app.admin.WifiSsidPolicy;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.MloLink;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiSsid;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.core.os.BuildCompat;
import com.android.app.viewcapture.data.ViewNode;
import com.android.wm.shell.R;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class StandardWifiEntry extends WifiEntry {
    public final DevicePolicyManager mDevicePolicyManager;
    public boolean mHasAddConfigUserRestriction;
    public boolean mIsAdminRestricted;
    public final boolean mIsEnhancedOpenSupported;
    public boolean mIsUserShareable;
    public final boolean mIsWpa3SaeSupported;
    public final boolean mIsWpa3SuiteBSupported;
    public final StandardWifiEntryKey mKey;
    public final Map mMatchingScanResults;
    public final Map mMatchingWifiConfigs;
    public boolean mShouldAutoOpenCaptivePortal;
    public final List mTargetScanResults;
    public final List mTargetSecurityTypes;
    public WifiConfiguration mTargetWifiConfig;
    public final UserManager mUserManager;

    public StandardWifiEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, StandardWifiEntryKey standardWifiEntryKey, WifiManager wifiManager) {
        super(wifiTrackerInjector, handler, wifiManager);
        this.mMatchingScanResults = new ArrayMap();
        this.mMatchingWifiConfigs = new ArrayMap();
        this.mTargetScanResults = new ArrayList();
        this.mTargetSecurityTypes = new ArrayList();
        this.mIsUserShareable = false;
        this.mShouldAutoOpenCaptivePortal = false;
        this.mIsAdminRestricted = false;
        this.mHasAddConfigUserRestriction = false;
        this.mKey = standardWifiEntryKey;
        this.mIsWpa3SaeSupported = wifiManager.isWpa3SaeSupported();
        this.mIsWpa3SuiteBSupported = wifiManager.isWpa3SuiteBSupported();
        this.mIsEnhancedOpenSupported = wifiManager.isEnhancedOpenSupported();
        this.mUserManager = wifiTrackerInjector.mUserManager;
        this.mDevicePolicyManager = wifiTrackerInjector.mDevicePolicyManager;
        updateSecurityTypes();
        updateAdminRestrictions();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean canConnect() {
        WifiConfiguration wifiConfiguration;
        WifiEnterpriseConfig wifiEnterpriseConfig;
        if (this.mScanResultLevel != -1 && getConnectedState() == 0) {
            if (hasAdminRestrictions()) {
                return false;
            }
            if (!this.mTargetSecurityTypes.contains(3) || (wifiConfiguration = this.mTargetWifiConfig) == null || (wifiEnterpriseConfig = wifiConfiguration.enterpriseConfig) == null) {
                return true;
            }
            if (!wifiEnterpriseConfig.isAuthenticationSimBased()) {
                return true;
            }
            List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class)).getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList != null && activeSubscriptionInfoList.size() != 0) {
                if (this.mTargetWifiConfig.carrierId == -1) {
                    return true;
                }
                Iterator<SubscriptionInfo> it = activeSubscriptionInfoList.iterator();
                while (it.hasNext()) {
                    if (it.next().getCarrierId() == this.mTargetWifiConfig.carrierId) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final boolean canDisconnect() {
        return getConnectedState() == 2;
    }

    public boolean canSetAutoJoinEnabled() {
        return isSaved() || isSuggestion();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetMeteredChoice() {
        return getWifiConfiguration() != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean canShare() {
        if (this.mInjector.mIsDemoMode) {
            return false;
        }
        WifiConfiguration wifiConfiguration = getWifiConfiguration();
        if (wifiConfiguration == null) {
            return false;
        }
        int i = BuildCompat.$r8$clinit;
        if (this.mUserManager.hasUserRestrictionForUser("no_sharing_admin_configured_wifi", UserHandle.getUserHandleForUid(wifiConfiguration.creatorUid))) {
            int i2 = wifiConfiguration.creatorUid;
            if (Utils.isDeviceOrProfileOwner(this.mContext, wifiConfiguration.creatorName, i2)) {
                return false;
            }
        }
        Iterator it = this.mTargetSecurityTypes.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (intValue == 0 || intValue == 1 || intValue == 2 || intValue == 4 || intValue == 6) {
                return true;
            }
        }
        return false;
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

    /* JADX WARN: Removed duplicated region for block: B:29:0x0112 A[Catch: all -> 0x008d, TRY_LEAVE, TryCatch #0 {all -> 0x008d, blocks: (B:4:0x0007, B:6:0x0018, B:9:0x0020, B:11:0x002d, B:13:0x0064, B:17:0x0090, B:19:0x009c, B:20:0x00c8, B:21:0x00d4, B:23:0x00da, B:25:0x00e0, B:29:0x0112, B:32:0x00f2, B:34:0x00f8, B:40:0x0103, B:41:0x011f), top: B:3:0x0007 }] */
    @Override // com.android.wifitrackerlib.WifiEntry
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void connect(final com.android.systemui.qs.tiles.dialog.InternetDialogController.WifiEntryConnectCallback r8) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.StandardWifiEntry.connect(com.android.systemui.qs.tiles.dialog.InternetDialogController$WifiEntryConnectCallback):void");
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean connectionInfoMatches(WifiInfo wifiInfo) {
        if (!wifiInfo.isPasspointAp() && !wifiInfo.isOsuAp()) {
            Iterator it = ((ArrayMap) this.mMatchingWifiConfigs).values().iterator();
            while (it.hasNext()) {
                if (((WifiConfiguration) it.next()).networkId == wifiInfo.getNetworkId()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getKey() {
        return this.mKey.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized int getMeteredChoice() {
        WifiConfiguration wifiConfiguration;
        if (!isSuggestion() && (wifiConfiguration = this.mTargetWifiConfig) != null) {
            int i = wifiConfiguration.meteredOverride;
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
        }
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getNetworkSelectionDescription() {
        return Utils.getNetworkSelectionDescription(getWifiConfiguration());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getScanResultDescription() {
        if (((ArrayList) this.mTargetScanResults).size() == 0) {
            return "";
        }
        return "[" + getScanResultDescription(2400, 2500) + ";" + getScanResultDescription(4900, 5900) + ";" + getScanResultDescription(5925, 7125) + ";" + getScanResultDescription(58320, 70200) + "]";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSecurityString() {
        return Utils.getSecurityString(this.mContext, this.mTargetSecurityTypes, true);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized List getSecurityTypes() {
        return new ArrayList(this.mTargetSecurityTypes);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getSsid() {
        return this.mKey.mScanResultKey.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getStandardString() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null) {
            return Utils.getStandardString(wifiInfo.getWifiStandard(), this.mContext);
        }
        if (this.mTargetScanResults.isEmpty()) {
            return "";
        }
        return Utils.getStandardString(((ScanResult) ((ArrayList) this.mTargetScanResults).get(0)).getWifiStandard(), this.mContext);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized String getSummary(boolean z) {
        StringJoiner stringJoiner;
        String disconnectedDescription;
        try {
            stringJoiner = new StringJoiner(this.mContext.getString(R.string.wifitrackerlib_summary_separator));
            int connectedState = getConnectedState();
            if (connectedState == 0) {
                disconnectedDescription = Utils.getDisconnectedDescription(this.mInjector, this.mContext, this.mTargetWifiConfig, z);
            } else if (connectedState != 1) {
                disconnectedDescription = null;
                if (connectedState != 2) {
                    Log.e("StandardWifiEntry", "getConnectedState() returned unknown state: " + connectedState);
                } else {
                    NetworkCapabilities networkCapabilities = this.mNetworkCapabilities;
                    if (networkCapabilities == null) {
                        Log.e("StandardWifiEntry", "Tried to get CONNECTED description, but mNetworkCapabilities was unexpectedly null!");
                    } else {
                        disconnectedDescription = Utils.getConnectedDescription(this.mContext, this.mTargetWifiConfig, networkCapabilities, this.mWifiInfo, isDefaultNetwork(), isLowQuality(), this.mConnectivityReport);
                    }
                }
            } else {
                disconnectedDescription = Utils.getConnectingDescription(this.mContext, this.mNetworkInfo);
            }
            if (!TextUtils.isEmpty(disconnectedDescription)) {
                stringJoiner.add(disconnectedDescription);
            }
            Context context = this.mContext;
            String str = "";
            if (context != null && canSetAutoJoinEnabled() && !isAutoJoinEnabled()) {
                str = context.getString(R.string.wifitrackerlib_auto_connect_disable);
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
        } catch (Throwable th) {
            throw th;
        }
        return stringJoiner.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getTitle() {
        return this.mKey.mScanResultKey.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized WifiConfiguration getWifiConfiguration() {
        if (!isSaved()) {
            return null;
        }
        return this.mTargetWifiConfig;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean hasAdminRestrictions() {
        try {
            if (this.mHasAddConfigUserRestriction) {
                if (!isSaved()) {
                    if (isSuggestion()) {
                    }
                    return true;
                }
            }
            if (!this.mIsAdminRestricted) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean isAutoJoinEnabled() {
        WifiConfiguration wifiConfiguration = this.mTargetWifiConfig;
        if (wifiConfiguration == null) {
            return false;
        }
        return wifiConfiguration.allowAutojoin;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000e, code lost:
    
        if (r0.meteredHint != false) goto L13;
     */
    @Override // com.android.wifitrackerlib.WifiEntry
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isMetered() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.getMeteredChoice()     // Catch: java.lang.Throwable -> L11
            r1 = 1
            if (r0 == r1) goto L14
            android.net.wifi.WifiConfiguration r0 = r2.mTargetWifiConfig     // Catch: java.lang.Throwable -> L11
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
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.StandardWifiEntry.isMetered():boolean");
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean isSaved() {
        boolean z;
        WifiConfiguration wifiConfiguration = this.mTargetWifiConfig;
        if (wifiConfiguration != null && !wifiConfiguration.fromWifiNetworkSuggestion) {
            z = wifiConfiguration.isEphemeral() ? false : true;
        }
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public synchronized boolean isSuggestion() {
        boolean z;
        WifiConfiguration wifiConfiguration = this.mTargetWifiConfig;
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

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0031, code lost:
    
        if (r0.getDisableReasonCounter(5) > 0) goto L24;
     */
    @Override // com.android.wifitrackerlib.WifiEntry
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean shouldEditBeforeConnect() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.net.wifi.WifiConfiguration r0 = r3.getWifiConfiguration()     // Catch: java.lang.Throwable -> L1b
            r1 = 0
            if (r0 != 0) goto La
            monitor-exit(r3)
            return r1
        La:
            android.net.wifi.WifiConfiguration$NetworkSelectionStatus r0 = r0.getNetworkSelectionStatus()     // Catch: java.lang.Throwable -> L1b
            int r2 = r0.getNetworkSelectionStatus()     // Catch: java.lang.Throwable -> L1b
            if (r2 != 0) goto L1d
            boolean r2 = r0.hasEverConnected()     // Catch: java.lang.Throwable -> L1b
            if (r2 != 0) goto L34
            goto L1d
        L1b:
            r0 = move-exception
            goto L39
        L1d:
            r2 = 2
            int r2 = r0.getDisableReasonCounter(r2)     // Catch: java.lang.Throwable -> L1b
            if (r2 > 0) goto L36
            r2 = 8
            int r2 = r0.getDisableReasonCounter(r2)     // Catch: java.lang.Throwable -> L1b
            if (r2 > 0) goto L36
            r2 = 5
            int r0 = r0.getDisableReasonCounter(r2)     // Catch: java.lang.Throwable -> L1b
            if (r0 <= 0) goto L34
            goto L36
        L34:
            monitor-exit(r3)
            return r1
        L36:
            monitor-exit(r3)
            r3 = 1
            return r3
        L39:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L1b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.StandardWifiEntry.shouldEditBeforeConnect():boolean");
    }

    public final void updateAdminRestrictions() {
        int i;
        int i2 = BuildCompat.$r8$clinit;
        UserManager userManager = this.mUserManager;
        if (userManager != null) {
            this.mHasAddConfigUserRestriction = userManager.hasUserRestriction("no_add_wifi_config");
        }
        DevicePolicyManager devicePolicyManager = this.mDevicePolicyManager;
        if (devicePolicyManager != null) {
            int minimumRequiredWifiSecurityLevel = devicePolicyManager.getMinimumRequiredWifiSecurityLevel();
            if (minimumRequiredWifiSecurityLevel != 0) {
                Iterator it = getSecurityTypes().iterator();
                while (it.hasNext()) {
                    switch (((Integer) it.next()).intValue()) {
                        case 0:
                        case 6:
                            i = 0;
                            break;
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                            i = 1;
                            break;
                        case 3:
                        case 8:
                        case 9:
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            i = 2;
                            break;
                        case 5:
                            i = 3;
                            break;
                        case 10:
                        default:
                            i = -1;
                            break;
                    }
                    if (i != -1 && minimumRequiredWifiSecurityLevel <= i) {
                    }
                }
                this.mIsAdminRestricted = true;
                return;
            }
            DevicePolicyManager devicePolicyManager2 = this.mDevicePolicyManager;
            int i3 = BuildCompat.$r8$clinit;
            WifiSsidPolicy wifiSsidPolicy = devicePolicyManager2.getWifiSsidPolicy();
            if (wifiSsidPolicy != null) {
                int policyType = wifiSsidPolicy.getPolicyType();
                Set<WifiSsid> ssids = wifiSsidPolicy.getSsids();
                if (policyType == 0 && !ssids.contains(WifiSsid.fromBytes(getSsid().getBytes(StandardCharsets.UTF_8)))) {
                    this.mIsAdminRestricted = true;
                    return;
                } else if (policyType == 1 && ssids.contains(WifiSsid.fromBytes(getSsid().getBytes(StandardCharsets.UTF_8)))) {
                    this.mIsAdminRestricted = true;
                    return;
                }
            }
        }
        this.mIsAdminRestricted = false;
    }

    public final synchronized void updateConfig(List list) {
        if (list == null) {
            try {
                list = Collections.emptyList();
            } catch (Throwable th) {
                throw th;
            }
        }
        ScanResultKey scanResultKey = this.mKey.mScanResultKey;
        String str = scanResultKey.mSsid;
        Set set = scanResultKey.mSecurityTypes;
        ((ArrayMap) this.mMatchingWifiConfigs).clear();
        for (WifiConfiguration wifiConfiguration : list) {
            if (!TextUtils.equals(str, WifiInfo.sanitizeSsid(wifiConfiguration.SSID))) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + str + ", Actual: " + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + ", Config: " + wifiConfiguration);
            }
            for (Integer num : Utils.getSecurityTypesFromWifiConfiguration(wifiConfiguration)) {
                int intValue = num.intValue();
                ArraySet arraySet = (ArraySet) set;
                if (!arraySet.contains(num)) {
                    throw new IllegalArgumentException("Attempted to update with wrong security! Expected one of: " + arraySet + ", Actual: " + intValue + ", Config: " + wifiConfiguration);
                }
                if (intValue != 4 ? intValue != 5 ? intValue != 6 ? true : this.mIsEnhancedOpenSupported : this.mIsWpa3SuiteBSupported : this.mIsWpa3SaeSupported) {
                    ((ArrayMap) this.mMatchingWifiConfigs).put(num, wifiConfiguration);
                }
            }
        }
        updateSecurityTypes();
        updateTargetScanResultInfo();
        notifyOnUpdated();
    }

    public final synchronized void updateScanResultInfo(List list) {
        if (list == null) {
            try {
                list = new ArrayList();
            } catch (Throwable th) {
                throw th;
            }
        }
        String str = this.mKey.mScanResultKey.mSsid;
        for (ScanResult scanResult : list) {
            if (!TextUtils.equals(scanResult.SSID, str)) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + str + ", Actual: " + scanResult.SSID + ", ScanResult: " + scanResult);
            }
        }
        ((ArrayMap) this.mMatchingScanResults).clear();
        Set set = this.mKey.mScanResultKey.mSecurityTypes;
        for (ScanResult scanResult2 : list) {
            ArrayList<Integer> arrayList = new ArrayList();
            for (int i : scanResult2.getSecurityTypes()) {
                arrayList.add(Integer.valueOf(i));
            }
            for (Integer num : arrayList) {
                int intValue = num.intValue();
                if (((ArraySet) set).contains(num)) {
                    if (intValue != 4 ? intValue != 5 ? intValue != 6 ? true : this.mIsEnhancedOpenSupported : this.mIsWpa3SuiteBSupported : this.mIsWpa3SaeSupported) {
                        if (!((ArrayMap) this.mMatchingScanResults).containsKey(num)) {
                            ((ArrayMap) this.mMatchingScanResults).put(num, new ArrayList());
                        }
                        ((List) ((ArrayMap) this.mMatchingScanResults).get(num)).add(scanResult2);
                    }
                }
            }
        }
        updateSecurityTypes();
        updateTargetScanResultInfo();
        notifyOnUpdated();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void updateSecurityTypes() {
        try {
            this.mTargetSecurityTypes.clear();
            WifiInfo wifiInfo = this.mWifiInfo;
            if (wifiInfo != null && wifiInfo.getCurrentSecurityType() != -1) {
                this.mTargetSecurityTypes.add(Integer.valueOf(this.mWifiInfo.getCurrentSecurityType()));
            }
            Set keySet = ((ArrayMap) this.mMatchingWifiConfigs).keySet();
            if (this.mTargetSecurityTypes.isEmpty() && this.mKey.mIsTargetingNewNetworks) {
                Set keySet2 = ((ArrayMap) this.mMatchingScanResults).keySet();
                Iterator it = keySet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        this.mTargetSecurityTypes.addAll(keySet2);
                        break;
                    }
                    Integer num = (Integer) it.next();
                    num.getClass();
                    if (keySet2.contains(num)) {
                        break;
                    }
                }
            }
            if (this.mTargetSecurityTypes.isEmpty()) {
                this.mTargetSecurityTypes.addAll(keySet);
            }
            if (this.mTargetSecurityTypes.isEmpty()) {
                this.mTargetSecurityTypes.addAll(this.mKey.mScanResultKey.mSecurityTypes);
            }
            this.mTargetWifiConfig = (WifiConfiguration) ((ArrayMap) this.mMatchingWifiConfigs).get(Integer.valueOf(Utils.getSingleSecurityTypeFromMultipleSecurityTypes(this.mTargetSecurityTypes)));
            ArraySet arraySet = new ArraySet();
            for (Integer num2 : this.mTargetSecurityTypes) {
                num2.getClass();
                if (((ArrayMap) this.mMatchingScanResults).containsKey(num2)) {
                    arraySet.addAll((Collection) ((ArrayMap) this.mMatchingScanResults).get(num2));
                }
            }
            this.mTargetScanResults.clear();
            this.mTargetScanResults.addAll(arraySet);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void updateTargetScanResultInfo() {
        try {
            ScanResult bestScanResultByLevel = Utils.getBestScanResultByLevel(this.mTargetScanResults);
            if (getConnectedState() == 0) {
                this.mScanResultLevel = bestScanResultByLevel != null ? this.mWifiManager.calculateSignalLevel(bestScanResultByLevel.level) : -1;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StandardWifiEntryKey {
        public final boolean mIsNetworkRequest;
        public final boolean mIsTargetingNewNetworks;
        public final ScanResultKey mScanResultKey;
        public final String mSuggestionProfileKey;

        public StandardWifiEntryKey(ScanResultKey scanResultKey, boolean z) {
            this.mScanResultKey = scanResultKey;
            this.mIsTargetingNewNetworks = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || StandardWifiEntryKey.class != obj.getClass()) {
                return false;
            }
            StandardWifiEntryKey standardWifiEntryKey = (StandardWifiEntryKey) obj;
            return Objects.equals(this.mScanResultKey, standardWifiEntryKey.mScanResultKey) && TextUtils.equals(this.mSuggestionProfileKey, standardWifiEntryKey.mSuggestionProfileKey) && this.mIsNetworkRequest == standardWifiEntryKey.mIsNetworkRequest;
        }

        public final int hashCode() {
            return Objects.hash(this.mScanResultKey, this.mSuggestionProfileKey, Boolean.valueOf(this.mIsNetworkRequest));
        }

        public final String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                ScanResultKey scanResultKey = this.mScanResultKey;
                if (scanResultKey != null) {
                    jSONObject.put("SCAN_RESULT_KEY", scanResultKey.toString());
                }
                String str = this.mSuggestionProfileKey;
                if (str != null) {
                    jSONObject.put("SUGGESTION_PROFILE_KEY", str);
                }
                boolean z = this.mIsNetworkRequest;
                if (z) {
                    jSONObject.put("IS_NETWORK_REQUEST", z);
                }
                boolean z2 = this.mIsTargetingNewNetworks;
                if (z2) {
                    jSONObject.put("IS_TARGETING_NEW_NETWORKS", z2);
                }
            } catch (JSONException e) {
                Log.wtf("StandardWifiEntry", "JSONException while converting StandardWifiEntryKey to string: " + e);
            }
            return "StandardWifiEntry:" + jSONObject.toString();
        }

        public StandardWifiEntryKey(WifiConfiguration wifiConfiguration, boolean z) {
            this.mIsTargetingNewNetworks = false;
            this.mScanResultKey = new ScanResultKey(WifiInfo.sanitizeSsid(wifiConfiguration.SSID), Utils.getSecurityTypesFromWifiConfiguration(wifiConfiguration));
            if (wifiConfiguration.fromWifiNetworkSuggestion) {
                this.mSuggestionProfileKey = new StringJoiner(",").add(wifiConfiguration.creatorName).add(String.valueOf(wifiConfiguration.carrierId)).add(String.valueOf(wifiConfiguration.subscriptionId)).toString();
            } else if (wifiConfiguration.fromWifiNetworkSpecifier) {
                this.mIsNetworkRequest = true;
            }
            this.mIsTargetingNewNetworks = z;
        }
    }

    public final synchronized String getScanResultDescription(final int i, final int i2) {
        final int i3 = 0;
        List list = (List) this.mTargetScanResults.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.StandardWifiEntry$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                int i4 = i;
                int i5 = i2;
                int i6 = ((ScanResult) obj).frequency;
                return i6 >= i4 && i6 <= i5;
            }
        }).sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.StandardWifiEntry$$ExternalSyntheticLambda1
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                ScanResult scanResult = (ScanResult) obj;
                switch (i3) {
                    case 0:
                        return scanResult.level * (-1);
                    default:
                        return scanResult.level;
                }
            }
        })).collect(Collectors.toList());
        int size = list.size();
        if (size == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(size);
        sb.append(")");
        if (size > 4) {
            final int i4 = 1;
            int asInt = list.stream().mapToInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.StandardWifiEntry$$ExternalSyntheticLambda1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    ScanResult scanResult = (ScanResult) obj;
                    switch (i4) {
                        case 0:
                            return scanResult.level * (-1);
                        default:
                            return scanResult.level;
                    }
                }
            }).max().getAsInt();
            sb.append("max=");
            sb.append(asInt);
            sb.append(",");
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        list.forEach(new Consumer() { // from class: com.android.wifitrackerlib.StandardWifiEntry$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                String sb2;
                StandardWifiEntry standardWifiEntry = StandardWifiEntry.this;
                StringBuilder sb3 = sb;
                long j = elapsedRealtime;
                ScanResult scanResult = (ScanResult) obj;
                synchronized (standardWifiEntry) {
                    try {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(" \n{");
                        sb4.append(scanResult.BSSID);
                        WifiInfo wifiInfo = standardWifiEntry.mWifiInfo;
                        if (wifiInfo != null && scanResult.BSSID.equals(wifiInfo.getBSSID())) {
                            sb4.append("*");
                        }
                        sb4.append("=");
                        sb4.append(scanResult.frequency);
                        sb4.append(",");
                        sb4.append(scanResult.level);
                        int wifiStandard = scanResult.getWifiStandard();
                        sb4.append(",");
                        sb4.append(Utils.getStandardString(wifiStandard, standardWifiEntry.mContext));
                        int i5 = BuildCompat.$r8$clinit;
                        if (wifiStandard == 8) {
                            sb4.append(",mldMac=");
                            sb4.append(scanResult.getApMldMacAddress());
                            sb4.append(",linkId=");
                            sb4.append(scanResult.getApMloLinkId());
                            sb4.append(",affLinks=");
                            StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
                            for (MloLink mloLink : scanResult.getAffiliatedMloLinks()) {
                                int band = mloLink.getBand();
                                int i6 = 1;
                                if (band != 1) {
                                    i6 = 2;
                                    if (band != 2) {
                                        if (band != 8) {
                                            i6 = 16;
                                            if (band != 16) {
                                                Log.e("StandardWifiEntry", "Unknown MLO link band: " + mloLink.getBand());
                                                i6 = -1;
                                            }
                                        } else {
                                            i6 = 8;
                                        }
                                    }
                                }
                                stringJoiner.add(new StringJoiner(",", "{", "}").add("apMacAddr=" + mloLink.getApMacAddress()).add("freq=" + ScanResult.convertChannelToFrequencyMhzIfSupported(mloLink.getChannel(), i6)).toString());
                            }
                            sb4.append(stringJoiner.toString());
                        }
                        int i7 = ((int) (j - (scanResult.timestamp / 1000))) / 1000;
                        sb4.append(",");
                        sb4.append(i7);
                        sb4.append("s");
                        sb4.append("}");
                        sb2 = sb4.toString();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                sb3.append(sb2);
            }
        });
        return sb.toString();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScanResultKey {
        public final Set mSecurityTypes;
        public final String mSsid;

        public ScanResultKey(String str, List list) {
            this.mSecurityTypes = new ArraySet();
            this.mSsid = str;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                int intValue = num.intValue();
                if (intValue == 0) {
                    this.mSecurityTypes.add(6);
                } else if (intValue == 6) {
                    this.mSecurityTypes.add(0);
                } else if (intValue == 9) {
                    this.mSecurityTypes.add(3);
                } else if (intValue == 2) {
                    this.mSecurityTypes.add(4);
                } else if (intValue == 3) {
                    this.mSecurityTypes.add(9);
                } else if (intValue == 4) {
                    this.mSecurityTypes.add(2);
                } else if (intValue != 11 && intValue != 12) {
                }
                this.mSecurityTypes.add(num);
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ScanResultKey.class != obj.getClass()) {
                return false;
            }
            ScanResultKey scanResultKey = (ScanResultKey) obj;
            if (TextUtils.equals(this.mSsid, scanResultKey.mSsid)) {
                if (((ArraySet) this.mSecurityTypes).equals(scanResultKey.mSecurityTypes)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(this.mSsid, this.mSecurityTypes);
        }

        public final String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                String str = this.mSsid;
                if (str != null) {
                    jSONObject.put("SSID", str);
                }
                if (!((ArraySet) this.mSecurityTypes).isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = ((ArraySet) this.mSecurityTypes).iterator();
                    while (it.hasNext()) {
                        jSONArray.put(((Integer) it.next()).intValue());
                    }
                    jSONObject.put("SECURITY_TYPES", jSONArray);
                }
            } catch (JSONException e) {
                Log.e("StandardWifiEntry", "JSONException while converting ScanResultKey to string: " + e);
            }
            return jSONObject.toString();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public ScanResultKey(android.net.wifi.ScanResult r6) {
            /*
                r5 = this;
                java.lang.String r0 = r6.SSID
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                int[] r6 = r6.getSecurityTypes()
                int r2 = r6.length
                r3 = 0
            Ld:
                if (r3 >= r2) goto L1b
                r4 = r6[r3]
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r1.add(r4)
                int r3 = r3 + 1
                goto Ld
            L1b:
                r5.<init>(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.StandardWifiEntry.ScanResultKey.<init>(android.net.wifi.ScanResult):void");
        }
    }

    public StandardWifiEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, StandardWifiEntryKey standardWifiEntryKey, List list, List list2, WifiManager wifiManager) {
        this(wifiTrackerInjector, handler, standardWifiEntryKey, wifiManager);
        if (list != null && !list.isEmpty()) {
            updateConfig(list);
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        updateScanResultInfo(list2);
    }
}
