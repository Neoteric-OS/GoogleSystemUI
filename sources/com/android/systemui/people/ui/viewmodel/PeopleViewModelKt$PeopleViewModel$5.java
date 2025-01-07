package com.android.systemui.people.ui.viewmodel;

import com.android.systemui.people.ui.viewmodel.PeopleViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PeopleViewModelKt$PeopleViewModel$5 extends FunctionReferenceImpl implements Function0 {
    final /* synthetic */ MutableStateFlow $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeopleViewModelKt$PeopleViewModel$5(StateFlowImpl stateFlowImpl) {
        super(0, Intrinsics.Kotlin.class, "onUserJourneyCancelled", "PeopleViewModel$onUserJourneyCancelled(Lkotlinx/coroutines/flow/MutableStateFlow;)V", 0);
        this.$result = stateFlowImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        MutableStateFlow mutableStateFlow = this.$result;
        PeopleViewModel.Result.Cancelled cancelled = PeopleViewModel.Result.Cancelled.INSTANCE;
        StateFlowImpl stateFlowImpl = (StateFlowImpl) mutableStateFlow;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, cancelled);
        return Unit.INSTANCE;
    }
}
