package com.android.systemui.statusbar.pipeline.mobile.data;

import android.content.Intent;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.settingslib.mobile.MobileMappings;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileInputLogger {
    public final LogBuffer buffer;

    public MobileInputLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logActionCarrierConfigChanged() {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logActionCarrierConfigChanged$2 mobileInputLogger$logActionCarrierConfigChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logActionCarrierConfigChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "Intent received: ACTION_CARRIER_CONFIG_CHANGED";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logActionCarrierConfigChanged$2, null));
    }

    public final void logCarrierConfigChanged(int i) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logCarrierConfigChanged$2 mobileInputLogger$logCarrierConfigChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logCarrierConfigChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "onCarrierConfigChanged: subId=");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logCarrierConfigChanged$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logDefaultDataSubRatConfig(MobileMappings.Config config) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logDefaultDataSubRatConfig$2 mobileInputLogger$logDefaultDataSubRatConfig$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logDefaultDataSubRatConfig$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("defaultDataSubRatConfig: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logDefaultDataSubRatConfig$2, null);
        ((LogMessageImpl) obtain).str1 = config.toString();
        logBuffer.commit(obtain);
    }

    public final void logDefaultMobileIconGroup(SignalIcon$MobileIconGroup signalIcon$MobileIconGroup) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logDefaultMobileIconGroup$2 mobileInputLogger$logDefaultMobileIconGroup$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logDefaultMobileIconGroup$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("defaultMobileIconGroup: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logDefaultMobileIconGroup$2, null);
        ((LogMessageImpl) obtain).str1 = signalIcon$MobileIconGroup.name;
        logBuffer.commit(obtain);
    }

    public final void logDefaultMobileIconMapping(Map map) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logDefaultMobileIconMapping$2 mobileInputLogger$logDefaultMobileIconMapping$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logDefaultMobileIconMapping$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("defaultMobileIconMapping: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logDefaultMobileIconMapping$2, null);
        ((LogMessageImpl) obtain).str1 = map.toString();
        logBuffer.commit(obtain);
    }

    public final void logOnCarrierNetworkChange(int i, boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnCarrierNetworkChange$2 mobileInputLogger$logOnCarrierNetworkChange$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnCarrierNetworkChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onCarrierNetworkChange: subId=" + logMessage.getInt1() + " active=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnCarrierNetworkChange$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logOnCarrierRoamingNtnModeChanged(boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnCarrierRoamingNtnModeChanged$2 mobileInputLogger$logOnCarrierRoamingNtnModeChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnCarrierRoamingNtnModeChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("onCarrierRoamingNtnModeChanged: ", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnCarrierRoamingNtnModeChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logOnDataActivity(int i, int i2) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnDataActivity$2 mobileInputLogger$logOnDataActivity$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnDataActivity$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return ListImplementation$$ExternalSyntheticOutline0.m("onDataActivity: subId=", logMessage.getInt1(), logMessage.getInt2(), " direction=");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnDataActivity$2, null);
        ((LogMessageImpl) obtain).int1 = i2;
        ((LogMessageImpl) obtain).int2 = i;
        logBuffer.commit(obtain);
    }

    public final void logOnDataConnectionStateChanged(int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnDataConnectionStateChanged$2 mobileInputLogger$logOnDataConnectionStateChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnDataConnectionStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                String str1 = logMessage.getStr1();
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "onDataConnectionStateChanged: subId=", " dataState=", " networkType=");
                m.append(str1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnDataConnectionStateChanged$2, null);
        ((LogMessageImpl) obtain).int1 = i3;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int2 = i;
        logMessageImpl.str1 = String.valueOf(i2);
        logBuffer.commit(obtain);
    }

    public final void logOnDataEnabledChanged(int i, boolean z) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnDataEnabledChanged$2 mobileInputLogger$logOnDataEnabledChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnDataEnabledChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onDataEnabledChanged: subId=" + logMessage.getInt1() + " enabled=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnDataEnabledChanged$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    public final void logOnDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo, int i) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnDisplayInfoChanged$2 mobileInputLogger$logOnDisplayInfoChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnDisplayInfoChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onDisplayInfoChanged: subId=" + logMessage.getInt1() + " displayInfo=" + logMessage.getStr1() + " isRoaming=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnDisplayInfoChanged$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str1 = telephonyDisplayInfo.toString();
        logMessageImpl.bool1 = telephonyDisplayInfo.isRoaming();
        logBuffer.commit(obtain);
    }

    public final void logOnServiceStateChanged(int i, ServiceState serviceState) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnServiceStateChanged$2 mobileInputLogger$logOnServiceStateChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnServiceStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onServiceStateChanged: subId=" + logMessage.getInt1() + " emergencyOnly=" + logMessage.getBool1() + " roaming=" + logMessage.getBool2() + " operator=" + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnServiceStateChanged$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = serviceState.isEmergencyOnly();
        logMessageImpl.bool2 = serviceState.getRoaming();
        logMessageImpl.str1 = serviceState.getOperatorAlphaShort();
        logBuffer.commit(obtain);
    }

    public final void logOnSignalStrengthsChanged(SignalStrength signalStrength, int i) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnSignalStrengthsChanged$2 mobileInputLogger$logOnSignalStrengthsChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnSignalStrengthsChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onSignalStrengthsChanged: subId=" + logMessage.getInt1() + " strengths=" + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnSignalStrengthsChanged$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str1 = signalStrength.toString();
        logBuffer.commit(obtain);
    }

    public final void logOnSubscriptionsChanged() {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logOnSubscriptionsChanged$2 mobileInputLogger$logOnSubscriptionsChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logOnSubscriptionsChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "onSubscriptionsChanged";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logOnSubscriptionsChanged$2, null));
    }

    public final void logPrioritizedNetworkAvailable(int i) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logPrioritizedNetworkAvailable$2 mobileInputLogger$logPrioritizedNetworkAvailable$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logPrioritizedNetworkAvailable$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GenericDocument$$ExternalSyntheticOutline0.m("Found prioritized network (nedId=", ")", ((LogMessage) obj).getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logPrioritizedNetworkAvailable$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logPrioritizedNetworkLost(int i) {
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logPrioritizedNetworkLost$2 mobileInputLogger$logPrioritizedNetworkLost$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logPrioritizedNetworkLost$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GenericDocument$$ExternalSyntheticOutline0.m("Lost prioritized network (nedId=", ")", ((LogMessage) obj).getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logPrioritizedNetworkLost$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logServiceProvidersUpdatedBroadcast(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("android.telephony.extra.SHOW_SPN", false);
        String stringExtra = intent.getStringExtra("android.telephony.extra.SPN");
        String stringExtra2 = intent.getStringExtra("android.telephony.extra.DATA_SPN");
        boolean booleanExtra2 = intent.getBooleanExtra("android.telephony.extra.SHOW_PLMN", false);
        String stringExtra3 = intent.getStringExtra("android.telephony.extra.PLMN");
        LogLevel logLevel = LogLevel.INFO;
        MobileInputLogger$logServiceProvidersUpdatedBroadcast$2 mobileInputLogger$logServiceProvidersUpdatedBroadcast$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$logServiceProvidersUpdatedBroadcast$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Intent: ACTION_SERVICE_PROVIDERS_UPDATED. showSpn=" + logMessage.getBool1() + " spn=" + logMessage.getStr1() + " dataSpn=" + logMessage.getStr2() + " showPlmn=" + logMessage.getBool2() + " plmn=" + logMessage.getStr3();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MobileInputLog", logLevel, mobileInputLogger$logServiceProvidersUpdatedBroadcast$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = booleanExtra;
        logMessageImpl.str1 = stringExtra;
        logMessageImpl.str2 = stringExtra2;
        logMessageImpl.bool2 = booleanExtra2;
        logMessageImpl.str3 = stringExtra3;
        logBuffer.commit(obtain);
    }
}
