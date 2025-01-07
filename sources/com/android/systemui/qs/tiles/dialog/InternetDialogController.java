package com.android.systemui.qs.tiles.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.telephony.CarrierConfigManager;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.android.settingslib.mobile.MobileMappings;
import com.android.settingslib.wifi.WifiUtils;
import com.android.settingslib.wifi.dpp.WifiDppIntentHelper;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.ToastPlugin;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.AccessPointControllerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.toast.SystemUIToast;
import com.android.systemui.toast.ToastFactory;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wifitrackerlib.HotspotNetworkEntry;
import com.android.wifitrackerlib.MergedCarrierEntry;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetDialogController implements AccessPointController.AccessPointCallback {
    static final long SHORT_DURATION_TIMEOUT = 4000;
    static final float TOAST_PARAMS_HORIZONTAL_WEIGHT = 1.0f;
    static final float TOAST_PARAMS_VERTICAL_WEIGHT = 1.0f;
    public final AccessPointController mAccessPointController;
    protected ActivityStarter mActivityStarter;
    public final BroadcastDispatcher mBroadcastDispatcher;
    InternetDialogCallback mCallback;
    protected boolean mCanConfigWifi;
    public final CarrierConfigTracker mCarrierConfigTracker;
    protected boolean mCarrierNetworkChangeMode;
    protected ConnectedWifiInternetMonitor mConnectedWifiInternetMonitor;
    public final IntentFilter mConnectionStateFilter;
    public final ConnectivityManager mConnectivityManager;
    public final DataConnectivityListener mConnectivityManagerNetworkCallback;
    public final Context mContext;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final Executor mExecutor;
    public final FeatureFlags mFeatureFlags;
    public final GlobalSettings mGlobalSettings;
    public final Handler mHandler;
    public boolean mHasActiveSubIdOnDds;
    public boolean mHasWifiEntries;
    protected KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LocationController mLocationController;
    protected SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener;
    public final SignalDrawable mSecondarySignalDrawable;
    public final SignalDrawable mSignalDrawable;
    public final SubscriptionManager mSubscriptionManager;
    public TelephonyManager mTelephonyManager;
    public final ToastFactory mToastFactory;
    protected WifiUtils.InternetIconInjector mWifiIconInjector;
    public final WifiManager mWifiManager;
    public final WifiStateWorker mWifiStateWorker;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final Handler mWorkerHandler;
    public static final Drawable EMPTY_DRAWABLE = new ColorDrawable(0);
    public static final int SUBTITLE_TEXT_WIFI_IS_OFF = R.string.wifi_is_off;
    public static final int SUBTITLE_TEXT_TAP_A_NETWORK_TO_CONNECT = R.string.tap_a_network_to_connect;
    public static final int SUBTITLE_TEXT_UNLOCK_TO_VIEW_NETWORKS = R.string.unlock_to_view_networks;
    public static final int SUBTITLE_TEXT_SEARCHING_FOR_NETWORKS = R.string.wifi_empty_list_wifi_on;
    public static final int SUBTITLE_TEXT_NON_CARRIER_NETWORK_UNAVAILABLE = R.string.non_carrier_network_unavailable;
    public static final int SUBTITLE_TEXT_ALL_CARRIER_NETWORK_UNAVAILABLE = R.string.all_network_unavailable;
    public static final boolean DEBUG = Log.isLoggable("InternetDialogController", 3);
    public static final TelephonyDisplayInfo DEFAULT_TELEPHONY_DISPLAY_INFO = new TelephonyDisplayInfo(0, 0, false);
    final Map mSubIdTelephonyDisplayInfoMap = new HashMap();
    final Map mSubIdTelephonyManagerMap = new HashMap();
    final Map mSubIdTelephonyCallbackMap = new HashMap();
    public MobileMappings.Config mConfig = null;
    public int mDefaultDataSubId = -1;
    protected boolean mHasEthernet = false;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogController.1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onRefreshCarrierInfo() {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onSimStateChanged(int i, int i2, int i3) {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }
    };
    public final AnonymousClass2 mConnectionStateReceiver = new BroadcastReceiver() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogController.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED".equals(action)) {
                if ("android.net.wifi.supplicant.CONNECTION_CHANGE".equals(action)) {
                    InternetDialogController.m851$$Nest$mupdateListener(InternetDialogController.this);
                }
            } else {
                if (InternetDialogController.DEBUG) {
                    Log.d("InternetDialogController", "ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
                }
                InternetDialogController.this.mConfig = MobileMappings.Config.readConfig(context);
                InternetDialogController.this.refreshHasActiveSubIdOnDds();
                InternetDialogController.m851$$Nest$mupdateListener(InternetDialogController.this);
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.dialog.InternetDialogController$1DisplayInfo, reason: invalid class name */
    public final class C1DisplayInfo {
        public CharSequence originalName;
        public SubscriptionInfo subscriptionInfo;
        public CharSequence uniqueName;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class ConnectedWifiInternetMonitor implements WifiEntry.WifiEntryCallback {
        public WifiEntry mWifiEntry;

        public ConnectedWifiInternetMonitor() {
        }

        @Override // com.android.wifitrackerlib.WifiEntry.WifiEntryCallback
        public final void onUpdated() {
            WifiEntry wifiEntry = this.mWifiEntry;
            if (wifiEntry == null) {
                return;
            }
            if (wifiEntry.getConnectedState() != 2) {
                WifiEntry wifiEntry2 = this.mWifiEntry;
                if (wifiEntry2 == null) {
                    return;
                }
                synchronized (wifiEntry2) {
                    wifiEntry2.mListener = null;
                }
                this.mWifiEntry = null;
                return;
            }
            if (wifiEntry.isDefaultNetwork() && wifiEntry.hasInternetAccess()) {
                WifiEntry wifiEntry3 = this.mWifiEntry;
                if (wifiEntry3 != null) {
                    synchronized (wifiEntry3) {
                        wifiEntry3.mListener = null;
                    }
                    this.mWifiEntry = null;
                }
                Drawable drawable = InternetDialogController.EMPTY_DRAWABLE;
                InternetDialogController.this.scanWifiAccessPoints();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DataConnectivityListener extends ConnectivityManager.NetworkCallback {
        public DataConnectivityListener() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            InternetDialogController.this.mHasEthernet = networkCapabilities.hasTransport(3);
            InternetDialogController internetDialogController = InternetDialogController.this;
            if (internetDialogController.mCanConfigWifi && (internetDialogController.mHasEthernet || networkCapabilities.hasTransport(1))) {
                InternetDialogController.this.scanWifiAccessPoints();
            }
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            InternetDialogController internetDialogController = InternetDialogController.this;
            internetDialogController.mHasEthernet = false;
            InternetDialogCallback internetDialogCallback = internetDialogController.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface InternetDialogCallback {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternetOnSubscriptionChangedListener extends SubscriptionManager.OnSubscriptionsChangedListener {
        public InternetOnSubscriptionChangedListener() {
        }

        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public final void onSubscriptionsChanged() {
            InternetDialogController.this.refreshHasActiveSubIdOnDds();
            InternetDialogController.m851$$Nest$mupdateListener(InternetDialogController.this);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternetTelephonyCallback extends TelephonyCallback implements TelephonyCallback.DataConnectionStateListener, TelephonyCallback.DisplayInfoListener, TelephonyCallback.ServiceStateListener, TelephonyCallback.SignalStrengthsListener, TelephonyCallback.UserMobileDataStateListener, TelephonyCallback.CarrierNetworkListener {
        public final int mSubId;

        public InternetTelephonyCallback(int i) {
            this.mSubId = i;
        }

        @Override // android.telephony.TelephonyCallback.CarrierNetworkListener
        public final void onCarrierNetworkChange(boolean z) {
            InternetDialogController internetDialogController = InternetDialogController.this;
            internetDialogController.mCarrierNetworkChangeMode = z;
            InternetDialogCallback internetDialogCallback = internetDialogController.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.telephony.TelephonyCallback.DataConnectionStateListener
        public final void onDataConnectionStateChanged(int i, int i2) {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.telephony.TelephonyCallback.DisplayInfoListener
        public final void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
            InternetDialogController.this.mSubIdTelephonyDisplayInfoMap.put(Integer.valueOf(this.mSubId), telephonyDisplayInfo);
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.telephony.TelephonyCallback.ServiceStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.telephony.TelephonyCallback.SignalStrengthsListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }

        @Override // android.telephony.TelephonyCallback.UserMobileDataStateListener
        public final void onUserMobileDataStateChanged(boolean z) {
            InternetDialogCallback internetDialogCallback = InternetDialogController.this.mCallback;
            if (internetDialogCallback != null) {
                ((InternetDialogDelegate) internetDialogCallback).updateDialog(true);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WifiEntryConnectCallback {
        public final ActivityStarter mActivityStarter;
        public final InternetDialogController mInternetDialogController;
        public final WifiEntry mWifiEntry;

        public WifiEntryConnectCallback(ActivityStarter activityStarter, WifiEntry wifiEntry, InternetDialogController internetDialogController) {
            this.mActivityStarter = activityStarter;
            this.mWifiEntry = wifiEntry;
            this.mInternetDialogController = internetDialogController;
        }

        public final void onConnectResult(int i) {
            boolean z = InternetDialogController.DEBUG;
            if (z) {
                ExifInterface$$ExternalSyntheticOutline0.m("onConnectResult ", "InternetDialogController", i);
            }
            if (i == 1) {
                String key = this.mWifiEntry.getKey();
                Intent intent = new Intent("com.android.settings.WIFI_DIALOG");
                intent.putExtra("key_chosen_wifientry_key", key);
                intent.putExtra("connect_for_caller", true);
                intent.addFlags(268435456);
                this.mActivityStarter.startActivity(intent, false);
                return;
            }
            if (i == 2) {
                this.mInternetDialogController.makeOverlayToast(R.string.wifi_failed_connect_message);
            } else if (z) {
                ExifInterface$$ExternalSyntheticOutline0.m("connect failure reason=", "InternetDialogController", i);
            }
        }
    }

    /* renamed from: -$$Nest$mupdateListener, reason: not valid java name */
    public static void m851$$Nest$mupdateListener(InternetDialogController internetDialogController) {
        int defaultDataSubscriptionId = internetDialogController.getDefaultDataSubscriptionId();
        int i = internetDialogController.mDefaultDataSubId;
        int defaultDataSubscriptionId2 = internetDialogController.getDefaultDataSubscriptionId();
        boolean z = DEBUG;
        if (i == defaultDataSubscriptionId2) {
            if (z) {
                Log.d("InternetDialogController", "DDS: no change");
                return;
            }
            return;
        }
        if (z) {
            ExifInterface$$ExternalSyntheticOutline0.m("DDS: defaultDataSubId:", "InternetDialogController", defaultDataSubscriptionId);
        }
        if (SubscriptionManager.isUsableSubscriptionId(defaultDataSubscriptionId)) {
            TelephonyCallback telephonyCallback = (TelephonyCallback) internetDialogController.mSubIdTelephonyCallbackMap.get(Integer.valueOf(internetDialogController.mDefaultDataSubId));
            if (telephonyCallback != null) {
                internetDialogController.mTelephonyManager.unregisterTelephonyCallback(telephonyCallback);
            } else if (z) {
                Log.e("InternetDialogController", "Unexpected null telephony call back for Sub " + internetDialogController.mDefaultDataSubId);
            }
            internetDialogController.mSubIdTelephonyCallbackMap.remove(Integer.valueOf(internetDialogController.mDefaultDataSubId));
            internetDialogController.mSubIdTelephonyDisplayInfoMap.remove(Integer.valueOf(internetDialogController.mDefaultDataSubId));
            internetDialogController.mSubIdTelephonyManagerMap.remove(Integer.valueOf(internetDialogController.mDefaultDataSubId));
            internetDialogController.mTelephonyManager = internetDialogController.mTelephonyManager.createForSubscriptionId(defaultDataSubscriptionId);
            internetDialogController.mSubIdTelephonyManagerMap.put(Integer.valueOf(defaultDataSubscriptionId), internetDialogController.mTelephonyManager);
            internetDialogController.registerInternetTelephonyCallback(internetDialogController.mTelephonyManager, defaultDataSubscriptionId);
            InternetDialogDelegate internetDialogDelegate = (InternetDialogDelegate) internetDialogController.mCallback;
            internetDialogDelegate.mDefaultDataSubId = defaultDataSubscriptionId;
            internetDialogDelegate.updateDialog(true);
        }
        internetDialogController.mDefaultDataSubId = defaultDataSubscriptionId;
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.systemui.qs.tiles.dialog.InternetDialogController$2] */
    public InternetDialogController(Context context, ActivityStarter activityStarter, AccessPointController accessPointController, SubscriptionManager subscriptionManager, TelephonyManager telephonyManager, WifiManager wifiManager, ConnectivityManager connectivityManager, Handler handler, Executor executor, BroadcastDispatcher broadcastDispatcher, KeyguardUpdateMonitor keyguardUpdateMonitor, GlobalSettings globalSettings, KeyguardStateController keyguardStateController, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, ToastFactory toastFactory, Handler handler2, CarrierConfigTracker carrierConfigTracker, LocationController locationController, DialogTransitionAnimator dialogTransitionAnimator, WifiStateWorker wifiStateWorker, FeatureFlags featureFlags) {
        if (DEBUG) {
            Log.d("InternetDialogController", "Init InternetDialogController");
        }
        this.mHandler = handler;
        this.mWorkerHandler = handler2;
        this.mExecutor = executor;
        this.mContext = context;
        this.mGlobalSettings = globalSettings;
        this.mWifiManager = wifiManager;
        this.mTelephonyManager = telephonyManager;
        this.mConnectivityManager = connectivityManager;
        this.mSubscriptionManager = subscriptionManager;
        this.mCarrierConfigTracker = carrierConfigTracker;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateController = keyguardStateController;
        IntentFilter intentFilter = new IntentFilter();
        this.mConnectionStateFilter = intentFilter;
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
        this.mActivityStarter = activityStarter;
        this.mAccessPointController = accessPointController;
        this.mWifiIconInjector = new WifiUtils.InternetIconInjector(context);
        this.mConnectivityManagerNetworkCallback = new DataConnectivityListener();
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mToastFactory = toastFactory;
        this.mSignalDrawable = new SignalDrawable(context);
        this.mSecondarySignalDrawable = new SignalDrawable(context);
        this.mLocationController = locationController;
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mConnectedWifiInternetMonitor = new ConnectedWifiInternetMonitor();
        this.mWifiStateWorker = wifiStateWorker;
        this.mFeatureFlags = featureFlags;
    }

    public final boolean activeNetworkIsCellular() {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager == null) {
            if (DEBUG) {
                Log.d("InternetDialogController", "ConnectivityManager is null, can not check active network.");
            }
            return false;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(0);
    }

    public final int getActiveAutoSwitchNonDdsSubId() {
        SubscriptionInfo activeSubscriptionInfo;
        if (!((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.QS_SECONDARY_DATA_SUB_INFO) || (activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(SubscriptionManager.getActiveDataSubscriptionId())) == null || activeSubscriptionInfo.getSubscriptionId() == this.mDefaultDataSubId || activeSubscriptionInfo.isOpportunistic()) {
            return -1;
        }
        int subscriptionId = activeSubscriptionInfo.getSubscriptionId();
        if (this.mSubIdTelephonyManagerMap.get(Integer.valueOf(subscriptionId)) == null) {
            TelephonyManager createForSubscriptionId = this.mTelephonyManager.createForSubscriptionId(subscriptionId);
            registerInternetTelephonyCallback(createForSubscriptionId, subscriptionId);
            this.mSubIdTelephonyManagerMap.put(Integer.valueOf(subscriptionId), createForSubscriptionId);
        }
        return subscriptionId;
    }

    public final Intent getConfiguratorQrCodeGeneratorIntentOrNull(WifiEntry wifiEntry) {
        String str;
        String str2;
        if (!((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.SHARE_WIFI_QS_BUTTON) || wifiEntry == null || this.mWifiManager == null || !wifiEntry.canShare() || wifiEntry.getWifiConfiguration() == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.WIFI_DPP_CONFIGURATOR_AUTH_QR_CODE_GENERATOR");
        intent.addFlags(335544320);
        WifiManager wifiManager = this.mWifiManager;
        WifiConfiguration wifiConfiguration = wifiEntry.getWifiConfiguration();
        String removeFirstAndLastDoubleQuotes = WifiDppIntentHelper.removeFirstAndLastDoubleQuotes(wifiConfiguration.SSID);
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            str = "SAE";
        } else {
            if (!wifiConfiguration.allowedKeyManagement.get(9)) {
                if (wifiConfiguration.allowedKeyManagement.get(1) || wifiConfiguration.allowedKeyManagement.get(4)) {
                    str = "WPA";
                } else if (wifiConfiguration.wepKeys[0] != null) {
                    str = "WEP";
                }
            }
            str = "nopass";
        }
        Iterator it = wifiManager.getPrivilegedConfiguredNetworks().iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = wifiConfiguration.preSharedKey;
                break;
            }
            WifiConfiguration wifiConfiguration2 = (WifiConfiguration) it.next();
            if (wifiConfiguration2.networkId == wifiConfiguration.networkId) {
                str2 = (wifiConfiguration.allowedKeyManagement.get(0) && wifiConfiguration.allowedAuthAlgorithms.get(1)) ? wifiConfiguration2.wepKeys[wifiConfiguration2.wepTxKeyIndex] : wifiConfiguration2.preSharedKey;
            }
        }
        String removeFirstAndLastDoubleQuotes2 = WifiDppIntentHelper.removeFirstAndLastDoubleQuotes(str2);
        if (!TextUtils.isEmpty(removeFirstAndLastDoubleQuotes)) {
            intent.putExtra("ssid", removeFirstAndLastDoubleQuotes);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("security", str);
        }
        if (!TextUtils.isEmpty(removeFirstAndLastDoubleQuotes2)) {
            intent.putExtra("preSharedKey", removeFirstAndLastDoubleQuotes2);
        }
        intent.putExtra("hiddenSsid", wifiConfiguration.hiddenSSID);
        return intent;
    }

    public int getDefaultDataSubscriptionId() {
        return SubscriptionManager.getDefaultDataSubscriptionId();
    }

    public Intent getSettingsIntent() {
        return new Intent("android.settings.NETWORK_PROVIDER_SETTINGS").addFlags(268435456);
    }

    public final Drawable getSignalStrengthDrawableWithLevel(int i, boolean z) {
        int state;
        SignalStrength signalStrength = ((TelephonyManager) this.mSubIdTelephonyManagerMap.getOrDefault(Integer.valueOf(i), this.mTelephonyManager)).getSignalStrength();
        int level = signalStrength == null ? 0 : signalStrength.getLevel();
        int i2 = 5;
        if (z) {
            MergedCarrierEntry mergedCarrierEntry = ((AccessPointControllerImpl) this.mAccessPointController).getMergedCarrierEntry();
            if (mergedCarrierEntry == null) {
                level = 0;
            } else {
                int level2 = mergedCarrierEntry.getLevel();
                if (level2 < 0) {
                    level2 = 0;
                }
                level = level2;
            }
        } else if (this.mSubscriptionManager != null) {
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
            PersistableBundle configForSubId = carrierConfigManager != null ? carrierConfigManager.getConfigForSubId(i) : null;
            if (configForSubId != null && configForSubId.getBoolean("inflate_signal_strength_bool", false)) {
                level++;
                i2 = 6;
            }
        }
        Context context = this.mContext;
        boolean z2 = !isMobileDataEnabled();
        boolean z3 = i == this.mDefaultDataSubId;
        if (this.mCarrierNetworkChangeMode) {
            int i3 = SignalDrawable.$r8$clinit;
            state = (i2 << 8) | 196608;
        } else {
            state = SignalDrawable.getState(level, i2, z2);
        }
        SignalDrawable signalDrawable = this.mSecondarySignalDrawable;
        SignalDrawable signalDrawable2 = this.mSignalDrawable;
        if (z3) {
            signalDrawable2.setLevel(state);
        } else {
            signalDrawable.setLevel(state);
        }
        Drawable[] drawableArr = new Drawable[2];
        drawableArr[0] = EMPTY_DRAWABLE;
        if (z3) {
            signalDrawable = signalDrawable2;
        }
        drawableArr[1] = signalDrawable;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.signal_strength_icon_size);
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        layerDrawable.setLayerGravity(0, 51);
        layerDrawable.setLayerGravity(1, 85);
        layerDrawable.setLayerSize(1, dimensionPixelSize, dimensionPixelSize);
        layerDrawable.setTintList(Utils.getColorAttr(android.R.attr.textColorTertiary, context));
        return layerDrawable;
    }

    public final Drawable getWifiDrawable(WifiEntry wifiEntry) {
        int i = 0;
        if (!(wifiEntry instanceof HotspotNetworkEntry)) {
            if (wifiEntry.getLevel() == -1) {
                return null;
            }
            WifiUtils.InternetIconInjector internetIconInjector = this.mWifiIconInjector;
            boolean shouldShowXLevelIcon = wifiEntry.shouldShowXLevelIcon();
            int level = wifiEntry.getLevel();
            Context context = internetIconInjector.context;
            if (level < 0) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Wi-Fi level is out of range! level:", "WifiUtils", level);
            } else if (level >= 5) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Wi-Fi level is out of range! level:", "WifiUtils", level);
                i = 4;
            } else {
                i = level;
            }
            return context.getDrawable(shouldShowXLevelIcon ? WifiUtils.NO_INTERNET_WIFI_PIE[i] : WifiUtils.WIFI_PIE[i]);
        }
        HotspotNetworkEntry hotspotNetworkEntry = (HotspotNetworkEntry) wifiEntry;
        synchronized (hotspotNetworkEntry) {
            HotspotNetwork hotspotNetwork = hotspotNetworkEntry.mHotspotNetworkData;
            if (hotspotNetwork != null) {
                i = hotspotNetwork.getNetworkProviderInfo().getDeviceType();
            }
        }
        Context context2 = this.mContext;
        int i2 = R.drawable.ic_hotspot_phone;
        if (i != 1) {
            if (i == 2) {
                i2 = R.drawable.ic_hotspot_tablet;
            } else if (i == 3) {
                i2 = R.drawable.ic_hotspot_laptop;
            } else if (i == 4) {
                i2 = R.drawable.ic_hotspot_watch;
            } else if (i == 5) {
                i2 = R.drawable.ic_hotspot_auto;
            }
        }
        return context2.getDrawable(i2);
    }

    public final boolean isAirplaneModeEnabled() {
        return this.mGlobalSettings.getInt(0, "airplane_mode_on") != 0;
    }

    public final boolean isCarrierNetworkActive() {
        MergedCarrierEntry mergedCarrierEntry = ((AccessPointControllerImpl) this.mAccessPointController).getMergedCarrierEntry();
        return mergedCarrierEntry != null && mergedCarrierEntry.isDefaultNetwork();
    }

    public final boolean isDataStateInService(int i) {
        ServiceState serviceState = ((TelephonyManager) this.mSubIdTelephonyManagerMap.getOrDefault(Integer.valueOf(i), this.mTelephonyManager)).getServiceState();
        NetworkRegistrationInfo networkRegistrationInfo = serviceState == null ? null : serviceState.getNetworkRegistrationInfo(2, 1);
        if (networkRegistrationInfo == null) {
            return false;
        }
        return networkRegistrationInfo.isRegistered();
    }

    public final boolean isMobileDataEnabled() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        return telephonyManager != null && telephonyManager.isDataEnabled();
    }

    public final boolean isVoiceStateInService(int i) {
        if (this.mTelephonyManager != null) {
            ServiceState serviceState = ((TelephonyManager) this.mSubIdTelephonyManagerMap.getOrDefault(Integer.valueOf(i), this.mTelephonyManager)).getServiceState();
            return serviceState != null && serviceState.getState() == 0;
        }
        if (DEBUG) {
            Log.d("InternetDialogController", "TelephonyManager is null, can not detect voice state.");
        }
        return false;
    }

    public final void launchWifiDetailsSetting(View view, String str) {
        Intent intent;
        if (TextUtils.isEmpty(str)) {
            if (DEBUG) {
                Log.d("InternetDialogController", "connected entry's key is empty");
            }
            intent = null;
        } else {
            Intent intent2 = new Intent("android.settings.WIFI_DETAILS_SETTINGS");
            Bundle bundle = new Bundle();
            bundle.putString("key_chosen_wifientry_key", str);
            intent2.putExtra(":settings:show_fragment_args", bundle);
            intent = intent2;
        }
        if (intent != null) {
            startActivity(intent, view);
        }
    }

    public final void makeOverlayToast(int i) {
        Resources resources = this.mContext.getResources();
        Context context = this.mContext;
        String string = resources.getString(i);
        String packageName = this.mContext.getPackageName();
        int myUserId = UserHandle.myUserId();
        int i2 = resources.getConfiguration().orientation;
        ToastFactory toastFactory = this.mToastFactory;
        toastFactory.getClass();
        LayoutInflater from = LayoutInflater.from(context);
        ToastPlugin toastPlugin = toastFactory.mPlugin;
        final SystemUIToast systemUIToast = toastPlugin != null ? new SystemUIToast(from, context, string, toastPlugin.createToast(string, packageName, myUserId), packageName, myUserId, i2) : new SystemUIToast(from, context, string, null, packageName, myUserId, i2);
        final View view = systemUIToast.mToastView;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.type = 2017;
        layoutParams.flags = 152;
        layoutParams.y = systemUIToast.getYOffset().intValue();
        int absoluteGravity = Gravity.getAbsoluteGravity(systemUIToast.getGravity().intValue(), resources.getConfiguration().getLayoutDirection());
        layoutParams.gravity = absoluteGravity;
        if ((absoluteGravity & 7) == 7) {
            layoutParams.horizontalWeight = 1.0f;
        }
        if ((absoluteGravity & 112) == 112) {
            layoutParams.verticalWeight = 1.0f;
        }
        this.mWindowManager.addView(view, layoutParams);
        Animator animator = systemUIToast.mInAnimator;
        if (animator != null) {
            animator.start();
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogController.3
            @Override // java.lang.Runnable
            public final void run() {
                Animator animator2 = systemUIToast.mOutAnimator;
                if (animator2 != null) {
                    animator2.start();
                    animator2.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogController.3.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator3) {
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            InternetDialogController.this.mWindowManager.removeViewImmediate(view);
                        }
                    });
                }
            }
        }, SHORT_DURATION_TIMEOUT);
    }

    public final void onAccessPointsChanged(List list) {
        final WifiEntry wifiEntry;
        final ArrayList arrayList;
        if (this.mCanConfigWifi) {
            int size = list == null ? 0 : list.size();
            final boolean z = size > 3;
            WifiEntry wifiEntry2 = null;
            if (size > 0) {
                ArrayList arrayList2 = new ArrayList();
                if (z) {
                    size = 3;
                }
                ConnectedWifiInternetMonitor connectedWifiInternetMonitor = this.mConnectedWifiInternetMonitor;
                WifiEntry wifiEntry3 = connectedWifiInternetMonitor.mWifiEntry;
                if (wifiEntry3 != null) {
                    synchronized (wifiEntry3) {
                        wifiEntry3.mListener = null;
                    }
                    connectedWifiInternetMonitor.mWifiEntry = null;
                }
                for (int i = 0; i < size; i++) {
                    WifiEntry wifiEntry4 = (WifiEntry) list.get(i);
                    ConnectedWifiInternetMonitor connectedWifiInternetMonitor2 = this.mConnectedWifiInternetMonitor;
                    if (wifiEntry4 == null) {
                        connectedWifiInternetMonitor2.getClass();
                    } else if (connectedWifiInternetMonitor2.mWifiEntry == null && wifiEntry4.getConnectedState() == 2 && (!wifiEntry4.isDefaultNetwork() || !wifiEntry4.hasInternetAccess())) {
                        connectedWifiInternetMonitor2.mWifiEntry = wifiEntry4;
                        synchronized (wifiEntry4) {
                            wifiEntry4.mListener = connectedWifiInternetMonitor2;
                        }
                    }
                    if (wifiEntry2 == null && wifiEntry4.isDefaultNetwork() && wifiEntry4.hasInternetAccess()) {
                        wifiEntry2 = wifiEntry4;
                    } else {
                        arrayList2.add(wifiEntry4);
                    }
                }
                this.mHasWifiEntries = true;
                wifiEntry = wifiEntry2;
                arrayList = arrayList2;
            } else {
                this.mHasWifiEntries = false;
                wifiEntry = null;
                arrayList = null;
            }
            InternetDialogCallback internetDialogCallback = this.mCallback;
            if (internetDialogCallback != null) {
                final InternetDialogDelegate internetDialogDelegate = (InternetDialogDelegate) internetDialogCallback;
                final boolean z2 = internetDialogDelegate.mMobileNetworkLayout.getVisibility() == 0 && internetDialogDelegate.mInternetDialogController.isAirplaneModeEnabled();
                internetDialogDelegate.mHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogDelegate$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        InternetDialogDelegate internetDialogDelegate2 = InternetDialogDelegate.this;
                        WifiEntry wifiEntry5 = wifiEntry;
                        List list2 = arrayList;
                        boolean z3 = z;
                        boolean z4 = z2;
                        internetDialogDelegate2.mConnectedWifiEntry = wifiEntry5;
                        internetDialogDelegate2.mWifiEntriesCount = list2 == null ? 0 : list2.size();
                        internetDialogDelegate2.mHasMoreWifiEntries = z3;
                        internetDialogDelegate2.updateDialog(z4);
                        InternetAdapter internetAdapter = internetDialogDelegate2.mAdapter;
                        int i2 = internetDialogDelegate2.mWifiEntriesCount;
                        internetAdapter.mWifiEntries = list2;
                        int i3 = internetAdapter.mMaxEntriesCount;
                        if (i2 >= i3) {
                            i2 = i3;
                        }
                        internetAdapter.mWifiEntriesCount = i2;
                        internetAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    public final void onWifiScan(boolean z) {
        if (this.mWifiStateWorker.isWifiEnabled() && this.mKeyguardStateController.isUnlocked()) {
            ((InternetDialogDelegate) this.mCallback).setProgressBarVisible(z);
        } else {
            ((InternetDialogDelegate) this.mCallback).setProgressBarVisible(false);
        }
    }

    public final void refreshHasActiveSubIdOnDds() {
        if (this.mSubscriptionManager == null) {
            this.mHasActiveSubIdOnDds = false;
            Log.e("InternetDialogController", "SubscriptionManager is null, set mHasActiveSubId = false");
            return;
        }
        int defaultDataSubscriptionId = getDefaultDataSubscriptionId();
        if (defaultDataSubscriptionId == -1) {
            this.mHasActiveSubIdOnDds = false;
            Log.d("InternetDialogController", "DDS is INVALID_SUBSCRIPTION_ID");
            return;
        }
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(defaultDataSubscriptionId);
        if (activeSubscriptionInfo == null) {
            this.mHasActiveSubIdOnDds = false;
            Log.e("InternetDialogController", "Can't get DDS subscriptionInfo");
        } else if (activeSubscriptionInfo.isOnlyNonTerrestrialNetwork()) {
            this.mHasActiveSubIdOnDds = false;
            Log.d("InternetDialogController", "This is NTN, so do not show mobile data");
        } else {
            this.mHasActiveSubIdOnDds = (activeSubscriptionInfo.isEmbedded() && activeSubscriptionInfo.getProfileClass() == 1) ? false : true;
            Log.i("InternetDialogController", "mHasActiveSubId:" + this.mHasActiveSubIdOnDds);
        }
    }

    public final void registerInternetTelephonyCallback(TelephonyManager telephonyManager, int i) {
        if (this.mSubIdTelephonyCallbackMap.containsKey(Integer.valueOf(i))) {
            return;
        }
        InternetTelephonyCallback internetTelephonyCallback = new InternetTelephonyCallback(i);
        this.mSubIdTelephonyCallbackMap.put(Integer.valueOf(i), internetTelephonyCallback);
        telephonyManager.registerTelephonyCallback(this.mExecutor, internetTelephonyCallback);
    }

    public final void scanWifiAccessPoints() {
        if (this.mCanConfigWifi) {
            ((AccessPointControllerImpl) this.mAccessPointController).scanForAccessPoints();
        }
    }

    public final void setMobileDataEnabled(Context context, final int i, final boolean z) {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        boolean z2 = DEBUG;
        if (telephonyManager == null) {
            if (z2) {
                Log.d("InternetDialogController", "TelephonyManager is null, can not set mobile data.");
            }
        } else if (this.mSubscriptionManager == null) {
            if (z2) {
                Log.d("InternetDialogController", "SubscriptionManager is null, can not set mobile data.");
            }
        } else {
            telephonyManager.setDataEnabledForReason(0, z);
            this.mWorkerHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.dialog.InternetDialogController$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    boolean z3;
                    InternetDialogController internetDialogController = InternetDialogController.this;
                    int i2 = i;
                    boolean z4 = z;
                    CarrierConfigTracker carrierConfigTracker = internetDialogController.mCarrierConfigTracker;
                    synchronized (carrierConfigTracker.mCarrierProvisionsWifiMergedNetworks) {
                        try {
                            if (carrierConfigTracker.mCarrierProvisionsWifiMergedNetworks.indexOfKey(i2) >= 0) {
                                z3 = carrierConfigTracker.mCarrierProvisionsWifiMergedNetworks.get(i2);
                            } else {
                                if (!carrierConfigTracker.mDefaultCarrierProvisionsWifiMergedNetworksLoaded) {
                                    carrierConfigTracker.mDefaultCarrierProvisionsWifiMergedNetworks = CarrierConfigManager.getDefaultConfig().getBoolean("carrier_provisions_wifi_merged_networks_bool");
                                    carrierConfigTracker.mDefaultCarrierProvisionsWifiMergedNetworksLoaded = true;
                                }
                                z3 = carrierConfigTracker.mDefaultCarrierProvisionsWifiMergedNetworks;
                            }
                        } finally {
                        }
                    }
                    if (z3) {
                        return;
                    }
                    MergedCarrierEntry mergedCarrierEntry = ((AccessPointControllerImpl) internetDialogController.mAccessPointController).getMergedCarrierEntry();
                    if (mergedCarrierEntry == null) {
                        if (InternetDialogController.DEBUG) {
                            Log.d("InternetDialogController", "MergedCarrierEntry is null, can not set the status.");
                        }
                    } else {
                        mergedCarrierEntry.mWifiManager.setCarrierNetworkOffloadEnabled(mergedCarrierEntry.mSubscriptionId, true, z4);
                        if (z4) {
                            return;
                        }
                        mergedCarrierEntry.mWifiManager.stopRestrictingAutoJoinToSubscriptionId();
                        mergedCarrierEntry.mWifiManager.startScan();
                    }
                }
            });
        }
    }

    public final void startActivity(Intent intent, View view) {
        InternetDialogCallback internetDialogCallback;
        DialogTransitionAnimator dialogTransitionAnimator = this.mDialogTransitionAnimator;
        dialogTransitionAnimator.getClass();
        DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view);
        if (createActivityTransitionController$default == null && (internetDialogCallback = this.mCallback) != null) {
            InternetDialogDelegate internetDialogDelegate = (InternetDialogDelegate) internetDialogCallback;
            if (InternetDialogDelegate.DEBUG) {
                Log.d("InternetDialog", "dismissDialog");
            }
            internetDialogDelegate.mInternetDialogManager.destroyDialog();
            SystemUIDialog systemUIDialog = internetDialogDelegate.mDialog;
            if (systemUIDialog != null) {
                systemUIDialog.dismiss();
                internetDialogDelegate.mDialog = null;
            }
        }
        this.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0, createActivityTransitionController$default);
    }
}
