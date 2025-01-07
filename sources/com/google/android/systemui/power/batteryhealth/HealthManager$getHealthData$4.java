package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import vendor.google.google_battery.BatteryHealthStats;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthManager$getHealthData$4 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $healthAlgo;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthData$4(HealthManager healthManager, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
        this.$healthAlgo = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$getHealthData$4(this.this$0, this.$healthAlgo, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthData$4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StandaloneCoroutine standaloneCoroutine = this.this$0.initializer;
            this.label = 1;
            if (standaloneCoroutine.join(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        try {
            IGoogleBattery iGoogleBattery = this.this$0.googleBattery;
            BatteryHealthStats healthStats = iGoogleBattery != null ? ((IGoogleBattery.Stub.Proxy) iGoogleBattery).getHealthStats(this.$healthAlgo) : null;
            HealthData healthData = new HealthData(healthStats != null ? healthStats.healthIndex : -1, healthStats != null ? healthStats.healthImpedanceIndex : -1, healthStats != null ? healthStats.healthCapacityIndex : -1, healthStats != null ? healthStats.healthStatus : -1);
            Log.i("HealthManager", "getHealthData: " + healthData + ", algo: " + this.$healthAlgo);
            return healthData;
        } catch (Exception e) {
            Log.w("HealthManager", "getHealthData: " + e + ", algo: " + this.$healthAlgo);
            return new HealthData(-1, -1, -1, -1);
        }
    }
}
