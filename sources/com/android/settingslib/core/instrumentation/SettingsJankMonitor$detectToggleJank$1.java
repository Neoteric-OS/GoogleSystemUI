package com.android.settingslib.core.instrumentation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SettingsJankMonitor$detectToggleJank$1 implements Runnable {
    public static final SettingsJankMonitor$detectToggleJank$1 INSTANCE = new SettingsJankMonitor$detectToggleJank$1();

    @Override // java.lang.Runnable
    public final void run() {
        SettingsJankMonitor.jankMonitor.end(57);
    }
}
