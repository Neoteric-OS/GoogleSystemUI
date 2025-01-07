package com.android.systemui.statusbar.pipeline.shared;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectivityInputLogger {
    public final LogBuffer buffer;

    public ConnectivityInputLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logDefaultConnectionsChanged(DefaultConnectionModel defaultConnectionModel) {
        LogLevel logLevel = LogLevel.DEBUG;
        ConnectivityInputLogger$logDefaultConnectionsChanged$2 connectivityInputLogger$logDefaultConnectionsChanged$2 = new ConnectivityInputLogger$logDefaultConnectionsChanged$2(1, defaultConnectionModel, DefaultConnectionModel.class, "messagePrinter", "messagePrinter(Lcom/android/systemui/log/core/LogMessage;)Ljava/lang/String;", 0);
        LogBuffer logBuffer = this.buffer;
        LogMessageImpl logMessageImpl = (LogMessageImpl) logBuffer.obtain("ConnectivityInputLogger", logLevel, connectivityInputLogger$logDefaultConnectionsChanged$2, null);
        logMessageImpl.setBool1(defaultConnectionModel.wifi.isDefault);
        logMessageImpl.setBool2(defaultConnectionModel.mobile.isDefault);
        logMessageImpl.setBool3(defaultConnectionModel.carrierMerged.isDefault);
        logMessageImpl.setBool4(defaultConnectionModel.ethernet.isDefault);
        logMessageImpl.setInt1(defaultConnectionModel.isValidated ? 1 : 0);
        logBuffer.commit(logMessageImpl);
    }

    public final void logOnDefaultCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        LogLevel logLevel = LogLevel.INFO;
        LoggerHelper$logOnCapabilitiesChanged$2 loggerHelper$logOnCapabilitiesChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.shared.LoggerHelper$logOnCapabilitiesChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str = logMessage.getBool1() ? "Default" : "";
                int int1 = logMessage.getInt1();
                String str1 = logMessage.getStr1();
                StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("on", str, "CapabilitiesChanged: net=", int1, " capabilities=");
                m.append(str1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("ConnectivityInputLogger", logLevel, loggerHelper$logOnCapabilitiesChanged$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.bool1 = true;
        logMessageImpl.int1 = network.getNetId();
        logMessageImpl.str1 = networkCapabilities.toString();
        logBuffer.commit(obtain);
    }

    public final void logOnDefaultLost(Network network) {
        LogLevel logLevel = LogLevel.INFO;
        LoggerHelper$logOnLost$2 loggerHelper$logOnLost$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.shared.LoggerHelper$logOnLost$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("on", logMessage.getBool1() ? "Default" : "", "Lost: net=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("ConnectivityInputLogger", logLevel, loggerHelper$logOnLost$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = network.getNetId();
        logMessageImpl.bool1 = true;
        logBuffer.commit(obtain);
    }

    public final void logTuningChanged(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        ConnectivityInputLogger$logTuningChanged$2 connectivityInputLogger$logTuningChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.shared.ConnectivityInputLogger$logTuningChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onTuningChanged: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("ConnectivityInputLogger", logLevel, connectivityInputLogger$logTuningChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logVcnSubscriptionId(int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        ConnectivityInputLogger$logVcnSubscriptionId$2 connectivityInputLogger$logVcnSubscriptionId$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.shared.ConnectivityInputLogger$logVcnSubscriptionId$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj).getInt1(), "vcnSubId changed: ");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("ConnectivityInputLogger", logLevel, connectivityInputLogger$logVcnSubscriptionId$2, null);
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }
}
