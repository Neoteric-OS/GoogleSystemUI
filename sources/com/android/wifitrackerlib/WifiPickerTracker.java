package com.android.wifitrackerlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.TransportInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.net.wifi.sharedconnectivity.app.HotspotNetworkConnectionStatus;
import android.net.wifi.sharedconnectivity.app.KnownNetwork;
import android.net.wifi.sharedconnectivity.app.KnownNetworkConnectionStatus;
import android.net.wifi.sharedconnectivity.app.SharedConnectivityClientCallback;
import android.net.wifi.sharedconnectivity.app.SharedConnectivityManager;
import android.net.wifi.sharedconnectivity.app.SharedConnectivitySettingsState;
import android.os.Handler;
import android.telephony.SubscriptionManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import androidx.core.os.BuildCompat;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.wifitrackerlib.StandardWifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class WifiPickerTracker {
    public List mActiveWifiEntries;
    public WifiEntry mConnectedWifiEntry;
    public final BaseWifiTracker$5 mConnectivityDiagnosticsExecutor;
    public final ConnectivityDiagnosticsManager mConnectivityDiagnosticsManager;
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public final BaseWifiTracker$2 mDefaultNetworkCallback;
    public final List mHotspotNetworkDataCache;
    public final List mHotspotNetworkEntryCache;
    public final WifiTrackerInjector mInjector;
    public final List mKnownNetworkDataCache;
    public final List mKnownNetworkEntryCache;
    public final WifiPickerTrackerCallback mListener;
    public final WifiPickerTrackerCallback mListener$1;
    public final Handler mMainHandler;
    public final long mMaxScanAgeMillis;
    public MergedCarrierEntry mMergedCarrierEntry;
    public final BaseWifiTracker$2 mNetworkCallback;
    public final ArrayMap mNetworkRequestConfigCache;
    public NetworkRequestEntry mNetworkRequestEntry;
    public final Map mOsuWifiEntryCache;
    public final Map mPasspointConfigCache;
    public final SparseArray mPasspointWifiConfigCache;
    public final Map mPasspointWifiEntryCache;
    public final long mScanIntervalMillis;
    public final ScanResultUpdater mScanResultUpdater;
    public final BaseWifiTracker$Scanner mScanner;
    public final BaseWifiTracker$7 mSharedConnectivityCallback;
    public final BaseWifiTracker$5 mSharedConnectivityExecutor;
    public final SharedConnectivityManager mSharedConnectivityManager;
    public final Map mStandardWifiConfigCache;
    public final List mStandardWifiEntryCache;
    public final Map mSuggestedConfigCache;
    public final List mSuggestedWifiEntryCache;
    public final String mTag;
    public List mWifiEntries;
    public final WifiManager mWifiManager;
    public final Handler mWorkerHandler;
    public int mWifiState = 1;
    public boolean mIsInitialized = false;
    public boolean mIsScanningDisabled = false;
    public final BaseWifiTracker$1 mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.wifitrackerlib.BaseWifiTracker$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            WifiPickerTracker.this.isVerboseLoggingEnabled();
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                WifiPickerTracker.this.mWifiState = intent.getIntExtra("wifi_state", 1);
                WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                boolean z = wifiPickerTracker.mWifiState == 3;
                BaseWifiTracker$Scanner baseWifiTracker$Scanner = wifiPickerTracker.mScanner;
                boolean z2 = baseWifiTracker$Scanner.mIsWifiEnabled;
                baseWifiTracker$Scanner.mIsWifiEnabled = z;
                if (z != z2) {
                    if (z) {
                        baseWifiTracker$Scanner.possiblyStartScanning();
                    } else {
                        Log.i(baseWifiTracker$Scanner.this$0.mTag, "Scanning stopped");
                        baseWifiTracker$Scanner.removeCallbacksAndMessages(null);
                    }
                }
                WifiPickerTracker wifiPickerTracker2 = WifiPickerTracker.this;
                WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback = wifiPickerTracker2.mListener$1;
                if (wifiPickerTrackerCallback != null) {
                    wifiPickerTracker2.mMainHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda3(wifiPickerTrackerCallback, 0));
                }
                WifiPickerTracker wifiPickerTracker3 = WifiPickerTracker.this;
                if (wifiPickerTracker3.mWifiState == 1) {
                    wifiPickerTracker3.mStandardWifiEntryCache.clear();
                    wifiPickerTracker3.mSuggestedWifiEntryCache.clear();
                    ((ArrayMap) wifiPickerTracker3.mPasspointWifiEntryCache).clear();
                    ((ArrayMap) wifiPickerTracker3.mOsuWifiEntryCache).clear();
                    wifiPickerTracker3.mInjector.getClass();
                    if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                        wifiPickerTracker3.mKnownNetworkEntryCache.clear();
                        wifiPickerTracker3.mHotspotNetworkEntryCache.clear();
                    }
                    wifiPickerTracker3.mNetworkRequestEntry = null;
                }
                wifiPickerTracker3.updateWifiEntries(0);
                return;
            }
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                WifiPickerTracker wifiPickerTracker4 = WifiPickerTracker.this;
                wifiPickerTracker4.conditionallyUpdateScanResults(intent.getBooleanExtra("resultsUpdated", true));
                wifiPickerTracker4.updateWifiEntries(1);
                return;
            }
            if ("android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action)) {
                WifiPickerTracker wifiPickerTracker5 = WifiPickerTracker.this;
                wifiPickerTracker5.updateWifiConfigurationsInternal();
                wifiPickerTracker5.updatePasspointConfigurations(wifiPickerTracker5.mWifiManager.getPasspointConfigurations());
                wifiPickerTracker5.conditionallyUpdateScanResults(false);
                Handler handler = wifiPickerTracker5.mMainHandler;
                WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback2 = wifiPickerTracker5.mListener;
                if (wifiPickerTrackerCallback2 != null) {
                    handler.post(new WifiPickerTracker$$ExternalSyntheticLambda37(wifiPickerTrackerCallback2));
                }
                if (wifiPickerTrackerCallback2 != null) {
                    handler.post(new WifiPickerTracker$$ExternalSyntheticLambda37(wifiPickerTrackerCallback2));
                }
                wifiPickerTracker5.updateWifiEntries(0);
                return;
            }
            if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                WifiPickerTracker wifiPickerTracker6 = WifiPickerTracker.this;
                WifiInfo connectionInfo = wifiPickerTracker6.mWifiManager.getConnectionInfo();
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (connectionInfo != null) {
                    wifiPickerTracker6.conditionallyCreateConnectedWifiEntry(connectionInfo);
                }
                Iterator it = wifiPickerTracker6.getAllWifiEntries().iterator();
                while (it.hasNext()) {
                    ((WifiEntry) it.next()).onPrimaryWifiInfoChanged(connectionInfo, networkInfo);
                }
                wifiPickerTracker6.updateWifiEntries(0);
                return;
            }
            if (!"android.net.wifi.RSSI_CHANGED".equals(action)) {
                if ("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED".equals(action)) {
                    WifiPickerTracker.this.handleDefaultSubscriptionChanged(intent.getIntExtra("subscription", -1));
                }
            } else {
                WifiPickerTracker wifiPickerTracker7 = WifiPickerTracker.this;
                WifiInfo connectionInfo2 = wifiPickerTracker7.mWifiManager.getConnectionInfo();
                Iterator it2 = wifiPickerTracker7.getAllWifiEntries().iterator();
                while (it2.hasNext()) {
                    ((WifiEntry) it2.next()).onPrimaryWifiInfoChanged(connectionInfo2, null);
                }
            }
        }
    };
    public final BaseWifiTracker$WifiTrackerLifecycleObserver mLifecycleObserver = new LifecycleObserver() { // from class: com.android.wifitrackerlib.BaseWifiTracker$WifiTrackerLifecycleObserver
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            BaseWifiTracker$7 baseWifiTracker$7;
            WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
            try {
                wifiPickerTracker.mContext.unregisterReceiver(wifiPickerTracker.mBroadcastReceiver);
                wifiPickerTracker.mConnectivityManager.unregisterNetworkCallback(wifiPickerTracker.mNetworkCallback);
                wifiPickerTracker.mConnectivityManager.unregisterNetworkCallback(wifiPickerTracker.mDefaultNetworkCallback);
                wifiPickerTracker.mConnectivityDiagnosticsManager.unregisterConnectivityDiagnosticsCallback(wifiPickerTracker.mConnectivityDiagnosticsCallback);
                SharedConnectivityManager sharedConnectivityManager = wifiPickerTracker.mSharedConnectivityManager;
                if (sharedConnectivityManager == null || (baseWifiTracker$7 = wifiPickerTracker.mSharedConnectivityCallback) == null) {
                    return;
                }
                int i = BuildCompat.$r8$clinit;
                if (sharedConnectivityManager.unregisterCallback(baseWifiTracker$7)) {
                    return;
                }
                Log.e(wifiPickerTracker.mTag, "onDestroyed: unregisterCallback failed");
            } catch (IllegalArgumentException unused) {
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
            wifiPickerTracker.isVerboseLoggingEnabled();
            BaseWifiTracker$Scanner baseWifiTracker$Scanner = wifiPickerTracker.mScanner;
            baseWifiTracker$Scanner.mIsStartedState = true;
            WifiPickerTracker wifiPickerTracker2 = baseWifiTracker$Scanner.this$0;
            wifiPickerTracker2.mWorkerHandler.post(new BaseWifiTracker$Scanner$$ExternalSyntheticLambda0(baseWifiTracker$Scanner, 1));
            wifiPickerTracker.mWorkerHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda1(wifiPickerTracker, 1));
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
            wifiPickerTracker.isVerboseLoggingEnabled();
            BaseWifiTracker$Scanner baseWifiTracker$Scanner = wifiPickerTracker.mScanner;
            baseWifiTracker$Scanner.mIsStartedState = false;
            WifiPickerTracker wifiPickerTracker2 = baseWifiTracker$Scanner.this$0;
            wifiPickerTracker2.mWorkerHandler.post(new BaseWifiTracker$Scanner$$ExternalSyntheticLambda0(baseWifiTracker$Scanner, 0));
            wifiPickerTracker.mWorkerHandler.post(new BaseWifiTracker$$ExternalSyntheticLambda1(wifiPickerTracker, 0));
        }
    };
    public final NetworkRequest mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addCapability(15).addTransportType(1).build();
    public final BaseWifiTracker$4 mConnectivityDiagnosticsCallback = new ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback() { // from class: com.android.wifitrackerlib.BaseWifiTracker$4
        @Override // android.net.ConnectivityDiagnosticsManager.ConnectivityDiagnosticsCallback
        public final void onConnectivityReportAvailable(ConnectivityDiagnosticsManager.ConnectivityReport connectivityReport) {
            for (WifiEntry wifiEntry : WifiPickerTracker.this.getAllWifiEntries()) {
                synchronized (wifiEntry) {
                    if (connectivityReport.getNetwork().equals(wifiEntry.mNetwork)) {
                        wifiEntry.mConnectivityReport = connectivityReport;
                        wifiEntry.notifyOnUpdated();
                    }
                }
            }
        }
    };

    /* JADX WARN: Type inference failed for: r11v0, types: [com.android.wifitrackerlib.BaseWifiTracker$1] */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.android.wifitrackerlib.BaseWifiTracker$WifiTrackerLifecycleObserver] */
    /* JADX WARN: Type inference failed for: r12v5, types: [com.android.wifitrackerlib.BaseWifiTracker$2] */
    /* JADX WARN: Type inference failed for: r12v6, types: [com.android.wifitrackerlib.BaseWifiTracker$2] */
    /* JADX WARN: Type inference failed for: r12v7, types: [com.android.wifitrackerlib.BaseWifiTracker$4] */
    /* JADX WARN: Type inference failed for: r12v8, types: [com.android.wifitrackerlib.BaseWifiTracker$5] */
    /* JADX WARN: Type inference failed for: r12v9, types: [com.android.wifitrackerlib.BaseWifiTracker$5] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.android.wifitrackerlib.BaseWifiTracker$7] */
    public WifiPickerTracker(WifiTrackerInjector wifiTrackerInjector, Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, Handler handler, Handler handler2, Clock clock, long j, long j2, WifiPickerTrackerCallback wifiPickerTrackerCallback) {
        final int i = 1;
        final int i2 = 0;
        this.mSharedConnectivityManager = null;
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback(this) { // from class: com.android.wifitrackerlib.BaseWifiTracker$2
            public final /* synthetic */ WifiPickerTracker this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                switch (i2) {
                    case 0:
                        this.this$0.handleNetworkCapabilitiesChanged(network, networkCapabilities);
                        return;
                    default:
                        WifiPickerTracker wifiPickerTracker = this.this$0;
                        for (WifiEntry wifiEntry : wifiPickerTracker.getAllWifiEntries()) {
                            synchronized (wifiEntry) {
                                wifiEntry.mDefaultNetwork = network;
                                wifiEntry.mDefaultNetworkCapabilities = networkCapabilities;
                                wifiEntry.notifyOnUpdated();
                            }
                        }
                        wifiPickerTracker.notifyOnWifiEntriesChanged(0);
                        return;
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                switch (i2) {
                    case 0:
                        Iterator it = this.this$0.getAllWifiEntries().iterator();
                        while (it.hasNext()) {
                            ((WifiEntry) it.next()).updateLinkProperties(network, linkProperties);
                        }
                        break;
                    default:
                        super.onLinkPropertiesChanged(network, linkProperties);
                        break;
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(Network network) {
                switch (i2) {
                    case 0:
                        WifiPickerTracker wifiPickerTracker = this.this$0;
                        Iterator it = wifiPickerTracker.getAllWifiEntries().iterator();
                        while (it.hasNext()) {
                            ((WifiEntry) it.next()).onNetworkLost(network);
                        }
                        NetworkRequestEntry networkRequestEntry = wifiPickerTracker.mNetworkRequestEntry;
                        if (networkRequestEntry != null && networkRequestEntry.getConnectedState() == 0) {
                            wifiPickerTracker.mNetworkRequestEntry = null;
                        }
                        wifiPickerTracker.updateWifiEntries(0);
                        return;
                    default:
                        WifiPickerTracker wifiPickerTracker2 = this.this$0;
                        for (WifiEntry wifiEntry : wifiPickerTracker2.getAllWifiEntries()) {
                            synchronized (wifiEntry) {
                                wifiEntry.mDefaultNetwork = null;
                                wifiEntry.mDefaultNetworkCapabilities = null;
                                wifiEntry.notifyOnUpdated();
                            }
                        }
                        wifiPickerTracker2.notifyOnWifiEntriesChanged(0);
                        return;
                }
            }
        };
        this.mDefaultNetworkCallback = new ConnectivityManager.NetworkCallback(this) { // from class: com.android.wifitrackerlib.BaseWifiTracker$2
            public final /* synthetic */ WifiPickerTracker this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                switch (i) {
                    case 0:
                        this.this$0.handleNetworkCapabilitiesChanged(network, networkCapabilities);
                        return;
                    default:
                        WifiPickerTracker wifiPickerTracker = this.this$0;
                        for (WifiEntry wifiEntry : wifiPickerTracker.getAllWifiEntries()) {
                            synchronized (wifiEntry) {
                                wifiEntry.mDefaultNetwork = network;
                                wifiEntry.mDefaultNetworkCapabilities = networkCapabilities;
                                wifiEntry.notifyOnUpdated();
                            }
                        }
                        wifiPickerTracker.notifyOnWifiEntriesChanged(0);
                        return;
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                switch (i) {
                    case 0:
                        Iterator it = this.this$0.getAllWifiEntries().iterator();
                        while (it.hasNext()) {
                            ((WifiEntry) it.next()).updateLinkProperties(network, linkProperties);
                        }
                        break;
                    default:
                        super.onLinkPropertiesChanged(network, linkProperties);
                        break;
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(Network network) {
                switch (i) {
                    case 0:
                        WifiPickerTracker wifiPickerTracker = this.this$0;
                        Iterator it = wifiPickerTracker.getAllWifiEntries().iterator();
                        while (it.hasNext()) {
                            ((WifiEntry) it.next()).onNetworkLost(network);
                        }
                        NetworkRequestEntry networkRequestEntry = wifiPickerTracker.mNetworkRequestEntry;
                        if (networkRequestEntry != null && networkRequestEntry.getConnectedState() == 0) {
                            wifiPickerTracker.mNetworkRequestEntry = null;
                        }
                        wifiPickerTracker.updateWifiEntries(0);
                        return;
                    default:
                        WifiPickerTracker wifiPickerTracker2 = this.this$0;
                        for (WifiEntry wifiEntry : wifiPickerTracker2.getAllWifiEntries()) {
                            synchronized (wifiEntry) {
                                wifiEntry.mDefaultNetwork = null;
                                wifiEntry.mDefaultNetworkCapabilities = null;
                                wifiEntry.notifyOnUpdated();
                            }
                        }
                        wifiPickerTracker2.notifyOnWifiEntriesChanged(0);
                        return;
                }
            }
        };
        this.mConnectivityDiagnosticsExecutor = new Executor(this) { // from class: com.android.wifitrackerlib.BaseWifiTracker$5
            public final /* synthetic */ WifiPickerTracker this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                switch (i2) {
                    case 0:
                        this.this$0.mWorkerHandler.post(runnable);
                        break;
                    default:
                        this.this$0.mWorkerHandler.post(runnable);
                        break;
                }
            }
        };
        this.mSharedConnectivityExecutor = new Executor(this) { // from class: com.android.wifitrackerlib.BaseWifiTracker$5
            public final /* synthetic */ WifiPickerTracker this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                switch (i) {
                    case 0:
                        this.this$0.mWorkerHandler.post(runnable);
                        break;
                    default:
                        this.this$0.mWorkerHandler.post(runnable);
                        break;
                }
            }
        };
        this.mSharedConnectivityCallback = null;
        this.mInjector = wifiTrackerInjector;
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mConnectivityManager = connectivityManager;
        this.mConnectivityDiagnosticsManager = (ConnectivityDiagnosticsManager) context.getSystemService(ConnectivityDiagnosticsManager.class);
        wifiTrackerInjector.getClass();
        if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
            int i3 = BuildCompat.$r8$clinit;
            this.mSharedConnectivityManager = (SharedConnectivityManager) context.getSystemService(SharedConnectivityManager.class);
            this.mSharedConnectivityCallback = new SharedConnectivityClientCallback() { // from class: com.android.wifitrackerlib.BaseWifiTracker$7
                public final void onHotspotNetworkConnectionStatusChanged(HotspotNetworkConnectionStatus hotspotNetworkConnectionStatus) {
                    WifiPickerTracker.this.mHotspotNetworkEntryCache.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda18(0, hotspotNetworkConnectionStatus)).forEach(new WifiPickerTracker$$ExternalSyntheticLambda14(2, hotspotNetworkConnectionStatus));
                }

                public final void onHotspotNetworksUpdated(List list) {
                    WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                    wifiPickerTracker.mInjector.getClass();
                    if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                        wifiPickerTracker.mHotspotNetworkDataCache.clear();
                        wifiPickerTracker.mHotspotNetworkDataCache.addAll(list);
                        wifiPickerTracker.updateHotspotNetworkEntries();
                        wifiPickerTracker.updateWifiEntries(0);
                    }
                }

                public final void onKnownNetworkConnectionStatusChanged(KnownNetworkConnectionStatus knownNetworkConnectionStatus) {
                    WifiPickerTracker.this.mKnownNetworkEntryCache.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda16(new StandardWifiEntry.ScanResultKey(knownNetworkConnectionStatus.getKnownNetwork().getSsid(), new ArrayList(knownNetworkConnectionStatus.getKnownNetwork().getSecurityTypes())), 0)).forEach(new WifiPickerTracker$$ExternalSyntheticLambda14(1, knownNetworkConnectionStatus));
                }

                public final void onKnownNetworksUpdated(List list) {
                    WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                    wifiPickerTracker.mInjector.getClass();
                    if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                        wifiPickerTracker.mKnownNetworkDataCache.clear();
                        wifiPickerTracker.mKnownNetworkDataCache.addAll(list);
                        ScanResultUpdater scanResultUpdater = wifiPickerTracker.mScanResultUpdater;
                        wifiPickerTracker.updateKnownNetworkEntryScans(scanResultUpdater.getScanResults(scanResultUpdater.mMaxScanAgeMillis));
                        wifiPickerTracker.updateWifiEntries(0);
                    }
                }

                public final void onRegisterCallbackFailed(Exception exc) {
                    WifiPickerTracker.this.getClass();
                }

                public final void onServiceConnected() {
                    WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                    wifiPickerTracker.mInjector.getClass();
                    if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                        wifiPickerTracker.mKnownNetworkDataCache.clear();
                        List knownNetworks = wifiPickerTracker.mSharedConnectivityManager.getKnownNetworks();
                        if (knownNetworks != null) {
                            wifiPickerTracker.mKnownNetworkDataCache.addAll(knownNetworks);
                        }
                        wifiPickerTracker.mHotspotNetworkDataCache.clear();
                        List hotspotNetworks = wifiPickerTracker.mSharedConnectivityManager.getHotspotNetworks();
                        if (hotspotNetworks != null) {
                            wifiPickerTracker.mHotspotNetworkDataCache.addAll(hotspotNetworks);
                        }
                        ScanResultUpdater scanResultUpdater = wifiPickerTracker.mScanResultUpdater;
                        wifiPickerTracker.updateKnownNetworkEntryScans(scanResultUpdater.getScanResults(scanResultUpdater.mMaxScanAgeMillis));
                        wifiPickerTracker.updateHotspotNetworkEntries();
                        HotspotNetworkConnectionStatus hotspotNetworkConnectionStatus = wifiPickerTracker.mSharedConnectivityManager.getHotspotNetworkConnectionStatus();
                        if (hotspotNetworkConnectionStatus != null) {
                            wifiPickerTracker.mHotspotNetworkEntryCache.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda18(0, hotspotNetworkConnectionStatus)).forEach(new WifiPickerTracker$$ExternalSyntheticLambda14(2, hotspotNetworkConnectionStatus));
                        }
                        wifiPickerTracker.updateWifiEntries(0);
                    }
                }

                public final void onServiceDisconnected() {
                    WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                    wifiPickerTracker.mInjector.getClass();
                    if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                        wifiPickerTracker.mKnownNetworkDataCache.clear();
                        wifiPickerTracker.mHotspotNetworkDataCache.clear();
                        wifiPickerTracker.mKnownNetworkEntryCache.clear();
                        wifiPickerTracker.mHotspotNetworkEntryCache.clear();
                        wifiPickerTracker.updateWifiEntries(0);
                    }
                }

                public final void onSharedConnectivitySettingsChanged(SharedConnectivitySettingsState sharedConnectivitySettingsState) {
                    WifiPickerTracker.this.getClass();
                }
            };
        }
        this.mMainHandler = handler;
        this.mWorkerHandler = handler2;
        this.mMaxScanAgeMillis = j;
        this.mScanIntervalMillis = j2;
        this.mListener$1 = wifiPickerTrackerCallback;
        this.mTag = "WifiPickerTracker";
        this.mScanResultUpdater = new ScanResultUpdater(clock, j + j2);
        this.mScanner = new BaseWifiTracker$Scanner(this, handler2.getLooper());
        if (lifecycle != null) {
            handler.post(new BaseWifiTracker$$ExternalSyntheticLambda0(i2, this, lifecycle));
        }
        this.mActiveWifiEntries = new ArrayList();
        this.mWifiEntries = new ArrayList();
        this.mStandardWifiConfigCache = new ArrayMap();
        this.mSuggestedConfigCache = new ArrayMap();
        this.mNetworkRequestConfigCache = new ArrayMap();
        this.mStandardWifiEntryCache = new ArrayList();
        this.mSuggestedWifiEntryCache = new ArrayList();
        this.mPasspointConfigCache = new ArrayMap();
        this.mPasspointWifiConfigCache = new SparseArray();
        this.mPasspointWifiEntryCache = new ArrayMap();
        this.mOsuWifiEntryCache = new ArrayMap();
        this.mKnownNetworkDataCache = new ArrayList();
        this.mKnownNetworkEntryCache = new ArrayList();
        this.mHotspotNetworkDataCache = new ArrayList();
        this.mHotspotNetworkEntryCache = new ArrayList();
        this.mListener = wifiPickerTrackerCallback;
    }

    public final void conditionallyCreateConnectedWifiEntry(WifiInfo wifiInfo) {
        WifiConfiguration wifiConfiguration;
        if (wifiInfo != null && !wifiInfo.isPasspointAp() && !wifiInfo.isOsuAp()) {
            final int networkId = wifiInfo.getNetworkId();
            Iterator it = ((ArrayMap) this.mStandardWifiConfigCache).values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                List list = (List) it.next();
                if (list.stream().map(new WifiPickerTracker$$ExternalSyntheticLambda1(10)).filter(new Predicate() { // from class: com.android.wifitrackerlib.WifiPickerTracker$$ExternalSyntheticLambda10
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((Integer) obj).intValue() == networkId;
                    }
                }).count() != 0) {
                    StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey = new StandardWifiEntry.StandardWifiEntryKey((WifiConfiguration) list.get(0), true);
                    Iterator it2 = this.mStandardWifiEntryCache.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            this.mStandardWifiEntryCache.add(new StandardWifiEntry(this.mInjector, this.mMainHandler, standardWifiEntryKey, list, null, this.mWifiManager));
                            break;
                        } else if (standardWifiEntryKey.equals(((StandardWifiEntry) it2.next()).mKey)) {
                            break;
                        }
                    }
                }
            }
        }
        if (wifiInfo != null && !wifiInfo.isPasspointAp() && !wifiInfo.isOsuAp()) {
            int networkId2 = wifiInfo.getNetworkId();
            Iterator it3 = ((ArrayMap) this.mSuggestedConfigCache).values().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                List list2 = (List) it3.next();
                if (!list2.isEmpty() && ((WifiConfiguration) list2.get(0)).networkId == networkId2) {
                    StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey2 = new StandardWifiEntry.StandardWifiEntryKey((WifiConfiguration) list2.get(0), true);
                    Iterator it4 = this.mSuggestedWifiEntryCache.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            this.mSuggestedWifiEntryCache.add(new StandardWifiEntry(this.mInjector, this.mMainHandler, standardWifiEntryKey2, list2, null, this.mWifiManager));
                            break;
                        } else if (standardWifiEntryKey2.equals(((StandardWifiEntry) it4.next()).mKey)) {
                            break;
                        }
                    }
                }
            }
        }
        Handler handler = this.mMainHandler;
        WifiTrackerInjector wifiTrackerInjector = this.mInjector;
        if (wifiInfo != null && wifiInfo.isPasspointAp() && (wifiConfiguration = (WifiConfiguration) this.mPasspointWifiConfigCache.get(wifiInfo.getNetworkId())) != null) {
            if (!((ArrayMap) this.mPasspointWifiEntryCache).containsKey(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey()))) {
                PasspointConfiguration passpointConfiguration = (PasspointConfiguration) ((ArrayMap) this.mPasspointConfigCache).get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey()));
                PasspointWifiEntry passpointWifiEntry = passpointConfiguration != null ? new PasspointWifiEntry(wifiTrackerInjector, handler, passpointConfiguration, this.mWifiManager) : new PasspointWifiEntry(wifiTrackerInjector, handler, wifiConfiguration, this.mWifiManager);
                ((ArrayMap) this.mPasspointWifiEntryCache).put(passpointWifiEntry.mKey, passpointWifiEntry);
            }
        }
        ArrayList arrayList = new ArrayList();
        if (wifiInfo != null) {
            int i = 0;
            while (true) {
                if (i >= this.mNetworkRequestConfigCache.size()) {
                    break;
                }
                List list3 = (List) this.mNetworkRequestConfigCache.valueAt(i);
                if (!list3.isEmpty() && ((WifiConfiguration) list3.get(0)).networkId == wifiInfo.getNetworkId()) {
                    arrayList.addAll(list3);
                    break;
                }
                i++;
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey3 = new StandardWifiEntry.StandardWifiEntryKey((WifiConfiguration) arrayList.get(0), false);
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null || !networkRequestEntry.mKey.equals(standardWifiEntryKey3)) {
            NetworkRequestEntry networkRequestEntry2 = new NetworkRequestEntry(wifiTrackerInjector, handler, standardWifiEntryKey3, this.mWifiManager);
            this.mNetworkRequestEntry = networkRequestEntry2;
            networkRequestEntry2.updateConfig(arrayList);
            ScanResultUpdater scanResultUpdater = this.mScanResultUpdater;
            updateNetworkRequestEntryScans(scanResultUpdater.getScanResults(scanResultUpdater.mMaxScanAgeMillis));
        }
    }

    public final void conditionallyUpdateScanResults(boolean z) {
        int wifiState = this.mWifiManager.getWifiState();
        WifiTrackerInjector wifiTrackerInjector = this.mInjector;
        if (wifiState == 1) {
            updateStandardWifiEntryScans(Collections.emptyList());
            updateSuggestedWifiEntryScans(Collections.emptyList());
            updatePasspointWifiEntryScans(Collections.emptyList());
            updateOsuWifiEntryScans(Collections.emptyList());
            wifiTrackerInjector.getClass();
            if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
                int i = BuildCompat.$r8$clinit;
                this.mKnownNetworkEntryCache.clear();
                this.mHotspotNetworkEntryCache.clear();
            }
            updateNetworkRequestEntryScans(Collections.emptyList());
            Collections.emptyList();
            return;
        }
        ScanResultUpdater scanResultUpdater = this.mScanResultUpdater;
        long j = this.mMaxScanAgeMillis;
        if (z) {
            scanResultUpdater.update(this.mWifiManager.getScanResults());
        } else {
            j += this.mScanIntervalMillis;
        }
        List scanResults = scanResultUpdater.getScanResults(j);
        updateStandardWifiEntryScans(scanResults);
        updateSuggestedWifiEntryScans(scanResults);
        updatePasspointWifiEntryScans(scanResults);
        updateOsuWifiEntryScans(scanResults);
        wifiTrackerInjector.getClass();
        if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
            int i2 = BuildCompat.$r8$clinit;
            updateKnownNetworkEntryScans(scanResults);
            updateHotspotNetworkEntries();
        }
        updateNetworkRequestEntryScans(scanResults);
    }

    public final List getAllWifiEntries() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mStandardWifiEntryCache);
        arrayList.addAll(this.mSuggestedWifiEntryCache);
        arrayList.addAll(((ArrayMap) this.mPasspointWifiEntryCache).values());
        arrayList.addAll(((ArrayMap) this.mOsuWifiEntryCache).values());
        this.mInjector.getClass();
        if (WifiTrackerInjector.isSharedConnectivityFeatureEnabled()) {
            arrayList.addAll(this.mKnownNetworkEntryCache);
            arrayList.addAll(this.mHotspotNetworkEntryCache);
        }
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry != null) {
            arrayList.add(networkRequestEntry);
        }
        MergedCarrierEntry mergedCarrierEntry = this.mMergedCarrierEntry;
        if (mergedCarrierEntry != null) {
            arrayList.add(mergedCarrierEntry);
        }
        return arrayList;
    }

    public final MergedCarrierEntry getMergedCarrierEntry() {
        int defaultDataSubscriptionId;
        if (!this.mIsInitialized && this.mMergedCarrierEntry == null && (defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId()) != -1) {
            this.mMergedCarrierEntry = new MergedCarrierEntry(this.mInjector, this.mWorkerHandler, this.mWifiManager, defaultDataSubscriptionId);
        }
        return this.mMergedCarrierEntry;
    }

    public final void handleDefaultSubscriptionChanged(int i) {
        if (i != -1) {
            MergedCarrierEntry mergedCarrierEntry = this.mMergedCarrierEntry;
            if (mergedCarrierEntry != null && i == mergedCarrierEntry.mSubscriptionId) {
                return;
            }
            this.mMergedCarrierEntry = new MergedCarrierEntry(this.mInjector, this.mWorkerHandler, this.mWifiManager, i);
            Network currentNetwork = this.mWifiManager.getCurrentNetwork();
            if (currentNetwork != null) {
                NetworkCapabilities networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(currentNetwork);
                if (networkCapabilities != null) {
                    this.mMergedCarrierEntry.onNetworkCapabilitiesChanged(currentNetwork, new NetworkCapabilities.Builder(networkCapabilities).setTransportInfo(this.mWifiManager.getConnectionInfo()).build());
                }
                LinkProperties linkProperties = this.mConnectivityManager.getLinkProperties(currentNetwork);
                if (linkProperties != null) {
                    this.mMergedCarrierEntry.updateLinkProperties(currentNetwork, linkProperties);
                }
            }
        } else if (this.mMergedCarrierEntry == null) {
            return;
        } else {
            this.mMergedCarrierEntry = null;
        }
        notifyOnWifiEntriesChanged(0);
    }

    public final void handleNetworkCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        if (this.mNetworkRequestConfigCache.size() + this.mPasspointWifiConfigCache.size() + ((ArrayMap) this.mSuggestedConfigCache).size() + ((ArrayMap) this.mStandardWifiConfigCache).size() == 0) {
            updateWifiConfigurationsInternal();
        }
        TransportInfo transportInfo = networkCapabilities.getTransportInfo();
        conditionallyCreateConnectedWifiEntry(transportInfo instanceof WifiInfo ? (WifiInfo) transportInfo : null);
        Iterator it = getAllWifiEntries().iterator();
        while (it.hasNext()) {
            ((WifiEntry) it.next()).onNetworkCapabilitiesChanged(network, networkCapabilities);
        }
        updateWifiEntries(0);
    }

    public final boolean isVerboseLoggingEnabled() {
        WifiTrackerInjector wifiTrackerInjector = this.mInjector;
        return !wifiTrackerInjector.mVerboseLoggingDisabledOverride && (wifiTrackerInjector.mWifiManager.isVerboseLoggingEnabled() || wifiTrackerInjector.mIsUserDebugVerboseLoggingEnabled);
    }

    public final void notifyOnWifiEntriesChanged(final int i) {
        if (this.mListener != null) {
            this.mMainHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.WifiPickerTracker$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    WifiPickerTracker wifiPickerTracker = WifiPickerTracker.this;
                    wifiPickerTracker.mListener.onWifiEntriesChanged(i);
                }
            });
        }
    }

    public final void updateHotspotNetworkEntries() {
        NetworkCapabilities networkCapabilities;
        Map map = (Map) this.mHotspotNetworkDataCache.stream().collect(Collectors.toMap(new WifiPickerTracker$$ExternalSyntheticLambda1(5), new WifiPickerTracker$$ExternalSyntheticLambda1(6), new WifiPickerTracker$$ExternalSyntheticLambda4(1)));
        ArraySet arraySet = new ArraySet(map.keySet());
        ((ArrayList) this.mHotspotNetworkEntryCache).removeIf(new WifiPickerTracker$$ExternalSyntheticLambda23(2, arraySet));
        ((ArrayList) this.mHotspotNetworkEntryCache).forEach(new WifiPickerTracker$$ExternalSyntheticLambda7(arraySet, map, 1));
        Network network = null;
        NetworkCapabilities networkCapabilities2 = null;
        if (arraySet.isEmpty()) {
            networkCapabilities = null;
        } else {
            Network currentNetwork = this.mWifiManager.getCurrentNetwork();
            if (currentNetwork != null && (networkCapabilities2 = this.mConnectivityManager.getNetworkCapabilities(currentNetwork)) != null) {
                networkCapabilities2 = new NetworkCapabilities.Builder(networkCapabilities2).setTransportInfo(this.mWifiManager.getConnectionInfo()).build();
            }
            NetworkCapabilities networkCapabilities3 = networkCapabilities2;
            network = currentNetwork;
            networkCapabilities = networkCapabilities3;
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            HotspotNetworkEntry hotspotNetworkEntry = new HotspotNetworkEntry(this.mInjector, this.mContext, this.mMainHandler, this.mWifiManager, this.mSharedConnectivityManager, (HotspotNetwork) map.get((Long) it.next()));
            if (network != null && networkCapabilities != null) {
                hotspotNetworkEntry.onNetworkCapabilitiesChanged(network, networkCapabilities);
            }
            this.mHotspotNetworkEntryCache.add(hotspotNetworkEntry);
        }
    }

    public final void updateKnownNetworkEntryScans(List list) {
        NetworkCapabilities networkCapabilities;
        final Map map = (Map) list.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda0(0)).collect(Collectors.groupingBy(new WifiPickerTracker$$ExternalSyntheticLambda1(0)));
        final Map map2 = (Map) this.mKnownNetworkDataCache.stream().collect(Collectors.toMap(new WifiPickerTracker$$ExternalSyntheticLambda1(4), new WifiPickerTracker$$ExternalSyntheticLambda1(8), new WifiPickerTracker$$ExternalSyntheticLambda4(0)));
        final int i = 0;
        ((ArrayList) this.mKnownNetworkEntryCache).removeIf(new Predicate() { // from class: com.android.wifitrackerlib.WifiPickerTracker$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                int i2 = i;
                Map map3 = map2;
                switch (i2) {
                    case 0:
                        return !map3.keySet().contains(((KnownNetworkEntry) obj).mKey.mScanResultKey);
                    default:
                        return map3.containsKey((StandardWifiEntry.ScanResultKey) obj);
                }
            }
        });
        Stream stream = map2.keySet().stream();
        Objects.requireNonNull(map);
        final int i2 = 1;
        Set<StandardWifiEntry.ScanResultKey> set = (Set) stream.filter(new Predicate() { // from class: com.android.wifitrackerlib.WifiPickerTracker$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                int i22 = i2;
                Map map3 = map;
                switch (i22) {
                    case 0:
                        return !map3.keySet().contains(((KnownNetworkEntry) obj).mKey.mScanResultKey);
                    default:
                        return map3.containsKey((StandardWifiEntry.ScanResultKey) obj);
                }
            }
        }).collect(Collectors.toSet());
        ((ArrayList) this.mKnownNetworkEntryCache).forEach(new WifiPickerTracker$$ExternalSyntheticLambda7(set, map, 0));
        Network network = null;
        NetworkCapabilities networkCapabilities2 = null;
        if (set.isEmpty()) {
            networkCapabilities = null;
        } else {
            Network currentNetwork = this.mWifiManager.getCurrentNetwork();
            if (currentNetwork != null && (networkCapabilities2 = this.mConnectivityManager.getNetworkCapabilities(currentNetwork)) != null) {
                networkCapabilities2 = new NetworkCapabilities.Builder(networkCapabilities2).setTransportInfo(this.mWifiManager.getConnectionInfo()).build();
            }
            NetworkCapabilities networkCapabilities3 = networkCapabilities2;
            network = currentNetwork;
            networkCapabilities = networkCapabilities3;
        }
        for (StandardWifiEntry.ScanResultKey scanResultKey : set) {
            KnownNetworkEntry knownNetworkEntry = new KnownNetworkEntry(this.mInjector, this.mMainHandler, new StandardWifiEntry.StandardWifiEntryKey(scanResultKey, true), (List) map.get(scanResultKey), this.mWifiManager, this.mSharedConnectivityManager, (KnownNetwork) map2.get(scanResultKey));
            if (network != null && networkCapabilities != null) {
                knownNetworkEntry.onNetworkCapabilitiesChanged(network, networkCapabilities);
            }
            this.mKnownNetworkEntryCache.add(knownNetworkEntry);
        }
        ((ArrayList) this.mKnownNetworkEntryCache).removeIf(new WifiPickerTracker$$ExternalSyntheticLambda0(13));
    }

    public final void updateNetworkRequestEntryScans(List list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null) {
            return;
        }
        this.mNetworkRequestEntry.updateScanResultInfo((List) list.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda16(networkRequestEntry.mKey.mScanResultKey, 1)).collect(Collectors.toList()));
    }

    public final void updateOsuWifiEntryScans(List list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        Map matchingOsuProviders = this.mWifiManager.getMatchingOsuProviders(list);
        Map matchingPasspointConfigsForOsuProviders = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(matchingOsuProviders.keySet());
        for (OsuWifiEntry osuWifiEntry : ((ArrayMap) this.mOsuWifiEntryCache).values()) {
            osuWifiEntry.updateScanResultInfo((List) matchingOsuProviders.remove(osuWifiEntry.mOsuProvider));
        }
        for (OsuProvider osuProvider : matchingOsuProviders.keySet()) {
            OsuWifiEntry osuWifiEntry2 = new OsuWifiEntry(this.mInjector, this.mMainHandler, osuProvider, this.mWifiManager);
            osuWifiEntry2.updateScanResultInfo((List) matchingOsuProviders.get(osuProvider));
            ((ArrayMap) this.mOsuWifiEntryCache).put(OsuWifiEntry.osuProviderToOsuWifiEntryKey(osuProvider), osuWifiEntry2);
        }
        ((ArrayMap) this.mOsuWifiEntryCache).values().forEach(new WifiPickerTracker$$ExternalSyntheticLambda7(this, matchingPasspointConfigsForOsuProviders, 3));
        ((ArrayMap) this.mOsuWifiEntryCache).entrySet().removeIf(new WifiPickerTracker$$ExternalSyntheticLambda0(10));
    }

    public final void updatePasspointConfigurations(List list) {
        Preconditions.checkNotNull(list, "Config list should not be null!");
        ((ArrayMap) this.mPasspointConfigCache).clear();
        ((ArrayMap) this.mPasspointConfigCache).putAll((Map) list.stream().collect(Collectors.toMap(new WifiPickerTracker$$ExternalSyntheticLambda1(9), Function.identity())));
        ((ArrayMap) this.mPasspointWifiEntryCache).entrySet().removeIf(new WifiPickerTracker$$ExternalSyntheticLambda15(this, 1));
    }

    public final void updatePasspointWifiEntryScans(List list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        TreeSet treeSet = new TreeSet();
        for (Pair pair : this.mWifiManager.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            List list2 = (List) ((Map) pair.second).get(0);
            List list3 = (List) ((Map) pair.second).get(1);
            String uniqueIdToPasspointWifiEntryKey = PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey());
            treeSet.add(uniqueIdToPasspointWifiEntryKey);
            if (!((ArrayMap) this.mPasspointWifiEntryCache).containsKey(uniqueIdToPasspointWifiEntryKey)) {
                if (wifiConfiguration.fromWifiNetworkSuggestion) {
                    ((ArrayMap) this.mPasspointWifiEntryCache).put(uniqueIdToPasspointWifiEntryKey, new PasspointWifiEntry(this.mInjector, this.mMainHandler, wifiConfiguration, this.mWifiManager));
                } else if (((ArrayMap) this.mPasspointConfigCache).containsKey(uniqueIdToPasspointWifiEntryKey)) {
                    ((ArrayMap) this.mPasspointWifiEntryCache).put(uniqueIdToPasspointWifiEntryKey, new PasspointWifiEntry(this.mInjector, this.mMainHandler, (PasspointConfiguration) ((ArrayMap) this.mPasspointConfigCache).get(uniqueIdToPasspointWifiEntryKey), this.mWifiManager));
                } else {
                    continue;
                }
            }
            PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) ((ArrayMap) this.mPasspointWifiEntryCache).get(uniqueIdToPasspointWifiEntryKey);
            synchronized (passpointWifiEntry) {
                try {
                    passpointWifiEntry.mWifiConfig = wifiConfiguration;
                    passpointWifiEntry.mCurrentHomeScanResults.clear();
                    passpointWifiEntry.mCurrentRoamingScanResults.clear();
                    if (list2 != null) {
                        passpointWifiEntry.mCurrentHomeScanResults.addAll(list2);
                    }
                    if (list3 != null) {
                        passpointWifiEntry.mCurrentRoamingScanResults.addAll(list3);
                    }
                    if (passpointWifiEntry.mWifiConfig != null) {
                        ArrayList arrayList = new ArrayList();
                        if (list2 != null && !list2.isEmpty()) {
                            arrayList.addAll(list2);
                        } else if (list3 != null && !list3.isEmpty()) {
                            arrayList.addAll(list3);
                        }
                        ScanResult bestScanResultByLevel = Utils.getBestScanResultByLevel(arrayList);
                        if (bestScanResultByLevel != null) {
                            passpointWifiEntry.mWifiConfig.SSID = "\"" + bestScanResultByLevel.SSID + "\"";
                        }
                        if (passpointWifiEntry.getConnectedState() == 0) {
                            passpointWifiEntry.mScanResultLevel = bestScanResultByLevel != null ? passpointWifiEntry.mWifiManager.calculateSignalLevel(bestScanResultByLevel.level) : -1;
                        }
                    } else {
                        passpointWifiEntry.mScanResultLevel = -1;
                    }
                    passpointWifiEntry.notifyOnUpdated();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        ((ArrayMap) this.mPasspointWifiEntryCache).entrySet().removeIf(new WifiPickerTracker$$ExternalSyntheticLambda23(3, treeSet));
    }

    public final void updateStandardWifiEntryScans(List list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        Map map = (Map) list.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda0(11)).collect(Collectors.groupingBy(new WifiPickerTracker$$ExternalSyntheticLambda1(0)));
        ArraySet arraySet = new ArraySet(map.keySet());
        ((ArrayList) this.mStandardWifiEntryCache).forEach(new WifiPickerTracker$$ExternalSyntheticLambda7(arraySet, map, 2));
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            StandardWifiEntry.ScanResultKey scanResultKey = (StandardWifiEntry.ScanResultKey) it.next();
            StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey = new StandardWifiEntry.StandardWifiEntryKey(scanResultKey, true);
            this.mStandardWifiEntryCache.add(new StandardWifiEntry(this.mInjector, this.mMainHandler, standardWifiEntryKey, (List) ((ArrayMap) this.mStandardWifiConfigCache).get(standardWifiEntryKey), (List) map.get(scanResultKey), this.mWifiManager));
        }
        ((ArrayList) this.mStandardWifiEntryCache).removeIf(new WifiPickerTracker$$ExternalSyntheticLambda0(12));
    }

    public final void updateSuggestedWifiEntryScans(List list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        final Set set = (Set) this.mWifiManager.getWifiConfigForMatchedNetworkSuggestionsSharedWithUser(list).stream().map(new WifiPickerTracker$$ExternalSyntheticLambda1(7)).collect(Collectors.toSet());
        final Map map = (Map) list.stream().filter(new WifiPickerTracker$$ExternalSyntheticLambda0(8)).collect(Collectors.groupingBy(new WifiPickerTracker$$ExternalSyntheticLambda1(0)));
        final ArraySet arraySet = new ArraySet();
        ((ArrayList) this.mSuggestedWifiEntryCache).forEach(new Consumer() { // from class: com.android.wifitrackerlib.WifiPickerTracker$$ExternalSyntheticLambda40
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Set set2 = arraySet;
                Map map2 = map;
                Set set3 = set;
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey = standardWifiEntry.mKey;
                ((ArraySet) set2).add(standardWifiEntryKey);
                standardWifiEntry.updateScanResultInfo((List) map2.get(standardWifiEntryKey.mScanResultKey));
                boolean contains = set3.contains(standardWifiEntryKey);
                synchronized (standardWifiEntry) {
                    standardWifiEntry.mIsUserShareable = contains;
                }
            }
        });
        for (StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey : ((ArrayMap) this.mSuggestedConfigCache).keySet()) {
            StandardWifiEntry.ScanResultKey scanResultKey = standardWifiEntryKey.mScanResultKey;
            if (!arraySet.contains(standardWifiEntryKey) && map.containsKey(scanResultKey)) {
                StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.mInjector, this.mMainHandler, standardWifiEntryKey, (List) ((ArrayMap) this.mSuggestedConfigCache).get(standardWifiEntryKey), (List) map.get(scanResultKey), this.mWifiManager);
                boolean contains = set.contains(standardWifiEntryKey);
                synchronized (standardWifiEntry) {
                    standardWifiEntry.mIsUserShareable = contains;
                }
                this.mSuggestedWifiEntryCache.add(standardWifiEntry);
            }
        }
        ((ArrayList) this.mSuggestedWifiEntryCache).removeIf(new WifiPickerTracker$$ExternalSyntheticLambda0(9));
    }

    public final void updateWifiConfigurations(List list) {
        Preconditions.checkNotNull(list, "Config list should not be null!");
        ((ArrayMap) this.mStandardWifiConfigCache).clear();
        ((ArrayMap) this.mSuggestedConfigCache).clear();
        this.mNetworkRequestConfigCache.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) it.next();
            if (!wifiConfiguration.carrierMerged) {
                StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey = new StandardWifiEntry.StandardWifiEntryKey(wifiConfiguration, true);
                if (wifiConfiguration.isPasspoint()) {
                    this.mPasspointWifiConfigCache.put(wifiConfiguration.networkId, wifiConfiguration);
                } else if (wifiConfiguration.fromWifiNetworkSuggestion) {
                    if (!((ArrayMap) this.mSuggestedConfigCache).containsKey(standardWifiEntryKey)) {
                        ((ArrayMap) this.mSuggestedConfigCache).put(standardWifiEntryKey, new ArrayList());
                    }
                    ((List) ((ArrayMap) this.mSuggestedConfigCache).get(standardWifiEntryKey)).add(wifiConfiguration);
                } else if (wifiConfiguration.fromWifiNetworkSpecifier) {
                    if (!this.mNetworkRequestConfigCache.containsKey(standardWifiEntryKey)) {
                        this.mNetworkRequestConfigCache.put(standardWifiEntryKey, new ArrayList());
                    }
                    ((List) this.mNetworkRequestConfigCache.get(standardWifiEntryKey)).add(wifiConfiguration);
                } else {
                    if (!((ArrayMap) this.mStandardWifiConfigCache).containsKey(standardWifiEntryKey)) {
                        ((ArrayMap) this.mStandardWifiConfigCache).put(standardWifiEntryKey, new ArrayList());
                    }
                    ((List) ((ArrayMap) this.mStandardWifiConfigCache).get(standardWifiEntryKey)).add(wifiConfiguration);
                }
            }
        }
        ((ArrayMap) this.mStandardWifiConfigCache).values().stream().flatMap(new WifiPickerTracker$$ExternalSyntheticLambda1(1)).filter(new WifiPickerTracker$$ExternalSyntheticLambda0(1)).map(new WifiPickerTracker$$ExternalSyntheticLambda1(2)).distinct().count();
        int i = 0;
        ((ArrayList) this.mStandardWifiEntryCache).forEach(new WifiPickerTracker$$ExternalSyntheticLambda14(i, this));
        ((ArrayList) this.mSuggestedWifiEntryCache).removeIf(new WifiPickerTracker$$ExternalSyntheticLambda15(this, i));
        ScanResultUpdater scanResultUpdater = this.mScanResultUpdater;
        updateSuggestedWifiEntryScans(scanResultUpdater.getScanResults(scanResultUpdater.mMaxScanAgeMillis));
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry != null) {
            networkRequestEntry.updateConfig((List) this.mNetworkRequestConfigCache.get(networkRequestEntry.mKey));
        }
    }

    public final void updateWifiConfigurationsInternal() {
        if (this.mContext.checkSelfPermission("android.permission.READ_WIFI_CREDENTIAL") == 0) {
            updateWifiConfigurations(this.mWifiManager.getPrivilegedConfiguredNetworks());
        } else {
            updateWifiConfigurations(this.mWifiManager.getConfiguredNetworks());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x02f7, code lost:
    
        if (r2.isPrimaryNetwork() != false) goto L93;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateWifiEntries(int r12) {
        /*
            Method dump skipped, instructions count: 773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wifitrackerlib.WifiPickerTracker.updateWifiEntries(int):void");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface WifiPickerTrackerCallback {
        void onWifiEntriesChanged();

        default void onWifiEntriesChanged(int i) {
            onWifiEntriesChanged();
        }

        void onWifiStateChanged();

        default void onScanRequested() {
        }
    }
}
