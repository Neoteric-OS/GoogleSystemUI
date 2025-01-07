package com.android.systemui.util.concurrency;

import android.os.Handler;
import android.os.Looper;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUIConcurrencyModule_ProvideBgHandlerFactory implements Provider {
    public static Handler provideBgHandler(Looper looper) {
        return new Handler(looper);
    }
}
