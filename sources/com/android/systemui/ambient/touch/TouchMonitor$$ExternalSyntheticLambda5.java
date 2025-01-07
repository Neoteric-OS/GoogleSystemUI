package com.android.systemui.ambient.touch;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ TouchMonitor$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        switch (this.$r8$classId) {
            case 0:
                return "Session popped, hashCode: " + logMessage.getInt1();
            case 1:
                return "stopMonitoring(): waiting for sessions to end: " + logMessage.getStr1();
            default:
                return "Session start, handler: " + logMessage.getStr1() + ", x: " + logMessage.getLong1() + ", y: " + logMessage.getLong2() + ", hashCode: " + logMessage.getInt1();
        }
    }
}
