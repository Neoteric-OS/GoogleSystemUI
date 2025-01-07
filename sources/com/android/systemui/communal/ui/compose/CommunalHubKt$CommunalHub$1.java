package com.android.systemui.communal.ui.compose;

import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalHubKt$CommunalHub$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BaseCommunalViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalHubKt$CommunalHub$1(BaseCommunalViewModel baseCommunalViewModel, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = baseCommunalViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalHubKt$CommunalHub$1(this.$viewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalHubKt$CommunalHub$1 communalHubKt$CommunalHub$1 = (CommunalHubKt$CommunalHub$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalHubKt$CommunalHub$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (!this.$viewModel.isEditMode()) {
            CommunalInteractor communalInteractor = this.$viewModel.communalInteractor;
            communalInteractor._firstVisibleItemIndex = 0;
            communalInteractor._firstVisibleItemOffset = 0;
        }
        return Unit.INSTANCE;
    }
}
