package com.android.systemui.settings.brightness;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BrightnessController$$ExternalSyntheticLambda1 implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return "%s brightness set in display %d to %.3f".formatted(logMessage.getBool1() ? "Starting" : "Finishing", Integer.valueOf(logMessage.getInt1()), Double.valueOf(logMessage.getDouble1()));
    }
}
