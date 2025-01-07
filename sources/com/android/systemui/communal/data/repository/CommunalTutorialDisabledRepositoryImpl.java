package com.android.systemui.communal.data.repository;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTutorialDisabledRepositoryImpl {
    public final ReadonlyStateFlow tutorialSettingState;

    public CommunalTutorialDisabledRepositoryImpl(CoroutineScope coroutineScope) {
        this.tutorialSettingState = FlowKt.stateIn(EmptyFlow.INSTANCE, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 10);
    }
}
