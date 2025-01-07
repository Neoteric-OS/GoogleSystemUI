package com.android.systemui.doze;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DozeLogger$logSensorTriggered$2 extends Lambda implements Function1 {
    public static final DozeLogger$logSensorTriggered$2 INSTANCE = new DozeLogger$logSensorTriggered$2();

    public DozeLogger$logSensorTriggered$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return "Sensor triggered, type=".concat(DozeLog.reasonToString(((LogMessage) obj).getInt1()));
    }
}
