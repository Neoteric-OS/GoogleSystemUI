package com.android.systemui.statusbar.pipeline.dagger;

import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.log.table.TableLogBufferFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPipelineModule_Companion_ProvideAirplaneTableLogBufferFactory implements Provider {
    public static TableLogBuffer provideAirplaneTableLogBuffer(TableLogBufferFactory tableLogBufferFactory) {
        return tableLogBufferFactory.create(30, "AirplaneTableLog");
    }
}
