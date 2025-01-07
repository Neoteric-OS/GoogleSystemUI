package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceUnlockedInteractor$deviceUnlockStatus$1$4 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceUnlockedInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceUnlockedInteractor$deviceUnlockStatus$1$4(DeviceUnlockedInteractor deviceUnlockedInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceUnlockedInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceUnlockedInteractor$deviceUnlockStatus$1$4 deviceUnlockedInteractor$deviceUnlockStatus$1$4 = new DeviceUnlockedInteractor$deviceUnlockStatus$1$4(this.this$0, continuation);
        deviceUnlockedInteractor$deviceUnlockStatus$1$4.L$0 = obj;
        return deviceUnlockedInteractor$deviceUnlockStatus$1$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceUnlockedInteractor$deviceUnlockStatus$1$4) create((Triple) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Triple triple = (Triple) this.L$0;
        return ((!((Boolean) triple.component1()).booleanValue() || ((Boolean) triple.component3()).booleanValue()) && !((Boolean) triple.component2()).booleanValue()) ? new DeviceUnlockedInteractor$special$$inlined$map$1(this.this$0.deviceUnlockSource, 2) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new DeviceUnlockStatus(false, null));
    }
}
