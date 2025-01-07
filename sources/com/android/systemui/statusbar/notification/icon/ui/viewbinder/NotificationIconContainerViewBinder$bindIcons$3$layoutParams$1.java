package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.widget.FrameLayout;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationIconContainerViewBinder$bindIcons$3$layoutParams$1 extends SuspendLambda implements Function4 {
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    /* synthetic */ int I$2;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        int intValue = ((Number) obj).intValue();
        int intValue2 = ((Number) obj2).intValue();
        int intValue3 = ((Number) obj3).intValue();
        NotificationIconContainerViewBinder$bindIcons$3$layoutParams$1 notificationIconContainerViewBinder$bindIcons$3$layoutParams$1 = new NotificationIconContainerViewBinder$bindIcons$3$layoutParams$1(4, (Continuation) obj4);
        notificationIconContainerViewBinder$bindIcons$3$layoutParams$1.I$0 = intValue;
        notificationIconContainerViewBinder$bindIcons$3$layoutParams$1.I$1 = intValue2;
        notificationIconContainerViewBinder$bindIcons$3$layoutParams$1.I$2 = intValue3;
        return notificationIconContainerViewBinder$bindIcons$3$layoutParams$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new FrameLayout.LayoutParams((this.I$1 * 2) + this.I$0, this.I$2);
    }
}
