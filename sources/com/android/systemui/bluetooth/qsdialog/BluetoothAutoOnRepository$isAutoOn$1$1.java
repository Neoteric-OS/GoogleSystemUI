package com.android.systemui.bluetooth.qsdialog;

import android.util.Log;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothAutoOnRepository$isAutoOn$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BluetoothEventManager $eventManager;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothAutoOnRepository$isAutoOn$1$1(BluetoothEventManager bluetoothEventManager, Continuation continuation) {
        super(2, continuation);
        this.$eventManager = bluetoothEventManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothAutoOnRepository$isAutoOn$1$1 bluetoothAutoOnRepository$isAutoOn$1$1 = new BluetoothAutoOnRepository$isAutoOn$1$1(this.$eventManager, continuation);
        bluetoothAutoOnRepository$isAutoOn$1$1.L$0 = obj;
        return bluetoothAutoOnRepository$isAutoOn$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothAutoOnRepository$isAutoOn$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.bluetooth.BluetoothCallback, com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$isAutoOn$1$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new BluetoothCallback() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$isAutoOn$1$1$listener$1
                @Override // com.android.settingslib.bluetooth.BluetoothCallback
                public final void onAutoOnStateChanged(int i2) {
                    if (i2 == 1 || i2 == 2) {
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(i2 == 2));
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("BluetoothAutoOnRepository", "Failed to send onAutoOnStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                        }
                    }
                }
            };
            this.$eventManager.registerCallback(r1);
            final BluetoothEventManager bluetoothEventManager = this.$eventManager;
            Function0 function0 = new Function0() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$isAutoOn$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BluetoothEventManager.this.unregisterCallback(r1);
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
