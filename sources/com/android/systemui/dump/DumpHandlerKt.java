package com.android.systemui.dump;

import android.icu.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DumpHandlerKt {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
    public static final String[] PRIORITY_OPTIONS = {"CRITICAL", "NORMAL"};
    public static final String[] COMMANDS = {"bugreport-critical", "bugreport-normal", "buffers", "dumpables", "tables", "config", "help"};
}
