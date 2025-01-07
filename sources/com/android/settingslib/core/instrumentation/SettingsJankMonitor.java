package com.android.settingslib.core.instrumentation;

import com.android.internal.jank.InteractionJankMonitor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SettingsJankMonitor {
    public static final InteractionJankMonitor jankMonitor = InteractionJankMonitor.getInstance();
    public static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static /* synthetic */ void getMONITORED_ANIMATION_DURATION_MS$annotations() {
    }
}
