package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.StatusBarState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj3).booleanValue();
        SharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2 sharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2 = new SharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2(4, (Continuation) obj4);
        sharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2.Z$0 = booleanValue;
        sharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2.L$0 = (StatusBarState) obj2;
        sharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2.Z$1 = booleanValue2;
        return sharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(((StatusBarState) this.L$0) == StatusBarState.SHADE_LOCKED || !this.Z$0 || this.Z$1);
    }
}
