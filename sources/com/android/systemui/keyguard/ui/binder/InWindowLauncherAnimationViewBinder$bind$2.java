package com.android.systemui.keyguard.ui.binder;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ui.binder.InWindowLauncherAnimationViewBinder$bind$1;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.keyguard.ui.viewmodel.InWindowLauncherAnimationViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InWindowLauncherAnimationViewBinder$bind$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ InWindowLauncherUnlockAnimationManager $inWindowLauncherUnlockAnimationManager;
    final /* synthetic */ InWindowLauncherAnimationViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InWindowLauncherAnimationViewBinder$bind$2(InWindowLauncherAnimationViewModel inWindowLauncherAnimationViewModel, InWindowLauncherUnlockAnimationManager inWindowLauncherUnlockAnimationManager, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = inWindowLauncherAnimationViewModel;
        this.$inWindowLauncherUnlockAnimationManager = inWindowLauncherUnlockAnimationManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new InWindowLauncherAnimationViewBinder$bind$2(this.$viewModel, this.$inWindowLauncherUnlockAnimationManager, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((InWindowLauncherAnimationViewBinder$bind$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        ReadonlyStateFlow readonlyStateFlow = this.$viewModel.shouldStartInWindowAnimation;
        InWindowLauncherAnimationViewBinder$bind$1.AnonymousClass1 anonymousClass1 = new InWindowLauncherAnimationViewBinder$bind$1.AnonymousClass1(this.$inWindowLauncherUnlockAnimationManager, 1);
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
