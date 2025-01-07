package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationEntry $entry;
    final /* synthetic */ Set $notificationsSeenWhileLocked;
    final /* synthetic */ Map $trackingJobsByEntry;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$1$1(NotificationEntry notificationEntry, Set set, OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Map map, Continuation continuation) {
        super(2, continuation);
        this.$entry = notificationEntry;
        this.$notificationsSeenWhileLocked = set;
        this.this$0 = originalUnseenKeyguardCoordinator;
        this.$trackingJobsByEntry = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$1$1(this.$entry, this.$notificationsSeenWhileLocked, this.this$0, this.$trackingJobsByEntry, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Set set = this.$notificationsSeenWhileLocked;
            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = this.this$0;
            Map map = this.$trackingJobsByEntry;
            NotificationEntry notificationEntry = this.$entry;
            this.label = 1;
            if (OriginalUnseenKeyguardCoordinator$trackSeenNotificationsWhileLockedAndNotDozing$2.access$invokeSuspend$trackSeenDurationThreshold(set, originalUnseenKeyguardCoordinator, map, notificationEntry, this) == coroutineSingletons) {
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
