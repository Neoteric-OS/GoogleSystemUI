package com.google.android.systemui.volume.domain.startable;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.panel.domain.VolumePanelStartable;
import com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaDeviceLoggerStartable implements VolumePanelStartable {
    public final AudioOutputInteractor audioOutputInteractor;
    public final PixelDeviceInteractor pixelDeviceInteractor;
    public final ContextScope scope;
    public final UiEventLogger uiEventLogger;

    public MediaDeviceLoggerStartable(ContextScope contextScope, PixelDeviceInteractor pixelDeviceInteractor, AudioOutputInteractor audioOutputInteractor, UiEventLogger uiEventLogger) {
        this.scope = contextScope;
        this.pixelDeviceInteractor = pixelDeviceInteractor;
        this.audioOutputInteractor = audioOutputInteractor;
        this.uiEventLogger = uiEventLogger;
    }

    @Override // com.android.systemui.volume.panel.domain.VolumePanelStartable
    public final void start() {
        MediaDeviceLoggerStartable$start$1 mediaDeviceLoggerStartable$start$1 = new MediaDeviceLoggerStartable$start$1(this, null);
        ContextScope contextScope = this.scope;
        BuildersKt.launch$default(contextScope, null, null, mediaDeviceLoggerStartable$start$1, 3);
        BuildersKt.launch$default(contextScope, null, null, new MediaDeviceLoggerStartable$start$2(this, null), 3);
        BuildersKt.launch$default(contextScope, null, null, new MediaDeviceLoggerStartable$start$3(this, null), 3);
    }
}
