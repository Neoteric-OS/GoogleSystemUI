package com.android.systemui.demomode;

import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DemoMode extends DemoModeCommandReceiver {
    public static final List NO_COMMANDS = new ArrayList();
    public static final List COMMANDS = Lists.newArrayList(new String[]{"bars", "battery", "clock", "network", "notifications", "operator", "status", "volume"});

    default List demoCommands() {
        return NO_COMMANDS;
    }
}
