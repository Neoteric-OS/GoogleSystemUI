package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FullMobileConnectionRepository$activeRepo$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ FullMobileConnectionRepository $this_run;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullMobileConnectionRepository$activeRepo$1$1(FullMobileConnectionRepository fullMobileConnectionRepository, Continuation continuation) {
        super(2, continuation);
        this.$this_run = fullMobileConnectionRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FullMobileConnectionRepository$activeRepo$1$1 fullMobileConnectionRepository$activeRepo$1$1 = new FullMobileConnectionRepository$activeRepo$1$1(this.$this_run, continuation);
        fullMobileConnectionRepository$activeRepo$1$1.Z$0 = ((Boolean) obj).booleanValue();
        return fullMobileConnectionRepository$activeRepo$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((FullMobileConnectionRepository$activeRepo$1$1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.Z$0 ? (MobileConnectionRepository) this.$this_run.carrierMergedRepo$delegate.getValue() : (MobileConnectionRepository) this.$this_run.mobileRepo$delegate.getValue();
    }
}
