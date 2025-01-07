package com.google.android.systemui.biometrics.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3 deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3 = new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3(3, (Continuation) obj3);
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3.L$0 = (Throwable) obj2;
        Unit unit = Unit.INSTANCE;
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }
}
