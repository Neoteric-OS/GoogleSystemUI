package com.android.systemui.log.dagger;

import android.os.Build;
import com.android.systemui.log.LogcatEchoTracker;
import com.android.systemui.log.echo.LogcatEchoTrackerDebug;
import com.android.systemui.log.echo.LogcatEchoTrackerProd;
import dagger.Lazy;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LogModule_ProvideLogcatEchoTrackerFactory implements Provider {
    public static LogcatEchoTracker provideLogcatEchoTracker(Lazy lazy) {
        if (!Build.isDebuggable()) {
            return new LogcatEchoTrackerProd();
        }
        LogcatEchoTrackerDebug logcatEchoTrackerDebug = (LogcatEchoTrackerDebug) lazy.get();
        logcatEchoTrackerDebug.start();
        return logcatEchoTrackerDebug;
    }
}
