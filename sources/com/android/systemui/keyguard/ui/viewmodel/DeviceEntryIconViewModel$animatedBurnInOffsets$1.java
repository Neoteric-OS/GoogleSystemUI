package com.android.systemui.keyguard.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewModel$animatedBurnInOffsets$1 extends SuspendLambda implements Function3 {
    /* synthetic */ float F$0;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceEntryIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryIconViewModel$animatedBurnInOffsets$1(DeviceEntryIconViewModel deviceEntryIconViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = deviceEntryIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        float floatValue = ((Number) obj2).floatValue();
        DeviceEntryIconViewModel$animatedBurnInOffsets$1 deviceEntryIconViewModel$animatedBurnInOffsets$1 = new DeviceEntryIconViewModel$animatedBurnInOffsets$1(this.this$0, (Continuation) obj3);
        deviceEntryIconViewModel$animatedBurnInOffsets$1.L$0 = (BurnInOffsets) obj;
        deviceEntryIconViewModel$animatedBurnInOffsets$1.F$0 = floatValue;
        return deviceEntryIconViewModel$animatedBurnInOffsets$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BurnInOffsets burnInOffsets = (BurnInOffsets) this.L$0;
        float f = this.F$0;
        return new BurnInOffsets(this.this$0.intEvaluator.evaluate(f, new Integer(0), new Integer(burnInOffsets.x)).intValue(), this.this$0.floatEvaluator.evaluate(f, (Number) new Integer(0), (Number) new Float(burnInOffsets.progress)).floatValue(), this.this$0.intEvaluator.evaluate(f, new Integer(0), new Integer(burnInOffsets.y)).intValue());
    }
}
