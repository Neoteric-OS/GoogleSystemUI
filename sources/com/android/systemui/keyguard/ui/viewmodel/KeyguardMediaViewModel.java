package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardMediaViewModel {
    public final ReadonlyStateFlow isMediaVisible;

    public KeyguardMediaViewModel(MediaCarouselInteractor mediaCarouselInteractor) {
        ReadonlyStateFlow readonlyStateFlow = mediaCarouselInteractor.hasActiveMediaOrRecommendation;
    }
}
