package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconInteractorImpl$level$1 extends SuspendLambda implements Function5 {
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int intValue = ((Number) obj2).intValue();
        int intValue2 = ((Number) obj3).intValue();
        boolean booleanValue2 = ((Boolean) obj4).booleanValue();
        MobileIconInteractorImpl$level$1 mobileIconInteractorImpl$level$1 = new MobileIconInteractorImpl$level$1(5, (Continuation) obj5);
        mobileIconInteractorImpl$level$1.Z$0 = booleanValue;
        mobileIconInteractorImpl$level$1.I$0 = intValue;
        mobileIconInteractorImpl$level$1.I$1 = intValue2;
        mobileIconInteractorImpl$level$1.Z$1 = booleanValue2;
        return mobileIconInteractorImpl$level$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        int i = this.I$0;
        int i2 = this.I$1;
        boolean z2 = this.Z$1;
        if (!z && z2) {
            i = i2;
        }
        return new Integer(i);
    }
}
