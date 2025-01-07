package com.google.android.systemui.volume.panel.component.clearcalling.domain;

import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria;
import com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor;
import com.google.android.systemui.volume.panel.component.shared.availabilitycriteria.GoogleComponentAvailabilityCriteria;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClearCallingAvailabilityCriteria implements ComponentAvailabilityCriteria {
    public final ReadonlySharedFlow availability;

    public ClearCallingAvailabilityCriteria(GoogleComponentAvailabilityCriteria googleComponentAvailabilityCriteria, AudioModeInteractor audioModeInteractor, ClearCallingInteractor clearCallingInteractor, ContextScope contextScope, UiEventLogger uiEventLogger) {
        this.availability = FlowKt.shareIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(googleComponentAvailabilityCriteria.isAvailable(), audioModeInteractor.isOngoingCall, new ClearCallingAvailabilityCriteria$availability$1(clearCallingInteractor, null))), new ClearCallingAvailabilityCriteria$availability$2(uiEventLogger, null), 0), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
    }

    @Override // com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria
    public final Flow isAvailable() {
        return this.availability;
    }
}
