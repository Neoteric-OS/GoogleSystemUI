package com.android.keyguard.logging;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.keyguard.CarrierTextManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CarrierTextManagerLogger {
    public final LogBuffer buffer;
    public String location;

    public CarrierTextManagerLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logCallbackSentFromUpdate(CarrierTextManager.CarrierTextCallbackInfo carrierTextCallbackInfo) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logCallbackSentFromUpdate$2 carrierTextManagerLogger$logCallbackSentFromUpdate$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logCallbackSentFromUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("┗ updateCarrierText: result=(carrierText=", str1, ", anySimReady=", bool1, ", airplaneMode="), logMessage.getBool2(), ")");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logCallbackSentFromUpdate$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = String.valueOf(carrierTextCallbackInfo.carrierText);
        logMessageImpl.bool1 = carrierTextCallbackInfo.anySimReady;
        logMessageImpl.bool2 = carrierTextCallbackInfo.airplaneMode;
        logBuffer.commit(obtain);
    }

    public final void logNewSatelliteCarrierText(String str) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logNewSatelliteCarrierText$2 carrierTextManagerLogger$logNewSatelliteCarrierText$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logNewSatelliteCarrierText$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("New satellite text = ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logNewSatelliteCarrierText$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logSimStateChangedCallback(int i, int i2, int i3) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logSimStateChangedCallback$2 carrierTextManagerLogger$logSimStateChangedCallback$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logSimStateChangedCallback$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "onSimStateChangedCallback: subId=" + logMessage.getLong1() + " slotId=" + logMessage.getInt1() + " simState=" + logMessage.getInt2();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logSimStateChangedCallback$2, null);
        ((LogMessageImpl) obtain).long1 = i;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i2;
        logMessageImpl.int2 = i3;
        logBuffer.commit(obtain);
    }

    public final void logStartListeningForSatelliteCarrierText() {
        LogLevel logLevel = LogLevel.DEBUG;
        CarrierTextManagerLogger$logStartListeningForSatelliteCarrierText$2 carrierTextManagerLogger$logStartListeningForSatelliteCarrierText$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logStartListeningForSatelliteCarrierText$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String str1 = ((LogMessage) obj).getStr1();
                if (str1 == null) {
                    str1 = "(unknown)";
                }
                return "Start listening for satellite carrier text. Location=".concat(str1);
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logStartListeningForSatelliteCarrierText$2, null);
        ((LogMessageImpl) obtain).str1 = this.location;
        logBuffer.commit(obtain);
    }

    public final void logStopListeningForSatelliteCarrierText(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        CarrierTextManagerLogger$logStopListeningForSatelliteCarrierText$2 carrierTextManagerLogger$logStopListeningForSatelliteCarrierText$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logStopListeningForSatelliteCarrierText$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                if (str1 == null) {
                    str1 = "(unknown)";
                }
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Stop listening for satellite carrier text. Location=", str1, " Reason=", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logStopListeningForSatelliteCarrierText$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = this.location;
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logUpdate(int i) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logUpdate$2 carrierTextManagerLogger$logUpdate$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                if (str1 == null) {
                    str1 = "(unknown)";
                }
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("updateCarrierText: location=", str1, " numSubs=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logUpdate$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        ((LogMessageImpl) obtain).str1 = this.location;
        logBuffer.commit(obtain);
    }

    public final void logUpdateCarrierTextForReason(final int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        Function1 function1 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUpdateCarrierTextForReason$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int i2 = i;
                String str = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "unknown" : "SATELLITE_CHANGED" : "ACTIVE_DATA_SUB_CHANGED" : "SIM_ERROR_STATE_CHANGED" : "ON_TELEPHONY_CAPABLE" : "REFRESH_CARRIER_INFO";
                String str1 = logMessage.getStr1();
                if (str1 == null) {
                    str1 = "(unknown)";
                }
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("refreshing carrier info for reason: ", str, " location=", str1);
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.str1 = this.location;
        logBuffer.commit(obtain);
    }

    public final void logUpdateFromStickyBroadcast(String str, String str2) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logUpdateFromStickyBroadcast$2 carrierTextManagerLogger$logUpdateFromStickyBroadcast$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUpdateFromStickyBroadcast$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("┣ updateCarrierText: getting PLMN/SPN sticky brdcst. plmn=", logMessage.getStr1(), ", spn=", logMessage.getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logUpdateFromStickyBroadcast$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logUpdateLoopStart(String str, int i, int i2) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logUpdateLoopStart$2 carrierTextManagerLogger$logUpdateLoopStart$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUpdateLoopStart$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                String str1 = logMessage.getStr1();
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "┣ updateCarrierText: updating sub=", " simState=", " carrierName=");
                m.append(str1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logUpdateLoopStart$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.int2 = i2;
        logMessageImpl.str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logUpdateWfcCheck() {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logUpdateWfcCheck$2 carrierTextManagerLogger$logUpdateWfcCheck$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUpdateWfcCheck$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "┣ updateCarrierText: found WFC state";
            }
        };
        LogBuffer logBuffer = this.buffer;
        logBuffer.commit(logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logUpdateWfcCheck$2, null));
    }

    public final void logUsingSatelliteText(String str) {
        LogLevel logLevel = LogLevel.VERBOSE;
        CarrierTextManagerLogger$logUsingSatelliteText$2 carrierTextManagerLogger$logUsingSatelliteText$2 = new Function1() { // from class: com.android.keyguard.logging.CarrierTextManagerLogger$logUsingSatelliteText$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("┣ updateCarrierText: using satellite text. text=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("CarrierTextManagerLog", logLevel, carrierTextManagerLogger$logUsingSatelliteText$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }
}
