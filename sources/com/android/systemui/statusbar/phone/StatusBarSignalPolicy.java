package com.android.systemui.statusbar.phone;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.net.VpnConfig;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.policy.SecurityController;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarSignalPolicy implements SignalCallback, SecurityController.SecurityControllerCallback, TunerService.Tunable, CoreStartable {
    public static final boolean DEBUG = Log.isLoggable("StatusBarSignalPolicy", 3);
    public final Context mContext;
    public final Handler mHandler = Handler.getMain();
    public boolean mHideAirplane;
    public boolean mHideEthernet;
    public boolean mHideMobile;
    public final StatusBarIconController mIconController;
    public boolean mInitialized;
    public final NetworkController mNetworkController;
    public final SecurityController mSecurityController;
    public final String mSlotAirplane;
    public final String mSlotEthernet;
    public final String mSlotMobile;
    public final String mSlotVpn;
    public final TunerService mTunerService;

    public StatusBarSignalPolicy(Context context, StatusBarIconController statusBarIconController, NetworkController networkController, SecurityController securityController, TunerService tunerService, JavaAdapter javaAdapter, AirplaneModeInteractor airplaneModeInteractor) {
        new ArrayList();
        this.mContext = context;
        this.mIconController = statusBarIconController;
        this.mNetworkController = networkController;
        this.mSecurityController = securityController;
        this.mTunerService = tunerService;
        this.mSlotAirplane = context.getString(R.string.status_bar_vpn);
        this.mSlotMobile = context.getString(R.string.suspended_widget_accessibility);
        this.mSlotEthernet = context.getString(R.string.storage_sd_card);
        this.mSlotVpn = context.getString(R.string.time_picker_decrement_hour_button);
        context.getString(R.string.sync_really_delete);
        context.getString(R.string.stk_cc_ss_to_dial_video);
        context.getResources().getBoolean(com.android.wm.shell.R.bool.config_showActivity);
    }

    @Override // com.android.systemui.statusbar.policy.SecurityController.SecurityControllerCallback
    public final void onStateChanged() {
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarSignalPolicy$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                boolean z;
                boolean z2 = true;
                StatusBarSignalPolicy statusBarSignalPolicy = StatusBarSignalPolicy.this;
                SecurityControllerImpl securityControllerImpl = (SecurityControllerImpl) statusBarSignalPolicy.mSecurityController;
                int[] profileIdsWithDisabled = securityControllerImpl.mUserManager.getProfileIdsWithDisabled(securityControllerImpl.mVpnUserId);
                int length = profileIdsWithDisabled.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    if (securityControllerImpl.mCurrentVpns.get(profileIdsWithDisabled[i]) != null) {
                        z = true;
                        break;
                    }
                    i++;
                }
                boolean isVpnBranded = securityControllerImpl.isVpnBranded();
                VpnConfig vpnConfig = (VpnConfig) securityControllerImpl.mCurrentVpns.get(securityControllerImpl.mVpnUserId);
                if (vpnConfig == null) {
                    int[] enabledProfileIds = securityControllerImpl.mUserManager.getEnabledProfileIds(securityControllerImpl.mVpnUserId);
                    int length2 = enabledProfileIds.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        VpnConfig vpnConfig2 = (VpnConfig) securityControllerImpl.mCurrentVpns.get(enabledProfileIds[i2]);
                        if (vpnConfig2 != null && !securityControllerImpl.getVpnValidationStatus(vpnConfig2)) {
                            z2 = false;
                            break;
                        }
                        i2++;
                    }
                } else {
                    z2 = securityControllerImpl.getVpnValidationStatus(vpnConfig);
                }
                int i3 = isVpnBranded ? z2 ? com.android.wm.shell.R.drawable.stat_sys_branded_vpn : com.android.wm.shell.R.drawable.stat_sys_no_internet_branded_vpn : z2 ? com.android.wm.shell.R.drawable.stat_sys_vpn_ic : com.android.wm.shell.R.drawable.stat_sys_no_internet_vpn_ic;
                String string = statusBarSignalPolicy.mContext.getResources().getString(com.android.wm.shell.R.string.accessibility_vpn_on);
                StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarSignalPolicy.mIconController;
                String str = statusBarSignalPolicy.mSlotVpn;
                statusBarIconControllerImpl.setIcon(string, str, i3);
                statusBarIconControllerImpl.setIconVisibility(str, z);
            }
        });
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("icon_blacklist".equals(str)) {
            ArraySet iconHideList = StatusBarIconController.getIconHideList(this.mContext, str2);
            boolean contains = iconHideList.contains(this.mSlotAirplane);
            boolean contains2 = iconHideList.contains(this.mSlotMobile);
            boolean contains3 = iconHideList.contains(this.mSlotEthernet);
            if (contains == this.mHideAirplane && contains2 == this.mHideMobile && contains3 == this.mHideEthernet) {
                return;
            }
            this.mHideAirplane = contains;
            this.mHideMobile = contains2;
            this.mHideEthernet = contains3;
            NetworkController networkController = this.mNetworkController;
            ((NetworkControllerImpl) networkController).removeCallback(this);
            ((NetworkControllerImpl) networkController).addCallback(this);
        }
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalCallback
    public final void setEthernetIndicators(IconState iconState) {
        int i = iconState.icon;
        String str = this.mSlotEthernet;
        StatusBarIconController statusBarIconController = this.mIconController;
        if (i <= 0) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, false);
        } else {
            ((StatusBarIconControllerImpl) statusBarIconController).setIcon(iconState.contentDescription, str, i);
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, true);
        }
    }

    @Override // com.android.systemui.statusbar.connectivity.SignalCallback
    public final void setIsAirplaneMode(IconState iconState) {
        int i;
        if (DEBUG) {
            Log.d("StatusBarSignalPolicy", "setIsAirplaneMode: icon = ".concat(iconState == null ? "" : iconState.toString()));
        }
        boolean z = iconState.visible && !this.mHideAirplane;
        String str = this.mSlotAirplane;
        StatusBarIconController statusBarIconController = this.mIconController;
        if (!z || (i = iconState.icon) <= 0) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, false);
        } else {
            ((StatusBarIconControllerImpl) statusBarIconController).setIcon(iconState.contentDescription, str, i);
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, true);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
