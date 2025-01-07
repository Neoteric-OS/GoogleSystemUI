package com.android.systemui.keyguard.domain.interactor;

import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2 deviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2 = new DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2(2, continuation);
        deviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2.Z$0 = ((Boolean) obj).booleanValue();
        return deviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2 deviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2 = (DeviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        deviceEntrySideFpsOverlayInteractor$showIndicatorForDeviceEntry$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("showIndicatorForDeviceEntry updated: ", "DeviceEntrySideFpsOverlayInteractor", this.Z$0);
        return Unit.INSTANCE;
    }
}
