package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.KeyguardSurfaceBehindInteractor;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSurfaceBehindViewModel {
    public final Flow surfaceBehindViewParams;

    public KeyguardSurfaceBehindViewModel(KeyguardSurfaceBehindInteractor keyguardSurfaceBehindInteractor) {
        Flow flow = keyguardSurfaceBehindInteractor.viewParams;
    }
}
