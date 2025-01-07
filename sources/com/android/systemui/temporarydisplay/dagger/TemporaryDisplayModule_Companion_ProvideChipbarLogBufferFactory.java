package com.android.systemui.temporarydisplay.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TemporaryDisplayModule_Companion_ProvideChipbarLogBufferFactory implements Provider {
    public static LogBuffer provideChipbarLogBuffer(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, "ChipbarLog", 40, false, 12);
    }
}
