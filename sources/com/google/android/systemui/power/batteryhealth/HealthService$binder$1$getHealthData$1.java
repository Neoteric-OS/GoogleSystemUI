package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthService$binder$1$getHealthData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $callerPackage;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthService$binder$1$getHealthData$1(String[] strArr, HealthService healthService, Continuation continuation) {
        super(2, continuation);
        this.$callerPackage = strArr;
        this.this$0 = healthService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$binder$1$getHealthData$1(this.$callerPackage, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthService$binder$1$getHealthData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                return (HealthData) obj;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return (HealthData) obj;
        }
        ResultKt.throwOnFailure(obj);
        Log.i("HealthService", "getHealthData: " + Arrays.toString(this.$callerPackage));
        String[] strArr = this.$callerPackage;
        if (strArr == null || !ArraysKt.contains(strArr, "com.google.android.apps.diagnosticstool")) {
            HealthManager healthManager = this.this$0.healthManager;
            this.label = 2;
            healthManager.getClass();
            obj = BuildersKt.withContext(healthManager.bgDispatcher, new HealthManager$getOnBackground$2(null, new HealthManager$getHealthData$2(healthManager, null)), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
            return (HealthData) obj;
        }
        HealthManager healthManager2 = this.this$0.healthManager;
        this.label = 1;
        healthManager2.getClass();
        obj = BuildersKt.withContext(healthManager2.bgDispatcher, new HealthManager$getOnBackground$2(null, new HealthManager$getHealthData$4(healthManager2, 8, null)), this);
        if (obj == coroutineSingletons) {
            return coroutineSingletons;
        }
        return (HealthData) obj;
    }
}
