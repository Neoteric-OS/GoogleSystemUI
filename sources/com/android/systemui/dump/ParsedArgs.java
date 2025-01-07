package com.android.systemui.dump;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ParsedArgs {
    public String command;
    public String dumpPriority;
    public boolean listOnly;
    public boolean matchAll;
    public final List nonFlagArgs;
    public boolean proto;
    public final String[] rawArgs;
    public int tailLength;

    public ParsedArgs(String[] strArr, List list) {
        this.rawArgs = strArr;
        this.nonFlagArgs = list;
    }
}
