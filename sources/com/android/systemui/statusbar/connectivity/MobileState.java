package com.android.systemui.statusbar.connectivity;

import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.settingslib.SignalIcon$IconGroup;
import com.android.settingslib.Utils;
import com.android.settingslib.mobile.TelephonyIcons;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileState extends ConnectivityState {
    public boolean airplaneMode;
    public int carrierId;
    public boolean carrierNetworkChangeMode;
    public boolean dataConnected;
    public boolean dataSim;
    public int dataState;
    public boolean defaultDataOff;
    public boolean isDefault;
    public boolean isEmergency;
    public String networkName;
    public String networkNameData;
    public NetworkTypeResIdCache networkTypeResIdCache;
    public boolean roaming;
    public ServiceState serviceState;
    public SignalStrength signalStrength;
    public TelephonyDisplayInfo telephonyDisplayInfo;
    public boolean userSetup;

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final void copyFrom(ConnectivityState connectivityState) {
        MobileState mobileState = connectivityState instanceof MobileState ? (MobileState) connectivityState : null;
        if (mobileState == null) {
            throw new IllegalArgumentException("MobileState can only update from another MobileState");
        }
        super.copyFrom(mobileState);
        this.networkName = mobileState.networkName;
        this.networkNameData = mobileState.networkNameData;
        this.dataSim = mobileState.dataSim;
        this.dataConnected = mobileState.dataConnected;
        this.isEmergency = mobileState.isEmergency;
        this.airplaneMode = mobileState.airplaneMode;
        this.carrierNetworkChangeMode = mobileState.carrierNetworkChangeMode;
        this.isDefault = mobileState.isDefault;
        this.userSetup = mobileState.userSetup;
        this.roaming = mobileState.roaming;
        this.dataState = mobileState.dataState;
        this.defaultDataOff = mobileState.defaultDataOff;
        this.telephonyDisplayInfo = mobileState.telephonyDisplayInfo;
        this.serviceState = mobileState.serviceState;
        this.signalStrength = mobileState.signalStrength;
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!MobileState.class.equals(obj != null ? obj.getClass() : null) || !super.equals(obj)) {
            return false;
        }
        MobileState mobileState = (MobileState) obj;
        return Intrinsics.areEqual(this.networkName, mobileState.networkName) && Intrinsics.areEqual(this.networkNameData, mobileState.networkNameData) && this.carrierId == mobileState.carrierId && this.dataSim == mobileState.dataSim && this.dataConnected == mobileState.dataConnected && this.isEmergency == mobileState.isEmergency && this.airplaneMode == mobileState.airplaneMode && this.carrierNetworkChangeMode == mobileState.carrierNetworkChangeMode && this.isDefault == mobileState.isDefault && this.userSetup == mobileState.userSetup && this.roaming == mobileState.roaming && this.dataState == mobileState.dataState && this.defaultDataOff == mobileState.defaultDataOff && Intrinsics.areEqual(this.telephonyDisplayInfo, mobileState.telephonyDisplayInfo) && Intrinsics.areEqual(this.serviceState, mobileState.serviceState) && Intrinsics.areEqual(this.signalStrength, mobileState.signalStrength);
    }

    public final String getOperatorAlphaShort() {
        ServiceState serviceState = this.serviceState;
        String operatorAlphaShort = serviceState != null ? serviceState.getOperatorAlphaShort() : null;
        return operatorAlphaShort == null ? "" : operatorAlphaShort;
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.networkName;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.networkNameData;
        int hashCode3 = (this.telephonyDisplayInfo.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.carrierId, (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31, 31), 31, this.dataSim), 31, this.dataConnected), 31, this.isEmergency), 31, this.airplaneMode), 31, this.carrierNetworkChangeMode), 31, this.isDefault), 31, this.userSetup), 31, this.roaming) + this.dataState) * 31, 31, this.defaultDataOff)) * 31;
        ServiceState serviceState = this.serviceState;
        int hashCode4 = (hashCode3 + (serviceState != null ? serviceState.hashCode() : 0)) * 31;
        SignalStrength signalStrength = this.signalStrength;
        return hashCode4 + (signalStrength != null ? signalStrength.hashCode() : 0);
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final List tableColumns() {
        return CollectionsKt.plus((Iterable) CollectionsKt__CollectionsKt.listOf("dataSim", "carrierId", "networkName", "networkNameData", "dataConnected", "roaming", "isDefault", "isEmergency", "airplaneMode", "carrierNetworkChangeMode", "userSetup", "dataState", "defaultDataOff", "showQuickSettingsRatIcon", "voiceServiceState", "isInService", "networkTypeIconCache", "serviceState", "signalStrength", "displayInfo"), (Collection) super.tableColumns());
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final List tableData() {
        String access$minLog;
        String access$minLog2;
        SignalIcon$IconGroup signalIcon$IconGroup;
        Boolean valueOf = Boolean.valueOf(this.dataSim);
        Integer valueOf2 = Integer.valueOf(this.carrierId);
        String str = this.networkName;
        String str2 = this.networkNameData;
        Boolean valueOf3 = Boolean.valueOf(this.dataConnected);
        Boolean valueOf4 = Boolean.valueOf(this.roaming);
        Boolean valueOf5 = Boolean.valueOf(this.isDefault);
        Boolean valueOf6 = Boolean.valueOf(this.isEmergency);
        Boolean valueOf7 = Boolean.valueOf(this.airplaneMode);
        Boolean valueOf8 = Boolean.valueOf(this.carrierNetworkChangeMode);
        Boolean valueOf9 = Boolean.valueOf(this.userSetup);
        Integer valueOf10 = Integer.valueOf(this.dataState);
        Boolean valueOf11 = Boolean.valueOf(this.defaultDataOff);
        Boolean valueOf12 = Boolean.valueOf(this.dataConnected || (((signalIcon$IconGroup = this.iconGroup) == TelephonyIcons.DATA_DISABLED || signalIcon$IconGroup == TelephonyIcons.NOT_DEFAULT_DATA) && this.userSetup));
        ServiceState serviceState = this.serviceState;
        Integer valueOf13 = Integer.valueOf(serviceState != null ? serviceState.getState() : -1);
        Boolean valueOf14 = Boolean.valueOf(Utils.isInService(this.serviceState));
        ServiceState serviceState2 = this.serviceState;
        String str3 = (serviceState2 == null || (access$minLog2 = MobileStateKt.access$minLog(serviceState2)) == null) ? "(null)" : access$minLog2;
        SignalStrength signalStrength = this.signalStrength;
        List listOf = CollectionsKt__CollectionsKt.listOf(valueOf, valueOf2, str, str2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, this.networkTypeResIdCache, str3, (signalStrength == null || (access$minLog = MobileStateKt.access$minLog(signalStrength)) == null) ? "(null)" : access$minLog, this.telephonyDisplayInfo);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        return CollectionsKt.plus((Iterable) arrayList, (Collection) super.tableData());
    }

    @Override // com.android.systemui.statusbar.connectivity.ConnectivityState
    public final void toString(StringBuilder sb) {
        String str;
        String access$minLog;
        SignalIcon$IconGroup signalIcon$IconGroup;
        super.toString(sb);
        sb.append(',');
        sb.append("dataSim=" + this.dataSim + ",");
        sb.append("carrierId=" + this.carrierId);
        sb.append("networkName=" + this.networkName + ",");
        sb.append("networkNameData=" + this.networkNameData + ",");
        sb.append("dataConnected=" + this.dataConnected + ",");
        sb.append("roaming=" + this.roaming + ",");
        sb.append("isDefault=" + this.isDefault + ",");
        sb.append("isEmergency=" + this.isEmergency + ",");
        sb.append("airplaneMode=" + this.airplaneMode + ",");
        sb.append("carrierNetworkChangeMode=" + this.carrierNetworkChangeMode + ",");
        sb.append("userSetup=" + this.userSetup + ",");
        sb.append("dataState=" + this.dataState + ",");
        sb.append("defaultDataOff=" + this.defaultDataOff + ",");
        sb.append("showQuickSettingsRatIcon=" + (this.dataConnected || (((signalIcon$IconGroup = this.iconGroup) == TelephonyIcons.DATA_DISABLED || signalIcon$IconGroup == TelephonyIcons.NOT_DEFAULT_DATA) && this.userSetup)) + ",");
        ServiceState serviceState = this.serviceState;
        sb.append("voiceServiceState=" + (serviceState != null ? serviceState.getState() : -1) + ",");
        sb.append("isInService=" + Utils.isInService(this.serviceState) + ",");
        StringBuilder sb2 = new StringBuilder("networkTypeIconCache=");
        sb2.append(this.networkTypeResIdCache);
        sb.append(sb2.toString());
        ServiceState serviceState2 = this.serviceState;
        String str2 = "(null)";
        if (serviceState2 == null || (str = MobileStateKt.access$minLog(serviceState2)) == null) {
            str = "(null)";
        }
        sb.append("serviceState=" + str + ",");
        SignalStrength signalStrength = this.signalStrength;
        if (signalStrength != null && (access$minLog = MobileStateKt.access$minLog(signalStrength)) != null) {
            str2 = access$minLog;
        }
        sb.append("signalStrength=" + str2 + ",");
        sb.append("displayInfo=" + this.telephonyDisplayInfo);
    }

    public static /* synthetic */ void getNetworkTypeResIdCache$annotations() {
    }
}
