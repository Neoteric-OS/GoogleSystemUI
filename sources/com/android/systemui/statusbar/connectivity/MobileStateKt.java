package com.android.systemui.statusbar.connectivity;

import android.telephony.ServiceState;
import android.telephony.SignalStrength;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MobileStateKt {
    public static final String access$minLog(ServiceState serviceState) {
        return "serviceState={state=" + serviceState.getState() + ",isEmergencyOnly=" + serviceState.isEmergencyOnly() + ",roaming=" + serviceState.getRoaming() + ",operatorNameAlphaShort=" + serviceState.getOperatorAlphaShort() + "}";
    }

    public static final String access$minLog(SignalStrength signalStrength) {
        return "signalStrength={isGsm=" + signalStrength.isGsm() + ",level=" + signalStrength.getLevel() + "}";
    }
}
