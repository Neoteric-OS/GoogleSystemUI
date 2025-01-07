package com.android.systemui.keyguard.ui.viewmodel;

import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewModel$transitionAlpha$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardTransitionInteractor $transitionInteractor;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceEntryIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryIconViewModel$transitionAlpha$2(DeviceEntryIconViewModel deviceEntryIconViewModel, KeyguardTransitionInteractor keyguardTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryIconViewModel;
        this.$transitionInteractor = keyguardTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryIconViewModel$transitionAlpha$2 deviceEntryIconViewModel$transitionAlpha$2 = new DeviceEntryIconViewModel$transitionAlpha$2(this.this$0, this.$transitionInteractor, continuation);
        deviceEntryIconViewModel$transitionAlpha$2.L$0 = obj;
        return deviceEntryIconViewModel$transitionAlpha$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryIconViewModel$transitionAlpha$2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        float f;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            DeviceEntryIconViewModel deviceEntryIconViewModel = this.this$0;
            KeyguardState currentState = this.$transitionInteractor.getCurrentState();
            deviceEntryIconViewModel.getClass();
            switch (currentState.ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 6:
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    f = 0.0f;
                    break;
                case 4:
                case 5:
                case 7:
                    f = 1.0f;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Float f2 = new Float(f);
            this.label = 1;
            if (flowCollector.emit(f2, this) == coroutineSingletons) {
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
