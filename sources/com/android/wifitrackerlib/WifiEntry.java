package com.android.wifitrackerlib;

import android.content.Context;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.BuildCompat;
import androidx.core.util.Preconditions;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WifiEntry {
    public static final Comparator WIFI_PICKER_COMPARATOR = Comparator.comparing(new WifiEntry$$ExternalSyntheticLambda0(0)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(6)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(7)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(8)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(9)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(10)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(11)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(12)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(1)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(2)).thenComparing(new WifiEntry$$ExternalSyntheticLambda0(4));
    public final Handler mCallbackHandler;
    public InternetDialogController.WifiEntryConnectCallback mConnectCallback;
    public ConnectedInfo mConnectedInfo;
    public ConnectivityDiagnosticsManager.ConnectivityReport mConnectivityReport;
    public final Context mContext;
    public Network mDefaultNetwork;
    public NetworkCapabilities mDefaultNetworkCapabilities;
    public final WifiTrackerInjector mInjector;
    public WifiEntryCallback mListener;
    public Network mNetwork;
    public NetworkCapabilities mNetworkCapabilities;
    public NetworkInfo mNetworkInfo;
    public WifiInfo mWifiInfo;
    public final WifiManager mWifiManager;
    public int mWifiInfoLevel = -1;
    public int mScanResultLevel = -1;
    public boolean mCalledConnect = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wifitrackerlib.WifiEntry$1, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$DetailedState;

        static {
            int[] iArr = new int[NetworkInfo.DetailedState.values().length];
            $SwitchMap$android$net$NetworkInfo$DetailedState = iArr;
            try {
                iArr[NetworkInfo.DetailedState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$DetailedState[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConnectActionListener implements WifiManager.ActionListener {
        public ConnectActionListener() {
        }

        public final void onFailure(int i) {
            WifiEntry wifiEntry = WifiEntry.this;
            wifiEntry.mCallbackHandler.post(new WifiEntry$$ExternalSyntheticLambda12(2, this));
        }

        public final void onSuccess() {
            synchronized (WifiEntry.this) {
                WifiEntry.this.mCalledConnect = true;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConnectedInfo {
        public ConnectedInfo() {
            new ArrayList();
            new ArrayList();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface WifiEntryCallback {
        void onUpdated();
    }

    static {
        Comparator.comparing(new WifiEntry$$ExternalSyntheticLambda0(5));
    }

    public WifiEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, WifiManager wifiManager) {
        Optional.empty();
        Preconditions.checkNotNull(wifiTrackerInjector, "Cannot construct with null injector!");
        Preconditions.checkNotNull(handler, "Cannot construct with null handler!");
        Preconditions.checkNotNull(wifiManager, "Cannot construct with null WifiManager!");
        this.mInjector = wifiTrackerInjector;
        this.mContext = wifiTrackerInjector.mContext;
        this.mCallbackHandler = handler;
        this.mWifiManager = wifiManager;
    }

    public abstract boolean canConnect();

    public boolean canDisconnect() {
        return false;
    }

    public boolean canSetMeteredChoice() {
        return false;
    }

    public boolean canShare() {
        return false;
    }

    public boolean canSignIn() {
        return false;
    }

    public final synchronized void clearConnectionInfo() {
        updateWifiInfo(null);
        this.mNetworkInfo = null;
        this.mNetworkCapabilities = null;
        this.mConnectivityReport = null;
        notifyOnUpdated();
    }

    public abstract void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback);

    public abstract boolean connectionInfoMatches(WifiInfo wifiInfo);

    public final boolean doesUnderlyingNetworkMatch(NetworkCapabilities networkCapabilities, int i) {
        if (i > 5) {
            Log.e("WifiEntry", "Underlying network depth greater than max depth of 5");
            return false;
        }
        if (networkCapabilities == null) {
            return false;
        }
        int i2 = BuildCompat.$r8$clinit;
        List underlyingNetworks = networkCapabilities.getUnderlyingNetworks();
        if (underlyingNetworks == null) {
            return false;
        }
        if (underlyingNetworks.contains(this.mNetwork)) {
            return true;
        }
        ConnectivityManager connectivityManager = this.mInjector.mConnectivityManager;
        if (connectivityManager == null) {
            Log.wtf("WifiEntry", "ConnectivityManager is null!");
            return false;
        }
        Iterator it = underlyingNetworks.iterator();
        while (it.hasNext()) {
            if (doesUnderlyingNetworkMatch(connectivityManager.getNetworkCapabilities((Network) it.next()), i + 1)) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof WifiEntry) {
            return getKey().equals(((WifiEntry) obj).getKey());
        }
        return false;
    }

    public synchronized int getConnectedState() {
        if (this.mNetworkCapabilities != null) {
            return 2;
        }
        NetworkInfo networkInfo = this.mNetworkInfo;
        if (networkInfo == null) {
            return 0;
        }
        switch (AnonymousClass1.$SwitchMap$android$net$NetworkInfo$DetailedState[networkInfo.getDetailedState().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    public abstract String getKey();

    public int getLevel() {
        int i = this.mWifiInfoLevel;
        return i != -1 ? i : this.mScanResultLevel;
    }

    public int getMeteredChoice() {
        return 0;
    }

    public String getNetworkSelectionDescription() {
        return "";
    }

    public String getScanResultDescription() {
        return "";
    }

    public String getSecurityString() {
        return "";
    }

    public List getSecurityTypes() {
        return Collections.emptyList();
    }

    public abstract String getSsid();

    public String getStandardString() {
        return "";
    }

    public abstract String getSummary(boolean z);

    public String getTitle() {
        return "";
    }

    public WifiConfiguration getWifiConfiguration() {
        return null;
    }

    public boolean hasAdminRestrictions() {
        return false;
    }

    public final synchronized boolean hasInternetAccess() {
        boolean z;
        NetworkCapabilities networkCapabilities = this.mNetworkCapabilities;
        if (networkCapabilities != null) {
            z = networkCapabilities.hasCapability(16);
        }
        return z;
    }

    public final int hashCode() {
        return getKey().hashCode();
    }

    public boolean isAutoJoinEnabled() {
        return false;
    }

    public final synchronized boolean isDefaultNetwork() {
        Network network = this.mNetwork;
        if (network != null && network.equals(this.mDefaultNetwork)) {
            return true;
        }
        return doesUnderlyingNetworkMatch(this.mDefaultNetworkCapabilities, 0);
    }

    public boolean isExpired() {
        return false;
    }

    public final synchronized boolean isLowQuality() {
        boolean z;
        NetworkCapabilities networkCapabilities;
        z = false;
        if (isPrimaryNetwork() && hasInternetAccess() && !isDefaultNetwork() && (networkCapabilities = this.mDefaultNetworkCapabilities) != null && networkCapabilities.hasTransport(0) && !this.mDefaultNetworkCapabilities.hasTransport(4)) {
            if (this.mDefaultNetworkCapabilities.hasCapability(13)) {
                z = true;
            }
        }
        return z;
    }

    public boolean isMetered() {
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0016, code lost:
    
        if (r0.isPrimary() != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized boolean isPrimaryNetwork() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.getConnectedState()     // Catch: java.lang.Throwable -> L19
            r1 = 0
            if (r0 != 0) goto La
            monitor-exit(r2)
            return r1
        La:
            android.net.NetworkInfo r0 = r2.mNetworkInfo     // Catch: java.lang.Throwable -> L19
            if (r0 != 0) goto L1b
            android.net.wifi.WifiInfo r0 = r2.mWifiInfo     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1c
            boolean r0 = r0.isPrimary()     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1c
            goto L1b
        L19:
            r0 = move-exception
            goto L1e
        L1b:
            r1 = 1
        L1c:
            monitor-exit(r2)
            return r1
        L1e:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.WifiEntry.isPrimaryNetwork():boolean");
    }

    public boolean isSaved() {
        return false;
    }

    public boolean isSubscription() {
        return false;
    }

    public boolean isSuggestion() {
        return false;
    }

    public final boolean isVerboseSummaryEnabled() {
        WifiTrackerInjector wifiTrackerInjector = this.mInjector;
        return !wifiTrackerInjector.mVerboseLoggingDisabledOverride && wifiTrackerInjector.mWifiManager.isVerboseLoggingEnabled();
    }

    public final void notifyOnUpdated() {
        if (this.mListener != null) {
            this.mCallbackHandler.post(new WifiEntry$$ExternalSyntheticLambda12(0, this));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0043 A[Catch: all -> 0x0023, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0009, B:10:0x0011, B:12:0x0017, B:14:0x001f, B:17:0x0027, B:19:0x002d, B:21:0x0035, B:26:0x0043, B:30:0x0048), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void onNetworkCapabilitiesChanged(android.net.Network r3, android.net.NetworkCapabilities r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.net.TransportInfo r0 = r4.getTransportInfo()     // Catch: java.lang.Throwable -> L23
            boolean r1 = r0 instanceof android.net.wifi.WifiInfo     // Catch: java.lang.Throwable -> L23
            if (r1 == 0) goto Lc
            android.net.wifi.WifiInfo r0 = (android.net.wifi.WifiInfo) r0     // Catch: java.lang.Throwable -> L23
            goto Ld
        Lc:
            r0 = 0
        Ld:
            if (r0 != 0) goto L11
            monitor-exit(r2)
            return
        L11:
            boolean r1 = r2.connectionInfoMatches(r0)     // Catch: java.lang.Throwable -> L23
            if (r1 != 0) goto L27
            android.net.Network r4 = r2.mNetwork     // Catch: java.lang.Throwable -> L23
            boolean r4 = r3.equals(r4)     // Catch: java.lang.Throwable -> L23
            if (r4 == 0) goto L25
            r2.onNetworkLost(r3)     // Catch: java.lang.Throwable -> L23
            goto L25
        L23:
            r3 = move-exception
            goto L54
        L25:
            monitor-exit(r2)
            return
        L27:
            boolean r1 = r0.isPrimary()     // Catch: java.lang.Throwable -> L23
            if (r1 != 0) goto L48
            r1 = 22
            boolean r1 = r4.hasCapability(r1)     // Catch: java.lang.Throwable -> L23
            if (r1 != 0) goto L40
            r1 = 26
            boolean r1 = r4.hasCapability(r1)     // Catch: java.lang.Throwable -> L23
            if (r1 == 0) goto L3e
            goto L40
        L3e:
            r1 = 0
            goto L41
        L40:
            r1 = 1
        L41:
            if (r1 != 0) goto L48
            r2.onNetworkLost(r3)     // Catch: java.lang.Throwable -> L23
            monitor-exit(r2)
            return
        L48:
            r2.mNetwork = r3     // Catch: java.lang.Throwable -> L23
            r2.mNetworkCapabilities = r4     // Catch: java.lang.Throwable -> L23
            r2.updateWifiInfo(r0)     // Catch: java.lang.Throwable -> L23
            r2.notifyOnUpdated()     // Catch: java.lang.Throwable -> L23
            monitor-exit(r2)
            return
        L54:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L23
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.WifiEntry.onNetworkCapabilitiesChanged(android.net.Network, android.net.NetworkCapabilities):void");
    }

    public final synchronized void onNetworkLost(Network network) {
        if (network.equals(this.mNetwork)) {
            clearConnectionInfo();
        }
    }

    public final synchronized void onPrimaryWifiInfoChanged(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        if (wifiInfo != null) {
            try {
                if (connectionInfoMatches(wifiInfo)) {
                    if (networkInfo != null) {
                        this.mNetworkInfo = networkInfo;
                    }
                    updateWifiInfo(wifiInfo);
                    notifyOnUpdated();
                    return;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.mNetworkInfo != null) {
            this.mNetworkInfo = null;
            notifyOnUpdated();
        }
    }

    public void onUpdated() {
        notifyOnUpdated();
    }

    public boolean shouldEditBeforeConnect() {
        return false;
    }

    public final boolean shouldShowXLevelIcon() {
        return (getConnectedState() == 0 || this.mConnectivityReport == null || (hasInternetAccess() && !isLowQuality()) || canSignIn() || !isPrimaryNetwork()) ? false : true;
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("][", "[", "]");
        stringJoiner.add(getClass().getSimpleName());
        stringJoiner.add(getTitle());
        stringJoiner.add(getSummary(true));
        StringBuilder sb = new StringBuilder("Level:");
        sb.append(getLevel());
        sb.append(shouldShowXLevelIcon() ? "!" : "");
        stringJoiner.add(sb.toString());
        String securityString = getSecurityString();
        if (!TextUtils.isEmpty(securityString)) {
            stringJoiner.add(securityString);
        }
        int connectedState = getConnectedState();
        if (connectedState == 2) {
            stringJoiner.add("Connected");
        } else if (connectedState == 1) {
            stringJoiner.add("Connecting...");
        }
        if (hasInternetAccess()) {
            stringJoiner.add("Internet");
        }
        if (isDefaultNetwork()) {
            stringJoiner.add("Default");
        }
        if (isPrimaryNetwork()) {
            stringJoiner.add("Primary");
        }
        if (isLowQuality()) {
            stringJoiner.add("LowQuality");
        }
        if (isSaved()) {
            stringJoiner.add("Saved");
        }
        if (isSubscription()) {
            stringJoiner.add("Subscription");
        }
        if (isSuggestion()) {
            stringJoiner.add("Suggestion");
        }
        if (isMetered()) {
            stringJoiner.add("Metered");
        }
        if ((isSaved() || isSuggestion() || isSubscription()) && !isAutoJoinEnabled()) {
            stringJoiner.add("AutoJoinDisabled");
        }
        if (isExpired()) {
            stringJoiner.add("Expired");
        }
        if (canSignIn()) {
            stringJoiner.add("SignIn");
        }
        if (shouldEditBeforeConnect()) {
            stringJoiner.add("EditBeforeConnect");
        }
        if (hasAdminRestrictions()) {
            stringJoiner.add("AdminRestricted");
        }
        return stringJoiner.toString();
    }

    public final synchronized void updateLinkProperties(Network network, LinkProperties linkProperties) {
        try {
            if (network.equals(this.mNetwork)) {
                if (this.mConnectedInfo == null) {
                    this.mConnectedInfo = new ConnectedInfo();
                }
                ArrayList arrayList = new ArrayList();
                for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                    if (linkAddress.getAddress() instanceof Inet4Address) {
                        ConnectedInfo connectedInfo = this.mConnectedInfo;
                        linkAddress.getAddress().getHostAddress();
                        connectedInfo.getClass();
                        try {
                            InetAddress byAddress = InetAddress.getByAddress(new byte[]{-1, -1, -1, -1});
                            ConnectedInfo connectedInfo2 = this.mConnectedInfo;
                            Utils.getNetworkPart(byAddress, linkAddress.getPrefixLength()).getHostAddress();
                            connectedInfo2.getClass();
                        } catch (IllegalArgumentException | UnknownHostException unused) {
                        }
                    } else if (linkAddress.getAddress() instanceof Inet6Address) {
                        arrayList.add(linkAddress.getAddress().getHostAddress());
                    }
                }
                this.mConnectedInfo.getClass();
                Iterator<RouteInfo> it = linkProperties.getRoutes().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RouteInfo next = it.next();
                    if (next.isDefaultRoute() && (next.getDestination().getAddress() instanceof Inet4Address) && next.hasGateway()) {
                        ConnectedInfo connectedInfo3 = this.mConnectedInfo;
                        next.getGateway().getHostAddress();
                        connectedInfo3.getClass();
                        break;
                    }
                }
                ConnectedInfo connectedInfo4 = this.mConnectedInfo;
                connectedInfo4.getClass();
                notifyOnUpdated();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void updateWifiInfo(WifiInfo wifiInfo) {
        synchronized (this) {
            if (wifiInfo == null) {
                this.mWifiInfo = null;
                this.mConnectedInfo = null;
                this.mWifiInfoLevel = -1;
                updateSecurityTypes();
                return;
            }
            this.mWifiInfo = wifiInfo;
            int rssi = wifiInfo.getRssi();
            if (rssi != -127) {
                this.mWifiInfoLevel = this.mWifiManager.calculateSignalLevel(rssi);
            }
            if (getConnectedState() == 2) {
                if (this.mCalledConnect) {
                    this.mCalledConnect = false;
                    this.mCallbackHandler.post(new WifiEntry$$ExternalSyntheticLambda12(1, this));
                }
                if (this.mConnectedInfo == null) {
                    this.mConnectedInfo = new ConnectedInfo();
                }
                ConnectedInfo connectedInfo = this.mConnectedInfo;
                this.mWifiInfo.getFrequency();
                connectedInfo.getClass();
                ConnectedInfo connectedInfo2 = this.mConnectedInfo;
                this.mWifiInfo.getLinkSpeed();
                connectedInfo2.getClass();
                ConnectedInfo connectedInfo3 = this.mConnectedInfo;
                this.mWifiInfo.getWifiStandard();
                connectedInfo3.getClass();
            }
            updateSecurityTypes();
        }
    }

    public void updateSecurityTypes() {
    }
}
