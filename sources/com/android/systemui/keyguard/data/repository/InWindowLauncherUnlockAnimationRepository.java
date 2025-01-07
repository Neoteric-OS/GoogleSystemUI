package com.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InWindowLauncherUnlockAnimationRepository {
    public final StateFlowImpl launcherActivityClass;
    public final StateFlowImpl launcherSmartspaceState;
    public final StateFlowImpl startedUnlockAnimation = StateFlowKt.MutableStateFlow(Boolean.FALSE);

    public InWindowLauncherUnlockAnimationRepository() {
        StateFlowKt.MutableStateFlow(null);
        this.launcherActivityClass = StateFlowKt.MutableStateFlow(null);
        this.launcherSmartspaceState = StateFlowKt.MutableStateFlow(null);
    }
}
