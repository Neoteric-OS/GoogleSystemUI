package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.log.core.LogLevel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.YieldKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ OriginalUnseenKeyguardCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2(OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = originalUnseenKeyguardCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2 originalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2 = new OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2(this.this$0, continuation);
        originalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2.Z$0 = ((Boolean) obj).booleanValue();
        return originalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((OriginalUnseenKeyguardCoordinator$clearUnseenNotificationsWhenShadeIsExpanded$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z2 = this.Z$0;
            this.Z$0 = z2;
            this.label = 1;
            if (YieldKt.yield(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            z = z2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z = this.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        if (z) {
            KeyguardCoordinatorLogger keyguardCoordinatorLogger = this.this$0.logger;
            keyguardCoordinatorLogger.buffer.log("KeyguardCoordinator", LogLevel.DEBUG, "Notifications have been marked as seen due to shade expansion.", null);
            this.this$0.unseenNotifications.clear();
        }
        return Unit.INSTANCE;
    }
}
