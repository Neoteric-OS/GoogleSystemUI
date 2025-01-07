package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewBinder$bind$1$onLongPressDetected$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ View $view;
    final /* synthetic */ DeviceEntryIconViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryIconViewBinder$bind$1$onLongPressDetected$1(View view, DeviceEntryIconViewModel deviceEntryIconViewModel, Continuation continuation) {
        super(2, continuation);
        this.$view = view;
        this.$viewModel = deviceEntryIconViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceEntryIconViewBinder$bind$1$onLongPressDetected$1(this.$view, this.$viewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryIconViewBinder$bind$1$onLongPressDetected$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.$view.clearFocus();
            this.$view.clearAccessibilityFocus();
            DeviceEntryIconViewModel deviceEntryIconViewModel = this.$viewModel;
            this.label = 1;
            if (deviceEntryIconViewModel.onUserInteraction(this) == coroutineSingletons) {
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
