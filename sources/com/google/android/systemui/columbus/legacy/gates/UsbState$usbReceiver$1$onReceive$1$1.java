package com.google.android.systemui.columbus.legacy.gates;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UsbState$usbReceiver$1$onReceive$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $newUsbConnected;
    int label;
    final /* synthetic */ UsbState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UsbState$usbReceiver$1$onReceive$1$1(boolean z, UsbState usbState, Continuation continuation) {
        super(2, continuation);
        this.$newUsbConnected = z;
        this.this$0 = usbState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UsbState$usbReceiver$1$onReceive$1$1(this.$newUsbConnected, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UsbState$usbReceiver$1$onReceive$1$1 usbState$usbReceiver$1$onReceive$1$1 = (UsbState$usbReceiver$1$onReceive$1$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        usbState$usbReceiver$1$onReceive$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.$newUsbConnected;
        UsbState usbState = this.this$0;
        if (z != usbState.usbConnected) {
            usbState.usbConnected = z;
            usbState.blockForMillis(usbState.gateDuration);
        }
        return Unit.INSTANCE;
    }
}
