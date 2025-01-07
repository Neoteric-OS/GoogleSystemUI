package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DeferredCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class PowerSaveState$refreshStatus$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PowerSaveState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PowerSaveState$refreshStatus$1(PowerSaveState powerSaveState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = powerSaveState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PowerSaveState$refreshStatus$1 powerSaveState$refreshStatus$1 = new PowerSaveState$refreshStatus$1(this.this$0, continuation);
        powerSaveState$refreshStatus$1.L$0 = obj;
        return powerSaveState$refreshStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PowerSaveState$refreshStatus$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Deferred async$default;
        PowerSaveState powerSaveState;
        PowerSaveState powerSaveState2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            PowerSaveState powerSaveState3 = this.this$0;
            DeferredCoroutine async$default2 = BuildersKt.async$default(coroutineScope, powerSaveState3.bgDispatcher, new PowerSaveState$refreshStatus$1$newBatterySaverEnabled$1(powerSaveState3, null), 2);
            PowerSaveState powerSaveState4 = this.this$0;
            async$default = BuildersKt.async$default(coroutineScope, powerSaveState4.bgDispatcher, new PowerSaveState$refreshStatus$1$newIsDeviceInteractive$1(powerSaveState4, null), 2);
            PowerSaveState powerSaveState5 = this.this$0;
            this.L$0 = async$default;
            this.L$1 = powerSaveState5;
            this.label = 1;
            Object awaitInternal = async$default2.awaitInternal(this);
            if (awaitInternal == coroutineSingletons) {
                return coroutineSingletons;
            }
            powerSaveState = powerSaveState5;
            obj = awaitInternal;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                powerSaveState2 = (PowerSaveState) this.L$0;
                ResultKt.throwOnFailure(obj);
                powerSaveState2.isDeviceInteractive = ((Boolean) obj).booleanValue();
                PowerSaveState powerSaveState6 = this.this$0;
                powerSaveState6.setBlocking((powerSaveState6.batterySaverEnabled || powerSaveState6.isDeviceInteractive) ? false : true);
                return Unit.INSTANCE;
            }
            powerSaveState = (PowerSaveState) this.L$1;
            async$default = (Deferred) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        powerSaveState.batterySaverEnabled = ((Boolean) obj).booleanValue();
        PowerSaveState powerSaveState7 = this.this$0;
        this.L$0 = powerSaveState7;
        this.L$1 = null;
        this.label = 2;
        Object await = async$default.await(this);
        if (await == coroutineSingletons) {
            return coroutineSingletons;
        }
        powerSaveState2 = powerSaveState7;
        obj = await;
        powerSaveState2.isDeviceInteractive = ((Boolean) obj).booleanValue();
        PowerSaveState powerSaveState62 = this.this$0;
        powerSaveState62.setBlocking((powerSaveState62.batterySaverEnabled || powerSaveState62.isDeviceInteractive) ? false : true);
        return Unit.INSTANCE;
    }
}
