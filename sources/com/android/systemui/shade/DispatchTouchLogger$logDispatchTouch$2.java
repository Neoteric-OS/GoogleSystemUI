package com.android.systemui.shade;

import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DispatchTouchLogger$logDispatchTouch$2 extends Lambda implements Function1 {
    final /* synthetic */ DispatchTouchLogger this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchTouchLogger$logDispatchTouch$2(DispatchTouchLogger dispatchTouchLogger) {
        super(1);
        this.this$0 = dispatchTouchLogger;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        String str1 = logMessage.getStr1();
        DispatchTouchLogger dispatchTouchLogger = this.this$0;
        int int1 = logMessage.getInt1();
        dispatchTouchLogger.getClass();
        String str = int1 != 0 ? int1 != 1 ? int1 != 2 ? int1 != 3 ? int1 != 5 ? int1 != 6 ? "OTHER" : "POINTER_UP" : "POINTER_DOWN" : "CANCEL" : "MOVE" : "UP" : "DOWN";
        long long1 = logMessage.getLong1();
        boolean bool1 = logMessage.getBool1();
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Touch: view=", str1, ", type=", str, ", downtime=");
        m.append(long1);
        m.append(", result=");
        m.append(bool1);
        return m.toString();
    }
}
