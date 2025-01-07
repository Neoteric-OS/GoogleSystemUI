package com.android.systemui.dreams;

import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamLogger extends Logger {
    public final void logShowOrHideStatusBarItem(String str, boolean z) {
        DreamLogger$logShowOrHideStatusBarItem$1 dreamLogger$logShowOrHideStatusBarItem$1 = new Function1() { // from class: com.android.systemui.dreams.DreamLogger$logShowOrHideStatusBarItem$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return (logMessage.getBool1() ? "Showing" : "Hiding") + " dream status bar item: " + logMessage.getInt1();
            }
        };
        LogMessage obtain = getBuffer().obtain(getTag(), LogLevel.DEBUG, dreamLogger$logShowOrHideStatusBarItem$1, null);
        obtain.setBool1(z);
        obtain.setStr1(str);
        getBuffer().commit(obtain);
    }
}
