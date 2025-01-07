package com.android.systemui.volume.domain.interactor;

import com.android.settingslib.volume.data.repository.AudioSharingRepositoryEmptyImpl;
import com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioSharingInteractorImpl implements AudioSharingInteractor {
    public final AudioSharingRepositoryEmptyImpl audioSharingRepository;
    public final AudioVolumeInteractor audioVolumeInteractor;
    public final CoroutineContext backgroundCoroutineContext;
    public final StateFlow isInAudioSharing;

    public AudioSharingInteractorImpl(CoroutineScope coroutineScope, CoroutineContext coroutineContext, AudioVolumeInteractor audioVolumeInteractor, AudioSharingRepositoryEmptyImpl audioSharingRepositoryEmptyImpl) {
        this.isInAudioSharing = audioSharingRepositoryEmptyImpl.inAudioSharing;
        FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(audioSharingRepositoryEmptyImpl.secondaryGroupId, audioSharingRepositoryEmptyImpl.volumeMap, new AudioSharingInteractorImpl$volume$1(3, null)));
    }

    @Override // com.android.systemui.volume.domain.interactor.AudioSharingInteractor
    public final Flow isInAudioSharing() {
        return this.isInAudioSharing;
    }
}
