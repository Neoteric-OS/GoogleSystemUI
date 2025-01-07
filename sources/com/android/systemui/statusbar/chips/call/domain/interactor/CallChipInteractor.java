package com.android.systemui.statusbar.chips.call.domain.interactor;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.phone.ongoingcall.data.repository.OngoingCallRepository;
import com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CallChipInteractor {
    public final LogBuffer logger;
    public final ReadonlyStateFlow ongoingCallState;

    public CallChipInteractor(CoroutineScope coroutineScope, OngoingCallRepository ongoingCallRepository, LogBuffer logBuffer) {
        this.logger = logBuffer;
        this.ongoingCallState = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(ongoingCallRepository.ongoingCallState, new CallChipInteractor$ongoingCallState$1(this, null), 0), coroutineScope, SharingStarted.Companion.Lazily, OngoingCallModel.NoCall.INSTANCE);
    }
}
