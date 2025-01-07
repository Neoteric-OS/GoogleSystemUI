package com.android.systemui.people.ui.viewmodel;

import android.content.Intent;
import com.android.systemui.people.data.repository.PeopleWidgetRepository;
import com.android.systemui.people.data.repository.PeopleWidgetRepositoryImpl;
import com.android.systemui.people.ui.viewmodel.PeopleViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class PeopleViewModelKt$PeopleViewModel$4 extends FunctionReferenceImpl implements Function1 {
    final /* synthetic */ MutableStateFlow $appWidgetId;
    final /* synthetic */ MutableStateFlow $result;
    final /* synthetic */ PeopleWidgetRepository $widgetRepository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeopleViewModelKt$PeopleViewModel$4(StateFlowImpl stateFlowImpl, PeopleWidgetRepository peopleWidgetRepository, StateFlowImpl stateFlowImpl2) {
        super(1, Intrinsics.Kotlin.class, "onTileClicked", "PeopleViewModel$onTileClicked(Lkotlinx/coroutines/flow/MutableStateFlow;Lcom/android/systemui/people/data/repository/PeopleWidgetRepository;Lkotlinx/coroutines/flow/MutableStateFlow;Lcom/android/systemui/people/ui/viewmodel/PeopleTileViewModel;)V", 0);
        this.$appWidgetId = stateFlowImpl;
        this.$widgetRepository = peopleWidgetRepository;
        this.$result = stateFlowImpl2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        MutableStateFlow mutableStateFlow = this.$appWidgetId;
        PeopleWidgetRepository peopleWidgetRepository = this.$widgetRepository;
        MutableStateFlow mutableStateFlow2 = this.$result;
        StateFlowImpl stateFlowImpl = (StateFlowImpl) mutableStateFlow;
        ((PeopleWidgetRepositoryImpl) peopleWidgetRepository).peopleSpaceWidgetManager.addNewWidget(((Number) stateFlowImpl.getValue()).intValue(), ((PeopleTileViewModel) obj).key);
        Intent intent = new Intent();
        intent.putExtra("appWidgetId", ((Number) stateFlowImpl.getValue()).intValue());
        PeopleViewModel.Result.Success success = new PeopleViewModel.Result.Success(intent);
        StateFlowImpl stateFlowImpl2 = (StateFlowImpl) mutableStateFlow2;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, success);
        return Unit.INSTANCE;
    }
}
