package com.google.android.systemui.ailabs.domain;

import com.google.android.systemui.ailabs.ui.data.repository.WarpLockscreenRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WarpLockScreenInteractor {
    public final WarpLockscreenRepository repository;
    public final ReadonlyStateFlow warps;

    public WarpLockScreenInteractor(WarpLockscreenRepository warpLockscreenRepository) {
        this.warps = warpLockscreenRepository.warps;
    }
}
