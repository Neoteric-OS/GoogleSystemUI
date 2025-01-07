package com.android.systemui.flags;

import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlagsFactory {
    public static final FlagsFactory INSTANCE = null;
    public static final Map flagMap = new LinkedHashMap();

    public static ReleasedFlag releasedFlag$default(String str) {
        ReleasedFlag releasedFlag = new ReleasedFlag(str, "systemui");
        flagMap.put(str, releasedFlag);
        return releasedFlag;
    }

    public static ResourceBooleanFlag resourceBooleanFlag$default(int i, String str) {
        ResourceBooleanFlag resourceBooleanFlag = new ResourceBooleanFlag(str, i);
        flagMap.put(str, resourceBooleanFlag);
        return resourceBooleanFlag;
    }

    public static SysPropBooleanFlag sysPropBooleanFlag$default(String str, boolean z) {
        SysPropBooleanFlag sysPropBooleanFlag = new SysPropBooleanFlag(str, z);
        flagMap.put(str, sysPropBooleanFlag);
        return sysPropBooleanFlag;
    }

    public static UnreleasedFlag unreleasedFlag$default(int i, String str) {
        return new UnreleasedFlag(str);
    }
}
