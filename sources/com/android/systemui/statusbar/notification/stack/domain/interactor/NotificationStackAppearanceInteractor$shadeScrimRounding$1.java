package com.android.systemui.statusbar.notification.stack.domain.interactor;

import com.android.systemui.shade.shared.model.ShadeMode;
import com.android.systemui.statusbar.notification.stack.shared.model.ShadeScrimRounding;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationStackAppearanceInteractor$shadeScrimRounding$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        NotificationStackAppearanceInteractor$shadeScrimRounding$1 notificationStackAppearanceInteractor$shadeScrimRounding$1 = new NotificationStackAppearanceInteractor$shadeScrimRounding$1(3, (Continuation) obj3);
        notificationStackAppearanceInteractor$shadeScrimRounding$1.L$0 = (ShadeMode) obj;
        notificationStackAppearanceInteractor$shadeScrimRounding$1.Z$0 = booleanValue;
        return notificationStackAppearanceInteractor$shadeScrimRounding$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new ShadeScrimRounding((Intrinsics.areEqual((ShadeMode) this.L$0, ShadeMode.Split.INSTANCE) && this.Z$0) ? false : true, !Intrinsics.areEqual(r4, ShadeMode.Single.INSTANCE));
    }
}
