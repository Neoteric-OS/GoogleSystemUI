package com.android.systemui.volume.domain.interactor;

import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioSharingInteractorEmptyImpl implements AudioSharingInteractor {
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isInAudioSharing = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);

    @Override // com.android.systemui.volume.domain.interactor.AudioSharingInteractor
    public final Flow isInAudioSharing() {
        return this.isInAudioSharing;
    }
}
