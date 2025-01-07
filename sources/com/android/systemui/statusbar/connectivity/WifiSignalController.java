package com.android.systemui.statusbar.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.Html;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.internal.util.Preconditions;
import com.android.settingslib.SignalIcon$IconGroup;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.settingslib.graph.SignalDrawable;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.settingslib.wifi.WifiStatusTracker;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.BitSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiSignalController extends SignalController {
    public final Handler mBgHandler;
    public final SignalIcon$MobileIconGroup mCarrierMergedWifiIconGroup;
    public final boolean mHasMobileDataFeature;
    public final SignalIcon$IconGroup mUnmergedWifiIconGroup;
    public final WifiManager mWifiManager;
    public final WifiStatusTracker mWifiTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WifiTrafficStateCallback implements WifiManager.TrafficStateCallback {
        public WifiTrafficStateCallback() {
        }

        public final void onStateChanged(int i) {
            WifiSignalController.this.setActivity(i);
        }
    }

    public WifiSignalController(Context context, boolean z, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl, WifiManager wifiManager, WifiStatusTrackerFactory wifiStatusTrackerFactory, Handler handler) {
        super("WifiSignalController", context, 1, callbackHandler, networkControllerImpl);
        SignalIcon$IconGroup signalIcon$IconGroup = WifiIcons.UNMERGED_WIFI;
        this.mUnmergedWifiIconGroup = signalIcon$IconGroup;
        this.mCarrierMergedWifiIconGroup = TelephonyIcons.CARRIER_MERGED_WIFI;
        this.mBgHandler = handler;
        this.mWifiManager = wifiManager;
        WifiSignalController$$ExternalSyntheticLambda0 wifiSignalController$$ExternalSyntheticLambda0 = new WifiSignalController$$ExternalSyntheticLambda0(this, 0);
        wifiStatusTrackerFactory.getClass();
        WifiStatusTracker wifiStatusTracker = new WifiStatusTracker(wifiStatusTrackerFactory.mContext, wifiStatusTrackerFactory.mWifiManager, wifiStatusTrackerFactory.mNetworkScoreManager, wifiStatusTrackerFactory.mConnectivityManager, wifiSignalController$$ExternalSyntheticLambda0, wifiStatusTrackerFactory.mMainHandler, handler);
        this.mWifiTracker = wifiStatusTracker;
        wifiStatusTracker.mNetworkScoreManager.registerNetworkScoreCache(1, wifiStatusTracker.mWifiNetworkScoreCache, 1);
        wifiStatusTracker.mWifiNetworkScoreCache.registerListener(wifiStatusTracker.mCacheListener);
        ConnectivityManager connectivityManager = wifiStatusTracker.mConnectivityManager;
        NetworkRequest networkRequest = wifiStatusTracker.mNetworkRequest;
        Handler handler2 = wifiStatusTracker.mHandler;
        connectivityManager.registerNetworkCallback(networkRequest, wifiStatusTracker.mNetworkCallback, handler2);
        wifiStatusTracker.mConnectivityManager.registerDefaultNetworkCallback(wifiStatusTracker.mDefaultNetworkCallback, handler2);
        this.mHasMobileDataFeature = z;
        if (wifiManager != null) {
            wifiManager.registerTrafficStateCallback(context.getMainExecutor(), new WifiTrafficStateCallback());
        }
        WifiState wifiState = (WifiState) this.mCurrentState;
        ((WifiState) this.mLastState).iconGroup = signalIcon$IconGroup;
        wifiState.iconGroup = signalIcon$IconGroup;
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final ConnectivityState cleanState() {
        WifiState wifiState = new WifiState();
        wifiState.ssid = null;
        wifiState.isDefault = false;
        wifiState.statusLabel = null;
        wifiState.isCarrierMerged = false;
        wifiState.isDefaultConnectionValidated = false;
        wifiState.subId = 0;
        return wifiState;
    }

    public final void copyWifiStates() {
        Preconditions.checkState(this.mBgHandler.getLooper().isCurrentThread());
        ConnectivityState connectivityState = this.mCurrentState;
        WifiStatusTracker wifiStatusTracker = this.mWifiTracker;
        ((WifiState) connectivityState).enabled = wifiStatusTracker.enabled;
        ((WifiState) connectivityState).isDefault = wifiStatusTracker.isDefaultNetwork;
        ((WifiState) connectivityState).connected = wifiStatusTracker.connected;
        ((WifiState) connectivityState).ssid = wifiStatusTracker.ssid;
        ((WifiState) connectivityState).rssi = wifiStatusTracker.rssi;
        ((WifiState) connectivityState).level = wifiStatusTracker.level;
        ((WifiState) connectivityState).statusLabel = wifiStatusTracker.statusLabel;
        ((WifiState) connectivityState).isCarrierMerged = wifiStatusTracker.isCarrierMerged;
        ((WifiState) connectivityState).subId = wifiStatusTracker.subId;
        ((WifiState) connectivityState).iconGroup = ((WifiState) connectivityState).isCarrierMerged ? this.mCarrierMergedWifiIconGroup : this.mUnmergedWifiIconGroup;
    }

    public final void doInBackground(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        Handler handler = this.mBgHandler;
        if (currentThread != handler.getLooper().getThread()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final void dump(PrintWriter printWriter) {
        String[] strArr;
        super.dump(printWriter);
        WifiStatusTracker wifiStatusTracker = this.mWifiTracker;
        wifiStatusTracker.getClass();
        printWriter.println("  - WiFi Network History ------");
        int i = 0;
        int i2 = 0;
        while (true) {
            strArr = wifiStatusTracker.mHistory;
            if (i >= 32) {
                break;
            }
            if (strArr[i] != null) {
                i2++;
            }
            i++;
        }
        for (int i3 = wifiStatusTracker.mHistoryIndex + 31; i3 >= (wifiStatusTracker.mHistoryIndex + 32) - i2; i3 += -1) {
            printWriter.println("  Previous WiFiNetwork(" + ((wifiStatusTracker.mHistoryIndex + 32) - i3) + "): " + strArr[i3 & 31]);
        }
        dumpTableData(printWriter);
    }

    public final int getCurrentIconIdForCarrierWifi() {
        ConnectivityState connectivityState = this.mCurrentState;
        int i = ((WifiState) connectivityState).level;
        int maxSignalLevel = this.mWifiManager.getMaxSignalLevel() + 1;
        boolean z = !((WifiState) connectivityState).isDefaultConnectionValidated;
        if (((WifiState) connectivityState).connected) {
            return SignalDrawable.getState(i, maxSignalLevel, z);
        }
        if (((WifiState) connectivityState).enabled) {
            return SignalDrawable.getState(0, maxSignalLevel, true);
        }
        return 0;
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalController
    public final void notifyListeners(SignalCallback signalCallback) {
        IconState iconState;
        int i;
        ConnectivityState connectivityState = this.mCurrentState;
        WifiState wifiState = (WifiState) connectivityState;
        boolean z = wifiState.isCarrierMerged;
        IconState iconState2 = null;
        NetworkControllerImpl networkControllerImpl = this.mNetworkController;
        if (!z) {
            boolean z2 = wifiState.enabled && ((wifiState.connected && wifiState.inetCondition == 1) || !this.mHasMobileDataFeature || wifiState.isDefault || this.mContext.getResources().getBoolean(R.bool.config_showWifiIndicatorWhenEnabled));
            String str = wifiState.connected ? wifiState.ssid : null;
            boolean z3 = z2 && wifiState.ssid != null;
            String charSequence = getTextIfExists(getContentDescription()).toString();
            if (wifiState.inetCondition == 0) {
                StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(charSequence, ",");
                m.append(this.mContext.getString(R.string.data_connection_no_internet));
                charSequence = m.toString();
            }
            IconState iconState3 = new IconState(getCurrentIconId(), charSequence, z2);
            if (wifiState.isDefault || (networkControllerImpl.mAirplaneMode && !networkControllerImpl.mConnectedTransports.get(3))) {
                iconState2 = new IconState(this.mWifiTracker.isCaptivePortal ? R.drawable.ic_qs_wifi_disconnected : connectivityState.connected ? connectivityState.iconGroup.qsIcons[connectivityState.inetCondition][connectivityState.level] : connectivityState.enabled ? connectivityState.iconGroup.qsDiscState : connectivityState.iconGroup.qsNullState, charSequence, wifiState.connected);
            }
            signalCallback.setWifiIndicators(new WifiIndicators(wifiState.enabled, iconState3, iconState2, z3 && wifiState.activityIn, z3 && wifiState.activityOut, str, wifiState.statusLabel));
            return;
        }
        if (wifiState.isDefault || networkControllerImpl.mAirplaneMode) {
            String charSequence2 = getTextIfExists(getContentDescription()).toString();
            SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = this.mCarrierMergedWifiIconGroup;
            CharSequence textIfExists = getTextIfExists(signalIcon$MobileIconGroup.dataContentDescription);
            String string = wifiState.inetCondition == 0 ? this.mContext.getString(R.string.data_connection_no_internet) : Html.fromHtml(textIfExists.toString(), 0).toString();
            boolean z4 = wifiState.enabled && wifiState.connected && wifiState.isDefault;
            IconState iconState4 = new IconState(getCurrentIconIdForCarrierWifi(), charSequence2, z4);
            int i2 = signalIcon$MobileIconGroup.dataType;
            int i3 = z4 ? i2 : 0;
            if (z4) {
                i = i2;
                iconState = new IconState(getCurrentIconIdForCarrierWifi(), charSequence2, wifiState.connected);
            } else {
                iconState = null;
                i = 0;
            }
            MobileSignalController controllerWithSubId = networkControllerImpl.getControllerWithSubId(wifiState.subId);
            signalCallback.setMobileDataIndicators(new MobileDataIndicators(iconState4, iconState, i3, i, wifiState.activityIn, wifiState.activityOut, string, textIfExists, controllerWithSubId != null ? controllerWithSubId.mPhone.getSimOperatorName() : "", wifiState.subId, false, true));
        }
    }

    public void setActivity(int i) {
        ConnectivityState connectivityState = this.mCurrentState;
        ((WifiState) connectivityState).activityIn = i == 3 || i == 1;
        ((WifiState) connectivityState).activityOut = i == 3 || i == 2;
        notifyListenersIfNecessary();
    }

    public final void updateConnectivity(BitSet bitSet, BitSet bitSet2) {
        WifiState wifiState = (WifiState) this.mCurrentState;
        wifiState.inetCondition = bitSet2.get(this.mTransportType) ? 1 : 0;
        wifiState.isDefaultConnectionValidated = bitSet2.get(0) || bitSet2.get(1);
        notifyListenersIfNecessary();
    }
}
