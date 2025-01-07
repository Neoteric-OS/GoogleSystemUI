package com.android.systemui.log.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LogModule_ProvideNotificationLockScreenLogBufferFactory implements Provider {
    public static LogBuffer provideNotificationLockScreenLogBuffer(LogBufferFactory logBufferFactory) {
        logBufferFactory.getClass();
        return LogBufferFactory.create$default(logBufferFactory, "NotifLockscreenLog", 50, false, 8);
    }
}
