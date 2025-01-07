package com.android.systemui.volume;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Util extends com.android.settingslib.volume.Util {
    public static String logTag(Class cls) {
        String concat = "vol.".concat(cls.getSimpleName());
        return concat.length() < 23 ? concat : concat.substring(0, 23);
    }

    public static String ringerModeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "RINGER_MODE_UNKNOWN_") : "RINGER_MODE_NORMAL" : "RINGER_MODE_VIBRATE" : "RINGER_MODE_SILENT";
    }
}
