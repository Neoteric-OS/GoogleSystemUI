package com.android.systemui.biometrics.data.repository;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FaceUserSettingsRepositoryImpl$watch$1 extends Lambda implements Function1 {
    final /* synthetic */ MutableStateFlow $toUpdate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceUserSettingsRepositoryImpl$watch$1(StateFlowImpl stateFlowImpl) {
        super(1);
        this.$toUpdate = stateFlowImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        StateFlowImpl stateFlowImpl = (StateFlowImpl) this.$toUpdate;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        return Unit.INSTANCE;
    }
}
