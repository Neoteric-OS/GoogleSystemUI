package com.android.keyguard;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardClockSwitch$$ExternalSyntheticLambda0 implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        int i = KeyguardClockSwitch.$r8$clinit;
        return "updateClockViews; useLargeClock=" + logMessage.getBool1() + "; animate=" + logMessage.getBool2() + "; mChildrenAreLaidOut=" + logMessage.getBool3();
    }
}
