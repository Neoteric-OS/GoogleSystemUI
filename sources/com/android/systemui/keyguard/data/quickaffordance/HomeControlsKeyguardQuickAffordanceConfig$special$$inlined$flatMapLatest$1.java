package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ HomeControlsKeyguardQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1(Continuation continuation, HomeControlsKeyguardQuickAffordanceConfig homeControlsKeyguardQuickAffordanceConfig) {
        super(3, continuation);
        this.this$0 = homeControlsKeyguardQuickAffordanceConfig;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        HomeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 homeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1 = new HomeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        homeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        homeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return homeControlsKeyguardQuickAffordanceConfig$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            boolean booleanValue = ((Boolean) this.L$1).booleanValue();
            KeyguardQuickAffordanceConfig.LockScreenState.Hidden hidden = KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE;
            if (booleanValue) {
                HomeControlsKeyguardQuickAffordanceConfig homeControlsKeyguardQuickAffordanceConfig = this.this$0;
                ControlsListingController controlsListingController = (ControlsListingController) homeControlsKeyguardQuickAffordanceConfig.component.controlsListingController.orElse(null);
                homeControlsKeyguardQuickAffordanceConfig.getClass();
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = controlsListingController == null ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(hidden) : FlowConflatedKt.conflatedCallbackFlow(new HomeControlsKeyguardQuickAffordanceConfig$stateInternal$1(controlsListingController, homeControlsKeyguardQuickAffordanceConfig, null));
            } else {
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(hidden);
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2, this) == coroutineSingletons) {
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
