package com.android.systemui.people.ui.compose;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.people.ui.viewmodel.PeopleViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PeopleScreenKt$PeopleScreen$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $onResult;
    final /* synthetic */ PeopleViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeopleScreenKt$PeopleScreen$1(PeopleViewModel peopleViewModel, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = peopleViewModel;
        this.$onResult = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new PeopleScreenKt$PeopleScreen$1(this.$viewModel, this.$onResult, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((PeopleScreenKt$PeopleScreen$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        final PeopleViewModel peopleViewModel = this.$viewModel;
        ReadonlyStateFlow readonlyStateFlow = peopleViewModel.result;
        final Function1 function1 = this.$onResult;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.people.ui.compose.PeopleScreenKt$PeopleScreen$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                PeopleViewModel.Result result = (PeopleViewModel.Result) obj2;
                if (result != null) {
                    PeopleViewModel.this.clearResult.invoke();
                    function1.invoke(result);
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        readonlyStateFlow.collect(flowCollector, this);
        return coroutineSingletons;
    }
}
