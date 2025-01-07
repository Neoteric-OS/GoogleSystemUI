package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthService$binder$1$unregisterHealthListener$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $callerPackage;
    final /* synthetic */ IHealthListener $listener;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthService$binder$1$unregisterHealthListener$1(String[] strArr, HealthService healthService, IHealthListener iHealthListener, Continuation continuation) {
        super(2, continuation);
        this.$callerPackage = strArr;
        this.this$0 = healthService;
        this.$listener = iHealthListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$binder$1$unregisterHealthListener$1(this.$callerPackage, this.this$0, this.$listener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        HealthService$binder$1$unregisterHealthListener$1 healthService$binder$1$unregisterHealthListener$1 = (HealthService$binder$1$unregisterHealthListener$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        healthService$binder$1$unregisterHealthListener$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Log.i("HealthService", "unregisterHealthListener: " + Arrays.toString(this.$callerPackage));
        this.this$0.healthListeners.unregister(this.$listener);
        HealthService healthService = this.this$0;
        healthService.registeredListenerNum$delegate.setValue(healthService, HealthService.$$delegatedProperties[0], Integer.valueOf(healthService.healthListeners.getRegisteredCallbackCount()));
        return Unit.INSTANCE;
    }
}
