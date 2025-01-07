package com.android.systemui.qs.panels.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PanelsModule_Companion_ProvidesPanelsLogFactory implements Provider {
    public static LogBuffer providesPanelsLog(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, "PanelsLog", 50, false, 12);
    }
}
