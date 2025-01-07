package com.google.android.systemui.power;

import android.util.Log;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ChargeLimitController$withGoogleBattery$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $action;
    int label;
    final /* synthetic */ ChargeLimitController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeLimitController$withGoogleBattery$2(ChargeLimitController chargeLimitController, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeLimitController;
        this.$action = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ChargeLimitController$withGoogleBattery$2(this.this$0, this.$action, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ChargeLimitController$withGoogleBattery$2 chargeLimitController$withGoogleBattery$2 = (ChargeLimitController$withGoogleBattery$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        chargeLimitController$withGoogleBattery$2.invokeSuspend(unit);
        return unit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.google.android.systemui.power.ChargeLimitController$withGoogleBattery$2] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.google.android.systemui.power.ChargeLimitController$withGoogleBattery$2] */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Exception, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r6v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ChargeLimitController$withGoogleBattery$2$deathRecipient$1 chargeLimitController$withGoogleBattery$2$deathRecipient$1 = ChargeLimitController$withGoogleBattery$2$deathRecipient$1.INSTANCE;
        GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl = this.this$0.googleBatteryManagerWrapper;
        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(chargeLimitController$withGoogleBattery$2$deathRecipient$1);
        Unit unit = Unit.INSTANCE;
        try {
            try {
            } catch (Exception e) {
                this = e;
                Log.w("ChargeLimitController", "withGoogleBattery: destroyHalInterface failed: ", this);
            }
            if (initHalInterface == null) {
                Log.w("ChargeLimitController", "withGoogleBattery: googleBattery is null");
                return unit;
            }
            try {
                this.$action.invoke(initHalInterface);
                GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl2 = this.this$0.googleBatteryManagerWrapper;
                GoogleBatteryManager.destroyHalInterface(initHalInterface, chargeLimitController$withGoogleBattery$2$deathRecipient$1);
                this = googleBatteryManagerWrapperImpl2;
            } catch (Exception e2) {
                Log.e("ChargeLimitController", "withGoogleBattery: failed to run action", e2);
                GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl3 = this.this$0.googleBatteryManagerWrapper;
                GoogleBatteryManager.destroyHalInterface(initHalInterface, chargeLimitController$withGoogleBattery$2$deathRecipient$1);
                this = googleBatteryManagerWrapperImpl3;
            }
            return unit;
        } catch (Throwable th) {
            try {
                GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl4 = this.this$0.googleBatteryManagerWrapper;
                GoogleBatteryManager.destroyHalInterface(initHalInterface, chargeLimitController$withGoogleBattery$2$deathRecipient$1);
            } catch (Exception e3) {
                Log.w("ChargeLimitController", "withGoogleBattery: destroyHalInterface failed: ", e3);
            }
            throw th;
        }
    }
}
