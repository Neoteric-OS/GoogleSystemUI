package com.android.systemui.log.echo;

import com.android.systemui.log.core.LogLevel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ParsedOverride {
    public final LogLevel level;
    public final String name;
    public final EchoOverrideType type;

    public ParsedOverride(EchoOverrideType echoOverrideType, String str, LogLevel logLevel) {
        this.type = echoOverrideType;
        this.name = str;
        this.level = logLevel;
    }
}
