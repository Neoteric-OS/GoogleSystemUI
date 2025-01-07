package com.android.systemui.volume.domain.interactor;

import com.android.systemui.volume.data.repository.VolumeDialogRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeDialogInteractor {
    public final ReadonlyStateFlow isDialogVisible;
    public final VolumeDialogRepository repository;

    public VolumeDialogInteractor(VolumeDialogRepository volumeDialogRepository) {
        this.repository = volumeDialogRepository;
        this.isDialogVisible = volumeDialogRepository.isDialogVisible;
    }
}
