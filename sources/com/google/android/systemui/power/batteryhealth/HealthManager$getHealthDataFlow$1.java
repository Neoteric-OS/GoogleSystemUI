package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthManager$getHealthDataFlow$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HealthManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthManager$getHealthDataFlow$1(HealthManager healthManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        HealthManager$getHealthDataFlow$1 healthManager$getHealthDataFlow$1 = new HealthManager$getHealthDataFlow$1(this.this$0, continuation);
        healthManager$getHealthDataFlow$1.L$0 = obj;
        return healthManager$getHealthDataFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthManager$getHealthDataFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.SharedPreferences$OnSharedPreferenceChangeListener, com.google.android.systemui.power.batteryhealth.HealthManager$getHealthDataFlow$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.systemui.power.batteryhealth.HealthManager$getHealthDataFlow$1$listener$1
                @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                    if (sharedPreferences != null) {
                        ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new Pair(sharedPreferences, str));
                    }
                }
            };
            this.this$0.context.getApplicationContext().getSharedPreferences("health_prefs", 0).registerOnSharedPreferenceChangeListener(r1);
            final HealthManager healthManager = this.this$0;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.power.batteryhealth.HealthManager$getHealthDataFlow$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    HealthManager.this.context.getApplicationContext().getSharedPreferences("health_prefs", 0).unregisterOnSharedPreferenceChangeListener(r1);
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
