package com.android.systemui.volume.panel.component.anc.domain.interactor;

import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.panel.component.anc.data.repository.AncSliceRepositoryImpl;
import com.android.systemui.volume.panel.component.anc.domain.model.AncSlices;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AncSliceInteractor {
    public final AncSliceRepositoryImpl ancSliceRepository;
    public final ReadonlyStateFlow ancSlices;
    public final AudioOutputInteractor audioOutputInteractor;
    public final StateFlowImpl buttonSliceWidth;
    public final StateFlowImpl popupSliceWidth;

    public AncSliceInteractor(AudioOutputInteractor audioOutputInteractor, AncSliceRepositoryImpl ancSliceRepositoryImpl, ContextScope contextScope) {
        this.audioOutputInteractor = audioOutputInteractor;
        this.ancSliceRepository = ancSliceRepositoryImpl;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(1);
        this.buttonSliceWidth = MutableStateFlow;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(1);
        this.popupSliceWidth = MutableStateFlow2;
        this.ancSlices = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.transformLatest(MutableStateFlow, new AncSliceInteractor$special$$inlined$flatMapLatest$1(null, this)), FlowKt.transformLatest(MutableStateFlow2, new AncSliceInteractor$special$$inlined$flatMapLatest$2(null, this)), new AncSliceInteractor$ancSlices$3(3, null)), contextScope, SharingStarted.Companion.Eagerly, AncSlices.Unavailable.INSTANCE);
    }
}
