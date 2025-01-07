package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import com.android.settingslib.SignalIcon$MobileIconGroup;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MobileConnectionsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2(MobileConnectionsRepositoryImpl mobileConnectionsRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mobileConnectionsRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2 mobileConnectionsRepositoryImpl$defaultMobileIconGroup$2 = new MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2(this.this$0, continuation);
        mobileConnectionsRepositoryImpl$defaultMobileIconGroup$2.L$0 = obj;
        return mobileConnectionsRepositoryImpl$defaultMobileIconGroup$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2 mobileConnectionsRepositoryImpl$defaultMobileIconGroup$2 = (MobileConnectionsRepositoryImpl$defaultMobileIconGroup$2) create((SignalIcon$MobileIconGroup) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        mobileConnectionsRepositoryImpl$defaultMobileIconGroup$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.logger.logDefaultMobileIconGroup((SignalIcon$MobileIconGroup) this.L$0);
        return Unit.INSTANCE;
    }
}
