package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DoNotDisturbQuickAffordanceConfig$lockScreenState$2 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DoNotDisturbQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoNotDisturbQuickAffordanceConfig$lockScreenState$2(DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = doNotDisturbQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DoNotDisturbQuickAffordanceConfig$lockScreenState$2 doNotDisturbQuickAffordanceConfig$lockScreenState$2 = new DoNotDisturbQuickAffordanceConfig$lockScreenState$2(this.this$0, continuation);
        doNotDisturbQuickAffordanceConfig$lockScreenState$2.L$0 = obj;
        return doNotDisturbQuickAffordanceConfig$lockScreenState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DoNotDisturbQuickAffordanceConfig$lockScreenState$2) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$lockScreenState$2$callback$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig = this.this$0;
            final ?? r1 = new ZenModeController.Callback() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$lockScreenState$2$callback$1
                @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
                public final void onZenAvailableChanged(boolean z) {
                    DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig2 = DoNotDisturbQuickAffordanceConfig.this;
                    doNotDisturbQuickAffordanceConfig2.oldIsAvailable = z;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(DoNotDisturbQuickAffordanceConfig.access$updateState(doNotDisturbQuickAffordanceConfig2));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "DoNotDisturbQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
                public final void onZenChanged(int i2) {
                    DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig2 = DoNotDisturbQuickAffordanceConfig.this;
                    doNotDisturbQuickAffordanceConfig2.zenMode = i2;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(DoNotDisturbQuickAffordanceConfig.access$updateState(doNotDisturbQuickAffordanceConfig2));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "DoNotDisturbQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            doNotDisturbQuickAffordanceConfig.zenMode = ((ZenModeControllerImpl) doNotDisturbQuickAffordanceConfig.controller).mZenMode;
            DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig2 = this.this$0;
            doNotDisturbQuickAffordanceConfig2.oldIsAvailable = ((ZenModeControllerImpl) doNotDisturbQuickAffordanceConfig2.controller).isZenAvailable();
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(DoNotDisturbQuickAffordanceConfig.access$updateState(this.this$0));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                FacePropertyRepositoryImpl$cameraInfo$1$callback$1$$ExternalSyntheticOutline0.m("Failed to send ", "updated state", " - downstream canceled or failed.", "DoNotDisturbQuickAffordanceConfig", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            ((ZenModeControllerImpl) this.this$0.controller).addCallback(r1);
            final DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig3 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$lockScreenState$2.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((ZenModeControllerImpl) DoNotDisturbQuickAffordanceConfig.this.controller).removeCallback(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
