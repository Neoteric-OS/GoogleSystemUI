package com.android.systemui.settings;

import android.hardware.display.DisplayManager;
import android.os.Handler;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MultiUserUtilsModule_ProvideDisplayTrackerFactory implements Provider {
    public static DisplayTrackerImpl provideDisplayTracker(DisplayManager displayManager, Handler handler) {
        return new DisplayTrackerImpl(displayManager, handler);
    }
}
