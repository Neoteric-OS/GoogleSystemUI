package com.android.systemui.bluetooth.qsdialog;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogLogger {
    public final LogBuffer logBuffer;

    public BluetoothTileDialogLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void logBluetoothState(BluetoothStateStage bluetoothStateStage, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothTileDialogLogger$logBluetoothState$2 bluetoothTileDialogLogger$logBluetoothState$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logBluetoothState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("BluetoothState. stage=", logMessage.getStr1(), " state=", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logBluetoothState$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = bluetoothStateStage.toString();
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
    }

    public final void logDeviceFetch(JobStatus jobStatus, DeviceFetchTrigger deviceFetchTrigger, long j) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothTileDialogLogger$logDeviceFetch$2 bluetoothTileDialogLogger$logDeviceFetch$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logDeviceFetch$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                long long1 = logMessage.getLong1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("DeviceFetch. status=", str1, " trigger=", str2, " duration=");
                m.append(long1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logDeviceFetch$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = jobStatus.toString();
        logMessageImpl.str2 = deviceFetchTrigger.toString();
        logMessageImpl.long1 = j;
        logBuffer.commit(obtain);
    }
}
