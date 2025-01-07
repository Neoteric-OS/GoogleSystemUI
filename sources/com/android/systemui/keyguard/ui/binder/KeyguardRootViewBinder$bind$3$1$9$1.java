package com.android.systemui.keyguard.ui.binder;

import android.view.ViewGroup;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$3;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardRootViewBinder$bind$3$1$9$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardClockInteractor $clockInteractor;
    final /* synthetic */ InteractionJankMonitor $jankMonitor;
    final /* synthetic */ KeyguardViewMediator $keyguardViewMediator;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ KeyguardRootViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardRootViewBinder$bind$3$1$9$1(KeyguardRootViewModel keyguardRootViewModel, KeyguardClockInteractor keyguardClockInteractor, ViewGroup viewGroup, InteractionJankMonitor interactionJankMonitor, KeyguardViewMediator keyguardViewMediator, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = keyguardRootViewModel;
        this.$clockInteractor = keyguardClockInteractor;
        this.$view = viewGroup;
        this.$jankMonitor = interactionJankMonitor;
        this.$keyguardViewMediator = keyguardViewMediator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardRootViewBinder$bind$3$1$9$1(this.$viewModel, this.$clockInteractor, this.$view, this.$jankMonitor, this.$keyguardViewMediator, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardRootViewBinder$bind$3$1$9$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flow = this.$viewModel.goneToAodTransition;
            KeyguardRootViewBinder$bind$3.AnonymousClass1.AnonymousClass8.C01321 c01321 = new KeyguardRootViewBinder$bind$3.AnonymousClass1.AnonymousClass8.C01321(this.$clockInteractor, this.$view, this.$jankMonitor, this.$keyguardViewMediator, 1);
            this.label = 1;
            if (flow.collect(c01321, this) == coroutineSingletons) {
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
