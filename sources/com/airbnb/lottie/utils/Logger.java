package com.airbnb.lottie.utils;

import android.util.Log;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Logger {
    public static final LogcatLogger INSTANCE = new LogcatLogger();

    public static void debug() {
        INSTANCE.getClass();
    }

    public static void warning(String str) {
        INSTANCE.getClass();
        Set set = LogcatLogger.loggedMessages;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, null);
        set.add(str);
    }

    public static void warning(String str, Throwable th) {
        INSTANCE.getClass();
        Set set = LogcatLogger.loggedMessages;
        if (set.contains(str)) {
            return;
        }
        Log.w("LOTTIE", str, th);
        set.add(str);
    }
}
