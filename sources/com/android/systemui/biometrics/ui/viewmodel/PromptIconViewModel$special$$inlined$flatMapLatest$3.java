package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl;
import com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel$special$$inlined$flatMapLatest$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ PromptSelectorInteractor $promptSelectorInteractor$inlined;
    final /* synthetic */ PromptViewModel $promptViewModel$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$special$$inlined$flatMapLatest$3(PromptSelectorInteractor promptSelectorInteractor, PromptIconViewModel promptIconViewModel, PromptViewModel promptViewModel, Continuation continuation) {
        super(3, continuation);
        this.$promptSelectorInteractor$inlined = promptSelectorInteractor;
        this.$promptViewModel$inlined = promptViewModel;
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptSelectorInteractor promptSelectorInteractor = this.$promptSelectorInteractor$inlined;
        PromptViewModel promptViewModel = this.$promptViewModel$inlined;
        PromptIconViewModel$special$$inlined$flatMapLatest$3 promptIconViewModel$special$$inlined$flatMapLatest$3 = new PromptIconViewModel$special$$inlined$flatMapLatest$3(promptSelectorInteractor, this.this$0, promptViewModel, (Continuation) obj3);
        promptIconViewModel$special$$inlined$flatMapLatest$3.L$0 = (FlowCollector) obj;
        promptIconViewModel$special$$inlined$flatMapLatest$3.L$1 = obj2;
        return promptIconViewModel$special$$inlined$flatMapLatest$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int ordinal = ((PromptIconViewModel.AuthType) this.L$1).ordinal();
            if (ordinal == 0) {
                StateFlow stateFlow = ((PromptSelectorInteractorImpl) this.$promptSelectorInteractor$inlined).fingerprintSensorType;
                PromptViewModel promptViewModel = this.$promptViewModel$inlined;
                combine = FlowKt.combine(stateFlow, promptViewModel.isAuthenticated, promptViewModel.isAuthenticating, promptViewModel.showingError, new PromptIconViewModel$shouldAnimateIconView$1$1(this.this$0, null));
            } else if (ordinal == 1) {
                PromptViewModel promptViewModel2 = this.$promptViewModel$inlined;
                combine = FlowKt.combine(promptViewModel2.isAuthenticated, promptViewModel2.isAuthenticating, promptViewModel2.showingError, new PromptIconViewModel$shouldAnimateIconView$1$2(this.this$0, null));
            } else {
                if (ordinal != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                StateFlow stateFlow2 = ((PromptSelectorInteractorImpl) this.$promptSelectorInteractor$inlined).fingerprintSensorType;
                PromptViewModel promptViewModel3 = this.$promptViewModel$inlined;
                combine = FlowKt.combine(stateFlow2, promptViewModel3.isAuthenticated, promptViewModel3.isAuthenticating, promptViewModel3.isPendingConfirmation, promptViewModel3.showingError, new PromptIconViewModel$shouldAnimateIconView$1$3(this.this$0, null));
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, combine, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
