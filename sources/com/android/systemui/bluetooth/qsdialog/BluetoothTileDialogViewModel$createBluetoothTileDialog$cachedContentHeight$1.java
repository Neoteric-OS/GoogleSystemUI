package com.android.systemui.bluetooth.qsdialog;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothTileDialogViewModel$createBluetoothTileDialog$cachedContentHeight$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BluetoothTileDialogViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothTileDialogViewModel$createBluetoothTileDialog$cachedContentHeight$1(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothTileDialogViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothTileDialogViewModel$createBluetoothTileDialog$cachedContentHeight$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothTileDialogViewModel$createBluetoothTileDialog$cachedContentHeight$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Integer(this.this$0.sharedPreferences.getInt("BluetoothTileDialogContentHeight", -2));
    }
}
