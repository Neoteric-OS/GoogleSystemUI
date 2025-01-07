package com.android.systemui.bluetooth;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothLogger {
    public final LogBuffer logBuffer;

    public BluetoothLogger(LogBuffer logBuffer) {
        this.logBuffer = logBuffer;
    }

    public final void logAclConnectionStateChanged(String str, String str2) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logAclConnectionStateChanged$2 bluetoothLogger$logAclConnectionStateChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logAclConnectionStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("AclConnectionStateChanged. address=", logMessage.getStr1(), " state=", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logAclConnectionStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logActiveDeviceChanged(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logActiveDeviceChanged$2 bluetoothLogger$logActiveDeviceChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logActiveDeviceChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("ActiveDeviceChanged. address=", logMessage.getStr1(), " profileId=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logActiveDeviceChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logBondStateChange(int i, String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logBondStateChange$2 bluetoothLogger$logBondStateChange$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logBondStateChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("DeviceBondStateChanged. address=", logMessage.getStr1(), " state=", logMessage.getInt1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logBondStateChange$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logDeviceAdded(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logDeviceAdded$2 bluetoothLogger$logDeviceAdded$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logDeviceAdded$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("DeviceAdded. address=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logDeviceAdded$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logDeviceAttributesChanged() {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logDeviceAttributesChanged$2 bluetoothLogger$logDeviceAttributesChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logDeviceAttributesChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "DeviceAttributesChanged.";
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        logBuffer.commit(logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logDeviceAttributesChanged$2, null));
    }

    public final void logDeviceConnectionStateChanged(String str, String str2) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logDeviceConnectionStateChanged$2 bluetoothLogger$logDeviceConnectionStateChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logDeviceConnectionStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("DeviceConnectionStateChanged. address=", logMessage.getStr1(), " state=", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logDeviceConnectionStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        ((LogMessageImpl) obtain).str2 = str2;
        logBuffer.commit(obtain);
    }

    public final void logDeviceDeleted(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logDeviceDeleted$2 bluetoothLogger$logDeviceDeleted$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logDeviceDeleted$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("DeviceDeleted. address=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logDeviceDeleted$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }

    public final void logProfileConnectionStateChanged(String str, String str2, int i) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logProfileConnectionStateChanged$2 bluetoothLogger$logProfileConnectionStateChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logProfileConnectionStateChanged$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str22 = logMessage.getStr2();
                int int1 = logMessage.getInt1();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("ProfileConnectionStateChanged. address=", str1, " state=", str22, " profileId=");
                m.append(int1);
                return m.toString();
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logProfileConnectionStateChanged$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str2 = str2;
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
    }

    public final void logStateChange(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothLogger$logStateChange$2 bluetoothLogger$logStateChange$2 = new Function1() { // from class: com.android.systemui.bluetooth.BluetoothLogger$logStateChange$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("BluetoothStateChanged. state=", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothLog", logLevel, bluetoothLogger$logStateChange$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }
}
