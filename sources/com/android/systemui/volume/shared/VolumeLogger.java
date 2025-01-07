package com.android.systemui.volume.shared;

import android.media.AudioSystem;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.settingslib.volume.shared.model.AudioStreamModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeLogger {
    public final LogBuffer logBuffer;

    public VolumeLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    /* renamed from: onSetVolumeRequested-VrMivd8, reason: not valid java name */
    public final void m895onSetVolumeRequestedVrMivd8(int i, int i2) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumeLogger$onSetVolumeRequested$2 volumeLogger$onSetVolumeRequested$2 = new Function1() { // from class: com.android.systemui.volume.shared.VolumeLogger$onSetVolumeRequested$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Set volume: stream=", logMessage.getStr1(), " volume=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_Volume", logLevel, volumeLogger$onSetVolumeRequested$2, null);
        Set set = AudioStream.supportedStreamTypes;
        ((LogMessageImpl) obtain).str1 = AudioSystem.streamToString(i);
        ((LogMessageImpl) obtain).int1 = i2;
        logBuffer.commit(obtain);
    }

    /* renamed from: onVolumeUpdateReceived-VrMivd8, reason: not valid java name */
    public final void m896onVolumeUpdateReceivedVrMivd8(int i, AudioStreamModel audioStreamModel) {
        LogLevel logLevel = LogLevel.DEBUG;
        VolumeLogger$onVolumeUpdateReceived$2 volumeLogger$onVolumeUpdateReceived$2 = new Function1() { // from class: com.android.systemui.volume.shared.VolumeLogger$onVolumeUpdateReceived$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Volume update received: stream=", logMessage.getStr1(), " volume=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("SysUI_Volume", logLevel, volumeLogger$onVolumeUpdateReceived$2, null);
        Set set = AudioStream.supportedStreamTypes;
        ((LogMessageImpl) obtain).str1 = AudioSystem.streamToString(i);
        ((LogMessageImpl) obtain).int1 = audioStreamModel.volume;
        logBuffer.commit(obtain);
    }
}
