package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
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
final class BluetoothStateInteractor$bluetoothStateUpdate$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BluetoothStateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothStateInteractor$bluetoothStateUpdate$1(BluetoothStateInteractor bluetoothStateInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothStateInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothStateInteractor$bluetoothStateUpdate$1 bluetoothStateInteractor$bluetoothStateUpdate$1 = new BluetoothStateInteractor$bluetoothStateUpdate$1(this.this$0, continuation);
        bluetoothStateInteractor$bluetoothStateUpdate$1.L$0 = obj;
        return bluetoothStateInteractor$bluetoothStateUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothStateInteractor$bluetoothStateUpdate$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.bluetooth.BluetoothCallback, com.android.systemui.bluetooth.qsdialog.BluetoothStateInteractor$bluetoothStateUpdate$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothEventManager bluetoothEventManager;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final BluetoothStateInteractor bluetoothStateInteractor = this.this$0;
            final ?? r1 = new BluetoothCallback() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothStateInteractor$bluetoothStateUpdate$1$listener$1
                @Override // com.android.settingslib.bluetooth.BluetoothCallback
                public final void onBluetoothStateChanged(int i2) {
                    if (i2 == 10 || i2 == 12) {
                        BluetoothStateInteractor.this.logger.logBluetoothState(BluetoothStateStage.BLUETOOTH_STATE_CHANGE_RECEIVED, BluetoothAdapter.nameForState(i2));
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(i2 == 12));
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("BtStateInteractor", "Failed to send onBluetoothStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                        }
                    }
                }
            };
            LocalBluetoothManager localBluetoothManager = bluetoothStateInteractor.localBluetoothManager;
            if (localBluetoothManager != null && (bluetoothEventManager = localBluetoothManager.mEventManager) != 0) {
                bluetoothEventManager.registerCallback(r1);
            }
            final BluetoothStateInteractor bluetoothStateInteractor2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothStateInteractor$bluetoothStateUpdate$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BluetoothEventManager bluetoothEventManager2;
                    LocalBluetoothManager localBluetoothManager2 = BluetoothStateInteractor.this.localBluetoothManager;
                    if (localBluetoothManager2 != null && (bluetoothEventManager2 = localBluetoothManager2.mEventManager) != null) {
                        bluetoothEventManager2.unregisterCallback(r1);
                    }
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
