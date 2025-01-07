package com.android.systemui.media.controls.ui.controller;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselControllerLogger {
    public final LogBuffer buffer;

    public MediaCarouselControllerLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void logPotentialMemoryLeak(String str) {
        LogLevel logLevel = LogLevel.DEBUG;
        MediaCarouselControllerLogger$logPotentialMemoryLeak$2 mediaCarouselControllerLogger$logPotentialMemoryLeak$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.MediaCarouselControllerLogger$logPotentialMemoryLeak$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Potential memory leak: Removing control panel for ", ((LogMessage) obj).getStr1(), " from map without calling #onDestroy");
            }
        };
        LogBuffer logBuffer = this.buffer;
        LogMessage obtain = logBuffer.obtain("MediaCarouselCtlrLog", logLevel, mediaCarouselControllerLogger$logPotentialMemoryLeak$2, null);
        ((LogMessageImpl) obtain).str1 = str;
        logBuffer.commit(obtain);
    }
}
