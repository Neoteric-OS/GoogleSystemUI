package com.android.systemui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.util.ArraySet;
import android.util.SparseBooleanArray;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment;
import com.android.systemui.statusbar.policy.CallbackController;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CarrierConfigTracker extends BroadcastReceiver implements CallbackController {
    public final CarrierConfigManager mCarrierConfigManager;
    public boolean mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfig;
    public boolean mDefaultAlwaysShowPrimarySignalBarInOpportunisticNetworkConfigLoaded;
    public boolean mDefaultCarrierProvisionsWifiMergedNetworks;
    public boolean mDefaultCarrierProvisionsWifiMergedNetworksLoaded;
    public boolean mDefaultShowOperatorNameConfig;
    public boolean mDefaultShowOperatorNameConfigLoaded;
    public final SparseBooleanArray mCallStrengthConfigs = new SparseBooleanArray();
    public final SparseBooleanArray mNoCallingConfigs = new SparseBooleanArray();
    public final SparseBooleanArray mCarrierProvisionsWifiMergedNetworks = new SparseBooleanArray();
    public final SparseBooleanArray mShowOperatorNameConfigs = new SparseBooleanArray();
    public final Set mListeners = new ArraySet();
    public final Set mDataListeners = new ArraySet();

    public CarrierConfigTracker(CarrierConfigManager carrierConfigManager, BroadcastDispatcher broadcastDispatcher) {
        this.mCarrierConfigManager = carrierConfigManager;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
        broadcastDispatcher.registerReceiver(this, intentFilter);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        ((ArraySet) this.mListeners).add((CollapsedStatusBarFragment.AnonymousClass2) obj);
    }

    public final boolean getShowOperatorNameInStatusBarConfig(int i) {
        if (this.mShowOperatorNameConfigs.indexOfKey(i) >= 0) {
            return this.mShowOperatorNameConfigs.get(i);
        }
        if (!this.mDefaultShowOperatorNameConfigLoaded) {
            this.mDefaultShowOperatorNameConfig = CarrierConfigManager.getDefaultConfig().getBoolean("show_operator_name_in_statusbar_bool");
            this.mDefaultShowOperatorNameConfigLoaded = true;
        }
        return this.mDefaultShowOperatorNameConfig;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        PersistableBundle configForSubId;
        String action = intent.getAction();
        if (!"android.telephony.action.CARRIER_CONFIG_CHANGED".equals(action)) {
            if ("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED".equals(action)) {
                intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                Iterator it = ((ArraySet) this.mDataListeners).iterator();
                while (it.hasNext()) {
                    CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                    if (collapsedStatusBarFragment.mOperatorNameViewController == null) {
                        collapsedStatusBarFragment.initOperatorName();
                    }
                }
                return;
            }
            return;
        }
        int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
        if (this.mCarrierConfigManager == null || !SubscriptionManager.isValidSubscriptionId(intExtra) || (configForSubId = this.mCarrierConfigManager.getConfigForSubId(intExtra)) == null) {
            return;
        }
        synchronized (this.mCallStrengthConfigs) {
            this.mCallStrengthConfigs.put(intExtra, configForSubId.getBoolean("display_call_strength_indicator_bool"));
        }
        synchronized (this.mNoCallingConfigs) {
            this.mNoCallingConfigs.put(intExtra, configForSubId.getBoolean("use_ip_for_calling_indicator_bool"));
        }
        synchronized (this.mCarrierProvisionsWifiMergedNetworks) {
            this.mCarrierProvisionsWifiMergedNetworks.put(intExtra, configForSubId.getBoolean("carrier_provisions_wifi_merged_networks_bool"));
        }
        synchronized (this.mShowOperatorNameConfigs) {
            this.mShowOperatorNameConfigs.put(intExtra, configForSubId.getBoolean("show_operator_name_in_statusbar_bool"));
        }
        Iterator it2 = ((ArraySet) this.mListeners).iterator();
        while (it2.hasNext()) {
            CollapsedStatusBarFragment collapsedStatusBarFragment2 = CollapsedStatusBarFragment.this;
            if (collapsedStatusBarFragment2.mOperatorNameViewController == null) {
                collapsedStatusBarFragment2.initOperatorName();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        ((ArraySet) this.mListeners).remove((CollapsedStatusBarFragment.AnonymousClass2) obj);
    }
}
