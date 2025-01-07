package com.google.android.systemui.power;

import android.content.SharedPreferences;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$loadDefenderStartTimestamp$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
        int i = BatteryDefenderNotificationHandler.$r8$clinit;
        long j = ((SharedPreferences) batteryDefenderNotificationHandler.sharedPreferences$delegate.getValue()).getLong("trigger_time", -1L);
        Log.d("BatteryDefenderNotification", "loadDefenderStartTimestamp: " + j);
        return new Long(j);
    }
}
