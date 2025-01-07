package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothAutoOnRepository$setAutoOn$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $value;
    int label;
    final /* synthetic */ BluetoothAutoOnRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothAutoOnRepository$setAutoOn$2(BluetoothAutoOnRepository bluetoothAutoOnRepository, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothAutoOnRepository;
        this.$value = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothAutoOnRepository$setAutoOn$2(this.this$0, this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothAutoOnRepository$setAutoOn$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer num;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            BluetoothAdapter bluetoothAdapter = this.this$0.bluetoothAdapter;
            if (bluetoothAdapter == null) {
                return null;
            }
            bluetoothAdapter.setAutoOnEnabled(this.$value);
            return Unit.INSTANCE;
        } catch (Exception e) {
            num = new Integer(Log.e("BluetoothAutoOnRepository", "Error calling setAutoOnEnabled", e));
            return num;
        } catch (NoSuchMethodError e2) {
            num = new Integer(Log.e("BluetoothAutoOnRepository", "Non-existed api setAutoOn", e2));
            return num;
        }
    }
}
