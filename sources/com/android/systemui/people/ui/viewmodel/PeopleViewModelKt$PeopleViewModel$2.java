package com.android.systemui.people.ui.viewmodel;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PeopleViewModelKt$PeopleViewModel$2 extends FunctionReferenceImpl implements Function1 {
    final /* synthetic */ MutableStateFlow $appWidgetId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeopleViewModelKt$PeopleViewModel$2(StateFlowImpl stateFlowImpl) {
        super(1, Intrinsics.Kotlin.class, "onWidgetIdChanged", "PeopleViewModel$onWidgetIdChanged(Lkotlinx/coroutines/flow/MutableStateFlow;I)V", 0);
        this.$appWidgetId = stateFlowImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int intValue = ((Number) obj).intValue();
        MutableStateFlow mutableStateFlow = this.$appWidgetId;
        Integer valueOf = Integer.valueOf(intValue);
        StateFlowImpl stateFlowImpl = (StateFlowImpl) mutableStateFlow;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        return Unit.INSTANCE;
    }
}
