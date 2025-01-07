package com.google.android.systemui.biometrics.repository;

import com.google.android.systemui.biometrics.DeviceEntryUnlockEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4 deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4 = new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4(2, continuation);
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4.L$0 = obj;
        return deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4 deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4 = (DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4) create((DeviceEntryUnlockEvent) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4.invokeSuspend(unit);
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
