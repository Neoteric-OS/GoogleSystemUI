package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthManager$saveAsHealthData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Integer $capacity;
    final /* synthetic */ Integer $health;
    final /* synthetic */ SharedPreferences $healthPrefs;
    final /* synthetic */ Integer $performance;
    final /* synthetic */ Integer $status;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthManager$saveAsHealthData$2(Integer num, Integer num2, Integer num3, Integer num4, SharedPreferences sharedPreferences, HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.$health = num;
        this.$performance = num2;
        this.$capacity = num3;
        this.$status = num4;
        this.$healthPrefs = sharedPreferences;
        this.this$0 = healthManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthManager$saveAsHealthData$2(this.$health, this.$performance, this.$capacity, this.$status, this.$healthPrefs, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$saveAsHealthData$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Log.i("HealthManager", "Got BHI, hi:" + this.$health + ", pi:" + this.$performance + ", ci:" + this.$capacity + ", hs:" + this.$status);
            SharedPreferences sharedPreferences = this.$healthPrefs;
            HealthManager healthManager = this.this$0;
            Integer num = this.$health;
            Integer num2 = this.$performance;
            Integer num3 = this.$capacity;
            Integer num4 = this.$status;
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (num != null) {
                edit.putInt("health_index", num.intValue());
            }
            if (num2 != null) {
                edit.putInt("perf_index", num2.intValue());
            }
            if (num3 != null) {
                edit.putInt("capacity_index", num3.intValue());
            }
            if (num4 != null) {
                edit.putInt("health_status", num4.intValue());
            }
            edit.apply();
            this.label = 1;
            boolean z = HealthManager.healthDebugEnabled;
            healthManager.getClass();
            obj = CoroutineScopeKt.coroutineScope(this, new HealthManager$getHealthDataFromPrefs$2(sharedPreferences, null));
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (HealthData) obj;
    }
}
