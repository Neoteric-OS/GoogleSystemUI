package com.android.systemui.volume.panel.domain.interactor;

import com.android.systemui.volume.panel.data.repository.VolumePanelGlobalStateRepository;
import com.android.systemui.volume.panel.shared.model.VolumePanelGlobalState;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelGlobalStateInteractor {
    public final VolumePanelGlobalStateRepository repository;

    public VolumePanelGlobalStateInteractor(VolumePanelGlobalStateRepository volumePanelGlobalStateRepository) {
        this.repository = volumePanelGlobalStateRepository;
    }

    public final void setVisible(final boolean z) {
        Object value;
        Function1 function1 = new Function1() { // from class: com.android.systemui.volume.panel.domain.interactor.VolumePanelGlobalStateInteractor$setVisible$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return new VolumePanelGlobalState(z);
            }
        };
        VolumePanelGlobalStateRepository volumePanelGlobalStateRepository = this.repository;
        StateFlowImpl stateFlowImpl = volumePanelGlobalStateRepository.mutableGlobalState;
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, function1.invoke(value)));
        volumePanelGlobalStateRepository.logger.onVolumePanelGlobalStateChanged((VolumePanelGlobalState) stateFlowImpl.getValue());
    }
}
