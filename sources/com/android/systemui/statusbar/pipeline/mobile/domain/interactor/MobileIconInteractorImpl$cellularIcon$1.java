package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconInteractorImpl$cellularIcon$1 extends SuspendLambda implements Function5 {
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        int intValue = ((Number) obj).intValue();
        int intValue2 = ((Number) obj2).intValue();
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        boolean booleanValue2 = ((Boolean) obj4).booleanValue();
        MobileIconInteractorImpl$cellularIcon$1 mobileIconInteractorImpl$cellularIcon$1 = new MobileIconInteractorImpl$cellularIcon$1(5, (Continuation) obj5);
        mobileIconInteractorImpl$cellularIcon$1.I$0 = intValue;
        mobileIconInteractorImpl$cellularIcon$1.I$1 = intValue2;
        mobileIconInteractorImpl$cellularIcon$1.Z$0 = booleanValue;
        mobileIconInteractorImpl$cellularIcon$1.Z$1 = booleanValue2;
        return mobileIconInteractorImpl$cellularIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new SignalIconModel.Cellular(this.I$0, this.I$1, this.Z$0, this.Z$1);
    }
}
