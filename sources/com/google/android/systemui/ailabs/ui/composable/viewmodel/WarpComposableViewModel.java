package com.google.android.systemui.ailabs.ui.composable.viewmodel;

import com.google.android.systemui.ailabs.domain.WarpLockScreenInteractor;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WarpComposableViewModel {
    public final WarpLockScreenInteractor interactor;
    public final ReadonlyStateFlow warps;

    public WarpComposableViewModel(WarpLockScreenInteractor warpLockScreenInteractor) {
        ReadonlyStateFlow readonlyStateFlow = warpLockScreenInteractor.warps;
    }
}
