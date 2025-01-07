package com.android.systemui.log.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LogModule_ProvideShadeWindowLogBufferFactory implements Provider {
    public static LogBuffer provideShadeWindowLogBuffer(LogBufferFactory logBufferFactory) {
        logBufferFactory.getClass();
        return LogBufferFactory.create$default(logBufferFactory, "ShadeWindowLog", 600, false, 8);
    }
}
