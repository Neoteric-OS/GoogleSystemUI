package com.google.android.systemui.power.batteryhealth;

import android.util.Log;
import com.android.settingslib.Utils;
import com.google.android.systemui.power.IncompatibleChargerNotification;
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
final class HealthService$binder$1$getIncompatibleChargerData$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String[] $callerPackage;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthService$binder$1$getIncompatibleChargerData$1(String[] strArr, HealthService healthService, Continuation continuation) {
        super(2, continuation);
        this.$callerPackage = strArr;
        this.this$0 = healthService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$binder$1$getIncompatibleChargerData$1(this.$callerPackage, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthService$binder$1$getIncompatibleChargerData$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Log.i("HealthService", "getIncompatibleChargingState: " + Arrays.toString(this.$callerPackage));
        return new IncompatibleChargerData(IncompatibleChargerNotification.getSharedPreferences(this.this$0.context).getLong(IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME, 0L), IncompatibleChargerNotification.getSharedPreferences(this.this$0.context).getLong(IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME, 0L), Utils.containsIncompatibleChargers(this.this$0.context, "HealthService"));
    }
}
