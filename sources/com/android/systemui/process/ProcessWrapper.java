package com.android.systemui.process;

import android.app.ActivityManager;
import android.os.Process;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProcessWrapper {
    public static boolean isForegroundUser() {
        return ActivityManager.getCurrentUser() == Process.myUserHandle().getIdentifier();
    }

    public static boolean isSystemUser() {
        return Process.myUserHandle().isSystem();
    }
}
