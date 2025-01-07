package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileIconInteractorImpl$cellularShownLevel$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        int intValue = ((Number) obj).intValue();
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        boolean booleanValue2 = ((Boolean) obj3).booleanValue();
        MobileIconInteractorImpl$cellularShownLevel$1 mobileIconInteractorImpl$cellularShownLevel$1 = new MobileIconInteractorImpl$cellularShownLevel$1(4, (Continuation) obj4);
        mobileIconInteractorImpl$cellularShownLevel$1.I$0 = intValue;
        mobileIconInteractorImpl$cellularShownLevel$1.Z$0 = booleanValue;
        mobileIconInteractorImpl$cellularShownLevel$1.Z$1 = booleanValue2;
        return mobileIconInteractorImpl$cellularShownLevel$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int i = this.I$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        if (!z) {
            i = 0;
        } else if (z2) {
            i++;
        }
        return new Integer(i);
    }
}
