package com.android.systemui.volume.panel.shared;

import android.media.AudioSystem;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.volume.panel.shared.model.VolumePanelGlobalState;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelState;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelLogger {
    public final LogBuffer logBuffer;

    public VolumePanelLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void onComponentAvailabilityChanged(String str, boolean z) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumePanelLogger$onComponentAvailabilityChanged$2 volumePanelLogger$onComponentAvailabilityChanged$2 = new Function1() { // from class: com.android.systemui.volume.panel.shared.VolumePanelLogger$onComponentAvailabilityChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return logMessage.getStr1() + " isAvailable=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_VolumePanel", logLevel, volumePanelLogger$onComponentAvailabilityChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
    }

    /* renamed from: onSetVolumeRequested-VrMivd8, reason: not valid java name */
    public final void m893onSetVolumeRequestedVrMivd8(int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumePanelLogger$onSetVolumeRequested$2 volumePanelLogger$onSetVolumeRequested$2 = new Function1() { // from class: com.android.systemui.volume.panel.shared.VolumePanelLogger$onSetVolumeRequested$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Set volume: stream=", logMessage.getStr1(), " volume=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_VolumePanel", logLevel, volumePanelLogger$onSetVolumeRequested$2, null);
        Set set = AudioStream.supportedStreamTypes;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = AudioSystem.streamToString(i);
        logMessageImpl.int1 = i2;
        logBuffer.commit(obtain);
    }

    public final void onVolumePanelGlobalStateChanged(VolumePanelGlobalState volumePanelGlobalState) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumePanelLogger$onVolumePanelGlobalStateChanged$2 volumePanelLogger$onVolumePanelGlobalStateChanged$2 = new Function1() { // from class: com.android.systemui.volume.panel.shared.VolumePanelLogger$onVolumePanelGlobalStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Global state changed: isVisible=", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_VolumePanel", logLevel, volumePanelLogger$onVolumePanelGlobalStateChanged$2, null);
        ((LogMessageImpl) obtain).bool1 = volumePanelGlobalState.isVisible;
        logBuffer.commit(obtain);
    }

    public final void onVolumePanelStateChanged(VolumePanelState volumePanelState) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumePanelLogger$onVolumePanelStateChanged$2 volumePanelLogger$onVolumePanelStateChanged$2 = new Function1() { // from class: com.android.systemui.volume.panel.shared.VolumePanelLogger$onVolumePanelStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("State changed: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_VolumePanel", logLevel, volumePanelLogger$onVolumePanelStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = volumePanelState.toString();
        logBuffer.commit(obtain);
    }

    /* renamed from: onVolumeUpdateReceived-VrMivd8, reason: not valid java name */
    public final void m894onVolumeUpdateReceivedVrMivd8(int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumePanelLogger$onVolumeUpdateReceived$2 volumePanelLogger$onVolumeUpdateReceived$2 = new Function1() { // from class: com.android.systemui.volume.panel.shared.VolumePanelLogger$onVolumeUpdateReceived$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Volume update received: stream=", logMessage.getStr1(), " volume=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_VolumePanel", logLevel, volumePanelLogger$onVolumeUpdateReceived$2, null);
        Set set = AudioStream.supportedStreamTypes;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = AudioSystem.streamToString(i);
        logMessageImpl.int1 = i2;
        logBuffer.commit(obtain);
    }
}
