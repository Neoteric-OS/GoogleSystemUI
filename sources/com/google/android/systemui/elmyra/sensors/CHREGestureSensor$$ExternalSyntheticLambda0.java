package com.google.android.systemui.elmyra.sensors;

import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$SensitivityUpdate;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CHREGestureSensor$$ExternalSyntheticLambda0 implements GestureConfiguration.Listener {
    public final /* synthetic */ CHREGestureSensor f$0;

    @Override // com.google.android.systemui.elmyra.sensors.config.GestureConfiguration.Listener
    public void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration) {
        CHREGestureSensor cHREGestureSensor = this.f$0;
        cHREGestureSensor.getClass();
        ContextHubMessages$SensitivityUpdate contextHubMessages$SensitivityUpdate = new ContextHubMessages$SensitivityUpdate();
        contextHubMessages$SensitivityUpdate.sensitivity = gestureConfiguration.getSensitivity();
        cHREGestureSensor.sendMessageToNanoApp(202, MessageNano.toByteArray(contextHubMessages$SensitivityUpdate));
    }
}
