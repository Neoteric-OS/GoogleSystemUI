package com.android.systemui.navigation.data.repository;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationRepository {
    public final NavigationModeController controller;
    public final Flow isGesturalMode = FlowConflatedKt.conflatedCallbackFlow(new NavigationRepository$isGesturalMode$1(this, null));

    public NavigationRepository(NavigationModeController navigationModeController) {
        this.controller = navigationModeController;
    }
}
