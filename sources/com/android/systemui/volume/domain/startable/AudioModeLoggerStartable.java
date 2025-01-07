package com.android.systemui.volume.domain.startable;

import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.systemui.volume.panel.domain.VolumePanelStartable;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioModeLoggerStartable implements VolumePanelStartable {
    public final AudioModeInteractor audioModeInteractor;
    public final ContextScope scope;
    public final UiEventLogger uiEventLogger;

    public AudioModeLoggerStartable(ContextScope contextScope, UiEventLogger uiEventLogger, AudioModeInteractor audioModeInteractor) {
        this.scope = contextScope;
        this.uiEventLogger = uiEventLogger;
        this.audioModeInteractor = audioModeInteractor;
    }

    @Override // com.android.systemui.volume.panel.domain.VolumePanelStartable
    public final void start() {
        BuildersKt.launch$default(this.scope, null, null, new AudioModeLoggerStartable$start$1(this, null), 3);
    }
}
