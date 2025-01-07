package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharedNotificationContainerViewModel$translationY$3 extends SuspendLambda implements Function4 {
    /* synthetic */ float F$0;
    /* synthetic */ float F$1;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        float floatValue = ((Number) obj).floatValue();
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        float floatValue2 = ((Number) obj3).floatValue();
        SharedNotificationContainerViewModel$translationY$3 sharedNotificationContainerViewModel$translationY$3 = new SharedNotificationContainerViewModel$translationY$3(4, (Continuation) obj4);
        sharedNotificationContainerViewModel$translationY$3.F$0 = floatValue;
        sharedNotificationContainerViewModel$translationY$3.Z$0 = booleanValue;
        sharedNotificationContainerViewModel$translationY$3.F$1 = floatValue2;
        return sharedNotificationContainerViewModel$translationY$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Float(this.Z$0 ? this.F$0 + this.F$1 : 0.0f);
    }
}
