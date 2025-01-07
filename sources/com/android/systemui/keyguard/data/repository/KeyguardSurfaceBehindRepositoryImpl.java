package com.android.systemui.keyguard.data.repository;

import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSurfaceBehindRepositoryImpl {
    public final StateFlowImpl _isAnimatingSurface;
    public final StateFlowImpl _isSurfaceRemoteAnimationTargetAvailable;
    public final ReadonlyStateFlow isAnimatingSurface;
    public final ReadonlyStateFlow isSurfaceRemoteAnimationTargetAvailable;

    public KeyguardSurfaceBehindRepositoryImpl() {
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isAnimatingSurface = MutableStateFlow;
        this.isAnimatingSurface = new ReadonlyStateFlow(MutableStateFlow);
        this.isSurfaceRemoteAnimationTargetAvailable = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
    }
}
