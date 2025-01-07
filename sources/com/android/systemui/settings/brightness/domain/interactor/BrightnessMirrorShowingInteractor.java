package com.android.systemui.settings.brightness.domain.interactor;

import com.android.systemui.settings.brightness.data.repository.BrightnessMirrorShowingRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BrightnessMirrorShowingInteractor {
    public final BrightnessMirrorShowingRepository brightnessMirrorShowingRepository;
    public final ReadonlyStateFlow isShowing;

    public BrightnessMirrorShowingInteractor(BrightnessMirrorShowingRepository brightnessMirrorShowingRepository) {
        this.isShowing = brightnessMirrorShowingRepository.isShowing;
    }
}
