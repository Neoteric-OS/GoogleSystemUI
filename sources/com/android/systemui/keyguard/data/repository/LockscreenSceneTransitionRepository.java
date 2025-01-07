package com.android.systemui.keyguard.data.repository;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LockscreenSceneTransitionRepository {
    public static final KeyguardState DEFAULT_STATE = KeyguardState.LOCKSCREEN;
    public final StateFlowImpl nextLockscreenTargetState = StateFlowKt.MutableStateFlow(DEFAULT_STATE);
}
