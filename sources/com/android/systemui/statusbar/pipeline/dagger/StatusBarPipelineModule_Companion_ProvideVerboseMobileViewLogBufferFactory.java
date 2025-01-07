package com.android.systemui.statusbar.pipeline.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPipelineModule_Companion_ProvideVerboseMobileViewLogBufferFactory implements Provider {
    public static LogBuffer provideVerboseMobileViewLogBuffer(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, "VerboseMobileViewLog", 100, false, 12);
    }
}
