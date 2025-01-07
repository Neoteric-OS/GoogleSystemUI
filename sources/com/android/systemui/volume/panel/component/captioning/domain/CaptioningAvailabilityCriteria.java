package com.android.systemui.volume.panel.component.captioning.domain;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.accessibility.domain.interactor.CaptioningInteractor;
import com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CaptioningAvailabilityCriteria implements ComponentAvailabilityCriteria {
    public final ReadonlyStateFlow availability;
    public final ContextScope scope;
    public final UiEventLogger uiEventLogger;

    public CaptioningAvailabilityCriteria(CaptioningInteractor captioningInteractor, ContextScope contextScope, UiEventLogger uiEventLogger) {
        this.uiEventLogger = uiEventLogger;
        this.availability = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(captioningInteractor.isSystemAudioCaptioningUiEnabled, new CaptioningAvailabilityCriteria$availability$1(this, null), 0), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }

    @Override // com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria
    public final Flow isAvailable() {
        return this.availability;
    }
}
