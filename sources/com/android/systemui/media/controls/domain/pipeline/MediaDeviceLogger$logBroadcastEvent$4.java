package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaDeviceLogger$logBroadcastEvent$4 extends Lambda implements Function1 {
    public static final MediaDeviceLogger$logBroadcastEvent$4 INSTANCE = new MediaDeviceLogger$logBroadcastEvent$4();

    public MediaDeviceLogger$logBroadcastEvent$4() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return logMessage.getStr1() + ", reason = " + logMessage.getInt1();
    }
}
