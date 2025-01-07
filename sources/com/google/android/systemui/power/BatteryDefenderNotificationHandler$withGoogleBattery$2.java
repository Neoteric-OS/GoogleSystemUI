package com.google.android.systemui.power;

import android.util.Log;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
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
final class BatteryDefenderNotificationHandler$withGoogleBattery$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $action;
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$withGoogleBattery$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
        this.$action = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$withGoogleBattery$2(this.this$0, this.$action, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        BatteryDefenderNotificationHandler$withGoogleBattery$2 batteryDefenderNotificationHandler$withGoogleBattery$2 = (BatteryDefenderNotificationHandler$withGoogleBattery$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        batteryDefenderNotificationHandler$withGoogleBattery$2.invokeSuspend(unit);
        return unit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl] */
    /* JADX WARN: Type inference failed for: r5v9, types: [com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1 = BatteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1.INSTANCE;
        this.this$0.googleBatteryManagerWrapper.getClass();
        IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
        Unit unit = Unit.INSTANCE;
        try {
            if (initHalInterface == null) {
                Log.w("BatteryDefenderNotification", "operation failed. cannot init hal interface");
                return unit;
            }
            try {
                this.$action.invoke(initHalInterface);
                this = this.this$0.googleBatteryManagerWrapper;
            } catch (Exception e) {
                Log.e("BatteryDefenderNotification", "operation error: ", e);
                this = this.this$0.googleBatteryManagerWrapper;
            }
            this.getClass();
            GoogleBatteryManager.destroyHalInterface(initHalInterface, batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
            return unit;
        } catch (Throwable th) {
            this.this$0.googleBatteryManagerWrapper.getClass();
            GoogleBatteryManager.destroyHalInterface(initHalInterface, batteryDefenderNotificationHandler$withGoogleBattery$2$deathRecipient$1);
            throw th;
        }
    }
}
