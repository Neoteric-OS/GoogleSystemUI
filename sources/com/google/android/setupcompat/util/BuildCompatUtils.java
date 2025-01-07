package com.google.android.setupcompat.util;

import android.os.Build;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BuildCompatUtils {
    public static boolean isAtLeastU() {
        String str = Build.VERSION.CODENAME;
        return str.equals("REL") || (!str.equals("REL") && str.compareTo("UpsideDownCake") >= 0);
    }
}
