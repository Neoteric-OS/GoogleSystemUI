package com.android.systemui.bluetooth.qsdialog;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothStateInteractor$bluetoothStateUpdate$2 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BluetoothStateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothStateInteractor$bluetoothStateUpdate$2(BluetoothStateInteractor bluetoothStateInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothStateInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothStateInteractor$bluetoothStateUpdate$2 bluetoothStateInteractor$bluetoothStateUpdate$2 = new BluetoothStateInteractor$bluetoothStateUpdate$2(this.this$0, continuation);
        bluetoothStateInteractor$bluetoothStateUpdate$2.L$0 = obj;
        return bluetoothStateInteractor$bluetoothStateUpdate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothStateInteractor$bluetoothStateUpdate$2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            BluetoothStateInteractor bluetoothStateInteractor = this.this$0;
            this.L$0 = flowCollector;
            this.label = 1;
            bluetoothStateInteractor.getClass();
            obj = BuildersKt.withContext(bluetoothStateInteractor.backgroundDispatcher, new BluetoothStateInteractor$isBluetoothEnabled$2(bluetoothStateInteractor, null), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.L$0 = null;
        this.label = 2;
        if (flowCollector.emit(obj, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
