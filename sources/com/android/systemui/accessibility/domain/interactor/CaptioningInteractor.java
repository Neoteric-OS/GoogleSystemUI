package com.android.systemui.accessibility.domain.interactor;

import com.android.systemui.accessibility.data.repository.CaptioningRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CaptioningInteractor {
    public final CaptioningInteractor$special$$inlined$map$1 isSystemAudioCaptioningEnabled;
    public final CaptioningInteractor$special$$inlined$map$1 isSystemAudioCaptioningUiEnabled;
    public final CaptioningRepositoryImpl repository;

    public CaptioningInteractor(CaptioningRepositoryImpl captioningRepositoryImpl) {
        this.repository = captioningRepositoryImpl;
        ReadonlyStateFlow readonlyStateFlow = captioningRepositoryImpl.captioningModel;
        this.isSystemAudioCaptioningEnabled = new CaptioningInteractor$special$$inlined$map$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(readonlyStateFlow), 0);
        this.isSystemAudioCaptioningUiEnabled = new CaptioningInteractor$special$$inlined$map$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(readonlyStateFlow), 1);
    }
}
