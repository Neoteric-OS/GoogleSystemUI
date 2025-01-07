package com.android.systemui.bluetooth.qsdialog;

import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothStateInteractor$setBluetoothEnabled$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $value;
    int label;
    final /* synthetic */ BluetoothStateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothStateInteractor$setBluetoothEnabled$2(BluetoothStateInteractor bluetoothStateInteractor, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothStateInteractor;
        this.$value = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothStateInteractor$setBluetoothEnabled$2(this.this$0, this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothStateInteractor$setBluetoothEnabled$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothStateInteractor bluetoothStateInteractor;
        LocalBluetoothManager localBluetoothManager;
        LocalBluetoothAdapter localBluetoothAdapter;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BluetoothStateInteractor bluetoothStateInteractor2 = this.this$0;
            this.label = 1;
            bluetoothStateInteractor2.getClass();
            obj = BuildersKt.withContext(bluetoothStateInteractor2.backgroundDispatcher, new BluetoothStateInteractor$isBluetoothEnabled$2(bluetoothStateInteractor2, null), this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean z = this.$value;
        if (booleanValue != z && (localBluetoothManager = (bluetoothStateInteractor = this.this$0).localBluetoothManager) != null && (localBluetoothAdapter = localBluetoothManager.mLocalAdapter) != null) {
            if (z) {
                localBluetoothAdapter.mAdapter.enable();
            } else {
                localBluetoothAdapter.mAdapter.disable();
            }
            bluetoothStateInteractor.logger.logBluetoothState(BluetoothStateStage.BLUETOOTH_STATE_VALUE_SET, String.valueOf(z));
        }
        return Unit.INSTANCE;
    }
}
