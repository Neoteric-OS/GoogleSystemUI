package com.google.android.systemui.biometrics.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2 extends SuspendLambda implements Function2 {
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2(2, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2 deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2 = (DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2) create((FlowCollector) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        deviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2.invokeSuspend(unit);
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
