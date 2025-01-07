package com.android.systemui.deviceentry.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DeviceEntryFaceAuthRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3(DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryFaceAuthRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3 deviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3 = new DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3(this.this$0, continuation);
        deviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3.Z$0 = ((Boolean) obj).booleanValue();
        return deviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3 deviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3 = (DeviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        deviceEntryFaceAuthRepositoryImpl$observeFaceAuthResettingConditions$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.Z$0) {
            DeviceEntryFaceAuthRepositoryImpl.access$clearPendingAuthRequest(this.this$0, "Resetting auth status");
            StateFlowImpl stateFlowImpl = this.this$0._isAuthenticated;
            Boolean bool = Boolean.FALSE;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
            DeviceEntryFaceAuthRepositoryImpl deviceEntryFaceAuthRepositoryImpl = this.this$0;
            deviceEntryFaceAuthRepositoryImpl.retryCount = 0;
            StandaloneCoroutine standaloneCoroutine = deviceEntryFaceAuthRepositoryImpl.halErrorRetryJob;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
        }
        return Unit.INSTANCE;
    }
}
