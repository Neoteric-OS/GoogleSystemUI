package com.android.systemui.log.core;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract /* synthetic */ class Logger$$ExternalSyntheticOutline0 {
    public static void m(Function1 function1, LogMessage logMessage, Logger logger, LogMessage logMessage2) {
        function1.invoke(logMessage);
        logger.getBuffer().commit(logMessage2);
    }
}
