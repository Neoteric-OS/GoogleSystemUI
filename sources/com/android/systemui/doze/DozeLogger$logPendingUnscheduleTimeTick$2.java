package com.android.systemui.doze;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DozeLogger$logPendingUnscheduleTimeTick$2 extends Lambda implements Function1 {
    public static final DozeLogger$logPendingUnscheduleTimeTick$2 INSTANCE = new DozeLogger$logPendingUnscheduleTimeTick$2();

    public DozeLogger$logPendingUnscheduleTimeTick$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return "Pending unschedule time tick, isPending=" + logMessage.getBool1() + ", isTimeTickScheduled:" + logMessage.getBool2();
    }
}
