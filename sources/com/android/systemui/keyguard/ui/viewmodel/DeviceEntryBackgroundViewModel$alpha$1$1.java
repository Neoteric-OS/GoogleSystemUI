package com.android.systemui.keyguard.ui.viewmodel;

import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryBackgroundViewModel$alpha$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardTransitionInteractor $keyguardTransitionInteractor;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryBackgroundViewModel$alpha$1$1(KeyguardTransitionInteractor keyguardTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.$keyguardTransitionInteractor = keyguardTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryBackgroundViewModel$alpha$1$1 deviceEntryBackgroundViewModel$alpha$1$1 = new DeviceEntryBackgroundViewModel$alpha$1$1(this.$keyguardTransitionInteractor, continuation);
        deviceEntryBackgroundViewModel$alpha$1$1.L$0 = obj;
        return deviceEntryBackgroundViewModel$alpha$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryBackgroundViewModel$alpha$1$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            switch (((KeyguardState) CollectionsKt.last(this.$keyguardTransitionInteractor.currentKeyguardState.getReplayCache())).ordinal()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                case 8:
                case 9:
                case 10:
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    Float f = new Float(0.0f);
                    this.label = 1;
                    if (flowCollector.emit(f, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                    break;
                case 5:
                case 7:
                    Float f2 = new Float(1.0f);
                    this.label = 2;
                    if (flowCollector.emit(f2, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                    break;
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
