package com.android.systemui.flags;

import dagger.internal.Provider;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlagsCommonModule_ProvidesAllFlagsFactory implements Provider {
    public static Map providesAllFlags() {
        FlagsFactory flagsFactory = FlagsFactory.INSTANCE;
        Map map = FlagsFactory.flagMap;
        map.containsKey(Flags.NULL_FLAG.name);
        return map;
    }
}
