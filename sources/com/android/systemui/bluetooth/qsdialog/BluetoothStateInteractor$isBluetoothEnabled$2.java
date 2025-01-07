package com.android.systemui.bluetooth.qsdialog;

import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothStateInteractor$isBluetoothEnabled$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BluetoothStateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothStateInteractor$isBluetoothEnabled$2(BluetoothStateInteractor bluetoothStateInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothStateInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothStateInteractor$isBluetoothEnabled$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothStateInteractor$isBluetoothEnabled$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LocalBluetoothAdapter localBluetoothAdapter;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LocalBluetoothManager localBluetoothManager = this.this$0.localBluetoothManager;
        boolean z = false;
        if (localBluetoothManager != null && (localBluetoothAdapter = localBluetoothManager.mLocalAdapter) != null && localBluetoothAdapter.mAdapter.isEnabled()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
