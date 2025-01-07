package com.google.android.systemui.columbus.legacy.sensors.config;

import android.util.Range;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$initialize$1;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$RecognizerStart;
import com.google.protobuf.nano.MessageNano;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GestureConfiguration {
    public static final Range SENSITIVITY_RANGE = Range.create(Float.valueOf(0.0f), Float.valueOf(1.0f));
    public final Function1 adjustmentCallback = new GestureConfiguration$adjustmentCallback$1(this);
    public final List adjustments;
    public CHREGestureSensor$initialize$1 listener;
    public float sensitivity;
    public final SensorConfiguration sensorConfiguration;

    public GestureConfiguration(List list, SensorConfiguration sensorConfiguration) {
        this.adjustments = list;
        this.sensorConfiguration = sensorConfiguration;
        this.sensitivity = sensorConfiguration.defaultSensitivityValue;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((LowSensitivitySettingAdjustment) it.next()).callback = this.adjustmentCallback;
        }
        updateSensitivity();
    }

    public final void updateSensitivity() {
        float f = this.sensorConfiguration.defaultSensitivityValue;
        for (LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment : this.adjustments) {
            Range range = SENSITIVITY_RANGE;
            if (lowSensitivitySettingAdjustment.useLowSensitivity) {
                f = lowSensitivitySettingAdjustment.sensorConfiguration.lowSensitivityValue;
            }
            f = ((Number) range.clamp(Float.valueOf(f))).floatValue();
        }
        if (Math.abs(this.sensitivity - f) >= 0.05f) {
            this.sensitivity = f;
            CHREGestureSensor$initialize$1 cHREGestureSensor$initialize$1 = this.listener;
            if (cHREGestureSensor$initialize$1 != null) {
                CHREGestureSensor cHREGestureSensor = cHREGestureSensor$initialize$1.this$0;
                cHREGestureSensor.getClass();
                ColumbusProto$RecognizerStart columbusProto$RecognizerStart = new ColumbusProto$RecognizerStart(1);
                columbusProto$RecognizerStart.sensitivity = this.sensitivity;
                CHREGestureSensor.sendMessageToNanoApp$default(cHREGestureSensor, 200, MessageNano.toByteArray(columbusProto$RecognizerStart), null, 12);
            }
        }
    }
}
