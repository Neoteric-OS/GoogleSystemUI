package com.android.systemui.biometrics.ui.viewmodel;

import com.android.keyguard.logging.DeviceEntryIconLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1 extends SuspendLambda implements Function4 {
    final /* synthetic */ DeviceEntryIconLogger $logger;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1(DeviceEntryIconLogger deviceEntryIconLogger, Continuation continuation) {
        super(4, continuation);
        this.$logger = deviceEntryIconLogger;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        DeviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1 deviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1 = new DeviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1(this.$logger, (Continuation) obj4);
        deviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1.Z$0 = booleanValue;
        deviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1.Z$1 = booleanValue2;
        deviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1.Z$2 = booleanValue3;
        return deviceEntryUdfpsTouchOverlayViewModel$shouldHandleTouches$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        boolean z4 = (z && !z3) || z2;
        this.$logger.logDeviceEntryUdfpsTouchOverlayShouldHandleTouches(z4, z, z2, z3);
        return Boolean.valueOf(z4);
    }
}
