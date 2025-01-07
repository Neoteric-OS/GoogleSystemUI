package com.android.systemui.statusbar.notification.shelf.ui.viewbinder;

import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerShelfViewBinder;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationShelfViewBinder$bind$2$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationIconContainerShelfViewBinder $nicBinder;
    final /* synthetic */ NotificationShelf $this_apply;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationShelfViewBinder$bind$2$1$1$1(NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder, NotificationShelf notificationShelf, Continuation continuation) {
        super(2, continuation);
        this.$nicBinder = notificationIconContainerShelfViewBinder;
        this.$this_apply = notificationShelf;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationShelfViewBinder$bind$2$1$1$1(this.$nicBinder, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationShelfViewBinder$bind$2$1$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder = this.$nicBinder;
            NotificationIconContainer notificationIconContainer = this.$this_apply.mShelfIcons;
            this.label = 1;
            if (notificationIconContainerShelfViewBinder.bind(notificationIconContainer, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
