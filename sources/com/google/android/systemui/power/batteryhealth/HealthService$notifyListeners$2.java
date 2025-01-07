package com.google.android.systemui.power.batteryhealth;

import android.os.RemoteException;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthService$notifyListeners$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $block;
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthService$notifyListeners$2(HealthService healthService, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthService;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$notifyListeners$2(this.this$0, this.$block, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        HealthService$notifyListeners$2 healthService$notifyListeners$2 = (HealthService$notifyListeners$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        healthService$notifyListeners$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int beginBroadcast = this.this$0.healthListeners.beginBroadcast();
        ExifInterface$$ExternalSyntheticOutline0.m("On BHI updates, listener num: ", "HealthService", beginBroadcast);
        for (int i = 0; i < beginBroadcast; i++) {
            try {
                this.$block.invoke(this.this$0.healthListeners.getBroadcastItem(i));
            } catch (RemoteException e) {
                Log.w("HealthService", "Fail to callback registered listener: ", e);
            }
        }
        this.this$0.healthListeners.finishBroadcast();
        return Unit.INSTANCE;
    }
}
