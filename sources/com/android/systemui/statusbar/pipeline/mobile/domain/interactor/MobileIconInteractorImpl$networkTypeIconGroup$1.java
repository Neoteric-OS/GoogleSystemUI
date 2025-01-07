package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconInteractorImpl$networkTypeIconGroup$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ MobileConnectionRepository $connectionRepository;
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ MobileIconInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileIconInteractorImpl$networkTypeIconGroup$1(MobileIconInteractorImpl mobileIconInteractorImpl, MobileConnectionRepository mobileConnectionRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mobileIconInteractorImpl;
        this.$connectionRepository = mobileConnectionRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        MobileIconInteractorImpl$networkTypeIconGroup$1 mobileIconInteractorImpl$networkTypeIconGroup$1 = new MobileIconInteractorImpl$networkTypeIconGroup$1(this.this$0, this.$connectionRepository, (Continuation) obj3);
        mobileIconInteractorImpl$networkTypeIconGroup$1.L$0 = (SignalIcon$MobileIconGroup) obj;
        mobileIconInteractorImpl$networkTypeIconGroup$1.Z$0 = booleanValue;
        return mobileIconInteractorImpl$networkTypeIconGroup$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SignalIcon$MobileIconGroup signalIcon$MobileIconGroup = (SignalIcon$MobileIconGroup) this.L$0;
        if (!this.Z$0) {
            return new NetworkTypeIconModel.DefaultIcon(signalIcon$MobileIconGroup);
        }
        int overrideFor = this.this$0.carrierIdOverrides.getOverrideFor(((Number) this.$connectionRepository.getCarrierId().getValue()).intValue(), this.this$0.context.getResources(), signalIcon$MobileIconGroup.name);
        return overrideFor > 0 ? new NetworkTypeIconModel.OverriddenIcon(signalIcon$MobileIconGroup, overrideFor) : new NetworkTypeIconModel.DefaultIcon(signalIcon$MobileIconGroup);
    }
}
