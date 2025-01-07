package com.android.systemui.statusbar.chips.screenrecord.domain.interactor;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository;
import com.android.systemui.screenrecord.data.repository.ScreenRecordRepositoryImpl;
import com.android.systemui.statusbar.chips.screenrecord.domain.model.ScreenRecordChipModel;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordChipInteractor {
    public final LogBuffer logger;
    public final CoroutineScope scope;
    public final ScreenRecordRepositoryImpl screenRecordRepository;
    public final ReadonlyStateFlow screenRecordState;

    public ScreenRecordChipInteractor(CoroutineScope coroutineScope, ScreenRecordRepositoryImpl screenRecordRepositoryImpl, MediaProjectionManagerRepository mediaProjectionManagerRepository, LogBuffer logBuffer) {
        this.scope = coroutineScope;
        this.screenRecordRepository = screenRecordRepositoryImpl;
        this.logger = logBuffer;
        this.screenRecordState = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(screenRecordRepositoryImpl.screenRecordState, mediaProjectionManagerRepository.mediaProjectionState, new ScreenRecordChipInteractor$screenRecordState$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ScreenRecordChipModel.DoingNothing.INSTANCE);
    }

    public final void stopRecording() {
        BuildersKt.launch$default(this.scope, null, null, new ScreenRecordChipInteractor$stopRecording$1(this, null), 3);
    }
}
